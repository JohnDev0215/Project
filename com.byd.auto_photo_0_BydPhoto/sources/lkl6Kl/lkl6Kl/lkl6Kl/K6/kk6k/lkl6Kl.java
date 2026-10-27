package lkl6Kl.lkl6Kl.lkl6Kl.K6.kk6k;

import android.app.WallpaperInfo;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkWallpaperManager.class, singleton = true)
public class lkl6Kl implements IDiLinkWallpaperManager {
    public lkl6Kl(Context context) {
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo() {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 getMultiWallpaperInfo 这个函数！");
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo(int i) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 getMultiWallpaperInfo(int userId) 这个函数！");
    }
}
