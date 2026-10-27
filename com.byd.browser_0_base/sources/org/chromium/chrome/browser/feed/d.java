package org.chromium.chrome.browser.feed;

import defpackage.yg2;
import defpackage.zk0;
import java.util.ArrayList;
import org.chromium.chrome.browser.feed.g;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: FeedSurfaceMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements g.a {
    public final /* synthetic */ PropertyModel a;
    public final /* synthetic */ g b;
    public final /* synthetic */ e c;

    public d(e eVar, PropertyModel propertyModel, g gVar) {
        this.c = eVar;
        this.a = propertyModel;
        this.b = gVar;
    }

    @Override // org.chromium.chrome.browser.feed.g.a
    public final void a(ArrayList arrayList) {
        if (arrayList.size() > this.c.x + 1) {
            this.a.l(yg2.f, true);
            zk0.b().f("webfeed.last_badge_animation_time", "" + System.currentTimeMillis());
            this.b.c(this);
        }
    }
}
