package com.byd.automultipletheme.common.bus;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.byd.automultipletheme.common.bus.LiveDataBus;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveDataBus.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\f\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\n0\u0006\"\u0004\b\u0000\u0010\n2\u0006\u0010\u000b\u001a\u00020\u0005R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/byd/automultipletheme/common/bus/LiveDataBus;", "", "()V", "bus", "", "", "Lcom/byd/automultipletheme/common/bus/LiveDataBus$BusMutableLiveData;", "remove", "", "with", ExifInterface.GPS_DIRECTION_TRUE, "key", "BusMutableLiveData", "ObserverWrapper", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LiveDataBus {
    public static final LiveDataBus INSTANCE = new LiveDataBus();
    private static final Map<String, BusMutableLiveData<Object>> bus = new LinkedHashMap();

    private LiveDataBus() {
    }

    public final <T> BusMutableLiveData<T> with(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Map<String, BusMutableLiveData<Object>> map = bus;
        if (!map.containsKey(key)) {
            map.put(key, new BusMutableLiveData<>());
        }
        MutableLiveData mutableLiveData = map.get(key);
        Objects.requireNonNull(mutableLiveData, "null cannot be cast to non-null type com.byd.automultipletheme.common.bus.LiveDataBus.BusMutableLiveData<T of com.byd.automultipletheme.common.bus.LiveDataBus.with>");
        return (BusMutableLiveData) mutableLiveData;
    }

    public final void remove() {
        bus.clear();
    }

    /* JADX INFO: compiled from: LiveDataBus.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0002J \u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0006H\u0016J\u0018\u0010\r\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0006H\u0016J\u0016\u0010\u000e\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0006J(\u0010\u000f\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0012J\u0018\u0010\u0013\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0006H\u0016R\"\u0010\u0004\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/byd/automultipletheme/common/bus/LiveDataBus$BusMutableLiveData;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/MutableLiveData;", "()V", "observerMap", "", "Landroidx/lifecycle/Observer;", "hook", "", "observer", "observe", "owner", "Landroidx/lifecycle/LifecycleOwner;", "observeForever", "observeForeverSticky", "observeSticky", "Lkotlin/Function0;", "Landroidx/lifecycle/Lifecycle;", "Lkotlin/Function1;", "removeObserver", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class BusMutableLiveData<T> extends MutableLiveData<T> {
        private final Map<Observer<?>, Observer<?>> observerMap = new LinkedHashMap();

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: observeSticky$lambda-0, reason: not valid java name */
        public static final Lifecycle m55observeSticky$lambda0(Function0 tmp0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return (Lifecycle) tmp0.invoke();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: observeSticky$lambda-1, reason: not valid java name */
        public static final void m56observeSticky$lambda1(Function1 tmp0, Object obj) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            tmp0.invoke(obj);
        }

        public final void observeSticky(final Function0<? extends Lifecycle> owner, final Function1<? super T, Unit> observer) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            Intrinsics.checkNotNullParameter(observer, "observer");
            super.observe(new LifecycleOwner() { // from class: com.byd.automultipletheme.common.bus.LiveDataBus$BusMutableLiveData$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.LifecycleOwner
                public final Lifecycle getLifecycle() {
                    return LiveDataBus.BusMutableLiveData.m55observeSticky$lambda0(owner);
                }
            }, new Observer() { // from class: com.byd.automultipletheme.common.bus.LiveDataBus$BusMutableLiveData$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    LiveDataBus.BusMutableLiveData.m56observeSticky$lambda1(observer, obj);
                }
            });
        }

        public final void observeForeverSticky(Observer<? super T> observer) {
            Intrinsics.checkNotNullParameter(observer, "observer");
            super.observeForever(observer);
        }

        @Override // androidx.lifecycle.LiveData
        public void observeForever(Observer<? super T> observer) {
            Intrinsics.checkNotNullParameter(observer, "observer");
            if (!this.observerMap.containsKey(observer)) {
                this.observerMap.put(observer, new ObserverWrapper(observer));
            }
            Object obj = this.observerMap.get(observer);
            Objects.requireNonNull(obj, "null cannot be cast to non-null type androidx.lifecycle.Observer<in T of com.byd.automultipletheme.common.bus.LiveDataBus.BusMutableLiveData>");
            super.observeForever((Observer) obj);
        }

        @Override // androidx.lifecycle.LiveData
        public void removeObserver(Observer<? super T> observer) {
            Intrinsics.checkNotNullParameter(observer, "observer");
            if (this.observerMap.containsKey(observer)) {
                Object objRemove = this.observerMap.remove(observer);
                Objects.requireNonNull(objRemove, "null cannot be cast to non-null type androidx.lifecycle.Observer<in T of com.byd.automultipletheme.common.bus.LiveDataBus.BusMutableLiveData>");
                observer = (Observer) objRemove;
            }
            super.removeObserver(observer);
        }

        @Override // androidx.lifecycle.LiveData
        public void observe(LifecycleOwner owner, Observer<? super T> observer) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            Intrinsics.checkNotNullParameter(observer, "observer");
            super.observe(owner, observer);
            try {
                hook(observer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private final void hook(Observer<?> observer) throws Exception {
            Field declaredField = LiveData.class.getDeclaredField("mObservers");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            Method declaredMethod = obj.getClass().getDeclaredMethod("get", Object.class);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(obj, observer);
            Object value = objInvoke instanceof Map.Entry ? ((Map.Entry) objInvoke).getValue() : null;
            Objects.requireNonNull(value, "Wrapper can not be null!");
            Class<? super Object> superclass = value.getClass().getSuperclass();
            Intrinsics.checkNotNull(superclass);
            Field declaredField2 = superclass.getDeclaredField("mLastVersion");
            declaredField2.setAccessible(true);
            Field declaredField3 = LiveData.class.getDeclaredField("mVersion");
            declaredField3.setAccessible(true);
            declaredField2.set(value, declaredField3.get(this));
        }
    }

    /* JADX INFO: compiled from: LiveDataBus.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\fR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/byd/automultipletheme/common/bus/LiveDataBus$ObserverWrapper;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/Observer;", "observer", "(Landroidx/lifecycle/Observer;)V", "getObserver", "()Landroidx/lifecycle/Observer;", "isCallOnObserverForever", "", "onChanged", "", "t", "(Ljava/lang/Object;)V", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class ObserverWrapper<T> implements Observer<T> {
        private final Observer<T> observer;

        public ObserverWrapper(Observer<T> observer) {
            Intrinsics.checkNotNullParameter(observer, "observer");
            this.observer = observer;
        }

        public final Observer<T> getObserver() {
            return this.observer;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(T t) {
            if (isCallOnObserverForever()) {
                return;
            }
            this.observer.onChanged(t);
        }

        private final boolean isCallOnObserverForever() {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "stackTrace");
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (Intrinsics.areEqual("androidx.lifecycle.LiveData", stackTraceElement.getClassName()) && Intrinsics.areEqual("observeForever", stackTraceElement.getMethodName())) {
                    return true;
                }
            }
            return false;
        }
    }
}
