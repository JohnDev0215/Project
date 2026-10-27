package com.amazon.mp3.moderation;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.mp3.dialog.NoNetworkEmptyPageDialog;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.reactnative.ReactExperienceNavigationRouteHelper;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ModerationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0012\u001a\u00020\u0013R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/moderation/ModerationHandler;", "", "hostActivity", "Landroidx/fragment/app/FragmentActivity;", "entity", "Lcom/amazon/mp3/library/item/AbstractItem;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/mp3/moderation/ModerationEntity;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/library/item/AbstractItem;Lcom/amazon/mp3/moderation/ModerationEntity;)V", "TAG", "", "kotlin.jvm.PlatformType", "ctaPrimeCache", "Lcom/amazon/mp3/prime/CTAPrimeCache;", "entityData", "Landroid/os/Bundle;", "moderationInitializer", "Lcom/amazon/mp3/moderation/ModerationInitializer;", "startModeration", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ModerationHandler {
    private final String TAG;
    private CTAPrimeCache ctaPrimeCache;
    private Bundle entityData;
    private final FragmentActivity hostActivity;
    private ModerationInitializer moderationInitializer;

    /* JADX INFO: compiled from: ModerationHandler.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ModerationEntity.values().length];
            iArr[ModerationEntity.PLAYLIST.ordinal()] = 1;
            iArr[ModerationEntity.ALBUM.ordinal()] = 2;
            iArr[ModerationEntity.TRACK.ordinal()] = 3;
            iArr[ModerationEntity.NOW_PLAYING_TRACK.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ModerationHandler(FragmentActivity hostActivity, AbstractItem entity, ModerationEntity entityType) {
        Bundle bundleBundleOf;
        Intrinsics.checkNotNullParameter(hostActivity, "hostActivity");
        Intrinsics.checkNotNullParameter(entity, "entity");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        this.hostActivity = hostActivity;
        this.ctaPrimeCache = new CTAPrimeCache(hostActivity);
        this.TAG = ModerationHandler.class.getSimpleName();
        int i = WhenMappings.$EnumSwitchMapping$0[entityType.ordinal()];
        if (i == 1) {
            CatalogPlaylist catalogPlaylist = (CatalogPlaylist) entity;
            bundleBundleOf = BundleKt.bundleOf(TuplesKt.to(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, ModerationEntity.PLAYLIST.toString()), TuplesKt.to(ContextMappingConstants.ENTITY_ID, catalogPlaylist.getLuid()), TuplesKt.to("entityVersion", catalogPlaylist.getVersion()), TuplesKt.to("entityTitle", catalogPlaylist.getTitle()), TuplesKt.to("entityDescription", catalogPlaylist.getDescription()), TuplesKt.to("imageUrl", catalogPlaylist.getThumbnailArtUrl()), TuplesKt.to("contentCreatorPid", catalogPlaylist.getProfileId()), TuplesKt.to("reportSource", "PLAYLIST_OVERFLOW_MENU"));
        } else if (i == 2) {
            Album album = (Album) entity;
            bundleBundleOf = BundleKt.bundleOf(TuplesKt.to(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, ModerationEntity.ALBUM.toString()), TuplesKt.to(ContextMappingConstants.ENTITY_ID, album.getAsin()), TuplesKt.to("entityTitle", album.getTitle()), TuplesKt.to("imageUrl", this.ctaPrimeCache.getArtworkUrlForAsin(album.getAsin())), TuplesKt.to("reportSource", "ALBUM_DETAIL_OVERFLOW_MENU"));
        } else if (i == 3) {
            MusicTrack musicTrack = (MusicTrack) entity;
            Pair[] pairArr = new Pair[5];
            pairArr[0] = TuplesKt.to(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, ModerationEntity.TRACK.toString());
            pairArr[1] = TuplesKt.to(ContextMappingConstants.ENTITY_ID, musicTrack.getAsin());
            pairArr[2] = TuplesKt.to("entityTitle", musicTrack.getTitle());
            pairArr[3] = TuplesKt.to("imageUrl", musicTrack.getAlbumArtUrl() != null ? musicTrack.getAlbumArtUrl() : this.ctaPrimeCache.getArtworkUrlForAsin(musicTrack.getAlbumAsin()));
            pairArr[4] = TuplesKt.to("reportSource", "TRACK_OVERFLOW_MENU");
            bundleBundleOf = BundleKt.bundleOf(pairArr);
        } else if (i == 4) {
            MusicTrack musicTrack2 = (MusicTrack) entity;
            Pair[] pairArr2 = new Pair[5];
            pairArr2[0] = TuplesKt.to(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, ModerationEntity.TRACK.toString());
            pairArr2[1] = TuplesKt.to(ContextMappingConstants.ENTITY_ID, musicTrack2.getAsin());
            pairArr2[2] = TuplesKt.to("entityTitle", musicTrack2.getTitle());
            pairArr2[3] = TuplesKt.to("imageUrl", musicTrack2.getAlbumArtUrl() != null ? musicTrack2.getAlbumArtUrl() : this.ctaPrimeCache.getArtworkUrlForAsin(musicTrack2.getAlbumAsin()));
            pairArr2[4] = TuplesKt.to("reportSource", "NOW_PLAYING");
            bundleBundleOf = BundleKt.bundleOf(pairArr2);
        } else {
            bundleBundleOf = new Bundle();
        }
        this.entityData = bundleBundleOf;
        this.moderationInitializer = new ModerationInitializer(bundleBundleOf);
    }

    public final void startModeration() {
        if (Feature.dragonfly_enabled.isEnabled() || Feature.dragonfly_fireos_enabled.isEnabled()) {
            ReactExperienceNavigationRouteHelper.INSTANCE.navigateToReportingModalRoute(this.entityData.getString(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, ""), this.entityData.getString(ContextMappingConstants.ENTITY_ID, ""), this.entityData.getString("reportSource", ""), this.hostActivity);
            return;
        }
        if (!ConnectivityUtil.hasAnyInternetConnection(this.hostActivity.getApplicationContext())) {
            NoNetworkEmptyPageDialog noNetworkEmptyPageDialog = new NoNetworkEmptyPageDialog(null, 1, 0 == true ? 1 : 0);
            FragmentManager supportFragmentManager = this.hostActivity.getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "hostActivity.supportFragmentManager");
            noNetworkEmptyPageDialog.show(supportFragmentManager, noNetworkEmptyPageDialog.getTag());
            return;
        }
        this.moderationInitializer.startModeration(this.hostActivity);
    }
}
