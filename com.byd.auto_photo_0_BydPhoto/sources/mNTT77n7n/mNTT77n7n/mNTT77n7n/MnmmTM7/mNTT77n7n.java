package mNTT77n7n.mNTT77n7n.mNTT77n7n.MnmmTM7;

import android.app.BydDisplayUtil;
import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBydDisplayUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkBydDisplayUtil.class, singleton = true)
public class mNTT77n7n implements IDiLinkBydDisplayUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BydDisplayUtil f3145a;

    public mNTT77n7n(Context context) {
        if (DiLink.isPlatform(DiLink.VERSION_NAME_DiR_RUI)) {
            this.f3145a = BydDisplayUtil.sInstance(context);
        }
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public int getDisplayId(@NonNull String str) {
        return this.f3145a.getDisplayId(str);
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public boolean isMultiUserDisplay() {
        return this.f3145a.isMutilUserDisplay();
    }
}
