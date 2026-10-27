package com.neusoft.optimus.utility.notification;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes2.dex */
public final class Notification {

    public interface INotifyFunction {
        void call();
    }

    public interface INotifyFunction1<T> {
        void call(T t);
    }

    public interface INotifyFunction2<T1, T2> {
        void call(T1 t1, T2 t2);
    }

    public interface INotifyFunction3<T1, T2, T3> {
        void call(T1 t1, T2 t2, T3 t3);
    }

    public interface INotifyFunction4<T1, T2, T3, T4> {
        void call(T1 t1, T2 t2, T3 t3, T4 t4);
    }

    private Notification() {
    }

    public static void notifyFunction(final INotifyFunction iNotifyFunction) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.neusoft.optimus.utility.notification.Notification.1
            @Override // java.lang.Runnable
            public void run() {
                iNotifyFunction.call();
            }
        });
    }

    public static <T> void notifyFunction1(final INotifyFunction1<T> iNotifyFunction1, final T t) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.neusoft.optimus.utility.notification.Notification.2
            @Override // java.lang.Runnable
            public void run() {
                iNotifyFunction1.call(t);
            }
        });
    }

    public static <T1, T2> void notifyFunction2(final INotifyFunction2<T1, T2> iNotifyFunction2, final T1 t1, final T2 t2) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.neusoft.optimus.utility.notification.Notification.3
            @Override // java.lang.Runnable
            public void run() {
                iNotifyFunction2.call(t1, t2);
            }
        });
    }

    public static <T1, T2, T3> void notifyFunction3(final INotifyFunction3<T1, T2, T3> iNotifyFunction3, final T1 t1, final T2 t2, final T3 t3) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.neusoft.optimus.utility.notification.Notification.4
            @Override // java.lang.Runnable
            public void run() {
                iNotifyFunction3.call(t1, t2, t3);
            }
        });
    }

    public static <T1, T2, T3, T4> void notifyFunction4(final INotifyFunction4<T1, T2, T3, T4> iNotifyFunction4, final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.neusoft.optimus.utility.notification.Notification.5
            @Override // java.lang.Runnable
            public void run() {
                iNotifyFunction4.call(t1, t2, t3, t4);
            }
        });
    }
}
