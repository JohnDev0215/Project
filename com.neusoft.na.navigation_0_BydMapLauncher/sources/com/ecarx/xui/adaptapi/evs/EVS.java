package com.ecarx.xui.adaptapi.evs;

import android.content.Context;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public abstract class EVS extends AdaptAPI {
    static final int EVS_CAMERA_REAR = 1;
    private Context context;
    int currentCameraId = 1;

    @Retention(RetentionPolicy.SOURCE)
    @interface EvsCameraId {
    }

    public interface IEvsCameraStatusObserver {
        void onEvsCameraClosed(int i);

        void onEvsCameraOpened(int i);
    }

    public abstract boolean attachEvsCameraStatusObserver(IEvsCameraStatusObserver iEvsCameraStatusObserver);

    public abstract boolean detachEvsCameraStatusObserver(IEvsCameraStatusObserver iEvsCameraStatusObserver);

    public abstract boolean isCameraOpened(int i);

    public EVS(Context context) {
        this.context = context;
    }

    public static EVS create(Context context) {
        return new ReaLize(context);
    }
}
