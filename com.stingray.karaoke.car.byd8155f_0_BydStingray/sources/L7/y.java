package L7;

import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class y implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final y f4068a = new y();

    @Override // L7.e
    public final Type p() {
        Class cls = Void.TYPE;
        A7.m.e(cls, "TYPE");
        return cls;
    }

    @Override // L7.e
    public final Object q(Object[] objArr) {
        throw new UnsupportedOperationException("call/callBy are not supported for this declaration.");
    }

    @Override // L7.e
    public final List r() {
        return n7.u.l;
    }

    @Override // L7.e
    public final /* bridge */ /* synthetic */ Member s() {
        return null;
    }
}
