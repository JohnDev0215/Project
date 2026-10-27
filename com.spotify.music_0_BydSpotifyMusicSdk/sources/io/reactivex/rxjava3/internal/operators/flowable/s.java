package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import p.az1;
import p.he1;
import p.rg2;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public final class s extends Flowable {
    public final /* synthetic */ int e;
    public final Object k;

    public /* synthetic */ s(int i, Object obj) {
        this.e = i;
        this.k = obj;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(final rg2 rg2Var) {
        Object obj;
        int i = this.e;
        Object obj2 = this.k;
        switch (i) {
            case 0:
                if (!(rg2Var instanceof io.reactivex.rxjava3.operators.a)) {
                    final Object[] objArr = (Object[]) obj2;
                    rg2Var.onSubscribe(new FlowableFromArray$BaseArraySubscription<T>(rg2Var, objArr) { // from class: io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray$ArraySubscription
                        private static final long serialVersionUID = 2587302975077663557L;
                        final rg2 downstream;

                        {
                            super(objArr);
                            this.downstream = rg2Var;
                        }

                        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray$BaseArraySubscription
                        public final void c() {
                            T[] tArr = this.array;
                            int length = tArr.length;
                            rg2 rg2Var2 = this.downstream;
                            for (int i2 = this.index; i2 != length; i2++) {
                                if (this.cancelled) {
                                    return;
                                }
                                T t = tArr[i2];
                                if (t == null) {
                                    rg2Var2.onError(new NullPointerException(u4.f("The element at index ", i2, " is null")));
                                    return;
                                }
                                rg2Var2.onNext(t);
                            }
                            if (this.cancelled) {
                                return;
                            }
                            rg2Var2.onComplete();
                        }

                        /* JADX WARN: Code restructure failed: missing block: B:22:0x0045, code lost:
                        
                            r10.index = r2;
                            r11 = addAndGet(-r6);
                         */
                        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray$BaseArraySubscription
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public final void d(long r11) {
                            /*
                                r10 = this;
                                T[] r0 = r10.array
                                int r1 = r0.length
                                int r2 = r10.index
                                p.rg2 r3 = r10.downstream
                                r4 = 0
                            L9:
                                r6 = r4
                            La:
                                int r8 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
                                if (r8 == 0) goto L33
                                if (r2 == r1) goto L33
                                boolean r8 = r10.cancelled
                                if (r8 == 0) goto L15
                                return
                            L15:
                                r8 = r0[r2]
                                if (r8 != 0) goto L2a
                                java.lang.NullPointerException r11 = new java.lang.NullPointerException
                                java.lang.String r12 = "The element at index "
                                java.lang.String r0 = " is null"
                                java.lang.String r12 = p.u4.f(r12, r2, r0)
                                r11.<init>(r12)
                                r3.onError(r11)
                                return
                            L2a:
                                r3.onNext(r8)
                                r8 = 1
                                long r6 = r6 + r8
                                int r2 = r2 + 1
                                goto La
                            L33:
                                if (r2 != r1) goto L3d
                                boolean r11 = r10.cancelled
                                if (r11 != 0) goto L3c
                                r3.onComplete()
                            L3c:
                                return
                            L3d:
                                long r11 = r10.get()
                                int r8 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
                                if (r8 != 0) goto La
                                r10.index = r2
                                long r11 = -r6
                                long r11 = r10.addAndGet(r11)
                                int r6 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
                                if (r6 != 0) goto L9
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray$ArraySubscription.d(long):void");
                        }
                    });
                    return;
                } else {
                    final io.reactivex.rxjava3.operators.a aVar = (io.reactivex.rxjava3.operators.a) rg2Var;
                    final Object[] objArr2 = (Object[]) obj2;
                    rg2Var.onSubscribe(new FlowableFromArray$BaseArraySubscription<T>(aVar, objArr2) { // from class: io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray$ArrayConditionalSubscription
                        private static final long serialVersionUID = 2587302975077663557L;
                        final io.reactivex.rxjava3.operators.a downstream;

                        {
                            super(objArr2);
                            this.downstream = aVar;
                        }

                        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray$BaseArraySubscription
                        public final void c() {
                            T[] tArr = this.array;
                            int length = tArr.length;
                            io.reactivex.rxjava3.operators.a aVar2 = this.downstream;
                            for (int i2 = this.index; i2 != length; i2++) {
                                if (this.cancelled) {
                                    return;
                                }
                                T t = tArr[i2];
                                if (t == null) {
                                    aVar2.onError(new NullPointerException(u4.f("The element at index ", i2, " is null")));
                                    return;
                                }
                                aVar2.d(t);
                            }
                            if (this.cancelled) {
                                return;
                            }
                            aVar2.onComplete();
                        }

                        /* JADX WARN: Code restructure failed: missing block: B:25:0x0048, code lost:
                        
                            r10.index = r2;
                            r11 = addAndGet(-r6);
                         */
                        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray$BaseArraySubscription
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public final void d(long r11) {
                            /*
                                r10 = this;
                                T[] r0 = r10.array
                                int r1 = r0.length
                                int r2 = r10.index
                                io.reactivex.rxjava3.operators.a r3 = r10.downstream
                                r4 = 0
                            L9:
                                r6 = r4
                            La:
                                int r8 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
                                if (r8 == 0) goto L36
                                if (r2 == r1) goto L36
                                boolean r8 = r10.cancelled
                                if (r8 == 0) goto L15
                                return
                            L15:
                                r8 = r0[r2]
                                if (r8 != 0) goto L2a
                                java.lang.NullPointerException r11 = new java.lang.NullPointerException
                                java.lang.String r12 = "The element at index "
                                java.lang.String r0 = " is null"
                                java.lang.String r12 = p.u4.f(r12, r2, r0)
                                r11.<init>(r12)
                                r3.onError(r11)
                                return
                            L2a:
                                boolean r8 = r3.d(r8)
                                if (r8 == 0) goto L33
                                r8 = 1
                                long r6 = r6 + r8
                            L33:
                                int r2 = r2 + 1
                                goto La
                            L36:
                                if (r2 != r1) goto L40
                                boolean r11 = r10.cancelled
                                if (r11 != 0) goto L3f
                                r3.onComplete()
                            L3f:
                                return
                            L40:
                                long r11 = r10.get()
                                int r8 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
                                if (r8 != 0) goto La
                                r10.index = r2
                                long r11 = -r6
                                long r11 = r10.addAndGet(r11)
                                int r6 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
                                if (r6 != 0) goto L9
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray$ArrayConditionalSubscription.d(long):void");
                        }
                    });
                    return;
                }
            case 1:
                try {
                    obj = ((io.reactivex.rxjava3.functions.q) obj2).get();
                } catch (Throwable th) {
                    th = th;
                    he1.R(th);
                }
                if (obj == null) {
                    throw io.reactivex.rxjava3.internal.util.c.b("Callable returned a null Throwable.");
                }
                Throwable th2 = io.reactivex.rxjava3.internal.util.c.a;
                th = (Throwable) obj;
                rg2Var.onSubscribe(EmptySubscription.c);
                rg2Var.onError(th);
                return;
            case 2:
                ((ObservableSource) obj2).subscribe(new w(rg2Var));
                return;
            default:
                ((az1) obj2).subscribe(rg2Var);
                return;
        }
    }
}
