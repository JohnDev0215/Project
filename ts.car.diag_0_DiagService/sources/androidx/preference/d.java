package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1064a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private SharedPreferences f1065b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    private androidx.preference.b f1066c;

    @Nullable
    private SharedPreferences.Editor d;
    private boolean e;
    private String f;
    private int g;
    private int h = 0;
    private PreferenceScreen i;
    private AbstractC0038d j;
    private c k;
    private a l;
    private b m;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        void a(Preference preference);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface b {
        void b(PreferenceScreen preferenceScreen);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface c {
        boolean c(Preference preference);
    }

    /* JADX INFO: renamed from: androidx.preference.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class AbstractC0038d {
        public abstract boolean a(Preference preference, Preference preference2);

        public abstract boolean b(Preference preference, Preference preference2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public d(Context context) {
        this.f1064a = context;
        m(b(context));
    }

    private static String b(Context context) {
        return context.getPackageName() + "_preferences";
    }

    @Nullable
    public <T extends Preference> T a(@NonNull CharSequence charSequence) {
        PreferenceScreen preferenceScreen = this.i;
        if (preferenceScreen == null) {
            return null;
        }
        return (T) preferenceScreen.E0(charSequence);
    }

    @Nullable
    SharedPreferences.Editor c() {
        if (this.f1066c != null) {
            return null;
        }
        if (!this.e) {
            return i().edit();
        }
        if (this.d == null) {
            this.d = i().edit();
        }
        return this.d;
    }

    public b d() {
        return this.m;
    }

    public c e() {
        return this.k;
    }

    public AbstractC0038d f() {
        return this.j;
    }

    @Nullable
    public androidx.preference.b g() {
        return this.f1066c;
    }

    public PreferenceScreen h() {
        return this.i;
    }

    @Nullable
    public SharedPreferences i() {
        if (g() != null) {
            return null;
        }
        if (this.f1065b == null) {
            this.f1065b = (this.h != 1 ? this.f1064a : androidx.core.content.a.a(this.f1064a)).getSharedPreferences(this.f, this.g);
        }
        return this.f1065b;
    }

    public void j(a aVar) {
        this.l = aVar;
    }

    public void k(b bVar) {
        this.m = bVar;
    }

    public void l(c cVar) {
        this.k = cVar;
    }

    public void m(String str) {
        this.f = str;
        this.f1065b = null;
    }

    boolean n() {
        return !this.e;
    }

    public void o(Preference preference) {
        a aVar = this.l;
        if (aVar != null) {
            aVar.a(preference);
        }
    }
}
