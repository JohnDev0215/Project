package com.amazon.music.views.library.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$styleable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackPopularityBar.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 42\u00020\u0001:\u00014B\u001d\b\u0016\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b2\u00103J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u001a\u0010\b\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0014J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0011J\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0011J\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0011J\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0011J\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0011R\u0016\u0010\u001d\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010\u001eR\u0016\u0010 \u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010\"\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010!R\u0016\u0010#\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010!R\u0016\u0010$\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010!R\u0016\u0010%\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010!R\u0018\u0010&\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010'R\u0018\u0010)\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\"\u0010-\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b-\u0010/\"\u0004\b0\u00101¨\u00065"}, d2 = {"Lcom/amazon/music/views/library/views/TrackPopularityBar;", "Landroid/view/View;", "Landroid/util/AttributeSet;", "attrs", "", "initAttrs", "Landroid/content/Context;", "context", "init", "Landroid/graphics/Canvas;", "canvas", "onDraw", "", "popularity", "setPopularity", "", "show", "", "totalBarCount", "setTotalBarCount", "barWidth", "setBarWidth", "barHeight", "setBarHeight", "barActiveColor", "setBarActiveColor", "barInactiveColor", "setBarInactiveColor", "", "mBarWidth", "F", "mBarHeight", "mBarActiveColor", "I", "mBarActiveColorSonicRush", "mBarInactiveColor", "mBarTransparentColor", "mTotalBarCount", "mContext", "Landroid/content/Context;", "Landroid/graphics/Paint;", "mPaint", "Landroid/graphics/Paint;", "mPopularity", "D", "isSonicRushEnabled", "Z", "()Z", "setSonicRushEnabled", "(Z)V", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class TrackPopularityBar extends View {
    private boolean isSonicRushEnabled;
    private int mBarActiveColor;
    private int mBarActiveColorSonicRush;
    private float mBarHeight;
    private int mBarInactiveColor;
    private int mBarTransparentColor;
    private float mBarWidth;
    private Context mContext;
    private Paint mPaint;
    private double mPopularity;
    private int mTotalBarCount;
    private static final int TRANSPARENT_COLOR = R$color.transparent;
    private static final int DEFAULT_BAR_WIDTH = R$dimen.spacer_2;
    private static final int DEFAULT_BAR_HEIGHT = R$dimen.spacer_8;
    private static int DEFAULT_BAR_ACTIVE_COLOR = R$color.vivace_blue;
    private static int DEFAULT_BAR_ACTIVE_COLOR_SONIC_RUSH = R$color.cyan_accent;
    private static final int DEFAULT_BAR_INACTIVE_COLOR = R$color.dark_grey;

    public final void setSonicRushEnabled(boolean z) {
        this.isSonicRushEnabled = z;
    }

    public TrackPopularityBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPopularity = -1.0d;
        init(context, attributeSet);
    }

    public final void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        initAttrs(attrs);
        Paint paint = new Paint();
        this.mPaint = paint;
        Intrinsics.checkNotNull(paint);
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = this.mPaint;
        Intrinsics.checkNotNull(paint2);
        paint2.setStrokeWidth(0.0f);
    }

    private final void initAttrs(AttributeSet attrs) {
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attrs, R$styleable.TrackPopularityBar, 0, 0);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "mContext!!.theme.obtainS…TrackPopularityBar, 0, 0)");
        this.mBarWidth = typedArrayObtainStyledAttributes.getDimension(R$styleable.TrackPopularityBar_bar_width, getResources().getDimension(DEFAULT_BAR_WIDTH));
        this.mBarHeight = typedArrayObtainStyledAttributes.getDimension(R$styleable.TrackPopularityBar_bar_height, getResources().getDimension(DEFAULT_BAR_HEIGHT));
        int i = R$styleable.TrackPopularityBar_active_color;
        this.mBarActiveColor = typedArrayObtainStyledAttributes.getColor(i, getResources().getColor(DEFAULT_BAR_ACTIVE_COLOR));
        this.mBarActiveColorSonicRush = typedArrayObtainStyledAttributes.getColor(i, getResources().getColor(DEFAULT_BAR_ACTIVE_COLOR_SONIC_RUSH));
        this.mBarInactiveColor = typedArrayObtainStyledAttributes.getColor(R$styleable.TrackPopularityBar_inactive_color, getResources().getColor(DEFAULT_BAR_INACTIVE_COLOR));
        this.mBarTransparentColor = getResources().getColor(TRANSPARENT_COLOR);
        this.mTotalBarCount = typedArrayObtainStyledAttributes.getInteger(R$styleable.TrackPopularityBar_total_bar_count, 15);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        int i2 = this.mTotalBarCount;
        float f = 0.0f;
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i3 + 1;
            Paint paint = this.mPaint;
            Intrinsics.checkNotNull(paint);
            if (i3 < this.mPopularity) {
                i = this.isSonicRushEnabled ? this.mBarActiveColorSonicRush : this.mBarActiveColor;
            } else {
                i = this.mBarInactiveColor;
            }
            paint.setColor(i);
            float f2 = f + this.mBarWidth;
            float f3 = this.mBarHeight;
            Paint paint2 = this.mPaint;
            Intrinsics.checkNotNull(paint2);
            canvas.drawRect(f, 0.0f, f2, f3, paint2);
            f += this.mBarWidth;
            if (i3 < this.mTotalBarCount - 1) {
                Paint paint3 = this.mPaint;
                Intrinsics.checkNotNull(paint3);
                paint3.setColor(getResources().getColor(TRANSPARENT_COLOR));
                float f4 = f + this.mBarWidth;
                float f5 = this.mBarHeight;
                Paint paint4 = this.mPaint;
                Intrinsics.checkNotNull(paint4);
                canvas.drawRect(f, 0.0f, f4, f5, paint4);
                f += this.mBarWidth;
            }
            i3 = i4;
        }
    }

    public final TrackPopularityBar setPopularity(double popularity) {
        double dCeil = Math.ceil(popularity * ((double) this.mTotalBarCount));
        this.mPopularity = dCeil;
        setContentDescription(Intrinsics.stringPlus("", Double.valueOf(dCeil)));
        return this;
    }

    public final boolean show() {
        if (this.mPopularity <= -1.0d) {
            return false;
        }
        if (getVisibility() != 0) {
            setVisibility(0);
        }
        invalidate();
        return true;
    }

    public final void setTotalBarCount(int totalBarCount) {
        this.mTotalBarCount = totalBarCount;
    }

    public final void setBarWidth(int barWidth) {
        this.mBarWidth = getResources().getDimension(barWidth);
    }

    public final void setBarHeight(int barHeight) {
        this.mBarHeight = getResources().getDimension(barHeight);
    }

    public final void setBarActiveColor(int barActiveColor) {
        this.mBarActiveColor = getResources().getColor(barActiveColor);
    }

    public final void setBarInactiveColor(int barInactiveColor) {
        this.mBarInactiveColor = getResources().getColor(barInactiveColor);
    }
}
