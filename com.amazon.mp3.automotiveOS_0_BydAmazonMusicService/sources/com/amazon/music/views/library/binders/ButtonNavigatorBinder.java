package com.amazon.music.views.library.binders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.views.library.models.ButtonNavigatorModel;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ButtonNavigatorBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0003H\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/views/library/binders/ButtonNavigatorBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/ui/foundations/views/BaseButton;", "Lcom/amazon/music/views/library/models/ButtonNavigatorModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "brushUriClickListenerProvider", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "context", "Landroid/content/Context;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ButtonNavigatorBinder implements UniversalBinder<BaseButton, ButtonNavigatorModel> {
    private final BrushUriClickListenerProvider brushUriClickListenerProvider;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<ButtonNavigatorModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(ButtonNavigatorModel.class);
    }

    public ButtonNavigatorBinder(StyleSheet styleSheet, BrushUriClickListenerProvider brushUriClickListenerProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(brushUriClickListenerProvider, "brushUriClickListenerProvider");
        this.styleSheet = styleSheet;
        this.brushUriClickListenerProvider = brushUriClickListenerProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public BaseButton createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new BaseButton(context, null, 0, 6, null);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(BaseButton view, final ButtonNavigatorModel model) {
        Integer spacerInPixels;
        Integer spacerInPixels2;
        BaseButton.StyleBuilder styleBuilderWithWidth;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Drawable icon = model.getIcon();
        if (icon != null) {
            view.setIcon(icon);
        }
        BaseButton.StyleBuilder buttonBuilder = this.styleSheet.getButtonBuilder(model.getButtonSizeKey(), model.getButtonStyleKey());
        if (buttonBuilder != null && (styleBuilderWithWidth = buttonBuilder.withWidth(model.getWidth())) != null) {
            styleBuilderWithWidth.applyStyle(view);
        }
        SpacerKey sideMarginSpacer = model.getSideMarginSpacer();
        int iIntValue = (sideMarginSpacer == null || (spacerInPixels = this.styleSheet.getSpacerInPixels(sideMarginSpacer)) == null) ? 0 : spacerInPixels.intValue();
        SpacerKey topMarginSpacer = model.getTopMarginSpacer();
        int iIntValue2 = (topMarginSpacer == null || (spacerInPixels2 = this.styleSheet.getSpacerInPixels(topMarginSpacer)) == null) ? 0 : spacerInPixels2.intValue();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.setMargins(iIntValue, iIntValue2, iIntValue, 0);
        }
        view.setLayoutParams(marginLayoutParams);
        String text = model.getText();
        if (text != null) {
            view.setText(text);
        }
        final String target = model.getTarget();
        if (target != null) {
            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ButtonNavigatorBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ButtonNavigatorBinder.m2276bind$lambda5$lambda4(this.f$0, target, model, view2);
                }
            });
        }
        view.setEnabled(model.getIsEnabled());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-5$lambda-4, reason: not valid java name */
    public static final void m2276bind$lambda5$lambda4(ButtonNavigatorBinder this$0, String target, ButtonNavigatorModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(target, "$target");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.brushUriClickListenerProvider.onClick(target, model.getViewReferenceId(), model.getTargetType());
    }
}
