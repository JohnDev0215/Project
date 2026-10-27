package b.b.a.h;

import android.util.SparseArray;
import android.view.View;
import androidx.annotation.NonNull;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: DoubleClickUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SparseArray<Long> f526a = new SparseArray<>();

    public static void a() {
        SparseArray<Long> sparseArray = f526a;
        if (sparseArray.size() > 0) {
            sparseArray.removeAtRange(0, sparseArray.size());
        }
    }

    public static boolean b(@NonNull View view, long j) {
        SparseArray<Long> sparseArray = f526a;
        Long l = sparseArray.get(view.getId(), 0L);
        if (j <= 0) {
            j = 400;
        }
        if (Math.abs(System.currentTimeMillis() - l.longValue()) > j) {
            if (sparseArray.size() > 1000) {
                sparseArray.removeAtRange(0, 1000);
            }
            sparseArray.put(view.getId(), Long.valueOf(System.currentTimeMillis()));
            return false;
        }
        BydLogHelper.logD("DoubleClickUtils", "duplicate click interval time < " + j);
        return true;
    }
}
