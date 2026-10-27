package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class Denied implements Serializable {
    private ExplicitDeny explicitDeny;
    private ImplicitDeny implicitDeny;

    public ImplicitDeny getImplicitDeny() {
        return this.implicitDeny;
    }

    public void setImplicitDeny(ImplicitDeny implicitDeny) {
        this.implicitDeny = implicitDeny;
    }

    public Denied withImplicitDeny(ImplicitDeny implicitDeny) {
        this.implicitDeny = implicitDeny;
        return this;
    }

    public ExplicitDeny getExplicitDeny() {
        return this.explicitDeny;
    }

    public void setExplicitDeny(ExplicitDeny explicitDeny) {
        this.explicitDeny = explicitDeny;
    }

    public Denied withExplicitDeny(ExplicitDeny explicitDeny) {
        this.explicitDeny = explicitDeny;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getImplicitDeny() != null) {
            sb.append("implicitDeny: " + getImplicitDeny() + ",");
        }
        if (getExplicitDeny() != null) {
            sb.append("explicitDeny: " + getExplicitDeny());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getImplicitDeny() == null ? 0 : getImplicitDeny().hashCode()) + 31) * 31) + (getExplicitDeny() != null ? getExplicitDeny().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Denied)) {
            return false;
        }
        Denied denied = (Denied) obj;
        if ((denied.getImplicitDeny() == null) ^ (getImplicitDeny() == null)) {
            return false;
        }
        if (denied.getImplicitDeny() != null && !denied.getImplicitDeny().equals(getImplicitDeny())) {
            return false;
        }
        if ((denied.getExplicitDeny() == null) ^ (getExplicitDeny() == null)) {
            return false;
        }
        return denied.getExplicitDeny() == null || denied.getExplicitDeny().equals(getExplicitDeny());
    }
}
