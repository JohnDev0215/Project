package com.amazon.music.platform.data.image;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.graphql.generated.AlbumDetailQuery;
import com.amazon.music.platform.graphql.generated.ArtistImagesQuery;
import com.amazon.music.platform.graphql.generated.PlaylistsQuery;
import com.amazon.music.platform.graphql.generated.TracksQuery;
import com.amazon.music.platform.graphql.generated.fragment.Album;
import com.amazon.music.platform.graphql.generated.fragment.Image;
import com.amazon.music.platform.graphql.generated.fragment.Playlist;
import com.amazon.music.platform.graphql.generated.fragment.Track;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FireFlyServiceProvider;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Optional;
import com.apollographql.apollo3.api.Query;
import com.google.android.gms.actions.SearchIntents;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: ImageUrlRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0014\u0010\u0015J$\u0010\u0007\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0001\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J&\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00062\u000e\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\tH\u0002J)\u0010\f\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/platform/data/image/ImageUrlRepository;", "", "", ContextMappingConstants.ENTITY_ID, "Lcom/amazon/music/platform/data/entity/EntityItemType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/apollographql/apollo3/api/Query;", "getQuery", SearchIntents.EXTRA_QUERY, "Lcom/apollographql/apollo3/api/ApolloResponse;", "response", "getHighestResolutionImageUrl", "getImageUrl", "(Ljava/lang/String;Lcom/amazon/music/platform/data/entity/EntityItemType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/amazon/music/platform/providers/FireFlyServiceProvider;", "provider", "Lcom/amazon/music/platform/providers/FireFlyServiceProvider;", "Lcom/amazon/music/platform/data/image/ImageUrlCache;", "imageUrlCache", "Lcom/amazon/music/platform/data/image/ImageUrlCache;", "<init>", "(Lcom/amazon/music/platform/providers/FireFlyServiceProvider;Lcom/amazon/music/platform/data/image/ImageUrlCache;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class ImageUrlRepository {
    private final ImageUrlCache imageUrlCache;
    private final FireFlyServiceProvider provider;

    /* JADX INFO: renamed from: com.amazon.music.platform.data.image.ImageUrlRepository$getImageUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: ImageUrlRepository.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.data.image.ImageUrlRepository", f = "ImageUrlRepository.kt", i = {0, 0, 0}, l = {45}, m = "getImageUrl", n = {"this", ContextMappingConstants.ENTITY_ID, SearchIntents.EXTRA_QUERY}, s = {"L$0", "L$1", "L$2"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ImageUrlRepository.this.getImageUrl(null, null, this);
        }
    }

    public ImageUrlRepository() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public ImageUrlRepository(FireFlyServiceProvider fireFlyServiceProvider, ImageUrlCache imageUrlCache) {
        this.provider = fireFlyServiceProvider;
        this.imageUrlCache = imageUrlCache;
    }

    public /* synthetic */ ImageUrlRepository(FireFlyServiceProvider fireFlyServiceProvider, ImageUrlCache imageUrlCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (FireFlyServiceProvider) Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(FireFlyServiceProvider.class)) : fireFlyServiceProvider, (i & 2) != 0 ? (ImageUrlCache) Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(ImageUrlCache.class)) : imageUrlCache);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getImageUrl(java.lang.String r7, com.amazon.music.platform.data.entity.EntityItemType r8, kotlin.coroutines.Continuation<? super java.lang.String> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.amazon.music.platform.data.image.ImageUrlRepository.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            com.amazon.music.platform.data.image.ImageUrlRepository$getImageUrl$1 r0 = (com.amazon.music.platform.data.image.ImageUrlRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.platform.data.image.ImageUrlRepository$getImageUrl$1 r0 = new com.amazon.music.platform.data.image.ImageUrlRepository$getImageUrl$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L41
            if (r2 != r3) goto L39
            java.lang.Object r7 = r0.L$2
            com.apollographql.apollo3.api.Query r7 = (com.apollographql.apollo3.api.Query) r7
            java.lang.Object r8 = r0.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r0 = r0.L$0
            com.amazon.music.platform.data.image.ImageUrlRepository r0 = (com.amazon.music.platform.data.image.ImageUrlRepository) r0
            kotlin.ResultKt.throwOnFailure(r9)
            r5 = r8
            r8 = r7
            r7 = r5
            goto L71
        L39:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L41:
            kotlin.ResultKt.throwOnFailure(r9)
            if (r7 == 0) goto L8f
            if (r8 != 0) goto L49
            goto L8f
        L49:
            com.amazon.music.platform.data.image.ImageUrlCache r9 = r6.imageUrlCache
            if (r9 == 0) goto L52
            java.lang.String r9 = r9.getUrl(r7)
            goto L53
        L52:
            r9 = r4
        L53:
            if (r9 == 0) goto L56
            return r9
        L56:
            com.apollographql.apollo3.api.Query r8 = r6.getQuery(r7, r8)
            if (r8 != 0) goto L5d
            return r4
        L5d:
            com.amazon.music.platform.providers.FireFlyServiceProvider r9 = r6.provider
            if (r9 == 0) goto L74
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r9 = r9.query(r8, r0)
            if (r9 != r1) goto L70
            return r1
        L70:
            r0 = r6
        L71:
            com.amazon.music.platform.Outcome r9 = (com.amazon.music.platform.Outcome) r9
            goto L76
        L74:
            r0 = r6
            r9 = r4
        L76:
            boolean r1 = r9 instanceof com.amazon.music.platform.Outcome.Success
            if (r1 == 0) goto L86
            com.amazon.music.platform.Outcome$Success r9 = (com.amazon.music.platform.Outcome.Success) r9
            java.lang.Object r9 = r9.getValue()
            com.apollographql.apollo3.api.ApolloResponse r9 = (com.apollographql.apollo3.api.ApolloResponse) r9
            java.lang.String r4 = r0.getHighestResolutionImageUrl(r8, r9)
        L86:
            if (r4 == 0) goto L8f
            com.amazon.music.platform.data.image.ImageUrlCache r8 = r0.imageUrlCache
            if (r8 == 0) goto L8f
            r8.putUrl(r7, r4)
        L8f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.data.image.ImageUrlRepository.getImageUrl(java.lang.String, com.amazon.music.platform.data.entity.EntityItemType, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Query<Object> getQuery(String entityId, EntityItemType entityType) {
        if (Intrinsics.areEqual(entityType, CollectionType.Album.INSTANCE)) {
            return new AlbumDetailQuery(entityId);
        }
        if (Intrinsics.areEqual(entityType, CollectionType.Artist.INSTANCE)) {
            return new ArtistImagesQuery(entityId);
        }
        if (Intrinsics.areEqual(entityType, CollectionType.Playlist.INSTANCE)) {
            return new PlaylistsQuery(new Optional.Present(CollectionsKt__CollectionsJVMKt.listOf(entityId)));
        }
        if (Intrinsics.areEqual(entityType, SingleType.Track.INSTANCE)) {
            return new TracksQuery(new Optional.Present(CollectionsKt__CollectionsJVMKt.listOf(entityId)));
        }
        return null;
    }

    private final String getHighestResolutionImageUrl(Query<?> query, ApolloResponse<Object> response) {
        List<TracksQuery.Track> tracks;
        TracksQuery.Track track;
        Track track2;
        List<Track.Image> images;
        Object next;
        Image image;
        Double width;
        Image image2;
        Double width2;
        Image image3;
        ArtistImagesQuery.Artist artist;
        List<ArtistImagesQuery.Image> images2;
        Object next2;
        Image image4;
        Double width3;
        Image image5;
        Double width4;
        Image image6;
        List<PlaylistsQuery.Playlist> playlists;
        PlaylistsQuery.Playlist playlist;
        Playlist playlist2;
        List<Playlist.Image> images3;
        Object next3;
        Image image7;
        Double width5;
        Image image8;
        Double width6;
        Image image9;
        AlbumDetailQuery.Album album;
        Album album2;
        List<Album.Image> images4;
        Object next4;
        Image image10;
        Double width7;
        Image image11;
        Double width8;
        Image image12;
        if (query instanceof AlbumDetailQuery) {
            D d = response.data;
            AlbumDetailQuery.Data data = d instanceof AlbumDetailQuery.Data ? (AlbumDetailQuery.Data) d : null;
            if (data == null || (album = data.getAlbum()) == null || (album2 = album.getAlbum()) == null || (images4 = album2.getImages()) == null) {
                return null;
            }
            Iterator<T> it = images4.iterator();
            if (it.hasNext()) {
                next4 = it.next();
                if (it.hasNext()) {
                    Album.Image image13 = (Album.Image) next4;
                    double dDoubleValue = (image13 == null || (image11 = image13.getImage()) == null || (width8 = image11.getWidth()) == null) ? 0.0d : width8.doubleValue();
                    do {
                        Object next5 = it.next();
                        Album.Image image14 = (Album.Image) next5;
                        double dDoubleValue2 = (image14 == null || (image10 = image14.getImage()) == null || (width7 = image10.getWidth()) == null) ? 0.0d : width7.doubleValue();
                        if (Double.compare(dDoubleValue, dDoubleValue2) < 0) {
                            next4 = next5;
                            dDoubleValue = dDoubleValue2;
                        }
                    } while (it.hasNext());
                }
            } else {
                next4 = null;
            }
            Album.Image image15 = (Album.Image) next4;
            if (image15 == null || (image12 = image15.getImage()) == null) {
                return null;
            }
            return image12.getUrl();
        }
        if (query instanceof PlaylistsQuery) {
            D d2 = response.data;
            PlaylistsQuery.Data data2 = d2 instanceof PlaylistsQuery.Data ? (PlaylistsQuery.Data) d2 : null;
            if (data2 == null || (playlists = data2.getPlaylists()) == null || (playlist = playlists.get(0)) == null || (playlist2 = playlist.getPlaylist()) == null || (images3 = playlist2.getImages()) == null) {
                return null;
            }
            Iterator<T> it2 = images3.iterator();
            if (it2.hasNext()) {
                next3 = it2.next();
                if (it2.hasNext()) {
                    Playlist.Image image16 = (Playlist.Image) next3;
                    double dDoubleValue3 = (image16 == null || (image8 = image16.getImage()) == null || (width6 = image8.getWidth()) == null) ? 0.0d : width6.doubleValue();
                    do {
                        Object next6 = it2.next();
                        Playlist.Image image17 = (Playlist.Image) next6;
                        double dDoubleValue4 = (image17 == null || (image7 = image17.getImage()) == null || (width5 = image7.getWidth()) == null) ? 0.0d : width5.doubleValue();
                        if (Double.compare(dDoubleValue3, dDoubleValue4) < 0) {
                            next3 = next6;
                            dDoubleValue3 = dDoubleValue4;
                        }
                    } while (it2.hasNext());
                }
            } else {
                next3 = null;
            }
            Playlist.Image image18 = (Playlist.Image) next3;
            if (image18 == null || (image9 = image18.getImage()) == null) {
                return null;
            }
            return image9.getUrl();
        }
        if (query instanceof ArtistImagesQuery) {
            D d3 = response.data;
            ArtistImagesQuery.Data data3 = d3 instanceof ArtistImagesQuery.Data ? (ArtistImagesQuery.Data) d3 : null;
            if (data3 == null || (artist = data3.getArtist()) == null || (images2 = artist.getImages()) == null) {
                return null;
            }
            Iterator<T> it3 = images2.iterator();
            if (it3.hasNext()) {
                next2 = it3.next();
                if (it3.hasNext()) {
                    ArtistImagesQuery.Image image19 = (ArtistImagesQuery.Image) next2;
                    double dDoubleValue5 = (image19 == null || (image5 = image19.getImage()) == null || (width4 = image5.getWidth()) == null) ? 0.0d : width4.doubleValue();
                    do {
                        Object next7 = it3.next();
                        ArtistImagesQuery.Image image20 = (ArtistImagesQuery.Image) next7;
                        double dDoubleValue6 = (image20 == null || (image4 = image20.getImage()) == null || (width3 = image4.getWidth()) == null) ? 0.0d : width3.doubleValue();
                        if (Double.compare(dDoubleValue5, dDoubleValue6) < 0) {
                            next2 = next7;
                            dDoubleValue5 = dDoubleValue6;
                        }
                    } while (it3.hasNext());
                }
            } else {
                next2 = null;
            }
            ArtistImagesQuery.Image image21 = (ArtistImagesQuery.Image) next2;
            if (image21 == null || (image6 = image21.getImage()) == null) {
                return null;
            }
            return image6.getUrl();
        }
        if (!(query instanceof TracksQuery)) {
            return null;
        }
        D d4 = response.data;
        TracksQuery.Data data4 = d4 instanceof TracksQuery.Data ? (TracksQuery.Data) d4 : null;
        if (data4 == null || (tracks = data4.getTracks()) == null || (track = tracks.get(0)) == null || (track2 = track.getTrack()) == null || (images = track2.getImages()) == null) {
            return null;
        }
        Iterator<T> it4 = images.iterator();
        if (it4.hasNext()) {
            next = it4.next();
            if (it4.hasNext()) {
                Track.Image image22 = (Track.Image) next;
                double dDoubleValue7 = (image22 == null || (image2 = image22.getImage()) == null || (width2 = image2.getWidth()) == null) ? 0.0d : width2.doubleValue();
                do {
                    Object next8 = it4.next();
                    Track.Image image23 = (Track.Image) next8;
                    double dDoubleValue8 = (image23 == null || (image = image23.getImage()) == null || (width = image.getWidth()) == null) ? 0.0d : width.doubleValue();
                    if (Double.compare(dDoubleValue7, dDoubleValue8) < 0) {
                        next = next8;
                        dDoubleValue7 = dDoubleValue8;
                    }
                } while (it4.hasNext());
            }
        } else {
            next = null;
        }
        Track.Image image24 = (Track.Image) next;
        if (image24 == null || (image3 = image24.getImage()) == null) {
            return null;
        }
        return image3.getUrl();
    }
}
