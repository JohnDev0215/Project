package RR9QRrr.RR9QRrr.RR9QRrr.r9R9RrrQr.R9R;

import android.app.WallpaperInfo;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkWallpaperManager.class, singleton = true)
public class RR9QRrr implements IDiLinkWallpaperManager {
    public RR9QRrr(Context context) {
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo() {
        throw new UnsupportedTargetPlatformException("rcs 没有 getMultiWallpaperInfo 这个函数！");
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo(int i) {
        throw new UnsupportedTargetPlatformException("rcs 没有 getMultiWallpaperInfo(int userId) 这个函数！");
    }
}
