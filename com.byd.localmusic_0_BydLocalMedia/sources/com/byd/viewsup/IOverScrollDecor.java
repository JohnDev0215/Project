package com.byd.viewsup;

import android.view.View;
import android.view.animation.Interpolator;
import com.byd.viewsup.adapters.IOverScroller;

/* JADX INFO: loaded from: classes5.dex */
public interface IOverScrollDecor extends OverScrollUpdateListener, IOverScroller {
    void detach();

    int getCurrentState();

    @Override // com.byd.viewsup.adapters.IOverScroller
    View getView();

    void setCanRebound(boolean z);

    void setCustomInterpolator(Interpolator interpolator);

    void setMaxDistance(float f);

    void setMaxDuration(long j);
}
