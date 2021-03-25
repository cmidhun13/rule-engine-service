package com.dxunited.ruleengine.service.datafetcher;

import com.dxunited.ruleengine.service.adopter.RuleEngineConnector;
import com.dxunited.ruleengine.service.entity.SzProjectRules;
import com.dxunited.ruleengine.service.entity.SzRule;
import com.dxunited.ruleengine.service.exception.SzRuleEngineException;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RuleDataFetcher implements DataFetcher<SzRule> {

    private RuleEngineConnector ruleEngineConnector;
    @Autowired
    public RuleDataFetcher(RuleEngineConnector ruleEngineConnector) {
        this.ruleEngineConnector = ruleEngineConnector;
    }

    @Override
    public SzRule get(DataFetchingEnvironment environment) {
        String data = environment.getArgument("name");
        System.out.println("==== "+data);
        SzRule szRule = ruleEngineConnector.getRuleByRuleName(data);
        boolean flag=false;
        Integer projectId = environment.getArgument("projectId");
        List<SzProjectRules> szProjectRulesList = ruleEngineConnector.getAllSzProjectRulesByProjectId(projectId);
        if (Objects.nonNull(szProjectRulesList)&&!szProjectRulesList.isEmpty())
        {
            for (SzProjectRules szProjectRules : szProjectRulesList)
            {
                if (szProjectRules.getRuleId().getRuleId()==szRule.getRuleId())
                {
                    flag=true;
                    break;
                }
            }
        }
        if (!flag)
        {
            throw new SzRuleEngineException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return szRule;
    }
}
