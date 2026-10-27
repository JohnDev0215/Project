package p;

import java.util.concurrent.ThreadFactory;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class cs2 implements ThreadFactory {
    public final /* synthetic */ String c;
    public final /* synthetic */ boolean e;

    public /* synthetic */ cs2(String str, boolean z) {
        this.c = str;
        this.e = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        byte[] bArr = Util.a;
        String str = this.c;
        v03.j(str, "$name");
        Thread thread = new Thread(runnable, str);
        thread.setDaemon(this.e);
        return thread;
    }
}
