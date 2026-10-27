package com.amazon.mp3.cache;

import android.net.Uri;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.MusicTrack;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlbumCache.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010%\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0013\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\"\u0010#J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0016\u0010\u000b\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u0016J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0003H\u0016J\u0018\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0010H\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0019\u001a\u00020\u0007H\u0016R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\"\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00100\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\"\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00030\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010 ¨\u0006$"}, d2 = {"Lcom/amazon/mp3/cache/AlbumCache;", "Lcom/amazon/mp3/cache/CollectionCache;", "Lcom/amazon/mp3/library/item/Album;", "Lcom/amazon/mp3/library/item/MusicTrack;", "Ljava/lang/Class;", "childClass", "collection", "", "cacheCollectionObject", "", "childrenItems", "cacheChildrenItems", "item", "cacheChildItem", "", "id", "Landroid/net/Uri;", "uri", "cacheChildUri", "getCollectionUri", "getCollectionObject", "", "getChildrenItemCount", "getChildUri", "getChildItem", "clearCache", "albumUri", "Landroid/net/Uri;", "album", "Lcom/amazon/mp3/library/item/Album;", "", "trackUriMapping", "Ljava/util/Map;", "trackMapping", "<init>", "(Landroid/net/Uri;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AlbumCache implements CollectionCache<Album, MusicTrack> {
    private Album album;
    private Uri albumUri;
    private Map<Uri, MusicTrack> trackMapping;
    private Map<String, Uri> trackUriMapping;

    public AlbumCache() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.amazon.mp3.cache.CollectionCache
    public Class<MusicTrack> childClass() {
        return MusicTrack.class;
    }

    public AlbumCache(Uri uri) {
        this.albumUri = uri;
        this.trackUriMapping = new LinkedHashMap();
        this.trackMapping = new LinkedHashMap();
    }

    public /* synthetic */ AlbumCache(Uri uri, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : uri);
    }

    public void cacheCollectionObject(Album collection) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        this.album = collection;
        this.albumUri = collection.getContentUri();
    }

    public void cacheChildItem(MusicTrack item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getAsin() != null) {
            Map<String, Uri> map = this.trackUriMapping;
            String asin = item.getAsin();
            Intrinsics.checkNotNullExpressionValue(asin, "item.asin");
            Uri contentUri = item.getContentUri();
            Intrinsics.checkNotNullExpressionValue(contentUri, "item.contentUri");
            map.put(asin, contentUri);
            Map<Uri, MusicTrack> map2 = this.trackMapping;
            Uri contentUri2 = item.getContentUri();
            Intrinsics.checkNotNullExpressionValue(contentUri2, "item.contentUri");
            map2.put(contentUri2, item);
            return;
        }
        if (item.getLuid() != null) {
            Map<String, Uri> map3 = this.trackUriMapping;
            String luid = item.getLuid();
            Intrinsics.checkNotNullExpressionValue(luid, "item.luid");
            Uri contentUri3 = item.getContentUri();
            Intrinsics.checkNotNullExpressionValue(contentUri3, "item.contentUri");
            map3.put(luid, contentUri3);
            Map<Uri, MusicTrack> map4 = this.trackMapping;
            Uri contentUri4 = item.getContentUri();
            Intrinsics.checkNotNullExpressionValue(contentUri4, "item.contentUri");
            map4.put(contentUri4, item);
        }
    }

    public void cacheChildUri(String id, Uri uri) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.trackUriMapping.put(id, uri);
    }

    /* JADX INFO: renamed from: getCollectionUri, reason: from getter */
    public Uri getAlbumUri() {
        return this.albumUri;
    }

    /* JADX INFO: renamed from: getCollectionObject, reason: from getter */
    public Album getAlbum() {
        return this.album;
    }

    @Override // com.amazon.mp3.cache.CollectionCache
    public int getChildrenItemCount() {
        return this.trackMapping.size();
    }

    @Override // com.amazon.mp3.cache.CollectionCache
    public Uri getChildUri(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return this.trackUriMapping.get(id);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.mp3.cache.CollectionCache
    public MusicTrack getChildItem(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Uri childUri = getChildUri(id);
        if (childUri == null) {
            return null;
        }
        return this.trackMapping.get(childUri);
    }

    public void clearCache() {
        this.albumUri = null;
        this.album = null;
        this.trackMapping.clear();
        this.trackUriMapping.clear();
    }

    public void cacheChildrenItems(Collection<? extends MusicTrack> childrenItems) {
        Intrinsics.checkNotNullParameter(childrenItems, "childrenItems");
        Iterator<T> it = childrenItems.iterator();
        while (it.hasNext()) {
            cacheChildItem((MusicTrack) it.next());
        }
    }
}
