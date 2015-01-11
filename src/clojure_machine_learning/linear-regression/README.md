Linear Regression
=================

Notes ... these may not be useful to anyone but me!

Define linear regression model with a single variable as

Y = beta * X + epsilon

where things are pretty analogous to y = mx + c

beta = "Regression coefficient" or "effect"

epsilon = "error term" or "bias"

error vs residual
-----------------

Error is the measure of the amount by which an observed value differs from it's expected value.

"Residual is an estimate of the unobservable statistical error" - that's not a very meaningful definition!

SSE
---

Sum of Squared Errors = SSE(Y') = sum^{N}_{i=1}(Y'_{i} - Y_{i})^2

for errors, would normally create different models and try to minimise the error

Cost function
=============
function of the parameters of a formulated model - generally equates to the MSE (mean square error)

J(beta, epsilon) = (1 / 2N) sum^{N}_{i=1}(Y'_{i} - Y_{i})^2

where Y'_{i} = beta * X_{i} + epsilon

therefore: to find the parameters beta and epsilon for an optimal linear regression model, where
J(beta, epsilon) is the cost function of the model,

(beta', epsilon') = arg main ||J(beta, epsilon)||
