package com.amazon.mp3.dialog.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;

/* JADX INFO: loaded from: classes3.dex */
public class DeleteTrackErrorDialogFragment extends DialogFragment {
    public static final String TAG = DeleteTrackErrorDialogFragment.class.getSimpleName();

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(getActivity());
        bauhausDialogBuilder.setMessage(R.string.dmusic_library_delete_track_error_dialog_message).setTitle(R.string.dmusic_library_delete_track_error_dialog_title).setNeutralButton(R.string.dmusic_button_ok, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.DeleteTrackErrorDialogFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                DeleteTrackErrorDialogFragment.this.dismiss();
            }
        });
        return bauhausDialogBuilder.create();
    }
}
