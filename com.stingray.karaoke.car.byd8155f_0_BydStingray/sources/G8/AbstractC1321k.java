package g8;

import io.sentry.C1489n1;
import java.util.LinkedHashMap;

/* JADX INFO: renamed from: g8.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1321k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C1315e f15013a = new C1315e(EnumC1318h.f15007m, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C1315e f15014b;
    public static final C1315e c;
    public static final LinkedHashMap d;

    static {
        EnumC1318h enumC1318h = EnumC1318h.f15008n;
        f15014b = new C1315e(enumC1318h, false);
        c = new C1315e(enumC1318h, true);
        String strConcat = "java/lang/".concat("Object");
        String strConcat2 = "java/util/function/".concat("Predicate");
        String strConcat3 = "java/util/function/".concat("Function");
        String strConcat4 = "java/util/function/".concat("Consumer");
        String strConcat5 = "java/util/function/".concat("BiFunction");
        String strConcat6 = "java/util/function/".concat("BiConsumer");
        String strConcat7 = "java/util/function/".concat("UnaryOperator");
        String strConcat8 = "java/util/".concat("stream/Stream");
        String strConcat9 = "java/util/".concat("Optional");
        K3.n nVar = new K3.n(1);
        new C1489n1(nVar, "java/util/".concat("Iterator")).o("forEachRemaining", new L0.l(strConcat4, 3));
        new C1489n1(nVar, "java/lang/".concat("Iterable")).o("spliterator", new C1323m(1, 4));
        C1489n1 c1489n1 = new C1489n1(nVar, "java/util/".concat("Collection"));
        c1489n1.o("removeIf", new L0.l(strConcat2, 9));
        c1489n1.o("stream", new L0.l(strConcat8, 10));
        c1489n1.o("parallelStream", new L0.l(strConcat8, 11));
        new C1489n1(nVar, "java/util/".concat("List")).o("replaceAll", new L0.l(strConcat7, 12));
        C1489n1 c1489n12 = new C1489n1(nVar, "java/util/".concat("Map"));
        c1489n12.o("forEach", new L0.l(strConcat6, 13));
        c1489n12.o("putIfAbsent", new L0.l(strConcat, 14));
        c1489n12.o("replace", new L0.l(strConcat, 15));
        c1489n12.o("replace", new L0.l(strConcat, 16));
        c1489n12.o("replaceAll", new L0.l(strConcat5, 17));
        c1489n12.o("compute", new C1320j(strConcat, 0, strConcat5));
        c1489n12.o("computeIfAbsent", new C1320j(strConcat, 1, strConcat3));
        c1489n12.o("computeIfPresent", new C1320j(strConcat, 2, strConcat5));
        c1489n12.o("merge", new C1320j(strConcat, 3, strConcat5));
        C1489n1 c1489n13 = new C1489n1(nVar, strConcat9);
        c1489n13.o("empty", new L0.l(strConcat9, 18));
        c1489n13.o("of", new C1320j(strConcat, 4, strConcat9));
        c1489n13.o("ofNullable", new C1320j(strConcat, 5, strConcat9));
        c1489n13.o("get", new L0.l(strConcat, 19));
        c1489n13.o("ifPresent", new L0.l(strConcat4, 20));
        new C1489n1(nVar, "java/lang/".concat("ref/Reference")).o("get", new L0.l(strConcat, 21));
        new C1489n1(nVar, strConcat2).o("test", new L0.l(strConcat, 22));
        new C1489n1(nVar, "java/util/function/".concat("BiPredicate")).o("test", new L0.l(strConcat, 23));
        new C1489n1(nVar, strConcat4).o("accept", new L0.l(strConcat, 4));
        new C1489n1(nVar, strConcat6).o("accept", new L0.l(strConcat, 5));
        new C1489n1(nVar, strConcat3).o("apply", new L0.l(strConcat, 6));
        new C1489n1(nVar, strConcat5).o("apply", new L0.l(strConcat, 7));
        new C1489n1(nVar, "java/util/function/".concat("Supplier")).o("get", new L0.l(strConcat, 8));
        d = nVar.f3576a;
    }
}
