package androidx.navigation;

import android.app.Activity;
import androidx.annotation.IdRes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Activity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"findNavController", "Landroidx/navigation/NavController;", "Landroid/app/Activity;", "viewId", "", "navigation-runtime-ktx_release"}, k = 2, mv = {1, 1, 16})
public final class ActivityKt {
    public static final NavController findNavController(Activity activity, @IdRes int i) {
        NavController navControllerFindNavController = Navigation.findNavController(activity, i);
        Intrinsics.checkExpressionValueIsNotNull(navControllerFindNavController, "Navigation.findNavController(this, viewId)");
        return navControllerFindNavController;
    }
}
