package defpackage;

import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: n3, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1196n3 {
    public static final ArrayList d = new ArrayList();
    public WeakHashMap a = null;
    public SparseArray b = null;
    public WeakReference c = null;

    public static C1196n3 a(View view) {
        C1196n3 c1196n3 = (C1196n3) view.getTag(AbstractC0371Um.F4);
        if (c1196n3 != null) {
            return c1196n3;
        }
        C1196n3 c1196n32 = new C1196n3();
        view.setTag(AbstractC0371Um.F4, c1196n32);
        return c1196n32;
    }

    public final View b(View view, KeyEvent keyEvent) {
        WeakHashMap weakHashMap = this.a;
        if (weakHashMap != null && weakHashMap.containsKey(view)) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                    View viewB = b(viewGroup.getChildAt(childCount), keyEvent);
                    if (viewB != null) {
                        return viewB;
                    }
                }
            }
            if (c(view, keyEvent)) {
                return view;
            }
        }
        return null;
    }

    public final boolean c(View view, KeyEvent keyEvent) {
        ArrayList arrayList = (ArrayList) view.getTag(AbstractC0371Um.G4);
        if (arrayList == null) {
            return false;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((InterfaceC1141m3) arrayList.get(size)).a(view, keyEvent)) {
                return true;
            }
        }
        return false;
    }
}
