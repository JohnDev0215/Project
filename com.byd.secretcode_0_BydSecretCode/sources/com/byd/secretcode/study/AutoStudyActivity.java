package com.byd.secretcode.study;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.byd.secretcode.R;

/* JADX INFO: loaded from: classes.dex */
public class AutoStudyActivity extends Activity {
    public TextView b;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_auto_study);
        this.b = (TextView) findViewById(R.id.auto_study_text_id);
    }
}
