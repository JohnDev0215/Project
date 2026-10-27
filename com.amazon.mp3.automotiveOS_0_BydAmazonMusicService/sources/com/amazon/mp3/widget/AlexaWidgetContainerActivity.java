package com.amazon.mp3.widget;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.activity.AccountStateCheckActivity;
import com.amazon.mp3.activity.util.AccountJobController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.AlexaIngressHelper;
import com.amazon.mp3.voice.VoiceManagerFactory;
import com.amazon.music.voice.AlexaEntryPoint;
import com.amazon.music.voice.VoiceManagerSingleton;
import com.amazon.music.voice.ui.AlexaUiListener;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaWidgetContainerActivity extends AppCompatActivity {
    private static final String TAG = "AlexaWidgetContainerActivity";
    private AlexaWidgetContainerActivityObserver lifecycleObserver;
    private AlexaUiListener mWidgetAlexaUiListener = new AlexaUiListener.SimpleAlexaUiListener() { // from class: com.amazon.mp3.widget.AlexaWidgetContainerActivity.1
        @Override // com.amazon.music.voice.ui.AlexaUiListener.SimpleAlexaUiListener, com.amazon.music.voice.ui.AlexaUiListener
        public void onAlexaUiClosed() {
            AlexaWidgetContainerActivity.this.finish();
        }
    };
    private AccountJobController.JobFinishedCallback mWidgetAccountJobFinishedCallback = new AccountJobController.JobFinishedCallback() { // from class: com.amazon.mp3.widget.AlexaWidgetContainerActivity.2
        @Override // com.amazon.mp3.activity.util.AccountJobController.JobFinishedCallback
        public void onSuccess() {
            new AlexaIngressHelper(AlexaWidgetContainerActivity.this).handleAlexaIngress(AlexaEntryPoint.WIDGET);
        }

        @Override // com.amazon.mp3.activity.util.AccountJobController.JobFinishedCallback
        public void onFailure() {
            Log.error(AlexaWidgetContainerActivity.TAG, "Failed to checkImmediate on AccountJobController");
        }
    };

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.lifecycleObserver = new AlexaWidgetContainerActivityObserver(new WidgetMetrics(this), getIntent());
        getLifecycle().addObserver(this.lifecycleObserver);
        launchAlexa();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (VoiceManagerSingleton.isInitialized()) {
            VoiceManagerSingleton.getInstance().removeAlexaUiListener(this.mWidgetAlexaUiListener);
        }
        super.onDestroy();
    }

    private void launchAlexa() {
        if (AccountCredentialUtil.get(getApplicationContext()).isSignedOut()) {
            BauhausToastUtils.showTextToast(getApplicationContext(), getString(R.string.dmusic_content_unavailable_dialog_title, getString(R.string.dmusic_settings_alexa_section)), 0);
            finish();
            return;
        }
        if (!VoiceManagerSingleton.isInitialized() || VoiceManagerFactory.shouldRefresh()) {
            VoiceManagerFactory.initializeVoiceManager(getApplicationContext());
        }
        if (VoiceManagerSingleton.getInstance().isInitRequired()) {
            VoiceManagerSingleton.getInstance().handleReopenMusicApp(getApplicationContext());
        }
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        if (viewGroup == null) {
            Log.debug(TAG, "Can't attach alexa to null viewGroup");
            return;
        }
        VoiceManagerSingleton.getInstance().attachView(this, viewGroup);
        VoiceManagerSingleton.getInstance().addAlexaUiListener(this.mWidgetAlexaUiListener);
        startActivity(AccountStateCheckActivity.getStartIntent(getApplicationContext(), this.mWidgetAccountJobFinishedCallback));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        VoiceManagerSingleton.getInstance().closeAlexaUi();
    }
}
