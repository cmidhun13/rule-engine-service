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
 *
 * @author Ram Prasad
 */
@Entity
@Data
@Table(name = "sz_rule_details")
@XmlRootElement
@Builder
@NamedQueries({
        @NamedQuery(name = "SzRuleDetails.findAll", query = "SELECT s FROM SzRuleDetails s")
        , @NamedQuery(name = "SzRuleDetails.findByRuleDetailId", query = "SELECT s FROM SzRuleDetails s WHERE s.ruleDetailId = :ruleDetailId")
        , @NamedQuery(name = "SzRuleDetails.findByRuleDetailCode", query = "SELECT s FROM SzRuleDetails s WHERE s.ruleDetailCode = :ruleDetailCode")
        , @NamedQuery(name = "SzRuleDetails.findByRuleDetailValue", query = "SELECT s FROM SzRuleDetails s WHERE s.ruleDetailValue = :ruleDetailValue")
        , @NamedQuery(name = "SzRuleDetails.findByIsActive", query = "SELECT s FROM SzRuleDetails s WHERE s.isActive = :isActive")
        , @NamedQuery(name = "SzRuleDetails.findByCreatedBy", query = "SELECT s FROM SzRuleDetails s WHERE s.createdBy = :createdBy")
        , @NamedQuery(name = "SzRuleDetails.findByCreatedDate", query = "SELECT s FROM SzRuleDetails s WHERE s.createdDate = :createdDate")
        , @NamedQuery(name = "SzRuleDetails.findByUpdatedBy", query = "SELECT s FROM SzRuleDetails s WHERE s.updatedBy = :updatedBy")
        , @NamedQuery(name = "SzRuleDetails.findByUpdatedDate", query = "SELECT s FROM SzRuleDetails s WHERE s.updatedDate = :updatedDate")})

public class SzRuleDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rule_detail_id")
    private Long ruleDetailId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "rule_detail_code")
    private String ruleDetailCode;
    @Size(max = 240)
    @Column(name = "rule_detail_value")
    private String ruleDetailValue;
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

    @Tolerate
    public SzRuleDetails() {
    }
    @Tolerate
    public SzRuleDetails(Long ruleDetailId) {
        this.ruleDetailId = ruleDetailId;
    }
    @Tolerate
    public SzRuleDetails(Long ruleDetailId, String ruleDetailCode) {
        this.ruleDetailId = ruleDetailId;
        this.ruleDetailCode = ruleDetailCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruleDetailId != null ? ruleDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SzRuleDetails)) {
            return false;
        }
        SzRuleDetails other = (SzRuleDetails) object;
        if ((this.ruleDetailId == null && other.ruleDetailId != null) || (this.ruleDetailId != null && !this.ruleDetailId.equals(other.ruleDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syzegee.ruleengine.entity.SzRuleDetails[ ruleDetailId=" + ruleDetailId + " ]";
    }

}
