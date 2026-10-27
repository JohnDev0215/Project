package com.android.server.telecom;

import android.app.NotificationManager;
import android.app.Person;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.VolumeShaper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.telecom.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.server.telecom.InCallTonePlayer;
import com.android.server.telecom.LogUtils;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public class Ringer {
    private static final int[] PULSE_AMPLITUDE;
    private static final long[] PULSE_PATTERN;
    private static final long[] PULSE_PRIMING_PATTERN;
    private static final long[] PULSE_RAMPING_PATTERN;
    private static final int[] SIMPLE_VIBRATION_AMPLITUDE;
    private static final long[] SIMPLE_VIBRATION_PATTERN;
    private static final AudioAttributes VIBRATION_ATTRIBUTES;
    private static VolumeShaper.Configuration mVolumeShaperConfig;
    private Call mCallWaitingCall;
    private InCallTonePlayer mCallWaitingPlayer;
    private final Context mContext;

    @VisibleForTesting
    public VibrationEffect mDefaultVibrationEffect;
    private final InCallController mInCallController;
    private final boolean mIsHapticPlaybackSupportedByDevice;
    private final InCallTonePlayer.Factory mPlayerFactory;
    private Call mRingingCall;
    private RingtoneFactory mRingtoneFactory;
    private final AsyncRingtonePlayer mRingtonePlayer;
    private final SystemSettingsUtil mSystemSettingsUtil;
    private Call mVibratingCall;
    private final VibrationEffectProxy mVibrationEffectProxy;
    private final Vibrator mVibrator;
    private static final int[] PULSE_PRIMING_AMPLITUDE = {0, 255, 0, 255, 0};
    private static final int[] PULSE_RAMPING_AMPLITUDE = {77, 77, 78, 79, 81, 84, 87, 93, 101, 114, 133, 162, 205, 255, 255, 0};
    private CompletableFuture<Void> mBlockOnRingingFuture = null;
    private CompletableFuture<Void> mVibrateFuture = CompletableFuture.completedFuture(null);
    private boolean mIsVibrating = false;

    public static class VibrationEffectProxy {
        public VibrationEffect createWaveform(long[] jArr, int[] iArr, int i) {
            return VibrationEffect.createWaveform(jArr, iArr, i);
        }

        public VibrationEffect get(Uri uri, Context context) {
            return VibrationEffect.get(uri, context);
        }
    }

    static {
        long[] jArr = {0, 12, 250, 12, 500};
        PULSE_PRIMING_PATTERN = jArr;
        long[] jArr2 = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 300, 1000};
        PULSE_RAMPING_PATTERN = jArr2;
        long[] jArr3 = new long[jArr.length + jArr2.length];
        PULSE_PATTERN = jArr3;
        System.arraycopy(jArr, 0, jArr3, 0, jArr.length);
        long[] jArr4 = PULSE_RAMPING_PATTERN;
        System.arraycopy(jArr4, 0, PULSE_PATTERN, PULSE_PRIMING_PATTERN.length, jArr4.length);
        int[] iArr = PULSE_PRIMING_AMPLITUDE;
        int[] iArr2 = new int[iArr.length + PULSE_RAMPING_AMPLITUDE.length];
        PULSE_AMPLITUDE = iArr2;
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        int[] iArr3 = PULSE_RAMPING_AMPLITUDE;
        System.arraycopy(iArr3, 0, PULSE_AMPLITUDE, PULSE_PRIMING_AMPLITUDE.length, iArr3.length);
        SIMPLE_VIBRATION_PATTERN = new long[]{0, 1000, 1000};
        SIMPLE_VIBRATION_AMPLITUDE = new int[]{0, 255, 0};
        VIBRATION_ATTRIBUTES = new AudioAttributes.Builder().setContentType(4).setUsage(6).build();
    }

    @VisibleForTesting
    public Ringer(InCallTonePlayer.Factory factory, Context context, SystemSettingsUtil systemSettingsUtil, AsyncRingtonePlayer asyncRingtonePlayer, RingtoneFactory ringtoneFactory, Vibrator vibrator, VibrationEffectProxy vibrationEffectProxy, InCallController inCallController) {
        this.mSystemSettingsUtil = systemSettingsUtil;
        this.mPlayerFactory = factory;
        this.mContext = context;
        this.mVibrator = vibrator;
        this.mRingtonePlayer = asyncRingtonePlayer;
        this.mRingtoneFactory = ringtoneFactory;
        this.mInCallController = inCallController;
        this.mVibrationEffectProxy = vibrationEffectProxy;
        if (context.getResources().getBoolean(R.bool.use_simple_vibration_pattern)) {
            this.mDefaultVibrationEffect = this.mVibrationEffectProxy.createWaveform(SIMPLE_VIBRATION_PATTERN, SIMPLE_VIBRATION_AMPLITUDE, 1);
        } else {
            this.mDefaultVibrationEffect = this.mVibrationEffectProxy.createWaveform(PULSE_PATTERN, PULSE_AMPLITUDE, 5);
        }
        this.mIsHapticPlaybackSupportedByDevice = this.mSystemSettingsUtil.isHapticPlaybackSupported(this.mContext);
    }

    @VisibleForTesting
    public void setBlockOnRingingFuture(CompletableFuture<Void> completableFuture) {
        this.mBlockOnRingingFuture = completableFuture;
    }

    public boolean startRinging(final Call call, boolean z) {
        final VibrationEffect vibrationEffect;
        CompletableFuture<Boolean> completableFuture;
        CompletableFuture<Boolean> completableFuturePlay;
        VibrationEffect vibrationEffectForCall;
        if (call == null) {
            Log.wtf(this, "startRinging called with null foreground call.", new Object[0]);
            return false;
        }
        if (call.getState() != 4 && call.getState() != 13) {
            Log.i(this, "startRinging called for non-ringing foreground callid=%s", new Object[]{call.getId()});
            return false;
        }
        AudioManager audioManager = (AudioManager) this.mContext.getSystemService("audio");
        LogUtils.EventTimer eventTimer = new LogUtils.EventTimer();
        boolean z2 = audioManager.getStreamVolume(2) > 0;
        eventTimer.record("isVolumeOverZero");
        final boolean zShouldRingForContact = shouldRingForContact(call.getContactUri());
        eventTimer.record("shouldRingForContact");
        boolean z3 = this.mRingtoneFactory.getRingtone(call) != null;
        eventTimer.record("getRingtone");
        boolean zIsSelfManaged = call.isSelfManaged();
        eventTimer.record("isSelfManaged");
        boolean zIsSilentRingingRequested = call.isSilentRingingRequested();
        eventTimer.record("isSilentRingRequested");
        boolean z4 = z2 && zShouldRingForContact && z3;
        eventTimer.record("isRingerAudible");
        boolean zHasExternalRinger = hasExternalRinger(call);
        eventTimer.record("hasExternalRinger");
        boolean zIsTheaterModeOn = this.mSystemSettingsUtil.isTheaterModeOn(this.mContext);
        eventTimer.record("isTheaterModeOn");
        boolean zDoesConnectedDialerSupportRinging = this.mInCallController.doesConnectedDialerSupportRinging();
        eventTimer.record("letDialerHandleRinging");
        Log.i(this, "startRinging timings: " + eventTimer, new Object[0]);
        boolean z5 = zIsTheaterModeOn || zDoesConnectedDialerSupportRinging || zIsSelfManaged || zHasExternalRinger || zIsSilentRingingRequested;
        boolean z6 = z4 || (z && zShouldRingForContact) || zIsSelfManaged;
        if (z5) {
            if (zDoesConnectedDialerSupportRinging) {
                Log.addEvent(call, "SKIP_RINGING", "Dialer handles");
            }
            if (zIsSilentRingingRequested) {
                Log.addEvent(call, "SKIP_RINGING", "Silent ringing requested");
            }
            Log.i(this, "Ending early -- isTheaterModeOn=%s, letDialerHandleRinging=%s, isSelfManaged=%s, hasExternalRinger=%s, silentRingingRequested=%s", new Object[]{Boolean.valueOf(zIsTheaterModeOn), Boolean.valueOf(zDoesConnectedDialerSupportRinging), Boolean.valueOf(zIsSelfManaged), Boolean.valueOf(zHasExternalRinger), Boolean.valueOf(zIsSilentRingingRequested)});
            CompletableFuture<Void> completableFuture2 = this.mBlockOnRingingFuture;
            if (completableFuture2 != null) {
                completableFuture2.complete(null);
            }
            return z6;
        }
        stopCallWaiting();
        final boolean zIsVibratorEnabled = isVibratorEnabled(this.mContext, call);
        if (z4) {
            this.mRingingCall = call;
            Log.addEvent(call, "START_RINGER");
            if (this.mSystemSettingsUtil.applyRampingRinger(this.mContext)) {
                Log.i(this, "start ramping ringer.", new Object[0]);
                if (this.mSystemSettingsUtil.enableAudioCoupledVibrationForRampingRinger()) {
                    vibrationEffectForCall = getVibrationEffectForCall(this.mRingtoneFactory, call);
                } else {
                    vibrationEffectForCall = this.mDefaultVibrationEffect;
                }
                if (mVolumeShaperConfig == null) {
                    mVolumeShaperConfig = new VolumeShaper.Configuration.Builder().setDuration(15000L).setCurve(new float[]{0.0f, 0.33333436f, 1.0f}, new float[]{0.0f, 0.0f, 1.0f}).setInterpolatorType(1).build();
                }
                completableFuturePlay = this.mRingtonePlayer.play(this.mRingtoneFactory, call, mVolumeShaperConfig, zIsVibratorEnabled);
            } else {
                completableFuturePlay = this.mRingtonePlayer.play(this.mRingtoneFactory, call, null, zIsVibratorEnabled);
                vibrationEffectForCall = getVibrationEffectForCall(this.mRingtoneFactory, call);
            }
            vibrationEffect = vibrationEffectForCall;
            completableFuture = completableFuturePlay;
        } else {
            String str = String.format("isVolumeOverZero=%s, shouldRingForContact=%s, isRingtonePresent=%s", Boolean.valueOf(z2), Boolean.valueOf(zShouldRingForContact), Boolean.valueOf(z3));
            Log.i(this, "startRinging: skipping because ringer would not be audible. " + str, new Object[0]);
            Log.addEvent(call, "SKIP_RINGING", "Inaudible: " + str);
            vibrationEffect = this.mDefaultVibrationEffect;
            completableFuture = null;
        }
        if (completableFuture != null) {
            final boolean z7 = z4;
            CompletableFuture<Void> completableFutureThenAccept = completableFuture.thenAccept(new Consumer() { // from class: com.android.server.telecom.-$$Lambda$Ringer$-fpgpxbSuovt8fdgiQ7vlMH6Zxc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$startRinging$0$Ringer(call, zShouldRingForContact, vibrationEffect, zIsVibratorEnabled, z7, (Boolean) obj);
                }
            });
            this.mVibrateFuture = completableFutureThenAccept;
            if (this.mBlockOnRingingFuture != null) {
                completableFutureThenAccept.whenComplete(new BiConsumer() { // from class: com.android.server.telecom.-$$Lambda$Ringer$-azfe6zLZOmSAo3QQJWLjoA4dKI
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        this.f$0.lambda$startRinging$1$Ringer((Void) obj, (Throwable) obj2);
                    }
                });
            }
        } else {
            CompletableFuture<Void> completableFuture3 = this.mBlockOnRingingFuture;
            if (completableFuture3 != null) {
                completableFuture3.complete(null);
            }
            Log.w(this, "startRinging: No haptics future; fallback to default behavior", new Object[0]);
            maybeStartVibration(call, zShouldRingForContact, vibrationEffect, zIsVibratorEnabled, z4);
        }
        return z6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$startRinging$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$startRinging$0$Ringer(Call call, boolean z, VibrationEffect vibrationEffect, boolean z2, boolean z3, Boolean bool) {
        if (!bool.booleanValue() || !this.mIsHapticPlaybackSupportedByDevice) {
            Log.i(this, "startRinging: fileHasHaptics=%b, hapticsSupported=%b", new Object[]{bool, Boolean.valueOf(this.mIsHapticPlaybackSupportedByDevice)});
            maybeStartVibration(call, z, vibrationEffect, z2, z3);
        } else if (this.mSystemSettingsUtil.applyRampingRinger(this.mContext) && !this.mSystemSettingsUtil.enableAudioCoupledVibrationForRampingRinger()) {
            Log.i(this, "startRinging: apply ramping ringer vibration", new Object[0]);
            maybeStartVibration(call, z, vibrationEffect, z2, z3);
        } else {
            Log.addEvent(call, "SKIP_VIBRATION", "using audio-coupled haptics");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$startRinging$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$startRinging$1$Ringer(Void r1, Throwable th) {
        this.mBlockOnRingingFuture.complete(null);
    }

    private void maybeStartVibration(Call call, boolean z, VibrationEffect vibrationEffect, boolean z2, boolean z3) {
        if (z2 && !this.mIsVibrating && z) {
            if (this.mSystemSettingsUtil.applyRampingRinger(this.mContext) && z3) {
                Log.i(this, "start vibration for ramping ringer.", new Object[0]);
                this.mIsVibrating = true;
                this.mVibrator.vibrate(vibrationEffect, VIBRATION_ATTRIBUTES);
                return;
            } else {
                Log.i(this, "start normal vibration.", new Object[0]);
                this.mIsVibrating = true;
                this.mVibrator.vibrate(vibrationEffect, VIBRATION_ATTRIBUTES);
                return;
            }
        }
        if (this.mIsVibrating) {
            Log.addEvent(call, "SKIP_VIBRATION", "already vibrating");
        }
    }

    private VibrationEffect getVibrationEffectForCall(RingtoneFactory ringtoneFactory, Call call) {
        Ringtone ringtone = ringtoneFactory.getRingtone(call);
        VibrationEffect vibrationEffect = null;
        Uri uri = ringtone != null ? ringtone.getUri() : null;
        if (uri != null) {
            try {
                vibrationEffect = this.mVibrationEffectProxy.get(uri, this.mContext);
            } catch (IllegalArgumentException e) {
                Log.e(this, e, "getVibrationEffectForCall: failed to get vibration effect", new Object[0]);
            }
        }
        return vibrationEffect == null ? this.mDefaultVibrationEffect : vibrationEffect;
    }

    public void startCallWaiting(Call call, String str) {
        if (this.mSystemSettingsUtil.isTheaterModeOn(this.mContext)) {
            return;
        }
        if (this.mInCallController.doesConnectedDialerSupportRinging()) {
            Log.addEvent(call, "SKIP_RINGING", "Dialer handles");
            return;
        }
        if (call.isSelfManaged()) {
            Log.addEvent(call, "SKIP_RINGING", "Self-managed");
            return;
        }
        Log.v(this, "Playing call-waiting tone.", new Object[0]);
        stopRinging();
        if (this.mCallWaitingPlayer == null) {
            Log.addEvent(call, "START_CALL_WAITING_TONE", str);
            this.mCallWaitingCall = call;
            InCallTonePlayer inCallTonePlayerCreatePlayer = this.mPlayerFactory.createPlayer(4);
            this.mCallWaitingPlayer = inCallTonePlayerCreatePlayer;
            inCallTonePlayerCreatePlayer.startTone();
        }
    }

    public void stopRinging() {
        Call call = this.mRingingCall;
        if (call != null) {
            Log.addEvent(call, "STOP_RINGER");
            this.mRingingCall = null;
        }
        this.mRingtonePlayer.stop();
        CompletableFuture<Void> completableFuture = this.mVibrateFuture;
        if (completableFuture != null) {
            completableFuture.cancel(true);
        }
        if (this.mIsVibrating) {
            Log.addEvent(this.mVibratingCall, "STOP_VIBRATOR");
            this.mVibrator.cancel();
            this.mIsVibrating = false;
            this.mVibratingCall = null;
        }
    }

    public void stopCallWaiting() {
        Log.v(this, "stop call waiting.", new Object[0]);
        if (this.mCallWaitingPlayer != null) {
            Call call = this.mCallWaitingCall;
            if (call != null) {
                Log.addEvent(call, "STOP_CALL_WAITING_TONE");
                this.mCallWaitingCall = null;
            }
            this.mCallWaitingPlayer.stopTone();
            this.mCallWaitingPlayer = null;
        }
    }

    public boolean isRinging() {
        return this.mRingtonePlayer.isPlaying();
    }

    private boolean shouldRingForContact(Uri uri) {
        NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        Bundle bundle = new Bundle();
        if (uri != null) {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            arrayList.add(new Person.Builder().setUri(uri.toString()).build());
            bundle.putParcelableArrayList("android.people.list", arrayList);
        }
        return notificationManager.matchesCallFilter(bundle);
    }

    private boolean hasExternalRinger(Call call) {
        Bundle intentExtras = call.getIntentExtras();
        if (intentExtras != null) {
            return intentExtras.getBoolean("android.telecom.extra.CALL_EXTERNAL_RINGER", false);
        }
        return false;
    }

    private boolean isVibratorEnabled(Context context, Call call) {
        int ringerModeInternal = ((AudioManager) context.getSystemService("audio")).getRingerModeInternal();
        boolean z = !getVibrateWhenRinging(context) ? ringerModeInternal != 1 : ringerModeInternal == 0;
        if (z) {
            Log.addEvent(call, "START_VIBRATOR", "hasVibrator=%b, userRequestsVibrate=%b, ringerMode=%d, isVibrating=%b", new Object[]{Boolean.valueOf(this.mVibrator.hasVibrator()), Boolean.valueOf(this.mSystemSettingsUtil.canVibrateWhenRinging(context)), Integer.valueOf(ringerModeInternal), Boolean.valueOf(this.mIsVibrating)});
        } else {
            Log.addEvent(call, "SKIP_VIBRATION", "hasVibrator=%b, userRequestsVibrate=%b, ringerMode=%d, isVibrating=%b", new Object[]{Boolean.valueOf(this.mVibrator.hasVibrator()), Boolean.valueOf(this.mSystemSettingsUtil.canVibrateWhenRinging(context)), Integer.valueOf(ringerModeInternal), Boolean.valueOf(this.mIsVibrating)});
        }
        return z;
    }

    private boolean getVibrateWhenRinging(Context context) {
        if (this.mVibrator.hasVibrator()) {
            return this.mSystemSettingsUtil.canVibrateWhenRinging(context) || this.mSystemSettingsUtil.applyRampingRinger(context);
        }
        return false;
    }
}
