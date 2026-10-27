package spi;

import android.annotation.TargetApi;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntPredicate;

/* JADX INFO: compiled from: DrawableStateUtil.java */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public final class m3 implements n3 {
    public final View a;

    @Nullable
    public int[] b;

    @Nullable
    public int[] c;

    public m3(View view) {
        this.a = view;
    }

    public final int[] a(int i, Function<Integer, int[]> function) {
        int[] iArrApply;
        int[] iArr = this.b;
        if (iArr == null) {
            iArrApply = function.apply(Integer.valueOf(i));
        } else {
            iArrApply = function.apply(Integer.valueOf(i + iArr.length));
            int[] iArr2 = this.b;
            int length = iArrApply.length;
            do {
                length--;
                if (length < 0) {
                    break;
                }
            } while (iArrApply[length] == 0);
            System.arraycopy(iArr2, 0, iArrApply, length + 1, iArr2.length);
        }
        int[] iArr3 = this.c;
        return (iArr3 == null || iArr3.length == 0) ? iArrApply : Arrays.stream(iArrApply).filter(new IntPredicate() { // from class: spi.l3
            @Override // java.util.function.IntPredicate
            public final boolean test(final int i2) {
                return Arrays.stream(this.a.c).noneMatch(new IntPredicate() { // from class: spi.k3
                    @Override // java.util.function.IntPredicate
                    public final boolean test(int i3) {
                        return i2 == i3;
                    }
                });
            }
        }).toArray();
    }

    @Override // spi.n3
    public final void d(@Nullable int[] iArr, @Nullable int[] iArr2) {
        this.b = iArr;
        this.c = iArr2;
        this.a.refreshDrawableState();
    }
}
