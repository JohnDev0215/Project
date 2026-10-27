package mNTT77n7n.mNTT77n7n.mNTT77n7n.MnmmTM7.m7nnMnn7m;

import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkWallpaperManager.class, singleton = true)
public class mNTT77n7n implements IDiLinkWallpaperManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WallpaperManager f3144a;

    public mNTT77n7n(Context context) {
        this.f3144a = WallpaperManager.getInstance(context);
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo() {
        return this.f3144a.getMultiWallpaperInfo();
    }

    @Override // com.byd.dilink.app.wallpaper.IDiLinkWallpaperManager
    public List<WallpaperInfo> getMultiWallpaperInfo(int i) {
        return this.f3144a.getMultiWallpaperInfo(i);
    }
}
