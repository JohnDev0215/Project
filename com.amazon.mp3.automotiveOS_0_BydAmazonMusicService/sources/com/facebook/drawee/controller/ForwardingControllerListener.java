package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public class ForwardingControllerListener<INFO> implements ControllerListener<INFO> {
    private final List<ControllerListener<? super INFO>> mListeners = new ArrayList(2);

    public synchronized void addListener(ControllerListener<? super INFO> listener) {
        this.mListeners.add(listener);
    }

    public synchronized void clearListeners() {
        this.mListeners.clear();
    }

    private synchronized void onException(String message, Throwable t) {
        Log.e("FdingControllerListener", message, t);
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onSubmit(String id, Object callerContext) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onSubmit(id, callerContext);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onSubmit", e);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onFinalImageSet(String id, @Nullable INFO imageInfo, @Nullable Animatable animatable) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onFinalImageSet(id, imageInfo, animatable);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onFinalImageSet", e);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public void onIntermediateImageSet(String id, @Nullable INFO imageInfo) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onIntermediateImageSet(id, imageInfo);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onIntermediateImageSet", e);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public void onIntermediateImageFailed(String id, Throwable throwable) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onIntermediateImageFailed(id, throwable);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onIntermediateImageFailed", e);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onFailure(String id, Throwable throwable) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onFailure(id, throwable);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onFailure", e);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onRelease(String id) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onRelease(id);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onRelease", e);
            }
        }
    }
}
