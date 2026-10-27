package androidx.compose.runtime;

import androidx.compose.runtime.collection.IdentityArrayMap;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.collection.IdentityScopeMap;
import androidx.compose.runtime.snapshots.StateObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: Composition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0000\u0018\u00002\u00020\u0001:\u0001eB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010E\u001a\u00020\u001b2\f\u0010F\u001a\b\u0012\u0004\u0012\u0002030GH\u0002J\b\u0010H\u001a\u00020\u001bH\u0016J \u0010I\u001a\u00020\u001b2\u0011\u0010J\u001a\r\u0012\u0004\u0012\u00020\u001b0\u001e¢\u0006\u0002\b\u001fH\u0016¢\u0006\u0002\u0010#J\b\u0010K\u001a\u00020\u001bH\u0016J\b\u0010L\u001a\u00020\u001bH\u0002J\b\u0010M\u001a\u00020\u001bH\u0002J\u0018\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u0002012\b\u0010Q\u001a\u0004\u0018\u000103J\b\u0010R\u001a\u00020\u001bH\u0016J\u0010\u0010S\u001a\u00020\u001b2\u0006\u0010T\u001a\u000203H\u0002J\u0016\u0010U\u001a\u00020\u000f2\f\u0010F\u001a\b\u0012\u0004\u0012\u0002030GH\u0016J\u0016\u0010V\u001a\u00020\u001b2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001eH\u0016J\b\u0010X\u001a\u00020\u000fH\u0016J\u0016\u0010Y\u001a\u00020\u001b2\f\u0010F\u001a\b\u0012\u0004\u0012\u0002030GH\u0016J\u0010\u0010Z\u001a\u00020\u001b2\u0006\u0010T\u001a\u000203H\u0016J\u0010\u0010[\u001a\u00020\u001b2\u0006\u0010T\u001a\u000203H\u0016J\u001d\u0010\\\u001a\u00020\u001b2\u0006\u0010Q\u001a\u0002032\u0006\u0010P\u001a\u000201H\u0000¢\u0006\u0002\b]J \u0010^\u001a\u00020\u001b2\u0011\u0010J\u001a\r\u0012\u0004\u0012\u00020\u001b0\u001e¢\u0006\u0002\b\u001fH\u0016¢\u0006\u0002\u0010#J\u001c\u0010_\u001a\u0016\u0012\u0004\u0012\u000201\u0012\f\u0012\n\u0012\u0004\u0012\u000203\u0018\u00010200H\u0002J\"\u0010`\u001a\u0002Ha\"\u0004\b\u0000\u0010a2\f\u0010W\u001a\b\u0012\u0004\u0012\u0002Ha0\u001eH\u0082\b¢\u0006\u0002\u0010bJ\u0010\u0010c\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020DH\u0002J\b\u0010d\u001a\u00020\u001bH\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011Ra\u0010\u0012\u001aU\u0012Q\u0012O\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0005¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b0\u0014j\u0002`\u001c0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u001b0\u001e¢\u0006\u0002\b\u001fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010'\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030)0(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\u0011R\u0014\u0010-\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u0011R\"\u0010/\u001a\u0016\u0012\u0004\u0012\u000201\u0012\f\u0012\n\u0012\u0004\u0012\u000203\u0018\u00010200X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00104\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u0010\u0011R\u0014\u00105\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u0010\u0011R\u0011\u00106\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0011R\u000e\u00107\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00108\u001a\b\u0012\u0004\u0012\u0002010(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00109\u001a\b\u0012\u0004\u0012\u0002010(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010:\u001a\u00020\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0011\"\u0004\b<\u0010=R\"\u0010>\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001030?j\n\u0012\u0006\u0012\u0004\u0018\u000103`@X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bA\u0010BR\u000e\u0010C\u001a\u00020DX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006f"}, d2 = {"Landroidx/compose/runtime/CompositionImpl;", "Landroidx/compose/runtime/ControlledComposition;", "parent", "Landroidx/compose/runtime/CompositionContext;", "applier", "Landroidx/compose/runtime/Applier;", "recomposeContext", "Lkotlin/coroutines/CoroutineContext;", "(Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/Applier;Lkotlin/coroutines/CoroutineContext;)V", "_recomposeContext", "abandonSet", "Ljava/util/HashSet;", "Landroidx/compose/runtime/RememberObserver;", "Lkotlin/collections/HashSet;", "areChildrenComposing", "", "getAreChildrenComposing", "()Z", "changes", "", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "", "Landroidx/compose/runtime/Change;", "composable", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "getComposable", "()Lkotlin/jvm/functions/Function2;", "setComposable", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "composer", "Landroidx/compose/runtime/ComposerImpl;", "derivedStates", "Landroidx/compose/runtime/collection/IdentityScopeMap;", "Landroidx/compose/runtime/DerivedState;", "disposed", "hasInvalidations", "getHasInvalidations", "hasPendingChanges", "getHasPendingChanges", "invalidations", "Landroidx/compose/runtime/collection/IdentityArrayMap;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/compose/runtime/collection/IdentityArraySet;", "", "isComposing", "isDisposed", "isRoot", "lock", "observations", "observationsProcessed", "pendingInvalidScopes", "getPendingInvalidScopes$runtime_release", "setPendingInvalidScopes$runtime_release", "(Z)V", "pendingModifications", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/AtomicReference;", "getRecomposeContext", "()Lkotlin/coroutines/CoroutineContext;", "slotTable", "Landroidx/compose/runtime/SlotTable;", "addPendingInvalidationsLocked", "values", "", "applyChanges", "composeContent", "content", "dispose", "drainPendingModificationsForCompositionLocked", "drainPendingModificationsLocked", "invalidate", "Landroidx/compose/runtime/InvalidationResult;", "scope", "instance", "invalidateAll", "invalidateScopeOfLocked", "value", "observesAnyOf", "prepareCompose", "block", "recompose", "recordModificationsOf", "recordReadOf", "recordWriteOf", "removeObservation", "removeObservation$runtime_release", "setContent", "takeInvalidations", "trackAbandonedValues", "T", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "validateRecomposeScopeAnchors", "verifyConsistent", "RememberEventDispatcher", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CompositionImpl implements ControlledComposition {
    private final CoroutineContext _recomposeContext;
    private final HashSet<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final List<Function3<Applier<?>, SlotWriter, RememberManager, Unit>> changes;
    private Function2<? super Composer, ? super Integer, Unit> composable;
    private final ComposerImpl composer;
    private final IdentityScopeMap<DerivedState<?>> derivedStates;
    private boolean disposed;
    private IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> invalidations;
    private final boolean isRoot;
    private final Object lock;
    private final IdentityScopeMap<RecomposeScopeImpl> observations;
    private final IdentityScopeMap<RecomposeScopeImpl> observationsProcessed;
    private final CompositionContext parent;
    private boolean pendingInvalidScopes;
    private final AtomicReference<Object> pendingModifications;
    private final SlotTable slotTable;

    public CompositionImpl(CompositionContext parent, Applier<?> applier, CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(applier, "applier");
        this.parent = parent;
        this.applier = applier;
        this.pendingModifications = new AtomicReference<>(null);
        this.lock = new Object();
        HashSet<RememberObserver> hashSet = new HashSet<>();
        this.abandonSet = hashSet;
        SlotTable slotTable = new SlotTable();
        this.slotTable = slotTable;
        this.observations = new IdentityScopeMap<>();
        this.derivedStates = new IdentityScopeMap<>();
        ArrayList arrayList = new ArrayList();
        this.changes = arrayList;
        this.observationsProcessed = new IdentityScopeMap<>();
        this.invalidations = new IdentityArrayMap<>(0, 1, null);
        ComposerImpl composerImpl = new ComposerImpl(applier, parent, slotTable, hashSet, arrayList, this);
        parent.registerComposer$runtime_release(composerImpl);
        this.composer = composerImpl;
        this._recomposeContext = coroutineContext;
        this.isRoot = parent instanceof Recomposer;
        this.composable = ComposableSingletons$CompositionKt.INSTANCE.m7getLambda1$runtime_release();
    }

    public /* synthetic */ CompositionImpl(CompositionContext compositionContext, Applier applier, CoroutineContext coroutineContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(compositionContext, applier, (i & 4) != 0 ? null : coroutineContext);
    }

    /* JADX INFO: renamed from: getPendingInvalidScopes$runtime_release, reason: from getter */
    public final boolean getPendingInvalidScopes() {
        return this.pendingInvalidScopes;
    }

    public final void setPendingInvalidScopes$runtime_release(boolean z) {
        this.pendingInvalidScopes = z;
    }

    public final CoroutineContext getRecomposeContext() {
        CoroutineContext coroutineContext = this._recomposeContext;
        return coroutineContext == null ? this.parent.getRecomposeCoroutineContext$runtime_release() : coroutineContext;
    }

    /* JADX INFO: renamed from: isRoot, reason: from getter */
    public final boolean getIsRoot() {
        return this.isRoot;
    }

    private final boolean getAreChildrenComposing() {
        return this.composer.getAreChildrenComposing$runtime_release();
    }

    public final Function2<Composer, Integer, Unit> getComposable() {
        return this.composable;
    }

    public final void setComposable(Function2<? super Composer, ? super Integer, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        this.composable = function2;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean isComposing() {
        return this.composer.getIsComposing();
    }

    @Override // androidx.compose.runtime.Composition
    /* JADX INFO: renamed from: isDisposed, reason: from getter */
    public boolean getDisposed() {
        return this.disposed;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean getHasPendingChanges() {
        boolean hasPendingChanges$runtime_release;
        synchronized (this.lock) {
            hasPendingChanges$runtime_release = this.composer.getHasPendingChanges$runtime_release();
        }
        return hasPendingChanges$runtime_release;
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(Function2<? super Composer, ? super Integer, Unit> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        if (!(!this.disposed)) {
            throw new IllegalStateException("The composition is disposed".toString());
        }
        this.composable = content;
        this.parent.composeInitial$runtime_release(this, content);
    }

    private final void drainPendingModificationsForCompositionLocked() {
        Object andSet = this.pendingModifications.getAndSet(CompositionKt.PendingApplyNoModifications);
        if (andSet == null) {
            return;
        }
        if (Intrinsics.areEqual(andSet, CompositionKt.PendingApplyNoModifications)) {
            throw new IllegalStateException("pending composition has not been applied".toString());
        }
        if (andSet instanceof Set) {
            addPendingInvalidationsLocked((Set) andSet);
            return;
        }
        if (!(andSet instanceof Object[])) {
            throw new IllegalStateException(Intrinsics.stringPlus("corrupt pendingModifications drain: ", this.pendingModifications).toString());
        }
        Set<? extends Object>[] setArr = (Set[]) andSet;
        int i = 0;
        int length = setArr.length;
        while (i < length) {
            Set<? extends Object> set = setArr[i];
            i++;
            addPendingInvalidationsLocked(set);
        }
    }

    private final void drainPendingModificationsLocked() {
        Object andSet = this.pendingModifications.getAndSet(null);
        if (Intrinsics.areEqual(andSet, CompositionKt.PendingApplyNoModifications)) {
            return;
        }
        if (andSet instanceof Set) {
            addPendingInvalidationsLocked((Set) andSet);
            return;
        }
        if (!(andSet instanceof Object[])) {
            if (andSet == null) {
                throw new IllegalStateException("calling recordModificationsOf and applyChanges concurrently is not supported".toString());
            }
            throw new IllegalStateException(Intrinsics.stringPlus("corrupt pendingModifications drain: ", this.pendingModifications).toString());
        }
        Set<? extends Object>[] setArr = (Set[]) andSet;
        int i = 0;
        int length = setArr.length;
        while (i < length) {
            Set<? extends Object> set = setArr[i];
            i++;
            addPendingInvalidationsLocked(set);
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void composeContent(Function2<? super Composer, ? super Integer, Unit> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        try {
            synchronized (this.lock) {
                drainPendingModificationsForCompositionLocked();
                this.composer.composeContent$runtime_release(takeInvalidations(), content);
                Unit unit = Unit.INSTANCE;
            }
        } catch (Throwable th) {
            if (!this.abandonSet.isEmpty()) {
                new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
            }
            throw th;
        }
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() {
        synchronized (this.lock) {
            if (!this.disposed) {
                this.disposed = true;
                setComposable(ComposableSingletons$CompositionKt.INSTANCE.m8getLambda2$runtime_release());
                boolean z = this.slotTable.getGroupsSize() > 0;
                if (z || (true ^ this.abandonSet.isEmpty())) {
                    RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
                    if (z) {
                        SlotWriter slotWriterOpenWriter = this.slotTable.openWriter();
                        try {
                            ComposerKt.removeCurrentGroup(slotWriterOpenWriter, rememberEventDispatcher);
                            Unit unit = Unit.INSTANCE;
                            slotWriterOpenWriter.close();
                            this.applier.clear();
                            rememberEventDispatcher.dispatchRememberObservers();
                        } catch (Throwable th) {
                            slotWriterOpenWriter.close();
                            throw th;
                        }
                    }
                    rememberEventDispatcher.dispatchAbandons();
                }
                this.composer.dispose$runtime_release();
            }
            Unit unit2 = Unit.INSTANCE;
        }
        this.parent.unregisterComposition$runtime_release(this);
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        boolean z;
        synchronized (this.lock) {
            z = this.invalidations.getSize() > 0;
        }
        return z;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void recordModificationsOf(Set<? extends Object> values) {
        Object obj;
        Object objPlus;
        Intrinsics.checkNotNullParameter(values, "values");
        do {
            obj = this.pendingModifications.get();
            if (obj == null ? true : Intrinsics.areEqual(obj, CompositionKt.PendingApplyNoModifications)) {
                objPlus = values;
            } else if (obj instanceof Set) {
                objPlus = new Set[]{(Set) obj, values};
            } else {
                if (!(obj instanceof Object[])) {
                    throw new IllegalStateException(Intrinsics.stringPlus("corrupt pendingModifications: ", this.pendingModifications).toString());
                }
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.collections.Set<kotlin.Any>>");
                }
                objPlus = ArraysKt___ArraysJvmKt.plus((Set<? extends Object>[]) obj, values);
            }
        } while (!CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.pendingModifications, obj, objPlus));
        if (obj == null) {
            synchronized (this.lock) {
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean observesAnyOf(Set<? extends Object> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        for (Object obj : values) {
            if (this.observations.contains(obj) || this.derivedStates.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void prepareCompose(Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.composer.prepareCompose$runtime_release(block);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void addPendingInvalidationsLocked(Set<? extends Object> values) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        for (Object obj : values) {
            if (obj instanceof RecomposeScopeImpl) {
                ((RecomposeScopeImpl) obj).invalidateForResult(null);
            } else {
                addPendingInvalidationsLocked$invalidate(this, objectRef, obj);
                IdentityScopeMap<DerivedState<?>> identityScopeMap = this.derivedStates;
                int iFind = identityScopeMap.find(obj);
                if (iFind >= 0) {
                    Iterator<T> it = identityScopeMap.scopeSetAt(iFind).iterator();
                    while (it.hasNext()) {
                        addPendingInvalidationsLocked$invalidate(this, objectRef, (DerivedState) it.next());
                    }
                }
            }
        }
        HashSet hashSet = (HashSet) objectRef.element;
        if (hashSet == null) {
            return;
        }
        IdentityScopeMap<RecomposeScopeImpl> identityScopeMap2 = this.observations;
        int size = identityScopeMap2.getSize();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3 = i + 1;
            int i4 = identityScopeMap2.getValueOrder()[i];
            IdentityArraySet<RecomposeScopeImpl> identityArraySet = identityScopeMap2.getScopeSets()[i4];
            Intrinsics.checkNotNull(identityArraySet);
            int size2 = identityArraySet.size();
            int i5 = 0;
            int i6 = 0;
            while (i5 < size2) {
                int i7 = i5 + 1;
                Object obj2 = identityArraySet.getValues()[i5];
                if (obj2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                }
                if (!hashSet.contains((RecomposeScopeImpl) obj2)) {
                    if (i6 != i5) {
                        identityArraySet.getValues()[i6] = obj2;
                    }
                    i6++;
                }
                i5 = i7;
            }
            int size3 = identityArraySet.size();
            for (int i8 = i6; i8 < size3; i8++) {
                identityArraySet.getValues()[i8] = null;
            }
            identityArraySet.setSize(i6);
            if (identityArraySet.size() > 0) {
                if (i2 != i) {
                    int i9 = identityScopeMap2.getValueOrder()[i2];
                    identityScopeMap2.getValueOrder()[i2] = i4;
                    identityScopeMap2.getValueOrder()[i] = i9;
                }
                i2++;
            }
            i = i3;
        }
        int size4 = identityScopeMap2.getSize();
        for (int i10 = i2; i10 < size4; i10++) {
            identityScopeMap2.getValues()[identityScopeMap2.getValueOrder()[i10]] = null;
        }
        identityScopeMap2.setSize(i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.util.HashSet] */
    /* JADX WARN: Type inference failed for: r2v6, types: [T, java.util.HashSet] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    private static final void addPendingInvalidationsLocked$invalidate(CompositionImpl compositionImpl, Ref.ObjectRef<HashSet<RecomposeScopeImpl>> objectRef, Object obj) {
        IdentityScopeMap<RecomposeScopeImpl> identityScopeMap = compositionImpl.observations;
        int iFind = identityScopeMap.find(obj);
        if (iFind >= 0) {
            for (RecomposeScopeImpl recomposeScopeImpl : identityScopeMap.scopeSetAt(iFind)) {
                if (!compositionImpl.observationsProcessed.remove(obj, recomposeScopeImpl) && recomposeScopeImpl.invalidateForResult(obj) != InvalidationResult.IGNORED) {
                    HashSet<RecomposeScopeImpl> hashSet = objectRef.element;
                    ?? r2 = hashSet;
                    if (hashSet == null) {
                        ?? hashSet2 = new HashSet();
                        objectRef.element = hashSet2;
                        r2 = hashSet2;
                    }
                    r2.add(recomposeScopeImpl);
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void recordReadOf(Object value) {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        Intrinsics.checkNotNullParameter(value, "value");
        if (getAreChildrenComposing() || (currentRecomposeScope$runtime_release = this.composer.getCurrentRecomposeScope$runtime_release()) == null) {
            return;
        }
        currentRecomposeScope$runtime_release.setUsed(true);
        this.observations.add(value, currentRecomposeScope$runtime_release);
        if (value instanceof DerivedState) {
            Iterator<T> it = ((DerivedState) value).getDependencies().iterator();
            while (it.hasNext()) {
                this.derivedStates.add((StateObject) it.next(), value);
            }
        }
        currentRecomposeScope$runtime_release.recordRead(value);
    }

    private final void invalidateScopeOfLocked(Object value) {
        IdentityScopeMap<RecomposeScopeImpl> identityScopeMap = this.observations;
        int iFind = identityScopeMap.find(value);
        if (iFind >= 0) {
            for (RecomposeScopeImpl recomposeScopeImpl : identityScopeMap.scopeSetAt(iFind)) {
                if (recomposeScopeImpl.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                    this.observationsProcessed.add(value, recomposeScopeImpl);
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void recordWriteOf(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        synchronized (this.lock) {
            invalidateScopeOfLocked(value);
            IdentityScopeMap<DerivedState<?>> identityScopeMap = this.derivedStates;
            int iFind = identityScopeMap.find(value);
            if (iFind >= 0) {
                Iterator<T> it = identityScopeMap.scopeSetAt(iFind).iterator();
                while (it.hasNext()) {
                    invalidateScopeOfLocked((DerivedState) it.next());
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean recompose() {
        boolean zRecompose$runtime_release;
        synchronized (this.lock) {
            drainPendingModificationsForCompositionLocked();
            try {
                zRecompose$runtime_release = this.composer.recompose$runtime_release(takeInvalidations());
                if (!zRecompose$runtime_release) {
                    drainPendingModificationsLocked();
                }
            } finally {
            }
        }
        return zRecompose$runtime_release;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyChanges() {
        synchronized (this.lock) {
            RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
            try {
                this.applier.onBeginChanges();
                SlotWriter slotWriterOpenWriter = this.slotTable.openWriter();
                try {
                    Applier<?> applier = this.applier;
                    List<Function3<Applier<?>, SlotWriter, RememberManager, Unit>> list = this.changes;
                    int size = list.size();
                    int i = 0;
                    for (int i2 = 0; i2 < size; i2++) {
                        list.get(i2).invoke(applier, slotWriterOpenWriter, rememberEventDispatcher);
                    }
                    this.changes.clear();
                    Unit unit = Unit.INSTANCE;
                    slotWriterOpenWriter.close();
                    this.applier.onEndChanges();
                    rememberEventDispatcher.dispatchRememberObservers();
                    rememberEventDispatcher.dispatchSideEffects();
                    if (getPendingInvalidScopes()) {
                        setPendingInvalidScopes$runtime_release(false);
                        IdentityScopeMap<RecomposeScopeImpl> identityScopeMap = this.observations;
                        int size2 = identityScopeMap.getSize();
                        int i3 = 0;
                        int i4 = 0;
                        while (i3 < size2) {
                            int i5 = i3 + 1;
                            int i6 = identityScopeMap.getValueOrder()[i3];
                            IdentityArraySet<RecomposeScopeImpl> identityArraySet = identityScopeMap.getScopeSets()[i6];
                            Intrinsics.checkNotNull(identityArraySet);
                            int size3 = identityArraySet.size();
                            int i7 = 0;
                            int i8 = 0;
                            while (i7 < size3) {
                                int i9 = i7 + 1;
                                Object obj = identityArraySet.getValues()[i7];
                                if (obj == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                }
                                if (!(!((RecomposeScopeImpl) obj).getValid())) {
                                    if (i8 != i7) {
                                        identityArraySet.getValues()[i8] = obj;
                                    }
                                    i8++;
                                }
                                i7 = i9;
                            }
                            int size4 = identityArraySet.size();
                            for (int i10 = i8; i10 < size4; i10++) {
                                identityArraySet.getValues()[i10] = null;
                            }
                            identityArraySet.setSize(i8);
                            if (identityArraySet.size() > 0) {
                                if (i4 != i3) {
                                    int i11 = identityScopeMap.getValueOrder()[i4];
                                    identityScopeMap.getValueOrder()[i4] = i6;
                                    identityScopeMap.getValueOrder()[i3] = i11;
                                }
                                i4++;
                            }
                            i3 = i5;
                        }
                        int size5 = identityScopeMap.getSize();
                        for (int i12 = i4; i12 < size5; i12++) {
                            identityScopeMap.getValues()[identityScopeMap.getValueOrder()[i12]] = null;
                        }
                        identityScopeMap.setSize(i4);
                        IdentityScopeMap<DerivedState<?>> identityScopeMap2 = this.derivedStates;
                        int size6 = identityScopeMap2.getSize();
                        int i13 = 0;
                        int i14 = 0;
                        while (i13 < size6) {
                            int i15 = i13 + 1;
                            int i16 = identityScopeMap2.getValueOrder()[i13];
                            IdentityArraySet<DerivedState<?>> identityArraySet2 = identityScopeMap2.getScopeSets()[i16];
                            Intrinsics.checkNotNull(identityArraySet2);
                            int size7 = identityArraySet2.size();
                            int i17 = i;
                            int i18 = i17;
                            while (i17 < size7) {
                                int i19 = i17 + 1;
                                Object obj2 = identityArraySet2.getValues()[i17];
                                if (obj2 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                }
                                if (!(!this.observations.contains((DerivedState) obj2))) {
                                    if (i18 != i17) {
                                        identityArraySet2.getValues()[i18] = obj2;
                                    }
                                    i18++;
                                }
                                i17 = i19;
                            }
                            int size8 = identityArraySet2.size();
                            for (int i20 = i18; i20 < size8; i20++) {
                                identityArraySet2.getValues()[i20] = null;
                            }
                            identityArraySet2.setSize(i18);
                            if (identityArraySet2.size() > 0) {
                                if (i14 != i13) {
                                    int i21 = identityScopeMap2.getValueOrder()[i14];
                                    identityScopeMap2.getValueOrder()[i14] = i16;
                                    identityScopeMap2.getValueOrder()[i13] = i21;
                                }
                                i14++;
                            }
                            i13 = i15;
                            i = 0;
                        }
                        int size9 = identityScopeMap2.getSize();
                        for (int i22 = i14; i22 < size9; i22++) {
                            identityScopeMap2.getValues()[identityScopeMap2.getValueOrder()[i22]] = null;
                        }
                        identityScopeMap2.setSize(i14);
                    }
                    rememberEventDispatcher.dispatchAbandons();
                    drainPendingModificationsLocked();
                    Unit unit2 = Unit.INSTANCE;
                } catch (Throwable th) {
                    slotWriterOpenWriter.close();
                    throw th;
                }
            } catch (Throwable th2) {
                rememberEventDispatcher.dispatchAbandons();
                throw th2;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void invalidateAll() {
        synchronized (this.lock) {
            Object[] slots = this.slotTable.getSlots();
            int i = 0;
            int length = slots.length;
            while (i < length) {
                Object obj = slots[i];
                i++;
                RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
                if (recomposeScopeImpl != null) {
                    recomposeScopeImpl.invalidate();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void verifyConsistent() {
        synchronized (this.lock) {
            if (!isComposing()) {
                this.slotTable.verifyWellFormed();
                validateRecomposeScopeAnchors(this.slotTable);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final InvalidationResult invalidate(RecomposeScopeImpl scope, Object instance) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        if (scope.getDefaultsInScope()) {
            scope.setDefaultsInvalid(true);
        }
        Anchor anchor = scope.getAnchor();
        if (anchor == null || !this.slotTable.ownsAnchor(anchor) || !anchor.getValid()) {
            return InvalidationResult.IGNORED;
        }
        if (anchor.toIndexFor(this.slotTable) < 0) {
            return InvalidationResult.IGNORED;
        }
        if (isComposing() && this.composer.tryImminentInvalidation$runtime_release(scope, instance)) {
            return InvalidationResult.IMMINENT;
        }
        if (instance != null) {
            CompositionKt.addValue(this.invalidations, scope, instance);
        } else {
            this.invalidations.set(scope, null);
        }
        this.parent.invalidate$runtime_release(this);
        return isComposing() ? InvalidationResult.DEFERRED : InvalidationResult.SCHEDULED;
    }

    public final void removeObservation$runtime_release(Object instance, RecomposeScopeImpl scope) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.observations.remove(instance, scope);
    }

    private final IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> takeInvalidations() {
        IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> identityArrayMap = this.invalidations;
        this.invalidations = new IdentityArrayMap<>(0, 1, null);
        return identityArrayMap;
    }

    private final void validateRecomposeScopeAnchors(SlotTable slotTable) {
        Object[] slots = slotTable.getSlots();
        ArrayList arrayList = new ArrayList();
        int length = slots.length;
        int i = 0;
        int i2 = 0;
        while (i2 < length) {
            Object obj = slots[i2];
            i2++;
            RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
            if (recomposeScopeImpl != null) {
                arrayList.add(recomposeScopeImpl);
            }
        }
        int size = arrayList.size();
        while (i < size) {
            int i3 = i + 1;
            RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) arrayList.get(i);
            Anchor anchor = recomposeScopeImpl2.getAnchor();
            if (anchor != null && !slotTable.slotsOf$runtime_release(anchor.toIndexFor(slotTable)).contains(recomposeScopeImpl2)) {
                throw new IllegalStateException(("Misaligned anchor " + anchor + " in scope " + recomposeScopeImpl2 + " encountered, scope found at " + ArraysKt___ArraysKt.indexOf((RecomposeScopeImpl[]) slotTable.getSlots(), recomposeScopeImpl2)).toString());
            }
            i = i3;
        }
    }

    private final <T> T trackAbandonedValues(Function0<? extends T> block) {
        try {
            T tInvoke = block.invoke();
            InlineMarker.finallyStart(1);
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            if (!this.abandonSet.isEmpty()) {
                new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    /* JADX INFO: compiled from: Composition.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u000bJ\u0006\u0010\u000e\u001a\u00020\u000bJ\u0010\u0010\u0006\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\u0016\u0010\u0010\u001a\u00020\u000b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/runtime/CompositionImpl$RememberEventDispatcher;", "Landroidx/compose/runtime/RememberManager;", "abandoning", "", "Landroidx/compose/runtime/RememberObserver;", "(Ljava/util/Set;)V", "forgetting", "", "remembering", "sideEffects", "Lkotlin/Function0;", "", "dispatchAbandons", "dispatchRememberObservers", "dispatchSideEffects", "instance", "sideEffect", "effect", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class RememberEventDispatcher implements RememberManager {
        private final Set<RememberObserver> abandoning;
        private final List<RememberObserver> forgetting;
        private final List<RememberObserver> remembering;
        private final List<Function0<Unit>> sideEffects;

        public RememberEventDispatcher(Set<RememberObserver> abandoning) {
            Intrinsics.checkNotNullParameter(abandoning, "abandoning");
            this.abandoning = abandoning;
            this.remembering = new ArrayList();
            this.forgetting = new ArrayList();
            this.sideEffects = new ArrayList();
        }

        @Override // androidx.compose.runtime.RememberManager
        public void remembering(RememberObserver instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            int iLastIndexOf = this.forgetting.lastIndexOf(instance);
            if (iLastIndexOf >= 0) {
                this.forgetting.remove(iLastIndexOf);
                this.abandoning.remove(instance);
            } else {
                this.remembering.add(instance);
            }
        }

        @Override // androidx.compose.runtime.RememberManager
        public void forgetting(RememberObserver instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            int iLastIndexOf = this.remembering.lastIndexOf(instance);
            if (iLastIndexOf >= 0) {
                this.remembering.remove(iLastIndexOf);
                this.abandoning.remove(instance);
            } else {
                this.forgetting.add(instance);
            }
        }

        @Override // androidx.compose.runtime.RememberManager
        public void sideEffect(Function0<Unit> effect) {
            Intrinsics.checkNotNullParameter(effect, "effect");
            this.sideEffects.add(effect);
        }

        public final void dispatchRememberObservers() {
            int size;
            if ((!this.forgetting.isEmpty()) && this.forgetting.size() - 1 >= 0) {
                while (true) {
                    int i = size - 1;
                    RememberObserver rememberObserver = this.forgetting.get(size);
                    if (!this.abandoning.contains(rememberObserver)) {
                        rememberObserver.onForgotten();
                    }
                    if (i < 0) {
                        break;
                    } else {
                        size = i;
                    }
                }
            }
            if (!this.remembering.isEmpty()) {
                List<RememberObserver> list = this.remembering;
                int i2 = 0;
                int size2 = list.size();
                while (i2 < size2) {
                    int i3 = i2 + 1;
                    RememberObserver rememberObserver2 = list.get(i2);
                    this.abandoning.remove(rememberObserver2);
                    rememberObserver2.onRemembered();
                    i2 = i3;
                }
            }
        }

        public final void dispatchSideEffects() {
            if (!this.sideEffects.isEmpty()) {
                List<Function0<Unit>> list = this.sideEffects;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    list.get(i).invoke();
                }
                this.sideEffects.clear();
            }
        }

        public final void dispatchAbandons() {
            if (!this.abandoning.isEmpty()) {
                Iterator<RememberObserver> it = this.abandoning.iterator();
                while (it.hasNext()) {
                    RememberObserver next = it.next();
                    it.remove();
                    next.onAbandoned();
                }
            }
        }
    }
}
