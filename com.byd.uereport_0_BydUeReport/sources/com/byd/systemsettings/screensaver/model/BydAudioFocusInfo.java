package com.byd.systemsettings.screensaver.model;

import android.media.AudioFocusInfo;

/* JADX INFO: loaded from: classes2.dex */
public class BydAudioFocusInfo {
    public AudioFocusInfo info;
    public boolean isMediaType;

    public BydAudioFocusInfo(AudioFocusInfo audioFocusInfo, boolean z7) {
        this.info = audioFocusInfo;
        this.isMediaType = z7;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("BydAudioFocusInfo{info=");
        if (this.info == null) {
            str = "null";
        } else {
            str = "{usage: " + this.info.getAttributes().getUsage() + ", pkg: " + this.info.getPackageName() + "}";
        }
        sb.append(str);
        sb.append(", isMediaType=");
        sb.append(this.isMediaType);
        sb.append('}');
        return sb.toString();
    }
}
