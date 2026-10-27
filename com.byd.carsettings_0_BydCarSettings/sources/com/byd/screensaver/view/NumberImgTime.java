package com.byd.screensaver.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class NumberImgTime extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int[] f3875a = {R.drawable.img_center_off_screen_number_0, R.drawable.img_center_off_screen_number_1, R.drawable.img_center_off_screen_number_2, R.drawable.img_center_off_screen_number_3, R.drawable.img_center_off_screen_number_4, R.drawable.img_center_off_screen_number_5, R.drawable.img_center_off_screen_number_6, R.drawable.img_center_off_screen_number_7, R.drawable.img_center_off_screen_number_8, R.drawable.img_center_off_screen_number_9};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ImageView f3876b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ImageView f3877c;

    public NumberImgTime(@NonNull Context context) {
        super(context);
        a(context);
    }

    public final void a(Context context) {
        setLayoutDirection(0);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_number_time, (ViewGroup) null);
        this.f3876b = (ImageView) viewInflate.findViewById(R.id.iv_number_0);
        this.f3877c = (ImageView) viewInflate.findViewById(R.id.iv_number_1);
        addView(viewInflate);
    }

    public void setTime(int i) {
        int i2 = i / 10;
        int i3 = i % 10;
        if (i2 >= 0) {
            int[] iArr = f3875a;
            if (i2 < iArr.length) {
                this.f3876b.setImageResource(iArr[i2]);
            }
        }
        if (i3 >= 0) {
            int[] iArr2 = f3875a;
            if (i3 < iArr2.length) {
                this.f3877c.setImageResource(iArr2[i3]);
            }
        }
    }

    public NumberImgTime(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public NumberImgTime(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }
}
