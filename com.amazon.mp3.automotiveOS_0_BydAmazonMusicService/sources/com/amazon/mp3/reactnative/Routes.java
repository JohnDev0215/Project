package com.amazon.mp3.reactnative;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Routes.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/reactnative/Routes;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/mp3/reactnative/Route;", "routes", "Ljava/util/List;", "getRoutes", "()Ljava/util/List;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class Routes {
    private final List<Route> routes;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Routes) && Intrinsics.areEqual(this.routes, ((Routes) other).routes);
    }

    public int hashCode() {
        return this.routes.hashCode();
    }

    public String toString() {
        return "Routes(routes=" + this.routes + ')';
    }

    public final List<Route> getRoutes() {
        return this.routes;
    }
}
