package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import java.util.List;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface AlbumMetadataOrBuilder extends yj1 {
    AlbumArtistMetadata getArtists(int i);

    int getArtistsCount();

    List<AlbumArtistMetadata> getArtistsList();

    String getCopyright(int i);

    ByteString getCopyrightBytes(int i);

    int getCopyrightCount();

    List<String> getCopyrightList();

    ImageGroup getCovers();

    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    boolean getIsPremiumOnly();

    String getLink();

    ByteString getLinkBytes();

    String getName();

    ByteString getNameBytes();

    int getNumDiscs();

    int getNumTracks();

    boolean getPlayability();

    int getYear();

    boolean hasCovers();

    boolean hasIsPremiumOnly();

    boolean hasLink();

    boolean hasName();

    boolean hasNumDiscs();

    boolean hasNumTracks();

    boolean hasPlayability();

    boolean hasYear();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
