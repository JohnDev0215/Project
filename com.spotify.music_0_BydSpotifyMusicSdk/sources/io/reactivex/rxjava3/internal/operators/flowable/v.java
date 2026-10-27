package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import java.util.Iterator;
import p.he1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class v extends Flowable {
    public final Iterable e;

    public v(Iterable iterable) {
        this.e = iterable;
    }

    public static <T> void subscribe(final rg2 rg2Var, final Iterator<? extends T> it) {
        EmptySubscription emptySubscription = EmptySubscription.c;
        try {
            if (!it.hasNext()) {
                rg2Var.onSubscribe(emptySubscription);
                rg2Var.onComplete();
            } else if (!(rg2Var instanceof io.reactivex.rxjava3.operators.a)) {
                rg2Var.onSubscribe(new FlowableFromIterable$BaseRangeSubscription<T>(rg2Var, it) { // from class: io.reactivex.rxjava3.internal.operators.flowable.FlowableFromIterable$IteratorSubscription
                    private static final long serialVersionUID = -6022804456014692607L;
                    final rg2 downstream;

                    {
                        super(it);
                        this.downstream = rg2Var;
                    }

                    @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableFromIterable$BaseRangeSubscription
                    public final void c() {
                        Iterator<? extends T> it2 = this.iterator;
                        rg2 rg2Var2 = this.downstream;
                        while (!this.cancelled) {
                            try {
                                T next = it2.next();
                                if (this.cancelled) {
                                    return;
                                }
                                if (next == null) {
                                    rg2Var2.onError(new NullPointerException("Iterator.next() returned a null value"));
                                    return;
                                }
                                rg2Var2.onNext(next);
                                if (this.cancelled) {
                                    return;
                                }
                                try {
                                    if (!it2.hasNext()) {
                                        if (this.cancelled) {
                                            return;
                                        }
                                        rg2Var2.onComplete();
                                        return;
                                    }
                                } catch (Throwable th) {
                                    he1.R(th);
                                    rg2Var2.onError(th);
                                    return;
                                }
                            } catch (Throwable th2) {
                                he1.R(th2);
                                rg2Var2.onError(th2);
                                return;
                            }
                        }
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:34:0x0058, code lost:
                    
                        r9 = addAndGet(-r4);
                     */
                    @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableFromIterable$BaseRangeSubscription
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final void d(long r9) {
                        /*
                            r8 = this;
                            java.util.Iterator<? extends T> r0 = r8.iterator
                            p.rg2 r1 = r8.downstream
                            r2 = 0
                        L6:
                            r4 = r2
                        L7:
                            int r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
                            if (r6 == 0) goto L50
                            boolean r6 = r8.cancelled
                            if (r6 == 0) goto L10
                            return
                        L10:
                            java.lang.Object r6 = r0.next()     // Catch: java.lang.Throwable -> L48
                            boolean r7 = r8.cancelled
                            if (r7 == 0) goto L19
                            return
                        L19:
                            if (r6 != 0) goto L26
                            java.lang.NullPointerException r9 = new java.lang.NullPointerException
                            java.lang.String r10 = "Iterator.next() returned a null value"
                            r9.<init>(r10)
                            r1.onError(r9)
                            return
                        L26:
                            r1.onNext(r6)
                            boolean r6 = r8.cancelled
                            if (r6 == 0) goto L2e
                            return
                        L2e:
                            boolean r6 = r0.hasNext()     // Catch: java.lang.Throwable -> L40
                            if (r6 != 0) goto L3c
                            boolean r9 = r8.cancelled
                            if (r9 != 0) goto L3b
                            r1.onComplete()
                        L3b:
                            return
                        L3c:
                            r6 = 1
                            long r4 = r4 + r6
                            goto L7
                        L40:
                            r9 = move-exception
                            p.he1.R(r9)
                            r1.onError(r9)
                            return
                        L48:
                            r9 = move-exception
                            p.he1.R(r9)
                            r1.onError(r9)
                            return
                        L50:
                            long r9 = r8.get()
                            int r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
                            if (r6 != 0) goto L7
                            long r9 = -r4
                            long r9 = r8.addAndGet(r9)
                            int r4 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
                            if (r4 != 0) goto L6
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.flowable.FlowableFromIterable$IteratorSubscription.d(long):void");
                    }
                });
            } else {
                final io.reactivex.rxjava3.operators.a aVar = (io.reactivex.rxjava3.operators.a) rg2Var;
                rg2Var.onSubscribe(new FlowableFromIterable$BaseRangeSubscription<T>(aVar, it) { // from class: io.reactivex.rxjava3.internal.operators.flowable.FlowableFromIterable$IteratorConditionalSubscription
                    private static final long serialVersionUID = -6022804456014692607L;
                    final io.reactivex.rxjava3.operators.a downstream;

                    {
                        super(it);
                        this.downstream = aVar;
                    }

                    @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableFromIterable$BaseRangeSubscription
                    public final void c() {
                        Iterator<? extends T> it2 = this.iterator;
                        io.reactivex.rxjava3.operators.a aVar2 = this.downstream;
                        while (!this.cancelled) {
                            try {
                                T next = it2.next();
                                if (this.cancelled) {
                                    return;
                                }
                                if (next == null) {
                                    aVar2.onError(new NullPointerException("Iterator.next() returned a null value"));
                                    return;
                                }
                                aVar2.d(next);
                                if (this.cancelled) {
                                    return;
                                }
                                try {
                                    if (!it2.hasNext()) {
                                        if (this.cancelled) {
                                            return;
                                        }
                                        aVar2.onComplete();
                                        return;
                                    }
                                } catch (Throwable th) {
                                    he1.R(th);
                                    aVar2.onError(th);
                                    return;
                                }
                            } catch (Throwable th2) {
                                he1.R(th2);
                                aVar2.onError(th2);
                                return;
                            }
                        }
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:35:0x005b, code lost:
                    
                        r9 = addAndGet(-r4);
                     */
                    @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableFromIterable$BaseRangeSubscription
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final void d(long r9) {
                        /*
                            r8 = this;
                            java.util.Iterator<? extends T> r0 = r8.iterator
                            io.reactivex.rxjava3.operators.a r1 = r8.downstream
                            r2 = 0
                        L6:
                            r4 = r2
                        L7:
                            int r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
                            if (r6 == 0) goto L53
                            boolean r6 = r8.cancelled
                            if (r6 == 0) goto L10
                            return
                        L10:
                            java.lang.Object r6 = r0.next()     // Catch: java.lang.Throwable -> L4b
                            boolean r7 = r8.cancelled
                            if (r7 == 0) goto L19
                            return
                        L19:
                            if (r6 != 0) goto L26
                            java.lang.NullPointerException r9 = new java.lang.NullPointerException
                            java.lang.String r10 = "Iterator.next() returned a null value"
                            r9.<init>(r10)
                            r1.onError(r9)
                            return
                        L26:
                            boolean r6 = r1.d(r6)
                            boolean r7 = r8.cancelled
                            if (r7 == 0) goto L2f
                            return
                        L2f:
                            boolean r7 = r0.hasNext()     // Catch: java.lang.Throwable -> L43
                            if (r7 != 0) goto L3d
                            boolean r9 = r8.cancelled
                            if (r9 != 0) goto L3c
                            r1.onComplete()
                        L3c:
                            return
                        L3d:
                            if (r6 == 0) goto L7
                            r6 = 1
                            long r4 = r4 + r6
                            goto L7
                        L43:
                            r9 = move-exception
                            p.he1.R(r9)
                            r1.onError(r9)
                            return
                        L4b:
                            r9 = move-exception
                            p.he1.R(r9)
                            r1.onError(r9)
                            return
                        L53:
                            long r9 = r8.get()
                            int r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
                            if (r6 != 0) goto L7
                            long r9 = -r4
                            long r9 = r8.addAndGet(r9)
                            int r4 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
                            if (r4 != 0) goto L6
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.flowable.FlowableFromIterable$IteratorConditionalSubscription.d(long):void");
                    }
                });
            }
        } catch (Throwable th) {
            he1.R(th);
            rg2Var.onSubscribe(emptySubscription);
            rg2Var.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        try {
            subscribe(rg2Var, this.e.iterator());
        } catch (Throwable th) {
            he1.R(th);
            rg2Var.onSubscribe(EmptySubscription.c);
            rg2Var.onError(th);
        }
    }
}
