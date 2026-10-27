package com.amazon.mp3.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.web.WebEvent;
import java.io.Serializable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseWebActivity extends BaseActivity {
    private static final String TAG = "BaseWebActivity";
    protected PublishSubject<WebEvent> mWebEventPublisher;
    private Subscription mWebEventSubscription;

    protected abstract void handleWebEvent(final WebEvent webEvent);

    protected abstract void init();

    public static Intent getTargetStartEvent(final Context context, @NonNull final Class<?> activityClass, final Serializable target, final String key) {
        Intent intent = new Intent(context, activityClass);
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, target);
        intent.putExtras(bundle);
        return intent;
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upsell_container);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Subscription subscription = this.mWebEventSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleNetworkError() {
        if (NetworkErrorDialog.isShowing()) {
            return;
        }
        NetworkErrorDialog.create(this, new NetworkErrorDialog.INetworkErrorDialogHandler() { // from class: com.amazon.mp3.activity.BaseWebActivity.1
            @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
            public void onRetryConnection() {
                BaseWebActivity.this.init();
            }

            @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
            public void onDialogDismiss() {
                BaseWebActivity.this.handleError();
            }

            @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
            public void onConnectionErrorDialogCancel() {
                BaseWebActivity.this.handleError();
            }
        }).show();
    }

    protected void handleError() {
        Log.warning(TAG, "Received an error while loading a webview");
        setResult(2);
        finish();
    }

    protected void setupPublisherForJSBridge() {
        this.mWebEventPublisher = PublishSubject.create();
        subscribeEvents();
    }

    private void subscribeEvents() {
        this.mWebEventSubscription = this.mWebEventPublisher.subscribe(new Action1<WebEvent>() { // from class: com.amazon.mp3.activity.BaseWebActivity.2
            @Override // rx.functions.Action1
            public void call(final WebEvent webEvent) {
                BaseWebActivity.this.handleWebEvent(webEvent);
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.activity.BaseWebActivity.3
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                BaseWebActivity.this.handleError();
            }
        });
    }
}
