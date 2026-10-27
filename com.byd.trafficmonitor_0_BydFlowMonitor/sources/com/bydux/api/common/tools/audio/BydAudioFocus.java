package com.bydux.api.common.tools.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.provider.Settings;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class BydAudioFocus {
    private AudioManager mAudioManager;
    private Context mContext;
    private AudioFocusRequest mAudioFocusRequest = null;
    private AudioAttributes mAudioAttributes = null;
    private BydAudioFocusListener mListener = null;
    private int mAudioFocusType = -1;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.bydux.api.common.tools.audio.BydAudioFocus.1
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i2) {
            BydLogHelper.logV("type=" + i2 + ", pkgName=" + BydAudioFocus.this.mContext.getPackageName());
            BydAudioFocus.this.mAudioFocusType = i2;
            BydAudioFocus.this.notifyListener(i2);
        }
    };

    public BydAudioFocus(Context context) {
        this.mContext = null;
        this.mAudioManager = null;
        this.mContext = context;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
    }

    public static int getAudioFocusUsage(Context context) {
        int i2 = Settings.System.getInt(context.getContentResolver(), "mMediaUsage", 0);
        BydLogHelper.logV("source=" + i2);
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyListener(int i2) {
        BydLogHelper.logV("type=" + i2 + ", pkgName=" + this.mContext.getPackageName());
        BydAudioFocusListener bydAudioFocusListener = this.mListener;
        if (bydAudioFocusListener != null) {
            bydAudioFocusListener.onAudioFocusChange(i2);
        }
    }

    public void abandonAudioFocus() {
        BydLogHelper.logV("type=" + this.mAudioFocusType + ", pkgName=" + this.mContext.getPackageName());
        this.mAudioFocusType = -1;
        this.mAudioManager.abandonAudioFocusRequest(this.mAudioFocusRequest);
    }

    public int getAudioFocusType() {
        BydLogHelper.logV("type=" + this.mAudioFocusType);
        return this.mAudioFocusType;
    }

    public boolean hasAudioFocus() {
        BydLogHelper.logV("type=" + this.mAudioFocusType);
        int i2 = this.mAudioFocusType;
        return i2 == 1 || i2 == 2 || i2 == 3;
    }

    public void registerAudioFocusListener(BydAudioFocusListener bydAudioFocusListener) {
        this.mListener = bydAudioFocusListener;
    }

    public int requestAudioFocus(int i2, int i3) {
        BydLogHelper.logV("type=" + i2 + ", usage=" + i3 + ", pkgName=" + this.mContext.getPackageName());
        if (hasAudioFocus()) {
            BydLogHelper.logV("has focus now!");
            return 1;
        }
        this.mAudioAttributes = new AudioAttributes.Builder().setUsage(i3).build();
        AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(i2).setAudioAttributes(this.mAudioAttributes).setOnAudioFocusChangeListener(this.mAudioFocusListener).build();
        this.mAudioFocusRequest = audioFocusRequestBuild;
        int iRequestAudioFocus = this.mAudioManager.requestAudioFocus(audioFocusRequestBuild);
        if (iRequestAudioFocus == 1) {
            BydLogHelper.logV("success");
            this.mAudioFocusType = i2;
            notifyListener(i2);
        } else {
            BydLogHelper.logE("fail");
        }
        return iRequestAudioFocus;
    }

    public void unregisterAudioFocusListener() {
        this.mListener = null;
    }
}
