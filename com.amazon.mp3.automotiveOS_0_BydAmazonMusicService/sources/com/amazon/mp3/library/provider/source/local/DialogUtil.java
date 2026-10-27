package com.amazon.mp3.library.provider.source.local;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.fragment.DeleteContentHandler;
import com.amazon.mp3.util.UniqueCodeUtil;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogUtil {
    public static final int CONFIRM_DELETE_CONTENT_ID = UniqueCodeUtil.nextUniqueCode();

    public static Dialog buildConfirmDeleteTrackDialog(final Activity activity, final Uri trackUri, String trackTitle) {
        if (trackUri == null) {
            throw new IllegalArgumentException("invalid track uri!");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton(R.string.dmusic_button_yes, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.provider.source.local.DialogUtil.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                DeleteUtil.deleteTracksFromDevice(activity, trackUri);
            }
        });
        builder.setNegativeButton(R.string.dmusic_button_no, (DialogInterface.OnClickListener) null);
        builder.setTitle(R.string.dmusic_track_confirm_delete_title);
        builder.setMessage(activity.getString(R.string.dmusic_track_confirm_delete_msg));
        return builder.create();
    }

    public static Dialog buildConfirmDeleteTrackFromCloudDialog(final Activity activity, final DeleteContentHandler deleteHandler) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton(R.string.dmusic_button_yes, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.provider.source.local.DialogUtil.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                DeleteContentHandler deleteContentHandler = deleteHandler;
                if (deleteContentHandler != null) {
                    deleteContentHandler.deleteContent();
                }
            }
        });
        builder.setNegativeButton(R.string.dmusic_button_no, (DialogInterface.OnClickListener) null);
        builder.setTitle(R.string.dmusic_context_delete_cloud);
        builder.setMessage(R.string.dmusic_track_confirm_delete_cloud_msg);
        return builder.create();
    }
}
