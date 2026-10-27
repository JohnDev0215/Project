package org.chromium.components.navigation_interception;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
public class NavigationParams {
    public final boolean hasUserGesture;
    public final boolean hasUserGestureCarryover;
    public final boolean isExternalProtocol;
    public final boolean isMainFrame;
    public final boolean isPost;
    public final boolean isRedirect;
    public final int pageTransitionType;
    public final String referrer;
    public final String url;

    public NavigationParams(String str, String str2, boolean z, boolean z2, int i, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.url = str;
        this.referrer = TextUtils.isEmpty(str2) ? null : str2;
        this.isPost = z;
        this.hasUserGesture = z2;
        this.pageTransitionType = i;
        this.isRedirect = z3;
        this.isExternalProtocol = z4;
        this.isMainFrame = z5;
        this.hasUserGestureCarryover = z6;
    }

    public static NavigationParams create(String str, String str2, boolean z, boolean z2, int i, boolean z3, boolean z4, boolean z5, boolean z6) {
        return new NavigationParams(str, str2, z, z2, i, z3, z4, z5, z6);
    }
}
