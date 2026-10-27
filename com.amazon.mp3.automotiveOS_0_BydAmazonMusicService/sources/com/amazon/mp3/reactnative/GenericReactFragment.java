package com.amazon.mp3.reactnative;

import android.annotation.TargetApi;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactDelegate;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenericReactFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 42\u00020\u00012\u00020\u0002:\u000254B\u0007¢\u0006\u0004\b2\u00103J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J&\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u000f\u001a\u00020\u0007H\u0016J\b\u0010\u0010\u001a\u00020\u0007H\u0016J\b\u0010\u0011\u001a\u00020\u0007H\u0016J\"\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0016J-\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00122\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ-\u0010!\u001a\u00020\u00072\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u001fH\u0017¢\u0006\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u00198\u0004X\u0084D¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020\u00198\u0004X\u0084D¢\u0006\f\n\u0004\b'\u0010$\u001a\u0004\b(\u0010&R\u0018\u0010*\u001a\u0004\u0018\u00010)8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010-\u001a\u00020,8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b-\u0010.R\u0018\u0010/\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u0010$R\u0018\u00100\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u00101¨\u00066"}, d2 = {"Lcom/amazon/mp3/reactnative/GenericReactFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/facebook/react/modules/core/PermissionAwareActivity;", "Lcom/facebook/react/ReactNativeHost;", "getReactNativeHost", "Landroid/os/Bundle;", "savedInstanceState", "", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "onResume", "onPause", "onDestroy", "", "requestCode", "resultCode", "Landroid/content/Intent;", "data", "onActivityResult", "", "", "permissions", "", "grantResults", "onRequestPermissionsResult", "(I[Ljava/lang/String;[I)V", "Lcom/facebook/react/modules/core/PermissionListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "requestPermissions", "([Ljava/lang/String;ILcom/facebook/react/modules/core/PermissionListener;)V", "ARG_COMPONENT_NAME", "Ljava/lang/String;", "getARG_COMPONENT_NAME", "()Ljava/lang/String;", "ARG_LAUNCH_OPTIONS", "getARG_LAUNCH_OPTIONS", "Lcom/amazon/mp3/reactnative/PrimaryReactInitializer;", "reactInitializer", "Lcom/amazon/mp3/reactnative/PrimaryReactInitializer;", "Lcom/facebook/react/ReactDelegate;", "mReactDelegate", "Lcom/facebook/react/ReactDelegate;", "mainComponentName", "mPermissionListener", "Lcom/facebook/react/modules/core/PermissionListener;", "<init>", "()V", "Companion", "Builder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class GenericReactFragment extends Fragment implements PermissionAwareActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String ARG_COMPONENT_NAME = "arg_component_name";
    private final String ARG_LAUNCH_OPTIONS = "arg_launch_options";
    private PermissionListener mPermissionListener;
    private ReactDelegate mReactDelegate;
    private String mainComponentName;
    private PrimaryReactInitializer reactInitializer;

    /* JADX INFO: compiled from: GenericReactFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/reactnative/GenericReactFragment$Companion;", "", "()V", "newInstance", "Lcom/amazon/mp3/reactnative/GenericReactFragment;", "componentName", "", "launchOptions", "Landroid/os/Bundle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final GenericReactFragment newInstance(String componentName, Bundle launchOptions) {
            GenericReactFragment genericReactFragment = new GenericReactFragment();
            Bundle bundle = new Bundle();
            bundle.putString("arg_component_name", componentName);
            bundle.putBundle("arg_launch_options", launchOptions);
            genericReactFragment.setArguments(bundle);
            return genericReactFragment;
        }
    }

    private final ReactNativeHost getReactNativeHost() {
        if (this.reactInitializer == null) {
            ComponentCallbacks2 application = requireActivity().getApplication();
            if (application == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.facebook.react.ReactApplication");
            }
            ReactNativeHost reactNativeHost = ((ReactApplication) application).getReactNativeHost();
            if (reactNativeHost == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.reactnative.PrimaryReactInitializer");
            }
            this.reactInitializer = (PrimaryReactInitializer) reactNativeHost;
        }
        PrimaryReactInitializer primaryReactInitializer = this.reactInitializer;
        if (primaryReactInitializer != null) {
            return primaryReactInitializer;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.reactnative.PrimaryReactInitializer");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle = null;
        super.onCreate(null);
        this.mainComponentName = null;
        if (getArguments() != null) {
            this.mainComponentName = requireArguments().getString(this.ARG_COMPONENT_NAME);
            bundle = requireArguments().getBundle(this.ARG_LAUNCH_OPTIONS);
        }
        if (this.mainComponentName == null) {
            throw new IllegalStateException("Cannot loadApp if component name is null".toString());
        }
        this.mReactDelegate = new ReactDelegate(getActivity(), getReactNativeHost(), this.mainComponentName, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ReactDelegate reactDelegate = this.mReactDelegate;
        ReactDelegate reactDelegate2 = null;
        if (reactDelegate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mReactDelegate");
            reactDelegate = null;
        }
        if (reactDelegate.getReactRootView() == null) {
            ReactDelegate reactDelegate3 = this.mReactDelegate;
            if (reactDelegate3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mReactDelegate");
                reactDelegate3 = null;
            }
            reactDelegate3.loadApp();
        }
        ReactDelegate reactDelegate4 = this.mReactDelegate;
        if (reactDelegate4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mReactDelegate");
        } else {
            reactDelegate2 = reactDelegate4;
        }
        return reactDelegate2.getReactRootView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ReactDelegate reactDelegate = this.mReactDelegate;
        if (reactDelegate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mReactDelegate");
            reactDelegate = null;
        }
        reactDelegate.onHostResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        ReactDelegate reactDelegate = this.mReactDelegate;
        if (reactDelegate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mReactDelegate");
            reactDelegate = null;
        }
        reactDelegate.onHostPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        ReactDelegate reactDelegate = this.mReactDelegate;
        if (reactDelegate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mReactDelegate");
            reactDelegate = null;
        }
        reactDelegate.onHostDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ReactDelegate reactDelegate = this.mReactDelegate;
        if (reactDelegate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mReactDelegate");
            reactDelegate = null;
        }
        reactDelegate.onActivityResult(requestCode, resultCode, data, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionListener permissionListener = this.mPermissionListener;
        if (permissionListener != null) {
            boolean z = false;
            if (permissionListener != null && permissionListener.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
                z = true;
            }
            if (z) {
                this.mPermissionListener = null;
            }
        }
    }

    @Override // com.facebook.react.modules.core.PermissionAwareActivity
    @TargetApi(23)
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mPermissionListener = listener;
        requestPermissions(permissions, requestCode);
    }

    /* JADX INFO: compiled from: GenericReactFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\bR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010\u000bR$\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/reactnative/GenericReactFragment$Builder;", "", "", "componentName", "setComponentName", "Landroid/os/Bundle;", "launchOptions", "setLaunchOptions", "Lcom/amazon/mp3/reactnative/GenericReactFragment;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "mComponentName", "Ljava/lang/String;", "mLaunchOptions", "Landroid/os/Bundle;", "getMLaunchOptions", "()Landroid/os/Bundle;", "setMLaunchOptions", "(Landroid/os/Bundle;)V", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Builder {
        private String mComponentName;
        private Bundle mLaunchOptions;

        public final Builder setComponentName(String componentName) {
            Intrinsics.checkNotNullParameter(componentName, "componentName");
            this.mComponentName = componentName;
            return this;
        }

        public final Builder setLaunchOptions(Bundle launchOptions) {
            Intrinsics.checkNotNullParameter(launchOptions, "launchOptions");
            this.mLaunchOptions = launchOptions;
            return this;
        }

        public final GenericReactFragment build() {
            return GenericReactFragment.INSTANCE.newInstance(this.mComponentName, this.mLaunchOptions);
        }
    }
}
