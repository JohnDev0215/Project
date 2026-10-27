package defpackage;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import org.chromium.chrome.R$string;
import org.chromium.ui.base.ResourceBundle;

/* JADX INFO: compiled from: LanguageItem.java */
/* JADX INFO: loaded from: classes.dex */
public final class f51 {
    public static final j4 f = new j4(1);
    public final String a;
    public final String b;
    public final String c;
    public final boolean d;
    public final boolean e;

    public f51(String str, String str2, String str3, boolean z) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = z;
        boolean z2 = true;
        if (!TextUtils.equals(str, null) && Arrays.binarySearch(ResourceBundle.a, str, null) < 0) {
            z2 = false;
        }
        this.e = z2;
    }

    public static f51 a() {
        return new f51(null, xz.a.getResources().getString(R$string.default_lang_subtitle), xs0.d.a.getDisplayName(Locale.getDefault()), true);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof f51) {
            return TextUtils.equals(this.a, ((f51) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.a);
    }

    public final String toString() {
        return this.a;
    }
}
