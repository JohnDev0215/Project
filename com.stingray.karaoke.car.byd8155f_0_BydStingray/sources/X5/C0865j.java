package X5;

import S8.InterfaceC0725w;
import android.graphics.Color;
import android.graphics.Typeface;
import com.stingray.karaoke.car.player.LeadVocalState;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: X5.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0865j extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ t6.h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f10271m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f10272n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ T.X f10273p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ T.X f10274q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ LeadVocalState f10275r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0865j(t6.h hVar, String str, String str2, int i7, T.X x8, T.X x9, LeadVocalState leadVocalState, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = hVar;
        this.f10271m = str;
        this.f10272n = str2;
        this.o = i7;
        this.f10273p = x8;
        this.f10274q = x9;
        this.f10275r = leadVocalState;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0865j(this.l, this.f10271m, this.f10272n, this.o, this.f10273p, this.f10274q, this.f10275r, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C0865j c0865j = (C0865j) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0865j.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        t6.h hVar = this.l;
        if (hVar != null) {
            T.X x8 = this.f10273p;
            y6.f fVar = (y6.f) x8.getValue();
            String str = this.f10271m;
            String str2 = this.f10272n;
            if (fVar != null && str != null && str2 != null) {
                int color = Color.parseColor(str);
                int color2 = Color.parseColor(str2);
                Typeface typeface = fVar.f24171P;
                A7.m.e(typeface, "typeface");
                fVar.f24159D = fVar.l(color, color2, typeface);
            }
            y6.f fVar2 = (y6.f) x8.getValue();
            if (fVar2 != null) {
                fVar2.n(hVar);
            }
            T.X x9 = this.f10274q;
            y6.f fVar3 = (y6.f) x9.getValue();
            if (fVar3 != null && str != null && str2 != null) {
                int color3 = Color.parseColor(str);
                int color4 = Color.parseColor(str2);
                Typeface typeface2 = fVar3.f24171P;
                A7.m.e(typeface2, "typeface");
                fVar3.f24159D = fVar3.l(color3, color4, typeface2);
            }
            y6.f fVar4 = (y6.f) x9.getValue();
            if (fVar4 != null) {
                fVar4.n(hVar);
            }
            l9.l.z(this.f10275r, hVar, x8, x9, this.o);
        }
        return m7.q.f19267a;
    }
}
