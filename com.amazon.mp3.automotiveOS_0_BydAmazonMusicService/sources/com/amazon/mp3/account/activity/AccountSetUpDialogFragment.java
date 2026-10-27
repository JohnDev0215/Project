package com.amazon.mp3.account.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes3.dex */
public class AccountSetUpDialogFragment extends DialogFragment {
    private AccountSetUpDialogListener mListener;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R.style.AccountSetUpDialogFragment);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.dialog_account_set_up, container, false);
        viewInflate.findViewById(R.id.account_set_up_dialog_positive_button).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.account.activity.AccountSetUpDialogFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AccountSetUpDialogFragment.this.mListener.onAcknowledgeDialog();
                AccountSetUpDialogFragment.this.dismiss();
            }
        });
        Button button = (Button) viewInflate.findViewById(R.id.account_set_up_dialog_negative_button);
        if (AmazonApplication.getCapabilities().isStandaloneExperienceSupported(getActivity())) {
            button.setText(R.string.dmusic_account_set_up_dialog_stand_alone_experience_button);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.account.activity.AccountSetUpDialogFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    AccountSetUpDialogFragment.this.mListener.onGoToStandAloneExperience();
                    AccountSetUpDialogFragment.this.dismiss();
                }
            });
        } else {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.account.activity.AccountSetUpDialogFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    AccountSetUpDialogFragment.this.mListener.onSignOut();
                    AccountSetUpDialogFragment.this.dismiss();
                }
            });
        }
        return viewInflate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(final Context context) {
        super.onAttach(context);
        try {
            this.mListener = (AccountSetUpDialogListener) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement AccountSetUpDialogListener");
        }
    }
}
