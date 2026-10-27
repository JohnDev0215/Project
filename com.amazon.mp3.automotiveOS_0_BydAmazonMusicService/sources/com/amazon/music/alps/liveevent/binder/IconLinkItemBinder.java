package com.amazon.music.alps.liveevent.binder;

import android.content.Context;
import android.view.View;
import com.amazon.music.alps.liveevent.ktx.ViewKt;
import com.amazon.music.alps.liveevent.view.IconLinkItemView;
import com.amazon.music.alps.liveevent.viewmodel.IconLinkItemViewModel;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.GridSize;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: IconLinkItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0085\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00128\u0010\u0006\u001a4\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007\u0012<\b\u0002\u0010\u000f\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0003H\u0016J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0002H\u0002J\u0018\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0003H\u0002J\u0010\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R@\u0010\u0006\u001a4\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000RB\u0010\u000f\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/alps/liveevent/binder/IconLinkItemBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/alps/liveevent/view/IconLinkItemView;", "Lcom/amazon/music/alps/liveevent/viewmodel/IconLinkItemViewModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "onClickLink", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "target", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "", "onViewed", ImagesContract.URL, "(Lcom/amazon/music/views/library/styles/StyleSheet;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "view", "model", "bindStyle", "bindText", "createView", "context", "Landroid/content/Context;", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class IconLinkItemBinder implements UniversalBinder<IconLinkItemView, IconLinkItemViewModel> {
    private final Function2<String, ActionType, Unit> onClickLink;
    private final Function2<String, ActionType, Unit> onViewed;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<IconLinkItemViewModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(IconLinkItemViewModel.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public IconLinkItemBinder(StyleSheet styleSheet, Function2<? super String, ? super ActionType, Unit> onClickLink, Function2<? super String, ? super ActionType, Unit> onViewed) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(onClickLink, "onClickLink");
        Intrinsics.checkNotNullParameter(onViewed, "onViewed");
        this.styleSheet = styleSheet;
        this.onClickLink = onClickLink;
        this.onViewed = onViewed;
    }

    public /* synthetic */ IconLinkItemBinder(StyleSheet styleSheet, Function2 function2, Function2 function22, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(styleSheet, function2, (i & 4) != 0 ? new Function2<String, ActionType, Unit>() { // from class: com.amazon.music.alps.liveevent.binder.IconLinkItemBinder.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str, ActionType noName_1) {
                Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ Unit mo4invoke(String str, ActionType actionType) {
                invoke2(str, actionType);
                return Unit.INSTANCE;
            }
        } : function22);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public IconLinkItemView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new IconLinkItemView(context);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(IconLinkItemView view, IconLinkItemViewModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bindStyle(view);
        bindText(view, model);
        this.onViewed.mo4invoke(model.getLinkTarget(), model.getActionType());
    }

    private final void bindStyle(IconLinkItemView view) {
        StyleSheet styleSheet = this.styleSheet;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        if (gridSize == null) {
            return;
        }
        int columnSpace = gridSize.getColumnSpace() / 2;
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        view.setPadding(columnSpace, 0, columnSpace, spacerInPixels == null ? 0 : -spacerInPixels.intValue());
    }

    private final void bindText(IconLinkItemView view, final IconLinkItemViewModel model) {
        String iconUrl = model.getIconUrl();
        if (iconUrl != null) {
            ViewKt.setImageUrl$default(view.getIcon(), iconUrl, null, 2, null);
        }
        String linkText = model.getLinkText();
        if (linkText != null) {
            view.getLink().setText(linkText);
        }
        final String linkTarget = model.getLinkTarget();
        if (linkTarget == null) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.alps.liveevent.binder.IconLinkItemBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                IconLinkItemBinder.m1865bindText$lambda6$lambda5$lambda4(this.f$0, linkTarget, model, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindText$lambda-6$lambda-5$lambda-4, reason: not valid java name */
    public static final void m1865bindText$lambda6$lambda5$lambda4(IconLinkItemBinder this$0, String it, IconLinkItemViewModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.onClickLink.mo4invoke(it, model.getActionType());
    }
}
