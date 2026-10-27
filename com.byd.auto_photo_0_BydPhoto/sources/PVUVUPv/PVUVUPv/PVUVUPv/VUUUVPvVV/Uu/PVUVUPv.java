package PVUVUPv.PVUVUPv.PVUVUPv.VUUUVPvVV.Uu;

import android.app.WallpaperInfo;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkWallpaperManager.class, singleton = true)
public class PVUVUPv implements IDiLinkWallpaperManager {
    public PVUVUPv(Context context) {
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo() {
        throw new UnsupportedTargetPlatformException("RSE 没有 getMultiWallpaperInfo 这个函数！");
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo(int i) {
        throw new UnsupportedTargetPlatformException("RSE 没有 getMultiWallpaperInfo(int userId) 这个函数！");
    }
}
