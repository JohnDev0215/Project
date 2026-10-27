package d9;

import A7.m;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f13643a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f13644b;

    public /* synthetic */ a(String str, boolean z6) {
        this.f13643a = str;
        this.f13644b = z6;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        String str = this.f13643a;
        m.f(str, "$name");
        Thread thread = new Thread(runnable, str);
        thread.setDaemon(this.f13644b);
        return thread;
    }
}
