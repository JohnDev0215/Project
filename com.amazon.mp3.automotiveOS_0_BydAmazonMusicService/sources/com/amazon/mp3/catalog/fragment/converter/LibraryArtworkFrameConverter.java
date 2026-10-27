package com.amazon.mp3.catalog.fragment.converter;

import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryArtworkFrameConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ=\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryArtworkFrameConverter;", "", "()V", "convert", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "item", "Lcom/amazon/mp3/library/item/AbstractItem;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "presetPlayState", "", "(Lcom/amazon/mp3/library/item/AbstractItem;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/Integer;)Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "imageUrl", "", "showRowCta", "(Lcom/amazon/mp3/library/item/AbstractItem;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryArtworkFrameConverter {
    public static final LibraryArtworkFrameConverter INSTANCE = new LibraryArtworkFrameConverter();

    private LibraryArtworkFrameConverter() {
    }

    public static /* synthetic */ ArtworkFrameModel convert$default(LibraryArtworkFrameConverter libraryArtworkFrameConverter, AbstractItem abstractItem, ContentMetadata contentMetadata, Integer num, int i, Object obj) {
        if ((i & 4) != 0) {
            num = null;
        }
        return libraryArtworkFrameConverter.convert(abstractItem, contentMetadata, num);
    }

    public final ArtworkFrameModel convert(AbstractItem item, ContentMetadata metadata, Integer presetPlayState) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return convert$default(this, item, metadata, null, presetPlayState, null, 16, null);
    }

    public static /* synthetic */ ArtworkFrameModel convert$default(LibraryArtworkFrameConverter libraryArtworkFrameConverter, AbstractItem abstractItem, ContentMetadata contentMetadata, String str, Integer num, Integer num2, int i, Object obj) {
        return libraryArtworkFrameConverter.convert(abstractItem, contentMetadata, str, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : num2);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amazon.music.views.library.models.ArtworkFrameModel convert(com.amazon.mp3.library.item.AbstractItem r17, com.amazon.music.views.library.metadata.ContentMetadata r18, java.lang.String r19, java.lang.Integer r20, java.lang.Integer r21) {
        /*
            r16 = this;
            r0 = r17
            r7 = r18
            java.lang.String r1 = "item"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.lang.String r1 = "metadata"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)
            boolean r1 = r0 instanceof com.amazon.mp3.library.item.Album
            r2 = 0
            if (r1 == 0) goto L1a
            r0 = 0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        L18:
            r3 = r0
            goto L3f
        L1a:
            boolean r1 = r0 instanceof com.amazon.mp3.library.item.Artist
            if (r1 == 0) goto L24
            r0 = 4
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L18
        L24:
            boolean r1 = r0 instanceof com.amazon.mp3.library.item.MusicTrack
            if (r1 == 0) goto L2e
            r0 = 2
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L18
        L2e:
            boolean r1 = r0 instanceof com.amazon.mp3.library.item.Playlist
            r3 = 1
            if (r1 == 0) goto L35
            r0 = r3
            goto L37
        L35:
            boolean r0 = r0 instanceof com.amazon.mp3.playlist.CatalogPlaylist
        L37:
            if (r0 == 0) goto L3e
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)
            goto L18
        L3e:
            r3 = r2
        L3f:
            if (r20 != 0) goto L57
            com.amazon.mp3.playback.state.LibraryEqualizerStateManager$Companion r0 = com.amazon.mp3.playback.state.LibraryEqualizerStateManager.INSTANCE
            com.amazon.music.media.playback.Playback r1 = com.amazon.music.media.playback.Playback.getInstance()
            com.amazon.music.media.playback.ControlSource r4 = com.amazon.music.media.playback.ControlSource.APP_UI
            com.amazon.music.media.playback.PlaybackController r1 = r1.getPlaybackController(r4)
            java.lang.String r4 = "getInstance().getPlaybac…ler(ControlSource.APP_UI)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            int r0 = r0.getPlaybackState(r7, r1)
            goto L5b
        L57:
            int r0 = r20.intValue()
        L5b:
            if (r21 == 0) goto L61
            com.amazon.music.views.library.styles.keys.IconStyleKey r1 = com.amazon.music.views.library.styles.keys.IconStyleKey.SONIC_RUSH_OVERLAY
            r12 = r1
            goto L62
        L61:
            r12 = r2
        L62:
            com.amazon.music.views.library.models.ArtworkFrameModel r15 = new com.amazon.music.views.library.models.ArtworkFrameModel
            r1 = 0
            r4 = 0
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r13 = 1929(0x789, float:2.703E-42)
            r14 = 0
            r0 = r15
            r2 = r19
            r6 = r21
            r7 = r18
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.converter.LibraryArtworkFrameConverter.convert(com.amazon.mp3.library.item.AbstractItem, com.amazon.music.views.library.metadata.ContentMetadata, java.lang.String, java.lang.Integer, java.lang.Integer):com.amazon.music.views.library.models.ArtworkFrameModel");
    }
}
