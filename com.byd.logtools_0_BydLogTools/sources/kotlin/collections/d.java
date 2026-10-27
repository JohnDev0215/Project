package kotlin.collections;

/* JADX INFO: compiled from: ArraysJVM.kt */
/* JADX INFO: loaded from: classes.dex */
class d {
    public static final void a(int i, int i2) {
        if (i <= i2) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i + ") is greater than size (" + i2 + ").");
    }
}
