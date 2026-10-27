package com.amazon.mp3.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;

/* JADX INFO: loaded from: classes3.dex */
public class EqualizerDialogBuilder {
    private Context mContext;

    public EqualizerDialogBuilder(final Context context) {
        this.mContext = context;
    }

    public Dialog getUnavailableWhileCastingDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this.mContext);
        bauhausDialogBuilder.setTitle(R.string.dmusic_equalizer_unavailable_title);
        bauhausDialogBuilder.setMessage(R.string.dmusic_equalizer_unavailable_casting_desc);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, (DialogInterface.OnClickListener) null);
        return bauhausDialogBuilder.create();
    }
}
