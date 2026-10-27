package kotlinx.coroutines;

/* JADX INFO: compiled from: AbstractCoroutine.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> extends s1 implements k5.d<T>, h0 {
    private final k5.g context;
    protected final k5.g parentContext;

    public /* synthetic */ a(k5.g gVar, boolean z6, int i7, kotlin.jvm.internal.g gVar2) {
        this(gVar, (i7 & 2) != 0 ? true : z6);
    }

    public static /* synthetic */ void getContext$annotations() {
    }

    protected void afterResume(Object obj) {
        afterCompletion(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.s1
    public String cancellationExceptionMessage() {
        return m0.a(this) + " was cancelled";
    }

    @Override // k5.d
    public final k5.g getContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.h0
    public k5.g getCoroutineContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.s1
    public final void handleOnCompletionException$kotlinx_coroutines_core(Throwable th) {
        g0.a(this.context, th);
    }

    public final void initParentJob$kotlinx_coroutines_core() {
        initParentJobInternal$kotlinx_coroutines_core((l1) this.parentContext.get(l1.f7703d));
    }

    @Override // kotlinx.coroutines.s1, kotlinx.coroutines.l1
    public boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.s1
    public String nameString$kotlinx_coroutines_core() {
        String strB = d0.b(this.context);
        if (strB == null) {
            return super.nameString$kotlinx_coroutines_core();
        }
        return '\"' + strB + "\":" + super.nameString$kotlinx_coroutines_core();
    }

    protected void onCancelled(Throwable th, boolean z6) {
    }

    protected void onCompleted(T t6) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.s1
    protected final void onCompletionInternal(Object obj) {
        if (!(obj instanceof y)) {
            onCompleted(obj);
        } else {
            y yVar = (y) obj;
            onCancelled(yVar.f7851a, yVar.a());
        }
    }

    protected void onStart() {
    }

    @Override // kotlinx.coroutines.s1
    public final void onStartInternal$kotlinx_coroutines_core() {
        onStart();
    }

    @Override // k5.d
    public final void resumeWith(Object obj) {
        Object objMakeCompletingOnce$kotlinx_coroutines_core = makeCompletingOnce$kotlinx_coroutines_core(c0.d(obj, null, 1, null));
        if (objMakeCompletingOnce$kotlinx_coroutines_core == t1.f7827b) {
            return;
        }
        afterResume(objMakeCompletingOnce$kotlinx_coroutines_core);
    }

    public final void start(k0 k0Var, r5.l<? super k5.d<? super T>, ? extends Object> lVar) {
        initParentJob$kotlinx_coroutines_core();
        k0Var.invoke(lVar, this);
    }

    public a(k5.g gVar, boolean z6) {
        super(z6);
        this.parentContext = gVar;
        this.context = gVar.plus(this);
    }

    public final <R> void start(k0 k0Var, R r7, r5.p<? super R, ? super k5.d<? super T>, ? extends Object> pVar) {
        initParentJob$kotlinx_coroutines_core();
        k0Var.invoke(pVar, r7, this);
    }
}
