package com.amazon.mp3.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.util.BauhausDialogBuilder;

/* JADX INFO: loaded from: classes3.dex */
public class SQLErrorDialog extends DialogActivity {
    public static Intent getStartIntent(Context c, String title, String text) {
        Intent intent = new Intent(c, (Class<?>) SQLErrorDialog.class);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_DIALOG_TITLE", title);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_DIALOG_TEXT", text);
        return intent;
    }

    public static Intent getStartIntent(Context c, int title_resource, int text_resource) {
        return getStartIntent(c, c.getString(title_resource), c.getString(text_resource));
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setTitle(getDialogTitle());
        bauhausDialogBuilder.setMessage(getDialogText());
        bauhausDialogBuilder.setCancelable(false);
        return bauhausDialogBuilder.create();
    }
}
