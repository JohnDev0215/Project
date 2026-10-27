package com.byd.overseaappstore.module.ability.util.act;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\f¨\u0006!"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/act/AssistActState;", "", "create", "", "resume", "stop", "saveInstanceState", "destroy", "(ZZZZZ)V", "getCreate", "()Z", "setCreate", "(Z)V", "getDestroy", "setDestroy", "getResume", "setResume", "getSaveInstanceState", "setSaveInstanceState", "getStop", "setStop", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class AssistActState {
    private boolean create;
    private boolean destroy;
    private boolean resume;
    private boolean saveInstanceState;
    private boolean stop;

    public AssistActState() {
        this(false, false, false, false, false, 31, null);
    }

    public AssistActState(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.create = z;
        this.resume = z2;
        this.stop = z3;
        this.saveInstanceState = z4;
        this.destroy = z5;
    }

    public /* synthetic */ AssistActState(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4, (i & 16) != 0 ? false : z5);
    }

    public static /* synthetic */ AssistActState copy$default(AssistActState assistActState, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, Object obj) {
        if ((i & 1) != 0) {
            z = assistActState.create;
        }
        if ((i & 2) != 0) {
            z2 = assistActState.resume;
        }
        boolean z6 = z2;
        if ((i & 4) != 0) {
            z3 = assistActState.stop;
        }
        boolean z7 = z3;
        if ((i & 8) != 0) {
            z4 = assistActState.saveInstanceState;
        }
        boolean z8 = z4;
        if ((i & 16) != 0) {
            z5 = assistActState.destroy;
        }
        return assistActState.copy(z, z6, z7, z8, z5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getCreate() {
        return this.create;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getResume() {
        return this.resume;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getStop() {
        return this.stop;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getSaveInstanceState() {
        return this.saveInstanceState;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getDestroy() {
        return this.destroy;
    }

    @NotNull
    public final AssistActState copy(boolean create, boolean resume, boolean stop, boolean saveInstanceState, boolean destroy) {
        return new AssistActState(create, resume, stop, saveInstanceState, destroy);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssistActState)) {
            return false;
        }
        AssistActState assistActState = (AssistActState) other;
        return this.create == assistActState.create && this.resume == assistActState.resume && this.stop == assistActState.stop && this.saveInstanceState == assistActState.saveInstanceState && this.destroy == assistActState.destroy;
    }

    public final boolean getCreate() {
        return this.create;
    }

    public final boolean getDestroy() {
        return this.destroy;
    }

    public final boolean getResume() {
        return this.resume;
    }

    public final boolean getSaveInstanceState() {
        return this.saveInstanceState;
    }

    public final boolean getStop() {
        return this.stop;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v5, types: [int] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public int hashCode() {
        boolean z = this.create;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.resume;
        ?? r2 = z2;
        if (z2) {
            r2 = 1;
        }
        int i2 = (i + r2) * 31;
        boolean z3 = this.stop;
        ?? r22 = z3;
        if (z3) {
            r22 = 1;
        }
        int i3 = (i2 + r22) * 31;
        boolean z4 = this.saveInstanceState;
        ?? r23 = z4;
        if (z4) {
            r23 = 1;
        }
        int i4 = (i3 + r23) * 31;
        boolean z5 = this.destroy;
        return i4 + (z5 ? 1 : z5);
    }

    public final void setCreate(boolean z) {
        this.create = z;
    }

    public final void setDestroy(boolean z) {
        this.destroy = z;
    }

    public final void setResume(boolean z) {
        this.resume = z;
    }

    public final void setSaveInstanceState(boolean z) {
        this.saveInstanceState = z;
    }

    public final void setStop(boolean z) {
        this.stop = z;
    }

    @NotNull
    public String toString() {
        return "AssistActState(create=" + this.create + ", resume=" + this.resume + ", stop=" + this.stop + ", saveInstanceState=" + this.saveInstanceState + ", destroy=" + this.destroy + ")";
    }
}
