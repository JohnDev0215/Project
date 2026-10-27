package spi;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class ct {
    public static final List<c> a = new ArrayList();
    public static volatile c[] b = new c[0];
    public static final a c = new a();

    public static class a extends c {
        @Override // spi.ct.c
        public final void a(String str, Object... objArr) {
            for (c cVar : ct.b) {
                cVar.a(str, objArr);
            }
        }

        @Override // spi.ct.c
        public final void b(String str, Object... objArr) {
            for (c cVar : ct.b) {
                cVar.b(str, objArr);
            }
        }

        @Override // spi.ct.c
        public final void d(String str, Object... objArr) {
            for (c cVar : ct.b) {
                cVar.d(str, objArr);
            }
        }

        @Override // spi.ct.c
        public final void e(int i, String str, @NotNull String str2) {
            throw new AssertionError("Missing override for log method.");
        }

        @Override // spi.ct.c
        public final void g(String str, Object... objArr) {
            for (c cVar : ct.b) {
                cVar.g(str, objArr);
            }
        }
    }

    public static class b extends c {
        public static final Pattern b = Pattern.compile("(\\$\\d+)+$");

        @Override // spi.ct.c
        public final String c() {
            String str = this.a.get();
            if (str != null) {
                this.a.remove();
            }
            if (str != null) {
                return str;
            }
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (stackTrace.length <= 5) {
                throw new IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?");
            }
            String className = stackTrace[5].getClassName();
            Matcher matcher = b.matcher(className);
            if (matcher.find()) {
                className = matcher.replaceAll("");
            }
            String strSubstring = className.substring(className.lastIndexOf(46) + 1);
            strSubstring.length();
            return strSubstring;
        }

        @Override // spi.ct.c
        public final void e(int i, String str, @NotNull String str2) {
            int iMin;
            if (str2.length() < 4000) {
                if (i == 7) {
                    Log.wtf(str, str2);
                    return;
                } else {
                    Log.println(i, str, str2);
                    return;
                }
            }
            int i2 = 0;
            int length = str2.length();
            while (i2 < length) {
                int iIndexOf = str2.indexOf(10, i2);
                if (iIndexOf == -1) {
                    iIndexOf = length;
                }
                while (true) {
                    iMin = Math.min(iIndexOf, i2 + 4000);
                    String strSubstring = str2.substring(i2, iMin);
                    if (i == 7) {
                        Log.wtf(str, strSubstring);
                    } else {
                        Log.println(i, str, strSubstring);
                    }
                    if (iMin >= iIndexOf) {
                        break;
                    } else {
                        i2 = iMin;
                    }
                }
                i2 = iMin + 1;
            }
        }
    }

    public static abstract class c {
        public final ThreadLocal<String> a = new ThreadLocal<>();

        public void a(String str, Object... objArr) {
            f(3, str, objArr);
        }

        public void b(String str, Object... objArr) {
            f(6, str, objArr);
        }

        @Nullable
        public String c() {
            String str = this.a.get();
            if (str != null) {
                this.a.remove();
            }
            return str;
        }

        public void d(String str, Object... objArr) {
            f(4, str, objArr);
        }

        public abstract void e(int i, @Nullable String str, @NotNull String str2);

        public final void f(int i, String str, Object... objArr) {
            String strC = c();
            if (str != null && str.length() == 0) {
                str = null;
            }
            if (str == null) {
                return;
            }
            if (objArr.length > 0) {
                str = String.format(str, objArr);
            }
            e(i, strC, str);
        }

        public void g(String str, Object... objArr) {
            f(5, str, objArr);
        }
    }

    private ct() {
        throw new AssertionError("No instances.");
    }

    public static void a(@NonNls String str, Object... objArr) {
        Objects.requireNonNull(c);
        for (c cVar : b) {
            cVar.a(str, objArr);
        }
    }

    public static void b(@NonNls String str, Object... objArr) {
        Objects.requireNonNull(c);
        for (c cVar : b) {
            cVar.b(str, objArr);
        }
    }

    public static void c(@NonNls String str, Object... objArr) {
        Objects.requireNonNull(c);
        for (c cVar : b) {
            cVar.g(str, objArr);
        }
    }
}
