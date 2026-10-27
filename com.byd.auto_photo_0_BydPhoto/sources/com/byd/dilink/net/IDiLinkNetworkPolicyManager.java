package com.byd.dilink.net;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkNetworkPolicyManager {
    void disableNat(String str, String str2);

    void enableNat(String str, String str2);

    void enableWhiteRules(boolean z, boolean z2);

    int[] getUidsWithPolicy(int i);

    boolean getWhiteListStats();

    void setMeteredRestrictedUid(int i, boolean z);

    void setUidPolicy(int i, int i2);

    void setUidWhiteRules(int i, boolean z);
}
