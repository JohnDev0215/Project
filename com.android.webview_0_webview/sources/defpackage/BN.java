package defpackage;

import android.content.Context;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class BN {
    public static final Map a;

    static {
        HashMap map = new HashMap();
        a = map;
        map.put("xiaomi", 24);
        a.put("htc", 26);
    }

    public static int a(ListAdapter listAdapter) {
        View view;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(-2, -2);
        View[] viewArr = new View[listAdapter.getViewTypeCount()];
        int iMax = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            int itemViewType = listAdapter.getItemViewType(i);
            if (itemViewType < 0) {
                view = listAdapter.getView(i, null, null);
            } else {
                viewArr[itemViewType] = listAdapter.getView(i, viewArr[itemViewType], null);
                view = viewArr[itemViewType];
            }
            view.setLayoutParams(layoutParams);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax = Math.max(iMax, view.getMeasuredWidth());
        }
        return iMax;
    }

    public static File b(Context context) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            File file = new File(context.getFilesDir(), "images");
            if (!file.exists() && !file.mkdir()) {
                throw new IOException("Folder cannot be created.");
            }
            return file;
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    public static boolean c() {
        return false;
    }

    public static void d(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup == null) {
            return;
        }
        viewGroup.removeView(view);
    }

    public static boolean e() {
        return false;
    }

    public static boolean f(Context context, InterfaceC1161mN interfaceC1161mN, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, String str) {
        return false;
    }
}
