package xxWX.xxWX.xxWX.WW3x3x;

import android.content.Context;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.IDiLinkPrefManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkPrefManager.class, singleton = true)
public class WX3xWXwxW implements IDiLinkPrefManager {
    public WX3xWXwxW(Context context) {
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventEnd(@Nullable String str) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有BydPerfManager类!");
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventStart(@Nullable String str) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有BydPerfManager类!");
    }
}
