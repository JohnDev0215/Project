package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.z;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\b¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0000@\u0000X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Lkotlin/jvm/internal/e;", "Lkotlin/collections/z;", "Lkotlin/Boolean;", "hasNext", "()Z", "Lkotlin/Float;", "b", "()F", "Lkotlin/FloatArray;", "d", "[F", "Lkotlin/Int;", "c", "I", "p0", "<init>", "([F)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
final class e extends z {
    private int c;
    private final float[] d;

    public e(@NotNull float[] fArr) {
        q.c(fArr, "array");
        this.d = fArr;
    }

    @Override // kotlin.collections.z
    public float b() {
        try {
            float[] fArr = this.d;
            int i = this.c;
            this.c = i + 1;
            return fArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.c--;
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.c < this.d.length;
    }
}
