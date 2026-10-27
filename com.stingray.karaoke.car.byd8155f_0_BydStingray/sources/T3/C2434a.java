package t3;

import A7.m;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import r.C2257p;

/* JADX INFO: renamed from: t3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2434a implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashSet f22455a;

    public C2434a(C2257p c2257p) {
        m.f(c2257p, "registry");
        this.f22455a = new LinkedHashSet();
        c2257p.d("androidx.savedstate.Restarter", this);
    }

    @Override // t3.d
    public final Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("classes_to_restore", new ArrayList<>(this.f22455a));
        return bundle;
    }
}
