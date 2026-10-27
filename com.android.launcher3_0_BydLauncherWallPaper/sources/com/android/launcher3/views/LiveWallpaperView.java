package com.android.launcher3.views;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.AlphaAnimation;
import com.android.launcher3.R;
import com.android.launcher3.home.Constants;
import com.android.launcher3.home.pageutil.PreferencesUtils;
import com.android.launcher3.util.Logger;

/* JADX INFO: loaded from: classes4.dex */
public class LiveWallpaperView extends SurfaceView implements SurfaceHolder.Callback {
    private Bitmap bitmap;
    private Context mContext;
    private Paint mPaint;
    WallpaperManager wallpaperManager;

    private void initWallpaperList() {
    }

    public LiveWallpaperView(Context context) {
        super(context);
        this.mContext = context;
        initWallpaperList();
        initPaintConfig();
        this.wallpaperManager = WallpaperManager.getInstance(context);
    }

    private void initPaintConfig() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(5.0f);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        Logger.e("pengbin", "LiveWallpaperView........surfaceCreated");
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(200L);
        startAnimation(alphaAnimation);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Logger.e("pengbin", "LiveWallpaperView........surfaceChanged");
        drawSurfaceView(holder);
    }

    private void drawSurfaceView(SurfaceHolder holder) {
        Canvas canvasLockCanvas;
        Bitmap bitmap = this.bitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.bitmap.recycle();
        }
        int i = PreferencesUtils.getInt(this.mContext, Constants.WALLPAPER_ID);
        setWallPaperBitmap(i);
        Logger.e("pengbin", "LiveWallpaperView........drawSurfaceView == " + i + " == " + this.bitmap);
        if (this.bitmap != null && (canvasLockCanvas = holder.lockCanvas()) != null) {
            Rect rect = new Rect();
            rect.top = 0;
            rect.left = 0;
            rect.bottom = canvasLockCanvas.getHeight();
            rect.right = canvasLockCanvas.getWidth();
            canvasLockCanvas.drawBitmap(this.bitmap, (Rect) null, rect, this.mPaint);
            holder.unlockCanvasAndPost(canvasLockCanvas);
        }
        sendMessageToSystemUI(i);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("pengbin", "LiveWallpaperView......surfaceDestroyed");
        Bitmap bitmap = this.bitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.bitmap.recycle();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int i = newConfig.uiMode & 48;
    }

    public boolean isLand() {
        return this.mContext.getResources().getConfiguration().orientation != 1;
    }

    public boolean isLightMode() {
        int i = this.mContext.getResources().getConfiguration().uiMode & 48;
        if (i == 32) {
            return false;
        }
        if (i == 16) {
        }
        return true;
    }

    private void setWallPaperBitmap(int resID) {
        if (isLand()) {
            if (isLightMode()) {
                if (resID == 100000010) {
                    this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.land_light_100000010));
                } else if (resID == 100000011) {
                    this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.land_light_100000011));
                } else if (resID == 100000012) {
                    this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.land_light_100000012));
                } else if (resID == 100000013) {
                    this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.land_light_100000013));
                } else if (resID == 100000014) {
                    this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.land_light_100000014));
                } else if (resID == 100000015) {
                    this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.land_light_100000015));
                }
            } else if (resID == 100000010) {
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.land_dark_100000010));
            } else if (resID == 100000011) {
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.land_dark_100000011));
            } else if (resID == 100000012) {
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.land_dark_100000012));
            } else if (resID == 100000013) {
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.land_dark_100000013));
            } else if (resID == 100000014) {
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.land_dark_100000014));
            } else if (resID == 100000015) {
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.land_dark_100000015));
            }
        } else if (isLightMode()) {
            if (resID == 100000010) {
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.port_light_100000010));
            } else if (resID == 100000011) {
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.port_light_100000011));
            } else if (resID == 100000012) {
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.port_light_100000012));
            } else if (resID == 100000013) {
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.port_light_100000013));
            } else if (resID == 100000014) {
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.port_light_100000014));
            } else if (resID == 100000015) {
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.port_light_100000015));
            }
        } else if (resID == 100000010) {
            this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.port_dark_100000010));
        } else if (resID == 100000011) {
            this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.port_dark_100000011));
        } else if (resID == 100000012) {
            this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.port_dark_100000012));
        } else if (resID == 100000013) {
            this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.port_dark_100000013));
        } else if (resID == 100000014) {
            this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.port_dark_100000014));
        } else if (resID == 100000015) {
            this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.port_dark_100000015));
        }
        if (resID == 110000012) {
            this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.wallpaper_110000012));
            return;
        }
        if (resID == 110000013) {
            this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.wallpaper_110000013));
        } else if (resID == 110000014) {
            this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.wallpaper_110000014));
        } else if (resID == 110000015) {
            this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.wallpaper_110000015));
        }
    }

    public void sendMessageToSystemUI(int id) {
        Logger.e("pengbin", "ACTION_WALLPAPER_UPDATE...............4");
        Intent intent = new Intent(Constants.ACTION_WALLPAPER_UPDATE);
        intent.putExtra(Constants.SEND_MESSAGE_TO_SYSTEMUI, id);
        this.mContext.sendBroadcast(intent);
    }
}
