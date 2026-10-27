package Q5;

import T.C0753m;
import T.C0761q;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.player.PlaybackType;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2772a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class E0 implements InterfaceC2786o {
    public final /* synthetic */ V8.N l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f6321m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f6322n;
    public final /* synthetic */ X5.D0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ T.X f6323p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ T.X f6324q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ T.X f6325r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ T.X f6326s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ T.X f6327t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ T.X f6328u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ T.X f6329v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ T.X f6330w;

    public E0(V8.N n10, boolean z6, InterfaceC2772a interfaceC2772a, X5.D0 d02, T.X x8, T.X x9, T.X x10, T.X x11, T.X x12, T.X x13, T.X x14, T.X x15) {
        this.l = n10;
        this.f6321m = z6;
        this.f6322n = interfaceC2772a;
        this.o = d02;
        this.f6323p = x8;
        this.f6324q = x9;
        this.f6325r = x10;
        this.f6326s = x11;
        this.f6327t = x12;
        this.f6328u = x13;
        this.f6329v = x14;
        this.f6330w = x15;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws XmlPullParserException, IOException {
        InterfaceC2772a interfaceC2772a;
        C0761q c0761q = (C0761q) obj2;
        ((Number) obj3).intValue();
        A7.m.f((w.q) obj, "$this$AnimatedVisibility");
        String str = (String) this.f6327t.getValue();
        String str2 = (String) this.f6328u.getValue();
        String str3 = (String) this.f6329v.getValue();
        T.X x8 = this.f6325r;
        PlayerStatus playerStatus = (PlayerStatus) x8.getValue();
        boolean zBooleanValue = ((Boolean) this.f6330w.getValue()).booleanValue();
        c0761q.Q(-237834425);
        X5.D0 d02 = this.o;
        boolean zH = c0761q.h(d02);
        Object objG = c0761q.G();
        T.S s6 = C0753m.f8270a;
        if (zH || objG == s6) {
            objG = new C0(d02, 0);
            c0761q.a0(objG);
        }
        InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) objG;
        c0761q.p(false);
        c0761q.Q(-237831294);
        boolean zH2 = c0761q.h(d02);
        T.X x9 = this.f6323p;
        boolean zF = c0761q.f(x8) | zH2 | c0761q.f(x9) | c0761q.f(this.f6324q) | c0761q.f(this.f6326s);
        Object objG2 = c0761q.G();
        if (zF || objG2 == s6) {
            final T.X x10 = this.f6323p;
            final T.X x11 = this.f6324q;
            final X5.D0 d03 = this.o;
            final T.X x12 = this.f6325r;
            interfaceC2772a = interfaceC2772a2;
            final T.X x13 = this.f6326s;
            objG2 = new InterfaceC2772a() { // from class: Q5.D0
                @Override // z7.InterfaceC2772a
                public final Object a() {
                    PlaybackType playbackType = PlaybackType.PLAY_PAUSE;
                    X5.D0 d04 = d03;
                    d04.r(playbackType);
                    if (((Boolean) x10.getValue()).booleanValue() && ((Boolean) x11.getValue()).booleanValue()) {
                        T.X x14 = x12;
                        if (((PlayerStatus) x14.getValue()) == PlayerStatus.PAUSED || ((PlayerStatus) x14.getValue()) == PlayerStatus.STOPPED) {
                            d04.h();
                            if (((Boolean) x13.getValue()).booleanValue()) {
                                d04.q();
                            }
                        }
                    }
                    return m7.q.f19267a;
                }
            };
            c0761q.a0(objG2);
        } else {
            interfaceC2772a = interfaceC2772a2;
        }
        InterfaceC2772a interfaceC2772a3 = (InterfaceC2772a) objG2;
        c0761q.p(false);
        c0761q.Q(-237817889);
        boolean zH3 = c0761q.h(d02);
        Object objG3 = c0761q.G();
        if (zH3 || objG3 == s6) {
            objG3 = new C0(d02, 1);
            c0761q.a0(objG3);
        }
        c0761q.p(false);
        boolean zBooleanValue2 = ((Boolean) x9.getValue()).booleanValue();
        InterfaceC2772a interfaceC2772a4 = this.f6322n;
        l9.d.m(null, str, str2, str3, playerStatus, this.l, zBooleanValue, this.f6321m, interfaceC2772a4, interfaceC2772a, interfaceC2772a3, (InterfaceC2772a) objG3, zBooleanValue2, c0761q, 0, 0);
        return m7.q.f19267a;
    }
}
