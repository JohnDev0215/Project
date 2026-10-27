package M7;

import Z7.x;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import n7.AbstractC1999n;
import o8.C2057b;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LinkedHashSet f4214a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2057b f4215b;

    static {
        List listD = AbstractC1999n.D(x.f10963a, x.f10968h, x.f10969i, x.c, x.d, x.f10966f);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = listD.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(C2057b.j((C2058c) it.next()));
        }
        f4214a = linkedHashSet;
        f4215b = C2057b.j(x.f10967g);
    }
}
