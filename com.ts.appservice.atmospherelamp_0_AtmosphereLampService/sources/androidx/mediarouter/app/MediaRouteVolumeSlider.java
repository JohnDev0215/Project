package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import androidx.appcompat.R;
import androidx.appcompat.widget.AppCompatSeekBar;

/* JADX INFO: loaded from: classes2.dex */
class MediaRouteVolumeSlider extends AppCompatSeekBar {
    private static final String TAG = "MediaRouteVolumeSlider";
    private int mBackgroundColor;
    private final float mDisabledAlpha;
    private boolean mHideThumb;
    private int mProgressAndThumbColor;
    private Drawable mThumb;

    public MediaRouteVolumeSlider(Context context) {
        this(context, null);
    }

    public MediaRouteVolumeSlider(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.seekBarStyle);
    }

    public MediaRouteVolumeSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(context);
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int alpha = isEnabled() ? 255 : (int) (this.mDisabledAlpha * 255.0f);
        this.mThumb.setColorFilter(this.mProgressAndThumbColor, PorterDuff.Mode.SRC_IN);
        this.mThumb.setAlpha(alpha);
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable instanceof LayerDrawable) {
            LayerDrawable ld = (LayerDrawable) getProgressDrawable();
            progressDrawable = ld.findDrawableByLayerId(android.R.id.progress);
            Drawable backgroundDrawable = ld.findDrawableByLayerId(android.R.id.background);
            backgroundDrawable.setColorFilter(this.mBackgroundColor, PorterDuff.Mode.SRC_IN);
        }
        progressDrawable.setColorFilter(this.mProgressAndThumbColor, PorterDuff.Mode.SRC_IN);
        progressDrawable.setAlpha(alpha);
    }

    @Override // android.widget.AbsSeekBar
    public void setThumb(Drawable thumb) {
        this.mThumb = thumb;
        super.setThumb(this.mHideThumb ? null : thumb);
    }

    public void setHideThumb(boolean hideThumb) {
        if (this.mHideThumb == hideThumb) {
            return;
        }
        this.mHideThumb = hideThumb;
        super.setThumb(hideThumb ? null : this.mThumb);
    }

    public void setColor(int color) {
        setColor(color, color);
    }

    public void setColor(int progressAndThumbColor, int backgroundColor) {
        if (this.mProgressAndThumbColor != progressAndThumbColor) {
            if (Color.alpha(progressAndThumbColor) != 255) {
                Log.e(TAG, "Volume slider progress and thumb color cannot be translucent: #" + Integer.toHexString(progressAndThumbColor));
            }
            this.mProgressAndThumbColor = progressAndThumbColor;
        }
        if (this.mBackgroundColor != backgroundColor) {
            if (Color.alpha(backgroundColor) != 255) {
                Log.e(TAG, "Volume slider background color cannot be translucent: #" + Integer.toHexString(backgroundColor));
            }
            this.mBackgroundColor = backgroundColor;
        }
    }
}
