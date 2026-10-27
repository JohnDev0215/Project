package b.b.a.h;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: ResourceGetUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class h {
    public static String a(@NonNull Context context, int i) {
        return b(context.getResources(), i);
    }

    public static String b(@NonNull Resources resources, int i) {
        return resources.getString(i);
    }
}
