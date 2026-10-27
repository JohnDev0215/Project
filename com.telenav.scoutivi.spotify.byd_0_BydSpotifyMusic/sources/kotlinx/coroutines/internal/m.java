package kotlinx.coroutines.internal;

/* JADX INFO: compiled from: LockFreeLinkedList.kt */
/* JADX INFO: loaded from: classes.dex */
public class m extends o {
    @Override // kotlinx.coroutines.internal.o
    public boolean L() {
        return false;
    }

    @Override // kotlinx.coroutines.internal.o
    public final boolean M() {
        throw new IllegalStateException("head cannot be removed".toString());
    }
}
