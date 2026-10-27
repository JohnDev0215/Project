package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.InputMethodManager;
import defpackage.h01;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: InputMethodManagerWrapperImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class i01 implements h01 {
    public final Context a;
    public WindowAndroid b;
    public final h01.a c;
    public uy2 d;
    public final boolean e = N.Mudil8Bg("OptimizeImmHideCalls");

    public i01(Context context, WindowAndroid windowAndroid, h01.a aVar) {
        this.a = context;
        this.b = windowAndroid;
        this.c = aVar;
    }

    @Override // defpackage.h01
    public final void a(WindowAndroid windowAndroid) {
        this.b = windowAndroid;
    }

    @Override // defpackage.h01
    public final boolean b(IBinder iBinder) {
        this.d = null;
        InputMethodManager inputMethodManagerJ = j();
        if (inputMethodManagerJ == null || (this.e && !inputMethodManagerJ.isAcceptingText())) {
            return false;
        }
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            return inputMethodManagerJ.hideSoftInputFromWindow(iBinder, 0, null);
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
        }
    }

    @Override // defpackage.h01
    public final void c(View view, CursorAnchorInfo cursorAnchorInfo) {
        InputMethodManager inputMethodManagerJ = j();
        if (inputMethodManagerJ == null) {
            return;
        }
        inputMethodManagerJ.updateCursorAnchorInfo(view, cursorAnchorInfo);
    }

    @Override // defpackage.h01
    public final boolean d(View view) {
        InputMethodManager inputMethodManagerJ = j();
        return inputMethodManagerJ != null && inputMethodManagerJ.isActive(view);
    }

    @Override // defpackage.h01
    public final void e(View view, int i, ResultReceiver resultReceiver) {
        boolean z;
        nx0 nx0VarK;
        Activity activity = null;
        this.d = null;
        WindowAndroid windowAndroid = this.b;
        if (windowAndroid != null && (nx0VarK = windowAndroid.k()) != null) {
            activity = (Activity) nx0VarK.get();
        }
        if (activity != null) {
            ya0 ya0VarA = ya0.a(this.a);
            ya0 ya0VarA2 = ya0.a(activity);
            int i2 = ya0VarA.b;
            int i3 = ya0VarA2.b;
            if (i3 != i2) {
                fa1.l("IMM", "Activity's display ID(%d) does not match context's display ID(%d). Using a workaround to show soft input on the correct display...", Integer.valueOf(i3), Integer.valueOf(i2));
                z = false;
            } else {
                z = true;
            }
            if (!z) {
                activity.getWindow().setLocalFocus(true, true);
                h01.a aVar = this.c;
                if (aVar != null) {
                    if (!(((ImeAdapterImpl) aVar).g != null)) {
                        this.d = new uy2(this, view, i, resultReceiver);
                        return;
                    }
                }
            }
        }
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            InputMethodManager inputMethodManagerJ = j();
            if (inputMethodManagerJ != null) {
                inputMethodManagerJ.showSoftInput(view, i, resultReceiver);
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
        }
    }

    @Override // defpackage.h01
    public final void f(View view, int i, int i2, int i3, int i4) {
        InputMethodManager inputMethodManagerJ = j();
        if (inputMethodManagerJ == null) {
            return;
        }
        inputMethodManagerJ.updateSelection(view, i, i2, i3, i4);
    }

    @Override // defpackage.h01
    public final void g() {
        uy2 uy2Var = this.d;
        if (uy2Var == null) {
            return;
        }
        this.d = null;
        PostTask.c(rh3.a, uy2Var);
    }

    @Override // defpackage.h01
    public final void h(View view, int i, ExtractedText extractedText) {
        InputMethodManager inputMethodManagerJ = j();
        if (inputMethodManagerJ == null) {
            return;
        }
        inputMethodManagerJ.updateExtractedText(view, i, extractedText);
    }

    @Override // defpackage.h01
    public final void i(View view) {
        InputMethodManager inputMethodManagerJ = j();
        if (inputMethodManagerJ == null) {
            return;
        }
        inputMethodManagerJ.restartInput(view);
    }

    public final InputMethodManager j() {
        nx0 nx0VarK;
        WindowAndroid windowAndroid = this.b;
        Context context = null;
        if (windowAndroid != null && (nx0VarK = windowAndroid.k()) != null) {
            context = (Activity) nx0VarK.get();
        }
        if (context == null) {
            context = this.a;
        }
        return (InputMethodManager) context.getSystemService("input_method");
    }
}
