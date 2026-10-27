package com.android.server.telecom;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.AudioRecordingConfiguration;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.telecom.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.server.telecom.TelecomSystem;
import com.android.server.telecom.Timeouts;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class CallRecordingTonePlayer extends CallsManagerListenerBase {
    private final AudioManager mAudioManager;
    private final Context mContext;
    private final TelecomSystem.SyncRoot mLock;
    private LoopingTonePlayer mLoopingTonePlayer;
    private final long mRepeatInterval;
    private AudioManager.AudioRecordingCallback mAudioRecordingCallback = new AudioManager.AudioRecordingCallback() { // from class: com.android.server.telecom.CallRecordingTonePlayer.1
        @Override // android.media.AudioManager.AudioRecordingCallback
        public void onRecordingConfigChanged(List<AudioRecordingConfiguration> list) {
            synchronized (CallRecordingTonePlayer.this.mLock) {
                try {
                    Log.startSession("CRTP.oRCC");
                    CallRecordingTonePlayer.this.handleRecordingConfigurationChange(list);
                    CallRecordingTonePlayer.this.maybeStartCallAudioTone();
                    CallRecordingTonePlayer.this.maybeStopCallAudioTone();
                } finally {
                    Log.endSession();
                }
            }
        }
    };
    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    private boolean mIsRecording = false;
    private List<Call> mCalls = new ArrayList();

    private class LoopingTonePlayer extends Handler {
        private Runnable mPlayToneRunnable;
        private MediaPlayer mRecordingTonePlayer;

        LoopingTonePlayer() {
            super(Looper.getMainLooper());
            this.mPlayToneRunnable = new Runnable() { // from class: com.android.server.telecom.CallRecordingTonePlayer.LoopingTonePlayer.1
                @Override // java.lang.Runnable
                public void run() {
                    if (LoopingTonePlayer.this.mRecordingTonePlayer != null) {
                        LoopingTonePlayer.this.mRecordingTonePlayer.start();
                        LoopingTonePlayer loopingTonePlayer = LoopingTonePlayer.this;
                        loopingTonePlayer.postDelayed(this, CallRecordingTonePlayer.this.mRepeatInterval);
                    }
                }
            };
            this.mRecordingTonePlayer = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean start() {
            if (this.mRecordingTonePlayer != null) {
                Log.w(CallRecordingTonePlayer.this, "Can't start looping tone player more than once", new Object[0]);
                return false;
            }
            CallRecordingTonePlayer callRecordingTonePlayer = CallRecordingTonePlayer.this;
            AudioDeviceInfo telephonyDevice = callRecordingTonePlayer.getTelephonyDevice(callRecordingTonePlayer.mAudioManager);
            if (telephonyDevice != null) {
                MediaPlayer mediaPlayerCreate = MediaPlayer.create(CallRecordingTonePlayer.this.mContext, R.raw.record);
                this.mRecordingTonePlayer = mediaPlayerCreate;
                mediaPlayerCreate.setPreferredDevice(telephonyDevice);
                this.mRecordingTonePlayer.setVolume(0.1f);
                this.mRecordingTonePlayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(2).build());
                post(this.mPlayToneRunnable);
                return true;
            }
            Log.w(this, "startCallRecordingTone: can't find telephony audio device.", new Object[0]);
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stop() {
            this.mRecordingTonePlayer.release();
            this.mRecordingTonePlayer = null;
        }
    }

    public CallRecordingTonePlayer(Context context, AudioManager audioManager, Timeouts.Adapter adapter, TelecomSystem.SyncRoot syncRoot) {
        this.mContext = context;
        this.mAudioManager = audioManager;
        this.mLock = syncRoot;
        this.mRepeatInterval = adapter.getCallRecordingToneRepeatIntervalMillis(context.getContentResolver());
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallAdded(Call call) {
        if (shouldUseRecordingTone(call)) {
            addCall(call);
        }
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallRemoved(Call call) {
        if (shouldUseRecordingTone(call)) {
            removeCall(call);
        }
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallStateChanged(Call call, int i, int i2) {
        if (shouldUseRecordingTone(call) && this.mIsRecording) {
            maybeStartCallAudioTone();
            maybeStopCallAudioTone();
        }
    }

    private void addCall(Call call) {
        boolean zIsEmpty = this.mCalls.isEmpty();
        this.mCalls.add(call);
        if (zIsEmpty) {
            handleRecordingConfigurationChange(this.mAudioManager.getActiveRecordingConfigurations());
            this.mAudioManager.registerAudioRecordingCallback(this.mAudioRecordingCallback, this.mMainThreadHandler);
        }
        maybeStartCallAudioTone();
    }

    private void removeCall(Call call) {
        this.mCalls.remove(call);
        if (this.mCalls.isEmpty()) {
            this.mAudioManager.unregisterAudioRecordingCallback(this.mAudioRecordingCallback);
            maybeStopCallAudioTone();
        }
    }

    private boolean shouldUseRecordingTone(Call call) {
        return call.getParentCall() == null && !call.isExternalCall() && !call.isEmergencyCall() && call.isUsingCallRecordingTone();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeStartCallAudioTone() {
        if (this.mIsRecording && hasActiveCall()) {
            startCallRecordingTone();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeStopCallAudioTone() {
        if (this.mIsRecording && hasActiveCall()) {
            return;
        }
        stopCallRecordingTone();
    }

    private boolean hasActiveCall() {
        return !this.mCalls.isEmpty() && this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallRecordingTonePlayer$0gVHLG74eRh_xED9sOBY7U26Gvs
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Call) obj).isActive();
            }
        }).count() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRecordingConfigurationChange(List<AudioRecordingConfiguration> list) {
        if (list == null) {
            list = Collections.emptyList();
        }
        boolean z = this.mIsRecording;
        boolean zIsRecordingInProgress = isRecordingInProgress(list);
        if (z != zIsRecordingInProgress) {
            this.mIsRecording = zIsRecordingInProgress;
            if (zIsRecordingInProgress) {
                Log.i(this, "handleRecordingConfigurationChange: recording started", new Object[0]);
            } else {
                Log.i(this, "handleRecordingConfigurationChange: recording stopped", new Object[0]);
            }
        }
    }

    private boolean isRecordingInProgress(List<AudioRecordingConfiguration> list) {
        Log.i(this, "isRecordingInProgress: recordingPackages=%s", new Object[]{(String) list.stream().map(new Function() { // from class: com.android.server.telecom.-$$Lambda$CallRecordingTonePlayer$00mTskiLMgDPbddIqazcqwQkEvo
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((AudioRecordingConfiguration) obj).getClientPackageName();
            }
        }).collect(Collectors.joining(", "))});
        return list.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallRecordingTonePlayer$yu_3oLWpy1cvmsDachRx1zeFTAI
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$isRecordingInProgress$2$CallRecordingTonePlayer((AudioRecordingConfiguration) obj);
            }
        }).count() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$isRecordingInProgress$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$isRecordingInProgress$2$CallRecordingTonePlayer(AudioRecordingConfiguration audioRecordingConfiguration) {
        return !hasCallForPackage(audioRecordingConfiguration.getClientPackageName());
    }

    private void startCallRecordingTone() {
        if (this.mLoopingTonePlayer != null) {
            Log.w(this, "Tone is already playing", new Object[0]);
            return;
        }
        LoopingTonePlayer loopingTonePlayer = new LoopingTonePlayer();
        this.mLoopingTonePlayer = loopingTonePlayer;
        if (loopingTonePlayer.start()) {
            return;
        }
        this.mLoopingTonePlayer = null;
    }

    private void stopCallRecordingTone() {
        if (this.mLoopingTonePlayer != null) {
            Log.i(this, "stopCallRecordingTone: stopping call recording tone.", new Object[0]);
            this.mLoopingTonePlayer.stop();
            this.mLoopingTonePlayer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AudioDeviceInfo getTelephonyDevice(AudioManager audioManager) {
        for (AudioDeviceInfo audioDeviceInfo : audioManager.getDevices(2)) {
            if (audioDeviceInfo.getType() == 18) {
                return audioDeviceInfo;
            }
        }
        return null;
    }

    private boolean hasCallForPackage(final String str) {
        return this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallRecordingTonePlayer$-g8WUXBnZzxDmOKLgnHLIc0rSNM
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CallRecordingTonePlayer.lambda$hasCallForPackage$3(str, (Call) obj);
            }
        }).count() >= 1;
    }

    static /* synthetic */ boolean lambda$hasCallForPackage$3(String str, Call call) {
        return (call.getTargetPhoneAccount() != null && call.getTargetPhoneAccount().getComponentName().getPackageName().equals(str)) || (call.getConnectionManagerPhoneAccount() != null && call.getConnectionManagerPhoneAccount().getComponentName().getPackageName().equals(str));
    }

    @VisibleForTesting
    public boolean hasCalls() {
        return this.mCalls.size() > 0;
    }

    @VisibleForTesting
    public boolean isRecording() {
        return this.mIsRecording;
    }
}
