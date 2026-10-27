package s;

import android.os.Handler;
import h6.f;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: renamed from: s.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2326c extends f {
    public final Object c = new Object();
    public final ExecutorService d = Executors.newFixedThreadPool(4, new ThreadFactoryC2325b());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Handler f21953e;
}
