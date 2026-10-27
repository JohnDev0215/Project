package J5;

import A7.m;
import android.content.Context;
import c9.B;
import c9.C;
import c9.C1045f;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C f3109a;

    public k(Context context) {
        m.f(context, "context");
        B b10 = A9.a.d;
        b10.f12452k = new C1045f(new File(context.getCacheDir(), "resource_cache"), 10000000L);
        i iVar = new i(context);
        ArrayList arrayList = b10.c;
        arrayList.add(iVar);
        b10.d.add(new a());
        arrayList.add(new i((h) null));
        this.f3109a = new C(b10);
    }
}
