const months = [null, 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];

export default class Render {
	constructor(monthList) {
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
		if(nl.length == 0) {
			let pos = this.monthList.indexOf(yrmon);
			if(pos > 0) {
				nlBefore = document.querySelectorAll('#col' + this.monthList[pos - 1]);
			}
			if(pos < this.monthList.length) {
				nlAfter = document.querySelectorAll('#col' + this.monthList[pos + 1]);
			}
			// all information has been gathered; now need to make a decision
			if(/*nlBefore.length == 0 &&*/ nlAfter.length == 0) {
				// just a simple add
				this.append_column(yrmon, data); // offset from muscle category is 0
			}
		} else {
			//
		}
	}

	append_column(yrmon, data) {
		let tr = document.getElementById('tableRecord');
		var th = document.createElement('th');
		let cellId = 'col' + yrmon;
		th.setAttribute('id', cellId);
		var year = yrmon.substring(0, 4);
		var month = yrmon.substring(4);
		var textNode = document.createTextNode(months[Number(month)] + ' ' + year);
		th.appendChild(textNode);
		tr.appendChild(th);

		document.querySelectorAll('.exercise').forEach(function(el, idx, parent) {
			let td = document.createElement('td');
			td.setAttribute('id', 'col' + yrmon);

			// extract the ID
			let cellID = Number(el.getAttribute('id').substring(2));
			let subdata = data.find(el => el['exerciseID'] == cellID);
			if(subdata == undefined) {
				td.innerHTML = '&nbsp;';
			} else {
				let info;
				let anchor = document.createElement('a');
				anchor.setAttribute('href', 'javascript:render.edit(' + idx + ')');
				if(subdata['weight'] == null) {
					info = subdata['rep1'];
					if(subdata['rep2'] != null) {
						info += ', ' + subdata['rep2'];
					}
				} else {
					if(subdata['rep2'] == null) {
						info = subdata['weight'] + ' / ' + subdata['rep1'];
					} else {
						info = subdata['rep1'] + ', ' + subdata['rep2'] + '(' + subdata['weight'] + ')';
					}
				}
				let text = document.createTextNode(info);
				td.setAttribute('class', 'center');

				anchor.appendChild(text);
				td.appendChild(anchor);

//				td.appendChild(text);
			}

			el.parentNode.appendChild(td);
		});
	}

	categories(data) {
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
				let textNode = document.createTextNode(obj['exercise']);
				td.appendChild(textNode);
				tr.appendChild(td);
/*	
				for(var i = 0; i < monthCount; i++) {
					td = document.createElement('td');
					td.setAttribute('id', 'col' + this.monthList[i]);
					td.innerHTML = '&nbsp;';
					tr.appendChild(td);
				}
*/	
				th.appendChild(tr);
			});
		});
	}

	edit(foo) {
		console.log(foo);
	}
}
