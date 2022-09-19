/**
 * Satria Adhi Pradana activity-platform app
 * @author: satria adhi pradana<satriaadhipradana0@gmail.com>
 * @date: 2022/1/11
 */
'use strict';
const port = 3100;
const express = require('express');
const path = require('path');
const app = express();
app.use(express.static('dist'));
app.get('*', function(request, response) {
  response.sendFile(path.resolve(__dirname, 'dist'));
});
app.listen(port, function() {
  console.log('started successfully, listening on port:' + port);
});
