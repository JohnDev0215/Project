package sst.sst.sst.stST;

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

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkWindowManager.class, singleton = true)
public class t1sT implements IDiLinkWindowManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WindowManager f3231a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> f3233c = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final IWindowManager f3232b = WindowManagerGlobal.getWindowManagerService();

    public class a extends DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> {

        /* JADX INFO: renamed from: sst.sst.sst.stST.t1sT$a$a, reason: collision with other inner class name */
        public class C0090a extends IDragDropedListener.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IDiLinkDragDropedListener f3235a;

            public C0090a(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
                this.f3235a = iDiLinkDragDropedListener;
            }
        }

        public a() {
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IDragDropedListener createInnerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
            return new C0090a(iDiLinkDragDropedListener);
        }
    }

    public t1sT(Context context) {
        this.f3231a = (WindowManager) context.getSystemService("window");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IDragDropedListener iDragDropedListener) {
        this.f3232b.unregisterDragDropControllerCallback(iDragDropedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(IDragDropedListener iDragDropedListener) {
        this.f3232b.registerDragDropControllerCallback(iDragDropedListener);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void addCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        this.f3231a.addCrossWindowBlurEnabledListener(consumer);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getDisplayImePolicyForMultiIme(int i) {
        throw new UnsupportedTargetPlatformException("ries 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getPhysicalWindowOrientation() {
        return this.f3232b.getPhysicalWindowOrientation();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public boolean isCrossWindowBlurEnabled() {
        return this.f3231a.isCrossWindowBlurEnabled();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void registerDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f3233c.addCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: sst.sst.sst.stST.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f3229a.d((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void removeCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        this.f3231a.removeCrossWindowBlurEnabledListener(consumer);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void unregisterDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f3233c.removeCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: sst.sst.sst.stST.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f3230a.a((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void updateDragDropCurrentPosition(int i) {
        this.f3232b.updateDragDropCurrentPosition(i);
    }
}
