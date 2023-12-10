window['main'] = {};

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
main.init = function() {
	let render = null;
	let allData = {};
	let count;

	drawTable();

	function getMonths() {
		return new Promise( resolve => {
			ajax({
				url: 'months',
				method: 'GET',
				success: function(data) {
					resolve(new Render(JSON.parse(data)));
				}
			});
		});
	}

	function getHeader(when, cb) {
		ajax('stats/' + when, 'GET', null, function(data) {
			allData[when] = data;
			if(--count == 0) {
				cb(); // callback
			}
		});
	}

	function drawHeaders() {
		let l = render.getMonths();
		count = monthCount;
		let callback = function() {
			return drawColumns('init');
		};
		for(let i = l.length - monthCount; i<l.length; i++) {
			getHeader(l[i], callback);
		}
	}

	function drawColumns(params) {
		let l = render.getMonths();
		for(let i = l.length - monthCount; i<l.length; i++) {
			let yrmon = l[i];
			render.column(yrmon, allData[yrmon]);
		}
	}

	async function drawTable() {
		render = await getMonths();
		window['render'] = render;
		ajax({
			url: 'exercises',
			method: 'GET',
			success: function(data) {
				render.categories(JSON.parse(data));
				drawHeaders();
			}
		});
	}
};

document.addEventListener('DOMContentLoaded', (event) => {
	main.init();
});
