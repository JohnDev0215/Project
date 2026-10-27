package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import java.util.LinkedHashMap;
import java.util.Map;
import org.chromium.ui.base.ViewAndroidDelegate;

/* JADX INFO: renamed from: xp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1785xp extends ViewAndroidDelegate {
    public final Map d;
    public final AbstractC0511ao e;
    public final C0463Zo f;

    public C1785xp(ViewGroup viewGroup, AbstractC0511ao abstractC0511ao, C0463Zo c0463Zo) {
        super(viewGroup);
        this.d = new LinkedHashMap();
        this.e = abstractC0511ao;
        this.f = c0463Zo;
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public View acquireView() {
        ViewGroup containerView = getContainerView();
        if (containerView == null) {
            return null;
        }
        View view = new View(containerView.getContext());
        containerView.addView(view);
        this.d.put(view, null);
        return view;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0087  */
    @Override // org.chromium.ui.base.ViewAndroidDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(android.view.ViewGroup r20) {
        /*
            r19 = this;
            r8 = r19
            r9 = r20
            java.util.Map r0 = r8.d
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r10 = r0.iterator()
        Le:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L8e
            java.lang.Object r0 = r10.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            android.view.View r1 = (android.view.View) r1
            java.lang.Object r0 = r0.getValue()
            wp r0 = (defpackage.C1730wp) r0
            if (r9 == 0) goto L2b
            r9.removeView(r1)
        L2b:
            android.view.ViewGroup r2 = r8.a
            r2.addView(r1)
            if (r0 == 0) goto L87
            float r2 = r0.a
            float r3 = r0.b
            float r4 = r0.c
            float r5 = r0.d
            int r6 = r0.e
            int r7 = r0.f
            android.view.ViewGroup r0 = r19.getContainerView()
            java.util.Map r11 = r8.d
            boolean r11 = r11.containsKey(r1)
            if (r11 == 0) goto L87
            if (r0 != 0) goto L4d
            goto L87
        L4d:
            java.util.Map r15 = r8.d
            wp r14 = new wp
            r11 = r14
            r12 = r2
            r13 = r3
            r9 = r14
            r14 = r4
            r18 = r10
            r10 = r15
            r15 = r5
            r16 = r6
            r17 = r7
            r11.<init>(r12, r13, r14, r15, r16, r17)
            r10.put(r1, r9)
            boolean r0 = r0 instanceof android.widget.FrameLayout
            if (r0 == 0) goto L6e
            r0 = r19
            super.setViewPosition(r1, r2, r3, r4, r5, r6, r7)
            goto L89
        L6e:
            Zo r0 = r8.f
            int r2 = r0.b
            int r6 = r6 + r2
            int r0 = r0.c
            int r7 = r7 + r0
            android.widget.AbsoluteLayout$LayoutParams r0 = new android.widget.AbsoluteLayout$LayoutParams
            int r2 = java.lang.Math.round(r4)
            int r3 = java.lang.Math.round(r5)
            r0.<init>(r2, r3, r6, r7)
            r1.setLayoutParams(r0)
            goto L89
        L87:
            r18 = r10
        L89:
            r9 = r20
            r10 = r18
            goto Le
        L8e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1785xp.c(android.view.ViewGroup):void");
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public void onBackgroundColorChanged(int i) {
        AbstractC0511ao abstractC0511ao = this.e;
        if (i == 0) {
            i = 1;
        }
        abstractC0511ao.b = i;
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public void removeView(View view) {
        this.d.remove(view);
        ViewGroup containerView = getContainerView();
        if (containerView != null) {
            containerView.removeView(view);
        }
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public void setViewPosition(View view, float f, float f2, float f3, float f4, int i, int i2) {
        ViewGroup containerView = getContainerView();
        if (!this.d.containsKey(view) || containerView == null) {
            return;
        }
        this.d.put(view, new C1730wp(f, f2, f3, f4, i, i2));
        if (containerView instanceof FrameLayout) {
            super.setViewPosition(view, f, f2, f3, f4, i, i2);
            return;
        }
        C0463Zo c0463Zo = this.f;
        view.setLayoutParams(new AbsoluteLayout.LayoutParams(Math.round(f3), Math.round(f4), i + c0463Zo.b, i2 + c0463Zo.c));
    }
}
