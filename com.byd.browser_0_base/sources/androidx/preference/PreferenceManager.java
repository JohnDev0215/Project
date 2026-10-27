package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public class PreferenceManager {
    public final Context a;
    public long b = 0;
    public SharedPreferences c = null;
    public SharedPreferences.Editor d;
    public boolean e;
    public String f;
    public PreferenceScreen g;
    public c h;
    public a i;
    public b j;

    public interface a {
        void y(Preference preference);
    }

    public interface b {
        void B(PreferenceScreen preferenceScreen);
    }

    public interface c {
        boolean C(Preference preference);
    }

    public PreferenceManager(Context context) {
        this.a = context;
        this.f = context.getPackageName() + "_preferences";
    }

    public final PreferenceScreen a(Context context) {
        PreferenceScreen preferenceScreen = new PreferenceScreen(context, null);
        preferenceScreen.x(this);
        return preferenceScreen;
    }

    public final SharedPreferences.Editor b() {
        if (!this.e) {
            return c().edit();
        }
        if (this.d == null) {
            this.d = c().edit();
        }
        return this.d;
    }

    public final SharedPreferences c() {
        if (this.c == null) {
            this.c = this.a.getSharedPreferences(this.f, 0);
        }
        return this.c;
    }

    public void setOnDisplayPreferenceDialogListener(a aVar) {
        this.i = aVar;
    }

    public void setOnNavigateToScreenListener(b bVar) {
        this.j = bVar;
    }

    public void setOnPreferenceTreeClickListener(c cVar) {
        this.h = cVar;
    }
}
