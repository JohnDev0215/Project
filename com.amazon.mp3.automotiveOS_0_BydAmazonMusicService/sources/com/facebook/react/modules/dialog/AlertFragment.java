package com.facebook.react.modules.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.facebook.react.modules.dialog.DialogModule;

/* JADX INFO: loaded from: classes6.dex */
public class AlertFragment extends DialogFragment implements DialogInterface.OnClickListener {

    @Nullable
    private final DialogModule.AlertFragmentListener mListener;

    public AlertFragment() {
        this.mListener = null;
    }

    @SuppressLint({"ValidFragment"})
    public AlertFragment(@Nullable DialogModule.AlertFragmentListener alertFragmentListener, Bundle bundle) {
        this.mListener = alertFragmentListener;
        setArguments(bundle);
    }

    public static Dialog createDialog(Context context, Bundle bundle, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder title = new AlertDialog.Builder(context).setTitle(bundle.getString("title"));
        if (bundle.containsKey("button_positive")) {
            title.setPositiveButton(bundle.getString("button_positive"), onClickListener);
        }
        if (bundle.containsKey("button_negative")) {
            title.setNegativeButton(bundle.getString("button_negative"), onClickListener);
        }
        if (bundle.containsKey("button_neutral")) {
            title.setNeutralButton(bundle.getString("button_neutral"), onClickListener);
        }
        if (bundle.containsKey("message")) {
            title.setMessage(bundle.getString("message"));
        }
        if (bundle.containsKey(ContextMappingConstants.ITEMS)) {
            title.setItems(bundle.getCharSequenceArray(ContextMappingConstants.ITEMS), onClickListener);
        }
        return title.create();
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return createDialog(getActivity(), getArguments(), this);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogModule.AlertFragmentListener alertFragmentListener = this.mListener;
        if (alertFragmentListener != null) {
            alertFragmentListener.onClick(dialogInterface, i);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogModule.AlertFragmentListener alertFragmentListener = this.mListener;
        if (alertFragmentListener != null) {
            alertFragmentListener.onDismiss(dialogInterface);
        }
    }
}
