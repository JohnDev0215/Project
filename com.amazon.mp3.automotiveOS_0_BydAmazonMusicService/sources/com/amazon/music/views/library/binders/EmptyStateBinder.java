package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.views.library.models.EmptyStateButtonType;
import com.amazon.music.views.library.models.EmptyStateModel;
import com.amazon.music.views.library.providers.EmptyStateActionProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.EmptyStateView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: EmptyStateBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0019\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0003H\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/views/library/binders/EmptyStateBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/EmptyStateView;", "Lcom/amazon/music/views/library/models/EmptyStateModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "emptyStateActionProvider", "Lcom/amazon/music/views/library/providers/EmptyStateActionProvider;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/EmptyStateActionProvider;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "context", "Landroid/content/Context;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EmptyStateBinder implements UniversalBinder<EmptyStateView, EmptyStateModel> {
    private final EmptyStateActionProvider emptyStateActionProvider;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<EmptyStateModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(EmptyStateModel.class);
    }

    public EmptyStateBinder(StyleSheet styleSheet, EmptyStateActionProvider emptyStateActionProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.emptyStateActionProvider = emptyStateActionProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public EmptyStateView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new EmptyStateView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(EmptyStateView view, EmptyStateModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        if (model.getButtonType() instanceof EmptyStateButtonType.ADDSONGS) {
            view.setButtonStyle(ButtonSizeKey.MEDIUM, ButtonStyleKey.GLASS_OUTLINE);
            view.setTitleFont(FontStyleKey.PRIMARY_BOLD);
            view.setButtonTopMargin(SpacerKey.SMALL);
            view.setPadding(SpacerKey.GIANT);
        } else {
            view.setButtonStyle(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID);
            view.setTitleFont(FontStyleKey.HEADLINE_4);
            view.setButtonTopMargin(SpacerKey.LARGE);
            view.setPadding(SpacerKey.EPIC);
        }
        view.setTitle(model.getTitle());
        view.setMessage(model.getMessage());
        final EmptyStateButtonType buttonType = model.getButtonType();
        if (buttonType == null) {
            return;
        }
        view.setButton(buttonType);
        view.setEmptyStateButtonClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.EmptyStateBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                EmptyStateBinder.m2297bind$lambda1$lambda0(this.f$0, buttonType, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-1$lambda-0, reason: not valid java name */
    public static final void m2297bind$lambda1$lambda0(EmptyStateBinder this$0, EmptyStateButtonType button, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(button, "$button");
        EmptyStateActionProvider emptyStateActionProvider = this$0.emptyStateActionProvider;
        if (emptyStateActionProvider == null) {
            return;
        }
        emptyStateActionProvider.onEmptyStateButtonClicked(button);
    }
}
