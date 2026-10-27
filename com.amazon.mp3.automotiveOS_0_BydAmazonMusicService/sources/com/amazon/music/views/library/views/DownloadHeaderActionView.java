package com.amazon.music.views.library.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.annotation.FloatRange;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.NotificationCompat;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.StatefulStyle;
import com.amazon.ui.foundations.utils.ColorUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: DownloadHeaderActionView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010P\u001a\u00020O\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0019\u001a\u00020\r¢\u0006\u0004\bQ\u0010RJ\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J-\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\f\u001a\u00020\nH\u0002J\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0002J\u0012\u0010\u0015\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0014R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u00020\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0019\u0010\u001b\"\u0004\b\u001c\u0010\u001dR*\u0010 \u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e8\u0006@FX\u0087\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R*\u0010&\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00068\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010-R\u0014\u0010/\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u0010-R\u0014\u00100\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u0010-R\u0014\u00101\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u0010-R\u0014\u00102\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u0010-R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u00109\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010!R\u0016\u0010;\u001a\u0004\u0018\u00010:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<R\u0016\u0010=\u001a\u0004\u0018\u00010:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010<R\u0016\u0010>\u001a\u0004\u0018\u00010:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010<R\u0014\u0010@\u001a\u00020?8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010AR\"\u0010C\u001a\u00020B8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\"\u0010I\u001a\u00020\r8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bI\u0010\u001a\u001a\u0004\bI\u0010\u001b\"\u0004\bJ\u0010\u001dR\u0014\u0010L\u001a\u00020K8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010N\u001a\u00020K8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010M¨\u0006S"}, d2 = {"Lcom/amazon/music/views/library/views/DownloadHeaderActionView;", "Lcom/amazon/ui/foundations/views/BaseButton;", "Landroid/graphics/Canvas;", "canvas", "", "displayProgress", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "state", "updateDownloadState", "updateButtonId", "", "iconRes", "iconColor", "", "downloaded", "updateButton", "(ILjava/lang/Integer;Z)V", "updateTextButton", "Lcom/amazon/ui/foundations/styles/ButtonStyle;", "getGlassIconStyleWithCustomizedIconColor", "getTextButtonStyle", "onDraw", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "isTextButton", "Z", "()Z", "setTextButton", "(Z)V", "", "value", NotificationCompat.CATEGORY_PROGRESS, "F", "getProgress", "()F", "setProgress", "(F)V", "downloadState", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "getDownloadState", "()Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "setDownloadState", "(Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;)V", "defaultColor", "I", "secondaryColor", "accentColor", "errorColor", "primaryGlass3", "accentGlass3", "Landroid/content/res/ColorStateList;", "glassOutlineBorderColorList", "Landroid/content/res/ColorStateList;", "Lcom/amazon/ui/foundations/styles/StatefulStyle;", "downloadStyle", "Lcom/amazon/ui/foundations/styles/StatefulStyle;", "paintStrokeWidth", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "iconBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "iconBuilderSR", "textButtonBuilder", "Landroid/graphics/RectF;", "rect", "Landroid/graphics/RectF;", "", "downloadText", "Ljava/lang/String;", "getDownloadText", "()Ljava/lang/String;", "setDownloadText", "(Ljava/lang/String;)V", "isSonicRushEnabled", "setSonicRushEnabled", "Landroid/graphics/Paint;", "progressPaint", "Landroid/graphics/Paint;", "progressBackgroundPaint", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Z)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class DownloadHeaderActionView extends BaseButton {
    private final int accentColor;
    private final int accentGlass3;
    private final int defaultColor;
    private DownloadStateModel.DownloadState downloadState;
    private final StatefulStyle downloadStyle;
    private String downloadText;
    private final int errorColor;
    private final ColorStateList glassOutlineBorderColorList;
    private final BaseButton.StyleBuilder iconBuilder;
    private final BaseButton.StyleBuilder iconBuilderSR;
    private boolean isSonicRushEnabled;
    private boolean isTextButton;
    private final float paintStrokeWidth;
    private final int primaryGlass3;

    @FloatRange(from = 0.0d, to = 100.0d)
    private float progress;
    private final Paint progressBackgroundPaint;
    private final Paint progressPaint;
    private final RectF rect;
    private final int secondaryColor;
    private final StyleSheet styleSheet;
    private final BaseButton.StyleBuilder textButtonBuilder;

    /* JADX INFO: compiled from: DownloadHeaderActionView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DownloadStateModel.DownloadState.values().length];
            iArr[DownloadStateModel.DownloadState.AVAILABLE_FOR_DOWNLOAD.ordinal()] = 1;
            iArr[DownloadStateModel.DownloadState.DOWNLOADING.ordinal()] = 2;
            iArr[DownloadStateModel.DownloadState.DOWNLOADED.ordinal()] = 3;
            iArr[DownloadStateModel.DownloadState.ERROR_SYNC.ordinal()] = 4;
            iArr[DownloadStateModel.DownloadState.ERROR_DOWNLOADING.ordinal()] = 5;
            iArr[DownloadStateModel.DownloadState.ERROR_DOWNLOAD_RETURN.ordinal()] = 6;
            iArr[DownloadStateModel.DownloadState.REDOWNLOAD.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadHeaderActionView(Context context, StyleSheet styleSheet, boolean z) {
        super(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.isTextButton = z;
        this.downloadState = DownloadStateModel.DownloadState.NO_STATE;
        ColorKey colorKey = ColorKey.COLOR_PRIMARY;
        Integer color = styleSheet.getColor(colorKey);
        int iIntValue = color == null ? R$color.white : color.intValue();
        this.defaultColor = iIntValue;
        Integer color2 = styleSheet.getColor(ColorKey.COLOR_SECONDARY);
        int iIntValue2 = color2 == null ? R$color.black : color2.intValue();
        this.secondaryColor = iIntValue2;
        ColorKey colorKey2 = ColorKey.COLOR_ACCENT;
        Integer color3 = styleSheet.getColor(colorKey2);
        int iIntValue3 = color3 == null ? R$color.blue_accent : color3.intValue();
        this.accentColor = iIntValue3;
        Integer color4 = styleSheet.getColor(ColorKey.COLOR_ERROR);
        this.errorColor = color4 == null ? R$color.error_red : color4.intValue();
        AlphaKey alphaKey = AlphaKey.GLASS_3;
        Integer color5 = styleSheet.getColor(colorKey, alphaKey);
        int iIntValue4 = color5 == null ? iIntValue : color5.intValue();
        this.primaryGlass3 = iIntValue4;
        Integer color6 = styleSheet.getColor(colorKey2, alphaKey);
        int iIntValue5 = color6 == null ? iIntValue3 : color6.intValue();
        this.accentGlass3 = iIntValue5;
        this.glassOutlineBorderColorList = ColorUtil.INSTANCE.getColorStateList(iIntValue4, Integer.valueOf(iIntValue4), Integer.valueOf(iIntValue), Integer.valueOf(iIntValue4));
        this.downloadStyle = new StatefulStyle(Integer.valueOf(iIntValue2), null, null, null, Integer.valueOf(iIntValue3), null, null, Integer.valueOf(iIntValue5), 110, null);
        float dimension = getResources().getDimension(R$dimen.spacer_2);
        this.paintStrokeWidth = dimension;
        IconSizeKey iconSizeKey = IconSizeKey.MEDIUM;
        this.iconBuilder = styleSheet.getIconBuilder(iconSizeKey, IconStyleKey.GLASS);
        this.iconBuilderSR = styleSheet.getIconBuilder(iconSizeKey, IconStyleKey.PRIMARY);
        this.textButtonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM_WITH_ICON, ButtonStyleKey.GLASS_OUTLINE);
        this.rect = new RectF();
        this.downloadText = "";
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(dimension);
        paint.setAntiAlias(true);
        this.progressPaint = paint;
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(dimension);
        Integer color7 = styleSheet.getColor(colorKey, AlphaKey.GLASS_2);
        paint2.setColor(color7 == null ? 0 : color7.intValue());
        paint2.setAntiAlias(true);
        this.progressBackgroundPaint = paint2;
        setWillNotDraw(false);
    }

    public /* synthetic */ DownloadHeaderActionView(Context context, StyleSheet styleSheet, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, (i & 4) != 0 ? false : z);
    }

    public final void setTextButton(boolean z) {
        this.isTextButton = z;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final void setProgress(float f) {
        this.progress = RangesKt___RangesKt.coerceIn(f, 0.0f, 100.0f) * 3.6f;
        invalidate();
    }

    public final DownloadStateModel.DownloadState getDownloadState() {
        return this.downloadState;
    }

    public final void setDownloadState(DownloadStateModel.DownloadState value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.downloadState = value;
        updateDownloadState(value);
    }

    public final String getDownloadText() {
        return this.downloadText;
    }

    public final void setDownloadText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.downloadText = str;
    }

    /* JADX INFO: renamed from: isSonicRushEnabled, reason: from getter */
    public boolean getIsSonicRushEnabled() {
        return this.isSonicRushEnabled;
    }

    public void setSonicRushEnabled(boolean z) {
        this.isSonicRushEnabled = z;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isTextButton || !ArraysKt___ArraysKt.contains(new DownloadStateModel.DownloadState[]{DownloadStateModel.DownloadState.DOWNLOADING, DownloadStateModel.DownloadState.ERROR_DOWNLOADING}, this.downloadState)) {
            return;
        }
        displayProgress(canvas);
    }

    private final void displayProgress(Canvas canvas) {
        float f = this.paintStrokeWidth / 2;
        RectF rectF = this.rect;
        rectF.left = f;
        rectF.top = f;
        rectF.right = getMeasuredWidth() - f;
        this.rect.bottom = getMeasuredHeight() - f;
        this.progressPaint.setColor(this.downloadState == DownloadStateModel.DownloadState.ERROR_DOWNLOADING ? this.errorColor : this.accentColor);
        if (canvas != null) {
            canvas.drawArc(this.rect, 0.0f, 360.0f, false, this.progressBackgroundPaint);
        }
        if (canvas == null) {
            return;
        }
        canvas.drawArc(this.rect, -90.0f, this.progress, false, this.progressPaint);
    }

    private final void updateDownloadState(DownloadStateModel.DownloadState state) {
        updateButtonId(state);
        setVisibility(0);
        switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
            case 1:
                String string = getContext().getString(R$string.download);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.download)");
                this.downloadText = string;
                updateButton$default(this, R$drawable.ic_action_download, null, false, 6, null);
                break;
            case 2:
                String string2 = getContext().getString(R$string.downloading);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.downloading)");
                this.downloadText = string2;
                updateButton$default(this, R$drawable.ic_action_cancel, null, false, 6, null);
                break;
            case 3:
                String string3 = getContext().getString(R$string.downloaded);
                Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.downloaded)");
                this.downloadText = string3;
                updateButton(this.isTextButton ? R$drawable.ic_other_arrowdown : R$drawable.ic_action_downloadcomplete, Integer.valueOf(this.accentColor), true);
                break;
            case 4:
                String string4 = getContext().getString(R$string.error);
                Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.error)");
                this.downloadText = string4;
                updateButton$default(this, R$drawable.ic_navigation_refresh, Integer.valueOf(this.errorColor), false, 4, null);
                break;
            case 5:
                String string5 = getContext().getString(R$string.error);
                Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.error)");
                this.downloadText = string5;
                updateButton$default(this, R$drawable.ic_action_download, null, false, 6, null);
                break;
            case 6:
                String string6 = getContext().getString(R$string.error);
                Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.string.error)");
                this.downloadText = string6;
                updateButton$default(this, R$drawable.ic_action_download, Integer.valueOf(this.errorColor), false, 4, null);
                break;
            case 7:
                String string7 = getContext().getString(R$string.redownload);
                Intrinsics.checkNotNullExpressionValue(string7, "context.getString(R.string.redownload)");
                this.downloadText = string7;
                updateButton$default(this, R$drawable.ic_navigation_refresh, Integer.valueOf(this.accentColor), false, 4, null);
                break;
            default:
                setVisibility(4);
                break;
        }
    }

    private final void updateButtonId(DownloadStateModel.DownloadState state) {
        if (WhenMappings.$EnumSwitchMapping$0[state.ordinal()] == 3) {
            setId(R$id.DownloadProgress);
        } else {
            setId(R$id.DownloadButton);
        }
    }

    static /* synthetic */ void updateButton$default(DownloadHeaderActionView downloadHeaderActionView, int i, Integer num, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            num = Integer.valueOf(downloadHeaderActionView.defaultColor);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        downloadHeaderActionView.updateButton(i, num, z);
    }

    private final void updateButton(int iconRes, Integer iconColor, boolean downloaded) {
        ButtonStyle glassIconStyleWithCustomizedIconColor;
        if (this.isTextButton) {
            updateTextButton(iconRes, downloaded);
            return;
        }
        Drawable drawable = AppCompatResources.getDrawable(getContext(), iconRes);
        BaseButton.StyleBuilder styleBuilder = getIsSonicRushEnabled() ? this.iconBuilderSR : this.iconBuilder;
        if (drawable != null && styleBuilder != null) {
            styleBuilder.withIcon(drawable);
        }
        if (iconColor != null && (glassIconStyleWithCustomizedIconColor = getGlassIconStyleWithCustomizedIconColor(iconColor.intValue())) != null && styleBuilder != null) {
            styleBuilder.withStyle(glassIconStyleWithCustomizedIconColor);
        }
        if (styleBuilder == null) {
            return;
        }
        styleBuilder.applyStyle(this);
    }

    private final void updateTextButton(int iconRes, boolean downloaded) {
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilder2;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Drawable drawable = AppCompatResources.getDrawable(getContext(), iconRes);
        if (drawable != null && (styleBuilder2 = this.textButtonBuilder) != null && (styleBuilderWithIcon = styleBuilder2.withIcon(drawable)) != null) {
            styleBuilderWithIcon.withText(this.downloadText);
        }
        ButtonStyle textButtonStyle = getTextButtonStyle(downloaded);
        if (textButtonStyle != null && (styleBuilder = this.textButtonBuilder) != null) {
            styleBuilder.withStyle(textButtonStyle);
        }
        BaseButton.StyleBuilder styleBuilder3 = this.textButtonBuilder;
        if (styleBuilder3 == null) {
            return;
        }
        styleBuilder3.applyStyle(this);
    }

    private final ButtonStyle getGlassIconStyleWithCustomizedIconColor(int iconColor) {
        StatefulStyle statefulStyleCopy;
        ButtonStyle iconStyle = getIsSonicRushEnabled() ? this.styleSheet.getIconStyle(IconStyleKey.PRIMARY) : this.styleSheet.getIconStyle(IconStyleKey.GLASS);
        Integer color = this.styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_3);
        StatefulStyle statefulStyle = iconStyle == null ? null : iconStyle.getStatefulStyle();
        if (statefulStyle == null) {
            statefulStyleCopy = null;
        } else {
            statefulStyleCopy = statefulStyle.copy((253 & 1) != 0 ? statefulStyle.color : Integer.valueOf(iconColor), (253 & 2) != 0 ? statefulStyle.pressedColor : Integer.valueOf(iconColor), (253 & 4) != 0 ? statefulStyle.focusedColor : null, (253 & 8) != 0 ? statefulStyle.disabledColor : Integer.valueOf(color == null ? iconColor : color.intValue()), (253 & 16) != 0 ? statefulStyle.backgroundColor : null, (253 & 32) != 0 ? statefulStyle.pressedBackgroundColor : null, (253 & 64) != 0 ? statefulStyle.focusedBackgroundColor : null, (253 & 128) != 0 ? statefulStyle.disabledBackgroundColor : null);
        }
        if (statefulStyleCopy == null) {
            return null;
        }
        return ButtonStyle.copy$default(iconStyle, statefulStyleCopy, null, null, null, 14, null);
    }

    private final ButtonStyle getTextButtonStyle(boolean downloaded) {
        StatefulStyle statefulStyle;
        StatefulStyle statefulStyleCopy;
        ButtonStyle buttonStyle = this.styleSheet.getButtonStyle(ButtonStyleKey.GLASS_OUTLINE);
        if (!downloaded) {
            return buttonStyle;
        }
        if (buttonStyle == null || (statefulStyle = buttonStyle.getStatefulStyle()) == null) {
            statefulStyleCopy = null;
        } else {
            statefulStyleCopy = statefulStyle.copy((253 & 1) != 0 ? statefulStyle.color : null, (253 & 2) != 0 ? statefulStyle.pressedColor : statefulStyle.getColor(), (253 & 4) != 0 ? statefulStyle.focusedColor : null, (253 & 8) != 0 ? statefulStyle.disabledColor : Integer.valueOf(this.primaryGlass3), (253 & 16) != 0 ? statefulStyle.backgroundColor : null, (253 & 32) != 0 ? statefulStyle.pressedBackgroundColor : statefulStyle.getBackgroundColor(), (253 & 64) != 0 ? statefulStyle.focusedBackgroundColor : null, (253 & 128) != 0 ? statefulStyle.disabledBackgroundColor : null);
        }
        if (statefulStyleCopy == null) {
            return null;
        }
        return ButtonStyle.copy$default(buttonStyle, statefulStyleCopy, this.downloadStyle, this.glassOutlineBorderColorList, null, 8, null);
    }
}
