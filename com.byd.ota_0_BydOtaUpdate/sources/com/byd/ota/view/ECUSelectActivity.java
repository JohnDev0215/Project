package com.byd.ota.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import c.a.b.d.d;
import c.a.b.d.e;
import c.a.g.l.h;
import com.byd.ota.R;
import com.byd.ota.adapter.GridviewAdapter;
import com.byd.ota.master.VehicleVersionInfo;
import com.byd.repository.bean.EcuVersionInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ECUSelectActivity extends BaseActivity implements View.OnClickListener {
    public static String R3 = "ECUSelectActivity";
    public GridView S3;
    public GridviewAdapter T3;
    public VehicleVersionInfo U3;
    public Button V3;
    public Button W3;
    public Button X3;
    public Button Y3;
    public TextView Z3;
    public List<String> a4 = new ArrayList();
    public Handler b4;

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) throws Throwable {
            if (message.what != 1) {
                return;
            }
            ECUSelectActivity.this.L();
        }
    }

    public class b implements Comparator<VehicleVersionInfo.StrategyBean.SequenceBean> {
        public b() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(VehicleVersionInfo.StrategyBean.SequenceBean sequenceBean, VehicleVersionInfo.StrategyBean.SequenceBean sequenceBean2) {
            return sequenceBean.ecuId.compareTo(sequenceBean2.ecuId);
        }
    }

    public class c implements GridviewAdapter.a {
        public c() {
        }

        @Override // com.byd.ota.adapter.GridviewAdapter.a
        public void a() {
            ECUSelectActivity.this.Z3.setText("已选中" + GridviewAdapter.b() + "项");
        }
    }

    public static VehicleVersionInfo K() throws Throwable {
        String strQ = d.q(c.a.b.b.a.f2321b + "strategy.json_backup");
        if (TextUtils.isEmpty(strQ)) {
            return null;
        }
        return (VehicleVersionInfo) h.d(strQ, VehicleVersionInfo.class);
    }

    public static boolean N(VehicleVersionInfo vehicleVersionInfo) {
        if (vehicleVersionInfo == null) {
            return false;
        }
        try {
            return d.t(c.a.b.b.a.f2321b, "strategy.json", h.f(vehicleVersionInfo));
        } catch (Exception e2) {
            e.b(R3, "writeDataWithProcessBuilder error：" + e2.getMessage());
            return false;
        }
    }

    public final void H() {
        for (Map.Entry<String, Boolean> entry : GridviewAdapter.c().entrySet()) {
            if (entry.getValue().booleanValue()) {
                GridviewAdapter.c().replace(entry.getKey(), Boolean.FALSE);
            }
        }
        I();
    }

    public final void I() {
        this.T3.notifyDataSetChanged();
        this.Z3.setText("已选中" + GridviewAdapter.b() + "项");
    }

    public final void J() {
        for (Map.Entry<String, Boolean> entry : GridviewAdapter.c().entrySet()) {
            if (entry.getValue().booleanValue()) {
                GridviewAdapter.c().replace(entry.getKey(), Boolean.FALSE);
            } else {
                GridviewAdapter.c().replace(entry.getKey(), Boolean.TRUE);
            }
        }
        I();
    }

    public final void L() throws Throwable {
        StringBuilder sb = new StringBuilder();
        String str = c.a.b.b.a.f2321b;
        sb.append(str);
        sb.append("strategy.json");
        sb.append("_backup");
        File file = new File(sb.toString());
        if (!file.exists()) {
            e.c(R3, " initData !file.exists strategy.json_backup");
            File file2 = new File(str + "strategy.json");
            if (file2.exists()) {
                try {
                    c.a.k.a.a(file2, file);
                } catch (IOException e2) {
                    e.c(R3, " initData" + e2);
                }
            }
        }
        e.c(R3, " initData " + file.exists() + " strategy.json_backup");
        if (file.exists()) {
            VehicleVersionInfo vehicleVersionInfoK = K();
            this.U3 = vehicleVersionInfoK;
            if (vehicleVersionInfoK != null) {
                vehicleVersionInfoK.strategy.sequence.sort(new b());
                GridviewAdapter gridviewAdapter = new GridviewAdapter(this.U3, this);
                this.T3 = gridviewAdapter;
                this.S3.setAdapter((ListAdapter) gridviewAdapter);
                this.T3.m(new c());
            }
        }
        if (this.T3 != null) {
            this.Z3.setText("当前有" + this.T3.getCount() + "项");
        }
    }

    public final void M() {
        this.S3 = (GridView) findViewById(R.id.gd);
        this.V3 = (Button) findViewById(R.id.bt_selectall);
        this.W3 = (Button) findViewById(R.id.bt_cancelselectall);
        this.X3 = (Button) findViewById(R.id.bt_deselectall);
        this.Y3 = (Button) findViewById(R.id.bt_submit);
        this.Z3 = (TextView) findViewById(R.id.tv);
        this.V3.setOnClickListener(this);
        this.W3.setOnClickListener(this);
        this.X3.setOnClickListener(this);
        this.Y3.setOnClickListener(this);
    }

    public final void O() {
        Iterator<Map.Entry<String, Boolean>> it = GridviewAdapter.c().entrySet().iterator();
        while (it.hasNext()) {
            GridviewAdapter.c().replace(it.next().getKey(), Boolean.TRUE);
        }
        I();
    }

    public final void P() {
        this.a4.clear();
        for (Map.Entry<String, Boolean> entry : GridviewAdapter.c().entrySet()) {
            if (entry.getValue().booleanValue()) {
                this.a4.add(entry.getKey());
            }
        }
        e.c(R3, " submit:" + this.a4.size());
        if (this.a4.size() != 0) {
            VehicleVersionInfo vehicleVersionInfo = new VehicleVersionInfo();
            VehicleVersionInfo vehicleVersionInfo2 = this.U3;
            vehicleVersionInfo.cause = vehicleVersionInfo2.cause;
            vehicleVersionInfo.status = vehicleVersionInfo2.status;
            for (String str : this.a4) {
                Iterator<VehicleVersionInfo.StrategyBean.SequenceBean> it = this.U3.strategy.sequence.iterator();
                while (it.hasNext()) {
                    if (it.next().ecuId.equals(str)) {
                        Q(vehicleVersionInfo, this.U3, str);
                    }
                }
            }
            N(vehicleVersionInfo);
        }
        Toast.makeText(this, "提交成功", 1).show();
    }

    public void Q(VehicleVersionInfo vehicleVersionInfo, VehicleVersionInfo vehicleVersionInfo2, String str) {
        if (vehicleVersionInfo2.strategy != null) {
            if (vehicleVersionInfo.strategy == null) {
                VehicleVersionInfo.StrategyBean strategyBean = new VehicleVersionInfo.StrategyBean();
                vehicleVersionInfo.strategy = strategyBean;
                VehicleVersionInfo.StrategyBean strategyBean2 = vehicleVersionInfo2.strategy;
                strategyBean.acknowledgements = strategyBean2.acknowledgements;
                strategyBean.batchId = strategyBean2.batchId;
                strategyBean.strategyVer = strategyBean2.strategyVer;
                strategyBean.countDown = strategyBean2.countDown;
                strategyBean.date = strategyBean2.date;
                strategyBean.description = strategyBean2.description;
                strategyBean.failureNumber = strategyBean2.failureNumber;
                strategyBean.freeBurningTime = strategyBean2.freeBurningTime;
                strategyBean.functionAddition = strategyBean2.functionAddition;
                strategyBean.functionOptimization = strategyBean2.functionOptimization;
                strategyBean.isForce = strategyBean2.isForce;
                strategyBean.isFreeBurning = strategyBean2.isFreeBurning;
                strategyBean.isSaleCar = strategyBean2.isSaleCar;
                strategyBean.lowestSoc = strategyBean2.lowestSoc;
                strategyBean.lowestVoltage = strategyBean2.lowestVoltage;
                strategyBean.priorityType = strategyBean2.priorityType;
                strategyBean.temperature = strategyBean2.temperature;
            }
            if (vehicleVersionInfo2.strategy.sequence != null) {
                VehicleVersionInfo.StrategyBean strategyBean3 = vehicleVersionInfo.strategy;
                if (strategyBean3.sequence == null) {
                    strategyBean3.sequence = new ArrayList();
                }
                Iterator<VehicleVersionInfo.StrategyBean.SequenceBean> it = vehicleVersionInfo2.strategy.sequence.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    VehicleVersionInfo.StrategyBean.SequenceBean next = it.next();
                    if (next.ecuId.equals(str)) {
                        vehicleVersionInfo.strategy.sequence.add(next);
                        break;
                    }
                }
            }
            List<String> list = vehicleVersionInfo2.strategy.rollbackDependencies;
            if (list != null) {
                vehicleVersionInfo.strategy.rollbackDependencies = list;
            }
        }
        if (vehicleVersionInfo2.configSourceInfo != null) {
            if (vehicleVersionInfo.configSourceInfo == null) {
                vehicleVersionInfo.configSourceInfo = new ArrayList();
            }
            Iterator<EcuVersionInfo> it2 = vehicleVersionInfo2.configSourceInfo.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                EcuVersionInfo next2 = it2.next();
                if (next2.ecuId.equals(str)) {
                    if (!this.T3.f(str)) {
                        vehicleVersionInfo.configSourceInfo.add(next2);
                    } else if (GridviewAdapter.a().get(str) != null && GridviewAdapter.a().get(str).booleanValue()) {
                        vehicleVersionInfo.configSourceInfo.add(next2);
                    }
                }
            }
        }
        if (vehicleVersionInfo2.configPackageInfo != null) {
            if (vehicleVersionInfo.configPackageInfo == null) {
                vehicleVersionInfo.configPackageInfo = new ArrayList();
            }
            Iterator<EcuVersionInfo> it3 = vehicleVersionInfo2.configPackageInfo.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                EcuVersionInfo next3 = it3.next();
                if (next3.ecuId.equals(str)) {
                    if (!this.T3.f(str)) {
                        vehicleVersionInfo.configPackageInfo.add(next3);
                    } else if (GridviewAdapter.a().get(str) != null && GridviewAdapter.a().get(str).booleanValue()) {
                        vehicleVersionInfo.configPackageInfo.add(next3);
                    }
                }
            }
        }
        if (vehicleVersionInfo2.sourcePackageInfo != null) {
            if (vehicleVersionInfo.sourcePackageInfo == null) {
                vehicleVersionInfo.sourcePackageInfo = new ArrayList();
            }
            Iterator<EcuVersionInfo> it4 = vehicleVersionInfo2.sourcePackageInfo.iterator();
            while (true) {
                if (!it4.hasNext()) {
                    break;
                }
                EcuVersionInfo next4 = it4.next();
                if (next4.ecuId.equals(str)) {
                    if (!this.T3.f(str)) {
                        vehicleVersionInfo.sourcePackageInfo.add(next4);
                    } else if (GridviewAdapter.d().get(str) != null && GridviewAdapter.d().get(str).booleanValue()) {
                        vehicleVersionInfo.sourcePackageInfo.add(next4);
                    }
                }
            }
        }
        if (vehicleVersionInfo2.targetPackageInfo != null) {
            if (vehicleVersionInfo.targetPackageInfo == null) {
                vehicleVersionInfo.targetPackageInfo = new ArrayList();
            }
            for (EcuVersionInfo ecuVersionInfo : vehicleVersionInfo2.targetPackageInfo) {
                if (ecuVersionInfo.ecuId.equals(str)) {
                    if (!this.T3.f(str)) {
                        vehicleVersionInfo.targetPackageInfo.add(ecuVersionInfo);
                        return;
                    } else {
                        if (GridviewAdapter.d().get(str) == null || !GridviewAdapter.d().get(str).booleanValue()) {
                            return;
                        }
                        vehicleVersionInfo.targetPackageInfo.add(ecuVersionInfo);
                        return;
                    }
                }
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_cancelselectall /* 2131230813 */:
                H();
                break;
            case R.id.bt_deselectall /* 2131230814 */:
                J();
                break;
            case R.id.bt_selectall /* 2131230815 */:
                O();
                break;
            case R.id.bt_submit /* 2131230816 */:
                P();
                break;
        }
    }

    @Override // com.byd.ota.view.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ecuselect);
        w().l();
        M();
        a aVar = new a(getMainLooper());
        this.b4 = aVar;
        aVar.sendEmptyMessageDelayed(1, 1000L);
    }
}
