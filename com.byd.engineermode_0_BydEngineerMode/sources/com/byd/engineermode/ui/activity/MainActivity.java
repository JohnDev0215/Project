package com.byd.engineermode.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.R;
import com.byd.engineermode.ui.functiontest.FunctionListActivity;
import com.byd.engineermode.utils.i;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends BaseActivity {
    private RecyclerView v;
    private List<com.byd.engineermode.bean.c> w;
    private com.byd.engineermode.a.a.d x;

    class a extends LinearLayoutManager {
        a(Context context, int i, boolean z) {
            super(context, i, z);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
        public boolean l() {
            return false;
        }
    }

    class b implements com.byd.engineermode.a.b.a<com.byd.engineermode.bean.c> {
        b() {
        }

        @Override // com.byd.engineermode.a.b.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(com.byd.engineermode.bean.c cVar, int i, int i2) {
        }

        @Override // com.byd.engineermode.a.b.a
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(com.byd.engineermode.bean.c cVar, int i, int i2) {
            i.b("MainActivity", "test name = " + cVar.a());
            if (i == 0) {
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(new Intent(mainActivity, (Class<?>) ElectronActivity.class));
                return;
            }
            if (i == 1) {
                MainActivity mainActivity2 = MainActivity.this;
                mainActivity2.startActivity(new Intent(mainActivity2, (Class<?>) FunctionListActivity.class));
                return;
            }
            if (i == 2) {
                MainActivity mainActivity3 = MainActivity.this;
                mainActivity3.startActivity(new Intent(mainActivity3, (Class<?>) VersionActivity.class));
            } else if (i == 3) {
                MainActivity mainActivity4 = MainActivity.this;
                mainActivity4.startActivity(new Intent(mainActivity4, (Class<?>) PasswordActivity.class));
            } else {
                if (i != 4) {
                    return;
                }
                MainActivity.this.finish();
            }
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList<String> arrayListA = com.byd.engineermode.utils.c.a();
            for (int i = 0; i < arrayListA.size(); i++) {
                String str = arrayListA.get(i);
                String strB = com.byd.engineermode.utils.b.a().b(str);
                com.byd.engineermode.utils.a.b("MainActivity", "mKey = " + str + ", mSPMessage = " + strB);
                if (!TextUtils.isEmpty(strB) && strB.length() > 400) {
                    com.byd.engineermode.utils.b.a().d(str);
                }
            }
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        this.v = (RecyclerView) findViewById(R.id.recyclerView);
        this.v.setLayoutManager(new a(this, 1, false));
        this.w = new ArrayList();
        for (String str : getResources().getStringArray(R.array.main_name)) {
            this.w.add(new com.byd.engineermode.bean.c(str));
        }
        com.byd.engineermode.a.a.d dVar = new com.byd.engineermode.a.a.d(this.w);
        this.x = dVar;
        this.v.setAdapter(dVar);
        this.x.y(new b());
        new Thread(new c()).start();
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}
