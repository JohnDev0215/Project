package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.views.library.models.MultiStateButtonModel;
import com.amazon.music.views.library.providers.MultiStateButtonClickListenerProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.MultiStateButtonView;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: MultiStateButtonBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0003H\u0016J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0003H\u0002J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J \u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/views/library/binders/MultiStateButtonBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/music/views/library/views/MultiStateButtonView;", "Lcom/amazon/music/views/library/models/MultiStateButtonModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "clickListenerProvider", "Lcom/amazon/music/views/library/providers/MultiStateButtonClickListenerProvider;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/MultiStateButtonClickListenerProvider;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "bindText", "createView", "context", "Landroid/content/Context;", "handleStateChange", "payload", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MultiStateButtonBinder implements BaseStateChangeBinder<MultiStateButtonView, MultiStateButtonModel> {
    private final MultiStateButtonClickListenerProvider clickListenerProvider;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<MultiStateButtonModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(MultiStateButtonModel.class);
    }

    public MultiStateButtonBinder(StyleSheet styleSheet, MultiStateButtonClickListenerProvider clickListenerProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(clickListenerProvider, "clickListenerProvider");
        this.styleSheet = styleSheet;
        this.clickListenerProvider = clickListenerProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public MultiStateButtonView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new MultiStateButtonView(context, null, 0, 6, null);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(MultiStateButtonView view, final MultiStateButtonModel model) {
        Integer spacerInPixels;
        Integer spacerInPixels2;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        BaseButton.StyleBuilder buttonBuilder = this.styleSheet.getButtonBuilder(model.getButtonSizeKey(), model.getButtonStyleKey());
        if (buttonBuilder != null) {
            buttonBuilder.applyStyle(view.getButton());
        }
        SpacerKey sideMarginSpacer = model.getSideMarginSpacer();
        int iIntValue = (sideMarginSpacer == null || (spacerInPixels = this.styleSheet.getSpacerInPixels(sideMarginSpacer)) == null) ? 0 : spacerInPixels.intValue();
        SpacerKey topMarginSpacer = model.getTopMarginSpacer();
        int iIntValue2 = (topMarginSpacer == null || (spacerInPixels2 = this.styleSheet.getSpacerInPixels(topMarginSpacer)) == null) ? 0 : spacerInPixels2.intValue();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            layoutParams2.setMargins(iIntValue, iIntValue2, iIntValue, 0);
        }
        view.getButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.MultiStateButtonBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MultiStateButtonBinder.m2344bind$lambda2(this.f$0, model, view2);
            }
        });
        bindText(view, model);
        view.setEnabled(model.getIsEnabled());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-2, reason: not valid java name */
    public static final void m2344bind$lambda2(MultiStateButtonBinder this$0, MultiStateButtonModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.clickListenerProvider.onClick(model, view);
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(MultiStateButtonView view, MultiStateButtonModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 5)) {
            bindText(view, model);
        }
    }

    private final void bindText(MultiStateButtonView view, MultiStateButtonModel model) {
        view.getButton().setText(model.getShowPrimary() ? model.getPrimaryText() : model.getSecondaryText());
    }
}
