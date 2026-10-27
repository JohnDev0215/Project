package defpackage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.chromium.base.b;

/* JADX INFO: compiled from: GSAState.java */
/* JADX INFO: loaded from: classes.dex */
public final class bs0 {

    @SuppressLint({"StaticFieldLeak"})
    public static bs0 c;
    public static final Pattern d = Pattern.compile("^(\\d+)\\.(\\d+)(\\.\\d+)*$");
    public final b<a> a = new b<>();
    public Boolean b;

    /* JADX INFO: compiled from: GSAState.java */
    public interface a {
        void a();
    }

    public static bs0 a() {
        if (c == null) {
            c = new bs0();
        }
        return c;
    }

    public static boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return true;
        }
        String[] strArrSplit = str.split("\\.", -1);
        String[] strArrSplit2 = str2.split("\\.", -1);
        int iMin = Math.min(strArrSplit.length, strArrSplit2.length);
        for (int i = 0; i < iMin; i++) {
            int i2 = Integer.parseInt(strArrSplit[i]);
            int i3 = Integer.parseInt(strArrSplit2[i]);
            if (i2 < i3) {
                return true;
            }
            if (i2 > i3) {
                return false;
            }
        }
        return strArrSplit.length < strArrSplit2.length;
    }

    public final boolean c() {
        Boolean bool = this.b;
        if (bool != null) {
            return bool.booleanValue();
        }
        Intent intent = new Intent("com.google.android.googlequicksearchbox.TEXT_ASSIST");
        intent.setPackage("com.google.android.googlequicksearchbox");
        boolean z = false;
        if (wt1.a(intent, 0)) {
            if (zt1.d("com.google.android.googlequicksearchbox") >= 300401021) {
                if (zt1.d("com.google.android.gms") >= 6577010) {
                    z = true;
                }
            }
        }
        Boolean boolValueOf = Boolean.valueOf(z);
        this.b = boolValueOf;
        return boolValueOf.booleanValue();
    }

    public final void d() {
        Iterator<a> it = this.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a();
            }
        }
    }
}
