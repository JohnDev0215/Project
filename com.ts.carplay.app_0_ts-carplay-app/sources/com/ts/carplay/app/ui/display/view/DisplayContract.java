package com.ts.carplay.app.ui.display.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.Surface;
import com.ts.carplay.app.ui.display.presenter.BasePresenter;

/* JADX INFO: loaded from: classes.dex */
public interface DisplayContract {

    public interface DisplayView {
        void onStatusChanged(int i);

        void requestVideoFocus(int i);
    }

    public interface Presenter extends BasePresenter {
        void hide();

        void hide(int i);

        void initManager(Context context);

        void release(Context context);

        void requestUi(int i);

        void screenTouchEvent(MotionEvent motionEvent);

        boolean show(Surface surface, int i);

        boolean show(Surface surface, int i, int i2);
    }
}
