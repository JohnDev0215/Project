package com.amazon.music.alps.liveevent.binder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.amazon.music.alps.liveevent.view.SelectItemView;
import com.amazon.music.alps.liveevent.viewmodel.SelectItemViewModel;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.UIExtensionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: SelectItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B)\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¢\u0006\u0002\u0010\u000bJ\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u000e\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/alps/liveevent/binder/SelectItemBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/alps/liveevent/view/SelectItemView;", "Lcom/amazon/music/alps/liveevent/viewmodel/SelectItemViewModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "selectItemClickHandler", "Lcom/amazon/music/alps/liveevent/binder/SelectItemClickHandler;", "onViewed", "Lkotlin/Function0;", "", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/alps/liveevent/binder/SelectItemClickHandler;Lkotlin/jvm/functions/Function0;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "view", "model", "createView", "context", "Landroid/content/Context;", "setItemClickHandler", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SelectItemBinder implements UniversalBinder<SelectItemView, SelectItemViewModel> {
    private final Function0<Unit> onViewed;
    private SelectItemClickHandler selectItemClickHandler;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<SelectItemViewModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(SelectItemViewModel.class);
    }

    public SelectItemBinder(StyleSheet styleSheet, SelectItemClickHandler selectItemClickHandler, Function0<Unit> onViewed) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(onViewed, "onViewed");
        this.styleSheet = styleSheet;
        this.selectItemClickHandler = selectItemClickHandler;
        this.onViewed = onViewed;
    }

    public /* synthetic */ SelectItemBinder(StyleSheet styleSheet, SelectItemClickHandler selectItemClickHandler, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(styleSheet, selectItemClickHandler, (i & 4) != 0 ? new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.binder.SelectItemBinder.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        } : function0);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public SelectItemView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new SelectItemView(context, this.styleSheet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-0, reason: not valid java name */
    public static final void m1873bind$lambda0(SelectItemBinder this$0, SelectItemViewModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        SelectItemClickHandler selectItemClickHandler = this$0.selectItemClickHandler;
        if (selectItemClickHandler == null) {
            return;
        }
        selectItemClickHandler.onClick(model.getIndex());
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(SelectItemView view, final SelectItemViewModel model) {
        FontStyle fontStyle;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.alps.liveevent.binder.SelectItemBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SelectItemBinder.m1873bind$lambda0(this.f$0, model, view2);
            }
        });
        String text = model.getText();
        if (text != null) {
            view.getTitleTextView().setText(text);
        }
        Boolean isSelected = model.getIsSelected();
        if (isSelected != null) {
            boolean zBooleanValue = isSelected.booleanValue();
            view.setSelected(zBooleanValue);
            view.getUnderlineView().setVisibility(zBooleanValue ? 0 : 4);
            if (zBooleanValue) {
                fontStyle = view.getStyleSheet().getFontStyle(FontStyleKey.PRIMARY);
            } else {
                fontStyle = view.getStyleSheet().getFontStyle(FontStyleKey.SECONDARY);
            }
            TextView titleTextView = view.getTitleTextView();
            Intrinsics.checkNotNull(fontStyle);
            UIExtensionsKt.applyFontStyle(titleTextView, fontStyle);
            TextView titleTextView2 = view.getTitleTextView();
            FontSize fontSize = view.getStyleSheet().getFontSize(FontSizeKey.BODY_2);
            Intrinsics.checkNotNull(fontSize);
            UIExtensionsKt.applyFontSize(titleTextView2, fontSize);
        }
        this.onViewed.invoke();
    }

    public final void setItemClickHandler(SelectItemClickHandler selectItemClickHandler) {
        Intrinsics.checkNotNullParameter(selectItemClickHandler, "selectItemClickHandler");
        this.selectItemClickHandler = selectItemClickHandler;
    }
}
