package com.amazon.mp3.dialog.dialogtarget;

import androidx.fragment.app.DialogFragment;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DialogTarget implements Serializable {
    public static final String BUNDLE_KEY = DialogTarget.class.getName();

    public abstract DialogFragment getDialogFragment() throws GetDialogFragmentException;

    public static class GetDialogFragmentException extends Exception {
        public GetDialogFragmentException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }
}
