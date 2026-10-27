package com.amazon.mp3.fragment.contextmenu.shoveler;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu;
import com.amazon.mp3.fragment.contextmenu.DialogFragmentPopOutContextMenu;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.PlayerContextMenu;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import rx.functions.Action1;

/* JADX INFO: compiled from: ShovelerExtensionFunctions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a\f\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u0004\u001a\u0012\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012\u001a\u0014\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u001a\u001c\u0010\u0018\u001a\u00020\u0014*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u001a*\u0010\u001e\u001a\u00020\u0014*\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\n\u001a\u00020\u000b¨\u0006&"}, d2 = {"cropBitmap", "Landroid/graphics/Bitmap;", "bitmap", "view", "Landroid/view/View;", "getScreenShot", "shouldDisplayOverflowMenu", "", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getActivity", "Landroid/app/Activity;", "getSnapPosition", "", "Landroidx/recyclerview/widget/SnapHelper;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "navigateToPlaylist", "", "Lcom/amazon/mp3/activity/NavigationActivity;", "playlistUri", "Landroid/net/Uri;", "showAllowingStateLoss", "Landroidx/fragment/app/DialogFragment;", "manager", "Landroidx/fragment/app/FragmentManager;", ParserUtil.TAG_QUERY_PARAM_NAME, "", "showOverflowWhenResumed", "Lcom/amazon/mp3/playback/fragment/NowPlayingFragment;", "bundle", "Landroid/os/Bundle;", "menu", "Landroid/view/Menu;", "playerContextMenu", "Lcom/amazon/mp3/playback/PlayerContextMenu;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ShovelerExtensionFunctionsKt {
    public static final void navigateToPlaylist(NavigationActivity navigationActivity, Uri uri) {
        Intrinsics.checkNotNullParameter(navigationActivity, "<this>");
        if (MediaProvider.Playlists.isPlaylist(uri)) {
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(navigationActivity, uri);
            intentForContentUri.putExtra("EXTRA_GO_TO_PLAYLIST", true);
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", MediaProvider.getSource(uri));
            navigationActivity.startActivity(intentForContentUri);
            navigationActivity.overridePendingTransition(R.anim.zoom_in, R.anim.fragment_fade_out);
            return;
        }
        Log.error(navigationActivity.getClass().getSimpleName(), Intrinsics.stringPlus("Unable to navigate to a playlist for ", uri));
    }

    public static final Activity getActivity(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
            Intrinsics.checkNotNullExpressionValue(context, "context.baseContext");
        }
        return null;
    }

    public static final Bitmap getScreenShot(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(ContextCompat.getColor(view.getContext(), R.color.black));
        view.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static final Bitmap cropBitmap(Bitmap bitmap, View view) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(view, "view");
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int width = iArr[0];
        int height = iArr[1];
        if (view.getHeight() + height > bitmap.getHeight()) {
            height = bitmap.getHeight() - view.getHeight();
        }
        if (view.getWidth() + width > bitmap.getWidth()) {
            width = bitmap.getWidth() - view.getWidth();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, width, height, view.getWidth(), view.getHeight());
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(bitmap, absX, absY, width, height)");
        return bitmapCreateBitmap;
    }

    public static final int getSnapPosition(SnapHelper snapHelper, RecyclerView recyclerView) {
        View viewFindSnapView;
        Intrinsics.checkNotNullParameter(snapHelper, "<this>");
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null || (viewFindSnapView = snapHelper.findSnapView(layoutManager)) == null) {
            return -1;
        }
        return layoutManager.getPosition(viewFindSnapView);
    }

    public static final boolean shouldDisplayOverflowMenu(Context context, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "context.resources.displayMetrics");
        return styleSheet.getScreenSizeKey(Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels)).compareTo(ScreenSizeKey.LARGE) < 0;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.fragment.contextmenu.shoveler.ShovelerExtensionFunctionsKt$showOverflowWhenResumed$1, reason: invalid class name */
    /* JADX INFO: compiled from: ShovelerExtensionFunctions.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.fragment.contextmenu.shoveler.ShovelerExtensionFunctionsKt$showOverflowWhenResumed$1", f = "ShovelerExtensionFunctions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Bundle $bundle;
        final /* synthetic */ Menu $menu;
        final /* synthetic */ PlayerContextMenu $playerContextMenu;
        final /* synthetic */ StyleSheet $styleSheet;
        final /* synthetic */ NowPlayingFragment $this_showOverflowWhenResumed;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(NowPlayingFragment nowPlayingFragment, StyleSheet styleSheet, Menu menu, PlayerContextMenu playerContextMenu, Bundle bundle, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_showOverflowWhenResumed = nowPlayingFragment;
            this.$styleSheet = styleSheet;
            this.$menu = menu;
            this.$playerContextMenu = playerContextMenu;
            this.$bundle = bundle;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$this_showOverflowWhenResumed, this.$styleSheet, this.$menu, this.$playerContextMenu, this.$bundle, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            DialogFragment dialogFragmentNewInstance;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Context contextRequireContext = this.$this_showOverflowWhenResumed.requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            if (ShovelerExtensionFunctionsKt.shouldDisplayOverflowMenu(contextRequireContext, this.$styleSheet)) {
                BottomSheetDialogFragmentContextMenu.Companion companion = BottomSheetDialogFragmentContextMenu.INSTANCE;
                Menu menu = this.$menu;
                Action1<MenuItem> clickAction = this.$playerContextMenu.getClickAction();
                Intrinsics.checkNotNullExpressionValue(clickAction, "playerContextMenu.clickAction");
                dialogFragmentNewInstance = BottomSheetDialogFragmentContextMenu.Companion.newInstance$default(companion, menu, clickAction, null, 4, null);
            } else {
                DialogFragmentPopOutContextMenu.Companion companion2 = DialogFragmentPopOutContextMenu.INSTANCE;
                Menu menu2 = this.$menu;
                Action1<MenuItem> clickAction2 = this.$playerContextMenu.getClickAction();
                Intrinsics.checkNotNullExpressionValue(clickAction2, "playerContextMenu.clickAction");
                dialogFragmentNewInstance = companion2.newInstance(menu2, clickAction2);
            }
            dialogFragmentNewInstance.setArguments(this.$bundle);
            FragmentManager childFragmentManager = this.$this_showOverflowWhenResumed.getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
            ShovelerExtensionFunctionsKt.showAllowingStateLoss(dialogFragmentNewInstance, childFragmentManager, "javaClass");
            return Unit.INSTANCE;
        }
    }

    public static final void showOverflowWhenResumed(NowPlayingFragment nowPlayingFragment, Bundle bundle, Menu menu, PlayerContextMenu playerContextMenu, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(nowPlayingFragment, "<this>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(playerContextMenu, "playerContextMenu");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        if (nowPlayingFragment.isAdded()) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(nowPlayingFragment), null, null, new AnonymousClass1(nowPlayingFragment, styleSheet, menu, playerContextMenu, bundle, null), 3, null);
        }
    }

    public static final void showAllowingStateLoss(DialogFragment dialogFragment, FragmentManager manager, String str) {
        Intrinsics.checkNotNullParameter(dialogFragment, "<this>");
        Intrinsics.checkNotNullParameter(manager, "manager");
        FragmentTransaction fragmentTransactionBeginTransaction = manager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "manager.beginTransaction()");
        fragmentTransactionBeginTransaction.add(dialogFragment, str);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }
}
