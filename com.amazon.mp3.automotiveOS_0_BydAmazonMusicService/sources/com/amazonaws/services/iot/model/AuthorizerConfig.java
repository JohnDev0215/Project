package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AuthorizerConfig implements Serializable {
    private Boolean allowAuthorizerOverride;
    private String defaultAuthorizerName;

    public String getDefaultAuthorizerName() {
        return this.defaultAuthorizerName;
    }

    public void setDefaultAuthorizerName(String str) {
        this.defaultAuthorizerName = str;
    }

    public AuthorizerConfig withDefaultAuthorizerName(String str) {
        this.defaultAuthorizerName = str;
        return this;
    }

    public Boolean isAllowAuthorizerOverride() {
        return this.allowAuthorizerOverride;
    }

    public Boolean getAllowAuthorizerOverride() {
        return this.allowAuthorizerOverride;
    }

    public void setAllowAuthorizerOverride(Boolean bool) {
        this.allowAuthorizerOverride = bool;
    }

    public AuthorizerConfig withAllowAuthorizerOverride(Boolean bool) {
        this.allowAuthorizerOverride = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDefaultAuthorizerName() != null) {
            sb.append("defaultAuthorizerName: " + getDefaultAuthorizerName() + ",");
        }
        if (getAllowAuthorizerOverride() != null) {
            sb.append("allowAuthorizerOverride: " + getAllowAuthorizerOverride());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getDefaultAuthorizerName() == null ? 0 : getDefaultAuthorizerName().hashCode()) + 31) * 31) + (getAllowAuthorizerOverride() != null ? getAllowAuthorizerOverride().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuthorizerConfig)) {
            return false;
        }
        AuthorizerConfig authorizerConfig = (AuthorizerConfig) obj;
        if ((authorizerConfig.getDefaultAuthorizerName() == null) ^ (getDefaultAuthorizerName() == null)) {
            return false;
        }
        if (authorizerConfig.getDefaultAuthorizerName() != null && !authorizerConfig.getDefaultAuthorizerName().equals(getDefaultAuthorizerName())) {
            return false;
        }
        if ((authorizerConfig.getAllowAuthorizerOverride() == null) ^ (getAllowAuthorizerOverride() == null)) {
            return false;
        }
        return authorizerConfig.getAllowAuthorizerOverride() == null || authorizerConfig.getAllowAuthorizerOverride().equals(getAllowAuthorizerOverride());
    }
}
