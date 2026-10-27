package com.amazon.mp3.recents.converters;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.recents.RecentsUtil;
import com.amazon.mp3.recents.data.RecentsPlaylistData;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: RecentsHorizontalTileConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0016J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0002H\u0016R\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/recents/converters/RecentsHorizontalTileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/mp3/recents/data/RecentsPlaylistData;", "", "contentType", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadata", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "createArtworkFrameModel", "Lcom/amazon/mp3/library/util/PlaylistUtil$PlaylistTrackInfo;", "playlistTrackInfo", "", "getSubtitle", "data", "Lcom/amazon/music/views/library/models/HorizontalTileModel;", "convert", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RecentsHorizontalTileConverter implements SingleBaseModelConverter<RecentsPlaylistData> {
    private final Context context;

    public RecentsHorizontalTileConverter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public HorizontalTileModel convert(RecentsPlaylistData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Uri playlistUri = data.getPlaylistUri();
        HashSet hashSet = new HashSet();
        RecentsUtil recentsUtil = RecentsUtil.INSTANCE;
        PlaylistMetadata playlistMetadata = new PlaylistMetadata(null, playlistUri, null, "", recentsUtil.getSmartPlaylistTitleFromId(data.getPlaylistId()), null, null, null, null, null, null, hashSet, new ArrayList(), false, null, null, null, null, false, null, false, null, null, false, null, 33548261, null);
        return new HorizontalTileModel(null, null, recentsUtil.getSmartPlaylistTitleFromId(data.getPlaylistId()), getSubtitle(data.getMPlaylistInfo()), null, createArtworkFrameModel(1, playlistMetadata), null, false, null, null, false, false, null, true, false, null, 1, playlistMetadata, null, false, false, null, false, null, null, null, false, false, false, null, 1073533907, null);
    }

    private final ArtworkFrameModel createArtworkFrameModel(int contentType, ContentMetadata contentMetadata) {
        return new ArtworkFrameModel(null, null, Integer.valueOf(contentType), null, null, null, contentMetadata, null, null, null, null, null, 4027, null);
    }

    private final String getSubtitle(PlaylistUtil.PlaylistTrackInfo playlistTrackInfo) {
        String string = this.context.getResources().getQuantityText(R.plurals.dmusic_library_songs_count, playlistTrackInfo.mTrackCount).toString();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(playlistTrackInfo.mTrackCount)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        return str;
    }
}
