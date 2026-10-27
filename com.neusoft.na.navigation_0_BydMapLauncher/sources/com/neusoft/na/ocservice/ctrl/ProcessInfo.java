package com.neusoft.na.ocservice.ctrl;

import com.neusoft.na.common.BasicLifeCycleController;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OnOffMasterController.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/neusoft/na/ocservice/ctrl/ProcessInfo;", "", "lifeCycleController", "Lcom/neusoft/na/common/BasicLifeCycleController;", "shouldRun", "", "(Lcom/neusoft/na/common/BasicLifeCycleController;Z)V", "getLifeCycleController", "()Lcom/neusoft/na/common/BasicLifeCycleController;", "getShouldRun", "()Z", "setShouldRun", "(Z)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final /* data */ class ProcessInfo {
    private final BasicLifeCycleController lifeCycleController;
    private boolean shouldRun;

    public static /* synthetic */ ProcessInfo copy$default(ProcessInfo processInfo, BasicLifeCycleController basicLifeCycleController, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            basicLifeCycleController = processInfo.lifeCycleController;
        }
        if ((i & 2) != 0) {
            z = processInfo.shouldRun;
        }
        return processInfo.copy(basicLifeCycleController, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final BasicLifeCycleController getLifeCycleController() {
        return this.lifeCycleController;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getShouldRun() {
        return this.shouldRun;
    }

    public final ProcessInfo copy(BasicLifeCycleController lifeCycleController, boolean shouldRun) {
        Intrinsics.checkNotNullParameter(lifeCycleController, "lifeCycleController");
        return new ProcessInfo(lifeCycleController, shouldRun);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProcessInfo)) {
            return false;
        }
        ProcessInfo processInfo = (ProcessInfo) other;
        return Intrinsics.areEqual(this.lifeCycleController, processInfo.lifeCycleController) && this.shouldRun == processInfo.shouldRun;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    public int hashCode() {
        int iHashCode = this.lifeCycleController.hashCode() * 31;
        boolean z = this.shouldRun;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "ProcessInfo(lifeCycleController=" + this.lifeCycleController + ", shouldRun=" + this.shouldRun + ')';
    }

    public ProcessInfo(BasicLifeCycleController lifeCycleController, boolean z) {
        Intrinsics.checkNotNullParameter(lifeCycleController, "lifeCycleController");
        this.lifeCycleController = lifeCycleController;
        this.shouldRun = z;
    }

    public final BasicLifeCycleController getLifeCycleController() {
        return this.lifeCycleController;
    }

    public final boolean getShouldRun() {
        return this.shouldRun;
    }

    public final void setShouldRun(boolean z) {
        this.shouldRun = z;
    }
}
