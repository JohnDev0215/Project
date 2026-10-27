package So.So.So.PopS8Sp;

import android.app.BydIconUtil;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBydIconUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkBydIconUtil.class, singleton = true)
public class PoOSp implements IDiLinkBydIconUtil {
    @Override // com.byd.dilink.app.IDiLinkBydIconUtil
    public int customIcon(@NonNull String str, @Nullable String str2) {
        return BydIconUtil.customIcon(str, str2);
    }

    @Override // com.byd.dilink.app.IDiLinkBydIconUtil
    public boolean isR4ui() {
        throw new UnsupportedTargetPlatformException("FSE 没有 isR4ui 这个函数！");
    }
}
