package defpackage;

import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/* JADX INFO: renamed from: yp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC1840yp {
    void a(int i, int i2, int i3, int i4);

    void b();

    void c(int i, int i2, boolean z, boolean z2);

    int computeHorizontalScrollOffset();

    int computeHorizontalScrollRange();

    void computeScroll();

    int computeVerticalScrollExtent();

    int computeVerticalScrollOffset();

    int computeVerticalScrollRange();

    boolean dispatchKeyEvent(KeyEvent keyEvent);

    AccessibilityNodeProvider getAccessibilityNodeProvider();

    void onAttachedToWindow();

    boolean onCheckIsTextEditor();

    void onConfigurationChanged(Configuration configuration);

    InputConnection onCreateInputConnection(EditorInfo editorInfo);

    void onDetachedFromWindow();

    boolean onDragEvent(DragEvent dragEvent);

    void onDraw(Canvas canvas);

    void onFocusChanged(boolean z, int i, Rect rect);

    boolean onGenericMotionEvent(MotionEvent motionEvent);

    boolean onHoverEvent(MotionEvent motionEvent);

    boolean onKeyUp(int i, KeyEvent keyEvent);

    void onMeasure(int i, int i2);

    void onSizeChanged(int i, int i2, int i3, int i4);

    boolean onTouchEvent(MotionEvent motionEvent);

    void onVisibilityChanged(View view, int i);

    void onWindowFocusChanged(boolean z);

    void onWindowVisibilityChanged(int i);

    boolean performAccessibilityAction(int i, Bundle bundle);

    void setLayerType(int i, Paint paint);
}
