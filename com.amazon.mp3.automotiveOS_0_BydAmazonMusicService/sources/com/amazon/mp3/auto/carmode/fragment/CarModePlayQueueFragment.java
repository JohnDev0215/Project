package com.amazon.mp3.auto.carmode.fragment;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.CarModeAlexaFabViewController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.catalog.fragment.BrowseViewsFactory;
import com.amazon.mp3.catalog.fragment.datasource.repository.PlayQueueDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PlayQueueViewModel;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.providers.PlayQueueClickListener;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.uicontentview.ContentViewManager;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.EndlessScrollDataProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* JADX INFO: compiled from: CarModePlayQueueFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J&\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010&\u001a\u00020\u001cH\u0002J\b\u0010'\u001a\u00020\u001cH\u0002J\u0006\u0010(\u001a\u00020\u001cJ\b\u0010)\u001a\u00020\u001cH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModePlayQueueFragment;", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeBaseFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "alexaButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "alexaFabViewController", "Lcom/amazon/mp3/auto/CarModeAlexaFabViewController;", "closeButton", "contentViewManager", "Lcom/amazon/music/uicontentview/ContentViewManager;", "pageModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "playQueueBrushViews", "Lcom/amazon/mp3/brush/BrushViews;", "playQueueClickListener", "Lcom/amazon/mp3/providers/PlayQueueClickListener;", "playQueueRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "queueTitle", "Landroid/widget/TextView;", "viewModel", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PlayQueueViewModel;", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onLoadMorePage", "scrollToPlayingContent", "setupBrushViews", "setupWidgets", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModePlayQueueFragment extends CarModeBaseFragment {
    private BaseButton alexaButton;
    private CarModeAlexaFabViewController alexaFabViewController;
    private BaseButton closeButton;
    private ContentViewManager contentViewManager;
    private PageGridViewModel pageModel;
    private BrushViews playQueueBrushViews;
    private PlayQueueClickListener playQueueClickListener;
    private RecyclerView playQueueRecyclerView;
    private TextView queueTitle;
    private PlayQueueViewModel viewModel;
    private final String TAG = CarModePlayQueueFragment.class.getSimpleName();
    private final ActionValidatedPlaybackController playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_car_mode_play_queue, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…_queue, container, false)");
        setRoot(viewInflate);
        View viewFindViewById = getRoot().findViewById(R.id.car_mode_play_queue_close_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.c…_play_queue_close_button)");
        this.closeButton = (BaseButton) viewFindViewById;
        View viewFindViewById2 = getRoot().findViewById(R.id.car_mode_play_queue_alexa_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.c…_play_queue_alexa_button)");
        this.alexaButton = (BaseButton) viewFindViewById2;
        View viewFindViewById3 = getRoot().findViewById(R.id.car_mode_play_queue_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.car_mode_play_queue_title)");
        this.queueTitle = (TextView) viewFindViewById3;
        View viewFindViewById4 = getRoot().findViewById(R.id.car_mode_play_queue);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "root.findViewById(R.id.car_mode_play_queue)");
        this.playQueueRecyclerView = (RecyclerView) viewFindViewById4;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        BaseButton baseButton = this.alexaButton;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alexaButton");
            baseButton = null;
        }
        this.alexaFabViewController = new CarModeAlexaFabViewController(contextRequireContext, baseButton, PageType.CAR_MODE_PLAY_QUEUE);
        return getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePlayQueueFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModePlayQueueFragment.m299onCreate$lambda0(this.f$0, (StyleSheet) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePlayQueueFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModePlayQueueFragment.m300onCreate$lambda1(this.f$0, (Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m299onCreate$lambda0(CarModePlayQueueFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setStyleSheet(styleSheet);
        this$0.setupWidgets();
        this$0.setupBrushViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-1, reason: not valid java name */
    public static final void m300onCreate$lambda1(CarModePlayQueueFragment this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "Failed to fetch stylesheet", th);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        MutableLiveData<PageGridViewModel> pageModelLiveData;
        super.onActivityCreated(savedInstanceState);
        PageType pageType = PageType.CAR_MODE_PLAY_QUEUE;
        ExperienceMode experienceMode = ExperienceMode.CAR_MODE;
        AutoUiEventUtil.sendUiPageViewEvent(pageType, experienceMode);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        PlayQueueClickListener playQueueClickListener = new PlayQueueClickListener(activity);
        playQueueClickListener.configMetricsArgs(pageType, ActionType.PLAY_SONG, experienceMode);
        this.playQueueClickListener = playQueueClickListener;
        PlayQueueDataRepository playQueueDataRepository = new PlayQueueDataRepository(activity, CloudQueueSequencer.INSTANCE.isCloudQueuePlaying(), true);
        Application application = activity.getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "context.application");
        PageViewModelProvider viewModel = PageViewModelFactoryProvider.INSTANCE.getViewModel(this, new PageViewModelFactoryProvider.PlayQueueViewModelFactory(application, playQueueDataRepository, true));
        PlayQueueViewModel playQueueViewModel = viewModel instanceof PlayQueueViewModel ? (PlayQueueViewModel) viewModel : null;
        this.viewModel = playQueueViewModel;
        if (playQueueViewModel != null) {
            playQueueViewModel.setCarModePlayQueue(true);
        }
        PlayQueueViewModel playQueueViewModel2 = this.viewModel;
        if (playQueueViewModel2 != null && (pageModelLiveData = playQueueViewModel2.getPageModelLiveData()) != null) {
            pageModelLiveData.observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePlayQueueFragment$$ExternalSyntheticLambda2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    CarModePlayQueueFragment.m298onActivityCreated$lambda4$lambda3(this.f$0, (PageGridViewModel) obj);
                }
            });
        }
        PlayQueueViewModel playQueueViewModel3 = this.viewModel;
        if (playQueueViewModel3 == null) {
            return;
        }
        playQueueViewModel3.fetchInitPage(20);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onActivityCreated$lambda-4$lambda-3, reason: not valid java name */
    public static final void m298onActivityCreated$lambda4$lambda3(CarModePlayQueueFragment this$0, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PageGridViewModel pageGridViewModel2 = this$0.pageModel;
        boolean z = (pageGridViewModel2 == null ? 0 : pageGridViewModel2.getPageIndex()) < (pageGridViewModel == null ? 0 : pageGridViewModel.getPageIndex());
        PlayQueueViewModel playQueueViewModel = this$0.viewModel;
        if (playQueueViewModel != null) {
            playQueueViewModel.updatePlayState();
        }
        this$0.pageModel = pageGridViewModel;
        if (z) {
            this$0.onLoadMorePage();
        } else {
            this$0.setupBrushViews();
        }
    }

    private final void setupWidgets() {
        FontStyle fontStyle;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        StyleSheet styleSheet = getStyleSheet();
        TextView textView = null;
        BaseButton.StyleBuilder iconBuilder = styleSheet == null ? null : styleSheet.getIconBuilder(IconSizeKey.LARGE, IconStyleKey.GLASS);
        BaseButton baseButton = this.closeButton;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
            baseButton = null;
        }
        Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.car_mode_caret_down);
        if (drawable != null && iconBuilder != null && (styleBuilderWithIcon2 = iconBuilder.withIcon(drawable)) != null) {
            styleBuilderWithIcon2.applyStyle(baseButton);
        }
        BaseButton baseButton2 = this.closeButton;
        if (baseButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
            baseButton2 = null;
        }
        baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePlayQueueFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModePlayQueueFragment.m301setupWidgets$lambda7(this.f$0, view);
            }
        });
        Drawable drawable2 = ContextCompat.getDrawable(requireContext(), R.drawable.ic_alexa_bauhaus);
        if (drawable2 != null && iconBuilder != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable2)) != null) {
            BaseButton baseButton3 = this.alexaButton;
            if (baseButton3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alexaButton");
                baseButton3 = null;
            }
            styleBuilderWithIcon.applyStyle(baseButton3);
        }
        TextView textView2 = this.queueTitle;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("queueTitle");
            textView2 = null;
        }
        MediaCollectionInfo sourceMediaCollectionInfo = this.playbackController.getCurrentMediaItem().getSourceMediaCollectionInfo();
        textView2.setText(sourceMediaCollectionInfo == null ? null : sourceMediaCollectionInfo.getName());
        StyleSheet styleSheet2 = getStyleSheet();
        if (styleSheet2 == null || (fontStyle = styleSheet2.getFontStyle(FontStyleKey.HEADLINE_3)) == null) {
            return;
        }
        FontUtil fontUtil = FontUtil.INSTANCE;
        TextView textView3 = this.queueTitle;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("queueTitle");
        } else {
            textView = textView3;
        }
        fontUtil.applyFontStyle(textView, fontStyle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupWidgets$lambda-7, reason: not valid java name */
    public static final void m301setupWidgets$lambda7(CarModePlayQueueFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getParentFragmentManager().getBackStackEntryCount() == 0) {
            FragmentActivity activity = this$0.getActivity();
            if (activity == null) {
                return;
            }
            activity.onBackPressed();
            return;
        }
        this$0.getParentFragmentManager().popBackStack();
    }

    public final void setupBrushViews() {
        List<BaseViewModel> models;
        BrushViews brushViews;
        if (getStyleSheet() != null) {
            PlayQueueViewModel playQueueViewModel = this.viewModel;
            boolean z = false;
            if (playQueueViewModel != null && !playQueueViewModel.hasPageData()) {
                z = true;
            }
            if (z) {
                return;
            }
            this.contentViewManager = new ContentViewManager();
            this.playQueueBrushViews = BrowseViewsFactory.createBrowseViewsV2(this, getStyleSheet(), PageType.CAR_MODE_PLAY_QUEUE, this.playQueueClickListener, new BrushUriClickListenerProvider() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePlayQueueFragment.setupBrushViews.1
                @Override // com.amazon.music.views.library.providers.BrushUriClickListenerProvider
                public void onClick(String target, String viewId, TargetType targetType) {
                }
            }, new EndlessScrollDataProvider() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePlayQueueFragment.setupBrushViews.2
                @Override // com.amazon.music.views.library.providers.EndlessScrollDataProvider
                public void loadMore(int page, int totalItemsCount) {
                    PlayQueueViewModel playQueueViewModel2;
                    Log.debug(CarModePlayQueueFragment.this.TAG, Intrinsics.stringPlus("scroll to page ", Integer.valueOf(page)));
                    PageGridViewModel pageGridViewModel = CarModePlayQueueFragment.this.pageModel;
                    if (pageGridViewModel == null || (playQueueViewModel2 = CarModePlayQueueFragment.this.viewModel) == null) {
                        return;
                    }
                    playQueueViewModel2.fetchMorePage(pageGridViewModel, page, 20);
                }
            }, this.contentViewManager, null);
            PageGridViewModel pageGridViewModel = this.pageModel;
            if (pageGridViewModel != null && (models = pageGridViewModel.getModels()) != null && (brushViews = this.playQueueBrushViews) != null) {
                UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
                Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
                RecyclerView recyclerView = this.playQueueRecyclerView;
                if (recyclerView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("playQueueRecyclerView");
                    recyclerView = null;
                }
                BrushViews.createRootView$default(brushViews, userSubscription, models, 2, recyclerView, (ViewGroup) getRoot(), null, 32, null);
            }
            scrollToPlayingContent();
        }
    }

    private final void onLoadMorePage() {
        List<BaseViewModel> models;
        BrushViews brushViews;
        PageGridViewModel pageGridViewModel = this.pageModel;
        if (pageGridViewModel == null || (models = pageGridViewModel.getModels()) == null || (brushViews = this.playQueueBrushViews) == null) {
            return;
        }
        brushViews.updateAdapter(models);
    }

    private final void scrollToPlayingContent() {
        int currentIndex = this.playbackController.getCurrentIndex();
        if (currentIndex >= 0) {
            RecyclerView recyclerView = this.playQueueRecyclerView;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playQueueRecyclerView");
                recyclerView = null;
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager == null) {
                return;
            }
            layoutManager.scrollToPosition(currentIndex);
        }
    }
}
