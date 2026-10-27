package com.amazon.mp3.view.lyrics.sharing.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsShareCardStyle;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricsCardAdapter;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingDataRepository;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingDeeplinkGenerator;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingMetricsSender;
import com.amazon.mp3.view.lyrics.sharing.viewModel.LyricsCardSelectionViewModel;
import com.amazon.mp3.view.lyrics.sharing.viewModel.LyricsCardSelectionViewModelFactory;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.share.menu.ShareableContent;
import com.amazon.music.share.menu.destinations.FacebookStoryShareDestination;
import com.amazon.music.share.menu.destinations.InstagramStoryShareDestination;
import com.amazon.music.share.menu.destinations.MessagesShareDestination;
import com.amazon.music.share.menu.destinations.ShareDestination;
import com.amazon.music.share.menu.providers.EnvironmentProvider;
import com.amazon.music.share.menu.providers.SocialMetadataProvider;
import com.amazon.music.share.menu.providers.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import dagger.android.support.AndroidSupportInjection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsCardSelectionFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Ê\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0001sB\u0019\u0012\u0006\u0010'\u001a\u00020&\u0012\b\b\u0002\u0010.\u001a\u00020-¢\u0006\u0004\bq\u0010rJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\u0016\u0010\b\u001a\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\b\u0010\t\u001a\u00020\u0002H\u0002J\b\u0010\n\u001a\u00020\u0002H\u0002J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0002J\n\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000eH\u0002J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0002J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0002J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0002J\u0010\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0012\u0010\u001d\u001a\u00020\u00022\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001e\u001a\u00020\u0002H\u0016J\b\u0010\u001f\u001a\u00020\u0002H\u0016J$\u0010$\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020 2\b\u0010#\u001a\u0004\u0018\u00010\"2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0016J\u001a\u0010%\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0016R\"\u0010'\u001a\u00020&8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0017\u0010.\u001a\u00020-8\u0006¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101R\"\u00103\u001a\u0002028\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\"\u0010:\u001a\u0002098\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\"\u0010A\u001a\u00020@8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bA\u0010B\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u0016\u0010G\u001a\u00020\u000e8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bG\u0010HR\u0016\u0010J\u001a\u00020I8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bJ\u0010KR\u001a\u0010M\u001a\b\u0012\u0004\u0012\u00020\u000b0L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010NR\u001b\u0010T\u001a\u00020O8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bP\u0010Q\u001a\u0004\bR\u0010SR\u0014\u0010X\u001a\u00020U8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bV\u0010WR\u0014\u0010\\\u001a\u00020Y8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0014\u0010`\u001a\u00020]8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b^\u0010_R\u0014\u0010d\u001a\u00020a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bb\u0010cR\u0014\u0010h\u001a\u00020e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bf\u0010gR\u0016\u0010l\u001a\u0004\u0018\u00010i8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bj\u0010kR\u0014\u0010n\u001a\u00020-8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bm\u00101R\u0014\u0010p\u001a\u00020-8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bo\u00101¨\u0006t"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/view/LyricsCardSelectionFragment;", "Landroidx/fragment/app/Fragment;", "", "hideActionBar", "loadDataAndBindViews", "", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsShareCardStyle;", "lyricsShareCardStyles", "initLyricsCards", "bindDestinationViewItems", "initCancelButton", "Lcom/amazon/music/share/menu/destinations/ShareDestination;", "destination", "onShareDestinationClickListener", "Landroid/view/View;", "getCurrentlyFocusedView", "view", "Landroid/graphics/Bitmap;", "createBitmapFromView", "coverBitmap", "addRoundedCorners", "Lcom/amazon/music/share/menu/ShareableContent;", "createShareableContent", "sendLyricsPresetShareMetrics", "Landroid/content/Context;", "context", "initDestinations", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "onResume", "onDestroy", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "onCreateView", "onViewCreated", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "lyricsSharingDataModel", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "getLyricsSharingDataModel", "()Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "setLyricsSharingDataModel", "(Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;)V", "", "initialPosition", "I", "getInitialPosition", "()I", "Lcom/amazon/music/share/menu/providers/StyleSheetProvider;", "styleSheetProvider", "Lcom/amazon/music/share/menu/providers/StyleSheetProvider;", "getStyleSheetProvider", "()Lcom/amazon/music/share/menu/providers/StyleSheetProvider;", "setStyleSheetProvider", "(Lcom/amazon/music/share/menu/providers/StyleSheetProvider;)V", "Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "socialMetadataProvider", "Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "getSocialMetadataProvider", "()Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "setSocialMetadataProvider", "(Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;)V", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "environmentProvider", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "getEnvironmentProvider", "()Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "setEnvironmentProvider", "(Lcom/amazon/music/share/menu/providers/EnvironmentProvider;)V", "rootView", "Landroid/view/View;", "Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricsCardAdapter;", "adapter", "Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricsCardAdapter;", "", "destinations", "Ljava/util/List;", "Lcom/amazon/mp3/view/lyrics/sharing/viewModel/LyricsCardSelectionViewModel;", "lyricsCardSelectionViewModel$delegate", "Lkotlin/Lazy;", "getLyricsCardSelectionViewModel", "()Lcom/amazon/mp3/view/lyrics/sharing/viewModel/LyricsCardSelectionViewModel;", "lyricsCardSelectionViewModel", "Lcom/amazon/ui/foundations/views/BaseButton;", "getCancelBtn", "()Lcom/amazon/ui/foundations/views/BaseButton;", "cancelBtn", "Landroidx/viewpager2/widget/ViewPager2;", "getLyricsCardSelectionViewpager", "()Landroidx/viewpager2/widget/ViewPager2;", "lyricsCardSelectionViewpager", "Landroid/widget/ImageView;", "getFragmentBackground", "()Landroid/widget/ImageView;", "fragmentBackground", "Landroid/widget/LinearLayout;", "getHorizontalShareDestinationView", "()Landroid/widget/LinearLayout;", "horizontalShareDestinationView", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStylesheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "stylesheet", "Lcom/amazon/music/media/playback/MediaItem;", "getMediaItem", "()Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "getFirstLyricsLineIndex", "firstLyricsLineIndex", "getNumLyricsLines", "numLyricsLines", "<init>", "(Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;I)V", "DestinationItemViewHolder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsCardSelectionFragment extends Fragment {
    private LyricsCardAdapter adapter;
    private final List<ShareDestination> destinations;
    public EnvironmentProvider environmentProvider;
    private final int initialPosition;

    /* JADX INFO: renamed from: lyricsCardSelectionViewModel$delegate, reason: from kotlin metadata */
    private final Lazy lyricsCardSelectionViewModel;
    private LyricsSharingDataModel lyricsSharingDataModel;
    private View rootView;
    public SocialMetadataProvider socialMetadataProvider;
    public StyleSheetProvider styleSheetProvider;

    public LyricsCardSelectionFragment(LyricsSharingDataModel lyricsSharingDataModel, int i) {
        Intrinsics.checkNotNullParameter(lyricsSharingDataModel, "lyricsSharingDataModel");
        this.lyricsSharingDataModel = lyricsSharingDataModel;
        this.initialPosition = i;
        this.destinations = new ArrayList();
        this.lyricsCardSelectionViewModel = LazyKt__LazyJVMKt.lazy(new Function0<LyricsCardSelectionViewModel>() { // from class: com.amazon.mp3.view.lyrics.sharing.view.LyricsCardSelectionFragment$lyricsCardSelectionViewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LyricsCardSelectionViewModel invoke() {
                LyricsCardSelectionFragment lyricsCardSelectionFragment = this.this$0;
                return (LyricsCardSelectionViewModel) new ViewModelProvider(lyricsCardSelectionFragment, new LyricsCardSelectionViewModelFactory(lyricsCardSelectionFragment.getLyricsSharingDataModel())).get(LyricsCardSelectionViewModel.class);
            }
        });
    }

    public final int getInitialPosition() {
        return this.initialPosition;
    }

    public final LyricsSharingDataModel getLyricsSharingDataModel() {
        return this.lyricsSharingDataModel;
    }

    public final StyleSheetProvider getStyleSheetProvider() {
        StyleSheetProvider styleSheetProvider = this.styleSheetProvider;
        if (styleSheetProvider != null) {
            return styleSheetProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("styleSheetProvider");
        return null;
    }

    public final SocialMetadataProvider getSocialMetadataProvider() {
        SocialMetadataProvider socialMetadataProvider = this.socialMetadataProvider;
        if (socialMetadataProvider != null) {
            return socialMetadataProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("socialMetadataProvider");
        return null;
    }

    public final EnvironmentProvider getEnvironmentProvider() {
        EnvironmentProvider environmentProvider = this.environmentProvider;
        if (environmentProvider != null) {
            return environmentProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("environmentProvider");
        return null;
    }

    private final BaseButton getCancelBtn() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view = null;
        }
        View viewFindViewById = view.findViewById(R.id.lyrics_cancel_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.lyrics_cancel_button)");
        return (BaseButton) viewFindViewById;
    }

    private final ViewPager2 getLyricsCardSelectionViewpager() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view = null;
        }
        View viewFindViewById = view.findViewById(R.id.card_slider);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.card_slider)");
        return (ViewPager2) viewFindViewById;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageView getFragmentBackground() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view = null;
        }
        View viewFindViewById = view.findViewById(R.id.fragment_background);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.fragment_background)");
        return (ImageView) viewFindViewById;
    }

    private final LinearLayout getHorizontalShareDestinationView() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view = null;
        }
        View viewFindViewById = view.findViewById(R.id.bottom_bar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.bottom_bar)");
        return (LinearLayout) viewFindViewById;
    }

    private final StyleSheet getStylesheet() {
        return getStyleSheetProvider().getStyleSheet();
    }

    private final MediaItem getMediaItem() {
        return this.lyricsSharingDataModel.getMediaItem();
    }

    private final int getFirstLyricsLineIndex() {
        return this.lyricsSharingDataModel.getStartIndexOfLyricsSelected();
    }

    private final int getNumLyricsLines() {
        return (this.lyricsSharingDataModel.getEndIndexOfLyricsSelected() - getFirstLyricsLineIndex()) + 1;
    }

    private final LyricsCardSelectionViewModel getLyricsCardSelectionViewModel() {
        return (LyricsCardSelectionViewModel) this.lyricsCardSelectionViewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        FragmentActivity activity;
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        FragmentActivity activity2 = getActivity();
        boolean z = false;
        if (activity2 != null && activity2.getRequestedOrientation() == 5) {
            z = true;
        }
        if (z || (activity = getActivity()) == null) {
            return;
        }
        activity.setRequestedOrientation(5);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        hideActionBar();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.setRequestedOrientation(-1);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_lyrics_card_selection, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…ection, container, false)");
        this.rootView = viewInflate;
        if (viewInflate != null) {
            return viewInflate;
        }
        Intrinsics.throwUninitializedPropertyAccessException("rootView");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();
        if (context != null) {
            initDestinations(context);
        }
        loadDataAndBindViews();
        MediaItem mediaItem = getMediaItem();
        if (mediaItem == null) {
            return;
        }
        LyricsSharingMetricsSender lyricsSharingMetricsSender = LyricsSharingMetricsSender.INSTANCE;
        lyricsSharingMetricsSender.sendLyricsPresetSelectorPageViewMetrics(mediaItem, getFirstLyricsLineIndex(), getNumLyricsLines());
        lyricsSharingMetricsSender.sendUIPageViewMetrics(PageType.LYRICS_PRESET_SELECTOR);
    }

    private final void hideActionBar() {
        if (getActivity() instanceof NowPlayingFragmentActivity) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.playback.activity.NowPlayingFragmentActivity");
            }
            ((NowPlayingFragmentActivity) activity).hideActionBar();
        }
    }

    private final void loadDataAndBindViews() {
        LyricsSharingDataRepository.INSTANCE.getBackgroundBitmapsLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.view.lyrics.sharing.view.LyricsCardSelectionFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LyricsCardSelectionFragment.m1716loadDataAndBindViews$lambda2(this.f$0, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadDataAndBindViews$lambda-2, reason: not valid java name */
    public static final void m1716loadDataAndBindViews$lambda2(LyricsCardSelectionFragment this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LyricsCardSelectionViewModel lyricsCardSelectionViewModel = this$0.getLyricsCardSelectionViewModel();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.initLyricsCards(lyricsCardSelectionViewModel.getBackgroundStyles(it));
        this$0.initCancelButton();
        this$0.bindDestinationViewItems();
    }

    private final void initLyricsCards(final List<LyricsShareCardStyle> lyricsShareCardStyles) {
        getLyricsCardSelectionViewpager().setOffscreenPageLimit(3);
        final int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.spacing_16);
        final int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.spacer_32);
        final float dimension = getResources().getDimension(R.dimen.spacer_8);
        final float dimension2 = getResources().getDimension(R.dimen.spacer_12);
        final float f = 0.8f;
        getLyricsCardSelectionViewpager().setPageTransformer(new ViewPager2.PageTransformer() { // from class: com.amazon.mp3.view.lyrics.sharing.view.LyricsCardSelectionFragment$$ExternalSyntheticLambda2
            @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
            public final void transformPage(View view, float f2) {
                LyricsCardSelectionFragment.m1715initLyricsCards$lambda3(dimensionPixelOffset2, dimensionPixelOffset, dimension, f, dimension2, view, f2);
            }
        });
        this.adapter = new LyricsCardAdapter(this.lyricsSharingDataModel, lyricsShareCardStyles, getLyricsCardSelectionViewpager(), getStylesheet());
        ViewPager2 lyricsCardSelectionViewpager = getLyricsCardSelectionViewpager();
        LyricsCardAdapter lyricsCardAdapter = this.adapter;
        if (lyricsCardAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            lyricsCardAdapter = null;
        }
        lyricsCardSelectionViewpager.setAdapter(lyricsCardAdapter);
        getLyricsCardSelectionViewpager().setCurrentItem(this.initialPosition, false);
        getLyricsCardSelectionViewpager().registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.amazon.mp3.view.lyrics.sharing.view.LyricsCardSelectionFragment.initLyricsCards.2
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                Integer backgroundDrawable = lyricsShareCardStyles.get(position).getLyricsShareCardBackground().getBackgroundDrawable();
                if (backgroundDrawable != null) {
                    LyricsCardSelectionFragment lyricsCardSelectionFragment = this;
                    lyricsCardSelectionFragment.getFragmentBackground().setImageResource(backgroundDrawable.intValue());
                }
                Bitmap backgroundBitmap = lyricsShareCardStyles.get(position).getLyricsShareCardBackground().getBackgroundBitmap();
                if (backgroundBitmap != null) {
                    this.getFragmentBackground().setImageBitmap(backgroundBitmap);
                }
                if (lyricsShareCardStyles.get(position).getLyricsShareCardBackground().getIsBlurAlbumArtBackground()) {
                    this.getFragmentBackground().setForeground(ResourcesCompat.getDrawable(this.requireContext().getResources(), R.drawable.stage_background_art_gradient, null));
                }
            }
        });
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Context context = getContext();
        if (context == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.Activity");
        }
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        LayoutParamUtils.INSTANCE.setLayoutMargins(getLyricsCardSelectionViewpager(), -1, (((displayMetrics.widthPixels - (getResources().getDimensionPixelSize(R.dimen.spacer_48) * 2)) * 16) / 9) + ((int) (2 * getResources().getDimension(R.dimen.spacer_40))), (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initLyricsCards$lambda-3, reason: not valid java name */
    public static final void m1715initLyricsCards$lambda3(int i, int i2, float f, float f2, float f3, View page, float f4) {
        Intrinsics.checkNotNullParameter(page, "page");
        page.setTranslationX((-((i * 2) + i2)) * f4);
        float fAbs = Math.abs(f4);
        if (fAbs >= 1.0f) {
            page.setElevation(f);
            page.setScaleY(f2);
        } else {
            float f5 = 1;
            page.setElevation(((f5 - fAbs) * f3) + f);
            page.setScaleY(((f2 - f5) * fAbs) + f5);
        }
    }

    private final void bindDestinationViewItems() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        List<ShareDestination> list = this.destinations;
        ArrayList<ShareDestination> arrayList = new ArrayList();
        for (Object obj : list) {
            if (((ShareDestination) obj).isAvailable()) {
                arrayList.add(obj);
            }
        }
        for (final ShareDestination shareDestination : arrayList) {
            View viewInflate = layoutInflaterFrom.inflate(R.layout.horizontal_share_sheet, (ViewGroup) getHorizontalShareDestinationView(), false);
            Intrinsics.checkNotNullExpressionValue(viewInflate, "layoutInflater.inflate(\n…lse\n                    )");
            DestinationItemViewHolder destinationItemViewHolder = new DestinationItemViewHolder(viewInflate);
            destinationItemViewHolder.getView().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.lyrics.sharing.view.LyricsCardSelectionFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LyricsCardSelectionFragment.m1713bindDestinationViewItems$lambda6$lambda5(this.f$0, shareDestination, view);
                }
            });
            destinationItemViewHolder.getIcon().setImageResource(shareDestination.getIconResId());
            destinationItemViewHolder.getText().setText(shareDestination.getNameResId());
            getHorizontalShareDestinationView().addView(destinationItemViewHolder.getView());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindDestinationViewItems$lambda-6$lambda-5, reason: not valid java name */
    public static final void m1713bindDestinationViewItems$lambda6$lambda5(LyricsCardSelectionFragment this$0, ShareDestination destination, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(destination, "$destination");
        this$0.onShareDestinationClickListener(destination);
    }

    /* JADX INFO: compiled from: LyricsCardSelectionFragment.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/view/LyricsCardSelectionFragment$DestinationItemViewHolder;", "", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "icon", "Landroid/widget/ImageView;", "getIcon", "()Landroid/widget/ImageView;", "text", "Landroid/widget/TextView;", "getText", "()Landroid/widget/TextView;", "getView", "()Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class DestinationItemViewHolder {
        private final ImageView icon;
        private final TextView text;
        private final View view;

        public DestinationItemViewHolder(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
            View viewFindViewById = view.findViewById(R.id.share_icon);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.share_icon)");
            this.icon = (ImageView) viewFindViewById;
            View viewFindViewById2 = view.findViewById(R.id.share_destination_name);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.share_destination_name)");
            this.text = (TextView) viewFindViewById2;
        }

        public final View getView() {
            return this.view;
        }

        public final ImageView getIcon() {
            return this.icon;
        }

        public final TextView getText() {
            return this.text;
        }
    }

    private final void initCancelButton() {
        Drawable drawable;
        ButtonSize iconSize = getStylesheet().getIconSize(IconSizeKey.SMALL);
        ButtonSize buttonSizeCopy = iconSize == null ? null : iconSize.copy((4026 & 1) != 0 ? iconSize.height : null, (4026 & 2) != 0 ? iconSize.width : null, (4026 & 4) != 0 ? iconSize.paddingHorizontal : 0, (4026 & 8) != 0 ? iconSize.paddingVertical : null, (4026 & 16) != 0 ? iconSize.minWidth : null, (4026 & 32) != 0 ? iconSize.cornerRadius : null, (4026 & 64) != 0 ? iconSize.textSize : null, (4026 & 128) != 0 ? iconSize.textTypeface : null, (4026 & 256) != 0 ? iconSize.textAllCaps : null, (4026 & 512) != 0 ? iconSize.iconSize : Integer.valueOf((int) getResources().getDimension(R.dimen.spacer_8)), (4026 & 1024) != 0 ? iconSize.iconPaddingLeft : null, (4026 & 2048) != 0 ? iconSize.iconPaddingRight : null);
        if (buttonSizeCopy == null || (drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.back_arrow, null)) == null) {
            return;
        }
        ButtonStyle iconStyle = getStylesheet().getIconStyle(IconStyleKey.GLASS);
        if (iconStyle != null) {
            new BaseButton.StyleBuilder().withSize(buttonSizeCopy).withStyle(iconStyle).withIcon(drawable).applyStyle(getCancelBtn());
        }
        getCancelBtn().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.lyrics.sharing.view.LyricsCardSelectionFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LyricsCardSelectionFragment.m1714initCancelButton$lambda9(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initCancelButton$lambda-9, reason: not valid java name */
    public static final void m1714initCancelButton$lambda9(LyricsCardSelectionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MediaItem mediaItem = this$0.getMediaItem();
        if (mediaItem != null) {
            LyricsSharingMetricsSender.sendUIClickMetrics$default(LyricsSharingMetricsSender.INSTANCE, mediaItem, ActionType.GO_BACK, PageType.LYRICS_PRESET_SELECTOR, null, 8, null);
        }
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        activity.onBackPressed();
    }

    private final void onShareDestinationClickListener(ShareDestination destination) {
        ShareableContent shareableContentCreateShareableContent;
        sendLyricsPresetShareMetrics(destination);
        View currentlyFocusedView = getCurrentlyFocusedView();
        if (currentlyFocusedView == null || (shareableContentCreateShareableContent = createShareableContent()) == null) {
            return;
        }
        shareableContentCreateShareableContent.setImageBitmap(addRoundedCorners(createBitmapFromView(currentlyFocusedView)));
        shareableContentCreateShareableContent.setBackgroundBitmap(createBitmapFromView(getFragmentBackground()));
        destination.share(shareableContentCreateShareableContent);
    }

    private final View getCurrentlyFocusedView() {
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition;
        int currentItem = getLyricsCardSelectionViewpager().getCurrentItem();
        RecyclerView recyclerView = (RecyclerView) getLyricsCardSelectionViewpager().getChildAt(0);
        if (recyclerView == null || (viewHolderFindViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(currentItem)) == null) {
            return null;
        }
        return viewHolderFindViewHolderForAdapterPosition.itemView;
    }

    private final Bitmap createBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return bitmap;
    }

    private final Bitmap addRoundedCorners(Bitmap coverBitmap) {
        Bitmap bitmap = Bitmap.createBitmap(coverBitmap.getWidth(), coverBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        float dimension = getResources().getDimension(R.dimen.lyrics_card_corner_radius_20);
        RectF rectF = new RectF(0.0f, 0.0f, coverBitmap.getWidth(), coverBitmap.getHeight());
        Path path = new Path();
        path.addRoundRect(rectF, dimension, dimension, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawRoundRect(rectF, dimension, dimension, new Paint(0));
        canvas.drawBitmap(coverBitmap, (Rect) null, rectF, (Paint) null);
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return bitmap;
    }

    private final ShareableContent createShareableContent() {
        MediaItemId mediaItemId;
        LyricsSharingDeeplinkGenerator lyricsSharingDeeplinkGenerator = LyricsSharingDeeplinkGenerator.INSTANCE;
        Context context = getContext();
        MediaItem mediaItem = this.lyricsSharingDataModel.getMediaItem();
        String id = null;
        if (mediaItem == null) {
            return null;
        }
        String strGenerateDeeplink = lyricsSharingDeeplinkGenerator.generateDeeplink(context, mediaItem);
        ShareableContent shareableContent = new ShareableContent();
        shareableContent.setTitle(getLyricsSharingDataModel().getTitle());
        MediaItem mediaItem2 = getLyricsSharingDataModel().getMediaItem();
        if (mediaItem2 != null && (mediaItemId = mediaItem2.getMediaItemId(MediaItemId.Type.ASIN)) != null) {
            id = mediaItemId.getId();
        }
        shareableContent.setEntityId(id);
        shareableContent.setEntityIdType(EntityIdType.ASIN);
        shareableContent.setEntityType(EntityType.LYRICS);
        shareableContent.setUrl(strGenerateDeeplink);
        return shareableContent;
    }

    private final void sendLyricsPresetShareMetrics(ShareDestination destination) {
        MediaItem mediaItem = getMediaItem();
        if (mediaItem == null) {
            return;
        }
        LyricsSharingMetricsSender lyricsSharingMetricsSender = LyricsSharingMetricsSender.INSTANCE;
        String metricsValue = destination.getActionType().getMetricsValue();
        Intrinsics.checkNotNullExpressionValue(metricsValue, "destination.actionType.metricValue");
        lyricsSharingMetricsSender.sendLyricsPresetShareMetrics(mediaItem, metricsValue, getFirstLyricsLineIndex(), getNumLyricsLines(), getLyricsCardSelectionViewpager().getCurrentItem());
    }

    private final void initDestinations(Context context) {
        CollectionsKt__MutableCollectionsKt.addAll(this.destinations, new ShareDestination[]{new InstagramStoryShareDestination(context, getSocialMetadataProvider(), getEnvironmentProvider(), getStylesheet()), new FacebookStoryShareDestination(context, getSocialMetadataProvider(), getEnvironmentProvider(), getStylesheet()), new MessagesShareDestination(context)});
    }
}
