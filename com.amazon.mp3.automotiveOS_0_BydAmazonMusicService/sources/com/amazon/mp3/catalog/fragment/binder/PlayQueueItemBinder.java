package com.amazon.mp3.catalog.fragment.binder;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.view.PlayQueueItemView;
import com.amazon.music.views.library.binders.BaseStateChangeBinder;
import com.amazon.music.views.library.binders.GroupBinder;
import com.amazon.music.views.library.binders.TextRowItemBinder;
import com.amazon.music.views.library.binders.VisualRowItemBinder;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.TableRowItemModel;
import com.amazon.music.views.library.models.TextRowItemModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: PlayQueueItemBinder.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0003H\u0016J\u0010\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J \u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\r0\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/binder/PlayQueueItemBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/mp3/catalog/fragment/view/PlayQueueItemView;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lcom/amazon/music/views/library/binders/GroupBinder;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "headerRowItemBinder", "Lcom/amazon/music/views/library/binders/TextRowItemBinder;", "visualRowItemBinder", "Lcom/amazon/music/views/library/binders/VisualRowItemBinder;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/binders/TextRowItemBinder;Lcom/amazon/music/views/library/binders/VisualRowItemBinder;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClassList", "", "getModelClassList", "()Ljava/util/List;", "bind", "", "view", "model", "createView", "context", "Landroid/content/Context;", "handleStateChange", "payload", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayQueueItemBinder implements BaseStateChangeBinder<PlayQueueItemView, BaseViewModel>, GroupBinder {
    private final TextRowItemBinder headerRowItemBinder;
    private final StyleSheet styleSheet;
    private final VisualRowItemBinder visualRowItemBinder;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<BaseViewModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(BaseViewModel.class);
    }

    public PlayQueueItemBinder(StyleSheet styleSheet, TextRowItemBinder headerRowItemBinder, VisualRowItemBinder visualRowItemBinder) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(headerRowItemBinder, "headerRowItemBinder");
        Intrinsics.checkNotNullParameter(visualRowItemBinder, "visualRowItemBinder");
        this.styleSheet = styleSheet;
        this.headerRowItemBinder = headerRowItemBinder;
        this.visualRowItemBinder = visualRowItemBinder;
    }

    @Override // com.amazon.music.views.library.binders.GroupBinder
    public List<KClass<? extends BaseViewModel>> getModelClassList() {
        return CollectionsKt__CollectionsKt.listOf((Object[]) new KClass[]{Reflection.getOrCreateKotlinClass(TextRowItemModel.class), Reflection.getOrCreateKotlinClass(VisualRowItemModel.class)});
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public PlayQueueItemView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new PlayQueueItemView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(PlayQueueItemView view, BaseViewModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        if (model instanceof VisualRowItemModel) {
            VisualRowItemModel visualRowItemModel = (VisualRowItemModel) model;
            this.visualRowItemBinder.bind(view.getVisualRowItemView(), visualRowItemModel);
            view.getVisualRowItemView().showOverflowButton(Intrinsics.areEqual(visualRowItemModel.getShowOverflow(), Boolean.TRUE));
            view.showVisualRowItemView();
        } else if (model instanceof TextRowItemModel) {
            this.headerRowItemBinder.bind(view.getHeaderRowItemView(), (TextRowItemModel) model);
            view.showTextRowItemView();
        }
        if (AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()) {
            TableRowItemModel tableRowItemModel = model instanceof TableRowItemModel ? (TableRowItemModel) model : null;
            ContentMetadata metadata = tableRowItemModel == null ? null : tableRowItemModel.getMetadata();
            TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
            if (trackMetadata == null ? false : Intrinsics.areEqual(trackMetadata.getIsExplicit(), Boolean.TRUE)) {
                view.setEnabled(false);
            }
        }
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(PlayQueueItemView view, BaseViewModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (model instanceof VisualRowItemModel) {
            this.visualRowItemBinder.handleStateChange(view.getVisualRowItemView(), (VisualRowItemModel) model, payload);
        }
    }
}
