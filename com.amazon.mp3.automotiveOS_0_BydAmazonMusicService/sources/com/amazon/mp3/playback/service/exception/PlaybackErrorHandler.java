package com.amazon.mp3.playback.service.exception;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.activity.PlaybackErrorDialogActivity;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlaybackException;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackErrorHandler {
    public static final String DEFAULT_ERROR_TOAST = AmazonApplication.getContext().getString(R.string.dmusic_playback_error_streaming_network_toast);
    private static final String LOGTAG = "PlaybackErrorHandler";
    private PlaybackException.PlaybackError mLastPlaybackError = null;

    public static void handlePlaybackError(Context context, ControlSource controlSource, Exception e) {
        PlaybackException.PlaybackError playbackError;
        if (e instanceof PlaybackException) {
            PlaybackException playbackException = (PlaybackException) e;
            if (handleCloudQueueException(context, controlSource, playbackException)) {
                return;
            } else {
                playbackError = playbackException.getPlaybackError();
            }
        } else {
            playbackError = null;
        }
        if (playbackError == null) {
            playbackError = PlaybackException.PlaybackError.GenericStreaming;
        }
        PlaybackControllerProvider.getController(controlSource).pause();
        if (playbackError.shouldNotify()) {
            notifyPlaybackError(context, e, playbackError);
        }
    }

    private static boolean handleCloudQueueException(final Context context, final ControlSource controlSource, final PlaybackException exception) {
        if (exception.getPlaybackError() == null) {
            return false;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[exception.getPlaybackError().ordinal()];
        if (i == 1) {
            NowPlayingUtil.clearAndFinishNowPlaying(context, true, PlayStateMutationReason.CLOUD_QUEUE_TERMINATED);
        } else if (i == 2) {
            Log.error(LOGTAG, "Throttling Limit Reached");
            PlaybackControllerProvider.getController(controlSource).pause();
        } else if (i != 3 && i != 4 && i != 5) {
            return false;
        }
        return true;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.service.exception.PlaybackErrorHandler$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError;

        static {
            int[] iArr = new int[PlaybackException.PlaybackError.values().length];
            $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError = iArr;
            try {
                iArr[PlaybackException.PlaybackError.QueueTerminated.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.ThrottlingLimitReached.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.ReachEndOfQueue.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.ConcurrentStreamLimit.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.DeviceOffline.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.UnknownFailure.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.InvalidStationSeed.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void notifyPlaybackError(Context c, Exception e, PlaybackException.PlaybackError error) {
        String str = LOGTAG;
        Log.info(str, "Notifying of playback error: %s", error.toString());
        if (error == PlaybackException.PlaybackError.NetworkFailure) {
            Log.error(str, "GENERAL CIRRUS ERROR: %s - %s", e.getClass().toString(), e.getMessage());
        }
        Context applicationContext = c.getApplicationContext();
        PlaybackErrorInformation playbackErrorInformationFromPlaybackError = PlaybackErrorInformation.fromPlaybackError(applicationContext, error);
        Log.error(str, playbackErrorInformationFromPlaybackError.toString());
        Intent intent = new Intent("com.amazon.mp3.playbackerror");
        intent.putExtra("com.amazon.mp3.playbackerror_type", error);
        intent.putExtra("com.amazon.mp3.playbackerror_message", playbackErrorInformationFromPlaybackError.getToastMessage());
        if (e instanceof PrimeStreamingConcurrencyException) {
            intent.putExtra("com.amazon.mp3.playbackerror_parcelable", (Parcelable) e);
        }
        applicationContext.sendBroadcast(intent);
    }

    public static Intent getNotificationIntent(Context c, PlaybackException.PlaybackError error) {
        Context applicationContext = c.getApplicationContext();
        PlaybackErrorInformation playbackErrorInformationFromPlaybackError = PlaybackErrorInformation.fromPlaybackError(applicationContext, error);
        Log.error(LOGTAG, playbackErrorInformationFromPlaybackError.toString());
        String dialogHeader = playbackErrorInformationFromPlaybackError.getDialogHeader();
        String dialogBody = playbackErrorInformationFromPlaybackError.getDialogBody();
        String positiveButtonText = playbackErrorInformationFromPlaybackError.getPositiveButtonText();
        String negativeButtonText = playbackErrorInformationFromPlaybackError.getNegativeButtonText();
        int buttonRunnableId = playbackErrorInformationFromPlaybackError.getButtonRunnableId();
        String buttonUriAction = playbackErrorInformationFromPlaybackError.getButtonUriAction();
        int cancelRunnableId = playbackErrorInformationFromPlaybackError.getCancelRunnableId();
        Object exception = error.getException();
        return PlaybackErrorDialogActivity.getStartIntent(applicationContext, (exception == null || !(exception instanceof Parcelable)) ? null : (Parcelable) exception, dialogHeader, dialogBody, positiveButtonText, buttonRunnableId, buttonUriAction, negativeButtonText, cancelRunnableId);
    }
}
