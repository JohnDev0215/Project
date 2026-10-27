package jViVV55i.jViVV55i.jViVV55i.ViiVJJIi;

import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBydDisplayUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkBydDisplayUtil.class, singleton = true)
public class jViVV55i implements IDiLinkBydDisplayUtil {
    public jViVV55i(Context context) {
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public int getDisplayId(@NonNull String str) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BydDisplayUtil 这个类！");
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public boolean isMultiUserDisplay() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BydDisplayUtil 这个类！");
    }
}
