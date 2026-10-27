package com.neusoft.na.ehpdbuserver;

/* JADX INFO: loaded from: classes.dex */
public interface EhpDbuOnMsg {
    void onEhpDBUOnMessage(long j, long j2, String str);

    void onEhpDBUTargetDBVersion(long j, String str);

    void onEhpLicenseStatus(int i, long j, long j2, int i2, boolean z);

    void onEhpTIServiceStatus(boolean z);
}
