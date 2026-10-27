package com.amazon.mp3.reactnative.modules;

import androidx.fragment.app.FragmentActivity;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SingleModuleReactPackage.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/ToastPackage;", "Lcom/amazon/mp3/reactnative/modules/SingleModuleReactPackage;", "Lcom/facebook/react/bridge/ReactApplicationContext;", "reactContext", "", "Lcom/facebook/react/bridge/NativeModule;", "createNativeModules", "Landroidx/fragment/app/FragmentActivity;", "hostActivity", "Landroidx/fragment/app/FragmentActivity;", "getHostActivity", "()Landroidx/fragment/app/FragmentActivity;", "<init>", "(Landroidx/fragment/app/FragmentActivity;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ToastPackage extends SingleModuleReactPackage {
    private final FragmentActivity hostActivity;

    public ToastPackage(FragmentActivity fragmentActivity) {
        super(null);
        this.hostActivity = fragmentActivity;
    }

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return CollectionsKt__CollectionsKt.mutableListOf(new ToastModule(reactContext, this.hostActivity));
    }
}
