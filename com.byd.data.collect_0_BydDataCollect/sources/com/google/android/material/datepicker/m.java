package com.google.android.material.datepicker;

import androidx.fragment.app.Fragment;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: PickerFragment.java */
/* JADX INFO: loaded from: classes.dex */
abstract class m<S> extends Fragment {
    protected final LinkedHashSet<l<S>> Y = new LinkedHashSet<>();

    m() {
    }

    boolean p1(l<S> lVar) {
        return this.Y.add(lVar);
    }

    void q1() {
        this.Y.clear();
    }
}
