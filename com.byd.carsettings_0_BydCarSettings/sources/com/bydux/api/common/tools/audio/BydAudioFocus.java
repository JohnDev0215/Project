package com.bydux.api.common.tools.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.provider.Settings;
import androidx.annotation.RequiresApi;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes3.dex */
public class BydAudioFocus {
    private AudioManager mAudioManager;
    private Context mContext;
    private AudioFocusRequest mAudioFocusRequest = null;
    private AudioAttributes mAudioAttributes = null;
    private BydAudioFocusListener mListener = null;
    private int mAudioFocusType = -1;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.bydux.api.common.tools.audio.BydAudioFocus.1
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            BydLogHelper.logV("type=" + i + ", pkgName=" + BydAudioFocus.this.mContext.getPackageName());
            BydAudioFocus.this.mAudioFocusType = i;
            BydAudioFocus.this.notifyListener(i);
        }
    };

    public BydAudioFocus(Context context) {
        this.mContext = null;
        this.mAudioManager = null;
        this.mContext = context;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
    }

    @RequiresApi(api = 26)
    public static int getAudioFocusUsage(Context context) {
        int i = Settings.System.getInt(context.getContentResolver(), "mMediaUsage", 0);
        BydLogHelper.logV("source=" + i);
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyListener(int i) {
        BydLogHelper.logV("type=" + i + ", pkgName=" + this.mContext.getPackageName());
        BydAudioFocusListener bydAudioFocusListener = this.mListener;
        if (bydAudioFocusListener != null) {
            bydAudioFocusListener.onAudioFocusChange(i);
        }
    }

    @RequiresApi(api = 26)
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
        int i = this.mAudioFocusType;
        return i == 1 || i == 2 || i == 3;
    }

    public void registerAudioFocusListener(BydAudioFocusListener bydAudioFocusListener) {
        this.mListener = bydAudioFocusListener;
    }

    @RequiresApi(api = 26)
    public int requestAudioFocus(int i, int i2) {
        BydLogHelper.logV("type=" + i + ", usage=" + i2 + ", pkgName=" + this.mContext.getPackageName());
        if (hasAudioFocus()) {
            BydLogHelper.logV("has focus now!");
            return 1;
        }
        this.mAudioAttributes = new AudioAttributes.Builder().setUsage(i2).build();
        AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(i).setAudioAttributes(this.mAudioAttributes).setOnAudioFocusChangeListener(this.mAudioFocusListener).build();
        this.mAudioFocusRequest = audioFocusRequestBuild;
        int iRequestAudioFocus = this.mAudioManager.requestAudioFocus(audioFocusRequestBuild);
        if (iRequestAudioFocus == 1) {
            BydLogHelper.logV("success");
            this.mAudioFocusType = i;
            notifyListener(i);
        } else {
            BydLogHelper.logE("fail");
        }
        return iRequestAudioFocus;
    }

    public void unregisterAudioFocusListener() {
        this.mListener = null;
    }
}
