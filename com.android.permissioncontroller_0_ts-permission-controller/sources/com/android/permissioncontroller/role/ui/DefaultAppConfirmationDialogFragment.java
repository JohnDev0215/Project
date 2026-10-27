package com.android.permissioncontroller.role.ui;

import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes8.dex */
public class DefaultAppConfirmationDialogFragment extends DialogFragment {
    private CharSequence mMessage;
    private String mPackageName;

    public interface Listener {
        void setDefaultApp(String str);
    }

    public static DefaultAppConfirmationDialogFragment newInstance(String packageName, CharSequence message) {
        DefaultAppConfirmationDialogFragment fragment = new DefaultAppConfirmationDialogFragment();
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.PACKAGE_NAME", packageName);
        arguments.putCharSequence("android.intent.extra.TEXT", message);
        fragment.setArguments(arguments);
        return fragment;
    }

    public static void show(String packageName, CharSequence message, Fragment fragment) {
        newInstance(packageName, message).show(fragment.getChildFragmentManager(), (String) null);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.mPackageName = arguments.getString("android.intent.extra.PACKAGE_NAME");
        this.mMessage = arguments.getCharSequence("android.intent.extra.TEXT");
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext(), getTheme()).setMessage(this.mMessage).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$DefaultAppConfirmationDialogFragment$xjItvlFbvmtzQT3K0jbow7nplck
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreateDialog$0$DefaultAppConfirmationDialogFragment(dialogInterface, i);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).create();
    }

    public /* synthetic */ void lambda$onCreateDialog$0$DefaultAppConfirmationDialogFragment(DialogInterface dialog, int which) {
        onOk();
    }

    private void onOk() {
        Listener listener = (Listener) getParentFragment();
        listener.setDefaultApp(this.mPackageName);
    }
}
