package com.spotify.cosmos.util.policy.proto;

import com.spotify.extendedmetadata.proto.ExtensionKind;
import java.util.List;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface EpisodeDecorationPolicyOrBuilder extends yj1 {
    boolean getAvailable();

    boolean getBackgroundable();

    boolean getCovers();

    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    boolean getDescription();

    ExtensionKind getExtension(int i);

    int getExtensionCount();

    List<ExtensionKind> getExtensionList();

    int getExtensionValue(int i);

    List<Integer> getExtensionValueList();

    boolean getFreezeFrames();

    boolean getIs19PlusOnly();

    boolean getIsBookChapter();

    boolean getIsCurated();

    boolean getIsExplicit();

    boolean getIsMusicAndTalk();

    boolean getIsPodcastShort();

    boolean getLanguage();

    boolean getLength();

    boolean getLink();

    boolean getManifestId();

    boolean getMediaTypeEnum();

    boolean getName();

    boolean getNumber();

    boolean getPreviewId();

    boolean getPreviewManifestId();

    boolean getPublishDate();

    boolean getType();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
