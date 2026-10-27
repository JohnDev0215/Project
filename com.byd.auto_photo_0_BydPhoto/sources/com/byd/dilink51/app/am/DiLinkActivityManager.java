package com.byd.dilink51.app.am;

import android.app.ActivityManager;
import android.app.BydActivityManager;
import android.app.IActivityListener;
import android.app.IActivityManager;
import android.app.IActivityTopListener;
import android.app.IActivityTopListenerMultiDisplay;
import android.app.IProcessObserver;
import android.os.ServiceManager;
import android.util.Log;
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
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkActivityManager.class, singleton = true)
public class DiLinkActivityManager implements IDiLinkActivityManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLink5_1_ActivityTopListeners f1515a = new DiLink5_1_ActivityTopListeners();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f1516b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f1517c = new a();
    private DiLink5_1_ProcessObservers d = new DiLink5_1_ProcessObservers();

    public static class DiLink5_1_ActivityTopListeners extends DiLinkCallbackHolders<IDiLinkActivityTopListener, IActivityTopListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityTopListener createInnerCallback(final IDiLinkActivityTopListener iDiLinkActivityTopListener) {
            return new IActivityTopListener.Stub() { // from class: com.byd.dilink51.app.am.DiLinkActivityManager.DiLink5_1_ActivityTopListeners.1
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

    public static class DiLink5_1_ProcessObservers extends DiLinkCallbackHolders<IDiLinkProcessObserver, IProcessObserver> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IProcessObserver createInnerCallback(final IDiLinkProcessObserver iDiLinkProcessObserver) {
            return new IProcessObserver.Stub() { // from class: com.byd.dilink51.app.am.DiLinkActivityManager.DiLink5_1_ProcessObservers.1
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

    public static class a extends DiLinkCallbackHolders<IDiLinkMultiDisplayActivityTopListener, IActivityTopListenerMultiDisplay> {

        /* JADX INFO: renamed from: com.byd.dilink51.app.am.DiLinkActivityManager$a$a, reason: collision with other inner class name */
        public class IActivityTopListenerMultiDisplayStubC0040a extends IActivityTopListenerMultiDisplay.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IDiLinkMultiDisplayActivityTopListener f1522a;

            public IActivityTopListenerMultiDisplayStubC0040a(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
                this.f1522a = iDiLinkMultiDisplayActivityTopListener;
            }

            public void onTopActivityChanged(String str, int i) {
                this.f1522a.onTopActivityChanged(str, i);
            }
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityTopListenerMultiDisplay createInnerCallback(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
            return new IActivityTopListenerMultiDisplayStubC0040a(iDiLinkMultiDisplayActivityTopListener);
        }
    }

    public static class b extends DiLinkCallbackHolders<IDiLinkActivityListener, IActivityListener> {

        public class a extends IActivityListener.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IDiLinkActivityListener f1524a;

            public a(IDiLinkActivityListener iDiLinkActivityListener) {
                this.f1524a = iDiLinkActivityListener;
            }

            public void onActivityChanged(String str) {
                this.f1524a.onActivityChanged(str);
            }
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IActivityListener createInnerCallback(IDiLinkActivityListener iDiLinkActivityListener) {
            return new a(iDiLinkActivityListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(IActivityListener iActivityListener) {
        BydActivityManager.getInstance().unregisterActivityListener(iActivityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(IActivityTopListener iActivityTopListener) {
        BydActivityManager.getInstance().unregisterActivityTopListener(iActivityTopListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(IActivityTopListenerMultiDisplay iActivityTopListenerMultiDisplay) {
        BydActivityManager.getInstance().unregisterActivityTopListenerMultiDisplay(iActivityTopListenerMultiDisplay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(IProcessObserver iProcessObserver) {
        IActivityManager.Stub.asInterface(ServiceManager.getService("activity")).unregisterProcessObserver(iProcessObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e(IActivityListener iActivityListener) {
        BydActivityManager.getInstance().registerActivityListener(iActivityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void f(IActivityTopListener iActivityTopListener) {
        BydActivityManager.getInstance().registerActivityTopListener(iActivityTopListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void g(IActivityTopListenerMultiDisplay iActivityTopListenerMultiDisplay) {
        BydActivityManager.getInstance().registerActivityTopListenerMultiDisplay(iActivityTopListenerMultiDisplay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void h(IProcessObserver iProcessObserver) {
        IActivityManager.Stub.asInterface(ServiceManager.getService("activity")).registerProcessObserver(iProcessObserver);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses() {
        return IActivityManager.Stub.asInterface(ServiceManager.getService("activity")).getRunningAppProcesses();
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public String getTopPackageName() {
        Log.d("TAG", "BydActivityManager: ");
        return BydActivityManager.getInstance().getTopPackageName();
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void killApplicationEx(String str, int i, int i2, String str2) {
        BydActivityManager.getInstance().killApplicationEx(str, i, i2, str2);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void killApplicationProcess(String str, int i) {
        IActivityManager.Stub.asInterface(ServiceManager.getService("activity")).killApplicationProcess(str, i);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityListener(IDiLinkActivityListener iDiLinkActivityListener) {
        this.f1516b.addCallback(iDiLinkActivityListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.app.am.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkActivityManager.e((IActivityListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener) {
        this.f1515a.addCallback(iDiLinkActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.app.am.h
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkActivityManager.f((IActivityTopListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
        this.f1517c.addCallback(iDiLinkMultiDisplayActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.app.am.e
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkActivityManager.g((IActivityTopListenerMultiDisplay) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void registerProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver) {
        this.d.addCallback(iDiLinkProcessObserver, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.app.am.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkActivityManager.h((IProcessObserver) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void scheduleApplicationInfoChanged(List<String> list, int i) {
        IActivityManager.Stub.asInterface(ServiceManager.getService("activity")).scheduleApplicationInfoChanged(list, i);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityListener(IDiLinkActivityListener iDiLinkActivityListener) {
        this.f1516b.removeCallback(iDiLinkActivityListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.app.am.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkActivityManager.a((IActivityListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener) {
        this.f1515a.removeCallback(iDiLinkActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.app.am.f
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkActivityManager.b((IActivityTopListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener) {
        this.f1517c.removeCallback(iDiLinkMultiDisplayActivityTopListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.app.am.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkActivityManager.c((IActivityTopListenerMultiDisplay) obj);
            }
        });
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityManager
    public void unregisterProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver) {
        this.d.removeCallback(iDiLinkProcessObserver, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.app.am.g
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkActivityManager.d((IProcessObserver) obj);
            }
        });
    }
}
