package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* JADX INFO: compiled from: signatureEnhancement.kt */
/* JADX INFO: loaded from: classes7.dex */
/* synthetic */ class SignatureEnhancement$SignatureParts$enhance$containsFunctionN$3 extends FunctionReference implements Function1<UnwrappedType, Boolean> {
    public static final SignatureEnhancement$SignatureParts$enhance$containsFunctionN$3 INSTANCE = new SignatureEnhancement$SignatureParts$enhance$containsFunctionN$3();

    SignatureEnhancement$SignatureParts$enhance$containsFunctionN$3() {
        super(1);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "containsFunctionN";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(Intrinsics.Kotlin.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "enhance$containsFunctionN(Lorg/jetbrains/kotlin/types/UnwrappedType;)Z";
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(UnwrappedType p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return Boolean.valueOf(SignatureEnhancement.SignatureParts.enhance$containsFunctionN(p0));
    }
}
