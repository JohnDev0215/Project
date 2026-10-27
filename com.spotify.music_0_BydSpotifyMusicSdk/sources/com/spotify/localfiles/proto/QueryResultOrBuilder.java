package com.spotify.localfiles.proto;

import java.util.List;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface QueryResultOrBuilder extends yj1 {
    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    LocalFile getFiles(int i);

    int getFilesCount();

    List<LocalFile> getFilesList();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
