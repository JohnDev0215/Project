package spi;

import android.content.Context;
import android.graphics.Path;
import androidx.annotation.NonNull;
import androidx.core.graphics.PathParser;
import com.android.car.ui.R$string;

/* JADX INFO: compiled from: IconCropper.java */
/* JADX INFO: loaded from: classes.dex */
public final class t4 {

    @NonNull
    public final Path a;

    public t4(@NonNull Path path) {
        this.a = path;
    }

    public t4(@NonNull Context context) {
        this(PathParser.createPathFromPathData(context.getString(R$string.config_crop_icon_mask)));
    }
}
