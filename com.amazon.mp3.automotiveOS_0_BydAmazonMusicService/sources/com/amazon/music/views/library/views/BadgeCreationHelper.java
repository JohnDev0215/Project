package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.LinearLayout;
import androidx.core.content.res.ResourcesCompat;
import com.amazon.music.resources.R$drawable;
import com.amazon.music.resources.R$string;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.TagSizeKey;
import com.amazon.music.views.library.styles.keys.TagStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: BadgeCreationHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0019J\u001c\u0010\u001e\u001a\u00020\u00162\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0 2\u0006\u0010!\u001a\u00020\"J$\u0010#\u001a\u00020\u00162\b\u0010$\u001a\u0004\u0018\u00010\b2\u0006\u0010%\u001a\u00020\u00192\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/music/views/library/views/BadgeCreationHelper;", "", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "context", "Landroid/content/Context;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Landroid/content/Context;)V", "atmosText", "", "audioQualityBadgeBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "getContext", "()Landroid/content/Context;", "dolbyIcon", "Landroid/graphics/drawable/Drawable;", "dolbyIconPadding", "", "immersiveBadgeBuilder", "languageOfPerformanceBadgeBuilder", "languageOfPerformancePadding", "nullIconPadding", "setAudioQualityText", "", "audioQualityText", "audioQualityBadgeView", "Lcom/amazon/ui/foundations/views/BaseButton;", "setExtraAtmosBadgeText", "shouldDisplay", "", "extraImmersiveContentBadgeView", "setLanguageOfPerformanceText", "languageOfPerformances", "", "badgeContainer", "Landroid/widget/LinearLayout;", "setSpatialBadgeText", "spatialBadgeText", "spatialContentBadgeView", "styleBuilder", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BadgeCreationHelper {
    private final String atmosText;
    private BaseButton.StyleBuilder audioQualityBadgeBuilder;
    private final Context context;
    private final Drawable dolbyIcon;
    private final int dolbyIconPadding;
    private BaseButton.StyleBuilder immersiveBadgeBuilder;
    private BaseButton.StyleBuilder languageOfPerformanceBadgeBuilder;
    private final int languageOfPerformancePadding;
    private final int nullIconPadding;

    public BadgeCreationHelper(StyleSheet styleSheet, Context context) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        TagSizeKey tagSizeKey = TagSizeKey.SMALL;
        TagStyleKey tagStyleKey = TagStyleKey.GLASS_PRIMARY;
        this.audioQualityBadgeBuilder = styleSheet.getTagBuilder(tagSizeKey, tagStyleKey);
        this.immersiveBadgeBuilder = styleSheet.getTagBuilder(tagSizeKey, tagStyleKey);
        this.languageOfPerformanceBadgeBuilder = styleSheet.getTagBuilder(tagSizeKey, tagStyleKey);
        this.languageOfPerformancePadding = (int) context.getResources().getDimension(R$dimen.spacer_micro);
        String string = context.getResources().getString(R$string.ivy_atmos_badge);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…R.string.ivy_atmos_badge)");
        this.atmosText = string;
        this.dolbyIcon = ResourcesCompat.getDrawable(context.getResources(), R$drawable.dd_logo, null);
        this.dolbyIconPadding = (int) context.getResources().getDimension(com.amazon.music.resources.R$dimen.small_spacing);
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setAudioQualityText(String audioQualityText, BaseButton audioQualityBadgeView) {
        Intrinsics.checkNotNullParameter(audioQualityBadgeView, "audioQualityBadgeView");
        if (audioQualityText != null && !TextUtils.isEmpty(audioQualityText)) {
            BaseButton.StyleBuilder styleBuilder = this.audioQualityBadgeBuilder;
            if (styleBuilder != null) {
                styleBuilder.withText(audioQualityText);
                styleBuilder.applyStyle(audioQualityBadgeView);
            }
            audioQualityBadgeView.setVisibility(0);
            return;
        }
        audioQualityBadgeView.setVisibility(8);
    }

    public final void setLanguageOfPerformanceText(List<String> languageOfPerformances, LinearLayout badgeContainer) {
        Intrinsics.checkNotNullParameter(languageOfPerformances, "languageOfPerformances");
        Intrinsics.checkNotNullParameter(badgeContainer, "badgeContainer");
        badgeContainer.removeAllViews();
        ArrayList<String> arrayList = new ArrayList();
        for (Object obj : languageOfPerformances) {
            if (true ^ StringsKt__StringsJVMKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        boolean z = false;
        for (String str : arrayList) {
            BaseButton baseButton = new BaseButton(getContext(), null, 0, 6, null);
            baseButton.setPadding(0, 0, this.languageOfPerformancePadding, 0);
            BaseButton.StyleBuilder styleBuilder = this.languageOfPerformanceBadgeBuilder;
            if (styleBuilder != null) {
                styleBuilder.withText(str);
                styleBuilder.applyStyle(baseButton);
            }
            badgeContainer.addView(baseButton);
            z = true;
        }
        if (z) {
            badgeContainer.setVisibility(0);
        } else {
            badgeContainer.setVisibility(8);
        }
    }

    public static /* synthetic */ void setSpatialBadgeText$default(BadgeCreationHelper badgeCreationHelper, String str, BaseButton baseButton, BaseButton.StyleBuilder styleBuilder, int i, Object obj) {
        if ((i & 4) != 0) {
            styleBuilder = null;
        }
        badgeCreationHelper.setSpatialBadgeText(str, baseButton, styleBuilder);
    }

    public final void setSpatialBadgeText(String spatialBadgeText, BaseButton spatialContentBadgeView, BaseButton.StyleBuilder styleBuilder) {
        Unit unit;
        BaseButton.StyleBuilder styleBuilder2;
        Drawable drawable;
        Intrinsics.checkNotNullParameter(spatialContentBadgeView, "spatialContentBadgeView");
        if (spatialBadgeText != null && !TextUtils.isEmpty(spatialBadgeText)) {
            Drawable colorDrawable = new ColorDrawable(0);
            int i = this.nullIconPadding;
            if (StringsKt__StringsJVMKt.equals(spatialBadgeText, this.atmosText, true) && (drawable = this.dolbyIcon) != null) {
                i = this.dolbyIconPadding;
                colorDrawable = drawable;
            }
            if (styleBuilder == null) {
                unit = null;
            } else {
                styleBuilder.withIcon(colorDrawable);
                styleBuilder.withText(spatialBadgeText);
                styleBuilder.applyStyle(spatialContentBadgeView);
                spatialContentBadgeView.getButton().setIconPadding(i);
                unit = Unit.INSTANCE;
            }
            if (unit == null && (styleBuilder2 = this.immersiveBadgeBuilder) != null) {
                styleBuilder2.withIcon(colorDrawable);
                styleBuilder2.withText(spatialBadgeText);
                styleBuilder2.applyStyle(spatialContentBadgeView);
                spatialContentBadgeView.getButton().setIconPadding(i);
            }
            spatialContentBadgeView.setVisibility(0);
            return;
        }
        spatialContentBadgeView.setVisibility(8);
    }

    public final void setExtraAtmosBadgeText(boolean shouldDisplay, BaseButton extraImmersiveContentBadgeView) {
        Intrinsics.checkNotNullParameter(extraImmersiveContentBadgeView, "extraImmersiveContentBadgeView");
        if (shouldDisplay) {
            BaseButton.StyleBuilder styleBuilder = this.immersiveBadgeBuilder;
            if (styleBuilder == null) {
                return;
            }
            Drawable drawable = this.dolbyIcon;
            if (drawable != null) {
                styleBuilder.withIcon(drawable);
            }
            styleBuilder.withText("");
            styleBuilder.applyStyle(extraImmersiveContentBadgeView);
            extraImmersiveContentBadgeView.setVisibility(0);
            return;
        }
        extraImmersiveContentBadgeView.setVisibility(8);
    }
}
