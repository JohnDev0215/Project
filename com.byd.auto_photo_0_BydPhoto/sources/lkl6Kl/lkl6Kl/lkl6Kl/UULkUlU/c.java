package lkl6Kl.lkl6Kl.lkl6Kl.UULkUlU;

import android.os.AccModeManager;
import android.os.WorkSource;
import androidx.annotation.NonNull;
import com.byd.dilink.os.IDiLinkAccLock;

/* JADX INFO: loaded from: classes2.dex */
public class c implements IDiLinkAccLock {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AccModeManager.AccLock f3128a;

    public c(AccModeManager.AccLock accLock) {
        this.f3128a = accLock;
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void acquire() {
        this.f3128a.acquire();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void acquire(long j) {
        this.f3128a.acquire(j);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public String getTag() {
        return this.f3128a.getTag();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public boolean isHeld() {
        return this.f3128a.isHeld();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void release() {
        this.f3128a.release();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void release(int i) {
        this.f3128a.release(i);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setAccSource(WorkSource workSource) {
        this.f3128a.setAccSource(workSource);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setHistoryTag(String str) {
        this.f3128a.setHistoryTag(str);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setReferenceCounted(boolean z) {
        this.f3128a.setReferenceCounted(z);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setTag(String str) {
        this.f3128a.setTag(str);
    }

    @NonNull
    public String toString() {
        return this.f3128a.toString();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public Runnable wrap(Runnable runnable) {
        return this.f3128a.wrap(runnable);
    }
}
