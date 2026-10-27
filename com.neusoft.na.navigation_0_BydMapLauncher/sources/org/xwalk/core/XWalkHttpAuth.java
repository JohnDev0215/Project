package org.xwalk.core;

/* JADX INFO: loaded from: classes4.dex */
public interface XWalkHttpAuth {
    void cancel();

    boolean isFirstAttempt();

    void proceed(String str, String str2);
}
