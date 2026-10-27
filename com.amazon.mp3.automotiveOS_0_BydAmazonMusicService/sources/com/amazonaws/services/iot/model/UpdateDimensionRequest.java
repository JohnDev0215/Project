package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDimensionRequest extends AmazonWebServiceRequest implements Serializable {
    private String name;
    private List<String> stringValues;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public UpdateDimensionRequest withName(String str) {
        this.name = str;
        return this;
    }

    public List<String> getStringValues() {
        return this.stringValues;
    }

    public void setStringValues(Collection<String> collection) {
        if (collection == null) {
            this.stringValues = null;
        } else {
            this.stringValues = new ArrayList(collection);
        }
    }

    public UpdateDimensionRequest withStringValues(String... strArr) {
        if (getStringValues() == null) {
            this.stringValues = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.stringValues.add(str);
        }
        return this;
    }

    public UpdateDimensionRequest withStringValues(Collection<String> collection) {
        setStringValues(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getName() != null) {
            sb.append("name: " + getName() + ",");
        }
        if (getStringValues() != null) {
            sb.append("stringValues: " + getStringValues());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getName() == null ? 0 : getName().hashCode()) + 31) * 31) + (getStringValues() != null ? getStringValues().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateDimensionRequest)) {
            return false;
        }
        UpdateDimensionRequest updateDimensionRequest = (UpdateDimensionRequest) obj;
        if ((updateDimensionRequest.getName() == null) ^ (getName() == null)) {
            return false;
        }
        if (updateDimensionRequest.getName() != null && !updateDimensionRequest.getName().equals(getName())) {
            return false;
        }
        if ((updateDimensionRequest.getStringValues() == null) ^ (getStringValues() == null)) {
            return false;
        }
        return updateDimensionRequest.getStringValues() == null || updateDimensionRequest.getStringValues().equals(getStringValues());
    }
}
