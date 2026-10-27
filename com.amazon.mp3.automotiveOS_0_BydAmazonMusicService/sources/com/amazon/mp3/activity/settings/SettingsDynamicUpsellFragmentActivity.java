package com.amazon.mp3.activity.settings;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.inappmessaging.DynamicMessagingManager;
import com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;

/* JADX INFO: loaded from: classes3.dex */
public class SettingsDynamicUpsellFragmentActivity extends BaseActivity implements DynamicMessageDialogFragmentListener {
    @Override // com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener
    public void onShow(final DialogInterface dialogInterface) {
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DynamicMessagingManager(this).displayDynamicMessage(this, new DynamicMessageEvent(Trigger.SETTINGS_UPSELL), new DynamicMessageMetricsAttributes(PageType.SETTINGS.getMetricsValue(), null)).subscribe();
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener
    public void onDismissClickEvent(DialogInterface dialogInterface) {
        finish();
    }
}
