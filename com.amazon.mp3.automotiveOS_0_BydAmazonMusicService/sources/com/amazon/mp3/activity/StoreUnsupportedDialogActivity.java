package com.amazon.mp3.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;

/* JADX INFO: loaded from: classes3.dex */
public class StoreUnsupportedDialogActivity extends DialogActivity {
    private DialogInterface.OnClickListener mOnOkClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.StoreUnsupportedDialogActivity.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            StoreUnsupportedDialogActivity.this.finish();
        }
    };

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass(context, StoreUnsupportedDialogActivity.class);
        return intent;
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setMessage(R.string.dmusic_store_unsupported_dialog_message);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, this.mOnOkClickListener);
        return bauhausDialogBuilder.create();
    }
}
