package com.byd.dilink30.app.am;

import android.app.ActivityManager;
import android.app.IActivityListener;
import android.app.IActivityManager;
import android.app.IProcessObserver;
import android.os.ServiceManager;
import androidx.annotation.Keep;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkActivityListener;
import com.byd.dilink.app.am.IDiLinkActivityManager;
import com.byd.dilink.app.am.IDiLinkActivityTopListener;
import com.byd.dilink.app.am.IDiLinkMultiDisplayActivityTopListener;
import com.byd.dilink.app.am.IDiLinkProcessObserver;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkActivityManager.class, singleton = true)
public class DiLinkActivityManager implements IDiLinkActivityManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLink3_0_ActivityListeners f1191a = new DiLink3_0_ActivityListeners();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink3_0_ProcessObservers f1192b = new DiLink3_0_ProcessObservers();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final IActivityManager f1193c = IActivityManager.Stub.asInterface(ServiceManager.getService("activity"));

    public static class DiLink3_0_ActivityListeners extends DiLinkCallbackHolders<IDiLinkActivityListener, IActivityListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityListener createInnerCallback(final IDiLinkActivityListener iDiLinkActivityListener) {
            return new IActivityListener.Stub() { // from class: com.byd.dilink30.app.am.DiLinkActivityManager.DiLink3_0_ActivityListeners.1
                @Keep
                public void onActivityChanged(String str) {
                    iDiLinkActivityListener.onActivityChanged(str);
                }
            };
        }
    }

    public static class DiLink3_0_ProcessObservers extends DiLinkCallbackHolders<IDiLinkProcessObserver, IProcessObserver> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IProcessObserver createInnerCallback(final IDiLinkProcessObserver iDiLinkProcessObserver) {
            return new IProcessObserver.Stub() { // from class: com.byd.dilink30.app.am.DiLinkActivityManager.DiLink3_0_ProcessObservers.1
                @Keep
                public void onForegroundActivitiesChanged(int i, int i2, boolean z) {
                    iDiLinkProcessObserver.onForegroundActivitiesChanged(i, i2, z);
                }

                @Keep
                public void onForegroundServicesChanged(int i, int i2, int i3) {
                    iDiLinkProcessObserver.onForegroundServicesChanged(i, i2, i3);
                }

                @Keep
                public void onProcessDied(int i, int i2) {
                    iDiLinkProcessObserver.onProcessDied(i, i2);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IActivityListener iActivityListener) {
        this.f1193c.unregisterActivityListener(iActivityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IProcessObserver iProcessObserver) {
        this.f1193c.unregisterProcessObserver(iProcessObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(IActivityListener iActivityListener) {
        this.f1193c.registerActivityListener(iActivityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(IProcessObserver iProcessObserver) {
        this.f1193c.registerProcessObserver(iProcessObserver);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses() {
        return this.f1193c.getRunningAppProcesses();
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public String getTopPackageName() {
        return this.f1193c.getTopPackageName();
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void killApplicationEx(String str, int i, int i2, String str2) {
        this.f1193c.killApplicationEx(str, i, i2, str2);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void killApplicationProcess(String str, int i) {
        this.f1193c.killApplicationProcess(str, i);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityListener(IDiLinkActivityListener iDiLinkActivityListener) {
        this.f1191a.addCallback(iDiLinkActivityListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.app.am.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1201a.c((IActivityListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 沒有 registerActivityTopListener 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 unregisterActivityTopListenerMultiDisplay 这个函数！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver) {
        this.f1192b.addCallback(iDiLinkProcessObserver, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.app.am.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1200a.d((IProcessObserver) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void scheduleApplicationInfoChanged(List<String> list, int i) {
        this.f1193c.scheduleApplicationInfoChanged(list, i);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityListener(IDiLinkActivityListener iDiLinkActivityListener) {
        this.f1191a.removeCallback(iDiLinkActivityListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.app.am.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1198a.a((IActivityListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 沒有 unregisterActivityTopListener 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 unregisterActivityTopListenerMultiDisplay 这个函数！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver) {
        this.f1192b.removeCallback(iDiLinkProcessObserver, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.app.am.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1199a.b((IProcessObserver) obj);
            }
        });
    }
}
