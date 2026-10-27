package lkl6Kl.lkl6Kl.lkl6Kl.K6;

import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBydDisplayUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkBydDisplayUtil.class, singleton = true)
public class LK6LklLk implements IDiLinkBydDisplayUtil {
    public LK6LklLk(Context context) {
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public int getDisplayId(@NonNull String str) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 BydDisplayUtil 这个类！");
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public boolean isMultiUserDisplay() {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 BydDisplayUtil 这个类！");
    }
}
