package BA1aabBX.BA1aabBX.BA1aabBX.Xbb;

import android.content.Context;
import android.os.BydPerfManager;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.IDiLinkPrefManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkPrefManager.class, singleton = true)
public class bBB implements IDiLinkPrefManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BydPerfManager f12a;

    public bBB(Context context) {
        if (DiLink.isPlatform(DiLink.VERSION_NAME_5_0, false)) {
            this.f12a = (BydPerfManager) context.getSystemService("BYDMgmt");
        }
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventEnd(@Nullable String str) {
        this.f12a.noteEventEnd(str);
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventStart(@Nullable String str) {
        this.f12a.noteEventStart(str);
    }
}
