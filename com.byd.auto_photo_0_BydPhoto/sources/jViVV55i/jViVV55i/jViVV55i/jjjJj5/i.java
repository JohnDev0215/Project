package jViVV55i.jViVV55i.jViVV55i.jjjJj5;

import android.os.AccModeManager;
import android.os.WorkSource;
import androidx.annotation.NonNull;
import com.byd.dilink.os.IDiLinkAccLock;

/* JADX INFO: loaded from: classes2.dex */
public class i implements IDiLinkAccLock {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AccModeManager.AccLock f3089a;

    public i(AccModeManager.AccLock accLock) {
        this.f3089a = accLock;
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void acquire() {
        this.f3089a.acquire();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void acquire(long j) {
        this.f3089a.acquire(j);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public String getTag() {
        return this.f3089a.getTag();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public boolean isHeld() {
        return this.f3089a.isHeld();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void release() {
        this.f3089a.release();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void release(int i) {
        this.f3089a.release(i);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setAccSource(WorkSource workSource) {
        this.f3089a.setAccSource(workSource);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setHistoryTag(String str) {
        this.f3089a.setHistoryTag(str);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setReferenceCounted(boolean z) {
        this.f3089a.setReferenceCounted(z);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setTag(String str) {
        this.f3089a.setTag(str);
    }

    @NonNull
    public String toString() {
        return this.f3089a.toString();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public Runnable wrap(Runnable runnable) {
        return this.f3089a.wrap(runnable);
    }
}
