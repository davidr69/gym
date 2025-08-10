/**
 * The steps that need to be performed are:
 * 1. retrieve a list of all month/years
 * 2. get a list of all exercises
 *
 * After BOTH of those operations complete, the statistics can be populated:
 * 3. retrieve that status for the last n columns iteratively and asynchronously
 *
 * After n column stats are retrieved, rendering can begin. These cannot be done
 * in parallel since the insertion/append point is determined once, and parallel
 * rendering can cause columns to switch progress, changing the insert/append point.
 * When a column render is requested, the rendering class will determine if it needs
 * to be retrieved or if it simply needs to be made visible (previously drawn). If
 * the data needs to be retrieved, the rendering class will determine where it needs
 * to be placed in the DOM.
 * 4. Render n columns iteratively and synchronously
 */
import Render from "./render.js";

export default class Workout {
	render;
	allData;
	count;
	monthCount = 6;

	constructor() {
		this.#init();
	}

	#init = () => {
		this.render = null;
		this.allData = {};
		this.count = 0;
		this.#drawTable();
	}

	#getMonths = () => {
 		fetch('months').then(response => {
			response.json().then(data => {
				this.render = new Render(data);
			});
		});
    }

	#getHeader = (when, cb) => {
		fetch(`stats/${when}`).then(response => {
			response.json().then(data => {
				this.allData[when] = data;
				if(--this.count === 0) {
					cb();
				}
			});
		});
	}

	#drawHeaders = () => {
		let l = this.render.getMonths();
		this.count = this.monthCount;
		let callback = () => {
			return this.#drawColumns('init');
		};
		for(let i = l.length - this.monthCount; i<l.length; i++) {
			this.#getHeader(l[i], callback);
		}
	}

	#drawColumns = (params) => {
		let l = this.render.getMonths();
		for(let i = l.length - this.monthCount; i<l.length; i++) {
			let yrmon = l[i];
			this.render.column(yrmon, this.allData[yrmon]);
		}
	}

	#drawTable = () => {
		this.#getMonths();

		fetch('exercises').then(response => {
			response.json().then(data => {
				this.render.categories(data);
				this.#drawHeaders();
			});
		});
	}
};
