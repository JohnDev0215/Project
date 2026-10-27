package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.h;
import kotlin.reflect.l;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public abstract class MutablePropertyReference0 extends MutablePropertyReference implements kotlin.reflect.h {
    public MutablePropertyReference0() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected kotlin.reflect.b computeReflected() {
        s.c(this);
        return this;
    }

    /* JADX WARN: Failed to parse method signature: ()TR
    jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: ()TR at position 3 ('R'), unexpected: T
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
    	at jadx.core.dex.visitors.SignatureProcessor.parseMethodSignature(SignatureProcessor.java:188)
    	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:40)
     */
    public abstract /* synthetic */ Object get();

    @Override // kotlin.reflect.l
    @SinceKotlin(version = "1.1")
    public Object getDelegate() {
        return ((kotlin.reflect.h) getReflected()).getDelegate();
    }

    @Override // kotlin.jvm.b.a
    public Object invoke() {
        return get();
    }

    /* JADX WARN: Failed to parse method signature: (TR)V
    jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: (TR)V at position 2 ('R'), unexpected: T
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeMethodArgs(SignatureParser.java:336)
    	at jadx.core.dex.visitors.SignatureProcessor.parseMethodSignature(SignatureProcessor.java:187)
    	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:40)
     */
    public abstract /* synthetic */ void set(Object obj);

    @SinceKotlin(version = "1.1")
    public MutablePropertyReference0(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public l.a getGetter() {
        return ((kotlin.reflect.h) getReflected()).getGetter();
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference
    public h.a getSetter() {
        return ((kotlin.reflect.h) getReflected()).getSetter();
    }
}
