package com.amazon.music.curate.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.R;
import com.amazon.music.curate.data.PlaylistCollage;
import com.amazon.music.curate.data.PlaylistService;
import com.amazon.music.curate.data.UserPlaylistRepository;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.data.database.RecsCache;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import com.amazon.music.curate.model.VisualModel;
import com.amazon.music.curate.provider.Configuration;
import com.amazon.music.curate.provider.DownloadProvider;
import com.amazon.music.curate.provider.DownloadStatus;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.provider.InternalProvider;
import com.amazon.music.curate.provider.NetworkProvider;
import com.amazon.music.curate.provider.OverflowMenuProvider;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.provider.PlaylistIntents;
import com.amazon.music.curate.provider.PlaylistProvider;
import com.amazon.music.curate.provider.ShareProvider;
import com.amazon.music.curate.provider.StationProvider;
import com.amazon.music.curate.provider.SubscriptionProvider;
import com.amazon.music.curate.view.UserPlaylistFragment;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;
import dagger.android.support.AndroidSupportInjection;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* JADX INFO: compiled from: UserPlaylistFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Í\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003*\u00016\u0018\u0000 p2\u00020\u0001:\u0002opB\u0005¢\u0006\u0002\u0010\u0002J\n\u0010Y\u001a\u0004\u0018\u00010ZH\u0002J\u0018\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u00020.H\u0002J\u0012\u0010`\u001a\u00020\\2\b\u0010a\u001a\u0004\u0018\u00010bH\u0016J&\u0010c\u001a\u0004\u0018\u00010^2\u0006\u0010d\u001a\u00020e2\b\u0010f\u001a\u0004\u0018\u00010g2\b\u0010a\u001a\u0004\u0018\u00010bH\u0016J\b\u0010h\u001a\u00020\\H\u0016J\b\u0010i\u001a\u00020\\H\u0016J\u0010\u0010j\u001a\u00020\\2\u0006\u0010k\u001a\u00020bH\u0016J\u0010\u0010l\u001a\u00020T2\u0006\u0010m\u001a\u00020nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001e\u0010!\u001a\u00020\"8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001e\u0010'\u001a\u00020(8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020.X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010/\u001a\u0002008\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u0010\u00105\u001a\u000206X\u0082\u0004¢\u0006\u0004\n\u0002\u00107R\u001e\u00108\u001a\u0002098\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u000e\u0010>\u001a\u00020.X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010?\u001a\u00020@8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001e\u0010E\u001a\u00020F8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001e\u0010K\u001a\u00020L8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u0010\u0010Q\u001a\u0004\u0018\u00010RX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010S\u001a\u0004\u0018\u00010TX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020VX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020XX\u0082.¢\u0006\u0002\n\u0000¨\u0006q"}, d2 = {"Lcom/amazon/music/curate/view/UserPlaylistFragment;", "Lcom/amazon/music/curate/view/BaseFragment;", "()V", "cache", "Lcom/amazon/music/curate/data/database/PlaylistDB;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "getDeviceInfoProvider", "()Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "setDeviceInfoProvider", "(Lcom/amazon/music/platform/providers/DeviceInfoProvider;)V", "downloadProvider", "Lcom/amazon/music/curate/provider/DownloadProvider;", "getDownloadProvider", "()Lcom/amazon/music/curate/provider/DownloadProvider;", "setDownloadProvider", "(Lcom/amazon/music/curate/provider/DownloadProvider;)V", "featureFlagProvider", "Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "getFeatureFlagProvider", "()Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "setFeatureFlagProvider", "(Lcom/amazon/music/curate/provider/FeatureFlagProvider;)V", "isToastShown", "", "lastPlaylist", "Lcom/amazon/music/curate/model/Playlist;", "networkProvider", "Lcom/amazon/music/curate/provider/NetworkProvider;", "getNetworkProvider", "()Lcom/amazon/music/curate/provider/NetworkProvider;", "setNetworkProvider", "(Lcom/amazon/music/curate/provider/NetworkProvider;)V", "overflowMenuProvider", "Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "getOverflowMenuProvider", "()Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "setOverflowMenuProvider", "(Lcom/amazon/music/curate/provider/OverflowMenuProvider;)V", "playbackProvider", "Lcom/amazon/music/curate/provider/PlaybackProvider;", "getPlaybackProvider", "()Lcom/amazon/music/curate/provider/PlaybackProvider;", "setPlaybackProvider", "(Lcom/amazon/music/curate/provider/PlaybackProvider;)V", "playlistId", "", "playlistProvider", "Lcom/amazon/music/curate/provider/PlaylistProvider;", "getPlaylistProvider", "()Lcom/amazon/music/curate/provider/PlaylistProvider;", "setPlaylistProvider", "(Lcom/amazon/music/curate/provider/PlaylistProvider;)V", "refreshPlaylistReceiver", "com/amazon/music/curate/view/UserPlaylistFragment$refreshPlaylistReceiver$1", "Lcom/amazon/music/curate/view/UserPlaylistFragment$refreshPlaylistReceiver$1;", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "getRequestInterceptor", "()Lcom/amazon/hermes/RequestInterceptor;", "setRequestInterceptor", "(Lcom/amazon/hermes/RequestInterceptor;)V", Splash.PARAMS_SESSION_ID, "shareProvider", "Lcom/amazon/music/curate/provider/ShareProvider;", "getShareProvider", "()Lcom/amazon/music/curate/provider/ShareProvider;", "setShareProvider", "(Lcom/amazon/music/curate/provider/ShareProvider;)V", "stationProvider", "Lcom/amazon/music/curate/provider/StationProvider;", "getStationProvider", "()Lcom/amazon/music/curate/provider/StationProvider;", "setStationProvider", "(Lcom/amazon/music/curate/provider/StationProvider;)V", "subscriptionProvider", "Lcom/amazon/music/curate/provider/SubscriptionProvider;", "getSubscriptionProvider", "()Lcom/amazon/music/curate/provider/SubscriptionProvider;", "setSubscriptionProvider", "(Lcom/amazon/music/curate/provider/SubscriptionProvider;)V", "target", "Lcom/squareup/picasso/Target;", "toast", "Landroid/widget/PopupWindow;", "userPlaylistViewModel", "Lcom/amazon/music/curate/view/UserPlaylistViewModel;", "visualRowItemListAdapter", "Lcom/amazon/music/curate/view/VisualRowItemListAdapter;", "getActionBar", "Landroidx/appcompat/app/ActionBar;", "loadBackgroundImage", "", "view", "Landroid/view/View;", "artworkUrl", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onResume", "onSaveInstanceState", "outState", "showSeeSimilarSongsToast", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "Builder", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserPlaylistFragment extends BaseFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String IS_TOAST_SHOWN_KEY;
    private static final Logger LOG;
    private static final String PLAYLIST_ID_KEY = "PLAYLIST_ID_KEY";
    private static final int RECS_HEADER_SCROLL_ADJUSTMENT;
    private static final String SESSION_ID_KEY = "SESSION_ID_KEY";
    private PlaylistDB cache;
    public DeviceInfoProvider deviceInfoProvider;
    public DownloadProvider downloadProvider;
    public FeatureFlagProvider featureFlagProvider;
    private boolean isToastShown;
    private Playlist lastPlaylist;
    public NetworkProvider networkProvider;
    public OverflowMenuProvider overflowMenuProvider;
    public PlaybackProvider playbackProvider;
    private String playlistId;
    public PlaylistProvider playlistProvider;
    private final UserPlaylistFragment$refreshPlaylistReceiver$1 refreshPlaylistReceiver = new BroadcastReceiver() { // from class: com.amazon.music.curate.view.UserPlaylistFragment$refreshPlaylistReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            UserPlaylistFragment.LOG.debug("Received refresh intent");
            UserPlaylistViewModel userPlaylistViewModel = this.this$0.userPlaylistViewModel;
            if (userPlaylistViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
                userPlaylistViewModel = null;
            }
            DataSource<Integer, VisualModel> value = userPlaylistViewModel.getPlaylistLiveData().getDataSource().getValue();
            if (value == null) {
                return;
            }
            value.invalidate();
        }
    };
    public RequestInterceptor requestInterceptor;
    private String sessionId;
    public ShareProvider shareProvider;
    public StationProvider stationProvider;
    public SubscriptionProvider subscriptionProvider;
    private Target target;
    private PopupWindow toast;
    private UserPlaylistViewModel userPlaylistViewModel;
    private VisualRowItemListAdapter visualRowItemListAdapter;

    /* JADX INFO: compiled from: UserPlaylistFragment.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/music/curate/view/UserPlaylistFragment$Companion;", "", "()V", "IS_TOAST_SHOWN_KEY", "", "LOG", "Lorg/slf4j/Logger;", "PLAYLIST_ID_KEY", "RECS_HEADER_SCROLL_ADJUSTMENT", "", UserPlaylistFragment.SESSION_ID_KEY, "builder", "Lcom/amazon/music/curate/view/UserPlaylistFragment$Builder;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Builder builder() {
            return new Builder();
        }
    }

    static {
        Logger logger = LoggerFactory.getLogger(UserPlaylistFragment.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(UserPlaylistFr…t::class.java.simpleName)");
        LOG = logger;
        IS_TOAST_SHOWN_KEY = "IS_TOAST_SHOWN";
        RECS_HEADER_SCROLL_ADJUSTMENT = 4;
    }

    /* JADX INFO: compiled from: UserPlaylistFragment.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/music/curate/view/UserPlaylistFragment$Builder;", "", "()V", "playlistId", "", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/curate/view/UserPlaylistFragment;", "withPlaylistId", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder {
        private String playlistId;

        public final Builder withPlaylistId(String playlistId) {
            Intrinsics.checkNotNullParameter(playlistId, "playlistId");
            this.playlistId = playlistId;
            return this;
        }

        public final UserPlaylistFragment build() {
            UserPlaylistFragment userPlaylistFragment = new UserPlaylistFragment();
            Bundle bundle = new Bundle();
            bundle.putString("PLAYLIST_ID_KEY", this.playlistId);
            userPlaylistFragment.setArguments(bundle);
            return userPlaylistFragment;
        }
    }

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

    public final ShareProvider getShareProvider() {
        ShareProvider shareProvider = this.shareProvider;
        if (shareProvider != null) {
            return shareProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("shareProvider");
        return null;
    }

    public final void setShareProvider(ShareProvider shareProvider) {
        Intrinsics.checkNotNullParameter(shareProvider, "<set-?>");
        this.shareProvider = shareProvider;
    }

    public final StationProvider getStationProvider() {
        StationProvider stationProvider = this.stationProvider;
        if (stationProvider != null) {
            return stationProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("stationProvider");
        return null;
    }

    public final void setStationProvider(StationProvider stationProvider) {
        Intrinsics.checkNotNullParameter(stationProvider, "<set-?>");
        this.stationProvider = stationProvider;
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

    public final OverflowMenuProvider getOverflowMenuProvider() {
        OverflowMenuProvider overflowMenuProvider = this.overflowMenuProvider;
        if (overflowMenuProvider != null) {
            return overflowMenuProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("overflowMenuProvider");
        return null;
    }

    public final void setOverflowMenuProvider(OverflowMenuProvider overflowMenuProvider) {
        Intrinsics.checkNotNullParameter(overflowMenuProvider, "<set-?>");
        this.overflowMenuProvider = overflowMenuProvider;
    }

    public final PlaylistProvider getPlaylistProvider() {
        PlaylistProvider playlistProvider = this.playlistProvider;
        if (playlistProvider != null) {
            return playlistProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playlistProvider");
        return null;
    }

    public final void setPlaylistProvider(PlaylistProvider playlistProvider) {
        Intrinsics.checkNotNullParameter(playlistProvider, "<set-?>");
        this.playlistProvider = playlistProvider;
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

    public final NetworkProvider getNetworkProvider() {
        NetworkProvider networkProvider = this.networkProvider;
        if (networkProvider != null) {
            return networkProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("networkProvider");
        return null;
    }

    public final void setNetworkProvider(NetworkProvider networkProvider) {
        Intrinsics.checkNotNullParameter(networkProvider, "<set-?>");
        this.networkProvider = networkProvider;
    }

    public final SubscriptionProvider getSubscriptionProvider() {
        SubscriptionProvider subscriptionProvider = this.subscriptionProvider;
        if (subscriptionProvider != null) {
            return subscriptionProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("subscriptionProvider");
        return null;
    }

    public final void setSubscriptionProvider(SubscriptionProvider subscriptionProvider) {
        Intrinsics.checkNotNullParameter(subscriptionProvider, "<set-?>");
        this.subscriptionProvider = subscriptionProvider;
    }

    public final DeviceInfoProvider getDeviceInfoProvider() {
        DeviceInfoProvider deviceInfoProvider = this.deviceInfoProvider;
        if (deviceInfoProvider != null) {
            return deviceInfoProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("deviceInfoProvider");
        return null;
    }

    public final void setDeviceInfoProvider(DeviceInfoProvider deviceInfoProvider) {
        Intrinsics.checkNotNullParameter(deviceInfoProvider, "<set-?>");
        this.deviceInfoProvider = deviceInfoProvider;
    }

    @Override // com.amazon.music.curate.view.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        PlaylistDB playlistDB;
        Bundle arguments = getArguments();
        String str = null;
        String string = arguments == null ? null : arguments.getString("PLAYLIST_ID_KEY");
        Intrinsics.checkNotNull(string);
        Intrinsics.checkNotNullExpressionValue(string, "arguments?.getString(PLAYLIST_ID_KEY)!!");
        this.playlistId = string;
        String string2 = savedInstanceState == null ? null : savedInstanceState.getString(SESSION_ID_KEY);
        if (string2 == null) {
            string2 = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string2, "randomUUID().toString()");
        }
        this.sessionId = string2;
        FragmentCallbacks[] fragmentCallbacksArr = new FragmentCallbacks[1];
        String metricsValue = PageType.DETAIL_USER_PLAYLIST.getMetricsValue();
        Intrinsics.checkNotNullExpressionValue(metricsValue, "DETAIL_USER_PLAYLIST.getMetricValue()");
        String str2 = this.playlistId;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistId");
            str2 = null;
        }
        String metricsValue2 = EntityIdType.PLAYLIST_ID.getMetricsValue();
        Intrinsics.checkNotNullExpressionValue(metricsValue2, "PLAYLIST_ID.metricValue");
        fragmentCallbacksArr[0] = new UiContentViewFragmentCallbacks(this, metricsValue, str2, metricsValue2);
        addFragmentCallbacks(fragmentCallbacksArr);
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        InternalProvider internalProvider = InternalProvider.Singleton.INSTANCE.get();
        RequestInterceptor requestInterceptor = getRequestInterceptor();
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNullExpressionValue(context, "context!!");
        PlaylistService playlistServiceProvideService = internalProvider.provideService(requestInterceptor, context, getFeatureFlagProvider());
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        Intrinsics.checkNotNullExpressionValue(context2, "context!!");
        this.cache = internalProvider.provideCache(context2, playlistServiceProvideService);
        RecsCache recsCacheProvideRecsCache = internalProvider.provideRecsCache(playlistServiceProvideService);
        OverflowMenuProvider overflowMenuProvider = getOverflowMenuProvider();
        FeatureFlagProvider featureFlagProvider = getFeatureFlagProvider();
        SubscriptionProvider subscriptionProvider = getSubscriptionProvider();
        DeviceInfoProvider deviceInfoProvider = getDeviceInfoProvider();
        PlaylistDB playlistDB2 = this.cache;
        if (playlistDB2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cache");
            playlistDB = null;
        } else {
            playlistDB = playlistDB2;
        }
        Configuration configuration = new Configuration(overflowMenuProvider, featureFlagProvider, subscriptionProvider, deviceInfoProvider, playlistDB, recsCacheProvideRecsCache, playlistServiceProvideService, internalProvider.provideRecentsService(getRequestInterceptor()));
        Context context3 = getContext();
        Intrinsics.checkNotNull(context3);
        Intrinsics.checkNotNullExpressionValue(context3, "context!!");
        UserPlaylistRepository userPlaylistRepository = new UserPlaylistRepository(configuration, context3);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
        String str3 = this.playlistId;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistId");
            str3 = null;
        }
        String str4 = this.sessionId;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Splash.PARAMS_SESSION_ID);
            str4 = null;
        }
        ViewModelProvider viewModelProvider = new ViewModelProvider(activity, new UserPlaylistViewModelFactory(str3, userPlaylistRepository, str4));
        String str5 = this.playlistId;
        if (str5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistId");
        } else {
            str = str5;
        }
        this.userPlaylistViewModel = (UserPlaylistViewModel) viewModelProvider.get(str, UserPlaylistViewModel.class);
        Context context4 = getContext();
        Intrinsics.checkNotNull(context4);
        LocalBroadcastManager.getInstance(context4).registerReceiver(this.refreshPlaylistReceiver, new IntentFilter(PlaylistIntents.INSTANCE.getACTION_REFRESH_PLAYLIST()));
        this.isToastShown = savedInstanceState != null ? savedInstanceState.getBoolean(IS_TOAST_SHOWN_KEY) : false;
    }

    @Override // com.amazon.music.curate.view.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        final View viewInflate = inflater.inflate(R.layout.detail_page, container, false);
        View viewFindViewById = viewInflate.findViewById(R.id.visual_item_list);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        }
        final RecyclerView recyclerView = (RecyclerView) viewFindViewById;
        View viewFindViewById2 = viewInflate.findViewById(R.id.swipe_refresh);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.swipe_refresh)");
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) viewFindViewById2;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.onCreateView.1
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                ActionBar actionBar;
                Intrinsics.checkNotNullParameter(outRect, "outRect");
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(state, "state");
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildViewHolder(view).getAdapterPosition() != 0 || (actionBar = UserPlaylistFragment.this.getActionBar()) == null) {
                    return;
                }
                outRect.top = actionBar.getHeight();
            }
        });
        this.visualRowItemListAdapter = new VisualRowItemListAdapter(this, getPlaybackProvider(), getDownloadProvider());
        UserPlaylistViewModel userPlaylistViewModel = this.userPlaylistViewModel;
        String str = null;
        if (userPlaylistViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
            userPlaylistViewModel = null;
        }
        userPlaylistViewModel.setOnSeeMoreClick(new Function1<String, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.onCreateView.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                invoke2(str2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                FragmentTransaction fragmentTransactionBeginTransaction;
                FragmentTransaction fragmentTransactionReplace;
                FragmentTransaction fragmentTransactionAddToBackStack;
                Intrinsics.checkNotNullParameter(it, "it");
                PlaylistRecsSeeMoreFragment playlistRecsSeeMoreFragmentBuild = PlaylistRecsSeeMoreFragment.INSTANCE.builder().withPlaylistId(it).build();
                View view = viewInflate;
                ViewParent parent = view == null ? null : view.getParent();
                if (parent == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
                }
                int id = ((ViewGroup) parent).getId();
                FragmentManager fragmentManager = this.getFragmentManager();
                if (fragmentManager == null || (fragmentTransactionBeginTransaction = fragmentManager.beginTransaction()) == null || (fragmentTransactionReplace = fragmentTransactionBeginTransaction.replace(id, playlistRecsSeeMoreFragmentBuild, "PlaylistRecsSeeMoreFragment")) == null || (fragmentTransactionAddToBackStack = fragmentTransactionReplace.addToBackStack("PlaylistRecsSeeMoreFragment")) == null) {
                    return;
                }
                fragmentTransactionAddToBackStack.commit();
            }
        });
        UserPlaylistViewModel userPlaylistViewModel2 = this.userPlaylistViewModel;
        if (userPlaylistViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
            userPlaylistViewModel2 = null;
        }
        userPlaylistViewModel2.setOnMoreClick(new Function1<View, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.onCreateView.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                UserPlaylistFragment.this.requireActivity().openContextMenu(it);
            }
        });
        UserPlaylistViewModel userPlaylistViewModel3 = this.userPlaylistViewModel;
        if (userPlaylistViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
            userPlaylistViewModel3 = null;
        }
        userPlaylistViewModel3.setOnPlayButtonClick(new AnonymousClass4());
        UserPlaylistViewModel userPlaylistViewModel4 = this.userPlaylistViewModel;
        if (userPlaylistViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
            userPlaylistViewModel4 = null;
        }
        userPlaylistViewModel4.setOnShuffleButtonClick(new AnonymousClass5());
        UserPlaylistViewModel userPlaylistViewModel5 = this.userPlaylistViewModel;
        if (userPlaylistViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
            userPlaylistViewModel5 = null;
        }
        userPlaylistViewModel5.setOnDownloadButtonClick(new Function1<Playlist, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.onCreateView.6
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Playlist playlist) {
                invoke2(playlist);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final Playlist playlist) {
                Intrinsics.checkNotNullParameter(playlist, "playlist");
                NetworkProvider networkProvider = UserPlaylistFragment.this.getNetworkProvider();
                FragmentActivity activity = UserPlaylistFragment.this.getActivity();
                Intrinsics.checkNotNull(activity);
                Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
                final UserPlaylistFragment userPlaylistFragment = UserPlaylistFragment.this;
                networkProvider.ifConnected(activity, new Function0<Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.onCreateView.6.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        DownloadProvider downloadProvider = userPlaylistFragment.getDownloadProvider();
                        Playlist playlist2 = playlist;
                        FragmentActivity activity2 = userPlaylistFragment.getActivity();
                        Intrinsics.checkNotNull(activity2);
                        Intrinsics.checkNotNullExpressionValue(activity2, "activity!!");
                        downloadProvider.download(playlist2, activity2);
                    }
                });
            }
        });
        UserPlaylistViewModel userPlaylistViewModel6 = this.userPlaylistViewModel;
        if (userPlaylistViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
            userPlaylistViewModel6 = null;
        }
        userPlaylistViewModel6.setOnStationButtonClick(new Function1<List<? extends Track>, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.onCreateView.7
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Track> list) {
                invoke2((List<Track>) list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final List<Track> tracks) {
                Intrinsics.checkNotNullParameter(tracks, "tracks");
                NetworkProvider networkProvider = UserPlaylistFragment.this.getNetworkProvider();
                FragmentActivity activity = UserPlaylistFragment.this.getActivity();
                Intrinsics.checkNotNull(activity);
                Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
                final UserPlaylistFragment userPlaylistFragment = UserPlaylistFragment.this;
                networkProvider.ifConnected(activity, new Function0<Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.onCreateView.7.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        StationProvider stationProvider = userPlaylistFragment.getStationProvider();
                        List<Track> list = tracks;
                        FragmentActivity activity2 = userPlaylistFragment.getActivity();
                        Intrinsics.checkNotNull(activity2);
                        Intrinsics.checkNotNullExpressionValue(activity2, "activity!!");
                        stationProvider.startStationFromTrackList(list, activity2);
                    }
                });
            }
        });
        UserPlaylistViewModel userPlaylistViewModel7 = this.userPlaylistViewModel;
        if (userPlaylistViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
            userPlaylistViewModel7 = null;
        }
        userPlaylistViewModel7.setOnShareButtonClick(new Function1<Playlist, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.onCreateView.8
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Playlist playlist) {
                invoke2(playlist);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final Playlist playlist) {
                Intrinsics.checkNotNullParameter(playlist, "playlist");
                NetworkProvider networkProvider = UserPlaylistFragment.this.getNetworkProvider();
                FragmentActivity activity = UserPlaylistFragment.this.getActivity();
                Intrinsics.checkNotNull(activity);
                Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
                final UserPlaylistFragment userPlaylistFragment = UserPlaylistFragment.this;
                networkProvider.ifConnected(activity, new Function0<Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.onCreateView.8.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        ShareProvider shareProvider = userPlaylistFragment.getShareProvider();
                        Playlist playlist2 = playlist;
                        FragmentActivity activity2 = userPlaylistFragment.getActivity();
                        Intrinsics.checkNotNull(activity2);
                        Intrinsics.checkNotNullExpressionValue(activity2, "activity!!");
                        shareProvider.sharePlaylist(playlist2, activity2);
                    }
                });
            }
        });
        UserPlaylistViewModel userPlaylistViewModel8 = this.userPlaylistViewModel;
        if (userPlaylistViewModel8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
            userPlaylistViewModel8 = null;
        }
        userPlaylistViewModel8.setOnEditButtonClick(new Function1<Playlist, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.onCreateView.9
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Playlist playlist) {
                invoke2(playlist);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final Playlist it) {
                Intrinsics.checkNotNullParameter(it, "it");
                NetworkProvider networkProvider = UserPlaylistFragment.this.getNetworkProvider();
                FragmentActivity activity = UserPlaylistFragment.this.getActivity();
                Intrinsics.checkNotNull(activity);
                Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
                final UserPlaylistFragment userPlaylistFragment = UserPlaylistFragment.this;
                networkProvider.ifConnected(activity, new Function0<Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.onCreateView.9.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        PlaylistProvider playlistProvider = userPlaylistFragment.getPlaylistProvider();
                        Playlist playlist = it;
                        FragmentActivity activity2 = userPlaylistFragment.getActivity();
                        Intrinsics.checkNotNull(activity2);
                        Intrinsics.checkNotNullExpressionValue(activity2, "activity!!");
                        playlistProvider.editPlaylist(playlist, activity2);
                    }
                });
            }
        });
        UserPlaylistViewModel userPlaylistViewModel9 = this.userPlaylistViewModel;
        if (userPlaylistViewModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
            userPlaylistViewModel9 = null;
        }
        userPlaylistViewModel9.setOnGetFragmentSessionId(new Function0<String>() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.onCreateView.10
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String str2 = UserPlaylistFragment.this.sessionId;
                if (str2 != null) {
                    return str2;
                }
                Intrinsics.throwUninitializedPropertyAccessException(Splash.PARAMS_SESSION_ID);
                return null;
            }
        });
        Observer<? super PagedList<VisualModel>> observer = new Observer() { // from class: com.amazon.music.curate.view.UserPlaylistFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UserPlaylistFragment.m1953onCreateView$lambda0(this.f$0, swipeRefreshLayout, (PagedList) obj);
            }
        };
        UserPlaylistViewModel userPlaylistViewModel10 = this.userPlaylistViewModel;
        if (userPlaylistViewModel10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
            userPlaylistViewModel10 = null;
        }
        userPlaylistViewModel10.getPlaylistLiveData().getRecsLoaded().observe(this, new Observer() { // from class: com.amazon.music.curate.view.UserPlaylistFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UserPlaylistFragment.m1954onCreateView$lambda2(this.f$0, container, recyclerView, (Boolean) obj);
            }
        });
        LOG.debug("Subscribing to live data");
        UserPlaylistViewModel userPlaylistViewModel11 = this.userPlaylistViewModel;
        if (userPlaylistViewModel11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
            userPlaylistViewModel11 = null;
        }
        userPlaylistViewModel11.getPlaylistLiveData().getItems().observe(this, observer);
        VisualRowItemListAdapter visualRowItemListAdapter = this.visualRowItemListAdapter;
        if (visualRowItemListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("visualRowItemListAdapter");
            visualRowItemListAdapter = null;
        }
        recyclerView.setAdapter(visualRowItemListAdapter);
        registerForContextMenu(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.music.curate.view.UserPlaylistFragment$$ExternalSyntheticLambda4
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                UserPlaylistFragment.m1956onCreateView$lambda4(this.f$0);
            }
        });
        PlaylistDB playlistDB = this.cache;
        if (playlistDB == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cache");
            playlistDB = null;
        }
        String str2 = this.playlistId;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistId");
            str2 = null;
        }
        playlistDB.getPlaylistLiveData(str2).observe(this, new Observer() { // from class: com.amazon.music.curate.view.UserPlaylistFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UserPlaylistFragment.m1958onCreateView$lambda5(this.f$0, viewInflate, (Playlist) obj);
            }
        });
        PlaylistDB playlistDB2 = this.cache;
        if (playlistDB2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cache");
            playlistDB2 = null;
        }
        String str3 = this.playlistId;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistId");
        } else {
            str = str3;
        }
        playlistDB2.getPlaylistTracksLiveData(str).observe(this, new Observer() { // from class: com.amazon.music.curate.view.UserPlaylistFragment$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UserPlaylistFragment.m1959onCreateView$lambda6(this.f$0, (List) obj);
            }
        });
        return viewInflate;
    }

    /* JADX INFO: renamed from: com.amazon.music.curate.view.UserPlaylistFragment$onCreateView$4, reason: invalid class name */
    /* JADX INFO: compiled from: UserPlaylistFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", "tracks", "", "Lcom/amazon/music/curate/model/Track;", "index", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    static final class AnonymousClass4 extends Lambda implements Function3<Playlist, List<? extends Track>, Integer, Unit> {
        AnonymousClass4() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(Playlist playlist, List<? extends Track> list, Integer num) {
            invoke(playlist, (List<Track>) list, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(final Playlist playlist, final List<Track> tracks, final int i) {
            Intrinsics.checkNotNullParameter(playlist, "playlist");
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            DownloadProvider downloadProvider = UserPlaylistFragment.this.getDownloadProvider();
            String id = playlist.getId();
            FragmentActivity activity = UserPlaylistFragment.this.getActivity();
            Intrinsics.checkNotNull(activity);
            Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
            LiveData<DownloadStatus> userPlaylistDownloadStatus = downloadProvider.getUserPlaylistDownloadStatus(id, activity);
            final UserPlaylistFragment userPlaylistFragment = UserPlaylistFragment.this;
            userPlaylistDownloadStatus.observe(userPlaylistFragment, new Observer() { // from class: com.amazon.music.curate.view.UserPlaylistFragment$onCreateView$4$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    UserPlaylistFragment.AnonymousClass4.m1961invoke$lambda0(userPlaylistFragment, playlist, tracks, i, (DownloadStatus) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invoke$lambda-0, reason: not valid java name */
        public static final void m1961invoke$lambda0(final UserPlaylistFragment this$0, final Playlist playlist, final List tracks, final int i, DownloadStatus downloadStatus) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(playlist, "$playlist");
            Intrinsics.checkNotNullParameter(tracks, "$tracks");
            if (downloadStatus == DownloadStatus.DOWNLOADED) {
                PlaybackProvider playbackProvider = this$0.getPlaybackProvider();
                FragmentActivity activity = this$0.getActivity();
                Intrinsics.checkNotNull(activity);
                playbackProvider.play(activity, playlist, tracks, i);
                return;
            }
            NetworkProvider networkProvider = this$0.getNetworkProvider();
            FragmentActivity activity2 = this$0.getActivity();
            Intrinsics.checkNotNull(activity2);
            Intrinsics.checkNotNullExpressionValue(activity2, "activity!!");
            networkProvider.ifConnected(activity2, new Function0<Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistFragment$onCreateView$4$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    PlaybackProvider playbackProvider2 = this$0.getPlaybackProvider();
                    FragmentActivity activity3 = this$0.getActivity();
                    Intrinsics.checkNotNull(activity3);
                    playbackProvider2.play(activity3, playlist, tracks, i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.curate.view.UserPlaylistFragment$onCreateView$5, reason: invalid class name */
    /* JADX INFO: compiled from: UserPlaylistFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", "tracks", "", "Lcom/amazon/music/curate/model/Track;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    static final class AnonymousClass5 extends Lambda implements Function2<Playlist, List<? extends Track>, Unit> {
        AnonymousClass5() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo4invoke(Playlist playlist, List<? extends Track> list) {
            invoke2(playlist, (List<Track>) list);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final Playlist playlist, final List<Track> tracks) {
            Intrinsics.checkNotNullParameter(playlist, "playlist");
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            DownloadProvider downloadProvider = UserPlaylistFragment.this.getDownloadProvider();
            String id = playlist.getId();
            FragmentActivity activity = UserPlaylistFragment.this.getActivity();
            Intrinsics.checkNotNull(activity);
            Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
            LiveData<DownloadStatus> userPlaylistDownloadStatus = downloadProvider.getUserPlaylistDownloadStatus(id, activity);
            final UserPlaylistFragment userPlaylistFragment = UserPlaylistFragment.this;
            userPlaylistDownloadStatus.observe(userPlaylistFragment, new Observer() { // from class: com.amazon.music.curate.view.UserPlaylistFragment$onCreateView$5$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    UserPlaylistFragment.AnonymousClass5.m1962invoke$lambda0(userPlaylistFragment, playlist, tracks, (DownloadStatus) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invoke$lambda-0, reason: not valid java name */
        public static final void m1962invoke$lambda0(final UserPlaylistFragment this$0, final Playlist playlist, final List tracks, DownloadStatus downloadStatus) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(playlist, "$playlist");
            Intrinsics.checkNotNullParameter(tracks, "$tracks");
            if (downloadStatus == DownloadStatus.DOWNLOADED) {
                PlaybackProvider playbackProvider = this$0.getPlaybackProvider();
                FragmentActivity activity = this$0.getActivity();
                Intrinsics.checkNotNull(activity);
                playbackProvider.shuffle(activity, playlist, tracks);
                return;
            }
            NetworkProvider networkProvider = this$0.getNetworkProvider();
            FragmentActivity activity2 = this$0.getActivity();
            Intrinsics.checkNotNull(activity2);
            Intrinsics.checkNotNullExpressionValue(activity2, "activity!!");
            networkProvider.ifConnected(activity2, new Function0<Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistFragment$onCreateView$5$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    PlaybackProvider playbackProvider2 = this$0.getPlaybackProvider();
                    FragmentActivity activity3 = this$0.getActivity();
                    Intrinsics.checkNotNull(activity3);
                    playbackProvider2.shuffle(activity3, playlist, tracks);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m1953onCreateView$lambda0(UserPlaylistFragment this$0, SwipeRefreshLayout swipeRefresh, PagedList pagedList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(swipeRefresh, "$swipeRefresh");
        Logger logger = LOG;
        StringBuilder sb = new StringBuilder();
        sb.append("Got ");
        VisualRowItemListAdapter visualRowItemListAdapter = null;
        sb.append(pagedList == null ? null : Integer.valueOf(pagedList.size()));
        sb.append(" tracks");
        logger.debug(sb.toString());
        VisualRowItemListAdapter visualRowItemListAdapter2 = this$0.visualRowItemListAdapter;
        if (visualRowItemListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("visualRowItemListAdapter");
        } else {
            visualRowItemListAdapter = visualRowItemListAdapter2;
        }
        Intrinsics.checkNotNull(pagedList);
        visualRowItemListAdapter.submitList(pagedList);
        swipeRefresh.setRefreshing(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-2, reason: not valid java name */
    public static final void m1954onCreateView$lambda2(final UserPlaylistFragment this$0, ViewGroup viewGroup, final RecyclerView visualItemList, Boolean isRecommendation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(visualItemList, "$visualItemList");
        if (this$0.isToastShown) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(isRecommendation, "isRecommendation");
        if (!isRecommendation.booleanValue() || viewGroup == null) {
            return;
        }
        viewGroup.post(new Runnable() { // from class: com.amazon.music.curate.view.UserPlaylistFragment$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                UserPlaylistFragment.m1955onCreateView$lambda2$lambda1(this.f$0, visualItemList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-2$lambda-1, reason: not valid java name */
    public static final void m1955onCreateView$lambda2$lambda1(UserPlaylistFragment this$0, RecyclerView visualItemList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(visualItemList, "$visualItemList");
        this$0.toast = this$0.showSeeSimilarSongsToast(visualItemList);
        this$0.isToastShown = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-4, reason: not valid java name */
    public static final void m1956onCreateView$lambda4(final UserPlaylistFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserPlaylistViewModel userPlaylistViewModel = this$0.userPlaylistViewModel;
        String str = null;
        if (userPlaylistViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
            userPlaylistViewModel = null;
        }
        String str2 = this$0.playlistId;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistId");
        } else {
            str = str2;
        }
        userPlaylistViewModel.removePlaylist(str).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.music.curate.view.UserPlaylistFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                UserPlaylistFragment.m1957onCreateView$lambda4$lambda3(this.f$0, (Unit) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-4$lambda-3, reason: not valid java name */
    public static final void m1957onCreateView$lambda4$lambda3(UserPlaylistFragment this$0, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserPlaylistViewModel userPlaylistViewModel = null;
        this$0.lastPlaylist = null;
        UserPlaylistViewModel userPlaylistViewModel2 = this$0.userPlaylistViewModel;
        if (userPlaylistViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
        } else {
            userPlaylistViewModel = userPlaylistViewModel2;
        }
        DataSource<Integer, VisualModel> value = userPlaylistViewModel.getPlaylistLiveData().getDataSource().getValue();
        if (value == null) {
            return;
        }
        value.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-5, reason: not valid java name */
    public static final void m1958onCreateView$lambda5(UserPlaylistFragment this$0, View view, Playlist playlist) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Logger logger = LOG;
        logger.debug("Got playlist");
        if (playlist == null || playlist.getNumSongs() <= 0) {
            return;
        }
        logger.debug("Loading background image");
        View viewFindViewById = view.findViewById(R.id.detail_page_frame);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.detail_page_frame)");
        this$0.loadBackgroundImage(viewFindViewById, playlist.getArtworkUrl());
        this$0.lastPlaylist = playlist;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-6, reason: not valid java name */
    public static final void m1959onCreateView$lambda6(UserPlaylistFragment this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LOG.debug("Invalidating data source because track count has changed");
        UserPlaylistViewModel userPlaylistViewModel = this$0.userPlaylistViewModel;
        if (userPlaylistViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userPlaylistViewModel");
            userPlaylistViewModel = null;
        }
        DataSource<Integer, VisualModel> value = userPlaylistViewModel.getPlaylistLiveData().getDataSource().getValue();
        if (value == null) {
            return;
        }
        value.invalidate();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_TOAST_SHOWN_KEY, this.isToastShown);
        String str = this.sessionId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Splash.PARAMS_SESSION_ID);
            str = null;
        }
        outState.putString(SESSION_ID_KEY, str);
    }

    @Override // com.amazon.music.curate.view.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        PopupWindow popupWindow = this.toast;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        this.toast = null;
        this.target = null;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        LocalBroadcastManager.getInstance(context).unregisterReceiver(this.refreshPlaylistReceiver);
        super.onDestroyView();
    }

    private final PopupWindow showSeeSimilarSongsToast(final RecyclerView recyclerView) {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        Object systemService = context.getSystemService("layout_inflater");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.LayoutInflater");
        }
        View viewInflate = ((LayoutInflater) systemService).inflate(R.layout.toast, (ViewGroup) null, false);
        if (viewInflate == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
        }
        TextView textView = (TextView) viewInflate;
        final PopupWindow popupWindow = new PopupWindow(textView, -2, -2);
        popupWindow.setAnimationStyle(R.style.Animation_AppCompat_Dialog);
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        textView.setText(context2.getResources().getString(R.string.see_similar_songs_toast_message));
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.view.UserPlaylistFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserPlaylistFragment.m1960showSeeSimilarSongsToast$lambda7(this.f$0, recyclerView, view);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.showSeeSimilarSongsToast.2
            private int totalYScroll;

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                int i = this.totalYScroll + dy;
                this.totalYScroll = i;
                if (i > 150) {
                    popupWindow.dismiss();
                    recyclerView2.removeOnScrollListener(this);
                }
            }
        });
        Rect rect = new Rect();
        View view = getView();
        if (view != null) {
            view.getGlobalVisibleRect(rect);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Context context3 = getContext();
        Intrinsics.checkNotNull(context3);
        popupWindow.showAtLocation(getView(), 81, 0, (displayMetrics.heightPixels - rect.bottom) + context3.getResources().getDimensionPixelSize(R.dimen.spacer_large));
        return popupWindow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showSeeSimilarSongsToast$lambda-7, reason: not valid java name */
    public static final void m1960showSeeSimilarSongsToast$lambda7(UserPlaylistFragment this$0, RecyclerView recyclerView, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(recyclerView, "$recyclerView");
        UiClickEvent.Builder builderWithPageType = UiClickEvent.builder(ActionType.DETAIL_USER_PLAYLIST_SEE_RECS_TOAST).withPageType(PageType.DETAIL_USER_PLAYLIST);
        String str = this$0.playlistId;
        VisualRowItemListAdapter visualRowItemListAdapter = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistId");
            str = null;
        }
        MetricsHolder.getManager().handleEvent(builderWithPageType.withEntityId(str).withEntityIdType(EntityIdType.PLAYLIST_ID).withInteractionType(InteractionType.TAP).withEntityPos(-1).build());
        VisualRowItemListAdapter visualRowItemListAdapter2 = this$0.visualRowItemListAdapter;
        if (visualRowItemListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("visualRowItemListAdapter");
        } else {
            visualRowItemListAdapter = visualRowItemListAdapter2;
        }
        recyclerView.smoothScrollToPosition(Math.max(0, visualRowItemListAdapter.getItemCount() - (15 - RECS_HEADER_SCROLL_ADJUSTMENT)));
    }

    @Override // com.amazon.music.curate.view.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ActionBar actionBar = getActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(false);
    }

    private final void loadBackgroundImage(final View view, String artworkUrl) {
        if (view.getWidth() == 0 || view.getHeight() == 0) {
            LOG.debug("Can't load background image for view with width " + view.getWidth() + " and height " + view.getHeight());
            return;
        }
        Logger logger = LOG;
        logger.debug("Loading background image for view with width " + view.getWidth() + " and height " + view.getHeight());
        logger.debug("loadBackgroundImage()");
        Target target = new Target() { // from class: com.amazon.music.curate.view.UserPlaylistFragment.loadBackgroundImage.1
            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(Drawable drawable) {
                UserPlaylistFragment.LOG.debug("onPrepareLoad()");
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapFailed(Exception exception, Drawable drawable) {
                UserPlaylistFragment.LOG.warn("Failed to load bitmap", (Throwable) exception);
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom p1) {
                UserPlaylistFragment.LOG.debug("onBitmapLoaded()");
                Context context = UserPlaylistFragment.this.getContext();
                if (context == null) {
                    return;
                }
                view.setBackground(new LayerDrawable(new Drawable[]{new BitmapDrawable(context.getResources(), bitmap), new ColorDrawable(context.getResources().getColor(R.color.color_secondary_glass_2)), context.getResources().getDrawable(R.drawable.gradient_1)}));
            }
        };
        this.target = target;
        RequestCreator requestCreatorCenterCrop = Picasso.get().load(PlaylistCollage.INSTANCE.updateSize(artworkUrl, view.getHeight())).resize(view.getWidth(), view.getHeight()).centerCrop();
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNullExpressionValue(context, "context!!");
        requestCreatorCenterCrop.transform(new BlurTransformation(context)).into(target);
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
