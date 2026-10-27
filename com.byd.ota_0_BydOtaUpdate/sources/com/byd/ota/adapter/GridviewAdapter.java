package com.byd.ota.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import c.a.b.d.e;
import com.byd.ota.R;
import com.byd.ota.master.VehicleVersionInfo;
import com.byd.repository.bean.EcuVersionInfo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class GridviewAdapter extends BaseAdapter {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static HashMap<String, Boolean> f3045d;
    public static HashMap<String, Boolean> s;
    public static HashMap<String, Boolean> x;
    public VehicleVersionInfo E3;
    public Context F3;
    public LayoutInflater G3;
    public a H3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f3044c = GridviewAdapter.class.getSimpleName();
    public static HashMap<String, String> y = new HashMap<String, String>() { // from class: com.byd.ota.adapter.GridviewAdapter.1
        {
            put("STEBU_Media_Autolink_00100001", "8155多媒体");
            put("STEBU_R_BCM_BYD_00400001", "右车身控制器(R1 PRO)");
            put("STEBU_AMP_BYD_00100001", "外置功放");
            put("STEBU_AMP_Toppower_00100001", "外置功放");
            put("STEBU_ACC_BYD_00100001", "空调压缩机");
            put("STEBU_RPTC_HGTECH_00100001", "后PTC-主机(辅助加热)");
            put("STEBU_FPTC_BYD_00100001", "前PTC(前辅助加热)");
            put("STEBU_PM2.5_BYD_00100001", "绿净系统 /PM 2.5");
            put("STEBU_LBMS_BYD_00100001", "启动铁电池(低电压电池管理系统)");
            put("STEBU_CS_KOSTAL_00100001", "组合开关");
            put("STEBU_IRVM_BYD_00100001", "内后视镜");
            put("STEBU_FL_L_BYD_00100001", "左前组合灯");
            put("STEBU_FL_R_BYD_00100001", "右前组合灯");
            put("STEBU_rear CPD radar_WHST_00100001", "后CPD毫米波雷达");
            put("STEBU_front CPD radar_WHST_00100001", "前CPD毫米波雷达");
            put("STEBU_FMRR_BOSCH_00300001", "前置毫米波雷达");
            put("STEBU_MPC_BOSCH_00100001", "多功能视频控制器");
            put("STEBU_INS_BYD_00100001", "液晶仪表");
            put("STEBU_INS_DESAY_00100001", "液晶仪表");
            put("STEBU_HFRM_BYD_00100001", "高频接收模块");
            put("STEBU_SEC_BYD_00100001", "智能进入控制器");
            put("STEBU_INFC_BYD_00100001", "车内智能进入控制器");
            put("STEBU_REPS_BSHH_00100001", "电动助力转向/R-EPS");
            put("STEBU_IPB_BOSCH_00100001", "电液制动");
            put("STEBU_SCPA_BYD_00100001", "换挡操纵机构面板");
            put("STEBU_VCU_BYD_00100001", "整车控制器/VCU");
            put("STEBU_FMCU_BYD_00100001", "前驱动电机控制器/FMCU");
            put("STEBU_EDCM_BYD_00100001", "电驱充电模块");
            put("STEBU_RMCU_BYD_00100001", "后驱动电机控制器/RMCU");
            put("STEBU_DCVDM_BYD_00100001", "直流电压检测模块");
            put("STEBU_BMS_BYD_03300001", "动力电池管理/BMC");
            put("STEBU_OBC_BYD_00100001", "车载充电器");
            put("STEBU_DC_DC_BYD_00100001", "DC-DC");
            put("STEBU_EVCC_BYD_00100001", "通讯转换器");
            put("STEBU_LA_BCM_BYD_00600001", "左车身控制器(L3 PRO)");
            put("STEBU_BSD_R_BOSCH_00100001", "从盲区监测");
            put("STEBU_LFCR_BOSCH_00100001", "左前角雷达");
            put("STEBU_RFCR_BOSCH_00100001", "右前角雷达");
            put("STEBU_BSD_BOSCH_00100001", "盲区监测");
            put("STEBU_HUD_BYD_00100001", "抬头显示屏");
            put("STEBU_SWS_BYD_00100001", "转向盘开关");
            put("STEBU_BA_BCM_BYD_00300001", "后车身域控制器A_B3");
            put("STEBU_BB_BCM_BYD_00300001", "后车身域控制器B_B3");
            put("STEBU_PadHolder_BYD_00100001", "显示屏支架总成");
            put("STEBU_WCT_BJYG_00100001", "无线充电模块");
            put("STEBU_WCT_BJYG_00500001", "无线充电模块_从机");
            put("STEBU_Mid-CPD-R_WHST_00100001", "中CPD毫米波雷达");
            put("STEBU_SRS_BYD_00100001", "安全气囊/SRS");
            put("STEBU_SRS_BYD_00500001", "安全气囊_瑞萨");
            put("STEBU_Disus_KEBODA_00200001", "主动悬架控制器");
            put("STEBU_TBOX_DESAY_00100001", "tbox");
            put("STEBU_ACCP_R_BYD_00100001", "后自动空调控制面板");
            put("STEBU_RL_L_BYD_00100001", "左组合灯控制模块");
            put("STEBU_RL_R_BYD_00100001", "右组合灯控制模块");
            put("STEBU_TL_L_BYD_00100001", "左尾灯控制模块");
            put("STEBU_TL_R_BYD_00100001", "右尾灯控制模块");
            put("STEBU_DAL_REBO_01300001", "右前门板氛围灯前灯头");
            put("STEBU_DAL_REBO_01400001", "右前门板氛围灯后灯头");
            put("STEBU_DAL_REBO_01500001", "右后门板氛围灯前灯头");
            put("STEBU_DAL_REBO_01600001", "右后门板氛围灯后灯头");
            put("STEBU_DAL_REBO_00900001", "左前门板氛围灯前灯头");
            put("STEBU_DAL_REBO_01000001", "左前门板氛围灯后灯头");
            put("STEBU_DAL_REBO_01100001", "左后门板氛围灯前灯头");
            put("STEBU_DAL_REBO_01200001", "左后门板氛围灯后灯头");
            put("STEBU_IAL_REBO_00400001", "副仪表板右氛围灯前灯头");
            put("STEBU_IAL_REBO_00900001", "副仪表板右氛围灯右灯头");
            put("STEBU_IAL_REBO_00600001", "副仪表板左氛围灯前灯头");
            put("STEBU_PAL_REBO_00200001", "左面板氛围灯");
            put("STEBU_PAL_REBO_00300001", "中面板氛围灯");
            put("STEBU_PAL_REBO_00400001", "右面板氛围灯");
            put("STEBU_IAL_REBO_00100001", "仪表板左氛围灯灯头");
            put("STEBU_IAL_REBO_00200001", "仪表板中氛围灯灯头");
            put("STEBU_IAL_REBO_00300001", "仪表板右氛围灯左灯头");
        }
    };

    public interface a {
        void a();
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public CheckBox f3046a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f3047b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f3048c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CheckBox f3049d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public CheckBox f3050e;
    }

    public GridviewAdapter(VehicleVersionInfo vehicleVersionInfo, Context context) {
        this.G3 = null;
        this.F3 = context;
        this.E3 = vehicleVersionInfo;
        this.G3 = LayoutInflater.from(context);
        f3045d = new HashMap<>();
        s = new HashMap<>();
        x = new HashMap<>();
        e();
    }

    public static HashMap<String, Boolean> a() {
        return s;
    }

    public static int b() {
        Iterator<Map.Entry<String, Boolean>> it = c().entrySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (c().get(it.next().getKey()).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    public static HashMap<String, Boolean> c() {
        return f3045d;
    }

    public static HashMap<String, Boolean> d() {
        return x;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void h(b bVar, int i2, CompoundButton compoundButton, boolean z) {
        e.c(f3044c, "onItemClick " + bVar.f3048c.getText().toString() + " " + bVar.f3046a.isChecked());
        c().put(bVar.f3048c.getText().toString(), Boolean.valueOf(bVar.f3046a.isChecked()));
        a aVar = this.H3;
        if (aVar != null) {
            aVar.a();
        }
        if (!z || !f(this.E3.strategy.sequence.get(i2).ecuId)) {
            bVar.f3050e.setVisibility(4);
            bVar.f3049d.setVisibility(4);
        } else {
            bVar.f3050e.setVisibility(0);
            bVar.f3049d.setVisibility(0);
            bVar.f3050e.setChecked(true);
            bVar.f3049d.setChecked(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j(int i2, b bVar, CompoundButton compoundButton, boolean z) {
        s.put(this.E3.strategy.sequence.get(i2).ecuId, Boolean.valueOf(z));
        if (bVar.f3049d.isChecked() == z) {
            bVar.f3046a.setChecked(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void l(int i2, b bVar, CompoundButton compoundButton, boolean z) {
        x.put(this.E3.strategy.sequence.get(i2).ecuId, Boolean.valueOf(z));
        if (bVar.f3050e.isChecked() == z) {
            bVar.f3046a.setChecked(z);
        }
    }

    public final void e() {
        e.c("GridviewAdapter", " initDate " + this.E3.strategy.sequence.size());
        for (int i2 = 0; i2 < this.E3.strategy.sequence.size(); i2++) {
            e.c("GridviewAdapter", " initDate i:" + i2 + " " + this.E3.strategy.sequence.get(i2).ecuId);
            c().put(this.E3.strategy.sequence.get(i2).ecuId, Boolean.FALSE);
        }
    }

    public boolean f(String str) {
        boolean z;
        boolean z2;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Iterator<EcuVersionInfo> it = this.E3.targetPackageInfo.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (str.equals(it.next().ecuId)) {
                z = true;
                break;
            }
        }
        Iterator<EcuVersionInfo> it2 = this.E3.configPackageInfo.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z2 = false;
                break;
            }
            if (str.equals(it2.next().ecuId)) {
                z2 = true;
                break;
            }
        }
        return z && z2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        VehicleVersionInfo vehicleVersionInfo = this.E3;
        if (vehicleVersionInfo != null) {
            return vehicleVersionInfo.strategy.sequence.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.E3.strategy.sequence.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(final int i2, View view, ViewGroup viewGroup) {
        View viewInflate;
        final b bVar;
        if (view == null) {
            bVar = new b();
            viewInflate = this.G3.inflate(R.layout.grid_view_item, (ViewGroup) null);
            bVar.f3047b = (TextView) viewInflate.findViewById(R.id.item_Name);
            bVar.f3048c = (TextView) viewInflate.findViewById(R.id.item_ID);
            bVar.f3046a = (CheckBox) viewInflate.findViewById(R.id.item_cb);
            bVar.f3050e = (CheckBox) viewInflate.findViewById(R.id.cb_config);
            bVar.f3049d = (CheckBox) viewInflate.findViewById(R.id.cb_target);
            viewInflate.setTag(bVar);
        } else {
            viewInflate = view;
            bVar = (b) view.getTag();
        }
        if (TextUtils.isEmpty(y.get(this.E3.strategy.sequence.get(i2).ecuId))) {
            bVar.f3047b.setVisibility(8);
        } else {
            bVar.f3047b.setVisibility(0);
            bVar.f3047b.setText(y.get(this.E3.strategy.sequence.get(i2).ecuId));
        }
        bVar.f3048c.setText(this.E3.strategy.sequence.get(i2).ecuId);
        bVar.f3048c.setTextColor(-65536);
        bVar.f3046a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: c.a.d.d.b
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f2375c.h(bVar, i2, compoundButton, z);
            }
        });
        bVar.f3050e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: c.a.d.d.a
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f2373c.j(i2, bVar, compoundButton, z);
            }
        });
        bVar.f3049d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: c.a.d.d.c
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f2377c.l(i2, bVar, compoundButton, z);
            }
        });
        bVar.f3046a.setChecked(c().get(this.E3.strategy.sequence.get(i2).ecuId).booleanValue());
        return viewInflate;
    }

    public void m(a aVar) {
        this.H3 = aVar;
    }
}
