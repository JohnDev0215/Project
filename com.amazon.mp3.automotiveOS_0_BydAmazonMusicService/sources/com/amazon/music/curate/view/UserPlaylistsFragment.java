package com.amazon.music.curate.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.curate.R;
import com.amazon.music.curate.data.PlaylistService;
import com.amazon.music.curate.data.UserPlaylistsRepository;
import com.amazon.music.curate.provider.DownloadProvider;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.provider.InternalProvider;
import com.amazon.music.curate.provider.PlaybackProvider;
import dagger.android.support.AndroidSupportInjection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: UserPlaylistsFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 *2\u00020\u0001:\u0001*B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002J\u0012\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J&\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010)\u001a\u00020 H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/amazon/music/curate/view/UserPlaylistsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "downloadProvider", "Lcom/amazon/music/curate/provider/DownloadProvider;", "getDownloadProvider", "()Lcom/amazon/music/curate/provider/DownloadProvider;", "setDownloadProvider", "(Lcom/amazon/music/curate/provider/DownloadProvider;)V", "featureFlagProvider", "Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "getFeatureFlagProvider", "()Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "setFeatureFlagProvider", "(Lcom/amazon/music/curate/provider/FeatureFlagProvider;)V", "playbackProvider", "Lcom/amazon/music/curate/provider/PlaybackProvider;", "getPlaybackProvider", "()Lcom/amazon/music/curate/provider/PlaybackProvider;", "setPlaybackProvider", "(Lcom/amazon/music/curate/provider/PlaybackProvider;)V", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "getRequestInterceptor", "()Lcom/amazon/hermes/RequestInterceptor;", "setRequestInterceptor", "(Lcom/amazon/hermes/RequestInterceptor;)V", "visualRowItemListAdapter", "Lcom/amazon/music/curate/view/VisualRowItemListAdapter;", "getActionBar", "Landroidx/appcompat/app/ActionBar;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onResume", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserPlaylistsFragment extends Fragment {
    private static final Logger LOG;
    public DownloadProvider downloadProvider;
    public FeatureFlagProvider featureFlagProvider;
    public PlaybackProvider playbackProvider;
    public RequestInterceptor requestInterceptor;
    private VisualRowItemListAdapter visualRowItemListAdapter;

    public final RequestInterceptor getRequestInterceptor() {
        RequestInterceptor requestInterceptor = this.requestInterceptor;
        if (requestInterceptor != null) {
            return requestInterceptor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("requestInterceptor");
        return null;
    }

    public final void setRequestInterceptor(RequestInterceptor requestInterceptor) {
        Intrinsics.checkNotNullParameter(requestInterceptor, "<set-?>");
        this.requestInterceptor = requestInterceptor;
    }

    public final PlaybackProvider getPlaybackProvider() {
        PlaybackProvider playbackProvider = this.playbackProvider;
        if (playbackProvider != null) {
            return playbackProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playbackProvider");
        return null;
    }

    public final void setPlaybackProvider(PlaybackProvider playbackProvider) {
        Intrinsics.checkNotNullParameter(playbackProvider, "<set-?>");
        this.playbackProvider = playbackProvider;
    }

    public final DownloadProvider getDownloadProvider() {
        DownloadProvider downloadProvider = this.downloadProvider;
        if (downloadProvider != null) {
            return downloadProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("downloadProvider");
        return null;
    }

    public final void setDownloadProvider(DownloadProvider downloadProvider) {
        Intrinsics.checkNotNullParameter(downloadProvider, "<set-?>");
        this.downloadProvider = downloadProvider;
    }

    public final FeatureFlagProvider getFeatureFlagProvider() {
        FeatureFlagProvider featureFlagProvider = this.featureFlagProvider;
        if (featureFlagProvider != null) {
            return featureFlagProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("featureFlagProvider");
        return null;
    }

    public final void setFeatureFlagProvider(FeatureFlagProvider featureFlagProvider) {
        Intrinsics.checkNotNullParameter(featureFlagProvider, "<set-?>");
        this.featureFlagProvider = featureFlagProvider;
    }

    static {
        Logger logger = LoggerFactory.getLogger(UserPlaylistsFragment.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(UserPlaylistsF…t::class.java.simpleName)");
        LOG = logger;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.visualRowItemListAdapter = new VisualRowItemListAdapter(this, getPlaybackProvider(), getDownloadProvider());
        final View viewInflate = View.inflate(getActivity(), R.layout.user_playlists, null);
        View viewFindViewById = viewInflate.findViewById(R.id.playlist_list);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        }
        RecyclerView recyclerView = (RecyclerView) viewFindViewById;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        recyclerView.setAdapter(this.visualRowItemListAdapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.amazon.music.curate.view.UserPlaylistsFragment.onCreateView.1
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                ActionBar actionBar;
                Intrinsics.checkNotNullParameter(outRect, "outRect");
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(state, "state");
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildViewHolder(view).getAdapterPosition() != 0 || (actionBar = UserPlaylistsFragment.this.getActionBar()) == null) {
                    return;
                }
                outRect.top = actionBar.getHeight();
            }
        });
        InternalProvider internalProvider = InternalProvider.Singleton.INSTANCE.get();
        RequestInterceptor requestInterceptor = getRequestInterceptor();
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNullExpressionValue(context, "context!!");
        PlaylistService playlistServiceProvideService = internalProvider.provideService(requestInterceptor, context, getFeatureFlagProvider());
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        Intrinsics.checkNotNullExpressionValue(context2, "context!!");
        UserPlaylistsViewModel userPlaylistsViewModel = (UserPlaylistsViewModel) new ViewModelProvider(this, new UserPlaylistsViewModelFactory(new UserPlaylistsRepository(internalProvider.provideCache(context2, playlistServiceProvideService), playlistServiceProvideService))).get(UserPlaylistsViewModel.class);
        userPlaylistsViewModel.setOnItemClick(new Function1<String, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistsFragment.onCreateView.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                FragmentTransaction fragmentTransactionBeginTransaction;
                FragmentTransaction fragmentTransactionReplace;
                FragmentTransaction fragmentTransactionAddToBackStack;
                Intrinsics.checkNotNullParameter(it, "it");
                UserPlaylistFragment userPlaylistFragmentBuild = UserPlaylistFragment.INSTANCE.builder().withPlaylistId(it).build();
                View view = viewInflate;
                ViewParent parent = view == null ? null : view.getParent();
                if (parent == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
                }
                int id = ((ViewGroup) parent).getId();
                FragmentManager fragmentManager = this.getFragmentManager();
                if (fragmentManager == null || (fragmentTransactionBeginTransaction = fragmentManager.beginTransaction()) == null || (fragmentTransactionReplace = fragmentTransactionBeginTransaction.replace(id, userPlaylistFragmentBuild, "UserPlaylistFragment")) == null || (fragmentTransactionAddToBackStack = fragmentTransactionReplace.addToBackStack("UserPlaylistFragment")) == null) {
                    return;
                }
                fragmentTransactionAddToBackStack.commit();
            }
        });
        userPlaylistsViewModel.getUserPlaylists().observe(this, new Observer() { // from class: com.amazon.music.curate.view.UserPlaylistsFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UserPlaylistsFragment.m1963onCreateView$lambda0(this.f$0, (PagedList) obj);
            }
        });
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m1963onCreateView$lambda0(UserPlaylistsFragment this$0, PagedList pagedList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VisualRowItemListAdapter visualRowItemListAdapter = this$0.visualRowItemListAdapter;
        if (visualRowItemListAdapter != null) {
            Intrinsics.checkNotNull(pagedList);
            visualRowItemListAdapter.submitList(pagedList);
        }
        Logger logger = LOG;
        StringBuilder sb = new StringBuilder();
        sb.append("Got ");
        sb.append(pagedList == null ? null : Integer.valueOf(pagedList.size()));
        sb.append(" visual models");
        logger.debug(sb.toString());
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ActionBar actionBar = getActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        actionBar.setTitle(context.getResources().getString(R.string.your_playlists_header));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ActionBar getActionBar() {
        FragmentActivity activity = getActivity();
        AppCompatActivity appCompatActivity = activity instanceof AppCompatActivity ? (AppCompatActivity) activity : null;
        if (appCompatActivity == null) {
            return null;
        }
        return appCompatActivity.getSupportActionBar();
    }
}
