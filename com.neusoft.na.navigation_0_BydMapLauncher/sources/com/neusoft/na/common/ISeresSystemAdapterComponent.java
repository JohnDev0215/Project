package com.neusoft.na.common;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public interface ISeresSystemAdapterComponent {
    void destroyTts();

    void initTTS(Context context, int i);

    void playTts(String str);

    void setTtsCallback(SeresSystemAdapterCallback seresSystemAdapterCallback);

    void stopTts();

    void switchTtsLanguage(String str);
}
