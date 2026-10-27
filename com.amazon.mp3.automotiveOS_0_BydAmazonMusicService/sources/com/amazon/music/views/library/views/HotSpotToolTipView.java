package com.amazon.music.views.library.views;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.FontSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: HotSpotToolTipView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 Y2\u00020\u0001:\u0002YZB\u0011\b\u0016\u0012\u0006\u0010V\u001a\u00020U¢\u0006\u0004\bW\u0010XJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002J9\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0004H\u0002J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\u0016\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016J\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0014J\u000e\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0014J\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0012\u0010\u001e\u001a\u00020\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0014J\u0012\u0010!\u001a\u00020\u00042\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0014J\u000e\u0010\"\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0002R.\u0010%\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010#8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001b\u00103\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u0010-R\u001b\u00106\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b4\u00101\u001a\u0004\b5\u0010-R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u00109R\u0014\u0010;\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u00109R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u0016\u0010?\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u0010+R\u0016\u0010@\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010B\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u0010AR\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010CR\u001b\u0010G\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bD\u00101\u001a\u0004\bE\u0010FR\u001b\u0010L\u001a\u00020H8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bI\u00101\u001a\u0004\bJ\u0010KR\u001b\u0010O\u001a\u00020H8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bM\u00101\u001a\u0004\bN\u0010KR\u001b\u0010T\u001a\u00020P8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bQ\u00101\u001a\u0004\bR\u0010S¨\u0006["}, d2 = {"Lcom/amazon/music/views/library/views/HotSpotToolTipView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "initPaintsColor", "initMaxWidth", "refreshPath", "", "arrowPosition", "left", "top", "right", "bottom", "getFitArrowPositionToDraw", "(FFFFF)Ljava/lang/Float;", "initPadding", "", "dp", "dpToPx", "", "text", "Landroid/view/View$OnClickListener;", "onClickListener", "setButton", "setTitle", "setText", "initStyleSheet", "Landroid/graphics/Canvas;", "canvas", "onDraw", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "getWidthConstraints", "Lcom/amazon/music/views/library/views/HotSpotToolTipView$Direction;", "value", "direction", "Lcom/amazon/music/views/library/views/HotSpotToolTipView$Direction;", "getDirection", "()Lcom/amazon/music/views/library/views/HotSpotToolTipView$Direction;", "setDirection", "(Lcom/amazon/music/views/library/views/HotSpotToolTipView$Direction;)V", "F", "getArrowPosition", "()F", "setArrowPosition", "(F)V", "arrowHeight$delegate", "Lkotlin/Lazy;", "getArrowHeight", "arrowHeight", "arrowWidth$delegate", "getArrowWidth", "arrowWidth", "Landroid/graphics/Paint;", "baseFillPaint", "Landroid/graphics/Paint;", "fillPaint", "strokePaint", "Landroid/graphics/Path;", "path", "Landroid/graphics/Path;", "cornerRadius", "basePadding", "I", "mediumPadding", "Lcom/amazon/music/views/library/styles/StyleSheet;", "layout$delegate", "getLayout", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "layout", "Landroid/widget/TextView;", "title$delegate", "getTitle", "()Landroid/widget/TextView;", "title", "primaryText$delegate", "getPrimaryText", ContextMappingConstants.PRIMARY_TEXT, "Lcom/amazon/ui/foundations/views/BaseButton;", "baseButton$delegate", "getBaseButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "baseButton", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "Direction", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class HotSpotToolTipView extends ConstraintLayout {

    /* JADX INFO: renamed from: arrowHeight$delegate, reason: from kotlin metadata */
    private final Lazy arrowHeight;
    private float arrowPosition;

    /* JADX INFO: renamed from: arrowWidth$delegate, reason: from kotlin metadata */
    private final Lazy arrowWidth;

    /* JADX INFO: renamed from: baseButton$delegate, reason: from kotlin metadata */
    private final Lazy baseButton;
    private final Paint baseFillPaint;
    private int basePadding;
    private float cornerRadius;
    private Direction direction;
    private final Paint fillPaint;

    /* JADX INFO: renamed from: layout$delegate, reason: from kotlin metadata */
    private final Lazy layout;
    private int mediumPadding;
    private final Path path;

    /* JADX INFO: renamed from: primaryText$delegate, reason: from kotlin metadata */
    private final Lazy primaryText;
    private final Paint strokePaint;
    private StyleSheet styleSheet;

    /* JADX INFO: renamed from: title$delegate, reason: from kotlin metadata */
    private final Lazy title;

    /* JADX INFO: compiled from: HotSpotToolTipView.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/views/library/views/HotSpotToolTipView$Direction;", "", "(Ljava/lang/String;I)V", "LEFT", "TOP", "RIGHT", "BOTTOM", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum Direction {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }

    /* JADX INFO: compiled from: HotSpotToolTipView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ScreenSizeKey.values().length];
            iArr[ScreenSizeKey.XSMALL.ordinal()] = 1;
            iArr[ScreenSizeKey.SMALL.ordinal()] = 2;
            iArr[ScreenSizeKey.MEDIUM.ordinal()] = 3;
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
    public HotSpotToolTipView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.arrowHeight = LazyKt__LazyJVMKt.lazy(new Function0<Float>() { // from class: com.amazon.music.views.library.views.HotSpotToolTipView$arrowHeight$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getResources().getDimension(R$dimen.spacer_10));
            }
        });
        this.arrowWidth = LazyKt__LazyJVMKt.lazy(new Function0<Float>() { // from class: com.amazon.music.views.library.views.HotSpotToolTipView$arrowWidth$2
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
        this.layout = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.HotSpotToolTipView$layout$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.tooltip_layout_root);
            }
        });
        this.title = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HotSpotToolTipView$title$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.primaryText = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HotSpotToolTipView$primaryText$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.text);
            }
        });
        this.baseButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.HotSpotToolTipView$baseButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.baseButton);
            }
        });
        View viewInflate = View.inflate(getContext(), R$layout.dmmviewlibrary_tooltip_layout, this);
        if (viewInflate == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
        }
        setWillNotDraw(false);
    }

    public final ConstraintLayout getLayout() {
        Object value = this.layout.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-layout>(...)");
        return (ConstraintLayout) value;
    }

    public final TextView getTitle() {
        Object value = this.title.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-title>(...)");
        return (TextView) value;
    }

    public final TextView getPrimaryText() {
        Object value = this.primaryText.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryText>(...)");
        return (TextView) value;
    }

    public final BaseButton getBaseButton() {
        Object value = this.baseButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-baseButton>(...)");
        return (BaseButton) value;
    }

    public final void setButton(String text, View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getBaseButton().setText(text);
        getBaseButton().setOnClickListener(onClickListener);
    }

    public final void setTitle(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        getTitle().setText(text);
    }

    public final void setText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        getPrimaryText().setText(text);
    }

    public final void initStyleSheet(StyleSheet styleSheet) {
        FontStyle fontStyle;
        FontStyle fontStyleCopy$default;
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
        FontSize fontSize = styleSheet.getFontSize(FontSizeKey.BODY_1);
        if (fontSize != null && (fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4)) != null && (fontStyleCopy$default = FontStyle.copy$default(fontStyle, fontSize, null, 0, null, 14, null)) != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitle(), fontStyleCopy$default);
        }
        FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getPrimaryText(), fontStyle2);
        }
        Integer color = styleSheet.getColor(ColorKey.COLOR_PRIMARY);
        if (color != null) {
            int iIntValue = color.intValue();
            getPrimaryText().setTextColor(iIntValue);
            getTitle().setTextColor(iIntValue);
        }
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID);
        if (buttonBuilder != null) {
            buttonBuilder.applyStyle(getBaseButton());
        }
        initMaxWidth(styleSheet);
        initPaintsColor(styleSheet);
        initPadding();
    }

    @Override // android.view.View
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

    @Override // android.view.View
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
        int widthConstraints = getWidthConstraints(styleSheet);
        getLayout().setMaxWidth(widthConstraints);
        getLayout().setMinWidth(widthConstraints);
    }

    public final int getWidthConstraints(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        int i = WhenMappings.$EnumSwitchMapping$0[styleSheet.getWidthScreenSizeKey().ordinal()];
        if (i == 1) {
            return dpToPx(280);
        }
        if (i == 2) {
            return dpToPx(320);
        }
        if (i == 3) {
            return dpToPx(320);
        }
        return dpToPx(374);
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
            setPadding(i, this.basePadding + ((int) getArrowHeight()), i, i);
            return;
        }
        if (i2 == 2) {
            setPadding(i, i, i, this.basePadding + ((int) getArrowHeight()));
            return;
        }
        if (i2 == 3) {
            setPadding(this.basePadding + ((int) getArrowHeight()), i, i, i);
        } else if (i2 == 4) {
            setPadding(i, i, this.basePadding + ((int) getArrowHeight()), i);
        } else {
            setPadding(i, i, i, i);
        }
    }

    private final int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
