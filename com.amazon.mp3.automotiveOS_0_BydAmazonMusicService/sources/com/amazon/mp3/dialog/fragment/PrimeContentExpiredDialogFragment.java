package com.amazon.mp3.dialog.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;

/* JADX INFO: loaded from: classes3.dex */
public class PrimeContentExpiredDialogFragment extends DialogFragment {
    public static final String TAG = PrimeContentExpiredDialogFragment.class.getSimpleName();

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final FragmentActivity activity = getActivity();
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(activity);
        bauhausDialogBuilder.setTitle(activity.getString(R.string.dmusic_prime_content_expiration_dialog_title));
        if (ConnectivityUtil.hasAnyInternetConnection()) {
            bauhausDialogBuilder.setMessage(activity.getString(R.string.dmusic_prime_content_expiration_dialog_message));
            bauhausDialogBuilder.setPositiveButton(R.string.dmusic_setting_resync_title, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.PrimeContentExpiredDialogFragment.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int which) {
                    SyncService.startSync(activity, 128);
                    BauhausToastUtils.showTextToast(PrimeContentExpiredDialogFragment.this.getActivity(), R.string.dmusic_setting_resync_toast, 0);
                }
            });
        } else {
            bauhausDialogBuilder.setMessage(activity.getString(R.string.dmusic_prime_content_expiration_dialog_message_no_connection));
            bauhausDialogBuilder.setPositiveButton(R.string.dmusic_prime_content_expiration_dialog_positive_btn, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.PrimeContentExpiredDialogFragment.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int which) {
                    PrimeContentExpiredDialogFragment.this.getActivity().startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
                }
            });
        }
        return bauhausDialogBuilder.create();
    }
}
