package b.b.o.b.b;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.Locale;

/* JADX INFO: compiled from: CarLayoutMangerUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static int a(b.b.o.b.a.a aVar) {
        return b(aVar, c());
    }

    public static int b(@NonNull b.b.o.b.a.a aVar, int i) {
        return i != 1 ? i != 2 ? aVar.h() : aVar.m() : aVar.c();
    }

    public static int c() {
        boolean zD = d();
        boolean zE = e();
        if (!zD && !zE) {
            return 2;
        }
        if (zD && zE) {
            return 1;
        }
        if (!zD || !zE) {
        }
        return 0;
    }

    public static boolean d() {
        return !b.b.a.i.a.f531a;
    }

    public static boolean e() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }
}
