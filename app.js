const express = require('express');
const helmet = require('helmet');
const indexRouter = require('./routes/index');

const app = express();

// Secure Express by hiding X-Powered-By
app.use(helmet.hidePoweredBy());

// Middleware to parse JSON requests
app.use(express.json());

// Define Routes
app.use('/', indexRouter);

const port = process.env.PORT || 3000;
app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});

module.exports = app;
