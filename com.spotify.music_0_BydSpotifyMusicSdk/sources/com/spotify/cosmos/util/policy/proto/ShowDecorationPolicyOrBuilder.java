package com.spotify.cosmos.util.policy.proto;

import com.spotify.extendedmetadata.proto.ExtensionKind;
import java.util.List;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface ShowDecorationPolicyOrBuilder extends yj1 {
    boolean getConsumptionOrder();

    boolean getCopyrights();

    boolean getCovers();

    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    boolean getDescription();

    ExtensionKind getExtension(int i);

    int getExtensionCount();

    List<ExtensionKind> getExtensionList();

    int getExtensionValue(int i);

    List<Integer> getExtensionValueList();

    boolean getIsBook();

    boolean getIsCreatorChannel();

    boolean getIsExplicit();

    boolean getIsMusicAndTalk();

    boolean getLanguage();

    boolean getLink();

    boolean getMediaTypeEnum();

    boolean getName();

    boolean getNumEpisodes();

    boolean getPopularity();

    boolean getPublisher();

    boolean getTrailerUri();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
