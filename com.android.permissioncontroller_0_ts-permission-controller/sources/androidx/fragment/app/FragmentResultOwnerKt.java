package androidx.fragment.app;

import android.os.Bundle;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FragmentResultOwner.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aW\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000628\b\u0004\u0010\u0007\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0003\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00010\bH\u0086\b¨\u0006\r"}, d2 = {"setFragmentResultListener", "", "Landroidx/fragment/app/FragmentResultOwner;", "requestKey", "", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "listener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "Landroid/os/Bundle;", "bundle", "fragment-ktx_release"}, k = 2, mv = {1, 1, 16})
public final class FragmentResultOwnerKt {

    /* JADX INFO: renamed from: androidx.fragment.app.FragmentResultOwnerKt$setFragmentResultListener$1, reason: invalid class name */
    /* JADX INFO: compiled from: FragmentResultOwner.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "requestKey", "", "bundle", "Landroid/os/Bundle;", "onFragmentResult"}, k = 3, mv = {1, 1, 16})
    public static final class AnonymousClass1 implements FragmentResultListener {
        final /* synthetic */ Function2 $listener;

        public AnonymousClass1(Function2 function2) {
            this.$listener = function2;
        }

        @Override // androidx.fragment.app.FragmentResultListener
        public final void onFragmentResult(String requestKey, Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(requestKey, "requestKey");
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            this.$listener.invoke(requestKey, bundle);
        }
    }

    public static final void setFragmentResultListener(FragmentResultOwner setFragmentResultListener, String requestKey, LifecycleOwner lifecycleOwner, Function2<? super String, ? super Bundle, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(setFragmentResultListener, "$this$setFragmentResultListener");
        Intrinsics.checkParameterIsNotNull(requestKey, "requestKey");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        setFragmentResultListener.setFragmentResultListener(requestKey, lifecycleOwner, new AnonymousClass1(listener));
    }
}
