package com.amazon.mp3.playback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.mp3.playback.service.exception.PlaybackErrorHandler;
import com.amazon.mp3.playback.service.exception.PrimeStreamingConcurrencyException;
import com.amazon.music.media.playback.PlaybackException;
import java.lang.ref.WeakReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackErrorReceiver extends BroadcastReceiver {
    private WeakReference<PlaybackErrorReceiverListener> listenerReference;
    private Logger mLog = LoggerFactory.getLogger(PlaybackErrorReceiver.class.getSimpleName());

    public interface PlaybackErrorReceiverListener {
        void startActivityForIntent(final Intent intent);

        void startNoNetworkDialog();
    }

    public PlaybackErrorReceiver(@NonNull final PlaybackErrorReceiverListener listener) {
        this.listenerReference = new WeakReference<>(listener);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0068  */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onReceive(android.content.Context r5, android.content.Intent r6) {
        /*
            r4 = this;
            if (r6 == 0) goto L73
            java.lang.String r0 = r6.getAction()
            java.lang.String r1 = "com.amazon.mp3.playbackerror"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L73
            java.lang.String r0 = "com.amazon.mp3.playbackerror_type"
            java.io.Serializable r0 = r6.getSerializableExtra(r0)
            com.amazon.music.media.playback.PlaybackException$PlaybackError r0 = (com.amazon.music.media.playback.PlaybackException.PlaybackError) r0
            java.lang.ref.WeakReference<com.amazon.mp3.playback.PlaybackErrorReceiver$PlaybackErrorReceiverListener> r1 = r4.listenerReference
            java.lang.Object r1 = r1.get()
            com.amazon.mp3.playback.PlaybackErrorReceiver$PlaybackErrorReceiverListener r1 = (com.amazon.mp3.playback.PlaybackErrorReceiver.PlaybackErrorReceiverListener) r1
            int[] r2 = com.amazon.mp3.playback.PlaybackErrorReceiver.AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError
            int r3 = r0.ordinal()
            r2 = r2[r3]
            java.lang.String r3 = "Broadcast received, but no listener to handle callback"
            switch(r2) {
                case 1: goto L55;
                case 2: goto L55;
                case 3: goto L50;
                case 4: goto L4b;
                case 5: goto L4b;
                case 6: goto L4b;
                case 7: goto L4b;
                case 8: goto L4b;
                case 9: goto L4b;
                case 10: goto L4b;
                case 11: goto L4b;
                case 12: goto L4b;
                case 13: goto L4b;
                case 14: goto L4b;
                case 15: goto L4b;
                case 16: goto L4b;
                case 17: goto L4b;
                case 18: goto L4b;
                case 19: goto L4b;
                case 20: goto L4b;
                case 21: goto L4b;
                case 22: goto L4b;
                case 23: goto L4b;
                case 24: goto L4b;
                case 25: goto L4b;
                case 26: goto L4b;
                case 27: goto L4b;
                case 28: goto L4b;
                case 29: goto L4b;
                case 30: goto L4b;
                case 31: goto L4b;
                case 32: goto L4b;
                case 33: goto L4b;
                case 34: goto L4b;
                case 35: goto L4b;
                case 36: goto L4b;
                case 37: goto L3a;
                default: goto L2b;
            }
        L2b:
            java.lang.String r0 = "com.amazon.mp3.playbackerror_message"
            java.lang.String r6 = r6.getStringExtra(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L61
            java.lang.String r6 = com.amazon.mp3.playback.service.exception.PlaybackErrorHandler.DEFAULT_ERROR_TOAST
            goto L61
        L3a:
            boolean r6 = com.amazon.mp3.util.ConnectivityUtil.hasAnyInternetConnection(r5)
            if (r6 != 0) goto L46
            if (r1 == 0) goto L46
            r1.startNoNetworkDialog()
            goto L65
        L46:
            android.content.Intent r5 = com.amazon.mp3.playback.service.exception.PlaybackErrorHandler.getNotificationIntent(r5, r0)
            goto L66
        L4b:
            android.content.Intent r5 = com.amazon.mp3.playback.service.exception.PlaybackErrorHandler.getNotificationIntent(r5, r0)
            goto L66
        L50:
            android.content.Intent r5 = r4.getConcurrentStreamingIntent(r5, r6, r0)
            goto L66
        L55:
            if (r1 == 0) goto L5b
            r1.startNoNetworkDialog()
            goto L60
        L5b:
            org.slf4j.Logger r5 = r4.mLog
            r5.warn(r3)
        L60:
            return
        L61:
            r0 = 0
            com.amazon.mp3.util.BauhausToastUtils.showDefaultToast(r5, r6, r0)
        L65:
            r5 = 0
        L66:
            if (r5 == 0) goto L73
            if (r1 == 0) goto L6e
            r1.startActivityForIntent(r5)
            goto L73
        L6e:
            org.slf4j.Logger r5 = r4.mLog
            r5.warn(r3)
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.PlaybackErrorReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.PlaybackErrorReceiver$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError;

        static {
            int[] iArr = new int[PlaybackException.PlaybackError.values().length];
            $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError = iArr;
            try {
                iArr[PlaybackException.PlaybackError.NetworkFailure.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.BufferUnderrun.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.PrimeStreamingConcurrency.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StreamingRestriction.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StreamingVideoRestriction.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.Cirrus.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.ConnectionLimit.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.ContentNotEligible.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.CustomerNotEligible.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.DMLS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.FileNotFound.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.InsufficientStorage.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.InvalidFile.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.InvalidParameter.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.GenericDrm.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.GenericHLSPlayer.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.GenericProgressivePlayer.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.GenericLocalPlayer.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.GenericChromecastPlayer.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.ChromecastPlayerHttp.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.Payment.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.RegistrationError.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.SignedOut.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StreamingPlayback.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StreamingPlaybackLocal.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StreamingPlaybackRemote.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.TOU.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StreamingServiceError.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.LocalFileNotFound.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.DrmFileNotFound.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.DeviceRooted.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.SecondaryStorageNotFound.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.ThrottlingLimitReached.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.UnknownFailure.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.DeviceOffline.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.InvalidStationSeed.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.GenericStreaming.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
        }
    }

    private Intent getConcurrentStreamingIntent(final Context context, final Intent playbackErrorIntent, final PlaybackException.PlaybackError playbackError) {
        return createConcurrencyDialogIntent(context, (PrimeStreamingConcurrencyException) playbackErrorIntent.getParcelableExtra("com.amazon.mp3.playbackerror_parcelable"));
    }

    public static Intent createConcurrencyDialogIntent(final Context context, final PrimeStreamingConcurrencyException extraException) {
        Intent notificationIntent = PlaybackErrorHandler.getNotificationIntent(context, PlaybackException.PlaybackError.PrimeStreamingConcurrency);
        notificationIntent.putExtra("com.amazon.mp3.playbackerror_parcelable", (Parcelable) extraException);
        return notificationIntent;
    }
}
