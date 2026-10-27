package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import dagger.spi.shaded.androidx.room.compiler.processing.CommonProcessorDelegate;
import dagger.spi.shaded.androidx.room.compiler.processing.XBasicAnnotationProcessor;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnvConfig;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacBasicAnnotationProcessor.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bB+\b\u0007\u0012 \b\u0002\u0010\u001f\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e0\u001d\u0012\u0004\u0012\u00020\u00180\u001c¢\u0006\u0004\b\u001a\u0010 R\u001b\u0010\b\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\u0005\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0013\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0005\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0017\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006!"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacBasicAnnotationProcessor;", "Ljavax/annotation/processing/AbstractProcessor;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XBasicAnnotationProcessor;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "xEnv$delegate", "Lkotlin/Lazy;", "getXEnv", "()Landroidx/room/compiler/processing/javac/JavacProcessingEnv;", "xEnv", "", "", "steps$delegate", "getSteps", "()Ljava/util/List;", "steps", "Ldagger/spi/shaded/androidx/room/compiler/processing/CommonProcessorDelegate;", "commonDelegate$delegate", "getCommonDelegate", "()Landroidx/room/compiler/processing/CommonProcessorDelegate;", "commonDelegate", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv;", "getXProcessingEnv", "()Landroidx/room/compiler/processing/XProcessingEnv;", "xProcessingEnv", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnvConfig;", "config", "<init>", "(Landroidx/room/compiler/processing/XProcessingEnvConfig;)V", "Lkotlin/Function1;", "", "", "configureEnv", "(Lkotlin/jvm/functions/Function1;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public abstract class JavacBasicAnnotationProcessor extends AbstractProcessor implements XBasicAnnotationProcessor {

    /* JADX INFO: renamed from: commonDelegate$delegate, reason: from kotlin metadata */
    private final Lazy commonDelegate;

    /* JADX INFO: renamed from: steps$delegate, reason: from kotlin metadata */
    private final Lazy steps;

    /* JADX INFO: renamed from: xEnv$delegate, reason: from kotlin metadata */
    private final Lazy xEnv;

    @JvmOverloads
    public JavacBasicAnnotationProcessor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ JavacBasicAnnotationProcessor(Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function1<Map<String, ? extends String>, XProcessingEnvConfig>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacBasicAnnotationProcessor.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final XProcessingEnvConfig invoke2(Map<String, String> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return XProcessingEnvConfig.INSTANCE.getDEFAULT();
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ XProcessingEnvConfig invoke(Map<String, ? extends String> map) {
                return invoke2((Map<String, String>) map);
            }
        } : function1);
    }

    @JvmOverloads
    public JavacBasicAnnotationProcessor(final Function1<? super Map<String, String>, XProcessingEnvConfig> configureEnv) {
        Intrinsics.checkNotNullParameter(configureEnv, "configureEnv");
        this.xEnv = LazyKt__LazyJVMKt.lazy(new Function0<JavacProcessingEnv>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacBasicAnnotationProcessor$xEnv$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacProcessingEnv invoke() {
                ProcessingEnvironment processingEnv = ((AbstractProcessor) this.this$0).processingEnv;
                Intrinsics.checkNotNullExpressionValue(processingEnv, "processingEnv");
                Function1<Map<String, String>, XProcessingEnvConfig> function1 = configureEnv;
                Map<String, String> options = ((AbstractProcessor) this.this$0).processingEnv.getOptions();
                Intrinsics.checkNotNullExpressionValue(options, "processingEnv.options");
                return new JavacProcessingEnv(processingEnv, function1.invoke(options));
            }
        });
        this.steps = LazyKt__LazyJVMKt.lazy(new Function0<List<Object>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacBasicAnnotationProcessor$steps$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<Object> invoke() {
                return CollectionsKt___CollectionsKt.toList(this.this$0.processingSteps());
            }
        });
        this.commonDelegate = LazyKt__LazyJVMKt.lazy(new Function0<CommonProcessorDelegate>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacBasicAnnotationProcessor$commonDelegate$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CommonProcessorDelegate invoke() {
                return new CommonProcessorDelegate(this.this$0.getClass(), this.this$0.getXEnv(), this.this$0.getSteps());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JavacProcessingEnv getXEnv() {
        return (JavacProcessingEnv) this.xEnv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Object> getSteps() {
        return (List) this.steps.getValue();
    }
}
