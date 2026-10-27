package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.models.StoryPageHeaderModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.StoryPageHeaderView;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: StoryPageHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001BS\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0003H\u0016J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0002H\u0002J\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0002H\u0002J\u0010\u0010 \u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010!\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020#H\u0016R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/music/views/library/binders/StoryPageHeaderBinder;", "Lcom/amazon/music/views/library/binders/PageHeaderBinder;", "Lcom/amazon/music/views/library/views/StoryPageHeaderView;", "Lcom/amazon/music/views/library/models/StoryPageHeaderModel;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "onClickListenerMap", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "contextMenuListener", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "textContainerOnTouchListener", "Landroid/view/View$OnTouchListener;", "paginationCount", "", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Ljava/util/Map;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;Landroid/view/View$OnTouchListener;Ljava/lang/Integer;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "Ljava/lang/Integer;", "bind", "", "view", "model", "bindPagination", "bindTextContainerOnTouchListener", "createView", "handleStateChange", "payload", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StoryPageHeaderBinder extends PageHeaderBinder<StoryPageHeaderView, StoryPageHeaderModel> {
    private final Integer paginationCount;
    private final View.OnTouchListener textContainerOnTouchListener;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<StoryPageHeaderModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(StoryPageHeaderModel.class);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StoryPageHeaderBinder(Context context, StyleSheet styleSheet, Map<PageHeaderActionIconModel.ActionType, ? extends ActionIconOnClickListener> onClickListenerMap, MetadataContextMenuProvider metadataContextMenuProvider, ContentEnabilityProvider contentEnabilityProvider, View.OnTouchListener onTouchListener, Integer num) {
        super(context, styleSheet, onClickListenerMap, metadataContextMenuProvider, contentEnabilityProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(onClickListenerMap, "onClickListenerMap");
        Intrinsics.checkNotNullParameter(contentEnabilityProvider, "contentEnabilityProvider");
        this.textContainerOnTouchListener = onTouchListener;
        this.paginationCount = num;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public StoryPageHeaderView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new StoryPageHeaderView(context, getStyleSheet());
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(StoryPageHeaderView view, StoryPageHeaderModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        super.bind(view, model);
        bindTextContainerOnTouchListener(view);
        bindPagination(view);
    }

    @Override // com.amazon.music.views.library.binders.PageHeaderBinder
    public void handleStateChange(StoryPageHeaderView view, StoryPageHeaderModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        super.handleStateChange(view, model, payload);
        if (Intrinsics.areEqual(payload, (Object) 17)) {
            view.updatePagination(model.getPrevDotIndex(), model.getCurDotIndex());
        }
    }

    private final void bindTextContainerOnTouchListener(StoryPageHeaderView view) {
        View.OnTouchListener onTouchListener = this.textContainerOnTouchListener;
        if (onTouchListener == null) {
            return;
        }
        view.setTextContainerOnTouchListener(onTouchListener);
    }

    private final void bindPagination(StoryPageHeaderView view) {
        Integer num = this.paginationCount;
        if (num == null) {
            return;
        }
        view.setPagination(num.intValue());
    }
}
