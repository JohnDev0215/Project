package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* JADX INFO: compiled from: StubTypes.kt */
/* JADX INFO: loaded from: classes7.dex */
public abstract class AbstractStubType extends SimpleType {
    public static final Companion Companion = new Companion(null);
    private final boolean isMarkedNullable;
    private final MemberScope memberScope;
    private final TypeConstructor originalTypeVariable;

    public abstract AbstractStubType materialize(boolean z);

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    public AbstractStubType refine(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public SimpleType replaceAnnotations(Annotations newAnnotations) {
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        return this;
    }

    public AbstractStubType(TypeConstructor originalTypeVariable, boolean z) {
        Intrinsics.checkNotNullParameter(originalTypeVariable, "originalTypeVariable");
        this.originalTypeVariable = originalTypeVariable;
        this.isMarkedNullable = z;
        MemberScope memberScopeCreateErrorScope = ErrorUtils.createErrorScope(Intrinsics.stringPlus("Scope for stub type: ", originalTypeVariable));
        Intrinsics.checkNotNullExpressionValue(memberScopeCreateErrorScope, "createErrorScope(\"Scope …: $originalTypeVariable\")");
        this.memberScope = memberScopeCreateErrorScope;
    }

    public final TypeConstructor getOriginalTypeVariable() {
        return this.originalTypeVariable;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public List<TypeProjection> getArguments() {
        return CollectionsKt__CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return Annotations.Companion.getEMPTY();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public SimpleType makeNullableAsSpecified(boolean z) {
        return z == isMarkedNullable() ? this : materialize(z);
    }

    /* JADX INFO: compiled from: StubTypes.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
