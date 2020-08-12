package com.syzegee.ruleengine.service.repository;

import com.syzegee.ruleengine.service.entity.SzProjectRules;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Ram Prasad
 */
@Repository
public interface SZProjectRulesRepository extends JpaRepository<SzProjectRules, Long> {

    @Query(" from SzProjectRules where isActive=true and projectRulesId=:projectRulesId")
    public SzProjectRules getSzProjectRulesById(@Param("projectRulesId") Long projectRulesId);

    @Query(" select spr from SzProjectRules spr inner join spr.ruleId rid where spr.isActive=true" +
            " and  rid.ruleId=:ruleId")
    public List<SzProjectRules> getAllSzProjectRulesByRuleId(@Param("ruleId") Long ruleId);

    @Query(" select spr from SzProjectRules spr inner join spr.projectId rpid where spr.isActive=true" +
            " and  rpid.projectId=:projectId")
    public List<SzProjectRules> getAllSzProjectRulesByProjectId(@Param("projectId") Long projectId);

    @Query(" from SzProjectRules where isActive=true")
    public List<SzProjectRules> getAllSzProjectRules();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(" update SzProjectRules set isActive =false where projectRulesId=:projectRulesId")
    int deleteSzProjectRulesById(@Param("projectRulesId") Long projectRulesId);


}

