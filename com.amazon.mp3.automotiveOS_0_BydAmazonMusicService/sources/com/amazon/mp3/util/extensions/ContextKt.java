package com.amazon.mp3.util.extensions;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Context.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u001a%\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0001\"\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\f\u001a\u00020\n*\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00072\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\n0\tH\u0007\u001a\u001d\u0010\u000f\u001a\u0004\u0018\u00010\r*\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroid/content/Context;", "", "", "permissionStrings", "", "isPermissionGranted", "(Landroid/content/Context;[Ljava/lang/String;)Z", "", "delayMillis", "Lkotlin/Function1;", "", "action", "runOnUiThread", "", "colorId", "getCompatColor", "(Landroid/content/Context;I)Ljava/lang/Integer;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0})
public final class ContextKt {
    @JvmOverloads
    public static final void runOnUiThread(Context context, Function1<? super Context, Unit> action) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        runOnUiThread$default(context, 0L, action, 1, null);
    }

    public static final boolean isPermissionGranted(Context context, String... permissionStrings) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(permissionStrings, "permissionStrings");
        if (permissionStrings.length == 0) {
            return false;
        }
        ArrayList<Pair> arrayList = new ArrayList(permissionStrings.length);
        int length = permissionStrings.length;
        int i = 0;
        while (i < length) {
            String str = permissionStrings[i];
            i++;
            arrayList.add(TuplesKt.to(str, Integer.valueOf(ContextCompat.checkSelfPermission(context, str))));
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        for (Pair pair : arrayList) {
            boolean z = ((Number) pair.getSecond()).intValue() == 0;
            if (!z) {
                Log.info("com.amazon.mp3.util.extensions.Context", Intrinsics.stringPlus((String) pair.getFirst(), " is not granted"));
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ void runOnUiThread$default(Context context, long j, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 0;
        }
        runOnUiThread(context, j, function1);
    }

    @JvmOverloads
    public static final void runOnUiThread(final Context context, long j, final Function1<? super Context, Unit> action) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        Handler handler = new Handler(context.getMainLooper());
        if (j == 0) {
            handler.post(new Runnable() { // from class: com.amazon.mp3.util.extensions.ContextKt$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ContextKt.m1657runOnUiThread$lambda4$lambda2(action, context);
                }
            });
        } else {
            handler.postDelayed(new Runnable() { // from class: com.amazon.mp3.util.extensions.ContextKt$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ContextKt.m1658runOnUiThread$lambda4$lambda3(action, context);
                }
            }, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: runOnUiThread$lambda-4$lambda-2, reason: not valid java name */
    public static final void m1657runOnUiThread$lambda4$lambda2(Function1 action, Context this_runOnUiThread) {
        Intrinsics.checkNotNullParameter(action, "$action");
        Intrinsics.checkNotNullParameter(this_runOnUiThread, "$this_runOnUiThread");
        action.invoke(this_runOnUiThread);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: runOnUiThread$lambda-4$lambda-3, reason: not valid java name */
    public static final void m1658runOnUiThread$lambda4$lambda3(Function1 action, Context this_runOnUiThread) {
        Intrinsics.checkNotNullParameter(action, "$action");
        Intrinsics.checkNotNullParameter(this_runOnUiThread, "$this_runOnUiThread");
        action.invoke(this_runOnUiThread);
    }

    public static final Integer getCompatColor(Context context, @ColorRes int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            return Integer.valueOf(ContextCompat.getColor(context, i));
        } catch (Resources.NotFoundException unused) {
            return null;
        }
    }
}
