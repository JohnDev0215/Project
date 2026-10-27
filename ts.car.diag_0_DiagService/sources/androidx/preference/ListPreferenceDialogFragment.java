package androidx.preference;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class ListPreferenceDialogFragment extends PreferenceDialogFragment {
    int k;
    private CharSequence[] l;
    private CharSequence[] m;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements DialogInterface.OnClickListener {
        a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            ListPreferenceDialogFragment listPreferenceDialogFragment = ListPreferenceDialogFragment.this;
            listPreferenceDialogFragment.k = i;
            listPreferenceDialogFragment.onClick(dialogInterface, -1);
            dialogInterface.dismiss();
        }
    }

    @Deprecated
    public ListPreferenceDialogFragment() {
    }

    private ListPreference h() {
        return (ListPreference) a();
    }

    @Deprecated
    public static ListPreferenceDialogFragment i(String str) {
        ListPreferenceDialogFragment listPreferenceDialogFragment = new ListPreferenceDialogFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", str);
        listPreferenceDialogFragment.setArguments(bundle);
        return listPreferenceDialogFragment;
    }

    @Override // androidx.preference.PreferenceDialogFragment
    @Deprecated
    public void e(boolean z) {
        int i;
        ListPreference listPreferenceH = h();
        if (!z || (i = this.k) < 0) {
            return;
        }
        String string = this.m[i].toString();
        if (listPreferenceH.d(string)) {
            listPreferenceH.Q0(string);
        }
    }

    @Override // androidx.preference.PreferenceDialogFragment
    protected void f(AlertDialog.Builder builder) {
        super.f(builder);
        builder.setSingleChoiceItems(this.l, this.k, new a());
        builder.setPositiveButton((CharSequence) null, (DialogInterface.OnClickListener) null);
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.k = bundle.getInt("ListPreferenceDialogFragment.index", 0);
            this.l = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entries");
            this.m = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entryValues");
            return;
        }
        ListPreference listPreferenceH = h();
        if (listPreferenceH.L0() == null || listPreferenceH.N0() == null) {
            throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
        }
        this.k = listPreferenceH.K0(listPreferenceH.O0());
        this.l = listPreferenceH.L0();
        this.m = listPreferenceH.N0();
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("ListPreferenceDialogFragment.index", this.k);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entries", this.l);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entryValues", this.m);
    }
}
