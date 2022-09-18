module.exports = {
  "presets": [
    ["@babel/preset-env", {
      "modules": false,
      "useBuiltIns": 'usage'
    }],
    "@babel/preset-react"
  ],
  "plugins": [
    ["@babel/syntax-dynamic-import"],
    ["@babel/transform-react-constant-elements"],
    ["@babel/proposal-class-properties"]
  ]
};
