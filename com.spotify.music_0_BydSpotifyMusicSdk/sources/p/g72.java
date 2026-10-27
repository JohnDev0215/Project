package p;

import androidx.work.impl.WorkDatabase;
import com.spotify.automotive.ubi.Referrer;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g72 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Object l;
    public final /* synthetic */ Object m;

    public /* synthetic */ g72(Object obj, Object obj2, Object obj3, Object obj4, int i) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
        this.l = obj3;
        this.m = obj4;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        Referrer referrer;
        int i = this.c;
        int i2 = 0;
        Object obj = this.m;
        Object obj2 = this.l;
        Object obj3 = this.k;
        Object obj4 = this.e;
        switch (i) {
            case 0:
                List list = (List) obj4;
                rx2 rx2Var = (rx2) obj3;
                sw swVar = (sw) obj2;
                WorkDatabase workDatabase = (WorkDatabase) obj;
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((b72) it.next()).a(rx2Var.a);
                }
                h72.b(swVar, workDatabase, list);
                break;
            case 1:
                t70 t70Var = (t70) obj4;
                wi wiVar = (wi) obj3;
                k32 k32Var = (k32) obj2;
                ai aiVar = (ai) obj;
                Logger logger = t70.f;
                t70Var.getClass();
                Logger logger2 = t70.f;
                try {
                    eo2 eo2VarA = t70Var.c.a(wiVar.a);
                    if (eo2VarA == null) {
                        String str = String.format("Transport backend '%s' is not registered", wiVar.a);
                        logger2.warning(str);
                        k32Var.a(new IllegalArgumentException(str));
                    } else {
                        ((j62) t70Var.e).J(new r70(t70Var, wiVar, ((oq) eo2VarA).a(aiVar), i2));
                        k32Var.a(null);
                    }
                } catch (Exception e) {
                    logger2.warning("Error scheduling event " + e.getMessage());
                    k32Var.a(e);
                    return;
                }
                break;
            case 2:
                oj ojVar = (oj) obj4;
                String str2 = (String) obj3;
                Integer num = (Integer) obj2;
                String str3 = (String) obj;
                ojVar.getClass();
                Referrer[] referrerArrValues = Referrer.values();
                int length = referrerArrValues.length;
                while (true) {
                    if (i2 < length) {
                        referrer = referrerArrValues[i2];
                        if (!v03.b(referrer.a(), str3)) {
                            i2++;
                        }
                    } else {
                        referrer = null;
                    }
                }
                ojVar.g.d(new rp2(str2, num, null, referrer));
                ((z1) ojVar.f).s(null, str2, null, str3);
                break;
            default:
                ((pd1) obj4).a.h((String) obj3, (String) obj2, (String) obj);
                break;
        }
    }
}
