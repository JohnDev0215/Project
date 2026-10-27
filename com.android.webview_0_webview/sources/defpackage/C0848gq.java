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
import org.chromium.android_webview.AwContents;

/* JADX INFO: renamed from: gq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0848gq implements InterfaceC1840yp {
    public AwContents a;
    public InterfaceC0264On b;
    public View c;

    public C0848gq(AwContents awContents, InterfaceC0264On interfaceC0264On, View view) {
        this.a = awContents;
        this.b = interfaceC0264On;
        this.c = view;
    }

    @Override // defpackage.InterfaceC1840yp
    public void a(int i, int i2, int i3, int i4) {
    }

    @Override // defpackage.InterfaceC1840yp
    public void b() {
    }

    @Override // defpackage.InterfaceC1840yp
    public void c(int i, int i2, boolean z, boolean z2) {
    }

    @Override // defpackage.InterfaceC1840yp
    public int computeHorizontalScrollOffset() {
        return 0;
    }

    @Override // defpackage.InterfaceC1840yp
    public int computeHorizontalScrollRange() {
        return 0;
    }

    @Override // defpackage.InterfaceC1840yp
    public void computeScroll() {
    }

    @Override // defpackage.InterfaceC1840yp
    public int computeVerticalScrollExtent() {
        return 0;
    }

    @Override // defpackage.InterfaceC1840yp
    public int computeVerticalScrollOffset() {
        return 0;
    }

    @Override // defpackage.InterfaceC1840yp
    public int computeVerticalScrollRange() {
        return 0;
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    @Override // defpackage.InterfaceC1840yp
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        return null;
    }

    @Override // defpackage.InterfaceC1840yp
    public void onAttachedToWindow() {
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean onCheckIsTextEditor() {
        return false;
    }

    @Override // defpackage.InterfaceC1840yp
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // defpackage.InterfaceC1840yp
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return null;
    }

    @Override // defpackage.InterfaceC1840yp
    public void onDetachedFromWindow() {
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean onDragEvent(DragEvent dragEvent) {
        return false;
    }

    @Override // defpackage.InterfaceC1840yp
    public void onDraw(Canvas canvas) {
        canvas.drawColor(this.a.n());
    }

    @Override // defpackage.InterfaceC1840yp
    public void onFocusChanged(boolean z, int i, Rect rect) {
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean onHoverEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return false;
    }

    @Override // defpackage.InterfaceC1840yp
    public void onMeasure(int i, int i2) {
        this.b.setMeasuredDimension(this.c.getMeasuredWidth(), this.c.getMeasuredHeight());
    }

    @Override // defpackage.InterfaceC1840yp
    public void onSizeChanged(int i, int i2, int i3, int i4) {
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // defpackage.InterfaceC1840yp
    public void onVisibilityChanged(View view, int i) {
    }

    @Override // defpackage.InterfaceC1840yp
    public void onWindowFocusChanged(boolean z) {
    }

    @Override // defpackage.InterfaceC1840yp
    public void onWindowVisibilityChanged(int i) {
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        return false;
    }

    @Override // defpackage.InterfaceC1840yp
    public void setLayerType(int i, Paint paint) {
    }
}
