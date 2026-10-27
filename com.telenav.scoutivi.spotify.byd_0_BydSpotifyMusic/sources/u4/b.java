package u4;

/* JADX INFO: compiled from: Exceptions.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static RuntimeException a(Throwable th) {
        throw b5.b.a(th);
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
