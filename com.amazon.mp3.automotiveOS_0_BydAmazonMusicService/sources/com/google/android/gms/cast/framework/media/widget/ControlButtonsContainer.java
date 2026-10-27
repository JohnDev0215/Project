package com.google.android.gms.cast.framework.media.widget;

import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface ControlButtonsContainer {
    @NonNull
    ImageView getButtonImageViewAt(int i) throws IndexOutOfBoundsException;

    int getButtonSlotCount();

    int getButtonTypeAt(int i) throws IndexOutOfBoundsException;

    @Nullable
    UIMediaController getUIMediaController();
}
