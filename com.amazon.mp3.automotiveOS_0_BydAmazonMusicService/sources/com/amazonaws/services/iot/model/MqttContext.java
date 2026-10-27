package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes5.dex */
public class MqttContext implements Serializable {
    private String clientId;
    private ByteBuffer password;
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public MqttContext withUsername(String str) {
        this.username = str;
        return this;
    }

    public ByteBuffer getPassword() {
        return this.password;
    }

    public void setPassword(ByteBuffer byteBuffer) {
        this.password = byteBuffer;
    }

    public MqttContext withPassword(ByteBuffer byteBuffer) {
        this.password = byteBuffer;
        return this;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public MqttContext withClientId(String str) {
        this.clientId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getUsername() != null) {
            sb.append("username: " + getUsername() + ",");
        }
        if (getPassword() != null) {
            sb.append("password: " + getPassword() + ",");
        }
        if (getClientId() != null) {
            sb.append("clientId: " + getClientId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getUsername() == null ? 0 : getUsername().hashCode()) + 31) * 31) + (getPassword() == null ? 0 : getPassword().hashCode())) * 31) + (getClientId() != null ? getClientId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MqttContext)) {
            return false;
        }
        MqttContext mqttContext = (MqttContext) obj;
        if ((mqttContext.getUsername() == null) ^ (getUsername() == null)) {
            return false;
        }
        if (mqttContext.getUsername() != null && !mqttContext.getUsername().equals(getUsername())) {
            return false;
        }
        if ((mqttContext.getPassword() == null) ^ (getPassword() == null)) {
            return false;
        }
        if (mqttContext.getPassword() != null && !mqttContext.getPassword().equals(getPassword())) {
            return false;
        }
        if ((mqttContext.getClientId() == null) ^ (getClientId() == null)) {
            return false;
        }
        return mqttContext.getClientId() == null || mqttContext.getClientId().equals(getClientId());
    }
}
