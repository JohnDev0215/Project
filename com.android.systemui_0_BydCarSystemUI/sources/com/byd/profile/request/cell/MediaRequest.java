package com.byd.profile.request.cell;

import android.content.Context;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.media.AudioManager;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class MediaRequest extends BaseRequest<MediaValue> {
    private AudioManager mAudioManager;
    private BYDAutoAudioDevice mBYDAutoAudioDevice;

    public MediaRequest(Context context, MediaValue wantedValue) {
        super(context, wantedValue);
        this.mBYDAutoAudioDevice = BYDAutoAudioDevice.getInstance(context);
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    protected boolean reset() {
        if (this.mBYDAutoAudioDevice.getVolume() != ((MediaValue) this.mWantedValue).volume) {
            BydLogHelper.logD("skip handleRequest");
            return true;
        }
        return super.reset();
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public boolean handleRequest(MediaValue requestValue) {
        BydLogHelper.logD("handleRequest MediaValue");
        BYDAutoAudioDevice bYDAutoAudioDevice = this.mBYDAutoAudioDevice;
        if (bYDAutoAudioDevice == null) {
            return true;
        }
        try {
            bYDAutoAudioDevice.setVolume(requestValue.volume);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public void initRequestValues(MediaValue wantValue) {
        BYDAutoAudioDevice bYDAutoAudioDevice = this.mBYDAutoAudioDevice;
        if (bYDAutoAudioDevice != null) {
            try {
                int volume = bYDAutoAudioDevice.getVolume();
                BydLogHelper.logD("initRequestValues volume = " + volume);
                if (volume < wantValue.volume) {
                    wantValue.volume = volume;
                }
                this.mCurrentValue = new MediaValue(volume);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static class MediaValue extends BaseValue {
        protected int volume;

        public MediaValue(int volume) {
            this.volume = volume;
        }
    }
}
