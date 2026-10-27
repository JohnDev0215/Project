package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class M6 implements Y6 {
    public final /* synthetic */ Object a;
    public final /* synthetic */ ArrayList b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ ArrayList d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ O6 g;

    public M6(O6 o6, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
        this.g = o6;
        this.a = obj;
        this.b = arrayList;
        this.c = obj2;
        this.d = arrayList2;
        this.e = obj3;
        this.f = arrayList3;
    }

    @Override // defpackage.Y6
    public void a(Z6 z6) {
    }

    @Override // defpackage.Y6
    public void b(Z6 z6) {
    }

    @Override // defpackage.Y6
    public void c(Z6 z6) {
        Object obj = this.a;
        if (obj != null) {
            this.g.n(obj, this.b, null);
        }
        Object obj2 = this.c;
        if (obj2 != null) {
            this.g.n(obj2, this.d, null);
        }
        Object obj3 = this.e;
        if (obj3 != null) {
            this.g.n(obj3, this.f, null);
        }
    }

    @Override // defpackage.Y6
    public void d(Z6 z6) {
    }
}
