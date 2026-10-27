package com.amazon.mp3.account.activity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.EmailUtil;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class SignInFlowErrorDialogFragment extends DialogFragment {
    public static final String TAG = SignInFlowErrorDialogFragment.class.getSimpleName();
    private int errorMessageId;

    public static SignInFlowErrorDialogFragment newInstance(int errorMessageId) {
        SignInFlowErrorDialogFragment signInFlowErrorDialogFragment = new SignInFlowErrorDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("error_message_id", errorMessageId);
        signInFlowErrorDialogFragment.setArguments(bundle);
        return signInFlowErrorDialogFragment;
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.errorMessageId = getArguments().getInt("error_message_id");
        Log.error(TAG, "Displaying error dialog with message: \"%s\"", getResources().getString(this.errorMessageId));
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(getActivity());
        final String string = getString(R.string.dmusic_sso_error_dialog_title);
        bauhausDialogBuilder.setTitle(string);
        bauhausDialogBuilder.setMessage(this.errorMessageId);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.account.activity.SignInFlowErrorDialogFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ((SignInFlowActivity) SignInFlowErrorDialogFragment.this.getActivity()).onErrorDialogClickOk();
            }
        });
        bauhausDialogBuilder.setNegativeButton(getString(R.string.dmusic_button_send_report), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.account.activity.SignInFlowErrorDialogFragment.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int i) {
                EmailUtil.reportError(SignInFlowErrorDialogFragment.this.getActivity().getApplicationContext(), string);
                ((SignInFlowActivity) SignInFlowErrorDialogFragment.this.getActivity()).onErrorDialogClickSendEmailReport();
            }
        });
        return bauhausDialogBuilder.create();
    }
}
