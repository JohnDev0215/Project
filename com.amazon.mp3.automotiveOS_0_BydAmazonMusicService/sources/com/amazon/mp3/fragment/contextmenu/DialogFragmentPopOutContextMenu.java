package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Space;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundEffectUtils;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundImageCondition;
import com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment;
import com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuSeeAllPlaylistsFragment;
import com.amazon.mp3.fragment.contextmenu.shoveler.ShovelerExtensionFunctionsKt;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.BlurStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.amazon.ui.foundations.utils.SpacerParser;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: compiled from: DialogFragmentPopOutContextMenu.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 U2\u00020\u00012\u00020\u0002:\u0001UB\u0007¢\u0006\u0004\bS\u0010TJ\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J&\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\b\u0010\u0014\u001a\u00020\u0003H\u0016J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\rH\u0016J\u0012\u0010\u001a\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010\u001d\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0016J \u0010!\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001b2\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001fH\u0016J\u0006\u0010\"\u001a\u00020\u0003J\u0006\u0010$\u001a\u00020#R\u0016\u0010&\u001a\u00020%8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010)\u001a\u00020(8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010/\u001a\u00020.8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b/\u00100R\u0018\u00102\u001a\u0004\u0018\u0001018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b2\u00103R\u001e\u00106\u001a\n\u0012\u0004\u0012\u000205\u0018\u0001048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0018\u00109\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u0018\u0010;\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010:R\u0018\u0010=\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u001c\u0010@\u001a\n ?*\u0004\u0018\u00010\u001b0\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010AR\"\u0010C\u001a\u00020B8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR8\u0010K\u001a&\u0012\f\u0012\n ?*\u0004\u0018\u00010J0J ?*\u0012\u0012\f\u0012\n ?*\u0004\u0018\u00010J0J\u0018\u00010I0I8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010LR\u0014\u0010N\u001a\u00020M8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010OR\u001c\u0010Q\u001a\n ?*\u0004\u0018\u00010P0P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010R¨\u0006V"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/DialogFragmentPopOutContextMenu;", "Landroidx/fragment/app/DialogFragment;", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuAddToPlaylistBaseFragment$ShovelerCallbacksListener;", "", "resizeDialog", "Landroid/graphics/Bitmap;", "bitmap", "Landroidx/core/graphics/drawable/RoundedBitmapDrawable;", "updateBitmapPosition", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "Landroid/view/View;", "onCreateView", "view", "onViewCreated", "onResume", "onPause", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "bundle", "navigateToSeeAllPage", "navigateToShovelerMenu", "", "error", "onAddToPlaylistError", "selectedPlaylistName", "Lkotlin/Function0;", "onToastClickListener", "onAddToPlaylistSuccess", "blurBackground", "", "isStage", "Landroid/widget/FrameLayout;", "menuContainer", "Landroid/widget/FrameLayout;", "Landroidx/cardview/widget/CardView;", "cardContainer", "Landroidx/cardview/widget/CardView;", "Landroidx/constraintlayout/widget/Guideline;", "topGuideline", "Landroidx/constraintlayout/widget/Guideline;", "Lcom/amazon/mp3/fragment/contextmenu/OverflowContextMenuViewModel;", "menuViewModel", "Lcom/amazon/mp3/fragment/contextmenu/OverflowContextMenuViewModel;", "Landroid/view/Menu;", "menu", "Landroid/view/Menu;", "Lrx/functions/Action1;", "Landroid/view/MenuItem;", "clickAction", "Lrx/functions/Action1;", "Lrx/Subscription;", "stylesheetSubscription", "Lrx/Subscription;", "blurSubscription", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Landroid/widget/Space;", "heightSpace", "Landroid/widget/Space;", "getHeightSpace", "()Landroid/widget/Space;", "setHeightSpace", "(Landroid/widget/Space;)V", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/mp3/fragment/contextmenu/StageOverflowMenuMoreButtons;", "overFlowMenuCloseObservable", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Lio/reactivex/rxjava3/disposables/Disposable;", "menuCloseSubscription", "Lio/reactivex/rxjava3/disposables/Disposable;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DialogFragmentPopOutContextMenu extends DialogFragment implements ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String TAG = DialogFragmentPopOutContextMenu.class.getSimpleName();
    private Subscription blurSubscription;
    private CardView cardContainer;
    private Action1<MenuItem> clickAction;
    public Space heightSpace;
    private Menu menu;
    private final Disposable menuCloseSubscription;
    private FrameLayout menuContainer;
    private OverflowContextMenuViewModel menuViewModel;
    private final PublishSubject<StageOverflowMenuMoreButtons> overFlowMenuCloseObservable;
    private final ActionValidatedPlaybackController playbackController;
    private StyleSheet styleSheet;
    private Subscription stylesheetSubscription;
    private Guideline topGuideline;

    /* JADX INFO: compiled from: DialogFragmentPopOutContextMenu.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StageOverflowMenuMoreButtons.values().length];
            iArr[StageOverflowMenuMoreButtons.STATION.ordinal()] = 1;
            iArr[StageOverflowMenuMoreButtons.DISLIKE.ordinal()] = 2;
            iArr[StageOverflowMenuMoreButtons.SHUFFLE.ordinal()] = 3;
            iArr[StageOverflowMenuMoreButtons.REPEAT.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public DialogFragmentPopOutContextMenu() {
        PublishSubject<StageOverflowMenuMoreButtons> publishSubjectCreate = PublishSubject.create();
        this.overFlowMenuCloseObservable = publishSubjectCreate;
        this.playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        this.menuCloseSubscription = publishSubjectCreate.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DialogFragmentPopOutContextMenu.m1106menuCloseSubscription$lambda3(this.f$0, (StageOverflowMenuMoreButtons) obj);
            }
        });
    }

    public final Space getHeightSpace() {
        Space space = this.heightSpace;
        if (space != null) {
            return space;
        }
        Intrinsics.throwUninitializedPropertyAccessException("heightSpace");
        return null;
    }

    public final void setHeightSpace(Space space) {
        Intrinsics.checkNotNullParameter(space, "<set-?>");
        this.heightSpace = space;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX INFO: renamed from: menuCloseSubscription$lambda-3, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m1106menuCloseSubscription$lambda3(com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu r3, com.amazon.mp3.fragment.contextmenu.StageOverflowMenuMoreButtons r4) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            android.content.Context r0 = r3.getContext()
            boolean r1 = r0 instanceof com.amazon.mp3.playback.activity.NowPlayingFragmentActivity
            r2 = 0
            if (r1 == 0) goto L12
            com.amazon.mp3.playback.activity.NowPlayingFragmentActivity r0 = (com.amazon.mp3.playback.activity.NowPlayingFragmentActivity) r0
            goto L13
        L12:
            r0 = r2
        L13:
            if (r0 != 0) goto L17
        L15:
            r0 = r2
            goto L2d
        L17:
            androidx.fragment.app.Fragment r0 = r0.getCurrentFragment()
            boolean r1 = r0 instanceof com.amazon.mp3.playback.fragment.NowPlayingFragment
            if (r1 == 0) goto L22
            com.amazon.mp3.playback.fragment.NowPlayingFragment r0 = (com.amazon.mp3.playback.fragment.NowPlayingFragment) r0
            goto L23
        L22:
            r0 = r2
        L23:
            if (r0 != 0) goto L26
            goto L15
        L26:
            com.amazon.mp3.view.stage.StageViewPresenter r0 = r0.getStageViewPresenter()
            if (r0 != 0) goto L2d
            goto L15
        L2d:
            if (r4 != 0) goto L31
            r4 = -1
            goto L39
        L31:
            int[] r1 = com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu.WhenMappings.$EnumSwitchMapping$0
            int r4 = r4.ordinal()
            r4 = r1[r4]
        L39:
            r1 = 1
            if (r4 == r1) goto L80
            r1 = 2
            if (r4 == r1) goto L80
            r1 = 3
            if (r4 == r1) goto L54
            r3 = 4
            if (r4 == r3) goto L46
            goto L83
        L46:
            if (r0 != 0) goto L49
            goto L83
        L49:
            com.amazon.mp3.view.stage.StageViewModel r3 = r0.getStageViewModel()
            if (r3 != 0) goto L50
            goto L83
        L50:
            r3.updateStageOverflowMenuSecondaryIcon()
            goto L83
        L54:
            com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController r3 = r3.playbackController
            boolean r3 = r3.isShuffled()
            if (r3 == 0) goto L72
            if (r0 != 0) goto L5f
            goto L63
        L5f:
            com.amazon.mp3.view.stage.StageOverflowMenuSecondaryIconStateProvider r2 = r0.getStageOverflowMenuSecondaryIconStateProvider()
        L63:
            if (r2 != 0) goto L66
            goto L6b
        L66:
            com.amazon.mp3.view.stage.StageOverflowMenuSecondaryIconState r3 = com.amazon.mp3.view.stage.StageOverflowMenuSecondaryIconState.SHUFFLE
            r2.setStageOverflowMenuSecondaryIconState(r3)
        L6b:
            if (r0 != 0) goto L6e
            goto L83
        L6e:
            r0.updateOverflowMenuButtonSecondaryIcon()
            goto L83
        L72:
            if (r0 != 0) goto L75
            goto L83
        L75:
            com.amazon.mp3.view.stage.StageViewModel r3 = r0.getStageViewModel()
            if (r3 != 0) goto L7c
            goto L83
        L7c:
            r3.updateStageOverflowMenuSecondaryIcon()
            goto L83
        L80:
            r3.dismissAllowingStateLoss()
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu.m1106menuCloseSubscription$lambda3(com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu, com.amazon.mp3.fragment.contextmenu.StageOverflowMenuMoreButtons):void");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Window window2;
        Window window3;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.stylesheetSubscription = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DialogFragmentPopOutContextMenu.m1109onCreateView$lambda4(this.f$0, (StyleSheet) obj);
            }
        });
        View viewInflate = inflater.inflate(R.layout.dialog_fragment_pop_outcontext_menu, container, false);
        View viewFindViewById = viewInflate.findViewById(R.id.card_container);
        CardView cardView = (CardView) viewFindViewById;
        cardView.setOnClickListener(null);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById<Ca…kListener(null)\n        }");
        this.cardContainer = cardView;
        View viewFindViewById2 = viewInflate.findViewById(R.id.menu_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "rootView.findViewById(R.id.menu_container)");
        this.menuContainer = (FrameLayout) viewFindViewById2;
        View viewFindViewById3 = viewInflate.findViewById(R.id.top_guideline);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "rootView.findViewById(R.id.top_guideline)");
        this.topGuideline = (Guideline) viewFindViewById3;
        View viewFindViewById4 = viewInflate.findViewById(R.id.height_space);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "rootView.findViewById(R.id.height_space)");
        setHeightSpace((Space) viewFindViewById4);
        if (isStage()) {
            Dialog dialog = getDialog();
            if (dialog != null && (window3 = dialog.getWindow()) != null) {
                window3.setGravity(8388693);
            }
        } else {
            Dialog dialog2 = getDialog();
            if (dialog2 != null && (window = dialog2.getWindow()) != null) {
                window.setGravity(8388661);
            }
        }
        Dialog dialog3 = getDialog();
        if (dialog3 != null && (window2 = dialog3.getWindow()) != null) {
            window2.setBackgroundDrawable(new ColorDrawable(0));
        }
        View viewFindViewById5 = viewInflate.findViewById(R.id.root_container);
        if (viewFindViewById5 != null) {
            viewFindViewById5.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DialogFragmentPopOutContextMenu.m1110onCreateView$lambda6(this.f$0, view);
                }
            });
        }
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-4, reason: not valid java name */
    public static final void m1109onCreateView$lambda4(DialogFragmentPopOutContextMenu this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.styleSheet = styleSheet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-6, reason: not valid java name */
    public static final void m1110onCreateView$lambda6(DialogFragmentPopOutContextMenu this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissAllowingStateLoss();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        PlayerOverflowMenuFragment playerOverflowMenuFragment;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        OverflowContextMenuViewModel overflowContextMenuViewModel = (OverflowContextMenuViewModel) new ViewModelProvider(fragmentActivityRequireActivity).get(OverflowContextMenuViewModel.class);
        Menu menu = this.menu;
        if (menu != null) {
            overflowContextMenuViewModel.setMenu(menu);
        }
        Action1<MenuItem> action1 = this.clickAction;
        if (action1 != null) {
            overflowContextMenuViewModel.setClickAction(action1);
        }
        this.menuViewModel = overflowContextMenuViewModel;
        Boolean boolValueOf = null;
        CardView cardView = null;
        if (overflowContextMenuViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuViewModel");
            overflowContextMenuViewModel = null;
        }
        overflowContextMenuViewModel.setChainAction(new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DialogFragmentPopOutContextMenu.m1112onViewCreated$lambda10(this.f$0, (MenuItem) obj);
            }
        });
        OverflowContextMenuViewModel overflowContextMenuViewModel2 = this.menuViewModel;
        if (overflowContextMenuViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuViewModel");
            overflowContextMenuViewModel2 = null;
        }
        if (overflowContextMenuViewModel2.getMenu() == null) {
            playerOverflowMenuFragment = null;
        } else {
            playerOverflowMenuFragment = new PlayerOverflowMenuFragment(this.overFlowMenuCloseObservable);
            Bundle arguments = getArguments();
            if (arguments != null) {
                arguments.putBoolean("FIRST_TIME_LAUNCHED", false);
            }
            Bundle arguments2 = getArguments();
            if (arguments2 != null) {
                arguments2.putBoolean("POP_OUT_MENU", true);
            }
            playerOverflowMenuFragment.setArguments(getArguments());
        }
        if (playerOverflowMenuFragment != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
            Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "childFragmentManager.beginTransaction()");
            fragmentTransactionBeginTransaction.replace(R.id.menu_container, playerOverflowMenuFragment);
            fragmentTransactionBeginTransaction.setReorderingAllowed(true);
            fragmentTransactionBeginTransaction.commitNow();
            resizeDialog();
            CardView cardView2 = this.cardContainer;
            if (cardView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
            } else {
                cardView = cardView2;
            }
            boolValueOf = Boolean.valueOf(cardView.post(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    DialogFragmentPopOutContextMenu.m1113onViewCreated$lambda14$lambda13(this.f$0);
                }
            }));
        }
        if (boolValueOf == null) {
            dismissAllowingStateLoss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-10, reason: not valid java name */
    public static final void m1112onViewCreated$lambda10(DialogFragmentPopOutContextMenu this$0, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OverflowContextMenuViewModel overflowContextMenuViewModel = this$0.menuViewModel;
        if (overflowContextMenuViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuViewModel");
            overflowContextMenuViewModel = null;
        }
        Action1<MenuItem> clickAction = overflowContextMenuViewModel.getClickAction();
        if (clickAction != null) {
            clickAction.call(menuItem);
        }
        this$0.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-14$lambda-13, reason: not valid java name */
    public static final void m1113onViewCreated$lambda14$lambda13(DialogFragmentPopOutContextMenu this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.blurBackground();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return DialogFragmentPopOutContextMenu.m1111onResume$lambda15(this.f$0, dialogInterface, i, keyEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onResume$lambda-15, reason: not valid java name */
    public static final boolean m1111onResume$lambda15(DialogFragmentPopOutContextMenu this$0, DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 4) {
            if (!(keyEvent != null && keyEvent.getAction() == 0)) {
                List<Fragment> fragments = this$0.getChildFragmentManager().getFragments();
                Intrinsics.checkNotNullExpressionValue(fragments, "childFragmentManager.fragments");
                Fragment fragment = (Fragment) CollectionsKt___CollectionsKt.last((List) fragments);
                if (fragment != null && (fragment instanceof ContextMenuSeeAllPlaylistsFragment)) {
                    this$0.navigateToShovelerMenu(this$0.getArguments());
                    return true;
                }
                this$0.dismissAllowingStateLoss();
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Subscription subscription = this.stylesheetSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Subscription subscription2 = this.blurSubscription;
        if (subscription2 == null) {
            return;
        }
        subscription2.unsubscribe();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        CardView cardView = this.cardContainer;
        if (cardView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
            cardView = null;
        }
        cardView.post(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DialogFragmentPopOutContextMenu.m1108onConfigurationChanged$lambda16(this.f$0);
            }
        });
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled() && newConfig.orientation == 2) {
            dismissAllowingStateLoss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onConfigurationChanged$lambda-16, reason: not valid java name */
    public static final void m1108onConfigurationChanged$lambda16(DialogFragmentPopOutContextMenu this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.resizeDialog();
        this$0.blurBackground();
    }

    @Override // com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener
    public void navigateToSeeAllPage(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        CardView cardView = this.cardContainer;
        if (cardView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
            cardView = null;
        }
        if (ShovelerExtensionFunctionsKt.getActivity(cardView) == null) {
            return;
        }
        ContextMenuSeeAllPlaylistsFragment contextMenuSeeAllPlaylistsFragment = new ContextMenuSeeAllPlaylistsFragment();
        contextMenuSeeAllPlaylistsFragment.setCallbacksListener(this);
        contextMenuSeeAllPlaylistsFragment.setArguments(bundle);
        FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.setReorderingAllowed(true);
        fragmentTransactionBeginTransaction.replace(R.id.menu_container, contextMenuSeeAllPlaylistsFragment);
        fragmentTransactionBeginTransaction.commitNow();
    }

    @Override // com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener
    public void navigateToShovelerMenu(Bundle bundle) {
        PlayerOverflowMenuFragment playerOverflowMenuFragment;
        OverflowContextMenuViewModel overflowContextMenuViewModel = this.menuViewModel;
        FragmentTransaction fragmentTransactionBeginTransaction = null;
        byte b = 0;
        byte b2 = 0;
        if (overflowContextMenuViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuViewModel");
            overflowContextMenuViewModel = null;
        }
        overflowContextMenuViewModel.setChainAction(new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu$$ExternalSyntheticLambda9
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DialogFragmentPopOutContextMenu.m1107navigateToShovelerMenu$lambda19(this.f$0, (MenuItem) obj);
            }
        });
        OverflowContextMenuViewModel overflowContextMenuViewModel2 = this.menuViewModel;
        if (overflowContextMenuViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuViewModel");
            overflowContextMenuViewModel2 = null;
        }
        int i = 1;
        if (overflowContextMenuViewModel2.getMenu() == null) {
            playerOverflowMenuFragment = null;
        } else {
            playerOverflowMenuFragment = new PlayerOverflowMenuFragment(b2 == true ? 1 : 0, i, b == true ? 1 : 0);
            Bundle arguments = getArguments();
            if (arguments != null) {
                arguments.putBoolean("FIRST_TIME_LAUNCHED", false);
            }
            playerOverflowMenuFragment.setArguments(getArguments());
        }
        if (playerOverflowMenuFragment != null) {
            fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.setReorderingAllowed(true);
            fragmentTransactionBeginTransaction.replace(R.id.menu_container, playerOverflowMenuFragment);
            fragmentTransactionBeginTransaction.commitNow();
        }
        if (fragmentTransactionBeginTransaction == null) {
            dismissAllowingStateLoss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: navigateToShovelerMenu$lambda-19, reason: not valid java name */
    public static final void m1107navigateToShovelerMenu$lambda19(DialogFragmentPopOutContextMenu this$0, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OverflowContextMenuViewModel overflowContextMenuViewModel = this$0.menuViewModel;
        if (overflowContextMenuViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuViewModel");
            overflowContextMenuViewModel = null;
        }
        Action1<MenuItem> clickAction = overflowContextMenuViewModel.getClickAction();
        if (clickAction != null) {
            clickAction.call(menuItem);
        }
        this$0.dismissAllowingStateLoss();
    }

    @Override // com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener
    public void onAddToPlaylistError(String error) {
        if (error != null) {
            Log.debug(this.TAG, error);
        }
        dismissAllowingStateLoss();
    }

    @Override // com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener
    public void onAddToPlaylistSuccess(String selectedPlaylistName, Function0<Unit> onToastClickListener) {
        Intrinsics.checkNotNullParameter(selectedPlaylistName, "selectedPlaylistName");
        if (getActivity() == null) {
            return;
        }
        BauhausToastUtils.showTextToastWithIcon(getActivity(), requireActivity().getString(R.string.dmusic_context_see_all_added_to_playlist, selectedPlaylistName), 1, R.drawable.explore_arrow, onToastClickListener);
        MetricsLogger.sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.TOAST).withContentName(ContentName.ADD_TO_PLAYLIST_CONFIRMATION).withImpressionTimestamp(System.currentTimeMillis()).build());
        dismissAllowingStateLoss();
    }

    private final void resizeDialog() {
        CardView cardView = this.cardContainer;
        if (cardView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
            cardView = null;
        }
        cardView.post(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                DialogFragmentPopOutContextMenu.m1114resizeDialog$lambda26(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: resizeDialog$lambda-26, reason: not valid java name */
    public static final void m1114resizeDialog$lambda26(DialogFragmentPopOutContextMenu this$0) {
        Window window;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DisplayMetrics displayMetrics = this$0.requireContext().getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "requireContext().resources.displayMetrics");
        TypedArray typedArrayObtainStyledAttributes = this$0.requireContext().getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "requireContext().theme.o…id.R.attr.actionBarSize))");
        int dimension = (int) typedArrayObtainStyledAttributes.getDimension(0, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        CardView cardView = this$0.cardContainer;
        CardView cardView2 = null;
        if (cardView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
            cardView = null;
        }
        if (cardView.getHeight() + dimension > i) {
            Guideline guideline = this$0.topGuideline;
            if (guideline == null) {
                Intrinsics.throwUninitializedPropertyAccessException("topGuideline");
                guideline = null;
            }
            guideline.setGuidelineBegin(0);
            CardView cardView3 = this$0.cardContainer;
            if (cardView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
                cardView3 = null;
            }
            ViewGroup.LayoutParams layoutParams = cardView3.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = (this$0.isStage() && NowPlayingUtil.isVideoPlaying()) ? -2 : -1;
            }
        } else {
            Guideline guideline2 = this$0.topGuideline;
            if (guideline2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("topGuideline");
                guideline2 = null;
            }
            guideline2.setGuidelineBegin(dimension);
            CardView cardView4 = this$0.cardContainer;
            if (cardView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
                cardView4 = null;
            }
            ViewGroup.LayoutParams layoutParams2 = cardView4.getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams2.height = (this$0.isStage() && NowPlayingUtil.isVideoPlaying()) ? -2 : (int) this$0.requireContext().getResources().getDimension(R.dimen.pop_out_context_menu_max_height);
            }
        }
        if (this$0.isStage()) {
            Space heightSpace = this$0.getHeightSpace();
            ViewGroup.LayoutParams layoutParams3 = heightSpace == null ? null : heightSpace.getLayoutParams();
            if (layoutParams3 != null) {
                layoutParams3.height = -2;
            }
            Dialog dialog = this$0.getDialog();
            WindowManager.LayoutParams attributes = (dialog == null || (window = dialog.getWindow()) == null) ? null : window.getAttributes();
            if (attributes != null) {
                SpacerParser spacerParser = SpacerParser.INSTANCE;
                Resources resources = this$0.getResources();
                Intrinsics.checkNotNullExpressionValue(resources, "resources");
                attributes.y = spacerParser.dpToPixels(resources, (int) this$0.getResources().getDimension(R.dimen.stage_tablet_overflow_menu_bottom_margin));
            }
            Dialog dialog2 = this$0.getDialog();
            Window window2 = dialog2 == null ? null : dialog2.getWindow();
            if (window2 != null) {
                window2.setAttributes(attributes);
            }
        }
        CardView cardView5 = this$0.cardContainer;
        if (cardView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
            cardView5 = null;
        }
        if (cardView5.getWidth() + dimension > i2) {
            Guideline guideline3 = this$0.topGuideline;
            if (guideline3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("topGuideline");
                guideline3 = null;
            }
            guideline3.setGuidelineBegin(dimension);
            CardView cardView6 = this$0.cardContainer;
            if (cardView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
            } else {
                cardView2 = cardView6;
            }
            ViewGroup.LayoutParams layoutParams4 = cardView2.getLayoutParams();
            if (layoutParams4 == null) {
                return;
            }
            layoutParams4.width = -1;
            return;
        }
        Guideline guideline4 = this$0.topGuideline;
        if (guideline4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topGuideline");
            guideline4 = null;
        }
        guideline4.setGuidelineBegin(dimension);
        CardView cardView7 = this$0.cardContainer;
        if (cardView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
        } else {
            cardView2 = cardView7;
        }
        ViewGroup.LayoutParams layoutParams5 = cardView2.getLayoutParams();
        if (layoutParams5 == null) {
            return;
        }
        layoutParams5.width = (int) this$0.requireContext().getResources().getDimension(R.dimen.pop_out_context_menu_width);
    }

    public final void blurBackground() {
        Window window;
        View decorView;
        final Bitmap screenShot;
        final StyleSheet styleSheet;
        CardView cardView = this.cardContainer;
        CardView cardView2 = null;
        if (cardView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
            cardView = null;
        }
        Activity activity = ShovelerExtensionFunctionsKt.getActivity(cardView);
        View viewFindViewById = (activity == null || (window = activity.getWindow()) == null || (decorView = window.getDecorView()) == null) ? null : decorView.findViewById(android.R.id.content);
        if (viewFindViewById == null || (screenShot = ShovelerExtensionFunctionsKt.getScreenShot(viewFindViewById)) == null || (styleSheet = this.styleSheet) == null) {
            return;
        }
        DynamicBackgroundEffectUtils dynamicBackgroundEffectUtils = DynamicBackgroundEffectUtils.INSTANCE;
        CardView cardView3 = this.cardContainer;
        if (cardView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
        } else {
            cardView2 = cardView3;
        }
        Context context = cardView2.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "cardContainer.context");
        this.blurSubscription = dynamicBackgroundEffectUtils.getTransformBlurredImageSubscription(screenShot, context, styleSheet, new DynamicBackgroundImageCondition(BlurStyleKey.BLUR_2, ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_2, GradientStyleKey.GRADIENT_1)).subscribe(new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu$$ExternalSyntheticLambda7
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DialogFragmentPopOutContextMenu.m1104blurBackground$lambda32$lambda31$lambda30$lambda28(screenShot, styleSheet, this, (Bitmap) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu$$ExternalSyntheticLambda8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DialogFragmentPopOutContextMenu.m1105blurBackground$lambda32$lambda31$lambda30$lambda29(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: blurBackground$lambda-32$lambda-31$lambda-30$lambda-28, reason: not valid java name */
    public static final void m1104blurBackground$lambda32$lambda31$lambda30$lambda28(Bitmap bitmap, StyleSheet mStylesheet, DialogFragmentPopOutContextMenu this$0, Bitmap blurredBitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "$bitmap");
        Intrinsics.checkNotNullParameter(mStylesheet, "$mStylesheet");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BitmapEffectUtils bitmapEffectUtils = BitmapEffectUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(blurredBitmap, "blurredBitmap");
        Bitmap scaledBitmap = bitmapEffectUtils.getScaledBitmap(blurredBitmap, bitmap.getWidth(), bitmap.getHeight());
        Integer cornerSize = mStylesheet.getCornerSize(CornerSizeKey.MEDIUM);
        CardView cardView = null;
        if (cornerSize != null) {
            int iIntValue = cornerSize.intValue();
            RoundedBitmapDrawable roundedBitmapDrawableUpdateBitmapPosition = this$0.updateBitmapPosition(scaledBitmap);
            float f = iIntValue;
            roundedBitmapDrawableUpdateBitmapPosition.setCornerRadius(f);
            CardView cardView2 = this$0.cardContainer;
            if (cardView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
                cardView2 = null;
            }
            cardView2.setBackground(roundedBitmapDrawableUpdateBitmapPosition);
            CardView cardView3 = this$0.cardContainer;
            if (cardView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
                cardView3 = null;
            }
            cardView3.setRadius(f);
        }
        FrameLayout frameLayout = this$0.menuContainer;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuContainer");
            frameLayout = null;
        }
        frameLayout.setVisibility(0);
        CardView cardView4 = this$0.cardContainer;
        if (cardView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
        } else {
            cardView = cardView4;
        }
        cardView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: blurBackground$lambda-32$lambda-31$lambda-30$lambda-29, reason: not valid java name */
    public static final void m1105blurBackground$lambda32$lambda31$lambda30$lambda29(DialogFragmentPopOutContextMenu this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, Intrinsics.stringPlus("Background was not loaded successfully. Error: ", th.getMessage()));
        FrameLayout frameLayout = this$0.menuContainer;
        CardView cardView = null;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuContainer");
            frameLayout = null;
        }
        frameLayout.setVisibility(0);
        CardView cardView2 = this$0.cardContainer;
        if (cardView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
        } else {
            cardView = cardView2;
        }
        cardView.setVisibility(0);
    }

    private final RoundedBitmapDrawable updateBitmapPosition(Bitmap bitmap) {
        CardView cardView = this.cardContainer;
        if (cardView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardContainer");
            cardView = null;
        }
        RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(getResources(), ShovelerExtensionFunctionsKt.cropBitmap(bitmap, cardView));
        Intrinsics.checkNotNullExpressionValue(roundedBitmapDrawableCreate, "create(resources, cropped)");
        return roundedBitmapDrawableCreate;
    }

    public final boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }

    /* JADX INFO: compiled from: DialogFragmentPopOutContextMenu.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/DialogFragmentPopOutContextMenu$Companion;", "", "()V", "newInstance", "Lcom/amazon/mp3/fragment/contextmenu/DialogFragmentPopOutContextMenu;", "menu", "Landroid/view/Menu;", "clickAction", "Lrx/functions/Action1;", "Landroid/view/MenuItem;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DialogFragmentPopOutContextMenu newInstance(Menu menu, Action1<MenuItem> clickAction) {
            Intrinsics.checkNotNullParameter(menu, "menu");
            Intrinsics.checkNotNullParameter(clickAction, "clickAction");
            DialogFragmentPopOutContextMenu dialogFragmentPopOutContextMenu = new DialogFragmentPopOutContextMenu();
            dialogFragmentPopOutContextMenu.menu = menu;
            dialogFragmentPopOutContextMenu.clickAction = clickAction;
            return dialogFragmentPopOutContextMenu;
        }
    }
}
