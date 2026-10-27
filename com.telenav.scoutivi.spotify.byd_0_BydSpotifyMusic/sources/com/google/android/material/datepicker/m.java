package com.google.android.material.datepicker;

import androidx.fragment.app.Fragment;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: PickerFragment.java */
/* JADX INFO: loaded from: classes.dex */
abstract class m<S> extends Fragment {

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    protected final LinkedHashSet<l<S>> f4493g0 = new LinkedHashSet<>();

    m() {
    }

    boolean N1(l<S> lVar) {
        return this.f4493g0.add(lVar);
    }

    void O1() {
        this.f4493g0.clear();
    }
}
