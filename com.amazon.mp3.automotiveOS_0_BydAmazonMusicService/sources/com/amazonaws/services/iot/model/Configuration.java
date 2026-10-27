package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class Configuration implements Serializable {
    private Boolean enabled;

    public Boolean isEnabled() {
        return this.enabled;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean bool) {
        this.enabled = bool;
    }

    public Configuration withEnabled(Boolean bool) {
        this.enabled = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getEnabled() != null) {
            sb.append("Enabled: " + getEnabled());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getEnabled() == null ? 0 : getEnabled().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Configuration)) {
            return false;
        }
        Configuration configuration = (Configuration) obj;
        if ((configuration.getEnabled() == null) ^ (getEnabled() == null)) {
            return false;
        }
        return configuration.getEnabled() == null || configuration.getEnabled().equals(getEnabled());
    }
}
