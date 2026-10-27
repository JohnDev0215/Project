package com.amazon.mp3.store.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ResourceUtil;

/* JADX INFO: loaded from: classes4.dex */
public class NoConnectionDialog extends DialogFragment {
    private FragmentManager fragmentManager;
    private Context mContext;
    private Dialog mDialog;
    private DialogInterface.OnDismissListener mListener;
    private boolean withRetry;
    private boolean mIsDialogShowing = false;
    private DialogInterface.OnCancelListener mOnCancelListener = new DialogInterface.OnCancelListener() { // from class: com.amazon.mp3.store.dialog.NoConnectionDialog.1
        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            if (NoConnectionDialog.this.mListener != null) {
                NoConnectionDialog.this.mListener.onDismiss(dialog);
            }
        }
    };
    private DialogInterface.OnClickListener mOnClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.store.dialog.NoConnectionDialog.2
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            if (NoConnectionDialog.this.mListener != null) {
                NoConnectionDialog.this.mListener.onDismiss(dialog);
            }
        }
    };
    private DialogInterface.OnClickListener mOnRetryClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.store.dialog.NoConnectionDialog.3
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(final DialogInterface dialog, final int i) {
            NoConnectionDialog.this.onRetryConnection(dialog);
        }
    };

    public static NoConnectionDialog newInstance(final boolean withRetry) {
        NoConnectionDialog noConnectionDialog = new NoConnectionDialog();
        Bundle bundle = new Bundle();
        bundle.putBoolean("withRetry", withRetry);
        noConnectionDialog.setArguments(bundle);
        return noConnectionDialog;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.mContext = getActivity();
        this.withRetry = getArguments().getBoolean("withRetry");
        Dialog dialogBuild = build();
        this.mDialog = dialogBuild;
        return dialogBuild;
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(final FragmentManager manager, final String tag) {
        if (this.mIsDialogShowing) {
            return;
        }
        this.fragmentManager = manager;
        FragmentTransaction fragmentTransactionBeginTransaction = manager.beginTransaction();
        Fragment fragmentFindFragmentByTag = this.fragmentManager.findFragmentByTag(tag);
        if (fragmentFindFragmentByTag != null) {
            fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag);
        }
        this.mIsDialogShowing = true;
        fragmentTransactionBeginTransaction.add(this, tag);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        this.mIsDialogShowing = false;
        super.onDismiss(dialog);
    }

    public void setListener(DialogInterface.OnDismissListener listener) {
        this.mListener = listener;
    }

    public boolean isShowing() {
        return this.mIsDialogShowing;
    }

    private Dialog build() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this.mContext);
        bauhausDialogBuilder.setOnCancelListener(this.mOnCancelListener);
        bauhausDialogBuilder.setTitle(R.string.dmusic_no_connection_title);
        bauhausDialogBuilder.setMessage(ResourceUtil.getNoConnectionStringId());
        if (this.withRetry) {
            bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_retry, this.mOnRetryClickListener);
        } else {
            bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, this.mOnClickListener);
        }
        return bauhausDialogBuilder.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRetryConnection(final DialogInterface dialog) {
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            newInstance(true).show(this.fragmentManager, "NoConnectionDialog");
            return;
        }
        DialogInterface.OnDismissListener onDismissListener = this.mListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }
}
