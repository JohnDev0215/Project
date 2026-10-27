package PVUVUPv.PVUVUPv.PVUVUPv.VUUUVPvVV;

import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBydDisplayUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkBydDisplayUtil.class, singleton = true)
public class PVUVUPv implements IDiLinkBydDisplayUtil {
    public PVUVUPv(Context context) {
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public int getDisplayId(@NonNull String str) {
        throw new UnsupportedTargetPlatformException("RSE 没有 BydDisplayUtil 这个类！");
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public boolean isMultiUserDisplay() {
        throw new UnsupportedTargetPlatformException("RSE 没有 BydDisplayUtil 这个类！");
    }
}
