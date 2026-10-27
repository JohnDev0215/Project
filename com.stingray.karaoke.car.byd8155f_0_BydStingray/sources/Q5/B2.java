package Q5;

import T.C0732b0;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.shared.logs.segment.songQueue.SourceType;
import java.util.List;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class B2 implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ m7.c f6281m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6282n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f6283p;

    public /* synthetic */ B2(int i7, InterfaceC2782k interfaceC2782k, C0732b0 c0732b0, T.X x8) {
        this.l = 2;
        this.o = i7;
        this.f6281m = interfaceC2782k;
        this.f6282n = c0732b0;
        this.f6283p = x8;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                Object obj = ((List) this.f6282n).get(this.o);
                String str = ((KaraokeItem) this.f6283p).f13107a;
                ((InterfaceC2785n) this.f6281m).invoke(obj, str != null ? new F6.b(SourceType.SWIMLANE, str) : null);
                break;
            case 1:
                Object obj2 = ((List) this.f6282n).get(this.o);
                String str2 = ((KaraokeItem) this.f6283p).f13107a;
                ((InterfaceC2785n) this.f6281m).invoke(obj2, str2 != null ? new F6.b(SourceType.SWIMLANE, str2) : null);
                break;
            default:
                C0732b0 c0732b0 = (C0732b0) this.f6282n;
                c0732b0.j(this.o);
                ((InterfaceC2782k) this.f6281m).b(Integer.valueOf(c0732b0.i()));
                ((T.X) this.f6283p).setValue(Boolean.FALSE);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ B2(InterfaceC2785n interfaceC2785n, List list, int i7, KaraokeItem karaokeItem, int i10) {
        this.l = i10;
        this.f6281m = interfaceC2785n;
        this.f6282n = list;
        this.o = i7;
        this.f6283p = karaokeItem;
    }
}
