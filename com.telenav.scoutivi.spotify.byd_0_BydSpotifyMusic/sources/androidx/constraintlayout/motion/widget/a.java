package androidx.constraintlayout.motion.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import java.nio.CharBuffer;

/* JADX INFO: compiled from: Debug.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"LogConditional"})
public class a {
    public static String a() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return ".(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName() + "()";
    }

    public static String b() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return ".(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
    }

    public static String c(Context context, int i7) {
        if (i7 == -1) {
            return "UNKNOWN";
        }
        try {
            return context.getResources().getResourceEntryName(i7);
        } catch (Exception unused) {
            return "?" + i7;
        }
    }

    public static String d(View view) {
        try {
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return "UNKNOWN";
        }
    }

    public static String e(MotionLayout motionLayout, int i7) {
        return f(motionLayout, i7, -1);
    }

    public static String f(MotionLayout motionLayout, int i7, int i8) {
        int length;
        if (i7 == -1) {
            return "UNDEFINED";
        }
        String resourceEntryName = motionLayout.getContext().getResources().getResourceEntryName(i7);
        if (i8 == -1) {
            return resourceEntryName;
        }
        if (resourceEntryName.length() > i8) {
            resourceEntryName = resourceEntryName.replaceAll("([^_])[aeiou]+", "$1");
        }
        if (resourceEntryName.length() <= i8 || (length = resourceEntryName.replaceAll("[^_]", "").length()) <= 0) {
            return resourceEntryName;
        }
        return resourceEntryName.replaceAll(CharBuffer.allocate((resourceEntryName.length() - i8) / length).toString().replace((char) 0, '.') + "_", "_");
    }

    public static void g(String str, String str2, int i7) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int iMin = Math.min(i7, stackTrace.length - 1);
        String str3 = " ";
        for (int i8 = 1; i8 <= iMin; i8++) {
            StackTraceElement stackTraceElement = stackTrace[i8];
            str3 = str3 + " ";
            Log.v(str, str2 + str3 + (".(" + stackTrace[i8].getFileName() + ":" + stackTrace[i8].getLineNumber() + ") " + stackTrace[i8].getMethodName()) + str3);
        }
    }
}
