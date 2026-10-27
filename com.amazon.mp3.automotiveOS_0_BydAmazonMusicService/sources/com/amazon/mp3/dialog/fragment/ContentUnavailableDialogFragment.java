package com.amazon.mp3.dialog.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.AccessibilityFocusDialogBuilder;
import com.amazon.mp3.library.fragment.DeleteContentHandler;
import com.amazon.mp3.util.StringUtil;

/* JADX INFO: loaded from: classes3.dex */
public class ContentUnavailableDialogFragment extends DialogFragment {
    public static final String TAG = ContentUnavailableDialogFragment.class.getSimpleName();
    private DeleteContentHandler mDeleteHandler;
    private String mItemName;

    public static ContentUnavailableDialogFragment newInstance(DeleteContentHandler deleteHandler, String itemName) {
        ContentUnavailableDialogFragment contentUnavailableDialogFragment = new ContentUnavailableDialogFragment();
        contentUnavailableDialogFragment.setDeleteHandler(deleteHandler);
        contentUnavailableDialogFragment.setItemName(itemName);
        return contentUnavailableDialogFragment;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AccessibilityFocusDialogBuilder accessibilityFocusDialogBuilder = new AccessibilityFocusDialogBuilder(getActivity());
        String lowercaseNoun = StringUtil.toLowercaseNoun(this.mItemName, getResources().getConfiguration().locale);
        accessibilityFocusDialogBuilder.setTitle(getString(R.string.dmusic_content_unavailable_dialog_title, this.mItemName));
        accessibilityFocusDialogBuilder.setMessage(getString(R.string.dmusic_content_unavailable_dialog_message, lowercaseNoun));
        if (this.mDeleteHandler != null) {
            accessibilityFocusDialogBuilder.setPositiveButton(getString(R.string.dmusic_content_unavailable_dialog_positive_btn, this.mItemName), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.ContentUnavailableDialogFragment.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int which) {
                    if (ContentUnavailableDialogFragment.this.mDeleteHandler != null) {
                        ContentUnavailableDialogFragment.this.mDeleteHandler.deleteContent();
                    }
                }
            });
        } else {
            accessibilityFocusDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, (DialogInterface.OnClickListener) null);
        }
        return accessibilityFocusDialogBuilder.create();
    }

    public void setDeleteHandler(DeleteContentHandler deleteHandler) {
        this.mDeleteHandler = deleteHandler;
    }

    public void setItemName(String itemName) {
        this.mItemName = itemName;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mItemName == null) {
            dismiss();
        }
    }
}
