package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetCardinalityResult implements Serializable {
    private Integer cardinality;

    public Integer getCardinality() {
        return this.cardinality;
    }

    public void setCardinality(Integer num) {
        this.cardinality = num;
    }

    public GetCardinalityResult withCardinality(Integer num) {
        this.cardinality = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCardinality() != null) {
            sb.append("cardinality: " + getCardinality());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getCardinality() == null ? 0 : getCardinality().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetCardinalityResult)) {
            return false;
        }
        GetCardinalityResult getCardinalityResult = (GetCardinalityResult) obj;
        if ((getCardinalityResult.getCardinality() == null) ^ (getCardinality() == null)) {
            return false;
        }
        return getCardinalityResult.getCardinality() == null || getCardinalityResult.getCardinality().equals(getCardinality());
    }
}
