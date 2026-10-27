package com.spotify.cosmos.util.policy.proto;

import com.spotify.extendedmetadata.proto.ExtensionKind;
import java.util.List;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface TrackDecorationPolicyOrBuilder extends yj1 {
    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    boolean getDiscNumber();

    ExtensionKind getExtension(int i);

    int getExtensionCount();

    List<ExtensionKind> getExtensionList();

    int getExtensionValue(int i);

    List<Integer> getExtensionValueList();

    boolean getHasLyrics();

    boolean getIs19PlusOnly();

    boolean getIsAvailableInMetadataCatalogue();

    boolean getIsCurated();

    boolean getIsExplicit();

    boolean getIsLocal();

    boolean getIsPremiumOnly();

    boolean getLength();

    boolean getLink();

    boolean getLocallyPlayable();

    boolean getName();

    boolean getPlayable();

    boolean getPlayableLocalTrack();

    boolean getPlayableTrackLink();

    boolean getPopularity();

    boolean getPreviewId();

    boolean getTrackDescriptors();

    boolean getTrackNumber();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
