package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.EEeZFfE;

import android.app.BydDisplayUtil;
import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBydDisplayUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkBydDisplayUtil.class, singleton = true)
public class eZZfeFZFF implements IDiLinkBydDisplayUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BydDisplayUtil f3027a;

    public eZZfeFZFF(Context context) {
        if (DiLink.isPlatform(DiLink.VERSION_NAME_5_1)) {
            this.f3027a = BydDisplayUtil.sInstance(context);
        }
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public int getDisplayId(@NonNull String str) {
        return this.f3027a.getDisplayId(str);
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public boolean isMultiUserDisplay() {
        return this.f3027a.isMutilUserDisplay();
    }
}
