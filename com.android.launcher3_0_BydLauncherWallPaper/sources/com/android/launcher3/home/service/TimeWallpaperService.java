package com.android.launcher3.home.service;

import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;
import com.android.launcher3.home.HomeApplication;
import com.android.launcher3.views.TimeWallpaperView;

/* JADX INFO: loaded from: classes4.dex */
public class TimeWallpaperService extends WallpaperService {
    private TimeWallpaperEngine timeWallpaperEngine;

    @Override // android.service.wallpaper.WallpaperService
    public WallpaperService.Engine onCreateEngine() {
        Log.e("pengbin", "TimeWallpaperService......onCreateEngine");
        if (HomeApplication.getInstance().getOnApplicationCallBack() != null) {
            HomeApplication.getInstance().getOnApplicationCallBack().onCancelStartAnimate();
        }
        TimeWallpaperEngine timeWallpaperEngine = new TimeWallpaperEngine();
        this.timeWallpaperEngine = timeWallpaperEngine;
        return timeWallpaperEngine;
    }

    private class TimeWallpaperEngine extends WallpaperService.Engine {
        private final SurfaceHolder surfaceHolder;
        private TimeWallpaperView timeWallpaperView;

        public TimeWallpaperEngine() {
            super(TimeWallpaperService.this);
            this.surfaceHolder = getSurfaceHolder();
            this.timeWallpaperView = new TimeWallpaperView(TimeWallpaperService.this.getBaseContext());
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
        }

        private void drawView() {
            TimeWallpaperView timeWallpaperView = this.timeWallpaperView;
            if (timeWallpaperView == null) {
                return;
            }
            timeWallpaperView.surfaceChanged(this.surfaceHolder, -1, timeWallpaperView.getWidth(), this.timeWallpaperView.getHeight());
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            TimeWallpaperView timeWallpaperView = this.timeWallpaperView;
            if (timeWallpaperView != null) {
                timeWallpaperView.surfaceCreated(getSurfaceHolder());
            }
            drawView();
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            Log.e("pengbin", "TimeWallpaperService......onSurfaceDestroyed");
            TimeWallpaperView timeWallpaperView = this.timeWallpaperView;
            if (timeWallpaperView != null) {
                timeWallpaperView.surfaceDestroyed(holder);
            }
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onDestroy() {
            super.onDestroy();
            Log.e("pengbin", "TimeWallpaperService......onDestroy");
        }
    }
}
