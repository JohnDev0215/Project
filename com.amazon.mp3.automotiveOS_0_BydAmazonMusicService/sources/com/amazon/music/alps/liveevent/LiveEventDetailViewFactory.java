package com.amazon.music.alps.liveevent;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.alps.liveevent.binder.EventHeaderBinder;
import com.amazon.music.alps.liveevent.binder.FlexibleTextContainerBinder;
import com.amazon.music.alps.liveevent.binder.IconLinkItemBinder;
import com.amazon.music.alps.liveevent.binder.LineupBinder;
import com.amazon.music.alps.liveevent.binder.NewEventHeaderBinder;
import com.amazon.music.alps.liveevent.binder.NewWaysToWatchBinder;
import com.amazon.music.alps.liveevent.binder.SelectItemBinder;
import com.amazon.music.alps.liveevent.binder.SponsorImageBinder;
import com.amazon.music.alps.liveevent.binder.SponsorImageItemBinder;
import com.amazon.music.alps.liveevent.binder.VerticalIconLinkItemBinder;
import com.amazon.music.alps.liveevent.viewmodel.EventHeaderViewModel;
import com.amazon.music.alps.liveevent.viewmodel.LiveEventDetailViewModel;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.uicontentview.ContentViewManager;
import com.amazon.music.views.library.binders.ArtworkFrameBinder;
import com.amazon.music.views.library.binders.ButtonBinder;
import com.amazon.music.views.library.binders.HorizontalTileBinder;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.binders.SectionBinder;
import com.amazon.music.views.library.binders.ShovelerBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.binders.VerticalTileBinder;
import com.amazon.music.views.library.deeplinks.DeeplinkClickListenerFactory;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveEventDetailViewFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0002\u0010\u0016J\u001c\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010&\u001a\u00020'R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/amazon/music/alps/liveevent/LiveEventDetailViewFactory;", "", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "userSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "viewModel", "Lcom/amazon/music/alps/liveevent/viewmodel/LiveEventDetailViewModel;", "iconLinkItemBinder", "Lcom/amazon/music/alps/liveevent/binder/IconLinkItemBinder;", "verticalIconLinkItemBinder", "Lcom/amazon/music/alps/liveevent/binder/VerticalIconLinkItemBinder;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "eventHeaderBinder", "Lcom/amazon/music/alps/liveevent/binder/EventHeaderBinder;", "newEventHeaderBinder", "Lcom/amazon/music/alps/liveevent/binder/NewEventHeaderBinder;", "(Landroidx/lifecycle/LifecycleOwner;Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/subscription/UserSubscription;Lcom/amazon/music/alps/liveevent/viewmodel/LiveEventDetailViewModel;Lcom/amazon/music/alps/liveevent/binder/IconLinkItemBinder;Lcom/amazon/music/alps/liveevent/binder/VerticalIconLinkItemBinder;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/alps/liveevent/binder/EventHeaderBinder;Lcom/amazon/music/alps/liveevent/binder/NewEventHeaderBinder;)V", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "buttonBinder", "Lcom/amazon/music/views/library/binders/ButtonBinder;", "sectionBinder", "Lcom/amazon/music/views/library/binders/SectionBinder;", "shovelerBinder", "Lcom/amazon/music/views/library/binders/ShovelerBinder;", "waysToWatchBinder", "Lcom/amazon/music/alps/liveevent/binder/NewWaysToWatchBinder;", "createLiveEventDetailViews", "", "baseViewModels", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LiveEventDetailViewFactory {
    private final BaseViewModelAdapterProvider adapterProvider;
    private final ButtonBinder buttonBinder;
    private final EventHeaderBinder eventHeaderBinder;
    private final NewEventHeaderBinder newEventHeaderBinder;
    private final SectionBinder sectionBinder;
    private final ShovelerBinder shovelerBinder;
    private final NewWaysToWatchBinder waysToWatchBinder;

    public LiveEventDetailViewFactory(LifecycleOwner lifecycleOwner, Context context, StyleSheet styleSheet, UserSubscription userSubscription, LiveEventDetailViewModel viewModel, IconLinkItemBinder iconLinkItemBinder, VerticalIconLinkItemBinder verticalIconLinkItemBinder, PageType pageType, EventHeaderBinder eventHeaderBinder, NewEventHeaderBinder newEventHeaderBinder) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(iconLinkItemBinder, "iconLinkItemBinder");
        Intrinsics.checkNotNullParameter(verticalIconLinkItemBinder, "verticalIconLinkItemBinder");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(eventHeaderBinder, "eventHeaderBinder");
        Intrinsics.checkNotNullParameter(newEventHeaderBinder, "newEventHeaderBinder");
        this.eventHeaderBinder = eventHeaderBinder;
        this.newEventHeaderBinder = newEventHeaderBinder;
        BaseViewModelAdapterProvider baseViewModelAdapterProvider = new BaseViewModelAdapterProvider(lifecycleOwner);
        this.adapterProvider = baseViewModelAdapterProvider;
        DeeplinksManager deeplinksManager = DeeplinksManager.get(context);
        Intrinsics.checkNotNullExpressionValue(deeplinksManager, "get(context)");
        DeeplinkClickListenerFactory deeplinkClickListenerFactory = new DeeplinkClickListenerFactory(context, deeplinksManager, viewModel.openContent());
        PicassoImageLoader picassoImageLoader = PicassoImageLoader.builder(context, Picasso.get()).withTag(context).build();
        Picasso picasso = Picasso.get();
        Intrinsics.checkNotNullExpressionValue(picassoImageLoader, "picassoImageLoader");
        ImageBinder imageBinder = new ImageBinder(context, styleSheet, picassoImageLoader);
        ContentViewManager contentViewManager = new ContentViewManager();
        ArtworkFrameBinder artworkFrameBinder = new ArtworkFrameBinder(context, styleSheet, imageBinder, null, null, 24, null);
        VerticalTileBinder verticalTileBinder = new VerticalTileBinder(styleSheet, userSubscription, artworkFrameBinder, null, deeplinkClickListenerFactory, null, imageBinder, 40, 0 == true ? 1 : 0);
        HorizontalTileBinder horizontalTileBinder = new HorizontalTileBinder(styleSheet, userSubscription, null, artworkFrameBinder, 0 == true ? 1 : 0, null, null, deeplinkClickListenerFactory, null, null, null, 1876, null);
        this.shovelerBinder = new ShovelerBinder(context, styleSheet, viewModel.openSeeMore(), baseViewModelAdapterProvider, contentViewManager, 0 == true ? 1 : 0, null, 0 == true ? 1 : 0, null, new UniversalBinder[]{verticalTileBinder}, 480, 0 == true ? 1 : 0);
        byte b = 0;
        byte b2 = 0;
        ButtonBinder buttonBinder = new ButtonBinder(context, styleSheet, null, null, null, 0 == true ? 1 : 0, null, 124, null);
        this.buttonBinder = buttonBinder;
        int i = 4;
        this.waysToWatchBinder = new NewWaysToWatchBinder(styleSheet, verticalIconLinkItemBinder, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
        SelectItemBinder selectItemBinder = new SelectItemBinder(styleSheet, null, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullExpressionValue(picasso, "picasso");
        int i2 = 2;
        SponsorImageBinder sponsorImageBinder = new SponsorImageBinder(styleSheet, new SponsorImageItemBinder(picasso, 0 == true ? 1 : 0, i2, 0 == true ? 1 : 0), baseViewModelAdapterProvider, null, 8, null);
        this.sectionBinder = new SectionBinder(context, styleSheet, viewModel.openSeeMore(), null, baseViewModelAdapterProvider, new ContentViewManager(), null, null, null, new UniversalBinder[]{iconLinkItemBinder, verticalIconLinkItemBinder, new FlexibleTextContainerBinder(styleSheet, b2 == true ? 1 : 0, i2, b == true ? 1 : 0), buttonBinder, new LineupBinder(pageType == PageType.LIVE_EVENT_DETAIL, styleSheet, baseViewModelAdapterProvider, selectItemBinder, horizontalTileBinder, null, 32, null), sponsorImageBinder}, 456, null);
    }

    public final void createLiveEventDetailViews(List<? extends BaseViewModel> baseViewModels, RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(baseViewModels, "baseViewModels");
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        if (baseViewModels.get(0) instanceof EventHeaderViewModel) {
            recyclerView.setPadding(recyclerView.getPaddingLeft(), 0, recyclerView.getPaddingRight(), recyclerView.getPaddingBottom());
        }
        recyclerView.setAdapter(this.adapterProvider.getAdapter(baseViewModels, this.eventHeaderBinder, this.newEventHeaderBinder, this.waysToWatchBinder, this.shovelerBinder, this.sectionBinder, this.buttonBinder));
    }
}
