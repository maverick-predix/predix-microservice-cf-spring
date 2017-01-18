#Initial Version

##Important
Please make sure to build maven project
1) Run As > maven clean
2) Run As > maven install

##Using the analytic rest client
The analytic rest client is defined in AnalyticsController.java

https://<microservice-url><analytic-endpoint>?body=<json-input>

e.g. = https://bmab-ms-spr.run.aws-usw02-pr.ice.predix.io/manipulation?body=
*if no json-input is specified default values will run

###Analytic endpoints
/manipulate
/xgboost
/rfv1
/rfv2
