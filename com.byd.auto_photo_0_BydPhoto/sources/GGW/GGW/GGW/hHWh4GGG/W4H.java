package GGW.GGW.GGW.hHWh4GGG;

import android.content.Context;
import android.os.BydPerfManager;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.IDiLinkPrefManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkPrefManager.class, singleton = true)
public class W4H implements IDiLinkPrefManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BydPerfManager f62a;

    public W4H(Context context) {
        if (DiLink.isPlatform("RUI", false)) {
            this.f62a = (BydPerfManager) context.getSystemService("BYDMgmt");
        }
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventEnd(@Nullable String str) {
        this.f62a.noteEventEnd(str);
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventStart(@Nullable String str) {
        this.f62a.noteEventStart(str);
    }
}
