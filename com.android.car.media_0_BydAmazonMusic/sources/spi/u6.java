package spi;

import androidx.annotation.Nullable;
import com.android.car.media.common.MediaItemMetadata;
import java.util.List;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: MediaBrowserViewModelImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class u6 {
    private u6() {
    }

    @Nullable
    public static List<MediaItemMetadata> a(boolean z, @Nullable List<MediaItemMetadata> list) {
        if (list == null) {
            return null;
        }
        return (List) list.stream().filter(z ? n7.e : n7.f).collect(Collectors.toList());
    }
}
