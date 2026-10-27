package com.byd.dilink.view;

import androidx.annotation.NonNull;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkWindowManager {
    default void addCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
    }

    int getDisplayImePolicyForMultiIme(int i);

    int getPhysicalWindowOrientation();

    default boolean isCrossWindowBlurEnabled() {
        return false;
    }

    void registerDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener);

    default void removeCrossWindowBlurEnabledListener(@NonNull Consumer<Boolean> consumer) {
    }

    void unregisterDragDropControllerCallback(IDiLinkDragDropedListener iDiLinkDragDropedListener);

    void updateDragDropCurrentPosition(int i);
}
