package com.amazon.music.ghostlistening;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import com.amazon.music.events.UserInteractionAppEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class GhostListeningDialog extends DialogFragment {
    public static final String DIALOG_CLICK_EVENT;
    public static final String TAG;
    private final Logger logger = LoggerFactory.getLogger(TAG);

    protected abstract int getMessage();

    protected abstract int getNegativeButtonText();

    protected abstract int getPositiveButtonText();

    protected abstract int getTitle();

    protected abstract boolean hasTitle();

    public abstract void onNegativeClick();

    public abstract void onPositiveClick();

    static {
        String simpleName = GhostListeningDialog.class.getSimpleName();
        TAG = simpleName;
        DIALOG_CLICK_EVENT = simpleName + "ButtonClick";
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public final Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (hasTitle()) {
            builder.setTitle(getTitle());
        }
        builder.setMessage(getMessage());
        builder.setPositiveButton(getPositiveButtonText(), new DialogInterface.OnClickListener() { // from class: com.amazon.music.ghostlistening.GhostListeningDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                GhostListeningDialog.this.logger.info("GhostListening dialog's positive button clicked.");
                UserInteractionAppEvent.builder(GhostListeningDialog.DIALOG_CLICK_EVENT).publish();
                GhostListeningDialog.this.onPositiveClick();
            }
        });
        builder.setNegativeButton(getNegativeButtonText(), new DialogInterface.OnClickListener() { // from class: com.amazon.music.ghostlistening.GhostListeningDialog.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                GhostListeningDialog.this.logger.info("GhostListening dialog's negative button clicked.");
                UserInteractionAppEvent.builder(GhostListeningDialog.DIALOG_CLICK_EVENT).publish();
                GhostListeningDialog.this.onNegativeClick();
            }
        });
        return builder.create();
    }
}
