package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.widget.FrameLayout;
import defpackage.ac3;
import defpackage.f81;
import defpackage.m60;
import defpackage.n60;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import m60.a;
import org.chromium.chrome.browser.download.home.filter.b;

/* JADX INFO: compiled from: DateOrderedListCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class g60 implements ac3.c {
    public final Context a;
    public final org.chromium.chrome.browser.download.home.filter.b b;
    public final m60 c;
    public final wa2 d;
    public FrameLayout e;

    /* JADX INFO: compiled from: DateOrderedListCoordinator.java */
    public interface a {
    }

    /* JADX INFO: compiled from: DateOrderedListCoordinator.java */
    @FunctionalInterface
    public interface b {
    }

    public g60(Context context, wc0 wc0Var, p32 p32Var, org.chromium.components.offline_items_collection.a aVar, pq2 pq2Var, org.chromium.components.browser_ui.widget.selectable_list.a aVar2, sc0 sc0Var, tc0 tc0Var, bi1 bi1Var, rk0 rk0Var, na0 na0Var) {
        this.a = context;
        g81 g81Var = new g81();
        g70 g70Var = new g70(g81Var);
        r60 r60Var = new r60(context, wc0Var, g70Var, tc0Var);
        this.d = new wa2(context, bi1Var);
        final m60 m60Var = new m60(aVar, rk0Var, new gx(10, this), pq2Var, new c01(7, this), aVar2, wc0Var, tc0Var, g81Var, na0Var);
        this.c = m60Var;
        org.chromium.chrome.browser.download.home.empty.a aVar3 = new org.chromium.chrome.browser.download.home.empty.a(context, m60Var.r);
        lg2 lg2Var = m60Var.s;
        hu2 hu2Var = new hu2(context, lg2Var);
        org.chromium.chrome.browser.download.home.filter.b bVar = new org.chromium.chrome.browser.download.home.filter.b(context, lg2Var, p32Var);
        this.b = bVar;
        b.a aVar4 = new b.a() { // from class: f60
            @Override // org.chromium.chrome.browser.download.home.filter.b.a
            public final void a(int i) {
                m60 m60Var2 = m60Var;
                r81 r81Var = m60Var2.m;
                n60.a aVar5 = r81Var.e;
                if (aVar5 != null) {
                    aVar5.reset();
                }
                n60.a aVar6 = r81Var.h;
                if (aVar6 != null) {
                    aVar6.reset();
                }
                ((Map) r81Var.j.a).clear();
                int i2 = r81Var.k;
                boolean z = i2 == i || !(i2 == 7 || i == 7);
                if (i2 == -1 || !z) {
                    r81Var.k = i;
                    r81Var.a();
                }
                m60.a aVar7 = m60Var2.new a();
                try {
                    eh3 eh3Var = m60Var2.r;
                    eh3Var.h = i;
                    eh3Var.k();
                    aVar7.close();
                } catch (Throwable th) {
                    try {
                        aVar7.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            }
        };
        org.chromium.base.b<b.a> bVar2 = bVar.a;
        bVar2.a(aVar4);
        bVar2.a(sc0Var);
        bVar2.a(aVar3);
        f81.i iVar = new f81.i(9223372036854775806L, hu2Var.b);
        ArrayList arrayList = g70Var.g;
        int size = arrayList.size();
        arrayList.add(iVar);
        g70Var.r(size, 1);
        f81.i iVar2 = new f81.i(9223372036854775805L, bVar.a());
        int size2 = arrayList.size();
        arrayList.add(iVar2);
        g70Var.r(size2, 1);
        this.e = new FrameLayout(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.e.addView(aVar3.g(), layoutParams);
        this.e.addView(r60Var.i, new FrameLayout.LayoutParams(-1, -1));
    }

    public final void a(String str) {
        m60 m60Var = this.c;
        m60Var.getClass();
        m60.a aVar = m60Var.new a();
        try {
            lg2 lg2Var = m60Var.s;
            lg2Var.getClass();
            if (str == null) {
                str = "";
            }
            String lowerCase = str.toLowerCase(Locale.getDefault());
            if (!TextUtils.equals(lg2Var.h, lowerCase)) {
                lg2Var.h = lowerCase;
                lg2Var.k();
            }
            aVar.close();
        } catch (Throwable th) {
            try {
                aVar.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
