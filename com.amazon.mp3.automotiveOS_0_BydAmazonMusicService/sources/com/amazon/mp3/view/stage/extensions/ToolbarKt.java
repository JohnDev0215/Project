package com.amazon.mp3.view.stage.extensions;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Toolbar.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001a\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003\u001a$\u0010\r\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u000b\u001a$\u0010\u000f\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u000b¨\u0006\u0010"}, d2 = {"Landroidx/appcompat/widget/Toolbar;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "toolBar", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "", "attachToToolbarLayout", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/ui/foundations/views/BaseButton;", "alexaButton", "Landroid/content/Context;", "context", "initAlexaButtonStyle", "backButton", "initStageBackButtonStyle", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0})
public final class ToolbarKt {
    public static final void attachToToolbarLayout(Toolbar toolbar, ConstraintLayout toolBar, Activity activity) {
        Intrinsics.checkNotNullParameter(toolbar, "<this>");
        Intrinsics.checkNotNullParameter(toolBar, "toolBar");
        Intrinsics.checkNotNullParameter(activity, "activity");
        View viewFindViewById = toolBar.findViewById(R.id.audioVideoToggleView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "toolBar.findViewById(R.id.audioVideoToggleView)");
        LinearLayout linearLayout = (LinearLayout) viewFindViewById;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.constrainHeight(linearLayout.getId(), -2);
        constraintSet.constrainWidth(linearLayout.getId(), -2);
        constraintSet.connect(linearLayout.getId(), 6, 0, 6, 0);
        constraintSet.connect(linearLayout.getId(), 7, 0, 7, 0);
        constraintSet.connect(linearLayout.getId(), 3, 0, 3, 0);
        constraintSet.connect(linearLayout.getId(), 4, 0, 4, 0);
        constraintSet.applyTo(toolBar);
    }

    public static final void initAlexaButtonStyle(Toolbar toolbar, StyleSheet styleSheet, BaseButton baseButton, Context context) {
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Intrinsics.checkNotNullParameter(toolbar, "<this>");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(context, "context");
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
        Drawable drawable = context.getDrawable(R.drawable.ic_alexa_bauhaus);
        if (drawable == null || baseButton == null) {
            return;
        }
        if (iconBuilder != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) != null) {
            styleBuilderWithIcon.applyStyle(baseButton);
        }
        ViewGroup.LayoutParams layoutParams = baseButton.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.setMargins(0, 0, (int) toolbar.getResources().getDimension(R.dimen.spacer_20), 0);
        baseButton.setLayoutParams(marginLayoutParams);
    }

    public static final void initStageBackButtonStyle(Toolbar toolbar, StyleSheet styleSheet, BaseButton baseButton, Context context) {
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Intrinsics.checkNotNullParameter(toolbar, "<this>");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(context, "context");
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
        Drawable drawable = context.getDrawable(R.drawable.ic_chevron_caretdown);
        if (drawable == null || baseButton == null) {
            return;
        }
        if (iconBuilder != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) != null) {
            styleBuilderWithIcon.applyStyle(baseButton);
        }
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.LARGE);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        int dimension = ScreenUtil.isPortrait() ? (int) toolbar.getResources().getDimension(R.dimen.spacer_20) : iIntValue + (spacerInPixels2 == null ? 0 : spacerInPixels2.intValue());
        ViewGroup.LayoutParams layoutParams = baseButton.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.setMargins(dimension, 0, 0, 0);
        baseButton.setLayoutParams(marginLayoutParams);
    }
}
