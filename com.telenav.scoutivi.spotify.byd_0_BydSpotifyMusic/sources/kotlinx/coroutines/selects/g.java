package kotlinx.coroutines.selects;

import kotlinx.coroutines.internal.c0;

/* JADX INFO: compiled from: Select.kt */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f7803a = new c0("NOT_SELECTED");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f7804b = new c0("ALREADY_SELECTED");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f7805c = new c0("UNDECIDED");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Object f7806d = new c0("RESUMED");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final h f7807e = new h();

    public static final Object d() {
        return f7804b;
    }

    public static final Object e() {
        return f7803a;
    }
}
