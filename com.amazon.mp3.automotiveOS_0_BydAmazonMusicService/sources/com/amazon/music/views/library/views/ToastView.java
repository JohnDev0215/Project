package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.snackbar.ContentViewCallback;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToastView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u00002\u00020\u00012\u00020\u0002:\u0001BB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020!H\u0016J\u0018\u0010&\u001a\u00020#2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020!H\u0016J\u0012\u0010'\u001a\u00020#2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\b\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u0004\u0018\u00010\u001fJ\b\u0010-\u001a\u00020#H\u0002J\b\u0010.\u001a\u00020#H\u0004J\u0012\u0010/\u001a\u00020#2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\u0018\u00100\u001a\u00020#2\u0006\u00101\u001a\u00020!2\u0006\u00102\u001a\u00020!H\u0014J\u0010\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0016J\u000e\u00107\u001a\u00020#2\u0006\u00108\u001a\u000209J\u0012\u0010:\u001a\u00020#2\b\u0010;\u001a\u0004\u0018\u00010\u001fH\u0016J\u000e\u0010<\u001a\u00020#2\u0006\u0010=\u001a\u00020!J\u0010\u0010<\u001a\u00020#2\b\u0010>\u001a\u0004\u0018\u00010+J\u000e\u0010?\u001a\u00020#2\u0006\u0010@\u001a\u00020!J\b\u0010A\u001a\u00020#H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/amazon/music/views/library/views/ToastView;", "Landroid/widget/FrameLayout;", "Lcom/google/android/material/snackbar/ContentViewCallback;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "clipPath", "Landroid/graphics/Path;", "fillPaint", "Landroid/graphics/Paint;", "gestureDetector", "Landroid/view/GestureDetector;", "rect", "Landroid/graphics/RectF;", "value", "Lcom/amazon/music/views/library/views/ToastView$State;", "state", "getState", "()Lcom/amazon/music/views/library/views/ToastView$State;", "setState", "(Lcom/amazon/music/views/library/views/ToastView$State;)V", "strokePaint", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "textView", "Landroid/widget/TextView;", "getTextView", "()Landroid/widget/TextView;", "toastOnCLickListener", "Landroid/view/View$OnClickListener;", "viewHeight", "", "animateContentIn", "", "delay", "duration", "animateContentOut", "beforeStrokeGetsDrawn", "canvas", "Landroid/graphics/Canvas;", "getLocale", "", "getOnClickListener", "initPaintColor", "initTextStyle", "onDraw", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onTouchEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "setGestureListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/GestureDetector$OnGestureListener;", "setOnClickListener", "l", "setText", "textResId", "text", "setTextColor", "color", "updateClipPath", "State", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class ToastView extends FrameLayout implements ContentViewCallback {
    private final Path clipPath;
    private final Paint fillPaint;
    private GestureDetector gestureDetector;
    private final RectF rect;
    private State state;
    private final Paint strokePaint;
    private final StyleSheet styleSheet;
    private View.OnClickListener toastOnCLickListener;
    private final int viewHeight;

    /* JADX INFO: compiled from: ToastView.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/views/library/views/ToastView$State;", "", "(Ljava/lang/String;I)V", "NORMAL", MediaError.ERROR_TYPE_ERROR, "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum State {
        NORMAL,
        ERROR
    }

    @Override // com.google.android.material.snackbar.ContentViewCallback
    public void animateContentIn(int delay, int duration) {
    }

    @Override // com.google.android.material.snackbar.ContentViewCallback
    public void animateContentOut(int delay, int duration) {
    }

    protected void beforeStrokeGetsDrawn(Canvas canvas) {
    }

    public abstract TextView getTextView();

    protected final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToastView(Context context, StyleSheet styleSheet) {
        Integer height;
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.state = State.NORMAL;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        this.fillPaint = paint;
        Paint paint2 = new Paint();
        paint2.setStrokeWidth(getResources().getDimension(R$dimen.spacer_2));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        this.strokePaint = paint2;
        ButtonSize buttonSize = styleSheet.getButtonSize(ButtonSizeKey.LARGE);
        int iIntValue = -2;
        if (buttonSize != null && (height = buttonSize.getHeight()) != null) {
            iIntValue = height.intValue();
        }
        this.viewHeight = iIntValue;
        this.rect = new RectF();
        this.clipPath = new Path();
        setWillNotDraw(false);
        initPaintColor();
    }

    public final State getState() {
        return this.state;
    }

    public final void setState(State value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.state = value;
        initPaintColor();
        invalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void initPaintColor() {
        /*
            r10 = this;
            com.amazon.music.views.library.styles.StyleSheet r0 = r10.styleSheet
            com.amazon.music.views.library.styles.keys.ColorKey r1 = com.amazon.music.views.library.styles.keys.ColorKey.COLOR_SECONDARY
            java.lang.Integer r0 = r0.getColor(r1)
            com.amazon.music.views.library.styles.StyleSheet r1 = r10.styleSheet
            com.amazon.music.views.library.styles.keys.ColorKey r2 = com.amazon.music.views.library.styles.keys.ColorKey.COLOR_ACCENT
            java.lang.Integer r1 = r1.getColor(r2)
            com.amazon.music.views.library.styles.StyleSheet r2 = r10.styleSheet
            com.amazon.music.views.library.styles.keys.AlphaKey r3 = com.amazon.music.views.library.styles.keys.AlphaKey.GLASS_2
            java.lang.Float r2 = r2.getAlpha(r3)
            com.amazon.music.views.library.styles.StyleSheet r3 = r10.styleSheet
            com.amazon.music.views.library.styles.keys.ColorKey r4 = com.amazon.music.views.library.styles.keys.ColorKey.COLOR_ERROR
            java.lang.Integer r3 = r3.getColor(r4)
            com.amazon.music.views.library.styles.StyleSheet r5 = r10.styleSheet
            com.amazon.music.views.library.styles.keys.AlphaKey r6 = com.amazon.music.views.library.styles.keys.AlphaKey.GLASS_3
            java.lang.Float r5 = r5.getAlpha(r6)
            com.amazon.music.views.library.views.ToastView$State r6 = r10.state
            com.amazon.music.views.library.views.ToastView$State r7 = com.amazon.music.views.library.views.ToastView.State.NORMAL
            r8 = 1065353216(0x3f800000, float:1.0)
            r9 = 0
            if (r6 != r7) goto L4e
            if (r0 == 0) goto L6b
            if (r1 == 0) goto L6b
            if (r2 == 0) goto L6b
            com.amazon.ui.foundations.utils.ColorParser r3 = com.amazon.ui.foundations.utils.ColorParser.INSTANCE
            int r0 = r0.intValue()
            int r1 = r1.intValue()
            float r2 = r2.floatValue()
            int r0 = r3.parseCombinationColor(r0, r8, r1, r2)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L6c
        L4e:
            if (r0 == 0) goto L6b
            if (r3 == 0) goto L6b
            if (r5 == 0) goto L6b
            com.amazon.ui.foundations.utils.ColorParser r1 = com.amazon.ui.foundations.utils.ColorParser.INSTANCE
            int r0 = r0.intValue()
            int r2 = r3.intValue()
            float r3 = r5.floatValue()
            int r0 = r1.parseCombinationColor(r0, r8, r2, r3)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L6c
        L6b:
            r0 = r9
        L6c:
            com.amazon.music.views.library.views.ToastView$State r1 = r10.state
            if (r1 != r7) goto L7f
            android.content.res.Resources r1 = r10.getResources()
            int r2 = com.amazon.music.views.library.R$color.outline_stroke
            int r1 = r1.getColor(r2, r9)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            goto L85
        L7f:
            com.amazon.music.views.library.styles.StyleSheet r1 = r10.styleSheet
            java.lang.Integer r1 = r1.getColor(r4)
        L85:
            if (r0 == 0) goto L90
            android.graphics.Paint r2 = r10.fillPaint
            int r0 = r0.intValue()
            r2.setColor(r0)
        L90:
            if (r1 == 0) goto L9b
            android.graphics.Paint r0 = r10.strokePaint
            int r1 = r1.intValue()
            r0.setColor(r1)
        L9b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.views.library.views.ToastView.initPaintColor():void");
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(this.viewHeight, 1073741824));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = this.rect;
        rectF.left = 0.0f;
        rectF.top = 0.0f;
        rectF.right = getMeasuredWidth();
        this.rect.bottom = getMeasuredHeight();
        updateClipPath();
        if (canvas != null) {
            canvas.clipPath(this.clipPath);
        }
        if (canvas != null) {
            canvas.drawRect(this.rect, this.fillPaint);
        }
        beforeStrokeGetsDrawn(canvas);
        if (canvas == null) {
            return;
        }
        canvas.drawPath(this.clipPath, this.strokePaint);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener l) {
        super.setOnClickListener(l);
        this.toastOnCLickListener = l;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        GestureDetector gestureDetector = this.gestureDetector;
        Boolean boolValueOf = gestureDetector == null ? null : Boolean.valueOf(gestureDetector.onTouchEvent(event));
        return boolValueOf == null ? super.onTouchEvent(event) : boolValueOf.booleanValue();
    }

    public final void setText(String text) {
        getTextView().setText(text);
    }

    public final void setText(int textResId) {
        getTextView().setText(textResId);
    }

    public final void setTextColor(int color) {
        getTextView().setTextColor(color);
    }

    public final void setGestureListener(GestureDetector.OnGestureListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.gestureDetector = new GestureDetector(getContext(), listener);
    }

    /* JADX INFO: renamed from: getOnClickListener, reason: from getter */
    public final View.OnClickListener getToastOnCLickListener() {
        return this.toastOnCLickListener;
    }

    private final void updateClipPath() {
        float height = getHeight() / 2.0f;
        this.clipPath.reset();
        this.clipPath.addRoundRect(this.rect, height, height, Path.Direction.CW);
        this.clipPath.close();
    }

    private final String getLocale() {
        String string = Locale.getDefault().toString();
        Intrinsics.checkNotNullExpressionValue(string, "locale.toString()");
        return string;
    }

    protected final void initTextStyle() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.TERTIARY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getTextView(), fontStyle);
        }
        Integer color = this.styleSheet.getColor(ColorKey.COLOR_PRIMARY);
        if (color != null) {
            getTextView().setTextColor(color.intValue());
        }
        Float textSize = this.styleSheet.getTextSize(getLocale());
        if (textSize == null) {
            return;
        }
        getTextView().setTextSize(textSize.floatValue());
    }
}
