package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.ExtractedText;
import org.chromium.base.Callback;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: PartialCustomTabInputMethodWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public final class hw1 implements h01 {
    public final i01 a;
    public final Callback<Runnable> b;

    public hw1(Context context, WindowAndroid windowAndroid, Callback<Runnable> callback) {
        this.a = new i01(context, windowAndroid, null);
        this.b = callback;
    }

    @Override // defpackage.h01
    public final void a(WindowAndroid windowAndroid) {
        this.a.b = windowAndroid;
    }

    @Override // defpackage.h01
    public final boolean b(IBinder iBinder) {
        return this.a.b(iBinder);
    }

    @Override // defpackage.h01
    public final void c(View view, CursorAnchorInfo cursorAnchorInfo) {
        this.a.c(view, cursorAnchorInfo);
    }

    @Override // defpackage.h01
    public final boolean d(View view) {
        return this.a.d(view);
    }

    @Override // defpackage.h01
    public final void e(View view, int i, ResultReceiver resultReceiver) {
        this.b.onResult(new gw1(this, 0, view, resultReceiver, 0));
    }

    @Override // defpackage.h01
    public final void f(View view, int i, int i2, int i3, int i4) {
        this.a.f(view, i, i2, i3, i4);
    }

    @Override // defpackage.h01
    public final void g() {
        this.a.g();
    }

    @Override // defpackage.h01
    public final void h(View view, int i, ExtractedText extractedText) {
        this.a.h(view, i, extractedText);
    }

    @Override // defpackage.h01
    public final void i(View view) {
        this.a.i(view);
    }
}
