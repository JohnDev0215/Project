package c.a.d;

import android.content.Context;
import com.bydux.api.R;

/* JADX INFO: compiled from: CarAutoTypeUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(int i, Context context, String str) {
        return (i == 60 || i == 123 || i == 317) ? str.replace(context.getString(R.string.smarttravel_brand_byd), context.getString(R.string.smarttravel_brand_ts)) : str;
    }

    public static int b(int i) {
        return (i == 60 || i == 123 || i == 317) ? R.drawable.car_mpv_bg : R.drawable.car_suv_bg;
    }
}
