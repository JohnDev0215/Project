package com.amazon.mp3.dialog.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;

/* JADX INFO: loaded from: classes3.dex */
public class MarketplaceUnsupportedDialog extends DialogFragment {
    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(getActivity());
        bauhausDialogBuilder.setTitle(getString(R.string.dmusic_no_subscription_in_marketplace_title));
        bauhausDialogBuilder.setMessage(getString(R.string.dmusic_no_subscription_in_marketplace_body));
        bauhausDialogBuilder.setNegativeButton(R.string.dmusic_button_ok, (DialogInterface.OnClickListener) null);
        return bauhausDialogBuilder.create();
    }
}
