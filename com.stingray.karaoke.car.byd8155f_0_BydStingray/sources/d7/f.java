package d7;

import G.i0;
import c7.InterfaceC1035a;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import k7.C1707a;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public final class f implements InterfaceC1035a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13498a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l f13499b;

    public /* synthetic */ f(l lVar, int i7) {
        this.f13498a = i7;
        this.f13499b = lVar;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // c7.InterfaceC1035a
    public final void q(Object... objArr) {
        switch (this.f13498a) {
            case 0:
                this.f13499b.h1();
                break;
            case 1:
                this.f13499b.e1("transport close", null);
                break;
            case 2:
                this.f13499b.f1(objArr.length > 0 ? (Exception) objArr[0] : null);
                break;
            case 3:
                f7.b bVar = objArr.length > 0 ? (f7.b) objArr[0] : null;
                l lVar = this.f13499b;
                int i7 = lVar.f13523L;
                Logger logger = l.f13510M;
                if (i7 == 1 || i7 == 2 || i7 == 3) {
                    if (logger.isLoggable(Level.FINE)) {
                        logger.fine(String.format("socket received: type '%s', data '%s'", bVar.f14641a, bVar.f14642b));
                    }
                    lVar.N0("packet", bVar);
                    lVar.N0("heartbeat", new Object[0]);
                    boolean zEquals = "open".equals(bVar.f14641a);
                    Object obj = bVar.f14642b;
                    if (!zEquals) {
                        String str = bVar.f14641a;
                        if ("ping".equals(str)) {
                            lVar.N0("ping", new Object[0]);
                            C1707a.a(new d(lVar, 0));
                        } else if ("error".equals(str)) {
                            a aVar = new a("server error");
                            aVar.l = obj;
                            lVar.f1(aVar);
                        } else if ("message".equals(str)) {
                            lVar.N0("data", obj);
                            lVar.N0("message", obj);
                        }
                    } else {
                        try {
                            lVar.g1(new i0((String) obj));
                        } catch (JSONException e10) {
                            lVar.N0("error", new a(e10));
                            return;
                        }
                    }
                } else if (logger.isLoggable(Level.FINE)) {
                    int i10 = lVar.f13523L;
                    StringBuilder sb = new StringBuilder("packet received with socket readyState '");
                    sb.append(i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? "null" : "CLOSED" : "CLOSING" : "OPEN" : "OPENING");
                    sb.append("'");
                    logger.fine(sb.toString());
                }
                break;
            default:
                int i11 = 0;
                while (true) {
                    l lVar2 = this.f13499b;
                    int i12 = lVar2.f13529t;
                    LinkedList linkedList = lVar2.f13516E;
                    if (i11 >= i12) {
                        lVar2.f13529t = 0;
                        if (linkedList.size() != 0) {
                            lVar2.c1();
                        } else {
                            lVar2.N0("drain", new Object[0]);
                        }
                    } else {
                        linkedList.poll();
                        i11++;
                    }
                    break;
                }
                break;
        }
    }
}
