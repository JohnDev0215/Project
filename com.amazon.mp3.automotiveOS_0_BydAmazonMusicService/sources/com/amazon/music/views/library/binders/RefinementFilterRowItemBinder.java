package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.RefinementFilterRowItemModel;
import com.amazon.music.views.library.providers.SimpleClickListenerProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.RefinementFilterRowItemView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: RefinementFilterRowItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0019\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/views/library/binders/RefinementFilterRowItemBinder;", "Lcom/amazon/music/views/library/binders/RefinementRowItemBinder;", "Lcom/amazon/music/views/library/models/FilterItemModel;", "Lcom/amazon/music/views/library/views/RefinementFilterRowItemView;", "Lcom/amazon/music/views/library/models/RefinementFilterRowItemModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "clickListenerProvider", "Lcom/amazon/music/views/library/providers/SimpleClickListenerProvider;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/SimpleClickListenerProvider;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "context", "Landroid/content/Context;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinementFilterRowItemBinder extends RefinementRowItemBinder<FilterItemModel, RefinementFilterRowItemView, RefinementFilterRowItemModel> {
    private final SimpleClickListenerProvider clickListenerProvider;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<RefinementFilterRowItemModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(RefinementFilterRowItemModel.class);
    }

    public RefinementFilterRowItemBinder(StyleSheet styleSheet, SimpleClickListenerProvider simpleClickListenerProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.clickListenerProvider = simpleClickListenerProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public RefinementFilterRowItemView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new RefinementFilterRowItemView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.RefinementRowItemBinder
    public void bind(final RefinementFilterRowItemView view, final RefinementFilterRowItemModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        super.bind(view, model);
        Integer viewId = model.getViewId();
        if (viewId != null) {
            view.setResourceId(viewId.intValue());
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.RefinementFilterRowItemBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RefinementFilterRowItemBinder.m2356bind$lambda1(model, view, this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-1, reason: not valid java name */
    public static final void m2356bind$lambda1(RefinementFilterRowItemModel model, RefinementFilterRowItemView view, RefinementFilterRowItemBinder this$0, View view2) {
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        model.setActive(view.flipSelection());
        SimpleClickListenerProvider simpleClickListenerProvider = this$0.clickListenerProvider;
        if (simpleClickListenerProvider == null) {
            return;
        }
        simpleClickListenerProvider.onClick(model.getContent());
    }
}
