package com.android.server.telecom;

import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.VolumeShaper;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.telecom.Log;
import android.telecom.Logging.EventManager;
import android.telecom.Logging.Session;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.Preconditions;
import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public class AsyncRingtonePlayer {
    private Handler mHandler;
    private CompletableFuture<Boolean> mHapticsFuture = null;
    private Ringtone mRingtone;

    public CompletableFuture<Boolean> play(RingtoneFactory ringtoneFactory, Call call, VolumeShaper.Configuration configuration, boolean z) {
        Log.d(this, "Posting play.", new Object[0]);
        if (this.mHapticsFuture == null) {
            this.mHapticsFuture = new CompletableFuture<>();
        }
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = ringtoneFactory;
        someArgsObtain.arg2 = call;
        someArgsObtain.arg3 = configuration;
        someArgsObtain.arg4 = Boolean.valueOf(z);
        someArgsObtain.arg5 = Log.createSubsession();
        postMessage(1, true, someArgsObtain);
        return this.mHapticsFuture;
    }

    public void stop() {
        Log.d(this, "Posting stop.", new Object[0]);
        postMessage(2, false, null);
    }

    private void postMessage(int i, boolean z, SomeArgs someArgs) {
        synchronized (this) {
            if (this.mHandler == null && z) {
                this.mHandler = getNewHandler();
            }
            if (this.mHandler == null) {
                Log.d(this, "Message %d skipped because there is no handler.", new Object[]{Integer.valueOf(i)});
            } else {
                this.mHandler.obtainMessage(i, someArgs).sendToTarget();
            }
        }
    }

    private Handler getNewHandler() {
        boolean z = true;
        Preconditions.checkState(this.mHandler == null);
        HandlerThread handlerThread = new HandlerThread("ringtone-player");
        handlerThread.start();
        return new Handler(handlerThread.getLooper(), null, z) { // from class: com.android.server.telecom.AsyncRingtonePlayer.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    AsyncRingtonePlayer.this.handlePlay((SomeArgs) message.obj);
                } else {
                    if (i != 2) {
                        return;
                    }
                    AsyncRingtonePlayer.this.handleStop();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePlay(SomeArgs someArgs) {
        Boolean bool = Boolean.FALSE;
        RingtoneFactory ringtoneFactory = (RingtoneFactory) someArgs.arg1;
        Call call = (Call) someArgs.arg2;
        VolumeShaper.Configuration configuration = (VolumeShaper.Configuration) someArgs.arg3;
        boolean zBooleanValue = ((Boolean) someArgs.arg4).booleanValue();
        Session session = (Session) someArgs.arg5;
        someArgs.recycle();
        Log.continueSession(session, "ARP.hP");
        try {
            if (this.mHandler.hasMessages(2)) {
                if (this.mHapticsFuture != null) {
                    this.mHapticsFuture.complete(bool);
                    this.mHapticsFuture = null;
                }
                return;
            }
            if (Uri.EMPTY.equals(call.getRingtone())) {
                this.mRingtone = null;
                if (this.mHapticsFuture != null) {
                    this.mHapticsFuture.complete(bool);
                    this.mHapticsFuture = null;
                }
                return;
            }
            ThreadUtil.checkNotOnMainThread();
            Log.i(this, "handlePlay: Play ringtone.", new Object[0]);
            if (this.mRingtone == null) {
                Ringtone ringtone = ringtoneFactory.getRingtone(call, configuration);
                this.mRingtone = ringtone;
                if (ringtone == null) {
                    Uri ringtone2 = call.getRingtone();
                    Log.addEvent((EventManager.Loggable) null, "ERROR", "Failed to get ringtone from factory. Skipping ringing. Uri was: " + (ringtone2 == null ? "null" : ringtone2.toSafeString()));
                    if (this.mHapticsFuture != null) {
                        this.mHapticsFuture.complete(bool);
                        this.mHapticsFuture = null;
                    }
                    return;
                }
                if (this.mHapticsFuture != null && !this.mHapticsFuture.isDone()) {
                    boolean zHasHapticChannels = ringtoneFactory.hasHapticChannels(this.mRingtone);
                    Log.i(this, "handlePlay: hasHaptics=%b, isVibrationEnabled=%b", new Object[]{Boolean.valueOf(zHasHapticChannels), Boolean.valueOf(zBooleanValue)});
                    SystemSettingsUtil systemSettingsUtil = new SystemSettingsUtil();
                    if (zHasHapticChannels && (configuration == null || systemSettingsUtil.enableAudioCoupledVibrationForRampingRinger())) {
                        AudioAttributes audioAttributes = this.mRingtone.getAudioAttributes();
                        Object[] objArr = new Object[1];
                        objArr[0] = zBooleanValue ? "unmuting" : "muting";
                        Log.d(this, "handlePlay: %s haptic channel", objArr);
                        this.mRingtone.setAudioAttributes(new AudioAttributes.Builder(audioAttributes).setHapticChannelsMuted(!zBooleanValue).build());
                    }
                    this.mHapticsFuture.complete(Boolean.valueOf(zHasHapticChannels));
                    this.mHapticsFuture = null;
                }
            }
            this.mRingtone.setLooping(true);
            if (this.mRingtone.isPlaying()) {
                Log.d(this, "Ringtone already playing.", new Object[0]);
            } else {
                this.mRingtone.play();
                Log.i(this, "Play ringtone, looping.", new Object[0]);
            }
        } finally {
            Log.cancelSubsession(session);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStop() {
        ThreadUtil.checkNotOnMainThread();
        Log.i(this, "Stop ringtone.", new Object[0]);
        if (this.mRingtone != null) {
            Log.d(this, "Ringtone.stop() invoked.", new Object[0]);
            this.mRingtone.stop();
            this.mRingtone = null;
        }
        synchronized (this) {
            if (this.mHandler.hasMessages(1)) {
                Log.v(this, "Keeping alive ringtone thread for subsequent play request.", new Object[0]);
            } else {
                this.mHandler.removeMessages(2);
                this.mHandler.getLooper().quitSafely();
                this.mHandler = null;
                Log.v(this, "Handler cleared.", new Object[0]);
            }
        }
    }

    public boolean isPlaying() {
        return this.mRingtone != null;
    }
}
