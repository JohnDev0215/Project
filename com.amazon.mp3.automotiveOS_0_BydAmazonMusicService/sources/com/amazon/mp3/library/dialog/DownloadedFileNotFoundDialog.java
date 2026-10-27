package com.amazon.mp3.library.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.helper.TrackDownloadHelper;
import com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.TrackType;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadedFileNotFoundDialog extends DialogFragment {
    private AbstractItem mItem;

    public static DownloadedFileNotFoundDialog newInstance(final AbstractItem item) {
        DownloadedFileNotFoundDialog downloadedFileNotFoundDialog = new DownloadedFileNotFoundDialog();
        downloadedFileNotFoundDialog.setItem(item);
        return downloadedFileNotFoundDialog;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int i;
        int i2;
        final FragmentActivity activity = getActivity();
        if (isNotTrackOrStorageLocationUnknown() || new TrackDownloadHelper(activity).isInInternalStorageLocation(this.mItem.isAllOwned(), ((MusicTrack) this.mItem).getLocalUri())) {
            i = R.string.downloaded_file_location_not_found_dialog_title;
            i2 = R.string.downloaded_file_location_not_found_dialog_message;
        } else {
            i = R.string.sd_card_not_found_dialog_title;
            i2 = R.string.sd_card_not_found_dialog_message;
        }
        BauhausDialogBuilder positiveButton = new BauhausDialogBuilder(activity).setTitle(i).setMessage(i2).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.DownloadedFileNotFoundDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialog, final int which) {
                DownloadedFileNotFoundDialog.this.sendMetricEvent(ActionType.SD_CARD_NOT_FOUND_OK);
            }
        });
        AbstractItem abstractItem = this.mItem;
        if (abstractItem != null && (abstractItem instanceof MusicTrack)) {
            final MusicTrack musicTrack = (MusicTrack) abstractItem;
            if (musicTrack.getType() != TrackType.CATALOG_PLAYLIST_TRACK) {
                positiveButton.setNegativeButton(R.string.dmusic_context_delete_device, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.DownloadedFileNotFoundDialog.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(final DialogInterface dialog, final int which) {
                        DownloadedFileNotFoundDialog.this.sendMetricEvent(ActionType.SD_CARD_NOT_FOUND_REMOVE_CONTENT);
                        new RemoveLibraryContentProvider(activity, null).removeTrackFromDevice(musicTrack);
                    }
                });
            }
        }
        return positiveButton.create();
    }

    private boolean isNotTrackOrStorageLocationUnknown() {
        AbstractItem abstractItem = this.mItem;
        if (abstractItem == null || !(abstractItem instanceof MusicTrack)) {
            return true;
        }
        MusicTrack musicTrack = (MusicTrack) abstractItem;
        return MusicDownloader.getInstance(getContext()).isTrackAvailableOffline(!musicTrack.isAllOwned() ? musicTrack.getAsin() : musicTrack.getLocalUri(), !musicTrack.isAllOwned());
    }

    protected void sendMetricEvent(final ActionType actionType) {
        if (actionType != null) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
        }
    }

    public void setItem(final AbstractItem item) {
        this.mItem = item;
    }
}
