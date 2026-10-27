package android.permission;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: renamed from: android.permission.-$$Lambda$PermissionControllerService$1$5k6tNlswoNAjCdgttrkQIe8VHVs, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$PermissionControllerService$1$5k6tNlswoNAjCdgttrkQIe8VHVs implements Runnable {
    public final /* synthetic */ CountDownLatch f$0;

    public /* synthetic */ $$Lambda$PermissionControllerService$1$5k6tNlswoNAjCdgttrkQIe8VHVs(CountDownLatch countDownLatch) {
        this.f$0 = countDownLatch;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.countDown();
    }
}
