package com.amazon.mp3.recents;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.cache.image.ImageMetadata;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.recents.data.RecentsPlaylistData;
import com.amazon.mp3.task.JobContext;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentsSmartPlaylistsProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/recents/RecentsSmartPlaylistsProvider;", "", "artworkSize", "", "(I)V", "mPlaylistIdToSourceMap", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "fetchRecentsPlaylistsData", "", "Lcom/amazon/mp3/recents/data/RecentsPlaylistData;", "retrieveSmartPlaylistData", "playlistId", PageUriUtils.SOURCE_QUERY_PARAM, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentsSmartPlaylistsProvider {
    private int artworkSize;
    private HashMap<Long, String> mPlaylistIdToSourceMap;

    public RecentsSmartPlaylistsProvider(int i) {
        this.artworkSize = i;
        HashMap<Long, String> map = new HashMap<>();
        this.mPlaylistIdToSourceMap = map;
        map.put(3L, "cirrus");
        this.mPlaylistIdToSourceMap.put(1L, "cirrus");
        this.mPlaylistIdToSourceMap.put(2L, "cirrus-local");
    }

    public final List<RecentsPlaylistData> fetchRecentsPlaylistsData() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Long, String> entry : this.mPlaylistIdToSourceMap.entrySet()) {
            arrayList.add(retrieveSmartPlaylistData(entry.getKey().longValue(), entry.getValue()));
        }
        return arrayList;
    }

    private final RecentsPlaylistData retrieveSmartPlaylistData(long playlistId, String source) {
        Uri playlistUri = MediaProvider.SmartPlaylists.getContentUri(source, playlistId);
        PlaylistUtil.PlaylistTrackInfo playlistTrackInfo = PlaylistUtil.createPlaylistInfo(AmazonApplication.getContext(), playlistUri, CirrusMediaSource.match(playlistUri));
        ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.PLAYLIST_SMART;
        JobContext<ImageMetadata> image = ImageLoaderFactory.getInstance().getImageLoader(itemType).getImage(new JobContext<>(new ImageMetadata(itemType, source, this.artworkSize, String.valueOf(playlistId))));
        Intrinsics.checkNotNullExpressionValue(image, "getInstance().getImageLo…ype).getImage(jobContext)");
        Intrinsics.checkNotNullExpressionValue(playlistUri, "playlistUri");
        Intrinsics.checkNotNullExpressionValue(playlistTrackInfo, "playlistTrackInfo");
        return new RecentsPlaylistData(playlistId, playlistUri, playlistTrackInfo, ((ImageMetadata) image.getMetadata()).getUri());
    }
}
