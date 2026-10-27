package com.android.systemui.stackdivider.view.preview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.core.content.ContextCompat;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.byd.card.utils.BitmapUtils;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class SplitSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final int ANIMATION_DURATION = 400;
    public static final int ORIENTATION_HORIZONTAL = 1;
    public static final int ORIENTATION_VERTICAL = 0;
    private static final String TAG = "SplitSurfaceView";
    private AnimationListener mAnimatorListener;
    private Drawable mBackground;
    private int mDividerWidth;
    private int mDrawableWidth;
    private float mEndPosition;
    private SurfaceHolder mHolder;
    private boolean mIsLooping;
    private volatile Bitmap mLeftIcon;
    private int mOrientation;
    private Paint mPaint;
    private volatile Bitmap mRightIcon;
    private Rect mSrcRect;
    private float mStartPosition;
    private volatile long mStartTime;
    private Thread mThread;

    public interface AnimationListener {
        void onAnimationEnd();
    }

    public SplitSurfaceView(Context context) {
        super(context);
        init(context);
    }

    public SplitSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SplitSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setOrientation(int orientation) {
        this.mOrientation = orientation;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.stackdivider.view.preview.SplitSurfaceView$1] */
    public void setDrawable(final Drawable left, final Drawable right) {
        if (left == null && right == null) {
            return;
        }
        new AsyncTask<Void, Void, Bitmap[]>() { // from class: com.android.systemui.stackdivider.view.preview.SplitSurfaceView.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Bitmap[] doInBackground(Void... voids) {
                Bitmap[] bitmapArr = new Bitmap[2];
                if (left != null) {
                    bitmapArr[0] = BitmapUtils.changedBitmap(SplitSurfaceView.this.getContext(), left, R.dimen.split_preview_icon);
                }
                if (right != null) {
                    bitmapArr[1] = BitmapUtils.changedBitmap(SplitSurfaceView.this.getContext(), right, R.dimen.split_preview_icon);
                }
                return bitmapArr;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Bitmap[] bitmaps) {
                SplitSurfaceView.this.mLeftIcon = bitmaps[0];
                SplitSurfaceView.this.mRightIcon = bitmaps[1];
            }
        }.execute(new Void[0]);
    }

    public void setDividerPosition(float start, float end) {
        this.mStartPosition = start;
        this.mEndPosition = end;
    }

    public void setAnimationListener(AnimationListener animatorListenerAdapter) {
        this.mAnimatorListener = animatorListenerAdapter;
    }

    public void startAnimation(int delay) {
        this.mStartTime = SystemClock.elapsedRealtime() + ((long) delay);
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.stackdivider.view.preview.-$$Lambda$SplitSurfaceView$dt2Jwu5fFSAT-WqArqA1aZqymAE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$startAnimation$0$SplitSurfaceView();
            }
        }, delay + 400);
    }

    public /* synthetic */ void lambda$startAnimation$0$SplitSurfaceView() {
        BydLogHelper.logD(TAG, "onAnimationEnd");
        AnimationListener animationListener = this.mAnimatorListener;
        if (animationListener != null) {
            animationListener.onAnimationEnd();
        }
    }

    private void init(Context context) {
        SurfaceHolder holder = getHolder();
        this.mHolder = holder;
        holder.addCallback(this);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(ContextCompat.getColor(context, R.color.divider_view_bg));
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.docked_stack_divider_size);
        this.mDividerWidth = dimensionPixelSize;
        this.mPaint.setStrokeWidth(dimensionPixelSize);
        this.mDrawableWidth = context.getResources().getDimensionPixelSize(R.dimen.split_preview_icon);
        int i = this.mDrawableWidth;
        this.mSrcRect = new Rect(0, 0, i, i);
        this.mBackground = context.getDrawable(R.drawable.split_preview_bg);
        setZOrderOnTop(true);
        getHolder().setFormat(-2);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        this.mIsLooping = true;
        Thread thread = new Thread(this);
        this.mThread = thread;
        thread.start();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (this.mOrientation == 1) {
            if (Float.compare(this.mEndPosition, 0.0f) == 0) {
                this.mEndPosition = ((-this.mDividerWidth) / 2.0f) / width;
            } else if (Float.compare(this.mEndPosition, 1.0f) == 0) {
                this.mEndPosition = ((this.mDividerWidth / 2.0f) / width) + 1.0f;
            }
            if (Float.compare(this.mStartPosition, 1.0f) == 0) {
                this.mStartPosition = ((this.mDividerWidth / 2.0f) / width) + 1.0f;
                return;
            } else {
                if (Float.compare(this.mStartPosition, 0.0f) == 0) {
                    this.mStartPosition = ((-this.mDividerWidth) / 2.0f) / width;
                    return;
                }
                return;
            }
        }
        if (Float.compare(this.mEndPosition, 0.0f) == 0) {
            this.mEndPosition = ((-this.mDividerWidth) / 2.0f) / height;
        } else if (Float.compare(this.mEndPosition, 1.0f) == 0) {
            this.mEndPosition = ((this.mDividerWidth / 2.0f) / height) + 1.0f;
        }
        if (Float.compare(this.mStartPosition, 1.0f) == 0) {
            this.mStartPosition = ((this.mDividerWidth / 2.0f) / height) + 1.0f;
        } else if (Float.compare(this.mStartPosition, 0.0f) == 0) {
            this.mStartPosition = ((-this.mDividerWidth) / 2.0f) / height;
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        this.mIsLooping = false;
        if (this.mLeftIcon != null) {
            this.mLeftIcon.recycle();
            this.mLeftIcon = null;
        }
        if (this.mRightIcon != null) {
            this.mRightIcon.recycle();
            this.mRightIcon = null;
        }
        try {
            this.mThread.join();
            this.mThread = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.mIsLooping) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (!draw()) {
                return;
            }
            long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
            if (jElapsedRealtime2 < 16) {
                try {
                    Thread.sleep(16 - jElapsedRealtime2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean draw() {
        float fDrawVertical;
        try {
            Canvas canvasLockHardwareCanvas = this.mHolder.lockHardwareCanvas();
            if (canvasLockHardwareCanvas != null) {
                if (this.mOrientation == 1) {
                    fDrawVertical = drawHorizontal(canvasLockHardwareCanvas);
                } else {
                    fDrawVertical = drawVertical(canvasLockHardwareCanvas);
                }
                this.mHolder.unlockCanvasAndPost(canvasLockHardwareCanvas);
            } else {
                fDrawVertical = 1.0f;
            }
            return fDrawVertical < 1.0f;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float drawVertical(android.graphics.Canvas r20) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.stackdivider.view.preview.SplitSurfaceView.drawVertical(android.graphics.Canvas):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float drawHorizontal(android.graphics.Canvas r20) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.stackdivider.view.preview.SplitSurfaceView.drawHorizontal(android.graphics.Canvas):float");
    }
}
