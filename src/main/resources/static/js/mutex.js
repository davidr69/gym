export default class Mutex {
	constructor() {
		this._busy  = false;
		this._queue = [];
	}

	synchronize(task) {
		this._queue.push(task);
		if (!this._busy) {
			this._dequeue();
		}
	}

	dequeue() {
		this._busy = true;
		let next = this._queue.shift();

		if (next) {
			this._execute(next);
		} else {
			this._busy = false;
		}
	}

	execute(task) {
		let self = this;

		task().then(function() {
			self._dequeue();
		}, function() {
			self._dequeue();
		});
	}
}
