package spi;

import java.util.List;
import java.util.RandomAccess;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class kk extends AbstractList<ByteString> implements RandomAccess {
    public static final a c = new a(null);

    @NotNull
    public final ByteString[] a;

    @NotNull
    public final int[] b;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(long j, g2 g2Var, int i, List<? extends ByteString> list, int i2, int i3, List<Integer> list2) {
            int i4;
            int i5;
            int i6;
            int i7;
            long j2;
            g2 g2Var2;
            long j3;
            int i8 = i;
            if (!(i2 < i3)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            for (int i9 = i2; i9 < i3; i9++) {
                if (!(list.get(i9).size() >= i8)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
            }
            ByteString byteString = list.get(i2);
            ByteString byteString2 = list.get(i3 - 1);
            if (i8 == byteString.size()) {
                int iIntValue = list2.get(i2).intValue();
                int i10 = i2 + 1;
                ByteString byteString3 = list.get(i10);
                i4 = i10;
                i5 = iIntValue;
                byteString = byteString3;
            } else {
                i4 = i2;
                i5 = -1;
            }
            if (byteString.getByte(i8) == byteString2.getByte(i8)) {
                int iMin = Math.min(byteString.size(), byteString2.size());
                int i11 = 0;
                for (int i12 = i8; i12 < iMin && byteString.getByte(i12) == byteString2.getByte(i12); i12++) {
                    i11++;
                }
                long j4 = 4;
                long j5 = (g2Var.b / j4) + j + ((long) 2) + ((long) i11) + 1;
                g2Var.V(-i11);
                g2Var.V(i5);
                int i13 = i8 + i11;
                while (i8 < i13) {
                    g2Var.V(byteString.getByte(i8) & 255);
                    i8++;
                }
                if (i4 + 1 == i3) {
                    if (!(i13 == list.get(i4).size())) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    g2Var.V(list2.get(i4).intValue());
                    return;
                } else {
                    g2 g2Var3 = new g2();
                    g2Var.V(((int) ((g2Var3.b / j4) + j5)) * (-1));
                    a(j5, g2Var3, i13, list, i4, i3, list2);
                    g2Var.x(g2Var3);
                    return;
                }
            }
            int i14 = 1;
            for (int i15 = i4 + 1; i15 < i3; i15++) {
                if (list.get(i15 - 1).getByte(i8) != list.get(i15).getByte(i8)) {
                    i14++;
                }
            }
            long j6 = 4;
            long j7 = ((long) (i14 * 2)) + (g2Var.b / j6) + j + ((long) 2);
            g2Var.V(i14);
            g2Var.V(i5);
            for (int i16 = i4; i16 < i3; i16++) {
                byte b = list.get(i16).getByte(i8);
                if (i16 == i4 || b != list.get(i16 - 1).getByte(i8)) {
                    g2Var.V(b & 255);
                }
            }
            g2 g2Var4 = new g2();
            while (i4 < i3) {
                byte b2 = list.get(i4).getByte(i8);
                int i17 = i4 + 1;
                int i18 = i17;
                while (true) {
                    if (i18 >= i3) {
                        i6 = i3;
                        break;
                    } else {
                        if (b2 != list.get(i18).getByte(i8)) {
                            i6 = i18;
                            break;
                        }
                        i18++;
                    }
                }
                if (i17 == i6 && i8 + 1 == list.get(i4).size()) {
                    g2Var.V(list2.get(i4).intValue());
                    i7 = i6;
                    j3 = j6;
                    j2 = j7;
                    g2Var2 = g2Var4;
                } else {
                    g2Var.V(((int) ((g2Var4.b / j6) + j7)) * (-1));
                    i7 = i6;
                    j2 = j7;
                    g2Var2 = g2Var4;
                    j3 = j6;
                    a(j7, g2Var4, i8 + 1, list, i4, i7, list2);
                }
                g2Var4 = g2Var2;
                i4 = i7;
                j6 = j3;
                j7 = j2;
            }
            g2Var.x(g2Var4);
        }
    }

    private kk(ByteString[] byteStringArr, int[] iArr) {
        this.a = byteStringArr;
        this.b = iArr;
    }

    public /* synthetic */ kk(ByteString[] byteStringArr, int[] iArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteStringArr, iArr);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ByteString) {
            return super.contains((ByteString) obj);
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final Object get(int i) {
        return this.a[i];
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    /* JADX INFO: renamed from: getSize */
    public final int get_size() {
        return this.a.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ByteString) {
            return super.indexOf((ByteString) obj);
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ByteString) {
            return super.lastIndexOf((ByteString) obj);
        }
        return -1;
    }
}
