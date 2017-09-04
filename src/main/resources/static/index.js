
function del() {
    let url = "/users/";
    let postData = {
        userId: document.getElementById("userId").value,
        passWord: document.getElementById("passWord").value
    };
    url = url + postData.userId;
    fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'passWord': postData.passWord
        },
        // ,
        // body: JSON.stringify(postData)
    }).then(function (response) {
        let status = response.status;
        if (status === 200) {
            alert("modify ok");
            return response.json();
        }
        else
            throw "modify failed"
    }).catch(function (err) {
        console.log(err);
    });
}

function modify() {
    let url = "/users/";
    let postData = {
        userId: document.getElementById("userId").value,
        passWord: document.getElementById("passWord").value
    };
    url = url + postData.userId;
    fetch(url, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'passWord': postData.passWord
        },
        // ,
        // body: JSON.stringify(postData)
    }).then(function (response) {
        let status = response.status;
        if (status === 200) {
            alert("modify ok");
            return response.json();
        }
        else
            throw "modify failed"
    }).catch(function (err) {
        console.log(err);
    });
}

function login() {
    let url = "/users/";
    let postData = {
        userId: document.getElementById("userId").value,
        passWord: document.getElementById("passWord").value
    };
    url = url + postData.userId;
    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'passWord': postData.passWord
        },
        // ,
        // body: JSON.stringify(postData)
    }).then(function (response) {
        let status = response.status;
        if (status === 200) {
            alert("login ok");
            return response.json();
        }
        else
            throw "login failed"
    }).catch(function (err) {
        console.log(err);
    });
}

function register() {
    let url = "/users";
    let postData = {
        userId: document.getElementById("userId").value,
        passWord: document.getElementById("passWord").value
    };
    fetch(url, {
        method: 'POST',
        // mode: 'cors',
        // credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        // ,
        body: JSON.stringify(postData)
    }).then(function (response) {
        let status = response.status;
        let res = {};
        if (status === 201) {
            res.msg = "注册成功";
            res.data = response.json();
        }
        else if (status === 403) {
            res.msg = "用户已存在";
        }
        else
            throw "注册失败";
        return res;
    }).then(function (res) {
            alert(res.msg);
            if (typeof(res.data) !== "undefined") {
                return res.data;
            }
        }
    ).then(function (data) {
        console.log(data);
    }).catch(function (err) {
        console.log(err);
    });
}