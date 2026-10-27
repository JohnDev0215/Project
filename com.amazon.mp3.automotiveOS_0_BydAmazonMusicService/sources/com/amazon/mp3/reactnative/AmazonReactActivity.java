package com.amazon.mp3.reactnative;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.tracing.Trace;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.reactnative.ReactInitializerRegistry;
import com.amazon.mp3.reactnativemodule.provider.ToolbarProvider;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.facebook.soloader.SoLoader;

/* JADX INFO: loaded from: classes4.dex */
public class AmazonReactActivity extends AppCompatActivity implements PermissionAwareActivity {
    public static String InitialPropsExtra = "INITIAL_PROPS";
    public static String ModuleNameExtra = "MODULE_NAME";
    public static String ReactInitializerNameExtra = "REACT_INITIALIZER_NAME";
    private ReactInstanceManager mReactInstanceManager = null;
    private ReactInitializer reactInitializer = null;
    private ReactInitializerRegistry.Key reactInitializerName = null;
    private ReactRootView mReactRootView = null;
    private Toolbar toolbar = null;
    private final String TAG = AmazonReactActivity.class.getSimpleName();
    private Runnable reactNativePermissionsCallback = null;
    private PermissionListener permissionsListener = null;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Trace.beginSection("AMA.AmazonReactActivity.onCreate");
        SoLoader.init((Context) this, false);
        Bundle extras = getIntent().getExtras();
        String string = extras.getString(ModuleNameExtra);
        if (extras.get(ReactInitializerNameExtra) instanceof ReactInitializerRegistry.Key) {
            ReactInitializerRegistry.Key key = (ReactInitializerRegistry.Key) extras.get(ReactInitializerNameExtra);
            this.reactInitializerName = key;
            this.reactInitializer = ReactInitializerRegistry.Registry.get(key);
        }
        if (this.reactInitializer == null) {
            Log.w(this.TAG, "could not locate reactInitializer, using default");
            this.reactInitializer = new ReactInitializer(getApplication());
        }
        if (string == null || string.isEmpty()) {
            Log.e(this.TAG, "moduleName was empty or null");
            Trace.endSection();
            return;
        }
        this.mReactRootView = new ReactRootView(this);
        this.mReactInstanceManager = this.reactInitializer.getReactInstanceManager();
        this.mReactRootView.startReactApplication(this.mReactInstanceManager, string, extras.getBundle(InitialPropsExtra));
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.react_activity, (ViewGroup) null);
        setContentView(linearLayout);
        ((FrameLayout) findViewById(R.id.react_frame_layout)).addView(this.mReactRootView);
        ToolbarProvider toolbarProvider = this.reactInitializer.getToolbarProvider();
        if (toolbarProvider != null && toolbarProvider.getToolbar() != null) {
            Toolbar toolbar = toolbarProvider.getToolbar();
            this.toolbar = toolbar;
            linearLayout.addView(toolbar, 0);
        }
        Trace.endSection();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Toolbar toolbar = this.toolbar;
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.reactnative.AmazonReactActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AmazonReactActivity.this.finish();
                }
            });
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostResume(this);
        }
        Runnable runnable = this.reactNativePermissionsCallback;
        if (runnable != null) {
            runnable.run();
        }
        this.reactNativePermissionsCallback = null;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostPause(this);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostDestroy(this);
        }
        ReactRootView reactRootView = this.mReactRootView;
        if (reactRootView != null) {
            reactRootView.unmountReactApplication();
        }
        Toolbar toolbar = this.toolbar;
        if (toolbar == null || !(toolbar.getParent() instanceof ViewGroup)) {
            return;
        }
        ((ViewGroup) this.toolbar.getParent()).removeView(this.toolbar);
    }

    @Override // com.facebook.react.modules.core.PermissionAwareActivity
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener permissionListener) {
        this.permissionsListener = this.permissionsListener;
        requestPermissions(permissions, requestCode);
    }
}
