package spi;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$string;

/* JADX INFO: compiled from: CommonFlags.java */
/* JADX INFO: loaded from: classes.dex */
public final class h2 {
    public static h2 c;
    public final Context a;
    public Boolean b;

    private h2(@NonNull Context context) {
        this.a = context.getApplicationContext();
    }

    public static h2 a(Context context) {
        if (c == null) {
            c = new h2(context);
        }
        return c;
    }

    public final boolean b() {
        if (this.b == null) {
            Resources resources = this.a.getResources();
            this.b = Boolean.valueOf(resources.getBoolean(R$bool.flag_improper_image_references) || "1".equals(b2.h(resources, R$string.flag_non_local_images_system_property_name)));
        }
        return this.b.booleanValue();
    }
}
