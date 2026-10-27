package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class SetLoggingOptionsRequest extends AmazonWebServiceRequest implements Serializable {
    private LoggingOptionsPayload loggingOptionsPayload;

    public LoggingOptionsPayload getLoggingOptionsPayload() {
        return this.loggingOptionsPayload;
    }

    public void setLoggingOptionsPayload(LoggingOptionsPayload loggingOptionsPayload) {
        this.loggingOptionsPayload = loggingOptionsPayload;
    }

    public SetLoggingOptionsRequest withLoggingOptionsPayload(LoggingOptionsPayload loggingOptionsPayload) {
        this.loggingOptionsPayload = loggingOptionsPayload;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getLoggingOptionsPayload() != null) {
            sb.append("loggingOptionsPayload: " + getLoggingOptionsPayload());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getLoggingOptionsPayload() == null ? 0 : getLoggingOptionsPayload().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SetLoggingOptionsRequest)) {
            return false;
        }
        SetLoggingOptionsRequest setLoggingOptionsRequest = (SetLoggingOptionsRequest) obj;
        if ((setLoggingOptionsRequest.getLoggingOptionsPayload() == null) ^ (getLoggingOptionsPayload() == null)) {
            return false;
        }
        return setLoggingOptionsRequest.getLoggingOptionsPayload() == null || setLoggingOptionsRequest.getLoggingOptionsPayload().equals(getLoggingOptionsPayload());
    }
}
