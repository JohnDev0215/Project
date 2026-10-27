package com.byd.dilink51_main.wm;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.view.IDragDropedListener;
import android.view.IWindowManager;
import android.view.WindowManager;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.view.IDiLinkDragDropedListener;
import com.byd.dilink.view.IDiLinkWindowManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkWindowManager.class, singleton = true)
public class DiLinkWindowManager implements IDiLinkWindowManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IWindowManager f1918a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WindowManager f1919b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> f1920c = new DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener>() { // from class: com.byd.dilink51_main.wm.DiLinkWindowManager.1
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IDragDropedListener createInnerCallback(final IDiLinkDragDropedListener iDiLinkDragDropedListener) {
            return new IDragDropedListener.Stub() { // from class: com.byd.dilink51_main.wm.DiLinkWindowManager.1.1
                @Keep
                public void onHideDrag() {
                    iDiLinkDragDropedListener.onHideDrag();
                }

                @Keep
                public void onShowDrag() {
                    iDiLinkDragDropedListener.onShowDrag();
                }
            };
        }
    };

    public DiLinkWindowManager(Context context) throws RemoteException {
        IBinder service = ServiceManager.getService("window");
        if (service == null) {
            throw new RemoteException("Cannot retrieve Window Manager");
        }
        this.f1919b = (WindowManager) context.getSystemService("window");
        this.f1918a = IWindowManager.Stub.asInterface(service);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IDragDropedListener iDragDropedListener) {
        this.f1918a.registerDragDropControllerCallback(iDragDropedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IDragDropedListener iDragDropedListener) {
        this.f1918a.unregisterDragDropControllerCallback(iDragDropedListener);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void addCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        this.f1919b.addCrossWindowBlurEnabledListener(consumer);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getDisplayImePolicyForMultiIme(int i) {
        return this.f1918a.getDisplayImePolicyForMultiIme(i);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getPhysicalWindowOrientation() {
        return this.f1918a.getPhysicalWindowOrientation();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public boolean isCrossWindowBlurEnabled() {
        return this.f1919b.isCrossWindowBlurEnabled();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void registerDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f1920c.addCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.wm.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1924a.a((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void removeCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        this.f1919b.removeCrossWindowBlurEnabledListener(consumer);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void unregisterDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f1920c.removeCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.wm.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1925a.b((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void updateDragDropCurrentPosition(int i) {
        this.f1918a.updateDragDropCurrentPosition(i);
    }
}
