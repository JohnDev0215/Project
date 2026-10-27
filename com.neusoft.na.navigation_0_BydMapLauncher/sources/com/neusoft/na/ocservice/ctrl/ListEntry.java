package com.neusoft.na.ocservice.ctrl;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActorServer.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/neusoft/na/ocservice/ctrl/ListEntry;", "", "component", "", "interfaces", "", "(Ljava/lang/String;Ljava/util/List;)V", "getComponent", "()Ljava/lang/String;", "getInterfaces", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final /* data */ class ListEntry {
    private final String component;
    private final List<String> interfaces;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ListEntry copy$default(ListEntry listEntry, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = listEntry.component;
        }
        if ((i & 2) != 0) {
            list = listEntry.interfaces;
        }
        return listEntry.copy(str, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getComponent() {
        return this.component;
    }

    public final List<String> component2() {
        return this.interfaces;
    }

    public final ListEntry copy(String component, List<String> interfaces) {
        Intrinsics.checkNotNullParameter(component, "component");
        Intrinsics.checkNotNullParameter(interfaces, "interfaces");
        return new ListEntry(component, interfaces);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ListEntry)) {
            return false;
        }
        ListEntry listEntry = (ListEntry) other;
        return Intrinsics.areEqual(this.component, listEntry.component) && Intrinsics.areEqual(this.interfaces, listEntry.interfaces);
    }

    public int hashCode() {
        return (this.component.hashCode() * 31) + this.interfaces.hashCode();
    }

    public String toString() {
        return "ListEntry(component=" + this.component + ", interfaces=" + this.interfaces + ')';
    }

    public ListEntry(String component, List<String> interfaces) {
        Intrinsics.checkNotNullParameter(component, "component");
        Intrinsics.checkNotNullParameter(interfaces, "interfaces");
        this.component = component;
        this.interfaces = interfaces;
    }

    public final String getComponent() {
        return this.component;
    }

    public final List<String> getInterfaces() {
        return this.interfaces;
    }
}
