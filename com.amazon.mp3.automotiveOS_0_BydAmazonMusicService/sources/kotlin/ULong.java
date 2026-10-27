package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ULongRange;

/* JADX INFO: compiled from: ULong.kt */
/* JADX INFO: loaded from: classes7.dex */
@SinceKotlin(version = "1.5")
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 |2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001|B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u000bJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u001a\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010%HÖ\u0003¢\u0006\u0004\b&\u0010'J\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u001dJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u001fJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b+\u0010\u000bJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b,\u0010\"J\u0010\u0010-\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b.\u0010/J\u0016\u00100\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b1\u0010\u0005J\u0016\u00102\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b3\u0010\u0005J\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u001dJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u001fJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b7\u0010\u000bJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b8\u0010\"J\u001b\u00109\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b:\u0010;J\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b<\u0010\u0013J\u001b\u00109\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b=\u0010\u000bJ\u001b\u00109\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b>\u0010?J\u001b\u0010@\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bA\u0010\u000bJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u001dJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010\u001fJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bE\u0010\u000bJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010\"J\u001b\u0010G\u001a\u00020H2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010JJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bL\u0010\u001dJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bM\u0010\u001fJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bN\u0010\u000bJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bO\u0010\"J\u001e\u0010P\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\rH\u0087\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bR\u0010\u001fJ\u001e\u0010S\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\rH\u0087\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bT\u0010\u001fJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bV\u0010\u001dJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bW\u0010\u001fJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bX\u0010\u000bJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bY\u0010\"J\u0010\u0010Z\u001a\u00020[H\u0087\b¢\u0006\u0004\b\\\u0010]J\u0010\u0010^\u001a\u00020_H\u0087\b¢\u0006\u0004\b`\u0010aJ\u0010\u0010b\u001a\u00020cH\u0087\b¢\u0006\u0004\bd\u0010eJ\u0010\u0010f\u001a\u00020\rH\u0087\b¢\u0006\u0004\bg\u0010/J\u0010\u0010h\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bi\u0010\u0005J\u0010\u0010j\u001a\u00020kH\u0087\b¢\u0006\u0004\bl\u0010mJ\u000f\u0010n\u001a\u00020oH\u0016¢\u0006\u0004\bp\u0010qJ\u0016\u0010r\u001a\u00020\u000eH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bs\u0010]J\u0016\u0010t\u001a\u00020\u0011H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bu\u0010/J\u0016\u0010v\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bw\u0010\u0005J\u0016\u0010x\u001a\u00020\u0016H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\by\u0010mJ\u001b\u0010z\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b{\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006}"}, d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "getData$annotations", "()V", "and", "other", "and-VKZWuLQ", "(JJ)J", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "dec", "dec-s-VKNKU", "div", "div-7apg3OU", "(JB)J", "div-WZ4Q5Ns", "(JI)J", "div-VKZWuLQ", "div-xj2QHRw", "(JS)J", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(J)I", "inc", "inc-s-VKNKU", "inv", "inv-s-VKNKU", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(JB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(JS)S", "or", "or-VKZWuLQ", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-s-VKNKU", "shr", "shr-s-VKNKU", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(J)B", "toDouble", "", "toDouble-impl", "(J)D", "toFloat", "", "toFloat-impl", "(J)F", "toInt", "toInt-impl", "toLong", "toLong-impl", "toShort", "", "toShort-impl", "(J)S", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-VKZWuLQ", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 6, 0}, xi = 48)
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
public final class ULong implements Comparable<ULong> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ULong m2976boximpl(long j) {
        return new ULong(j);
    }

    @PublishedApi
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m2982constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2988equalsimpl(long j, Object obj) {
        return (obj instanceof ULong) && j == ((ULong) obj).getData();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2989equalsimpl0(long j, long j2) {
        return j == j2;
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2994hashCodeimpl(long j) {
        return (int) (j ^ (j >>> 32));
    }

    @InlineOnly
    /* JADX INFO: renamed from: toByte-impl, reason: not valid java name */
    private static final byte m3021toByteimpl(long j) {
        return (byte) j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toInt-impl, reason: not valid java name */
    private static final int m3024toIntimpl(long j) {
        return (int) j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toLong-impl, reason: not valid java name */
    private static final long m3025toLongimpl(long j) {
        return j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toShort-impl, reason: not valid java name */
    private static final short m3026toShortimpl(long j) {
        return (short) j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m3030toULongsVKNKU(long j) {
        return j;
    }

    public boolean equals(Object obj) {
        return m2988equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m2994hashCodeimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getData() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ULong uLong) {
        return UnsignedKt.ulongCompare(getData(), uLong.getData());
    }

    @PublishedApi
    private /* synthetic */ ULong(long j) {
        this.data = j;
    }

    /* JADX INFO: compiled from: ULong.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"}, d2 = {"Lkotlin/ULong$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/ULong;", "J", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static final int m2977compareTo7apg3OU(long j, byte b) {
        return UnsignedKt.ulongCompare(j, m2982constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static final int m2981compareToxj2QHRw(long j, short s) {
        return UnsignedKt.ulongCompare(j, m2982constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static final int m2980compareToWZ4Q5Ns(long j, int i) {
        return UnsignedKt.ulongCompare(j, m2982constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private int m2978compareToVKZWuLQ(long j) {
        return UnsignedKt.ulongCompare(getData(), j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static int m2979compareToVKZWuLQ(long j, long j2) {
        return UnsignedKt.ulongCompare(j, j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-7apg3OU, reason: not valid java name */
    private static final long m3006plus7apg3OU(long j, byte b) {
        return m2982constructorimpl(j + m2982constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final long m3009plusxj2QHRw(long j, short s) {
        return m2982constructorimpl(j + m2982constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final long m3008plusWZ4Q5Ns(long j, int i) {
        return m2982constructorimpl(j + m2982constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m3007plusVKZWuLQ(long j, long j2) {
        return m2982constructorimpl(j + j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-7apg3OU, reason: not valid java name */
    private static final long m2997minus7apg3OU(long j, byte b) {
        return m2982constructorimpl(j - m2982constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final long m3000minusxj2QHRw(long j, short s) {
        return m2982constructorimpl(j - m2982constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final long m2999minusWZ4Q5Ns(long j, int i) {
        return m2982constructorimpl(j - m2982constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m2998minusVKZWuLQ(long j, long j2) {
        return m2982constructorimpl(j - j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-7apg3OU, reason: not valid java name */
    private static final long m3017times7apg3OU(long j, byte b) {
        return m2982constructorimpl(j * m2982constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-xj2QHRw, reason: not valid java name */
    private static final long m3020timesxj2QHRw(long j, short s) {
        return m2982constructorimpl(j * m2982constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final long m3019timesWZ4Q5Ns(long j, int i) {
        return m2982constructorimpl(j * m2982constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m3018timesVKZWuLQ(long j, long j2) {
        return m2982constructorimpl(j * j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-7apg3OU, reason: not valid java name */
    private static final long m2984div7apg3OU(long j, byte b) {
        return UnsignedKt.m3159ulongDivideeb3DHEI(j, m2982constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-xj2QHRw, reason: not valid java name */
    private static final long m2987divxj2QHRw(long j, short s) {
        return UnsignedKt.m3159ulongDivideeb3DHEI(j, m2982constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final long m2986divWZ4Q5Ns(long j, int i) {
        return UnsignedKt.m3159ulongDivideeb3DHEI(j, m2982constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m2985divVKZWuLQ(long j, long j2) {
        return UnsignedKt.m3159ulongDivideeb3DHEI(j, j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-7apg3OU, reason: not valid java name */
    private static final long m3011rem7apg3OU(long j, byte b) {
        return UnsignedKt.m3160ulongRemaindereb3DHEI(j, m2982constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final long m3014remxj2QHRw(long j, short s) {
        return UnsignedKt.m3160ulongRemaindereb3DHEI(j, m2982constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final long m3013remWZ4Q5Ns(long j, int i) {
        return UnsignedKt.m3160ulongRemaindereb3DHEI(j, m2982constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m3012remVKZWuLQ(long j, long j2) {
        return UnsignedKt.m3160ulongRemaindereb3DHEI(j, j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final long m2990floorDiv7apg3OU(long j, byte b) {
        return UnsignedKt.m3159ulongDivideeb3DHEI(j, m2982constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final long m2993floorDivxj2QHRw(long j, short s) {
        return UnsignedKt.m3159ulongDivideeb3DHEI(j, m2982constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final long m2992floorDivWZ4Q5Ns(long j, int i) {
        return UnsignedKt.m3159ulongDivideeb3DHEI(j, m2982constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m2991floorDivVKZWuLQ(long j, long j2) {
        return UnsignedKt.m3159ulongDivideeb3DHEI(j, j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m3001mod7apg3OU(long j, byte b) {
        return UByte.m2828constructorimpl((byte) UnsignedKt.m3160ulongRemaindereb3DHEI(j, m2982constructorimpl(((long) b) & 255)));
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m3004modxj2QHRw(long j, short s) {
        return UShort.m3088constructorimpl((short) UnsignedKt.m3160ulongRemaindereb3DHEI(j, m2982constructorimpl(((long) s) & 65535)));
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m3003modWZ4Q5Ns(long j, int i) {
        return UInt.m2904constructorimpl((int) UnsignedKt.m3160ulongRemaindereb3DHEI(j, m2982constructorimpl(((long) i) & 4294967295L)));
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m3002modVKZWuLQ(long j, long j2) {
        return UnsignedKt.m3160ulongRemaindereb3DHEI(j, j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: inc-s-VKNKU, reason: not valid java name */
    private static final long m2995incsVKNKU(long j) {
        return m2982constructorimpl(j + 1);
    }

    @InlineOnly
    /* JADX INFO: renamed from: dec-s-VKNKU, reason: not valid java name */
    private static final long m2983decsVKNKU(long j) {
        return m2982constructorimpl(j - 1);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rangeTo-VKZWuLQ, reason: not valid java name */
    private static final ULongRange m3010rangeToVKZWuLQ(long j, long j2) {
        return new ULongRange(j, j2, null);
    }

    @InlineOnly
    /* JADX INFO: renamed from: shl-s-VKNKU, reason: not valid java name */
    private static final long m3015shlsVKNKU(long j, int i) {
        return m2982constructorimpl(j << i);
    }

    @InlineOnly
    /* JADX INFO: renamed from: shr-s-VKNKU, reason: not valid java name */
    private static final long m3016shrsVKNKU(long j, int i) {
        return m2982constructorimpl(j >>> i);
    }

    @InlineOnly
    /* JADX INFO: renamed from: and-VKZWuLQ, reason: not valid java name */
    private static final long m2975andVKZWuLQ(long j, long j2) {
        return m2982constructorimpl(j & j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: or-VKZWuLQ, reason: not valid java name */
    private static final long m3005orVKZWuLQ(long j, long j2) {
        return m2982constructorimpl(j | j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: xor-VKZWuLQ, reason: not valid java name */
    private static final long m3032xorVKZWuLQ(long j, long j2) {
        return m2982constructorimpl(j ^ j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: inv-s-VKNKU, reason: not valid java name */
    private static final long m2996invsVKNKU(long j) {
        return m2982constructorimpl(~j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m3028toUBytew2LRezQ(long j) {
        return UByte.m2828constructorimpl((byte) j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m3031toUShortMh2AYeg(long j) {
        return UShort.m3088constructorimpl((short) j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m3029toUIntpVg5ArA(long j) {
        return UInt.m2904constructorimpl((int) j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toFloat-impl, reason: not valid java name */
    private static final float m3023toFloatimpl(long j) {
        return (float) UnsignedKt.ulongToDouble(j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toDouble-impl, reason: not valid java name */
    private static final double m3022toDoubleimpl(long j) {
        return UnsignedKt.ulongToDouble(j);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m3027toStringimpl(long j) {
        return UnsignedKt.ulongToString(j);
    }

    public String toString() {
        return m3027toStringimpl(this.data);
    }
}
