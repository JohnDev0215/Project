package defpackage;

import androidx.preference.Preference;

/* JADX INFO: compiled from: ManagedPreferenceDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public interface mb1 {
    boolean b(Preference preference);

    int d();

    boolean e();

    default boolean g(Preference preference) {
        return r(preference) || b(preference);
    }

    boolean r(Preference preference);
}
