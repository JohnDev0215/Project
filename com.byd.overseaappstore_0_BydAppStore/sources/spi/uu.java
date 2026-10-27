package spi;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import kotlin.ExceptionsKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ke;
import spi.mn;

/* JADX INFO: loaded from: classes2.dex */
@JvmName(name = "Util")
public final class uu {

    @JvmField
    @NotNull
    public static final byte[] a;

    @JvmField
    @NotNull
    public static final ke b = ke.b.c(new String[0]);

    @JvmField
    @NotNull
    public static final mn.b.a c;
    public static final kk d;

    @JvmField
    @NotNull
    public static final TimeZone e;
    public static final Regex f;

    @JvmField
    @NotNull
    public static final String g;

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0124, code lost:
    
        continue;
     */
    static {
        /*
            Method dump skipped, instruction units count: 410
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.uu.<clinit>():void");
    }

    @NotNull
    public static final Throwable A(@NotNull Exception withSuppressed, @NotNull List<? extends Exception> suppressed) {
        Intrinsics.checkNotNullParameter(withSuppressed, "$this$withSuppressed");
        Intrinsics.checkNotNullParameter(suppressed, "suppressed");
        if (suppressed.size() > 1) {
            System.out.println(suppressed);
        }
        Iterator<? extends Exception> it = suppressed.iterator();
        while (it.hasNext()) {
            ExceptionsKt.addSuppressed(withSuppressed, it.next());
        }
        return withSuppressed;
    }

    public static final boolean a(@NotNull hf canReuseConnectionFor, @NotNull hf other) {
        Intrinsics.checkNotNullParameter(canReuseConnectionFor, "$this$canReuseConnectionFor");
        Intrinsics.checkNotNullParameter(other, "other");
        return Intrinsics.areEqual(canReuseConnectionFor.e, other.e) && canReuseConnectionFor.f == other.f && Intrinsics.areEqual(canReuseConnectionFor.b, other.b);
    }

    public static final void b(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static final void c(@NotNull Closeable closeQuietly) {
        Intrinsics.checkNotNullParameter(closeQuietly, "$this$closeQuietly");
        try {
            closeQuietly.close();
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception unused) {
        }
    }

    public static final void d(@NotNull Socket closeQuietly) {
        Intrinsics.checkNotNullParameter(closeQuietly, "$this$closeQuietly");
        try {
            closeQuietly.close();
        } catch (AssertionError e2) {
            throw e2;
        } catch (RuntimeException e3) {
            if (!Intrinsics.areEqual(e3.getMessage(), "bio == null")) {
                throw e3;
            }
        } catch (Exception unused) {
        }
    }

    public static final int e(@NotNull String delimiterOffset, char c2, int i, int i2) {
        Intrinsics.checkNotNullParameter(delimiterOffset, "$this$delimiterOffset");
        while (i < i2) {
            if (delimiterOffset.charAt(i) == c2) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static final int f(@NotNull String delimiterOffset, @NotNull String delimiters, int i, int i2) {
        Intrinsics.checkNotNullParameter(delimiterOffset, "$this$delimiterOffset");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        while (i < i2) {
            if (StringsKt.b(delimiters, delimiterOffset.charAt(i))) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static final boolean g(@NotNull vr discard) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        Intrinsics.checkNotNullParameter(discard, "$this$discard");
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
        try {
            return u(discard, 100);
        } catch (IOException unused) {
            return false;
        }
    }

    @NotNull
    public static final String h(@NotNull String format, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.US;
        Object[] objArrCopyOf = Arrays.copyOf(args, args.length);
        String str = String.format(locale, format, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(locale, format, *args)");
        return str;
    }

    public static final boolean i(@NotNull String[] hasIntersection, @Nullable String[] strArr, @NotNull Comparator<? super String> comparator) {
        Intrinsics.checkNotNullParameter(hasIntersection, "$this$hasIntersection");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (!(hasIntersection.length == 0) && strArr != null) {
            if (!(strArr.length == 0)) {
                for (String str : hasIntersection) {
                    for (String str2 : strArr) {
                        if (comparator.compare(str, str2) == 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static final long j(@NotNull ln headersContentLength) {
        Intrinsics.checkNotNullParameter(headersContentLength, "$this$headersContentLength");
        String toLongOrDefault = headersContentLength.g.a("Content-Length");
        if (toLongOrDefault != null) {
            Intrinsics.checkNotNullParameter(toLongOrDefault, "$this$toLongOrDefault");
            try {
                return Long.parseLong(toLongOrDefault);
            } catch (NumberFormatException unused) {
            }
        }
        return -1L;
    }

    @SafeVarargs
    @NotNull
    public static final <T> List<T> k(@NotNull T... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Object[] objArr = (Object[]) elements.clone();
        List<T> listUnmodifiableList = Collections.unmodifiableList(CollectionsKt.listOf(Arrays.copyOf(objArr, objArr.length)));
        Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "Collections.unmodifiable…istOf(*elements.clone()))");
        return listUnmodifiableList;
    }

    public static final int l(@NotNull String indexOfControlOrNonAscii) {
        Intrinsics.checkNotNullParameter(indexOfControlOrNonAscii, "$this$indexOfControlOrNonAscii");
        int length = indexOfControlOrNonAscii.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = indexOfControlOrNonAscii.charAt(i);
            if (Intrinsics.compare((int) cCharAt, 31) <= 0 || Intrinsics.compare((int) cCharAt, 127) >= 0) {
                return i;
            }
        }
        return -1;
    }

    public static final int m(@NotNull String indexOfFirstNonAsciiWhitespace, int i, int i2) {
        Intrinsics.checkNotNullParameter(indexOfFirstNonAsciiWhitespace, "$this$indexOfFirstNonAsciiWhitespace");
        while (i < i2) {
            char cCharAt = indexOfFirstNonAsciiWhitespace.charAt(i);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static final int n(@NotNull String indexOfLastNonAsciiWhitespace, int i, int i2) {
        Intrinsics.checkNotNullParameter(indexOfLastNonAsciiWhitespace, "$this$indexOfLastNonAsciiWhitespace");
        int i3 = i2 - 1;
        if (i3 >= i) {
            while (true) {
                char cCharAt = indexOfLastNonAsciiWhitespace.charAt(i3);
                if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                    return i3 + 1;
                }
                if (i3 == i) {
                    break;
                }
                i3--;
            }
        }
        return i;
    }

    @NotNull
    public static final String[] o(@NotNull String[] intersect, @NotNull String[] other, @NotNull Comparator<? super String> comparator) {
        Intrinsics.checkNotNullParameter(intersect, "$this$intersect");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        ArrayList arrayList = new ArrayList();
        for (String str : intersect) {
            int length = other.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (comparator.compare(str, other[i]) == 0) {
                    arrayList.add(str);
                    break;
                }
                i++;
            }
        }
        Object[] array = arrayList.toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
        return (String[]) array;
    }

    public static final boolean p(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return StringsKt.h(name, "Authorization") || StringsKt.h(name, "Cookie") || StringsKt.h(name, "Proxy-Authorization") || StringsKt.h(name, "Set-Cookie");
    }

    public static final int q(char c2) {
        if ('0' <= c2 && '9' >= c2) {
            return c2 - '0';
        }
        char c3 = 'a';
        if ('a' > c2 || 'f' < c2) {
            c3 = 'A';
            if ('A' > c2 || 'F' < c2) {
                return -1;
            }
        }
        return (c2 - c3) + 10;
    }

    @NotNull
    public static final Charset r(@NotNull l2 readBomAsCharset, @NotNull Charset charset) throws IOException {
        Charset charset2;
        String str;
        Intrinsics.checkNotNullParameter(readBomAsCharset, "$this$readBomAsCharset");
        Intrinsics.checkNotNullParameter(charset, "default");
        int iV = readBomAsCharset.v(d);
        if (iV == -1) {
            return charset;
        }
        if (iV == 0) {
            charset2 = StandardCharsets.UTF_8;
            str = "UTF_8";
        } else if (iV == 1) {
            charset2 = StandardCharsets.UTF_16BE;
            str = "UTF_16BE";
        } else {
            if (iV != 2) {
                if (iV == 3) {
                    return Charsets.INSTANCE.UTF32_BE();
                }
                if (iV == 4) {
                    return Charsets.INSTANCE.UTF32_LE();
                }
                throw new AssertionError();
            }
            charset2 = StandardCharsets.UTF_16LE;
            str = "UTF_16LE";
        }
        Intrinsics.checkNotNullExpressionValue(charset2, str);
        return charset2;
    }

    public static final int s(@NotNull l2 readMedium) throws IOException {
        Intrinsics.checkNotNullParameter(readMedium, "$this$readMedium");
        return (readMedium.readByte() & 255) | ((readMedium.readByte() & 255) << 16) | ((readMedium.readByte() & 255) << 8);
    }

    public static final int t(@NotNull g2 skipAll, byte b2) throws EOFException {
        Intrinsics.checkNotNullParameter(skipAll, "$this$skipAll");
        int i = 0;
        while (!skipAll.h() && skipAll.F(0L) == b2) {
            i++;
            skipAll.readByte();
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006d A[PHI: r13
  0x006d: PHI (r13v3 boolean) = (r13v2 boolean), (r13v7 boolean) binds: [B:22:0x006b, B:13:0x0053] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean u(@org.jetbrains.annotations.NotNull spi.vr r12, int r13) throws java.io.IOException {
        /*
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            java.lang.String r1 = "$this$skipAll"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r1)
            java.lang.String r1 = "timeUnit"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            long r1 = java.lang.System.nanoTime()
            spi.dt r3 = r12.timeout()
            boolean r3 = r3.e()
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            if (r3 == 0) goto L29
            spi.dt r3 = r12.timeout()
            long r6 = r3.c()
            long r6 = r6 - r1
            goto L2a
        L29:
            r6 = r4
        L2a:
            spi.dt r3 = r12.timeout()
            long r8 = (long) r13
            long r8 = r0.toNanos(r8)
            long r8 = java.lang.Math.min(r6, r8)
            long r8 = r8 + r1
            r3.d(r8)
            spi.g2 r13 = new spi.g2     // Catch: java.lang.Throwable -> L56 java.io.InterruptedIOException -> L68
            r13.<init>()     // Catch: java.lang.Throwable -> L56 java.io.InterruptedIOException -> L68
        L40:
            r8 = 8192(0x2000, double:4.0474E-320)
            long r8 = r12.read(r13, r8)     // Catch: java.lang.Throwable -> L56 java.io.InterruptedIOException -> L68
            r10 = -1
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r0 == 0) goto L50
            r13.B()     // Catch: java.lang.Throwable -> L56 java.io.InterruptedIOException -> L68
            goto L40
        L50:
            r13 = 1
            int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r0 != 0) goto L75
            goto L6d
        L56:
            r13 = move-exception
            int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            spi.dt r12 = r12.timeout()
            if (r0 != 0) goto L63
            r12.a()
            goto L67
        L63:
            long r1 = r1 + r6
            r12.d(r1)
        L67:
            throw r13
        L68:
            r13 = 0
            int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r0 != 0) goto L75
        L6d:
            spi.dt r12 = r12.timeout()
            r12.a()
            goto L7d
        L75:
            spi.dt r12 = r12.timeout()
            long r1 = r1 + r6
            r12.d(r1)
        L7d:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.uu.u(spi.vr, int):boolean");
    }

    @NotNull
    public static final ke v(@NotNull List<ie> toHeaders) {
        Intrinsics.checkNotNullParameter(toHeaders, "$this$toHeaders");
        ke.a aVar = new ke.a();
        for (ie ieVar : toHeaders) {
            aVar.c(ieVar.b.utf8(), ieVar.c.utf8());
        }
        return aVar.d();
    }

    @NotNull
    public static final String w(@NotNull hf toHostHeader, boolean z) {
        Intrinsics.checkNotNullParameter(toHostHeader, "$this$toHostHeader");
        String strH = StringsKt__StringsKt.contains$default(toHostHeader.e, ":", false, 2, (Object) null) ? e.h(d.i('['), toHostHeader.e, ']') : toHostHeader.e;
        if (!z && toHostHeader.f == hf.l.b(toHostHeader.b)) {
            return strH;
        }
        return strH + ':' + toHostHeader.f;
    }

    @NotNull
    public static final <T> List<T> x(@NotNull List<? extends T> toImmutableList) {
        Intrinsics.checkNotNullParameter(toImmutableList, "$this$toImmutableList");
        List<T> listUnmodifiableList = Collections.unmodifiableList(CollectionsKt.toMutableList((Collection) toImmutableList));
        Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "Collections.unmodifiableList(toMutableList())");
        return listUnmodifiableList;
    }

    public static final int y(@Nullable String str, int i) {
        if (str != null) {
            try {
                long j = Long.parseLong(str);
                if (j > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (j < 0) {
                    return 0;
                }
                return (int) j;
            } catch (NumberFormatException unused) {
            }
        }
        return i;
    }

    @NotNull
    public static final String z(@NotNull String trimSubstring, int i, int i2) {
        Intrinsics.checkNotNullParameter(trimSubstring, "$this$trimSubstring");
        int iM = m(trimSubstring, i, i2);
        String strSubstring = trimSubstring.substring(iM, n(trimSubstring, iM, i2));
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }
}
