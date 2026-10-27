package Q5;

import T.C0735d;
import T.C0761q;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.kca.model.KaraokeSongResponseV2;
import java.io.IOException;
import n3.C1930E;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.y0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0641y0 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7144m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f7145n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f7146p;

    public /* synthetic */ C0641y0(int i7, int i10, Object obj, Object obj2, Object obj3) {
        this.l = i10;
        this.f7144m = obj;
        this.o = obj2;
        this.f7146p = obj3;
        this.f7145n = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws XmlPullParserException, IOException {
        C0761q c0761q = (C0761q) obj;
        Integer num = (Integer) obj2;
        switch (this.l) {
            case 0:
                num.getClass();
                int iZ = C0735d.Z(this.f7145n | 1);
                io.sentry.config.a.g((g0.o) this.f7144m, (V8.N) this.o, (InterfaceC2785n) this.f7146p, c0761q, iZ);
                break;
            case 1:
                num.getClass();
                int iZ2 = C0735d.Z(this.f7145n | 1);
                w0.c.t((g0.o) this.f7144m, (String) this.o, (N0.L) this.f7146p, c0761q, iZ2);
                break;
            case 2:
                num.intValue();
                l9.l.v((PlayerStatus) this.f7144m, (X5.H0) this.o, (C1930E) this.f7146p, c0761q, C0735d.Z(this.f7145n | 1));
                break;
            case 3:
                num.getClass();
                int iZ3 = C0735d.Z(this.f7145n | 1);
                C7.a.c((g0.o) this.f7144m, (KaraokeItem) this.o, (InterfaceC2782k) this.f7146p, c0761q, iZ3);
                break;
            default:
                num.getClass();
                int iZ4 = C0735d.Z(this.f7145n | 1);
                com.bumptech.glide.c.j((g0.o) this.f7144m, (KaraokeSongResponseV2) this.o, (InterfaceC2772a) this.f7146p, c0761q, iZ4);
                break;
        }
        return m7.q.f19267a;
    }
}
