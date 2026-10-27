package RR9QRrr.RR9QRrr.RR9QRrr.r9R9RrrQr;

import android.content.Context;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBydIconUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkBydIconUtil.class, singleton = true)
public class R9rQ9R implements IDiLinkBydIconUtil {
    public R9rQ9R(Context context) {
    }

    @Override // com.byd.dilink.app.IDiLinkBydIconUtil
    public int customIcon(String str, @Nullable String str2) {
        throw new UnsupportedTargetPlatformException("rcs 没有 customIcon 这个函数！");
    }

    @Override // com.byd.dilink.app.IDiLinkBydIconUtil
    public boolean isR4ui() {
        throw new UnsupportedTargetPlatformException("rcs 没有 isR4ui 这个函数！");
    }
}
