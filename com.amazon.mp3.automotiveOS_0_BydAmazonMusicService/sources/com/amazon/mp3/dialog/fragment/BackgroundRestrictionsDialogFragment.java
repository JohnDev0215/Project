package com.amazon.mp3.dialog.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.AccessibilityFocusDialogBuilder;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class BackgroundRestrictionsDialogFragment extends DialogFragment {
    public static final String TAG = BackgroundRestrictionsDialogFragment.class.getSimpleName();

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final Context context = getContext();
        AccessibilityFocusDialogBuilder accessibilityFocusDialogBuilder = new AccessibilityFocusDialogBuilder(context);
        accessibilityFocusDialogBuilder.setTitle(R.string.dmusic_background_restrictions_title);
        accessibilityFocusDialogBuilder.setMessage(R.string.dmusic_background_restrictions_message);
        accessibilityFocusDialogBuilder.setPositiveButton(R.string.dmusic_background_restrictions_turn_off, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.BackgroundRestrictionsDialogFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                Log.info(BackgroundRestrictionsDialogFragment.TAG, "Launching App Info to assist in turning the battery restrictions off.");
                context.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + context.getPackageName())));
            }
        });
        accessibilityFocusDialogBuilder.setNegativeButton(R.string.dmusic_background_restrictions_ignore, (DialogInterface.OnClickListener) null);
        return accessibilityFocusDialogBuilder.create();
    }
}
