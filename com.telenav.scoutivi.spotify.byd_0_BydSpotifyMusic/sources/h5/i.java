package h5;

/* JADX INFO: compiled from: ArraysJVM.kt */
/* JADX INFO: loaded from: classes.dex */
class i {
    public static final void a(int i7, int i8) {
        if (i7 <= i8) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i7 + ") is greater than size (" + i8 + ").");
    }
}
