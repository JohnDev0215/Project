package com.android.car.audio.hal;

import android.hardware.automotive.audiocontrol.V1_0.IAudioControl;
import android.hardware.automotive.audiocontrol.V2_0.IFocusListener;
import android.os.RemoteException;
import android.util.Log;
import com.android.car.audio.hal.AudioControlWrapper;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class AudioControlWrapperV1 implements AudioControlWrapper {
    private static final String TAG = AudioControlWrapperV1.class.getSimpleName();
    private IAudioControl mAudioControlV1;
    private AudioControlWrapper.AudioControlDeathRecipient mDeathRecipient;

    public static IAudioControl getService() {
        try {
            return IAudioControl.getService(true);
        } catch (RemoteException e) {
            throw new IllegalStateException("Failed to get IAudioControl@1.0 service", e);
        } catch (NoSuchElementException e2) {
            return null;
        }
    }

    AudioControlWrapperV1(IAudioControl audioControlV1) {
        Objects.requireNonNull(audioControlV1);
        this.mAudioControlV1 = audioControlV1;
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public boolean supportsHalAudioFocus() {
        return false;
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void registerFocusListener(IFocusListener focusListener) {
        throw new UnsupportedOperationException("Focus listener is unsupported for IAudioControl@1.0");
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void unregisterFocusListener() {
        throw new UnsupportedOperationException("Focus listener is unsupported for IAudioControl@1.0");
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void onAudioFocusChange(int usage, int zoneId, int focusChange) {
        throw new UnsupportedOperationException("Focus listener is unsupported for IAudioControl@1.0");
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void dump(String indent, PrintWriter writer) {
        writer.printf("%s*AudioControlWrapperV1*\n", indent);
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void setFadeTowardFront(float value) {
        try {
            this.mAudioControlV1.setFadeTowardFront(value);
        } catch (RemoteException e) {
            Log.e(TAG, "setFadeTowardFront failed", e);
        }
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void setBalanceTowardRight(float value) {
        try {
            this.mAudioControlV1.setBalanceTowardRight(value);
        } catch (RemoteException e) {
            Log.e(TAG, "setBalanceTowardRight failed", e);
        }
    }

    @Deprecated
    public int getBusForContext(int audioContext) {
        try {
            return this.mAudioControlV1.getBusForContext(audioContext);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to query IAudioControl HAL to get bus for context", e);
            throw new IllegalStateException("Failed to query IAudioControl#getBusForContext", e);
        }
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void linkToDeath(AudioControlWrapper.AudioControlDeathRecipient deathRecipient) {
        try {
            this.mAudioControlV1.linkToDeath(new $$Lambda$AudioControlWrapperV1$GmHsHZmywBeSZ4Vpqn_BffHdl2k(this), 0L);
            this.mDeathRecipient = deathRecipient;
        } catch (RemoteException e) {
            throw new IllegalStateException("Call to IAudioControl@1.0#linkToDeath failed", e);
        }
    }

    @Override // com.android.car.audio.hal.AudioControlWrapper
    public void unlinkToDeath() {
        try {
            this.mAudioControlV1.unlinkToDeath(new $$Lambda$AudioControlWrapperV1$GmHsHZmywBeSZ4Vpqn_BffHdl2k(this));
            this.mDeathRecipient = null;
        } catch (RemoteException e) {
            throw new IllegalStateException("Call to IAudioControl@1.0#unlinkToDeath failed", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void serviceDied(long cookie) {
        Log.w(TAG, "IAudioControl@1.0 died. Fetching new handle");
        this.mAudioControlV1 = getService();
        linkToDeath(this.mDeathRecipient);
        AudioControlWrapper.AudioControlDeathRecipient audioControlDeathRecipient = this.mDeathRecipient;
        if (audioControlDeathRecipient != null) {
            audioControlDeathRecipient.serviceDied();
        }
    }
}
