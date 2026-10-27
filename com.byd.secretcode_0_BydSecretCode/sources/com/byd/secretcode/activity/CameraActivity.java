package com.byd.secretcode.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import com.byd.secretcode.camera.AutoFitTextureView;
import com.bydux.api.common.tools.log.BydLogHelper;
import defpackage.gg;

/* JADX INFO: loaded from: classes.dex */
public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    public AutoFitTextureView b = null;
    public gg c = null;
    public int d = 1;
    public Button e = null;
    public Button f = null;
    public Button g = null;
    public Button h = null;

    public final void initView() {
        AutoFitTextureView autoFitTextureView = (AutoFitTextureView) findViewById(R.id.textureview);
        this.b = autoFitTextureView;
        this.c.A(autoFitTextureView);
        this.e = (Button) findViewById(R.id.rotate_0);
        this.f = (Button) findViewById(R.id.rotate_90);
        this.h = (Button) findViewById(R.id.rotate_180);
        this.g = (Button) findViewById(R.id.rotate_270);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.g.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rotate_0 /* 2131296737 */:
                this.c.x(1920, 930, getWindowManager().getDefaultDisplay().getRotation());
                break;
            case R.id.rotate_180 /* 2131296738 */:
                this.c.x(1920, 930, 2);
                break;
            case R.id.rotate_270 /* 2131296739 */:
                this.c.x(1280, 720, 1);
                break;
            case R.id.rotate_90 /* 2131296740 */:
                BydLogHelper.logD("CameraActivity", "v_h_screen_btn type =" + this.d);
                this.c.x(1920, 930, 3);
                break;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        getWindow().clearFlags(1024);
        super.onCreate(bundle);
        setContentView(R.layout.activity_cameradvr);
        this.c = gg.y(this);
        initView();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.c.w();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }
}
