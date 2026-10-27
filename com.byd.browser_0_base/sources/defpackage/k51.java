package defpackage;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.R$id;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.m;

/* JADX INFO: compiled from: LanguagePickerCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k51 {
    public final em3 a;
    public final m51 b;

    public k51(View view, em3 em3Var, a8 a8Var) {
        view.getContext();
        this.a = em3Var;
        PropertyModel propertyModel = new PropertyModel(n51.d);
        ya1 ya1Var = new ya1();
        j.b(propertyModel, view, new n1(4));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R$id.recycler_view);
        view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        m mVar = new m(ya1Var);
        mVar.H(1, new da(8), new ee3(8));
        recyclerView.setAdapter(mVar);
        this.b = new m51(propertyModel, ya1Var, em3Var, a8Var);
    }

    public final void a(int i, Runnable runnable, Runnable runnable2) {
        m51 m51Var = this.b;
        m51Var.f = i;
        PropertyModel.r<Runnable> rVar = n51.a;
        a9 a9Var = new a9(m51Var, 24, runnable2);
        PropertyModel propertyModel = m51Var.c;
        propertyModel.p(rVar, a9Var);
        propertyModel.p(n51.b, new y70(m51Var, 17, runnable));
        m51Var.a(m51Var.a.a(m51Var.f));
    }
}
