package com.amazon.mp3.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.helper.TrackDownloadHelper;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadedFileNotFoundActivityDialog extends DialogActivity {
    private static final String IS_ALL_OWNED;
    private static final String URI_PATH;
    private boolean isAllOwned;
    private final DialogInterface.OnClickListener negativeListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.DownloadedFileNotFoundActivityDialog.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(final DialogInterface dialog, final int which) {
            DownloadedFileNotFoundActivityDialog.this.startActivity(SettingsActivity.getStartAndScrollToIntent(DownloadedFileNotFoundActivityDialog.this.getApplication(), R.string.setting_key_category_storage));
            DownloadedFileNotFoundActivityDialog.this.finish();
        }
    };
    private final DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.DownloadedFileNotFoundActivityDialog.2
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(final DialogInterface dialog, final int which) {
            DownloadedFileNotFoundActivityDialog.this.sendMetricEvent(ActionType.SD_CARD_NOT_FOUND_OK);
            DownloadedFileNotFoundActivityDialog.this.finish();
        }
    };
    private String uri;

    static {
        String simpleName = DownloadedFileNotFoundActivityDialog.class.getSimpleName();
        IS_ALL_OWNED = simpleName + "_IS_OWNED";
        URI_PATH = simpleName + "_URI_PATH";
    }

    public static Intent getStartIntent(final Context context, final MusicTrack track) {
        Intent intent = new Intent();
        intent.setClass(context, DownloadedFileNotFoundActivityDialog.class);
        intent.putExtra(IS_ALL_OWNED, track.isAllOwned());
        intent.putExtra(URI_PATH, track.getLocalUri());
        return intent;
    }

    public static Intent getStartIntent(final Context context, final boolean isAllOwned, final String uri) {
        Intent intent = new Intent();
        intent.setClass(context, DownloadedFileNotFoundActivityDialog.class);
        intent.putExtra(IS_ALL_OWNED, isAllOwned);
        intent.putExtra(URI_PATH, uri);
        return intent;
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        int i;
        int i2;
        getApplication();
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setCancelable(true);
        if (this.uri == null || !new TrackDownloadHelper(this).isInInternalStorageLocation(this.isAllOwned, this.uri)) {
            i = R.string.sd_card_not_found_dialog_title;
            i2 = R.string.sd_card_not_found_dialog_message;
        } else {
            i = R.string.downloaded_file_location_not_found_dialog_title;
            i2 = R.string.downloaded_file_location_not_found_dialog_message;
        }
        bauhausDialogBuilder.setTitle(i).setMessage(i2).setPositiveButton(R.string.ok, this.positiveListener).setNegativeButton(R.string.dmusic_more_settings_tab, this.negativeListener);
        return bauhausDialogBuilder.create();
    }

    protected void sendMetricEvent(final ActionType actionType) {
        if (actionType != null) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
        }
    }

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            this.isAllOwned = getIntent().getBooleanExtra(IS_ALL_OWNED, false);
            this.uri = getIntent().getStringExtra(URI_PATH);
        } else {
            this.isAllOwned = false;
            this.uri = null;
        }
    }
}
