package com.amazon.mp3.platform.mediaitem;

import android.net.Uri;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.data.identifiers.Asin;
import com.amazon.music.platform.data.identifiers.PlaylistId;
import com.amazon.music.platform.data.identifiers.StationKey;
import com.amazon.music.platform.data.identifiers.StationSeedId;
import com.amazon.music.platform.data.identifiers.UserPlaylistId;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityItemUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0004J#\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/platform/mediaitem/EntityItemUtils;", "", "()V", "getCollectionEntityItemIdentifier", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "collectionUri", "Landroid/net/Uri;", "getEntityItemTracksUri", "uri", "entityItem", "getEntityItemUri", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EntityItemUtils {
    public static final EntityItemUtils INSTANCE = new EntityItemUtils();

    /* JADX INFO: renamed from: com.amazon.mp3.platform.mediaitem.EntityItemUtils$getEntityItemUri$1, reason: invalid class name */
    /* JADX INFO: compiled from: EntityItemUtils.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.platform.mediaitem.EntityItemUtils", f = "EntityItemUtils.kt", i = {}, l = {34, 42}, m = "getEntityItemUri", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EntityItemUtils.this.getEntityItemUri(null, null, this);
        }
    }

    private EntityItemUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getEntityItemUri(android.content.Context r10, com.amazon.music.platform.data.entity.EntityItemIdentifier r11, kotlin.coroutines.Continuation<? super android.net.Uri> r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof com.amazon.mp3.platform.mediaitem.EntityItemUtils.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r12
            com.amazon.mp3.platform.mediaitem.EntityItemUtils$getEntityItemUri$1 r0 = (com.amazon.mp3.platform.mediaitem.EntityItemUtils.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.platform.mediaitem.EntityItemUtils$getEntityItemUri$1 r0 = new com.amazon.mp3.platform.mediaitem.EntityItemUtils$getEntityItemUri$1
            r0.<init>(r12)
        L18:
            r5 = r0
            java.lang.Object r12 = r5.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 2
            r3 = 1
            r8 = 0
            if (r1 == 0) goto L3a
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            kotlin.ResultKt.throwOnFailure(r12)
            goto L81
        L2e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L36:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L59
        L3a:
            kotlin.ResultKt.throwOnFailure(r12)
            com.amazon.music.platform.data.entity.EntityItemType r12 = r11.getType()
            boolean r1 = r12 instanceof com.amazon.music.platform.data.entity.SingleType.Track
            if (r1 == 0) goto L64
            com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper r12 = new com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper
            r12.<init>(r10)
            android.net.Uri r10 = r12.getContentUri(r11)
            if (r10 != 0) goto L63
            r5.label = r3
            java.lang.Object r12 = r12.m1360import(r11, r5)
            if (r12 != r0) goto L59
            return r0
        L59:
            com.amazon.mp3.library.item.MusicTrack r12 = (com.amazon.mp3.library.item.MusicTrack) r12
            if (r12 != 0) goto L5e
            goto L62
        L5e:
            android.net.Uri r8 = r12.getContentUri()
        L62:
            r10 = r8
        L63:
            return r10
        L64:
            boolean r12 = r12 instanceof com.amazon.music.platform.data.entity.CollectionType.Playlist
            if (r12 == 0) goto L8c
            com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper r1 = new com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper
            r1.<init>(r10)
            android.net.Uri r10 = r1.getContentUri(r11)
            if (r10 != 0) goto L8b
            r3 = 0
            r4 = 0
            r6 = 4
            r7 = 0
            r5.label = r2
            r2 = r11
            java.lang.Object r12 = com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper.import$default(r1, r2, r3, r4, r5, r6, r7)
            if (r12 != r0) goto L81
            return r0
        L81:
            com.amazon.mp3.playlist.CatalogPlaylist r12 = (com.amazon.mp3.playlist.CatalogPlaylist) r12
            if (r12 != 0) goto L86
            goto L8a
        L86:
            android.net.Uri r8 = r12.getContentUri()
        L8a:
            r10 = r8
        L8b:
            return r10
        L8c:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.mediaitem.EntityItemUtils.getEntityItemUri(android.content.Context, com.amazon.music.platform.data.entity.EntityItemIdentifier, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final EntityItemIdentifier getCollectionEntityItemIdentifier(Uri collectionUri) {
        Intrinsics.checkNotNullParameter(collectionUri, "collectionUri");
        int iMatch = DefaultUriMatcher.match(collectionUri);
        if (iMatch == 1) {
            return null;
        }
        if (iMatch == 5) {
            return new EntityItemIdentifier(new Asin(String.valueOf(MediaProvider.Albums.getAlbumId(collectionUri))), CollectionType.Album.INSTANCE);
        }
        if (iMatch == 9) {
            return new EntityItemIdentifier(new Asin(String.valueOf(MediaProvider.Artists.getArtistId(collectionUri))), CollectionType.Artist.INSTANCE);
        }
        if (iMatch == 28) {
            String playlistAsin = MediaProvider.PrimePlaylists.getPlaylistAsin(collectionUri);
            if (MediaProvider.isAsin(playlistAsin)) {
                return new EntityItemIdentifier(new Asin(playlistAsin.toString()), CollectionType.Playlist.INSTANCE);
            }
            return new EntityItemIdentifier(new PlaylistId(String.valueOf(MediaProvider.Playlists.getPlaylistId(collectionUri))), CollectionType.Playlist.INSTANCE);
        }
        if (iMatch != 32) {
            if (iMatch == 15) {
                return new EntityItemIdentifier(new PlaylistId(String.valueOf(MediaProvider.SmartPlaylists.getPlaylistId(collectionUri))), CollectionType.Playlist.INSTANCE);
            }
            if (iMatch != 16) {
                return null;
            }
            return new EntityItemIdentifier(new UserPlaylistId(String.valueOf(MediaProvider.Playlists.getPlaylistId(collectionUri))), CollectionType.Playlist.INSTANCE);
        }
        String id = MediaProvider.Station.getKey(collectionUri);
        Intrinsics.checkNotNullExpressionValue(id, "id");
        if (!(id.length() == 0)) {
            Intrinsics.checkNotNullExpressionValue(id, "id");
            return new EntityItemIdentifier(new StationKey(id), CollectionType.Station.INSTANCE);
        }
        String id2 = MediaProvider.Station.getLegacySeedId(collectionUri);
        Intrinsics.checkNotNullExpressionValue(id2, "id");
        return new EntityItemIdentifier(new StationSeedId(id2), CollectionType.Station.INSTANCE);
    }

    public final Uri getEntityItemTracksUri(Uri uri, EntityItemIdentifier entityItem) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(entityItem, "entityItem");
        if (entityItem.getType() instanceof SingleType.Track) {
            return uri;
        }
        if (entityItem.getType() instanceof CollectionType.Playlist) {
            return uri.buildUpon().appendEncodedPath("tracks").build();
        }
        return null;
    }
}
