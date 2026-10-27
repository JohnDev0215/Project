package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class VpcDestinationSummary implements Serializable {
    private String roleArn;
    private List<String> securityGroups;
    private List<String> subnetIds;
    private String vpcId;

    public List<String> getSubnetIds() {
        return this.subnetIds;
    }

    public void setSubnetIds(Collection<String> collection) {
        if (collection == null) {
            this.subnetIds = null;
        } else {
            this.subnetIds = new ArrayList(collection);
        }
    }

    public VpcDestinationSummary withSubnetIds(String... strArr) {
        if (getSubnetIds() == null) {
            this.subnetIds = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.subnetIds.add(str);
        }
        return this;
    }

    public VpcDestinationSummary withSubnetIds(Collection<String> collection) {
        setSubnetIds(collection);
        return this;
    }

    public List<String> getSecurityGroups() {
        return this.securityGroups;
    }

    public void setSecurityGroups(Collection<String> collection) {
        if (collection == null) {
            this.securityGroups = null;
        } else {
            this.securityGroups = new ArrayList(collection);
        }
    }

    public VpcDestinationSummary withSecurityGroups(String... strArr) {
        if (getSecurityGroups() == null) {
            this.securityGroups = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.securityGroups.add(str);
        }
        return this;
    }

    public VpcDestinationSummary withSecurityGroups(Collection<String> collection) {
        setSecurityGroups(collection);
        return this;
    }

    public String getVpcId() {
        return this.vpcId;
    }

    public void setVpcId(String str) {
        this.vpcId = str;
    }

    public VpcDestinationSummary withVpcId(String str) {
        this.vpcId = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public VpcDestinationSummary withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSubnetIds() != null) {
            sb.append("subnetIds: " + getSubnetIds() + ",");
        }
        if (getSecurityGroups() != null) {
            sb.append("securityGroups: " + getSecurityGroups() + ",");
        }
        if (getVpcId() != null) {
            sb.append("vpcId: " + getVpcId() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getSubnetIds() == null ? 0 : getSubnetIds().hashCode()) + 31) * 31) + (getSecurityGroups() == null ? 0 : getSecurityGroups().hashCode())) * 31) + (getVpcId() == null ? 0 : getVpcId().hashCode())) * 31) + (getRoleArn() != null ? getRoleArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof VpcDestinationSummary)) {
            return false;
        }
        VpcDestinationSummary vpcDestinationSummary = (VpcDestinationSummary) obj;
        if ((vpcDestinationSummary.getSubnetIds() == null) ^ (getSubnetIds() == null)) {
            return false;
        }
        if (vpcDestinationSummary.getSubnetIds() != null && !vpcDestinationSummary.getSubnetIds().equals(getSubnetIds())) {
            return false;
        }
        if ((vpcDestinationSummary.getSecurityGroups() == null) ^ (getSecurityGroups() == null)) {
            return false;
        }
        if (vpcDestinationSummary.getSecurityGroups() != null && !vpcDestinationSummary.getSecurityGroups().equals(getSecurityGroups())) {
            return false;
        }
        if ((vpcDestinationSummary.getVpcId() == null) ^ (getVpcId() == null)) {
            return false;
        }
        if (vpcDestinationSummary.getVpcId() != null && !vpcDestinationSummary.getVpcId().equals(getVpcId())) {
            return false;
        }
        if ((vpcDestinationSummary.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return vpcDestinationSummary.getRoleArn() == null || vpcDestinationSummary.getRoleArn().equals(getRoleArn());
    }
}
