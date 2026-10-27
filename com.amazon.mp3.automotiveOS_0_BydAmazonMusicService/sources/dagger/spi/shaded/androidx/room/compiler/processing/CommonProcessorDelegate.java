package dagger.spi.shaded.androidx.room.compiler.processing;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: XBasicAnnotationProcessor.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\"\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B)\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\b¢\u0006\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR&\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/CommonProcessorDelegate;", "", "Ljava/lang/Class;", "processorClass", "Ljava/lang/Class;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv;", "", "steps", "Ljava/util/List;", "", "", "deferredElementNames", "Ljava/util/Set;", "", "", "elementsDeferredBySteps", "Ljava/util/Map;", "<init>", "(Ljava/lang/Class;Landroidx/room/compiler/processing/XProcessingEnv;Ljava/util/List;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class CommonProcessorDelegate {
    private final Set<String> deferredElementNames;
    private final Map<Object, Set<String>> elementsDeferredBySteps;
    private final XProcessingEnv env;
    private final Class<?> processorClass;
    private final List<Object> steps;

    public CommonProcessorDelegate(Class<?> processorClass, XProcessingEnv env, List<Object> steps) {
        Intrinsics.checkNotNullParameter(processorClass, "processorClass");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(steps, "steps");
        this.processorClass = processorClass;
        this.env = env;
        this.steps = steps;
        this.deferredElementNames = new LinkedHashSet();
        this.elementsDeferredBySteps = new LinkedHashMap();
    }
}
