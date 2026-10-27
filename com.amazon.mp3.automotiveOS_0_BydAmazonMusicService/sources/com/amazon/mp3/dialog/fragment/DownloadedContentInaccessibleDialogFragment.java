package com.amazon.mp3.dialog.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.AccessibilityFocusDialogBuilder;
import com.amazon.mp3.util.BauhausDialogBuilder;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadedContentInaccessibleDialogFragment extends DialogFragment {
    private String mItemType;

    public static DownloadedContentInaccessibleDialogFragment newInstance(final String itemType) {
        DownloadedContentInaccessibleDialogFragment downloadedContentInaccessibleDialogFragment = new DownloadedContentInaccessibleDialogFragment();
        downloadedContentInaccessibleDialogFragment.setItemName(itemType);
        return downloadedContentInaccessibleDialogFragment;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        FragmentActivity activity = getActivity();
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(activity);
        bauhausDialogBuilder.setTitle(getString(R.string.dmusic_dialog_error_content_not_accessible_title, this.mItemType));
        bauhausDialogBuilder.setMessage(getString(R.string.dmusic_downloaded_content_unavailable_dialog_message));
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, (DialogInterface.OnClickListener) null);
        Dialog dialogCreate = bauhausDialogBuilder.create();
        AccessibilityFocusDialogBuilder.setAccessibilityFocusDelay(activity, dialogCreate);
        return dialogCreate;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mItemType == null) {
            dismiss();
        }
    }

    private void setItemName(final String itemType) {
        this.mItemType = itemType;
    }
}
