package RR9QRrr.RR9QRrr.RR9QRrr.qRr;

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
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkWindowManager.class, singleton = true)
public class RR9QRrr implements IDiLinkWindowManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WindowManager f123a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> f125c = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final IWindowManager f124b = WindowManagerGlobal.getWindowManagerService();

    public class a extends DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> {

        /* JADX INFO: renamed from: RR9QRrr.RR9QRrr.RR9QRrr.qRr.RR9QRrr$a$a, reason: collision with other inner class name */
        public class C0002a extends IDragDropedListener.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IDiLinkDragDropedListener f127a;

            public C0002a(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
                this.f127a = iDiLinkDragDropedListener;
            }
        }

        public a() {
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IDragDropedListener createInnerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
            return new C0002a(iDiLinkDragDropedListener);
        }
    }

    public RR9QRrr(Context context) {
        this.f123a = (WindowManager) context.getSystemService("window");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IDragDropedListener iDragDropedListener) {
        this.f124b.unregisterDragDropControllerCallback(iDragDropedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IDragDropedListener iDragDropedListener) {
        this.f124b.registerDragDropControllerCallback(iDragDropedListener);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void addCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        throw new UnsupportedTargetPlatformException("rcs 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getDisplayImePolicyForMultiIme(int i) {
        throw new UnsupportedTargetPlatformException("rcs 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getPhysicalWindowOrientation() {
        return this.f124b.getPhysicalWindowOrientation();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public boolean isCrossWindowBlurEnabled() {
        throw new UnsupportedTargetPlatformException("rcs 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void registerDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f125c.addCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: RR9QRrr.RR9QRrr.RR9QRrr.qRr.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f130a.b((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void removeCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        throw new UnsupportedTargetPlatformException("rcs 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void unregisterDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f125c.removeCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: RR9QRrr.RR9QRrr.RR9QRrr.qRr.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f129a.a((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void updateDragDropCurrentPosition(int i) {
        throw new UnsupportedTargetPlatformException("rcs不存在updateDragDropCurrentPosition(int dx)方法");
    }
}
