package p;

import android.view.ViewGroup;
import com.spotify.music.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class zn2 {
    public static final vg a;
    public static final ThreadLocal b;
    public static final ArrayList c;

    static {
        vg vgVar = new vg();
        vgVar.G = false;
        vgVar.N(new xl0(2));
        vgVar.N(new wq());
        vgVar.N(new xl0(1));
        a = vgVar;
        b = new ThreadLocal();
        c = new ArrayList();
    }

    public static void a(ViewGroup viewGroup, wn2 wn2Var) {
        ArrayList arrayList = c;
        if (arrayList.contains(viewGroup)) {
            return;
        }
        WeakHashMap weakHashMap = zt2.a;
        if (lt2.c(viewGroup)) {
            arrayList.add(viewGroup);
            if (wn2Var == null) {
                wn2Var = a;
            }
            wn2 wn2VarClone = wn2Var.clone();
            ArrayList arrayList2 = (ArrayList) b().get(viewGroup);
            if (arrayList2 != null && arrayList2.size() > 0) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((wn2) it.next()).y(viewGroup);
                }
            }
            if (wn2VarClone != null) {
                wn2VarClone.k(viewGroup, true);
            }
            u4.z(viewGroup.getTag(R.id.transition_current_scene));
            viewGroup.setTag(R.id.transition_current_scene, null);
            if (wn2VarClone != null) {
                yn2 yn2Var = new yn2();
                yn2Var.c = wn2VarClone;
                yn2Var.e = viewGroup;
                viewGroup.addOnAttachStateChangeListener(yn2Var);
                viewGroup.getViewTreeObserver().addOnPreDrawListener(yn2Var);
            }
        }
    }

    public static nb b() {
        nb nbVar;
        ThreadLocal threadLocal = b;
        WeakReference weakReference = (WeakReference) threadLocal.get();
        if (weakReference != null && (nbVar = (nb) weakReference.get()) != null) {
            return nbVar;
        }
        nb nbVar2 = new nb();
        threadLocal.set(new WeakReference(nbVar2));
        return nbVar2;
    }
}
