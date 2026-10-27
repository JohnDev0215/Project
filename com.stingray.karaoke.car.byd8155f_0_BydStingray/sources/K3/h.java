package K3;

import A7.D;
import S8.AbstractC0721s;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import androidx.lifecycle.A;
import g1.AbstractC1274a;
import java.util.LinkedHashMap;
import n7.u;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3529a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f3530b;
    public Object c;
    public A3.k d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public L3.d f3531e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u f3532f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public M3.e f3533g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final D f3534h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final LinkedHashMap f3535i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f3536j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f3537k;
    public final n l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public L3.h f3538m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public L3.f f3539n;
    public A9.b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public L3.h f3540p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public L3.f f3541q;

    public h(Context context) {
        this.f3529a = context;
        this.f3530b = N3.c.f4574a;
        this.c = null;
        this.d = null;
        this.f3531e = null;
        this.f3532f = u.l;
        this.f3533g = null;
        this.f3534h = null;
        this.f3535i = null;
        this.f3536j = true;
        this.f3537k = true;
        this.l = null;
        this.f3538m = null;
        this.f3539n = null;
        this.o = null;
        this.f3540p = null;
        this.f3541q = null;
    }

    public final i a() {
        AbstractC0721s abstractC0721s;
        L3.h hVar;
        o oVar;
        L3.f fVar;
        Object obj = this.c;
        if (obj == null) {
            obj = k.f3564a;
        }
        Object obj2 = obj;
        A3.k kVar = this.d;
        c cVar = this.f3530b;
        Bitmap.Config config = cVar.f3517g;
        L3.d dVar = this.f3531e;
        if (dVar == null) {
            dVar = cVar.f3516f;
        }
        L3.d dVar2 = dVar;
        M3.e eVar = this.f3533g;
        M3.e eVar2 = eVar == null ? cVar.f3515e : eVar;
        D d = this.f3534h;
        c9.u uVarM = d != null ? d.m() : null;
        if (uVarM == null) {
            uVarM = N3.d.f4576b;
        } else {
            Bitmap.Config config2 = N3.d.f4575a;
        }
        c9.u uVar = uVarM;
        LinkedHashMap linkedHashMap = this.f3535i;
        q qVar = linkedHashMap != null ? new q(AbstractC1274a.e0(linkedHashMap)) : null;
        q qVar2 = qVar == null ? q.f3583b : qVar;
        c cVar2 = this.f3530b;
        boolean z6 = cVar2.f3518h;
        boolean z10 = cVar2.f3519i;
        b bVar = cVar2.f3522m;
        b bVar2 = cVar2.f3523n;
        b bVar3 = cVar2.o;
        AbstractC0721s abstractC0721s2 = cVar2.f3513a;
        AbstractC0721s abstractC0721s3 = cVar2.f3514b;
        AbstractC0721s abstractC0721s4 = cVar2.c;
        AbstractC0721s abstractC0721s5 = cVar2.d;
        A9.b bVarG = this.o;
        Context context = this.f3529a;
        if (bVarG == null) {
            Object baseContext = context;
            abstractC0721s = abstractC0721s2;
            while (true) {
                if (baseContext instanceof A) {
                    bVarG = ((A) baseContext).g();
                    break;
                }
                if (!(baseContext instanceof ContextWrapper)) {
                    bVarG = null;
                    break;
                }
                baseContext = ((ContextWrapper) baseContext).getBaseContext();
            }
            if (bVarG == null) {
                bVarG = g.f3528n;
            }
        } else {
            abstractC0721s = abstractC0721s2;
        }
        A9.b bVar4 = bVarG;
        L3.h hVar2 = this.f3538m;
        if (hVar2 == null) {
            L3.h cVar3 = this.f3540p;
            if (cVar3 == null) {
                cVar3 = new L3.c(context);
            }
            hVar = cVar3;
        } else {
            hVar = hVar2;
        }
        L3.f fVar2 = this.f3539n;
        if (fVar2 == null && (fVar2 = this.f3541q) == null) {
            if ((hVar2 instanceof L3.i ? (L3.i) hVar2 : null) != null) {
                throw null;
            }
            fVar = L3.f.f4003m;
            oVar = null;
        } else {
            oVar = null;
            fVar = fVar2;
        }
        n nVar = this.l;
        if (nVar != null) {
            oVar = new o(AbstractC1274a.e0(nVar.f3576a));
        }
        if (oVar == null) {
            oVar = o.f3577m;
        }
        return new i(this.f3529a, obj2, kVar, config, dVar2, this.f3532f, eVar2, uVar, qVar2, this.f3536j, z6, z10, this.f3537k, bVar, bVar2, bVar3, abstractC0721s, abstractC0721s3, abstractC0721s4, abstractC0721s5, bVar4, hVar, fVar, oVar, new d(this.f3538m, this.f3539n, this.f3533g, this.f3531e), this.f3530b);
    }

    public h(i iVar, Context context) {
        this.f3529a = context;
        this.f3530b = iVar.f3563z;
        this.c = iVar.f3543b;
        this.d = iVar.c;
        d dVar = iVar.f3562y;
        this.f3531e = dVar.d;
        this.f3532f = iVar.f3545f;
        this.f3533g = dVar.c;
        this.f3534h = iVar.f3547h.q();
        this.f3535i = z.z(iVar.f3548i.f3584a);
        this.f3536j = iVar.f3549j;
        this.f3537k = iVar.f3551m;
        this.l = new n(iVar.f3561x);
        this.f3538m = dVar.f3524a;
        this.f3539n = dVar.f3525b;
        if (iVar.f3542a == context) {
            this.o = iVar.f3558u;
            this.f3540p = iVar.f3559v;
            this.f3541q = iVar.f3560w;
        } else {
            this.o = null;
            this.f3540p = null;
            this.f3541q = null;
        }
    }
}
