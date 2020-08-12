package com.syzegee.ruleengine.service.entity;



import lombok.Builder;
import lombok.Data;
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
@Data
@Table(name = "sz_rule")
@XmlRootElement
@Builder
@NamedQueries({
        @NamedQuery(name = "SzRule.findAll", query = "SELECT s FROM SzRule s")
        , @NamedQuery(name = "SzRule.findByRuleId", query = "SELECT s FROM SzRule s WHERE s.ruleId = :ruleId")
        , @NamedQuery(name = "SzRule.findByRuleCode", query = "SELECT s FROM SzRule s WHERE s.ruleCode = :ruleCode")
        , @NamedQuery(name = "SzRule.findByRuleName", query = "SELECT s FROM SzRule s WHERE s.ruleName = :ruleName")
        , @NamedQuery(name = "SzRule.findByRuleDesc", query = "SELECT s FROM SzRule s WHERE s.ruleDesc = :ruleDesc")
        , @NamedQuery(name = "SzRule.findByRuleType", query = "SELECT s FROM SzRule s WHERE s.ruleType = :ruleType")
        , @NamedQuery(name = "SzRule.findByIsActive", query = "SELECT s FROM SzRule s WHERE s.isActive = :isActive")
        , @NamedQuery(name = "SzRule.findByCreatedBy", query = "SELECT s FROM SzRule s WHERE s.createdBy = :createdBy")
        , @NamedQuery(name = "SzRule.findByCreatedDate", query = "SELECT s FROM SzRule s WHERE s.createdDate = :createdDate")
        , @NamedQuery(name = "SzRule.findByUpdatedBy", query = "SELECT s FROM SzRule s WHERE s.updatedBy = :updatedBy")
        , @NamedQuery(name = "SzRule.findByUpdatedDate", query = "SELECT s FROM SzRule s WHERE s.updatedDate = :updatedDate")})

public class SzRule  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rule_id")
    private Long ruleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "rule_code")
    private String ruleCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "rule_name")
    private String ruleName;
    @Size(max = 240)
    @Column(name = "rule_desc")
    private String ruleDesc;
    @Size(max = 240)
    @Column(name = "rule_type")
    private String ruleType;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ruleId")
    private Collection<SzRuleDetails> szRuleDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ruleId")
    private Collection<SzProjectRules> szProjectRulesCollection;

    @Tolerate
    public SzRule() {
    }
    @Tolerate
    public SzRule(Long ruleId) {
        this.ruleId = ruleId;
    }

    @Tolerate
    public SzRule(Long ruleId, String ruleCode, String ruleName) {
        this.ruleId = ruleId;
        this.ruleCode = ruleCode;
        this.ruleName = ruleName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruleId != null ? ruleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SzRule)) {
            return false;
        }
        SzRule other = (SzRule) object;
        if ((this.ruleId == null && other.ruleId != null) || (this.ruleId != null && !this.ruleId.equals(other.ruleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syzegee.ruleengine.entity.SzRule[ ruleId=" + ruleId + " ]";
    }


}
