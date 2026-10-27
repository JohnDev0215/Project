package dagger.internal.codegen.base;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.squareup.javapoet.ClassName;
import dagger.internal.codegen.javapoet.TypeNames;
import dagger.internal.codegen.xprocessing.XProcessingEnvs;
import dagger.spi.model.RequestKind;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.XType;

/* JADX INFO: loaded from: classes7.dex */
public final class RequestKinds {
    private static final ImmutableMap<RequestKind, ClassName> FRAMEWORK_CLASSES = ImmutableMap.of(RequestKind.PROVIDER, TypeNames.PROVIDER, RequestKind.LAZY, TypeNames.LAZY, RequestKind.PRODUCER, TypeNames.PRODUCER, RequestKind.PRODUCED, TypeNames.PRODUCED);

    /* JADX INFO: renamed from: dagger.internal.codegen.base.RequestKinds$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$dagger$spi$model$RequestKind;

        static {
            int[] iArr = new int[RequestKind.values().length];
            $SwitchMap$dagger$spi$model$RequestKind = iArr;
            try {
                iArr[RequestKind.INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.PROVIDER_OF_LAZY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.FUTURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.PROVIDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.LAZY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.PRODUCER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.PRODUCED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.MEMBERS_INJECTION.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static XType requestType(RequestKind requestKind, XType type, XProcessingEnv processingEnv) {
        int i = AnonymousClass1.$SwitchMap$dagger$spi$model$RequestKind[requestKind.ordinal()];
        if (i == 1) {
            return type;
        }
        if (i == 2) {
            return XProcessingEnvs.wrapType(TypeNames.PROVIDER, requestType(RequestKind.LAZY, type, processingEnv), processingEnv);
        }
        if (i == 3) {
            return XProcessingEnvs.wrapType(TypeNames.LISTENABLE_FUTURE, type, processingEnv);
        }
        return XProcessingEnvs.wrapType(frameworkClassName(requestKind), type, processingEnv);
    }

    public static ClassName frameworkClassName(RequestKind requestKind) {
        ImmutableMap<RequestKind, ClassName> immutableMap = FRAMEWORK_CLASSES;
        Preconditions.checkArgument(immutableMap.containsKey(requestKind), "no framework class for %s", requestKind);
        return immutableMap.get(requestKind);
    }

    private RequestKinds() {
    }
}
