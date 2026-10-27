package kotlin.jvm.internal;

/* JADX INFO: compiled from: FunctionReference.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends c implements h, w5.e {
    private final int arity;
    private final int flags;

    public i(int i7) {
        this(i7, c.NO_RECEIVER, null, null, null, 0);
    }

    @Override // kotlin.jvm.internal.c
    protected w5.b computeReflected() {
        return s.a(this);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof i) {
            i iVar = (i) obj;
            return getName().equals(iVar.getName()) && getSignature().equals(iVar.getSignature()) && this.flags == iVar.flags && this.arity == iVar.arity && l.a(getBoundReceiver(), iVar.getBoundReceiver()) && l.a(getOwner(), iVar.getOwner());
        }
        if (obj instanceof w5.e) {
            return obj.equals(compute());
        }
        return false;
    }

    @Override // kotlin.jvm.internal.h
    public int getArity() {
        return this.arity;
    }

    public int hashCode() {
        return (((getOwner() == null ? 0 : getOwner().hashCode() * 31) + getName().hashCode()) * 31) + getSignature().hashCode();
    }

    @Override // w5.e
    public boolean isExternal() {
        return getReflected().isExternal();
    }

    @Override // w5.e
    public boolean isInfix() {
        return getReflected().isInfix();
    }

    @Override // w5.e
    public boolean isInline() {
        return getReflected().isInline();
    }

    @Override // w5.e
    public boolean isOperator() {
        return getReflected().isOperator();
    }

    @Override // kotlin.jvm.internal.c, w5.b
    public boolean isSuspend() {
        return getReflected().isSuspend();
    }

    public String toString() {
        w5.b bVarCompute = compute();
        if (bVarCompute != this) {
            return bVarCompute.toString();
        }
        if ("<init>".equals(getName())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + getName() + " (Kotlin reflection is not available)";
    }

    public i(int i7, Object obj) {
        this(i7, obj, null, null, null, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.c
    public w5.e getReflected() {
        return (w5.e) super.getReflected();
    }

    public i(int i7, Object obj, Class cls, String str, String str2, int i8) {
        super(obj, cls, str, str2, (i8 & 1) == 1);
        this.arity = i7;
        this.flags = i8 >> 1;
    }
}
