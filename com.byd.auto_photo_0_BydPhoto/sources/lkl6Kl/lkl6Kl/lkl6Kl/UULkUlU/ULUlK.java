package lkl6Kl.lkl6Kl.lkl6Kl.UULkUlU;

import android.content.Context;
import android.os.BydPerfManager;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.IDiLinkPrefManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkPrefManager.class, singleton = true)
public class ULUlK implements IDiLinkPrefManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BydPerfManager f3125a;

    public ULUlK(Context context) {
        if (DiLink.isPlatform(DiLink.VERSION_NAME_4_0, false)) {
            this.f3125a = (BydPerfManager) context.getSystemService("BYDMgmt");
        }
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventEnd(@Nullable String str) {
        this.f3125a.noteEventEnd(str);
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventStart(@Nullable String str) {
        this.f3125a.noteEventStart(str);
    }
}
