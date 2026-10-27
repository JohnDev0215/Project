package com.byd.dilink.hardware.camera;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkBydCameraConflictManager {
    boolean addCameraConflictCallback(int i, @NonNull IDiLinkConflictedCallback iDiLinkConflictedCallback);

    boolean removeCameraConflictCallback(int i, @NonNull IDiLinkConflictedCallback iDiLinkConflictedCallback);
}
