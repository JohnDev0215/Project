package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FEFZ3E3FZ;

import android.content.Context;
import android.view.BydWindowManager;
import android.view.IDragDropedListener;
import android.view.WindowManager;
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
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkWindowManager.class, singleton = true)
public class eZZfeFZFF implements IDiLinkWindowManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WindowManager f3033a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> f3034b = new a();

    public class a extends DiLinkCallbackHolders<IDiLinkDragDropedListener, IDragDropedListener> {

        /* JADX INFO: renamed from: eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FEFZ3E3FZ.eZZfeFZFF$a$a, reason: collision with other inner class name */
        public class C0084a extends IDragDropedListener.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IDiLinkDragDropedListener f3036a;

            public C0084a(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
                this.f3036a = iDiLinkDragDropedListener;
            }
        }

        public a() {
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IDragDropedListener createInnerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
            return new C0084a(iDiLinkDragDropedListener);
        }
    }

    public eZZfeFZFF(Context context) {
        this.f3033a = (WindowManager) context.getSystemService("window");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(IDragDropedListener iDragDropedListener) {
        BydWindowManager.getInstance().unregisterDragDropControllerCallback(iDragDropedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(IDragDropedListener iDragDropedListener) {
        BydWindowManager.getInstance().registerDragDropControllerCallback(iDragDropedListener);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void addCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        this.f3033a.addCrossWindowBlurEnabledListener(consumer);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getDisplayImePolicyForMultiIme(int i) {
        return BydWindowManager.getInstance().getDisplayImePolicyForMultiIme(i);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getPhysicalWindowOrientation() {
        return BydWindowManager.getInstance().getPhysicalWindowOrientation();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public boolean isCrossWindowBlurEnabled() {
        return this.f3033a.isCrossWindowBlurEnabled();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void registerDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f3034b.addCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FEFZ3E3FZ.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                eZZfeFZFF.b((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void removeCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        this.f3033a.removeCrossWindowBlurEnabledListener(consumer);
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void unregisterDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        this.f3034b.removeCallback(iDiLinkDragDropedListener, new IDiLinkCallbackHandler() { // from class: eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FEFZ3E3FZ.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                eZZfeFZFF.a((IDragDropedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void updateDragDropCurrentPosition(int i) {
        BydWindowManager.getInstance().updateDragDropCurrentPosition(i);
    }
}
