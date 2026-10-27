package defpackage;

import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;

/* JADX INFO: compiled from: TabPersistentStore.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s23 extends ac<ui2> {
    public final /* synthetic */ Boolean h;
    public final /* synthetic */ int i;
    public final /* synthetic */ b73 j;
    public final /* synthetic */ z23 k;

    public s23(z23 z23Var, Boolean bool, int i, b73 b73Var) {
        this.k = z23Var;
        this.h = bool;
        this.i = i;
        this.j = b73Var;
    }

    @Override // defpackage.i9
    public final Object b() {
        b73 b73Var = this.j;
        z23 z23Var = this.k;
        int i = this.i;
        Boolean bool = this.h;
        if (bool == null) {
            Object obj = z23.A;
            z23Var.getClass();
            t23 t23Var = new t23(z23Var, i);
            t23Var.d(b73Var);
            z23Var.y = t23Var;
        } else {
            boolean zBooleanValue = bool.booleanValue();
            CriticalPersistedTabData.a aVar = CriticalPersistedTabData.A;
            m02 m02VarA = m02.a(CriticalPersistedTabData.class, zBooleanValue);
            ui2 ui2Var = (ui2) m02VarA.f().c(i, m02VarA.e);
            if (!(ui2Var == null)) {
                return ui2Var;
            }
            Object obj2 = z23.A;
            z23Var.getClass();
            t23 t23Var2 = new t23(z23Var, i);
            t23Var2.d(b73Var);
            z23Var.y = t23Var2;
        }
        return null;
    }
}
