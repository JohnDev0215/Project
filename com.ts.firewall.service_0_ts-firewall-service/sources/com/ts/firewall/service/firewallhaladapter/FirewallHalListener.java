package com.ts.firewall.service.firewallhaladapter;

/* JADX INFO: loaded from: classes.dex */
public interface FirewallHalListener {
    void onFirewallStatusChange(boolean z);

    void onGetLogResponse(int i, int i2, int i3, String str);

    void onGetRuleResponse(int i, int i2, int i3, String str);

    void onRequestFinished(int i, int i2);

    void onWhiteListStatusChange(boolean z);
}
