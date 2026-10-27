package com.bugsnag.android;

/* JADX INFO: compiled from: BreadcrumbHooks.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"shouldDiscardNetworkBreadcrumb", "", "Lcom/bugsnag/android/Client;", "bugsnag-plugin-android-okhttp_release"}, k = 2, mv = {1, 4, 2})
public final class BreadcrumbHooksKt {
    public static final boolean shouldDiscardNetworkBreadcrumb(Client client) {
        return client.getConfig().shouldDiscardBreadcrumb(BreadcrumbType.REQUEST);
    }
}
