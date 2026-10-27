package spi;

import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import com.android.car.media.common.playback.PlaybackViewModel;

/* JADX INFO: compiled from: ControlBarHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class c3 {
    public static void a(@NonNull LifecycleOwner lifecycleOwner, @NonNull PlaybackViewModel playbackViewModel, @Nullable ProgressBar progressBar, boolean z) {
        if (progressBar == null) {
            return;
        }
        if (z) {
            playbackViewModel.l.observe(lifecycleOwner, new b0(progressBar, 1));
        } else {
            progressBar.setVisibility(8);
        }
    }
}
