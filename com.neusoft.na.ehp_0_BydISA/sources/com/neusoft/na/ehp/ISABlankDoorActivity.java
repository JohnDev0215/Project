package com.neusoft.na.ehp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.neusoft.dlt.LogService;
import com.neusoft.na.ehp.manager.EhpManager;
import com.neusoft.na.ehp.utils.DataCovert;
import com.neusoft.na.ehp.utils.MessageBean;
import com.neusoft.na.ehp.utils.MessageType;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class ISABlankDoorActivity extends Activity {
    private String TAG = "ISABlankDoorActivity";
    private RelativeLayout relativeLayout;
    private TextView tvAccident;
    private TextView tvConstruction;
    private TextView tvCountryCode;
    private TextView tvDBVersion;
    private TextView tvJams;
    private TextView tvJunctionSign;
    private TextView tvLocation;
    private TextView tvMapStatus;
    private TextView tvNextRampAndRampOffset;
    private TextView tvRoadtype;
    private TextView tvRoundaboutSign;
    private TextView tvSign;
    private TextView tvSpeedLimitAndSpeedlimitType;
    private TextView tvSpeedlimit_Rain;
    private TextView tvSpeedlimit_Snow;
    private TextView tvStopSign;
    private TextView tvTime;
    private TextView tvTimeConditionSignExist;
    private TextView tvTrafficLightSign;
    private TextView tvVersion;
    private TextView tvYieldSign;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_isa_blank_door);
        Log.i(this.TAG, "onCreate()  " + System.currentTimeMillis());
        EhpManager.isPostEventBus = true;
        if (!EventBus.getDefault().isRegistered(this)) {
            Log.i(this.TAG, "register start  " + System.currentTimeMillis());
            EventBus.getDefault().register(this);
            Log.i(this.TAG, "register  " + System.currentTimeMillis());
        }
        Log.i(this.TAG, "register over  " + System.currentTimeMillis());
        initView();
        findViewById(R.id.blackBg).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.ISABlankDoorActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ISABlankDoorActivity.this.changeColor(false);
            }
        });
        findViewById(R.id.whiteBg).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.ISABlankDoorActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ISABlankDoorActivity.this.changeColor(true);
            }
        });
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.ISABlankDoorActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ISABlankDoorActivity.this.finish();
            }
        });
    }

    private void initView() {
        this.tvVersion = (TextView) findViewById(R.id.tvVersion);
        this.tvDBVersion = (TextView) findViewById(R.id.tvDBVersion);
        this.tvLocation = (TextView) findViewById(R.id.tvLocation);
        this.tvTime = (TextView) findViewById(R.id.tvTime);
        this.tvMapStatus = (TextView) findViewById(R.id.tvMapStatus);
        this.tvSpeedLimitAndSpeedlimitType = (TextView) findViewById(R.id.tvSpeedLimitAndSpeedlimitType);
        this.tvTimeConditionSignExist = (TextView) findViewById(R.id.tvTimeConditionSignExist);
        this.tvSpeedlimit_Rain = (TextView) findViewById(R.id.tvSpeedlimit_Rain);
        this.tvSpeedlimit_Snow = (TextView) findViewById(R.id.tvSpeedlimit_Snow);
        this.tvCountryCode = (TextView) findViewById(R.id.tvCountryCode);
        this.tvRoadtype = (TextView) findViewById(R.id.tvRoadtype);
        this.tvNextRampAndRampOffset = (TextView) findViewById(R.id.tvNextRampAndRampOffset);
        this.tvJams = (TextView) findViewById(R.id.tvJams);
        this.tvConstruction = (TextView) findViewById(R.id.tvConstruction);
        this.tvAccident = (TextView) findViewById(R.id.tvAccident);
        this.tvSign = (TextView) findViewById(R.id.tvSign);
        this.tvRoundaboutSign = (TextView) findViewById(R.id.tvRoundaboutSign);
        this.tvJunctionSign = (TextView) findViewById(R.id.tvJunctionSign);
        this.tvTrafficLightSign = (TextView) findViewById(R.id.tvTrafficLightSign);
        this.tvStopSign = (TextView) findViewById(R.id.tvStopSign);
        this.tvYieldSign = (TextView) findViewById(R.id.tvYieldSign);
        this.relativeLayout = (RelativeLayout) findViewById(R.id.rl);
        Switch r0 = (Switch) findViewById(R.id.switchBtn);
        this.tvVersion.setText("Version:8.0.0.186.1-G_NEU_ISA");
        this.tvDBVersion.setText("DB Version:" + DataCovert.dbVersion);
        final SharedPreferences sharedPreferences = getSharedPreferences("dltLogStatus", 0);
        final boolean z = sharedPreferences.getBoolean("openDlt", false);
        r0.setChecked(z);
        r0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.neusoft.na.ehp.ISABlankDoorActivity.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                if (!z2 && z) {
                    ISABlankDoorActivity.this.stopService(new Intent(ISABlankDoorActivity.this, (Class<?>) LogService.class));
                }
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putBoolean("openDlt", z2);
                editorEdit.commit();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageBean messageBean) {
        if (messageBean.getMessageType() == MessageType.DATA) {
            int[] data = messageBean.getData();
            String strCovertMap_SpeedLimit_Unit = DataCovert.covertMap_SpeedLimit_Unit(data[10]);
            this.tvTime.setText("Time:" + DataCovert.currentTime());
            this.tvMapStatus.setText("Status:" + DataCovert.covertMap_Status(data[11]));
            this.tvCountryCode.setText("Country:" + DataCovert.covertCountryCode_10(data[9]));
            this.tvSpeedLimitAndSpeedlimitType.setText("Speed:" + DataCovert.covertSpeedlimit_S(data[0]) + strCovertMap_SpeedLimit_Unit + " (" + DataCovert.covertSpeedlimit_Type(data[5]) + ")");
            this.tvSpeedlimit_Rain.setText(DataCovert.covertSpeedlimit_Rain(data[6]) + strCovertMap_SpeedLimit_Unit);
            this.tvSpeedlimit_Snow.setText(DataCovert.covertSpeedlimit_Snow(data[7]) + strCovertMap_SpeedLimit_Unit);
            this.tvTimeConditionSignExist.setText("TimeExist:" + DataCovert.covertTimeConditionSignExist(data[2]) + "  TimeUsed:" + DataCovert.covertTimeConditionSignUsedSpeedlimit_S(data[1]));
            this.tvRoadtype.setText("Road:" + DataCovert.covertRoadtype_S(data[8]));
            this.tvNextRampAndRampOffset.setText("Ramp:" + DataCovert.covertNextRamp(data[3]) + " (Dist:" + DataCovert.covertRampOffset(data[4]) + ")");
            if (data.length > 17) {
                this.tvSign.setText("Sign:" + DataCovert.covertTraffic_sign_type_S(data[16]));
                this.tvJams.setText("Jams:" + DataCovert.covertTraffic_Jams_status_S(data[12]) + "," + DataCovert.plusAddl10(data[27]) + "-" + DataCovert.plusAddl11(data[28]) + "(" + DataCovert.plusAddl12(data[29]) + ")");
                this.tvConstruction.setText("Construction:" + DataCovert.covertConstruction_Zones_status_S(data[13]) + "," + DataCovert.plusAddl13(data[30]) + "-" + DataCovert.plusAddl14(data[31]) + "(" + DataCovert.plusAddl15(data[32]) + ")");
                this.tvAccident.setText("Accident:" + DataCovert.covertAccident_Ahead_status_S(data[14]) + "," + DataCovert.plusAddl16(data[33]) + "-" + DataCovert.plusAddl17(data[34]) + "(" + DataCovert.plusAddl18(data[35]) + ")");
                this.tvRoundaboutSign.setText("RoundaboutSign:(" + DataCovert.plusAddl0(data[17]) + "," + DataCovert.plusAddl1(data[18]) + ")");
                this.tvJunctionSign.setText("JunctionSign:(" + DataCovert.plusAddl2(data[19]) + "," + DataCovert.plusAddl3(data[20]) + ")");
                this.tvTrafficLightSign.setText("TrafficLightSign:(" + DataCovert.plusAddl4(data[21]) + "," + DataCovert.plusAddl5(data[22]) + ")");
                this.tvStopSign.setText("StopSign:(" + DataCovert.plusAddl6(data[23]) + "," + DataCovert.plusAddl7(data[24]) + ")");
                this.tvYieldSign.setText("YieldSign:(" + DataCovert.plusAddl8(data[25]) + "," + DataCovert.plusAddl9(data[26]) + ")");
            }
            this.tvDBVersion.setText("DB Version:" + DataCovert.dbVersion);
            Log.i(this.TAG, "DB Version:" + DataCovert.dbVersion + "  set dbVersion over  " + System.currentTimeMillis());
            return;
        }
        if (messageBean.getMessageType() == MessageType.LOCATION) {
            this.tvLocation.setText("Geo:  Latitude:" + messageBean.getLocation().getLatitude() + "  Longitude:" + messageBean.getLocation().getLongitude());
        } else if (messageBean.getMessageType() == MessageType.DB_VERSION) {
            this.tvDBVersion.setText("DB Version:" + messageBean.getDb_version());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeColor(boolean z) {
        this.tvVersion.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvLocation.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvTime.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvMapStatus.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvTimeConditionSignExist.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvCountryCode.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvRoadtype.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvNextRampAndRampOffset.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvJams.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvConstruction.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvAccident.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvSign.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvRoundaboutSign.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvJunctionSign.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvTrafficLightSign.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvStopSign.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvYieldSign.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.tvDBVersion.setTextColor(z ? getColor(R.color.text_color_black) : getColor(R.color.text_color_white));
        this.relativeLayout.setBackgroundColor(getColor(z ? R.color.bg_white_color : R.color.bg_black_color));
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this.TAG, "onDestroy()  " + System.currentTimeMillis());
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        EhpManager.isPostEventBus = false;
        Log.i(this.TAG, "onDestroy over  " + System.currentTimeMillis());
    }
}
