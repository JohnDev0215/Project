package com.amazon.mp3.reactnative;

import android.content.Context;
import android.os.Bundle;
import android.os.Trace;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactRootView;
import com.facebook.soloader.SoLoader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenericReactActivity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/reactnative/GenericReactActivity;", "Lcom/facebook/react/ReactActivity;", "Lcom/facebook/react/modules/core/DefaultHardwareBackBtnHandler;", "()V", "reactRootView", "Lcom/facebook/react/ReactRootView;", "invokeDefaultOnBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GenericReactActivity extends ReactActivity {
    private ReactRootView reactRootView;

    @Override // com.facebook.react.ReactActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        ReactRootView reactRootView = null;
        super.onCreate(null);
        Trace.beginSection("AMA.GenericReactActivity.onCreate");
        SoLoader.init((Context) this, false);
        this.reactRootView = new ReactRootView(this);
        Bundle extras = getIntent().getExtras();
        ReactRootView reactRootView2 = this.reactRootView;
        if (reactRootView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reactRootView");
            reactRootView2 = null;
        }
        reactRootView2.startReactApplication(getReactInstanceManager(), "DragonFly", extras);
        ReactRootView reactRootView3 = this.reactRootView;
        if (reactRootView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reactRootView");
        } else {
            reactRootView = reactRootView3;
        }
        setContentView(reactRootView);
        Trace.endSection();
    }

    @Override // com.facebook.react.ReactActivity, com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
}
