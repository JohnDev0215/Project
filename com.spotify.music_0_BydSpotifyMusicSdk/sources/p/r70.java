package p;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r70 implements vh2, h62 {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Object l;

    public /* synthetic */ r70(Object obj, Object obj2, Object obj3, int i) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
        this.l = obj3;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x01b8 A[PHI: r14
  0x01b8: PHI (r14v12 com.google.android.datatransport.runtime.firebase.transport.LogEventDropped$Reason) = 
  (r14v5 com.google.android.datatransport.runtime.firebase.transport.LogEventDropped$Reason)
  (r14v7 com.google.android.datatransport.runtime.firebase.transport.LogEventDropped$Reason)
  (r14v8 com.google.android.datatransport.runtime.firebase.transport.LogEventDropped$Reason)
  (r14v9 com.google.android.datatransport.runtime.firebase.transport.LogEventDropped$Reason)
  (r14v10 com.google.android.datatransport.runtime.firebase.transport.LogEventDropped$Reason)
 binds: [B:40:0x01b6, B:46:0x01c8, B:49:0x01d1, B:52:0x01da, B:55:0x01e3] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // p.h62
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object apply(java.lang.Object r26) {
        /*
            Method dump skipped, instruction units count: 1016
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.r70.apply(java.lang.Object):java.lang.Object");
    }

    @Override // p.vh2
    public final Object b() {
        t70 t70Var = (t70) this.e;
        wi wiVar = (wi) this.k;
        ai aiVar = (ai) this.l;
        j62 j62Var = (j62) t70Var.d;
        j62Var.getClass();
        Object[] objArr = {wiVar.c, aiVar.a, wiVar.a};
        if (Log.isLoggable("TRuntime.".concat("SQLiteEventStore"), 3)) {
            String.format("Storing event with priority=%s, name=%s for destination %s", objArr);
        }
        ((Long) j62Var.i(new r70(j62Var, (Object) aiVar, wiVar, 4))).longValue();
        ((s61) t70Var.a).a(wiVar, 1, false);
        return null;
    }

    public /* synthetic */ r70(j62 j62Var, Object obj, wi wiVar, int i) {
        this.c = i;
        this.e = j62Var;
        this.l = obj;
        this.k = wiVar;
    }
}
