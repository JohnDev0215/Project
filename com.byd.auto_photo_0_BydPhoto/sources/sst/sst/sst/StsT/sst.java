package sst.sst.sst.StsT;

import android.app.BydDisplayUtil;
import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.MemorySafeContext;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBydDisplayUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkBydDisplayUtil.class, singleton = true)
public class sst implements IDiLinkBydDisplayUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BydDisplayUtil f3216a;

    public sst(Context context) {
        this.f3216a = BydDisplayUtil.sInstance(MemorySafeContext.get(context));
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public int getDisplayId(@NonNull String str) {
        return this.f3216a.getDisplayId(str);
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public boolean isMultiUserDisplay() {
        throw new UnsupportedTargetPlatformException("ries 没有 BydDisplayUtil 这个类！");
    }
}
