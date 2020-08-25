package com.syzegee.ruleengine.service.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import com.syzegee.ruleengine.service.adopter.RuleEngineConnector;
import com.syzegee.ruleengine.service.entity.SzRuleProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleProjectDataFetcher implements DataFetcher<SzRuleProject> {


    private RuleEngineConnector ruleEngineConnector;

    @Autowired
    public RuleProjectDataFetcher(RuleEngineConnector ruleEngineConnector) {
        this.ruleEngineConnector=ruleEngineConnector;
    }

    @Override
    public SzRuleProject get(DataFetchingEnvironment environment) {
        String data = environment.getArgument("id");
        return  ruleEngineConnector.getRuleProjectByCustomerId(data);
    }


}
