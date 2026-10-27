package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import java.util.List;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface TrackMetadataOrBuilder extends yj1 {
    TrackAlbumMetadata getAlbum();

    TrackArtistMetadata getArtist(int i);

    int getArtistCount();

    List<TrackArtistMetadata> getArtistList();

    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    int getDiscNumber();

    Extension getExtension(int i);

    int getExtensionCount();

    List<Extension> getExtensionList();

    boolean getHasLyrics();

    boolean getIs19PlusOnly();

    boolean getIsCurated();

    boolean getIsExplicit();

    boolean getIsLocal();

    boolean getIsPremiumOnly();

    int getLength();

    String getLink();

    ByteString getLinkBytes();

    boolean getLocallyPlayable();

    String getName();

    ByteString getNameBytes();

    boolean getPlayable();

    boolean getPlayableLocalTrack();

    String getPlayableTrackLink();

    ByteString getPlayableTrackLinkBytes();

    int getPopularity();

    String getPreviewId();

    ByteString getPreviewIdBytes();

    TrackDescriptor getTrackDescriptors(int i);

    int getTrackDescriptorsCount();

    List<TrackDescriptor> getTrackDescriptorsList();

    int getTrackNumber();

    boolean hasAlbum();

    boolean hasDiscNumber();

    boolean hasHasLyrics();

    boolean hasIs19PlusOnly();

    boolean hasIsCurated();

    boolean hasIsExplicit();

    boolean hasIsLocal();

    boolean hasIsPremiumOnly();

    boolean hasLength();

    boolean hasLink();

    boolean hasLocallyPlayable();

    boolean hasName();

    boolean hasPlayable();

    boolean hasPlayableLocalTrack();

    boolean hasPlayableTrackLink();

    boolean hasPopularity();

    boolean hasPreviewId();

    boolean hasTrackNumber();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
