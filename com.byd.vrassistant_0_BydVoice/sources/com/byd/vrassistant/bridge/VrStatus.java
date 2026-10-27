package com.byd.vrassistant.bridge;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public interface VrStatus {
    boolean getEnableWordStreaming();

    Locale getLocale(String str);

    int getSkinType();

    boolean isAAForeground();

    boolean isMultiDialogEnabled();

    boolean isVoiceOnly();
}
