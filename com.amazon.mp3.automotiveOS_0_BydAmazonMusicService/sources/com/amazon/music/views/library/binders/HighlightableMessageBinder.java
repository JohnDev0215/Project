package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.views.library.models.ButtonType;
import com.amazon.music.views.library.models.HighlightableMessageModel;
import com.amazon.music.views.library.providers.ButtonActionProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.views.HighlightableMessageView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: HighlightableMessageBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001b\u0012\u0006\u0010\r\u001a\u00020\f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0018\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0003H\u0016R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/views/library/binders/HighlightableMessageBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/HighlightableMessageView;", "Lcom/amazon/music/views/library/models/HighlightableMessageModel;", "view", "", "cleanHighlightableMessageView", "Landroid/content/Context;", "context", "createView", "model", "bind", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/providers/ButtonActionProvider;", "buttonActionProvider", "Lcom/amazon/music/views/library/providers/ButtonActionProvider;", "getButtonActionProvider", "()Lcom/amazon/music/views/library/providers/ButtonActionProvider;", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "<init>", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/ButtonActionProvider;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class HighlightableMessageBinder implements UniversalBinder<HighlightableMessageView, HighlightableMessageModel> {
    private final ButtonActionProvider buttonActionProvider;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<HighlightableMessageModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(HighlightableMessageModel.class);
    }

    public HighlightableMessageBinder(StyleSheet styleSheet, ButtonActionProvider buttonActionProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.buttonActionProvider = buttonActionProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public HighlightableMessageView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new HighlightableMessageView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(HighlightableMessageView view, final HighlightableMessageModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        cleanHighlightableMessageView(view);
        if (model.getButtonType() instanceof ButtonType.ADD_SONGS) {
            view.setButton(((ButtonType.ADD_SONGS) model.getButtonType()).getButtonString());
            view.setButtonClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.HighlightableMessageBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HighlightableMessageBinder.m2319bind$lambda0(this.f$0, model, view2);
                }
            });
            view.setButtonStyle(((ButtonType.ADD_SONGS) model.getButtonType()).getButtonSizeKey(), ((ButtonType.ADD_SONGS) model.getButtonType()).getButtonStyleKey());
            view.setButtonTopMargin(((ButtonType.ADD_SONGS) model.getButtonType()).getButtonMargin());
        }
        view.initMargin(view, model);
        view.initStyling();
        String message = model.getMessage();
        if (message == null) {
            return;
        }
        view.setMessage(message, model.getStringToHighlight(), this.styleSheet.getColor(ColorKey.COLOR_ACCENT));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-0, reason: not valid java name */
    public static final void m2319bind$lambda0(HighlightableMessageBinder this$0, HighlightableMessageModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        ButtonActionProvider buttonActionProvider = this$0.buttonActionProvider;
        if (buttonActionProvider == null) {
            return;
        }
        buttonActionProvider.onButtonClicked(model.getButtonType());
    }

    private final void cleanHighlightableMessageView(HighlightableMessageView view) {
        view.setMessageText("");
    }
}
