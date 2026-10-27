package com.google.android.gms.internal.cast;

import com.google.common.util.concurrent.ListenableFuture;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzof<V> extends zzoj implements ListenableFuture<V> {
    static final boolean zza;
    private static final Logger zzb;
    private static final zza zzc;
    private static final Object zzd;

    @CheckForNull
    private volatile zzd listeners;

    @CheckForNull
    private volatile Object value;

    @CheckForNull
    private volatile zzk waiters;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    abstract class zza {
        /* synthetic */ zza(AnonymousClass1 anonymousClass1) {
        }

        abstract zzd zza(zzof zzofVar, zzd zzdVar);

        abstract zzk zzb(zzof zzofVar, zzk zzkVar);

        abstract void zzc(zzk zzkVar, @CheckForNull zzk zzkVar2);

        abstract void zzd(zzk zzkVar, Thread thread);

        abstract boolean zze(zzof zzofVar, @CheckForNull zzd zzdVar, zzd zzdVar2);

        abstract boolean zzf(zzof zzofVar, @CheckForNull Object obj, Object obj2);

        abstract boolean zzg(zzof zzofVar, @CheckForNull zzk zzkVar, @CheckForNull zzk zzkVar2);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    final class zzb {

        @CheckForNull
        static final zzb zza;

        @CheckForNull
        static final zzb zzb;
        final boolean zzc;

        @CheckForNull
        final Throwable zzd;

        static {
            if (zzof.zza) {
                zzb = null;
                zza = null;
            } else {
                zzb = new zzb(false, null);
                zza = new zzb(true, null);
            }
        }

        zzb(boolean z, @CheckForNull Throwable th) {
            this.zzc = z;
            this.zzd = th;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    final class zzc {
        static final zzc zza = new zzc(new Throwable("Failure occurred while trying to finish a future.") { // from class: com.google.android.gms.internal.cast.zzof.zzc.1
            {
                super("Failure occurred while trying to finish a future.");
            }

            @Override // java.lang.Throwable
            public final synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable zzb;

        zzc(Throwable th) {
            th.getClass();
            this.zzb = th;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    final class zzd {
        static final zzd zza = new zzd();

        @CheckForNull
        zzd next;

        @CheckForNull
        final Runnable zzb;

        @CheckForNull
        final Executor zzc;

        zzd() {
            this.zzb = null;
            this.zzc = null;
        }

        zzd(Runnable runnable, Executor executor) {
            this.zzb = runnable;
            this.zzc = executor;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    final class zze extends zza {
        final AtomicReferenceFieldUpdater<zzk, Thread> zza;
        final AtomicReferenceFieldUpdater<zzk, zzk> zzb;
        final AtomicReferenceFieldUpdater<zzof, zzk> zzc;
        final AtomicReferenceFieldUpdater<zzof, zzd> zzd;
        final AtomicReferenceFieldUpdater<zzof, Object> zze;

        zze(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
            super(null);
            this.zza = atomicReferenceFieldUpdater;
            this.zzb = atomicReferenceFieldUpdater2;
            this.zzc = atomicReferenceFieldUpdater3;
            this.zzd = atomicReferenceFieldUpdater4;
            this.zze = atomicReferenceFieldUpdater5;
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final zzd zza(zzof zzofVar, zzd zzdVar) {
            return this.zzd.getAndSet(zzofVar, zzdVar);
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final zzk zzb(zzof zzofVar, zzk zzkVar) {
            return this.zzc.getAndSet(zzofVar, zzkVar);
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final void zzc(zzk zzkVar, @CheckForNull zzk zzkVar2) {
            this.zzb.lazySet(zzkVar, zzkVar2);
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final void zzd(zzk zzkVar, Thread thread) {
            this.zza.lazySet(zzkVar, thread);
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final boolean zze(zzof zzofVar, @CheckForNull zzd zzdVar, zzd zzdVar2) {
            return zzog.zza(this.zzd, zzofVar, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final boolean zzf(zzof zzofVar, @CheckForNull Object obj, Object obj2) {
            return zzog.zza(this.zze, zzofVar, obj, obj2);
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final boolean zzg(zzof zzofVar, @CheckForNull zzk zzkVar, @CheckForNull zzk zzkVar2) {
            return zzog.zza(this.zzc, zzofVar, zzkVar, zzkVar2);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    final class zzf<V> implements Runnable {
        final zzof<V> zza;
        final ListenableFuture<? extends V> zzb;

        @Override // java.lang.Runnable
        public final void run() {
            throw null;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    final class zzg extends zza {
        private zzg() {
            super(null);
        }

        /* synthetic */ zzg(AnonymousClass1 anonymousClass1) {
            super(null);
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final zzd zza(zzof zzofVar, zzd zzdVar) {
            zzd zzdVar2;
            synchronized (zzofVar) {
                zzdVar2 = zzofVar.listeners;
                if (zzdVar2 != zzdVar) {
                    zzofVar.listeners = zzdVar;
                }
            }
            return zzdVar2;
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final zzk zzb(zzof zzofVar, zzk zzkVar) {
            zzk zzkVar2;
            synchronized (zzofVar) {
                zzkVar2 = zzofVar.waiters;
                if (zzkVar2 != zzkVar) {
                    zzofVar.waiters = zzkVar;
                }
            }
            return zzkVar2;
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final void zzc(zzk zzkVar, @CheckForNull zzk zzkVar2) {
            zzkVar.next = zzkVar2;
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final void zzd(zzk zzkVar, Thread thread) {
            zzkVar.thread = thread;
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final boolean zze(zzof zzofVar, @CheckForNull zzd zzdVar, zzd zzdVar2) {
            synchronized (zzofVar) {
                if (zzofVar.listeners != zzdVar) {
                    return false;
                }
                zzofVar.listeners = zzdVar2;
                return true;
            }
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final boolean zzf(zzof zzofVar, @CheckForNull Object obj, Object obj2) {
            synchronized (zzofVar) {
                if (zzofVar.value != obj) {
                    return false;
                }
                zzofVar.value = obj2;
                return true;
            }
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final boolean zzg(zzof zzofVar, @CheckForNull zzk zzkVar, @CheckForNull zzk zzkVar2) {
            synchronized (zzofVar) {
                if (zzofVar.waiters != zzkVar) {
                    return false;
                }
                zzofVar.waiters = zzkVar2;
                return true;
            }
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    interface zzh<V> extends ListenableFuture<V> {
        @Override // com.google.common.util.concurrent.ListenableFuture
        /* synthetic */ void addListener(Runnable runnable, Executor executor);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    abstract class zzi<V> extends zzof<V> implements zzh<V> {
        zzi() {
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    final class zzj extends zza {
        static final Unsafe zza;
        static final long zzb;
        static final long zzc;
        static final long zzd;
        static final long zze;
        static final long zzf;

        static {
            Unsafe unsafe;
            try {
                try {
                    unsafe = Unsafe.getUnsafe();
                } catch (SecurityException unused) {
                    unsafe = (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.android.gms.internal.cast.zzof.zzj.1
                        public static final Unsafe zza() throws Exception {
                            for (Field field : Unsafe.class.getDeclaredFields()) {
                                field.setAccessible(true);
                                Object obj = field.get(null);
                                if (Unsafe.class.isInstance(obj)) {
                                    return (Unsafe) Unsafe.class.cast(obj);
                                }
                            }
                            throw new NoSuchFieldError("the Unsafe");
                        }

                        @Override // java.security.PrivilegedExceptionAction
                        public final /* bridge */ /* synthetic */ Unsafe run() throws Exception {
                            return zza();
                        }
                    });
                }
                try {
                    zzc = unsafe.objectFieldOffset(zzof.class.getDeclaredField("waiters"));
                    zzb = unsafe.objectFieldOffset(zzof.class.getDeclaredField("listeners"));
                    zzd = unsafe.objectFieldOffset(zzof.class.getDeclaredField("value"));
                    zze = unsafe.objectFieldOffset(zzk.class.getDeclaredField("thread"));
                    zzf = unsafe.objectFieldOffset(zzk.class.getDeclaredField("next"));
                    zza = unsafe;
                } catch (Exception e) {
                    zzdu.zza(e);
                    throw new RuntimeException(e);
                }
            } catch (PrivilegedActionException e2) {
                throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
            }
        }

        private zzj() {
            super(null);
        }

        /* synthetic */ zzj(AnonymousClass1 anonymousClass1) {
            super(null);
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final zzd zza(zzof zzofVar, zzd zzdVar) {
            zzd zzdVar2;
            do {
                zzdVar2 = zzofVar.listeners;
                if (zzdVar == zzdVar2) {
                    return zzdVar2;
                }
            } while (!zze(zzofVar, zzdVar2, zzdVar));
            return zzdVar2;
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final zzk zzb(zzof zzofVar, zzk zzkVar) {
            zzk zzkVar2;
            do {
                zzkVar2 = zzofVar.waiters;
                if (zzkVar == zzkVar2) {
                    return zzkVar2;
                }
            } while (!zzg(zzofVar, zzkVar2, zzkVar));
            return zzkVar2;
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final void zzc(zzk zzkVar, @CheckForNull zzk zzkVar2) {
            zza.putObject(zzkVar, zzf, zzkVar2);
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final void zzd(zzk zzkVar, Thread thread) {
            zza.putObject(zzkVar, zze, thread);
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final boolean zze(zzof zzofVar, @CheckForNull zzd zzdVar, zzd zzdVar2) {
            return zzoh.zza(zza, zzofVar, zzb, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final boolean zzf(zzof zzofVar, @CheckForNull Object obj, Object obj2) {
            return zzoh.zza(zza, zzofVar, zzd, obj, obj2);
        }

        @Override // com.google.android.gms.internal.cast.zzof.zza
        final boolean zzg(zzof zzofVar, @CheckForNull zzk zzkVar, @CheckForNull zzk zzkVar2) {
            return zzoh.zza(zza, zzofVar, zzc, zzkVar, zzkVar2);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    final class zzk {
        static final zzk zza = new zzk(false);

        @CheckForNull
        volatile zzk next;

        @CheckForNull
        volatile Thread thread;

        zzk() {
            zzof.zzc.zzd(this, Thread.currentThread());
        }

        zzk(boolean z) {
        }
    }

    static {
        boolean z;
        Throwable th;
        Throwable th2;
        zza zzgVar;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z = false;
        }
        zza = z;
        zzb = Logger.getLogger(zzof.class.getName());
        AnonymousClass1 anonymousClass1 = null;
        try {
            zzgVar = new zzj(anonymousClass1);
            th2 = null;
            th = null;
        } catch (Throwable th3) {
            try {
                th2 = th3;
                zzgVar = new zze(AtomicReferenceFieldUpdater.newUpdater(zzk.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(zzk.class, zzk.class, "next"), AtomicReferenceFieldUpdater.newUpdater(zzof.class, zzk.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(zzof.class, zzd.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(zzof.class, Object.class, "value"));
                th = null;
            } catch (Throwable th4) {
                th = th4;
                th2 = th3;
                zzgVar = new zzg(anonymousClass1);
            }
        }
        zzc = zzgVar;
        if (th != null) {
            Logger logger = zzb;
            Level level = Level.SEVERE;
            logger.logp(level, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "UnsafeAtomicHelper is broken!", th2);
            logger.logp(level, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
        zzd = new Object();
    }

    protected zzof() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Object zzj(ListenableFuture listenableFuture) {
        Throwable thZzh;
        if (listenableFuture instanceof zzh) {
            Object zzbVar = ((zzof) listenableFuture).value;
            if (zzbVar instanceof zzb) {
                zzb zzbVar2 = (zzb) zzbVar;
                if (zzbVar2.zzc) {
                    Throwable th = zzbVar2.zzd;
                    zzbVar = th != null ? new zzb(false, th) : zzb.zzb;
                }
            }
            zzbVar.getClass();
            return zzbVar;
        }
        if ((listenableFuture instanceof zzoj) && (thZzh = ((zzoj) listenableFuture).zzh()) != null) {
            return new zzc(thZzh);
        }
        boolean zIsCancelled = listenableFuture.isCancelled();
        if ((!zza) && zIsCancelled) {
            zzb zzbVar3 = zzb.zzb;
            zzbVar3.getClass();
            return zzbVar3;
        }
        try {
            Object objZzk = zzk(listenableFuture);
            if (!zIsCancelled) {
                return objZzk == null ? zzd : objZzk;
            }
            String strValueOf = String.valueOf(listenableFuture);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 84);
            sb.append("get() did not throw CancellationException, despite reporting isCancelled() == true: ");
            sb.append(strValueOf);
            return new zzb(false, new IllegalArgumentException(sb.toString()));
        } catch (CancellationException e) {
            return !zIsCancelled ? new zzc(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: ".concat(String.valueOf(listenableFuture)), e)) : new zzb(false, e);
        } catch (ExecutionException e2) {
            return zIsCancelled ? new zzb(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(listenableFuture)), e2)) : new zzc(e2.getCause());
        } catch (Throwable th2) {
            return new zzc(th2);
        }
    }

    private static Object zzk(Future future) throws ExecutionException {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    private final void zzl(StringBuilder sb) {
        try {
            Object objZzk = zzk(this);
            sb.append("SUCCESS, result=[");
            if (objZzk == null) {
                sb.append("null");
            } else if (objZzk == this) {
                sb.append("this future");
            } else {
                sb.append(objZzk.getClass().getName());
                sb.append("@");
                sb.append(Integer.toHexString(System.identityHashCode(objZzk)));
            }
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e2) {
            sb.append("FAILURE, cause=[");
            sb.append(e2.getCause());
            sb.append("]");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void zzm(StringBuilder sb) {
        String strConcat;
        String string;
        int length = sb.length();
        sb.append("PENDING");
        Object obj = this.value;
        if (obj instanceof zzf) {
            sb.append(", setFuture=[");
            zzn(sb, ((zzf) obj).zzb);
            sb.append("]");
        } else {
            try {
                if (this instanceof ScheduledFuture) {
                    long delay = ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS);
                    StringBuilder sb2 = new StringBuilder(41);
                    sb2.append("remaining delay=[");
                    sb2.append(delay);
                    sb2.append(" ms]");
                    string = sb2.toString();
                } else {
                    string = null;
                }
                strConcat = zzdt.zza(string);
            } catch (RuntimeException | StackOverflowError e) {
                strConcat = "Exception thrown from implementation: ".concat(String.valueOf(e.getClass()));
            }
            if (strConcat != null) {
                sb.append(", info=[");
                sb.append(strConcat);
                sb.append("]");
            }
        }
        if (isDone()) {
            sb.delete(length, sb.length());
            zzl(sb);
        }
    }

    private final void zzn(StringBuilder sb, @CheckForNull Object obj) {
        try {
            if (obj == this) {
                sb.append("this future");
            } else {
                sb.append(obj);
            }
        } catch (RuntimeException | StackOverflowError e) {
            sb.append("Exception thrown from implementation: ");
            sb.append(e.getClass());
        }
    }

    private static void zzo(zzof zzofVar) {
        zzd zzdVar = null;
        while (true) {
            for (zzk zzkVarZzb = zzc.zzb(zzofVar, zzk.zza); zzkVarZzb != null; zzkVarZzb = zzkVarZzb.next) {
                Thread thread = zzkVarZzb.thread;
                if (thread != null) {
                    zzkVarZzb.thread = null;
                    LockSupport.unpark(thread);
                }
            }
            zzd zzdVar2 = zzdVar;
            zzd zzdVarZza = zzc.zza(zzofVar, zzd.zza);
            zzd zzdVar3 = zzdVar2;
            while (zzdVarZza != null) {
                zzd zzdVar4 = zzdVarZza.next;
                zzdVarZza.next = zzdVar3;
                zzdVar3 = zzdVarZza;
                zzdVarZza = zzdVar4;
            }
            while (zzdVar3 != null) {
                zzdVar = zzdVar3.next;
                Runnable runnable = zzdVar3.zzb;
                runnable.getClass();
                if (runnable instanceof zzf) {
                    zzf zzfVar = (zzf) runnable;
                    zzofVar = zzfVar.zza;
                    if (zzofVar.value == zzfVar) {
                        if (zzc.zzf(zzofVar, zzfVar, zzj(zzfVar.zzb))) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    Executor executor = zzdVar3.zzc;
                    executor.getClass();
                    zzp(runnable, executor);
                }
                zzdVar3 = zzdVar;
            }
            return;
        }
    }

    private static void zzp(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = zzb;
            Level level = Level.SEVERE;
            String strValueOf = String.valueOf(runnable);
            String strValueOf2 = String.valueOf(executor);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 57 + strValueOf2.length());
            sb.append("RuntimeException while executing runnable ");
            sb.append(strValueOf);
            sb.append(" with executor ");
            sb.append(strValueOf2);
            logger.logp(level, "com.google.common.util.concurrent.AbstractFuture", "executeListener", sb.toString(), (Throwable) e);
        }
    }

    private final void zzq(zzk zzkVar) {
        zzkVar.thread = null;
        while (true) {
            zzk zzkVar2 = this.waiters;
            if (zzkVar2 != zzk.zza) {
                zzk zzkVar3 = null;
                while (zzkVar2 != null) {
                    zzk zzkVar4 = zzkVar2.next;
                    if (zzkVar2.thread != null) {
                        zzkVar3 = zzkVar2;
                    } else if (zzkVar3 != null) {
                        zzkVar3.next = zzkVar4;
                        if (zzkVar3.thread == null) {
                            break;
                        }
                    } else if (!zzc.zzg(this, zzkVar2, zzkVar4)) {
                        break;
                    }
                    zzkVar2 = zzkVar4;
                }
                return;
            }
            return;
        }
    }

    private static final Object zzr(Object obj) throws ExecutionException {
        if (obj instanceof zzb) {
            Throwable th = ((zzb) obj).zzd;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof zzc) {
            throw new ExecutionException(((zzc) obj).zzb);
        }
        if (obj == zzd) {
            return null;
        }
        return obj;
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        zzd zzdVar;
        zzdr.zzc(runnable, "Runnable was null.");
        zzdr.zzc(executor, "Executor was null.");
        if (!isDone() && (zzdVar = this.listeners) != zzd.zza) {
            zzd zzdVar2 = new zzd(runnable, executor);
            do {
                zzdVar2.next = zzdVar;
                if (zzc.zze(this, zzdVar, zzdVar2)) {
                    return;
                } else {
                    zzdVar = this.listeners;
                }
            } while (zzdVar != zzd.zza);
        }
        zzp(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        zzb zzbVar;
        Object obj = this.value;
        if (!(obj == null) && !(obj instanceof zzf)) {
            return false;
        }
        if (zza) {
            zzbVar = new zzb(z, new CancellationException("Future.cancel() was called."));
        } else {
            zzbVar = z ? zzb.zza : zzb.zzb;
            zzbVar.getClass();
        }
        zzof<V> zzofVar = this;
        boolean z2 = false;
        while (true) {
            if (zzc.zzf(zzofVar, obj, zzbVar)) {
                zzo(zzofVar);
                if (!(obj instanceof zzf)) {
                    break;
                }
                ListenableFuture<? extends V> listenableFuture = ((zzf) obj).zzb;
                if (!(listenableFuture instanceof zzh)) {
                    listenableFuture.cancel(z);
                    break;
                }
                zzofVar = (zzof) listenableFuture;
                obj = zzofVar.value;
                if (!(obj == null) && !(obj instanceof zzf)) {
                    break;
                }
                z2 = true;
            } else {
                obj = zzofVar.value;
                if (!(obj instanceof zzf)) {
                    return z2;
                }
            }
        }
        return true;
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws ExecutionException, InterruptedException {
        Object obj;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj2 = this.value;
        if ((obj2 != null) && (!(obj2 instanceof zzf))) {
            return zzr(obj2);
        }
        zzk zzkVar = this.waiters;
        if (zzkVar != zzk.zza) {
            zzk zzkVar2 = new zzk();
            do {
                zza zzaVar = zzc;
                zzaVar.zzc(zzkVar2, zzkVar);
                if (zzaVar.zzg(this, zzkVar, zzkVar2)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            zzq(zzkVar2);
                            throw new InterruptedException();
                        }
                        obj = this.value;
                    } while (!((obj != null) & (!(obj instanceof zzf))));
                    return zzr(obj);
                }
                zzkVar = this.waiters;
            } while (zzkVar != zzk.zza);
        }
        Object obj3 = this.value;
        obj3.getClass();
        return zzr(obj3);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.value instanceof zzb;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return (!(r0 instanceof zzf)) & (this.value != null);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.")) {
            sb.append(getClass().getSimpleName());
        } else {
            sb.append(getClass().getName());
        }
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[status=");
        if (this.value instanceof zzb) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            zzl(sb);
        } else {
            zzm(sb);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.cast.zzoj
    @CheckForNull
    protected final Throwable zzh() {
        if (!(this instanceof zzh)) {
            return null;
        }
        Object obj = this.value;
        if (obj instanceof zzc) {
            return ((zzc) obj).zzb;
        }
        return null;
    }

    protected boolean zzi(Object obj) {
        if (!zzc.zzf(this, null, zzd)) {
            return false;
        }
        zzo(this);
        return true;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j);
        if (!Thread.interrupted()) {
            Object obj = this.value;
            boolean z = true;
            if ((obj != null) & (!(obj instanceof zzf))) {
                return zzr(obj);
            }
            long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
            if (nanos >= 1000) {
                zzk zzkVar = this.waiters;
                if (zzkVar != zzk.zza) {
                    zzk zzkVar2 = new zzk();
                    do {
                        zza zzaVar = zzc;
                        zzaVar.zzc(zzkVar2, zzkVar);
                        if (zzaVar.zzg(this, zzkVar, zzkVar2)) {
                            do {
                                LockSupport.parkNanos(this, Math.min(nanos, 2147483647999999999L));
                                if (Thread.interrupted()) {
                                    zzq(zzkVar2);
                                    throw new InterruptedException();
                                }
                                Object obj2 = this.value;
                                if (!((obj2 != null) & (!(obj2 instanceof zzf)))) {
                                    nanos = jNanoTime - System.nanoTime();
                                } else {
                                    return zzr(obj2);
                                }
                            } while (nanos >= 1000);
                            zzq(zzkVar2);
                        } else {
                            zzkVar = this.waiters;
                        }
                    } while (zzkVar != zzk.zza);
                }
                Object obj3 = this.value;
                obj3.getClass();
                return zzr(obj3);
            }
            while (nanos > 0) {
                Object obj4 = this.value;
                if (!((obj4 != null) & (!(obj4 instanceof zzf)))) {
                    if (!Thread.interrupted()) {
                        nanos = jNanoTime - System.nanoTime();
                    } else {
                        throw new InterruptedException();
                    }
                } else {
                    return zzr(obj4);
                }
            }
            String string = toString();
            String string2 = timeUnit.toString();
            Locale locale = Locale.ROOT;
            String lowerCase = string2.toLowerCase(locale);
            String lowerCase2 = timeUnit.toString().toLowerCase(locale);
            StringBuilder sb = new StringBuilder(String.valueOf(lowerCase2).length() + 28);
            sb.append("Waited ");
            sb.append(j);
            sb.append(" ");
            sb.append(lowerCase2);
            String string3 = sb.toString();
            if (nanos + 1000 < 0) {
                String strConcat = string3.concat(" (plus ");
                long j2 = -nanos;
                long jConvert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
                long nanos2 = j2 - timeUnit.toNanos(jConvert);
                if (jConvert != 0 && nanos2 <= 1000) {
                    z = false;
                }
                if (jConvert > 0) {
                    String strValueOf = String.valueOf(strConcat);
                    StringBuilder sb2 = new StringBuilder(strValueOf.length() + 21 + String.valueOf(lowerCase).length());
                    sb2.append(strValueOf);
                    sb2.append(jConvert);
                    sb2.append(" ");
                    sb2.append(lowerCase);
                    String string4 = sb2.toString();
                    if (z) {
                        string4 = string4.concat(",");
                    }
                    strConcat = String.valueOf(string4).concat(" ");
                }
                if (z) {
                    String strValueOf2 = String.valueOf(strConcat);
                    StringBuilder sb3 = new StringBuilder(strValueOf2.length() + 33);
                    sb3.append(strValueOf2);
                    sb3.append(nanos2);
                    sb3.append(" nanoseconds ");
                    strConcat = sb3.toString();
                }
                string3 = String.valueOf(strConcat).concat("delay)");
            }
            if (isDone()) {
                throw new TimeoutException(String.valueOf(string3).concat(" but future completed as timeout expired"));
            }
            StringBuilder sb4 = new StringBuilder(String.valueOf(string3).length() + 5 + string.length());
            sb4.append(string3);
            sb4.append(" for ");
            sb4.append(string);
            throw new TimeoutException(sb4.toString());
        }
        throw new InterruptedException();
    }
}
