package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.models.enums.RefinementListType;
import com.amazon.music.views.library.providers.RefinementClickListenerProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.RefinementPillView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: RefinementPillBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0004B\u0017\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0003H\u0016J \u0010\u000e\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\fH\u0016R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/views/library/binders/RefinementPillBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/RefinementPillView;", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Landroid/content/Context;", "context", "createView", "view", "model", "", "bind", "", "payload", "handleStateChange", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/providers/RefinementClickListenerProvider;", "clickListenerProvider", "Lcom/amazon/music/views/library/providers/RefinementClickListenerProvider;", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "<init>", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/RefinementClickListenerProvider;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class RefinementPillBinder implements UniversalBinder<RefinementPillView, RefinementPillModel>, BaseStateChangeBinder<RefinementPillView, RefinementPillModel> {
    private final RefinementClickListenerProvider clickListenerProvider;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<RefinementPillModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(RefinementPillModel.class);
    }

    public RefinementPillBinder(StyleSheet styleSheet, RefinementClickListenerProvider clickListenerProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(clickListenerProvider, "clickListenerProvider");
        this.styleSheet = styleSheet;
        this.clickListenerProvider = clickListenerProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public RefinementPillView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new RefinementPillView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(final RefinementPillView view, final RefinementPillModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        ExposedRefinementListModel parentRefinementListModel = model.getParentRefinementListModel();
        RefinementListType refinementListType = parentRefinementListModel == null ? null : parentRefinementListModel.getRefinementListType();
        if (refinementListType == null) {
            refinementListType = RefinementListType.FILTER;
        }
        view.setRefinementListType(refinementListType);
        view.setView(model.getContent(), model.getIsFocused(), new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.RefinementPillBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RefinementPillBinder.m2358bind$lambda0(this.f$0, model, view, view2);
            }
        });
        Integer viewId = model.getViewId();
        if (viewId == null) {
            return;
        }
        view.setId(viewId.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-0, reason: not valid java name */
    public static final void m2358bind$lambda0(RefinementPillBinder this$0, RefinementPillModel model, RefinementPillView view, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(view, "$view");
        this$0.clickListenerProvider.onClick(model, view);
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(RefinementPillView view, RefinementPillModel model, Object payload) {
        FilterModel content;
        FilterItemModel itemModel;
        ExposedRefinementListModel parentRefinementListModel;
        List<RefinementPillModel> refinementList;
        FilterModel content2;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 11)) {
            FilterModel content3 = model.getContent();
            if (content3 != null) {
                view.updateViewFilterModel(content3, model.getIsFocused());
            }
            if (view.getRefinementListType() != RefinementListType.NAVIGATION || (content = model.getContent()) == null || (itemModel = content.getItemModel()) == null || !itemModel.getSelected() || (parentRefinementListModel = model.getParentRefinementListModel()) == null || (refinementList = parentRefinementListModel.getRefinementList()) == null) {
                return;
            }
            for (RefinementPillModel refinementPillModel : refinementList) {
                if (!Intrinsics.areEqual(refinementPillModel, model) && (content2 = refinementPillModel.getContent()) != null) {
                    FilterItemModel itemModel2 = content2.getItemModel();
                    if (itemModel2 != null) {
                        itemModel2.setSelected(false);
                    }
                    refinementPillModel.updateFilterModel(content2);
                }
            }
        }
    }
}
