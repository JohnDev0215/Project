package com.amazon.mp3.library.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.fragment.contextmenu.VideoContextMenuProvider;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.library.adapter.VideoPlaylistDetailAdapter;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.mp3.util.extensions.CollectionKt;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.share.PlaylistShareProvider;
import com.amazon.music.views.library.views.FixedAspectImageView;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoPlaylistDetailFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 P2\u00020\u00012\u00020\u0002:\u0001PB\u0005¢\u0006\u0002\u0010\u0003J\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0016J\u0018\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@H\u0016J&\u0010A\u001a\u0004\u0018\u00010\u00122\u0006\u0010?\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010&2\b\u0010D\u001a\u0004\u0018\u00010EH\u0016J\u0010\u0010F\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0016J\b\u0010G\u001a\u00020<H\u0016J\u0010\u0010H\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0016J\b\u0010I\u001a\u00020<H\u0016J\u001a\u0010J\u001a\u00020<2\u0006\u0010K\u001a\u00020\u00122\b\u0010D\u001a\u0004\u0018\u00010EH\u0016J\u0012\u0010L\u001a\u00020<2\b\u0010M\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010N\u001a\u00020<2\u0006\u0010O\u001a\u000208H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\"8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0014R\u0014\u0010)\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u0018R\u001b\u0010+\u001a\u00020,8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b/\u0010\u000b\u001a\u0004\b-\u0010.R\u000e\u00100\u001a\u000201X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u00102\u001a\u0002038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\u000b\u001a\u0004\b4\u00105¨\u0006Q"}, d2 = {"Lcom/amazon/mp3/library/fragment/VideoPlaylistDetailFragment;", "Lcom/amazon/mp3/fragment/BaseFragment;", "Lcom/amazon/mp3/fragment/headerbar/OverflowMenuReceiver;", "()V", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "actionBarView", "Lcom/amazon/mp3/actionbar/view/ActionBarView;", "getActionBarView", "()Lcom/amazon/mp3/actionbar/view/ActionBarView;", "actionBarView$delegate", "Lkotlin/Lazy;", "adapter", "Lcom/amazon/mp3/library/adapter/VideoPlaylistDetailAdapter;", "getAdapter", "()Lcom/amazon/mp3/library/adapter/VideoPlaylistDetailAdapter;", "adapter$delegate", "loadingIndicator", "Landroid/view/View;", "getLoadingIndicator", "()Landroid/view/View;", "playPauseButtonIV", "Landroid/widget/ImageView;", "getPlayPauseButtonIV", "()Landroid/widget/ImageView;", "playlistArtworkIV", "Lcom/amazon/music/views/library/views/FixedAspectImageView;", "getPlaylistArtworkIV", "()Lcom/amazon/music/views/library/views/FixedAspectImageView;", "playlistDescriptionTV", "Landroid/widget/TextView;", "getPlaylistDescriptionTV", "()Landroid/widget/TextView;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "rootView", "Landroid/view/ViewGroup;", "scrollViewContainer", "getScrollViewContainer", "shuffleButtonIV", "getShuffleButtonIV", "videoContextMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider;", "getVideoContextMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider;", "videoContextMenuProvider$delegate", "videoPlaylistAsin", "", "videoPlaylistDetailViewModel", "Lcom/amazon/mp3/library/fragment/VideoPlaylistDetailViewModel;", "getVideoPlaylistDetailViewModel", "()Lcom/amazon/mp3/library/fragment/VideoPlaylistDetailViewModel;", "videoPlaylistDetailViewModel$delegate", "onContextItemSelected", "", "item", "Landroid/view/MenuItem;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/LayoutInflater;", "container", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "onPause", "onPrepareOptionsMenu", "onResume", "onViewCreated", "view", "setActionBarProvider", "provider", "showLoading", "shouldShowLoading", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoPlaylistDetailFragment extends BaseFragment implements OverflowMenuReceiver {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = VideoPlaylistDetailFragment.class.getSimpleName();
    private ActionBarProvider actionBarProvider;
    private ViewGroup rootView;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter = LazyKt__LazyJVMKt.lazy(new Function0<VideoPlaylistDetailAdapter>() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$adapter$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final VideoPlaylistDetailAdapter invoke() {
            final VideoPlaylistDetailFragment videoPlaylistDetailFragment = this.this$0;
            return new VideoPlaylistDetailAdapter(new Function2<List<? extends PrimeTrack>, Integer, Unit>() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$adapter$2.1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public /* bridge */ /* synthetic */ Unit mo4invoke(List<? extends PrimeTrack> list, Integer num) {
                    invoke(list, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(List<? extends PrimeTrack> playlistTracks, int i) {
                    Intrinsics.checkNotNullParameter(playlistTracks, "playlistTracks");
                    Context context = videoPlaylistDetailFragment.getContext();
                    if (context == null) {
                        return;
                    }
                    videoPlaylistDetailFragment.getVideoPlaylistDetailViewModel().onVideoClicked(context, playlistTracks, i);
                }
            });
        }
    });
    private String videoPlaylistAsin = "";

    /* JADX INFO: renamed from: videoPlaylistDetailViewModel$delegate, reason: from kotlin metadata */
    private final Lazy videoPlaylistDetailViewModel = LazyKt__LazyJVMKt.lazy(new Function0<VideoPlaylistDetailViewModel>() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$videoPlaylistDetailViewModel$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final VideoPlaylistDetailViewModel invoke() {
            VideoPlaylistDetailFragment videoPlaylistDetailFragment = this.this$0;
            AmazonApplication application = AmazonApplication.getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
            return (VideoPlaylistDetailViewModel) ViewModelProviders.of(videoPlaylistDetailFragment, new VideoPlaylistDetailViewModelFactory(application, this.this$0.videoPlaylistAsin)).get(VideoPlaylistDetailViewModel.class);
        }
    });

    /* JADX INFO: renamed from: actionBarView$delegate, reason: from kotlin metadata */
    private final Lazy actionBarView = LazyKt__LazyJVMKt.lazy(new Function0<ActionBarView>() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$actionBarView$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActionBarView invoke() {
            return new ActionBarView(this.this$0.getActivity(), null, null, false, false, null, 62, null);
        }
    });

    /* JADX INFO: renamed from: videoContextMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy videoContextMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<VideoContextMenuProvider>() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$videoContextMenuProvider$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final VideoContextMenuProvider invoke() {
            FragmentActivity activity = this.this$0.getActivity();
            if (activity != null) {
                return new VideoContextMenuProvider((BaseActivity) activity, new VideoContextMenuProvider.VideoContextMenuProviderListener() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$videoContextMenuProvider$2.1
                });
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.BaseActivity");
        }
    });

    private final FixedAspectImageView getPlaylistArtworkIV() {
        ViewGroup viewGroup = this.rootView;
        FixedAspectImageView fixedAspectImageView = viewGroup == null ? null : (FixedAspectImageView) viewGroup.findViewById(R.id.video_playlist_artwork);
        if (fixedAspectImageView != null) {
            return fixedAspectImageView;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.views.FixedAspectImageView");
    }

    private final ImageView getPlayPauseButtonIV() {
        ViewGroup viewGroup = this.rootView;
        ImageView imageView = viewGroup == null ? null : (ImageView) viewGroup.findViewById(R.id.play_pause_button);
        if (imageView != null) {
            return imageView;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
    }

    private final ImageView getShuffleButtonIV() {
        ViewGroup viewGroup = this.rootView;
        ImageView imageView = viewGroup == null ? null : (ImageView) viewGroup.findViewById(R.id.shuffle_button);
        if (imageView != null) {
            return imageView;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
    }

    private final TextView getPlaylistDescriptionTV() {
        ViewGroup viewGroup = this.rootView;
        TextView textView = viewGroup == null ? null : (TextView) viewGroup.findViewById(R.id.playlist_description);
        if (textView != null) {
            return textView;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
    }

    private final View getScrollViewContainer() {
        ViewGroup viewGroup = this.rootView;
        View viewFindViewById = viewGroup == null ? null : viewGroup.findViewById(R.id.scroll_view_container);
        if (viewFindViewById != null) {
            return viewFindViewById;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.View");
    }

    private final View getLoadingIndicator() {
        ViewGroup viewGroup = this.rootView;
        View viewFindViewById = viewGroup == null ? null : viewGroup.findViewById(R.id.loading_indicator);
        if (viewFindViewById != null) {
            return viewFindViewById;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.View");
    }

    private final RecyclerView getRecyclerView() {
        ViewGroup viewGroup = this.rootView;
        RecyclerView recyclerView = viewGroup == null ? null : (RecyclerView) viewGroup.findViewById(R.id.playlist_tracks);
        if (recyclerView != null) {
            return recyclerView;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
    }

    private final VideoPlaylistDetailAdapter getAdapter() {
        return (VideoPlaylistDetailAdapter) this.adapter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VideoPlaylistDetailViewModel getVideoPlaylistDetailViewModel() {
        return (VideoPlaylistDetailViewModel) this.videoPlaylistDetailViewModel.getValue();
    }

    private final ActionBarView getActionBarView() {
        return (ActionBarView) this.actionBarView.getValue();
    }

    private final VideoContextMenuProvider getVideoContextMenuProvider() {
        return (VideoContextMenuProvider) this.videoContextMenuProvider.getValue();
    }

    /* JADX INFO: compiled from: VideoPlaylistDetailFragment.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/library/fragment/VideoPlaylistDetailFragment$Companion;", "", "()V", "ARG_ASIN", "", "TAG", "kotlin.jvm.PlatformType", "newInstance", "Lcom/amazon/mp3/library/fragment/VideoPlaylistDetailFragment;", "asin", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final VideoPlaylistDetailFragment newInstance(String asin) {
            Intrinsics.checkNotNullParameter(asin, "asin");
            Bundle bundle = new Bundle();
            bundle.putString("arg_asin", asin);
            VideoPlaylistDetailFragment videoPlaylistDetailFragment = new VideoPlaylistDetailFragment();
            videoPlaylistDetailFragment.setArguments(bundle);
            return videoPlaylistDetailFragment;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        if (this.rootView == null) {
            View viewInflate = inflater.inflate(R.layout.video_playlist_detail, (ViewGroup) null);
            if (viewInflate == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            this.rootView = (ViewGroup) viewInflate;
        }
        return this.rootView;
    }

    @Override // com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver
    public void setActionBarProvider(ActionBarProvider provider) {
        if (provider == null) {
            return;
        }
        this.actionBarProvider = provider;
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Resources resources;
        String string;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        String str = "";
        if (arguments != null && (string = arguments.getString("arg_asin")) != null) {
            str = string;
        }
        this.videoPlaylistAsin = str;
        ActionBarProvider actionBarProvider = this.actionBarProvider;
        ActionBarProvider actionBarProvider2 = null;
        if (actionBarProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actionBarProvider");
            actionBarProvider = null;
        }
        actionBarProvider.setHeaderView(getActionBarView());
        getActionBarView().resetHeight();
        ActionBarProvider actionBarProvider3 = this.actionBarProvider;
        if (actionBarProvider3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actionBarProvider");
        } else {
            actionBarProvider2 = actionBarProvider3;
        }
        actionBarProvider2.requestHomeButtonAsBack();
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(PageType.DETAIL_PLAYLIST.getMetricsValue()).withDetailPageItemId(this.videoPlaylistAsin).withDetailPageItemIdType(EntityIdType.ASIN.getMetricsValue()).build());
        FixedAspectImageView playlistArtworkIV = getPlaylistArtworkIV();
        Context context = getContext();
        float fraction = 1.0f;
        if (context != null && (resources = context.getResources()) != null) {
            fraction = resources.getFraction(R.fraction.video_aspect_ratio, 1, 1);
        }
        playlistArtworkIV.setScalingFactor(fraction);
        getVideoPlaylistDetailViewModel().getVideos().observe(this, new Observer() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoPlaylistDetailFragment.m1285onViewCreated$lambda2(this.f$0, (List) obj);
            }
        });
        getVideoPlaylistDetailViewModel().getPlaylistDescription().observe(this, new Observer() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoPlaylistDetailFragment.m1286onViewCreated$lambda3(this.f$0, (String) obj);
            }
        });
        getVideoPlaylistDetailViewModel().getArtworkUrl().observe(this, new Observer() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoPlaylistDetailFragment.m1287onViewCreated$lambda4(this.f$0, (String) obj);
            }
        });
        getVideoPlaylistDetailViewModel().getTitle().observe(this, new Observer() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoPlaylistDetailFragment.m1288onViewCreated$lambda5(this.f$0, (String) obj);
            }
        });
        getVideoPlaylistDetailViewModel().getLength().observe(this, new Observer() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoPlaylistDetailFragment.m1289onViewCreated$lambda6(this.f$0, (String) obj);
            }
        });
        getVideoPlaylistDetailViewModel().getOnPlaylistFetched().observe(this, new Observer() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoPlaylistDetailFragment.m1290onViewCreated$lambda7(this.f$0, (Unit) obj);
            }
        });
        getVideoPlaylistDetailViewModel().getUpsell().observe(this, new Observer() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoPlaylistDetailFragment.m1291onViewCreated$lambda9(this.f$0, (DialogFragment) obj);
            }
        });
        getVideoPlaylistDetailViewModel().getOnExplicitFilterChanged().observe(this, new Observer() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoPlaylistDetailFragment.m1279onViewCreated$lambda11(this.f$0, (Unit) obj);
            }
        });
        getVideoPlaylistDetailViewModel().getShowLoadingState().observe(this, new Observer() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$$ExternalSyntheticLambda9
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoPlaylistDetailFragment.m1281onViewCreated$lambda13(this.f$0, (Boolean) obj);
            }
        });
        getPlayPauseButtonIV().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VideoPlaylistDetailFragment.m1283onViewCreated$lambda15(this.f$0, view2);
            }
        });
        getShuffleButtonIV().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VideoPlaylistDetailFragment.m1284onViewCreated$lambda17(this.f$0, view2);
            }
        });
        Log.info(TAG, "Video Playlist ASIN: {" + this.videoPlaylistAsin + '}');
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final void m1285onViewCreated$lambda2(VideoPlaylistDetailFragment this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || CollectionKt.deepEquals(this$0.getAdapter().getData(), list)) {
            return;
        }
        this$0.getAdapter().setData(list);
        if (this$0.getRecyclerView().getAdapter() == null) {
            this$0.getRecyclerView().setAdapter(this$0.getAdapter());
            this$0.getRecyclerView().setLayoutManager(new LinearLayoutManager(this$0.getContext()));
        } else {
            RecyclerView.Adapter adapter = this$0.getRecyclerView().getAdapter();
            if (adapter == null) {
                return;
            }
            adapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-3, reason: not valid java name */
    public static final void m1286onViewCreated$lambda3(VideoPlaylistDetailFragment this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getPlaylistDescriptionTV().setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-4, reason: not valid java name */
    public static final void m1287onViewCreated$lambda4(VideoPlaylistDetailFragment this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PicassoImageLoader.builder(this$0.getContext(), Picasso.get()).withTag(this$0.getContext()).build().loadImage(str, this$0.getPlaylistArtworkIV());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-5, reason: not valid java name */
    public static final void m1288onViewCreated$lambda5(VideoPlaylistDetailFragment this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getActionBarView().setTitles(str, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-6, reason: not valid java name */
    public static final void m1289onViewCreated$lambda6(VideoPlaylistDetailFragment this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getActionBarView().setSubtitle(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-7, reason: not valid java name */
    public static final void m1290onViewCreated$lambda7(VideoPlaylistDetailFragment this$0, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setHasOptionsMenu(true);
        this$0.registerForContextMenu(this$0.getRecyclerView());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-9, reason: not valid java name */
    public static final void m1291onViewCreated$lambda9(VideoPlaylistDetailFragment this$0, DialogFragment dialogFragment) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentManager fragmentManager = this$0.getFragmentManager();
        if (fragmentManager == null || dialogFragment == null) {
            return;
        }
        dialogFragment.show(fragmentManager, (String) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-11, reason: not valid java name */
    public static final void m1279onViewCreated$lambda11(final VideoPlaylistDetailFragment this$0, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                VideoPlaylistDetailFragment.m1280onViewCreated$lambda11$lambda10(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-11$lambda-10, reason: not valid java name */
    public static final void m1280onViewCreated$lambda11$lambda10(VideoPlaylistDetailFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getAdapter().notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-13, reason: not valid java name */
    public static final void m1281onViewCreated$lambda13(final VideoPlaylistDetailFragment this$0, final Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                VideoPlaylistDetailFragment.m1282onViewCreated$lambda13$lambda12(this.f$0, bool);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-13$lambda-12, reason: not valid java name */
    public static final void m1282onViewCreated$lambda13$lambda12(VideoPlaylistDetailFragment this$0, Boolean shouldShowLoading) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(shouldShowLoading, "shouldShowLoading");
        this$0.showLoading(shouldShowLoading.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-15, reason: not valid java name */
    public static final void m1283onViewCreated$lambda15(VideoPlaylistDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity != null && ActivityKt.isAvailable(activity)) {
            this$0.getVideoPlaylistDetailViewModel().sendVideoClickMetrics(ActionType.PLAY_CURATED_PLAYLIST);
            this$0.getVideoPlaylistDetailViewModel().play(activity, this$0.getAdapter().getData(), false, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-17, reason: not valid java name */
    public static final void m1284onViewCreated$lambda17(VideoPlaylistDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity != null && ActivityKt.isAvailable(activity)) {
            this$0.getVideoPlaylistDetailViewModel().sendVideoClickMetrics(ActionType.PLAY_SHUFFLE_ALL);
            this$0.getVideoPlaylistDetailViewModel().play(activity, this$0.getAdapter().getData(), true, true);
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getVideoPlaylistDetailViewModel().loadData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_prime_playlist_detail, menu);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onPrepareOptionsMenu(menu);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        RecyclerView.Adapter adapter = getRecyclerView().getAdapter();
        if (adapter == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.library.adapter.VideoPlaylistDetailAdapter");
        }
        VideoPlaylistDetailAdapter videoPlaylistDetailAdapter = (VideoPlaylistDetailAdapter) adapter;
        PrimeTrack primeTrack = videoPlaylistDetailAdapter.getData().get(videoPlaylistDetailAdapter.getClickedPosition());
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return getVideoContextMenuProvider().onContextMenuItemSelected(activity, item, CollectionsKt__CollectionsJVMKt.listOf(primeTrack), "cirrus");
        }
        return super.onContextItemSelected(item);
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        getVideoPlaylistDetailViewModel().cancel();
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == R.id.MenuPlaylistShare) {
            new PlaylistShareProvider(getActivity(), EndpointsProvider.get().getMusicDomain(), this.videoPlaylistAsin, getVideoPlaylistDetailViewModel().getTitle().getValue(), getVideoPlaylistDetailViewModel().getArtworkUrl().getValue()).startShare();
        }
        return super.onOptionsItemSelected(item);
    }

    private final void showLoading(boolean shouldShowLoading) {
        getLoadingIndicator().setVisibility(shouldShowLoading ? 0 : 8);
        getScrollViewContainer().setVisibility(shouldShowLoading ? 8 : 0);
    }
}
