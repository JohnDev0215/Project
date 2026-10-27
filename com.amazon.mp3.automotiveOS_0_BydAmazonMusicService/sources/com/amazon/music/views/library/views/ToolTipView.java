package com.amazon.music.views.library.views;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.appcompat.widget.AppCompatTextView;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: ToolTipView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 @2\u00020\u0001:\u0002@AB\u0011\b\u0016\u0012\u0006\u0010=\u001a\u00020<¢\u0006\u0004\b>\u0010?J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002J9\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0004H\u0002J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0012\u0010\u0017\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0014J\u0012\u0010\u001a\u001a\u00020\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0014R.\u0010\u001d\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001b\u0010+\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010%R\u001b\u0010.\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010)\u001a\u0004\b-\u0010%R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00101R\u0014\u00103\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00101R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u0010#R\u0016\u00108\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010:\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u00109R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010;¨\u0006B"}, d2 = {"Lcom/amazon/music/views/library/views/ToolTipView;", "Landroidx/appcompat/widget/AppCompatTextView;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "initPaintsColor", "initMaxWidth", "refreshPath", "", "arrowPosition", "left", "top", "right", "bottom", "getFitArrowPositionToDraw", "(FFFFF)Ljava/lang/Float;", "initPadding", "", "dp", "dpToPx", "initStyleSheet", "Landroid/graphics/Canvas;", "canvas", "onDraw", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "Lcom/amazon/music/views/library/views/ToolTipView$Direction;", "value", "direction", "Lcom/amazon/music/views/library/views/ToolTipView$Direction;", "getDirection", "()Lcom/amazon/music/views/library/views/ToolTipView$Direction;", "setDirection", "(Lcom/amazon/music/views/library/views/ToolTipView$Direction;)V", "F", "getArrowPosition", "()F", "setArrowPosition", "(F)V", "arrowHeight$delegate", "Lkotlin/Lazy;", "getArrowHeight", "arrowHeight", "arrowWidth$delegate", "getArrowWidth", "arrowWidth", "Landroid/graphics/Paint;", "baseFillPaint", "Landroid/graphics/Paint;", "fillPaint", "strokePaint", "Landroid/graphics/Path;", "path", "Landroid/graphics/Path;", "cornerRadius", "basePadding", "I", "mediumPadding", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "Direction", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class ToolTipView extends AppCompatTextView {

    /* JADX INFO: renamed from: arrowHeight$delegate, reason: from kotlin metadata */
    private final Lazy arrowHeight;
    private float arrowPosition;

    /* JADX INFO: renamed from: arrowWidth$delegate, reason: from kotlin metadata */
    private final Lazy arrowWidth;
    private final Paint baseFillPaint;
    private int basePadding;
    private float cornerRadius;
    private Direction direction;
    private final Paint fillPaint;
    private int mediumPadding;
    private final Path path;
    private final Paint strokePaint;
    private StyleSheet styleSheet;

    /* JADX INFO: compiled from: ToolTipView.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/views/library/views/ToolTipView$Direction;", "", "(Ljava/lang/String;I)V", "LEFT", "TOP", "RIGHT", "BOTTOM", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum Direction {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }

    /* JADX INFO: compiled from: ToolTipView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ScreenSizeKey.values().length];
            iArr[ScreenSizeKey.XSMALL.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Direction.values().length];
            iArr2[Direction.TOP.ordinal()] = 1;
            iArr2[Direction.BOTTOM.ordinal()] = 2;
            iArr2[Direction.LEFT.ordinal()] = 3;
            iArr2[Direction.RIGHT.ordinal()] = 4;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public final Direction getDirection() {
        return this.direction;
    }

    public final void setDirection(Direction direction) {
        this.direction = direction;
        initPadding();
    }

    public final float getArrowPosition() {
        return this.arrowPosition;
    }

    public final void setArrowPosition(float f) {
        this.arrowPosition = f;
    }

    private final float getArrowHeight() {
        return ((Number) this.arrowHeight.getValue()).floatValue();
    }

    private final float getArrowWidth() {
        return ((Number) this.arrowWidth.getValue()).floatValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToolTipView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.arrowHeight = LazyKt__LazyJVMKt.lazy(new Function0<Float>() { // from class: com.amazon.music.views.library.views.ToolTipView$arrowHeight$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getResources().getDimension(R$dimen.spacer_10));
            }
        });
        this.arrowWidth = LazyKt__LazyJVMKt.lazy(new Function0<Float>() { // from class: com.amazon.music.views.library.views.ToolTipView$arrowWidth$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getResources().getDimension(R$dimen.spacer_20));
            }
        });
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        this.baseFillPaint = paint;
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        this.fillPaint = paint2;
        Paint paint3 = new Paint();
        paint3.setStrokeWidth(getResources().getDimension(R$dimen.spacer_1));
        paint3.setColor(getContext().getColor(R$color.outline_stroke));
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setAntiAlias(true);
        this.strokePaint = paint3;
        this.path = new Path();
        setMinHeight(getResources().getDimensionPixelSize(R$dimen.spacer_64));
    }

    public final void initStyleSheet(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.BASE);
        if (spacerInPixels != null) {
            this.basePadding = spacerInPixels.intValue();
        }
        Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        if (spacerInPixels2 != null) {
            this.mediumPadding = spacerInPixels2.intValue();
        }
        if (styleSheet.getCornerSize(CornerSizeKey.MEDIUM) != null) {
            this.cornerRadius = r0.intValue();
        }
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(this, fontStyle);
        }
        Integer color = styleSheet.getColor(ColorKey.COLOR_PRIMARY);
        if (color != null) {
            setTextColor(color.intValue());
        }
        initMaxWidth(styleSheet);
        initPaintsColor(styleSheet);
        initPadding();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        refreshPath();
        if (canvas != null) {
            canvas.drawPath(this.path, this.baseFillPaint);
        }
        if (canvas != null) {
            canvas.drawPath(this.path, this.fillPaint);
        }
        if (canvas != null) {
            canvas.drawPath(this.path, this.strokePaint);
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet != null) {
            initMaxWidth(styleSheet);
        }
        initPadding();
    }

    private final void initPaintsColor(StyleSheet styleSheet) {
        Integer color = styleSheet.getColor(ColorKey.COLOR_SECONDARY);
        if (color != null) {
            this.baseFillPaint.setColor(color.intValue());
        }
        Integer color2 = styleSheet.getColor(ColorKey.COLOR_ACCENT, AlphaKey.GLASS_2);
        if (color2 == null) {
            return;
        }
        this.fillPaint.setColor(color2.intValue());
    }

    private final void initMaxWidth(StyleSheet styleSheet) {
        int iDpToPx;
        if (WhenMappings.$EnumSwitchMapping$0[styleSheet.getWidthScreenSizeKey().ordinal()] == 1) {
            iDpToPx = dpToPx(380);
        } else {
            iDpToPx = dpToPx(374);
        }
        setMaxWidth(iDpToPx);
    }

    private final void refreshPath() {
        float strokeWidth = this.strokePaint.getStrokeWidth() / 2.0f;
        Direction direction = this.direction;
        Direction direction2 = Direction.TOP;
        float arrowHeight = (direction == direction2 ? getArrowHeight() : 0.0f) + 0.0f + strokeWidth;
        Direction direction3 = this.direction;
        Direction direction4 = Direction.LEFT;
        float arrowHeight2 = (direction3 == direction4 ? getArrowHeight() : 0.0f) + 0.0f + strokeWidth;
        float width = getWidth();
        Direction direction5 = this.direction;
        Direction direction6 = Direction.RIGHT;
        float arrowHeight3 = (width - (direction5 == direction6 ? getArrowHeight() : 0.0f)) - strokeWidth;
        float height = getHeight();
        Direction direction7 = this.direction;
        Direction direction8 = Direction.BOTTOM;
        float arrowHeight4 = (height - (direction7 == direction8 ? getArrowHeight() : 0.0f)) - strokeWidth;
        Float fitArrowPositionToDraw = getFitArrowPositionToDraw(this.arrowPosition, arrowHeight2, arrowHeight, arrowHeight3, arrowHeight4);
        this.path.reset();
        this.path.moveTo(this.cornerRadius + arrowHeight2, arrowHeight);
        if (fitArrowPositionToDraw != null && this.direction == direction2) {
            this.path.lineTo(fitArrowPositionToDraw.floatValue() - (getArrowWidth() / 2.0f), arrowHeight);
            this.path.lineTo(fitArrowPositionToDraw.floatValue(), arrowHeight - getArrowHeight());
            this.path.lineTo(fitArrowPositionToDraw.floatValue() + (getArrowWidth() / 2.0f), arrowHeight);
        }
        this.path.lineTo(arrowHeight3 - this.cornerRadius, arrowHeight);
        this.path.quadTo(arrowHeight3, arrowHeight, arrowHeight3, this.cornerRadius + arrowHeight);
        if (fitArrowPositionToDraw != null && this.direction == direction6) {
            this.path.lineTo(arrowHeight3, fitArrowPositionToDraw.floatValue() - (getArrowWidth() / 2.0f));
            this.path.lineTo(getArrowHeight() + arrowHeight3, fitArrowPositionToDraw.floatValue());
            this.path.lineTo(arrowHeight3, fitArrowPositionToDraw.floatValue() + (getArrowWidth() / 2.0f));
        }
        this.path.lineTo(arrowHeight3, arrowHeight4 - this.cornerRadius);
        this.path.quadTo(arrowHeight3, arrowHeight4, arrowHeight3 - this.cornerRadius, arrowHeight4);
        if (fitArrowPositionToDraw != null && this.direction == direction8) {
            this.path.lineTo(fitArrowPositionToDraw.floatValue() + (getArrowWidth() / 2.0f), arrowHeight4);
            this.path.lineTo(fitArrowPositionToDraw.floatValue(), getArrowHeight() + arrowHeight4);
            this.path.lineTo(fitArrowPositionToDraw.floatValue() - (getArrowWidth() / 2.0f), arrowHeight4);
        }
        this.path.lineTo(this.cornerRadius + arrowHeight2, arrowHeight4);
        this.path.quadTo(arrowHeight2, arrowHeight4, arrowHeight2, arrowHeight4 - this.cornerRadius);
        if (fitArrowPositionToDraw != null && this.direction == direction4) {
            this.path.lineTo(arrowHeight2, fitArrowPositionToDraw.floatValue() + (getArrowWidth() / 2.0f));
            this.path.lineTo(arrowHeight2 - getArrowHeight(), fitArrowPositionToDraw.floatValue());
            this.path.lineTo(arrowHeight2, fitArrowPositionToDraw.floatValue() - (getArrowWidth() / 2.0f));
        }
        this.path.lineTo(arrowHeight2, this.cornerRadius + arrowHeight);
        this.path.quadTo(arrowHeight2, arrowHeight, this.cornerRadius + arrowHeight2, arrowHeight);
        this.path.close();
    }

    private final Float getFitArrowPositionToDraw(float arrowPosition, float left, float top, float right, float bottom) {
        float arrowWidth;
        float arrowWidth2;
        Direction direction = this.direction;
        int i = direction == null ? -1 : WhenMappings.$EnumSwitchMapping$1[direction.ordinal()];
        if (i == 1 || i == 2) {
            float f = 2;
            arrowWidth = (getArrowWidth() / f) + left + this.cornerRadius;
            arrowWidth2 = (right - this.cornerRadius) - (getArrowWidth() / f);
        } else {
            if (i != 3 && i != 4) {
                return null;
            }
            float f2 = 2;
            arrowWidth = top + this.cornerRadius + (getArrowWidth() / f2);
            arrowWidth2 = (bottom - this.cornerRadius) - (getArrowWidth() / f2);
        }
        if (arrowWidth2 < arrowWidth) {
            return null;
        }
        return Float.valueOf(RangesKt___RangesKt.coerceIn(arrowPosition, arrowWidth, arrowWidth2));
    }

    private final void initPadding() {
        int i = this.basePadding;
        Direction direction = getDirection();
        int i2 = direction == null ? -1 : WhenMappings.$EnumSwitchMapping$1[direction.ordinal()];
        if (i2 == 1) {
            setPadding(i, this.mediumPadding + ((int) getArrowHeight()), i, i);
            return;
        }
        if (i2 == 2) {
            setPadding(i, i, i, this.mediumPadding + ((int) getArrowHeight()));
            return;
        }
        if (i2 == 3) {
            setPadding(this.mediumPadding + ((int) getArrowHeight()), i, i, i);
        } else if (i2 == 4) {
            setPadding(i, i, this.mediumPadding + ((int) getArrowHeight()), i);
        } else {
            setPadding(i, i, i, i);
        }
    }

    private final int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
