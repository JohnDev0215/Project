package p;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class xg1 implements Runnable {
    public final /* synthetic */ int c = 1;
    public final /* synthetic */ wg1 e;
    public final /* synthetic */ int k;
    public final /* synthetic */ String l;
    public final /* synthetic */ int m;
    public final /* synthetic */ wg1 n;

    public xg1(wg1 wg1Var, wg1 wg1Var2, String str, int i, int i2, Bundle bundle) {
        this.n = wg1Var;
        this.e = wg1Var2;
        this.l = str;
        this.k = i;
        this.m = i2;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        int i2 = this.m;
        String str = this.l;
        wg1 wg1Var = this.n;
        wg1 wg1Var2 = this.e;
        switch (i) {
            case 0:
                IBinder iBinderA = wg1Var2.a();
                ((bh1) wg1Var.a).m.remove(iBinderA);
                Object obj = wg1Var.a;
                bh1 bh1Var = (bh1) obj;
                jg1 jg1Var = new jg1(bh1Var, this.l, this.k, this.m, this.e);
                bh1Var.getClass();
                jg1Var.f = bh1Var.a(str, i2);
                bh1Var.getClass();
                if (jg1Var.f == null) {
                    try {
                        wg1Var2.g(2, null);
                    } catch (RemoteException unused) {
                        return;
                    }
                } else {
                    try {
                        ((bh1) obj).m.put(iBinderA, jg1Var);
                        iBinderA.linkToDeath(jg1Var, 0);
                        if (((bh1) obj).o != null) {
                            hg1 hg1Var = jg1Var.f;
                            wg1Var2.c((String) hg1Var.a, ((bh1) obj).o, (Bundle) hg1Var.b);
                        }
                    } catch (RemoteException unused2) {
                        bh1Var.m.remove(iBinderA);
                        return;
                    }
                }
                break;
            default:
                IBinder iBinderA2 = wg1Var2.a();
                ((bh1) wg1Var.a).m.remove(iBinderA2);
                bh1 bh1Var2 = (bh1) wg1Var.a;
                Iterator it = bh1Var2.l.iterator();
                while (true) {
                    if (it.hasNext()) {
                        jg1 jg1Var2 = (jg1) it.next();
                        if (jg1Var2.c == this.k) {
                            jg1Var = (TextUtils.isEmpty(str) || i2 <= 0) ? new jg1(bh1Var2, jg1Var2.a, jg1Var2.b, jg1Var2.c, this.e) : null;
                            it.remove();
                        }
                    }
                }
                if (jg1Var == null) {
                    jg1Var = new jg1(bh1Var2, this.l, this.m, this.k, this.e);
                }
                bh1Var2.m.put(iBinderA2, jg1Var);
                try {
                    iBinderA2.linkToDeath(jg1Var, 0);
                } catch (RemoteException unused3) {
                    return;
                }
                break;
        }
    }

    public xg1(wg1 wg1Var, wg1 wg1Var2, int i, String str, int i2, Bundle bundle) {
        this.n = wg1Var;
        this.e = wg1Var2;
        this.k = i;
        this.l = str;
        this.m = i2;
    }
}
