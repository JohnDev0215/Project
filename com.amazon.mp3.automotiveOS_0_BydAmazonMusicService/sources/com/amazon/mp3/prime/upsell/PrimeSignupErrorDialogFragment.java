package com.amazon.mp3.prime.upsell;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.Branding;
import com.amazon.mp3.util.BauhausDialogBuilder;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeSignupErrorDialogFragment extends DialogFragment {
    public static PrimeSignupErrorDialogFragment newInstance(boolean startLibraryHomeOnDismiss) {
        PrimeSignupErrorDialogFragment primeSignupErrorDialogFragment = new PrimeSignupErrorDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("startLibraryHomeOnDismiss", startLibraryHomeOnDismiss);
        primeSignupErrorDialogFragment.setArguments(bundle);
        return primeSignupErrorDialogFragment;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        FragmentActivity activity = getActivity();
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(activity);
        String primeBranding = Branding.getPrimeBranding(activity);
        bauhausDialogBuilder.setTitle(activity.getString(R.string.dmusic_prime_signup_error_dialog_title, primeBranding));
        bauhausDialogBuilder.setMessage(activity.getString(R.string.dmusic_prime_signup_error_dialog_description, primeBranding));
        bauhausDialogBuilder.setCancelable(false);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.prime.upsell.PrimeSignupErrorDialogFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                if (PrimeSignupErrorDialogFragment.this.getArguments().getBoolean("startLibraryHomeOnDismiss", true)) {
                    PrimeSignupErrorDialogFragment primeSignupErrorDialogFragment = PrimeSignupErrorDialogFragment.this;
                    primeSignupErrorDialogFragment.startActivity(MusicHomeActivity.getStartIntent(primeSignupErrorDialogFragment.getActivity()));
                }
                PrimeSignupErrorDialogFragment.this.getActivity().finish();
            }
        });
        return bauhausDialogBuilder.create();
    }
}
