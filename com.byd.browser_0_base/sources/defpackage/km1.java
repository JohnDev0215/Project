package defpackage;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* JADX INFO: compiled from: NewTabPageDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public interface km1 {
    public static final a a = new a();

    /* JADX INFO: compiled from: NewTabPageDelegate.java */
    public class a implements km1 {
    }

    default boolean a() {
        return false;
    }

    default boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    default boolean e() {
        return false;
    }

    default boolean g() {
        return false;
    }

    default boolean i() {
        return false;
    }

    default boolean j() {
        return false;
    }

    default void b(float f) {
    }

    default void c(x10 x10Var) {
    }

    default void f(float f) {
    }

    default void h(ToolbarPhone.e eVar) {
    }

    default void k(float f) {
    }

    default void d(Rect rect, Point point) {
    }
}
