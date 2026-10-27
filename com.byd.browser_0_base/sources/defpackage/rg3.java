package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;

/* JADX INFO: compiled from: TutorialListCoordinatorImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rg3 extends View {
    public final /* synthetic */ sg3 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public rg3(sg3 sg3Var, Context context) {
        super(context);
        this.e = sg3Var;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        this.e.b.c();
    }
}
