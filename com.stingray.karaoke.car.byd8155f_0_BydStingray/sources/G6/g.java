package G6;

import S8.InterfaceC0725w;
import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.l;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f2701m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(Context context, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = context;
        this.f2701m = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new g(this.l, this.f2701m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        try {
            Context context = this.l;
            A4.h.c(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
            l lVarB = com.bumptech.glide.b.a(context).f12668p.b(context);
            lVarB.getClass();
            com.bumptech.glide.j jVarX = new com.bumptech.glide.j(lVarB.l, lVarB, Bitmap.class, lVarB.f12711m).a(l.f12710t).x(this.f2701m);
            jVarX.getClass();
            w4.e eVar = new w4.e();
            jVarX.w(eVar, eVar, jVarX, A4.h.f420b);
            return (Bitmap) eVar.get();
        } catch (Exception unused) {
            return null;
        }
    }
}
