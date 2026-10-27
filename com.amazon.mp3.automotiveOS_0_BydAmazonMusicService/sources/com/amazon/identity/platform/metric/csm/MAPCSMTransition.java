package com.amazon.identity.platform.metric.csm;

import android.content.Context;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public interface MAPCSMTransition {
    void addAttribute(String str, String str2);

    void addTag(String str);

    long mark(String str);

    void setRequestId(String str);

    void stopTransition(Context context);
}
