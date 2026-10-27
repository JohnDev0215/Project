package Q5;

import com.stingray.karaoke.scoring.utils.ScoringSoundFxType;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.t1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0622t1 implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f7073m;

    public /* synthetic */ C0622t1(InterfaceC2785n interfaceC2785n, int i7) {
        this.l = i7;
        this.f7073m = interfaceC2785n;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                String str = (String) obj;
                A7.m.f(str, "it");
                this.f7073m.invoke(str, 1500L);
                return m7.q.f19267a;
            default:
                return Integer.valueOf(((Number) this.f7073m.invoke((ScoringSoundFxType) obj, null)).intValue());
        }
    }
}
