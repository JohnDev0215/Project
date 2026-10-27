package com.android.systemui.statusbar.phone;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.util.BlurUtil;
import com.android.systemui.util.ScreenShotUtil;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class BlurViewController {
    private static final int BLUR_END = 700;
    private static final int BLUR_START = 200;
    private static final String TAG = "BlurViewController";
    private Bitmap blurBitmap;
    private StatusBar mBar;
    private ImageView mBlurView;
    protected Context mContext;
    protected int mHeight;
    protected WindowManager.LayoutParams mLayoutParams;
    private ImageView mOverlayView;
    protected int mType;
    protected int mWidth;
    protected WindowManager mWindowManager;

    public void setBlurView(ImageView mBlurView) {
        this.mBlurView = mBlurView;
    }

    public void setOverlayView(ImageView mOverlayView) {
        this.mOverlayView = mOverlayView;
    }

    public BlurViewController(Context context) {
        this(context, 0);
    }

    public BlurViewController(Context context, int type) {
        this.mContext = context;
        this.mType = type;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
    }

    private void initLayoutParams(boolean isFullScreen) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        layoutParams.type = 2038;
        this.mLayoutParams.format = -3;
        this.mLayoutParams.flags = 142608168;
        this.mLayoutParams.flags |= 16777216;
        this.mLayoutParams.gravity = 17;
        this.mLayoutParams.softInputMode = 16;
        this.mLayoutParams.setTitle("SafetyWarningWindowManager");
        this.mLayoutParams.packageName = this.mContext.getPackageName();
        if (isFullScreen) {
            this.mLayoutParams.width = -1;
            this.mLayoutParams.height = -1;
        } else {
            this.mLayoutParams.width = this.mWidth;
            this.mLayoutParams.height = this.mHeight;
        }
    }

    public void setBar(StatusBar bar) {
        this.mBar = bar;
    }

    public void show(View view) {
        if (view != null) {
            try {
                this.mWindowManager.addView(view, this.mLayoutParams);
            } catch (Exception e) {
                BydLogHelper.logE(TAG, e.toString());
                e.printStackTrace();
            }
        }
    }

    public void show(View view, int x, int y) {
        if (view != null) {
            this.mLayoutParams.x = x;
            this.mLayoutParams.y = y;
            this.mWindowManager.addView(view, this.mLayoutParams);
        }
    }

    public void dismiss(View view) {
        if (view != null) {
            try {
                this.mWindowManager.removeView(view);
            } catch (Exception e) {
                BydLogHelper.logE(TAG, e.toString());
                e.printStackTrace();
            }
        }
    }

    public void dismiss() {
        dismiss(this.mBlurView);
    }

    public void show() {
        show(this.mBlurView);
    }

    protected void initView() {
        ImageView imageView = (ImageView) LayoutInflater.from(this.mContext).inflate(R.layout.view_blur, (ViewGroup) null);
        this.mBlurView = imageView;
        imageView.setFocusable(false);
        this.mBlurView.setClickable(false);
    }

    public void handleTouchEvent(MotionEvent event) {
        event.getY();
        if (event.getAction() != 0) {
            return;
        }
        try {
            Log.i(TAG, "set blur bg");
            setBlurBackground();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "e:" + e);
        }
    }

    private void startAlphaAnimation(float start, float end) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(start, end);
        valueAnimatorOfFloat.setDuration((long) (Math.abs(end - start) * 500.0f));
        valueAnimatorOfFloat.start();
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.phone.BlurViewController.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                BlurViewController.this.mBlurView.setAlpha(((Float) animation.getAnimatedValue()).floatValue());
            }
        });
    }

    public Bitmap getBlurBitmap() {
        return this.blurBitmap;
    }

    public void clearBlurBitmap() {
        Bitmap bitmap = this.blurBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.blurBitmap = null;
    }

    public void setBlurBackground() {
        Log.w(TAG, "setBlurBackground start");
        new Thread(new Runnable() { // from class: com.android.systemui.statusbar.phone.BlurViewController.2
            @Override // java.lang.Runnable
            public void run() {
                Bitmap bitmapTakeScreenShot = ScreenShotUtil.takeScreenShot(BlurViewController.this.mContext);
                if (bitmapTakeScreenShot == null) {
                    Log.e(BlurViewController.TAG, "setBlurBackground bitmap == null");
                    bitmapTakeScreenShot = BitmapFactory.decodeResource(BlurViewController.this.mContext.getResources(), R.mipmap.img_app_bg);
                }
                BlurViewController blurViewController = BlurViewController.this;
                blurViewController.blurBitmap = BlurUtil.blur(blurViewController.mContext, bitmapTakeScreenShot, 25);
                BlurViewController.this.setImg();
            }
        }).start();
    }

    public void setImg() {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.BlurViewController.3
            @Override // java.lang.Runnable
            public void run() {
                if (BlurViewController.this.mBar.getQSFragment() == null) {
                    Log.e(BlurViewController.TAG, "getQSFragment  null");
                    return;
                }
                if (BlurViewController.this.mBar.getQSFragment().getQuickQSPanel() == null) {
                    Log.e(BlurViewController.TAG, "getQuickQSPanel  null");
                    return;
                }
                if (BlurViewController.this.mBar.getQSFragment().getQuickQSPanel().getImgDrawerMask() == null) {
                    Log.e(BlurViewController.TAG, "getImgDrawerMask  null");
                    return;
                }
                if (BlurViewController.this.blurBitmap == null) {
                    Log.e(BlurViewController.TAG, "blurBitmap  null");
                    return;
                }
                BlurViewController.this.mBar.getQSFragment().getQuickQSPanel().getImgDrawerMask().setImageBitmap(BlurViewController.this.blurBitmap);
                BlurViewController.this.mBar.getQSFragment().getQuickQSPanel().getImgDrawerMask().setVisibility(0);
                if (BlurViewController.this.mBar.getQSFragment().getQuickQSPanel().getImgDrawerOverlay() == null) {
                    Log.e(BlurViewController.TAG, "getImgDrawerOverlay  null");
                } else {
                    BlurViewController.this.mBar.getQSFragment().getQuickQSPanel().getImgDrawerOverlay().setVisibility(0);
                    Log.w(BlurViewController.TAG, "setBlurBackground end");
                }
            }
        });
    }
}
