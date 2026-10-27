package com.android.car.developeroptions.biometrics.face;

import android.os.Bundle;
import android.view.View;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.biometrics.BiometricEnrollBase;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;

/* JADX INFO: loaded from: classes.dex */
public class FaceEnrollFinish extends BiometricEnrollBase {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1508;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollBase, com.android.car.developeroptions.core.InstrumentedActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.face_enroll_finish);
        setHeaderText(R.string.security_settings_face_enroll_finish_title);
        FooterBarMixin footerBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        this.mFooterBarMixin = footerBarMixin;
        FooterButton.Builder builder = new FooterButton.Builder(this);
        builder.setText(R.string.security_settings_face_enroll_done);
        builder.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.face.-$$Lambda$coPRtx0jvO9_TDQ5XOfLA-MJd8Y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onNextButtonClick(view);
            }
        });
        builder.setButtonType(5);
        builder.setTheme(R.style.SudGlifButton_Primary);
        footerBarMixin.setPrimaryButton(builder.build());
    }

    public void onNextButtonClick(View view) {
        setResult(1);
        finish();
    }
}
