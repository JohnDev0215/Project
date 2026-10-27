package com.loostone.libtuning.inf.extern;

/* JADX INFO: loaded from: classes.dex */
public interface IAudioFFTProcess {
    void pause();

    void registerFFTReadListener(IFFTReadListener iFFTReadListener);

    void release();

    void resume();

    void start();

    void stop();

    void unRegisterFFTReadListener();
}
