package com.amazon.mp3.find.view;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.actionbar.view.BauhausFilterActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.card.prime.ContextMenuHandler;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.catalog.fragment.BrowseViewsFactory;
import com.amazon.mp3.catalog.fragment.BrushFragment;
import com.amazon.mp3.catalog.fragment.helper.BrushPageScrollHelper;
import com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider;
import com.amazon.mp3.find.util.BrushViewModelWrapper;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.prime.browse.PrimeMusicPagerFragment;
import com.amazon.mp3.providers.DefaultAddToLibraryProvider;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.find.uicontentview.SearchContentViewManager;
import com.amazon.music.find.viewmodels.BasePageGridViewModel;
import com.amazon.music.find.viewmodels.FindPageGridViewModelContext;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PageGridViewModelContext;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.squareup.picasso.Picasso;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: BaseBrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0096\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0007\b&\u0018\u0000 ß\u0001*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005:\u0002ß\u0001B\t¢\u0006\u0006\bÝ\u0001\u0010Þ\u0001J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0012\u0010\f\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0014J&\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u0014\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0016\u001a\u00020\u0015H$J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u0015H\u0014J\b\u0010\u001b\u001a\u00020\bH\u0004J$\u0010!\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u00152\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001dH\u0014J\u001e\u0010#\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00152\b\u0010\"\u001a\u0004\u0018\u00010\nH\u0004J\u0019\u0010$\u001a\u00028\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\nH$¢\u0006\u0004\b$\u0010%J\b\u0010&\u001a\u00020\bH\u0014J\b\u0010'\u001a\u00020\bH\u0016J\b\u0010(\u001a\u00020\bH\u0016J\b\u0010)\u001a\u00020\u001dH&J\b\u0010*\u001a\u00020\bH\u0016J\b\u0010+\u001a\u00020\bH\u0016J\b\u0010,\u001a\u00020\bH\u0016J\u0012\u0010/\u001a\u00020\b2\b\u0010.\u001a\u0004\u0018\u00010-H\u0016J\u0012\u00101\u001a\u00020\b2\b\u0010.\u001a\u0004\u0018\u000100H\u0016J\b\u00102\u001a\u00020\bH\u0016J\u001c\u00106\u001a\u00020\b2\b\u00104\u001a\u0004\u0018\u0001032\b\u00105\u001a\u0004\u0018\u00010\u0012H\u0016J\"\u0010<\u001a\u00020\b2\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020\u00122\b\u0010;\u001a\u0004\u0018\u00010:H\u0016J\u0010\u0010@\u001a\u00020?2\u0006\u0010>\u001a\u00020=H\u0016J\u001a\u0010B\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010A\u001a\u00020?H\u0004J\u0010\u0010E\u001a\u00020\b2\u0006\u0010D\u001a\u00020CH\u0014J\b\u0010F\u001a\u00020\bH\u0004J\b\u0010G\u001a\u00020?H\u0014J\u0012\u0010I\u001a\u00020?2\b\u0010H\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010J\u001a\u00020\bH\u0004J\b\u0010K\u001a\u00020\bH\u0004J\u0012\u0010M\u001a\u00020\b2\b\u0010L\u001a\u0004\u0018\u00010\u001dH\u0004J&\u0010P\u001a\u00020\b2\b\u0010N\u001a\u0004\u0018\u00010\u001d2\b\u0010O\u001a\u0004\u0018\u00010\u001d2\b\u0010\"\u001a\u0004\u0018\u00010\nH\u0004J\u0018\u0010Q\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010A\u001a\u00020?H\u0004J\b\u0010R\u001a\u00020\bH\u0004J\u0010\u0010T\u001a\u00020S2\u0006\u0010\u0007\u001a\u00020\u0006H$J\u001b\u0010V\u001a\u00020\b2\n\b\u0002\u0010U\u001a\u0004\u0018\u00010?H\u0004¢\u0006\u0004\bV\u0010WJ\b\u0010X\u001a\u00020?H\u0014J\b\u0010Y\u001a\u00020\bH\u0004J\b\u0010Z\u001a\u00020\bH\u0004J\u0012\u0010]\u001a\u00020\b2\b\u0010\\\u001a\u0004\u0018\u00010[H\u0004J\u0012\u0010`\u001a\u00020\b2\b\u0010_\u001a\u0004\u0018\u00010^H\u0004J\u0012\u0010a\u001a\u00020\b2\b\u0010_\u001a\u0004\u0018\u00010^H\u0004J\u0012\u0010b\u001a\u00020\b2\b\u0010_\u001a\u0004\u0018\u00010^H\u0014J\u0012\u0010c\u001a\u00020\b2\b\u0010_\u001a\u0004\u0018\u00010^H\u0014R\"\u0010d\u001a\u00020\u00128\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\bd\u0010e\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR\"\u0010j\u001a\u00020\u00128\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\bj\u0010e\u001a\u0004\bk\u0010g\"\u0004\bl\u0010iR$\u0010m\u001a\u0004\u0018\u00010 8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bm\u0010n\u001a\u0004\bo\u0010p\"\u0004\bq\u0010rR\"\u0010t\u001a\u00020s8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\bt\u0010u\u001a\u0004\bv\u0010w\"\u0004\bx\u0010yR#\u0010{\u001a\u00020z8\u0004@\u0004X\u0084.¢\u0006\u0013\n\u0004\b{\u0010|\u001a\u0004\b}\u0010~\"\u0005\b\u007f\u0010\u0080\u0001R\u001b\u0010\u0081\u0001\u001a\u0004\u0018\u00010S8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001R)\u0010\u0083\u0001\u001a\u00020\u000f8\u0004@\u0004X\u0084.¢\u0006\u0018\n\u0006\b\u0083\u0001\u0010\u0084\u0001\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001\"\u0006\b\u0087\u0001\u0010\u0088\u0001R+\u0010\u0089\u0001\u001a\u0004\u0018\u00010-8\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\b\u0089\u0001\u0010\u008a\u0001\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001\"\u0006\b\u008d\u0001\u0010\u008e\u0001R+\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u00178\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\b\u008f\u0001\u0010\u0090\u0001\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001\"\u0006\b\u0093\u0001\u0010\u0094\u0001R+\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u00198\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\b\u0095\u0001\u0010\u0096\u0001\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001\"\u0006\b\u0099\u0001\u0010\u009a\u0001R+\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u001d8\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\b\u009b\u0001\u0010\u009c\u0001\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001\"\u0006\b\u009f\u0001\u0010 \u0001R+\u0010¡\u0001\u001a\u0004\u0018\u00010\u001d8\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\b¡\u0001\u0010\u009c\u0001\u001a\u0006\b¢\u0001\u0010\u009e\u0001\"\u0006\b£\u0001\u0010 \u0001R*\u0010¥\u0001\u001a\u00030¤\u00018\u0004@\u0004X\u0084.¢\u0006\u0018\n\u0006\b¥\u0001\u0010¦\u0001\u001a\u0006\b§\u0001\u0010¨\u0001\"\u0006\b©\u0001\u0010ª\u0001R0\u0010¬\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000«\u00018\u0004@\u0004X\u0084.¢\u0006\u0018\n\u0006\b¬\u0001\u0010\u00ad\u0001\u001a\u0006\b®\u0001\u0010¯\u0001\"\u0006\b°\u0001\u0010±\u0001R*\u0010³\u0001\u001a\u00030²\u00018\u0004@\u0004X\u0084.¢\u0006\u0018\n\u0006\b³\u0001\u0010´\u0001\u001a\u0006\bµ\u0001\u0010¶\u0001\"\u0006\b·\u0001\u0010¸\u0001R,\u0010º\u0001\u001a\u0005\u0018\u00010¹\u00018\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\bº\u0001\u0010»\u0001\u001a\u0006\b¼\u0001\u0010½\u0001\"\u0006\b¾\u0001\u0010¿\u0001R*\u0010Á\u0001\u001a\u00030À\u00018\u0004@\u0004X\u0084.¢\u0006\u0018\n\u0006\bÁ\u0001\u0010Â\u0001\u001a\u0006\bÃ\u0001\u0010Ä\u0001\"\u0006\bÅ\u0001\u0010Æ\u0001R\u0019\u0010Ç\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\bÇ\u0001\u0010\u0084\u0001R\u0018\u0010É\u0001\u001a\u00030È\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÉ\u0001\u0010Ê\u0001R,\u0010Ì\u0001\u001a\u0005\u0018\u00010Ë\u00018\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\bÌ\u0001\u0010Í\u0001\u001a\u0006\bÎ\u0001\u0010Ï\u0001\"\u0006\bÐ\u0001\u0010Ñ\u0001R7\u0010Ô\u0001\u001a \u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d0Ò\u0001j\u000f\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d`Ó\u00018\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\bÔ\u0001\u0010Õ\u0001R\u0014\u0010Ø\u0001\u001a\u00028\u00008F¢\u0006\b\u001a\u0006\bÖ\u0001\u0010×\u0001R\u001a\u0010Ü\u0001\u001a\u0005\u0018\u00010Ù\u00018$X¤\u0004¢\u0006\b\u001a\u0006\bÚ\u0001\u0010Û\u0001¨\u0006à\u0001"}, d2 = {"Lcom/amazon/mp3/find/view/BaseBrushFragment;", "Lcom/amazon/music/find/viewmodels/BasePageGridViewModel;", "T", "Landroidx/fragment/app/Fragment;", "Lcom/amazon/mp3/prime/browse/PrimeMusicPagerFragment;", "Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageGridViewModel", "", "createDownloadStateModelProviderListener", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "inflateLayout", "Landroid/view/View;", "onCreateView", "onActivityCreated", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/mp3/find/view/BrushContextMenuProvider;", "createContextMenuProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "createRowButtonOnClickProvider", "initDefaultViews", "fragmentActivity", "", "title", "subTitle", "Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "createActionBarView", "bundle", "getStandaloneTitle", "createViewModel", "(Landroid/os/Bundle;)Lcom/amazon/music/find/viewmodels/BasePageGridViewModel;", "handlePageRefresh", "onPause", "onResume", "getBrushScrollIdentifier", "onDestroy", "onDestroyView", "onStop", "Lcom/amazon/mp3/library/adapter/LibraryPagerAdapter$OnListViewCreatedListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setOnListViewCreatedListener", "Landroid/view/View$OnTouchListener;", "setupTouchListener", "scrollToTop", "Lcom/amazon/mp3/card/prime/ContextMenuHandler;", "contextMenuHandler", "view", "openContextMenuForView", "Landroid/view/ContextMenu;", "menu", "v", "Landroid/view/ContextMenu$ContextMenuInfo;", "menuInfo", "onCreateContextMenu", "Landroid/view/MenuItem;", "menuItem", "", "onContextItemSelected", "isInitialPage", "updatePageModel", "Lcom/amazon/mp3/actionbar/view/BauhausFilterActionBarView;", "actionBar", "setupActionBarPills", "showEmptyView", "shouldErrorLayoutBeVisible", "pageModel", "hasValidBrushData", "stopImageRequests", "stopViewModelWrapper", "link", "handleDeepLink", "uri", "nextToken", "handleBrushUri", "setupBrushViews", "sendUiContentViewEvent", "Lcom/amazon/mp3/brush/BrushViews;", "createBrushViews", "resetScrollPosition", "setupScrollPosition", "(Ljava/lang/Boolean;)V", "shouldSwipeBeEnabled", "handleSwipeRefresh", "storeScrollPositions", "Lio/reactivex/rxjava3/disposables/Disposable;", "disposable", "addDisposable", "Lcom/amazon/music/find/viewmodels/FindPageGridViewModelContext;", "pageGridViewModelContext", "markRenderingStarted", "markRenderingCompleted", "afterRenderingCompleted", "notifyUpdatePageModelCompleted", "mLoadingView", "Landroid/view/View;", "getMLoadingView", "()Landroid/view/View;", "setMLoadingView", "(Landroid/view/View;)V", "mEmptyLayout", "getMEmptyLayout", "setMEmptyLayout", "mActionBarView", "Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "getMActionBarView", "()Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "setMActionBarView", "(Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;)V", "Landroidx/recyclerview/widget/RecyclerView;", "mRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getMRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setMRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "mSwipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "getMSwipeRefreshLayout", "()Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "setMSwipeRefreshLayout", "(Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;)V", "mBrushViews", "Lcom/amazon/mp3/brush/BrushViews;", "root", "Landroid/view/ViewGroup;", "getRoot", "()Landroid/view/ViewGroup;", "setRoot", "(Landroid/view/ViewGroup;)V", "mOnListViewCreatedListener", "Lcom/amazon/mp3/library/adapter/LibraryPagerAdapter$OnListViewCreatedListener;", "getMOnListViewCreatedListener", "()Lcom/amazon/mp3/library/adapter/LibraryPagerAdapter$OnListViewCreatedListener;", "setMOnListViewCreatedListener", "(Lcom/amazon/mp3/library/adapter/LibraryPagerAdapter$OnListViewCreatedListener;)V", "mContextMenuProvider", "Lcom/amazon/mp3/find/view/BrushContextMenuProvider;", "getMContextMenuProvider", "()Lcom/amazon/mp3/find/view/BrushContextMenuProvider;", "setMContextMenuProvider", "(Lcom/amazon/mp3/find/view/BrushContextMenuProvider;)V", "mRowButtonOnClickProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "getMRowButtonOnClickProvider", "()Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "setMRowButtonOnClickProvider", "(Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;)V", "mToolbarTitle", "Ljava/lang/String;", "getMToolbarTitle", "()Ljava/lang/String;", "setMToolbarTitle", "(Ljava/lang/String;)V", "mStandaloneTitle", "getMStandaloneTitle", "setMStandaloneTitle", "Lcom/amazon/music/find/uicontentview/SearchContentViewManager;", "mContentViewManager", "Lcom/amazon/music/find/uicontentview/SearchContentViewManager;", "getMContentViewManager", "()Lcom/amazon/music/find/uicontentview/SearchContentViewManager;", "setMContentViewManager", "(Lcom/amazon/music/find/uicontentview/SearchContentViewManager;)V", "Lcom/amazon/mp3/find/util/BrushViewModelWrapper;", "mViewModelWrapper", "Lcom/amazon/mp3/find/util/BrushViewModelWrapper;", "getMViewModelWrapper", "()Lcom/amazon/mp3/find/util/BrushViewModelWrapper;", "setMViewModelWrapper", "(Lcom/amazon/mp3/find/util/BrushViewModelWrapper;)V", "Lcom/amazon/mp3/catalog/fragment/helper/BrushPageScrollHelper;", "mBrushPageScrollHelper", "Lcom/amazon/mp3/catalog/fragment/helper/BrushPageScrollHelper;", "getMBrushPageScrollHelper", "()Lcom/amazon/mp3/catalog/fragment/helper/BrushPageScrollHelper;", "setMBrushPageScrollHelper", "(Lcom/amazon/mp3/catalog/fragment/helper/BrushPageScrollHelper;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "mStyleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getMStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setMStyleSheet", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "Landroid/widget/ImageView;", "mBackgroundArtworkLayout", "Landroid/widget/ImageView;", "getMBackgroundArtworkLayout", "()Landroid/widget/ImageView;", "setMBackgroundArtworkLayout", "(Landroid/widget/ImageView;)V", "mBackgroundArtworkOverlayLayout", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "mCompositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider$BauhausDefaultListener;", "mDownloadStateModelProviderListener", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider$BauhausDefaultListener;", "getMDownloadStateModelProviderListener", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider$BauhausDefaultListener;", "setMDownloadStateModelProviderListener", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider$BauhausDefaultListener;)V", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "hotSpotExperienceVault", "Ljava/util/HashMap;", "getViewModel", "()Lcom/amazon/music/find/viewmodels/BasePageGridViewModel;", "viewModel", "", "getInvalidateRecycleViewDelayedDuration", "()Ljava/lang/Long;", "invalidateRecycleViewDelayedDuration", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class BaseBrushFragment<T extends BasePageGridViewModel> extends Fragment implements PrimeMusicPagerFragment, ContextMenuSupportingFragment {
    private static final Logger logger = LoggerFactory.getLogger(BaseBrushFragment.class.getName());
    private HashMap<String, String> hotSpotExperienceVault;
    private BauhausActionBarView mActionBarView;
    protected ImageView mBackgroundArtworkLayout;
    private ViewGroup mBackgroundArtworkOverlayLayout;
    protected BrushPageScrollHelper mBrushPageScrollHelper;
    private BrushViews mBrushViews;
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    protected SearchContentViewManager mContentViewManager;
    private BrushContextMenuProvider mContextMenuProvider;
    private GroupDownloadStateModelProvider.BauhausDefaultListener mDownloadStateModelProviderListener;
    protected View mEmptyLayout;
    protected View mLoadingView;
    private LibraryPagerAdapter.OnListViewCreatedListener mOnListViewCreatedListener;
    protected RecyclerView mRecyclerView;
    private RowButtonOnClickProvider mRowButtonOnClickProvider;
    private String mStandaloneTitle;
    private StyleSheet mStyleSheet;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    private String mToolbarTitle;
    protected BrushViewModelWrapper<T> mViewModelWrapper;
    protected ViewGroup root;

    protected void afterRenderingCompleted(FindPageGridViewModelContext pageGridViewModelContext) {
    }

    protected abstract BrushViews createBrushViews(PageGridViewModel pageGridViewModel);

    protected abstract BrushContextMenuProvider createContextMenuProvider(FragmentActivity activity);

    protected abstract T createViewModel(Bundle savedInstanceState);

    public abstract String getBrushScrollIdentifier();

    protected abstract Long getInvalidateRecycleViewDelayedDuration();

    protected void setupActionBarPills(BauhausFilterActionBarView actionBar) {
        Intrinsics.checkNotNullParameter(actionBar, "actionBar");
    }

    protected boolean shouldErrorLayoutBeVisible() {
        return true;
    }

    protected boolean shouldSwipeBeEnabled() {
        return true;
    }

    protected final View getMLoadingView() {
        View view = this.mLoadingView;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mLoadingView");
        return null;
    }

    protected final void setMLoadingView(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.mLoadingView = view;
    }

    protected final View getMEmptyLayout() {
        View view = this.mEmptyLayout;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
        return null;
    }

    protected final void setMEmptyLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.mEmptyLayout = view;
    }

    protected final BauhausActionBarView getMActionBarView() {
        return this.mActionBarView;
    }

    protected final void setMActionBarView(BauhausActionBarView bauhausActionBarView) {
        this.mActionBarView = bauhausActionBarView;
    }

    protected final RecyclerView getMRecyclerView() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
        return null;
    }

    protected final void setMRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.mRecyclerView = recyclerView;
    }

    protected final SwipeRefreshLayout getMSwipeRefreshLayout() {
        SwipeRefreshLayout swipeRefreshLayout = this.mSwipeRefreshLayout;
        if (swipeRefreshLayout != null) {
            return swipeRefreshLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
        return null;
    }

    protected final void setMSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        Intrinsics.checkNotNullParameter(swipeRefreshLayout, "<set-?>");
        this.mSwipeRefreshLayout = swipeRefreshLayout;
    }

    protected final ViewGroup getRoot() {
        ViewGroup viewGroup = this.root;
        if (viewGroup != null) {
            return viewGroup;
        }
        Intrinsics.throwUninitializedPropertyAccessException("root");
        return null;
    }

    protected final void setRoot(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<set-?>");
        this.root = viewGroup;
    }

    protected final LibraryPagerAdapter.OnListViewCreatedListener getMOnListViewCreatedListener() {
        return this.mOnListViewCreatedListener;
    }

    protected final BrushContextMenuProvider getMContextMenuProvider() {
        return this.mContextMenuProvider;
    }

    protected final void setMContextMenuProvider(BrushContextMenuProvider brushContextMenuProvider) {
        this.mContextMenuProvider = brushContextMenuProvider;
    }

    protected final RowButtonOnClickProvider getMRowButtonOnClickProvider() {
        return this.mRowButtonOnClickProvider;
    }

    protected final void setMRowButtonOnClickProvider(RowButtonOnClickProvider rowButtonOnClickProvider) {
        this.mRowButtonOnClickProvider = rowButtonOnClickProvider;
    }

    protected final String getMToolbarTitle() {
        return this.mToolbarTitle;
    }

    protected final void setMToolbarTitle(String str) {
        this.mToolbarTitle = str;
    }

    protected final String getMStandaloneTitle() {
        return this.mStandaloneTitle;
    }

    protected final SearchContentViewManager getMContentViewManager() {
        SearchContentViewManager searchContentViewManager = this.mContentViewManager;
        if (searchContentViewManager != null) {
            return searchContentViewManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mContentViewManager");
        return null;
    }

    protected final void setMContentViewManager(SearchContentViewManager searchContentViewManager) {
        Intrinsics.checkNotNullParameter(searchContentViewManager, "<set-?>");
        this.mContentViewManager = searchContentViewManager;
    }

    protected final BrushViewModelWrapper<T> getMViewModelWrapper() {
        BrushViewModelWrapper<T> brushViewModelWrapper = this.mViewModelWrapper;
        if (brushViewModelWrapper != null) {
            return brushViewModelWrapper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mViewModelWrapper");
        return null;
    }

    protected final void setMViewModelWrapper(BrushViewModelWrapper<T> brushViewModelWrapper) {
        Intrinsics.checkNotNullParameter(brushViewModelWrapper, "<set-?>");
        this.mViewModelWrapper = brushViewModelWrapper;
    }

    protected final BrushPageScrollHelper getMBrushPageScrollHelper() {
        BrushPageScrollHelper brushPageScrollHelper = this.mBrushPageScrollHelper;
        if (brushPageScrollHelper != null) {
            return brushPageScrollHelper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mBrushPageScrollHelper");
        return null;
    }

    protected final void setMBrushPageScrollHelper(BrushPageScrollHelper brushPageScrollHelper) {
        Intrinsics.checkNotNullParameter(brushPageScrollHelper, "<set-?>");
        this.mBrushPageScrollHelper = brushPageScrollHelper;
    }

    protected final StyleSheet getMStyleSheet() {
        return this.mStyleSheet;
    }

    protected final ImageView getMBackgroundArtworkLayout() {
        ImageView imageView = this.mBackgroundArtworkLayout;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mBackgroundArtworkLayout");
        return null;
    }

    protected final void setMBackgroundArtworkLayout(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.mBackgroundArtworkLayout = imageView;
    }

    public final T getViewModel() {
        return (T) getMViewModelWrapper().getViewModel();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        logger.debug("SPL::onCreate");
        super.onCreate(savedInstanceState);
        setMContentViewManager(new SearchContentViewManager());
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        setMViewModelWrapper(new BrushViewModelWrapper<>(contextRequireContext, createViewModel(savedInstanceState)));
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.find.view.BaseBrushFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BaseBrushFragment.m1007onCreate$lambda0(this.f$0, (StyleSheet) obj);
            }
        });
        FragmentActivity activity = getActivity();
        MusicHomeActivity musicHomeActivity = activity instanceof MusicHomeActivity ? (MusicHomeActivity) activity : null;
        HashMap<String, String> hotSpotExperienceVault = musicHomeActivity != null ? musicHomeActivity.getHotSpotExperienceVault() : null;
        if (hotSpotExperienceVault == null) {
            hotSpotExperienceVault = new HashMap<>();
        }
        this.hotSpotExperienceVault = hotSpotExperienceVault;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m1007onCreate$lambda0(BaseBrushFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug("SPL::styleSheet loaded");
        this$0.mStyleSheet = styleSheet;
    }

    protected ViewGroup inflateLayout(LayoutInflater inflater, ViewGroup container) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.search_results, container, false);
        if (viewInflate != null) {
            return (ViewGroup) viewInflate;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        setRoot(inflateLayout(inflater, container));
        ViewGroup root = getRoot();
        View viewFindViewById = root.findViewById(R.id.loading_spinner);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "it.findViewById(R.id.loading_spinner)");
        setMLoadingView(viewFindViewById);
        getMLoadingView().setVisibility(0);
        View viewFindViewById2 = root.findViewById(R.id.swipeRefreshLayout);
        if (viewFindViewById2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.swiperefreshlayout.widget.SwipeRefreshLayout");
        }
        setMSwipeRefreshLayout((SwipeRefreshLayout) viewFindViewById2);
        getMSwipeRefreshLayout().setColorSchemeResources(R.color.white);
        getMSwipeRefreshLayout().setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.glass_2));
        getMSwipeRefreshLayout().setVisibility(0);
        if (shouldSwipeBeEnabled()) {
            getMSwipeRefreshLayout().setEnabled(true);
        }
        getMSwipeRefreshLayout().setRefreshing(false);
        getMSwipeRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.mp3.find.view.BaseBrushFragment$$ExternalSyntheticLambda1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                BaseBrushFragment.m1008onCreateView$lambda2$lambda1(this.f$0);
            }
        });
        View viewFindViewById3 = root.findViewById(R.id.recyclerView);
        if (viewFindViewById3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        }
        setMRecyclerView((RecyclerView) viewFindViewById3);
        View viewFindViewById4 = root.findViewById(R.id.empty_layout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "it.findViewById(R.id.empty_layout)");
        setMEmptyLayout(viewFindViewById4);
        getMEmptyLayout().setVisibility(8);
        Bundle arguments = getArguments();
        setMToolbarTitle(arguments == null ? null : arguments.getString(BrushFragmentNavigation.TOOLBAR_TITLE_KEY));
        View viewFindViewById5 = root.findViewById(R.id.artwork);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "it.findViewById(R.id.artwork)");
        setMBackgroundArtworkLayout((ImageView) viewFindViewById5);
        View viewFindViewById6 = root.findViewById(R.id.artworkOverlayLayout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "it.findViewById(R.id.artworkOverlayLayout)");
        this.mBackgroundArtworkOverlayLayout = (ViewGroup) viewFindViewById6;
        return root;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-2$lambda-1, reason: not valid java name */
    public static final void m1008onCreateView$lambda2$lambda1(BaseBrushFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handleSwipeRefresh();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        logger.debug("SPL::onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            setMBrushPageScrollHelper(new BrushPageScrollHelper(activity, getBrushScrollIdentifier()));
            setMContextMenuProvider(createContextMenuProvider(activity));
            setMRowButtonOnClickProvider(createRowButtonOnClickProvider(activity));
        }
        initDefaultViews();
        handlePageRefresh();
    }

    protected RowButtonOnClickProvider createRowButtonOnClickProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return new DefaultAddToLibraryProvider(activity, getMViewModelWrapper().getMLibraryStateProvider(), null, 4, null);
    }

    protected final void initDefaultViews() {
        this.mStandaloneTitle = getStandaloneTitle(getActivity(), getArguments());
        FragmentActivity activity = getActivity();
        MusicHomeActivity musicHomeActivity = activity instanceof MusicHomeActivity ? (MusicHomeActivity) activity : null;
        if (musicHomeActivity == null) {
            return;
        }
        String mStandaloneTitle = getMStandaloneTitle();
        if (mStandaloneTitle == null) {
            mStandaloneTitle = getMToolbarTitle();
        }
        setMActionBarView(createActionBarView(musicHomeActivity, mStandaloneTitle, null));
        musicHomeActivity.setHeaderView(getMActionBarView());
        musicHomeActivity.restoreHomeButton();
    }

    protected BauhausActionBarView createActionBarView(FragmentActivity fragmentActivity, String title, String subTitle) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        return new BauhausActionBarView(fragmentActivity, title, subTitle);
    }

    protected final String getStandaloneTitle(FragmentActivity fragmentActivity, Bundle bundle) {
        int i;
        Resources resources;
        if (bundle == null || !bundle.containsKey("com.amazon.mp3.STANDALONE_FRAGMENT_KEY") || (i = bundle.getInt("com.amazon.mp3.STANDALONE_FRAGMENT_TITLE_KEY", 0)) == 0 || !(fragmentActivity instanceof NavigationActivity) || (resources = ((NavigationActivity) fragmentActivity).getResources()) == null) {
            return null;
        }
        return resources.getString(i);
    }

    protected void handlePageRefresh() {
        getMViewModelWrapper().getPageData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.find.view.BaseBrushFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BaseBrushFragment.m1006handlePageRefresh$lambda6(this.f$0, (PageGridViewModel) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handlePageRefresh$lambda-6, reason: not valid java name */
    public static final void m1006handlePageRefresh$lambda6(BaseBrushFragment this$0, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean z = false;
        if (pageGridViewModel != null && pageGridViewModel.getPageIndex() == 0) {
            z = true;
        }
        this$0.updatePageModel(pageGridViewModel, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        logger.debug("SPL::onPause");
        sendUiContentViewEvent();
        storeScrollPositions();
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        logger.debug("SPL::onResume");
        super.onResume();
        getMViewModelWrapper().onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        Logger logger2 = logger;
        logger2.debug("SPL::onDestroy");
        FragmentActivity activity = getActivity();
        MusicHomeActivity musicHomeActivity = activity instanceof MusicHomeActivity ? (MusicHomeActivity) activity : null;
        if (musicHomeActivity != null) {
            musicHomeActivity.removeHeaderView();
        }
        BrowseViewsFactory.removeRegisteredSholverImageLoader(getContext());
        if (!this.mCompositeDisposable.isDisposed()) {
            logger2.debug("SPL::onDestroy. Disposing composite disposable...");
            this.mCompositeDisposable.dispose();
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        logger.debug("SPL::onDestroyView");
        stopImageRequests();
        storeScrollPositions();
        getMViewModelWrapper().getMDownloadStateModelProvider().unregisterDownloadStateModelListener();
        stopViewModelWrapper();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        logger.debug("SPL::onStop. Cleaning composite disposable...");
        this.mCompositeDisposable.clear();
        getMViewModelWrapper().getMDownloadStateModelProvider().unregisterDownloadStateModelListener();
        super.onStop();
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void setOnListViewCreatedListener(LibraryPagerAdapter.OnListViewCreatedListener listener) {
        this.mOnListViewCreatedListener = listener;
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void setupTouchListener(View.OnTouchListener listener) {
        getMRecyclerView().setOnTouchListener(listener);
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void scrollToTop() {
        getMRecyclerView().scrollToPosition(0);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuSupportingFragment
    public void openContextMenuForView(ContextMenuHandler contextMenuHandler, View view) {
        if (view == null) {
            return;
        }
        registerForContextMenu(view);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.openContextMenu(view);
        }
        unregisterForContextMenu(view);
    }

    @Override // androidx.fragment.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(v, "v");
        BrushContextMenuProvider brushContextMenuProvider = this.mContextMenuProvider;
        if (brushContextMenuProvider == null) {
            return;
        }
        brushContextMenuProvider.onCreateContextMenu(menu, "cirrus");
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        BrushContextMenuProvider brushContextMenuProvider = this.mContextMenuProvider;
        if (brushContextMenuProvider == null) {
            return false;
        }
        return brushContextMenuProvider.onContextMenuItemSelected(menuItem, "cirrus");
    }

    protected final void updatePageModel(PageGridViewModel pageGridViewModel, boolean isInitialPage) {
        Logger logger2 = logger;
        StringBuilder sb = new StringBuilder();
        sb.append("SPL::updatePageModel. isInitialPage: ");
        sb.append(isInitialPage);
        sb.append(", pageGridViewModelHashCode=");
        sb.append(pageGridViewModel == null ? null : Integer.valueOf(pageGridViewModel.hashCode()));
        logger2.debug(sb.toString());
        if (pageGridViewModel == null) {
            showEmptyView();
            afterRenderingCompleted(null);
            notifyUpdatePageModelCompleted(null);
            return;
        }
        PageGridViewModelContext context = pageGridViewModel.getContext();
        final FindPageGridViewModelContext findPageGridViewModelContext = context instanceof FindPageGridViewModelContext ? (FindPageGridViewModelContext) context : null;
        if (!hasValidBrushData(pageGridViewModel)) {
            markRenderingStarted(findPageGridViewModelContext);
            showEmptyView();
        } else {
            createDownloadStateModelProviderListener(pageGridViewModel);
            getMViewModelWrapper().getMDownloadStateModelProvider().registerDownloadStateModelListenerForTrack(this.mDownloadStateModelProviderListener);
            String title = pageGridViewModel.getTitle();
            this.mToolbarTitle = title;
            BauhausActionBarView bauhausActionBarView = this.mActionBarView;
            if (bauhausActionBarView != null) {
                String str = this.mStandaloneTitle;
                if (str != null) {
                    title = str;
                }
                bauhausActionBarView.setTitles(title, pageGridViewModel.getSubTitle());
            }
            BauhausActionBarView bauhausActionBarView2 = this.mActionBarView;
            if (bauhausActionBarView2 instanceof BauhausFilterActionBarView) {
                if (bauhausActionBarView2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.actionbar.view.BauhausFilterActionBarView");
                }
                setupActionBarPills((BauhausFilterActionBarView) bauhausActionBarView2);
            }
            markRenderingStarted(findPageGridViewModelContext);
            setupBrushViews(pageGridViewModel, isInitialPage);
            getMViewModelWrapper().refreshModelStateProviders();
            getMSwipeRefreshLayout().setRefreshing(false);
        }
        RecyclerView mRecyclerView = getMRecyclerView();
        if (!ViewCompat.isLaidOut(mRecyclerView) || mRecyclerView.isLayoutRequested()) {
            mRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.amazon.mp3.find.view.BaseBrushFragment$updatePageModel$$inlined$doOnLayout$1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.removeOnLayoutChangeListener(this);
                    this.this$0.markRenderingCompleted(findPageGridViewModelContext);
                    this.this$0.notifyUpdatePageModelCompleted(findPageGridViewModelContext);
                }
            });
        } else {
            markRenderingCompleted(findPageGridViewModelContext);
            notifyUpdatePageModelCompleted(findPageGridViewModelContext);
        }
        afterRenderingCompleted(findPageGridViewModelContext);
    }

    private final void createDownloadStateModelProviderListener(final PageGridViewModel pageGridViewModel) {
        this.mDownloadStateModelProviderListener = new GroupDownloadStateModelProvider.BauhausDefaultListener() { // from class: com.amazon.mp3.find.view.BaseBrushFragment.createDownloadStateModelProviderListener.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(false, 1, null);
            }

            @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.BauhausDefaultListener
            /* JADX INFO: renamed from: getPageModel, reason: from getter */
            public PageGridViewModel get$pageGridViewModel() {
                return pageGridViewModel;
            }

            @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.BauhausDefaultListener
            public PageStateManager getPageStateManager() {
                return this.getMViewModelWrapper().getMPageStateManager();
            }
        };
    }

    protected final void showEmptyView() {
        logger.debug("SPL::showEmptyView");
        getMLoadingView().setVisibility(8);
        if (shouldErrorLayoutBeVisible()) {
            getMEmptyLayout().setVisibility(0);
        } else {
            getMRecyclerView().setVisibility(8);
        }
    }

    protected boolean hasValidBrushData(PageGridViewModel pageModel) {
        List<BaseViewModel> models;
        if (pageModel == null || (models = pageModel.getModels()) == null) {
            return false;
        }
        return !models.isEmpty();
    }

    protected final void stopImageRequests() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        getMRecyclerView().setOnScrollListener(null);
        Picasso.get().cancelTag(context);
    }

    protected final void stopViewModelWrapper() {
        BrushViewModelWrapper<T> mViewModelWrapper = getMViewModelWrapper();
        if (mViewModelWrapper == null) {
            return;
        }
        mViewModelWrapper.onCleared();
    }

    protected final void handleDeepLink(String link) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        Context context;
        if (link == null || (context = getContext()) == null) {
            return;
        }
        DeeplinksManager.get(context).handle(Uri.parse(link));
    }

    protected final void handleBrushUri(String uri, String nextToken, Bundle bundle) {
        if (uri == null) {
            return;
        }
        BrushFragment brushFragmentCreateFragment = new BrushFragmentNavigation.Builder(uri).withToken(nextToken).withBundle(bundle).createFragment();
        MusicHomeActivity musicHomeActivity = (MusicHomeActivity) getActivity();
        Intrinsics.checkNotNull(musicHomeActivity);
        musicHomeActivity.changeScreenFragment(brushFragmentCreateFragment, true, true, true);
    }

    protected final void setupBrushViews(PageGridViewModel pageGridViewModel, boolean isInitialPage) {
        Intrinsics.checkNotNullParameter(pageGridViewModel, "pageGridViewModel");
        logger.debug("SPL::setupBrushViews. isInitialPage=" + isInitialPage + ", pageGridViewModel=" + ((Object) pageGridViewModel.toString()));
        if (this.mStyleSheet == null) {
            return;
        }
        List<BaseViewModel> models = pageGridViewModel.getModels();
        PageGridViewModelContext context = pageGridViewModel.getContext();
        FindPageGridViewModelContext findPageGridViewModelContext = context instanceof FindPageGridViewModelContext ? (FindPageGridViewModelContext) context : null;
        ExecutionContext executionContext = findPageGridViewModelContext != null ? findPageGridViewModelContext.getExecutionContext() : null;
        boolean z = executionContext != null && executionContext.getResetScrollPosition();
        BrushViews brushViews = this.mBrushViews;
        if (brushViews != null && !isInitialPage) {
            if (brushViews == null) {
                return;
            }
            brushViews.updateAdapter(models);
            return;
        }
        BrushViews brushViewsCreateBrushViews = createBrushViews(pageGridViewModel);
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        BrushViews.createRootView$default(brushViewsCreateBrushViews, userSubscription, models, pageGridViewModel.getTileType(), getMRecyclerView(), getMSwipeRefreshLayout(), null, 32, null);
        this.mBrushViews = brushViewsCreateBrushViews;
        getMLoadingView().setVisibility(8);
        getMRecyclerView().setVisibility(0);
        setupScrollPosition(Boolean.valueOf(z));
        LibraryPagerAdapter.OnListViewCreatedListener mOnListViewCreatedListener = getMOnListViewCreatedListener();
        if (mOnListViewCreatedListener != null) {
            mOnListViewCreatedListener.onListViewCreated(getMRecyclerView());
        }
        Long invalidateRecycleViewDelayedDuration = getInvalidateRecycleViewDelayedDuration();
        if (invalidateRecycleViewDelayedDuration == null) {
            return;
        }
        getMRecyclerView().postInvalidateDelayed(invalidateRecycleViewDelayedDuration.longValue());
    }

    protected final void sendUiContentViewEvent() {
        if (getUserVisibleHint()) {
            logger.debug("SPL::sendUiContentViewEvent");
            getMContentViewManager().sendUiContentViewEvent();
        }
    }

    protected final void setupScrollPosition(Boolean resetScrollPosition) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getMRecyclerView().getLayoutManager();
        if (linearLayoutManager == null) {
            return;
        }
        if (resetScrollPosition != null && resetScrollPosition.booleanValue()) {
            linearLayoutManager.scrollToPosition(0);
            return;
        }
        Pair<Integer, Integer> lastScrollPosition = getMBrushPageScrollHelper().getLastScrollPosition();
        Object obj = lastScrollPosition.first;
        Intrinsics.checkNotNullExpressionValue(obj, "lastScrollPosition.first");
        if (((Number) obj).intValue() >= 0) {
            Object obj2 = lastScrollPosition.first;
            Intrinsics.checkNotNullExpressionValue(obj2, "lastScrollPosition.first");
            int iIntValue = ((Number) obj2).intValue();
            Object obj3 = lastScrollPosition.second;
            Intrinsics.checkNotNullExpressionValue(obj3, "lastScrollPosition.second");
            linearLayoutManager.scrollToPositionWithOffset(iIntValue, ((Number) obj3).intValue());
        }
    }

    protected final void handleSwipeRefresh() {
        BrushViewModelWrapper<T> mViewModelWrapper = getMViewModelWrapper();
        if (mViewModelWrapper == null) {
            return;
        }
        getMBrushPageScrollHelper().reset();
        mViewModelWrapper.onRefresh();
    }

    protected final void storeScrollPositions() {
        if (getMRecyclerView() == null) {
            return;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getMRecyclerView().getLayoutManager();
        if (linearLayoutManager != null) {
            int iFindFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            View childAt = getMRecyclerView().getChildAt(0);
            if (iFindFirstVisibleItemPosition == -1) {
                iFindFirstVisibleItemPosition = 0;
            }
            if (childAt != null) {
                int top = childAt.getTop();
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
                i = top - (marginLayoutParams != null ? marginLayoutParams.topMargin : 0);
            }
            BrushPageScrollHelper mBrushPageScrollHelper = getMBrushPageScrollHelper();
            if (mBrushPageScrollHelper == null) {
                return;
            }
            mBrushPageScrollHelper.saveCurrentScrollPostition(iFindFirstVisibleItemPosition, i);
            return;
        }
        BrushPageScrollHelper mBrushPageScrollHelper2 = getMBrushPageScrollHelper();
        if (mBrushPageScrollHelper2 == null) {
            return;
        }
        mBrushPageScrollHelper2.reset();
    }

    protected final void addDisposable(Disposable disposable) {
        if (disposable == null) {
            return;
        }
        this.mCompositeDisposable.add(disposable);
    }

    protected final void markRenderingStarted(FindPageGridViewModelContext pageGridViewModelContext) {
        ExecutionContext executionContext;
        if (pageGridViewModelContext == null || (executionContext = pageGridViewModelContext.getExecutionContext()) == null) {
            return;
        }
        executionContext.markRenderingStarted();
    }

    protected final void markRenderingCompleted(FindPageGridViewModelContext pageGridViewModelContext) {
        ExecutionContext executionContext;
        if (pageGridViewModelContext == null || (executionContext = pageGridViewModelContext.getExecutionContext()) == null) {
            return;
        }
        executionContext.markRenderingCompleted();
    }

    protected void notifyUpdatePageModelCompleted(FindPageGridViewModelContext pageGridViewModelContext) {
        if (pageGridViewModelContext == null) {
            return;
        }
        getViewModel().completeOperation(pageGridViewModelContext.getExecutionContext());
    }
}
