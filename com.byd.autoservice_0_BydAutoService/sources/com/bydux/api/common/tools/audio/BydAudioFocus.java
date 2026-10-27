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
        public void onAudioFocusChange(int i3) {
            BydLogHelper.logV("type=" + i3 + ", pkgName=" + BydAudioFocus.this.mContext.getPackageName());
            BydAudioFocus.this.mAudioFocusType = i3;
            BydAudioFocus.this.notifyListener(i3);
        }
    };

    public BydAudioFocus(Context context) {
        this.mContext = null;
        this.mAudioManager = null;
        this.mContext = context;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
    }

    public static int getAudioFocusUsage(Context context) {
        int i3 = Settings.System.getInt(context.getContentResolver(), "mMediaUsage", 0);
        BydLogHelper.logV("source=" + i3);
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyListener(int i3) {
        BydLogHelper.logV("type=" + i3 + ", pkgName=" + this.mContext.getPackageName());
        BydAudioFocusListener bydAudioFocusListener = this.mListener;
        if (bydAudioFocusListener != null) {
            bydAudioFocusListener.onAudioFocusChange(i3);
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
        int i3 = this.mAudioFocusType;
        return i3 == 1 || i3 == 2 || i3 == 3;
    }

    public void registerAudioFocusListener(BydAudioFocusListener bydAudioFocusListener) {
        this.mListener = bydAudioFocusListener;
    }

    public int requestAudioFocus(int i3, int i4) {
        BydLogHelper.logV("type=" + i3 + ", usage=" + i4 + ", pkgName=" + this.mContext.getPackageName());
        if (hasAudioFocus()) {
            BydLogHelper.logV("has focus now!");
            return 1;
        }
        this.mAudioAttributes = new AudioAttributes.Builder().setUsage(i4).build();
        AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(i3).setAudioAttributes(this.mAudioAttributes).setOnAudioFocusChangeListener(this.mAudioFocusListener).build();
        this.mAudioFocusRequest = audioFocusRequestBuild;
        int iRequestAudioFocus = this.mAudioManager.requestAudioFocus(audioFocusRequestBuild);
        if (iRequestAudioFocus == 1) {
            BydLogHelper.logV("success");
            this.mAudioFocusType = i3;
            notifyListener(i3);
        } else {
            BydLogHelper.logE("fail");
        }
        return iRequestAudioFocus;
    }

    public void unregisterAudioFocusListener() {
        this.mListener = null;
    }
}
