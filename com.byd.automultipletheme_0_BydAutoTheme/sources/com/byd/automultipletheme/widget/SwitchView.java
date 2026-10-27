package com.byd.automultipletheme.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.common.db.DB;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SwitchView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u0002B'\b\u0007\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\u0013H\u0002J*\u0010B\u001a\u00020C2\b\u0010D\u001a\u0004\u0018\u00010\u00192\u0006\u0010.\u001a\u00020\u00132\u0006\u0010E\u001a\u00020\u00132\u0006\u0010F\u001a\u00020\u0013H\u0002J\b\u0010G\u001a\u0004\u0018\u00010\u001dJ\b\u0010H\u001a\u0004\u0018\u00010\u001dJ\u0014\u0010I\u001a\u00020C2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010J\u001a\u00020CH\u0002J\b\u0010K\u001a\u00020CH\u0002J\b\u0010L\u001a\u00020CH\u0002J\u0006\u0010M\u001a\u00020\u001bJ\u0010\u0010N\u001a\u00020C2\u0006\u0010O\u001a\u00020\u0013H\u0002J\u0010\u0010P\u001a\u00020C2\u0006\u0010Q\u001a\u00020\u0001H\u0016J\u0010\u0010R\u001a\u00020C2\u0006\u0010S\u001a\u00020TH\u0014J\u0018\u0010U\u001a\u00020C2\u0006\u0010V\u001a\u00020\b2\u0006\u0010W\u001a\u00020\bH\u0014J\b\u0010X\u001a\u00020CH\u0002J)\u0010Y\u001a\u00020C2\u0006\u0010\n\u001a\u00020\r2\u0012\u0010Z\u001a\n\u0012\u0006\b\u0001\u0012\u00020\\0[\"\u00020\\H\u0002¢\u0006\u0002\u0010]J\u000e\u0010^\u001a\u00020C2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010 \u001a\u00020C2\u0006\u0010\u001c\u001a\u00020\bJ\u0010\u0010_\u001a\u00020C2\b\u0010,\u001a\u0004\u0018\u00010-J\u0010\u0010`\u001a\u00020C2\b\u0010;\u001a\u0004\u0018\u00010\u001dJ\u0010\u0010a\u001a\u00020C2\b\u0010>\u001a\u0004\u0018\u00010\u001dJ\u000e\u0010b\u001a\u00020\b2\u0006\u0010c\u001a\u00020\u0013J\u000e\u0010d\u001a\u00020\u001d2\u0006\u0010e\u001a\u00020\bR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0010\u0010\"\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0010\u00104\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00109\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010;\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010=\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006f"}, d2 = {"Lcom/byd/automultipletheme/widget/SwitchView;", "Landroid/view/View;", "Landroid/view/View$OnClickListener;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "anim", "Landroid/animation/ValueAnimator;", "anim2", "Landroid/animation/ObjectAnimator;", "anim3", "anim4", "animSet", "Landroid/animation/AnimatorSet;", "animatorLift", "", "animatorRight", "bgColor", "bgPaint", "Landroid/graphics/Paint;", "bgPath", "Landroid/graphics/Path;", "checked", "", "clickColor", "", "getClickColor", "()Ljava/lang/String;", "setClickColor", "(Ljava/lang/String;)V", "clickPaint", "clickPath", "leftTextPaint", "mClickWidth", "mHeight", "mLeftTextX", "mLiftTextY", "mRightTextX", "mRightTexty", "mWidth", "onClickCheckedListener", "Lcom/byd/automultipletheme/widget/OnClickCheckedListener;", "padding", "rgb", "getRgb", "()I", "setRgb", "(I)V", "rightTextPaint", "roundRect", "Landroid/graphics/RectF;", "sliderOnLeftColor", "sliderOnRightColor", "textLeft", "textLeftClickColor", "textLeftColor", "textRight", "textRightClickColor", "textRightColor", DB.CAROUSEL_TIME_TIME, "dp2px", "dpValue", "getPath", "", "path", "width", "offset", "getTextLeftColor", "getTextRightColor", "init", "initAnim", "initPaint", "initPath", "isChecked", "offsetWidth", "w", "onClick", "v", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "refreshColor", "setAnimView", "values", "", "", "(Landroid/animation/ObjectAnimator;[Ljava/lang/Object;)V", "setChecked", "setOnClickCheckedListener", "setTextLeftColor", "setTextRightColor", "sp2px", "spValue", "toHexEncoding", "color", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SwitchView extends View implements View.OnClickListener {
    private ValueAnimator anim;
    private ObjectAnimator anim2;
    private ObjectAnimator anim3;
    private ObjectAnimator anim4;
    private AnimatorSet animSet;
    private float animatorLift;
    private float animatorRight;
    private int bgColor;
    private Paint bgPaint;
    private Path bgPath;
    private boolean checked;
    private String clickColor;
    private Paint clickPaint;
    private Path clickPath;
    private Paint leftTextPaint;
    private float mClickWidth;
    private float mHeight;
    private float mLeftTextX;
    private float mLiftTextY;
    private float mRightTextX;
    private float mRightTexty;
    private float mWidth;
    private OnClickCheckedListener onClickCheckedListener;
    private float padding;
    private int rgb;
    private Paint rightTextPaint;
    private RectF roundRect;
    private String sliderOnLeftColor;
    private String sliderOnRightColor;
    private String textLeft;
    private String textLeftClickColor;
    private String textLeftColor;
    private String textRight;
    private String textRightClickColor;
    private String textRightColor;
    private int time;

    public SwitchView(Context context) {
        this(context, null, 0, 6, null);
    }

    public SwitchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ SwitchView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public SwitchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.rgb = Color.rgb(255, 255, 255);
        init(attributeSet);
        initPaint();
        setOnClickListener(this);
    }

    public final String getClickColor() {
        return this.clickColor;
    }

    public final void setClickColor(String str) {
        Paint paint = this.clickPaint;
        Intrinsics.checkNotNull(paint);
        paint.setColor(Color.parseColor(str));
    }

    public final int getRgb() {
        return this.rgb;
    }

    public final void setRgb(int i) {
        this.rgb = i;
    }

    private final void init(AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.SwitchView);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…, R.styleable.SwitchView)");
        this.bgColor = typedArrayObtainStyledAttributes.getColor(0, this.rgb);
        this.sliderOnLeftColor = String.valueOf(typedArrayObtainStyledAttributes.getColor(3, Color.rgb(34, 139, 34)));
        this.sliderOnRightColor = String.valueOf(typedArrayObtainStyledAttributes.getColor(4, Color.rgb(34, 139, 34)));
        this.textLeftColor = String.valueOf(typedArrayObtainStyledAttributes.getColor(7, Color.rgb(0, 0, 0)));
        this.textRightColor = String.valueOf(typedArrayObtainStyledAttributes.getColor(10, Color.rgb(0, 0, 0)));
        this.textLeftClickColor = String.valueOf(typedArrayObtainStyledAttributes.getColor(6, this.rgb));
        this.textRightClickColor = String.valueOf(typedArrayObtainStyledAttributes.getColor(9, this.rgb));
        this.checked = typedArrayObtainStyledAttributes.getBoolean(2, false);
        this.textLeft = typedArrayObtainStyledAttributes.getString(5);
        this.textRight = typedArrayObtainStyledAttributes.getString(8);
        this.padding = typedArrayObtainStyledAttributes.getDimension(1, dp2px(4.0f));
        this.time = typedArrayObtainStyledAttributes.getInteger(11, 300);
        typedArrayObtainStyledAttributes.recycle();
    }

    private final void initPaint() {
        this.animSet = new AnimatorSet();
        this.anim = new ValueAnimator();
        ObjectAnimator objectAnimator = new ObjectAnimator();
        this.anim2 = objectAnimator;
        Intrinsics.checkNotNull(objectAnimator);
        objectAnimator.setTarget(this);
        ObjectAnimator objectAnimator2 = this.anim2;
        Intrinsics.checkNotNull(objectAnimator2);
        objectAnimator2.setPropertyName("textLeftColor");
        ObjectAnimator objectAnimator3 = new ObjectAnimator();
        this.anim3 = objectAnimator3;
        Intrinsics.checkNotNull(objectAnimator3);
        objectAnimator3.setTarget(this);
        ObjectAnimator objectAnimator4 = this.anim3;
        Intrinsics.checkNotNull(objectAnimator4);
        objectAnimator4.setPropertyName("textRightColor");
        ObjectAnimator objectAnimator5 = new ObjectAnimator();
        this.anim4 = objectAnimator5;
        Intrinsics.checkNotNull(objectAnimator5);
        objectAnimator5.setTarget(this);
        ObjectAnimator objectAnimator6 = this.anim4;
        Intrinsics.checkNotNull(objectAnimator6);
        objectAnimator6.setPropertyName("clickColor");
        this.roundRect = new RectF();
        this.clickPath = new Path();
        this.bgPath = new Path();
        Paint paint = new Paint();
        this.bgPaint = paint;
        Intrinsics.checkNotNull(paint);
        paint.setColor(this.bgColor);
        Paint paint2 = this.bgPaint;
        Intrinsics.checkNotNull(paint2);
        paint2.setAntiAlias(true);
        this.clickPaint = new Paint();
        refreshColor();
        Paint paint3 = this.clickPaint;
        Intrinsics.checkNotNull(paint3);
        paint3.setAntiAlias(true);
        Paint paint4 = new Paint();
        this.leftTextPaint = paint4;
        Intrinsics.checkNotNull(paint4);
        paint4.setTextSize(sp2px(21.0f));
        Paint paint5 = this.leftTextPaint;
        Intrinsics.checkNotNull(paint5);
        paint5.setAntiAlias(true);
        Paint paint6 = new Paint();
        this.rightTextPaint = paint6;
        Intrinsics.checkNotNull(paint6);
        paint6.setTextSize(sp2px(21.0f));
        Paint paint7 = this.rightTextPaint;
        Intrinsics.checkNotNull(paint7);
        paint7.setAntiAlias(true);
        if (!getChecked()) {
            Paint paint8 = this.leftTextPaint;
            Intrinsics.checkNotNull(paint8);
            String str = this.textLeftClickColor;
            Intrinsics.checkNotNull(str);
            paint8.setColor(Integer.parseInt(str));
            Paint paint9 = this.rightTextPaint;
            Intrinsics.checkNotNull(paint9);
            String str2 = this.textRightColor;
            Intrinsics.checkNotNull(str2);
            paint9.setColor(Integer.parseInt(str2));
            return;
        }
        Paint paint10 = this.rightTextPaint;
        Intrinsics.checkNotNull(paint10);
        String str3 = this.textRightClickColor;
        Intrinsics.checkNotNull(str3);
        paint10.setColor(Integer.parseInt(str3));
        Paint paint11 = this.leftTextPaint;
        Intrinsics.checkNotNull(paint11);
        String str4 = this.textLeftColor;
        Intrinsics.checkNotNull(str4);
        paint11.setColor(Integer.parseInt(str4));
    }

    private final void refreshColor() {
        if (!getChecked()) {
            String str = this.sliderOnLeftColor;
            Intrinsics.checkNotNull(str);
            setClickColor(Integer.parseInt(str));
        } else {
            String str2 = this.sliderOnRightColor;
            Intrinsics.checkNotNull(str2);
            setClickColor(Integer.parseInt(str2));
        }
    }

    private final void initPath() {
        float f = 2;
        this.mClickWidth = (this.mWidth - (this.padding * f)) / f;
        if (!getChecked()) {
            offsetWidth(0.0f);
        } else {
            offsetWidth(this.mClickWidth);
        }
        getPath(this.bgPath, 0.0f, this.mWidth, 0.0f);
        float f2 = this.mClickWidth;
        Paint paint = this.leftTextPaint;
        Intrinsics.checkNotNull(paint);
        float fMeasureText = (f2 - paint.measureText(this.textLeft)) / f;
        float f3 = this.padding;
        this.mLeftTextX = fMeasureText + f3;
        float f4 = this.mClickWidth;
        Paint paint2 = this.rightTextPaint;
        Intrinsics.checkNotNull(paint2);
        this.mRightTextX = f3 + f4 + ((f4 - paint2.measureText(this.textRight)) / f);
        Paint paint3 = this.leftTextPaint;
        Intrinsics.checkNotNull(paint3);
        Paint.FontMetrics fontMetrics = paint3.getFontMetrics();
        this.mLiftTextY = (this.mHeight / f) + ((Math.abs(fontMetrics.ascent) - fontMetrics.descent) / f);
        Paint paint4 = this.rightTextPaint;
        Intrinsics.checkNotNull(paint4);
        Paint.FontMetrics fontMetrics2 = paint4.getFontMetrics();
        this.mRightTexty = (this.mHeight / f) + ((Math.abs(fontMetrics2.ascent) - fontMetrics2.descent) / f);
        this.animatorRight = 0.0f;
        this.animatorLift = this.mClickWidth;
    }

    private final void initAnim() {
        AnimatorSet animatorSet = this.animSet;
        if (animatorSet != null) {
            Intrinsics.checkNotNull(animatorSet);
            if (animatorSet.isRunning()) {
                AnimatorSet animatorSet2 = this.animSet;
                Intrinsics.checkNotNull(animatorSet2);
                animatorSet2.cancel();
            }
        }
        this.animSet = new AnimatorSet();
        if (getChecked()) {
            ValueAnimator valueAnimator = this.anim;
            Intrinsics.checkNotNull(valueAnimator);
            valueAnimator.setFloatValues(this.animatorRight, this.mClickWidth);
            ObjectAnimator objectAnimator = this.anim2;
            Intrinsics.checkNotNull(objectAnimator);
            String str = this.textLeftClickColor;
            Intrinsics.checkNotNull(str);
            String str2 = this.textLeftColor;
            Intrinsics.checkNotNull(str2);
            setAnimView(objectAnimator, toHexEncoding(Integer.parseInt(str)), toHexEncoding(Integer.parseInt(str2)));
            ObjectAnimator objectAnimator2 = this.anim3;
            Intrinsics.checkNotNull(objectAnimator2);
            String str3 = this.textRightColor;
            Intrinsics.checkNotNull(str3);
            String str4 = this.textRightClickColor;
            Intrinsics.checkNotNull(str4);
            setAnimView(objectAnimator2, toHexEncoding(Integer.parseInt(str3)), toHexEncoding(Integer.parseInt(str4)));
            ObjectAnimator objectAnimator3 = this.anim4;
            Intrinsics.checkNotNull(objectAnimator3);
            String str5 = this.sliderOnLeftColor;
            Intrinsics.checkNotNull(str5);
            String str6 = this.sliderOnRightColor;
            Intrinsics.checkNotNull(str6);
            setAnimView(objectAnimator3, toHexEncoding(Integer.parseInt(str5)), toHexEncoding(Integer.parseInt(str6)));
        } else {
            ValueAnimator valueAnimator2 = this.anim;
            Intrinsics.checkNotNull(valueAnimator2);
            valueAnimator2.setFloatValues(this.animatorLift, 0.0f);
            ObjectAnimator objectAnimator4 = this.anim2;
            Intrinsics.checkNotNull(objectAnimator4);
            String str7 = this.textLeftColor;
            Intrinsics.checkNotNull(str7);
            String str8 = this.textLeftClickColor;
            Intrinsics.checkNotNull(str8);
            setAnimView(objectAnimator4, toHexEncoding(Integer.parseInt(str7)), toHexEncoding(Integer.parseInt(str8)));
            ObjectAnimator objectAnimator5 = this.anim3;
            Intrinsics.checkNotNull(objectAnimator5);
            String str9 = this.textRightClickColor;
            Intrinsics.checkNotNull(str9);
            String str10 = this.textRightColor;
            Intrinsics.checkNotNull(str10);
            setAnimView(objectAnimator5, toHexEncoding(Integer.parseInt(str9)), toHexEncoding(Integer.parseInt(str10)));
            ObjectAnimator objectAnimator6 = this.anim4;
            Intrinsics.checkNotNull(objectAnimator6);
            String str11 = this.sliderOnRightColor;
            Intrinsics.checkNotNull(str11);
            String str12 = this.sliderOnLeftColor;
            Intrinsics.checkNotNull(str12);
            setAnimView(objectAnimator6, toHexEncoding(Integer.parseInt(str11)), toHexEncoding(Integer.parseInt(str12)));
        }
        ValueAnimator valueAnimator3 = this.anim;
        Intrinsics.checkNotNull(valueAnimator3);
        valueAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.byd.automultipletheme.widget.SwitchView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator4) {
                SwitchView.m108initAnim$lambda0(this.f$0, valueAnimator4);
            }
        });
        AnimatorSet animatorSet3 = this.animSet;
        Intrinsics.checkNotNull(animatorSet3);
        animatorSet3.play(this.anim).with(this.anim2).with(this.anim3).with(this.anim4);
        AnimatorSet animatorSet4 = this.animSet;
        Intrinsics.checkNotNull(animatorSet4);
        animatorSet4.setDuration(this.time);
        AnimatorSet animatorSet5 = this.animSet;
        Intrinsics.checkNotNull(animatorSet5);
        animatorSet5.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0058  */
    /* JADX INFO: renamed from: initAnim$lambda-0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m108initAnim$lambda0(com.byd.automultipletheme.widget.SwitchView r6, android.animation.ValueAnimator r7) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.Object r0 = r7.getAnimatedValue()
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Float"
            java.util.Objects.requireNonNull(r0, r1)
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            r2 = 1
            r3 = 0
            r4 = 0
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 != 0) goto L1e
            r0 = 1
            goto L1f
        L1e:
            r0 = 0
        L1f:
            if (r0 != 0) goto L58
            java.lang.Object r0 = r7.getAnimatedValue()
            java.util.Objects.requireNonNull(r0, r1)
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            float r5 = r6.mClickWidth
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 != 0) goto L35
            goto L36
        L35:
            r2 = 0
        L36:
            if (r2 == 0) goto L39
            goto L58
        L39:
            java.lang.Object r0 = r7.getAnimatedValue()
            java.util.Objects.requireNonNull(r0, r1)
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            r6.animatorRight = r0
            java.lang.Object r0 = r7.getAnimatedValue()
            java.util.Objects.requireNonNull(r0, r1)
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            r6.animatorLift = r0
            goto L5e
        L58:
            r6.animatorRight = r4
            float r0 = r6.mClickWidth
            r6.animatorLift = r0
        L5e:
            java.lang.Object r7 = r7.getAnimatedValue()
            java.util.Objects.requireNonNull(r7, r1)
            java.lang.Float r7 = (java.lang.Float) r7
            float r7 = r7.floatValue()
            r6.offsetWidth(r7)
            r6.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.automultipletheme.widget.SwitchView.m108initAnim$lambda0(com.byd.automultipletheme.widget.SwitchView, android.animation.ValueAnimator):void");
    }

    private final void setAnimView(ObjectAnimator anim, Object... values) {
        anim.setObjectValues(Arrays.copyOf(values, values.length));
        anim.setEvaluator(new ColorEvaluator());
    }

    private final void offsetWidth(float w) {
        getPath(this.clickPath, this.padding, this.mClickWidth, w);
    }

    private final void getPath(Path path, float padding, float width, float offset) {
        RectF rectF = this.roundRect;
        Intrinsics.checkNotNull(rectF);
        float f = 0 + padding;
        rectF.set(f + offset, f, width + padding + offset, this.mHeight - padding);
        Intrinsics.checkNotNull(path);
        path.rewind();
        RectF rectF2 = this.roundRect;
        Intrinsics.checkNotNull(rectF2);
        float f2 = this.mHeight;
        float f3 = 2;
        path.addRoundRect(rectF2, f2 / f3, f2 / f3, Path.Direction.CW);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float size = View.MeasureSpec.getSize(widthMeasureSpec);
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        float size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        if (mode == 1073741824) {
            this.mWidth = size;
        }
        if (mode2 == 1073741824) {
            this.mHeight = size2;
        }
        setMeasuredDimension((int) this.mWidth, (int) this.mHeight);
        initPath();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawColor(0);
        Path path = this.bgPath;
        Intrinsics.checkNotNull(path);
        Paint paint = this.bgPaint;
        Intrinsics.checkNotNull(paint);
        canvas.drawPath(path, paint);
        Path path2 = this.clickPath;
        Intrinsics.checkNotNull(path2);
        Paint paint2 = this.clickPaint;
        Intrinsics.checkNotNull(paint2);
        canvas.drawPath(path2, paint2);
        String str = this.textLeft;
        Intrinsics.checkNotNull(str);
        float f = this.mLeftTextX;
        float f2 = this.mLiftTextY;
        Paint paint3 = this.leftTextPaint;
        Intrinsics.checkNotNull(paint3);
        canvas.drawText(str, f, f2, paint3);
        String str2 = this.textRight;
        Intrinsics.checkNotNull(str2);
        float f3 = this.mRightTextX;
        float f4 = this.mRightTexty;
        Paint paint4 = this.rightTextPaint;
        Intrinsics.checkNotNull(paint4);
        canvas.drawText(str2, f3, f4, paint4);
    }

    private final int dp2px(float dpValue) {
        return (int) ((dpValue * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public final int sp2px(float spValue) {
        return (int) ((spValue * getContext().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public final String getTextRightColor() {
        return this.textRightColor;
    }

    public final void setTextRightColor(String textRightColor) {
        Paint paint = this.rightTextPaint;
        Intrinsics.checkNotNull(paint);
        paint.setColor(Color.parseColor(textRightColor));
    }

    public final String getTextLeftColor() {
        return this.textLeftColor;
    }

    public final void setTextLeftColor(String textLeftColor) {
        Paint paint = this.leftTextPaint;
        Intrinsics.checkNotNull(paint);
        paint.setColor(Color.parseColor(textLeftColor));
    }

    public final void setClickColor(int clickColor) {
        Paint paint = this.clickPaint;
        Intrinsics.checkNotNull(paint);
        paint.setColor(clickColor);
    }

    public final String toHexEncoding(int color) {
        StringBuffer stringBuffer = new StringBuffer();
        String hexString = Integer.toHexString(Color.red(color));
        Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(Color.red(color))");
        String hexString2 = Integer.toHexString(Color.green(color));
        Intrinsics.checkNotNullExpressionValue(hexString2, "toHexString(Color.green(color))");
        String hexString3 = Integer.toHexString(Color.blue(color));
        Intrinsics.checkNotNullExpressionValue(hexString3, "toHexString(Color.blue(color))");
        if (hexString.length() == 1) {
            hexString = '0' + hexString;
        }
        if (hexString2.length() == 1) {
            hexString2 = '0' + hexString2;
        }
        if (hexString3.length() == 1) {
            hexString3 = '0' + hexString3;
        }
        stringBuffer.append("#");
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String upperCase = hexString.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        stringBuffer.append(upperCase);
        Locale locale2 = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
        String upperCase2 = hexString2.toUpperCase(locale2);
        Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(locale)");
        stringBuffer.append(upperCase2);
        Locale locale3 = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale3, "getDefault()");
        String upperCase3 = hexString3.toUpperCase(locale3);
        Intrinsics.checkNotNullExpressionValue(upperCase3, "this as java.lang.String).toUpperCase(locale)");
        stringBuffer.append(upperCase3);
        String string = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        setChecked(!this.checked);
        OnClickCheckedListener onClickCheckedListener = this.onClickCheckedListener;
        if (onClickCheckedListener != null) {
            Intrinsics.checkNotNull(onClickCheckedListener);
            onClickCheckedListener.onClick();
        }
    }

    public final void setChecked(boolean checked) {
        if (checked == this.checked) {
            return;
        }
        this.checked = checked;
        initAnim();
    }

    public final void setOnClickCheckedListener(OnClickCheckedListener onClickCheckedListener) {
        this.onClickCheckedListener = onClickCheckedListener;
    }

    /* JADX INFO: renamed from: isChecked, reason: from getter */
    public final boolean getChecked() {
        return this.checked;
    }
}
