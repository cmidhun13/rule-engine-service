package com.dxunited.ruleengine.service.repository;


import com.dxunited.ruleengine.service.entity.SzRuleDetails;

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
public interface SZRuleDetailsRepository  extends JpaRepository<SzRuleDetails, Long> {

    @Query(" from SzRuleDetails where isActive=true and ruleDetailId=:ruleDetailId")
    public SzRuleDetails getSzRuleDetailsById(@Param("ruleDetailId") Long ruleDetailId);

    @Query(" select srd from SzRuleDetails srd inner join srd.ruleId rid where srd.isActive=true" +
            " and rid.ruleId=:ruleId")
    public List<SzRuleDetails> getAllSzRuledetailsByRuleId(@Param("ruleId") Long ruleId);

    @Query(" from SzRuleDetails where isActive=true")
    public List<SzRuleDetails> getAllSzRuleDetails();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(" update SzRuleDetails set isActive =false where ruleDetailId=:ruleDetailId")
    int deleteSzRuleDetailsById(@Param("ruleDetailId") Long ruleDetailId);

}
