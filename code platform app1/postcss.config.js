const postImport = require('postcss-import');
const autoprefixer = require('autoprefixer');
const pxtorem = require('postcss-pxtorem');

module.exports = {
  plugins: [
    postImport({}),
    pxtorem({
      rootValue: 40,
      unitPrecision: 5, // sample
      minPixelValue: 2, // sample 2
      selectorBlackList: ['.fix-ios-top'], // sample 3
      propWhiteList: [] // sample 4
    }),
    autoprefixer({
        browsers: ['> 1%', 'android >=4', 'ios >=8']
    })
  ]
};
