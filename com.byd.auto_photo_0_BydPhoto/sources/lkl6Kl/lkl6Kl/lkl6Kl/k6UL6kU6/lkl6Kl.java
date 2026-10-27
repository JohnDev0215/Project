package lkl6Kl.lkl6Kl.lkl6Kl.k6UL6kU6;

import android.content.Context;
import android.view.IWindowManager;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.view.IDiLinkDragDropedListener;
import com.byd.dilink.view.IDiLinkWindowManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkWindowManager.class, singleton = true)
public class lkl6Kl implements IDiLinkWindowManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WindowManager f3133a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final IWindowManager f3134b = WindowManagerGlobal.getWindowManagerService();

    public lkl6Kl(Context context) {
        this.f3133a = (WindowManager) context.getSystemService("window");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void addCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        throw new UnsupportedTargetPlatformException("Di4.0 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getDisplayImePolicyForMultiIme(int i) {
        throw new UnsupportedTargetPlatformException("Di4.0 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getPhysicalWindowOrientation() {
        return this.f3134b.getPhysicalWindowOrientation();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public boolean isCrossWindowBlurEnabled() {
        throw new UnsupportedTargetPlatformException("Di4.0 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void registerDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 registerDragDropControllerCallback！");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void removeCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        throw new UnsupportedTargetPlatformException("Di4.0 找不到对应接口");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void unregisterDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 unregisterDragDropControllerCallback！");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void updateDragDropCurrentPosition(int i) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 updateDragDropCurrentPosition！");
    }
}
