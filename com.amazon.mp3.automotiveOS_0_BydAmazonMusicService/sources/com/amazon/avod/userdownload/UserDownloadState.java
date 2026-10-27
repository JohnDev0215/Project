package com.amazon.avod.userdownload;

import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum UserDownloadState {
    QUEUEING("Queueing"),
    QUEUED("Queued"),
    DOWNLOADING("Downloading"),
    DOWNLOADED("Downloaded"),
    ERROR("Error"),
    WAITING("Waiting"),
    PAUSED("Paused"),
    DELETE_REQUESTED("DeleteRequested"),
    DELETING("Deleting"),
    DELETED("Deleted");

    public static final ImmutableSet<UserDownloadState> COMPLETE_STATES;
    public static final ImmutableSet<UserDownloadState> DELETION_STATES;
    public static final ImmutableSet<UserDownloadState> IDLE_WORK_IN_PROGRESS_STATES;
    public static final ImmutableSet<UserDownloadState> WORK_NEEDED_STATES;
    private final String mPersistenceName;

    static {
        UserDownloadState userDownloadState = QUEUEING;
        UserDownloadState userDownloadState2 = QUEUED;
        UserDownloadState userDownloadState3 = DOWNLOADING;
        UserDownloadState userDownloadState4 = DOWNLOADED;
        UserDownloadState userDownloadState5 = ERROR;
        UserDownloadState userDownloadState6 = WAITING;
        UserDownloadState userDownloadState7 = PAUSED;
        UserDownloadState userDownloadState8 = DELETE_REQUESTED;
        UserDownloadState userDownloadState9 = DELETING;
        UserDownloadState userDownloadState10 = DELETED;
        ImmutableSet<UserDownloadState> immutableSetOf = ImmutableSet.of(userDownloadState7, userDownloadState2, userDownloadState6);
        IDLE_WORK_IN_PROGRESS_STATES = immutableSetOf;
        ImmutableSet<UserDownloadState> immutableSetBuild = ImmutableSet.builder().addAll((Iterable) immutableSetOf).add(userDownloadState).add(userDownloadState3).add(userDownloadState5).build();
        WORK_NEEDED_STATES = immutableSetBuild;
        ImmutableSet<UserDownloadState> immutableSetBuild2 = ImmutableSet.builder().add(userDownloadState4).build();
        COMPLETE_STATES = immutableSetBuild2;
        ImmutableSet<UserDownloadState> immutableSetBuild3 = ImmutableSet.builder().add(userDownloadState8).add(userDownloadState9).add(userDownloadState10).build();
        DELETION_STATES = immutableSetBuild3;
        Preconditions2.checkFullSetWithBlacklist(UserDownloadState.class, ImmutableSet.builder().addAll((Iterable) immutableSetBuild).addAll((Iterable) immutableSetBuild2).addAll((Iterable) immutableSetBuild3).build(), ImmutableSet.of());
    }

    UserDownloadState(@Nonnull String str) {
        this.mPersistenceName = (String) Preconditions.checkNotNull(str, "persistenceName");
    }

    public String getPersistenceName() {
        return this.mPersistenceName;
    }

    public static UserDownloadState fromPersistenceName(@Nonnull String str) {
        Preconditions.checkNotNull(str, "persistenceName");
        for (UserDownloadState userDownloadState : values()) {
            if (userDownloadState.getPersistenceName().equalsIgnoreCase(str)) {
                return userDownloadState;
            }
        }
        UserDownloadState userDownloadState2 = QUEUED;
        Preconditions2.failWeakly("DownloadStatus corresponding to persistenceName %s was not found returning %s", str, userDownloadState2);
        return userDownloadState2;
    }
}
