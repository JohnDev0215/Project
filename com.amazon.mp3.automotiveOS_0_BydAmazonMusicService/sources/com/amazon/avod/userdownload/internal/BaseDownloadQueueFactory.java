package com.amazon.avod.userdownload.internal;

import com.amazon.avod.userdownload.UserDownloadState;
import com.google.common.collect.ImmutableSet;

/* JADX INFO: loaded from: classes2.dex */
public class BaseDownloadQueueFactory {
    public static final ImmutableSet<UserDownloadState> DOWNLOAD_STATES = ImmutableSet.of(UserDownloadState.QUEUED, UserDownloadState.PAUSED, UserDownloadState.WAITING, UserDownloadState.DOWNLOADING, UserDownloadState.ERROR);
    public static final ImmutableSet<UserDownloadState> DELETE_STATES = ImmutableSet.of(UserDownloadState.DELETING, UserDownloadState.DELETE_REQUESTED);
}
