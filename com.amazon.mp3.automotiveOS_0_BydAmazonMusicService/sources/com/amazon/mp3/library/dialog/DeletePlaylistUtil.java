package com.amazon.mp3.library.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.UniqueCodeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class DeletePlaylistUtil {
    public static final int CONFIRM_DELETE_PRIME_PLAYLIST_ID = UniqueCodeUtil.nextUniqueCode();

    public static Dialog createPrimeDialog(final Context context, final boolean remote, final DialogInterface.OnClickListener deleteListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(remote ? R.string.dmusic_playlist_confirm_delete_title : R.string.dmusic_playlist_context_remove_download);
        String string = context.getString(R.string.dmusic_prime_playlist_delete_msg_cloud);
        if (!remote) {
            string = context.getString(R.string.dmusic_offline_prime_playlist_delete_msg_device);
        }
        builder.setMessage(string);
        builder.setPositiveButton(remote ? R.string.dmusic_button_delete : R.string.dmusic_button_remove, deleteListener);
        builder.setNegativeButton(R.string.dmusic_button_cancel, (DialogInterface.OnClickListener) null);
        return builder.create();
    }

    public static Dialog createBauhausPrimeDialog(final Context context, final boolean remote, final DialogInterface.OnClickListener deleteListener) {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(context);
        bauhausDialogBuilder.setTitle(remote ? R.string.dmusic_playlist_confirm_delete_title : R.string.dmusic_playlist_context_remove_download);
        String string = context.getString(R.string.dmusic_prime_playlist_delete_msg_cloud);
        if (!remote) {
            string = context.getString(R.string.dmusic_offline_prime_playlist_delete_msg_device);
        }
        bauhausDialogBuilder.setMessage(string);
        bauhausDialogBuilder.setPositiveButton(remote ? R.string.dmusic_button_delete : R.string.dmusic_button_remove, deleteListener);
        bauhausDialogBuilder.setNegativeButton(R.string.dmusic_button_cancel, (DialogInterface.OnClickListener) null);
        return bauhausDialogBuilder.create();
    }
}
