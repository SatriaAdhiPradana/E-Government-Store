const currentApp = 'government';
module.exports = {
    production: {
        routerPath: {
            government: '',
        },
        domain: 'https://api.doallcollect.com',
        currentApp
    },
    development: {
        routerPath: {
            government: '',
        },
        domain: 'https://api.doallcollect.com',
        currentApp
    }
}[process.env.NODE_ENV || 'development']
