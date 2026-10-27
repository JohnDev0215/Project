package org.chromium.components.browser_ui.accessibility;

import J.N;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import defpackage.a8;
import defpackage.ku2;
import defpackage.xz;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes2.dex */
public class FontSizePrefs {

    @SuppressLint({"StaticFieldLeak"})
    public static FontSizePrefs c;
    public final long a;
    public final b<a> b = new b<>();

    public interface a {
        void a(boolean z);

        void b(float f, float f2);
    }

    public FontSizePrefs(Profile profile) {
        this.a = N.MsRweqIy(this, profile);
    }

    public final float a() {
        ku2 ku2VarT = ku2.t();
        try {
            SharedPreferences sharedPreferences = xz.a.a;
            float fO = sharedPreferences.getFloat("user_font_scale_factor", 0.0f);
            ku2VarT.close();
            if (fO == 0.0f) {
                float fMaawwu0p = N.Maawwu0p(this.a, this);
                fO = Math.abs(fMaawwu0p - 1.0f) <= 0.001f ? 1.0f : a8.o(fMaawwu0p / xz.a.getResources().getConfiguration().fontScale, 0.5f, 2.0f);
                sharedPreferences.edit().putFloat("user_font_scale_factor", fO).apply();
            }
            return fO;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final void b(float f) {
        long j = this.a;
        float fMaawwu0p = N.Maawwu0p(j, this);
        N.M3_4WQmR(j, this, f);
        if (fMaawwu0p < 1.3f && f >= 1.3f && !N.MYwzxNqz(j, this)) {
            c(true, false);
            return;
        }
        if (fMaawwu0p < 1.3f || f >= 1.3f) {
            return;
        }
        ku2 ku2VarT = ku2.t();
        try {
            boolean z = xz.a.a.getBoolean("user_set_force_enable_zoom", false);
            ku2VarT.close();
            if (z) {
                return;
            }
            c(false, false);
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final void c(boolean z, boolean z2) {
        xz.a.a.edit().putBoolean("user_set_force_enable_zoom", z2).apply();
        N.MkBbXiXK(this.a, this, z);
    }

    @CalledByNative
    public final void onFontScaleFactorChanged(float f) {
        float fA = a();
        Iterator<a> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).b(f, fA);
            }
        }
    }

    @CalledByNative
    public final void onForceEnableZoomChanged(boolean z) {
        Iterator<a> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a(z);
            }
        }
    }
}
