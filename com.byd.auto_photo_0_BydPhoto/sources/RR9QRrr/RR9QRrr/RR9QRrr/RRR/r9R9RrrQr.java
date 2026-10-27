package RR9QRrr.RR9QRrr.RR9QRrr.RRR;

import android.content.Context;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.IDiLinkPrefManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkPrefManager.class, singleton = true)
public class r9R9RrrQr implements IDiLinkPrefManager {
    public r9R9RrrQr(Context context) {
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventEnd(@Nullable String str) {
        throw new UnsupportedTargetPlatformException("rcs 没有 BydPerfManager 类！");
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventStart(@Nullable String str) {
        throw new UnsupportedTargetPlatformException("rcs 没有 BydPerfManager 类！");
    }
}
