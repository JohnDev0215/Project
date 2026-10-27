package n4;

import android.app.Application;
import android.content.Context;

/* JADX INFO: compiled from: ApplicationContextModule.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f8545a;

    public a(Context context) {
        this.f8545a = context;
    }

    Application a() {
        return k4.a.a(this.f8545a);
    }

    Context b() {
        return this.f8545a;
    }
}
