package dagger.internal.codegen.binding;

import dagger.internal.codegen.base.ElementFormatter;
import dagger.internal.codegen.base.Formatter;
import dagger.internal.codegen.base.RequestKinds;
import dagger.internal.codegen.xprocessing.XTypes;
import dagger.spi.model.DaggerAnnotation;
import dagger.spi.model.DependencyRequest;
import dagger.spi.model.RequestKind;
import dagger.spi.shaded.androidx.room.compiler.processing.XElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XElementKt;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv;
import java.util.Optional;
import java.util.function.Function;

/* JADX INFO: loaded from: classes7.dex */
public final class DependencyRequestFormatter extends Formatter<DependencyRequest> {
    private final XProcessingEnv processingEnv;

    DependencyRequestFormatter(XProcessingEnv processingEnv) {
        this.processingEnv = processingEnv;
    }

    @Override // dagger.internal.codegen.base.Formatter
    public String format(DependencyRequest request) {
        if (!request.requestElement().isPresent()) {
            return "";
        }
        XElement xElementXprocessing = request.requestElement().get().xprocessing();
        if (XElementKt.isMethod(xElementXprocessing)) {
            return "    " + request.key() + " is " + componentMethodRequestVerb(request) + " at\n        " + ElementFormatter.elementToString(xElementXprocessing);
        }
        if (XElementKt.isVariableElement(xElementXprocessing)) {
            return "    " + formatQualifier(request.key().qualifier()) + XTypes.toStableString(RequestKinds.requestType(request.kind(), request.key().type().xprocessing(), this.processingEnv)) + " is injected at\n        " + ElementFormatter.elementToString(xElementXprocessing);
        }
        if (XElementKt.isTypeElement(xElementXprocessing)) {
            return "";
        }
        throw new IllegalStateException("Invalid request element " + xElementXprocessing);
    }

    private String formatQualifier(Optional<DaggerAnnotation> maybeQualifier) {
        return (String) maybeQualifier.map(new Function() { // from class: dagger.internal.codegen.binding.DependencyRequestFormatter$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DependencyRequestFormatter.lambda$formatQualifier$0((DaggerAnnotation) obj);
            }
        }).orElse("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$formatQualifier$0(DaggerAnnotation daggerAnnotation) {
        return daggerAnnotation + " ";
    }

    /* JADX INFO: renamed from: dagger.internal.codegen.binding.DependencyRequestFormatter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$dagger$spi$model$RequestKind;

        static {
            int[] iArr = new int[RequestKind.values().length];
            $SwitchMap$dagger$spi$model$RequestKind = iArr;
            try {
                iArr[RequestKind.FUTURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.PRODUCER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.INSTANCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.LAZY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.PROVIDER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.PROVIDER_OF_LAZY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.MEMBERS_INJECTION.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.PRODUCED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private String componentMethodRequestVerb(DependencyRequest request) {
        switch (AnonymousClass1.$SwitchMap$dagger$spi$model$RequestKind[request.kind().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return "requested";
            case 7:
                return "injected";
            default:
                throw new AssertionError("illegal request kind for method: " + request);
        }
    }
}
