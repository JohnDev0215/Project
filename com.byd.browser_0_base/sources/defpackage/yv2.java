package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* JADX INFO: compiled from: StylusWritingHandler.java */
/* JADX INFO: loaded from: classes3.dex */
public interface yv2 {
    boolean a();

    default boolean d(View view, MotionEvent motionEvent) {
        return false;
    }

    boolean i(ImeAdapterImpl.a aVar);

    void m(Rect rect, Point point);

    default void g(Context context) {
    }

    default void l(EditorInfo editorInfo) {
    }

    default void o(boolean z) {
    }

    default void b(View view, MotionEvent motionEvent) {
    }

    default void e(View view, Rect rect, boolean z) {
    }

    default void j(int i, int i2, String str) {
    }
}
