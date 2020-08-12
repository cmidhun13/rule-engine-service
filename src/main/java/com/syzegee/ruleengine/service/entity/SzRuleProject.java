package com.syzegee.ruleengine.service.entity;

import lombok.Builder;
import lombok.experimental.Tolerate;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ram Prasad
 */
@Entity
@Table(name = "sz_rule_project")
@XmlRootElement
@Builder
@NamedQueries({
        @NamedQuery(name = "SzRuleProject.findAll", query = "SELECT s FROM SzRuleProject s")
        , @NamedQuery(name = "SzRuleProject.findByProjectId", query = "SELECT s FROM SzRuleProject s WHERE s.projectId = :projectId")
        , @NamedQuery(name = "SzRuleProject.findByCustomerId", query = "SELECT s FROM SzRuleProject s WHERE s.customerId = :customerId")
        , @NamedQuery(name = "SzRuleProject.findByProjectCode", query = "SELECT s FROM SzRuleProject s WHERE s.projectCode = :projectCode")
        , @NamedQuery(name = "SzRuleProject.findByProjectName", query = "SELECT s FROM SzRuleProject s WHERE s.projectName = :projectName")
        , @NamedQuery(name = "SzRuleProject.findByProjectDesc", query = "SELECT s FROM SzRuleProject s WHERE s.projectDesc = :projectDesc")
        , @NamedQuery(name = "SzRuleProject.findByIsActive", query = "SELECT s FROM SzRuleProject s WHERE s.isActive = :isActive")
        , @NamedQuery(name = "SzRuleProject.findByCreatedBy", query = "SELECT s FROM SzRuleProject s WHERE s.createdBy = :createdBy")
        , @NamedQuery(name = "SzRuleProject.findByCreatedDate", query = "SELECT s FROM SzRuleProject s WHERE s.createdDate = :createdDate")
        , @NamedQuery(name = "SzRuleProject.findByUpdatedBy", query = "SELECT s FROM SzRuleProject s WHERE s.updatedBy = :updatedBy")
        , @NamedQuery(name = "SzRuleProject.findByUpdatedDate", query = "SELECT s FROM SzRuleProject s WHERE s.updatedDate = :updatedDate")})

public class SzRuleProject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "project_id")
    private Long projectId;
    @Size(max = 120)
    @Column(name = "customer_id")
    private String customerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "project_code")
    private String projectCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "project_name")
    private String projectName;
    @Size(max = 240)
    @Column(name = "project_desc")
    private String projectDesc;
    @Column(name = "is_active")
    private Boolean isActive;
    @Size(max = 120)
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 120)
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId")
    private Collection<SzProjectRules> szProjectRulesCollection;

    @Tolerate
    public SzRuleProject() {
    }
    @Tolerate
    public SzRuleProject(Long projectId) {
        this.projectId = projectId;
    }
    @Tolerate
    public SzRuleProject(Long projectId, String projectCode, String projectName) {
        this.projectId = projectId;
        this.projectCode = projectCode;
        this.projectName = projectName;
    }



    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @XmlTransient
    public Collection<SzProjectRules> getSzProjectRulesCollection() {
        return szProjectRulesCollection;
    }

    public void setSzProjectRulesCollection(Collection<SzProjectRules> szProjectRulesCollection) {
        this.szProjectRulesCollection = szProjectRulesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectId != null ? projectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SzRuleProject)) {
            return false;
        }
        SzRuleProject other = (SzRuleProject) object;
        if ((this.projectId == null && other.projectId != null) || (this.projectId != null && !this.projectId.equals(other.projectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syzegee.ruleengine.entity.SzRuleProject[ projectId=" + projectId + " ]";
    }

}
