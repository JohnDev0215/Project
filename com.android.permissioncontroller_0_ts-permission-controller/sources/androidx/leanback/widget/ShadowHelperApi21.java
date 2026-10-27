package androidx.leanback.widget;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

/* JADX INFO: loaded from: classes3.dex */
class ShadowHelperApi21 {
    static final ViewOutlineProvider sOutlineProvider = new ViewOutlineProvider() { // from class: androidx.leanback.widget.ShadowHelperApi21.1
        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRect(0, 0, view.getWidth(), view.getHeight());
            outline.setAlpha(1.0f);
        }
    };

    static class ShadowImpl {
        float mFocusedZ;
        float mNormalZ;
        View mShadowContainer;

        ShadowImpl() {
        }
    }

    public static Object addDynamicShadow(View shadowContainer, float unfocusedZ, float focusedZ, int roundedCornerRadius) {
        if (roundedCornerRadius > 0) {
            RoundedRectHelperApi21.setClipToRoundedOutline(shadowContainer, true, roundedCornerRadius);
        } else {
            shadowContainer.setOutlineProvider(sOutlineProvider);
        }
        ShadowImpl impl = new ShadowImpl();
        impl.mShadowContainer = shadowContainer;
        impl.mNormalZ = unfocusedZ;
        impl.mFocusedZ = focusedZ;
        shadowContainer.setZ(impl.mNormalZ);
        return impl;
    }

    public static void setShadowFocusLevel(Object object, float level) {
        ShadowImpl impl = (ShadowImpl) object;
        impl.mShadowContainer.setZ(impl.mNormalZ + ((impl.mFocusedZ - impl.mNormalZ) * level));
    }

    private ShadowHelperApi21() {
    }
}
