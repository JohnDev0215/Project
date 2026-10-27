package androidx.compose.runtime;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CompositionLocal.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a<\u0010\u0000\u001a\u00020\u00012\u001a\u0010\b\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\n0\t\"\u0006\u0012\u0002\b\u00030\n2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0002\u0010\u000b\u001a0\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\r\"\u0004\b\u0000\u0010\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0005\u001a \u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\r\"\u0004\b\u0000\u0010\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0005¨\u0006\u0013"}, d2 = {"CompositionLocalProvider", "", "context", "Landroidx/compose/runtime/CompositionLocalContext;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/runtime/CompositionLocalContext;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "values", "", "Landroidx/compose/runtime/ProvidedValue;", "([Landroidx/compose/runtime/ProvidedValue;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "compositionLocalOf", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "T", "policy", "Landroidx/compose/runtime/SnapshotMutationPolicy;", "defaultFactory", "staticCompositionLocalOf", "runtime_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class CompositionLocalKt {
    public static /* synthetic */ ProvidableCompositionLocal compositionLocalOf$default(SnapshotMutationPolicy snapshotMutationPolicy, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            snapshotMutationPolicy = SnapshotStateKt.structuralEqualityPolicy();
        }
        return compositionLocalOf(snapshotMutationPolicy, function0);
    }

    public static final <T> ProvidableCompositionLocal<T> compositionLocalOf(SnapshotMutationPolicy<T> policy, Function0<? extends T> defaultFactory) {
        Intrinsics.checkNotNullParameter(policy, "policy");
        Intrinsics.checkNotNullParameter(defaultFactory, "defaultFactory");
        return new DynamicProvidableCompositionLocal(policy, defaultFactory);
    }

    public static final <T> ProvidableCompositionLocal<T> staticCompositionLocalOf(Function0<? extends T> defaultFactory) {
        Intrinsics.checkNotNullParameter(defaultFactory, "defaultFactory");
        return new StaticProvidableCompositionLocal(defaultFactory);
    }

    @Composable
    public static final void CompositionLocalProvider(final ProvidedValue<?>[] values, final Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i) {
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1460639761);
        composerStartRestartGroup.startProviders(values);
        content.mo4invoke(composerStartRestartGroup, Integer.valueOf((i >> 3) & 14));
        composerStartRestartGroup.endProviders();
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.runtime.CompositionLocalKt.CompositionLocalProvider.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ Unit mo4invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i2) {
                ProvidedValue<?>[] providedValueArr = values;
                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) Arrays.copyOf(providedValueArr, providedValueArr.length), content, composer2, i | 1);
            }
        });
    }

    @Composable
    public static final void CompositionLocalProvider(final CompositionLocalContext context, final Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1460639014);
        int i2 = (i & 14) == 0 ? (composerStartRestartGroup.changed(context) ? 4 : 2) | i : i;
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(content) ? 32 : 16;
        }
        if (((i2 & 91) ^ 18) != 0 || !composerStartRestartGroup.getSkipping()) {
            PersistentMap<CompositionLocal<Object>, State<Object>> compositionLocals$runtime_release = context.getCompositionLocals$runtime_release();
            ArrayList arrayList = new ArrayList(compositionLocals$runtime_release.size());
            for (Map.Entry<CompositionLocal<Object>, State<Object>> entry : compositionLocals$runtime_release.entrySet()) {
                arrayList.add(((ProvidableCompositionLocal) entry.getKey()).provides(entry.getValue().getValue()));
            }
            Object[] array = arrayList.toArray(new ProvidedValue[0]);
            if (array == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
            ProvidedValue[] providedValueArr = (ProvidedValue[]) array;
            CompositionLocalProvider((ProvidedValue<?>[]) Arrays.copyOf(providedValueArr, providedValueArr.length), content, composerStartRestartGroup, (i2 & 112) | 8);
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.runtime.CompositionLocalKt.CompositionLocalProvider.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ Unit mo4invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i3) {
                CompositionLocalKt.CompositionLocalProvider(context, content, composer2, i | 1);
            }
        });
    }
}
