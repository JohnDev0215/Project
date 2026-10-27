package Q5;

import F0.C0221p;
import com.stingray.karaoke.car.player.SongQueuePosition;
import com.stingray.karaoke.scoring.utils.ScoringSoundFxType;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.kca.model.KaraokeItemType;
import i6.C1415d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: Q5.m1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0595m1 implements InterfaceC2772a {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f6958m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6959n;
    public final /* synthetic */ Object o;

    public /* synthetic */ C0595m1(C1415d c1415d, boolean z6, F6.b bVar) {
        this.f6959n = c1415d;
        this.f6958m = z6;
        this.o = bVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                C1415d c1415d = (C1415d) this.f6959n;
                String str = (String) c1415d.f12276z.getValue();
                String str2 = (String) c1415d.f15764L.getValue();
                boolean zBooleanValue = ((Boolean) c1415d.f15766N.getValue()).booleanValue();
                boolean zBooleanValue2 = ((Boolean) c1415d.f15765M.getValue()).booleanValue();
                V8.N n10 = c1415d.f12274x;
                int size = ((List) ((V8.f0) n10.l).getValue()).size();
                C0603o1 c0603o1 = new C0603o1(c1415d, (F6.b) this.o);
                N5.q0 q0Var = c1415d.f15760H;
                int size2 = ((List) q0Var.f4752v.getValue()).size();
                List list = (List) ((V8.f0) n10.l).getValue();
                A7.m.f(list, "items");
                ArrayList arrayList = new ArrayList();
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        boolean z6 = !arrayList.isEmpty() && ((List) q0Var.f4752v.getValue()).size() <= 0;
                        C0221p c0221p = new C0221p(0, c1415d, C1415d.class, "openPlayer", "openPlayer()V", 0, 13);
                        if (z6) {
                            c0603o1.invoke(size2 > 0 ? SongQueuePosition.SING_LAST : SongQueuePosition.SING_NOW, Boolean.FALSE);
                            c0221p.a();
                        } else {
                            n7.z.b(null, this.f6958m, new b0.b(-779595704, new C0587k1(str, str2, zBooleanValue, zBooleanValue2, size, size2, c0603o1, c0221p), true), 7);
                        }
                        break;
                    } else {
                        KaraokeItem karaokeItem = (KaraokeItem) it.next();
                        KaraokeItem karaokeItem2 = karaokeItem.f13109e == KaraokeItemType.PLAY ? karaokeItem : null;
                        if (karaokeItem2 != null) {
                            arrayList.add(karaokeItem2);
                        }
                    }
                }
                break;
            default:
                if (this.f6958m) {
                    T.X x8 = (T.X) this.o;
                    float fFloatValue = ((Number) x8.getValue()).floatValue();
                    InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f6959n;
                    if (fFloatValue <= 1.0f) {
                        interfaceC2782k.b(ScoringSoundFxType.RS_REACTION_L1);
                    } else if (((Number) x8.getValue()).floatValue() <= 2.0f || ((Number) x8.getValue()).floatValue() <= 3.0f) {
                        interfaceC2782k.b(ScoringSoundFxType.RS_REACTION_L3);
                    } else if (((Number) x8.getValue()).floatValue() <= 4.0f) {
                        interfaceC2782k.b(ScoringSoundFxType.RS_REACTION_L4);
                    } else if (((Number) x8.getValue()).floatValue() <= 5.0f) {
                        interfaceC2782k.b(ScoringSoundFxType.RS_REACTION_L5);
                    }
                }
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ C0595m1(boolean z6, InterfaceC2782k interfaceC2782k, T.X x8) {
        this.f6958m = z6;
        this.f6959n = interfaceC2782k;
        this.o = x8;
    }
}
