package com.youth.banner.util;

import android.content.res.Resources;
import android.graphics.Outline;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

/* JADX INFO: loaded from: classes2.dex */
public class BannerUtils {
    public static int dp2px(float f8) {
        return (int) TypedValue.applyDimension(1, f8, Resources.getSystem().getDisplayMetrics());
    }

    public static int getRealPosition(boolean z7, int i8, int i9) {
        if (!z7) {
            return i8;
        }
        if (i8 == 0) {
            return i9 - 1;
        }
        if (i8 == i9 + 1) {
            return 0;
        }
        return i8 - 1;
    }

    public static View getView(ViewGroup viewGroup, int i8) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(i8, viewGroup, false);
        ViewGroup.LayoutParams layoutParams = viewInflate.getLayoutParams();
        if (layoutParams.height != -1 || layoutParams.width != -1) {
            layoutParams.height = -1;
            layoutParams.width = -1;
            viewInflate.setLayoutParams(layoutParams);
        }
        return viewInflate;
    }

    public static void setBannerRound(View view, final float f8) {
        view.setOutlineProvider(new ViewOutlineProvider() { // from class: com.youth.banner.util.BannerUtils.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view2, Outline outline) {
                outline.setRoundRect(0, 0, view2.getWidth(), view2.getHeight(), f8);
            }
        });
        view.setClipToOutline(true);
    }
}
