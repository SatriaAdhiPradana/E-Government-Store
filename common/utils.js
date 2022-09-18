import adhi from 'adhi-js-sdk';

const linkToMiniApp = (event) => {
    let element = event.currentTarget || event.target.parentNode;
    let url = element.dataset.url || '';
    let type = element.dataset.type || 'h5';

    if (url && url.indexOf('http') < 0 && type === 'h5') {
        url = document.location.protocol + '//' + document.location.host + url;
    }

    console.log('jumpToUrl=' + url);
    if (url) {
        let base_url = decodeURIComponent(url).split('?')[0];
        let params = getQueryObj(url);
        let paramStr = '';
        Object.keys(params).forEach(key => {
            paramStr += paramStr === '' ? '?' + key + '=' + params[key] : '&' + key + '=' + params[key];
        })

        let mini_url = '/pages/webview/webview?url=' + base_url;
        let scene = mini_url + encodeURIComponent(paramStr);

        if (type === 'miniApp') {
            mini_url = url;
            scene = mini_url;
        }

        console.log('newUrl:', mini_url, scene);
        wx.miniProgram.navigateTo({url: scene});
        return false;
    } else {
        return true;
    }
};

const getQueryObj = (link) => {
    let loc = decodeURIComponent(document.location.href);

    if (link) {
        loc = decodeURIComponent(link);
    }
    console.log('loc:', loc);
    let variables = '';
    let variableArr = [];
    let finalArr = [];

    if (loc.indexOf('?') > 0) {
        variables = loc.split('?')[1];
    }

    if (variables.length > 0) {
        variableArr = variables.split('#')[0].split('&');
    }

    for (let i = 0; i < variableArr.length; i++) {
        let obj = {};

        obj.name = variableArr[i].split('=')[0];
        obj.value = variableArr[i].split('=')[1];
        finalArr.push(obj);
    }

    let query_obj = {};

    for (let i = 0; i < finalArr.length; i++) {
        query_obj[finalArr[i].name] = finalArr[i].value;
    }

    return query_obj;
};
let emptyFn = function() {};
const invokeMethod = (obj) => {
    let appInterface = window.yohoInterface;
    if (appInterface) {
        appInterface.triggerEvent(obj.success || emptyFn, obj.fail || emptyFn, {
            method: obj.method,
            arguments: obj.args
        });
    } else {
        console.log('暂不支持，请在YOHO!BUY应用中打开');
    }
}

const getEnv = () =>{
    return new Promise((resolve)=>{
        wx.miniProgram.getEnv(function (res,err){
            if(err){
                resolve(err)
            }
            return resolve(res.miniprogram);
        })
    })
}

const parseUrl= (url)=> {
    let query = {},
        hashs,
        hash,
        i;

    url = (url || '').split('?');
    hashs = (url[1] || '').split('&');

    if (hashs && hashs.length) {
        for (i = 0; i < hashs.length; i++) {
            hash = hashs[i].split('=');
            query[hash[0]] = hash[1];
        }
    }

    return {
        path: url[0],
        query: query
    };
}

const image = (url, width, height, mode) => {
    mode = !isNaN(mode) ? mode : 2;
    url = url || '';
    url = url.replace(/{width}/g, width).replace(/{height}/g, height).replace(/{mode}/g, mode);
    if (url.indexOf('imageView2') > 0) {
        url += '/q/70';
    }
    return url.replace('http:', '');
};


const image2 = (imageUrl, opts) => {
    if (imageUrl) {
        let params = opts.hash;
        let urls = imageUrl.split('?');
        let query = urls[1] || '';
        let uri = urls[0];

        if (uri.indexOf('http:') === 0) {
            uri = uri.replace('http:', '');
        }

        if (query) {
            query = query.replace(/{width}/g, params.w)
                .replace(/{height}/g, params.h)
                .replace(/{mode}/g, (params.mode || 2));

            if (query.indexOf('imageView2') === 0) {
                if (params.q && query.indexOf('/q/') > 0) {
                    query = query.replace(/\/q\/\d+/g, '/q/' + params.q);
                } else {
                    query += '/q/' + params.q;
                }
            } else if (query.indexOf('imageMogr2') === 0) {
                if (params.q && query.indexOf('/quality/') > 0) {
                    query = query.replace(/\/quality\/\d+/g, '/quality/' + params.q);
                } else {
                    query += '/quality/' + params.q;
                }
            } else if (query.indexOf('imageView/') === 0) {
                if (params.q && query.indexOf('/q/') > 0) {
                    query = query.replace(/\/q\/\d+/g, '/q/' + params.q);
                } else {
                    query += '/q/' + params.q;
                }

                if (params.mode) {
                    query = query.replace(/imageView\/\d{1}\//, 'imageView/' + params.mode + '/');
                }
            }
        } else {
            query = 'imageView2/2/interlace/1/q/' + (params.q || 75);
        }
        return uri + '?' + query;
    } else {
        return imageUrl;
    }
};

export {
    image,
    image2,
    getEnv,
    invokeMethod,
    getQueryObj,
    linkToMiniApp,
    parseUrl
};
