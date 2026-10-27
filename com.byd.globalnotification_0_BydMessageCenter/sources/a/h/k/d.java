package a.h.k;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f812a;

    public interface a {
        boolean a(MotionEvent motionEvent);
    }

    public static class b implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final GestureDetector f813a;

        public b(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            this.f813a = new GestureDetector(context, onGestureListener, handler);
        }

        @Override // a.h.k.d.a
        public boolean a(MotionEvent motionEvent) {
            return this.f813a.onTouchEvent(motionEvent);
        }
    }

    public d(Context context, GestureDetector.OnGestureListener onGestureListener) {
        this.f812a = new b(context, onGestureListener, null);
    }
}
