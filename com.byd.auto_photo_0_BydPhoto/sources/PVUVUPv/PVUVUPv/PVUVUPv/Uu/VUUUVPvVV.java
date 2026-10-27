package PVUVUPv.PVUVUPv.PVUVUPv.uu;

import android.content.Context;
import android.view.IDragDropedListener;
import android.view.IWindowManager;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
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
@Platform(versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkWindowManager.class, singleton = true)
public class VUUUVPvVV implements IDiLinkWindowManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WindowManager f85a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> f87c = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final IWindowManager f86b = WindowManagerGlobal.getWindowManagerService();

    public class a extends DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> {

        /* JADX INFO: renamed from: PVUVUPv.PVUVUPv.PVUVUPv.uu.VUUUVPvVV$a$a, reason: collision with other inner class name */
        public class C0001a extends IDragDropedListener.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IDiLinkDragDropedListener f89a;

            public C0001a(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
                this.f89a = iDiLinkDragDropedListener;
            }
        }

        public a() {
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IDragDropedListener createInnerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
            return new C0001a(iDiLinkDragDropedListener);
        }
    }

    public VUUUVPvVV(Context context) {
        this.f85a = (WindowManager) context.getSystemService("window");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IDragDropedListener iDragDropedListener) {
        this.f86b.registerDragDropControllerCallback(iDragDropedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(IDragDropedListener iDragDropedListener) {
        this.f86b.unregisterDragDropControllerCallback(iDragDropedListener);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void addCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        throw new UnsupportedTargetPlatformException("RSE 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getDisplayImePolicyForMultiIme(int i) {
        throw new UnsupportedTargetPlatformException("RSE 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getPhysicalWindowOrientation() {
        return this.f86b.getPhysicalWindowOrientation();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public boolean isCrossWindowBlurEnabled() {
        throw new UnsupportedTargetPlatformException("RSE 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void registerDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f87c.addCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: PVUVUPv.PVUVUPv.PVUVUPv.uu.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f91a.a((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void removeCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        throw new UnsupportedTargetPlatformException("RSE 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void unregisterDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f87c.removeCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: PVUVUPv.PVUVUPv.PVUVUPv.uu.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f92a.d((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void updateDragDropCurrentPosition(int i) {
        this.f86b.updateDragDropCurrentPosition(i);
    }
}
