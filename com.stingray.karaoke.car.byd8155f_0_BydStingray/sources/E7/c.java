package E7;

import A7.m;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class c extends a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final b f1847n = new b(0);

    @Override // E7.a
    public final Random a() {
        Object obj = this.f1847n.get();
        m.e(obj, "get(...)");
        return (Random) obj;
    }
}
