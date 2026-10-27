package B9;

import A7.m;
import java.util.Arrays;
import timber.log.Timber;

/* JADX INFO: loaded from: classes.dex */
public final class a extends b {
    @Override // B9.b
    public final void a(Exception exc) {
        for (b bVar : Timber.c) {
            bVar.a(exc);
        }
    }

    @Override // B9.b
    public final void b(String str, Object... objArr) {
        m.f(objArr, "args");
        for (b bVar : Timber.c) {
            bVar.b(str, Arrays.copyOf(objArr, objArr.length));
        }
    }

    @Override // B9.b
    public final void c(String str, String str2) {
        m.f(str2, "message");
        throw new AssertionError();
    }

    public final void e(String str) {
        m.f(str, "tag");
        b[] bVarArr = Timber.c;
        int length = bVarArr.length;
        int i7 = 0;
        while (i7 < length) {
            b bVar = bVarArr[i7];
            i7++;
            bVar.f823a.set(str);
        }
    }
}
