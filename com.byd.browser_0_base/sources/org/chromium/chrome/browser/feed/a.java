package org.chromium.chrome.browser.feed;

import J.N;
import android.app.Activity;
import android.graphics.Rect;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.j81;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.chromium.chrome.browser.feed.FeedStream;

/* JADX INFO: compiled from: FeedSliceViewTracker.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements ViewTreeObserver.OnPreDrawListener {
    public final Activity e;
    public RecyclerView f;
    public f g;
    public j81 h;
    public boolean j;
    public InterfaceC0083a k;
    public long m;
    public final HashSet<String> i = new HashSet<>();
    public HashMap<String, ArrayList<b>> l = new HashMap<>();

    /* JADX INFO: renamed from: org.chromium.chrome.browser.feed.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FeedSliceViewTracker.java */
    public interface InterfaceC0083a {
    }

    /* JADX INFO: compiled from: FeedSliceViewTracker.java */
    public class b {
    }

    public a(RecyclerView recyclerView, Activity activity, f fVar, j81 j81Var, FeedStream.h hVar) {
        this.e = activity;
        this.f = recyclerView;
        this.g = fVar;
        this.h = j81Var;
        this.k = hVar;
    }

    public final int a(View view) {
        Rect rect = new Rect(0, 0, view.getWidth(), view.getHeight());
        if (!this.f.getChildVisibleRect(view, rect, null)) {
            return 0;
        }
        return rect.height() * rect.width();
    }

    public final boolean b(View view, float f) {
        int height = view.getHeight() * view.getWidth();
        return height > 0 && ((float) a(view)) / ((float) height) >= f;
    }

    public final void c() {
        if (this.m != 0) {
            InterfaceC0083a interfaceC0083a = this.k;
            long jElapsedRealtime = SystemClock.elapsedRealtime() - this.m;
            FeedStream feedStream = FeedStream.this;
            N.Mbwu_g37(feedStream.c, feedStream, jElapsedRealtime);
            this.m = 0L;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0100  */
    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onPreDraw() {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.feed.a.onPreDraw():boolean");
    }
}
