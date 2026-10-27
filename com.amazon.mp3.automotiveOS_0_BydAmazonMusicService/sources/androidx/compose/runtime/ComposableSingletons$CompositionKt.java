package androidx.compose.runtime;

import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Composition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
public final class ComposableSingletons$CompositionKt {
    public static final ComposableSingletons$CompositionKt INSTANCE = new ComposableSingletons$CompositionKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f0lambda1 = ComposableLambdaKt.composableLambdaInstance(-985543095, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.runtime.ComposableSingletons$CompositionKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo4invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        @Composable
        public final void invoke(Composer composer, int i) {
            if (((i & 11) ^ 2) == 0 && composer.getSkipping()) {
                composer.skipToGroupEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f1lambda2 = ComposableLambdaKt.composableLambdaInstance(-985548681, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.runtime.ComposableSingletons$CompositionKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo4invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        @Composable
        public final void invoke(Composer composer, int i) {
            if (((i & 11) ^ 2) == 0 && composer.getSkipping()) {
                composer.skipToGroupEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$runtime_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7getLambda1$runtime_release() {
        return f0lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$runtime_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8getLambda2$runtime_release() {
        return f1lambda2;
    }
}
