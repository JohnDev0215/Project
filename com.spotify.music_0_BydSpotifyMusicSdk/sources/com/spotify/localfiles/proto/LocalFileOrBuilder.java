package com.spotify.localfiles.proto;

import com.google.protobuf.ByteString;
import com.spotify.localfiles.proto.LocalFile;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface LocalFileOrBuilder extends yj1 {
    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    LocalFile.Metadata getMetadata();

    String getPath();

    ByteString getPathBytes();

    boolean hasMetadata();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
