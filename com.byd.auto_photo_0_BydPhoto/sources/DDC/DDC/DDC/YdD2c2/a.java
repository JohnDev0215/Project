package DDC.DDC.DDC.YdD2c2;

import android.os.AccModeManager;
import android.os.WorkSource;
import androidx.annotation.NonNull;
import com.byd.dilink.os.IDiLinkAccLock;

/* JADX INFO: loaded from: classes.dex */
public class a implements IDiLinkAccLock {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AccModeManager.AccLock f29a;

    public a(AccModeManager.AccLock accLock) {
        this.f29a = accLock;
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void acquire() {
        this.f29a.acquire();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void acquire(long j) {
        this.f29a.acquire(j);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public String getTag() {
        return this.f29a.getTag();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public boolean isHeld() {
        return this.f29a.isHeld();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void release() {
        this.f29a.release();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void release(int i) {
        this.f29a.release(i);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setAccSource(WorkSource workSource) {
        this.f29a.setAccSource(workSource);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setHistoryTag(String str) {
        this.f29a.setHistoryTag(str);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setReferenceCounted(boolean z) {
        this.f29a.setReferenceCounted(z);
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public void setTag(String str) {
        this.f29a.setTag(str);
    }

    @NonNull
    public String toString() {
        return this.f29a.toString();
    }

    @Override // com.byd.dilink.os.IDiLinkAccLock
    public Runnable wrap(Runnable runnable) {
        return this.f29a.wrap(runnable);
    }
}
