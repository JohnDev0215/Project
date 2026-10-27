package u;

import android.content.Context;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;

/* JADX INFO: compiled from: Debug.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return ".(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
    }

    public static String b(Context context, int i3) {
        if (i3 == -1) {
            return "UNKNOWN";
        }
        try {
            return context.getResources().getResourceEntryName(i3);
        } catch (Exception unused) {
            return "?" + i3;
        }
    }

    public static String c(View view) {
        try {
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return "UNKNOWN";
        }
    }

    public static String d(MotionLayout motionLayout, int i3) {
        return i3 == -1 ? "UNDEFINED" : motionLayout.getContext().getResources().getResourceEntryName(i3);
    }

    public static void e(String str, String str2, int i3) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int iMin = Math.min(i3, stackTrace.length - 1);
        String str3 = " ";
        for (int i4 = 1; i4 <= iMin; i4++) {
            StackTraceElement stackTraceElement = stackTrace[i4];
            str3 = str3 + " ";
            Log.v(str, str2 + str3 + (".(" + stackTrace[i4].getFileName() + ":" + stackTrace[i4].getLineNumber() + ") " + stackTrace[i4].getMethodName()) + str3);
        }
    }
}
