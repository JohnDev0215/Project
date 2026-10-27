package y0;

import android.content.Context;
import b1.c;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import y0.f;

/* JADX INFO: compiled from: DatabaseConfiguration.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.InterfaceC0031c f9887a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f9888b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f9889c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f.d f9890d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<f.b> f9891e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f9892f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f.c f9893g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Executor f9894h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f9895i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final Set<Integer> f9896j;

    public a(Context context, String str, c.InterfaceC0031c interfaceC0031c, f.d dVar, List<f.b> list, boolean z6, f.c cVar, Executor executor, boolean z7, Set<Integer> set) {
        this.f9887a = interfaceC0031c;
        this.f9888b = context;
        this.f9889c = str;
        this.f9890d = dVar;
        this.f9891e = list;
        this.f9892f = z6;
        this.f9893g = cVar;
        this.f9894h = executor;
        this.f9895i = z7;
        this.f9896j = set;
    }

    public boolean a(int i7) {
        Set<Integer> set;
        return this.f9895i && ((set = this.f9896j) == null || !set.contains(Integer.valueOf(i7)));
    }
}
