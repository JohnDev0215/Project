package com.byd.auto_emanual.util;

import android.content.Context;
import android.graphics.Outline;
import android.text.Html;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.image.KImageView;

/* JADX INFO: loaded from: classes.dex */
public class ViewUtil {
    public static final int NOT_TREAD_ORIENTATION = -1;
    public static final int TIME_INTERVAL_1000 = 1000;
    public static final int TIME_INTERVAL_2500 = 2500;
    public static final int TIME_INTERVAL_5000 = 5000;
    private static long lastKeyUpTime;

    public static boolean isPort(int orientation) {
        return orientation == 1;
    }

    public static boolean isRtl(int layoutDirection) {
        return layoutDirection == 1;
    }

    public static boolean notTreatOrientation(int orientation) {
        return orientation == -1;
    }

    public static void setViewCorner(View view, final float radius) {
        view.setOutlineProvider(new ViewOutlineProvider() { // from class: com.byd.auto_emanual.util.ViewUtil.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view2, Outline outline) {
                outline.setRoundRect(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom(), radius);
            }
        });
        view.setClipToOutline(true);
    }

    public static boolean showToast(Toast toast, int time_interval) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - lastKeyUpTime <= time_interval) {
            return false;
        }
        toast.show();
        lastKeyUpTime = jCurrentTimeMillis;
        return true;
    }

    public static boolean isTimeToDo(int time_interval) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - lastKeyUpTime <= time_interval) {
            return false;
        }
        lastKeyUpTime = jCurrentTimeMillis;
        return true;
    }

    public static TextView createTextView(Context context, String text) {
        TextView textView = new TextView(context);
        textView.setText(Html.fromHtml(text, 0));
        textView.setTextSize(2, context.getResources().getDimensionPixelOffset(R.dimen.media_image_text_content_text_size));
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        return textView;
    }

    public static KImageView createKImageView(Context context, String url) {
        KImageView kImageView = new KImageView(context);
        kImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        kImageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        kImageView.setImagePath(url);
        return kImageView;
    }
}
