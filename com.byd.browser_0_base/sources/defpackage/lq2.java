package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import defpackage.qn;

/* JADX INFO: compiled from: SpareChildConnection.java */
/* JADX INFO: loaded from: classes3.dex */
public final class lq2 {
    public final gn a;
    public qn b;
    public boolean c;
    public qn.e d;

    /* JADX INFO: compiled from: SpareChildConnection.java */
    public class a implements qn.e {
        public a() {
        }

        @Override // qn.e
        public final void a(qn qnVar) {
            lq2 lq2Var = lq2.this;
            qn.e eVar = lq2Var.d;
            if (eVar != null) {
                eVar.a(qnVar);
            }
            if (lq2Var.b != null) {
                lq2Var.b = null;
                lq2Var.c = false;
            }
        }

        @Override // qn.e
        public final void b(qn qnVar) {
            Log.w("cr_SpareChildConn", "Failed to warm up the spare sandbox service");
            lq2 lq2Var = lq2.this;
            qn.e eVar = lq2Var.d;
            if (eVar != null) {
                eVar.b(qnVar);
            }
            lq2Var.b = null;
            lq2Var.c = false;
        }

        @Override // qn.e
        public final void c() {
            lq2 lq2Var = lq2.this;
            lq2Var.c = true;
            qn.e eVar = lq2Var.d;
            if (eVar != null) {
                eVar.c();
                lq2Var.b = null;
                lq2Var.c = false;
            }
        }
    }

    public lq2(Context context, gn gnVar, Bundle bundle) {
        this.a = gnVar;
        a aVar = new a();
        gnVar.getClass();
        this.b = gnVar.b(context, bundle, new en(gnVar, aVar));
    }
}
