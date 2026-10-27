package org.chromium.chrome.browser.feed;

import J.N;
import android.util.TypedValue;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.rh3;
import defpackage.tf2;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.feed.FeedStream;

/* JADX INFO: compiled from: FeedStream.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends RecyclerView.q {
    public final /* synthetic */ FeedStream e;

    public b(FeedStream feedStream) {
        this.e = feedStream;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public final void b(RecyclerView recyclerView, int i, int i2) {
        FeedStream feedStream = this.e;
        if (feedStream.x != null) {
            int i3 = feedStream.m + i2;
            feedStream.m = i3;
            if (i3 < 0) {
                feedStream.m = 0;
            }
            if (feedStream.m >= TypedValue.applyDimension(1, feedStream.a, feedStream.w.getResources().getDisplayMetrics()) && feedStream.l(feedStream.n)) {
                feedStream.m = 0;
            }
        }
        N.MI0NT3kQ(feedStream.c, feedStream);
        FeedStream.f fVar = feedStream.s;
        if (i2 == 0) {
            fVar.getClass();
            return;
        }
        int i4 = fVar.c;
        if (i4 != 0) {
            if ((i2 > 0) != (i4 > 0)) {
                fVar.a();
            }
        }
        fVar.c += i2;
        if (fVar.a != null) {
            fVar.b = false;
            return;
        }
        fVar.b = true;
        tf2.a aVar = new tf2.a(fVar);
        fVar.a = aVar;
        PostTask.b(rh3.a, aVar, 200L);
    }
}
