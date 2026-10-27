package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.devtools.ksp.processing.Resolver;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSName;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeAlias;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.Nullability;
import com.google.devtools.ksp.symbol.Variance;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnvConfig;
import dagger.spi.shaded.androidx.room.compiler.processing.XType;
import dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.XTypeElementStore;
import dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspArrayType;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KspProcessingEnv.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B3\u0012\u0012\u0010F\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020E\u0012\u0006\u0010G\u001a\u00020=\u0012\u0006\u0010H\u001a\u00020=\u0012\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\bI\u0010JJ\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0006H\u0016J\u0016\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rJ\u0016\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013J\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0015J+\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u00182\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0019\"\u00020\u0006H\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001e\u001a\u00020\u001d8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u001a\u0010#\u001a\u00020\"8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0018\u0010(\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R \u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00040*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u001b\u00102\u001a\u00020-8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101R\u0014\u00106\u001a\u0002038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u00105R$\u0010<\u001a\u00020'2\u0006\u00107\u001a\u00020'8F@@X\u0086\u000e¢\u0006\f\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u0011\u0010@\u001a\u00020=8F¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0011\u0010D\u001a\u00020A8F¢\u0006\u0006\u001a\u0004\bB\u0010C¨\u0006K"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv;", "", "qName", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspTypeElement;", "findTypeElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "findType", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspArrayType;", "getArrayType", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "ksTypeParam", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "ksTypeArgument", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspType;", "wrap", "Lcom/google/devtools/ksp/symbol/KSType;", "ksType", "", "allowPrimitives", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "declaration", "wrapClassDeclaration", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeElement;", "", "types", "getDeclaredType", "(Landroidx/room/compiler/processing/XTypeElement;[Landroidx/room/compiler/processing/XType;)Landroidx/room/compiler/processing/ksp/KspType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnvConfig;", "config", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnvConfig;", "getConfig", "()Landroidx/room/compiler/processing/XProcessingEnvConfig;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv$Backend;", "backend", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv$Backend;", "getBackend", "()Landroidx/room/compiler/processing/XProcessingEnv$Backend;", "Lcom/google/devtools/ksp/processing/Resolver;", "_resolver", "Lcom/google/devtools/ksp/processing/Resolver;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/XTypeElementStore;", "typeElementStore", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/XTypeElementStore;", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspArrayType$Factory;", "arrayTypeFactory$delegate", "Lkotlin/Lazy;", "getArrayTypeFactory", "()Landroidx/room/compiler/processing/ksp/KspArrayType$Factory;", "arrayTypeFactory", "", "getJvmVersion", "()I", "jvmVersion", "value", "getResolver", "()Lcom/google/devtools/ksp/processing/Resolver;", "setResolver$room_compiler_processing", "(Lcom/google/devtools/ksp/processing/Resolver;)V", "resolver", "", "getCommonTypes", "()Landroidx/room/compiler/processing/ksp/KspProcessingEnv$CommonTypes;", "commonTypes", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspVoidType;", "getVoidType", "()Landroidx/room/compiler/processing/ksp/KspVoidType;", "voidType", "", "options", "codeGenerator", "logger", "<init>", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;Landroidx/room/compiler/processing/XProcessingEnvConfig;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class KspProcessingEnv implements XProcessingEnv {
    private Resolver _resolver;

    /* JADX INFO: renamed from: arrayTypeFactory$delegate, reason: from kotlin metadata */
    private final Lazy arrayTypeFactory;
    private final XProcessingEnv.Backend backend;
    private final XProcessingEnvConfig config;
    private final XTypeElementStore<KSClassDeclaration, KspTypeElement> typeElementStore;

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public int getJvmVersion() {
        return 8;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public XProcessingEnvConfig getConfig() {
        return this.config;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public XProcessingEnv.Backend getBackend() {
        return this.backend;
    }

    public final Resolver getResolver() {
        Resolver resolver = this._resolver;
        Intrinsics.checkNotNull(resolver);
        return resolver;
    }

    private final KspArrayType.Factory getArrayTypeFactory() {
        return (KspArrayType.Factory) this.arrayTypeFactory.getValue();
    }

    public final KspVoidType getVoidType() {
        return new KspVoidType(this, getResolver().getBuiltIns().getUnitType(), false, null);
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public KspTypeElement findTypeElement(String qName) {
        Intrinsics.checkNotNullParameter(qName, "qName");
        return (KspTypeElement) this.typeElementStore.get(qName);
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public XType findType(String qName) {
        Intrinsics.checkNotNullParameter(qName, "qName");
        KspTypeMapper kspTypeMapper = KspTypeMapper.INSTANCE;
        KSClassDeclaration kSClassDeclarationFindClass = ResolverExtKt.findClass(getResolver(), kspTypeMapper.swapWithKotlinType(qName));
        if (kSClassDeclarationFindClass == null) {
            return null;
        }
        return wrap(kSClassDeclarationFindClass.asType(CollectionsKt__CollectionsKt.emptyList()), kspTypeMapper.isJavaPrimitiveType(qName));
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public KspType getDeclaredType(XTypeElement type, XType... types) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(types, "types");
        if (!(type instanceof KspTypeElement)) {
            throw new IllegalStateException(("Unexpected type element type: " + type).toString());
        }
        ArrayList arrayList = new ArrayList(types.length);
        for (XType xType : types) {
            if (!(xType instanceof KspType)) {
                throw new IllegalStateException((xType + " is not an instance of KspType").toString());
            }
            arrayList.add(getResolver().getTypeArgument(KSTypeReferenceExtKt.createTypeReference(((KspType) xType).getKsType()), Variance.INVARIANT));
        }
        return wrap(((KspTypeElement) type).getDeclaration().asType(arrayList), false);
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public KspArrayType getArrayType(XType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (!(type instanceof KspType)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        return getArrayTypeFactory().createWithComponentType((KspType) type);
    }

    public final KspType wrap(KSTypeParameter ksTypeParam, KSTypeArgument ksTypeArgument) {
        Intrinsics.checkNotNullParameter(ksTypeParam, "ksTypeParam");
        Intrinsics.checkNotNullParameter(ksTypeArgument, "ksTypeArgument");
        KSTypeReference type = ksTypeArgument.getType();
        if (type != null && ksTypeArgument.getVariance() == Variance.INVARIANT) {
            return wrap(type.getResolved(), false);
        }
        return new KspTypeArgumentType(this, ksTypeParam, ksTypeArgument, null);
    }

    public final KspType wrap(KSType ksType, boolean allowPrimitives) {
        Intrinsics.checkNotNullParameter(ksType, "ksType");
        KSDeclaration declaration = ksType.getDeclaration();
        if (declaration instanceof KSTypeAlias) {
            KspType kspTypeWrap = wrap(((KSTypeAlias) declaration).getType().getResolved(), allowPrimitives && ksType.getNullability() == Nullability.NOT_NULL);
            return ksType.getNullability() == Nullability.NULLABLE ? kspTypeWrap.makeNullable() : kspTypeWrap;
        }
        KSName qualifiedName = ksType.getDeclaration().getQualifiedName();
        String strAsString = qualifiedName != null ? qualifiedName.asString() : null;
        if (declaration instanceof KSTypeParameter) {
            KSTypeParameter kSTypeParameter = (KSTypeParameter) declaration;
            return new KspTypeArgumentType(this, kSTypeParameter, getResolver().getTypeArgument(KSTypeReferenceExtKt.createTypeReference(ksType), kSTypeParameter.getVariance()), null);
        }
        if (allowPrimitives && strAsString != null && ksType.getNullability() == Nullability.NOT_NULL) {
            if (KspTypeMapper.INSTANCE.getPrimitiveJavaTypeName(strAsString) != null) {
                return new KspPrimitiveType(this, ksType, null);
            }
            if (Intrinsics.areEqual(strAsString, "kotlin.Unit")) {
                return getVoidType();
            }
        }
        KspArrayType kspArrayTypeCreateIfArray = getArrayTypeFactory().createIfArray(ksType);
        return kspArrayTypeCreateIfArray != null ? kspArrayTypeCreateIfArray : new DefaultKspType(this, ksType, null);
    }

    public final KspTypeElement wrapClassDeclaration(KSClassDeclaration declaration) {
        Intrinsics.checkNotNullParameter(declaration, "declaration");
        return (KspTypeElement) this.typeElementStore.get(declaration);
    }
}
