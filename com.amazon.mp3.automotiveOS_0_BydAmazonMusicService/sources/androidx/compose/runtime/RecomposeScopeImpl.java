package androidx.compose.runtime;

import androidx.compose.runtime.collection.IdentityArrayIntMap;
import androidx.compose.runtime.collection.IdentityArrayMap;
import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecomposeScopeImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u000e\u00104\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u000eJ\u001c\u00106\u001a\u0010\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u0010\u0018\u0001072\u0006\u00109\u001a\u00020\u000fJ\b\u0010:\u001a\u00020\u0010H\u0016J\u0010\u0010;\u001a\u00020<2\b\u0010\u0015\u001a\u0004\u0018\u00010,J\u0016\u0010=\u001a\u00020\u00162\u000e\u0010>\u001a\n\u0012\u0004\u0012\u00020,\u0018\u00010?J\u000e\u0010@\u001a\u00020\u00102\u0006\u0010A\u001a\u00020,J\u0006\u0010B\u001a\u00020\u0010J\u0006\u0010C\u001a\u00020\u0010J\u000e\u0010D\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u000fJ\"\u0010E\u001a\u00020\u00102\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\rH\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0005R\u000e\u0010\u0014\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u000e\u0010\u001f\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010 \u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b!\u0010\u0019\"\u0004\b\"\u0010\u001bR$\u0010#\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b$\u0010\u0019\"\u0004\b%\u0010\u001bR$\u0010&\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168@@BX\u0080\u000e¢\u0006\f\u001a\u0004\b'\u0010\u0019\"\u0004\b(\u0010\u001bR\"\u0010)\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030+\u0012\u0006\u0012\u0004\u0018\u00010,\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010/\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b0\u0010\u0019\"\u0004\b1\u0010\u001bR\u0011\u00102\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b3\u0010\u0019¨\u0006F"}, d2 = {"Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/compose/runtime/ScopeUpdateScope;", "Landroidx/compose/runtime/RecomposeScope;", "composition", "Landroidx/compose/runtime/CompositionImpl;", "(Landroidx/compose/runtime/CompositionImpl;)V", "anchor", "Landroidx/compose/runtime/Anchor;", "getAnchor", "()Landroidx/compose/runtime/Anchor;", "setAnchor", "(Landroidx/compose/runtime/Anchor;)V", "block", "Lkotlin/Function2;", "Landroidx/compose/runtime/Composer;", "", "", "getComposition", "()Landroidx/compose/runtime/CompositionImpl;", "setComposition", "currentToken", "value", "", "defaultsInScope", "getDefaultsInScope", "()Z", "setDefaultsInScope", "(Z)V", "defaultsInvalid", "getDefaultsInvalid", "setDefaultsInvalid", "flags", "requiresRecompose", "getRequiresRecompose", "setRequiresRecompose", "rereading", "getRereading", "setRereading", "skipped", "getSkipped$runtime_release", "setSkipped", "trackedDependencies", "Landroidx/compose/runtime/collection/IdentityArrayMap;", "Landroidx/compose/runtime/DerivedState;", "", "trackedInstances", "Landroidx/compose/runtime/collection/IdentityArrayIntMap;", "used", "getUsed", "setUsed", "valid", "getValid", "compose", "composer", "end", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "token", "invalidate", "invalidateForResult", "Landroidx/compose/runtime/InvalidationResult;", "isInvalidFor", "instances", "Landroidx/compose/runtime/collection/IdentityArraySet;", "recordRead", "instance", "rereadTrackedInstances", "scopeSkipped", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "updateScope", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecomposeScopeImpl implements ScopeUpdateScope, RecomposeScope {
    private Anchor anchor;
    private Function2<? super Composer, ? super Integer, Unit> block;
    private CompositionImpl composition;
    private int currentToken;
    private int flags;
    private IdentityArrayMap<DerivedState<?>, Object> trackedDependencies;
    private IdentityArrayIntMap trackedInstances;

    public RecomposeScopeImpl(CompositionImpl compositionImpl) {
        this.composition = compositionImpl;
    }

    public final CompositionImpl getComposition() {
        return this.composition;
    }

    public final void setComposition(CompositionImpl compositionImpl) {
        this.composition = compositionImpl;
    }

    public final Anchor getAnchor() {
        return this.anchor;
    }

    public final void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public final boolean getValid() {
        if (this.composition == null) {
            return false;
        }
        Anchor anchor = this.anchor;
        return anchor == null ? false : anchor.getValid();
    }

    public final boolean getUsed() {
        return (this.flags & 1) != 0;
    }

    public final void setUsed(boolean z) {
        if (z) {
            this.flags |= 1;
        } else {
            this.flags &= -2;
        }
    }

    public final boolean getDefaultsInScope() {
        return (this.flags & 2) != 0;
    }

    public final void setDefaultsInScope(boolean z) {
        if (z) {
            this.flags |= 2;
        } else {
            this.flags &= -3;
        }
    }

    public final boolean getDefaultsInvalid() {
        return (this.flags & 4) != 0;
    }

    public final void setDefaultsInvalid(boolean z) {
        if (z) {
            this.flags |= 4;
        } else {
            this.flags &= -5;
        }
    }

    public final boolean getRequiresRecompose() {
        return (this.flags & 8) != 0;
    }

    public final void setRequiresRecompose(boolean z) {
        if (z) {
            this.flags |= 8;
        } else {
            this.flags &= -9;
        }
    }

    public final void compose(Composer composer) {
        Unit unit;
        Intrinsics.checkNotNullParameter(composer, "composer");
        Function2<? super Composer, ? super Integer, Unit> function2 = this.block;
        if (function2 == null) {
            unit = null;
        } else {
            function2.mo4invoke(composer, 1);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            throw new IllegalStateException("Invalid restart scope".toString());
        }
    }

    public final InvalidationResult invalidateForResult(Object value) {
        CompositionImpl compositionImpl = this.composition;
        InvalidationResult invalidationResultInvalidate = compositionImpl == null ? null : compositionImpl.invalidate(this, value);
        return invalidationResultInvalidate == null ? InvalidationResult.IGNORED : invalidationResultInvalidate;
    }

    @Override // androidx.compose.runtime.RecomposeScope
    public void invalidate() {
        CompositionImpl compositionImpl = this.composition;
        if (compositionImpl == null) {
            return;
        }
        compositionImpl.invalidate(this, null);
    }

    @Override // androidx.compose.runtime.ScopeUpdateScope
    public void updateScope(Function2<? super Composer, ? super Integer, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.block = block;
    }

    private final boolean getRereading() {
        return (this.flags & 32) != 0;
    }

    private final void setRereading(boolean z) {
        if (z) {
            this.flags |= 32;
        } else {
            this.flags &= -33;
        }
    }

    public final boolean getSkipped$runtime_release() {
        return (this.flags & 16) != 0;
    }

    private final void setSkipped(boolean z) {
        if (z) {
            this.flags |= 16;
        } else {
            this.flags &= -17;
        }
    }

    public final void start(int token) {
        this.currentToken = token;
        setSkipped(false);
    }

    public final void scopeSkipped() {
        setSkipped(true);
    }

    public final void recordRead(Object instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        if (getRereading()) {
            return;
        }
        IdentityArrayIntMap identityArrayIntMap = this.trackedInstances;
        if (identityArrayIntMap == null) {
            identityArrayIntMap = new IdentityArrayIntMap();
            this.trackedInstances = identityArrayIntMap;
        }
        identityArrayIntMap.add(instance, this.currentToken);
        if (instance instanceof DerivedState) {
            IdentityArrayMap<DerivedState<?>, Object> identityArrayMap = this.trackedDependencies;
            if (identityArrayMap == null) {
                identityArrayMap = new IdentityArrayMap<>(0, 1, null);
                this.trackedDependencies = identityArrayMap;
            }
            identityArrayMap.set(instance, ((DerivedState) instance).getCurrentValue());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0042 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean isInvalidFor(androidx.compose.runtime.collection.IdentityArraySet<java.lang.Object> r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != 0) goto L4
            return r0
        L4:
            androidx.compose.runtime.collection.IdentityArrayMap<androidx.compose.runtime.DerivedState<?>, java.lang.Object> r1 = r5.trackedDependencies
            if (r1 != 0) goto L9
            return r0
        L9:
            boolean r2 = r6.isNotEmpty()
            if (r2 == 0) goto L43
            boolean r2 = r6.isEmpty()
            r3 = 0
            if (r2 == 0) goto L18
        L16:
            r6 = r0
            goto L40
        L18:
            java.util.Iterator r6 = r6.iterator()
        L1c:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto L16
            java.lang.Object r2 = r6.next()
            boolean r4 = r2 instanceof androidx.compose.runtime.DerivedState
            if (r4 == 0) goto L3c
            java.lang.Object r4 = r1.get(r2)
            androidx.compose.runtime.DerivedState r2 = (androidx.compose.runtime.DerivedState) r2
            java.lang.Object r2 = r2.getValue()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r2)
            if (r2 == 0) goto L3c
            r2 = r0
            goto L3d
        L3c:
            r2 = r3
        L3d:
            if (r2 != 0) goto L1c
            r6 = r3
        L40:
            if (r6 == 0) goto L43
            return r3
        L43:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.isInvalidFor(androidx.compose.runtime.collection.IdentityArraySet):boolean");
    }

    public final void rereadTrackedInstances() {
        IdentityArrayIntMap identityArrayIntMap;
        CompositionImpl compositionImpl = this.composition;
        if (compositionImpl == null || (identityArrayIntMap = this.trackedInstances) == null) {
            return;
        }
        setRereading(true);
        try {
            int size = identityArrayIntMap.getSize();
            int i = 0;
            while (i < size) {
                int i2 = i + 1;
                Object obj = identityArrayIntMap.getKeys()[i];
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                }
                int i3 = identityArrayIntMap.getValues()[i];
                compositionImpl.recordReadOf(obj);
                i = i2;
            }
        } finally {
            setRereading(false);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0038, code lost:
    
        if (r3 == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003f, code lost:
    
        return new androidx.compose.runtime.RecomposeScopeImpl$end$1$2(r8, r9, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:?, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlin.jvm.functions.Function1<androidx.compose.runtime.Composition, kotlin.Unit> end(final int r9) {
        /*
            r8 = this;
            androidx.compose.runtime.collection.IdentityArrayIntMap r0 = r8.trackedInstances
            r1 = 0
            if (r0 != 0) goto L6
            goto L3f
        L6:
            boolean r2 = r8.getSkipped$runtime_release()
            if (r2 != 0) goto L3f
            int r2 = r0.getSize()
            r3 = 0
            r4 = r3
        L12:
            r5 = 1
            if (r4 >= r2) goto L38
            int r6 = r4 + 1
            java.lang.Object[] r7 = r0.getKeys()
            r7 = r7[r4]
            if (r7 == 0) goto L30
            int[] r7 = r0.getValues()
            r4 = r7[r4]
            if (r4 == r9) goto L29
            r4 = r5
            goto L2a
        L29:
            r4 = r3
        L2a:
            if (r4 == 0) goto L2e
            r3 = r5
            goto L38
        L2e:
            r4 = r6
            goto L12
        L30:
            java.lang.NullPointerException r9 = new java.lang.NullPointerException
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.Any"
            r9.<init>(r0)
            throw r9
        L38:
            if (r3 == 0) goto L3f
            androidx.compose.runtime.RecomposeScopeImpl$end$1$2 r1 = new androidx.compose.runtime.RecomposeScopeImpl$end$1$2
            r1.<init>()
        L3f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.end(int):kotlin.jvm.functions.Function1");
    }
}
