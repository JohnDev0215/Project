package b.j;

import android.R;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapperImpl;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: DataBindingUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static d f1214a = new DataBinderMapperImpl();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static e f1215b = null;

    public static <T extends ViewDataBinding> T a(e eVar, View view, int i) {
        return (T) f1214a.b(eVar, view, i);
    }

    public static <T extends ViewDataBinding> T b(e eVar, View[] viewArr, int i) {
        return (T) f1214a.c(eVar, viewArr, i);
    }

    public static <T extends ViewDataBinding> T c(e eVar, ViewGroup viewGroup, int i, int i2) {
        int childCount = viewGroup.getChildCount();
        int i3 = childCount - i;
        if (i3 == 1) {
            return (T) a(eVar, viewGroup.getChildAt(childCount - 1), i2);
        }
        View[] viewArr = new View[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            viewArr[i4] = viewGroup.getChildAt(i4 + i);
        }
        return (T) b(eVar, viewArr, i2);
    }

    public static <T extends ViewDataBinding> T d(Activity activity, int i) {
        return (T) e(activity, i, f1215b);
    }

    public static <T extends ViewDataBinding> T e(Activity activity, int i, e eVar) {
        activity.setContentView(i);
        return (T) c(eVar, (ViewGroup) activity.getWindow().getDecorView().findViewById(R.id.content), 0, i);
    }
}
