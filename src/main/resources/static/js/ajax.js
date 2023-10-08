function ajax(params) {
	if(! 'url' in params) {
		throw '"url" is required';
	}

	let client = new XMLHttpRequest();
	let method = 'method' in params ? params.method : 'GET';

	client.addEventListener("load", function() {
		if(client.status == 200) {
			if('success' in params) {
				params.success(client.responseText);
			}
		} else {
			if('fail' in params) {
				params.fail(client.response);
			}
		}
	});
	client.open(method, params.url);

	if('data' in params) {
		client.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		let arr = [];
		for([k,v] of Object.entries(params.data)) {
			arr.push(k + '=' + encodeURIComponent(v));
		}
		client.send(arr.join('&'));
	} else {
		client.send();
	}
}
