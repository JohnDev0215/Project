package GGW.GGW.GGW.W4H.HG44hggH;

import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.Context;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkWallpaperManager.class, singleton = true)
public class GGW implements IDiLinkWallpaperManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WallpaperManager f48a;

    public GGW(Context context) {
        this.f48a = WallpaperManager.getInstance(context);
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo() {
        return this.f48a.getMultiWallpaperInfo();
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo(int i) {
        return this.f48a.getMultiWallpaperInfo(i);
    }
}
