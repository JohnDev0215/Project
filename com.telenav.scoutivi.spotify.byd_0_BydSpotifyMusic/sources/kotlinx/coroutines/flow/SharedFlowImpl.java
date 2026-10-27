package kotlinx.coroutines.flow;

import g5.l;
import g5.r;
import h5.p;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import k5.d;
import k5.g;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.h;
import kotlin.jvm.internal.l;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.internal.c0;
import kotlinx.coroutines.m;
import kotlinx.coroutines.v0;
import l5.c;

/* JADX INFO: compiled from: SharedFlow.kt */
/* JADX INFO: loaded from: classes.dex */
final class SharedFlowImpl<T> extends AbstractSharedFlow<SharedFlowSlot> implements MutableSharedFlow<T>, CancellableFlow<T>, FusibleFlow<T> {
    private Object[] buffer;
    private final int bufferCapacity;
    private int bufferSize;
    private long minCollectorIndex;
    private final k onBufferOverflow;
    private int queueSize;
    private final int replay;
    private long replayIndex;

    /* JADX INFO: compiled from: SharedFlow.kt */
    private static final class Emitter implements v0 {
        public final d<r> cont;
        public final SharedFlowImpl<?> flow;
        public long index;
        public final Object value;

        /* JADX WARN: Multi-variable type inference failed */
        public Emitter(SharedFlowImpl<?> sharedFlowImpl, long j7, Object obj, d<? super r> dVar) {
            this.flow = sharedFlowImpl;
            this.index = j7;
            this.value = obj;
            this.cont = dVar;
        }

        @Override // kotlinx.coroutines.v0
        public void dispose() {
            this.flow.cancelEmitter(this);
        }
    }

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[k.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[k.SUSPEND.ordinal()] = 1;
            iArr[k.DROP_LATEST.ordinal()] = 2;
            iArr[k.DROP_OLDEST.ordinal()] = 3;
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.SharedFlowImpl$collect$1, reason: invalid class name */
    /* JADX INFO: compiled from: SharedFlow.kt */
    @f(c = "kotlinx.coroutines.flow.SharedFlowImpl", f = "SharedFlow.kt", l = {314, 321, 324}, m = "collect")
    static final class AnonymousClass1 extends kotlin.coroutines.jvm.internal.d {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(d dVar) {
            super(dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SharedFlowImpl.this.collect(null, this);
        }
    }

    public SharedFlowImpl(int i7, int i8, k kVar) {
        this.replay = i7;
        this.bufferCapacity = i8;
        this.onBufferOverflow = kVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelEmitter(Emitter emitter) {
        synchronized (this) {
            if (emitter.index < getHead()) {
                return;
            }
            Object[] objArr = this.buffer;
            l.c(objArr);
            if (SharedFlowKt.getBufferAt(objArr, emitter.index) != emitter) {
                return;
            }
            SharedFlowKt.setBufferAt(objArr, emitter.index, SharedFlowKt.NO_VALUE);
            cleanupTailLocked();
            r rVar = r.f6563a;
        }
    }

    private final void cleanupTailLocked() {
        if (this.bufferCapacity != 0 || this.queueSize > 1) {
            Object[] objArr = this.buffer;
            l.c(objArr);
            while (this.queueSize > 0 && SharedFlowKt.getBufferAt(objArr, (getHead() + ((long) getTotalSize())) - 1) == SharedFlowKt.NO_VALUE) {
                this.queueSize--;
                SharedFlowKt.setBufferAt(objArr, getHead() + ((long) getTotalSize()), null);
            }
        }
    }

    private final void correctCollectorIndexesOnDropOldest(long j7) {
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        if (((AbstractSharedFlow) this).nCollectors != 0 && (abstractSharedFlowSlotArr = ((AbstractSharedFlow) this).slots) != null) {
            for (AbstractSharedFlowSlot abstractSharedFlowSlot : abstractSharedFlowSlotArr) {
                if (abstractSharedFlowSlot != null) {
                    SharedFlowSlot sharedFlowSlot = (SharedFlowSlot) abstractSharedFlowSlot;
                    long j8 = sharedFlowSlot.index;
                    if (j8 >= 0 && j8 < j7) {
                        sharedFlowSlot.index = j7;
                    }
                }
            }
        }
        this.minCollectorIndex = j7;
    }

    private final void dropOldestLocked() {
        Object[] objArr = this.buffer;
        l.c(objArr);
        SharedFlowKt.setBufferAt(objArr, getHead(), null);
        this.bufferSize--;
        long head = getHead() + 1;
        if (this.replayIndex < head) {
            this.replayIndex = head;
        }
        if (this.minCollectorIndex < head) {
            correctCollectorIndexesOnDropOldest(head);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enqueueLocked(Object obj) {
        int totalSize = getTotalSize();
        Object[] objArrGrowBuffer = this.buffer;
        if (objArrGrowBuffer == null) {
            objArrGrowBuffer = growBuffer(null, 0, 2);
        } else if (totalSize >= objArrGrowBuffer.length) {
            objArrGrowBuffer = growBuffer(objArrGrowBuffer, totalSize, objArrGrowBuffer.length * 2);
        }
        SharedFlowKt.setBufferAt(objArrGrowBuffer, getHead() + ((long) totalSize), obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v3 */
    public final d<r>[] findSlotsToResumeLocked(d<r>[] dVarArr) {
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        SharedFlowSlot sharedFlowSlot;
        d<? super r> dVar;
        int length = dVarArr.length;
        if (((AbstractSharedFlow) this).nCollectors != 0 && (abstractSharedFlowSlotArr = ((AbstractSharedFlow) this).slots) != null) {
            int length2 = abstractSharedFlowSlotArr.length;
            int i7 = 0;
            dVarArr = dVarArr;
            while (i7 < length2) {
                AbstractSharedFlowSlot abstractSharedFlowSlot = abstractSharedFlowSlotArr[i7];
                if (abstractSharedFlowSlot != null && (dVar = (sharedFlowSlot = (SharedFlowSlot) abstractSharedFlowSlot).cont) != null && tryPeekLocked(sharedFlowSlot) >= 0) {
                    int length3 = dVarArr.length;
                    dVarArr = dVarArr;
                    if (length >= length3) {
                        Object[] objArrCopyOf = Arrays.copyOf(dVarArr, Math.max(2, dVarArr.length * 2));
                        l.d(objArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
                        dVarArr = (d[]) objArrCopyOf;
                    }
                    dVarArr[length] = dVar;
                    sharedFlowSlot.cont = null;
                    length++;
                }
                i7++;
                dVarArr = dVarArr;
            }
        }
        return dVarArr;
    }

    private final long getBufferEndIndex() {
        return getHead() + ((long) this.bufferSize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getHead() {
        return Math.min(this.minCollectorIndex, this.replayIndex);
    }

    private final Object getPeekedValueLockedAt(long j7) {
        Object[] objArr = this.buffer;
        l.c(objArr);
        Object bufferAt = SharedFlowKt.getBufferAt(objArr, j7);
        return bufferAt instanceof Emitter ? ((Emitter) bufferAt).value : bufferAt;
    }

    private final long getQueueEndIndex() {
        return getHead() + ((long) this.bufferSize) + ((long) this.queueSize);
    }

    private final int getReplaySize() {
        return (int) ((getHead() + ((long) this.bufferSize)) - this.replayIndex);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getTotalSize() {
        return this.bufferSize + this.queueSize;
    }

    private final Object[] growBuffer(Object[] objArr, int i7, int i8) {
        if (!(i8 > 0)) {
            throw new IllegalStateException("Buffer size overflow".toString());
        }
        Object[] objArr2 = new Object[i8];
        this.buffer = objArr2;
        if (objArr == null) {
            return objArr2;
        }
        long head = getHead();
        for (int i9 = 0; i9 < i7; i9++) {
            long j7 = ((long) i9) + head;
            SharedFlowKt.setBufferAt(objArr2, j7, SharedFlowKt.getBufferAt(objArr, j7));
        }
        return objArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean tryEmitLocked(T t6) {
        if (getNCollectors() == 0) {
            return tryEmitNoCollectorsLocked(t6);
        }
        if (this.bufferSize >= this.bufferCapacity && this.minCollectorIndex <= this.replayIndex) {
            int i7 = WhenMappings.$EnumSwitchMapping$0[this.onBufferOverflow.ordinal()];
            if (i7 == 1) {
                return false;
            }
            if (i7 == 2) {
                return true;
            }
        }
        enqueueLocked(t6);
        int i8 = this.bufferSize + 1;
        this.bufferSize = i8;
        if (i8 > this.bufferCapacity) {
            dropOldestLocked();
        }
        if (getReplaySize() > this.replay) {
            updateBufferLocked(this.replayIndex + 1, this.minCollectorIndex, getBufferEndIndex(), getQueueEndIndex());
        }
        return true;
    }

    private final boolean tryEmitNoCollectorsLocked(T t6) {
        if (this.replay == 0) {
            return true;
        }
        enqueueLocked(t6);
        int i7 = this.bufferSize + 1;
        this.bufferSize = i7;
        if (i7 > this.replay) {
            dropOldestLocked();
        }
        this.minCollectorIndex = getHead() + ((long) this.bufferSize);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long tryPeekLocked(SharedFlowSlot sharedFlowSlot) {
        long j7 = sharedFlowSlot.index;
        if (j7 < getBufferEndIndex()) {
            return j7;
        }
        if (this.bufferCapacity <= 0 && j7 <= getHead() && this.queueSize != 0) {
            return j7;
        }
        return -1L;
    }

    private final Object tryTakeValue(SharedFlowSlot sharedFlowSlot) {
        Object obj;
        d<r>[] dVarArrUpdateCollectorIndexLocked$kotlinx_coroutines_core = AbstractSharedFlowKt.EMPTY_RESUMES;
        synchronized (this) {
            long jTryPeekLocked = tryPeekLocked(sharedFlowSlot);
            if (jTryPeekLocked < 0) {
                obj = SharedFlowKt.NO_VALUE;
            } else {
                long j7 = sharedFlowSlot.index;
                Object peekedValueLockedAt = getPeekedValueLockedAt(jTryPeekLocked);
                sharedFlowSlot.index = jTryPeekLocked + 1;
                dVarArrUpdateCollectorIndexLocked$kotlinx_coroutines_core = updateCollectorIndexLocked$kotlinx_coroutines_core(j7);
                obj = peekedValueLockedAt;
            }
        }
        for (d<r> dVar : dVarArrUpdateCollectorIndexLocked$kotlinx_coroutines_core) {
            if (dVar != null) {
                r rVar = r.f6563a;
                l.a aVar = g5.l.f6557e;
                dVar.resumeWith(g5.l.a(rVar));
            }
        }
        return obj;
    }

    private final void updateBufferLocked(long j7, long j8, long j9, long j10) {
        long jMin = Math.min(j8, j7);
        for (long head = getHead(); head < jMin; head++) {
            Object[] objArr = this.buffer;
            kotlin.jvm.internal.l.c(objArr);
            SharedFlowKt.setBufferAt(objArr, head, null);
        }
        this.replayIndex = j7;
        this.minCollectorIndex = j8;
        this.bufferSize = (int) (j9 - jMin);
        this.queueSize = (int) (j10 - j9);
    }

    final /* synthetic */ Object awaitValue(SharedFlowSlot sharedFlowSlot, d<? super r> dVar) throws Throwable {
        kotlinx.coroutines.k kVar = new kotlinx.coroutines.k(c.c(dVar), 1);
        kVar.A();
        synchronized (this) {
            if (tryPeekLocked(sharedFlowSlot) < 0) {
                sharedFlowSlot.cont = kVar;
            } else {
                r rVar = r.f6563a;
                l.a aVar = g5.l.f6557e;
                kVar.resumeWith(g5.l.a(rVar));
            }
            r rVar2 = r.f6563a;
        }
        Object objY = kVar.y();
        if (objY == l5.d.d()) {
            h.c(dVar);
        }
        return objY;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c0 A[Catch: all -> 0x006f, TRY_LEAVE, TryCatch #1 {all -> 0x006f, blocks: (B:14:0x003b, B:35:0x00a0, B:38:0x00aa, B:39:0x00ad, B:42:0x00c0, B:19:0x0059, B:22:0x006b, B:33:0x0092), top: B:51:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2, types: [kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot] */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object, kotlinx.coroutines.flow.SharedFlowSlot] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Object, kotlinx.coroutines.flow.SharedFlowSlot] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r6v1, types: [kotlinx.coroutines.flow.internal.AbstractSharedFlow] */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object, kotlinx.coroutines.flow.SharedFlowImpl] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00bd -> B:15:0x003e). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector<? super T> r9, k5.d<? super g5.r> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.SharedFlowImpl.collect(kotlinx.coroutines.flow.FlowCollector, k5.d):java.lang.Object");
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(T t6, d<? super r> dVar) throws Throwable {
        if (tryEmit(t6)) {
            return r.f6563a;
        }
        Object objEmitSuspend = emitSuspend(t6, dVar);
        return objEmitSuspend == l5.d.d() ? objEmitSuspend : r.f6563a;
    }

    final /* synthetic */ Object emitSuspend(T t6, d<? super r> dVar) throws Throwable {
        d<r>[] dVarArrFindSlotsToResumeLocked;
        Emitter emitter;
        kotlinx.coroutines.k kVar = new kotlinx.coroutines.k(c.c(dVar), 1);
        kVar.A();
        d<r>[] dVarArrFindSlotsToResumeLocked2 = AbstractSharedFlowKt.EMPTY_RESUMES;
        synchronized (this) {
            if (tryEmitLocked(t6)) {
                r rVar = r.f6563a;
                l.a aVar = g5.l.f6557e;
                kVar.resumeWith(g5.l.a(rVar));
                dVarArrFindSlotsToResumeLocked = findSlotsToResumeLocked(dVarArrFindSlotsToResumeLocked2);
                emitter = null;
            } else {
                Emitter emitter2 = new Emitter(this, ((long) getTotalSize()) + getHead(), t6, kVar);
                enqueueLocked(emitter2);
                this.queueSize++;
                if (this.bufferCapacity == 0) {
                    dVarArrFindSlotsToResumeLocked2 = findSlotsToResumeLocked(dVarArrFindSlotsToResumeLocked2);
                }
                dVarArrFindSlotsToResumeLocked = dVarArrFindSlotsToResumeLocked2;
                emitter = emitter2;
            }
        }
        if (emitter != null) {
            m.a(kVar, emitter);
        }
        for (d<r> dVar2 : dVarArrFindSlotsToResumeLocked) {
            if (dVar2 != null) {
                r rVar2 = r.f6563a;
                l.a aVar2 = g5.l.f6557e;
                dVar2.resumeWith(g5.l.a(rVar2));
            }
        }
        Object objY = kVar.y();
        if (objY == l5.d.d()) {
            h.c(dVar);
        }
        return objY;
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow<T> fuse(g gVar, int i7, k kVar) {
        return SharedFlowKt.fuseSharedFlow(this, gVar, i7, kVar);
    }

    @Override // kotlinx.coroutines.flow.SharedFlow
    public List<T> getReplayCache() {
        synchronized (this) {
            int replaySize = getReplaySize();
            if (replaySize == 0) {
                return p.h();
            }
            ArrayList arrayList = new ArrayList(replaySize);
            Object[] objArr = this.buffer;
            kotlin.jvm.internal.l.c(objArr);
            for (int i7 = 0; i7 < replaySize; i7++) {
                arrayList.add(SharedFlowKt.getBufferAt(objArr, this.replayIndex + ((long) i7)));
            }
            return arrayList;
        }
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public void resetReplayCache() {
        synchronized (this) {
            updateBufferLocked(getBufferEndIndex(), this.minCollectorIndex, getBufferEndIndex(), getQueueEndIndex());
            r rVar = r.f6563a;
        }
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public boolean tryEmit(T t6) {
        int i7;
        boolean z6;
        d<r>[] dVarArrFindSlotsToResumeLocked = AbstractSharedFlowKt.EMPTY_RESUMES;
        synchronized (this) {
            if (tryEmitLocked(t6)) {
                dVarArrFindSlotsToResumeLocked = findSlotsToResumeLocked(dVarArrFindSlotsToResumeLocked);
                z6 = true;
            } else {
                z6 = false;
            }
        }
        for (d<r> dVar : dVarArrFindSlotsToResumeLocked) {
            if (dVar != null) {
                r rVar = r.f6563a;
                l.a aVar = g5.l.f6557e;
                dVar.resumeWith(g5.l.a(rVar));
            }
        }
        return z6;
    }

    public final d<r>[] updateCollectorIndexLocked$kotlinx_coroutines_core(long j7) {
        long j8;
        long j9;
        long j10;
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        if (j7 > this.minCollectorIndex) {
            return AbstractSharedFlowKt.EMPTY_RESUMES;
        }
        long head = getHead();
        long j11 = ((long) this.bufferSize) + head;
        if (this.bufferCapacity == 0 && this.queueSize > 0) {
            j11++;
        }
        if (((AbstractSharedFlow) this).nCollectors != 0 && (abstractSharedFlowSlotArr = ((AbstractSharedFlow) this).slots) != null) {
            for (AbstractSharedFlowSlot abstractSharedFlowSlot : abstractSharedFlowSlotArr) {
                if (abstractSharedFlowSlot != null) {
                    long j12 = ((SharedFlowSlot) abstractSharedFlowSlot).index;
                    if (j12 >= 0 && j12 < j11) {
                        j11 = j12;
                    }
                }
            }
        }
        if (j11 <= this.minCollectorIndex) {
            return AbstractSharedFlowKt.EMPTY_RESUMES;
        }
        long bufferEndIndex = getBufferEndIndex();
        int iMin = getNCollectors() > 0 ? Math.min(this.queueSize, this.bufferCapacity - ((int) (bufferEndIndex - j11))) : this.queueSize;
        d<r>[] dVarArr = AbstractSharedFlowKt.EMPTY_RESUMES;
        long j13 = ((long) this.queueSize) + bufferEndIndex;
        if (iMin > 0) {
            dVarArr = new d[iMin];
            Object[] objArr = this.buffer;
            kotlin.jvm.internal.l.c(objArr);
            long j14 = bufferEndIndex;
            int i7 = 0;
            while (true) {
                if (bufferEndIndex >= j13) {
                    j8 = j11;
                    j9 = j13;
                    break;
                }
                Object bufferAt = SharedFlowKt.getBufferAt(objArr, bufferEndIndex);
                j8 = j11;
                c0 c0Var = SharedFlowKt.NO_VALUE;
                if (bufferAt != c0Var) {
                    Objects.requireNonNull(bufferAt, "null cannot be cast to non-null type kotlinx.coroutines.flow.SharedFlowImpl.Emitter");
                    Emitter emitter = (Emitter) bufferAt;
                    int i8 = i7 + 1;
                    j9 = j13;
                    dVarArr[i7] = emitter.cont;
                    SharedFlowKt.setBufferAt(objArr, bufferEndIndex, c0Var);
                    SharedFlowKt.setBufferAt(objArr, j14, emitter.value);
                    j10 = 1;
                    j14++;
                    if (i8 >= iMin) {
                        break;
                    }
                    i7 = i8;
                } else {
                    j9 = j13;
                    j10 = 1;
                }
                bufferEndIndex += j10;
                j11 = j8;
                j13 = j9;
            }
            bufferEndIndex = j14;
        } else {
            j8 = j11;
            j9 = j13;
        }
        int i9 = (int) (bufferEndIndex - head);
        long j15 = getNCollectors() == 0 ? bufferEndIndex : j8;
        long jMax = Math.max(this.replayIndex, bufferEndIndex - ((long) Math.min(this.replay, i9)));
        if (this.bufferCapacity == 0 && jMax < j9) {
            Object[] objArr2 = this.buffer;
            kotlin.jvm.internal.l.c(objArr2);
            if (kotlin.jvm.internal.l.a(SharedFlowKt.getBufferAt(objArr2, jMax), SharedFlowKt.NO_VALUE)) {
                bufferEndIndex++;
                jMax++;
            }
        }
        updateBufferLocked(jMax, j15, bufferEndIndex, j9);
        cleanupTailLocked();
        return (dVarArr.length == 0) ^ true ? findSlotsToResumeLocked(dVarArr) : dVarArr;
    }

    public final long updateNewCollectorIndexLocked$kotlinx_coroutines_core() {
        long j7 = this.replayIndex;
        if (j7 < this.minCollectorIndex) {
            this.minCollectorIndex = j7;
        }
        return j7;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public SharedFlowSlot createSlot() {
        return new SharedFlowSlot();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public SharedFlowSlot[] createSlotArray(int i7) {
        return new SharedFlowSlot[i7];
    }
}
