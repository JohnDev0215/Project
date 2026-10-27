package defpackage;

import android.os.IBinder;
import android.os.ResultReceiver;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.ExtractedText;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: InputMethodManagerWrapper.java */
/* JADX INFO: loaded from: classes3.dex */
public interface h01 {

    /* JADX INFO: compiled from: InputMethodManagerWrapper.java */
    public interface a {
    }

    void a(WindowAndroid windowAndroid);

    boolean b(IBinder iBinder);

    void c(View view, CursorAnchorInfo cursorAnchorInfo);

    boolean d(View view);

    void e(View view, int i, ResultReceiver resultReceiver);

    void f(View view, int i, int i2, int i3, int i4);

    void g();

    void h(View view, int i, ExtractedText extractedText);

    void i(View view);
}
