package com.amazon.mp3.reactnative;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.tracing.Trace;
import com.amazon.mp3.reactnative.ReactInitializerRegistry;
import com.facebook.react.ReactDelegate;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;

/* JADX INFO: loaded from: classes4.dex */
public class AmazonReactFragment extends Fragment implements PermissionAwareActivity {

    @Nullable
    private PermissionListener mPermissionListener;
    private ReactDelegate mReactDelegate;
    private String mainComponentName;
    private ReactInitializer reactInitializer;

    public String getMainComponentName() {
        return this.mainComponentName;
    }

    protected ReactNativeHost getReactNativeHost() {
        if (this.reactInitializer == null) {
            this.reactInitializer = new ReactInitializer(getActivity().getApplication());
        }
        return this.reactInitializer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AmazonReactFragment newInstance(String componentName, Bundle launchOptions, ReactInitializerRegistry.Key key) {
        Bundle bundle = new Bundle();
        AmazonReactFragment amazonReactFragment = new AmazonReactFragment();
        bundle.putString("arg_component_name", componentName);
        bundle.putBundle("arg_launch_options", launchOptions);
        bundle.putSerializable("arg_react_initializer_key", key);
        amazonReactFragment.setArguments(bundle);
        return amazonReactFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Trace.beginSection("AMA.AmazonReactFragment.onCreate");
        Bundle bundle = null;
        this.mainComponentName = null;
        if (getArguments() != null) {
            this.mainComponentName = getArguments().getString("arg_component_name");
            bundle = getArguments().getBundle("arg_launch_options");
            if (getArguments().get("arg_react_initializer_key") instanceof ReactInitializerRegistry.Key) {
                this.reactInitializer = ReactInitializerRegistry.Registry.get((ReactInitializerRegistry.Key) getArguments().get("arg_react_initializer_key"));
            }
        }
        if (this.mainComponentName == null) {
            throw new IllegalStateException("Cannot loadApp if component name is null");
        }
        this.mReactDelegate = new ReactDelegate(getActivity(), getReactNativeHost(), this.mainComponentName, bundle);
        Trace.endSection();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.mReactDelegate.getReactRootView() == null) {
            this.mReactDelegate.loadApp();
        }
        return this.mReactDelegate.getReactRootView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mReactDelegate.onHostResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mReactDelegate.onHostPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mReactDelegate.onHostDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.mReactDelegate.onActivityResult(requestCode, resultCode, data, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionListener permissionListener = this.mPermissionListener;
        if (permissionListener == null || !permissionListener.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            return;
        }
        this.mPermissionListener = null;
    }

    @Override // com.facebook.react.modules.core.PermissionAwareActivity
    @TargetApi(23)
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        this.mPermissionListener = listener;
        requestPermissions(permissions, requestCode);
    }

    public static class Builder {
        String mComponentName = null;
        Bundle mLaunchOptions = null;
        ReactInitializerRegistry.Key reactInitializerRegistryKey;

        public Builder setComponentName(String componentName) {
            this.mComponentName = componentName;
            return this;
        }

        public Builder setLaunchOptions(Bundle launchOptions) {
            this.mLaunchOptions = launchOptions;
            return this;
        }

        public Builder setReactInitializerRegistryKey(ReactInitializerRegistry.Key key) {
            this.reactInitializerRegistryKey = key;
            return this;
        }

        public AmazonReactFragment build() {
            return AmazonReactFragment.newInstance(this.mComponentName, this.mLaunchOptions, this.reactInitializerRegistryKey);
        }
    }
}
