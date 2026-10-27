package kotlin.jvm.internal;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: CallableReference.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c implements w5.b, Serializable {
    public static final Object NO_RECEIVER = a.f7494e;
    private final boolean isTopLevel;
    private final String name;
    private final Class owner;
    protected final Object receiver;
    private transient w5.b reflected;
    private final String signature;

    /* JADX INFO: compiled from: CallableReference.java */
    private static class a implements Serializable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final a f7494e = new a();

        private a() {
        }

        private Object readResolve() {
            return f7494e;
        }
    }

    public c() {
        this(NO_RECEIVER);
    }

    @Override // w5.b
    public Object call(Object... objArr) {
        return getReflected().call(objArr);
    }

    @Override // w5.b
    public Object callBy(Map map) {
        return getReflected().callBy(map);
    }

    public w5.b compute() {
        w5.b bVar = this.reflected;
        if (bVar != null) {
            return bVar;
        }
        w5.b bVarComputeReflected = computeReflected();
        this.reflected = bVarComputeReflected;
        return bVarComputeReflected;
    }

    protected abstract w5.b computeReflected();

    @Override // w5.a
    public List<Annotation> getAnnotations() {
        return getReflected().getAnnotations();
    }

    public Object getBoundReceiver() {
        return this.receiver;
    }

    public String getName() {
        return this.name;
    }

    public w5.d getOwner() {
        Class cls = this.owner;
        if (cls == null) {
            return null;
        }
        return this.isTopLevel ? s.c(cls) : s.b(cls);
    }

    @Override // w5.b
    public List<Object> getParameters() {
        return getReflected().getParameters();
    }

    protected w5.b getReflected() {
        w5.b bVarCompute = compute();
        if (bVarCompute != this) {
            return bVarCompute;
        }
        throw new q5.b();
    }

    @Override // w5.b
    public w5.f getReturnType() {
        return getReflected().getReturnType();
    }

    public String getSignature() {
        return this.signature;
    }

    @Override // w5.b
    public List<Object> getTypeParameters() {
        return getReflected().getTypeParameters();
    }

    @Override // w5.b
    public w5.g getVisibility() {
        return getReflected().getVisibility();
    }

    @Override // w5.b
    public boolean isAbstract() {
        return getReflected().isAbstract();
    }

    @Override // w5.b
    public boolean isFinal() {
        return getReflected().isFinal();
    }

    @Override // w5.b
    public boolean isOpen() {
        return getReflected().isOpen();
    }

    @Override // w5.b
    public boolean isSuspend() {
        return getReflected().isSuspend();
    }

    protected c(Object obj) {
        this(obj, null, null, null, false);
    }

    protected c(Object obj, Class cls, String str, String str2, boolean z6) {
        this.receiver = obj;
        this.owner = cls;
        this.name = str;
        this.signature = str2;
        this.isTopLevel = z6;
    }
}
