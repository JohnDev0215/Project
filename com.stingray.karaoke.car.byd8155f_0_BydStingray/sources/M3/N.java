package m3;

import android.content.Context;
import android.widget.FrameLayout;
import g2.C1289b;
import h2.AbstractC1343A;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class N extends FrameLayout implements H {
    public final C1803c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final L f19003m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f19004n;
    public C1804d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f19005p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f19006q;

    public N(Context context) {
        super(context, null);
        this.f19004n = Collections.emptyList();
        this.o = C1804d.f19012g;
        this.f19005p = 0.0533f;
        this.f19006q = 0.08f;
        C1803c c1803c = new C1803c(context);
        this.l = c1803c;
        L l = new L(context, null);
        this.f19003m = l;
        l.setBackgroundColor(0);
        addView(c1803c);
        addView(l);
    }

    @Override // m3.H
    public final void a(List list, C1804d c1804d, float f10, float f11) {
        this.o = c1804d;
        this.f19005p = f10;
        this.f19006q = f11;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i7 = 0; i7 < list.size(); i7++) {
            C1289b c1289b = (C1289b) list.get(i7);
            if (c1289b.d != null) {
                arrayList.add(c1289b);
            } else {
                arrayList2.add(c1289b);
            }
        }
        if (!this.f19004n.isEmpty() || !arrayList2.isEmpty()) {
            this.f19004n = arrayList2;
            c();
        }
        this.l.a(arrayList, c1804d, f10, f11);
        invalidate();
    }

    public final String b(float f10, int i7) {
        float fS = h6.n.s(i7, f10, getHeight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        if (fS == -3.4028235E38f) {
            return "unset";
        }
        Object[] objArr = {Float.valueOf(fS / getContext().getResources().getDisplayMetrics().density)};
        int i10 = AbstractC1343A.f15195a;
        return String.format(Locale.US, "%.2fpx", objArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x05f7  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0633  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0656  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0678  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x06ae  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0239  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c() {
        /*
            Method dump skipped, instruction units count: 1833
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: m3.N.c():void");
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        super.onLayout(z6, i7, i10, i11, i12);
        if (!z6 || this.f19004n.isEmpty()) {
            return;
        }
        c();
    }
}
