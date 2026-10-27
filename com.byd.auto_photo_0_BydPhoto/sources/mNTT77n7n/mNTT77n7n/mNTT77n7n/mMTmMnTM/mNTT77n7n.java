package mNTT77n7n.mNTT77n7n.mNTT77n7n.mMTmMnTM;

import android.content.Context;
import android.view.IDragDropedListener;
import android.view.IWindowManager;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.view.IDiLinkDragDropedListener;
import com.byd.dilink.view.IDiLinkWindowManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkWindowManager.class, singleton = true)
public class mNTT77n7n implements IDiLinkWindowManager {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WindowManager f3153b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> f3154c = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IWindowManager f3152a = WindowManagerGlobal.getWindowManagerService();

    public class a extends DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> {

        /* JADX INFO: renamed from: mNTT77n7n.mNTT77n7n.mNTT77n7n.mMTmMnTM.mNTT77n7n$a$a, reason: collision with other inner class name */
        public class C0089a extends IDragDropedListener.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IDiLinkDragDropedListener f3156a;

            public C0089a(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
                this.f3156a = iDiLinkDragDropedListener;
            }
        }

        public a() {
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IDragDropedListener createInnerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
            return new C0089a(iDiLinkDragDropedListener);
        }
    }

    public mNTT77n7n(Context context) {
        this.f3153b = (WindowManager) context.getSystemService("window");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(IDragDropedListener iDragDropedListener) {
        this.f3152a.registerDragDropControllerCallback(iDragDropedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(IDragDropedListener iDragDropedListener) {
        this.f3152a.unregisterDragDropControllerCallback(iDragDropedListener);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void addCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        this.f3153b.addCrossWindowBlurEnabledListener(consumer);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getDisplayImePolicyForMultiIme(int i) {
        return this.f3152a.getDisplayImePolicyForMultiIme(i);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getPhysicalWindowOrientation() {
        return this.f3152a.getPhysicalWindowOrientation();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public boolean isCrossWindowBlurEnabled() {
        return this.f3153b.isCrossWindowBlurEnabled();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void registerDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f3154c.addCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: mNTT77n7n.mNTT77n7n.mNTT77n7n.mMTmMnTM.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f3150a.c((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void removeCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        this.f3153b.removeCrossWindowBlurEnabledListener(consumer);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void unregisterDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f3154c.removeCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: mNTT77n7n.mNTT77n7n.mNTT77n7n.mMTmMnTM.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f3151a.d((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void updateDragDropCurrentPosition(int i) {
        this.f3152a.updateDragDropCurrentPosition(i);
    }
}
