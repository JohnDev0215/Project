package com.android.systemui;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.systemui.statusbar.policy.CarController;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class RotateActivity extends AppCompatActivity {
    private CarController mCarController;
    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3;
    private TextView tv_4;
    private TextView tv_5;
    private TextView tv_6;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);
        this.mCarController = (CarController) Dependency.get(CarController.class);
        this.tv_1 = (TextView) findViewById(R.id.tv_1);
        this.tv_2 = (TextView) findViewById(R.id.tv_2);
        this.tv_3 = (TextView) findViewById(R.id.tv_3);
        this.tv_4 = (TextView) findViewById(R.id.tv_4);
        this.tv_5 = (TextView) findViewById(R.id.tv_5);
        this.tv_6 = (TextView) findViewById(R.id.tv_6);
        this.tv_4.setText("当前方向 getScreenAngle：" + this.mCarController.getScreenAngle());
        this.tv_1.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.RotateActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                int padRotation = RotateActivity.this.mCarController.setPadRotation(2);
                Toast.makeText(RotateActivity.this, "" + padRotation, 0).show();
                RotateActivity.this.tv_1.setText(RotateActivity.this.tv_1.getText().toString() + ":" + padRotation);
                BydLogHelper.logD("CarControllerImpl 横向 setResult :" + padRotation);
            }
        });
        this.tv_2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.RotateActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                int padRotation = RotateActivity.this.mCarController.setPadRotation(1);
                Toast.makeText(RotateActivity.this, "" + padRotation, 0).show();
                RotateActivity.this.tv_2.setText(RotateActivity.this.tv_2.getText().toString() + ":" + padRotation);
                BydLogHelper.logD("CarControllerImpl 竖向 setResult :" + padRotation);
            }
        });
        this.tv_3.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.RotateActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                int screenAngle = RotateActivity.this.mCarController.getScreenAngle();
                Toast.makeText(RotateActivity.this, "" + screenAngle, 0).show();
                RotateActivity.this.tv_4.setText(RotateActivity.this.tv_4.getText().toString() + ":" + screenAngle);
            }
        });
        this.tv_5.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.RotateActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Settings.System.putInt(RotateActivity.this.getContentResolver(), "user_rotation", 0);
            }
        });
        this.tv_6.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.RotateActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Settings.System.putInt(RotateActivity.this.getContentResolver(), "user_rotation", 1);
            }
        });
    }
}
