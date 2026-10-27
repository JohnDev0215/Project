package com.amazon.mp3.playlist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.detailpages.playlist.PlaylistDetailFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.destination.parser.Action;
import com.amazon.music.find.utils.PageUriUtils;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityPlaylistUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J*\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rJ \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0016\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\b¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/playlist/EntityPlaylistUtil;", "", "()V", "buildEntityPlaylistFetchFragment", "Landroidx/fragment/app/Fragment;", ContextMappingConstants.ENTITY_ID, "", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/mp3/playlist/PlaylistSeedEntityType;", "buildEntityPlaylistFragment", "playlistId", "playlistTitle", "addToLibrary", "", "initiatePlayback", "buildEntityPlaylistIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "getLocalizedEntityType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EntityPlaylistUtil {
    public static final EntityPlaylistUtil INSTANCE = new EntityPlaylistUtil();

    /* JADX INFO: compiled from: EntityPlaylistUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlaylistSeedEntityType.values().length];
            iArr[PlaylistSeedEntityType.ALBUM.ordinal()] = 1;
            iArr[PlaylistSeedEntityType.ARTIST.ordinal()] = 2;
            iArr[PlaylistSeedEntityType.GENRE.ordinal()] = 3;
            iArr[PlaylistSeedEntityType.PLAYLIST.ordinal()] = 4;
            iArr[PlaylistSeedEntityType.TRACK.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private EntityPlaylistUtil() {
    }

    @JvmStatic
    public static final Fragment buildEntityPlaylistFetchFragment(String entityId, PlaylistSeedEntityType entityType) {
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        return EntityPlaylistFetchFragment.INSTANCE.newInstance(entityId, entityType);
    }

    @JvmStatic
    public static final Intent buildEntityPlaylistIntent(Context context, String entityId, PlaylistSeedEntityType entityType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intent intent = new Intent();
        intent.setClass(context, MusicHomeActivity.class);
        intent.putExtra("com.amazon.mp3.fragment.extra", EntityPlaylistFetchFragment.class.getSimpleName());
        intent.putExtra("ARG_ENTITY_ID", entityId);
        intent.putExtra("ARG_ENTITY_TYPE", entityType);
        return intent;
    }

    public final Fragment buildEntityPlaylistFragment(String playlistId, String playlistTitle, boolean addToLibrary, boolean initiatePlayback) {
        Uri contentUri = MediaProvider.SharedUserPlaylists.getContentUri("cirrus", playlistId);
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        bundle.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus");
        bundle.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        bundle.putBoolean("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
        bundle.putBoolean("EXTRA_IS_ENTITY", true);
        if (initiatePlayback && AmazonApplication.getCapabilities().isEntityPlaylistAutoPlaybackSupported()) {
            bundle.putSerializable("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION", Action.PLAY);
        }
        return PlaylistDetailFactory.createFragment(playlistId, playlistTitle, addToLibrary, bundle);
    }

    public final String getLocalizedEntityType(Context context, PlaylistSeedEntityType entityType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        int i = WhenMappings.$EnumSwitchMapping$0[entityType.ordinal()];
        if (i == 1) {
            String string = context.getString(R.string.dmusic_seed_entity_type_album);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…c_seed_entity_type_album)");
            return string;
        }
        if (i == 2) {
            String string2 = context.getString(R.string.dmusic_seed_entity_type_artist);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…_seed_entity_type_artist)");
            return string2;
        }
        if (i == 3) {
            String string3 = context.getString(R.string.dmusic_seed_entity_type_genre);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.stri…c_seed_entity_type_genre)");
            return string3;
        }
        if (i == 4) {
            String string4 = context.getString(R.string.dmusic_seed_entity_type_playlist);
            Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.stri…eed_entity_type_playlist)");
            return string4;
        }
        if (i != 5) {
            throw new NoWhenBranchMatchedException();
        }
        String string5 = context.getString(R.string.dmusic_seed_entity_type_track);
        Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.stri…c_seed_entity_type_track)");
        return string5;
    }
}
