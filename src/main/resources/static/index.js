function login() {
    let url = "/user/insert";
    // let postData = {
    //     star: $('#star').val(),
    //     curLevel: $('#curLevel').val(),
    //     curExp: $('#curExp').val(),
    //     fightExp: $('#fightExp').val(),
    // };
    fetch(url, {
        method: 'GET',
        // mode: 'cors',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        }
        // ,
        // body: JSON.stringify(postData)
    }).then(function (response) {
        if (response.ok)
            return response.json();
        else
            throw "not ok"
    }).then(function (data) {
        console.log(data);
    }).catch(function (err) {
        console.log(err);
    });
}