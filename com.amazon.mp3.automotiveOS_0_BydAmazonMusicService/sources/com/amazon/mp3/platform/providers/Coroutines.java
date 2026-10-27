package com.amazon.mp3.platform.providers;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: Coroutines.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001e\u0010\u001fR\u001b\u0010\u0007\u001a\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001f\u0010\n\u001a\n \t*\u0004\u0018\u00010\b0\b8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0012\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0004\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0017\u001a\u00020\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0004\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u001a\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0004\u001a\u0004\b\u0019\u0010\u0011R\u001b\u0010\u001d\u001a\u00020\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0004\u001a\u0004\b\u001c\u0010\u0016¨\u0006 "}, d2 = {"Lcom/amazon/mp3/platform/providers/Coroutines;", "", "Lkotlinx/coroutines/CompletableJob;", "parentJob$delegate", "Lkotlin/Lazy;", "getParentJob", "()Lkotlinx/coroutines/CompletableJob;", "parentJob", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "Lkotlinx/coroutines/CoroutineScope;", "ioScope$delegate", "getIoScope", "()Lkotlinx/coroutines/CoroutineScope;", "ioScope", "Lkotlin/coroutines/CoroutineContext;", "ioContext$delegate", "getIoContext", "()Lkotlin/coroutines/CoroutineContext;", "ioContext", "mainScope$delegate", "getMainScope", "mainScope", "mainContext$delegate", "getMainContext", "mainContext", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class Coroutines {
    public static final Coroutines INSTANCE = new Coroutines();

    /* JADX INFO: renamed from: parentJob$delegate, reason: from kotlin metadata */
    private static final Lazy parentJob = LazyKt__LazyJVMKt.lazy(new Function0<CompletableJob>() { // from class: com.amazon.mp3.platform.providers.Coroutines$parentJob$2
        @Override // kotlin.jvm.functions.Function0
        public final CompletableJob invoke() {
            return SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        }
    });
    private static final String TAG = Coroutines.class.getSimpleName();

    /* JADX INFO: renamed from: ioScope$delegate, reason: from kotlin metadata */
    private static final Lazy ioScope = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineScope>() { // from class: com.amazon.mp3.platform.providers.Coroutines$ioScope$2
        @Override // kotlin.jvm.functions.Function0
        public final CoroutineScope invoke() {
            return CoroutineScopeKt.plus(CoroutineScopeKt.plus(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), Coroutines.INSTANCE.getParentJob()), new Coroutines$ioScope$2$invoke$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE));
        }
    });

    /* JADX INFO: renamed from: ioContext$delegate, reason: from kotlin metadata */
    private static final Lazy ioContext = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineContext>() { // from class: com.amazon.mp3.platform.providers.Coroutines$ioContext$2
        @Override // kotlin.jvm.functions.Function0
        public final CoroutineContext invoke() {
            return Coroutines.INSTANCE.getIoScope().getCoroutineContext();
        }
    });

    /* JADX INFO: renamed from: mainScope$delegate, reason: from kotlin metadata */
    private static final Lazy mainScope = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineScope>() { // from class: com.amazon.mp3.platform.providers.Coroutines$mainScope$2
        @Override // kotlin.jvm.functions.Function0
        public final CoroutineScope invoke() {
            return CoroutineScopeKt.plus(CoroutineScopeKt.plus(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), Coroutines.INSTANCE.getParentJob()), new Coroutines$mainScope$2$invoke$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE));
        }
    });

    /* JADX INFO: renamed from: mainContext$delegate, reason: from kotlin metadata */
    private static final Lazy mainContext = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineContext>() { // from class: com.amazon.mp3.platform.providers.Coroutines$mainContext$2
        @Override // kotlin.jvm.functions.Function0
        public final CoroutineContext invoke() {
            return Coroutines.INSTANCE.getMainScope().getCoroutineContext();
        }
    });

    private Coroutines() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CompletableJob getParentJob() {
        return (CompletableJob) parentJob.getValue();
    }

    public final String getTAG() {
        return TAG;
    }

    public final CoroutineScope getIoScope() {
        return (CoroutineScope) ioScope.getValue();
    }

    public final CoroutineContext getIoContext() {
        return (CoroutineContext) ioContext.getValue();
    }

    public final CoroutineScope getMainScope() {
        return (CoroutineScope) mainScope.getValue();
    }
}
