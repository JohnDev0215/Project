package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import java.util.Objects;
import java.util.function.BiFunction;
import spi.e6;

/* JADX INFO: compiled from: LiveDataFunctions.java */
/* JADX INFO: loaded from: classes.dex */
public final class e6 {

    /* JADX INFO: compiled from: LiveDataFunctions.java */
    public static class a<T, U, R> extends MediatorLiveData<R> {

        @NonNull
        public final BiFunction<T, U, R> a;
        public boolean b;
        public boolean c;
        public boolean d;

        @Nullable
        public T e;

        @Nullable
        public U f;

        public a(@NonNull LiveData<T> liveData, @NonNull LiveData<U> liveData2, @NonNull BiFunction<T, U, R> biFunction) {
            this(liveData, liveData2, true, true, biFunction);
        }

        public final void a() {
            if (this.b && this.c) {
                R rApply = this.a.apply(this.e, this.f);
                if (this.d && rApply == getValue()) {
                    return;
                }
                this.d = true;
                setValue(rApply);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public a(@NonNull LiveData<T> liveData, @NonNull LiveData<U> liveData2, boolean z, boolean z2, @NonNull BiFunction<T, U, R> biFunction) {
            this.a = biFunction;
            final int i = 1;
            if (!z) {
                this.b = true;
            }
            if (!z2) {
                this.c = true;
            }
            if (liveData == liveData2) {
                final int i2 = 0;
                addSource(liveData, new Observer(this) { // from class: spi.d6
                    public final /* synthetic */ e6.a b;

                    {
                        this.b = this;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        switch (i2) {
                            case 0:
                                e6.a aVar = this.b;
                                aVar.b = true;
                                aVar.c = true;
                                aVar.e = obj;
                                aVar.f = obj;
                                aVar.a();
                                break;
                            case 1:
                                e6.a aVar2 = this.b;
                                aVar2.b = true;
                                aVar2.e = obj;
                                aVar2.a();
                                break;
                            default:
                                e6.a aVar3 = this.b;
                                aVar3.c = true;
                                aVar3.f = obj;
                                aVar3.a();
                                break;
                        }
                    }
                });
                return;
            }
            Objects.requireNonNull(liveData);
            addSource(liveData, new Observer(this) { // from class: spi.d6
                public final /* synthetic */ e6.a b;

                {
                    this.b = this;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    switch (i) {
                        case 0:
                            e6.a aVar = this.b;
                            aVar.b = true;
                            aVar.c = true;
                            aVar.e = obj;
                            aVar.f = obj;
                            aVar.a();
                            break;
                        case 1:
                            e6.a aVar2 = this.b;
                            aVar2.b = true;
                            aVar2.e = obj;
                            aVar2.a();
                            break;
                        default:
                            e6.a aVar3 = this.b;
                            aVar3.c = true;
                            aVar3.f = obj;
                            aVar3.a();
                            break;
                    }
                }
            });
            Objects.requireNonNull(liveData2);
            final int i3 = 2;
            addSource(liveData2, new Observer(this) { // from class: spi.d6
                public final /* synthetic */ e6.a b;

                {
                    this.b = this;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    switch (i3) {
                        case 0:
                            e6.a aVar = this.b;
                            aVar.b = true;
                            aVar.c = true;
                            aVar.e = obj;
                            aVar.f = obj;
                            aVar.a();
                            break;
                        case 1:
                            e6.a aVar2 = this.b;
                            aVar2.b = true;
                            aVar2.e = obj;
                            aVar2.a();
                            break;
                        default:
                            e6.a aVar3 = this.b;
                            aVar3.c = true;
                            aVar3.f = obj;
                            aVar3.a();
                            break;
                    }
                }
            });
        }
    }

    private e6() {
    }

    public static <T> MutableLiveData<T> a(@Nullable T t) {
        MutableLiveData<T> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(t);
        return mutableLiveData;
    }

    public static <T, R> LiveData<R> b(@NonNull LiveData<T> liveData, @NonNull Function<T, R> function) {
        return Transformations.map(liveData, new b6(null, function));
    }
}
