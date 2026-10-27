package com.byd.dilink51_main.app.am;

import android.app.ActivityManager;
import android.app.IActivityListener;
import android.app.IActivityManager;
import android.app.IActivityTopListener;
import android.app.IActivityTopListenerMultiDisplay;
import android.app.IProcessObserver;
import android.os.ServiceManager;
import androidx.annotation.Keep;
import com.byd.dilink.DiLink;
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
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkActivityManager.class, singleton = true)
public class DiLinkActivityManager implements IDiLinkActivityManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLink5_1_ActivityListeners f1721a = new DiLink5_1_ActivityListeners();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink5_1_ActivityTopListeners f1722b = new DiLink5_1_ActivityTopListeners();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLink5_1_MultiDisplayActivityTopListeners f1723c = new DiLink5_1_MultiDisplayActivityTopListeners();
    private DiLink5_1_ProcessObservers d = new DiLink5_1_ProcessObservers();
    private final IActivityManager e = IActivityManager.Stub.asInterface(ServiceManager.getService("activity"));

    public static class DiLink5_1_ActivityListeners extends DiLinkCallbackHolders<IDiLinkActivityListener, IActivityListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityListener createInnerCallback(final IDiLinkActivityListener iDiLinkActivityListener) {
            return new IActivityListener.Stub() { // from class: com.byd.dilink51_main.app.am.DiLinkActivityManager.DiLink5_1_ActivityListeners.1
                @Keep
                public void onActivityChanged(String str) {
                    iDiLinkActivityListener.onActivityChanged(str);
                }
            };
        }
    }

    public static class DiLink5_1_ActivityTopListeners extends DiLinkCallbackHolders<IDiLinkActivityTopListener, IActivityTopListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityTopListener createInnerCallback(final IDiLinkActivityTopListener iDiLinkActivityTopListener) {
            return new IActivityTopListener.Stub() { // from class: com.byd.dilink51_main.app.am.DiLinkActivityManager.DiLink5_1_ActivityTopListeners.1
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

    public static class DiLink5_1_MultiDisplayActivityTopListeners extends DiLinkCallbackHolders<IDiLinkMultiDisplayActivityTopListener, IActivityTopListenerMultiDisplay> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityTopListenerMultiDisplay createInnerCallback(final IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
            return new IActivityTopListenerMultiDisplay.Stub() { // from class: com.byd.dilink51_main.app.am.DiLinkActivityManager.DiLink5_1_MultiDisplayActivityTopListeners.1
                @Keep
                public void onTopActivityChanged(String str, int i) {
                    iDiLinkMultiDisplayActivityTopListener.onTopActivityChanged(str, i);
                }
            };
        }
    }

    public static class DiLink5_1_ProcessObservers extends DiLinkCallbackHolders<IDiLinkProcessObserver, IProcessObserver> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IProcessObserver createInnerCallback(final IDiLinkProcessObserver iDiLinkProcessObserver) {
            return new IProcessObserver.Stub() { // from class: com.byd.dilink51_main.app.am.DiLinkActivityManager.DiLink5_1_ProcessObservers.1
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
        this.e.registerActivityListener(iActivityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IActivityTopListener iActivityTopListener) {
        this.e.registerActivityTopListener(iActivityTopListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(IActivityTopListenerMultiDisplay iActivityTopListenerMultiDisplay) {
        this.e.registerActivityTopListenerMultiDisplay(iActivityTopListenerMultiDisplay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(IProcessObserver iProcessObserver) {
        this.e.registerProcessObserver(iProcessObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(IActivityListener iActivityListener) {
        this.e.unregisterActivityListener(iActivityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(IActivityTopListener iActivityTopListener) {
        this.e.unregisterActivityTopListener(iActivityTopListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(IActivityTopListenerMultiDisplay iActivityTopListenerMultiDisplay) {
        this.e.unregisterActivityTopListenerMultiDisplay(iActivityTopListenerMultiDisplay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(IProcessObserver iProcessObserver) {
        this.e.unregisterProcessObserver(iProcessObserver);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses() {
        return this.e.getRunningAppProcesses();
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public String getTopPackageName() {
        return this.e.getTopPackageName();
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void killApplicationEx(String str, int i, int i2, String str2) {
        this.e.killApplicationEx(str, i, i2, str2);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void killApplicationProcess(String str, int i) {
        this.e.killApplicationProcess(str, i);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityListener(IDiLinkActivityListener iDiLinkActivityListener) {
        this.f1721a.addCallback(iDiLinkActivityListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.app.am.f
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1737a.a((IActivityListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener) {
        this.f1722b.addCallback(iDiLinkActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.app.am.h
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1739a.b((IActivityTopListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
        this.f1723c.addCallback(iDiLinkMultiDisplayActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.app.am.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1734a.c((IActivityTopListenerMultiDisplay) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver) {
        this.d.addCallback(iDiLinkProcessObserver, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.app.am.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1733a.d((IProcessObserver) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void scheduleApplicationInfoChanged(List<String> list, int i) {
        this.e.scheduleApplicationInfoChanged(list, i);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityListener(IDiLinkActivityListener iDiLinkActivityListener) {
        this.f1721a.removeCallback(iDiLinkActivityListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.app.am.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1735a.e((IActivityListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener) {
        this.f1722b.removeCallback(iDiLinkActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.app.am.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1732a.f((IActivityTopListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
        this.f1723c.removeCallback(iDiLinkMultiDisplayActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.app.am.e
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1736a.g((IActivityTopListenerMultiDisplay) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver) {
        this.d.removeCallback(iDiLinkProcessObserver, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.app.am.g
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1738a.h((IProcessObserver) obj);
            }
        });
    }
}
