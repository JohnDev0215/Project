package com.neusoft.na.common;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public interface IBydSystemAdapterComponent {
    void destroyTts();

    int getCarType();

    int getTtsAudioUsage();

    int getUiStyle();

    void initTTS(Context context);

    boolean isRightRudderMode();

    void playTts(String str);

    void setOverseasMapSupplier(int i);

    void setTtsCallback(BydSystemAdapterCallback bydSystemAdapterCallback);

    void startListenCarSpeedChange();

    void stopTts();

    void switchTtsLanguage(String str);
}
