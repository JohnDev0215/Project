package P7;

import h8.C1390g;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import n7.AbstractC1985B;
import n7.AbstractC1999n;
import n7.AbstractC2004s;
import w8.EnumC2609d;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LinkedHashSet f5454a = AbstractC1985B.r(C1390g.g("Collection", "toArray()[Ljava/lang/Object;", "toArray([Ljava/lang/Object;)[Ljava/lang/Object;"), "java/lang/annotation/Annotation.annotationType()Ljava/lang/Class;");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final LinkedHashSet f5455b;
    public static final LinkedHashSet c;
    public static final LinkedHashSet d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final LinkedHashSet f5456e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final LinkedHashSet f5457f;

    static {
        List<EnumC2609d> listD = AbstractC1999n.D(EnumC2609d.BOOLEAN, EnumC2609d.CHAR);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (EnumC2609d enumC2609d : listD) {
            String strB = enumC2609d.e().f().b();
            A7.m.e(strB, "it.wrapperFqName.shortName().asString()");
            AbstractC2004s.L(linkedHashSet, C1390g.f(strB, enumC2609d.f23359m + "Value()" + enumC2609d.c()));
        }
        f5455b = AbstractC1985B.q(AbstractC1985B.q(AbstractC1985B.q(AbstractC1985B.q(AbstractC1985B.q(AbstractC1985B.q(linkedHashSet, C1390g.g("List", "sort(Ljava/util/Comparator;)V")), C1390g.f("String", "codePointAt(I)I", "codePointBefore(I)I", "codePointCount(II)I", "compareToIgnoreCase(Ljava/lang/String;)I", "concat(Ljava/lang/String;)Ljava/lang/String;", "contains(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/StringBuffer;)Z", "endsWith(Ljava/lang/String;)Z", "equalsIgnoreCase(Ljava/lang/String;)Z", "getBytes()[B", "getBytes(II[BI)V", "getBytes(Ljava/lang/String;)[B", "getBytes(Ljava/nio/charset/Charset;)[B", "getChars(II[CI)V", "indexOf(I)I", "indexOf(II)I", "indexOf(Ljava/lang/String;)I", "indexOf(Ljava/lang/String;I)I", "intern()Ljava/lang/String;", "isEmpty()Z", "lastIndexOf(I)I", "lastIndexOf(II)I", "lastIndexOf(Ljava/lang/String;)I", "lastIndexOf(Ljava/lang/String;I)I", "matches(Ljava/lang/String;)Z", "offsetByCodePoints(II)I", "regionMatches(ILjava/lang/String;II)Z", "regionMatches(ZILjava/lang/String;II)Z", "replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(CC)Ljava/lang/String;", "replaceFirst(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;", "split(Ljava/lang/String;I)[Ljava/lang/String;", "split(Ljava/lang/String;)[Ljava/lang/String;", "startsWith(Ljava/lang/String;I)Z", "startsWith(Ljava/lang/String;)Z", "substring(II)Ljava/lang/String;", "substring(I)Ljava/lang/String;", "toCharArray()[C", "toLowerCase()Ljava/lang/String;", "toLowerCase(Ljava/util/Locale;)Ljava/lang/String;", "toUpperCase()Ljava/lang/String;", "toUpperCase(Ljava/util/Locale;)Ljava/lang/String;", "trim()Ljava/lang/String;", "isBlank()Z", "lines()Ljava/util/stream/Stream;", "repeat(I)Ljava/lang/String;")), C1390g.f("Double", "isInfinite()Z", "isNaN()Z")), C1390g.f("Float", "isInfinite()Z", "isNaN()Z")), C1390g.f("Enum", "getDeclaringClass()Ljava/lang/Class;", "finalize()V")), C1390g.f("CharSequence", "isEmpty()Z"));
        c = AbstractC1985B.q(AbstractC1985B.q(AbstractC1985B.q(AbstractC1985B.q(AbstractC1985B.q(AbstractC1985B.q(C1390g.f("CharSequence", "codePoints()Ljava/util/stream/IntStream;", "chars()Ljava/util/stream/IntStream;"), C1390g.g("Iterator", "forEachRemaining(Ljava/util/function/Consumer;)V")), C1390g.f("Iterable", "forEach(Ljava/util/function/Consumer;)V", "spliterator()Ljava/util/Spliterator;")), C1390g.f("Throwable", "setStackTrace([Ljava/lang/StackTraceElement;)V", "fillInStackTrace()Ljava/lang/Throwable;", "getLocalizedMessage()Ljava/lang/String;", "printStackTrace()V", "printStackTrace(Ljava/io/PrintStream;)V", "printStackTrace(Ljava/io/PrintWriter;)V", "getStackTrace()[Ljava/lang/StackTraceElement;", "initCause(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "getSuppressed()[Ljava/lang/Throwable;", "addSuppressed(Ljava/lang/Throwable;)V")), C1390g.g("Collection", "spliterator()Ljava/util/Spliterator;", "parallelStream()Ljava/util/stream/Stream;", "stream()Ljava/util/stream/Stream;", "removeIf(Ljava/util/function/Predicate;)Z")), C1390g.g("List", "replaceAll(Ljava/util/function/UnaryOperator;)V")), C1390g.g("Map", "getOrDefault(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "forEach(Ljava/util/function/BiConsumer;)V", "replaceAll(Ljava/util/function/BiFunction;)V", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;"));
        d = AbstractC1985B.q(AbstractC1985B.q(C1390g.g("Collection", "removeIf(Ljava/util/function/Predicate;)Z"), C1390g.g("List", "replaceAll(Ljava/util/function/UnaryOperator;)V", "sort(Ljava/util/Comparator;)V")), C1390g.g("Map", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove(Ljava/lang/Object;Ljava/lang/Object;)Z", "replaceAll(Ljava/util/function/BiFunction;)V", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z"));
        EnumC2609d enumC2609d2 = EnumC2609d.BOOLEAN;
        EnumC2609d enumC2609d3 = EnumC2609d.BYTE;
        List listD2 = AbstractC1999n.D(enumC2609d2, enumC2609d3, EnumC2609d.DOUBLE, EnumC2609d.FLOAT, enumC2609d3, EnumC2609d.INT, EnumC2609d.LONG, EnumC2609d.SHORT);
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        Iterator it = listD2.iterator();
        while (it.hasNext()) {
            String strB2 = ((EnumC2609d) it.next()).e().f().b();
            A7.m.e(strB2, "it.wrapperFqName.shortName().asString()");
            String[] strArrA = C1390g.a("Ljava/lang/String;");
            AbstractC2004s.L(linkedHashSet2, C1390g.f(strB2, (String[]) Arrays.copyOf(strArrA, strArrA.length)));
        }
        String[] strArrA2 = C1390g.a("D");
        LinkedHashSet linkedHashSetQ = AbstractC1985B.q(linkedHashSet2, C1390g.f("Float", (String[]) Arrays.copyOf(strArrA2, strArrA2.length)));
        String[] strArrA3 = C1390g.a("[C", "[CII", "[III", "[BIILjava/lang/String;", "[BIILjava/nio/charset/Charset;", "[BLjava/lang/String;", "[BLjava/nio/charset/Charset;", "[BII", "[B", "Ljava/lang/StringBuffer;", "Ljava/lang/StringBuilder;");
        f5456e = AbstractC1985B.q(linkedHashSetQ, C1390g.f("String", (String[]) Arrays.copyOf(strArrA3, strArrA3.length)));
        String[] strArrA4 = C1390g.a("Ljava/lang/String;Ljava/lang/Throwable;ZZ");
        f5457f = C1390g.f("Throwable", (String[]) Arrays.copyOf(strArrA4, strArrA4.length));
    }
}
