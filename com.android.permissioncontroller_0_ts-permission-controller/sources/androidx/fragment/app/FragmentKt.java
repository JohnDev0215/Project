package androidx.fragment.app;

import android.os.Bundle;
import androidx.fragment.app.FragmentResultOwnerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Fragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001a\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b\u001aO\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000428\b\u0004\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0003\u0012\u0013\u0012\u00110\b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00010\u000bH\u0086\b¨\u0006\u000f"}, d2 = {"clearFragmentResult", "", "Landroidx/fragment/app/Fragment;", "requestKey", "", "clearFragmentResultListener", "setFragmentResult", "result", "Landroid/os/Bundle;", "setFragmentResultListener", "listener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "bundle", "fragment-ktx_release"}, k = 2, mv = {1, 1, 16})
public final class FragmentKt {
    public static final void setFragmentResult(Fragment setFragmentResult, String requestKey, Bundle result) {
        Intrinsics.checkParameterIsNotNull(setFragmentResult, "$this$setFragmentResult");
        Intrinsics.checkParameterIsNotNull(requestKey, "requestKey");
        Intrinsics.checkParameterIsNotNull(result, "result");
        setFragmentResult.getParentFragmentManager().setFragmentResult(requestKey, result);
    }

    public static final void clearFragmentResult(Fragment clearFragmentResult, String requestKey) {
        Intrinsics.checkParameterIsNotNull(clearFragmentResult, "$this$clearFragmentResult");
        Intrinsics.checkParameterIsNotNull(requestKey, "requestKey");
        clearFragmentResult.getParentFragmentManager().clearFragmentResult(requestKey);
    }

    public static final void setFragmentResultListener(Fragment setFragmentResultListener, String requestKey, Function2<? super String, ? super Bundle, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(setFragmentResultListener, "$this$setFragmentResultListener");
        Intrinsics.checkParameterIsNotNull(requestKey, "requestKey");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        FragmentResultOwner parentFragmentManager = setFragmentResultListener.getParentFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(parentFragmentManager, "parentFragmentManager");
        FragmentResultOwner $this$setFragmentResultListener$iv = parentFragmentManager;
        $this$setFragmentResultListener$iv.setFragmentResultListener(requestKey, setFragmentResultListener, new FragmentResultOwnerKt.AnonymousClass1(listener));
    }

    public static final void clearFragmentResultListener(Fragment clearFragmentResultListener, String requestKey) {
        Intrinsics.checkParameterIsNotNull(clearFragmentResultListener, "$this$clearFragmentResultListener");
        Intrinsics.checkParameterIsNotNull(requestKey, "requestKey");
        clearFragmentResultListener.getParentFragmentManager().clearFragmentResultListener(requestKey);
    }
}
