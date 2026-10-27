package jViVV55i.jViVV55i.jViVV55i.ii55;

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
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkWindowManager.class, singleton = true)
public class jViVV55i implements IDiLinkWindowManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WindowManager f3062a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final IWindowManager f3063b = WindowManagerGlobal.getWindowManagerService();

    public jViVV55i(Context context) {
        this.f3062a = (WindowManager) context.getSystemService("window");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void addCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 addCrossWindowBlurEnabledListener 方法！");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getDisplayImePolicyForMultiIme(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 getDisplayImePolicyForMultiIme 方法！");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public int getPhysicalWindowOrientation() {
        return this.f3063b.getPhysicalWindowOrientation();
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public boolean isCrossWindowBlurEnabled() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 isCrossWindowBlurEnabled 方法！");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void registerDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 registerDragDropControllerCallback 方法！");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void removeCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 removeCrossWindowBlurEnabledListener 方法！");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void unregisterDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 UnsupportedTargetPlatformException 方法！");
    }

    @Override // com.byd.dilink.view.IDiLinkWindowManager
    public void updateDragDropCurrentPosition(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 updateDragDropCurrentPosition 方法！");
    }
}
