package com.neusoft.optimus.utility.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/* JADX INFO: loaded from: classes2.dex */
public class FontsUtils {
    public static final int DROIDSANS = 0;
    public static final int DROIDSANS_BOLD = 1;
    public static final int Normal_20151114 = 2;
    public static final int NotoSansCJKsc_Regular = 3;

    public static void setTextFonts(Context context, int i, TextView textView) {
        if (i == 0) {
            textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "DroidSans.ttf"));
        } else if (i == 1) {
            textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "DroidSans-Bold.ttf"));
        } else {
            if (i != 2) {
                return;
            }
            textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "GEELY_Normal_20151114.otf"));
        }
    }
}
