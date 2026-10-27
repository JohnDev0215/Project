package com.th3rdwave.safeareacontext;

import java.util.EnumSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeAreaViewLocalData.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001b"}, d2 = {"Lcom/th3rdwave/safeareacontext/SafeAreaViewLocalData;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/th3rdwave/safeareacontext/EdgeInsets;", "insets", "Lcom/th3rdwave/safeareacontext/EdgeInsets;", "getInsets", "()Lcom/th3rdwave/safeareacontext/EdgeInsets;", "Lcom/th3rdwave/safeareacontext/SafeAreaViewMode;", "mode", "Lcom/th3rdwave/safeareacontext/SafeAreaViewMode;", "getMode", "()Lcom/th3rdwave/safeareacontext/SafeAreaViewMode;", "Ljava/util/EnumSet;", "Lcom/th3rdwave/safeareacontext/SafeAreaViewEdges;", "edges", "Ljava/util/EnumSet;", "getEdges", "()Ljava/util/EnumSet;", "<init>", "(Lcom/th3rdwave/safeareacontext/EdgeInsets;Lcom/th3rdwave/safeareacontext/SafeAreaViewMode;Ljava/util/EnumSet;)V", "react-native-safe-area-context_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class SafeAreaViewLocalData {
    private final EnumSet<SafeAreaViewEdges> edges;
    private final EdgeInsets insets;
    private final SafeAreaViewMode mode;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SafeAreaViewLocalData)) {
            return false;
        }
        SafeAreaViewLocalData safeAreaViewLocalData = (SafeAreaViewLocalData) other;
        return Intrinsics.areEqual(this.insets, safeAreaViewLocalData.insets) && this.mode == safeAreaViewLocalData.mode && Intrinsics.areEqual(this.edges, safeAreaViewLocalData.edges);
    }

    public int hashCode() {
        return (((this.insets.hashCode() * 31) + this.mode.hashCode()) * 31) + this.edges.hashCode();
    }

    public String toString() {
        return "SafeAreaViewLocalData(insets=" + this.insets + ", mode=" + this.mode + ", edges=" + this.edges + ')';
    }

    public SafeAreaViewLocalData(EdgeInsets insets, SafeAreaViewMode mode, EnumSet<SafeAreaViewEdges> edges) {
        Intrinsics.checkNotNullParameter(insets, "insets");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(edges, "edges");
        this.insets = insets;
        this.mode = mode;
        this.edges = edges;
    }

    public final EdgeInsets getInsets() {
        return this.insets;
    }

    public final SafeAreaViewMode getMode() {
        return this.mode;
    }

    public final EnumSet<SafeAreaViewEdges> getEdges() {
        return this.edges;
    }
}
