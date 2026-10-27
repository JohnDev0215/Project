package a.k;

import a.k.c;
import a.k.i;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: loaded from: classes.dex */
public class p extends c<i.a, i, Void> {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c.a<i.a, i, Void> f927f = new a();

    public static class a extends c.a<i.a, i, Void> {
        @Override // a.k.c.a
        public void a(i.a aVar, i iVar, int i, Void r4) {
            i iVar2 = iVar;
            ViewDataBinding.j jVar = (ViewDataBinding.j) aVar;
            ViewDataBinding viewDataBindingA = jVar.f1552a.a();
            if (viewDataBindingA == null) {
                return;
            }
            ViewDataBinding.i<i> iVar3 = jVar.f1552a;
            if (iVar3.f1551c != iVar2) {
                return;
            }
            ViewDataBinding.j(viewDataBindingA, iVar3.f1550b, iVar2, i);
        }
    }

    public p() {
        super(f927f);
    }
}
