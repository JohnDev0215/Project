package com.amazon.mp3.playback.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.util.Clock;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ConcurrencyErrorDialogActivity extends DialogActivity {
    private String mOldDeviceName;
    private PlaybackController mController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
    private final DialogInterface.OnClickListener mOnPositiveButtonClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playback.activity.ConcurrencyErrorDialogActivity.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            ConcurrencyErrorDialogActivity.this.mController.resolveMaxConcurrency(ConcurrencyErrorDialogActivity.this.mOldDeviceName, true, Clock.now());
            dialog.cancel();
        }
    };
    private final DialogInterface.OnClickListener mOnCancelClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playback.activity.ConcurrencyErrorDialogActivity.2
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    };

    public static Intent getIntent(final Context context, final String oldDeviceName) {
        Intent intent = new Intent(context, (Class<?>) ConcurrencyErrorDialogActivity.class);
        intent.putExtra("com.amazon.mp3.playback.activity.EXTRA_CONCURRENCY_DEVICE_NAME", oldDeviceName);
        intent.addFlags(268435456);
        return intent;
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        String string;
        String stringExtra = getIntent().getStringExtra("com.amazon.mp3.playback.activity.EXTRA_CONCURRENCY_DEVICE_NAME");
        this.mOldDeviceName = stringExtra;
        if (!StringUtils.isEmpty(stringExtra)) {
            string = getApplicationContext().getResources().getString(R.string.dmusic_prime_streaming_concurrency_dlg_message_w_device_name_no_time, this.mOldDeviceName, getApplicationContext().getResources().getString(R.string.dmusic_prime_streaming_concurrency_dlg_continue));
        } else {
            string = getApplicationContext().getResources().getString(R.string.dmusic_prime_streaming_concurrency_dlg_message_no_device_name_no_time, getApplicationContext().getResources().getString(R.string.dmusic_prime_streaming_concurrency_dlg_continue));
        }
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setTitle(R.string.dmusic_prime_streaming_concurrency_dlg_title);
        bauhausDialogBuilder.setMessage(string);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_prime_streaming_concurrency_dlg_continue, this.mOnPositiveButtonClickListener);
        bauhausDialogBuilder.setCancelable(true);
        bauhausDialogBuilder.setNegativeButton(R.string.dmusic_prime_streaming_concurrency_dlg_cancel, this.mOnCancelClickListener);
        return bauhausDialogBuilder.create();
    }
}
