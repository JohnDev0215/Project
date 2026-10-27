package G6;

import D.L;
import Q5.R2;
import T.C0735d;
import T.C0761q;
import a.AbstractC0904a;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.kca.model.KaraokeItem;
import g0.o;
import java.io.IOException;
import l9.l;
import m7.q;
import n3.C1930E;
import org.xmlpull.v1.XmlPullParserException;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2692m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f2693n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f2694p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f2695q;

    public /* synthetic */ c(Object obj, Object obj2, Object obj3, Object obj4, int i7, int i10) {
        this.l = i10;
        this.f2692m = obj;
        this.o = obj2;
        this.f2694p = obj3;
        this.f2695q = obj4;
        this.f2693n = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws XmlPullParserException, IOException {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f2693n | 1);
                b bVar = (b) this.f2694p;
                InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f2695q;
                l.o((o) this.f2692m, (String) this.o, bVar, interfaceC2782k, (C0761q) obj, iZ);
                break;
            case 1:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f2693n | 1);
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.f2694p;
                L l = (L) this.f2695q;
                AbstractC2673a.m((o) this.f2692m, (LoadingInterface$LoadingState) this.o, interfaceC2772a, l, (C0761q) obj, iZ2);
                break;
            case 2:
                ((Integer) obj2).getClass();
                int iZ3 = C0735d.Z(this.f2693n | 1);
                KaraokeItem karaokeItem = (KaraokeItem) this.o;
                InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) this.f2694p;
                InterfaceC2772a interfaceC2772a3 = (InterfaceC2772a) this.f2695q;
                com.bumptech.glide.c.q((o) this.f2692m, karaokeItem, interfaceC2772a2, interfaceC2772a3, (C0761q) obj, iZ3);
                break;
            case 3:
                ((Integer) obj2).getClass();
                int iZ4 = C0735d.Z(this.f2693n | 1);
                R2 r22 = (R2) this.f2694p;
                InterfaceC2772a interfaceC2772a4 = (InterfaceC2772a) this.f2695q;
                com.bumptech.glide.c.u((o) this.f2692m, (String) this.o, r22, interfaceC2772a4, (C0761q) obj, iZ4);
                break;
            case 4:
                ((Integer) obj2).getClass();
                int iZ5 = C0735d.Z(this.f2693n | 1);
                String str = (String) this.f2694p;
                InterfaceC2772a interfaceC2772a5 = (InterfaceC2772a) this.f2695q;
                io.sentry.config.a.j((String) this.o, (String) this.f2692m, str, interfaceC2772a5, (C0761q) obj, iZ5);
                break;
            case 5:
                ((Integer) obj2).intValue();
                l9.d.l((String) this.o, (InterfaceC2772a) this.f2692m, (InterfaceC2772a) this.f2694p, (InterfaceC2772a) this.f2695q, (C0761q) obj, C0735d.Z(this.f2693n | 1));
                break;
            case 6:
                ((Integer) obj2).intValue();
                l.f((o) this.f2692m, (String) this.o, (InterfaceC2772a) this.f2694p, (L) this.f2695q, (C0761q) obj, C0735d.Z(this.f2693n | 1));
                break;
            case 7:
                ((Integer) obj2).intValue();
                w0.c.i((String) this.o, (String) this.f2692m, (LoadingInterface$LoadingState) this.f2694p, (InterfaceC2772a) this.f2695q, (C0761q) obj, C0735d.Z(this.f2693n | 1));
                break;
            default:
                ((Integer) obj2).intValue();
                AbstractC0904a.k((PlayerStatus) this.f2692m, (InterfaceC2772a) this.o, (C1930E) this.f2694p, (InterfaceC2772a) this.f2695q, (C0761q) obj, C0735d.Z(this.f2693n | 1));
                break;
        }
        return q.f19267a;
    }

    public /* synthetic */ c(String str, Object obj, Object obj2, InterfaceC2772a interfaceC2772a, int i7, int i10) {
        this.l = i10;
        this.o = str;
        this.f2692m = obj;
        this.f2694p = obj2;
        this.f2695q = interfaceC2772a;
        this.f2693n = i7;
    }
}
