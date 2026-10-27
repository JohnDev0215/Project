package com.byd.dilink51_main.os;

import android.content.Context;
import android.os.AccModeManager;
import android.os.IAccModeListener;
import android.os.IAccModeManager;
import android.os.IBinder;
import android.os.ServiceManager;
import android.os.WorkSource;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.os.IDiLinkAccLock;
import com.byd.dilink.os.IDiLinkAccModeListener;
import com.byd.dilink.os.IDiLinkAccModeManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkAccModeManager.class, singleton = true)
public class DiLinkAccModeManager implements IDiLinkAccModeManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkAccModeListener, IAccModeListener> f1820a = new DiLinkCallbackHolders<IDiLinkAccModeListener, IAccModeListener>() { // from class: com.byd.dilink51_main.os.DiLinkAccModeManager.1
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IAccModeListener createInnerCallback(final IDiLinkAccModeListener iDiLinkAccModeListener) {
            return new IAccModeListener.Stub() { // from class: com.byd.dilink51_main.os.DiLinkAccModeManager.1.1
                @Keep
                public void onStatusChange(boolean z) {
                    iDiLinkAccModeListener.onStatusChange(z);
                }
            };
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private AccModeManager f1821b;

    public DiLinkAccModeManager(Context context) {
        if (DiLink.isPlatform(DiLink.VERSION_NAME_5_1, false)) {
            this.f1821b = (AccModeManager) context.getSystemService("accmodemanager");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IAccModeListener iAccModeListener) {
        this.f1821b.addListener(iAccModeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IAccModeListener iAccModeListener) {
        this.f1821b.removeListener(iAccModeListener);
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public void acquireAccLock(IBinder iBinder, int i, String str, String str2, WorkSource workSource, String str3) {
        IAccModeManager.Stub.asInterface(ServiceManager.getService("accmodemanager")).acquireAccLock(iBinder, i, str, str2, workSource, str3);
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public void addListener(IDiLinkAccModeListener iDiLinkAccModeListener) {
        this.f1820a.addCallbackIgnoreRemoteException(iDiLinkAccModeListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1837a.a((IAccModeListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public int getAccModeStatus() {
        return IAccModeManager.Stub.asInterface(ServiceManager.getService("accmodemanager")).getAccModeStatus();
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    @NonNull
    public IDiLinkAccLock newAccLock(int i, @NonNull String str) {
        return new DDC.DDC.DDC.YdD2c2.a(this.f1821b.newAccLock(i, str));
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public void releaseAccLock(IBinder iBinder, int i) {
        IAccModeManager.Stub.asInterface(ServiceManager.getService("accmodemanager")).releaseAccLock(iBinder, i);
    }

    @Override // com.byd.dilink.os.IDiLinkAccModeManager
    public void removeListener(IDiLinkAccModeListener iDiLinkAccModeListener) {
        this.f1820a.removeCallbackIgnoreRemoteException(iDiLinkAccModeListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1834a.b((IAccModeListener) obj);
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
