package com.byd.dilink50.wm;

import android.content.Context;
import android.view.IDragDropedListener;
import android.view.IWindowManager;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.view.IDiLinkDragDropedListener;
import com.byd.dilink.view.IDiLinkWindowManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkWindowManager.class, singleton = true)
public class DiLinkWindowManager implements IDiLinkWindowManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WindowManager f1495a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> f1497c = new DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener>() { // from class: com.byd.dilink50.wm.DiLinkWindowManager.1
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IDragDropedListener createInnerCallback(final IDiLinkDragDropedListener iDiLinkDragDropedListener) {
            return new IDragDropedListener.Stub() { // from class: com.byd.dilink50.wm.DiLinkWindowManager.1.1
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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final IWindowManager f1496b = WindowManagerGlobal.getWindowManagerService();

    public DiLinkWindowManager(Context context) {
        this.f1495a = (WindowManager) context.getSystemService("window");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IDragDropedListener iDragDropedListener) {
        this.f1496b.registerDragDropControllerCallback(iDragDropedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IDragDropedListener iDragDropedListener) {
        this.f1496b.unregisterDragDropControllerCallback(iDragDropedListener);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void addCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        this.f1495a.addCrossWindowBlurEnabledListener(consumer);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    @WindowManager.DisplayImePolicy
    public int getDisplayImePolicyForMultiIme(int i) {
        throw new UnsupportedTargetPlatformException("Di5.0 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getPhysicalWindowOrientation() {
        return this.f1496b.getPhysicalWindowOrientation();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public boolean isCrossWindowBlurEnabled() {
        return this.f1495a.isCrossWindowBlurEnabled();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void registerDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f1497c.addCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.wm.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1502a.a((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void removeCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        this.f1495a.removeCrossWindowBlurEnabledListener(consumer);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void unregisterDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f1497c.removeCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.wm.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1501a.b((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void updateDragDropCurrentPosition(int i) {
        this.f1496b.updateDragDropCurrentPosition(i);
    }
}
