package spi;

import android.content.DialogInterface;
import android.view.View;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: DialogFragmentCallbacks.java */
/* JADX INFO: loaded from: classes2.dex */
public interface i3 extends DialogInterface.OnClickListener {
    default void a() {
    }

    default void onBindDialogView(@NonNull View view) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    default void onClick(@NonNull DialogInterface dialogInterface, int i) {
    }

    default void onDialogClosed(boolean z) {
    }
}
