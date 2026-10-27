package So.So.So.pP8PP;

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
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkWindowManager.class, singleton = true)
public class So implements IDiLinkWindowManager {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WindowManager f157b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> f158c = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IWindowManager f156a = WindowManagerGlobal.getWindowManagerService();

    public class a extends DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> {

        /* JADX INFO: renamed from: So.So.So.pP8PP.So$a$a, reason: collision with other inner class name */
        public class C0003a extends IDragDropedListener.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IDiLinkDragDropedListener f160a;

            public C0003a(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
                this.f160a = iDiLinkDragDropedListener;
            }
        }

        public a() {
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IDragDropedListener createInnerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
            return new C0003a(iDiLinkDragDropedListener);
        }
    }

    public So(Context context) {
        this.f157b = (WindowManager) context.getSystemService("window");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IDragDropedListener iDragDropedListener) {
        this.f156a.unregisterDragDropControllerCallback(iDragDropedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IDragDropedListener iDragDropedListener) {
        this.f156a.registerDragDropControllerCallback(iDragDropedListener);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void addCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        this.f157b.addCrossWindowBlurEnabledListener(consumer);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getDisplayImePolicyForMultiIme(int i) {
        throw new UnsupportedTargetPlatformException("FSE 没有 getDisplayImePolicyForMultiIme 这个函数！");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getPhysicalWindowOrientation() {
        return this.f156a.getPhysicalWindowOrientation();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public boolean isCrossWindowBlurEnabled() {
        return this.f157b.isCrossWindowBlurEnabled();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void registerDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f158c.addCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: So.So.So.pP8PP.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f163a.b((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void removeCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        this.f157b.removeCrossWindowBlurEnabledListener(consumer);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void unregisterDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f158c.removeCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: So.So.So.pP8PP.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f162a.a((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void updateDragDropCurrentPosition(int i) {
        this.f156a.updateDragDropCurrentPosition(i);
    }
}
