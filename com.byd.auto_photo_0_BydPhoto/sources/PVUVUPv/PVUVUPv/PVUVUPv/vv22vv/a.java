package PVUVUPv.PVUVUPv.PVUVUPv.vv22vv;

import android.os.AccModeManager;
import android.os.WorkSource;
import androidx.annotation.NonNull;
import com.byd.dilink.os.IDiLinkAccLock;

/* JADX INFO: loaded from: classes.dex */
public class a implements IDiLinkAccLock {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AccModeManager.AccLock f101a;

    public a(AccModeManager.AccLock accLock) {
        this.f101a = accLock;
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void acquire() {
        this.f101a.acquire();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void acquire(long j) {
        this.f101a.acquire(j);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public String getTag() {
        return this.f101a.getTag();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public boolean isHeld() {
        return this.f101a.isHeld();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void release() {
        this.f101a.release();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void release(int i) {
        this.f101a.release(i);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setAccSource(WorkSource workSource) {
        this.f101a.setAccSource(workSource);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setHistoryTag(String str) {
        this.f101a.setHistoryTag(str);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setReferenceCounted(boolean z) {
        this.f101a.setReferenceCounted(z);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setTag(String str) {
        this.f101a.setTag(str);
    }

    @NonNull
    public String toString() {
        return this.f101a.toString();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public Runnable wrap(Runnable runnable) {
        return this.f101a.wrap(runnable);
    }
}
