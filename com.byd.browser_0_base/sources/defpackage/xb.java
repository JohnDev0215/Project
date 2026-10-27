package defpackage;

import androidx.activity.OnBackPressedDispatcher;

/* JADX INFO: compiled from: BackPressHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class xb {

    /* JADX INFO: compiled from: BackPressHelper.java */
    public class a extends xr1 {
        public final /* synthetic */ vb d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(vb vbVar) {
            super(false);
            this.d = vbVar;
        }

        @Override // defpackage.xr1
        public final void a() {
            this.d.o();
        }
    }

    /* JADX INFO: compiled from: BackPressHelper.java */
    public class b extends xr1 {
        public final /* synthetic */ c d;
        public final /* synthetic */ OnBackPressedDispatcher e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(c cVar, OnBackPressedDispatcher onBackPressedDispatcher) {
            super(true);
            this.d = cVar;
            this.e = onBackPressedDispatcher;
        }

        @Override // defpackage.xr1
        public final void a() {
            if (this.d.a()) {
                return;
            }
            b(false);
            this.e.c();
        }
    }

    /* JADX INFO: compiled from: BackPressHelper.java */
    public interface c {
        boolean a();
    }

    public xb(b71 b71Var, OnBackPressedDispatcher onBackPressedDispatcher, c cVar) {
        onBackPressedDispatcher.a(b71Var, new b(cVar, onBackPressedDispatcher));
    }

    public static void a(b71 b71Var, OnBackPressedDispatcher onBackPressedDispatcher, vb vbVar) {
        a aVar = new a(vbVar);
        vbVar.getHandleBackPressChangedSupplier().p(new wb(0, aVar));
        onBackPressedDispatcher.a(b71Var, aVar);
    }

    @Deprecated
    public static void b(b71 b71Var, OnBackPressedDispatcher onBackPressedDispatcher, c cVar) {
        new xb(b71Var, onBackPressedDispatcher, cVar);
    }
}
