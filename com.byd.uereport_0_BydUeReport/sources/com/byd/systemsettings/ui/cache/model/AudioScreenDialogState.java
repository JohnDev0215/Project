package com.byd.systemsettings.ui.cache.model;

import com.byd.systemsettings.system.model.ClockStyle;

/* JADX INFO: loaded from: classes2.dex */
public class AudioScreenDialogState extends DialogState {
    private int curMusicSrc;
    private int curPlayState;
    private ClockStyle curStyle;

    public AudioScreenDialogState(ClockStyle clockStyle, int i8, int i9) {
        this.curStyle = clockStyle;
        this.curPlayState = i8;
        this.curMusicSrc = i9;
    }

    public int getCurMusicSrc() {
        return this.curMusicSrc;
    }

    public int getCurPlayState() {
        return this.curPlayState;
    }

    public ClockStyle getCurStyle() {
        return this.curStyle;
    }

    public void setCurMusicSrc(int i8) {
        this.curMusicSrc = i8;
    }

    public void setCurPlayState(int i8) {
        this.curPlayState = i8;
    }

    public void setCurStyle(ClockStyle clockStyle) {
        this.curStyle = clockStyle;
    }
}
