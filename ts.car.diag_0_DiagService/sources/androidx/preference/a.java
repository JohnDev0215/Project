package androidx.preference;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
final class a extends Preference {
    private long P;

    a(Context context, List<Preference> list, long j) {
        super(context);
        E0();
        F0(list);
        this.P = j + 1000000;
    }

    private void E0() {
        m0(R$layout.expand_button);
        h0(R$drawable.ic_arrow_down_24dp);
        w0(R$string.expand_button_title);
        q0(999);
    }

    private void F0(List<Preference> list) {
        ArrayList arrayList = new ArrayList();
        CharSequence string = null;
        for (Preference preference : list) {
            CharSequence charSequenceA = preference.A();
            boolean z = preference instanceof PreferenceGroup;
            if (z && !TextUtils.isEmpty(charSequenceA)) {
                arrayList.add((PreferenceGroup) preference);
            }
            if (arrayList.contains(preference.r())) {
                if (z) {
                    arrayList.add((PreferenceGroup) preference);
                }
            } else if (!TextUtils.isEmpty(charSequenceA)) {
                string = string == null ? charSequenceA : j().getString(R$string.summary_collapsed_preference_list, string, charSequenceA);
            }
        }
        u0(string);
    }

    @Override // androidx.preference.Preference
    public void M(f fVar) {
        super.M(fVar);
        fVar.Q(false);
    }

    @Override // androidx.preference.Preference
    long n() {
        return this.P;
    }
}
