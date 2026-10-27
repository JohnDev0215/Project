package com.amazon.mp3.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.mp3.util.UniqueCodeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class BlueMoonExceptionActivity extends BaseActivity {
    public static final int ACCOUNT_NOT_VERIFIED_EXCEPTION_REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    public static final int DEVICE_AUTHORIZATION_EXCEPTION_REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) BlueMoonExceptionActivity.class);
        intent.addFlags(268435456);
        return intent;
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();
    }
}
