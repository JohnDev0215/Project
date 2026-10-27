package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetRegistrationCodeResult implements Serializable {
    private String registrationCode;

    public String getRegistrationCode() {
        return this.registrationCode;
    }

    public void setRegistrationCode(String str) {
        this.registrationCode = str;
    }

    public GetRegistrationCodeResult withRegistrationCode(String str) {
        this.registrationCode = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRegistrationCode() != null) {
            sb.append("registrationCode: " + getRegistrationCode());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getRegistrationCode() == null ? 0 : getRegistrationCode().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetRegistrationCodeResult)) {
            return false;
        }
        GetRegistrationCodeResult getRegistrationCodeResult = (GetRegistrationCodeResult) obj;
        if ((getRegistrationCodeResult.getRegistrationCode() == null) ^ (getRegistrationCode() == null)) {
            return false;
        }
        return getRegistrationCodeResult.getRegistrationCode() == null || getRegistrationCodeResult.getRegistrationCode().equals(getRegistrationCode());
    }
}
