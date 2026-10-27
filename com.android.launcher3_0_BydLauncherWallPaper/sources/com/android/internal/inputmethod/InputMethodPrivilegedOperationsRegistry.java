package com.android.internal.inputmethod;

import android.os.IBinder;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class InputMethodPrivilegedOperationsRegistry {
    private static final Object sLock = new Object();
    private static InputMethodPrivilegedOperations sNop;
    private static WeakHashMap<IBinder, WeakReference<InputMethodPrivilegedOperations>> sRegistry;

    private InputMethodPrivilegedOperationsRegistry() {
    }

    private static InputMethodPrivilegedOperations getNopOps() {
        if (sNop == null) {
            sNop = new InputMethodPrivilegedOperations();
        }
        return sNop;
    }

    public static void put(IBinder iBinder, InputMethodPrivilegedOperations inputMethodPrivilegedOperations) {
        synchronized (sLock) {
            if (sRegistry == null) {
                sRegistry = new WeakHashMap<>();
            }
            WeakReference<InputMethodPrivilegedOperations> weakReferencePut = sRegistry.put(iBinder, new WeakReference<>(inputMethodPrivilegedOperations));
            if (weakReferencePut != null) {
                throw new IllegalStateException(weakReferencePut.get() + " is already registered for  this token=" + iBinder + " newOps=" + inputMethodPrivilegedOperations);
            }
        }
    }

    public static InputMethodPrivilegedOperations get(IBinder iBinder) {
        synchronized (sLock) {
            WeakHashMap<IBinder, WeakReference<InputMethodPrivilegedOperations>> weakHashMap = sRegistry;
            if (weakHashMap == null) {
                return getNopOps();
            }
            WeakReference<InputMethodPrivilegedOperations> weakReference = weakHashMap.get(iBinder);
            if (weakReference == null) {
                return getNopOps();
            }
            InputMethodPrivilegedOperations inputMethodPrivilegedOperations = weakReference.get();
            if (inputMethodPrivilegedOperations != null) {
                return inputMethodPrivilegedOperations;
            }
            return getNopOps();
        }
    }

    public static void remove(IBinder iBinder) {
        synchronized (sLock) {
            WeakHashMap<IBinder, WeakReference<InputMethodPrivilegedOperations>> weakHashMap = sRegistry;
            if (weakHashMap == null) {
                return;
            }
            weakHashMap.remove(iBinder);
            if (sRegistry.isEmpty()) {
                sRegistry = null;
            }
        }
    }

    public static boolean isRegistered(IBinder iBinder) {
        synchronized (sLock) {
            WeakHashMap<IBinder, WeakReference<InputMethodPrivilegedOperations>> weakHashMap = sRegistry;
            if (weakHashMap == null) {
                return false;
            }
            return weakHashMap.containsKey(iBinder);
        }
    }
}
