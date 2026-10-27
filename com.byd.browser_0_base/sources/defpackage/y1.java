package defpackage;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.base.CoreAccountId;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.components.signin.identitymanager.AccountTrackerService;

/* JADX INFO: compiled from: AccountTrackerService.java */
/* JADX INFO: loaded from: classes3.dex */
public final class y1 extends i9<List<String>> {
    public final /* synthetic */ List h;
    public final /* synthetic */ AccountManagerFacade i;
    public final /* synthetic */ boolean j;
    public final /* synthetic */ AccountTrackerService k;

    public y1(ArrayList arrayList, AccountManagerFacade accountManagerFacade, AccountTrackerService accountTrackerService, boolean z) {
        this.k = accountTrackerService;
        this.h = arrayList;
        this.i = accountManagerFacade;
        this.j = z;
    }

    @Override // defpackage.i9
    public final List<String> b() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        ArrayList arrayList = new ArrayList();
        Iterator it = this.h.iterator();
        if (it.hasNext()) {
            this.i.c();
        } else {
            oj2.b0(SystemClock.elapsedRealtime() - jElapsedRealtime, "Signin.AndroidGetAccountIdsTime");
        }
        return arrayList;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // defpackage.i9
    public final void l(List<String> list) {
        List<String> list2 = list;
        int size = list2.size();
        List list3 = this.h;
        int size2 = list3.size();
        boolean z = this.j;
        AccountTrackerService accountTrackerService = this.k;
        if (size != size2) {
            accountTrackerService.g = 0;
            accountTrackerService.b(z);
            return;
        }
        accountTrackerService.getClass();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list2.size(); i++) {
            String str = (String) list3.get(i);
            String str2 = list2.get(i);
            arrayList.add(new CoreAccountInfo(new CoreAccountId(str2), str, str2));
        }
        accountTrackerService.a(arrayList, z);
    }
}
