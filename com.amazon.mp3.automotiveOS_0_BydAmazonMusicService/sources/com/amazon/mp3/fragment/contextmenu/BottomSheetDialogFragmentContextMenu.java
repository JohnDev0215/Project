package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.BlurStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
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

/* JADX INFO: compiled from: BottomSheetDialogFragmentContextMenu.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 V2\u00020\u00012\u00020\u0002:\u0001VB\u0007¢\u0006\u0004\bT\u0010UJ&\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J\u001a\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0007H\u0016J\u0012\u0010\u0010\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007H\u0016J \u0010\u0015\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0013H\u0016J\u0012\u0010\u0017\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0018\u001a\u00020\fH\u0016J\b\u0010\u0019\u001a\u00020\fH\u0016J\u0010\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016J\u0010\u0010\u001f\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001dH\u0016J\b\u0010!\u001a\u00020 H\u0002J\b\u0010\"\u001a\u00020\fH\u0002J\b\u0010#\u001a\u00020\fH\u0002J\u0012\u0010%\u001a\u00020\f2\b\b\u0002\u0010$\u001a\u00020 H\u0002J\u0010\u0010'\u001a\u00020\f2\u0006\u0010&\u001a\u00020\tH\u0002R\u0016\u0010)\u001a\u00020(8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010/\u001a\u00020.8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b/\u00100R\u0016\u00102\u001a\u0002018\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u00105\u001a\u0002048\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b5\u00106R\u0018\u00108\u001a\u0004\u0018\u0001078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010:\u001a\u00020 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u0010;R\u001e\u0010>\u001a\n\u0012\u0004\u0012\u00020=\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010?R\u0018\u0010A\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010BR\u0018\u0010D\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010ER\u0018\u0010G\u001a\u0004\u0018\u00010F8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010HR\u001c\u0010J\u001a\n I*\u0004\u0018\u00010\u00110\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u0010KR8\u0010N\u001a&\u0012\f\u0012\n I*\u0004\u0018\u00010M0M I*\u0012\u0012\f\u0012\n I*\u0004\u0018\u00010M0M\u0018\u00010L0L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010OR\u0014\u0010Q\u001a\u00020P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010RR\u001c\u0010S\u001a\n I*\u0004\u0018\u00010C0C8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010E¨\u0006W"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/BottomSheetDialogFragmentContextMenu;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuAddToPlaylistBaseFragment$ShovelerCallbacksListener;", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "Landroid/view/View;", "onCreateView", "view", "", "onViewCreated", "bundle", "navigateToSeeAllPage", "navigateToShovelerMenu", "", "selectedPlaylistName", "Lkotlin/Function0;", "onToastClickListener", "onAddToPlaylistSuccess", "error", "onAddToPlaylistError", "onPause", "onResume", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "Landroid/content/DialogInterface;", "dialog", "onDismiss", "", "isShovelerShown", "resizeDialog", "resetDismissButtonParams", "repositionBitmap", "blurBackground", "rootContainer", "initBottomSheetBehavior", "Landroid/widget/TextView;", "dismissButton", "Landroid/widget/TextView;", "Landroid/widget/ImageView;", "backgroundView", "Landroid/widget/ImageView;", "Landroid/widget/LinearLayout;", "menuWrapperLayout", "Landroid/widget/LinearLayout;", "Landroid/widget/FrameLayout;", "menuContainer", "Landroid/widget/FrameLayout;", "Lcom/amazon/mp3/fragment/contextmenu/OverflowContextMenuViewModel;", "menuViewModel", "Lcom/amazon/mp3/fragment/contextmenu/OverflowContextMenuViewModel;", "Landroid/view/Menu;", "menu", "Landroid/view/Menu;", "showMenuItemsOnly", "Z", "Lrx/functions/Action1;", "Landroid/view/MenuItem;", "clickAction", "Lrx/functions/Action1;", "Lrx/Subscription;", "stylesheetSubscription", "Lrx/Subscription;", "Lio/reactivex/rxjava3/disposables/Disposable;", "blurSubscription", "Lio/reactivex/rxjava3/disposables/Disposable;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/mp3/fragment/contextmenu/StageOverflowMenuMoreButtons;", "overFlowMenuCloseObservable", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "menuCloseSubscription", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BottomSheetDialogFragmentContextMenu extends BottomSheetDialogFragment implements ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DynamicBackgroundImageCondition DYN_BACKGROUND_IMAGE_CONDITION = new DynamicBackgroundImageCondition(BlurStyleKey.BLUR_2, ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_3, GradientStyleKey.GRADIENT_1);
    private final String TAG = BottomSheetDialogFragmentContextMenu.class.getSimpleName();
    private ImageView backgroundView;
    private Disposable blurSubscription;
    private Action1<MenuItem> clickAction;
    private TextView dismissButton;
    private Menu menu;
    private final Disposable menuCloseSubscription;
    private FrameLayout menuContainer;
    private OverflowContextMenuViewModel menuViewModel;
    private LinearLayout menuWrapperLayout;
    private final PublishSubject<StageOverflowMenuMoreButtons> overFlowMenuCloseObservable;
    private final ActionValidatedPlaybackController playbackController;
    private boolean showMenuItemsOnly;
    private StyleSheet styleSheet;
    private Subscription stylesheetSubscription;

    /* JADX INFO: compiled from: BottomSheetDialogFragmentContextMenu.kt */
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

    public BottomSheetDialogFragmentContextMenu() {
        PublishSubject<StageOverflowMenuMoreButtons> publishSubjectCreate = PublishSubject.create();
        this.overFlowMenuCloseObservable = publishSubjectCreate;
        this.playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        this.menuCloseSubscription = publishSubjectCreate.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                BottomSheetDialogFragmentContextMenu.m1087menuCloseSubscription$lambda3(this.f$0, (StageOverflowMenuMoreButtons) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX INFO: renamed from: menuCloseSubscription$lambda-3, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m1087menuCloseSubscription$lambda3(com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu r3, com.amazon.mp3.fragment.contextmenu.StageOverflowMenuMoreButtons r4) {
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
            int[] r1 = com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu.WhenMappings.$EnumSwitchMapping$0
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
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu.m1087menuCloseSubscription$lambda3(com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu, com.amazon.mp3.fragment.contextmenu.StageOverflowMenuMoreButtons):void");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.stylesheetSubscription = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BottomSheetDialogFragmentContextMenu.m1092onCreateView$lambda4(this.f$0, (StyleSheet) obj);
            }
        });
        View viewInflate = inflater.inflate(R.layout.bottom_sheet_dialog_context_menu, container, false);
        View viewFindViewById = viewInflate.findViewById(R.id.dismiss_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.dismiss_button)");
        TextView textView = (TextView) viewFindViewById;
        this.dismissButton = textView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dismissButton");
            textView = null;
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BottomSheetDialogFragmentContextMenu.m1093onCreateView$lambda5(this.f$0, view);
            }
        });
        View viewFindViewById2 = viewInflate.findViewById(R.id.blurred_background_image_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "rootView.findViewById(R.…ed_background_image_view)");
        this.backgroundView = (ImageView) viewFindViewById2;
        Bundle arguments = getArguments();
        if (arguments != null && (bitmap = (Bitmap) arguments.getParcelable("EXTRA_BACKGROUND")) != null) {
            ImageView imageView = this.backgroundView;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("backgroundView");
                imageView = null;
            }
            imageView.setImageBitmap(bitmap);
            Bundle arguments2 = getArguments();
            if (arguments2 != null) {
                arguments2.putParcelable("EXTRA_BACKGROUND", null);
            }
        }
        View viewFindViewById3 = viewInflate.findViewById(R.id.menu_wrapper);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "rootView.findViewById(R.id.menu_wrapper)");
        this.menuWrapperLayout = (LinearLayout) viewFindViewById3;
        View viewFindViewById4 = viewInflate.findViewById(R.id.menu_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "rootView.findViewById(R.id.menu_container)");
        this.menuContainer = (FrameLayout) viewFindViewById4;
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled()) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SHOW_OVERFLOW).withInteractionType(InteractionType.TAP).withPageType(PageType.NOW_PLAYING_STAGE).withEventTime(System.currentTimeMillis()).build());
            MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder("nowPlayingOverflow").build());
        }
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-4, reason: not valid java name */
    public static final void m1092onCreateView$lambda4(BottomSheetDialogFragmentContextMenu this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.styleSheet = styleSheet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-5, reason: not valid java name */
    public static final void m1093onCreateView$lambda5(BottomSheetDialogFragmentContextMenu this$0, View view) {
        PageType pageType;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissAllowingStateLoss();
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled()) {
            pageType = PageType.NOW_PLAYING_OVERFLOW;
        } else {
            pageType = PageType.NOW_PLAYING;
        }
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SELECT_DISMISS).withInteractionType(InteractionType.TAP).withPageType(pageType).withEventTime(System.currentTimeMillis()).build());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment playerOverflowMenuMoreSectionFragment;
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
        ImageView imageView = null;
        if (overflowContextMenuViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuViewModel");
            overflowContextMenuViewModel = null;
        }
        overflowContextMenuViewModel.setChainAction(new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BottomSheetDialogFragmentContextMenu.m1095onViewCreated$lambda10(this.f$0, (MenuItem) obj);
            }
        });
        OverflowContextMenuViewModel overflowContextMenuViewModel2 = this.menuViewModel;
        if (overflowContextMenuViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuViewModel");
            overflowContextMenuViewModel2 = null;
        }
        if (overflowContextMenuViewModel2.getMenu() == null) {
            playerOverflowMenuMoreSectionFragment = null;
        } else if (!this.showMenuItemsOnly) {
            playerOverflowMenuMoreSectionFragment = new PlayerOverflowMenuFragment(this.overFlowMenuCloseObservable);
            Bundle arguments = getArguments();
            if (arguments != null) {
                arguments.putBoolean("FIRST_TIME_LAUNCHED", true);
            }
            playerOverflowMenuMoreSectionFragment.setArguments(getArguments());
        } else {
            playerOverflowMenuMoreSectionFragment = new PlayerOverflowMenuMoreSectionFragment();
        }
        if (playerOverflowMenuMoreSectionFragment != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
            Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "childFragmentManager.beginTransaction()");
            fragmentTransactionBeginTransaction.replace(R.id.menu_container, playerOverflowMenuMoreSectionFragment);
            fragmentTransactionBeginTransaction.setReorderingAllowed(true);
            fragmentTransactionBeginTransaction.commitNow();
            ImageView imageView2 = this.backgroundView;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("backgroundView");
            } else {
                imageView = imageView2;
            }
            boolValueOf = Boolean.valueOf(imageView.post(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    BottomSheetDialogFragmentContextMenu.m1096onViewCreated$lambda14$lambda13(this.f$0);
                }
            }));
        }
        if (boolValueOf == null) {
            dismissAllowingStateLoss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-10, reason: not valid java name */
    public static final void m1095onViewCreated$lambda10(BottomSheetDialogFragmentContextMenu this$0, MenuItem menuItem) {
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
    public static final void m1096onViewCreated$lambda14$lambda13(BottomSheetDialogFragmentContextMenu this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.resizeDialog();
        this$0.blurBackground(true);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener
    public void navigateToSeeAllPage(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        ImageView imageView = this.backgroundView;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundView");
            imageView = null;
        }
        if (ShovelerExtensionFunctionsKt.getActivity(imageView) == null) {
            return;
        }
        ContextMenuSeeAllPlaylistsFragment contextMenuSeeAllPlaylistsFragment = new ContextMenuSeeAllPlaylistsFragment();
        contextMenuSeeAllPlaylistsFragment.setCallbacksListener(this);
        contextMenuSeeAllPlaylistsFragment.setArguments(bundle);
        FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.setReorderingAllowed(true);
        fragmentTransactionBeginTransaction.replace(R.id.menu_container, contextMenuSeeAllPlaylistsFragment);
        fragmentTransactionBeginTransaction.commitNow();
        ImageView imageView3 = this.backgroundView;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundView");
        } else {
            imageView2 = imageView3;
        }
        imageView2.post(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                BottomSheetDialogFragmentContextMenu.m1088navigateToSeeAllPage$lambda17(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: navigateToSeeAllPage$lambda-17, reason: not valid java name */
    public static final void m1088navigateToSeeAllPage$lambda17(BottomSheetDialogFragmentContextMenu this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.resizeDialog();
        this$0.blurBackground(false);
        this$0.resetDismissButtonParams();
    }

    @Override // com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener
    public void navigateToShovelerMenu(Bundle bundle) {
        Fragment playerOverflowMenuMoreSectionFragment;
        OverflowContextMenuViewModel overflowContextMenuViewModel = this.menuViewModel;
        FragmentTransaction fragmentTransaction = null;
        ImageView imageView = null;
        if (overflowContextMenuViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuViewModel");
            overflowContextMenuViewModel = null;
        }
        overflowContextMenuViewModel.setChainAction(new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$$ExternalSyntheticLambda7
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BottomSheetDialogFragmentContextMenu.m1089navigateToShovelerMenu$lambda18(this.f$0, (MenuItem) obj);
            }
        });
        OverflowContextMenuViewModel overflowContextMenuViewModel2 = this.menuViewModel;
        if (overflowContextMenuViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuViewModel");
            overflowContextMenuViewModel2 = null;
        }
        if (overflowContextMenuViewModel2.getMenu() == null) {
            playerOverflowMenuMoreSectionFragment = null;
        } else if (!this.showMenuItemsOnly) {
            playerOverflowMenuMoreSectionFragment = new PlayerOverflowMenuFragment(this.overFlowMenuCloseObservable);
            Bundle arguments = getArguments();
            if (arguments != null) {
                arguments.putBoolean("FIRST_TIME_LAUNCHED", false);
            }
            playerOverflowMenuMoreSectionFragment.setArguments(getArguments());
        } else {
            playerOverflowMenuMoreSectionFragment = new PlayerOverflowMenuMoreSectionFragment();
        }
        if (playerOverflowMenuMoreSectionFragment != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.replace(R.id.menu_container, playerOverflowMenuMoreSectionFragment);
            fragmentTransactionBeginTransaction.setReorderingAllowed(true);
            fragmentTransactionBeginTransaction.commitNow();
            ImageView imageView2 = this.backgroundView;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("backgroundView");
            } else {
                imageView = imageView2;
            }
            imageView.post(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    BottomSheetDialogFragmentContextMenu.m1090navigateToShovelerMenu$lambda23$lambda22$lambda21(this.f$0);
                }
            });
            fragmentTransaction = fragmentTransactionBeginTransaction;
        }
        if (fragmentTransaction == null) {
            dismissAllowingStateLoss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: navigateToShovelerMenu$lambda-18, reason: not valid java name */
    public static final void m1089navigateToShovelerMenu$lambda18(BottomSheetDialogFragmentContextMenu this$0, MenuItem menuItem) {
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
    /* JADX INFO: renamed from: navigateToShovelerMenu$lambda-23$lambda-22$lambda-21, reason: not valid java name */
    public static final void m1090navigateToShovelerMenu$lambda23$lambda22$lambda21(BottomSheetDialogFragmentContextMenu this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.resizeDialog();
        this$0.blurBackground(true);
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

    @Override // com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener
    public void onAddToPlaylistError(String error) {
        if (error != null) {
            Log.debug(this.TAG, error);
        }
        dismissAllowingStateLoss();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Subscription subscription = this.stylesheetSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Disposable disposable = this.blurSubscription;
        if (disposable != null) {
            disposable.dispose();
        }
        this.menuCloseSubscription.dispose();
    }

    private final boolean isShovelerShown() {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        Intrinsics.checkNotNullExpressionValue(fragments, "childFragmentManager.fragments");
        Fragment fragment = (Fragment) CollectionsKt___CollectionsKt.last((List) fragments);
        return fragment != null && (fragment instanceof PlayerOverflowMenuFragment);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return BottomSheetDialogFragmentContextMenu.m1094onResume$lambda25(this.f$0, dialogInterface, i, keyEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onResume$lambda-25, reason: not valid java name */
    public static final boolean m1094onResume$lambda25(BottomSheetDialogFragmentContextMenu this$0, DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
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

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        ImageView imageView = this.backgroundView;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundView");
            imageView = null;
        }
        imageView.post(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                BottomSheetDialogFragmentContextMenu.m1091onConfigurationChanged$lambda26(this.f$0);
            }
        });
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled() && newConfig.orientation == 2) {
            dismissAllowingStateLoss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onConfigurationChanged$lambda-26, reason: not valid java name */
    public static final void m1091onConfigurationChanged$lambda26(BottomSheetDialogFragmentContextMenu this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.blurBackground(false);
    }

    private final void resizeDialog() {
        Dialog dialog = getDialog();
        FrameLayout frameLayout = null;
        BottomSheetDialog bottomSheetDialog = dialog == null ? null : (BottomSheetDialog) dialog;
        FrameLayout frameLayout2 = (FrameLayout) (bottomSheetDialog == null ? null : bottomSheetDialog.findViewById(R.id.design_bottom_sheet));
        int i = isShovelerShown() ? -1 : -2;
        ViewGroup.LayoutParams layoutParams = frameLayout2 == null ? null : frameLayout2.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = i;
        }
        LinearLayout linearLayout = this.menuWrapperLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuWrapperLayout");
            linearLayout = null;
        }
        ViewGroup.LayoutParams layoutParams2 = linearLayout.getLayoutParams();
        if (layoutParams2 != null) {
            layoutParams2.height = i;
        }
        FrameLayout frameLayout3 = this.menuContainer;
        if (frameLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuContainer");
        } else {
            frameLayout = frameLayout3;
        }
        ViewGroup.LayoutParams layoutParams3 = frameLayout.getLayoutParams();
        if (layoutParams3 != null) {
            layoutParams3.height = i;
        }
        if (frameLayout2 != null) {
            initBottomSheetBehavior(frameLayout2);
        }
        resetDismissButtonParams();
    }

    private final void resetDismissButtonParams() {
        int dimension = (int) getResources().getDimension(R.dimen.bauhaus_spacer_huge);
        TextView textView = this.dismissButton;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dismissButton");
            textView = null;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.setMargins(0, dimension, 0, 0);
        TextView textView3 = this.dismissButton;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dismissButton");
        } else {
            textView2 = textView3;
        }
        textView2.setLayoutParams(marginLayoutParams);
    }

    private final void blurBackground(final boolean repositionBitmap) {
        Window window;
        View decorView;
        Bitmap screenShot;
        StyleSheet styleSheet;
        ImageView imageView = this.backgroundView;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundView");
            imageView = null;
        }
        Activity activity = ShovelerExtensionFunctionsKt.getActivity(imageView);
        View viewFindViewById = (activity == null || (window = activity.getWindow()) == null || (decorView = window.getDecorView()) == null) ? null : decorView.findViewById(android.R.id.content);
        if (viewFindViewById == null || (screenShot = ShovelerExtensionFunctionsKt.getScreenShot(viewFindViewById)) == null || (styleSheet = this.styleSheet) == null) {
            return;
        }
        final float width = screenShot.getWidth();
        final float height = screenShot.getHeight();
        DynamicBackgroundEffectUtils dynamicBackgroundEffectUtils = DynamicBackgroundEffectUtils.INSTANCE;
        ImageView imageView3 = this.backgroundView;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundView");
        } else {
            imageView2 = imageView3;
        }
        Context context = imageView2.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "backgroundView.context");
        this.blurSubscription = dynamicBackgroundEffectUtils.getTransformBlurredImageSubscriptionV2(screenShot, context, styleSheet, DYN_BACKGROUND_IMAGE_CONDITION).observeOn(Schedulers.computation()).map(new Function() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$$ExternalSyntheticLambda9
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return BottomSheetDialogFragmentContextMenu.m1084blurBackground$lambda36$lambda35$lambda34$lambda31(width, height, repositionBitmap, this, (Bitmap) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$$ExternalSyntheticLambda10
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                BottomSheetDialogFragmentContextMenu.m1085blurBackground$lambda36$lambda35$lambda34$lambda32(this.f$0, (Bitmap) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$$ExternalSyntheticLambda11
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                BottomSheetDialogFragmentContextMenu.m1086blurBackground$lambda36$lambda35$lambda34$lambda33(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: blurBackground$lambda-36$lambda-35$lambda-34$lambda-31, reason: not valid java name */
    public static final Bitmap m1084blurBackground$lambda36$lambda35$lambda34$lambda31(float f, float f2, boolean z, BottomSheetDialogFragmentContextMenu this$0, Bitmap blurredBitmap) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BitmapEffectUtils bitmapEffectUtils = BitmapEffectUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(blurredBitmap, "blurredBitmap");
        Bitmap scaledBitmap = bitmapEffectUtils.getScaledBitmap(blurredBitmap, f, f2);
        blurredBitmap.recycle();
        if (!z) {
            return scaledBitmap;
        }
        ImageView imageView = this$0.backgroundView;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundView");
            imageView = null;
        }
        Bitmap bitmapCropBitmap = ShovelerExtensionFunctionsKt.cropBitmap(scaledBitmap, imageView);
        scaledBitmap.recycle();
        return bitmapCropBitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: blurBackground$lambda-36$lambda-35$lambda-34$lambda-32, reason: not valid java name */
    public static final void m1085blurBackground$lambda36$lambda35$lambda34$lambda32(BottomSheetDialogFragmentContextMenu this$0, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ImageView imageView = this$0.backgroundView;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundView");
            imageView = null;
        }
        imageView.setImageBitmap(bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: blurBackground$lambda-36$lambda-35$lambda-34$lambda-33, reason: not valid java name */
    public static final void m1086blurBackground$lambda36$lambda35$lambda34$lambda33(BottomSheetDialogFragmentContextMenu this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, Intrinsics.stringPlus("Background is not loaded successfully. Error: ", th.getMessage()));
        ImageView imageView = this$0.backgroundView;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundView");
            imageView = null;
        }
        imageView.setVisibility(8);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        OverflowContextMenuViewModel overflowContextMenuViewModel = this.menuViewModel;
        if (overflowContextMenuViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuViewModel");
            overflowContextMenuViewModel = null;
        }
        overflowContextMenuViewModel.clear();
        super.onDismiss(dialog);
    }

    private final void initBottomSheetBehavior(View rootContainer) {
        BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() { // from class: com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu$initBottomSheetBehavior$callback$1
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(View contextMenu, float slideOffset) {
                Intrinsics.checkNotNullParameter(contextMenu, "contextMenu");
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(View contextMenu, int newState) {
                Intrinsics.checkNotNullParameter(contextMenu, "contextMenu");
                if (newState == 5) {
                    this.this$0.dismissAllowingStateLoss();
                }
            }
        };
        BottomSheetBehavior bottomSheetBehaviorFrom = BottomSheetBehavior.from(rootContainer);
        bottomSheetBehaviorFrom.setSkipCollapsed(true);
        bottomSheetBehaviorFrom.setFitToContents(true);
        bottomSheetBehaviorFrom.setState(3);
        bottomSheetBehaviorFrom.setPeekHeight(rootContainer.getHeight());
        bottomSheetBehaviorFrom.setBottomSheetCallback(bottomSheetCallback);
    }

    /* JADX INFO: compiled from: BottomSheetDialogFragmentContextMenu.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/BottomSheetDialogFragmentContextMenu$Companion;", "", "()V", "DYN_BACKGROUND_IMAGE_CONDITION", "Lcom/amazon/mp3/dynamicbackground/DynamicBackgroundImageCondition;", "newInstance", "Lcom/amazon/mp3/fragment/contextmenu/BottomSheetDialogFragmentContextMenu;", "menu", "Landroid/view/Menu;", "clickAction", "Lrx/functions/Action1;", "Landroid/view/MenuItem;", "showMenuItemsOnly", "", "(Landroid/view/Menu;Lrx/functions/Action1;Ljava/lang/Boolean;)Lcom/amazon/mp3/fragment/contextmenu/BottomSheetDialogFragmentContextMenu;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ BottomSheetDialogFragmentContextMenu newInstance$default(Companion companion, Menu menu, Action1 action1, Boolean bool, int i, Object obj) {
            if ((i & 4) != 0) {
                bool = Boolean.FALSE;
            }
            return companion.newInstance(menu, action1, bool);
        }

        public final BottomSheetDialogFragmentContextMenu newInstance(Menu menu, Action1<MenuItem> clickAction, Boolean showMenuItemsOnly) {
            Intrinsics.checkNotNullParameter(menu, "menu");
            Intrinsics.checkNotNullParameter(clickAction, "clickAction");
            BottomSheetDialogFragmentContextMenu bottomSheetDialogFragmentContextMenu = new BottomSheetDialogFragmentContextMenu();
            bottomSheetDialogFragmentContextMenu.menu = menu;
            bottomSheetDialogFragmentContextMenu.clickAction = clickAction;
            if (showMenuItemsOnly != null) {
                bottomSheetDialogFragmentContextMenu.showMenuItemsOnly = showMenuItemsOnly.booleanValue();
            }
            return bottomSheetDialogFragmentContextMenu;
        }
    }
}
