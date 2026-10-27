package z8;

import F8.AbstractC0287w;
import Q7.InterfaceC0649b;
import T7.AbstractC0795n;

/* JADX INFO: renamed from: z8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2794b extends A9.b {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AbstractC0795n f24420n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public C2794b(InterfaceC0649b interfaceC0649b, AbstractC0287w abstractC0287w) {
        super(abstractC0287w);
        if (abstractC0287w == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "receiverType", "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/ExtensionReceiver", "<init>"));
        }
        this.f24420n = (AbstractC0795n) interfaceC0649b;
    }

    @Override // A9.b
    public final String toString() {
        return b() + ": Ext {" + this.f24420n + "}";
    }
}
