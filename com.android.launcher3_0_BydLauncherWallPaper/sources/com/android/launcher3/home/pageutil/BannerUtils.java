package com.android.launcher3.home.pageutil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.android.launcher3.R;
import com.android.launcher3.home.viewpage.DefaultViewPage;
import com.android.launcher3.home.viewpage.WallPage;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class BannerUtils {
    public static final int[] IMAGES = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7};

    public static ArrayList<WallPage> getImage(Context context, int count) {
        ArrayList<WallPage> arrayList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            arrayList.add(new DefaultViewPage(context, IMAGES[i]));
        }
        return arrayList;
    }

    public static ArrayList<WallPage> getImage(Context context) {
        ArrayList<WallPage> arrayList = new ArrayList<>();
        arrayList.add(new DefaultViewPage(context, IMAGES[0]));
        return arrayList;
    }

    public static Bitmap getLocalFirstBitmap(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), IMAGES[0], new BitmapFactory.Options());
    }

    public static Bitmap getLocalFirstBitmap(Context context, int index) {
        return BitmapFactory.decodeResource(context.getResources(), IMAGES[index], new BitmapFactory.Options());
    }

    public static boolean isLand(Context context) {
        return context.getResources().getConfiguration().orientation != 1;
    }

    public static int getCurrentOrientation(Context context) {
        return context.getResources().getConfiguration().orientation;
    }
}
