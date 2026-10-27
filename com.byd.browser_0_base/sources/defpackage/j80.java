package defpackage;

import defpackage.wp2;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: DeleteUndoCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class j80 {
    public final wp2 a;
    public final a b = new a();

    /* JADX INFO: compiled from: DeleteUndoCoordinator.java */
    public static class a implements wp2.b {
        @Override // wp2.b
        public final void d(Object obj) {
            ((Callback) obj).onResult(Boolean.TRUE);
        }

        @Override // wp2.b
        public final void j(Object obj) {
            ((Callback) obj).onResult(Boolean.FALSE);
        }
    }

    public j80(wp2 wp2Var) {
        this.a = wp2Var;
    }
}
