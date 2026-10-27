package defpackage;

import java.util.concurrent.Executor;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Pu implements Executor {
    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        PostTask.a(C1242nv.h, runnable, 0L);
    }
}
