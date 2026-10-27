package lkl6Kl.lkl6Kl.lkl6Kl.UULkUlU;

import android.content.Context;
import android.os.AccModeManager;
import android.os.IAccModeListener;
import android.os.IAccModeManager;
import android.os.IBinder;
import android.os.ServiceManager;
import android.os.WorkSource;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.os.IDiLinkAccLock;
import com.byd.dilink.os.IDiLinkAccModeListener;
import com.byd.dilink.os.IDiLinkAccModeManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkAccModeManager.class, singleton = true)
public class LK6LklLk implements IDiLinkAccModeManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLinkCallbackHolders<IDiLinkAccModeListener, IAccModeListener> f3120a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AccModeManager f3121b;

    public class a extends DiLinkCallbackHolders<IDiLinkAccModeListener, IAccModeListener> {

        /* JADX INFO: renamed from: lkl6Kl.lkl6Kl.lkl6Kl.UULkUlU.LK6LklLk$a$a, reason: collision with other inner class name */
        public class IAccModeListenerStubC0088a extends IAccModeListener.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IDiLinkAccModeListener f3123a;

            public IAccModeListenerStubC0088a(IDiLinkAccModeListener iDiLinkAccModeListener) {
                this.f3123a = iDiLinkAccModeListener;
            }

            public void onStatusChange(boolean z) {
                this.f3123a.onStatusChange(z);
            }
        }

        public a() {
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IAccModeListener createInnerCallback(IDiLinkAccModeListener iDiLinkAccModeListener) {
            return new IAccModeListenerStubC0088a(iDiLinkAccModeListener);
        }
    }

    public LK6LklLk(Context context) {
        this.f3121b = (AccModeManager) context.getSystemService("accmodemanager");
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public void acquireAccLock(IBinder iBinder, int i, String str, String str2, WorkSource workSource, String str3) {
        IAccModeManager.Stub.asInterface(ServiceManager.getService("accmodemanager")).acquireAccLock(iBinder, i, str, str2, workSource, str3);
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public void addListener(IDiLinkAccModeListener iDiLinkAccModeListener) {
        DiLinkCallbackHolders<IDiLinkAccModeListener, IAccModeListener> diLinkCallbackHolders = this.f3120a;
        final AccModeManager accModeManager = this.f3121b;
        Objects.requireNonNull(accModeManager);
        diLinkCallbackHolders.addCallbackIgnoreRemoteException(iDiLinkAccModeListener, new IDiLinkCallbackHandler() { // from class: lkl6Kl.lkl6Kl.lkl6Kl.UULkUlU.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                accModeManager.addListener((IAccModeListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public int getAccModeStatus() {
        throw new UnsupportedTargetPlatformException("4.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    @NonNull
    public IDiLinkAccLock newAccLock(int i, @NonNull String str) {
        return new c(this.f3121b.newAccLock(i, str));
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public void releaseAccLock(IBinder iBinder, int i) {
        IAccModeManager.Stub.asInterface(ServiceManager.getService("accmodemanager")).releaseAccLock(iBinder, i);
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public void removeListener(IDiLinkAccModeListener iDiLinkAccModeListener) {
        DiLinkCallbackHolders<IDiLinkAccModeListener, IAccModeListener> diLinkCallbackHolders = this.f3120a;
        final AccModeManager accModeManager = this.f3121b;
        Objects.requireNonNull(accModeManager);
        diLinkCallbackHolders.removeCallbackIgnoreRemoteException(iDiLinkAccModeListener, new IDiLinkCallbackHandler() { // from class: lkl6Kl.lkl6Kl.lkl6Kl.UULkUlU.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                accModeManager.removeListener((IAccModeListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public void requestSuspending(int i) {
        IAccModeManager.Stub.asInterface(ServiceManager.getService("accmodemanager")).requestSuspending(i);
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public void rmPkg2AccWhiteList(String str) {
        IAccModeManager.Stub.asInterface(ServiceManager.getService("accmodemanager")).rmPkg2AccWhiteList(str);
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public void setPkg2AccWhiteList(String str) {
        IAccModeManager.Stub.asInterface(ServiceManager.getService("accmodemanager")).setPkg2AccWhiteList(str);
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public void validateAccLockParameters(int i, String str) {
        AccModeManager.validateAccLockParameters(i, str);
    }
}
