package com.android.launcher3.home.service;

import android.content.res.Configuration;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;
import com.android.launcher3.home.HomeApplication;
import com.android.launcher3.util.Logger;
import com.android.launcher3.views.LiveWallpaperView;

/* JADX INFO: loaded from: classes4.dex */
public class LiveWallpaperService extends WallpaperService {
    private LiveWallpaperEngine liveWallpaperEngine;

    @Override // android.service.wallpaper.WallpaperService
    public WallpaperService.Engine onCreateEngine() {
        Log.e("pengbin", "LiveWallpaperService......onCreateEngine");
        if (HomeApplication.getInstance().getOnApplicationCallBack() != null) {
            HomeApplication.getInstance().getOnApplicationCallBack().onCancelStartAnimate();
        }
        LiveWallpaperEngine liveWallpaperEngine = new LiveWallpaperEngine();
        this.liveWallpaperEngine = liveWallpaperEngine;
        return liveWallpaperEngine;
    }

    private class LiveWallpaperEngine extends WallpaperService.Engine {
        private LiveWallpaperView liveWallpaperView;
        private final SurfaceHolder surfaceHolder;

        public LiveWallpaperEngine() {
            super(LiveWallpaperService.this);
            this.surfaceHolder = getSurfaceHolder();
            this.liveWallpaperView = new LiveWallpaperView(LiveWallpaperService.this.getBaseContext());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void drawView() {
            LiveWallpaperView liveWallpaperView = this.liveWallpaperView;
            if (liveWallpaperView == null) {
                return;
            }
            liveWallpaperView.surfaceChanged(this.surfaceHolder, -1, liveWallpaperView.getWidth(), this.liveWallpaperView.getHeight());
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            LiveWallpaperView liveWallpaperView = this.liveWallpaperView;
            if (liveWallpaperView != null) {
                liveWallpaperView.surfaceCreated(getSurfaceHolder());
            }
            drawView();
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            drawView();
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            LiveWallpaperView liveWallpaperView = this.liveWallpaperView;
            if (liveWallpaperView != null) {
                liveWallpaperView.surfaceDestroyed(holder);
            }
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onDestroy() {
            super.onDestroy();
            Log.e("pengbin", "LiveWallpaperEngine......onDestroy");
        }
    }

    @Override // android.service.wallpaper.WallpaperService, android.app.Service
    public void onDestroy() {
        Logger.e("pengbin", "LiveWallpaperService.....onDestroy");
        super.onDestroy();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        LiveWallpaperEngine liveWallpaperEngine;
        super.onConfigurationChanged(newConfig);
        int i = newConfig.uiMode & 48;
        if ((i == 32 || i == 16) && (liveWallpaperEngine = this.liveWallpaperEngine) != null) {
            liveWallpaperEngine.drawView();
        }
    }
}
