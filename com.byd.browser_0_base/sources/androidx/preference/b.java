package androidx.preference;

import android.content.Context;
import android.text.TextUtils;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.n32;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ExpandButton.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends Preference {
    public final long S;

    public b(Context context, ArrayList arrayList, long j) {
        super(context);
        this.f17J = R$layout.expand_button;
        int i = R$drawable.ic_arrow_down_24dp;
        L(AppCompatResources.a(this.e, i));
        this.n = i;
        S(R$string.expand_button_title);
        N(999);
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        CharSequence string = null;
        while (it.hasNext()) {
            Preference preference = (Preference) it.next();
            CharSequence charSequence = preference.l;
            boolean z = preference instanceof PreferenceGroup;
            if (z && !TextUtils.isEmpty(charSequence)) {
                arrayList2.add((PreferenceGroup) preference);
            }
            if (arrayList2.contains(preference.N)) {
                if (z) {
                    arrayList2.add((PreferenceGroup) preference);
                }
            } else if (!TextUtils.isEmpty(charSequence)) {
                string = string == null ? charSequence : this.e.getString(R$string.summary_collapsed_preference_list, string, charSequence);
            }
        }
        R(string);
        this.S = j + 1000000;
    }

    @Override // androidx.preference.Preference
    public final long n() {
        return this.S;
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        n32Var.B = false;
    }
}
