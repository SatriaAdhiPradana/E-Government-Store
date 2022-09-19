import httpService from '../../../common/httpService'
import config from '../../../config'

let serviceList = params => httpService.postJson(config.domain + '/government/serviceList', params);
let serviceDetail = params => httpService.postJson(config.domain + '/government/serviceDetail', params);
let travelList = params => httpService.postJson(config.domain + '/government/travelList', params);

export default {serviceList, serviceDetail, travelList};
