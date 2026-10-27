package GGW.GGW.GGW.W4H;

import android.app.BydIconUtil;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBydIconUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkBydIconUtil.class, singleton = true)
public class Gg4HHGg44 implements IDiLinkBydIconUtil {
    @Override // com.byd.dilink.app.IDiLinkBydIconUtil
    public int customIcon(@NonNull String str, @Nullable String str2) {
        return BydIconUtil.customIcon(str, str2);
    }

    @Override // com.byd.dilink.app.IDiLinkBydIconUtil
    public boolean isR4ui() {
        return BydIconUtil.isR4ui();
    }
}
