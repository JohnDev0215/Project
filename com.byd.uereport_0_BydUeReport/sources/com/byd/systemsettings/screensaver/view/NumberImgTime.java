package com.byd.systemsettings.screensaver.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.byd.systemsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class NumberImgTime extends FrameLayout {
    private static int[] NUM_IMG_IDS = {R.drawable.img_center_off_screen_number_0, R.drawable.img_center_off_screen_number_1, R.drawable.img_center_off_screen_number_2, R.drawable.img_center_off_screen_number_3, R.drawable.img_center_off_screen_number_4, R.drawable.img_center_off_screen_number_5, R.drawable.img_center_off_screen_number_6, R.drawable.img_center_off_screen_number_7, R.drawable.img_center_off_screen_number_8, R.drawable.img_center_off_screen_number_9};
    private ImageView iv0;
    private ImageView iv1;

    public NumberImgTime(Context context) {
        super(context);
        initViews(context);
    }

    private void initViews(Context context) {
        setLayoutDirection(0);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_number_time, (ViewGroup) null);
        this.iv0 = (ImageView) viewInflate.findViewById(R.id.iv_number_0);
        this.iv1 = (ImageView) viewInflate.findViewById(R.id.iv_number_1);
        addView(viewInflate);
    }

    public void setTime(int i8) {
        int i9 = i8 / 10;
        int i10 = i8 % 10;
        if (i9 >= 0) {
            int[] iArr = NUM_IMG_IDS;
            if (i9 < iArr.length) {
                this.iv0.setImageResource(iArr[i9]);
            }
        }
        if (i10 >= 0) {
            int[] iArr2 = NUM_IMG_IDS;
            if (i10 < iArr2.length) {
                this.iv1.setImageResource(iArr2[i10]);
            }
        }
    }

    public NumberImgTime(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews(context);
    }

    public NumberImgTime(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        initViews(context);
    }

    public NumberImgTime(Context context, AttributeSet attributeSet, int i8, int i9) {
        super(context, attributeSet, i8, i9);
        initViews(context);
    }
}
