package androidx.preference;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class ListPreferenceDialogFragment extends PreferenceDialogFragment {
    public int m;
    public CharSequence[] n;
    public CharSequence[] o;

    public class a implements DialogInterface.OnClickListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            ListPreferenceDialogFragment listPreferenceDialogFragment = ListPreferenceDialogFragment.this;
            listPreferenceDialogFragment.m = i;
            listPreferenceDialogFragment.onClick(dialogInterface, -1);
            dialogInterface.dismiss();
        }
    }

    @Deprecated
    public ListPreferenceDialogFragment() {
    }

    @Override // androidx.preference.PreferenceDialogFragment
    @Deprecated
    public final void c(boolean z) {
        int i;
        ListPreference listPreference = (ListPreference) a();
        if (!z || (i = this.m) < 0) {
            return;
        }
        String string = this.o[i].toString();
        if (listPreference.h(string)) {
            listPreference.b0(string);
        }
    }

    @Override // androidx.preference.PreferenceDialogFragment
    public final void d(AlertDialog.Builder builder) {
        builder.setSingleChoiceItems(this.n, this.m, new a());
        builder.setPositiveButton((CharSequence) null, (DialogInterface.OnClickListener) null);
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.m = bundle.getInt("ListPreferenceDialogFragment.index", 0);
            this.n = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entries");
            this.o = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entryValues");
            return;
        }
        ListPreference listPreference = (ListPreference) a();
        if (listPreference.Y == null || listPreference.Z == null) {
            throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
        }
        this.m = listPreference.Y(listPreference.a0);
        this.n = listPreference.Y;
        this.o = listPreference.Z;
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("ListPreferenceDialogFragment.index", this.m);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entries", this.n);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entryValues", this.o);
    }
}
