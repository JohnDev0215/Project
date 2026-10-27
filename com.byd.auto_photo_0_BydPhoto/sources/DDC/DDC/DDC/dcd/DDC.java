package DDC.DDC.DDC.dcd;

import android.app.BydDisplayUtil;
import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBydDisplayUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkBydDisplayUtil.class, singleton = true)
public class DDC implements IDiLinkBydDisplayUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BydDisplayUtil f40a;

    public DDC(Context context) {
        if (DiLink.isPlatform(DiLink.VERSION_NAME_5_1)) {
            this.f40a = BydDisplayUtil.sInstance(context);
        }
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public int getDisplayId(@NonNull String str) {
        return this.f40a.getDisplayId(str);
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public boolean isMultiUserDisplay() {
        return this.f40a.isMutilUserDisplay();
    }
}
