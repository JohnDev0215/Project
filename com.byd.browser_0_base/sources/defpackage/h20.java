package defpackage;

import defpackage.o23;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;

/* JADX INFO: compiled from: CurrentPageVerifier.java */
/* JADX INFO: loaded from: classes.dex */
public final class h20 implements hk1 {
    public final a30 e;
    public final oj f;
    public final il3 g;
    public b h;
    public final org.chromium.base.b<Runnable> i = new org.chromium.base.b<>();

    /* JADX INFO: compiled from: CurrentPageVerifier.java */
    public class a extends o23.b {
        public a() {
        }

        @Override // o23.b
        public final void A1(Tab tab) {
            h20.this.b(tab.getUrl().j());
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
            if (!navigationHandle.g || navigationHandle.c) {
                return;
            }
            h20.this.b(navigationHandle.e.j());
        }
    }

    /* JADX INFO: compiled from: CurrentPageVerifier.java */
    public static class b {
        public final String a;
        public final String b;
        public final int c;

        public b(int i, String str, String str2) {
            this.a = str;
            this.b = str2;
            this.c = i;
        }
    }

    public h20(q2 q2Var, o23 o23Var, a30 a30Var, oj ojVar, il3 il3Var) {
        a aVar = new a();
        this.e = a30Var;
        this.f = ojVar;
        this.g = il3Var;
        o23Var.c(aVar);
        q2Var.f(this);
    }

    @Override // defpackage.hk1
    public final void G0() {
        b(this.f.K());
    }

    public final void a(int i, String str, String str2) {
        this.h = new b(i, str, str2);
        Iterator<Runnable> it = this.i.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((Runnable) aVar.next()).run();
            }
        }
    }

    public final void b(String str) {
        il3 il3Var = this.g;
        s62<Boolean> s62VarD = il3Var.d(str);
        String strA = il3Var.a(str);
        if (strA == null) {
            return;
        }
        if (s62VarD.d()) {
            a(s62VarD.b.booleanValue() ? 1 : 2, strA, str);
        } else {
            a(0, strA, str);
            s62VarD.g(new n62(this, 4, strA));
        }
    }
}
