package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import com.spotify.extendedmetadata.proto.ExtensionKind;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface ExtensionOrBuilder extends yj1 {
    ByteString getData();

    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    ExtensionKind getExtensionKind();

    boolean hasData();

    boolean hasExtensionKind();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
