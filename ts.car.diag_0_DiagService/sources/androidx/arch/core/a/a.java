package androidx.arch.core.a;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class a extends c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile a f398c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    private c f399a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NonNull
    private c f400b;

    private a() {
        b bVar = new b();
        this.f400b = bVar;
        this.f399a = bVar;
    }

    @NonNull
    public static a b() {
        if (f398c != null) {
            return f398c;
        }
        synchronized (a.class) {
            if (f398c == null) {
                f398c = new a();
            }
        }
        return f398c;
    }

    @Override // androidx.arch.core.a.c
    public boolean a() {
        return this.f399a.a();
    }
}
