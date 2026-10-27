package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class LambdaAction implements Serializable {
    private String functionArn;

    public String getFunctionArn() {
        return this.functionArn;
    }

    public void setFunctionArn(String str) {
        this.functionArn = str;
    }

    public LambdaAction withFunctionArn(String str) {
        this.functionArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getFunctionArn() != null) {
            sb.append("functionArn: " + getFunctionArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getFunctionArn() == null ? 0 : getFunctionArn().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof LambdaAction)) {
            return false;
        }
        LambdaAction lambdaAction = (LambdaAction) obj;
        if ((lambdaAction.getFunctionArn() == null) ^ (getFunctionArn() == null)) {
            return false;
        }
        return lambdaAction.getFunctionArn() == null || lambdaAction.getFunctionArn().equals(getFunctionArn());
    }
}
