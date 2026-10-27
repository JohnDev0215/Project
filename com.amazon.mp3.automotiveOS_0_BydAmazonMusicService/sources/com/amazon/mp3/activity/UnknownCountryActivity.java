package com.amazon.mp3.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import com.amazon.mp3.account.activity.AccountDeregistrationDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.service.job.JobSessionActivity;

/* JADX INFO: loaded from: classes3.dex */
public class UnknownCountryActivity extends JobSessionActivity {
    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) UnknownCountryActivity.class);
        intent.addFlags(32768);
        intent.addFlags(268435456);
        return intent;
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unknown_country_activity);
        ((TextView) findViewById(R.id.UnknownCountryText)).setMovementMethod(LinkMovementMethod.getInstance());
        findViewById(R.id.sign_out).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.UnknownCountryActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AccountDeregistrationDialogActivity.start(UnknownCountryActivity.this);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AccountDeregistrationDialogActivity.REQUEST_CODE && resultCode == -1) {
            startActivity(new Intent(this, (Class<?>) MusicLauncherActivity.class));
            finish();
        }
    }
}
