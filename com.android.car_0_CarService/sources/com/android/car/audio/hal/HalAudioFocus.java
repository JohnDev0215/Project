package com.android.car.audio.hal;

import android.hardware.automotive.audiocontrol.V2_0.IFocusListener;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.util.Preconditions;
import java.io.PrintWriter;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class HalAudioFocus extends IFocusListener.Stub {
    private static final String TAG = HalAudioFocus.class.getSimpleName();
    private final AudioControlWrapper mAudioControlWrapper;
    private final AudioManager mAudioManager;
    private final SparseArray<SparseArray<HalAudioFocusRequest>> mHalFocusRequestsByZoneAndUsage;
    private final Object mLock = new Object();

    public HalAudioFocus(AudioManager audioManager, AudioControlWrapper audioControlWrapper, int[] audioZoneIds) {
        Objects.requireNonNull(audioManager);
        this.mAudioManager = audioManager;
        Objects.requireNonNull(audioControlWrapper);
        this.mAudioControlWrapper = audioControlWrapper;
        Objects.requireNonNull(audioZoneIds);
        this.mHalFocusRequestsByZoneAndUsage = new SparseArray<>(audioZoneIds.length);
        for (int zoneId : audioZoneIds) {
            this.mHalFocusRequestsByZoneAndUsage.append(zoneId, new SparseArray<>());
        }
    }

    public void registerFocusListener() {
        this.mAudioControlWrapper.registerFocusListener(this);
    }

    public void unregisterFocusListener() {
        this.mAudioControlWrapper.unregisterFocusListener();
    }

    @Override // android.hardware.automotive.audiocontrol.V2_0.IFocusListener
    public void requestAudioFocus(int usage, int zoneId, int focusGain) {
        Preconditions.checkArgument(this.mHalFocusRequestsByZoneAndUsage.contains(zoneId), "Invalid zoneId %d provided in requestAudioFocus", new Object[]{Integer.valueOf(zoneId)});
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Requesting focus gain " + focusGain + " with usage " + AudioAttributes.usageToString(usage) + " and zoneId " + zoneId);
        }
        synchronized (this.mLock) {
            HalAudioFocusRequest currentRequest = this.mHalFocusRequestsByZoneAndUsage.get(zoneId).get(usage);
            if (currentRequest != null) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "A request already exists for zoneId " + zoneId + " and usage " + usage);
                }
                this.mAudioControlWrapper.onAudioFocusChange(usage, zoneId, currentRequest.mFocusStatus);
            } else {
                makeAudioFocusRequestLocked(usage, zoneId, focusGain);
            }
        }
    }

    @Override // android.hardware.automotive.audiocontrol.V2_0.IFocusListener
    public void abandonAudioFocus(int usage, int zoneId) throws RemoteException {
        Preconditions.checkArgument(this.mHalFocusRequestsByZoneAndUsage.contains(zoneId), "Invalid zoneId %d provided in abandonAudioFocus", new Object[]{Integer.valueOf(zoneId)});
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Abandoning focus with usage " + AudioAttributes.usageToString(usage) + " for zoneId " + zoneId);
        }
        synchronized (this.mLock) {
            abandonAudioFocusLocked(usage, zoneId);
        }
    }

    public void reset() {
        Log.d(TAG, "Resetting HAL Audio Focus requests");
        synchronized (this.mLock) {
            for (int i = 0; i < this.mHalFocusRequestsByZoneAndUsage.size(); i++) {
                int zoneId = this.mHalFocusRequestsByZoneAndUsage.keyAt(i);
                SparseArray<HalAudioFocusRequest> requestsByUsage = this.mHalFocusRequestsByZoneAndUsage.valueAt(i);
                int usageCount = requestsByUsage.size();
                for (int j = 0; j < usageCount; j++) {
                    int usage = requestsByUsage.keyAt(j);
                    abandonAudioFocusLocked(usage, zoneId);
                }
            }
        }
    }

    public void dump(String indent, PrintWriter writer) {
        writer.printf("%s*HalAudioFocus*\n", indent);
        writer.printf("%s\tCurrent focus requests:\n", indent);
        for (int i = 0; i < this.mHalFocusRequestsByZoneAndUsage.size(); i++) {
            int zoneId = this.mHalFocusRequestsByZoneAndUsage.keyAt(i);
            writer.printf("%s\t\tZone %s:\n", indent, Integer.valueOf(zoneId));
            SparseArray<HalAudioFocusRequest> requestsByUsage = this.mHalFocusRequestsByZoneAndUsage.valueAt(i);
            for (int j = 0; j < requestsByUsage.size(); j++) {
                int usage = requestsByUsage.keyAt(j);
                HalAudioFocusRequest request = requestsByUsage.valueAt(j);
                writer.printf("%s\t\t\t%s - focusGain: %s\n", indent, AudioAttributes.usageToString(usage), Integer.valueOf(request.mFocusStatus));
            }
        }
    }

    private void abandonAudioFocusLocked(int usage, int zoneId) {
        HalAudioFocusRequest currentRequest = (HalAudioFocusRequest) this.mHalFocusRequestsByZoneAndUsage.get(zoneId).removeReturnOld(usage);
        if (currentRequest == null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "No focus to abandon for usage " + AudioAttributes.usageToString(usage) + " and zoneId " + zoneId);
                return;
            }
            return;
        }
        int result = this.mAudioManager.abandonAudioFocusRequest(currentRequest.mAudioFocusRequest);
        if (result == 1) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Abandoned focus for usage " + AudioAttributes.usageToString(usage) + "and zoneId " + zoneId);
            }
            this.mAudioControlWrapper.onAudioFocusChange(usage, zoneId, -1);
            return;
        }
        Log.w(TAG, "Failed to abandon focus for usage " + AudioAttributes.usageToString(usage) + " and zoneId " + zoneId);
    }

    private AudioAttributes generateAudioAttributes(int usage, int zoneId) {
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        Bundle bundle = new Bundle();
        bundle.putInt("android.car.media.AUDIOFOCUS_EXTRA_REQUEST_ZONE_ID", zoneId);
        builder.addBundle(bundle);
        if (AudioAttributes.isSystemUsage(usage)) {
            builder.setSystemUsage(usage);
        } else {
            builder.setUsage(usage);
        }
        return builder.build();
    }

    private AudioFocusRequest generateFocusRequestLocked(final int usage, final int zoneId, int focusGain) {
        AudioAttributes attributes = generateAudioAttributes(usage, zoneId);
        return new AudioFocusRequest.Builder(focusGain).setAudioAttributes(attributes).setOnAudioFocusChangeListener(new AudioManager.OnAudioFocusChangeListener() { // from class: com.android.car.audio.hal.-$$Lambda$HalAudioFocus$SNmMD7XSbRTfzR5KNU114vHfU04
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i) {
                this.f$0.lambda$generateFocusRequestLocked$0$HalAudioFocus(usage, zoneId, i);
            }
        }).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onAudioFocusChange, reason: merged with bridge method [inline-methods] */
    public void lambda$generateFocusRequestLocked$0$HalAudioFocus(int usage, int zoneId, int focusChange) {
        synchronized (this.mLock) {
            HalAudioFocusRequest currentRequest = this.mHalFocusRequestsByZoneAndUsage.get(zoneId).get(usage);
            if (currentRequest != null) {
                if (focusChange == -1) {
                    this.mHalFocusRequestsByZoneAndUsage.get(zoneId).remove(usage);
                } else {
                    currentRequest.mFocusStatus = focusChange;
                }
                this.mAudioControlWrapper.onAudioFocusChange(usage, zoneId, focusChange);
            }
        }
    }

    private void makeAudioFocusRequestLocked(int usage, int zoneId, int focusGain) {
        AudioFocusRequest audioFocusRequest = generateFocusRequestLocked(usage, zoneId, focusGain);
        int requestResult = this.mAudioManager.requestAudioFocus(audioFocusRequest);
        int resultingFocusGain = focusGain;
        if (requestResult == 1) {
            HalAudioFocusRequest halAudioFocusRequest = new HalAudioFocusRequest(audioFocusRequest, focusGain);
            this.mHalFocusRequestsByZoneAndUsage.get(zoneId).append(usage, halAudioFocusRequest);
        } else if (requestResult == 0) {
            resultingFocusGain = -1;
        } else if (requestResult == 2) {
            Log.w(TAG, "Delayed result for request with usage " + AudioAttributes.usageToString(usage) + ", zoneId " + zoneId + ", and focusGain " + focusGain);
            resultingFocusGain = -1;
        }
        this.mAudioControlWrapper.onAudioFocusChange(usage, zoneId, resultingFocusGain);
    }

    private final class HalAudioFocusRequest {
        final AudioFocusRequest mAudioFocusRequest;
        int mFocusStatus;

        HalAudioFocusRequest(AudioFocusRequest audioFocusRequest, int focusStatus) {
            this.mAudioFocusRequest = audioFocusRequest;
            this.mFocusStatus = focusStatus;
        }
    }
}
