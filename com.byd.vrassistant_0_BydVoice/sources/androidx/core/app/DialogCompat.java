package androidx.core.app;

import android.app.Dialog;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class DialogCompat {
    private DialogCompat() {
    }

    public static View requireViewById(Dialog dialog, int id) {
        return dialog.requireViewById(id);
    }
}
