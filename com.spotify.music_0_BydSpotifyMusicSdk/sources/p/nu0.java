package p;

import android.content.Context;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class nu0 implements yy1 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ nu0(Context context, String str) {
        this.a = 2;
        this.c = context;
        this.b = str;
    }

    @Override // p.yy1
    public final Object get() {
        int i = this.a;
        Object obj = this.b;
        Object obj2 = this.c;
        switch (i) {
            case 0:
                ru0 ru0Var = (ru0) obj;
                String strC = ru0Var.c();
                return new y50((Context) obj2, strC);
            case 1:
                gw gwVar = (gw) obj;
                mv mvVar = (mv) obj2;
                gwVar.getClass();
                aw awVar = mvVar.f;
                jm2 jm2Var = new jm2();
                HashSet hashSet = new HashSet();
                HashSet hashSet2 = new HashSet();
                HashSet hashSet3 = new HashSet();
                HashSet hashSet4 = new HashSet();
                HashSet hashSet5 = new HashSet();
                for (d90 d90Var : mvVar.c) {
                    int i2 = d90Var.c;
                    boolean z = i2 == 0;
                    int i3 = d90Var.b;
                    dz1 dz1Var = d90Var.a;
                    if (z) {
                        if (i3 == 2) {
                            hashSet4.add(dz1Var);
                        } else {
                            hashSet.add(dz1Var);
                        }
                    } else if (i2 == 2) {
                        hashSet3.add(dz1Var);
                    } else if (i3 == 2) {
                        hashSet5.add(dz1Var);
                    } else {
                        hashSet2.add(dz1Var);
                    }
                }
                Set set = mvVar.g;
                if (!set.isEmpty()) {
                    hashSet.add(dz1.a(bz1.class));
                }
                jm2Var.a = Collections.unmodifiableSet(hashSet);
                jm2Var.b = Collections.unmodifiableSet(hashSet2);
                jm2Var.c = Collections.unmodifiableSet(hashSet3);
                jm2Var.d = Collections.unmodifiableSet(hashSet4);
                jm2Var.e = Collections.unmodifiableSet(hashSet5);
                jm2Var.f = set;
                jm2Var.g = gwVar;
                return awVar.a(jm2Var);
            default:
                return new x11((Context) obj2, (String) obj);
        }
    }

    public /* synthetic */ nu0(Object obj, int i, Object obj2) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }
}
