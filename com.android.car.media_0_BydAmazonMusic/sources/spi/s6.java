package spi;

import android.content.Context;
import android.util.Size;
import com.android.car.ui.R$integer;

/* JADX INFO: compiled from: MediaAppConfig.java */
/* JADX INFO: loaded from: classes.dex */
public final class s6 {
    private s6() {
    }

    public static Size a(Context context) {
        int integer = context.getResources().getInteger(R$integer.media_items_bitmap_max_size_px);
        return new Size(integer, integer);
    }
}
