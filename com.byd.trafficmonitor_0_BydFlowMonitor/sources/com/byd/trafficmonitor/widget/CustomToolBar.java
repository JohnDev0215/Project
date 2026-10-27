package com.byd.trafficmonitor.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;
import com.bydux.api.R;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class CustomToolBar extends Toolbar {
    public ImageButton Q;

    public CustomToolBar(Context context) {
        super(context);
    }

    public final void P() {
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField("mNavButtonView");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            if (obj instanceof ImageButton) {
                ImageButton imageButton = (ImageButton) obj;
                this.Q = imageButton;
                imageButton.getLayoutParams().width = getContext().getResources().getDimensionPixelSize(R.dimen.dp_48);
                this.Q.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                Log.i("CustomView", "set view width ");
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        }
    }

    public CustomToolBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        P();
    }
}
