package l3;

import B5.RunnableC0079c;
import android.os.IBinder;
import java.util.HashMap;

/* JADX INFO: renamed from: l3.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1756l implements IBinder.DeathRecipient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f18580a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18581b;
    public final int c;
    public final T d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C1764u f18582e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final HashMap f18583f = new HashMap();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ AbstractServiceC1765v f18584g;

    public C1756l(AbstractServiceC1765v abstractServiceC1765v, String str, int i7, int i10, C1764u c1764u) {
        this.f18584g = abstractServiceC1765v;
        this.f18580a = str;
        this.f18581b = i7;
        this.c = i10;
        this.d = new T(str, i7, i10);
        this.f18582e = c1764u;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        this.f18584g.f18614r.post(new RunnableC0079c(this, 26));
    }
}
