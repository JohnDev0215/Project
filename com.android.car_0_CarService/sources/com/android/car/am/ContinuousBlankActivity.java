package com.android.car.am;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.android.car.R;

/* JADX INFO: loaded from: classes3.dex */
public class ContinuousBlankActivity extends Activity {
    private static final String TAG = "CAR.BLANK";

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuous_blank);
        Log.i(TAG, "ContinuousBlankActivity created:");
    }
}
