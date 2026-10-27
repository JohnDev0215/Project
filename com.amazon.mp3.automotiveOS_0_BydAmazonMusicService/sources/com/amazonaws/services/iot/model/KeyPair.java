package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class KeyPair implements Serializable {
    private String privateKey;
    private String publicKey;

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String str) {
        this.publicKey = str;
    }

    public KeyPair withPublicKey(String str) {
        this.publicKey = str;
        return this;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(String str) {
        this.privateKey = str;
    }

    public KeyPair withPrivateKey(String str) {
        this.privateKey = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPublicKey() != null) {
            sb.append("PublicKey: " + getPublicKey() + ",");
        }
        if (getPrivateKey() != null) {
            sb.append("PrivateKey: " + getPrivateKey());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getPublicKey() == null ? 0 : getPublicKey().hashCode()) + 31) * 31) + (getPrivateKey() != null ? getPrivateKey().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof KeyPair)) {
            return false;
        }
        KeyPair keyPair = (KeyPair) obj;
        if ((keyPair.getPublicKey() == null) ^ (getPublicKey() == null)) {
            return false;
        }
        if (keyPair.getPublicKey() != null && !keyPair.getPublicKey().equals(getPublicKey())) {
            return false;
        }
        if ((keyPair.getPrivateKey() == null) ^ (getPrivateKey() == null)) {
            return false;
        }
        return keyPair.getPrivateKey() == null || keyPair.getPrivateKey().equals(getPrivateKey());
    }
}
