package com.byd.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.profile.ProfileManager;
import com.byd.profile.dialog.AlarmDialog;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    private RadioButton mBtnBaby;
    private RadioButton mBtnPet;
    private RadioButton mBtnRest;
    private RadioButton mBtnRomantic;
    private RadioButton mBtnWipe;
    private int mClickIndex;
    private int mMode;
    private RadioGroup mRadioGroup;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton radioButton = (RadioButton) findViewById(R.id.btn_baby);
        this.mBtnBaby = radioButton;
        radioButton.setOnClickListener(this);
        this.mBtnBaby.setOnLongClickListener(this);
        RadioButton radioButton2 = (RadioButton) findViewById(R.id.btn_rest);
        this.mBtnRest = radioButton2;
        radioButton2.setOnClickListener(this);
        this.mBtnRest.setOnLongClickListener(this);
        RadioButton radioButton3 = (RadioButton) findViewById(R.id.btn_pet);
        this.mBtnPet = radioButton3;
        radioButton3.setOnClickListener(this);
        this.mBtnPet.setOnLongClickListener(this);
        RadioButton radioButton4 = (RadioButton) findViewById(R.id.btn_romantic);
        this.mBtnRomantic = radioButton4;
        radioButton4.setOnClickListener(this);
        this.mBtnRomantic.setOnLongClickListener(this);
        RadioButton radioButton5 = (RadioButton) findViewById(R.id.btn_wipe);
        this.mBtnWipe = radioButton5;
        radioButton5.setOnClickListener(this);
        this.mBtnWipe.setOnLongClickListener(this);
        this.mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.byd.profile.MainActivity.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                BydLogHelper.logD("onCheckedChanged isChecked = " + ((RadioButton) MainActivity.this.findViewById(checkedId)).isChecked());
            }
        });
        ProfileManager.getInstance(this).registerOnModeChangeListener(new ProfileManager.OnModeChangeListener() { // from class: com.byd.profile.MainActivity.2
            @Override // com.byd.profile.ProfileManager.OnModeChangeListener
            public void onModeChange(final int mode, boolean isStarted) {
                BydLogHelper.logD("mode = " + mode);
                new Handler().postDelayed(new Runnable() { // from class: com.byd.profile.MainActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        int i = mode;
                        if (i == 0) {
                            MainActivity.this.mBtnBaby.setChecked(false);
                            MainActivity.this.mBtnRest.setChecked(false);
                            MainActivity.this.mBtnPet.setChecked(false);
                            MainActivity.this.mBtnRomantic.setChecked(false);
                            MainActivity.this.mBtnWipe.setChecked(false);
                            return;
                        }
                        if (i == 1) {
                            MainActivity.this.mMode = 1;
                            MainActivity.this.mBtnBaby.setChecked(true);
                            return;
                        }
                        if (i == 2) {
                            MainActivity.this.mMode = 2;
                            MainActivity.this.mBtnRest.setChecked(true);
                            return;
                        }
                        if (i == 3) {
                            MainActivity.this.mMode = 3;
                            MainActivity.this.mBtnPet.setChecked(true);
                            return;
                        }
                        if (i != 4) {
                            if (i != 5) {
                                return;
                            }
                            MainActivity.this.mMode = 5;
                            MainActivity.this.mBtnWipe.setChecked(true);
                            return;
                        }
                        MainActivity.this.mMode = 4;
                        BydLogHelper.logD("checkedId = " + MainActivity.this.mRadioGroup.getCheckedRadioButtonId());
                        MainActivity.this.mBtnRomantic.setChecked(true);
                        MainActivity.this.mBtnRomantic.setChecked(true);
                        BydLogHelper.logD("Romantic checked = " + MainActivity.this.mBtnRomantic.isChecked() + " currMode = " + ProfileManager.getInstance(MainActivity.this).getProfileMode());
                    }
                }, 100L);
            }
        });
    }

    private boolean checkOverlayDisplayPermission() {
        return Settings.canDrawOverlays(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (!checkOverlayDisplayPermission()) {
            startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())));
            return;
        }
        int i = 0;
        int id = v.getId();
        if (id == R.id.btn_baby) {
            i = 1;
            new AlarmDialog().show(getSupportFragmentManager(), "");
        } else if (id == R.id.btn_rest) {
            i = 2;
        } else if (id == R.id.btn_pet) {
            i = 3;
        } else if (id == R.id.btn_romantic) {
            i = 4;
        } else if (id == R.id.btn_wipe) {
            i = 5;
        }
        this.mClickIndex = i;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        int i;
        int id = v.getId();
        if (id == R.id.btn_baby) {
            i = 1;
        } else if (id == R.id.btn_rest) {
            i = 2;
        } else if (id == R.id.btn_pet) {
            i = 3;
        } else if (id == R.id.btn_romantic) {
            i = 4;
        } else {
            i = id == R.id.btn_wipe ? 5 : 0;
        }
        ProfileManager.getInstance(this).editMode(i, this);
        return true;
    }
}
