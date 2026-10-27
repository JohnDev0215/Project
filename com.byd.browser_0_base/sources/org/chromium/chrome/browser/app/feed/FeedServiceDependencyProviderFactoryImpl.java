package org.chromium.chrome.browser.app.feed;

import defpackage.a8;
import defpackage.jl0;
import org.chromium.build.annotations.UsedByReflection;

/* JADX INFO: loaded from: classes.dex */
@UsedByReflection
public class FeedServiceDependencyProviderFactoryImpl implements jl0 {
    public static FeedServiceDependencyProviderFactoryImpl a;

    @UsedByReflection
    public static jl0 getInstance() {
        if (a == null) {
            a = new FeedServiceDependencyProviderFactoryImpl();
        }
        return a;
    }

    @Override // defpackage.jl0
    public final a8 a() {
        return new a8();
    }
}
