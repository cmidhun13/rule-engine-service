package com.syzegee.ruleengine.service.adopter;


import com.syzegee.ruleengine.service.entity.SzProjectRules;
import com.syzegee.ruleengine.service.entity.SzRuleDetails;
import com.syzegee.ruleengine.service.entity.SzRuleProject;
import com.syzegee.ruleengine.service.repository.SZProjectRulesRepository;
import com.syzegee.ruleengine.service.repository.SZRuleDetailsRepository;
import com.syzegee.ruleengine.service.repository.SZRuleProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.syzegee.ruleengine.service.repository.SZRuleRepository;
import com.syzegee.ruleengine.service.entity.SzRule;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;


/**
 * Ram Prasad
 */
@Component
public class RuleEngineConnector {

    private static final Logger logger = LogManager.getLogger(RuleEngineConnector.class);

    @Autowired
    private SZRuleRepository szRuleRepository;

    @Autowired
    private SZRuleDetailsRepository szRuleDetailsRepository;

    @Autowired
    private SZRuleProjectRepository szRuleProjectRepository;

    @Autowired
    private SZProjectRulesRepository szProjectRulesRepository;



    public SzRule getSyzegeeRuleBySzRuleId(long szRuleId){
        SzRule ruleEntity = szRuleRepository.getSzRuleById(szRuleId);
        return ruleEntity;
    }

    public SzRuleDetails getSyzegeeRuleDetailBySzRuleDetailId(long szRuleDetailId){
        SzRuleDetails ruleDetailEntity= szRuleDetailsRepository.getSzRuleDetailsById(szRuleDetailId);
        return ruleDetailEntity;
    }

    public List<SzRuleDetails> getAllSyzegeeRuleDetailsByRuleId(long szRuleId){
       return szRuleDetailsRepository.getAllSzRuledetailsByRuleId(szRuleId);
    }


    public SzRuleProject getRuleProjectByProjectId(long ruleProjectId){
        SzRuleProject ruleProject  = szRuleProjectRepository.getSzRuleProjectById(ruleProjectId);
        return ruleProject;
    }

    public SzRuleProject  getRuleProjectByCustomerId(String customerId){
        return szRuleProjectRepository.getSzRuleProjectByCustomerId(customerId);
    }


    public SzProjectRules getProjectRuleByProRuleId(long projectRuleId){
        SzProjectRules projectRules = szProjectRulesRepository.getSzProjectRulesById(projectRuleId);
        return projectRules;
    }

    public  List<SzProjectRules>  getAllSzProjectRulesByProjectId(long projectId){
        List<SzProjectRules> allSzProjectRulesByProjectId = szProjectRulesRepository.getAllSzProjectRulesByProjectId(projectId);
        return allSzProjectRulesByProjectId;
    }

    public SzRule getRuleByRuleName(String ruleName){
        SzRule szRuleByRuleName = szRuleRepository.getSzRuleByRuleName(ruleName);
        return  szRuleByRuleName;
    }

    public  List<SzRuleDetails> getAllSzRuleDetailsByRuleId(long ruleId){
         List<SzRuleDetails> allSzRuledetails = szRuleDetailsRepository.getAllSzRuledetailsByRuleId(ruleId);
         return allSzRuledetails;
    }
     public  List<SzProjectRules>  getProjectRulesByRuleId(long ruleId){
          List<SzProjectRules> allSzProjectRules= szProjectRulesRepository.getAllSzProjectRulesByRuleId(ruleId);
          return allSzProjectRules;
    }

    public List<SzRuleProject> getAllRuleProject(){
          return szRuleProjectRepository.getAllSzRuleProjects();
    }

}
