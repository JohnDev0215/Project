package com.android.car.developeroptions.biometrics.face;

import android.animation.TimeAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.android.car.developeroptions.biometrics.BiometricEnrollSidecar;
import com.android.car.developeroptions.biometrics.face.ParticleCollection;

/* JADX INFO: loaded from: classes.dex */
public class FaceEnrollAnimationDrawable extends Drawable implements BiometricEnrollSidecar.Listener {
    private final ParticleCollection.Listener mAnimationListener = new ParticleCollection.Listener() { // from class: com.android.car.developeroptions.biometrics.face.FaceEnrollAnimationDrawable.1
        @Override // com.android.car.developeroptions.biometrics.face.ParticleCollection.Listener
        public void onEnrolled() {
            if (FaceEnrollAnimationDrawable.this.mTimeAnimator == null || !FaceEnrollAnimationDrawable.this.mTimeAnimator.isStarted()) {
                return;
            }
            FaceEnrollAnimationDrawable.this.mTimeAnimator.end();
            FaceEnrollAnimationDrawable.this.mListener.onEnrolled();
        }
    };
    private Rect mBounds;
    private final Paint mCircleCutoutPaint;
    private final Context mContext;
    private final ParticleCollection.Listener mListener;
    private ParticleCollection mParticleCollection;
    private final Paint mSquarePaint;
    private TimeAnimator mTimeAnimator;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public FaceEnrollAnimationDrawable(Context context, ParticleCollection.Listener listener) {
        this.mContext = context;
        this.mListener = listener;
        Paint paint = new Paint();
        this.mSquarePaint = paint;
        paint.setColor(-1);
        this.mSquarePaint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.mCircleCutoutPaint = paint2;
        paint2.setColor(0);
        this.mCircleCutoutPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.mCircleCutoutPaint.setAntiAlias(true);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar.Listener
    public void onEnrollmentHelp(int i, CharSequence charSequence) {
        this.mParticleCollection.onEnrollmentHelp(i, charSequence);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar.Listener
    public void onEnrollmentError(int i, CharSequence charSequence) {
        this.mParticleCollection.onEnrollmentError(i, charSequence);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar.Listener
    public void onEnrollmentProgressChange(int i, int i2) {
        this.mParticleCollection.onEnrollmentProgressChange(i, i2);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.mBounds = rect;
        this.mParticleCollection = new ParticleCollection(this.mContext, this.mAnimationListener, rect, 20);
        if (this.mTimeAnimator == null) {
            TimeAnimator timeAnimator = new TimeAnimator();
            this.mTimeAnimator = timeAnimator;
            timeAnimator.setTimeListener(new TimeAnimator.TimeListener() { // from class: com.android.car.developeroptions.biometrics.face.-$$Lambda$FaceEnrollAnimationDrawable$yTGqfw7kg08mRRstQ4hgOSO-oY4
                @Override // android.animation.TimeAnimator.TimeListener
                public final void onTimeUpdate(TimeAnimator timeAnimator2, long j, long j2) {
                    this.f$0.lambda$onBoundsChange$0$FaceEnrollAnimationDrawable(timeAnimator2, j, j2);
                }
            });
            this.mTimeAnimator.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onBoundsChange$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onBoundsChange$0$FaceEnrollAnimationDrawable(TimeAnimator timeAnimator, long j, long j2) {
        this.mParticleCollection.update(j, j2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mBounds == null) {
            return;
        }
        canvas.save();
        canvas.drawRect(0.0f, 0.0f, this.mBounds.width(), this.mBounds.height(), this.mSquarePaint);
        canvas.drawCircle(this.mBounds.exactCenterX(), this.mBounds.exactCenterY(), (this.mBounds.height() / 2) - 20, this.mCircleCutoutPaint);
        this.mParticleCollection.draw(canvas);
        canvas.restore();
    }
}
