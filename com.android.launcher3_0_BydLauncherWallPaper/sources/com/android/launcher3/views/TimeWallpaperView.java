package com.android.launcher3.views;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CarTwilightManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.AlphaAnimation;
import com.android.launcher3.R;
import com.android.launcher3.home.Constants;
import com.android.launcher3.util.Logger;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes4.dex */
public class TimeWallpaperView extends SurfaceView implements SurfaceHolder.Callback {
    private Bitmap bitmap;
    private int mBitmapType;
    private CarTwilightManager mCarTwilightManager;
    private Context mContext;
    private int mCurrentTime;
    private Paint mPaint;
    private long mSunriseTime;
    private long mSunsetTime;
    private SurfaceHolder mSurfaceHolder;
    private BroadcastReceiver mTimeReceiver;
    public SimpleDateFormat simpleDateformat;
    private WallpaperManager wallpaperManager;

    public TimeWallpaperView(Context context) {
        super(context);
        this.mBitmapType = 0;
        this.mTimeReceiver = new BroadcastReceiver() { // from class: com.android.launcher3.views.TimeWallpaperView.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if (action != null) {
                    if (action.equals("android.intent.action.TIME_TICK") || action.equals("android.intent.action.TIME_SET") || action.equals("android.intent.action.TIMEZONE_CHANGED")) {
                        TimeWallpaperView.this.simpleDateformat = new SimpleDateFormat("HHmm");
                        TimeWallpaperView timeWallpaperView = TimeWallpaperView.this;
                        timeWallpaperView.mCurrentTime = Integer.parseInt(timeWallpaperView.simpleDateformat.format(Long.valueOf(System.currentTimeMillis())));
                        if (TimeWallpaperView.this.mCurrentTime <= TimeWallpaperView.this.mSunriseTime - 30 || TimeWallpaperView.this.mCurrentTime >= TimeWallpaperView.this.mSunriseTime + 120) {
                            if (TimeWallpaperView.this.mCurrentTime < TimeWallpaperView.this.mSunriseTime + 120 || TimeWallpaperView.this.mCurrentTime > TimeWallpaperView.this.mSunsetTime - 120) {
                                if (TimeWallpaperView.this.mCurrentTime <= TimeWallpaperView.this.mSunsetTime - 120 || TimeWallpaperView.this.mCurrentTime >= TimeWallpaperView.this.mSunsetTime + 30) {
                                    if ((TimeWallpaperView.this.mCurrentTime >= TimeWallpaperView.this.mSunsetTime + 30 || TimeWallpaperView.this.mCurrentTime <= TimeWallpaperView.this.mSunriseTime - 30) && TimeWallpaperView.this.mBitmapType == 9) {
                                        return;
                                    }
                                } else if (TimeWallpaperView.this.mBitmapType == 8) {
                                    return;
                                }
                            } else if (TimeWallpaperView.this.mBitmapType == 6) {
                                return;
                            }
                        } else if (TimeWallpaperView.this.mBitmapType == 7) {
                            return;
                        }
                        TimeWallpaperView timeWallpaperView2 = TimeWallpaperView.this;
                        timeWallpaperView2.drawSurfaceView(timeWallpaperView2.mSurfaceHolder);
                    }
                }
            }
        };
        this.mContext = context;
        initPaintConfig();
        this.mCarTwilightManager = (CarTwilightManager) context.getSystemService("twilight");
        this.wallpaperManager = WallpaperManager.getInstance(context);
        initData();
        registerReceiver(context);
    }

    private void initData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        this.simpleDateformat = simpleDateFormat;
        this.mCurrentTime = Integer.parseInt(simpleDateFormat.format(Long.valueOf(System.currentTimeMillis())));
        if (this.mCarTwilightManager.getSunriseTimeMillis() != -1) {
            this.mSunriseTime = Integer.parseInt(this.simpleDateformat.format(Long.valueOf(this.mCarTwilightManager.getSunriseTimeMillis())));
        } else {
            this.mSunriseTime = 600L;
        }
        if (this.mCarTwilightManager.getSunsetTimeMillis() != -1) {
            this.mSunsetTime = Integer.parseInt(this.simpleDateformat.format(Long.valueOf(this.mCarTwilightManager.getSunsetTimeMillis())));
        } else {
            this.mSunsetTime = 1800L;
        }
        Logger.e("pengbin", "mCurrentTime == " + this.mCurrentTime + " = mSunriseTime = " + this.mSunriseTime + " =mSunsetTime= " + this.mSunsetTime);
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
        this.mSurfaceHolder = holder;
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(200L);
        startAnimation(alphaAnimation);
        drawSurfaceView(holder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        drawSurfaceView(holder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void drawSurfaceView(SurfaceHolder holder) {
        Canvas canvasLockCanvas;
        Bitmap bitmap = this.bitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.bitmap.recycle();
        }
        int i = this.mCurrentTime;
        long j = i;
        long j2 = this.mSunriseTime;
        if (j > j2 - 30 && i < j2 + 120) {
            Logger.e("pengbin", "bitmap ==== 显示日出图");
            this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.sunrise));
            this.mBitmapType = 7;
        } else if (i >= j2 + 120 && i <= this.mSunsetTime - 120) {
            Logger.e("pengbin", "bitmap ==== 显示白天图");
            this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.forenoon));
            this.mBitmapType = 6;
        } else {
            long j3 = i;
            long j4 = this.mSunsetTime;
            if (j3 > j4 - 120 && i < j4 + 30) {
                Logger.e("pengbin", "bitmap ==== 显示日落图");
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.sunset));
                this.mBitmapType = 8;
            } else if (i >= j4 + 30 || i <= j2 - 30) {
                Logger.e("pengbin", "bitmap ==== 显示黑夜图");
                this.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.evening));
                this.mBitmapType = 9;
            }
        }
        if (this.bitmap != null && (canvasLockCanvas = holder.lockCanvas()) != null) {
            Rect rect = new Rect();
            rect.top = 0;
            rect.left = 0;
            rect.bottom = canvasLockCanvas.getHeight();
            rect.right = canvasLockCanvas.getWidth();
            canvasLockCanvas.drawBitmap(this.bitmap, (Rect) null, rect, this.mPaint);
            holder.unlockCanvasAndPost(canvasLockCanvas);
        }
        sendMessageToSystemUI(this.mBitmapType + 100);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("pengbin", "TimeWallpaperView......surfaceDestroyed");
        Bitmap bitmap = this.bitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.bitmap.recycle();
        }
        this.mContext.unregisterReceiver(this.mTimeReceiver);
    }

    public void registerReceiver(Context context) {
        Logger.e("pengbin", "TimeWallpaperView.......registerReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        context.registerReceiver(this.mTimeReceiver, intentFilter);
    }

    public void sendMessageToSystemUI(int id) {
        Logger.e("pengbin", "ACTION_WALLPAPER_UPDATE...............3");
        Intent intent = new Intent(Constants.ACTION_WALLPAPER_UPDATE);
        intent.putExtra(Constants.SEND_MESSAGE_TO_SYSTEMUI, id);
        this.mContext.sendBroadcast(intent);
    }
}
