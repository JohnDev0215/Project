package androidx.preference;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class MultiSelectListPreferenceDialogFragment extends PreferenceDialogFragment {
    Set<String> k = new HashSet();
    boolean l;
    CharSequence[] m;
    CharSequence[] n;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements DialogInterface.OnMultiChoiceClickListener {
        a() {
        }

        @Override // android.content.DialogInterface.OnMultiChoiceClickListener
        public void onClick(DialogInterface dialogInterface, int i, boolean z) {
            if (z) {
                MultiSelectListPreferenceDialogFragment multiSelectListPreferenceDialogFragment = MultiSelectListPreferenceDialogFragment.this;
                multiSelectListPreferenceDialogFragment.l |= multiSelectListPreferenceDialogFragment.k.add(multiSelectListPreferenceDialogFragment.n[i].toString());
            } else {
                MultiSelectListPreferenceDialogFragment multiSelectListPreferenceDialogFragment2 = MultiSelectListPreferenceDialogFragment.this;
                multiSelectListPreferenceDialogFragment2.l |= multiSelectListPreferenceDialogFragment2.k.remove(multiSelectListPreferenceDialogFragment2.n[i].toString());
            }
        }
    }

    @Deprecated
    public MultiSelectListPreferenceDialogFragment() {
    }

    private MultiSelectListPreference h() {
        return (MultiSelectListPreference) a();
    }

    @Deprecated
    public static MultiSelectListPreferenceDialogFragment i(String str) {
        MultiSelectListPreferenceDialogFragment multiSelectListPreferenceDialogFragment = new MultiSelectListPreferenceDialogFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", str);
        multiSelectListPreferenceDialogFragment.setArguments(bundle);
        return multiSelectListPreferenceDialogFragment;
    }

    @Override // androidx.preference.PreferenceDialogFragment
    @Deprecated
    public void e(boolean z) {
        MultiSelectListPreference multiSelectListPreferenceH = h();
        if (z && this.l) {
            Set<String> set = this.k;
            if (multiSelectListPreferenceH.d(set)) {
                multiSelectListPreferenceH.N0(set);
            }
        }
        this.l = false;
    }

    @Override // androidx.preference.PreferenceDialogFragment
    protected void f(AlertDialog.Builder builder) {
        super.f(builder);
        int length = this.n.length;
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            zArr[i] = this.k.contains(this.n[i].toString());
        }
        builder.setMultiChoiceItems(this.m, zArr, new a());
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.k.clear();
            this.k.addAll(bundle.getStringArrayList("MultiSelectListPreferenceDialogFragment.values"));
            this.l = bundle.getBoolean("MultiSelectListPreferenceDialogFragment.changed", false);
            this.m = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragment.entries");
            this.n = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragment.entryValues");
            return;
        }
        MultiSelectListPreference multiSelectListPreferenceH = h();
        if (multiSelectListPreferenceH.K0() == null || multiSelectListPreferenceH.L0() == null) {
            throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
        }
        this.k.clear();
        this.k.addAll(multiSelectListPreferenceH.M0());
        this.l = false;
        this.m = multiSelectListPreferenceH.K0();
        this.n = multiSelectListPreferenceH.L0();
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("MultiSelectListPreferenceDialogFragment.values", new ArrayList<>(this.k));
        bundle.putBoolean("MultiSelectListPreferenceDialogFragment.changed", this.l);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragment.entries", this.m);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragment.entryValues", this.n);
    }
}
