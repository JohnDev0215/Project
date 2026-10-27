package spi;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import java.util.Objects;

/* JADX INFO: compiled from: AlertDialogBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public final class i {
    public AlertDialog.Builder a;
    public Context b;
    public ViewGroup c;
    public boolean d;
    public boolean e;
    public final h f;
    public final g g;

    /* JADX INFO: compiled from: AlertDialogBuilder.java */
    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (Build.VERSION.SDK_INT < 30) {
                return;
            }
            new Bundle();
            Objects.requireNonNull(i.this);
            Objects.requireNonNull(i.this);
            throw null;
        }
    }

    public i(Context context) {
        this(context, 0);
    }

    public static /* synthetic */ WindowInsets a(i iVar, View view, WindowInsets windowInsets) {
        Objects.requireNonNull(iVar);
        if (Build.VERSION.SDK_INT < 30) {
            return view.onApplyWindowInsets(windowInsets);
        }
        if (!windowInsets.isVisible(WindowInsets.Type.ime())) {
            return view.onApplyWindowInsets(windowInsets);
        }
        new Bundle();
        throw null;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [spi.g] */
    public i(Context context, int i) {
        this.d = true;
        this.e = false;
        new a();
        this.f = new h(this, 0);
        this.g = new DialogInterface.OnDismissListener() { // from class: spi.g
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                ViewGroup viewGroup = this.a.c;
                if (viewGroup != null) {
                    viewGroup.setOnApplyWindowInsetsListener(null);
                }
            }
        };
        this.a = new AlertDialog.Builder(context, i);
        this.b = context;
    }
}
