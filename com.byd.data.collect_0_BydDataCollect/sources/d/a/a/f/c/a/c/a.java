package d.a.a.f.c.a.c;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

/* JADX INFO: compiled from: SkinContextWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f4658a;

    public a(Context context) {
        super(context);
        this.f4658a = context;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return c.n().q() ? c.n().p() : super.getResources();
    }
}
