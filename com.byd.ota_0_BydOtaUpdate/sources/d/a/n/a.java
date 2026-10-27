package d.a.n;

/* JADX INFO: compiled from: Exceptions.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
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
