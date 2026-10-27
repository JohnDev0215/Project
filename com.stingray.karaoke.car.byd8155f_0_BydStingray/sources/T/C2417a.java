package t;

import java.util.HashMap;

/* JADX INFO: renamed from: t.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2417a extends C2422f {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final HashMap f22416p = new HashMap();

    @Override // t.C2422f
    public final C2419c a(Object obj) {
        return (C2419c) this.f22416p.get(obj);
    }

    @Override // t.C2422f
    public final Object d(Object obj) {
        Object objD = super.d(obj);
        this.f22416p.remove(obj);
        return objD;
    }
}
