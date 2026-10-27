package sst.sst.sst.StsT;

import android.app.BydIconUtil;
import android.content.Context;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.MemorySafeContext;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBydIconUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkBydIconUtil.class, singleton = true)
public class SssTQ implements IDiLinkBydIconUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f3215a;

    public SssTQ(Context context) {
        this.f3215a = context.getApplicationContext();
    }

    @Override // com.byd.dilink.app.IDiLinkBydIconUtil
    public int customIcon(String str, @Nullable String str2) {
        return BydIconUtil.customIcon(MemorySafeContext.get(this.f3215a), str, str2);
    }

    @Override // com.byd.dilink.app.IDiLinkBydIconUtil
    public boolean isR4ui() {
        throw new UnsupportedTargetPlatformException("ries 没有 isR4ui 这个函数！");
    }
}
