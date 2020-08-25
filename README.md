# rule-enigne-service

Rule Engine Service Project

Url: http://ec2-18-188-157-35.us-east-2.compute.amazonaws.com:8090

## API List
| API	| Type	| Url					| Controller|
| ----	| ---	| --					| ----------|
| RunRule| POST| /syzegee/v1/ruleengine/runrule| CustomerRuleController|
| Get Plans| POST| /syzegee/v1/ruleengine/plans| CustomerRuleController|


## Steps to Run an application
1. Take the latest copy of the code from the repository
2. Open project.
3. build the project using Gradle-><<Project>>->Tasks->build->build.
4. Once Project get build successfully, press Shift+F10 to run it.