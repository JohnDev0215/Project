package com.amazon.mp3.dialog.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.AccessibilityFocusDialogBuilder;
import com.amazon.mp3.util.StringUtil;

/* JADX INFO: loaded from: classes3.dex */
public class ContentNotCastableDialogFragment extends DialogFragment {
    public static final String TAG = ContentNotCastableDialogFragment.class.getSimpleName();
    private String mItemName;

    public static ContentNotCastableDialogFragment newInstance(final String itemName) {
        ContentNotCastableDialogFragment contentNotCastableDialogFragment = new ContentNotCastableDialogFragment();
        contentNotCastableDialogFragment.setItemName(itemName);
        return contentNotCastableDialogFragment;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AccessibilityFocusDialogBuilder accessibilityFocusDialogBuilder = new AccessibilityFocusDialogBuilder(getActivity());
        String lowercaseNoun = StringUtil.toLowercaseNoun(this.mItemName, getResources().getConfiguration().locale);
        accessibilityFocusDialogBuilder.setTitle(getString(R.string.dmusic_content_unavailable_dialog_title, this.mItemName));
        accessibilityFocusDialogBuilder.setMessage(getString(R.string.cast_unsupported_content_text, lowercaseNoun));
        accessibilityFocusDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, (DialogInterface.OnClickListener) null);
        return accessibilityFocusDialogBuilder.create();
    }

    public void setItemName(final String itemName) {
        this.mItemName = itemName;
    }
}
