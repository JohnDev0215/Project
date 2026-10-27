package X5;

import S8.InterfaceC0725w;
import android.os.Bundle;
import com.stingray.karaoke.car.ui.player.PlayerSideMenuRoute;
import h8.AbstractC1386c;
import java.net.URLDecoder;
import java.util.Iterator;
import n3.C1945j;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class B extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ T.X l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D0 f10092m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ T.X f10093n;
    public final /* synthetic */ T.X o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B(T.X x8, D0 d02, T.X x9, T.X x10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = x8;
        this.f10092m = d02;
        this.f10093n = x9;
        this.o = x10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new B(this.l, this.f10092m, this.f10093n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        B b10 = (B) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        b10.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object next;
        Bundle bundleC;
        String string;
        C1945j c1945j;
        Bundle bundleC2;
        String string2;
        n3.y yVar;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        N n10 = PlayerSideMenuRoute.Companion;
        T.X x8 = this.l;
        C1945j c1945j2 = (C1945j) x8.getValue();
        String str = (c1945j2 == null || (yVar = c1945j2.f19865m) == null) ? null : yVar.f19918r;
        n10.getClass();
        String strY0 = str != null ? Q8.h.Y0(str, "player/", str) : null;
        String strG = M5.b.g("player/", strY0 != null ? Q8.h.b1(strY0, "/", strY0) : null);
        Iterator<E> it = PlayerSideMenuRoute.getEntries().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (A7.m.a(((PlayerSideMenuRoute) next).getValue(), strG)) {
                break;
            }
        }
        PlayerSideMenuRoute playerSideMenuRoute = (PlayerSideMenuRoute) next;
        if (playerSideMenuRoute != null) {
            T.X x9 = this.f10093n;
            x9.setValue(playerSideMenuRoute);
            int i7 = A.f10089a[((PlayerSideMenuRoute) x9.getValue()).ordinal()];
            D0 d02 = this.f10092m;
            if (i7 == 1 || i7 == 2) {
                String strName = playerSideMenuRoute.name();
                d02.getClass();
                A7.m.f(strName, "id");
                d02.f10156w.a(Q8.h.b1(strName, "?", strName), null);
            } else if ((i7 == 3 || i7 == 4 || i7 == 5) && (c1945j = (C1945j) x8.getValue()) != null && (bundleC2 = c1945j.c()) != null && (string2 = bundleC2.getString("id")) != null) {
                String strDecode = URLDecoder.decode(string2, "utf-8");
                String str2 = ((PlayerSideMenuRoute) x9.getValue()) + "/" + strDecode;
                d02.getClass();
                A7.m.f(str2, "id");
                d02.f10156w.a(Q8.h.b1(str2, "?", str2), null);
            }
            C1945j c1945j3 = (C1945j) x8.getValue();
            if (c1945j3 != null && (bundleC = c1945j3.c()) != null && (string = bundleC.getString("title")) != null) {
                this.o.setValue(URLDecoder.decode(string, "utf-8"));
            }
        }
        return m7.q.f19267a;
    }
}
