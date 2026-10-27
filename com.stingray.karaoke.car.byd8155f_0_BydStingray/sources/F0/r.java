package F0;

import S8.AbstractC0727y;
import a6.C0928a;
import androidx.compose.ui.platform.AndroidComposeView;
import com.stingray.karaoke.scoring.utils.ScoringSoundFxType;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.shared.logs.segment.ActionOwner;
import i6.C1415d;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import l0.C1711b;
import n7.AbstractC1998m;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r extends A7.j implements InterfaceC2785n {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f2252t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(int i7, Object obj, Class cls, String str, String str2, int i10, int i11) {
        super(i7, i10, cls, obj, str, str2);
        this.f2252t = i11;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f2252t) {
            case 0:
                return Boolean.valueOf(AndroidComposeView.e((AndroidComposeView) this.f455m, (C1711b) obj, (m0.d) obj2));
            case 1:
                ((C0928a) this.f455m).k(((Number) obj).intValue(), ((Number) obj2).intValue());
                return m7.q.f19267a;
            case 2:
                String str = (String) obj;
                long jLongValue = ((Number) obj2).longValue();
                A7.m.f(str, "p0");
                e6.g gVar = (e6.g) this.f455m;
                gVar.getClass();
                AbstractC0727y.w(androidx.lifecycle.a0.i(gVar), null, null, new e6.f(gVar, str, jLongValue, null), 3);
                return m7.q.f19267a;
            case 3:
                KaraokeItem karaokeItem = (KaraokeItem) obj;
                A7.m.f(karaokeItem, "p0");
                ((e6.g) this.f455m).n(karaokeItem, (F6.b) obj2);
                return m7.q.f19267a;
            case 4:
                ((e6.g) this.f455m).k(((Number) obj).intValue(), ((Number) obj2).intValue());
                return m7.q.f19267a;
            case 5:
                KaraokeItem karaokeItem2 = (KaraokeItem) obj;
                A7.m.f(karaokeItem2, "p0");
                ((f6.g) this.f455m).n(karaokeItem2, (F6.b) obj2);
                return m7.q.f19267a;
            case 6:
                KaraokeItem karaokeItem3 = (KaraokeItem) obj;
                A7.m.f(karaokeItem3, "p0");
                ((f6.o) this.f455m).n(karaokeItem3, (F6.b) obj2);
                return m7.q.f19267a;
            case 7:
                ((f6.p) this.f455m).k(((Number) obj).intValue(), ((Number) obj2).intValue());
                return m7.q.f19267a;
            case 8:
                KaraokeItem karaokeItem4 = (KaraokeItem) obj;
                A7.m.f(karaokeItem4, "p0");
                ((d6.j) this.f455m).n(karaokeItem4, (F6.b) obj2);
                return m7.q.f19267a;
            case 9:
                String str2 = (String) obj;
                int iIntValue = ((Number) obj2).intValue();
                X5.H0 h02 = (X5.H0) this.f455m;
                h02.getClass();
                if (str2 != null && str2.length() != 0) {
                    N5.q0 q0Var = h02.f10174m;
                    q0Var.getClass();
                    synchronized (q0Var) {
                        try {
                            if (q0Var.f4741i.isEmpty()) {
                                q0Var.f4741i.addAll(q0Var.f4740h);
                            }
                            Iterator it = q0Var.f4741i.iterator();
                            int i7 = 0;
                            while (true) {
                                if (!it.hasNext()) {
                                    i7 = -1;
                                } else if (!A7.m.a(((N5.f0) it.next()).d, str2)) {
                                    i7++;
                                }
                            }
                            int iMin = Math.min(iIntValue, q0Var.f4741i.size() - 1);
                            if (i7 >= 0) {
                                q0Var.f4741i.add(iMin, (N5.f0) q0Var.f4741i.remove(i7));
                                V8.f0 f0Var = q0Var.f4745n;
                                List listZ0 = AbstractC1998m.z0(q0Var.f4741i);
                                f0Var.getClass();
                                f0Var.m(null, listZ0);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                        break;
                    }
                }
                return m7.q.f19267a;
            case 10:
                String str3 = (String) obj;
                int iIntValue2 = ((Number) obj2).intValue();
                X5.H0 h03 = (X5.H0) this.f455m;
                h03.getClass();
                if (str3 != null && str3.length() != 0) {
                    h03.f10174m.h(str3, iIntValue2, ActionOwner.HOST);
                }
                return m7.q.f19267a;
            case 11:
                ((C1415d) this.f455m).k(((Number) obj).intValue(), ((Number) obj2).intValue());
                return m7.q.f19267a;
            default:
                ScoringSoundFxType scoringSoundFxType = (ScoringSoundFxType) obj;
                X5.D0 d02 = (X5.D0) this.f455m;
                String strName = null;
                if (scoringSoundFxType != null) {
                    d02.getClass();
                    strName = scoringSoundFxType.name();
                }
                LinkedHashMap linkedHashMap = d02.f10105D.f3035a;
                return 0;
        }
    }
}
