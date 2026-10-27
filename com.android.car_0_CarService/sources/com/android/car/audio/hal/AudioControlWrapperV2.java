package com.android.car.audio.hal;

import android.hardware.automotive.audiocontrol.V2_0.IAudioControl;
import android.hardware.automotive.audiocontrol.V2_0.ICloseHandle;
import android.hardware.automotive.audiocontrol.V2_0.IFocusListener;
import android.media.AudioAttributes;
import android.os.RemoteException;
import android.util.Log;
import com.android.car.audio.hal.AudioControlWrapper;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class AudioControlWrapperV2 implements AudioControlWrapper {
    private static final String TAG = AudioControlWrapperV2.class.getSimpleName();
    private IAudioControl mAudioControlV2;
    private ICloseHandle mCloseHandle;
    private AudioControlWrapper.AudioControlDeathRecipient mDeathRecipient;

    public static IAudioControl getService() {
        try {
            return IAudioControl.getService(true);
        } catch (RemoteException e) {
            throw new IllegalStateException("Failed to get IAudioControl@2.0 service", e);
        } catch (NoSuchElementException e2) {
            return null;
        }
    }

    AudioControlWrapperV2(IAudioControl audioControlV2) {
        Objects.requireNonNull(audioControlV2);
        this.mAudioControlV2 = audioControlV2;
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void unregisterFocusListener() {
        ICloseHandle iCloseHandle = this.mCloseHandle;
        if (iCloseHandle != null) {
            try {
                try {
                    iCloseHandle.close();
                } catch (RemoteException e) {
                    Log.e(TAG, "Failed to close focus listener", e);
                }
            } finally {
                this.mCloseHandle = null;
            }
        }
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public boolean supportsHalAudioFocus() {
        return true;
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void registerFocusListener(IFocusListener focusListener) {
        Log.d(TAG, "Registering focus listener on AudioControl HAL");
        try {
            this.mCloseHandle = this.mAudioControlV2.registerFocusListener(focusListener);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to register focus listener");
            throw new IllegalStateException("IAudioControl#registerFocusListener failed", e);
        }
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void onAudioFocusChange(int usage, int zoneId, int focusChange) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "onAudioFocusChange: usage " + AudioAttributes.usageToString(usage) + ", zoneId " + zoneId + ", focusChange " + focusChange);
        }
        try {
            this.mAudioControlV2.onAudioFocusChange(usage, zoneId, focusChange);
        } catch (RemoteException e) {
            throw new IllegalStateException("Failed to query IAudioControl#onAudioFocusChange", e);
        }
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void dump(String indent, PrintWriter writer) {
        writer.printf("%s*AudioControlWrapperV2*\n", indent);
        Object[] objArr = new Object[2];
        objArr[0] = indent;
        objArr[1] = Boolean.valueOf(this.mCloseHandle != null);
        writer.printf("%s\tFocus listener registered on HAL? %b", objArr);
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void setFadeTowardFront(float value) {
        try {
            this.mAudioControlV2.setFadeTowardFront(value);
        } catch (RemoteException e) {
            Log.e(TAG, "setFadeTowardFront failed", e);
        }
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void setBalanceTowardRight(float value) {
        try {
            this.mAudioControlV2.setBalanceTowardRight(value);
        } catch (RemoteException e) {
            Log.e(TAG, "setBalanceTowardRight failed", e);
        }
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void linkToDeath(AudioControlWrapper.AudioControlDeathRecipient deathRecipient) {
        try {
            this.mAudioControlV2.linkToDeath(new $$Lambda$AudioControlWrapperV2$1LDFeHbsBvYnZGrFG68ICZEM1D0(this), 0L);
            this.mDeathRecipient = deathRecipient;
        } catch (RemoteException e) {
            throw new IllegalStateException("Call to IAudioControl@2.0#linkToDeath failed", e);
        }
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void unlinkToDeath() {
        try {
            this.mAudioControlV2.unlinkToDeath(new $$Lambda$AudioControlWrapperV2$1LDFeHbsBvYnZGrFG68ICZEM1D0(this));
            this.mDeathRecipient = null;
        } catch (RemoteException e) {
            throw new IllegalStateException("Call to IAudioControl@2.0#unlinkToDeath failed", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void serviceDied(long cookie) {
        Log.w(TAG, "IAudioControl@2.0 died. Fetching new handle");
        this.mAudioControlV2 = getService();
        linkToDeath(this.mDeathRecipient);
        AudioControlWrapper.AudioControlDeathRecipient audioControlDeathRecipient = this.mDeathRecipient;
        if (audioControlDeathRecipient != null) {
            audioControlDeathRecipient.serviceDied();
        }
    }
}
