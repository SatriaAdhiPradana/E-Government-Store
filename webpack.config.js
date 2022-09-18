const webpack = require('webpack');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const path = require('path');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const CleanWebpackPlugin = require('clean-webpack-plugin');
const HtmlWebpackPlugin = require("html-webpack-plugin");
const config = require("./config");
const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");

const appHtmls = require('./app/' + config.currentApp + '/title');

let pathsToClean = [
    'dist'
];
let cleanOptions = {
    root: __dirname,
    verbose: true,
    dry: false
};

let plugins = [
    new webpack.DefinePlugin({//sampleadhi
        PRODUCT: false
    }),
    new MiniCssExtractPlugin({
        // Options similar to the same options in webpackOptions.output
        // both options are optional
        filename: "css/[name].css",
        chunkFilename: "css/[id].css"
    }),//csssampleadhi
    new CleanWebpackPlugin(pathsToClean, cleanOptions),//清除历史版本
].concat(Object.keys(appHtmls).map(title => {
    return new HtmlWebpackPlugin({
        filename: title + '.html',
        template: './index.ejs',
        title: appHtmls[title],
        inject: true,
    })
}));

module.exports = {
    optimization: {
        minimizer: [
            new UglifyJsPlugin({
                cache: true,
                parallel: true,
                sourceMap: true // set to true if you want JS source maps
            }),
            new OptimizeCSSAssetsPlugin({})
        ],
        splitChunks: {
            chunks () {
                return false
            },
            cacheGroups: {
                default: false,
                vendors: {
                    chunks: 'all',
                    name: 'vendors',
                    test: /node_modules/,
                }
            }
        }
    },
    //sample adhi
    entry: [`./app/${config.currentApp}/index.js`],
    //sample adhi
    output: {
        chunkFilename: 'js/[name].[chunkhash:8].chunk.js',
        path: path.resolve(__dirname, 'dist'),
        filename: '[name].[chunkhash:8].js',
    },
    //sample adhi
    plugins,
    //sample adhi
    module: {
        rules: [
            {
                test: /\.js/,
                exclude: /node_modules/,
                use: [{
                    loader: 'babel-loader',
                }]
            },
            {
                test: /\.(sa|sc|c)ss$/,
                use: [
                    MiniCssExtractPlugin.loader ,
                    'css-loader',
                    'postcss-loader',
                    'sass-loader',
                ],
            },
            {
                test: /\.(png|jpg|gif)$/,
                use: "url-loader?limit=8192&name=images/[name].[hash:7].[ext]",
            },
            // {test: /\.html$/, use: 'html-withimg-loader'},
            {test: /\.json$/, use: 'json-loader'}
        ]
    },
    devServer: {
        publicPath: config.routerPath[config.currentApp] || '',
        port: 8080,
        disableHostCheck: true,
    },
    devtool: "source-map",
    //adhi hahahaha
    resolve: {
        alias: {
            '@': path.resolve(__dirname, 'src')
        }
    }
};
