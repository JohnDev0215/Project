package jViVV55i.jViVV55i.jViVV55i.ViiVJJIi.IiJI5I55;

import android.app.WallpaperInfo;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkWallpaperManager.class, singleton = true)
public class jViVV55i implements IDiLinkWallpaperManager {
    public jViVV55i(Context context) {
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 getMultiWallpaperInfo 这个函数！");
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 getMultiWallpaperInfo(int userId) 这个函数！");
    }
}
