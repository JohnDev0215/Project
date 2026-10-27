package androidx.navigation;

import android.os.Bundle;
import androidx.annotation.CallSuper;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH&J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0006H\u0016J\u0010\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0006H\u0017J\u0018\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0010H\u0016J\u0018\u0010#\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0010H\u0016J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0006H\u0016J\u0010\u0010%\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0006H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\f¨\u0006&"}, d2 = {"Landroidx/navigation/NavigatorState;", "", "()V", "_backStack", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Landroidx/navigation/NavBackStackEntry;", "_transitionsInProgress", "", "backStack", "Lkotlinx/coroutines/flow/StateFlow;", "getBackStack", "()Lkotlinx/coroutines/flow/StateFlow;", "backStackLock", "Ljava/util/concurrent/locks/ReentrantLock;", "isNavigating", "", "()Z", "setNavigating", "(Z)V", "transitionsInProgress", "getTransitionsInProgress", "createBackStackEntry", "destination", "Landroidx/navigation/NavDestination;", "arguments", "Landroid/os/Bundle;", "markTransitionComplete", "", "entry", "onLaunchSingleTop", "backStackEntry", "pop", "popUpTo", "saveState", "popWithTransition", "push", "pushWithTransition", "navigation-common_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class NavigatorState {

    @NotNull
    private final MutableStateFlow<List<NavBackStackEntry>> _backStack;

    @NotNull
    private final MutableStateFlow<Set<NavBackStackEntry>> _transitionsInProgress;

    @NotNull
    private final StateFlow<List<NavBackStackEntry>> backStack;

    @NotNull
    private final ReentrantLock backStackLock = new ReentrantLock(true);

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    private boolean isNavigating;

    @NotNull
    private final StateFlow<Set<NavBackStackEntry>> transitionsInProgress;

    public NavigatorState() {
        MutableStateFlow<List<NavBackStackEntry>> MutableStateFlow = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this._backStack = MutableStateFlow;
        MutableStateFlow<Set<NavBackStackEntry>> MutableStateFlow2 = StateFlowKt.MutableStateFlow(SetsKt.emptySet());
        this._transitionsInProgress = MutableStateFlow2;
        this.backStack = FlowKt.asStateFlow(MutableStateFlow);
        this.transitionsInProgress = FlowKt.asStateFlow(MutableStateFlow2);
    }

    @NotNull
    public abstract NavBackStackEntry createBackStackEntry(@NotNull NavDestination destination, @Nullable Bundle arguments);

    @NotNull
    public final StateFlow<List<NavBackStackEntry>> getBackStack() {
        return this.backStack;
    }

    @NotNull
    public final StateFlow<Set<NavBackStackEntry>> getTransitionsInProgress() {
        return this.transitionsInProgress;
    }

    /* JADX INFO: renamed from: isNavigating, reason: from getter */
    public final boolean getIsNavigating() {
        return this.isNavigating;
    }

    public void markTransitionComplete(@NotNull NavBackStackEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        MutableStateFlow<Set<NavBackStackEntry>> mutableStateFlow = this._transitionsInProgress;
        mutableStateFlow.setValue(SetsKt.minus(mutableStateFlow.getValue(), entry));
    }

    @CallSuper
    public void onLaunchSingleTop(@NotNull NavBackStackEntry backStackEntry) {
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        MutableStateFlow<List<NavBackStackEntry>> mutableStateFlow = this._backStack;
        mutableStateFlow.setValue(CollectionsKt.plus((Collection<? extends NavBackStackEntry>) CollectionsKt.minus(mutableStateFlow.getValue(), CollectionsKt.last((List) this._backStack.getValue())), backStackEntry));
    }

    public void pop(@NotNull NavBackStackEntry popUpTo, boolean saveState) {
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        ReentrantLock reentrantLock = this.backStackLock;
        reentrantLock.lock();
        try {
            MutableStateFlow<List<NavBackStackEntry>> mutableStateFlow = this._backStack;
            List<NavBackStackEntry> value = mutableStateFlow.getValue();
            ArrayList arrayList = new ArrayList();
            for (Object obj : value) {
                if (!(!Intrinsics.areEqual((NavBackStackEntry) obj, popUpTo))) {
                    break;
                } else {
                    arrayList.add(obj);
                }
            }
            mutableStateFlow.setValue(arrayList);
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void popWithTransition(@NotNull NavBackStackEntry popUpTo, boolean saveState) {
        NavBackStackEntry navBackStackEntryPrevious;
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        MutableStateFlow<Set<NavBackStackEntry>> mutableStateFlow = this._transitionsInProgress;
        mutableStateFlow.setValue(SetsKt.plus(mutableStateFlow.getValue(), popUpTo));
        List<NavBackStackEntry> value = this.backStack.getValue();
        ListIterator<NavBackStackEntry> listIterator = value.listIterator(value.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                navBackStackEntryPrevious = null;
                break;
            }
            navBackStackEntryPrevious = listIterator.previous();
            NavBackStackEntry navBackStackEntry = navBackStackEntryPrevious;
            if (!Intrinsics.areEqual(navBackStackEntry, popUpTo) && this.backStack.getValue().lastIndexOf(navBackStackEntry) < this.backStack.getValue().lastIndexOf(popUpTo)) {
                break;
            }
        }
        NavBackStackEntry navBackStackEntry2 = navBackStackEntryPrevious;
        if (navBackStackEntry2 != null) {
            MutableStateFlow<Set<NavBackStackEntry>> mutableStateFlow2 = this._transitionsInProgress;
            mutableStateFlow2.setValue(SetsKt.plus(mutableStateFlow2.getValue(), navBackStackEntry2));
        }
        pop(popUpTo, saveState);
    }

    public void push(@NotNull NavBackStackEntry backStackEntry) {
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        ReentrantLock reentrantLock = this.backStackLock;
        reentrantLock.lock();
        try {
            MutableStateFlow<List<NavBackStackEntry>> mutableStateFlow = this._backStack;
            mutableStateFlow.setValue(CollectionsKt.plus((Collection<? extends NavBackStackEntry>) mutableStateFlow.getValue(), backStackEntry));
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void pushWithTransition(@NotNull NavBackStackEntry backStackEntry) {
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) CollectionsKt.lastOrNull((List) this.backStack.getValue());
        if (navBackStackEntry != null) {
            MutableStateFlow<Set<NavBackStackEntry>> mutableStateFlow = this._transitionsInProgress;
            mutableStateFlow.setValue(SetsKt.plus(mutableStateFlow.getValue(), navBackStackEntry));
        }
        MutableStateFlow<Set<NavBackStackEntry>> mutableStateFlow2 = this._transitionsInProgress;
        mutableStateFlow2.setValue(SetsKt.plus(mutableStateFlow2.getValue(), backStackEntry));
        push(backStackEntry);
    }

    public final void setNavigating(boolean z) {
        this.isNavigating = z;
    }
}
