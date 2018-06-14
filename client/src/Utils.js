function postData(url, data) {
  // Default options are marked with *
	//debugger;
  return fetch(url, {
	headers: { "Content-type": "application/json" },
    body: JSON.stringify(data), // must match 'Content-Type' header
    cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    method: 'POST', // *GET, POST, PUT, DELETE, etc.
    mode: 'cors', // no-cors, cors, *same-origin
    redirect: 'follow', // manual, *follow, error
  })
  .then(response => response.json()) // parses response to JSON
}

export default postData;