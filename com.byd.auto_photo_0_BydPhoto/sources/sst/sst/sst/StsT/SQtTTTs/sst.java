package sst.sst.sst.StsT.SQtTTTs;

import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.MemorySafeContext;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkWallpaperManager.class, singleton = true)
public class sst implements IDiLinkWallpaperManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WallpaperManager f3214a;

    public sst(Context context) {
        this.f3214a = WallpaperManager.getInstance(MemorySafeContext.get(context));
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo() {
        return this.f3214a.getMultiWallpaperInfo();
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo(int i) {
        return this.f3214a.getMultiWallpaperInfo(i);
    }
}
