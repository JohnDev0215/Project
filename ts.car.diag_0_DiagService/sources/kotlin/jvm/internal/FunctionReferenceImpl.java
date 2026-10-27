package kotlin.jvm.internal;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class FunctionReferenceImpl extends FunctionReference {
    private final String name;
    private final kotlin.reflect.e owner;
    private final String signature;

    public FunctionReferenceImpl(int i, kotlin.reflect.e eVar, String str, String str2) {
        super(i);
        this.owner = eVar;
        this.name = str;
        this.signature = str2;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getName() {
        return this.name;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public kotlin.reflect.e getOwner() {
        return this.owner;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return this.signature;
    }
}
