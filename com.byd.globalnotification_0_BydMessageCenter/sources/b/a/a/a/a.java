package b.a.a.a;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: outline */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static float a(float f2, float f3, float f4, float f5) {
        return ((f2 - f3) * f4) + f5;
    }

    public static String b(RecyclerView recyclerView, StringBuilder sb) {
        sb.append(recyclerView.A());
        return sb.toString();
    }

    public static String c(String str, String str2) {
        return str + str2;
    }

    public static StringBuilder d(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static void e(StringBuilder sb, Fragment fragment, String str) {
        sb.append(fragment);
        Log.d(str, sb.toString());
    }
}
