package defpackage;

import android.content.Context;
import android.view.GestureDetector;

/* JADX INFO: compiled from: GestureDetectorCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class is0 {
    public final b a;

    /* JADX INFO: compiled from: GestureDetectorCompat.java */
    public interface a {
        void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener);
    }

    /* JADX INFO: compiled from: GestureDetectorCompat.java */
    public static class b implements a {
        public final GestureDetector a;

        public b(Context context, GestureDetector.SimpleOnGestureListener simpleOnGestureListener) {
            this.a = new GestureDetector(context, simpleOnGestureListener, null);
        }

        @Override // is0.a
        public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            this.a.setOnDoubleTapListener(onDoubleTapListener);
        }
    }

    public is0(Context context, GestureDetector.SimpleOnGestureListener simpleOnGestureListener) {
        this.a = new b(context, simpleOnGestureListener);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.a.setOnDoubleTapListener(onDoubleTapListener);
    }
}
