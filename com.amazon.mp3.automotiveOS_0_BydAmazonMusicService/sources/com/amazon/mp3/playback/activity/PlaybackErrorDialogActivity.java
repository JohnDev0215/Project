package com.amazon.mp3.playback.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Parcelable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.mp3.account.activity.SignInFlowLauncherActivity;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.service.exception.PrimeStreamingConcurrencyException;
import com.amazon.mp3.playback.streaming.concurrency.StreamingConcurrencyCheckReceiver;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.EmailUtil;
import com.amazon.music.metrics.mts.event.types.StreamingConcurrencyResponse;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackErrorDialogActivity extends DialogActivity {
    private int mButtonRunnableId;
    private String mButtonUriAction;
    private int mCancelRunnableId;
    private String mNegativeButtonText;
    private String mPositiveButtonText;
    private PrimeStreamingConcurrencyException mPrimeConcurrencyException;
    private final DialogInterface.OnClickListener mOnPositiveButtonClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playback.activity.PlaybackErrorDialogActivity.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            PlaybackErrorDialogActivity playbackErrorDialogActivity = PlaybackErrorDialogActivity.this;
            playbackErrorDialogActivity.sendBroadcast(playbackErrorDialogActivity.mButtonRunnableId);
            PlaybackErrorDialogActivity playbackErrorDialogActivity2 = PlaybackErrorDialogActivity.this;
            playbackErrorDialogActivity2.postUserRunnable(playbackErrorDialogActivity2.mButtonRunnableId);
            dialog.cancel();
        }
    };
    private final DialogInterface.OnClickListener mOnNegativeButtonClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playback.activity.PlaybackErrorDialogActivity.2
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            PlaybackErrorDialogActivity playbackErrorDialogActivity = PlaybackErrorDialogActivity.this;
            playbackErrorDialogActivity.sendBroadcast(playbackErrorDialogActivity.mCancelRunnableId);
            PlaybackErrorDialogActivity playbackErrorDialogActivity2 = PlaybackErrorDialogActivity.this;
            playbackErrorDialogActivity2.postUserRunnable(playbackErrorDialogActivity2.mCancelRunnableId);
            dialog.cancel();
        }
    };
    private final Runnable mRunnableContactCustomerSupport = new Runnable() { // from class: com.amazon.mp3.playback.activity.PlaybackErrorDialogActivity.3
        @Override // java.lang.Runnable
        public void run() {
            PlaybackErrorDialogActivity playbackErrorDialogActivity = PlaybackErrorDialogActivity.this;
            EmailUtil.reportError(playbackErrorDialogActivity, playbackErrorDialogActivity.getString(R.string.dmusic_playback_error_email_subject));
        }
    };
    private final Runnable mRunnableResync = new Runnable() { // from class: com.amazon.mp3.playback.activity.PlaybackErrorDialogActivity.4
        @Override // java.lang.Runnable
        public void run() {
            SyncService.startSync(PlaybackErrorDialogActivity.this, 14003);
        }
    };
    private final Runnable mRunnableSignIn = new Runnable() { // from class: com.amazon.mp3.playback.activity.PlaybackErrorDialogActivity.5
        @Override // java.lang.Runnable
        public void run() {
            PlaybackErrorDialogActivity.this.startActivityForResult(new Intent(PlaybackErrorDialogActivity.this, (Class<?>) SignInFlowLauncherActivity.class), SignInFlowLauncherActivity.REQUEST_CODE);
        }
    };
    private final Runnable mRunnableSettings = new Runnable() { // from class: com.amazon.mp3.playback.activity.PlaybackErrorDialogActivity.6
        @Override // java.lang.Runnable
        public void run() {
            SettingsActivity.start(PlaybackErrorDialogActivity.this);
        }
    };
    private final Runnable mRunnableUriAction = new Runnable() { // from class: com.amazon.mp3.playback.activity.PlaybackErrorDialogActivity.7
        @Override // java.lang.Runnable
        public void run() {
            PlaybackErrorDialogActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(PlaybackErrorDialogActivity.this.mButtonUriAction)));
        }
    };
    private final Runnable mRunnableMaxConcurrency = new Runnable() { // from class: com.amazon.mp3.playback.activity.PlaybackErrorDialogActivity.8
        @Override // java.lang.Runnable
        public void run() {
            String remoteDeviceName = PlaybackErrorDialogActivity.this.mPrimeConcurrencyException.getStreamingResponseList().get(0).getRemoteDeviceName();
            PlaybackErrorDialogActivity.this.sendBroadcast(StreamingConcurrencyCheckReceiver.getBroadcastIntentForHandleCustomerDecision(1, PlaybackErrorDialogActivity.this.mPrimeConcurrencyException.getContentId(), PlaybackErrorDialogActivity.this.mPrimeConcurrencyException.getTrackDuration(), PlaybackErrorDialogActivity.this.mPrimeConcurrencyException.getStreamingResponseList().get(0).getElapsedTimeSeconds()));
            MetricsLogger.primeConcurrentMusicResponse(remoteDeviceName, StreamingConcurrencyResponse.ACCEPT);
        }
    };
    private final Runnable mRunnableMaxConcurrencyCancel = new Runnable() { // from class: com.amazon.mp3.playback.activity.PlaybackErrorDialogActivity.9
        @Override // java.lang.Runnable
        public void run() {
            String remoteDeviceName = PlaybackErrorDialogActivity.this.mPrimeConcurrencyException.getStreamingResponseList().get(0).getRemoteDeviceName();
            PlaybackErrorDialogActivity.this.sendBroadcast(StreamingConcurrencyCheckReceiver.getBroadcastIntentForHandleCustomerDecision(2, PlaybackErrorDialogActivity.this.mPrimeConcurrencyException.getContentId(), PlaybackErrorDialogActivity.this.mPrimeConcurrencyException.getTrackDuration(), PlaybackErrorDialogActivity.this.mPrimeConcurrencyException.getStreamingResponseList().get(0).getElapsedTimeSeconds()));
            MetricsLogger.primeConcurrentMusicResponse(remoteDeviceName, StreamingConcurrencyResponse.CANCEL);
        }
    };
    private final Runnable mRunnableRefreshAccount = new Runnable() { // from class: com.amazon.mp3.playback.activity.PlaybackErrorDialogActivity.10
        @Override // java.lang.Runnable
        public void run() {
            SyncService.startSync(PlaybackErrorDialogActivity.this, 48);
        }
    };
    private final Runnable mRunnableClearNowPlayingPage = new Runnable() { // from class: com.amazon.mp3.playback.activity.PlaybackErrorDialogActivity.11
        @Override // java.lang.Runnable
        public void run() {
            NowPlayingUtil.clearAndFinishNowPlaying(PlaybackErrorDialogActivity.this, true, PlayStateMutationReason.CLOUD_QUEUE_INVALID_SEED);
        }
    };

    public static Intent getStartIntent(Context c, Parcelable p, String dialogHeader, String dialogBody, String positiveButtonText, int buttonRunnableId, String buttonUriAction, String negativeButtonText, int cancelRunnableId) {
        Intent intent = new Intent(c, (Class<?>) PlaybackErrorDialogActivity.class);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_DIALOG_TITLE", dialogHeader);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_DIALOG_TEXT", dialogBody);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_POSITIVE_BUTTON_TEXT", positiveButtonText);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_BUTTON_RUNNABLE_ID", buttonRunnableId);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_BUTTON_URI_ACTION", buttonUriAction);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_NEGATIVE_BUTTON_TEXT", negativeButtonText);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_BUTTON_CANCEL_ID", cancelRunnableId);
        if (p != null) {
            intent.putExtra("com.amazon.mp3.activity.EXTRA_EXCEPTION", p);
        }
        return intent;
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setTitle(getDialogTitle());
        bauhausDialogBuilder.setMessage(getDialogText());
        String str = this.mPositiveButtonText;
        if (str != null) {
            bauhausDialogBuilder.setPositiveButton(str, this.mOnPositiveButtonClickListener);
            bauhausDialogBuilder.setCancelable(false);
        }
        String str2 = this.mNegativeButtonText;
        if (str2 != null) {
            bauhausDialogBuilder.setNegativeButton(str2, this.mOnNegativeButtonClickListener);
        } else {
            bauhausDialogBuilder.setNegativeButton(getString(R.string.dmusic_button_ok), this.mOnNegativeButtonClickListener);
        }
        return bauhausDialogBuilder.create();
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected void init() {
        super.init();
        this.mButtonRunnableId = getIntent().getIntExtra("com.amazon.mp3.activity.EXTRA_BUTTON_RUNNABLE_ID", -1);
        this.mCancelRunnableId = getIntent().getIntExtra("com.amazon.mp3.activity.EXTRA_BUTTON_CANCEL_ID", -1);
        this.mButtonUriAction = getIntent().getStringExtra("com.amazon.mp3.activity.EXTRA_BUTTON_URI_ACTION");
        this.mPositiveButtonText = getIntent().getStringExtra("com.amazon.mp3.activity.EXTRA_POSITIVE_BUTTON_TEXT");
        this.mNegativeButtonText = getIntent().getStringExtra("com.amazon.mp3.activity.EXTRA_NEGATIVE_BUTTON_TEXT");
        Parcelable parcelableExtra = getIntent().getParcelableExtra("com.amazon.mp3.activity.EXTRA_EXCEPTION");
        if (parcelableExtra instanceof PrimeStreamingConcurrencyException) {
            PrimeStreamingConcurrencyException primeStreamingConcurrencyException = (PrimeStreamingConcurrencyException) parcelableExtra;
            this.mPrimeConcurrencyException = primeStreamingConcurrencyException;
            MetricsLogger.primeConcurrentMusicDialog(primeStreamingConcurrencyException.getStreamingResponseList().get(0).getRemoteDeviceName());
            return;
        }
        this.mPrimeConcurrencyException = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void postUserRunnable(int runnableId) {
        if (runnableId <= 0) {
            return;
        }
        Handler handler = new Handler();
        switch (runnableId) {
            case 1:
                handler.post(this.mRunnableResync);
                return;
            case 2:
                handler.post(this.mRunnableSignIn);
                return;
            case 3:
                handler.post(this.mRunnableContactCustomerSupport);
                return;
            case 4:
                handler.post(this.mRunnableSettings);
                return;
            case 5:
                handler.post(this.mRunnableUriAction);
                return;
            case 6:
                handler.post(this.mRunnableMaxConcurrency);
                return;
            case 7:
                handler.post(this.mRunnableMaxConcurrencyCancel);
                return;
            case 8:
                handler.post(this.mRunnableRefreshAccount);
                return;
            case 9:
                handler.post(this.mRunnableClearNowPlayingPage);
                return;
            case 10:
                return;
            default:
                throw new IllegalArgumentException("Invalid runnable ID specified: " + this.mButtonRunnableId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBroadcast(int runnableId) {
        Intent intent = new Intent("com.amazon.mp3.library.activity.nowplaying.dialogDismiss");
        intent.putExtra("com.amazon.mp3.activity.BROADCAST_BUTTON_RUNNABLE_ID", runnableId);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
