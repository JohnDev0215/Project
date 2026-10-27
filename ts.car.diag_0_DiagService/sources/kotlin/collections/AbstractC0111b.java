package kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
/* JADX INFO: renamed from: kotlin.collections.b, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\t\b\u0016¢\u0006\u0004\b\u0013\u0010\u000fJ\u000f\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0006\u0010\u0005J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0007H\u0004¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0004¢\u0006\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\u00020\u00078\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0004\u0010\u0010R\u0016\u0010\f\u001a\u00020\u00118\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\u0012¨\u0006\u0014"}, d2 = {"Lkotlin/collections/b;", "T", "Ljava/util/Iterator;", "Lkotlin/Boolean;", "d", "()Z", "hasNext", "Lkotlin/Any;", "next", "()Ljava/lang/Object;", "value", "Lkotlin/Unit;", "c", "(Ljava/lang/Object;)V", "b", "()V", "Ljava/lang/Object;", "Lkotlin/collections/State;", "Lkotlin/collections/State;", "<init>", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public abstract class AbstractC0111b<T> implements Iterator<T> {
    private State c = State.NotReady;
    private T d;

    private final boolean d() {
        this.c = State.Failed;
        a();
        return this.c == State.Ready;
    }

    protected abstract void a();

    protected final void b() {
        this.c = State.Done;
    }

    protected final void c(T value) {
        this.d = value;
        this.c = State.Ready;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (!(this.c != State.Failed)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int i = C0110a.f1484a[this.c.ordinal()];
        if (i == 1) {
            return false;
        }
        if (i != 2) {
            return d();
        }
        return true;
    }

    @Override // java.util.Iterator
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.c = State.NotReady;
        return this.d;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
