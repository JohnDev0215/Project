package spi;

import android.app.UiStyleConfig;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: compiled from: BydUiStyleConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a1 {
    public static int a = UiStyleConfig.getInstance(Utils.getApp()).getUiStyle();

    public static boolean a() {
        int i = a;
        return i == 1 || i == 3;
    }
}
