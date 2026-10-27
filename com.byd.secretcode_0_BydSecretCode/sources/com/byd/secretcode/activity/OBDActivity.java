package com.byd.secretcode.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.test.bean.NetMap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import com.byd.secretcode.util.MessageEvent;
import com.byd.secretcode.view.MultiSpinner;
import com.bydux.api.common.tools.log.BydLogHelper;
import defpackage.bm;
import defpackage.km;
import defpackage.mg;
import defpackage.rf;
import defpackage.sg;
import defpackage.tg;
import defpackage.xg;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class OBDActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int FUNCTION_CLOSE_ID_MAPPING = 10;
    private static final int FUNCTION_CLOSE_IGNITION = 6;
    private static final int FUNCTION_CLOSE_NETWORK_SEGMENT_MAPPING = 9;
    private static final int FUNCTION_OPEN_ID_MAPPING = 8;
    private static final int FUNCTION_OPEN_IGNITION = 5;
    private static final int FUNCTION_OPEN_NETWORK_SEGMENT_MAPPING = 7;
    private static final int OBD_ADAS = 5;
    private static final int OBD_CARBODY_2 = 6;
    private static final int OBD_CAR_BODY = 2;
    private static final int OBD_CHASSIS_NET = 4;
    private static final int OBD_ENERGY = 3;
    private static final int OBD_OP_NETMAPPING_IDS = 2;
    private static final int OBD_OP_NETMAPPING_NET = 1;
    private static final int OBD_OP_NUMOFIGNCYCLES_CONFIGURE = 0;
    private static final int OBD_SMART_ENTRY = 1;
    private static final String TAG = "OBDActivity";
    private static Dialog mShowDialog;
    public Button bt_back;
    public Button bt_close;
    public Button bt_close_id_mapping;
    public Button bt_close_network_mapping;
    public Button bt_confirm;
    public Button bt_id_back;
    public Button bt_id_confirm;
    public Button bt_id_mapping;
    public Button bt_id_reset;
    public Button bt_network_segment_mapping;
    public Button bt_open;
    public Button bt_start_mapping;
    public Button button;
    public Button button6;
    public Button button7;
    public Button button8;
    private Set<Object> checkedSet;
    private List<xg> dataList;
    public EditText editTextNumber;
    public EditText edittext_adas_net;
    public EditText edittext_car_body;
    public EditText edittext_car_body_2;
    public EditText edittext_chassis_net;
    public EditText edittext_energy;
    public EditText edittext_smart_entry;
    public ImageButton iv_adas_net_del;
    public ImageButton iv_car_body_2_del;
    public ImageButton iv_car_body_del;
    public ImageButton iv_chassis_net_del;
    public ImageButton iv_energy_del;
    public ImageButton iv_smart_entry_del;
    public AbsBYDAutoTestListener listener;
    public BYDAutoTestDevice mBYDAutoTestDevice;
    public int mNetworkID;
    private m mapHandler;
    private MultiSpinner multiSpinner;
    public View obd_cheack_imei;
    public View obd_data_mapping_view;
    public EditText obd_et_password;
    public View obd_id_segment_mapping_view;
    public TextView obd_imei_tv;
    public View obd_main_view;
    public View obd_network_segment_mapping_view;
    public Button obd_ok_btn;
    public RelativeLayout rl_adas_net;
    public RelativeLayout rl_car_body;
    public RelativeLayout rl_car_body_2;
    public RelativeLayout rl_chassis_net;
    public RelativeLayout rl_energy;
    public RelativeLayout rl_smart_entry;
    public TextView textView8;
    public TextView textView9;
    private int mFunctionID = -1;
    private int mCycleNum = 10;
    private String mImeiStr = "";
    private final int PASSWORD_SIZE = 6;
    public ArrayList<NetMap> netMapList = new ArrayList<>();

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OBDActivity.this.back();
            OBDActivity.mShowDialog.dismiss();
            OBDActivity.this.mapHandler.sendEmptyMessage(6);
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OBDActivity.mShowDialog.dismiss();
        }
    }

    public class c implements DialogInterface.OnDismissListener {
        public c() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OBDActivity.this.textView8.setText("当前发送的数据为0x747/0x74F / 1003");
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OBDActivity.this.textView8.setText("当前发送的数据为0x747/0x74F / 2701");
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OBDActivity.this.textView8.setText("当前发送的数据为0x747/0x74F / 2702");
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    public class h implements AdapterView.OnItemSelectedListener {
        public h() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (i == 0) {
                OBDActivity.this.mNetworkID = 5;
            } else if (i == 1) {
                OBDActivity.this.mNetworkID = 4;
            } else if (i == 2) {
                OBDActivity.this.mNetworkID = 3;
            } else if (i == 3) {
                OBDActivity.this.mNetworkID = 2;
            } else if (i == 4) {
                OBDActivity.this.mNetworkID = 6;
            } else if (i == 5) {
                OBDActivity.this.mNetworkID = 1;
            }
            OBDActivity.this.mFunctionID = 7;
            OBDActivity.this.netMapList.clear();
            OBDActivity.this.netMapList.add(new NetMap(OBDActivity.this.mNetworkID, new ArrayList()));
            OBDActivity.this.mapHandler.sendEmptyMessage(7);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class i implements TextWatcher {
        public i() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class j extends AbsBYDAutoTestListener {

        public class a implements Runnable {
            public final /* synthetic */ int b;

            public a(int i) {
                this.b = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.b == 1) {
                    OBDActivity oBDActivity = OBDActivity.this;
                    mg.a(oBDActivity, oBDActivity.getString(R.string.obd_This_feature_cannot_be_turned_on));
                }
            }
        }

        public j() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onObdReport(int i, int i2) {
            super.onObdReport(i, i2);
            BydLogHelper.logD("iviObdOperationType / " + i + ", iviObdStatus / " + i2);
            OBDActivity.this.runOnUiThread(new a(i2));
        }
    }

    public class k implements View.OnClickListener {
        public final /* synthetic */ AlertDialog b;

        public k(AlertDialog alertDialog) {
            this.b = alertDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.b.dismiss();
            OBDActivity.this.finish();
        }
    }

    public class l implements View.OnClickListener {
        public final /* synthetic */ AlertDialog b;

        public l(AlertDialog alertDialog) {
            this.b = alertDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.b.dismiss();
        }
    }

    public final class m extends Handler {
        public m() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            BydLogHelper.logD("mFunctionID / " + OBDActivity.this.mFunctionID);
            int i = message.what;
            if (i == 5) {
                OBDActivity oBDActivity = OBDActivity.this;
                oBDActivity.mCycleNum = oBDActivity.getValue();
                if (OBDActivity.this.mCycleNum < 1 || OBDActivity.this.mCycleNum > 100) {
                    OBDActivity oBDActivity2 = OBDActivity.this;
                    mg.a(oBDActivity2, oBDActivity2.getResources().getString(R.string.obd_waring));
                    return;
                } else {
                    OBDActivity oBDActivity3 = OBDActivity.this;
                    oBDActivity3.obdRequest(0, oBDActivity3.mCycleNum, OBDActivity.this.netMapList, true);
                    return;
                }
            }
            if (i != 6) {
                if (i == 7) {
                    OBDActivity oBDActivity4 = OBDActivity.this;
                    oBDActivity4.obdRequest(1, oBDActivity4.mCycleNum, OBDActivity.this.netMapList, true);
                    return;
                } else {
                    if (i != 8) {
                        return;
                    }
                    OBDActivity oBDActivity5 = OBDActivity.this;
                    oBDActivity5.obdRequest(2, oBDActivity5.mCycleNum, OBDActivity.this.netMapList, true);
                    return;
                }
            }
            if (OBDActivity.this.mFunctionID == 9) {
                OBDActivity oBDActivity6 = OBDActivity.this;
                oBDActivity6.obdRequest(1, oBDActivity6.mCycleNum, OBDActivity.this.netMapList, false);
            } else if (OBDActivity.this.mFunctionID == 10) {
                OBDActivity oBDActivity7 = OBDActivity.this;
                oBDActivity7.obdRequest(2, oBDActivity7.mCycleNum, OBDActivity.this.netMapList, false);
            }
        }

        public /* synthetic */ m(OBDActivity oBDActivity, d dVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void back() {
        this.obd_main_view.setVisibility(8);
        this.obd_data_mapping_view.setVisibility(0);
        this.obd_network_segment_mapping_view.setVisibility(8);
        this.obd_id_segment_mapping_view.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getValue() {
        if (TextUtils.isEmpty(this.editTextNumber.getText().toString().trim())) {
            return 10;
        }
        try {
            return Integer.parseInt(this.editTextNumber.getText().toString().trim());
        } catch (NumberFormatException unused) {
            return 10;
        }
    }

    @km(threadMode = ThreadMode.MAIN)
    private void initData() {
        String strA = sg.a();
        this.mImeiStr = strA;
        if (strA != null) {
            this.obd_imei_tv.setText(getString(R.string.imei) + this.mImeiStr);
        }
        this.mBYDAutoTestDevice = BYDAutoTestDevice.getInstance(getApplicationContext());
        j jVar = new j();
        this.listener = jVar;
        this.mBYDAutoTestDevice.registerListener((AbsBYDAutoTestListener) jVar);
        ArrayList arrayList = new ArrayList();
        xg xgVar = new xg();
        xgVar.c(getString(R.string.obd_smart_entry));
        xgVar.d("01");
        arrayList.add(xgVar);
        xg xgVar2 = new xg();
        xgVar2.c(getString(R.string.obd_car_body));
        xgVar2.d("02");
        arrayList.add(xgVar2);
        xg xgVar3 = new xg();
        xgVar3.c(getString(R.string.obd_energy));
        xgVar3.d("03");
        arrayList.add(xgVar3);
        xg xgVar4 = new xg();
        xgVar4.c(getString(R.string.obd_chassis_net));
        xgVar4.d("04");
        arrayList.add(xgVar4);
        xg xgVar5 = new xg();
        xgVar5.c(getString(R.string.obd_adas_net));
        xgVar5.d("05");
        arrayList.add(xgVar5);
        xg xgVar6 = new xg();
        xgVar6.c(getString(R.string.obd_car_body_2));
        xgVar6.d("06");
        arrayList.add(xgVar6);
        this.multiSpinner.setDataList(arrayList);
    }

    private void initView() {
        this.obd_cheack_imei = findViewById(R.id.obd_cheack_imei);
        this.obd_main_view = findViewById(R.id.obd_main_view);
        this.obd_data_mapping_view = findViewById(R.id.obd_data_mapping_view);
        this.obd_network_segment_mapping_view = findViewById(R.id.obd_network_segment_mapping_view);
        this.obd_id_segment_mapping_view = findViewById(R.id.obd_id_segment_mapping_view);
        this.bt_open = (Button) findViewById(R.id.bt_open);
        this.bt_close = (Button) findViewById(R.id.bt_close);
        this.bt_confirm = (Button) findViewById(R.id.bt_confirm);
        this.bt_start_mapping = (Button) findViewById(R.id.bt_start_mapping);
        this.bt_network_segment_mapping = (Button) findViewById(R.id.bt_network_segment_mapping);
        this.bt_id_mapping = (Button) findViewById(R.id.bt_id_mapping);
        this.bt_close_network_mapping = (Button) findViewById(R.id.bt_close_network_mapping);
        this.bt_close_id_mapping = (Button) findViewById(R.id.bt_close_id_mapping);
        this.bt_id_confirm = (Button) findViewById(R.id.bt_id_confirm);
        this.bt_id_reset = (Button) findViewById(R.id.bt_id_reset);
        this.editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        this.edittext_smart_entry = (EditText) findViewById(R.id.edittext_smart_entry);
        this.edittext_car_body = (EditText) findViewById(R.id.edittext_car_body);
        this.edittext_energy = (EditText) findViewById(R.id.edittext_energy);
        this.edittext_chassis_net = (EditText) findViewById(R.id.edittext_chassis_net);
        this.edittext_adas_net = (EditText) findViewById(R.id.edittext_adas_net);
        this.edittext_car_body_2 = (EditText) findViewById(R.id.edittext_car_body_2);
        this.iv_smart_entry_del = (ImageButton) findViewById(R.id.iv_smart_entry_del);
        this.iv_car_body_del = (ImageButton) findViewById(R.id.iv_car_body_del);
        this.iv_energy_del = (ImageButton) findViewById(R.id.iv_energy_del);
        this.iv_chassis_net_del = (ImageButton) findViewById(R.id.iv_chassis_net_del);
        this.iv_adas_net_del = (ImageButton) findViewById(R.id.iv_adas_net_del);
        this.iv_car_body_2_del = (ImageButton) findViewById(R.id.iv_car_body_2_del);
        this.rl_smart_entry = (RelativeLayout) findViewById(R.id.rl_smart_entry);
        this.rl_car_body = (RelativeLayout) findViewById(R.id.rl_car_body);
        this.rl_energy = (RelativeLayout) findViewById(R.id.rl_energy);
        this.rl_chassis_net = (RelativeLayout) findViewById(R.id.rl_chassis_net);
        this.rl_adas_net = (RelativeLayout) findViewById(R.id.rl_adas_net);
        this.rl_car_body_2 = (RelativeLayout) findViewById(R.id.rl_car_body_2);
        this.obd_imei_tv = (TextView) findViewById(R.id.obd_imei_tv);
        this.obd_et_password = (EditText) findViewById(R.id.obd_et_password);
        this.obd_ok_btn = (Button) findViewById(R.id.obd_ok_btn);
        this.button = (Button) findViewById(R.id.button);
        this.button6 = (Button) findViewById(R.id.button6);
        this.button7 = (Button) findViewById(R.id.button7);
        this.button8 = (Button) findViewById(R.id.button8);
        this.textView8 = (TextView) findViewById(R.id.textView8);
        this.textView9 = (TextView) findViewById(R.id.textView9);
        this.bt_back = (Button) findViewById(R.id.bt_back);
        this.bt_id_back = (Button) findViewById(R.id.bt_id_back);
        this.button.setOnClickListener(new d());
        this.button6.setOnClickListener(new e());
        this.button7.setOnClickListener(new f());
        this.button8.setOnClickListener(new g());
        this.bt_open.setOnClickListener(this);
        this.bt_close.setOnClickListener(this);
        this.bt_confirm.setOnClickListener(this);
        this.bt_start_mapping.setOnClickListener(this);
        this.bt_network_segment_mapping.setOnClickListener(this);
        this.bt_id_mapping.setOnClickListener(this);
        this.bt_close_network_mapping.setOnClickListener(this);
        this.bt_close_id_mapping.setOnClickListener(this);
        this.bt_id_confirm.setOnClickListener(this);
        this.bt_id_reset.setOnClickListener(this);
        this.iv_smart_entry_del.setOnClickListener(this);
        this.iv_car_body_del.setOnClickListener(this);
        this.iv_energy_del.setOnClickListener(this);
        this.iv_chassis_net_del.setOnClickListener(this);
        this.iv_adas_net_del.setOnClickListener(this);
        this.iv_car_body_2_del.setOnClickListener(this);
        this.obd_ok_btn.setOnClickListener(this);
        this.bt_back.setOnClickListener(this);
        this.bt_id_back.setOnClickListener(this);
        ArrayList arrayList = new ArrayList();
        arrayList.add(getString(R.string.obd_adas_net));
        arrayList.add(getString(R.string.obd_chassis_net));
        arrayList.add(getString(R.string.obd_energy));
        arrayList.add(getString(R.string.obd_car_body));
        arrayList.add(getString(R.string.obd_car_body_2));
        arrayList.add(getString(R.string.obd_smart_entry));
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        Spinner spinner = (Spinner) findViewById(R.id.spinner_network);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        spinner.setOnItemSelectedListener(new h());
        MultiSpinner multiSpinner = (MultiSpinner) findViewById(R.id.mulSpinner);
        this.multiSpinner = multiSpinner;
        multiSpinner.setTitle(getString(R.string.obd_selection_1));
        this.edittext_smart_entry.addTextChangedListener(new i());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void obdRequest(int i2, int i3, ArrayList<NetMap> arrayList, boolean z) {
        if (this.mBYDAutoTestDevice == null) {
            BydLogHelper.logE("mBYDAutoTestDevice is null!");
            return;
        }
        BydLogHelper.logE("obdRequest iviObdOperationType / " + i2 + ", ignCycleNum / " + i3 + ", mapOpenStatus / " + z);
        this.mBYDAutoTestDevice.obdRequest(i2, i3, arrayList, z);
    }

    private void reset() {
        this.edittext_smart_entry.setText("");
        this.edittext_car_body.setText("");
        this.edittext_energy.setText("");
        this.edittext_chassis_net.setText("");
        this.edittext_adas_net.setText("");
        this.edittext_car_body_2.setText("");
        this.rl_smart_entry.setVisibility(8);
        this.rl_car_body.setVisibility(8);
        this.rl_energy.setVisibility(8);
        this.rl_chassis_net.setVisibility(8);
        this.rl_adas_net.setVisibility(8);
        this.rl_car_body_2.setVisibility(8);
        this.multiSpinner.setCheckedSet(new HashSet());
        this.netMapList.clear();
    }

    public void ShowCloseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewInflate = LayoutInflater.from(getApplication()).inflate(R.layout.map_dialog_layout, (ViewGroup) null);
        builder.setView(viewInflate);
        AlertDialog alertDialogCreate = builder.create();
        Window window = alertDialogCreate.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        window.setAttributes(attributes);
        Button button = (Button) viewInflate.findViewById(R.id.bt_mian_comfirm);
        Button button2 = (Button) viewInflate.findViewById(R.id.bt_mian_cancel);
        button.setOnClickListener(new k(alertDialogCreate));
        button2.setOnClickListener(new l(alertDialogCreate));
        alertDialogCreate.show();
    }

    public void ShowDialog() {
        mShowDialog = new Dialog(this, R.style.NormalDialogStyle);
        View viewInflate = View.inflate(this, R.layout.dialog_open_file_manager, null);
        Button button = (Button) viewInflate.findViewById(R.id.bt_not_enough_open);
        Button button2 = (Button) viewInflate.findViewById(R.id.bt_not_enough_exit);
        mShowDialog.setContentView(viewInflate);
        Window window = mShowDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        window.setAttributes(attributes);
        button.setOnClickListener(new a());
        button2.setOnClickListener(new b());
        mShowDialog.setOnDismissListener(new c());
        mShowDialog.show();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ArrayList arrayList;
        String strSubstring;
        int id = view.getId();
        switch (id) {
            case R.id.bt_back /* 2131296352 */:
                back();
                break;
            case R.id.bt_close /* 2131296353 */:
                ShowCloseDialog();
                break;
            case R.id.bt_close_id_mapping /* 2131296354 */:
                this.mFunctionID = 10;
                ShowDialog();
                break;
            case R.id.bt_close_network_mapping /* 2131296355 */:
                this.mFunctionID = 9;
                ShowDialog();
                break;
            case R.id.bt_confirm /* 2131296356 */:
                this.mFunctionID = 5;
                this.mapHandler.sendEmptyMessage(5);
                break;
            default:
                switch (id) {
                    case R.id.bt_id_back /* 2131296362 */:
                        back();
                        break;
                    case R.id.bt_id_confirm /* 2131296363 */:
                        this.mFunctionID = 8;
                        this.netMapList.clear();
                        String string = this.edittext_smart_entry.getText().toString();
                        String string2 = this.edittext_car_body.getText().toString();
                        String string3 = this.edittext_energy.getText().toString();
                        String string4 = this.edittext_chassis_net.getText().toString();
                        String string5 = this.edittext_adas_net.getText().toString();
                        String string6 = this.edittext_car_body_2.getText().toString();
                        ArrayList arrayList2 = new ArrayList();
                        ArrayList arrayList3 = new ArrayList();
                        ArrayList arrayList4 = new ArrayList();
                        ArrayList arrayList5 = new ArrayList();
                        ArrayList arrayList6 = new ArrayList();
                        ArrayList arrayList7 = new ArrayList();
                        try {
                            if (string.isEmpty()) {
                                arrayList = arrayList7;
                            } else {
                                String[] strArrSplit = string.replace("0x", "").trim().split("[,\\，]");
                                StringBuilder sb = new StringBuilder();
                                arrayList = arrayList7;
                                try {
                                    sb.append("分割后的数据 smart_entry.length / ");
                                    sb.append(strArrSplit.length);
                                    BydLogHelper.logE(sb.toString());
                                    int i2 = 0;
                                    while (i2 < strArrSplit.length) {
                                        BydLogHelper.logE("分割后的数据 / " + strArrSplit[i2]);
                                        arrayList2.add(Integer.valueOf(Integer.parseInt(strArrSplit[i2], 16)));
                                        BydLogHelper.logE("分割后的数据11111 / " + strArrSplit[i2]);
                                        i2++;
                                        string6 = string6;
                                    }
                                } catch (NumberFormatException unused) {
                                }
                            }
                            String str = string6;
                            if (!string2.isEmpty()) {
                                for (String str2 : string2.replace("0x", "").trim().split("[,\\，]")) {
                                    arrayList3.add(Integer.valueOf(Integer.parseInt(str2, 16)));
                                }
                            }
                            if (!string3.isEmpty()) {
                                for (String str3 : string3.replace("0x", "").trim().split("[,\\，]")) {
                                    arrayList4.add(Integer.valueOf(Integer.parseInt(str3, 16)));
                                }
                            }
                            if (!string4.isEmpty()) {
                                for (String str4 : string4.replace("0x", "").trim().split("[,\\，]")) {
                                    arrayList5.add(Integer.valueOf(Integer.parseInt(str4, 16)));
                                }
                            }
                            if (!string5.isEmpty()) {
                                for (String str5 : string5.replace("0x", "").trim().split("[,\\，]")) {
                                    arrayList6.add(Integer.valueOf(Integer.parseInt(str5, 16)));
                                }
                            }
                            if (!str.isEmpty()) {
                                String[] strArrSplit2 = str.replace("0x", "").trim().split("[,\\，]");
                                int i3 = 0;
                                while (i3 < strArrSplit2.length) {
                                    arrayList7 = arrayList;
                                    arrayList7.add(Integer.valueOf(Integer.parseInt(strArrSplit2[i3], 16)));
                                    i3++;
                                    arrayList = arrayList7;
                                }
                            }
                            arrayList7 = arrayList;
                            break;
                        } catch (NumberFormatException unused2) {
                        }
                        this.netMapList.add(new NetMap(1, arrayList2));
                        this.netMapList.add(new NetMap(2, arrayList3));
                        this.netMapList.add(new NetMap(3, arrayList4));
                        this.netMapList.add(new NetMap(4, arrayList5));
                        this.netMapList.add(new NetMap(5, arrayList6));
                        this.netMapList.add(new NetMap(6, arrayList7));
                        this.mapHandler.sendEmptyMessage(8);
                        break;
                    case R.id.bt_id_mapping /* 2131296364 */:
                        this.obd_main_view.setVisibility(8);
                        this.obd_data_mapping_view.setVisibility(8);
                        this.obd_network_segment_mapping_view.setVisibility(8);
                        this.obd_id_segment_mapping_view.setVisibility(0);
                        break;
                    case R.id.bt_id_reset /* 2131296365 */:
                        reset();
                        break;
                    default:
                        switch (id) {
                            case R.id.bt_network_segment_mapping /* 2131296371 */:
                                this.obd_main_view.setVisibility(8);
                                this.obd_data_mapping_view.setVisibility(8);
                                this.obd_network_segment_mapping_view.setVisibility(0);
                                this.obd_id_segment_mapping_view.setVisibility(8);
                                break;
                            case R.id.bt_open /* 2131296374 */:
                                back();
                                break;
                            case R.id.bt_start_mapping /* 2131296377 */:
                                this.bt_network_segment_mapping.setVisibility(0);
                                this.bt_id_mapping.setVisibility(0);
                                break;
                            case R.id.obd_ok_btn /* 2131296660 */:
                                String string7 = this.obd_et_password.getText().toString();
                                if (string7 != null && string7.isEmpty()) {
                                    mg.b(this, getString(R.string.offline_config_password_err));
                                } else if (string7.length() == 6) {
                                    if (TextUtils.isEmpty(this.mImeiStr) || this.mImeiStr.length() < 6) {
                                        strSubstring = "112233";
                                    } else {
                                        String str6 = this.mImeiStr;
                                        strSubstring = str6.substring(str6.length() - 6, this.mImeiStr.length());
                                    }
                                    rf rfVarK = rf.k();
                                    tg.b();
                                    boolean zJ = rfVarK.j(strSubstring, tg.e(string7));
                                    BydLogHelper.logD(TAG, "onTextChanged check =" + zJ + ",imei =" + strSubstring);
                                    if (zJ) {
                                        this.obd_et_password.setText("");
                                        this.obd_cheack_imei.setVisibility(8);
                                        this.obd_main_view.setVisibility(0);
                                        this.obd_data_mapping_view.setVisibility(8);
                                        this.obd_network_segment_mapping_view.setVisibility(8);
                                        this.obd_id_segment_mapping_view.setVisibility(8);
                                    } else {
                                        mg.b(this, getString(R.string.offline_config_password_err));
                                    }
                                } else {
                                    mg.b(this, getString(R.string.offline_config_password_err));
                                }
                                break;
                            default:
                                switch (id) {
                                    case R.id.iv_adas_net_del /* 2131296558 */:
                                        this.edittext_adas_net.setText("");
                                        break;
                                    case R.id.iv_car_body_2_del /* 2131296559 */:
                                        this.edittext_car_body_2.setText("");
                                        break;
                                    case R.id.iv_car_body_del /* 2131296560 */:
                                        this.edittext_car_body.setText("");
                                        break;
                                    case R.id.iv_chassis_net_del /* 2131296561 */:
                                        this.edittext_chassis_net.setText("");
                                        break;
                                    case R.id.iv_energy_del /* 2131296562 */:
                                        this.edittext_energy.setText("");
                                        break;
                                    case R.id.iv_smart_entry_del /* 2131296563 */:
                                        this.edittext_smart_entry.setText("");
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @km(threadMode = ThreadMode.MAIN)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.obd_activity);
        this.mapHandler = new m(this, null);
        initView();
        initData();
        bm.c().o(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @km(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        BydLogHelper.logE(TAG, "onMessageEvent tag = " + messageEvent.tag);
        this.rl_smart_entry.setVisibility(8);
        this.rl_car_body.setVisibility(8);
        this.rl_energy.setVisibility(8);
        this.rl_chassis_net.setVisibility(8);
        this.rl_adas_net.setVisibility(8);
        this.rl_car_body_2.setVisibility(8);
        if ("obd_change".equals(messageEvent.tag)) {
            List<xg> checkedOptions = this.multiSpinner.getCheckedOptions();
            this.dataList = checkedOptions;
            int size = checkedOptions.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (getString(R.string.obd_smart_entry).equals(this.dataList.get(i2).a())) {
                    this.rl_smart_entry.setVisibility(0);
                } else if (getString(R.string.obd_car_body).equals(this.dataList.get(i2).a())) {
                    this.rl_car_body.setVisibility(0);
                } else if (getString(R.string.obd_energy).equals(this.dataList.get(i2).a())) {
                    this.rl_energy.setVisibility(0);
                } else if (getString(R.string.obd_chassis_net).equals(this.dataList.get(i2).a())) {
                    this.rl_chassis_net.setVisibility(0);
                } else if (getString(R.string.obd_adas_net).equals(this.dataList.get(i2).a())) {
                    this.rl_adas_net.setVisibility(0);
                } else if (getString(R.string.obd_car_body_2).equals(this.dataList.get(i2).a())) {
                    this.rl_car_body_2.setVisibility(0);
                }
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    @km(threadMode = ThreadMode.MAIN)
    public void onPause() {
        super.onPause();
        bm.c().q(this);
        this.mBYDAutoTestDevice.unregisterListener(this.listener);
        this.mCycleNum = 10;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    @km(threadMode = ThreadMode.MAIN)
    public void onResume() {
        super.onResume();
    }
}
