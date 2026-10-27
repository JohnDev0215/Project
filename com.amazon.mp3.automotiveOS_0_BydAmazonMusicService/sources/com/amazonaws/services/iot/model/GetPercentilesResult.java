package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetPercentilesResult implements Serializable {
    private List<PercentPair> percentiles;

    public List<PercentPair> getPercentiles() {
        return this.percentiles;
    }

    public void setPercentiles(Collection<PercentPair> collection) {
        if (collection == null) {
            this.percentiles = null;
        } else {
            this.percentiles = new ArrayList(collection);
        }
    }

    public GetPercentilesResult withPercentiles(PercentPair... percentPairArr) {
        if (getPercentiles() == null) {
            this.percentiles = new ArrayList(percentPairArr.length);
        }
        for (PercentPair percentPair : percentPairArr) {
            this.percentiles.add(percentPair);
        }
        return this;
    }

    public GetPercentilesResult withPercentiles(Collection<PercentPair> collection) {
        setPercentiles(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPercentiles() != null) {
            sb.append("percentiles: " + getPercentiles());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getPercentiles() == null ? 0 : getPercentiles().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetPercentilesResult)) {
            return false;
        }
        GetPercentilesResult getPercentilesResult = (GetPercentilesResult) obj;
        if ((getPercentilesResult.getPercentiles() == null) ^ (getPercentiles() == null)) {
            return false;
        }
        return getPercentilesResult.getPercentiles() == null || getPercentilesResult.getPercentiles().equals(getPercentiles());
    }
}
