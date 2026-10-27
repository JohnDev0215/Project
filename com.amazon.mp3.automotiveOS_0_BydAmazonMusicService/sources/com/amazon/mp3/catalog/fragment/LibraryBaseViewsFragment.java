package com.amazon.mp3.catalog.fragment;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.detailpages.refinement.RefinementsMapping;
import com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener;
import com.amazon.mp3.fragment.viewmodel.LibrarySyncStateUpdater;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.RepositoryEvent;
import com.amazon.mp3.library.refinement.sort.DbSort;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.offline.OfflineStoragePermissionHandler;
import com.amazon.mp3.platform.providers.LibrarySyncStateProvider;
import com.amazon.mp3.prime.cta.AddRemovePrimeManager;
import com.amazon.mp3.providers.DefaultAddToLibraryProvider;
import com.amazon.mp3.providers.LibraryContextMenuProvider;
import com.amazon.mp3.providers.MetadataContextMenuHandler;
import com.amazon.mp3.providers.MissingSDCardHandler;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.views.BauhausLoadingView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: compiled from: LibraryBaseViewsFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000 o2\u00020\u0001:\u0001oB\u0007¢\u0006\u0004\bm\u0010nJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\n\u0010\t\u001a\u0004\u0018\u00010\bH\u0002J\n\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0014J\b\u0010\u0011\u001a\u00020\u0006H\u0014J\u0012\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0016J\b\u0010\u0016\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0012H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0014J\u0010\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u0019H\u0004J\b\u0010\u001d\u001a\u00020\u0006H\u0014J\u0006\u0010\u001e\u001a\u00020\u0006J\n\u0010 \u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u0010#\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020!H\u0014J\u0010\u0010$\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020!H\u0014J\b\u0010%\u001a\u00020\u000eH\u0004J\b\u0010&\u001a\u00020\u0006H\u0014J\b\u0010'\u001a\u00020\u0006H\u0014J\u0010\u0010)\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0014J\u0010\u0010*\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020!H\u0014J\u0012\u0010,\u001a\u0004\u0018\u00010\u00042\u0006\u0010+\u001a\u00020\u0002H\u0014J\b\u0010-\u001a\u00020\u0006H$J\b\u0010.\u001a\u00020\u000eH\u0014J\u0010\u0010/\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u0002H\u0016J\u0014\u00103\u001a\u0004\u0018\u0001022\b\u00101\u001a\u0004\u0018\u000100H\u0014J\u0011\u00105\u001a\u0004\u0018\u000104H\u0014¢\u0006\u0004\b5\u00106J\b\u00107\u001a\u00020\u0006H\u0014J\b\u00108\u001a\u00020\u0006H\u0014J\b\u00109\u001a\u00020\u000eH\u0004J\b\u0010:\u001a\u00020\u000eH\u0004J\b\u0010;\u001a\u00020\u000eH\u0014J\n\u0010=\u001a\u0004\u0018\u00010<H\u0016J\b\u0010>\u001a\u00020\u0006H\u0016J\b\u0010?\u001a\u0004\u0018\u00010\u0019J\b\u0010@\u001a\u00020\u000eH\u0004R.\u0010C\u001a\u0004\u0018\u00010A2\b\u0010B\u001a\u0004\u0018\u00010A8\u0014@VX\u0094\u000e¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR$\u0010I\u001a\u0004\u0018\u00010!8\u0014@\u0014X\u0094\u000e¢\u0006\u0012\n\u0004\bI\u0010J\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR$\u0010O\u001a\u0004\u0018\u00010\u00198\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\"\u0010V\u001a\u00020U8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\bV\u0010W\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R$\u0010]\u001a\u0004\u0018\u00010\\8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b]\u0010^\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\u0018\u0010d\u001a\u0004\u0018\u00010c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bd\u0010eR\"\u0010g\u001a\u00020f8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bg\u0010h\u001a\u0004\bi\u0010j\"\u0004\bk\u0010l¨\u0006p"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/LibraryBaseViewsFragment;", "Lcom/amazon/mp3/catalog/fragment/AbstractBaseViewsFragment;", "Landroidx/fragment/app/FragmentActivity;", "fragmentActivity", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "getDefaultAddToLibraryProvider", "", "shouldBlockForInitialSync", "Lcom/amazon/music/views/library/models/FilterItemModel;", "currentSortModel", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "getRefinementListModel", "Landroid/content/Context;", "context", "", "onAttach", "shouldSupportRemoveTrackFromPlaylist", "shouldSupportDeletePlaylist", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "onPause", "onDestroy", "outState", "onSaveInstanceState", "", "getSourceId", "updatedSource", "updateSource", "shouldUseCachedSource", "isSourceLocal", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "getContextMenuProvider", "Landroid/net/Uri;", "trackUri", "onTrackAdded", "onTrackRemoved", "syncPendingAddedTracks", "shouldRemoveGoToAlbum", "shouldRemoveDownloadForTracks", "isVideoTrack", "shouldRemoveVideoPlayForTracks", "removeTrackFromUserPlaylist", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "getRowButtonOnClickProvider", "isDisplayingOnlyLibraryContent", "registerLiveDataObservers", "initPageModelProvider", "Landroid/view/ViewGroup;", "root", "Landroid/view/View;", "getEmptyChildView", "", "getEmptyLayoutID", "()Ljava/lang/Integer;", "requireNetworkConnection", "shouldBlockDataFetch", "initLibraryStateObserver", "initLocalRepositoryUpdatesObserver", "showLoadingView", "Lcom/amazon/mp3/providers/MissingSDCardHandler;", "getMissingSDCardProvider", "isRefinementFilterApplied", "refinementSortOrder", "checkOfflineStoragePermissions", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "<set-?>", "pageModelProvider", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "getPageModelProvider", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "setPageModelProvider", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;)V", "contentUri", "Landroid/net/Uri;", "getContentUri", "()Landroid/net/Uri;", "setContentUri", "(Landroid/net/Uri;)V", "sourceIdString", "Ljava/lang/String;", "getSourceIdString", "()Ljava/lang/String;", "setSourceIdString", "(Ljava/lang/String;)V", "Lcom/amazon/mp3/fragment/contextmenu/AbstractTrackContextMenuListener;", "trackContextMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/AbstractTrackContextMenuListener;", "getTrackContextMenuListener", "()Lcom/amazon/mp3/fragment/contextmenu/AbstractTrackContextMenuListener;", "setTrackContextMenuListener", "(Lcom/amazon/mp3/fragment/contextmenu/AbstractTrackContextMenuListener;)V", "Lcom/amazon/mp3/platform/providers/LibrarySyncStateProvider$LibrarySyncState;", "currentLibraryState", "Lcom/amazon/mp3/platform/providers/LibrarySyncStateProvider$LibrarySyncState;", "getCurrentLibraryState", "()Lcom/amazon/mp3/platform/providers/LibrarySyncStateProvider$LibrarySyncState;", "setCurrentLibraryState", "(Lcom/amazon/mp3/platform/providers/LibrarySyncStateProvider$LibrarySyncState;)V", "Lcom/amazon/mp3/activity/settings/LastViewedScreenUtil$LastViewedScreen;", "lastViewedScreen", "Lcom/amazon/mp3/activity/settings/LastViewedScreenUtil$LastViewedScreen;", "Lrx/subscriptions/CompositeSubscription;", "subscriptions", "Lrx/subscriptions/CompositeSubscription;", "getSubscriptions", "()Lrx/subscriptions/CompositeSubscription;", "setSubscriptions", "(Lrx/subscriptions/CompositeSubscription;)V", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class LibraryBaseViewsFragment extends AbstractBaseViewsFragment {
    private Uri contentUri;
    private LibrarySyncStateProvider.LibrarySyncState currentLibraryState;
    private LastViewedScreenUtil.LastViewedScreen lastViewedScreen;
    private PageViewModelProvider pageModelProvider;
    private String sourceIdString;
    private CompositeSubscription subscriptions = new CompositeSubscription();
    protected AbstractTrackContextMenuListener trackContextMenuListener;

    protected abstract boolean isDisplayingOnlyLibraryContent();

    protected void onTrackAdded(Uri trackUri) {
        Intrinsics.checkNotNullParameter(trackUri, "trackUri");
    }

    protected void removeTrackFromUserPlaylist(Uri trackUri) {
        Intrinsics.checkNotNullParameter(trackUri, "trackUri");
    }

    protected boolean shouldRemoveDownloadForTracks() {
        return false;
    }

    protected boolean shouldRemoveGoToAlbum() {
        return false;
    }

    protected boolean shouldRemoveVideoPlayForTracks(boolean isVideoTrack) {
        return true;
    }

    protected boolean shouldSupportDeletePlaylist() {
        return false;
    }

    protected boolean shouldSupportRemoveTrackFromPlaylist() {
        return false;
    }

    protected boolean shouldUseCachedSource() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageViewModelProvider getPageModelProvider() {
        return this.pageModelProvider;
    }

    public void setPageModelProvider(PageViewModelProvider pageViewModelProvider) {
        this.pageModelProvider = pageViewModelProvider;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Uri getContentUri() {
        return this.contentUri;
    }

    protected void setContentUri(Uri uri) {
        this.contentUri = uri;
    }

    protected final String getSourceIdString() {
        return this.sourceIdString;
    }

    protected final void setSourceIdString(String str) {
        this.sourceIdString = str;
    }

    protected final AbstractTrackContextMenuListener getTrackContextMenuListener() {
        AbstractTrackContextMenuListener abstractTrackContextMenuListener = this.trackContextMenuListener;
        if (abstractTrackContextMenuListener != null) {
            return abstractTrackContextMenuListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("trackContextMenuListener");
        return null;
    }

    protected final void setTrackContextMenuListener(AbstractTrackContextMenuListener abstractTrackContextMenuListener) {
        Intrinsics.checkNotNullParameter(abstractTrackContextMenuListener, "<set-?>");
        this.trackContextMenuListener = abstractTrackContextMenuListener;
    }

    protected final void setCurrentLibraryState(LibrarySyncStateProvider.LibrarySyncState librarySyncState) {
        this.currentLibraryState = librarySyncState;
    }

    protected final CompositeSubscription getSubscriptions() {
        return this.subscriptions;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        setTrackContextMenuListener(new AbstractTrackContextMenuListener(context, this) { // from class: com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment.onAttach.1
            final /* synthetic */ Context $context;
            final /* synthetic */ LibraryBaseViewsFragment this$0;

            @Override // com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener, com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider.OnRemoveLibraryContentListener
            public void onRemoveContentConfirm() {
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(context, null);
                this.$context = context;
                this.this$0 = this;
            }

            @Override // com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener
            protected boolean shouldRemoveShowAlbumDetail() {
                return this.this$0.shouldRemoveGoToAlbum();
            }

            @Override // com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener, com.amazon.mp3.fragment.contextmenu.OnAddLibraryContentListener
            public void onAddContentComplete(Uri uri) {
                Intrinsics.checkNotNullParameter(uri, "uri");
                this.this$0.onTrackAdded(uri);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener, com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider.OnRemoveLibraryContentListener
            public void onRemoveContentComplete(Uri trackUri) {
                Intrinsics.checkNotNullParameter(trackUri, "trackUri");
                this.this$0.onTrackRemoved(trackUri);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener
            protected void removeItemRemoveFromPlaylist(ContextMenu menu) {
                Intrinsics.checkNotNullParameter(menu, "menu");
                if (this.this$0.shouldSupportRemoveTrackFromPlaylist()) {
                    return;
                }
                super.removeItemRemoveFromPlaylist(menu);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener
            protected void removeFromPlaylist(Uri trackUri) {
                Intrinsics.checkNotNullParameter(trackUri, "trackUri");
                this.this$0.removeTrackFromUserPlaylist(trackUri);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener, com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
            public boolean shouldRemoveTrackDownloadItem() {
                return this.this$0.shouldRemoveDownloadForTracks();
            }

            @Override // com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener
            protected boolean shouldRemoveVideoPlay(boolean isVideoTrack) {
                return this.this$0.shouldRemoveVideoPlayForTracks(isVideoTrack);
            }
        });
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        LastViewedScreenUtil.LastViewedScreen lastViewedScreen = null;
        setContentUri(arguments == null ? null : (Uri) arguments.getParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI"));
        if (savedInstanceState != null && savedInstanceState.containsKey("com.amazon.mp3.library.EXTRA_SCREEN_VIEWED")) {
            String string = savedInstanceState.getString("com.amazon.mp3.library.EXTRA_SCREEN_VIEWED");
            if (string != null) {
                lastViewedScreen = LastViewedScreenUtil.LastViewedScreen.valueOf(string);
            }
        } else {
            lastViewedScreen = LastViewedScreenUtil.getLastViewedScreen(AmazonApplication.getApplication().getApplicationContext());
        }
        this.lastViewedScreen = lastViewedScreen;
        this.sourceIdString = getSourceId();
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        AddRemovePrimeManager addRemovePrimeManager = AddRemovePrimeManager.getInstance(getActivity());
        if (addRemovePrimeManager.isAddQueueEmpty() && addRemovePrimeManager.isRemoveQueueEmpty()) {
            return;
        }
        SyncService.startSync(getActivity(), 1042);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.subscriptions.unsubscribe();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        String strName;
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        LastViewedScreenUtil.LastViewedScreen lastViewedScreen = this.lastViewedScreen;
        if (lastViewedScreen == null || (strName = lastViewedScreen.name()) == null) {
            return;
        }
        outState.putString("com.amazon.mp3.library.EXTRA_SCREEN_VIEWED", strName);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected String getSourceId() {
        String source;
        String str = this.sourceIdString;
        if (str != null) {
            return str;
        }
        Bundle arguments = getArguments();
        String cirrusMediaSource = null;
        String string = arguments == null ? null : arguments.getString("com.amazon.mp3.library.EXTRA_SOURCE_ID");
        Uri contentUri = getContentUri();
        if (contentUri == null || (source = MediaProvider.getSource(contentUri)) == null) {
            source = "cirrus";
        }
        if (shouldUseCachedSource()) {
            LastViewedScreenUtil.LastViewedSource lastViewedSource = LastViewedScreenUtil.getLastViewedSource(getContext(), LastViewedScreenUtil.LastViewedSource.CLOUD);
            if (lastViewedSource != null) {
                cirrusMediaSource = lastViewedSource.toCirrusMediaSource();
            }
        } else {
            cirrusMediaSource = "cirrus";
        }
        return (Intrinsics.areEqual(cirrusMediaSource, "cirrus-local") || Intrinsics.areEqual(source, "cirrus-local") || Intrinsics.areEqual(string, "cirrus-local")) ? "cirrus-local" : "cirrus";
    }

    protected final void updateSource(String updatedSource) {
        Intrinsics.checkNotNullParameter(updatedSource, "updatedSource");
        this.sourceIdString = updatedSource;
        LastViewedScreenUtil.LastViewedSource lastViewedSourceFromCirrusMediaSource = LastViewedScreenUtil.LastViewedSource.fromCirrusMediaSource(updatedSource);
        if (getContext() != null) {
            LastViewedScreenUtil.setLastViewedSource(getContext(), lastViewedSourceFromCirrusMediaSource);
        }
        if (isSourceLocal()) {
            checkOfflineStoragePermissions();
        }
    }

    public final boolean isSourceLocal() {
        return Intrinsics.areEqual(this.sourceIdString, "cirrus-local");
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MetadataContextMenuHandler getContextMenuProvider() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        if (getContextMenuHandler() == null) {
            setContextMenuHandler(new LibraryContextMenuProvider(activity, this, getPageType(), getTrackContextMenuListener(), null, null, null, LifecycleOwnerKt.getLifecycleScope(this), 112, null));
        }
        return getContextMenuHandler();
    }

    protected void onTrackRemoved(Uri trackUri) {
        Intrinsics.checkNotNullParameter(trackUri, "trackUri");
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        if (pageModelProvider == null) {
            return;
        }
        PageViewModelProvider.DefaultImpls.fetchUpdates$default(pageModelProvider, 20, getDiffUtilFactory(), null, 4, null);
    }

    protected final void syncPendingAddedTracks() {
        AddRemovePrimeManager addRemovePrimeManager = AddRemovePrimeManager.getInstance(getActivity());
        if (addRemovePrimeManager.isAddQueueEmpty() && addRemovePrimeManager.isRemoveQueueEmpty()) {
            return;
        }
        SyncService.startSync(getActivity(), 1042);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected RowButtonOnClickProvider getRowButtonOnClickProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (getRowButtonOnClickProvider() == null) {
            setRowButtonOnClickProvider(getDefaultAddToLibraryProvider(activity));
        }
        return getRowButtonOnClickProvider();
    }

    private final RowButtonOnClickProvider getDefaultAddToLibraryProvider(FragmentActivity fragmentActivity) {
        return new DefaultAddToLibraryProvider(fragmentActivity, getLibraryStateProviderInstance(), getTrackContextMenuListener());
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void registerLiveDataObservers() {
        super.registerLiveDataObservers();
        initLibraryStateObserver();
        initLocalRepositoryUpdatesObserver();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void initPageModelProvider(FragmentActivity activity) {
        PageViewModelProvider viewModel;
        Intrinsics.checkNotNullParameter(activity, "activity");
        PageDataRepository pageDataRepository = getPageDataRepository();
        if (pageDataRepository == null) {
            viewModel = null;
        } else {
            PageViewModelFactoryProvider pageViewModelFactoryProvider = PageViewModelFactoryProvider.INSTANCE;
            Application application = activity.getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "activity.application");
            viewModel = pageViewModelFactoryProvider.getViewModel(this, application, pageDataRepository);
        }
        setPageModelProvider(viewModel);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected View getEmptyChildView(ViewGroup root) {
        Integer emptyLayoutID = getEmptyLayoutID();
        if (emptyLayoutID == null) {
            return null;
        }
        return View.inflate(getActivity(), emptyLayoutID.intValue(), null);
    }

    protected Integer getEmptyLayoutID() {
        return isSourceLocal() ? Integer.valueOf(R.layout.library_empty_local) : Integer.valueOf(R.layout.library_empty_cirrus);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean requireNetworkConnection() {
        return shouldBlockForInitialSync();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean shouldBlockDataFetch() {
        return shouldBlockForInitialSync() || super.shouldBlockDataFetch();
    }

    private final boolean shouldBlockForInitialSync() {
        LibrarySyncStateProvider.LibrarySyncState librarySyncState = this.currentLibraryState;
        return (librarySyncState == null ? false : librarySyncState.isRunningInitialSync()) && isDisplayingOnlyLibraryContent();
    }

    protected final void initLibraryStateObserver() {
        Context applicationContext;
        Context context = getContext();
        if (context == null || (applicationContext = context.getApplicationContext()) == null) {
            return;
        }
        setCurrentLibraryState(LibrarySyncStateProvider.INSTANCE.getLibraryState(applicationContext));
        final PageViewModelProvider pageModelProvider = getPageModelProvider();
        if (pageModelProvider == null) {
            return;
        }
        pageModelProvider.getLibrarySyncStateLiveData().removeObservers(this);
        pageModelProvider.getLibrarySyncStateLiveData().observe(this, new Observer() { // from class: com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LibraryBaseViewsFragment.m498initLibraryStateObserver$lambda11$lambda10$lambda8(this.f$0, (LibrarySyncStateProvider.LibrarySyncState) obj);
            }
        });
        pageModelProvider.getLibrarySyncEventLiveData().removeObservers(this);
        pageModelProvider.getLibrarySyncEventLiveData().observe(this, new Observer() { // from class: com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LibraryBaseViewsFragment.m499initLibraryStateObserver$lambda11$lambda10$lambda9(this.f$0, pageModelProvider, (LibrarySyncStateUpdater.LibrarySyncEvent) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initLibraryStateObserver$lambda-11$lambda-10$lambda-8, reason: not valid java name */
    public static final void m498initLibraryStateObserver$lambda11$lambda10$lambda8(LibraryBaseViewsFragment this$0, LibrarySyncStateProvider.LibrarySyncState librarySyncState) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.currentLibraryState = librarySyncState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initLibraryStateObserver$lambda-11$lambda-10$lambda-9, reason: not valid java name */
    public static final void m499initLibraryStateObserver$lambda11$lambda10$lambda9(LibraryBaseViewsFragment this$0, PageViewModelProvider pageModelProvider, LibrarySyncStateUpdater.LibrarySyncEvent librarySyncEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(pageModelProvider, "$pageModelProvider");
        if (this$0.isDisplayingOnlyLibraryContent()) {
            if (this$0.hasFetchedData()) {
                pageModelProvider.fetchUpdates(this$0.getPageSize(), this$0.getDiffUtilFactory(), FetchType.SYNC);
            } else if (librarySyncEvent == LibrarySyncStateUpdater.LibrarySyncEvent.INITIAL_LIBRARY_SYNC_COMPLETED) {
                this$0.getInitialData(this$0.getPageSize());
            }
        }
    }

    protected final void initLocalRepositoryUpdatesObserver() {
        final PageViewModelProvider pageModelProvider = getPageModelProvider();
        if (pageModelProvider == null) {
            return;
        }
        LiveData<RepositoryEvent> localRepositoryUpdateEventLiveData = pageModelProvider.getLocalRepositoryUpdateEventLiveData();
        localRepositoryUpdateEventLiveData.removeObservers(this);
        localRepositoryUpdateEventLiveData.observe(this, new Observer() { // from class: com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LibraryBaseViewsFragment.m500initLocalRepositoryUpdatesObserver$lambda13$lambda12(pageModelProvider, this, (RepositoryEvent) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initLocalRepositoryUpdatesObserver$lambda-13$lambda-12, reason: not valid java name */
    public static final void m500initLocalRepositoryUpdatesObserver$lambda13$lambda12(PageViewModelProvider pageModelProvider, LibraryBaseViewsFragment this$0, RepositoryEvent repositoryEvent) {
        Intrinsics.checkNotNullParameter(pageModelProvider, "$pageModelProvider");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        pageModelProvider.fetchUpdates(this$0.getPageSize(), this$0.getDiffUtilFactory(), FetchType.UPDATE);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void showLoadingView() {
        Resources resources;
        String string;
        if (shouldBlockForInitialSync()) {
            View loadingView = getLoadingView();
            BauhausLoadingView bauhausLoadingView = loadingView instanceof BauhausLoadingView ? (BauhausLoadingView) loadingView : null;
            if (bauhausLoadingView == null) {
                return;
            }
            Context context = getContext();
            String str = "";
            if (context != null && (resources = context.getResources()) != null && (string = resources.getString(R.string.dmusic_library_no_listiew_items_syncing)) != null) {
                str = string;
            }
            bauhausLoadingView.setText(str);
            return;
        }
        super.showLoadingView();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MissingSDCardHandler getMissingSDCardProvider() {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        return new MissingSDCardHandler(context);
    }

    public boolean isRefinementFilterApplied() {
        List<FilterModel> filterModels;
        ExposedRefinementListModel refinementListModel = getRefinementListModel();
        if (refinementListModel == null || (filterModels = refinementListModel.getFilterModels()) == null || filterModels.isEmpty()) {
            return false;
        }
        Iterator<T> it = filterModels.iterator();
        while (it.hasNext()) {
            if (((FilterModel) it.next()).isSelected()) {
                return true;
            }
        }
        return false;
    }

    public final String refinementSortOrder() {
        FilterItemModel filterItemModelCurrentSortModel = currentSortModel();
        if (filterItemModelCurrentSortModel == null) {
            return null;
        }
        DbSort<MusicTrack> dbSort = RefinementsMapping.INSTANCE.getTrackSortMapping().get(RefinementFilterType.INSTANCE.getRefinementTypeFromDisplayName(filterItemModelCurrentSortModel.getTitle()));
        if (dbSort == null) {
            return null;
        }
        return dbSort.getColumnName();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0048 A[EDGE_INSN: B:34:0x0048->B:24:0x0048 BREAK  A[LOOP:0: B:9:0x0014->B:37:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[LOOP:0: B:9:0x0014->B:37:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.models.FilterItemModel currentSortModel() {
        /*
            r6 = this;
            com.amazon.music.views.library.models.ExposedRefinementListModel r0 = r6.getRefinementListModel()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            java.util.List r0 = r0.getFilterModels()
            if (r0 != 0) goto L10
            r2 = r1
            goto L4a
        L10:
            java.util.Iterator r0 = r0.iterator()
        L14:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L47
            java.lang.Object r2 = r0.next()
            r3 = r2
            com.amazon.music.views.library.models.FilterModel r3 = (com.amazon.music.views.library.models.FilterModel) r3
            boolean r4 = r3.isCollection()
            if (r4 == 0) goto L43
            com.amazon.music.views.library.models.FilterCollectionModel r3 = r3.getCollectionModel()
            if (r3 != 0) goto L2f
            r3 = r1
            goto L33
        L2f:
            java.lang.String r3 = r3.getTitle()
        L33:
            com.amazon.mp3.view.refinements.RefinementFilterType$Companion r4 = com.amazon.mp3.view.refinements.RefinementFilterType.INSTANCE
            com.amazon.mp3.view.refinements.RefinementFilterType r5 = com.amazon.mp3.view.refinements.RefinementFilterType.SORT
            java.lang.String r4 = r4.getDisplayName(r5)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            if (r3 == 0) goto L43
            r3 = 1
            goto L44
        L43:
            r3 = 0
        L44:
            if (r3 == 0) goto L14
            goto L48
        L47:
            r2 = r1
        L48:
            com.amazon.music.views.library.models.FilterModel r2 = (com.amazon.music.views.library.models.FilterModel) r2
        L4a:
            boolean r0 = r2 instanceof com.amazon.music.views.library.models.FilterCollectionModel
            if (r0 == 0) goto L51
            com.amazon.music.views.library.models.FilterCollectionModel r2 = (com.amazon.music.views.library.models.FilterCollectionModel) r2
            goto L52
        L51:
            r2 = r1
        L52:
            if (r2 != 0) goto L55
            goto L59
        L55:
            com.amazon.music.views.library.models.FilterItemModel r1 = r2.getSelectedSortingFilterModel()
        L59:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment.currentSortModel():com.amazon.music.views.library.models.FilterItemModel");
    }

    private final ExposedRefinementListModel getRefinementListModel() {
        List<BaseViewModel> models;
        PageGridViewModel pageModel = getPageModel();
        if (pageModel == null || (models = pageModel.getModels()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : models) {
            if (obj instanceof ExposedRefinementListModel) {
                arrayList.add(obj);
            }
        }
        return (ExposedRefinementListModel) CollectionsKt___CollectionsKt.getOrNull(arrayList, 0);
    }

    protected final void checkOfflineStoragePermissions() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        OfflineStoragePermissionHandler.INSTANCE.displayPermissionRequestIfNeeded(activity);
    }
}
