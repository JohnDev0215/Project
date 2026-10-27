package DDC.DDC.DDC.dcd.DcDc2c22Y;

import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkWallpaperManager.class, singleton = true)
public class DDC implements IDiLinkWallpaperManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WallpaperManager f41a;

    public DDC(Context context) {
        this.f41a = WallpaperManager.getInstance(context);
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo() {
        return this.f41a.getMultiWallpaperInfo();
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo(int i) {
        return this.f41a.getMultiWallpaperInfo(i);
    }
}
