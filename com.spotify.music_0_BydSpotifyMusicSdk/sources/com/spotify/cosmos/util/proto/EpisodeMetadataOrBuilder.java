package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import com.spotify.cosmos.util.proto.EpisodeMetadata;
import java.util.List;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface EpisodeMetadataOrBuilder extends yj1 {
    boolean getAvailable();

    boolean getBackgroundable();

    ImageGroup getCovers();

    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    String getDescription();

    ByteString getDescriptionBytes();

    EpisodeMetadata.EpisodeType getEpisodeType();

    Extension getExtension(int i);

    int getExtensionCount();

    List<Extension> getExtensionList();

    ImageGroup getFreezeFrames();

    boolean getIs19PlusOnly();

    boolean getIsBookChapter();

    boolean getIsCurated();

    boolean getIsExplicit();

    boolean getIsMusicAndTalk();

    boolean getIsPodcastShort();

    String getLanguage();

    ByteString getLanguageBytes();

    int getLength();

    String getLink();

    ByteString getLinkBytes();

    String getManifestId();

    ByteString getManifestIdBytes();

    EpisodeMetadata.MediaType getMediaTypeEnum();

    String getName();

    ByteString getNameBytes();

    int getNumber();

    String getPreviewId();

    ByteString getPreviewIdBytes();

    String getPreviewManifestId();

    ByteString getPreviewManifestIdBytes();

    long getPublishDate();

    EpisodeShowMetadata getShow();

    boolean hasAvailable();

    boolean hasBackgroundable();

    boolean hasCovers();

    boolean hasDescription();

    boolean hasEpisodeType();

    boolean hasFreezeFrames();

    boolean hasIs19PlusOnly();

    boolean hasIsBookChapter();

    boolean hasIsCurated();

    boolean hasIsExplicit();

    boolean hasIsMusicAndTalk();

    boolean hasIsPodcastShort();

    boolean hasLanguage();

    boolean hasLength();

    boolean hasLink();

    boolean hasManifestId();

    boolean hasMediaTypeEnum();

    boolean hasName();

    boolean hasNumber();

    boolean hasPreviewId();

    boolean hasPreviewManifestId();

    boolean hasPublishDate();

    boolean hasShow();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
