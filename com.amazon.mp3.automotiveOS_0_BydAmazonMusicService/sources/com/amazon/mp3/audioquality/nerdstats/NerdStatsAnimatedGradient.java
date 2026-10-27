package com.amazon.mp3.audioquality.nerdstats;

import android.animation.TimeAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import androidx.core.content.res.ResourcesCompat;
import com.BV.LinearGradient.LinearGradientManager;
import com.amazon.mp3.automotiveOS.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NerdStatsAnimatedGradient.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u00104\u001a\u000203¢\u0006\u0004\b5\u00106J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0014J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0012\u0010\u0010\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0016J\b\u0010\u0012\u001a\u00020\u0006H\u0016J\b\u0010\u0013\u001a\u00020\u0006H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J \u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018H\u0016R\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u000b8\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010 R\u0014\u0010\"\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010 R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010\u001eR\u0014\u0010'\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0017\u0010*\u001a\u00020)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0017\u0010/\u001a\u00020.8\u0006¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102¨\u00067"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/NerdStatsAnimatedGradient;", "Landroid/graphics/drawable/Drawable;", "Landroid/graphics/drawable/Animatable;", "Landroid/animation/TimeAnimator$TimeListener;", "Landroid/graphics/Rect;", "bounds", "", "onBoundsChange", "Landroid/graphics/Canvas;", "canvas", "draw", "", "alpha", "setAlpha", "Landroid/graphics/ColorFilter;", "colorFilter", "setColorFilter", "getOpacity", AsmString.METHOD_FIREBASE_PERF_TRACE_START, AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "", "isRunning", "Landroid/animation/TimeAnimator;", "animation", "", "totalTime", "deltaTime", "onTimeUpdate", "", "pixelsPerSecond", "F", "SECTION_TIME_IN_MS", "I", "primaryColor", "animationColor", "Landroid/graphics/Paint;", "paint", "Landroid/graphics/Paint;", "y", "animator", "Landroid/animation/TimeAnimator;", "", LinearGradientManager.PROP_COLORS, "[I", "getColors", "()[I", "", "positions", "[F", "getPositions", "()[F", "Landroid/content/Context;", "context", "<init>", "(FLandroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class NerdStatsAnimatedGradient extends Drawable implements Animatable, TimeAnimator.TimeListener {
    private final int SECTION_TIME_IN_MS;
    private final int animationColor;
    private final TimeAnimator animator;
    private final int[] colors;
    private final Paint paint;
    private final float pixelsPerSecond;
    private final float[] positions;
    private final int primaryColor;
    private float y;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public NerdStatsAnimatedGradient(float f, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.pixelsPerSecond = f;
        this.SECTION_TIME_IN_MS = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        int color = ResourcesCompat.getColor(context.getResources(), R.color.nerd_stats_vertical_divider_base, null);
        this.primaryColor = color;
        int color2 = ResourcesCompat.getColor(context.getResources(), R.color.nerd_stats_vertical_divider_animated, null);
        this.animationColor = color2;
        this.paint = new Paint();
        TimeAnimator timeAnimator = new TimeAnimator();
        this.animator = timeAnimator;
        this.colors = new int[]{color, color2, color};
        this.positions = new float[]{0.1f, 0.5f, 1.0f};
        timeAnimator.setTimeListener(this);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        this.paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, bounds.height(), this.colors, this.positions, Shader.TileMode.MIRROR));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.clipRect(getBounds());
        canvas.translate(0.0f, this.y);
        canvas.drawPaint(this.paint);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.animator.start();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.animator.cancel();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.animator.isRunning();
    }

    @Override // android.animation.TimeAnimator.TimeListener
    public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.y = (this.pixelsPerSecond * totalTime) / this.SECTION_TIME_IN_MS;
        invalidateSelf();
    }
}
