package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.EEeZFfE.fFFZ;

import android.app.BydWallpaperManager;
import android.app.WallpaperInfo;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkWallpaperManager.class, singleton = true)
public class eZZfeFZFF implements IDiLinkWallpaperManager {
    public eZZfeFZFF(Context context) {
        if (DiLink.isPlatform(DiLink.VERSION_NAME_5_1, true)) {
            BydWallpaperManager.getInstance().setContext(context);
        }
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo() {
        return BydWallpaperManager.getInstance().getMultiWallpaperInfo();
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo(int i) {
        return BydWallpaperManager.getInstance().getMultiWallpaperInfo(i);
    }
}
