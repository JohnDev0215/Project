package com.amazon.mp3.reactnative;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Route.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR$\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/reactnative/Route;", "", "", "toString", "", "hashCode", "other", "", "equals", "path", "Ljava/lang/String;", "getPath", "()Ljava/lang/String;", "presentationStyle", "getPresentationStyle", "regexRoute", "getRegexRoute", "setRegexRoute", "(Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class Route {
    private final String path;
    private final String presentationStyle;
    private String regexRoute;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Route)) {
            return false;
        }
        Route route = (Route) other;
        return Intrinsics.areEqual(this.path, route.path) && Intrinsics.areEqual(this.presentationStyle, route.presentationStyle) && Intrinsics.areEqual(this.regexRoute, route.regexRoute);
    }

    public int hashCode() {
        int iHashCode = ((this.path.hashCode() * 31) + this.presentationStyle.hashCode()) * 31;
        String str = this.regexRoute;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "Route(path=" + this.path + ", presentationStyle=" + this.presentationStyle + ", regexRoute=" + ((Object) this.regexRoute) + ')';
    }

    public final String getPath() {
        return this.path;
    }

    public final String getPresentationStyle() {
        return this.presentationStyle;
    }

    public final void setRegexRoute(String str) {
        this.regexRoute = str;
    }
}
