package android.permission;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: renamed from: android.permission.-$$Lambda$PermissionControllerService$1$xXX-ywdCqM5leaxXkxy473i7s2A, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$PermissionControllerService$1$xXXywdCqM5leaxXkxy473i7s2A implements Runnable {
    public final /* synthetic */ CountDownLatch f$0;

    public /* synthetic */ $$Lambda$PermissionControllerService$1$xXXywdCqM5leaxXkxy473i7s2A(CountDownLatch countDownLatch) {
        this.f$0 = countDownLatch;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.countDown();
    }
}
