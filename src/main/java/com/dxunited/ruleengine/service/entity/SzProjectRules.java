package com.dxunited.ruleengine.service.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Ram Prasad
 */
@Entity
@Data
@Table(name = "sz_project_rules")
@XmlRootElement
@Builder
@NamedQueries({
        @NamedQuery(name = "SzProjectRules.findAll", query = "SELECT s FROM SzProjectRules s")
        , @NamedQuery(name = "SzProjectRules.findByProjectRulesId", query = "SELECT s FROM SzProjectRules s WHERE s.projectRulesId = :projectRulesId")
        , @NamedQuery(name = "SzProjectRules.findByRuleValue", query = "SELECT s FROM SzProjectRules s WHERE s.ruleValue = :ruleValue")
        , @NamedQuery(name = "SzProjectRules.findByIsActive", query = "SELECT s FROM SzProjectRules s WHERE s.isActive = :isActive")
        , @NamedQuery(name = "SzProjectRules.findByCreatedBy", query = "SELECT s FROM SzProjectRules s WHERE s.createdBy = :createdBy")
        , @NamedQuery(name = "SzProjectRules.findByCreatedDate", query = "SELECT s FROM SzProjectRules s WHERE s.createdDate = :createdDate")
        , @NamedQuery(name = "SzProjectRules.findByUpdatedBy", query = "SELECT s FROM SzProjectRules s WHERE s.updatedBy = :updatedBy")
        , @NamedQuery(name = "SzProjectRules.findByUpdatedDate", query = "SELECT s FROM SzProjectRules s WHERE s.updatedDate = :updatedDate")})

public class SzProjectRules  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "project_rules_id")
    private Long projectRulesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "rule_value")
    private String ruleValue;
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
    @JoinColumn(name = "rule_id", referencedColumnName = "rule_id")
    @ManyToOne(optional = false)
    private SzRule ruleId;
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    @ManyToOne(optional = false)
    private SzRuleProject projectId;

    @Tolerate
    public SzProjectRules() {
    }
    @Tolerate
    public SzProjectRules(Long projectRulesId) {
        this.projectRulesId = projectRulesId;
    }
    @Tolerate
    public SzProjectRules(Long projectRulesId, String ruleValue) {
        this.projectRulesId = projectRulesId;
        this.ruleValue = ruleValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectRulesId != null ? projectRulesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SzProjectRules)) {
            return false;
        }
        SzProjectRules other = (SzProjectRules) object;
        if ((this.projectRulesId == null && other.projectRulesId != null) || (this.projectRulesId != null && !this.projectRulesId.equals(other.projectRulesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syzegee.ruleengine.entity.SzProjectRules[ projectRulesId=" + projectRulesId + " ]";
    }

}
