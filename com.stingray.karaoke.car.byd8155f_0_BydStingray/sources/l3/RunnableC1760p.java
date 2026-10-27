package l3;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import h2.AbstractC1347d;

/* JADX INFO: renamed from: l3.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1760p implements Runnable {
    public final /* synthetic */ C1764u l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f18592m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f18593n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Bundle f18594p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C1764u f18595q;

    public RunnableC1760p(int i7, int i10, Bundle bundle, String str, C1764u c1764u, C1764u c1764u2) {
        this.f18595q = c1764u;
        this.l = c1764u2;
        this.f18592m = str;
        this.f18593n = i7;
        this.o = i10;
        this.f18594p = bundle;
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
        C1764u c1764u = this.l;
        IBinder binder = ((Messenger) c1764u.f18608m).getBinder();
        C1764u c1764u2 = this.f18595q;
        ((AbstractServiceC1765v) c1764u2.f18608m).f18612p.remove(binder);
        String str = this.f18592m;
        C1756l c1756l = new C1756l((AbstractServiceC1765v) c1764u2.f18608m, str, this.f18593n, this.o, c1764u);
        AbstractServiceC1765v abstractServiceC1765v = (AbstractServiceC1765v) c1764u2.f18608m;
        abstractServiceC1765v.f18613q = c1756l;
        C1755k c1755kA = abstractServiceC1765v.a(this.f18594p);
        abstractServiceC1765v.f18613q = null;
        if (c1755kA == null) {
            StringBuilder sbJ = AbstractC1347d.j("No root for client ", str, " from service ");
            sbJ.append(RunnableC1760p.class.getName());
            Log.i("MBServiceCompat", sbJ.toString());
            try {
                c1764u.V(2, null);
                return;
            } catch (RemoteException unused) {
                h6.n.t("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=".concat(str));
                return;
            }
        }
        try {
            abstractServiceC1765v.f18612p.put(binder, c1756l);
            binder.linkToDeath(c1756l, 0);
            O o = abstractServiceC1765v.f18615s;
            if (o != null) {
                c1764u.Q(o, c1755kA.f18579a);
            }
        } catch (RemoteException unused2) {
            h6.n.t("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=".concat(str));
            abstractServiceC1765v.f18612p.remove(binder);
        }
    }
}
