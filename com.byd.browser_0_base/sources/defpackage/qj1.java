package defpackage;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.c;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: compiled from: MultiSelectListPreferenceDialogFragmentCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class qj1 extends c {
    public CharSequence[] A0;
    public final HashSet x0 = new HashSet();
    public boolean y0;
    public CharSequence[] z0;

    /* JADX INFO: compiled from: MultiSelectListPreferenceDialogFragmentCompat.java */
    public class a implements DialogInterface.OnMultiChoiceClickListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnMultiChoiceClickListener
        public final void onClick(DialogInterface dialogInterface, int i, boolean z) {
            qj1 qj1Var = qj1.this;
            if (z) {
                qj1Var.y0 |= qj1Var.x0.add(qj1Var.A0[i].toString());
            } else {
                qj1Var.y0 |= qj1Var.x0.remove(qj1Var.A0[i].toString());
            }
        }
    }

    @Override // androidx.preference.c
    public final void P0(boolean z) {
        if (z && this.y0) {
            MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference) N0();
            HashSet hashSet = this.x0;
            if (multiSelectListPreference.h(hashSet)) {
                multiSelectListPreference.Y(hashSet);
            }
        }
        this.y0 = false;
    }

    @Override // androidx.preference.c
    public final void Q0(AlertDialog.Builder builder) {
        int length = this.A0.length;
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            zArr[i] = this.x0.contains(this.A0[i].toString());
        }
        builder.d(this.z0, zArr, new a());
    }

    @Override // androidx.preference.c, defpackage.r90, androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        CharSequence[] charSequenceArr;
        super.c0(bundle);
        HashSet hashSet = this.x0;
        if (bundle != null) {
            hashSet.clear();
            hashSet.addAll(bundle.getStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values"));
            this.y0 = bundle.getBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", false);
            this.z0 = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries");
            this.A0 = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues");
            return;
        }
        MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference) N0();
        if (multiSelectListPreference.Y == null || (charSequenceArr = multiSelectListPreference.Z) == null) {
            throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
        }
        hashSet.clear();
        hashSet.addAll(multiSelectListPreference.a0);
        this.y0 = false;
        this.z0 = multiSelectListPreference.Y;
        this.A0 = charSequenceArr;
    }

    @Override // androidx.preference.c, defpackage.r90, androidx.fragment.app.Fragment
    public final void n0(Bundle bundle) {
        super.n0(bundle);
        bundle.putStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values", new ArrayList<>(this.x0));
        bundle.putBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", this.y0);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries", this.z0);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues", this.A0);
    }
}
