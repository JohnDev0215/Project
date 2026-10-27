package okio;

import java.io.IOException;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Options.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004:\u0001\u0015B\u001f\b\u0002\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u000eH\u0096\u0002R\u001e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lokio/Options;", "Lkotlin/collections/AbstractList;", "Lokio/ByteString;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "byteStrings", "", "trie", "", "([Lokio/ByteString;[I)V", "getByteStrings$okio", "()[Lokio/ByteString;", "[Lokio/ByteString;", "size", "", "getSize", "()I", "getTrie$okio", "()[I", "get", "index", "Companion", "okio"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class Options extends AbstractList<ByteString> implements RandomAccess {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ByteString[] byteStrings;
    private final int[] trie;

    public /* synthetic */ Options(ByteString[] byteStringArr, int[] iArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteStringArr, iArr);
    }

    @JvmStatic
    public static final Options of(ByteString... byteStringArr) {
        return INSTANCE.of(byteStringArr);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ByteString) {
            return contains((ByteString) obj);
        }
        return false;
    }

    public /* bridge */ boolean contains(ByteString byteString) {
        return super.contains(byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ByteString) {
            return indexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int indexOf(ByteString byteString) {
        return super.indexOf(byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ByteString) {
            return lastIndexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(ByteString byteString) {
        return super.lastIndexOf(byteString);
    }

    /* JADX INFO: renamed from: getByteStrings$okio, reason: from getter */
    public final ByteString[] getByteStrings() {
        return this.byteStrings;
    }

    /* JADX INFO: renamed from: getTrie$okio, reason: from getter */
    public final int[] getTrie() {
        return this.trie;
    }

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    /* JADX INFO: renamed from: getSize */
    public int get_size() {
        return this.byteStrings.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public ByteString get(int index) {
        return this.byteStrings[index];
    }

    /* JADX INFO: compiled from: Options.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JT\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002J!\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0016\"\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0017R\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lokio/Options$Companion;", "", "()V", "intCount", "", "Lokio/Buffer;", "getIntCount", "(Lokio/Buffer;)J", "buildTrieRecursive", "", "nodeOffset", "node", "byteStringOffset", "", "byteStrings", "", "Lokio/ByteString;", "fromIndex", "toIndex", "indexes", "of", "Lokio/Options;", "", "([Lokio/ByteString;)Lokio/Options;", "okio"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:59:0x00e1, code lost:
        
            continue;
         */
        @kotlin.jvm.JvmStatic
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final okio.Options of(okio.ByteString... r17) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 322
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.Options.Companion.of(okio.ByteString[]):okio.Options");
        }

        static /* synthetic */ void buildTrieRecursive$default(Companion companion, long j, Buffer buffer, int i, List list, int i2, int i3, List list2, int i4, Object obj) throws IOException {
            companion.buildTrieRecursive((i4 & 1) != 0 ? 0L : j, buffer, (i4 & 4) != 0 ? 0 : i, list, (i4 & 16) != 0 ? 0 : i2, (i4 & 32) != 0 ? list.size() : i3, list2);
        }

        private final void buildTrieRecursive(long nodeOffset, Buffer node, int byteStringOffset, List<? extends ByteString> byteStrings, int fromIndex, int toIndex, List<Integer> indexes) throws IOException {
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            Buffer buffer;
            int i6 = byteStringOffset;
            if (!(fromIndex < toIndex)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (fromIndex < toIndex) {
                int i7 = fromIndex;
                while (true) {
                    int i8 = i7 + 1;
                    if (!(byteStrings.get(i7).size() >= i6)) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    if (i8 >= toIndex) {
                        break;
                    } else {
                        i7 = i8;
                    }
                }
            }
            ByteString byteString = byteStrings.get(fromIndex);
            ByteString byteString2 = byteStrings.get(toIndex - 1);
            int i9 = -1;
            if (i6 == byteString.size()) {
                int iIntValue = indexes.get(fromIndex).intValue();
                int i10 = fromIndex + 1;
                ByteString byteString3 = byteStrings.get(i10);
                i = i10;
                i2 = iIntValue;
                byteString = byteString3;
            } else {
                i = fromIndex;
                i2 = -1;
            }
            if (byteString.getByte(i6) != byteString2.getByte(i6)) {
                int i11 = i + 1;
                int i12 = 1;
                if (i11 < toIndex) {
                    while (true) {
                        int i13 = i11 + 1;
                        if (byteStrings.get(i11 - 1).getByte(i6) != byteStrings.get(i11).getByte(i6)) {
                            i12++;
                        }
                        if (i13 >= toIndex) {
                            break;
                        } else {
                            i11 = i13;
                        }
                    }
                }
                long intCount = nodeOffset + getIntCount(node) + ((long) 2) + ((long) (i12 * 2));
                node.writeInt(i12);
                node.writeInt(i2);
                if (i < toIndex) {
                    int i14 = i;
                    while (true) {
                        int i15 = i14 + 1;
                        byte b = byteStrings.get(i14).getByte(i6);
                        if (i14 == i || b != byteStrings.get(i14 - 1).getByte(i6)) {
                            node.writeInt(b & 255);
                        }
                        if (i15 >= toIndex) {
                            break;
                        } else {
                            i14 = i15;
                        }
                    }
                }
                Buffer buffer2 = new Buffer();
                while (i < toIndex) {
                    byte b2 = byteStrings.get(i).getByte(i6);
                    int i16 = i + 1;
                    if (i16 < toIndex) {
                        int i17 = i16;
                        while (true) {
                            int i18 = i17 + 1;
                            if (b2 != byteStrings.get(i17).getByte(i6)) {
                                i4 = i17;
                                break;
                            } else if (i18 >= toIndex) {
                                break;
                            } else {
                                i17 = i18;
                            }
                        }
                        i4 = toIndex;
                    } else {
                        i4 = toIndex;
                    }
                    if (i16 == i4 && i6 + 1 == byteStrings.get(i).size()) {
                        node.writeInt(indexes.get(i).intValue());
                        i5 = i4;
                        buffer = buffer2;
                    } else {
                        node.writeInt(((int) (intCount + getIntCount(buffer2))) * i9);
                        i5 = i4;
                        buffer = buffer2;
                        buildTrieRecursive(intCount, buffer2, i6 + 1, byteStrings, i, i4, indexes);
                    }
                    buffer2 = buffer;
                    i = i5;
                    i9 = -1;
                }
                node.writeAll(buffer2);
                return;
            }
            int iMin = Math.min(byteString.size(), byteString2.size());
            if (i6 < iMin) {
                int i19 = i6;
                i3 = 0;
                while (true) {
                    int i20 = i19 + 1;
                    if (byteString.getByte(i19) != byteString2.getByte(i19)) {
                        break;
                    }
                    i3++;
                    if (i20 >= iMin) {
                        break;
                    } else {
                        i19 = i20;
                    }
                }
            } else {
                i3 = 0;
            }
            long intCount2 = nodeOffset + getIntCount(node) + ((long) 2) + ((long) i3) + 1;
            node.writeInt(-i3);
            node.writeInt(i2);
            int i21 = i6 + i3;
            if (i6 < i21) {
                while (true) {
                    int i22 = i6 + 1;
                    node.writeInt(byteString.getByte(i6) & 255);
                    if (i22 >= i21) {
                        break;
                    } else {
                        i6 = i22;
                    }
                }
            }
            if (i + 1 == toIndex) {
                if (!(i21 == byteStrings.get(i).size())) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                node.writeInt(indexes.get(i).intValue());
            } else {
                Buffer buffer3 = new Buffer();
                node.writeInt(((int) (getIntCount(buffer3) + intCount2)) * (-1));
                buildTrieRecursive(intCount2, buffer3, i21, byteStrings, i, toIndex, indexes);
                node.writeAll(buffer3);
            }
        }

        private final long getIntCount(Buffer buffer) {
            return buffer.getSize() / ((long) 4);
        }
    }
}
