package com.amazon.mp3.playback.view.lyrics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import androidx.core.view.ViewCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.views.PlayNotificationIcon;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsLineView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010=\u001a\u00020<\u0012\u0006\u0010?\u001a\u00020>¢\u0006\u0004\b@\u0010AJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002J\u0006\u0010\u000b\u001a\u00020\u0004J\u0010\u0010\u000e\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0011J\u000e\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0014J\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0017J\u0010\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001aH\u0014R\u001c\u0010\u001e\u001a\n \u001d*\u0004\u0018\u00010\f0\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\u00178\u0002X\u0082D¢\u0006\u0006\n\u0004\b)\u0010*R\u001b\u00100\u001a\u00020+8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u001b\u00105\u001a\u0002018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u0010-\u001a\u0004\b3\u00104R\u001b\u00109\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010-\u001a\u0004\b7\u00108R\u0018\u0010:\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u0010;¨\u0006B"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/LyricsLineView;", "Landroid/widget/FrameLayout;", "", "isStage", "", "initBottomGradient", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "initStyleSheet", "isPlaying", "updateEqualizerIcon", "showEqualizer", "", "text", "showLyricsText", "Lcom/amazon/music/views/library/views/PlayNotificationIcon;", "getEqualizerView", "", "alpha", "setTextAlpha", "Lcom/amazon/ui/foundations/styles/FontStyle;", "fontStyle", "setTextStyle", "", "gravity", "setGravity", "Landroid/graphics/Canvas;", "canvas", "dispatchDraw", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "FADE_COLORS_REVERSE", "[I", "Landroid/graphics/Paint;", "gradientPaintBottom", "Landroid/graphics/Paint;", "Landroid/graphics/Rect;", "gradientRectBottom", "Landroid/graphics/Rect;", "gradientSizeBottom", "I", "Landroid/widget/TextView;", "mCurrentPlayingLineText$delegate", "Lkotlin/Lazy;", "getMCurrentPlayingLineText", "()Landroid/widget/TextView;", "mCurrentPlayingLineText", "Landroid/widget/TextSwitcher;", "currentPlayingLineTextSwitcher$delegate", "getCurrentPlayingLineTextSwitcher", "()Landroid/widget/TextSwitcher;", "currentPlayingLineTextSwitcher", "mEqualizerView$delegate", "getMEqualizerView", "()Lcom/amazon/music/views/library/views/PlayNotificationIcon;", "mEqualizerView", "mEqualizerOffStateAlpha", "Ljava/lang/Float;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsLineView extends FrameLayout {
    private final int[] FADE_COLORS_REVERSE;
    private final String TAG;

    /* JADX INFO: renamed from: currentPlayingLineTextSwitcher$delegate, reason: from kotlin metadata */
    private final Lazy currentPlayingLineTextSwitcher;
    private Paint gradientPaintBottom;
    private Rect gradientRectBottom;
    private final int gradientSizeBottom;

    /* JADX INFO: renamed from: mCurrentPlayingLineText$delegate, reason: from kotlin metadata */
    private final Lazy mCurrentPlayingLineText;
    private Float mEqualizerOffStateAlpha;

    /* JADX INFO: renamed from: mEqualizerView$delegate, reason: from kotlin metadata */
    private final Lazy mEqualizerView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LyricsLineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.TAG = LyricsLineView.class.getSimpleName();
        this.FADE_COLORS_REVERSE = new int[]{ViewCompat.MEASURED_STATE_MASK, 0};
        Paint paint = new Paint(1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        this.gradientPaintBottom = paint;
        this.gradientRectBottom = new Rect();
        this.gradientSizeBottom = 75;
        this.mCurrentPlayingLineText = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsLineView$mCurrentPlayingLineText$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.CurrentPlayingLineText);
            }
        });
        this.currentPlayingLineTextSwitcher = LazyKt__LazyJVMKt.lazy(new Function0<TextSwitcher>() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsLineView$currentPlayingLineTextSwitcher$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextSwitcher invoke() {
                return (TextSwitcher) this.this$0.findViewById(R.id.CurrentPlayingLineTextSwitcher);
            }
        });
        this.mEqualizerView = LazyKt__LazyJVMKt.lazy(new Function0<PlayNotificationIcon>() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsLineView$mEqualizerView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlayNotificationIcon invoke() {
                return (PlayNotificationIcon) this.this$0.findViewById(R.id.EqualizerView);
            }
        });
        LayoutInflater.from(getContext()).inflate(R.layout.view_lyrics_line, (ViewGroup) this, true);
        if (isStage()) {
            getCurrentPlayingLineTextSwitcher().setVisibility(0);
            getMCurrentPlayingLineText().setVisibility(8);
            getCurrentPlayingLineTextSwitcher().getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.stage_lyrics_view_height);
            LayoutParamUtils.INSTANCE.setLayoutMargins(getMEqualizerView(), -2, -2, Boolean.TRUE, 0, 0, 0, Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.stage_equalizer_bottom_margin)));
            invalidate();
            return;
        }
        getCurrentPlayingLineTextSwitcher().setVisibility(8);
        getMCurrentPlayingLineText().setVisibility(0);
    }

    private final TextView getMCurrentPlayingLineText() {
        Object value = this.mCurrentPlayingLineText.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mCurrentPlayingLineText>(...)");
        return (TextView) value;
    }

    private final TextSwitcher getCurrentPlayingLineTextSwitcher() {
        Object value = this.currentPlayingLineTextSwitcher.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-currentPlayingLineTextSwitcher>(...)");
        return (TextSwitcher) value;
    }

    private final PlayNotificationIcon getMEqualizerView() {
        Object value = this.mEqualizerView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mEqualizerView>(...)");
        return (PlayNotificationIcon) value;
    }

    public final void initStyleSheet(final StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        getCurrentPlayingLineTextSwitcher().removeAllViews();
        getCurrentPlayingLineTextSwitcher().setFactory(new ViewSwitcher.ViewFactory() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsLineView$$ExternalSyntheticLambda0
            @Override // android.widget.ViewSwitcher.ViewFactory
            public final View makeView() {
                return LyricsLineView.m1529initStyleSheet$lambda2(this.f$0, styleSheet);
            }
        });
        Integer iconSize = null;
        if (isStage()) {
            ButtonSize iconSize2 = styleSheet.getIconSize(IconSizeKey.LARGE);
            if (iconSize2 != null) {
                iconSize = iconSize2.getIconSize();
            }
        } else {
            ButtonSize iconSize3 = styleSheet.getIconSize(IconSizeKey.MEDIUM);
            if (iconSize3 != null) {
                iconSize = iconSize3.getIconSize();
            }
        }
        if (iconSize != null) {
            int iIntValue = iconSize.intValue();
            LayoutParamUtils.INSTANCE.setLayoutMargins(getMEqualizerView(), iIntValue, iIntValue, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        }
        Integer color = styleSheet.getColor(ColorKey.COLOR_PRIMARY);
        if (color != null) {
            try {
                getMEqualizerView().setColorFilter(color.intValue());
            } catch (NullPointerException e) {
                Log.error(this.TAG, "Error while setting color filter for mEqualizerView", e);
            }
        }
        Float alpha = styleSheet.getAlpha(AlphaKey.GLASS_4);
        if (alpha != null) {
            this.mEqualizerOffStateAlpha = Float.valueOf(alpha.floatValue());
        }
        if (isStage()) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 17;
            setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initStyleSheet$lambda-2, reason: not valid java name */
    public static final View m1529initStyleSheet$lambda2(LyricsLineView this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(styleSheet, "$styleSheet");
        TextView textView = new TextView(this$0.getContext());
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.LYRICS_XRAY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(textView, fontStyle);
        }
        if (this$0.isStage()) {
            textView.setHeight((int) this$0.getResources().getDimension(R.dimen.stage_lyrics_view_height));
        }
        textView.setGravity(17);
        textView.setMaxLines(3);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        return textView;
    }

    public final void updateEqualizerIcon(boolean isPlaying) {
        if (getMEqualizerView().getVisibility() == 0) {
            getMEqualizerView().setIsPlaying(isPlaying);
            if (!isPlaying) {
                PlayNotificationIcon mEqualizerView = getMEqualizerView();
                Float f = this.mEqualizerOffStateAlpha;
                mEqualizerView.setAlpha(f != null ? f.floatValue() : 1.0f);
                return;
            }
            getMEqualizerView().setAlpha(1.0f);
        }
    }

    public final void showEqualizer() {
        getMEqualizerView().setVisibility(0);
        getMCurrentPlayingLineText().setVisibility(8);
        getCurrentPlayingLineTextSwitcher().setVisibility(8);
    }

    public final void showLyricsText(String text) {
        if (isStage()) {
            getCurrentPlayingLineTextSwitcher().setVisibility(0);
        } else {
            getMCurrentPlayingLineText().setVisibility(0);
        }
        getMEqualizerView().setVisibility(8);
        if (text == null || Intrinsics.areEqual(text, getMCurrentPlayingLineText().getText())) {
            return;
        }
        getMCurrentPlayingLineText().setText(text);
        if (isStage()) {
            getCurrentPlayingLineTextSwitcher().setText(text);
        }
    }

    public final PlayNotificationIcon getEqualizerView() {
        return getMEqualizerView();
    }

    public final void setTextAlpha(float alpha) {
        getMCurrentPlayingLineText().setAlpha(alpha);
    }

    public final void setTextStyle(FontStyle fontStyle) {
        Intrinsics.checkNotNullParameter(fontStyle, "fontStyle");
        FontUtil.INSTANCE.applyFontStyle(getMCurrentPlayingLineText(), fontStyle);
    }

    public final void setGravity(int gravity) {
        getMCurrentPlayingLineText().setGravity(gravity);
        ViewGroup.LayoutParams layoutParams = getMEqualizerView().getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).gravity = gravity;
        }
    }

    private final boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (getVisibility() == 8 || getWidth() == 0 || getHeight() == 0 || !isStage()) {
            super.dispatchDraw(canvas);
            return;
        }
        initBottomGradient();
        int iSaveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null, 31);
        super.dispatchDraw(canvas);
        canvas.drawRect(this.gradientRectBottom, this.gradientPaintBottom);
        canvas.restoreToCount(iSaveLayer);
    }

    private final void initBottomGradient() {
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int iMin = Math.min(this.gradientSizeBottom, height);
        int paddingLeft = getPaddingLeft();
        int paddingTop = (getPaddingTop() + height) - iMin;
        int i = iMin + paddingTop;
        this.gradientRectBottom.set(paddingLeft, paddingTop, getWidth() - getPaddingRight(), i);
        float f = paddingLeft;
        this.gradientPaintBottom.setShader(new LinearGradient(f, paddingTop, f, i, this.FADE_COLORS_REVERSE, (float[]) null, Shader.TileMode.CLAMP));
    }
}
