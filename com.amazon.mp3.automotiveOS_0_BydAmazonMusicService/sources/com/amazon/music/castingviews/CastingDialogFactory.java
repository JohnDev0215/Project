package com.amazon.music.castingviews;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/* JADX INFO: loaded from: classes4.dex */
public class CastingDialogFactory {
    public static AlertDialog createRemoteControlDialog(Context context, String str, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getString(R$string.casting_remote_control_dialog_text, str)).setNegativeButton(R$string.casting_remote_control_dialog_negative_text, onClickListener).setPositiveButton(R$string.casting_remote_control_dialog_positive_text, onClickListener);
        return builder.create();
    }

    public static AlertDialog createDisconnectDeviceFirstDialog(Context context, String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getString(R$string.casting_disconnect_first, str)).setPositiveButton(R$string.dmusic_ok_button_title, (DialogInterface.OnClickListener) null);
        return builder.create();
    }
}
