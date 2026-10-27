package com.android.server.telecom.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import com.android.server.telecom.R;
import com.android.server.telecom.SystemSettingsUtil;

/* JADX INFO: loaded from: classes.dex */
public class TelecomDeveloperMenu extends Activity {
    private Switch mEnhancedCallingSwitch;
    private SystemSettingsUtil mSystemSettingsUtil;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSystemSettingsUtil = new SystemSettingsUtil();
        setContentView(R.layout.telecom_developer_menu);
        Switch r2 = (Switch) findViewById(R.id.switchEnhancedCallBlocking);
        this.mEnhancedCallingSwitch = r2;
        r2.setOnClickListener(new View.OnClickListener() { // from class: com.android.server.telecom.ui.-$$Lambda$TelecomDeveloperMenu$-pRfn1zYL-fJMhTamrko47LcwB8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0$TelecomDeveloperMenu(view);
            }
        });
        loadPreferences();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$0$TelecomDeveloperMenu(View view) {
        handleEnhancedCallingToggle();
    }

    private void handleEnhancedCallingToggle() {
        this.mSystemSettingsUtil.setEnhancedCallBlockingEnabled(this, this.mEnhancedCallingSwitch.isChecked());
    }

    private void loadPreferences() {
        this.mEnhancedCallingSwitch.setChecked(this.mSystemSettingsUtil.isEnhancedCallBlockingEnabled(this));
    }
}
