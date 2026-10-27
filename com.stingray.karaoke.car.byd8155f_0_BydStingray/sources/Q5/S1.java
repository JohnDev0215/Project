package Q5;

import D.C0138u;
import T.C0761q;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.kca.model.KaraokeSongResponseV2;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class S1 implements InterfaceC2786o {
    public final /* synthetic */ List l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f6537m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ LoadingInterface$LoadingState f6538n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ KaraokeSongResponseV2 f6539p;

    public S1(List list, InterfaceC2785n interfaceC2785n, LoadingInterface$LoadingState loadingInterface$LoadingState, InterfaceC2772a interfaceC2772a, KaraokeSongResponseV2 karaokeSongResponseV2) {
        this.l = list;
        this.f6537m = interfaceC2785n;
        this.f6538n = loadingInterface$LoadingState;
        this.o = interfaceC2772a;
        this.f6539p = karaokeSongResponseV2;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws XmlPullParserException, IOException {
        C0138u c0138u = (C0138u) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(c0138u, "$this$BoxWithConstraints");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(c0138u) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            int iM = h6.f.m(c0138u.b(), 0.0f, c0761q, 2);
            g0.l lVar = g0.l.f14684a;
            KaraokeSongResponseV2 karaokeSongResponseV2 = this.f6539p;
            io.sentry.config.a.w(lVar, this.l, iM, this.f6537m, this.f6538n, this.o, karaokeSongResponseV2 != null ? karaokeSongResponseV2.f13136a : null, c0761q, 6);
        }
        return m7.q.f19267a;
    }
}
