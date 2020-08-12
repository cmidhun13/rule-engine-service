package com.syzegee.ruleengine.service.service;

import com.syzegee.ruleengine.service.adopter.RuleEngineConnector;
import com.syzegee.ruleengine.service.datafetcher.RuleDataFetcher;
import com.syzegee.ruleengine.service.datafetcher.RuleDetailsFetcher;
import com.syzegee.ruleengine.service.datafetcher.RuleProjectDataFetcher;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleEngineService {

    @Autowired
    private RuleEngineConnector ruleEngineConnector;

    public DataFetcher retrieveRuleProjectById(){
        DataFetcher fetcher = new RuleProjectDataFetcher(ruleEngineConnector);
        return fetcher ;

    }

    public DataFetcher retrieveRuleByRuleName(){
        DataFetcher fetcher = new RuleDataFetcher(ruleEngineConnector);
        return fetcher ;
    }

    public DataFetcher retrieveRuleDetailsByRuleName(){
        DataFetcher fetcher = new RuleDetailsFetcher(ruleEngineConnector);
        return fetcher ;
    }

}
