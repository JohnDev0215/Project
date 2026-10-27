package spi;

import android.content.Context;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

/* JADX INFO: compiled from: BasePermissions.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class w {
    public transient boolean a = false;

    public abstract String[] a();

    public final String[] b(Context context, boolean z) {
        String[] strArrA = a();
        ArrayList arrayList = new ArrayList();
        for (String str : strArrA) {
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                if (!z) {
                    arrayList.add(str);
                } else if (c(str)) {
                    arrayList.add(str);
                }
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public abstract boolean c(String str);
}
