package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ThingGroupMetadata implements Serializable {
    private Date creationDate;
    private String parentGroupName;
    private List<GroupNameAndArn> rootToParentThingGroups;

    public String getParentGroupName() {
        return this.parentGroupName;
    }

    public void setParentGroupName(String str) {
        this.parentGroupName = str;
    }

    public ThingGroupMetadata withParentGroupName(String str) {
        this.parentGroupName = str;
        return this;
    }

    public List<GroupNameAndArn> getRootToParentThingGroups() {
        return this.rootToParentThingGroups;
    }

    public void setRootToParentThingGroups(Collection<GroupNameAndArn> collection) {
        if (collection == null) {
            this.rootToParentThingGroups = null;
        } else {
            this.rootToParentThingGroups = new ArrayList(collection);
        }
    }

    public ThingGroupMetadata withRootToParentThingGroups(GroupNameAndArn... groupNameAndArnArr) {
        if (getRootToParentThingGroups() == null) {
            this.rootToParentThingGroups = new ArrayList(groupNameAndArnArr.length);
        }
        for (GroupNameAndArn groupNameAndArn : groupNameAndArnArr) {
            this.rootToParentThingGroups.add(groupNameAndArn);
        }
        return this;
    }

    public ThingGroupMetadata withRootToParentThingGroups(Collection<GroupNameAndArn> collection) {
        setRootToParentThingGroups(collection);
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public ThingGroupMetadata withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getParentGroupName() != null) {
            sb.append("parentGroupName: " + getParentGroupName() + ",");
        }
        if (getRootToParentThingGroups() != null) {
            sb.append("rootToParentThingGroups: " + getRootToParentThingGroups() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getParentGroupName() == null ? 0 : getParentGroupName().hashCode()) + 31) * 31) + (getRootToParentThingGroups() == null ? 0 : getRootToParentThingGroups().hashCode())) * 31) + (getCreationDate() != null ? getCreationDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingGroupMetadata)) {
            return false;
        }
        ThingGroupMetadata thingGroupMetadata = (ThingGroupMetadata) obj;
        if ((thingGroupMetadata.getParentGroupName() == null) ^ (getParentGroupName() == null)) {
            return false;
        }
        if (thingGroupMetadata.getParentGroupName() != null && !thingGroupMetadata.getParentGroupName().equals(getParentGroupName())) {
            return false;
        }
        if ((thingGroupMetadata.getRootToParentThingGroups() == null) ^ (getRootToParentThingGroups() == null)) {
            return false;
        }
        if (thingGroupMetadata.getRootToParentThingGroups() != null && !thingGroupMetadata.getRootToParentThingGroups().equals(getRootToParentThingGroups())) {
            return false;
        }
        if ((thingGroupMetadata.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return thingGroupMetadata.getCreationDate() == null || thingGroupMetadata.getCreationDate().equals(getCreationDate());
    }
}
