package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.chip.ChipGroup;
import defpackage.jc1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: CheckableGroup.java */
/* JADX INFO: loaded from: classes.dex */
public final class cn<T extends jc1<T>> {
    public final HashMap a = new HashMap();
    public final HashSet b = new HashSet();
    public a c;
    public boolean d;
    public boolean e;

    /* JADX INFO: compiled from: CheckableGroup.java */
    public interface a {
    }

    public final boolean a(jc1<T> jc1Var) {
        int id = jc1Var.getId();
        HashSet hashSet = this.b;
        if (hashSet.contains(Integer.valueOf(id))) {
            return false;
        }
        jc1<T> jc1Var2 = (jc1) this.a.get(Integer.valueOf(c()));
        if (jc1Var2 != null) {
            e(jc1Var2, false);
        }
        boolean zAdd = hashSet.add(Integer.valueOf(id));
        if (!jc1Var.isChecked()) {
            jc1Var.setChecked(true);
        }
        return zAdd;
    }

    public final ArrayList b(ViewGroup viewGroup) {
        HashSet hashSet = new HashSet(this.b);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof jc1) && hashSet.contains(Integer.valueOf(childAt.getId()))) {
                arrayList.add(Integer.valueOf(childAt.getId()));
            }
        }
        return arrayList;
    }

    public final int c() {
        if (this.d) {
            HashSet hashSet = this.b;
            if (!hashSet.isEmpty()) {
                return ((Integer) hashSet.iterator().next()).intValue();
            }
        }
        return -1;
    }

    public final void d() {
        a aVar = this.c;
        if (aVar != null) {
            new HashSet(this.b);
            ChipGroup chipGroup = ChipGroup.this;
            ChipGroup.d dVar = chipGroup.k;
            if (dVar != null) {
                chipGroup.l.b(chipGroup);
                ChipGroup chipGroup2 = ChipGroup.this;
                if (chipGroup2.l.d) {
                    chipGroup2.getCheckedChipId();
                    throw null;
                }
            }
        }
    }

    public final boolean e(jc1<T> jc1Var, boolean z) {
        int id = jc1Var.getId();
        HashSet hashSet = this.b;
        if (!hashSet.contains(Integer.valueOf(id))) {
            return false;
        }
        if (z && hashSet.size() == 1 && hashSet.contains(Integer.valueOf(id))) {
            jc1Var.setChecked(true);
            return false;
        }
        boolean zRemove = hashSet.remove(Integer.valueOf(id));
        if (jc1Var.isChecked()) {
            jc1Var.setChecked(false);
        }
        return zRemove;
    }

    public void setOnCheckedStateChangeListener(a aVar) {
        this.c = aVar;
    }
}
