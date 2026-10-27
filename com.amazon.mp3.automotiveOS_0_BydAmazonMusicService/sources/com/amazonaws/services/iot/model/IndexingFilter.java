package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class IndexingFilter implements Serializable {
    private List<String> namedShadowNames;

    public List<String> getNamedShadowNames() {
        return this.namedShadowNames;
    }

    public void setNamedShadowNames(Collection<String> collection) {
        if (collection == null) {
            this.namedShadowNames = null;
        } else {
            this.namedShadowNames = new ArrayList(collection);
        }
    }

    public IndexingFilter withNamedShadowNames(String... strArr) {
        if (getNamedShadowNames() == null) {
            this.namedShadowNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.namedShadowNames.add(str);
        }
        return this;
    }

    public IndexingFilter withNamedShadowNames(Collection<String> collection) {
        setNamedShadowNames(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getNamedShadowNames() != null) {
            sb.append("namedShadowNames: " + getNamedShadowNames());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getNamedShadowNames() == null ? 0 : getNamedShadowNames().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof IndexingFilter)) {
            return false;
        }
        IndexingFilter indexingFilter = (IndexingFilter) obj;
        if ((indexingFilter.getNamedShadowNames() == null) ^ (getNamedShadowNames() == null)) {
            return false;
        }
        return indexingFilter.getNamedShadowNames() == null || indexingFilter.getNamedShadowNames().equals(getNamedShadowNames());
    }
}
