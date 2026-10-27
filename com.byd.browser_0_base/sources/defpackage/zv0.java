package defpackage;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.browser.feed.NativeViewListRenderer;
import org.chromium.chrome.browser.feed.f;

/* JADX INFO: compiled from: HybridListRenderer.java */
/* JADX INFO: loaded from: classes2.dex */
public interface zv0 {
    default RecyclerView d(f fVar) {
        NativeViewListRenderer nativeViewListRenderer = (NativeViewListRenderer) this;
        nativeViewListRenderer.i = fVar;
        Context context = nativeViewListRenderer.h;
        RecyclerView recyclerView = new RecyclerView(context);
        nativeViewListRenderer.k = recyclerView;
        recyclerView.setAdapter(nativeViewListRenderer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        nativeViewListRenderer.k.setLayoutManager(linearLayoutManager);
        ((f) nativeViewListRenderer.i).b.add(nativeViewListRenderer);
        nativeViewListRenderer.q(0, ((f) nativeViewListRenderer.i).d());
        nativeViewListRenderer.j = new NativeViewListRenderer.a(linearLayoutManager);
        return nativeViewListRenderer.k;
    }
}
