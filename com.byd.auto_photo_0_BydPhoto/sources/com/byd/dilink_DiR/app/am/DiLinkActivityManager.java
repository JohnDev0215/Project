package com.byd.dilink_DiR.app.am;

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
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkActivityManager.class, singleton = true)
public class DiLinkActivityManager implements IDiLinkActivityManager {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLinkDiR_ActivityTopListeners f2149b = new DiLinkDiR_ActivityTopListeners();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLinkDiR_ProcessObservers f2150c = new DiLinkDiR_ProcessObservers();
    private DiLinkDiR_MultiDisplayActivityTopListeners d = new DiLinkDiR_MultiDisplayActivityTopListeners();
    private DiLinkDiR_ActivityListeners e = new DiLinkDiR_ActivityListeners();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IActivityManager f2148a = IActivityManager.Stub.asInterface(ServiceManager.getService("activity"));

    public static class DiLinkDiR_ActivityListeners extends DiLinkCallbackHolders<IDiLinkActivityListener, IActivityListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityListener createInnerCallback(final IDiLinkActivityListener iDiLinkActivityListener) {
            return new IActivityListener.Stub() { // from class: com.byd.dilink_DiR.app.am.DiLinkActivityManager.DiLinkDiR_ActivityListeners.1
                @Keep
                public void onActivityChanged(String str) {
                    iDiLinkActivityListener.onActivityChanged(str);
                }
            };
        }
    }

    public static class DiLinkDiR_ActivityTopListeners extends DiLinkCallbackHolders<IDiLinkActivityTopListener, IActivityTopListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityTopListener createInnerCallback(final IDiLinkActivityTopListener iDiLinkActivityTopListener) {
            return new IActivityTopListener.Stub() { // from class: com.byd.dilink_DiR.app.am.DiLinkActivityManager.DiLinkDiR_ActivityTopListeners.1
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

    public static class DiLinkDiR_MultiDisplayActivityTopListeners extends DiLinkCallbackHolders<IDiLinkMultiDisplayActivityTopListener, IActivityTopListenerMultiDisplay> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityTopListenerMultiDisplay createInnerCallback(final IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
            return new IActivityTopListenerMultiDisplay.Stub() { // from class: com.byd.dilink_DiR.app.am.DiLinkActivityManager.DiLinkDiR_MultiDisplayActivityTopListeners.1
                @Keep
                public void onTopActivityChanged(String str, int i) {
                    iDiLinkMultiDisplayActivityTopListener.onTopActivityChanged(str, i);
                }
            };
        }
    }

    public static class DiLinkDiR_ProcessObservers extends DiLinkCallbackHolders<IDiLinkProcessObserver, IProcessObserver> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IProcessObserver createInnerCallback(final IDiLinkProcessObserver iDiLinkProcessObserver) {
            return new IProcessObserver.Stub() { // from class: com.byd.dilink_DiR.app.am.DiLinkActivityManager.DiLinkDiR_ProcessObservers.1
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
    public /* synthetic */ void i(IActivityListener iActivityListener) {
        this.f2148a.registerActivityListener(iActivityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(IActivityTopListener iActivityTopListener) {
        this.f2148a.registerActivityTopListener(iActivityTopListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(IActivityTopListenerMultiDisplay iActivityTopListenerMultiDisplay) {
        this.f2148a.registerActivityTopListenerMultiDisplay(iActivityTopListenerMultiDisplay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(IProcessObserver iProcessObserver) {
        this.f2148a.registerProcessObserver(iProcessObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(IActivityListener iActivityListener) {
        this.f2148a.unregisterActivityListener(iActivityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(IActivityTopListener iActivityTopListener) {
        this.f2148a.unregisterActivityTopListener(iActivityTopListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(IActivityTopListenerMultiDisplay iActivityTopListenerMultiDisplay) {
        this.f2148a.unregisterActivityTopListenerMultiDisplay(iActivityTopListenerMultiDisplay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(IProcessObserver iProcessObserver) {
        this.f2148a.unregisterProcessObserver(iProcessObserver);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses() {
        return this.f2148a.getRunningAppProcesses();
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public String getTopPackageName() {
        return this.f2148a.getTopPackageName();
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void killApplicationEx(String str, int i, int i2, String str2) {
        this.f2148a.killApplicationEx(str, i, i2, str2);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void killApplicationProcess(String str, int i) {
        this.f2148a.killApplicationProcess(str, i);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityListener(IDiLinkActivityListener iDiLinkActivityListener) {
        this.e.addCallback(iDiLinkActivityListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.app.am.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2159a.i((IActivityListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener) {
        this.f2149b.addCallback(iDiLinkActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.app.am.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2161a.j((IActivityTopListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
        this.d.addCallback(iDiLinkMultiDisplayActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.app.am.f
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2164a.k((IActivityTopListenerMultiDisplay) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver) {
        this.f2150c.addCallback(iDiLinkProcessObserver, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.app.am.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2162a.l((IProcessObserver) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void scheduleApplicationInfoChanged(List<String> list, int i) {
        this.f2148a.scheduleApplicationInfoChanged(list, i);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityListener(IDiLinkActivityListener iDiLinkActivityListener) {
        this.e.removeCallback(iDiLinkActivityListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.app.am.h
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2166a.m((IActivityListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener) {
        this.f2149b.removeCallback(iDiLinkActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.app.am.e
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2163a.n((IActivityTopListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
        this.d.removeCallback(iDiLinkMultiDisplayActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.app.am.g
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2165a.o((IActivityTopListenerMultiDisplay) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver) {
        this.f2150c.removeCallback(iDiLinkProcessObserver, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.app.am.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2160a.p((IProcessObserver) obj);
            }
        });
    }
}
