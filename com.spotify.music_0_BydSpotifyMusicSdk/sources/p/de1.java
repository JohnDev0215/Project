package p;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class de1 {
    public static final be1 a;

    static {
        String property;
        Object next;
        int i = pi2.a;
        be1 be1VarB = null;
        try {
            property = System.getProperty("kotlinx.coroutines.fast.service.loader");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property != null) {
            Boolean.parseBoolean(property);
        }
        List listJ0 = kotlin.sequences.a.J0(nb2.H0(u4.y()));
        Iterator it = listJ0.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                int iC = ((ce1) next).c();
                do {
                    Object next2 = it.next();
                    int iC2 = ((ce1) next2).c();
                    if (iC < iC2) {
                        next = next2;
                        iC = iC2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        ce1 ce1Var = (ce1) next;
        if (ce1Var != null) {
            try {
                be1VarB = ce1Var.b(listJ0);
            } catch (Throwable unused2) {
                ce1Var.a();
            }
            if (be1VarB != null) {
                a = be1VarB;
                return;
            }
        }
        throw new IllegalStateException("Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'");
    }
}
