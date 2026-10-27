package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import java.util.List;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface ShowMetadataOrBuilder extends yj1 {
    String getConsumptionOrder();

    ByteString getConsumptionOrderBytes();

    String getCopyright(int i);

    ByteString getCopyrightBytes(int i);

    int getCopyrightCount();

    List<String> getCopyrightList();

    ImageGroup getCovers();

    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    String getDescription();

    ByteString getDescriptionBytes();

    Extension getExtension(int i);

    int getExtensionCount();

    List<Extension> getExtensionList();

    boolean getIsBook();

    boolean getIsCreatorChannel();

    boolean getIsExplicit();

    boolean getIsMusicAndTalk();

    String getLanguage();

    ByteString getLanguageBytes();

    String getLink();

    ByteString getLinkBytes();

    int getMediaTypeEnum();

    String getName();

    ByteString getNameBytes();

    int getNumEpisodes();

    int getPopularity();

    String getPublisher();

    ByteString getPublisherBytes();

    String getTrailerUri();

    ByteString getTrailerUriBytes();

    boolean hasConsumptionOrder();

    boolean hasCovers();

    boolean hasDescription();

    boolean hasIsBook();

    boolean hasIsCreatorChannel();

    boolean hasIsExplicit();

    boolean hasIsMusicAndTalk();

    boolean hasLanguage();

    boolean hasLink();

    boolean hasMediaTypeEnum();

    boolean hasName();

    boolean hasNumEpisodes();

    boolean hasPopularity();

    boolean hasPublisher();

    boolean hasTrailerUri();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
