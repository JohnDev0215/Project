package com.amazon.mp3.playlist;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.ErrorDialogButton;
import com.amazon.mp3.dialog.OneButtonErrorDialog;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.prime.browse.PrimeTabFragmentHost;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityPlaylistFetchFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 ?2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001?B\u0005¢\u0006\u0002\u0010\u0004J\n\u0010\u0011\u001a\u0004\u0018\u00010\nH\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u000f\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016¢\u0006\u0002\u0010\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J\n\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001dH\u0002J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\u001dH\u0002J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020&H\u0002J\u0012\u0010'\u001a\u00020\u001d2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0012\u0010*\u001a\u00020\u001d2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J&\u0010+\u001a\u0004\u0018\u00010\u000e2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u00102\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010/\u001a\u00020\u001dH\u0016J\b\u00100\u001a\u00020\u001dH\u0016J\b\u00101\u001a\u00020\u001dH\u0016J\u0012\u00102\u001a\u00020\u001d2\b\u00103\u001a\u0004\u0018\u00010\bH\u0016J\b\u00104\u001a\u000205H\u0016J\u0010\u00106\u001a\u0002052\u0006\u00107\u001a\u00020\u0015H\u0016J\b\u00108\u001a\u000205H\u0016J\b\u00109\u001a\u00020\u001dH\u0002J\u0010\u0010:\u001a\u00020\u001d2\u0006\u0010;\u001a\u00020<H\u0002J\b\u0010=\u001a\u00020\u001dH\u0002J\b\u0010>\u001a\u00020\u001dH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/amazon/mp3/playlist/EntityPlaylistFetchFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/amazon/mp3/fragment/headerbar/OverflowMenuReceiver;", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "()V", "actionBarManager", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", ContextMappingConstants.ENTITY_ID, "", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/mp3/playlist/PlaylistSeedEntityType;", "loadingView", "Landroid/view/View;", "root", "Landroid/view/ViewGroup;", "getArtworkUrl", "getContentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getFilterButtonClickDestination", "", "()Ljava/lang/Integer;", "getRefinementFilterModels", "", "Lcom/amazon/music/views/library/models/FilterModel;", "getRefinementListModel", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "handleFetchError", "", "exception", "", "hideSpinner", "initFetchListener", "Lcom/amazon/mp3/playlist/EntityPlaylistFetchListener;", "initializeActionBarManager", "navigateToPlaylistDetailFragment", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "onDestroy", "onPause", "onResume", "setActionBarProvider", "provider", "shouldDisplayFilterAndContextButtons", "", "shouldDisplayPageComponentsInTopAppBar", "actionBarHeight", "shouldDisplayTitle", "showCreationFailureDialog", "showNetworkErrorDialog", "handler", "Lcom/amazon/mp3/library/dialog/NetworkErrorDialog$INetworkErrorDialogHandler;", "showSpinner", "startPlaylistFetch", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EntityPlaylistFetchFragment extends Fragment implements OverflowMenuReceiver, BaseViewsFilterActionBarManager.InfoProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = EntityPlaylistFetchFragment.class.getSimpleName();
    private BaseViewsFilterActionBarManager actionBarManager;
    private ActionBarProvider actionBarProvider;
    private String entityId;
    private PlaylistSeedEntityType entityType;
    private View loadingView;
    private ViewGroup root;

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public String getArtworkUrl() {
        return null;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public ContentMetadata getContentMetadata() {
        return null;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public Integer getFilterButtonClickDestination() {
        return null;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public ExposedRefinementListModel getRefinementListModel() {
        return null;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterAndContextButtons() {
        return false;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayPageComponentsInTopAppBar(int actionBarHeight) {
        return false;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    /* JADX INFO: renamed from: shouldDisplayTitle */
    public boolean getDisplayTitle() {
        return false;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean doesHaveRefinement() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.doesHaveRefinement(this);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    /* JADX INFO: renamed from: getTitle */
    public String getTitleText() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.getTitle(this);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayContextMenuOverflowButton() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldDisplayContextMenuOverflowButton(this);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterButton(RecyclerView recyclerView, int i) {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldDisplayFilterButton(this, recyclerView, i);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldHideRefinementsOnLoad() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldHideRefinementsOnLoad(this);
    }

    /* JADX INFO: compiled from: EntityPlaylistFetchFragment.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/playlist/EntityPlaylistFetchFragment$Companion;", "", "()V", "ARG_ENTITY_ID", "", "ARG_ENTITY_TYPE", "ARG_FETCH_COMPLETED", "EXTRA_IS_ENTITY", "TAG", "kotlin.jvm.PlatformType", "newInstance", "Lcom/amazon/mp3/playlist/EntityPlaylistFetchFragment;", ContextMappingConstants.ENTITY_ID, PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/mp3/playlist/PlaylistSeedEntityType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EntityPlaylistFetchFragment newInstance(String entityId, PlaylistSeedEntityType entityType) {
            Intrinsics.checkNotNullParameter(entityId, "entityId");
            Intrinsics.checkNotNullParameter(entityType, "entityType");
            EntityPlaylistFetchFragment entityPlaylistFetchFragment = new EntityPlaylistFetchFragment();
            Bundle bundle = new Bundle();
            bundle.putString("ARG_ENTITY_ID", entityId);
            bundle.putSerializable("ARG_ENTITY_TYPE", entityType);
            entityPlaylistFetchFragment.setArguments(bundle);
            return entityPlaylistFetchFragment;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeActionBarManager();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager == null) {
            return;
        }
        getLifecycle().addObserver(baseViewsFilterActionBarManager);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString("ARG_ENTITY_ID");
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        this.entityId = string;
        Serializable serializable = arguments != null ? arguments.getSerializable("ARG_ENTITY_TYPE") : null;
        if (serializable == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.playlist.PlaylistSeedEntityType");
        }
        this.entityType = (PlaylistSeedEntityType) serializable;
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        View viewInflate = inflater.inflate(R.layout.fragment_entity_playlist_loader, container, false);
        if (viewInflate == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
        this.root = constraintLayout;
        this.loadingView = constraintLayout.findViewById(R.id.loading_spinner);
        return this.root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        FragmentManager supportFragmentManager;
        super.onResume();
        Bundle arguments = getArguments();
        boolean z = false;
        if (arguments != null && arguments.getBoolean("ARG_FETCH_COMPLETED")) {
            z = true;
        }
        if (z) {
            FragmentActivity activity = getActivity();
            if (activity == null || (supportFragmentManager = activity.getSupportFragmentManager()) == null) {
                return;
            }
            supportFragmentManager.popBackStack();
            return;
        }
        startPlaylistFetch();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        EntityPlaylistProvider.INSTANCE.stop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager == null) {
            return;
        }
        getLifecycle().removeObserver(baseViewsFilterActionBarManager);
    }

    private final void initializeActionBarManager() {
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = new BaseViewsFilterActionBarManager(this, this, null, this.actionBarProvider);
        this.actionBarManager = baseViewsFilterActionBarManager;
        baseViewsFilterActionBarManager.hideFilterAndContextButtons();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideSpinner() {
        View view = this.loadingView;
        if (view == null) {
            return;
        }
        view.setVisibility(8);
    }

    private final void showSpinner() {
        View view = this.loadingView;
        if (view == null) {
            return;
        }
        view.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPlaylistFetch() {
        showSpinner();
        EntityPlaylistFetchListener entityPlaylistFetchListenerInitFetchListener = initFetchListener();
        EntityPlaylistProvider entityPlaylistProvider = EntityPlaylistProvider.INSTANCE;
        Context context = getContext();
        String str = this.entityId;
        PlaylistSeedEntityType playlistSeedEntityType = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.ENTITY_ID);
            str = null;
        }
        PlaylistSeedEntityType playlistSeedEntityType2 = this.entityType;
        if (playlistSeedEntityType2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(PageUriUtils.ENTITY_TYPE_QUERY_PARAM);
        } else {
            playlistSeedEntityType = playlistSeedEntityType2;
        }
        entityPlaylistProvider.startPlaylistFetch(context, str, playlistSeedEntityType, entityPlaylistFetchListenerInitFetchListener);
    }

    private final EntityPlaylistFetchListener initFetchListener() {
        return new EntityPlaylistFetchListener() { // from class: com.amazon.mp3.playlist.EntityPlaylistFetchFragment.initFetchListener.1
            @Override // com.amazon.mp3.playlist.EntityPlaylistFetchListener
            public void onEntityPlaylistFetchCompleted(CatalogPlaylist playlist) {
                Intrinsics.checkNotNullParameter(playlist, "playlist");
                EntityPlaylistFetchFragment.this.hideSpinner();
                Bundle arguments = EntityPlaylistFetchFragment.this.getArguments();
                if (arguments != null) {
                    arguments.putBoolean("ARG_FETCH_COMPLETED", true);
                }
                EntityPlaylistFetchFragment.this.navigateToPlaylistDetailFragment(playlist);
            }

            @Override // com.amazon.mp3.playlist.EntityPlaylistFetchListener
            public void onEntityPlaylistFetchError(Throwable exception) {
                Intrinsics.checkNotNullParameter(exception, "exception");
                String str = EntityPlaylistFetchFragment.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to fetch entity playlist for entity id: ");
                String str2 = EntityPlaylistFetchFragment.this.entityId;
                PlaylistSeedEntityType playlistSeedEntityType = null;
                if (str2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.ENTITY_ID);
                    str2 = null;
                }
                sb.append(str2);
                sb.append(" and type: ");
                PlaylistSeedEntityType playlistSeedEntityType2 = EntityPlaylistFetchFragment.this.entityType;
                if (playlistSeedEntityType2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(PageUriUtils.ENTITY_TYPE_QUERY_PARAM);
                } else {
                    playlistSeedEntityType = playlistSeedEntityType2;
                }
                sb.append(playlistSeedEntityType);
                Log.error(str, sb.toString(), exception);
                EntityPlaylistFetchFragment.this.hideSpinner();
                EntityPlaylistFetchFragment.this.handleFetchError(exception);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void navigateToPlaylistDetailFragment(CatalogPlaylist playlist) {
        Fragment fragmentBuildEntityPlaylistFragment = EntityPlaylistUtil.INSTANCE.buildEntityPlaylistFragment(playlist.getLuid(), playlist.getTitle(), false, true);
        KeyEventDispatcher.Component activity = getActivity();
        NavigationProvider navigationProvider = activity instanceof NavigationProvider ? (NavigationProvider) activity : null;
        if (navigationProvider == null) {
            return;
        }
        navigationProvider.changeScreenFragment(fragmentBuildEntityPlaylistFragment, false, true, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleFetchError(Throwable exception) {
        if (ConnectivityUtil.hasAnyInternetConnection(getContext())) {
            showCreationFailureDialog();
        } else {
            showNetworkErrorDialog(new NetworkErrorDialog.INetworkErrorDialogHandler() { // from class: com.amazon.mp3.playlist.EntityPlaylistFetchFragment$handleFetchError$handler$1
                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onConnectionErrorDialogCancel() {
                    FragmentManager supportFragmentManager;
                    FragmentActivity activity = this.this$0.getActivity();
                    if (activity == null || (supportFragmentManager = activity.getSupportFragmentManager()) == null) {
                        return;
                    }
                    supportFragmentManager.popBackStack();
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onDialogDismiss() {
                    FragmentManager supportFragmentManager;
                    FragmentActivity activity = this.this$0.getActivity();
                    if (activity == null || (supportFragmentManager = activity.getSupportFragmentManager()) == null) {
                        return;
                    }
                    supportFragmentManager.popBackStack();
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onRetryConnection() {
                    if (ConnectivityUtil.hasAnyInternetConnection(this.this$0.getContext())) {
                        this.this$0.startPlaylistFetch();
                    } else {
                        NetworkErrorDialog.create(this.this$0.getContext(), this).show();
                    }
                }
            });
        }
    }

    private final void showNetworkErrorDialog(NetworkErrorDialog.INetworkErrorDialogHandler handler) {
        NetworkErrorDialog.create(getContext(), handler).show();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final void showCreationFailureDialog() {
        Context context = getContext();
        String string = null;
        PlaylistSeedEntityType playlistSeedEntityType = null;
        if (context != null) {
            EntityPlaylistUtil entityPlaylistUtil = EntityPlaylistUtil.INSTANCE;
            PlaylistSeedEntityType playlistSeedEntityType2 = this.entityType;
            if (playlistSeedEntityType2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(PageUriUtils.ENTITY_TYPE_QUERY_PARAM);
            } else {
                playlistSeedEntityType = playlistSeedEntityType2;
            }
            String lowerCase = entityPlaylistUtil.getLocalizedEntityType(context, playlistSeedEntityType).toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            string = getString(R.string.dmusic_dialog_entity_playlist_error_message, lowerCase);
        }
        if (string == null) {
            string = getString(R.string.dmusic_dialog_prime_playlist_not_found_message);
        }
        Intrinsics.checkNotNullExpressionValue(string, "context?.let {\n         …aylist_not_found_message)");
        new OneButtonErrorDialog(getActivity(), getString(R.string.dmusic_dialog_entity_playlist_error_title), string, new ErrorDialogButton(getString(R.string.dmusic_dialog_prime_not_found_button), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playlist.EntityPlaylistFetchFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                EntityPlaylistFetchFragment.m1565showCreationFailureDialog$lambda3(this.f$0, dialogInterface, i);
            }
        })).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showCreationFailureDialog$lambda-3, reason: not valid java name */
    public static final void m1565showCreationFailureDialog$lambda3(EntityPlaylistFetchFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PrimeTabFragmentHost primeTabFragmentHostNewInstance = PrimeTabFragmentHost.newInstance(new Bundle());
        KeyEventDispatcher.Component activity = this$0.getActivity();
        NavigationProvider navigationProvider = activity instanceof NavigationProvider ? (NavigationProvider) activity : null;
        if (navigationProvider == null) {
            return;
        }
        navigationProvider.changeScreenFragment(primeTabFragmentHostNewInstance, false, true, false);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public List<FilterModel> getRefinementFilterModels() {
        return new ArrayList();
    }

    @Override // com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver
    public void setActionBarProvider(ActionBarProvider provider) {
        this.actionBarProvider = provider;
    }
}
