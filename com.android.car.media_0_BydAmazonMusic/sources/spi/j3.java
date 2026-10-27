package spi;

import android.util.Log;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: DisabledPreferenceCallback.java */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface j3 extends fc {
    @Deprecated
    default void setMessageToShowWhenDisabledPreferenceClicked(@NonNull String str) {
        Log.w("carui", "setMessageToShowWhenDisabledPreferenceClicked is deprecated, and does nothing!");
    }
}
