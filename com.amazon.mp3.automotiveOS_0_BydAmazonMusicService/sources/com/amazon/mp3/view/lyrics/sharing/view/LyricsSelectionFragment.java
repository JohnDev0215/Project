package com.amazon.mp3.view.lyrics.sharing.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dagger.DaggerSubcomponents;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsLine;
import com.amazon.mp3.playback.OnLyricsLineClickListener;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.view.lyrics.LyricsAdapter;
import com.amazon.mp3.playback.view.lyrics.LyricsItemDecoration;
import com.amazon.mp3.playback.view.lyrics.LyricsLayoutManager;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingMetricsSender;
import com.amazon.mp3.view.lyrics.sharing.viewModel.LyricsSelectionViewModel;
import com.amazon.mp3.view.lyrics.sharing.viewModel.LyricsSelectionViewModelFactory;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.styles.StatefulStyle;
import com.amazon.ui.foundations.utils.ColorUtil;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import io.reactivex.rxjava3.subjects.PublishSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;

/* JADX INFO: compiled from: LyricsSelectionFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 X2\u00020\u00012\u00020\u0002:\u0001XB\u001f\u0012\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0\"\u0012\b\b\u0002\u0010%\u001a\u00020\n¢\u0006\u0004\bV\u0010WJ\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\nH\u0002J\b\u0010\u000e\u001a\u00020\u0005H\u0002J\u0012\u0010\u0011\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u0005H\u0016J\b\u0010\u0013\u001a\u00020\u0005H\u0016J$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016J\u001a\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00182\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016J$\u0010!\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0016R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0017\u0010%\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\"\u0010*\u001a\u00020)8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\"\u00100\u001a\u00020\u00188\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u0018\u00107\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u00108R\u0016\u0010:\u001a\u0002098\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u0010;R\"\u0010=\u001a\u00020<8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u0014\u0010F\u001a\u00020C8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bD\u0010ER\u0014\u0010I\u001a\u00020\u001f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0014\u0010K\u001a\u00020\u001f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010HR\u0014\u0010O\u001a\u00020L8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bM\u0010NR\u0014\u0010Q\u001a\u00020L8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bP\u0010NR\u0014\u0010S\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bR\u0010(R\u0014\u0010U\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bT\u0010(¨\u0006Y"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/view/LyricsSelectionFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/amazon/mp3/playback/OnLyricsLineClickListener;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "initLyricsListRecyclerView", "initSelectionHeader", "initExitLyricsSharingButton", "initNextButton", "", "getNextButtonWidth", "newCountOfSelectedLines", "updateSelectionLineCounter", "hideActionBar", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "onResume", "onDestroyView", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "view", "onViewCreated", "Lcom/amazon/mp3/lyrics/item/LyricsLine;", "lyricsLine", "position", "Landroid/widget/TextView;", "lyricsTextView", "onLyricsLineClicked", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "openLyricsCardSelectionFragmentSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "cardPosition", "I", "getCardPosition", "()I", "Lcom/amazon/mp3/view/lyrics/sharing/viewModel/LyricsSelectionViewModel;", "lyricsSelectionViewModel", "Lcom/amazon/mp3/view/lyrics/sharing/viewModel/LyricsSelectionViewModel;", "getLyricsSelectionViewModel", "()Lcom/amazon/mp3/view/lyrics/sharing/viewModel/LyricsSelectionViewModel;", "setLyricsSelectionViewModel", "(Lcom/amazon/mp3/view/lyrics/sharing/viewModel/LyricsSelectionViewModel;)V", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "Lcom/amazon/mp3/playback/view/lyrics/LyricsAdapter;", "lyricsAdapter", "Lcom/amazon/mp3/playback/view/lyrics/LyricsAdapter;", "", "isLyricsRecyclerViewPositionInitialized", "Z", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "lyricsSharingDataModel", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "getLyricsSharingDataModel", "()Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "setLyricsSharingDataModel", "(Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;)V", "Landroidx/recyclerview/widget/RecyclerView;", "getLyricsListRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "lyricsListRecyclerView", "getSelectLinesHeader", "()Landroid/widget/TextView;", "selectLinesHeader", "getSelectedLinesCounter", "selectedLinesCounter", "Lcom/amazon/ui/foundations/views/BaseButton;", "getNextButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "nextButton", "getExitLyricsSharingButton", "exitLyricsSharingButton", "getFirstLyricsLineIndex", "firstLyricsLineIndex", "getNumLyricsLines", "numLyricsLines", "<init>", "(Lio/reactivex/rxjava3/subjects/PublishSubject;I)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsSelectionFragment extends Fragment implements OnLyricsLineClickListener {
    private static final String TAG = LyricsSelectionFragment.class.getSimpleName();
    private final int cardPosition;
    private boolean isLyricsRecyclerViewPositionInitialized;
    private LyricsAdapter lyricsAdapter;
    public LyricsSelectionViewModel lyricsSelectionViewModel;
    public LyricsSharingDataModel lyricsSharingDataModel;
    private final PublishSubject<Integer> openLyricsCardSelectionFragmentSubject;
    public View rootView;

    public LyricsSelectionFragment(PublishSubject<Integer> openLyricsCardSelectionFragmentSubject, int i) {
        Intrinsics.checkNotNullParameter(openLyricsCardSelectionFragmentSubject, "openLyricsCardSelectionFragmentSubject");
        this.openLyricsCardSelectionFragmentSubject = openLyricsCardSelectionFragmentSubject;
        this.cardPosition = i;
    }

    public final int getCardPosition() {
        return this.cardPosition;
    }

    public final LyricsSelectionViewModel getLyricsSelectionViewModel() {
        LyricsSelectionViewModel lyricsSelectionViewModel = this.lyricsSelectionViewModel;
        if (lyricsSelectionViewModel != null) {
            return lyricsSelectionViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("lyricsSelectionViewModel");
        return null;
    }

    public final void setLyricsSelectionViewModel(LyricsSelectionViewModel lyricsSelectionViewModel) {
        Intrinsics.checkNotNullParameter(lyricsSelectionViewModel, "<set-?>");
        this.lyricsSelectionViewModel = lyricsSelectionViewModel;
    }

    public final View getRootView() {
        View view = this.rootView;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("rootView");
        return null;
    }

    public final void setRootView(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.rootView = view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RecyclerView getLyricsListRecyclerView() {
        View viewFindViewById = getRootView().findViewById(R.id.LyricsListView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.LyricsListView)");
        return (RecyclerView) viewFindViewById;
    }

    private final TextView getSelectLinesHeader() {
        View viewFindViewById = getRootView().findViewById(R.id.SelectLines);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.SelectLines)");
        return (TextView) viewFindViewById;
    }

    private final TextView getSelectedLinesCounter() {
        View viewFindViewById = getRootView().findViewById(R.id.SelectCounter);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.SelectCounter)");
        return (TextView) viewFindViewById;
    }

    private final BaseButton getNextButton() {
        View viewFindViewById = getRootView().findViewById(R.id.NextButton);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.NextButton)");
        return (BaseButton) viewFindViewById;
    }

    private final BaseButton getExitLyricsSharingButton() {
        View viewFindViewById = getRootView().findViewById(R.id.ExitLyricsSharing);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.ExitLyricsSharing)");
        return (BaseButton) viewFindViewById;
    }

    private final int getFirstLyricsLineIndex() {
        return getLyricsSharingDataModel().getStartIndexOfLyricsSelected();
    }

    private final int getNumLyricsLines() {
        return (getLyricsSharingDataModel().getEndIndexOfLyricsSelected() - getFirstLyricsLineIndex()) + 1;
    }

    public final LyricsSharingDataModel getLyricsSharingDataModel() {
        LyricsSharingDataModel lyricsSharingDataModel = this.lyricsSharingDataModel;
        if (lyricsSharingDataModel != null) {
            return lyricsSharingDataModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("lyricsSharingDataModel");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        FragmentActivity activity;
        super.onCreate(savedInstanceState);
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
    public void onDestroyView() {
        super.onDestroyView();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.setRequestedOrientation(-1);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        DaggerSubcomponents.INSTANCE.getNowPlayingComponent().inject(this);
        View viewInflate = inflater.inflate(R.layout.fragment_lyrics_selection, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…ection, container, false)");
        setRootView(viewInflate);
        return getRootView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        RecyclerView lyricsListRecyclerView = getLyricsListRecyclerView();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        lyricsListRecyclerView.setLayoutManager(new LyricsLayoutManager(contextRequireContext));
        getLyricsListRecyclerView().addItemDecoration(new LyricsItemDecoration());
        MediaItem mediaItem = getLyricsSharingDataModel().getMediaItem();
        if (mediaItem != null) {
            LyricsSharingMetricsSender lyricsSharingMetricsSender = LyricsSharingMetricsSender.INSTANCE;
            lyricsSharingMetricsSender.sendLyricsLineSelectionPageViewMetrics(mediaItem);
            lyricsSharingMetricsSender.sendUIPageViewMetrics(PageType.LYRICS_LINE_SELECTION);
        }
        StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.view.lyrics.sharing.view.LyricsSelectionFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LyricsSelectionFragment.m1721onViewCreated$lambda4(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-4, reason: not valid java name */
    public static final void m1721onViewCreated$lambda4(final LyricsSelectionFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (styleSheet == null) {
            return;
        }
        this$0.lyricsAdapter = new LyricsAdapter(this$0.getContext(), null, styleSheet, true, true);
        this$0.getLyricsListRecyclerView().setAdapter(this$0.lyricsAdapter);
        LyricsAdapter lyricsAdapter = this$0.lyricsAdapter;
        if (lyricsAdapter != null) {
            lyricsAdapter.setOnLyricsLineClickListener(this$0);
        }
        this$0.setLyricsSelectionViewModel((LyricsSelectionViewModel) new ViewModelProvider(this$0, new LyricsSelectionViewModelFactory(this$0.getLyricsSharingDataModel())).get(LyricsSelectionViewModel.class));
        this$0.getLyricsSelectionViewModel().getLyrics().observe(this$0.getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.view.lyrics.sharing.view.LyricsSelectionFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LyricsSelectionFragment.m1722onViewCreated$lambda4$lambda3$lambda1(this.f$0, (Lyrics) obj);
            }
        });
        this$0.getLyricsSelectionViewModel().getSelectedLinesCounter().observe(this$0.getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.view.lyrics.sharing.view.LyricsSelectionFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LyricsSelectionFragment.m1723onViewCreated$lambda4$lambda3$lambda2(this.f$0, (Integer) obj);
            }
        });
        this$0.getLyricsSelectionViewModel().setTheLatestLyrics();
        this$0.initLyricsListRecyclerView(styleSheet);
        this$0.initSelectionHeader(styleSheet);
        this$0.initExitLyricsSharingButton(styleSheet);
        this$0.initNextButton(styleSheet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-4$lambda-3$lambda-1, reason: not valid java name */
    public static final void m1722onViewCreated$lambda4$lambda3$lambda1(LyricsSelectionFragment this$0, Lyrics lyrics) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LyricsAdapter lyricsAdapter = this$0.lyricsAdapter;
        if (lyricsAdapter != null) {
            lyricsAdapter.updateLyrics(lyrics);
        }
        if (lyrics == null || this$0.isLyricsRecyclerViewPositionInitialized) {
            return;
        }
        this$0.isLyricsRecyclerViewPositionInitialized = true;
        this$0.getLyricsListRecyclerView().scrollToPosition(this$0.getLyricsSharingDataModel().getLastLineNumberInFocus());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-4$lambda-3$lambda-2, reason: not valid java name */
    public static final void m1723onViewCreated$lambda4$lambda3$lambda2(LyricsSelectionFragment this$0, Integer newCountOfSelectedLines) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(newCountOfSelectedLines, "newCountOfSelectedLines");
        this$0.updateSelectionLineCounter(newCountOfSelectedLines.intValue());
    }

    private final void initLyricsListRecyclerView(StyleSheet styleSheet) {
        GridSize gridSize;
        Context context = getContext();
        if (context != null && (gridSize = styleSheet.getGridSize(context)) != null) {
            int marginSpace = gridSize.getMarginSpace() + gridSize.getColumnSpace();
            LayoutParamUtils.INSTANCE.setLayoutMargins(getLyricsListRecyclerView(), -1, -1, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : Integer.valueOf(marginSpace), (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : Integer.valueOf(marginSpace), (240 & 128) != 0 ? null : null);
        }
        getLyricsListRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.mp3.view.lyrics.sharing.view.LyricsSelectionFragment.initLyricsListRecyclerView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                super.onScrollStateChanged(recyclerView, newState);
                LyricsSharingDataModel lyricsSharingDataModel = LyricsSelectionFragment.this.getLyricsSharingDataModel();
                RecyclerView.LayoutManager layoutManager = LyricsSelectionFragment.this.getLyricsListRecyclerView().getLayoutManager();
                if (layoutManager == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.playback.view.lyrics.LyricsLayoutManager");
                }
                lyricsSharingDataModel.setLastLineNumberInFocus(((LyricsLayoutManager) layoutManager).findFirstVisibleItemPosition());
            }
        });
    }

    private final void initSelectionHeader(StyleSheet styleSheet) {
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.INDEX);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getSelectedLinesCounter(), fontStyle);
        }
        getSelectedLinesCounter().setAllCaps(false);
        FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle2 == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getSelectLinesHeader(), fontStyle2);
    }

    private final void initExitLyricsSharingButton(StyleSheet styleSheet) {
        BaseButton.StyleBuilder styleBuilderWithIcon;
        getExitLyricsSharingButton().setVisibility(0);
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
        Context context = getContext();
        if (context == null) {
            return;
        }
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_action_cancel);
        if (drawable != null && iconBuilder != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) != null) {
            styleBuilderWithIcon.applyStyle(getExitLyricsSharingButton());
        }
        getExitLyricsSharingButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.lyrics.sharing.view.LyricsSelectionFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LyricsSelectionFragment.m1719initExitLyricsSharingButton$lambda12$lambda11(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initExitLyricsSharingButton$lambda-12$lambda-11, reason: not valid java name */
    public static final void m1719initExitLyricsSharingButton$lambda12$lambda11(LyricsSelectionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MediaItem mediaItem = this$0.getLyricsSharingDataModel().getMediaItem();
        if (mediaItem != null) {
            LyricsSharingMetricsSender.sendUIClickMetrics$default(LyricsSharingMetricsSender.INSTANCE, mediaItem, ActionType.GO_BACK, PageType.LYRICS_LINE_SELECTION, null, 8, null);
        }
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        activity.onBackPressed();
    }

    private final void initNextButton(StyleSheet styleSheet) {
        getNextButton().setVisibility(0);
        ViewGroup.LayoutParams layoutParams = getNextButton().getLayoutParams();
        layoutParams.width = getNextButtonWidth();
        getNextButton().setLayoutParams(layoutParams);
        ButtonSizeKey buttonSizeKey = ButtonSizeKey.LARGE;
        ButtonStyleKey buttonStyleKey = ButtonStyleKey.GLASS;
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(buttonSizeKey, buttonStyleKey);
        ButtonStyle iconStyle = styleSheet.getIconStyle(IconStyleKey.SECONDARY_GLASS);
        Context context = getContext();
        ButtonStyle buttonStyleCopy$default = null;
        if (context != null) {
            int color = ContextCompat.getColor(context, R.color.transparent);
            int color2 = ContextCompat.getColor(context, R.color.primary);
            int color3 = ContextCompat.getColor(context, R.color.primary_glass_3);
            int color4 = ContextCompat.getColor(context, R.color.primary_glass_2);
            StatefulStyle statefulStyle = new StatefulStyle(Integer.valueOf(color2), Integer.valueOf(color2), Integer.valueOf(color2), Integer.valueOf(color3), Integer.valueOf(color4), Integer.valueOf(ContextCompat.getColor(context, R.color.primary_glass_1)), Integer.valueOf(color3), Integer.valueOf(color4));
            ButtonStyle buttonStyle = styleSheet.getButtonStyle(buttonStyleKey);
            if (buttonStyle != null) {
                buttonStyleCopy$default = ButtonStyle.copy$default(buttonStyle, statefulStyle, null, ColorUtil.INSTANCE.getColorStateList(color, Integer.valueOf(color), Integer.valueOf(color2), Integer.valueOf(color)), Integer.valueOf((int) getResources().getDimension(R.dimen.spacer_2)), 2, null);
            }
        }
        if (buttonBuilder != null) {
            String string = getResources().getString(R.string.lyrics_selection_page_next_button_text);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…on_page_next_button_text)");
            buttonBuilder.withText(string);
            if (iconStyle != null) {
                buttonBuilder.withActiveStyle(iconStyle);
            }
            if (buttonStyleCopy$default != null) {
                buttonBuilder.withStyle(buttonStyleCopy$default);
            }
            buttonBuilder.applyStyle(getNextButton());
        }
        getNextButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.lyrics.sharing.view.LyricsSelectionFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LyricsSelectionFragment.m1720initNextButton$lambda18(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initNextButton$lambda-18, reason: not valid java name */
    public static final void m1720initNextButton$lambda18(LyricsSelectionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MediaItem mediaItem = this$0.getLyricsSharingDataModel().getMediaItem();
        if (mediaItem != null) {
            LyricsSharingMetricsSender lyricsSharingMetricsSender = LyricsSharingMetricsSender.INSTANCE;
            lyricsSharingMetricsSender.sendNextButtonClickMetrics(mediaItem, this$0.getFirstLyricsLineIndex(), this$0.getNumLyricsLines());
            LyricsSharingMetricsSender.sendUIClickMetrics$default(lyricsSharingMetricsSender, mediaItem, ActionType.LYRICS_SELECTION_NEXT_BUTTON_CLICK, PageType.LYRICS_LINE_SELECTION, null, 8, null);
        }
        this$0.openLyricsCardSelectionFragmentSubject.onNext(Integer.valueOf(this$0.cardPosition));
    }

    private final int getNextButtonWidth() {
        int dimension = (int) getResources().getDimension(R.dimen.lyrics_sharing_next_button_width);
        String string = getResources().getString(R.string.lyrics_selection_page_next_button_text);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…on_page_next_button_text)");
        float f = getResources().getDisplayMetrics().scaledDensity * 15;
        TextPaint textPaint = new TextPaint(1);
        textPaint.setTextSize(f);
        textPaint.setTypeface(Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_regular)));
        return Math.max((int) textPaint.measureText(string), dimension) + ((int) getResources().getDimension(R.dimen.spacer_48));
    }

    private final void updateSelectionLineCounter(int newCountOfSelectedLines) {
        if (newCountOfSelectedLines == 1) {
            getSelectedLinesCounter().setText(getString(R.string.line_selected, 1));
            getNextButton().setEnabled(true);
            return;
        }
        if (2 <= newCountOfSelectedLines && newCountOfSelectedLines < 4) {
            getSelectedLinesCounter().setText(getString(R.string.lines_selected, Integer.valueOf(newCountOfSelectedLines)));
            getNextButton().setEnabled(true);
        } else {
            getSelectedLinesCounter().setText(getResources().getString(R.string.default_no_lines_selected_text));
            getNextButton().setEnabled(false);
        }
    }

    @Override // com.amazon.mp3.playback.OnLyricsLineClickListener
    public void onLyricsLineClicked(LyricsLine lyricsLine, int position, TextView lyricsTextView) {
        getLyricsSelectionViewModel().onLyricsLineClicked(position);
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
}
