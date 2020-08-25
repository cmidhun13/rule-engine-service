package com.syzegee.ruleengine.service.controller;


import com.syzegee.ruleengine.service.exception.SzRuleEngineRestException;
import com.syzegee.ruleengine.service.response.GenericResponse;
import com.syzegee.ruleengine.service.service.RuleEngineService;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Ram Prasad
 */
@RestController
@RequestMapping("/syzegee/v1/ruleengine")
public class CustomerRuleController extends CustomerRuleAbstractController {


    @Autowired
    RuleEngineService ruleEngineService;

    /**
     * This method is used to get customer by custId using graphql
     *
     * @param query takes the query to get customer for specefic fields
     * @return object of customer based on the grapg query else return error
     */

    @PostMapping("/customers")
    public ResponseEntity<Object> getCustomerRecord(@RequestBody String query) {

//        logger.info("Entered into getCustomer@CustomerController");

        ExecutionResult execute;
        Object customerData;
        try {
            DataFetcher dataFetcher = ruleEngineService.retrieveRuleProjectById();
            execute = buildResponse(dataFetcher).execute(query);
            customerData = execute.getData();
        } catch (IOException ex) {

            throw new SzRuleEngineRestException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
                    "error occured while fetching the record", ex.getMessage());
        }

        List<GraphQLError> errors = execute.getErrors();
        if (!errors.isEmpty()) {

            throw new SzRuleEngineRestException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
                    "Query validation error", execute);

        } else {
            return new ResponseEntity<>(customerData, HttpStatus.OK);
        }
    }
    @PostMapping("/runrule")
    public ResponseEntity<Object> runRUleRecord(@RequestBody String query) {

        ExecutionResult execute;
        Object ruleData;
        try {
            DataFetcher dataFetcher = ruleEngineService.retrieveRuleByRuleName();
            execute = buildRuleResponse(dataFetcher).execute(query);
            ruleData = execute.getData();
        } catch (IOException ex) {
            throw new SzRuleEngineRestException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
                    "error occured while fetching the record", ex.getMessage());
        }
        List<GraphQLError> errors = execute.getErrors();
        if (!errors.isEmpty()) {

            throw new SzRuleEngineRestException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
                    "Query validation error", execute);

        } else {
            return new ResponseEntity<>(ruleData, HttpStatus.OK);
        }
    }
    @PostMapping("/plans")
    public ResponseEntity<GenericResponse<Object>> runRuleDetails(@RequestBody String query) {
        ExecutionResult execute;
        Object ruleData;
        try {
            DataFetcher dataFetcher = ruleEngineService.retrieveRuleDetailsByRuleName();
            execute = buildRuleDetailsResponse(dataFetcher).execute(query);
            ruleData = execute.getData();
        } catch (IOException ex) {
            throw new SzRuleEngineRestException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
                    "error occured while fetching the record", ex.getMessage());
        }
        if (((LinkedHashMap) ruleData).get("ruleName") != null) {
            return ResponseEntity.ok(new GenericResponse<Object>(true,HttpStatus.OK.value(),"Plan record fetched successfully.","Plan record fetched successfully.", ruleData));
        }
            return ResponseEntity.ok(new GenericResponse<Object>(false,HttpStatus.OK.value(),"Provide valid rule name.","Rulename does not exist.", null));
    }
}
