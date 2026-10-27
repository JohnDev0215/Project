package com.facebook.drawee.interfaces;

import android.view.MotionEvent;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public interface DraweeController {
    @Nullable
    DraweeHierarchy getHierarchy();

    void onAttach();

    void onDetach();

    boolean onTouchEvent(MotionEvent event);

    void setHierarchy(@Nullable DraweeHierarchy hierarchy);
}
