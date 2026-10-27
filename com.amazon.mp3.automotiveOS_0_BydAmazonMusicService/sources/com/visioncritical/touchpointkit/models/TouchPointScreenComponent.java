package com.visioncritical.touchpointkit.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TouchPointTrigger.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f\"\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/visioncritical/touchpointkit/models/TouchPointScreenComponent;", "", "", "toString", "", "hashCode", "other", "", "equals", "screenName", "Ljava/lang/String;", "getScreenName", "()Ljava/lang/String;", "componentName", "getComponentName", "setComponentName", "(Ljava/lang/String;)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "touchpointkit_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class TouchPointScreenComponent {
    private String componentName;
    private final String screenName;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TouchPointScreenComponent)) {
            return false;
        }
        TouchPointScreenComponent touchPointScreenComponent = (TouchPointScreenComponent) other;
        return Intrinsics.areEqual(this.screenName, touchPointScreenComponent.screenName) && Intrinsics.areEqual(this.componentName, touchPointScreenComponent.componentName);
    }

    public int hashCode() {
        int iHashCode = this.screenName.hashCode() * 31;
        String str = this.componentName;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "TouchPointScreenComponent(screenName=" + this.screenName + ", componentName=" + ((Object) this.componentName) + ')';
    }

    public TouchPointScreenComponent(String screenName, String str) {
        Intrinsics.checkNotNullParameter(screenName, "screenName");
        this.screenName = screenName;
        this.componentName = str;
    }

    public /* synthetic */ TouchPointScreenComponent(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2);
    }

    public final String getScreenName() {
        return this.screenName;
    }

    public final String getComponentName() {
        return this.componentName;
    }

    public final void setComponentName(String str) {
        this.componentName = str;
    }
}
