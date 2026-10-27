package com.amazon.device.crashmanager.rtla;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface RtlaCrashDetails {
    List<String> getActiveWebLabs();

    String getPageType();

    String getSessionId();

    String getSubPageType();

    String getUserAgent();
}
