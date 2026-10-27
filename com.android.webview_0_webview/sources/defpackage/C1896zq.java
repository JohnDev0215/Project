package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import org.chromium.android_webview.PopupTouchHandleDrawable;

/* JADX INFO: renamed from: zq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1896zq {
    public View a;
    public final int[] b = new int[2];
    public final ArrayList c = new ArrayList();
    public ViewTreeObserver.OnPreDrawListener d;

    public C1896zq(View view) {
        this.a = view;
        b();
        this.d = new ViewTreeObserverOnPreDrawListenerC1786xq(this);
    }

    public void a(InterfaceC1841yq interfaceC1841yq) {
        if (this.c.contains(interfaceC1841yq)) {
            return;
        }
        if (this.c.isEmpty()) {
            this.a.getViewTreeObserver().addOnPreDrawListener(this.d);
            b();
        }
        this.c.add(interfaceC1841yq);
    }

    public final void b() {
        int[] iArr = this.b;
        int i = iArr[0];
        int i2 = iArr[1];
        this.a.getLocationInWindow(iArr);
        int[] iArr2 = this.b;
        if (iArr2[0] == i && iArr2[1] == i2) {
            return;
        }
        for (int i3 = 0; i3 < this.c.size(); i3++) {
            InterfaceC1841yq interfaceC1841yq = (InterfaceC1841yq) this.c.get(i3);
            int[] iArr3 = this.b;
            int i4 = iArr3[0];
            int i5 = iArr3[1];
            PopupTouchHandleDrawable popupTouchHandleDrawable = ((C0959iq) interfaceC1841yq).a;
            if (popupTouchHandleDrawable.B != i4 || popupTouchHandleDrawable.C != i5) {
                popupTouchHandleDrawable.B = i4;
                popupTouchHandleDrawable.C = i5;
                popupTouchHandleDrawable.m();
            }
        }
    }
}
