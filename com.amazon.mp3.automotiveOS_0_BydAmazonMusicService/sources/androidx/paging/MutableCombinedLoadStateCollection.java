package androidx.paging;

import androidx.paging.LoadState;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: MutableCombinedLoadStateCollection.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u001b\u001a\u00020\u00112\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u0010J*\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\u0007H\u0002J\u0018\u0010\"\u001a\u0004\u0018\u00010\u00072\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\rJ\u001a\u0010&\u001a\u00020\u00112\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u0010J\u0018\u0010'\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u00132\b\u0010)\u001a\u0004\u0018\u00010\u0013J\u001e\u0010'\u001a\u00020\r2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\r2\u0006\u0010*\u001a\u00020\u0007J\n\u0010+\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010,\u001a\u00020\u0011H\u0002R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016¨\u0006-"}, d2 = {"Landroidx/paging/MutableCombinedLoadStateCollection;", "", "()V", "_stateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/paging/CombinedLoadStates;", "append", "Landroidx/paging/LoadState;", "flow", "Lkotlinx/coroutines/flow/Flow;", "getFlow", "()Lkotlinx/coroutines/flow/Flow;", "isInitialized", "", "listeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function1;", "", "<set-?>", "Landroidx/paging/LoadStates;", "mediator", "getMediator", "()Landroidx/paging/LoadStates;", "prepend", "refresh", PageUriUtils.SOURCE_QUERY_PARAM, "getSource", "addListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "computeHelperState", "previousState", "sourceRefreshState", "sourceState", "remoteState", "get", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Landroidx/paging/LoadType;", "remote", "removeListener", "set", "sourceLoadStates", "remoteLoadStates", "state", "snapshot", "updateHelperStatesAndDispatch", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class MutableCombinedLoadStateCollection {
    private final MutableStateFlow<CombinedLoadStates> _stateFlow;
    private LoadState append;
    private final Flow<CombinedLoadStates> flow;
    private boolean isInitialized;
    private final CopyOnWriteArrayList<Function1<CombinedLoadStates, Unit>> listeners = new CopyOnWriteArrayList<>();
    private LoadStates mediator;
    private LoadState prepend;
    private LoadState refresh;
    private LoadStates source;

    public MutableCombinedLoadStateCollection() {
        LoadState.NotLoading.Companion companion = LoadState.NotLoading.INSTANCE;
        this.refresh = companion.getIncomplete$paging_common();
        this.prepend = companion.getIncomplete$paging_common();
        this.append = companion.getIncomplete$paging_common();
        this.source = LoadStates.INSTANCE.getIDLE();
        MutableStateFlow<CombinedLoadStates> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._stateFlow = MutableStateFlow;
        this.flow = FlowKt.filterNotNull(MutableStateFlow);
    }

    public final LoadStates getSource() {
        return this.source;
    }

    public final LoadStates getMediator() {
        return this.mediator;
    }

    public final Flow<CombinedLoadStates> getFlow() {
        return this.flow;
    }

    public final void set(LoadStates sourceLoadStates, LoadStates remoteLoadStates) {
        Intrinsics.checkNotNullParameter(sourceLoadStates, "sourceLoadStates");
        this.isInitialized = true;
        this.source = sourceLoadStates;
        this.mediator = remoteLoadStates;
        updateHelperStatesAndDispatch();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean set(androidx.paging.LoadType r4, boolean r5, androidx.paging.LoadState r6) {
        /*
            r3 = this;
            java.lang.String r0 = "type"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "state"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r0 = 1
            r3.isInitialized = r0
            r1 = 0
            if (r5 == 0) goto L2b
            androidx.paging.LoadStates r5 = r3.mediator
            if (r5 != 0) goto L1d
            androidx.paging.LoadStates$Companion r2 = androidx.paging.LoadStates.INSTANCE
            androidx.paging.LoadStates r2 = r2.getIDLE()
            goto L1e
        L1d:
            r2 = r5
        L1e:
            androidx.paging.LoadStates r4 = r2.modifyState$paging_common(r4, r6)
            r3.mediator = r4
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r4 != 0) goto L3a
            goto L3b
        L2b:
            androidx.paging.LoadStates r5 = r3.source
            androidx.paging.LoadStates r4 = r5.modifyState$paging_common(r4, r6)
            r3.source = r4
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r4 != 0) goto L3a
            goto L3b
        L3a:
            r0 = r1
        L3b:
            r3.updateHelperStatesAndDispatch()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.MutableCombinedLoadStateCollection.set(androidx.paging.LoadType, boolean, androidx.paging.LoadState):boolean");
    }

    public final LoadState get(LoadType type, boolean remote) {
        Intrinsics.checkNotNullParameter(type, "type");
        LoadStates loadStates = remote ? this.mediator : this.source;
        if (loadStates == null) {
            return null;
        }
        return loadStates.get$paging_common(type);
    }

    public final void addListener(Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.add(listener);
        CombinedLoadStates combinedLoadStatesSnapshot = snapshot();
        if (combinedLoadStatesSnapshot == null) {
            return;
        }
        listener.invoke(combinedLoadStatesSnapshot);
    }

    public final void removeListener(Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.remove(listener);
    }

    private final CombinedLoadStates snapshot() {
        if (this.isInitialized) {
            return new CombinedLoadStates(this.refresh, this.prepend, this.append, this.source, this.mediator);
        }
        return null;
    }

    private final void updateHelperStatesAndDispatch() {
        LoadState loadState = this.refresh;
        LoadState refresh = this.source.getRefresh();
        LoadState refresh2 = this.source.getRefresh();
        LoadStates loadStates = this.mediator;
        this.refresh = computeHelperState(loadState, refresh, refresh2, loadStates == null ? null : loadStates.getRefresh());
        LoadState loadState2 = this.prepend;
        LoadState refresh3 = this.source.getRefresh();
        LoadState prepend = this.source.getPrepend();
        LoadStates loadStates2 = this.mediator;
        this.prepend = computeHelperState(loadState2, refresh3, prepend, loadStates2 == null ? null : loadStates2.getPrepend());
        LoadState loadState3 = this.append;
        LoadState refresh4 = this.source.getRefresh();
        LoadState append = this.source.getAppend();
        LoadStates loadStates3 = this.mediator;
        this.append = computeHelperState(loadState3, refresh4, append, loadStates3 != null ? loadStates3.getAppend() : null);
        CombinedLoadStates combinedLoadStatesSnapshot = snapshot();
        if (combinedLoadStatesSnapshot != null) {
            this._stateFlow.setValue(combinedLoadStatesSnapshot);
            Iterator<T> it = this.listeners.iterator();
            while (it.hasNext()) {
                ((Function1) it.next()).invoke(combinedLoadStatesSnapshot);
            }
        }
    }

    private final LoadState computeHelperState(LoadState previousState, LoadState sourceRefreshState, LoadState sourceState, LoadState remoteState) {
        return remoteState == null ? sourceState : (!(previousState instanceof LoadState.Loading) || ((sourceRefreshState instanceof LoadState.NotLoading) && (remoteState instanceof LoadState.NotLoading)) || (remoteState instanceof LoadState.Error)) ? remoteState : previousState;
    }
}
