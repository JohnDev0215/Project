package spi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class ke implements Iterable<Pair<? extends String, ? extends String>>, KMappedMarker {
    public static final b b = new b(null);
    public final String[] a;

    public static final class a {

        @NotNull
        public final List<String> a = new ArrayList(20);

        @NotNull
        public final a a(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            b bVar = ke.b;
            bVar.a(name);
            bVar.b(value, name);
            c(name, value);
            return this;
        }

        @NotNull
        public final a b(@NotNull String line) {
            String strSubstring;
            Intrinsics.checkNotNullParameter(line, "line");
            int iJ = StringsKt.j(line, ':', 1, 4);
            if (iJ != -1) {
                strSubstring = line.substring(0, iJ);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                line = line.substring(iJ + 1);
                Intrinsics.checkNotNullExpressionValue(line, "(this as java.lang.String).substring(startIndex)");
            } else {
                if (line.charAt(0) == ':') {
                    line = line.substring(1);
                    Intrinsics.checkNotNullExpressionValue(line, "(this as java.lang.String).substring(startIndex)");
                }
                strSubstring = "";
            }
            c(strSubstring, line);
            return this;
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
        /* JADX WARN: Type inference failed for: r2v1, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
        @NotNull
        public final a c(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            this.a.add(name);
            this.a.add(StringsKt.trim((CharSequence) value).toString());
            return this;
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
        @NotNull
        public final ke d() {
            Object[] array = this.a.toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            return new ke((String[]) array, null);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
        /* JADX WARN: Type inference failed for: r3v0, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
        /* JADX WARN: Type inference failed for: r5v2, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
        @Nullable
        public final String e(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            IntProgression intProgressionStep = RangesKt.step(RangesKt.downTo(this.a.size() - 2, 0), 2);
            int first = intProgressionStep.getFirst();
            int last = intProgressionStep.getLast();
            int step = intProgressionStep.getStep();
            if (step >= 0) {
                if (first > last) {
                    return null;
                }
            } else if (first < last) {
                return null;
            }
            while (!StringsKt.h(name, (String) this.a.get(first))) {
                if (first == last) {
                    return null;
                }
                first += step;
            }
            return (String) this.a.get(first + 1);
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
        /* JADX WARN: Type inference failed for: r1v2, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
        /* JADX WARN: Type inference failed for: r1v6, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
        /* JADX WARN: Type inference failed for: r1v7, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
        @NotNull
        public final a f(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            int i = 0;
            while (i < this.a.size()) {
                if (StringsKt.h(name, (String) this.a.get(i))) {
                    this.a.remove(i);
                    this.a.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        @NotNull
        public final a g(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            b bVar = ke.b;
            bVar.a(name);
            bVar.b(value, name);
            f(name);
            c(name, value);
            return this;
        }
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(String str) {
            if (!(str.length() > 0)) {
                throw new IllegalArgumentException("name is empty".toString());
            }
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if (!('!' <= cCharAt && '~' >= cCharAt)) {
                    throw new IllegalArgumentException(uu.h("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i), str).toString());
                }
            }
        }

        public final void b(String str, String str2) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if (!(cCharAt == '\t' || (' ' <= cCharAt && '~' >= cCharAt))) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(uu.h("Unexpected char %#04x at %d in %s value", Integer.valueOf(cCharAt), Integer.valueOf(i), str2));
                    sb.append(uu.p(str2) ? "" : d.f(": ", str));
                    throw new IllegalArgumentException(sb.toString().toString());
                }
            }
        }

        @JvmStatic
        @JvmName(name = "of")
        @NotNull
        public final ke c(@NotNull String... namesAndValues) throws CloneNotSupportedException {
            Intrinsics.checkNotNullParameter(namesAndValues, "namesAndValues");
            if (!(namesAndValues.length % 2 == 0)) {
                throw new IllegalArgumentException("Expected alternating header names and values".toString());
            }
            Object objClone = namesAndValues.clone();
            Objects.requireNonNull(objClone, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            String[] strArr = (String[]) objClone;
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (!(strArr[i] != null)) {
                    throw new IllegalArgumentException("Headers cannot be null".toString());
                }
                String str = strArr[i];
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.CharSequence");
                strArr[i] = StringsKt.trim((CharSequence) str).toString();
            }
            IntProgression intProgressionStep = RangesKt.step(ArraysKt.getIndices(strArr), 2);
            int first = intProgressionStep.getFirst();
            int last = intProgressionStep.getLast();
            int step = intProgressionStep.getStep();
            if (step < 0 ? first >= last : first <= last) {
                while (true) {
                    String str2 = strArr[first];
                    String str3 = strArr[first + 1];
                    a(str2);
                    b(str3, str2);
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
            return new ke(strArr, null);
        }
    }

    private ke(String[] strArr) {
        this.a = strArr;
    }

    public /* synthetic */ ke(String[] strArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(strArr);
    }

    @Nullable
    public final String a(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        b bVar = b;
        String[] strArr = this.a;
        Objects.requireNonNull(bVar);
        IntProgression intProgressionStep = RangesKt.step(RangesKt.downTo(strArr.length - 2, 0), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if (step < 0 ? first >= last : first <= last) {
            while (!StringsKt.h(name, strArr[first])) {
                if (first != last) {
                    first += step;
                }
            }
            return strArr[first + 1];
        }
        return null;
    }

    @NotNull
    public final String b(int i) {
        return this.a[i * 2];
    }

    @NotNull
    public final a c() {
        a aVar = new a();
        CollectionsKt.addAll(aVar.a, this.a);
        return aVar;
    }

    @NotNull
    public final String d(int i) {
        return this.a[(i * 2) + 1];
    }

    @NotNull
    public final List<String> e(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        int length = this.a.length / 2;
        ArrayList arrayList = null;
        for (int i = 0; i < length; i++) {
            if (StringsKt.h(name, b(i))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(d(i));
            }
        }
        if (arrayList == null) {
            return CollectionsKt.emptyList();
        }
        List<String> listUnmodifiableList = Collections.unmodifiableList(arrayList);
        Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "Collections.unmodifiableList(result)");
        return listUnmodifiableList;
    }

    public final boolean equals(@Nullable Object obj) {
        return (obj instanceof ke) && Arrays.equals(this.a, ((ke) obj).a);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.a);
    }

    @Override // java.lang.Iterable
    @NotNull
    public final Iterator<Pair<? extends String, ? extends String>> iterator() {
        int length = this.a.length / 2;
        Pair[] pairArr = new Pair[length];
        for (int i = 0; i < length; i++) {
            pairArr[i] = TuplesKt.to(b(i), d(i));
        }
        return ArrayIteratorKt.iterator(pairArr);
    }

    @NotNull
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int length = this.a.length / 2;
        for (int i = 0; i < length; i++) {
            String strB = b(i);
            String strD = d(i);
            sb.append(strB);
            sb.append(": ");
            if (uu.p(strB)) {
                strD = "██";
            }
            sb.append(strD);
            sb.append("\n");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
