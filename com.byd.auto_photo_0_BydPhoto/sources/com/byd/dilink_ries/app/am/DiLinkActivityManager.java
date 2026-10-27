package com.byd.dilink_ries.app.am;

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
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkActivityManager.class, singleton = true)
public class DiLinkActivityManager implements IDiLinkActivityManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLink_ries_ActivityTopListeners f2563a = new DiLink_ries_ActivityTopListeners();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink_ries_ActivityListeners f2564b = new DiLink_ries_ActivityListeners();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLink_ries_ProcessObservers f2565c = new DiLink_ries_ProcessObservers();
    private final IActivityManager d = IActivityManager.Stub.asInterface(ServiceManager.getService("activity"));

    public static class DiLink_ries_ActivityListeners extends DiLinkCallbackHolders<IDiLinkActivityListener, IActivityListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityListener createInnerCallback(final IDiLinkActivityListener iDiLinkActivityListener) {
            return new IActivityListener.Stub() { // from class: com.byd.dilink_ries.app.am.DiLinkActivityManager.DiLink_ries_ActivityListeners.1
                @Keep
                public void onActivityChanged(String str) {
                    iDiLinkActivityListener.onActivityChanged(str);
                }
            };
        }
    }

    public static class DiLink_ries_ActivityTopListeners extends DiLinkCallbackHolders<IDiLinkActivityTopListener, IActivityTopListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityTopListener createInnerCallback(final IDiLinkActivityTopListener iDiLinkActivityTopListener) {
            return new IActivityTopListener.Stub() { // from class: com.byd.dilink_ries.app.am.DiLinkActivityManager.DiLink_ries_ActivityTopListeners.1
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

    public static class DiLink_ries_ProcessObservers extends DiLinkCallbackHolders<IDiLinkProcessObserver, IProcessObserver> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IProcessObserver createInnerCallback(final IDiLinkProcessObserver iDiLinkProcessObserver) {
            return new IProcessObserver.Stub() { // from class: com.byd.dilink_ries.app.am.DiLinkActivityManager.DiLink_ries_ProcessObservers.1
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
    public /* synthetic */ void j(IActivityListener iActivityListener) {
        this.d.registerActivityListener(iActivityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(IActivityTopListener iActivityTopListener) {
        this.d.registerActivityTopListener(iActivityTopListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(IProcessObserver iProcessObserver) {
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
        this.d.killApplicationEx(str, i, i2, str2);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void killApplicationProcess(String str, int i) {
        this.d.killApplicationProcess(str, i);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityListener(IDiLinkActivityListener iDiLinkActivityListener) {
        this.f2564b.addCallback(iDiLinkActivityListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.app.am.f
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2577a.j((IActivityListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener) {
        this.f2563a.addCallback(iDiLinkActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.app.am.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2574a.k((IActivityTopListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
        throw new UnsupportedTargetPlatformException("ries 没有 unregisterActivityTopListenerMultiDisplay 这个函数！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver) {
        this.f2565c.addCallback(iDiLinkProcessObserver, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.app.am.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2575a.l((IProcessObserver) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void scheduleApplicationInfoChanged(List<String> list, int i) {
        this.d.scheduleApplicationInfoChanged(list, i);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityListener(IDiLinkActivityListener iDiLinkActivityListener) {
        this.f2564b.removeCallback(iDiLinkActivityListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.app.am.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2573a.a((IActivityListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener) {
        this.f2563a.removeCallback(iDiLinkActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.app.am.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2572a.b((IActivityTopListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
        throw new UnsupportedTargetPlatformException("ries 没有 unregisterActivityTopListenerMultiDisplay 这个函数！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver) {
        this.f2565c.removeCallback(iDiLinkProcessObserver, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.app.am.e
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2576a.c((IProcessObserver) obj);
            }
        });
    }
}
