package com.android.internal.app;

import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.app.slice.Slice;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.internal.R;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class PlatLogoActivity extends Activity {
    private static final String R_EGG_UNLOCK_SETTING = "egg_mode_r";
    static final String TOUCH_STATS = "touch.stats";
    private static final int UNLOCK_TRIES = 3;
    private static final boolean WRITE_SETTINGS = true;
    BigDialView mDialView;
    double mPressureMin = 0.0d;
    double mPressureMax = -1.0d;

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        float f = getResources().getDisplayMetrics().density;
        getWindow().getDecorView().setSystemUiVisibility(768);
        getWindow().setNavigationBarColor(0);
        getWindow().setStatusBarColor(0);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        this.mDialView = new BigDialView(this, null);
        if (Settings.System.getLong(getContentResolver(), R_EGG_UNLOCK_SETTING, 0L) == 0) {
            this.mDialView.setUnlockTries(3);
        } else {
            this.mDialView.setUnlockTries(0);
        }
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setBackgroundColor(-65536);
        frameLayout.addView(this.mDialView, -1, -1);
        setContentView(frameLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchNextStage(boolean z) {
        long jCurrentTimeMillis;
        ContentResolver contentResolver = getContentResolver();
        if (z) {
            jCurrentTimeMillis = 0;
        } else {
            try {
                jCurrentTimeMillis = System.currentTimeMillis();
            } catch (RuntimeException e) {
                Log.e("com.android.internal.app.PlatLogoActivity", "Can't write settings", e);
            }
        }
        Settings.System.putLong(contentResolver, R_EGG_UNLOCK_SETTING, jCurrentTimeMillis);
        try {
            startActivity(new Intent(Intent.ACTION_MAIN).setFlags(268468224).addCategory("com.android.internal.category.PLATLOGO"));
        } catch (ActivityNotFoundException unused) {
            Log.e("com.android.internal.app.PlatLogoActivity", "No more eggs.");
        }
    }

    private void measureTouchPressure(MotionEvent motionEvent) {
        float pressure = motionEvent.getPressure();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            if (this.mPressureMax < 0.0d) {
                double d = pressure;
                this.mPressureMax = d;
                this.mPressureMin = d;
                return;
            }
            return;
        }
        if (actionMasked != 2) {
            return;
        }
        double d2 = pressure;
        if (d2 < this.mPressureMin) {
            this.mPressureMin = d2;
        }
        if (d2 > this.mPressureMax) {
            this.mPressureMax = d2;
        }
    }

    private void syncTouchPressure() {
        try {
            String string = Settings.System.getString(getContentResolver(), TOUCH_STATS);
            if (string == null) {
                string = "{}";
            }
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has("min")) {
                this.mPressureMin = Math.min(this.mPressureMin, jSONObject.getDouble("min"));
            }
            if (jSONObject.has(Slice.SUBTYPE_MAX)) {
                this.mPressureMax = Math.max(this.mPressureMax, jSONObject.getDouble(Slice.SUBTYPE_MAX));
            }
            if (this.mPressureMax >= 0.0d) {
                jSONObject.put("min", this.mPressureMin);
                jSONObject.put(Slice.SUBTYPE_MAX, this.mPressureMax);
                Settings.System.putString(getContentResolver(), TOUCH_STATS, jSONObject.toString());
            }
        } catch (Exception e) {
            Log.e("com.android.internal.app.PlatLogoActivity", "Can't write touch settings", e);
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        syncTouchPressure();
    }

    @Override // android.app.Activity
    public void onStop() {
        syncTouchPressure();
        super.onStop();
    }

    class BigDialView extends ImageView {
        private static final int COLOR_BLUE = -12417548;
        private static final int COLOR_CHARTREUSE = -1050673;
        private static final int COLOR_GREEN = -12723068;
        private static final int COLOR_LIGHTBLUE = -2625538;
        private static final int COLOR_NAVY = -16306110;
        private static final int COLOR_ORANGE = -497868;
        private static final int STEPS = 11;
        private static final float VALUE_CHANGE_MAX = 0.09090909f;
        private BigDialDrawable mDialDrawable;
        private boolean mWasLocked;

        BigDialView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            init();
        }

        BigDialView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            init();
        }

        BigDialView(Context context, AttributeSet attributeSet, int i, int i2) {
            super(context, attributeSet, i, i2);
            init();
        }

        private void init() {
            BigDialDrawable bigDialDrawable = new BigDialDrawable();
            this.mDialDrawable = bigDialDrawable;
            setImageDrawable(bigDialDrawable);
        }

        @Override // android.widget.ImageView, android.view.View
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
        }

        double toPositiveDegrees(double d) {
            return ((Math.toDegrees(d) + 360.0d) - 90.0d) % 360.0d;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                this.mWasLocked = this.mDialDrawable.isLocked();
            } else {
                if (actionMasked == 1) {
                    if (this.mWasLocked != this.mDialDrawable.isLocked()) {
                        PlatLogoActivity.this.launchNextStage(this.mDialDrawable.isLocked());
                    }
                    return true;
                }
                if (actionMasked != 2) {
                    return false;
                }
            }
            float positiveDegrees = (float) toPositiveDegrees(Math.atan2(motionEvent.getX() - ((getLeft() + getRight()) / 2.0f), motionEvent.getY() - ((getTop() + getBottom()) / 2.0f)));
            int userLevel = this.mDialDrawable.getUserLevel();
            this.mDialDrawable.touchAngle(positiveDegrees);
            int userLevel2 = this.mDialDrawable.getUserLevel();
            if (userLevel != userLevel2) {
                performHapticFeedback(userLevel2 == 11 ? 16 : 4);
            }
            return true;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (this.mDialDrawable.getUserLevel() < 10) {
                BigDialDrawable bigDialDrawable = this.mDialDrawable;
                bigDialDrawable.setUserLevel(bigDialDrawable.getUserLevel() + 1);
                performHapticFeedback(4);
            }
            return true;
        }

        void setUnlockTries(int i) {
            this.mDialDrawable.setUnlockTries(i);
        }

        private class BigDialDrawable extends Drawable {
            final Drawable mEleven;
            private boolean mNightMode;
            public final int STEPS = 10;
            private int mUnlockTries = 0;
            final Paint mPaint = new Paint();
            private float mValue = 0.0f;
            float mElevenAnim = 0.0f;
            ObjectAnimator mElevenShowAnimator = ObjectAnimator.ofFloat(this, "elevenAnim", 0.0f, 1.0f).setDuration(300L);
            ObjectAnimator mElevenHideAnimator = ObjectAnimator.ofFloat(this, "elevenAnim", 1.0f, 0.0f).setDuration(500L);

            float clamp(float f, float f2, float f3) {
                return f < f2 ? f2 : f > f3 ? f3 : f;
            }

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

            float valueToAngle(float f) {
                return (1.0f - f) * 315.0f;
            }

            BigDialDrawable() {
                this.mNightMode = BigDialView.this.getContext().getResources().getConfiguration().isNightModeActive();
                this.mEleven = BigDialView.this.getContext().getDrawable(R.drawable.ic_number11);
                this.mElevenShowAnimator.setInterpolator(new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f));
                this.mElevenHideAnimator.setInterpolator(new PathInterpolator(0.8f, 0.2f, 0.6f, 1.0f));
            }

            public void setUnlockTries(int i) {
                if (this.mUnlockTries != i) {
                    this.mUnlockTries = i;
                    setValue(getValue());
                    invalidateSelf();
                }
            }

            boolean isLocked() {
                return this.mUnlockTries > 0;
            }

            public void setValue(float f) {
                float f2 = isLocked() ? 0.9f : 1.0f;
                if (f < 0.0f) {
                    f = 0.0f;
                } else if (f > f2) {
                    f = f2;
                }
                this.mValue = f;
                invalidateSelf();
            }

            public float getValue() {
                return this.mValue;
            }

            public int getUserLevel() {
                return Math.round((getValue() * 10.0f) - 0.25f);
            }

            public void setUserLevel(int i) {
                setValue(getValue() + (i / 10.0f));
            }

            public float getElevenAnim() {
                return this.mElevenAnim;
            }

            public void setElevenAnim(float f) {
                if (this.mElevenAnim != f) {
                    this.mElevenAnim = f;
                    invalidateSelf();
                }
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas canvas) {
                Rect bounds = getBounds();
                int iWidth = bounds.width();
                float f = iWidth;
                float f2 = f / 2.0f;
                float fHeight = bounds.height() / 2.0f;
                float f3 = f / 4.0f;
                canvas.drawColor(this.mNightMode ? BigDialView.COLOR_NAVY : BigDialView.COLOR_LIGHTBLUE);
                canvas.save();
                canvas.rotate(45.0f, f2, fHeight);
                canvas.clipRect(f2, fHeight - f3, Math.min(iWidth, r2), fHeight + f3);
                int i = this.mNightMode ? 1610612768 : 268906562;
                this.mPaint.setShader(new LinearGradient(f2, fHeight, Math.min(iWidth, r2), fHeight, i, i & 16777215, Shader.TileMode.CLAMP));
                this.mPaint.setColor(-16777216);
                canvas.drawPaint(this.mPaint);
                this.mPaint.setShader(null);
                canvas.restore();
                this.mPaint.setStyle(Paint.Style.FILL);
                this.mPaint.setColor(BigDialView.COLOR_GREEN);
                canvas.drawCircle(f2, fHeight, f3, this.mPaint);
                this.mPaint.setColor(this.mNightMode ? BigDialView.COLOR_LIGHTBLUE : BigDialView.COLOR_NAVY);
                float f4 = 0.85f * f;
                int i2 = 0;
                while (i2 < 10) {
                    canvas.save();
                    canvas.rotate(-valueToAngle(i2 / 10.0f), f2, fHeight);
                    canvas.drawCircle(f4, fHeight, i2 <= getUserLevel() ? 20.0f : 5.0f, this.mPaint);
                    canvas.restore();
                    i2++;
                }
                float f5 = this.mElevenAnim;
                if (f5 > 0.0f) {
                    int i3 = (int) (((((double) (f5 * 0.5f)) + 0.5d) * ((double) iWidth)) / 14.0d);
                    int i4 = (int) (f4 + (i3 / 4.0f));
                    int i5 = (int) fHeight;
                    this.mEleven.setBounds(i4 - i3, i5 - i3, i4 + i3, i5 + i3);
                    this.mEleven.setTint(((((int) clamp(this.mElevenAnim * 510.0f, 0.0f, 255.0f)) << 24) | 16777215) & BigDialView.COLOR_ORANGE);
                    this.mEleven.draw(canvas);
                }
                canvas.rotate(-valueToAngle(this.mValue), f2, fHeight);
                this.mPaint.setColor(-1);
                float f6 = f2 / 12.0f;
                canvas.drawCircle((f - f3) - (f6 * 2.0f), fHeight, f6, this.mPaint);
            }

            float angleToValue(float f) {
                return 1.0f - clamp(f / 315.0f, 0.0f, 1.0f);
            }

            public void touchAngle(float f) {
                int userLevel = getUserLevel();
                float fAngleToValue = angleToValue(f);
                if (Math.abs(fAngleToValue - getValue()) < BigDialView.VALUE_CHANGE_MAX) {
                    setValue(fAngleToValue);
                    if (isLocked() && userLevel != 9 && getUserLevel() == 9) {
                        this.mUnlockTries--;
                    } else if (!isLocked() && getUserLevel() == 0) {
                        this.mUnlockTries = 3;
                    }
                    if (isLocked()) {
                        return;
                    }
                    if (getUserLevel() == 10 && this.mElevenAnim != 1.0f && !this.mElevenShowAnimator.isRunning()) {
                        this.mElevenHideAnimator.cancel();
                        this.mElevenShowAnimator.start();
                    } else {
                        if (getUserLevel() == 10 || this.mElevenAnim != 1.0f || this.mElevenHideAnimator.isRunning()) {
                            return;
                        }
                        this.mElevenShowAnimator.cancel();
                        this.mElevenHideAnimator.start();
                    }
                }
            }
        }
    }
}
