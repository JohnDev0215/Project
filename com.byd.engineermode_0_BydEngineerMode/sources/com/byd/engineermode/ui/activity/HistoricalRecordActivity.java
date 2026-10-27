package com.byd.engineermode.ui.activity;

import android.content.Context;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.R;
import com.byd.engineermode.DiagnoseApplication;
import com.byd.engineermode.a.a.e;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class HistoricalRecordActivity extends BaseActivity {
    private ExecutorService A = Executors.newSingleThreadExecutor();
    private RecyclerView v;
    private e w;
    BYDAutoVersionDevice x;
    BYDAutoOtaDevice y;
    private Handler z;

    class a extends LinearLayoutManager {
        a(Context context, int i, boolean z) {
            super(context, i, z);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
        public boolean l() {
            return true;
        }
    }

    class b implements Runnable {

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                HistoricalRecordActivity.this.w.h();
            }
        }

        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HistoricalRecordActivity.this.w.y(HistoricalRecordActivity.this.R());
            HistoricalRecordActivity.this.z.post(new a());
        }
    }

    private static class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<HistoricalRecordActivity> f2338a;

        public c(HistoricalRecordActivity historicalRecordActivity) {
            this.f2338a = new WeakReference<>(historicalRecordActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            com.byd.engineermode.utils.a.a("handleMessage");
            HistoricalRecordActivity historicalRecordActivity = this.f2338a.get();
            if (historicalRecordActivity != null) {
                historicalRecordActivity.w.h();
            }
        }
    }

    private void P(ArrayList<com.byd.engineermode.bean.c> arrayList, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            com.byd.engineermode.utils.a.c("addList  key isEmpty");
            return;
        }
        if (TextUtils.isEmpty(str2) || str2.contains("null")) {
            com.byd.engineermode.utils.a.c("addList  msg isEmpty or null, key = " + str);
            com.byd.engineermode.bean.c cVar = new com.byd.engineermode.bean.c(str + " : null");
            cVar.d(false);
            arrayList.add(cVar);
            return;
        }
        if (str2.contains("#@$")) {
            com.byd.engineermode.utils.a.a("addList length = " + str2.length() + ", msg = " + str2);
            for (int i = 0; i < str2.length(); i++) {
                if (i == 0) {
                    String strSubstring = str2.substring(i, i + 17);
                    com.byd.engineermode.utils.a.a("addList substring = " + strSubstring);
                    com.byd.engineermode.bean.c cVar2 = new com.byd.engineermode.bean.c(str + " : " + strSubstring);
                    cVar2.d(false);
                    arrayList.add(cVar2);
                }
                if (i != 0 && i % 20 == 0) {
                    String strSubstring2 = str2.substring(i, i + 17);
                    com.byd.engineermode.utils.a.a("addList substring = " + strSubstring2);
                    com.byd.engineermode.bean.c cVar3 = new com.byd.engineermode.bean.c(S(str) + "   " + strSubstring2);
                    cVar3.d(false);
                    arrayList.add(cVar3);
                }
            }
        }
    }

    private List<com.byd.engineermode.bean.c> Q() {
        ArrayList arrayList = new ArrayList();
        ArrayList<String> arrayListA = com.byd.engineermode.utils.c.a();
        for (int i = 0; i < arrayListA.size(); i++) {
            com.byd.engineermode.bean.c cVar = new com.byd.engineermode.bean.c(arrayListA.get(i) + " : ");
            cVar.d(false);
            arrayList.add(cVar);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<com.byd.engineermode.bean.c> R() {
        ArrayList<com.byd.engineermode.bean.c> arrayList = new ArrayList<>();
        ArrayList<String> arrayListA = com.byd.engineermode.utils.c.a();
        for (int i = 0; i < arrayListA.size(); i++) {
            P(arrayList, arrayListA.get(i), com.byd.engineermode.utils.b.a().b(arrayListA.get(i)));
        }
        return arrayList;
    }

    private String S(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_version);
        this.v = (RecyclerView) findViewById(R.id.recyclerView);
        a aVar = new a(this, 1, false);
        this.x = BYDAutoVersionDevice.getInstance(DiagnoseApplication.e());
        this.y = BYDAutoOtaDevice.getInstance(DiagnoseApplication.e());
        this.v.setLayoutManager(aVar);
        e eVar = new e(Q());
        this.w = eVar;
        this.v.setAdapter(eVar);
        this.z = new c(this);
        com.byd.engineermode.ui.dialog.a.a(this).b(R.string.version_loading);
        this.A.submit(new b());
    }
}
