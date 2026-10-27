package sst.sst.sst.SttTst;

import android.os.AccModeManager;
import android.os.WorkSource;
import androidx.annotation.NonNull;
import com.byd.dilink.os.IDiLinkAccLock;

/* JADX INFO: loaded from: classes2.dex */
public class a implements IDiLinkAccLock {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AccModeManager.AccLock f3223a;

    public a(AccModeManager.AccLock accLock) {
        this.f3223a = accLock;
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void acquire() {
        this.f3223a.acquire();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void acquire(long j) {
        this.f3223a.acquire(j);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public String getTag() {
        return this.f3223a.getTag();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public boolean isHeld() {
        return this.f3223a.isHeld();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void release() {
        this.f3223a.release();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void release(int i) {
        this.f3223a.release(i);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setAccSource(WorkSource workSource) {
        this.f3223a.setAccSource(workSource);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setHistoryTag(String str) {
        this.f3223a.setHistoryTag(str);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setReferenceCounted(boolean z) {
        this.f3223a.setReferenceCounted(z);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setTag(String str) {
        this.f3223a.setTag(str);
    }

    @NonNull
    public String toString() {
        return this.f3223a.toString();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public Runnable wrap(Runnable runnable) {
        return this.f3223a.wrap(runnable);
    }
}
