package com.amazon.KiangService;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;

/* JADX INFO: loaded from: classes2.dex */
public class PushInformation implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.KiangService.PushInformation");
    private String provider;
    private String providerKey;
    private String secret;

    public String getProviderKey() {
        return this.providerKey;
    }

    public void setProviderKey(String str) {
        this.providerKey = str;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String str) {
        this.secret = str;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.providerKey, this.secret, this.provider);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PushInformation)) {
            return false;
        }
        PushInformation pushInformation = (PushInformation) obj;
        return Helper.equals(this.providerKey, pushInformation.providerKey) && Helper.equals(this.secret, pushInformation.secret) && Helper.equals(this.provider, pushInformation.provider);
    }
}
