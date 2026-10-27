package F0;

import android.content.ClipboardManager;
import android.content.Context;

/* JADX INFO: renamed from: F0.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0205h implements InterfaceC0222p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ClipboardManager f2207a;

    public C0205h(Context context) {
        Object systemService = context.getSystemService("clipboard");
        A7.m.d(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        this.f2207a = (ClipboardManager) systemService;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(N0.C0411f r18) {
        /*
            Method dump skipped, instruction units count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.C0205h.a(N0.f):void");
    }
}
