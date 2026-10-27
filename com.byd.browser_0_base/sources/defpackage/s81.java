package defpackage;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.ListPreference;
import androidx.preference.c;

/* JADX INFO: compiled from: ListPreferenceDialogFragmentCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class s81 extends c {
    public int x0;
    public CharSequence[] y0;
    public CharSequence[] z0;

    /* JADX INFO: compiled from: ListPreferenceDialogFragmentCompat.java */
    public class a implements DialogInterface.OnClickListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            s81 s81Var = s81.this;
            s81Var.x0 = i;
            s81Var.onClick(dialogInterface, -1);
            dialogInterface.dismiss();
        }
    }

    @Override // androidx.preference.c
    public final void P0(boolean z) {
        int i;
        if (!z || (i = this.x0) < 0) {
            return;
        }
        String string = this.z0[i].toString();
        ListPreference listPreference = (ListPreference) N0();
        if (listPreference.h(string)) {
            listPreference.b0(string);
        }
    }

    @Override // androidx.preference.c
    public final void Q0(AlertDialog.Builder builder) {
        builder.h(this.y0, this.x0, new a());
        builder.g(null, null);
    }

    @Override // androidx.preference.c, defpackage.r90, androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        if (bundle != null) {
            this.x0 = bundle.getInt("ListPreferenceDialogFragment.index", 0);
            this.y0 = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entries");
            this.z0 = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entryValues");
            return;
        }
        ListPreference listPreference = (ListPreference) N0();
        if (listPreference.Y == null || listPreference.Z == null) {
            throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
        }
        this.x0 = listPreference.Y(listPreference.a0);
        this.y0 = listPreference.Y;
        this.z0 = listPreference.Z;
    }

    @Override // androidx.preference.c, defpackage.r90, androidx.fragment.app.Fragment
    public final void n0(Bundle bundle) {
        super.n0(bundle);
        bundle.putInt("ListPreferenceDialogFragment.index", this.x0);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entries", this.y0);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entryValues", this.z0);
    }
}
