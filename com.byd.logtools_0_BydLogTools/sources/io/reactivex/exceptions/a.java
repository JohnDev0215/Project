package io.reactivex.exceptions;

import io.reactivex.internal.util.ExceptionHelper;

/* JADX INFO: compiled from: Exceptions.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static RuntimeException a(Throwable th) {
        throw ExceptionHelper.c(th);
    }

    public static void b(Throwable th) {
        if (th instanceof VirtualMachineError) {
            throw ((VirtualMachineError) th);
        }
        if (th instanceof ThreadDeath) {
            throw ((ThreadDeath) th);
        }
        if (th instanceof LinkageError) {
            throw ((LinkageError) th);
        }
    }
}
