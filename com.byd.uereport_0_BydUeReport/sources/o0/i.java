package o0;

import android.view.MotionEvent;

/* JADX INFO: compiled from: MotionEventCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static boolean a(MotionEvent motionEvent, int i8) {
        return (motionEvent.getSource() & i8) == i8;
    }
}
