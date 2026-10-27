package com.amazon.mp3.catalog.fragment;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.catalog.fragment.AutoOffsetScrollListener;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher;
import com.amazon.mp3.find.util.EntityTrackingIntentUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.intents.EntityVisitIntentInfo;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.binders.BackgroundImageOverlayBinder;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.ui.foundations.styles.GradientStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DetailBrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J(\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\b\u0010\u000f\u001a\u00020\u0006H\u0014J\n\u0010\u0010\u001a\u0004\u0018\u00010\u000eH$J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0006H\u0014J\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\u0006H\u0016J\b\u0010\u0019\u001a\u00020\u0006H\u0016J\u0012\u0010\u001a\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002JV\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0018\u0010!\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010\u000e2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0004J\u001a\u0010(\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010)\u001a\u00020\u0017H\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/DetailBrushFragment;", "Lcom/amazon/mp3/catalog/fragment/BrushFragment;", "()V", "backgroundImageOverlayBinder", "Lcom/amazon/music/views/library/binders/BackgroundImageOverlayBinder;", "bindBackgroundImageToFirstItem", "", "bindBackgroundImageView", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "artistArtworkLayout", "Landroid/view/View;", "artistArtwork", "backgroundImageUrl", "", "createBrushRootView", "getBackgroundImageUrl", "getNavigationIntentEvent", "Lcom/amazon/music/find/intents/EntityVisitIntentInfo;", "pageGridViewModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "initActionBarView", "isGridListHeaderViewPresent", "", "onStart", "onStop", "sendNavigationIntentEvent", "sendUiClickMetric", "blockRef", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", ContextMappingConstants.CONTENT_INFO, "", ContextMappingConstants.ENTITY_ID, ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", "updatePageModel", "isInitialPage", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class DetailBrushFragment extends BrushFragment {
    private BackgroundImageOverlayBinder backgroundImageOverlayBinder;

    protected abstract String getBackgroundImageUrl();

    protected EntityVisitIntentInfo getNavigationIntentEvent(PageGridViewModel pageGridViewModel) {
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void initActionBarView() {
        if (this.mActionBarView == null) {
            FragmentActivity activity = getActivity();
            this.mActionBarView = activity != null ? new ActionBarView(activity, null, null, false, false, null, 62, null) : null;
        } else if (this.mPageModel.getNextPageToken() != null) {
            this.mActionBarView.setTitles(this.mToolbarTitle, null);
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        DynamicBackgroundImagePublisher.INSTANCE.getInstance().pauseDynamicBackgroundImagePublisher();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        DynamicBackgroundImagePublisher.INSTANCE.getInstance().resumeDynamicBackgroundImagePublisher();
        BackgroundImageOverlayBinder backgroundImageOverlayBinder = this.backgroundImageOverlayBinder;
        if (backgroundImageOverlayBinder == null) {
            return;
        }
        backgroundImageOverlayBinder.clearBitmaps();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void createBrushRootView() {
        BrushViews brushViews = this.brushViews;
        Intrinsics.checkNotNullExpressionValue(brushViews, "brushViews");
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        List<BaseViewModel> models = this.mPageModel.getModels();
        int tileType = this.mPageModel.getTileType();
        RecyclerView mRecyclerView = this.mRecyclerView;
        Intrinsics.checkNotNullExpressionValue(mRecyclerView, "mRecyclerView");
        BrushViews.createRootView$default(brushViews, userSubscription, models, tileType, mRecyclerView, this.swipeRefreshLayout, null, 32, null);
        setBrushViewsTopOffsetIfNeed();
        setupLayoutAndScrollPosition();
        bindBackgroundImageToFirstItem();
    }

    public final void bindBackgroundImageToFirstItem() {
        final String backgroundImageUrl = getBackgroundImageUrl();
        final Pair<Integer, Integer> pair = setupLayoutAndScrollPosition();
        this.mBackgroundArtworkLayout.setVisibility(0);
        this.mBackgroundArtworkOverlayLayout.setVisibility(0);
        View view = getView();
        if (view == null) {
            return;
        }
        view.post(new Runnable() { // from class: com.amazon.mp3.catalog.fragment.DetailBrushFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DetailBrushFragment.m476bindBackgroundImageToFirstItem$lambda1(this.f$0, pair, backgroundImageUrl);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindBackgroundImageToFirstItem$lambda-1, reason: not valid java name */
    public static final void m476bindBackgroundImageToFirstItem$lambda1(final DetailBrushFragment this$0, Pair lastScrollPosition, final String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewGroup mBackgroundArtworkOverlayLayout = this$0.mBackgroundArtworkOverlayLayout;
        Intrinsics.checkNotNullExpressionValue(mBackgroundArtworkOverlayLayout, "mBackgroundArtworkOverlayLayout");
        Intrinsics.checkNotNullExpressionValue(lastScrollPosition, "lastScrollPosition");
        MusicDetailPageAutoOffsetScrollListener musicDetailPageAutoOffsetScrollListener = new MusicDetailPageAutoOffsetScrollListener(mBackgroundArtworkOverlayLayout, 0, lastScrollPosition, new AutoOffsetScrollListener.OnBindCallback() { // from class: com.amazon.mp3.catalog.fragment.DetailBrushFragment$bindBackgroundImageToFirstItem$1$autoOffsetScrollListener$1
            @Override // com.amazon.mp3.catalog.fragment.AutoOffsetScrollListener.OnBindCallback
            public void onBind(View view) {
                if (view == null) {
                    return;
                }
                DetailBrushFragment detailBrushFragment = this.this$0;
                String str2 = str;
                StyleSheet styleSheet = detailBrushFragment.styleSheet;
                Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
                ImageView mBackgroundArtworkLayout = detailBrushFragment.mBackgroundArtworkLayout;
                Intrinsics.checkNotNullExpressionValue(mBackgroundArtworkLayout, "mBackgroundArtworkLayout");
                detailBrushFragment.bindBackgroundImageView(styleSheet, view, mBackgroundArtworkLayout, str2);
            }
        });
        RecyclerView recyclerView = this$0.mRecyclerView;
        if (recyclerView == null) {
            return;
        }
        recyclerView.setOnScrollListener(musicDetailPageAutoOffsetScrollListener);
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void updatePageModel(PageGridViewModel pageGridViewModel, boolean isInitialPage) {
        sendNavigationIntentEvent(pageGridViewModel);
        super.updatePageModel(pageGridViewModel, isInitialPage);
    }

    private final void sendNavigationIntentEvent(PageGridViewModel pageGridViewModel) {
        EntityVisitIntentInfo navigationIntentEvent = getNavigationIntentEvent(pageGridViewModel);
        Context context = getContext();
        if (navigationIntentEvent == null || context == null) {
            return;
        }
        EntityTrackingIntentUtil.INSTANCE.sendEntityVisitIntentEvent(context, navigationIntentEvent);
    }

    public final void bindBackgroundImageView(StyleSheet styleSheet, View artistArtworkLayout, View artistArtwork, String backgroundImageUrl) {
        RecyclerView recyclerView;
        BackgroundImageOverlayBinder backgroundImageOverlayBinder;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(artistArtworkLayout, "artistArtworkLayout");
        Intrinsics.checkNotNullParameter(artistArtwork, "artistArtwork");
        BrushViews brushViews = this.brushViews;
        if (brushViews == null) {
            return;
        }
        BackgroundImageOverlayBinder backgroundImageOverlayBinder2 = new BackgroundImageOverlayBinder(brushViews.getImageBinder(), styleSheet.getColor(ColorKey.COLOR_SECONDARY, AlphaKey.GLASS_3));
        this.backgroundImageOverlayBinder = backgroundImageOverlayBinder2;
        backgroundImageOverlayBinder2.bindBackgroundViewWithImageOverlay(artistArtworkLayout, artistArtwork, backgroundImageUrl);
        GradientStyle gradientStyle = styleSheet.getGradientStyle(GradientStyleKey.GRADIENT_1);
        GradientStyle gradientStyle2 = styleSheet.getGradientStyle(GradientStyleKey.GRADIENT_2);
        if (gradientStyle == null || gradientStyle2 == null || (recyclerView = this.mRecyclerView) == null || (backgroundImageOverlayBinder = this.backgroundImageOverlayBinder) == null) {
            return;
        }
        backgroundImageOverlayBinder.setupGradientBackgroundOverlay(recyclerView.getHeight(), this.mRecyclerView.getWidth(), gradientStyle, gradientStyle2, artistArtworkLayout);
    }

    protected final void sendUiClickMetric(String blockRef, ActionType actionType, InteractionType interactionType, final Map<String, String> contentInfo, String entityId, EntityIdType entityIdType, EntityType entityType) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        Intrinsics.checkNotNullParameter(entityIdType, "entityIdType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        UiClickEvent uiClickEventBuild = UiClickEvent.builder(actionType).withInteractionType(interactionType).withPageType(getPageType()).withBlockRef(blockRef).withContentInfo(new ArrayList<Map<String, ? extends String>>(contentInfo) { // from class: com.amazon.mp3.catalog.fragment.DetailBrushFragment$sendUiClickMetric$event$1
            final /* synthetic */ Map<String, String> $contentInfo;

            {
                this.$contentInfo = contentInfo;
                add(contentInfo);
            }

            @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
            public final /* bridge */ boolean contains(Object obj) {
                if (obj == null ? true : obj instanceof Map) {
                    return contains((Map<String, String>) obj);
                }
                return false;
            }

            public /* bridge */ boolean contains(Map<String, String> map) {
                return super.contains((Object) map);
            }

            public /* bridge */ int getSize() {
                return super.size();
            }

            @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
            public final /* bridge */ int indexOf(Object obj) {
                if (obj == null ? true : obj instanceof Map) {
                    return indexOf((Map<String, String>) obj);
                }
                return -1;
            }

            public /* bridge */ int indexOf(Map<String, String> map) {
                return super.indexOf((Object) map);
            }

            @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
            public final /* bridge */ int lastIndexOf(Object obj) {
                if (obj == null ? true : obj instanceof Map) {
                    return lastIndexOf((Map<String, String>) obj);
                }
                return -1;
            }

            public /* bridge */ int lastIndexOf(Map<String, String> map) {
                return super.lastIndexOf((Object) map);
            }

            @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
            public final /* bridge */ boolean remove(Object obj) {
                if (obj == null ? true : obj instanceof Map) {
                    return remove((Map<String, String>) obj);
                }
                return false;
            }

            public /* bridge */ boolean remove(Map<String, String> map) {
                return super.remove((Object) map);
            }

            @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
            public final /* bridge */ int size() {
                return getSize();
            }
        }).withEntityId(entityId).withEntityIdType(entityIdType).withEntityType(entityType).withEventTime(System.currentTimeMillis()).build();
        Intrinsics.checkNotNullExpressionValue(uiClickEventBuild, "contentInfo: Map<String?…\n                .build()");
        MetricsHolder.getManager().handleEvent(uiClickEventBuild);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean isGridListHeaderViewPresent() {
        /*
            r4 = this;
            com.amazon.music.views.library.models.PageGridViewModel r0 = r4.mPageModel
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L8
        L6:
            r1 = r2
            goto L2e
        L8:
            java.util.List r0 = r0.getModels()
            if (r0 != 0) goto Lf
            goto L6
        Lf:
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L17
        L15:
            r0 = r2
            goto L2c
        L17:
            java.util.Iterator r0 = r0.iterator()
        L1b:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L15
            java.lang.Object r3 = r0.next()
            com.amazon.music.views.library.models.base.BaseViewModel r3 = (com.amazon.music.views.library.models.base.BaseViewModel) r3
            boolean r3 = r3 instanceof com.amazon.music.views.library.models.GridListHeaderModel
            if (r3 == 0) goto L1b
            r0 = r1
        L2c:
            if (r0 != r1) goto L6
        L2e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.DetailBrushFragment.isGridListHeaderViewPresent():boolean");
    }
}
