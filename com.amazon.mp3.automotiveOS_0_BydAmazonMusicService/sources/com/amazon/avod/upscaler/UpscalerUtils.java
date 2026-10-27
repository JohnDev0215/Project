package com.amazon.avod.upscaler;

import android.content.Context;
import android.view.SurfaceView;
import com.amazon.avod.util.CastUtils;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class UpscalerUtils {
    private boolean mInitialized;

    @Nonnull
    public static UpscalerUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final UpscalerUtils INSTANCE = new UpscalerUtils();

        private SingletonHolder() {
        }
    }

    private UpscalerUtils() {
        this.mInitialized = false;
    }

    @Nullable
    public SurfaceView createUpscalerSurfaceViewByReflection(@Nonnull Context context) {
        try {
            return (SurfaceView) CastUtils.castTo(Class.forName("com.amazon.avod.upscaler.UpscalerSurfaceView").getConstructors()[0].newInstance(context), SurfaceView.class);
        } catch (ClassNotFoundException | Exception unused) {
            return null;
        }
    }
}
