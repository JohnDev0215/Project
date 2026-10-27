package spi;

/* JADX INFO: loaded from: classes2.dex */
public final class x8 {
    private x8() {
        throw new IllegalStateException("No instances!");
    }

    public static void a(Throwable th) {
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
