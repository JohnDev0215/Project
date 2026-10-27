package com.amazon.music.find.providers;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.find.model.search.PlaylistSearchItem;
import com.amazon.music.find.model.search.SearchItem;
import kotlin.Metadata;

/* JADX INFO: compiled from: CacheImageProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lcom/amazon/music/find/providers/CacheImageProvider;", "", "generatePrimePlaylistImageUri", "Landroid/net/Uri;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/find/model/search/PlaylistSearchItem;", "generateUserPlaylistImageUri", "getCacheImage", "Landroid/graphics/drawable/Drawable;", "searchItem", "Lcom/amazon/music/find/model/search/SearchItem;", "artworkSize", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface CacheImageProvider {
    Uri generatePrimePlaylistImageUri(PlaylistSearchItem playlist);

    Uri generateUserPlaylistImageUri(PlaylistSearchItem playlist);

    Drawable getCacheImage(SearchItem searchItem, int artworkSize);
}
