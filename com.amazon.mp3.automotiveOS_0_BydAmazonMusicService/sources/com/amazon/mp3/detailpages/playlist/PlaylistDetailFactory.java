package com.amazon.mp3.detailpages.playlist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration;
import com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment;
import com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistDetailFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\bH\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J&\u0010\u0011\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J.\u0010\u0011\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\bH\u0007J0\u0010\u0011\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0007J\b\u0010\u0012\u001a\u00020\bH\u0002¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailFactory;", "", "()V", "bauhausFragment", "Landroidx/fragment/app/Fragment;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "forceHeroImage", "", "fragmentTitleOverride", "", "asin", "isVideo", "playlistName", "addToLibrary", "bundle", "Landroid/os/Bundle;", "createFragment", "usesBauhausPlaylistDetailPage", "createVideoPlaylistDetailFragment", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistDetailFactory {
    public static final PlaylistDetailFactory INSTANCE = new PlaylistDetailFactory();

    private PlaylistDetailFactory() {
    }

    public static /* synthetic */ Fragment createFragment$default(Intent intent, boolean z, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        return createFragment(intent, z, str);
    }

    @JvmStatic
    public static final Fragment createFragment(Intent intent, boolean forceHeroImage, String fragmentTitleOverride) {
        return createFragment(intent, forceHeroImage, fragmentTitleOverride, INSTANCE.usesBauhausPlaylistDetailPage());
    }

    @JvmStatic
    public static final Fragment createFragment(Intent intent, boolean forceHeroImage, String fragmentTitleOverride, boolean usesBauhausPlaylistDetailPage) {
        if (usesBauhausPlaylistDetailPage) {
            return INSTANCE.bauhausFragment(intent, forceHeroImage, fragmentTitleOverride);
        }
        com.amazon.mp3.library.fragment.PlaylistDetailFragment playlistDetailFragmentNewInstance = com.amazon.mp3.library.fragment.PlaylistDetailFragment.newInstance(intent, forceHeroImage, fragmentTitleOverride);
        Intrinsics.checkNotNullExpressionValue(playlistDetailFragmentNewInstance, "{\n            com.amazon…tTitleOverride)\n        }");
        return playlistDetailFragmentNewInstance;
    }

    public static /* synthetic */ Fragment createFragment$default(String str, String str2, boolean z, Bundle bundle, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return createFragment(str, str2, z, bundle);
    }

    @JvmStatic
    public static final Fragment createFragment(String asin, String playlistName, boolean addToLibrary, Bundle bundle) {
        PlaylistDetailFactory playlistDetailFactory = INSTANCE;
        if (playlistDetailFactory.usesBauhausPlaylistDetailPage()) {
            return playlistDetailFactory.bauhausFragment(asin, playlistName, addToLibrary, bundle);
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        PrimePlaylistDetailFragment primePlaylistDetailFragmentNewInstance = PrimePlaylistDetailFragment.newInstance(asin, playlistName, addToLibrary, bundle);
        Intrinsics.checkNotNullExpressionValue(primePlaylistDetailFragmentNewInstance, "{\n            PrimePlayl…le ?: Bundle())\n        }");
        return primePlaylistDetailFragmentNewInstance;
    }

    @JvmStatic
    public static final Fragment createVideoPlaylistDetailFragment(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        PlaylistDetailFactory playlistDetailFactory = INSTANCE;
        if (playlistDetailFactory.usesBauhausPlaylistDetailPage()) {
            return playlistDetailFactory.bauhausFragment(asin, true);
        }
        return VideoPlaylistDetailFragment.INSTANCE.newInstance(asin);
    }

    private final Fragment bauhausFragment(Intent intent, boolean forceHeroImage, String fragmentTitleOverride) {
        if (intent != null) {
            CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = null;
            byte b = 0;
            PlaylistDetailDependencies playlistDetailDependenciesBuildDependencies$default = PlaylistDetailDependencyFactory.buildDependencies$default(false, (Uri) intent.getParcelableExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI"), null, 4, null);
            Bundle extras = intent.getExtras();
            boolean zCanPlayOnDemand = new PlaylistModelConfiguration(cachedCapabilityActionProviderImpl, 1, b == true ? 1 : 0).canPlayOnDemand(AmazonApplication.getLibraryItemFactory().getItem((Uri) (extras == null ? null : extras.get("com.amazon.mp3.library.EXTRA_CONTENT_URI"))));
            PlaylistDetailFragment.Companion companion = PlaylistDetailFragment.INSTANCE;
            Bundle extras2 = intent.getExtras();
            if (extras2 == null) {
                extras2 = new Bundle();
            }
            return companion.newInstance(playlistDetailDependenciesBuildDependencies$default, false, extras2, Boolean.valueOf(zCanPlayOnDemand));
        }
        com.amazon.mp3.library.fragment.PlaylistDetailFragment playlistDetailFragmentNewInstance = com.amazon.mp3.library.fragment.PlaylistDetailFragment.newInstance(intent, forceHeroImage, fragmentTitleOverride);
        Intrinsics.checkNotNullExpressionValue(playlistDetailFragmentNewInstance, "newInstance(intent, forc…e, fragmentTitleOverride)");
        return playlistDetailFragmentNewInstance;
    }

    private final Fragment bauhausFragment(String asin, String playlistName, boolean addToLibrary, Bundle bundle) {
        PlaylistDetailDependencies playlistDetailDependenciesBuildDependencies = PlaylistDetailDependencyFactory.buildDependencies(true, null, asin);
        boolean zIsOnDemandPlaylist = GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandPlaylist(asin);
        PlaylistDetailFragment.Companion companion = PlaylistDetailFragment.INSTANCE;
        if (bundle == null) {
            bundle = new Bundle();
        }
        return companion.newInstance(playlistDetailDependenciesBuildDependencies, false, bundle, Boolean.valueOf(zIsOnDemandPlaylist));
    }

    private final Fragment bauhausFragment(String asin, boolean isVideo) {
        PlaylistDetailDependencies playlistDetailDependenciesBuildDependencies$default = PlaylistDetailDependencyFactory.buildDependencies$default(true, null, null, 4, null);
        Uri contentUri = MediaProvider.PrimePlaylists.getContentUri("cirrus", asin);
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        bundle.putBoolean("com.amazon.mp3.library.EXTRA_IS_VIDEO", isVideo);
        return PlaylistDetailFragment.Companion.newInstance$default(PlaylistDetailFragment.INSTANCE, playlistDetailDependenciesBuildDependencies$default, false, bundle, null, 8, null);
    }

    private final boolean usesBauhausPlaylistDetailPage() {
        return AmazonApplication.getCapabilities().isBauhausPlaylistDetailEnabled();
    }
}
