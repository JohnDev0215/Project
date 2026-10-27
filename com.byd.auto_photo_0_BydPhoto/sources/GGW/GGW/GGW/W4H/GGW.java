package GGW.GGW.GGW.W4H;

import android.app.BydDisplayUtil;
import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBydDisplayUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkBydDisplayUtil.class, singleton = true)
public class GGW implements IDiLinkBydDisplayUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BydDisplayUtil f47a;

    public GGW(Context context) {
        if (DiLink.isPlatform("RUI")) {
            this.f47a = BydDisplayUtil.sInstance(context);
        }
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public int getDisplayId(@NonNull String str) {
        return this.f47a.getDisplayId(str);
    }

    @Override // com.byd.dilink.app.IDiLinkBydDisplayUtil
    public boolean isMultiUserDisplay() {
        return this.f47a.isMutilUserDisplay();
    }
}
