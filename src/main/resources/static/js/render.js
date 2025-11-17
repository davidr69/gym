const months = [null, 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];

export default class Render {
	monthList;

	constructor() { }

	init(monthList) {
		this.monthList = monthList;
	}

	getMonths() {
		return this.monthList;
	}

	column(yrmon, data) {
		let col = '#col' + yrmon;
		let nl = document.querySelectorAll(col);
		let nlBefore;
		let nlAfter;
		if(nl.length === 0) {
			let pos = this.monthList.indexOf(yrmon);
			if(pos > 0) {
				nlBefore = document.querySelectorAll('#col' + this.monthList[pos - 1]);
			}
			if(pos < this.monthList.length) {
				nlAfter = document.querySelectorAll('#col' + this.monthList[pos + 1]);
			}
			// all information has been gathered; now need to make a decision
			if(/*nlBefore.length == 0 &&*/ nlAfter.length === 0) {
				// just a simple add
				this.#append_column(yrmon, data); // offset from muscle category is 0
			}
		} else {
			//
		}
	}

	#append_column(yrmon, data) {
		let tr = document.getElementById('tableRecord');
		let th = document.createElement('th');
		let cellId = 'col' + yrmon;
		th.setAttribute('id', cellId);
		let year = yrmon.substring(0, 4);
		let month = yrmon.substring(4);
		let textNode = document.createTextNode(months[Number(month)] + ' ' + year);
		th.appendChild(textNode);
		tr.appendChild(th);

		let idx = 0;
		for(let el of document.querySelectorAll('.exercise')) {
			let row = data[idx++];
			// row should have {"id":93,"muscle":"Abdominals","muscleId":8,"exercise":"Ab Carver","weight":null,"rep1":null,"rep2":null}
			let td = document.createElement('td');
			td.setAttribute('id', 'col' + yrmon);
			if(row['weight'] === null && row['rep1'] === null && row['rep2'] === null) {
				td.innerHTML = '&nbsp;';
			} else {
				let anchor = document.createElement('a');
				anchor.setAttribute('href', 'javascript:render.edit(' + idx + ')');

				let disp;
				if(row['weight'] === null) {
					disp = row['rep1'];
					if(row['rep2'] !== null) {
						disp += ', ' + row['rep2'];
					}
				} else {
					if(row['rep2'] === null) {
						disp = `${row['weight']} / ${row['rep1']}`;
					} else {
						disp = `${row['rep1']}, ${row['rep2']} (${row['weight']})`;
					}
				}

				let text = document.createTextNode(disp);
				td.setAttribute('class', 'center');

				anchor.appendChild(text);
				td.appendChild(anchor);
			}

			el.parentNode.appendChild(td);
		}

	}

	drawMusclesAndExercises(data) {
		let th = document.getElementById('tableBody').parentNode;
		data.forEach(section => {
			let tr = document.createElement('tr');
			let td = document.createElement('td');
			td.setAttribute('class', 'muscle');
			let textNode = document.createTextNode(section['description']);
			td.appendChild(textNode);
			tr.appendChild(td);
			th.appendChild(tr);

			section['exercises'].forEach(obj => {
				let tr = document.createElement('tr');
				tr.setAttribute('class', 'data');

				let td = document.createElement('td');
				td.setAttribute('class', 'exercise');
				td.setAttribute('id', 'ex' + obj['id']);

				let textNode = document.createTextNode(obj['exerciseName']);
				td.appendChild(textNode);
				tr.appendChild(td);

				th.appendChild(tr);
			});
		});
	}

	edit(foo) {
		console.log(foo);
	}
}
