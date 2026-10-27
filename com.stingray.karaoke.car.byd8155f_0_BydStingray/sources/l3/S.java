package l3;

import android.content.ContentResolver;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class S {
    public static final boolean c = U.f18544b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f18541a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ContentResolver f18542b;

    public final boolean a(Q q4, String str) {
        int i7 = q4.f18540b;
        return i7 < 0 ? this.f18541a.getPackageManager().checkPermission(str, q4.f18539a) == 0 : this.f18541a.checkPermission(str, i7, q4.c) == 0;
    }
}
