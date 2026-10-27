package com.byd.dilink_rcs.app.am;

import android.app.ActivityManager;
import android.app.IActivityListener;
import android.app.IActivityManager;
import android.app.IActivityTopListener;
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
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkActivityManager.class, singleton = true)
public class DiLinkActivityManager implements IDiLinkActivityManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLink_rcs_ActivityTopListeners f2465a = new DiLink_rcs_ActivityTopListeners();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink_rcs_ActivityListeners f2466b = new DiLink_rcs_ActivityListeners();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLink_rcs_ProcessObservers f2467c = new DiLink_rcs_ProcessObservers();
    private final IActivityManager d = IActivityManager.Stub.asInterface(ServiceManager.getService("activity"));

    public static class DiLink_rcs_ActivityListeners extends DiLinkCallbackHolders<IDiLinkActivityListener, IActivityListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityListener createInnerCallback(final IDiLinkActivityListener iDiLinkActivityListener) {
            return new IActivityListener.Stub() { // from class: com.byd.dilink_rcs.app.am.DiLinkActivityManager.DiLink_rcs_ActivityListeners.1
                @Keep
                public void onActivityChanged(String str) {
                    iDiLinkActivityListener.onActivityChanged(str);
                }
            };
        }
    }

    public static class DiLink_rcs_ActivityTopListeners extends DiLinkCallbackHolders<IDiLinkActivityTopListener, IActivityTopListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityTopListener createInnerCallback(final IDiLinkActivityTopListener iDiLinkActivityTopListener) {
            return new IActivityTopListener.Stub() { // from class: com.byd.dilink_rcs.app.am.DiLinkActivityManager.DiLink_rcs_ActivityTopListeners.1
                @Keep
                public void onTopActivityChanged(String str) {
                    iDiLinkActivityTopListener.onTopActivityChanged(str);
                }

                @Keep
                public void onTopPackageChanged(String str) {
                    iDiLinkActivityTopListener.onTopPackageChanged(str);
                }
            };
        }
    }

    public static class DiLink_rcs_ProcessObservers extends DiLinkCallbackHolders<IDiLinkProcessObserver, IProcessObserver> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IProcessObserver createInnerCallback(final IDiLinkProcessObserver iDiLinkProcessObserver) {
            return new IProcessObserver.Stub() { // from class: com.byd.dilink_rcs.app.am.DiLinkActivityManager.DiLink_rcs_ProcessObservers.1
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
        this.d.unregisterActivityListener(iActivityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IActivityTopListener iActivityTopListener) {
        this.d.unregisterActivityTopListener(iActivityTopListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(IProcessObserver iProcessObserver) {
        this.d.unregisterProcessObserver(iProcessObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(IActivityListener iActivityListener) {
        this.d.registerActivityListener(iActivityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(IActivityTopListener iActivityTopListener) {
        this.d.registerActivityTopListener(iActivityTopListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(IProcessObserver iProcessObserver) {
        this.d.registerProcessObserver(iProcessObserver);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses() {
        return this.d.getRunningAppProcesses();
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public String getTopPackageName() {
        return this.d.getTopPackageName();
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void killApplicationEx(String str, int i, int i2, String str2) {
        throw new UnsupportedTargetPlatformException("rcs 不存在killApplicationEx方法");
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void killApplicationProcess(String str, int i) {
        this.d.killApplicationProcess(str, i);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityListener(IDiLinkActivityListener iDiLinkActivityListener) {
        this.f2466b.addCallback(iDiLinkActivityListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rcs.app.am.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2475a.d((IActivityListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener) {
        this.f2465a.addCallback(iDiLinkActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rcs.app.am.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2477a.e((IActivityTopListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
        throw new UnsupportedTargetPlatformException("rcs 没有 unregisterActivityTopListenerMultiDisplay 这个函数！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver) {
        this.f2467c.addCallback(iDiLinkProcessObserver, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rcs.app.am.f
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2479a.f((IProcessObserver) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void scheduleApplicationInfoChanged(List<String> list, int i) {
        this.d.scheduleApplicationInfoChanged(list, i);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityListener(IDiLinkActivityListener iDiLinkActivityListener) {
        this.f2466b.removeCallback(iDiLinkActivityListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rcs.app.am.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2476a.a((IActivityListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener) {
        this.f2465a.removeCallback(iDiLinkActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rcs.app.am.e
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2478a.b((IActivityTopListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
        throw new UnsupportedTargetPlatformException("rcs 没有 unregisterActivityTopListenerMultiDisplay 这个函数！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver) {
        this.f2467c.removeCallback(iDiLinkProcessObserver, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rcs.app.am.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2474a.c((IProcessObserver) obj);
            }
        });
    }
}
