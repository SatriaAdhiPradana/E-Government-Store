const {stringify} = require('query-string')

export default {
    postForm: (targetAPIUri, formData) => {
        return fetch(targetAPIUri, {
            method: 'POST',
            credentials: "include",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            body: formData,
        }).then((res) => {
            let res2 = res.json().then((response) => {
                return response
            }).catch((err) => {
                console.log("Err: ", res)
            });
            return res2;
        })
    },
    postJson: (targetAPIUri, formData) => {
        return fetch(targetAPIUri, {
            method: 'POST',
            headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
            body: JSON.stringify(formData),
        }).then((res) => {
            let res2 = res.json().then((response) => {
                return response
            }).catch((err) => {
                console.log("Err: ", res)
            });
            return res2;
        })
    },
    get: (targetAPIUri, query) => {
        if (query) {
            targetAPIUri = `${targetAPIUri}?${stringify(query)}`
        }

        return fetch(targetAPIUri, {
            method: 'get',
            credentials: "include",
        }).then((res) => {
            let res2 = res.json().then((response) => {
                return response
            }).catch((err) => {
                console.log("Err: ", res)
            });
            return res2;
        })
    }
}
