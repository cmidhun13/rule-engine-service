package com.syzegee.ruleengine.service.repository;


import com.syzegee.ruleengine.service.entity.SzRuleProject;
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
public interface SZRuleProjectRepository extends JpaRepository<SzRuleProject, Long> {

    @Query(" from SzRuleProject where isActive=true and projectId=:projectId")
    public SzRuleProject getSzRuleProjectById(@Param("projectId") Long projectId);

    @Query(" from SzRuleProject where isActive=true and customerId=:customerId")
    public SzRuleProject getSzRuleProjectByCustomerId(@Param("customerId") String customerId);

    @Query(" from SzRuleProject where isActive=true")
    public List<SzRuleProject> getAllSzRuleProjects();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(" update SzRuleProject set isActive =false where projectId=:projectId")
    int deleteSzRuleProjectById(@Param("projectId") Long projectId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(" update SzRuleProject set isActive =false where customerId=:customerId")
    int deleteSzRuleProjectByCustomerId(@Param("customerId") String customerId);
}
