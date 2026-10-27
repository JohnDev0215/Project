package com.amazon.music.views.library.binders;

import android.content.Context;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.DefaultPageHeaderModel;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.DefaultPageHeaderView;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: DefaultPageHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001BW\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0014\b\u0002\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\u001e\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"\u0012\u0006\u0010%\u001a\u00020$\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b&\u0010'J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016R\"\u0010\f\u001a\u00020\u000b8\u0014@\u0014X\u0094\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0014@\u0014X\u0094\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Lcom/amazon/music/views/library/binders/DefaultPageHeaderBinder;", "Lcom/amazon/music/views/library/binders/PageHeaderBinder;", "Lcom/amazon/music/views/library/views/DefaultPageHeaderView;", "Lcom/amazon/music/views/library/models/DefaultPageHeaderModel;", "Landroid/content/Context;", "context", "createView", "view", "model", "", "bind", "", "isSonicRushEnabled", "Z", "()Z", "setSonicRushEnabled", "(Z)V", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "setPageType", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "onClickListenerMap", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "contextMenuListener", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "contentEnabilityProvider", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Ljava/util/Map;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;ZLcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class DefaultPageHeaderBinder extends PageHeaderBinder<DefaultPageHeaderView, DefaultPageHeaderModel> {
    private boolean isSonicRushEnabled;
    private PageType pageType;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<DefaultPageHeaderModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(DefaultPageHeaderModel.class);
    }

    @Override // com.amazon.music.views.library.binders.PageHeaderBinder
    /* JADX INFO: renamed from: isSonicRushEnabled, reason: from getter */
    protected boolean getIsSonicRushEnabled() {
        return this.isSonicRushEnabled;
    }

    protected PageType getPageType() {
        return this.pageType;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultPageHeaderBinder(Context context, StyleSheet styleSheet, Map<PageHeaderActionIconModel.ActionType, ? extends ActionIconOnClickListener> onClickListenerMap, MetadataContextMenuProvider metadataContextMenuProvider, ContentEnabilityProvider contentEnabilityProvider, boolean z, PageType pageType) {
        super(context, styleSheet, onClickListenerMap, metadataContextMenuProvider, contentEnabilityProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(onClickListenerMap, "onClickListenerMap");
        Intrinsics.checkNotNullParameter(contentEnabilityProvider, "contentEnabilityProvider");
        this.isSonicRushEnabled = z;
        this.pageType = pageType;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public DefaultPageHeaderView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new DefaultPageHeaderView(context, getStyleSheet(), getIsSonicRushEnabled(), getPageType());
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(DefaultPageHeaderView view, DefaultPageHeaderModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        super.setSonicRushEnabled(getIsSonicRushEnabled());
        super.setPageType(getPageType());
        super.bind(view, model);
    }
}
