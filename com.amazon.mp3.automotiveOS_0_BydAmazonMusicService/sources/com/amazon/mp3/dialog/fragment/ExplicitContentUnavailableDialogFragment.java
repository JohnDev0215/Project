package com.amazon.mp3.dialog.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.dialog.ExplicitFilterDialogBuilder;
import com.amazon.mp3.elf.ExplicitLanguageManager;
import com.amazon.mp3.util.ConnectivityUtil;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class ExplicitContentUnavailableDialogFragment extends DialogFragment {
    public static final String TAG = ExplicitContentUnavailableDialogFragment.class.getSimpleName();

    public enum ExplicitContentType {
        VIDEO,
        AUDIO
    }

    public static ExplicitContentUnavailableDialogFragment newInstance(final ExplicitContentType explicitContentType) {
        ExplicitContentUnavailableDialogFragment explicitContentUnavailableDialogFragment = new ExplicitContentUnavailableDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("explicitContentType", explicitContentType);
        explicitContentUnavailableDialogFragment.setArguments(bundle);
        return explicitContentUnavailableDialogFragment;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        Context context = getContext();
        Serializable serializable = getArguments() != null ? getArguments().getSerializable("explicitContentType") : null;
        boolean zIsExplicitLanguageFilterEnabled = AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled();
        setCancelable(false);
        return ExplicitFilterDialogBuilder.builder(context).withExplicitFilterEnabled(zIsExplicitLanguageFilterEnabled).withContentUnavailable().withPositiveButtonOnClickListener(getPositiveButtonOnClickListener()).build((ExplicitContentType) serializable);
    }

    private DialogInterface.OnClickListener getPositiveButtonOnClickListener() {
        return new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.ExplicitContentUnavailableDialogFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                Context context = ExplicitContentUnavailableDialogFragment.this.getContext();
                if (ConnectivityUtil.checkConnectivityAndShowDialog(context)) {
                    new ExplicitLanguageManager(context).toggleExplicitFilterSetting();
                }
            }
        };
    }
}
