package com.byd.logtools.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.byd.logtools.R;
import com.byd.logtools.logservice.BYDLogService;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FaultUploadActivity extends BydBaseActivity implements View.OnClickListener, BYDLogService.r, BYDLogService.q, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static BYDLogService f2986b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static d f2987c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f2988d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private RadioGroup f2989e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TextView f2990f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TextView f2991g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Button f2992h;

    @SuppressLint({"StaticFieldLeak"})
    private com.byd.logtools.tools.d i;
    private Dialog j;
    private Dialog k;
    private Dialog l;
    LinearLayout n;
    private ListView o;
    private com.byd.logtools.tools.b p;
    ArrayList<String> q;
    List<File> r;
    ArrayList<String> s;
    private boolean m = true;
    private final ServiceConnection t = new a();

    class a implements ServiceConnection {

        /* JADX INFO: renamed from: com.byd.logtools.activity.FaultUploadActivity$a$a, reason: collision with other inner class name */
        class RunnableC0055a implements Runnable {
            RunnableC0055a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BydLogHelper.logD("onServiceConnected mDataFileLists = " + FaultUploadActivity.this.r.size() + ", mCheckedLists = " + FaultUploadActivity.this.q.size());
                com.byd.logtools.tools.b bVar = FaultUploadActivity.this.p;
                FaultUploadActivity faultUploadActivity = FaultUploadActivity.this;
                bVar.a(faultUploadActivity.r, faultUploadActivity.q);
            }
        }

        a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BydLogHelper.logE("onServiceConnected classname = " + componentName);
            BYDLogService unused = FaultUploadActivity.f2986b = ((BYDLogService.p) iBinder).a();
            FaultUploadActivity.this.r.clear();
            FaultUploadActivity.this.r.addAll(FaultUploadActivity.f2986b.t);
            BydLogHelper.logD("onServiceConnected mLocalBYDLogService.historyLogList = " + FaultUploadActivity.f2986b.t.size());
            FaultUploadActivity.f2986b.C0(FaultUploadActivity.this);
            FaultUploadActivity.f2986b.B0(FaultUploadActivity.this);
            FaultUploadActivity.this.runOnUiThread(new RunnableC0055a());
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            BydLogHelper.logE("onServiceDisconnected classname = " + componentName);
            FaultUploadActivity.f2986b.J0(FaultUploadActivity.this);
            FaultUploadActivity.f2986b.I0(FaultUploadActivity.this);
            BYDLogService unused = FaultUploadActivity.f2986b = null;
        }
    }

    class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f2995b;

        b(List list) {
            this.f2995b = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            FaultUploadActivity.this.r.clear();
            FaultUploadActivity.this.r.addAll(this.f2995b);
            BydLogHelper.logD("onListViewDataSetChanged = " + this.f2995b.size() + ", mDataFileLists = " + FaultUploadActivity.this.r.size());
            com.byd.logtools.tools.b bVar = FaultUploadActivity.this.p;
            FaultUploadActivity faultUploadActivity = FaultUploadActivity.this;
            bVar.a(faultUploadActivity.r, faultUploadActivity.q);
        }
    }

    class c implements RadioGroup.OnCheckedChangeListener {
        c() {
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        @SuppressLint({"NonConstantResourceId"})
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.rb_current_log /* 2131296574 */:
                    FaultUploadActivity.this.y(true);
                    break;
                case R.id.rb_history_log /* 2131296575 */:
                    FaultUploadActivity.this.y(false);
                    break;
            }
        }
    }

    @SuppressLint({"HandlerLeak"})
    private class d extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<FaultUploadActivity> f2998a;

        d(FaultUploadActivity faultUploadActivity) {
            this.f2998a = new WeakReference<>(faultUploadActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f2998a.get() == null) {
                BydLogHelper.logV("outer is null");
                return;
            }
            switch (message.what) {
                case 0:
                    BydLogHelper.logD(" FAULT_UPLOAD_CONN_NET_ERROR");
                    break;
                case 1:
                    break;
                case 2:
                    BydLogHelper.logD(" FAULT_UPLOAD_UPLOAD_LOG_RUNNING");
                    FaultUploadActivity faultUploadActivity = FaultUploadActivity.this;
                    faultUploadActivity.j = faultUploadActivity.i.b(R.string.logtools_wait_upload_message_string);
                    FaultUploadActivity.f2987c.sendEmptyMessageDelayed(7, 1L);
                    return;
                case 3:
                    BydLogHelper.logD(" FAULT_UPLOAD_UPLOAD_LOG_FINISH");
                    FaultUploadActivity faultUploadActivity2 = FaultUploadActivity.this;
                    faultUploadActivity2.l = faultUploadActivity2.i.d();
                    FaultUploadActivity.f2987c.sendEmptyMessageDelayed(6, 1L);
                    return;
                case 4:
                    if (FaultUploadActivity.f2986b == null) {
                        FaultUploadActivity.f2987c.sendEmptyMessageDelayed(4, 1000L);
                        return;
                    } else {
                        BydLogHelper.logD(" EVENT_BEGIN_UPLOAD_CURRENT_LOG uploadAllLogsOneKey");
                        FaultUploadActivity.f2986b.O0();
                        return;
                    }
                case 5:
                case 6:
                    if (FaultUploadActivity.this.j != null) {
                        FaultUploadActivity.this.j.dismiss();
                        return;
                    }
                    return;
                case 7:
                    if (FaultUploadActivity.this.l != null) {
                        FaultUploadActivity.this.l.dismiss();
                    }
                    if (FaultUploadActivity.this.k != null) {
                        FaultUploadActivity.this.k.dismiss();
                        return;
                    }
                    return;
                case 8:
                    if (FaultUploadActivity.f2986b == null) {
                        FaultUploadActivity.f2987c.sendEmptyMessageDelayed(8, 1000L);
                        return;
                    }
                    BydLogHelper.logD(" EVENT_BEGIN_UPLOAD_HISTORY_LOG uploadAllLogsOneKey");
                    ArrayList<String> arrayList = FaultUploadActivity.this.q;
                    if (arrayList == null || arrayList.size() == 0) {
                        return;
                    }
                    ArrayList<String> arrayList2 = FaultUploadActivity.this.s;
                    if (arrayList2 != null) {
                        arrayList2.clear();
                        FaultUploadActivity.this.s = null;
                    }
                    FaultUploadActivity.this.s = new ArrayList<>(FaultUploadActivity.this.q);
                    FaultUploadActivity.f2986b.R0(FaultUploadActivity.this.s);
                    return;
                default:
                    return;
            }
            BydLogHelper.logD(" FAULT_UPLOAD_UPLOAD_LOG_ERROR");
            FaultUploadActivity faultUploadActivity3 = FaultUploadActivity.this;
            faultUploadActivity3.l = faultUploadActivity3.i.c(FaultUploadActivity.this);
            FaultUploadActivity.f2987c.sendEmptyMessageDelayed(5, 1L);
        }
    }

    private void v() {
        if (this.q.size() == 0) {
            com.byd.logtools.tools.a.a().b(R.string.logtools_select_file);
            return;
        }
        Message message = new Message();
        message.what = 8;
        f2987c.sendMessage(message);
    }

    private void x() {
        this.q = new ArrayList<>();
        this.r = new ArrayList();
        bindService(new Intent(this, (Class<?>) BYDLogService.class), this.t, 1);
        com.byd.logtools.tools.b bVar = new com.byd.logtools.tools.b(this.f2988d, this.r);
        this.p = bVar;
        this.o.setAdapter((ListAdapter) bVar);
        this.o.setOnItemClickListener(this);
        this.i = new com.byd.logtools.tools.d(this.f2988d);
        this.f2989e.check(R.id.rb_current_log);
        this.m = true;
        f2987c = new d(this);
        this.f2992h.setOnClickListener(this);
        this.f2989e.setOnCheckedChangeListener(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(boolean z) {
        if (z) {
            this.f2990f.setVisibility(8);
            this.f2991g.setVisibility(8);
            this.o.setVisibility(8);
            this.n.setVisibility(0);
            this.m = true;
            return;
        }
        this.f2990f.setVisibility(0);
        this.o.setVisibility(0);
        this.f2991g.setVisibility(0);
        this.n.setVisibility(8);
        this.f2990f.setText(R.string.logtools_history_log_select_title);
        this.f2991g.setText(R.string.logtools_history_log_select_message);
        BydLogHelper.logD("showCurrentOrHistoryView mDataFileLists = " + this.r.size() + ", mCheckedLists = " + this.q.size());
        this.p.a(this.r, this.q);
        this.m = false;
    }

    @Override // com.byd.logtools.logservice.BYDLogService.q
    public void a(boolean z, String str) {
        BydLogHelper.logD("onUploadResultChanged result = " + z + ", info tag = " + str);
        if ("msg_network_error".equals(str)) {
            BydLogHelper.logD("upload fail: msg_network_error");
            f2987c.sendEmptyMessage(0);
            return;
        }
        if (!"msg_upload_result".equals(str) || !z) {
            if ("msg_upload_result".equals(str)) {
                BydLogHelper.logD("upload fail: sendMultipart onFailure");
                f2987c.sendEmptyMessage(1);
                return;
            }
            return;
        }
        BydLogHelper.logD("sendMultipart onResponse Success");
        BYDLogService bYDLogService = f2986b;
        bYDLogService.S0(bYDLogService.U);
        f2987c.sendEmptyMessage(3);
        this.q.clear();
    }

    @Override // com.byd.logtools.logservice.BYDLogService.q
    public void d(List<File> list) {
        runOnUiThread(new b(list));
    }

    @Override // com.byd.logtools.logservice.BYDLogService.r
    public void e(String str) {
        if ("action.conn.network.error".equals(str)) {
            f2987c.sendEmptyMessage(0);
            return;
        }
        if ("action.upload.all.log.running".equals(str)) {
            f2987c.sendEmptyMessage(2);
        } else if ("action.upload.all.log.error".equals(str)) {
            f2987c.sendEmptyMessage(1);
        } else if ("action.upload.all.log.finish".equals(str)) {
            f2987c.sendEmptyMessage(3);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view != null && view.getId() == R.id.fault_upload_log_button) {
            if (this.m) {
                u();
            } else {
                v();
            }
        }
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().clearFlags(1024);
        setContentView(R.layout.activity_fault_upload_log);
        this.f2989e = (RadioGroup) findViewById(R.id.rg_fault_upload_log);
        this.f2990f = (TextView) findViewById(R.id.fault_upload_log_title);
        this.f2991g = (TextView) findViewById(R.id.fault_upload_log_message);
        this.n = (LinearLayout) findViewById(R.id.ll_current_log);
        this.o = (ListView) findViewById(R.id.history_log_list_view);
        this.f2992h = (Button) findViewById(R.id.fault_upload_log_button);
        this.f2988d = this;
        x();
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        BydLogHelper.logV(" onDestroy() ");
        if (!i("FaultUploadActivity")) {
            Dialog dialog = this.j;
            if (dialog != null) {
                dialog.dismiss();
            }
            Dialog dialog2 = this.l;
            if (dialog2 != null) {
                dialog2.dismiss();
            }
            Dialog dialog3 = this.k;
            if (dialog3 != null) {
                dialog3.dismiss();
            }
            this.j = null;
            this.l = null;
            this.k = null;
            unbindService(this.t);
        }
        BYDLogService bYDLogService = f2986b;
        if (bYDLogService != null) {
            bYDLogService.J0(this);
            f2986b.I0(this);
            f2986b = null;
        }
        d dVar = f2987c;
        if (dVar != null) {
            dVar.removeMessages(0);
            f2987c.removeMessages(1);
            f2987c.removeMessages(2);
            f2987c.removeMessages(3);
            f2987c.removeMessages(4);
            f2987c.removeMessages(5);
            f2987c.removeMessages(6);
            f2987c.removeMessages(7);
            f2987c.removeMessages(8);
            f2987c = null;
        }
        ArrayList<String> arrayList = this.q;
        if (arrayList != null) {
            arrayList.clear();
            this.q = null;
        }
        ArrayList<String> arrayList2 = this.s;
        if (arrayList2 != null) {
            arrayList2.clear();
            this.s = null;
        }
        List<File> list = this.r;
        if (list != null) {
            list.clear();
            this.s = null;
        }
        super.onDestroy();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox1);
        if (checkBox.isChecked()) {
            BydLogHelper.logD("onUploadResultChanged remove mCheckedLists.size() = " + this.q.size());
            checkBox.setChecked(false);
            this.q.remove(checkBox.getText().toString());
            return;
        }
        BydLogHelper.logD("onUploadResultChanged add mCheckedLists.size() = " + this.q.size());
        if (this.q.size() >= 3) {
            com.byd.logtools.tools.a.a().c("一次最多可选项三个文件");
        } else {
            checkBox.setChecked(true);
            this.q.add(checkBox.getText().toString());
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        BydLogHelper.logV(" onPause() ");
        super.onPause();
        if (i("FaultUploadActivity")) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (i("FaultUploadActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        BydLogHelper.logV(" onStop() ");
        super.onStop();
        if (i("FaultUploadActivity")) {
            finish();
        }
    }

    public void u() {
        Message message = new Message();
        message.what = 4;
        f2987c.sendMessage(message);
    }

    public void w() {
        if (this.m) {
            u();
        } else {
            v();
        }
    }
}
