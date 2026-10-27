package PVUVUPv.PVUVUPv.PVUVUPv.vv22vv;

import android.content.Context;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.IDiLinkPrefManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkPrefManager.class, singleton = true)
public class VUUUVPvVV implements IDiLinkPrefManager {
    public VUUUVPvVV(Context context) {
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventEnd(@Nullable String str) {
        throw new UnsupportedTargetPlatformException("RSE 没有 BydPerfManager 类！");
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventStart(@Nullable String str) {
        throw new UnsupportedTargetPlatformException("RSE 没有 BydPerfManager 类！");
    }
}
