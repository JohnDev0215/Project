package spi;

import com.android.car.ui.toolbar.MenuItemAdapterV1;
import com.android.car.ui.toolbar.b;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class hb implements b.d {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ hb(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.android.car.ui.toolbar.b.d
    public final void a(com.android.car.ui.toolbar.b bVar) {
        switch (this.a) {
            case 0:
                jb jbVar = (jb) this.b;
                int iIndexOf = jbVar.K.indexOf(bVar);
                if (iIndexOf >= 0) {
                    jbVar.L.set(iIndexOf, jbVar.b(bVar));
                    jbVar.M.notifyItemChanged(iIndexOf);
                } else {
                    jbVar.a();
                }
                jbVar.c();
                break;
            default:
                ((MenuItemAdapterV1) this.b).lambda$new$0(bVar);
                break;
        }
    }
}
