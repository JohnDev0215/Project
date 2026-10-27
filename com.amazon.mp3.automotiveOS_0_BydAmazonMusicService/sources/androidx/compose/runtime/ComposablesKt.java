package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Composables.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u001ab\u0010\u0012\u001a\u00020\u0013\"\b\b\u0000\u0010\u0014*\u00020\u0015\"\u000e\b\u0001\u0010\u0016\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00172\u000e\b\b\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00192\"\u0010\u001a\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u001c\u0012\u0004\u0012\u00020\u00130\u001b¢\u0006\u0002\b\u001d¢\u0006\u0002\b\u001eH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001f\u001aq\u0010\u0012\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0014\"\u000e\b\u0001\u0010\u0016\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00172\u000e\b\b\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00192\"\u0010\u001a\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u001c\u0012\u0004\u0012\u00020\u00130\u001b¢\u0006\u0002\b\u001d¢\u0006\u0002\b\u001e2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\u00130\u0019¢\u0006\u0002\b!H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\"\u001a\u0097\u0001\u0010\u0012\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0014\"\u000e\b\u0001\u0010\u0016\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00172\u000e\b\b\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00192\"\u0010\u001a\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u001c\u0012\u0004\u0012\u00020\u00130\u001b¢\u0006\u0002\b\u001d¢\u0006\u0002\b\u001e2$\b\b\u0010#\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140$\u0012\u0004\u0012\u00020\u00130\u001b¢\u0006\u0002\b!¢\u0006\u0002\b\u001e2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\u00130\u0019¢\u0006\u0002\b!H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010%\u001ab\u0010&\u001a\u00020\u0013\"\b\b\u0000\u0010\u0014*\u00020\u0015\"\u000e\b\u0001\u0010\u0016\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00172\u000e\b\b\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00192\"\u0010\u001a\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u001c\u0012\u0004\u0012\u00020\u00130\u001b¢\u0006\u0002\b\u001d¢\u0006\u0002\b\u001eH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001f\u001aq\u0010&\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0014\"\u000e\b\u0001\u0010\u0016\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00172\u000e\b\b\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00192\"\u0010\u001a\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u001c\u0012\u0004\u0012\u00020\u00130\u001b¢\u0006\u0002\b\u001d¢\u0006\u0002\b\u001e2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\u00130\u0019¢\u0006\u0002\b!H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\"\u001a\u0097\u0001\u0010&\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0014\"\u000e\b\u0001\u0010\u0016\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00172\u000e\b\b\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00192\"\u0010\u001a\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u001c\u0012\u0004\u0012\u00020\u00130\u001b¢\u0006\u0002\b\u001d¢\u0006\u0002\b\u001e2$\b\b\u0010#\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140$\u0012\u0004\u0012\u00020\u00130\u001b¢\u0006\u0002\b!¢\u0006\u0002\b\u001e2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\u00130\u0019¢\u0006\u0002\b!H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010%\u001a.\u0010'\u001a\u00020\u00132\b\u0010(\u001a\u0004\u0018\u00010\u00152\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\u00130\u0019¢\u0006\u0002\b!H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010)\u001a\b\u0010*\u001a\u00020\u0013H\u0001\u001aB\u0010(\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u00142\u0016\u0010+\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00150,\"\u0004\u0018\u00010\u00152\u0011\u0010-\u001a\r\u0012\u0004\u0012\u0002H\u00140\u0019¢\u0006\u0002\b!H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010.\u001a*\u0010/\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u00142\u0011\u00100\u001a\r\u0012\u0004\u0012\u0002H\u00140\u0019¢\u0006\u0002\b\u001dH\u0087\bø\u0001\u0001¢\u0006\u0002\u00101\u001a4\u0010/\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u00142\b\u00102\u001a\u0004\u0018\u00010\u00152\u0011\u00100\u001a\r\u0012\u0004\u0012\u0002H\u00140\u0019¢\u0006\u0002\b\u001dH\u0087\bø\u0001\u0001¢\u0006\u0002\u00103\u001a>\u0010/\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u00142\b\u00102\u001a\u0004\u0018\u00010\u00152\b\u00104\u001a\u0004\u0018\u00010\u00152\u0011\u00100\u001a\r\u0012\u0004\u0012\u0002H\u00140\u0019¢\u0006\u0002\b\u001dH\u0087\bø\u0001\u0001¢\u0006\u0002\u00105\u001aH\u0010/\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u00142\b\u00102\u001a\u0004\u0018\u00010\u00152\b\u00104\u001a\u0004\u0018\u00010\u00152\b\u00106\u001a\u0004\u0018\u00010\u00152\u0011\u00100\u001a\r\u0012\u0004\u0012\u0002H\u00140\u0019¢\u0006\u0002\b\u001dH\u0087\bø\u0001\u0001¢\u0006\u0002\u00107\u001aB\u0010/\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u00142\u0016\u0010+\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00150,\"\u0004\u0018\u00010\u00152\u0011\u00100\u001a\r\u0012\u0004\u0012\u0002H\u00140\u0019¢\u0006\u0002\b\u001dH\u0087\bø\u0001\u0001¢\u0006\u0002\u00108\u001a\r\u00109\u001a\u00020:H\u0007¢\u0006\u0002\u0010;\"\u0011\u0010\u0000\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\"\u0017\u0010\b\u001a\u00020\t8G¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0011\u0010\u000e\u001a\u00020\u000f8G¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u0006<"}, d2 = {"currentComposer", "Landroidx/compose/runtime/Composer;", "getCurrentComposer", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/Composer;", "currentCompositeKeyHash", "", "getCurrentCompositeKeyHash", "(Landroidx/compose/runtime/Composer;I)I", "currentCompositionLocalContext", "Landroidx/compose/runtime/CompositionLocalContext;", "getCurrentCompositionLocalContext$annotations", "()V", "getCurrentCompositionLocalContext", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/CompositionLocalContext;", "currentRecomposeScope", "Landroidx/compose/runtime/RecomposeScope;", "getCurrentRecomposeScope", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/RecomposeScope;", "ComposeNode", "", "T", "", "E", "Landroidx/compose/runtime/Applier;", "factory", "Lkotlin/Function0;", "update", "Lkotlin/Function1;", "Landroidx/compose/runtime/Updater;", "Landroidx/compose/runtime/DisallowComposableCalls;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "skippableUpdate", "Landroidx/compose/runtime/SkippableUpdater;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "ReusableComposeNode", "ReusableContent", "key", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "invalidApplier", "keys", "", "block", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "remember", "calculation", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "key1", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "key2", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "key3", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "rememberCompositionContext", "Landroidx/compose/runtime/CompositionContext;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/CompositionContext;", "runtime_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ComposablesKt {
    public static /* synthetic */ void getCurrentCompositionLocalContext$annotations() {
    }

    @Composable
    public static final <T> T remember(Object obj, Function0<? extends T> calculation, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        composer.startReplaceableGroup(-3686930);
        boolean zChanged = composer.changed(obj);
        T tInvoke = (T) composer.rememberedValue();
        if (zChanged || tInvoke == Composer.INSTANCE.getEmpty()) {
            tInvoke = calculation.invoke();
            composer.updateRememberedValue(tInvoke);
        }
        composer.endReplaceableGroup();
        return tInvoke;
    }

    @Composable
    public static final <T> T remember(Object obj, Object obj2, Function0<? extends T> calculation, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        composer.startReplaceableGroup(-3686552);
        boolean zChanged = composer.changed(obj) | composer.changed(obj2);
        T tInvoke = (T) composer.rememberedValue();
        if (zChanged || tInvoke == Composer.INSTANCE.getEmpty()) {
            tInvoke = calculation.invoke();
            composer.updateRememberedValue(tInvoke);
        }
        composer.endReplaceableGroup();
        return tInvoke;
    }

    @Composable
    public static final <T> T remember(Object obj, Object obj2, Object obj3, Function0<? extends T> calculation, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        composer.startReplaceableGroup(-3686095);
        boolean zChanged = composer.changed(obj) | composer.changed(obj2) | composer.changed(obj3);
        T tInvoke = (T) composer.rememberedValue();
        if (zChanged || tInvoke == Composer.INSTANCE.getEmpty()) {
            tInvoke = calculation.invoke();
            composer.updateRememberedValue(tInvoke);
        }
        composer.endReplaceableGroup();
        return tInvoke;
    }

    @Composable
    public static final <T> T remember(Object[] keys, Function0<? extends T> calculation, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        composer.startReplaceableGroup(-3685570);
        int length = keys.length;
        int i2 = 0;
        boolean zChanged = false;
        while (i2 < length) {
            Object obj = keys[i2];
            i2++;
            zChanged |= composer.changed(obj);
        }
        T tInvoke = (T) composer.rememberedValue();
        if (zChanged || tInvoke == Composer.INSTANCE.getEmpty()) {
            tInvoke = calculation.invoke();
            composer.updateRememberedValue(tInvoke);
        }
        composer.endReplaceableGroup();
        return tInvoke;
    }

    @Composable
    public static final <T> T key(Object[] keys, Function2<? super Composer, ? super Integer, ? extends T> block, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        Intrinsics.checkNotNullParameter(block, "block");
        composer.startReplaceableGroup(-1542330587);
        T tMo4invoke = block.mo4invoke(composer, Integer.valueOf((i >> 3) & 14));
        composer.endReplaceableGroup();
        return tMo4invoke;
    }

    @Composable
    public static final void ReusableContent(Object obj, Function2<? super Composer, ? super Integer, Unit> content, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(content, "content");
        composer.startReplaceableGroup(-1530021272);
        composer.startReusableGroup(ComposerKt.reuseKey, obj);
        content.mo4invoke(composer, Integer.valueOf((i >> 3) & 14));
        composer.endReusableGroup();
        composer.endReplaceableGroup();
    }

    @Composable
    @ReadOnlyComposable
    @JvmName(name = "getCurrentComposer")
    public static final Composer getCurrentComposer(Composer composer, int i) {
        throw new NotImplementedError("Implemented as an intrinsic");
    }

    @Composable
    @ReadOnlyComposable
    @JvmName(name = "getCurrentRecomposeScope")
    public static final RecomposeScope getCurrentRecomposeScope(Composer composer, int i) {
        RecomposeScope recomposeScope = composer.getRecomposeScope();
        if (recomposeScope == null) {
            throw new IllegalStateException("no recompose scope found".toString());
        }
        composer.recordUsed(recomposeScope);
        return recomposeScope;
    }

    @Composable
    @JvmName(name = "getCurrentCompositionLocalContext")
    public static final CompositionLocalContext getCurrentCompositionLocalContext(Composer composer, int i) {
        composer.startReplaceableGroup(550242516);
        CompositionLocalContext compositionLocalContext = new CompositionLocalContext(composer.buildContext().getCompositionLocalScope$runtime_release());
        composer.endReplaceableGroup();
        return compositionLocalContext;
    }

    @Composable
    @JvmName(name = "getCurrentCompositeKeyHash")
    public static final int getCurrentCompositeKeyHash(Composer composer, int i) {
        return composer.getCompoundKeyHash();
    }

    @Composable
    public static final /* synthetic */ <T, E extends Applier<?>> void ComposeNode(final Function0<? extends T> factory, Function1<? super Updater<T>, Unit> update, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(update, "update");
        composer.startReplaceableGroup(-2103250935);
        Applier<?> applier = composer.getApplier();
        Intrinsics.reifiedOperationMarker(3, "E");
        if (!(applier instanceof Applier)) {
            invalidApplier();
        }
        composer.startNode();
        if (composer.getInserting()) {
            composer.createNode(new Function0<T>() { // from class: androidx.compose.runtime.ComposablesKt.ComposeNode.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final T invoke() {
                    return factory.invoke();
                }
            });
        } else {
            composer.useNode();
        }
        update.invoke(Updater.m18boximpl(Updater.m19constructorimpl(composer)));
        composer.endNode();
        composer.endReplaceableGroup();
    }

    @Composable
    public static final /* synthetic */ <T, E extends Applier<?>> void ReusableComposeNode(final Function0<? extends T> factory, Function1<? super Updater<T>, Unit> update, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(update, "update");
        composer.startReplaceableGroup(1546164872);
        Applier<?> applier = composer.getApplier();
        Intrinsics.reifiedOperationMarker(3, "E");
        if (!(applier instanceof Applier)) {
            invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(new Function0<T>() { // from class: androidx.compose.runtime.ComposablesKt.ReusableComposeNode.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final T invoke() {
                    return factory.invoke();
                }
            });
        } else {
            composer.useNode();
        }
        composer.disableReusing();
        update.invoke(Updater.m18boximpl(Updater.m19constructorimpl(composer)));
        composer.enableReusing();
        composer.endNode();
        composer.endReplaceableGroup();
    }

    @Composable
    public static final /* synthetic */ <T, E extends Applier<?>> void ComposeNode(Function0<? extends T> factory, Function1<? super Updater<T>, Unit> update, Function2<? super Composer, ? super Integer, Unit> content, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(content, "content");
        composer.startReplaceableGroup(-2103248186);
        Applier<?> applier = composer.getApplier();
        Intrinsics.reifiedOperationMarker(3, "E");
        if (!(applier instanceof Applier)) {
            invalidApplier();
        }
        composer.startNode();
        if (composer.getInserting()) {
            composer.createNode(factory);
        } else {
            composer.useNode();
        }
        update.invoke(Updater.m18boximpl(Updater.m19constructorimpl(composer)));
        content.mo4invoke(composer, Integer.valueOf((i >> 6) & 14));
        composer.endNode();
        composer.endReplaceableGroup();
    }

    @Composable
    public static final /* synthetic */ <T, E extends Applier<?>> void ReusableComposeNode(Function0<? extends T> factory, Function1<? super Updater<T>, Unit> update, Function2<? super Composer, ? super Integer, Unit> content, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(content, "content");
        composer.startReplaceableGroup(1546167803);
        Applier<?> applier = composer.getApplier();
        Intrinsics.reifiedOperationMarker(3, "E");
        if (!(applier instanceof Applier)) {
            invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(factory);
        } else {
            composer.useNode();
        }
        composer.disableReusing();
        update.invoke(Updater.m18boximpl(Updater.m19constructorimpl(composer)));
        composer.enableReusing();
        content.mo4invoke(composer, Integer.valueOf((i >> 6) & 14));
        composer.endNode();
        composer.endReplaceableGroup();
    }

    @Composable
    public static final /* synthetic */ <T, E extends Applier<?>> void ComposeNode(Function0<? extends T> factory, Function1<? super Updater<T>, Unit> update, Function3<? super SkippableUpdater<T>, ? super Composer, ? super Integer, Unit> skippableUpdate, Function2<? super Composer, ? super Integer, Unit> content, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(skippableUpdate, "skippableUpdate");
        Intrinsics.checkNotNullParameter(content, "content");
        Applier<?> applier = composer.getApplier();
        Intrinsics.reifiedOperationMarker(3, "E");
        if (!(applier instanceof Applier)) {
            invalidApplier();
        }
        composer.startNode();
        if (composer.getInserting()) {
            composer.createNode(factory);
        } else {
            composer.useNode();
        }
        update.invoke(Updater.m18boximpl(Updater.m19constructorimpl(composer)));
        skippableUpdate.invoke(SkippableUpdater.m10boximpl(SkippableUpdater.m11constructorimpl(composer)), composer, Integer.valueOf((i >> 3) & 112));
        composer.startReplaceableGroup(2058660585);
        content.mo4invoke(composer, Integer.valueOf((i >> 9) & 14));
        composer.endReplaceableGroup();
        composer.endNode();
    }

    @Composable
    public static final /* synthetic */ <T, E extends Applier<?>> void ReusableComposeNode(Function0<? extends T> factory, Function1<? super Updater<T>, Unit> update, Function3<? super SkippableUpdater<T>, ? super Composer, ? super Integer, Unit> skippableUpdate, Function2<? super Composer, ? super Integer, Unit> content, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(skippableUpdate, "skippableUpdate");
        Intrinsics.checkNotNullParameter(content, "content");
        Applier<?> applier = composer.getApplier();
        Intrinsics.reifiedOperationMarker(3, "E");
        if (!(applier instanceof Applier)) {
            invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(factory);
        } else {
            composer.useNode();
        }
        composer.disableReusing();
        update.invoke(Updater.m18boximpl(Updater.m19constructorimpl(composer)));
        composer.enableReusing();
        skippableUpdate.invoke(SkippableUpdater.m10boximpl(SkippableUpdater.m11constructorimpl(composer)), composer, Integer.valueOf((i >> 3) & 112));
        composer.startReplaceableGroup(2058660585);
        content.mo4invoke(composer, Integer.valueOf((i >> 9) & 14));
        composer.endReplaceableGroup();
        composer.endNode();
    }

    @PublishedApi
    public static final void invalidApplier() {
        throw new IllegalStateException("Invalid applier".toString());
    }

    @Composable
    public static final CompositionContext rememberCompositionContext(Composer composer, int i) {
        composer.startReplaceableGroup(-1359197906);
        CompositionContext compositionContextBuildContext = composer.buildContext();
        composer.endReplaceableGroup();
        return compositionContextBuildContext;
    }

    @Composable
    public static final <T> T remember(Function0<? extends T> calculation, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        composer.startReplaceableGroup(-3687241);
        T tInvoke = (T) composer.rememberedValue();
        if (tInvoke == Composer.INSTANCE.getEmpty()) {
            tInvoke = calculation.invoke();
            composer.updateRememberedValue(tInvoke);
        }
        composer.endReplaceableGroup();
        return tInvoke;
    }
}
