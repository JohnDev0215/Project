package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteDimensionRequest extends AmazonWebServiceRequest implements Serializable {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public DeleteDimensionRequest withName(String str) {
        this.name = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getName() != null) {
            sb.append("name: " + getName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getName() == null ? 0 : getName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteDimensionRequest)) {
            return false;
        }
        DeleteDimensionRequest deleteDimensionRequest = (DeleteDimensionRequest) obj;
        if ((deleteDimensionRequest.getName() == null) ^ (getName() == null)) {
            return false;
        }
        return deleteDimensionRequest.getName() == null || deleteDimensionRequest.getName().equals(getName());
    }
}
