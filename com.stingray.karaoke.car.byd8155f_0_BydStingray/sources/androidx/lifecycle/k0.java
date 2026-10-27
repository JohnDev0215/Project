package androidx.lifecycle;

import java.util.Iterator;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f11997a = new LinkedHashMap();

    public final void a() {
        LinkedHashMap linkedHashMap = this.f11997a;
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((f0) it.next()).b();
        }
        linkedHashMap.clear();
    }
}
