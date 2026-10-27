package androidx.preference;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class MultiSelectListPreferenceDialogFragment extends PreferenceDialogFragment {
    public final HashSet m = new HashSet();
    public boolean n;
    public CharSequence[] o;
    public CharSequence[] p;

    public class a implements DialogInterface.OnMultiChoiceClickListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnMultiChoiceClickListener
        public final void onClick(DialogInterface dialogInterface, int i, boolean z) {
            MultiSelectListPreferenceDialogFragment multiSelectListPreferenceDialogFragment = MultiSelectListPreferenceDialogFragment.this;
            if (z) {
                multiSelectListPreferenceDialogFragment.n |= multiSelectListPreferenceDialogFragment.m.add(multiSelectListPreferenceDialogFragment.p[i].toString());
            } else {
                multiSelectListPreferenceDialogFragment.n |= multiSelectListPreferenceDialogFragment.m.remove(multiSelectListPreferenceDialogFragment.p[i].toString());
            }
        }
    }

    @Deprecated
    public MultiSelectListPreferenceDialogFragment() {
    }

    @Override // androidx.preference.PreferenceDialogFragment
    @Deprecated
    public final void c(boolean z) {
        MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference) a();
        if (z && this.n) {
            HashSet hashSet = this.m;
            if (multiSelectListPreference.h(hashSet)) {
                multiSelectListPreference.Y(hashSet);
            }
        }
        this.n = false;
    }

    @Override // androidx.preference.PreferenceDialogFragment
    public final void d(AlertDialog.Builder builder) {
        int length = this.p.length;
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            zArr[i] = this.m.contains(this.p[i].toString());
        }
        builder.setMultiChoiceItems(this.o, zArr, new a());
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public final void onCreate(Bundle bundle) {
        CharSequence[] charSequenceArr;
        super.onCreate(bundle);
        HashSet hashSet = this.m;
        if (bundle != null) {
            hashSet.clear();
            hashSet.addAll(bundle.getStringArrayList("MultiSelectListPreferenceDialogFragment.values"));
            this.n = bundle.getBoolean("MultiSelectListPreferenceDialogFragment.changed", false);
            this.o = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragment.entries");
            this.p = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragment.entryValues");
            return;
        }
        MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference) a();
        if (multiSelectListPreference.Y == null || (charSequenceArr = multiSelectListPreference.Z) == null) {
            throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
        }
        hashSet.clear();
        hashSet.addAll(multiSelectListPreference.a0);
        this.n = false;
        this.o = multiSelectListPreference.Y;
        this.p = charSequenceArr;
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("MultiSelectListPreferenceDialogFragment.values", new ArrayList<>(this.m));
        bundle.putBoolean("MultiSelectListPreferenceDialogFragment.changed", this.n);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragment.entries", this.o);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragment.entryValues", this.p);
    }
}
