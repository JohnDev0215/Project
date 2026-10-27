package n2;

import android.content.Context;
import android.os.Handler;
import h2.AbstractC1346c;
import java.util.ArrayList;
import r2.C2286h;
import r2.InterfaceC2281c;
import t2.C2433b;
import v2.C2546d;
import z2.C2759e;

/* JADX INFO: renamed from: n2.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1912l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f19685a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final P6.a f19686b;

    public C1912l(Context context) {
        this.f19685a = context;
        this.f19686b = new P6.a(context, 12, false);
    }

    public final AbstractC1905e[] a(Handler handler, SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u, SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u2, SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u3, SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u4) {
        ArrayList arrayList = new ArrayList();
        P6.a aVar = this.f19686b;
        Context context = this.f19685a;
        arrayList.add(new C2759e(context, aVar, handler, surfaceHolderCallbackC1920u));
        p2.q qVar = new p2.q(context);
        AbstractC1346c.j(!qVar.f20759b);
        qVar.f20759b = true;
        if (((h3.y) qVar.d) == null) {
            qVar.d = new h3.y(new f2.d[0]);
        }
        if (((m2.b) qVar.f20762g) == null) {
            qVar.f20762g = new m2.b(context);
        }
        arrayList.add(new p2.x(this.f19685a, aVar, handler, surfaceHolderCallbackC1920u2, new p2.v(qVar)));
        arrayList.add(new C2546d(surfaceHolderCallbackC1920u3, handler.getLooper()));
        arrayList.add(new C2433b(surfaceHolderCallbackC1920u4, handler.getLooper()));
        arrayList.add(new A2.b());
        arrayList.add(new C2286h(InterfaceC2281c.f21784i));
        return (AbstractC1905e[]) arrayList.toArray(new AbstractC1905e[0]);
    }
}
