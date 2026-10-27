package com.byd.overseaappstore.module.ability.util.notify.router;

import com.byd.overseaappstore.module.ability.util.notify.router.gear.ExecutorNotPGearState;
import com.byd.overseaappstore.module.ability.util.notify.router.gear.NotPGearState;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.ExecutorIUUState;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import com.byd.overseaappstore.module.ability.util.notify.router.net.ExecutorNetContextState;
import com.byd.overseaappstore.module.ability.util.notify.router.net.NetContextState;
import com.byd.overseaappstore.module.ability.util.notify.router.storage.ExecutorStorageState;
import com.byd.overseaappstore.module.ability.util.notify.router.storage.StorageState;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.ct;
import spi.d;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0016\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0016J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\fJ\u001c\u0010\u001c\u001a\u00020\u001b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00160\u0015H\u0002J\u0006\u0010\u001e\u001a\u00020\u001bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00160\u00150\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/router/ExecutorDialogRouterMgr;", "", "executorNetContextState", "Lcom/byd/overseaappstore/module/ability/util/notify/router/net/ExecutorNetContextState;", "executorNotPGearState", "Lcom/byd/overseaappstore/module/ability/util/notify/router/gear/ExecutorNotPGearState;", "executorStorageState", "Lcom/byd/overseaappstore/module/ability/util/notify/router/storage/ExecutorStorageState;", "executorIuuState", "Lcom/byd/overseaappstore/module/ability/util/notify/router/iuu/ExecutorIUUState;", "(Lcom/byd/overseaappstore/module/ability/util/notify/router/net/ExecutorNetContextState;Lcom/byd/overseaappstore/module/ability/util/notify/router/gear/ExecutorNotPGearState;Lcom/byd/overseaappstore/module/ability/util/notify/router/storage/ExecutorStorageState;Lcom/byd/overseaappstore/module/ability/util/notify/router/iuu/ExecutorIUUState;)V", "currentShowingBizType", "", "getExecutorIuuState", "()Lcom/byd/overseaappstore/module/ability/util/notify/router/iuu/ExecutorIUUState;", "getExecutorNetContextState", "()Lcom/byd/overseaappstore/module/ability/util/notify/router/net/ExecutorNetContextState;", "getExecutorStorageState", "()Lcom/byd/overseaappstore/module/ability/util/notify/router/storage/ExecutorStorageState;", "queue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lkotlin/Pair;", "Lcom/byd/overseaappstore/module/ability/util/notify/router/BaseDialogData;", "addBizTypeAndData", "bizType", "data", "updateCurrentShowingBizType", "", "workExecute", "pair", "workNotify", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExecutorDialogRouterMgr {
    private int currentShowingBizType;

    @NotNull
    private final ExecutorIUUState executorIuuState;

    @NotNull
    private final ExecutorNetContextState executorNetContextState;

    @NotNull
    private final ExecutorNotPGearState executorNotPGearState;

    @NotNull
    private final ExecutorStorageState executorStorageState;

    @NotNull
    private ConcurrentLinkedQueue<Pair<Integer, BaseDialogData>> queue;

    public ExecutorDialogRouterMgr(@NotNull ExecutorNetContextState executorNetContextState, @NotNull ExecutorNotPGearState executorNotPGearState, @NotNull ExecutorStorageState executorStorageState, @NotNull ExecutorIUUState executorIuuState) {
        Intrinsics.checkNotNullParameter(executorNetContextState, "executorNetContextState");
        Intrinsics.checkNotNullParameter(executorNotPGearState, "executorNotPGearState");
        Intrinsics.checkNotNullParameter(executorStorageState, "executorStorageState");
        Intrinsics.checkNotNullParameter(executorIuuState, "executorIuuState");
        this.executorNetContextState = executorNetContextState;
        this.executorNotPGearState = executorNotPGearState;
        this.executorStorageState = executorStorageState;
        this.executorIuuState = executorIuuState;
        this.queue = new ConcurrentLinkedQueue<>();
    }

    private final void workExecute(Pair<Integer, ? extends BaseDialogData> pair) {
        int iIntValue = pair.getFirst().intValue();
        if (iIntValue == 0) {
            int size = this.queue.size();
            int i = this.currentShowingBizType;
            StringBuilder sbL = e.l("executor dialogBizRouter work NULL unknown queue.size:", size, " bizType:", iIntValue, " currentShowingBizType:");
            sbL.append(i);
            ct.b(sbL.toString(), new Object[0]);
            updateCurrentShowingBizType(0);
            return;
        }
        if (iIntValue != 1) {
            if (iIntValue != 2) {
                if (iIntValue != 3) {
                    if (iIntValue != 4) {
                        return;
                    }
                    if (!(pair.getSecond() instanceof NotPGearState)) {
                        int size2 = this.queue.size();
                        int i2 = this.currentShowingBizType;
                        StringBuilder sbL2 = e.l("executor dialogBizRouter work NOT_P_GEAR dataNotCompat queue.size:", size2, " bizType:", iIntValue, " currentShowingBizType:");
                        sbL2.append(i2);
                        ct.b(sbL2.toString(), new Object[0]);
                        return;
                    }
                    this.executorNotPGearState.notifyNotPGearStateData((NotPGearState) pair.getSecond());
                    int size3 = this.queue.size();
                    int i3 = this.currentShowingBizType;
                    StringBuilder sbL3 = e.l("executor dialogBizRouter work NOT_P_GEAR notify queue.size:", size3, " bizType:", iIntValue, " currentShowingBizType:");
                    sbL3.append(i3);
                    ct.a(sbL3.toString(), new Object[0]);
                    return;
                }
                if (pair.getSecond() instanceof IUUState) {
                    this.executorIuuState.notifyIUUStateData((IUUState) pair.getSecond());
                    int size4 = this.queue.size();
                    int i4 = this.currentShowingBizType;
                    StringBuilder sbL4 = e.l("executor dialogBizRouter work IUU notify queue.size:", size4, " bizType:", iIntValue, " currentShowingBizType:");
                    sbL4.append(i4);
                    ct.a(sbL4.toString(), new Object[0]);
                } else {
                    int size5 = this.queue.size();
                    int i5 = this.currentShowingBizType;
                    StringBuilder sbL5 = e.l("executor dialogBizRouter work IUU dataNotCompat queue.size:", size5, " bizType:", iIntValue, " currentShowingBizType:");
                    sbL5.append(i5);
                    ct.b(sbL5.toString(), new Object[0]);
                }
            } else if (pair.getSecond() instanceof StorageState) {
                this.executorStorageState.notifyNetContextStateData((StorageState) pair.getSecond());
                int size6 = this.queue.size();
                int i6 = this.currentShowingBizType;
                StringBuilder sbL6 = e.l("executor dialogBizRouter work STORAGE_LEAK notify queue.size:", size6, " bizType:", iIntValue, " currentShowingBizType:");
                sbL6.append(i6);
                ct.a(sbL6.toString(), new Object[0]);
            } else {
                int size7 = this.queue.size();
                int i7 = this.currentShowingBizType;
                StringBuilder sbL7 = e.l("executor dialogBizRouter work STORAGE_LEAK dataNotCompat queue.size:", size7, " bizType:", iIntValue, " currentShowingBizType:");
                sbL7.append(i7);
                ct.b(sbL7.toString(), new Object[0]);
            }
        } else if (pair.getSecond() instanceof NetContextState) {
            this.executorNetContextState.notifyNetContextStateData((NetContextState) pair.getSecond());
            int size8 = this.queue.size();
            int i8 = this.currentShowingBizType;
            StringBuilder sbL8 = e.l("executor dialogBizRouter work WIFI_WORK_ONLY notify queue.size:", size8, " bizType:", iIntValue, " currentShowingBizType:");
            sbL8.append(i8);
            ct.a(sbL8.toString(), new Object[0]);
        } else {
            int size9 = this.queue.size();
            int i9 = this.currentShowingBizType;
            StringBuilder sbL9 = e.l("executor dialogBizRouter work WIFI_WORK_ONLY dataNotCompat queue.size:", size9, " bizType:", iIntValue, " currentShowingBizType:");
            sbL9.append(i9);
            ct.b(sbL9.toString(), new Object[0]);
        }
        updateCurrentShowingBizType(0);
        workNotify();
    }

    @NotNull
    public final ExecutorDialogRouterMgr addBizTypeAndData(int bizType, @NotNull BaseDialogData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.queue.add(new Pair<>(Integer.valueOf(bizType), data));
        ct.a(d.c("executor dialogBizRouter addBizTypeAndData bizType:", bizType, " currentShowingBizType:", this.currentShowingBizType), new Object[0]);
        return this;
    }

    @NotNull
    public final ExecutorIUUState getExecutorIuuState() {
        return this.executorIuuState;
    }

    @NotNull
    public final ExecutorNetContextState getExecutorNetContextState() {
        return this.executorNetContextState;
    }

    @NotNull
    public final ExecutorStorageState getExecutorStorageState() {
        return this.executorStorageState;
    }

    public final void updateCurrentShowingBizType(int bizType) {
        ct.a(d.c("executor dialogBizRouter updateCurrentShowingBizType  toUpdateBizType:", bizType, " currentShowingBizType:", this.currentShowingBizType), new Object[0]);
        this.currentShowingBizType = bizType;
    }

    public final void workNotify() {
        Object next;
        if (this.queue.isEmpty()) {
            ct.b(e.c("executor dialogBizRouter workNotify queue is empty currentShowingBizType:", this.currentShowingBizType), new Object[0]);
            return;
        }
        Iterator<T> it = this.queue.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((Number) ((Pair) next).getFirst()).intValue() == 4) {
                    break;
                }
            }
        }
        Pair<Integer, ? extends BaseDialogData> pair = (Pair) next;
        if (pair != null) {
            if (((NotPGearState) pair.getSecond()).getNotPGearState()) {
                updateCurrentShowingBizType(0);
            } else {
                updateCurrentShowingBizType(4);
            }
            this.queue.remove(pair);
            workExecute(pair);
            ct.a(d.c("executor dialogBizRouter work notify removed P queue.size:", this.queue.size(), " currentShowingBizType:", this.currentShowingBizType), new Object[0]);
        }
        ct.a(d.c("executor dialogBizRouter work notify added queue.size:", this.queue.size(), " currentShowingBizType:", this.currentShowingBizType), new Object[0]);
        if (this.currentShowingBizType != 4) {
            Pair<Integer, BaseDialogData> pairPoll = this.queue.poll();
            if (pairPoll == null) {
                ct.b(d.c("executor dialogBizRouter workNotify queue.size:", this.queue.size(), " pollPair is null currentShowingBizType:", this.currentShowingBizType), new Object[0]);
                return;
            }
            ct.a(d.c("executor dialogBizRouter work notify removed queue.size:", this.queue.size(), " currentShowingBizType:", this.currentShowingBizType), new Object[0]);
            updateCurrentShowingBizType(pairPoll.getFirst().intValue());
            workExecute(pairPoll);
        }
    }
}
