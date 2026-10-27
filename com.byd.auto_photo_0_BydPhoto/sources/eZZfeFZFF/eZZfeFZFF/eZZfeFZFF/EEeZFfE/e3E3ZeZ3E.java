package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.EEeZFfE;

import android.app.BydIconUtil;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBydIconUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkBydIconUtil.class, singleton = true)
public class e3E3ZeZ3E implements IDiLinkBydIconUtil {
    @Override // com.byd.dilink.app.IDiLinkBydIconUtil
    public int customIcon(@NonNull String str, @Nullable String str2) {
        return BydIconUtil.customIcon(str, str2);
    }

    @Override // com.byd.dilink.app.IDiLinkBydIconUtil
    public boolean isR4ui() {
        throw new UnsupportedTargetPlatformException("Di5.1 没有 isR4ui 这个函数！");
    }
}
