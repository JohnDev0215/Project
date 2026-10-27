package com.android.internal.inputmethod;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
public final class CancellationGroup {
    private final Object mLock = new Object();
    private ArrayList<CountDownLatch> mLatchList = null;
    private boolean mCanceled = false;

    public static final class Completable {
        private Completable() {
        }

        protected static class ValueBase {
            private final CancellationGroup mParentGroup;
            private final CountDownLatch mLatch = new CountDownLatch(1);
            protected final Object mValueLock = new Object();
            protected boolean mHasValue = false;

            protected ValueBase(CancellationGroup cancellationGroup) {
                this.mParentGroup = cancellationGroup;
            }

            public boolean hasValue() {
                boolean z;
                synchronized (this.mValueLock) {
                    z = this.mHasValue;
                }
                return z;
            }

            protected void onComplete() {
                this.mLatch.countDown();
            }

            public boolean await(int i, TimeUnit timeUnit) {
                boolean zAwait;
                if (!this.mParentGroup.registerLatch(this.mLatch)) {
                    return false;
                }
                try {
                    zAwait = this.mLatch.await(i, timeUnit);
                } catch (InterruptedException unused) {
                    zAwait = true;
                } catch (Throwable th) {
                    this.mParentGroup.unregisterLatch(this.mLatch);
                    throw th;
                }
                this.mParentGroup.unregisterLatch(this.mLatch);
                return zAwait;
            }
        }

        public static final class Int extends ValueBase {
            private int mValue;

            @Override // com.android.internal.inputmethod.CancellationGroup.Completable.ValueBase
            public /* bridge */ /* synthetic */ boolean await(int i, TimeUnit timeUnit) {
                return super.await(i, timeUnit);
            }

            @Override // com.android.internal.inputmethod.CancellationGroup.Completable.ValueBase
            public /* bridge */ /* synthetic */ boolean hasValue() {
                return super.hasValue();
            }

            private Int(CancellationGroup cancellationGroup) {
                super(cancellationGroup);
                this.mValue = 0;
            }

            void onComplete(int i) {
                synchronized (this.mValueLock) {
                    if (this.mHasValue) {
                        throw new UnsupportedOperationException("onComplete() cannot be called multiple times");
                    }
                    this.mValue = i;
                    this.mHasValue = true;
                }
                onComplete();
            }

            public int getValue() {
                int i;
                synchronized (this.mValueLock) {
                    if (!this.mHasValue) {
                        throw new UnsupportedOperationException("getValue() is allowed only if hasValue() returns true");
                    }
                    i = this.mValue;
                }
                return i;
            }
        }

        public static class Values<T> extends ValueBase {
            private T mValue;

            @Override // com.android.internal.inputmethod.CancellationGroup.Completable.ValueBase
            public /* bridge */ /* synthetic */ boolean await(int i, TimeUnit timeUnit) {
                return super.await(i, timeUnit);
            }

            @Override // com.android.internal.inputmethod.CancellationGroup.Completable.ValueBase
            public /* bridge */ /* synthetic */ boolean hasValue() {
                return super.hasValue();
            }

            protected Values(CancellationGroup cancellationGroup) {
                super(cancellationGroup);
                this.mValue = null;
            }

            void onComplete(T t) {
                synchronized (this.mValueLock) {
                    if (this.mHasValue) {
                        throw new UnsupportedOperationException("onComplete() cannot be called multiple times");
                    }
                    this.mValue = t;
                    this.mHasValue = true;
                }
                onComplete();
            }

            public T getValue() {
                T t;
                synchronized (this.mValueLock) {
                    if (!this.mHasValue) {
                        throw new UnsupportedOperationException("getValue() is allowed only if hasValue() returns true");
                    }
                    t = this.mValue;
                }
                return t;
            }
        }

        public static final class CharSequence extends Values<java.lang.CharSequence> {
            private CharSequence(CancellationGroup cancellationGroup) {
                super(cancellationGroup);
            }
        }

        public static final class ExtractedText extends Values<android.view.inputmethod.ExtractedText> {
            private ExtractedText(CancellationGroup cancellationGroup) {
                super(cancellationGroup);
            }
        }
    }

    public Completable.Int createCompletableInt() {
        return new Completable.Int(this);
    }

    public Completable.CharSequence createCompletableCharSequence() {
        return new Completable.CharSequence(this);
    }

    public Completable.ExtractedText createCompletableExtractedText() {
        return new Completable.ExtractedText(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean registerLatch(CountDownLatch countDownLatch) {
        synchronized (this.mLock) {
            if (this.mCanceled) {
                return false;
            }
            if (this.mLatchList == null) {
                this.mLatchList = new ArrayList<>(1);
            }
            this.mLatchList.add(countDownLatch);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterLatch(CountDownLatch countDownLatch) {
        synchronized (this.mLock) {
            ArrayList<CountDownLatch> arrayList = this.mLatchList;
            if (arrayList != null) {
                arrayList.remove(countDownLatch);
            }
        }
    }

    public void cancelAll() {
        synchronized (this.mLock) {
            if (!this.mCanceled) {
                this.mCanceled = true;
                ArrayList<CountDownLatch> arrayList = this.mLatchList;
                if (arrayList != null) {
                    arrayList.forEach(new Consumer() { // from class: com.android.internal.inputmethod.-$$Lambda$CancellationGroup$_4zm8rEyVrss7LL0NI_JyGsLAW0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((CountDownLatch) obj).countDown();
                        }
                    });
                    this.mLatchList.clear();
                    this.mLatchList = null;
                }
            }
        }
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mCanceled;
        }
        return z;
    }
}
