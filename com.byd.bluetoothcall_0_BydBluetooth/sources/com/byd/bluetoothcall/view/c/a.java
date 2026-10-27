package com.byd.bluetoothcall.view.c;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.r;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.info.CallLogInfo;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CallLogFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Fragment {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private f f3373b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private RecyclerView f3374c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private LinearLayoutManager f3375d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.byd.bluetoothcall.view.b.a f3376e;
    private LinearLayout f;
    private View g;
    private List<CallLogInfo> h = new ArrayList();
    private Handler i = new b();

    /* JADX INFO: renamed from: com.byd.bluetoothcall.view.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CallLogFragment.java */
    class ViewOnTouchListenerC0084a implements View.OnTouchListener {
        ViewOnTouchListenerC0084a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (a.this.f3376e == null) {
                return false;
            }
            a.this.f3376e.C(-1);
            return false;
        }
    }

    /* JADX INFO: compiled from: CallLogFragment.java */
    class b extends Handler {
        b() {
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            int i = message.what;
            if (i == 10002) {
                if (a.this.h == null || a.this.h.size() <= 0 || a.this.f3375d == null || a.this.f3376e == null) {
                    return;
                }
                int iZ = a.this.f3376e.z();
                int iV1 = a.this.f3375d.V1();
                if (iZ == -1) {
                    a.this.f3376e.C(iV1);
                    return;
                }
                if (iZ <= 0) {
                    a.this.f3376e.C(a.this.h.size() - 1);
                    a.this.f3375d.E2(a.this.h.size() - 1, 200);
                    return;
                }
                int i2 = iZ - 1;
                a.this.f3376e.C(i2);
                if (iV1 - 1 >= 0) {
                    a.this.f3375d.E2(i2, 200);
                    return;
                }
                return;
            }
            if (i != 10003 || a.this.h == null || a.this.h.size() <= 0 || a.this.f3375d == null || a.this.f3376e == null) {
                return;
            }
            int iZ2 = a.this.f3376e.z();
            int iV12 = a.this.f3375d.V1();
            Log.d("DANDAN", "select_postion == " + iZ2 + " == " + iV12);
            if (iZ2 == -1) {
                a.this.f3376e.C(iV12);
                return;
            }
            int i3 = iZ2 + 1;
            if (i3 >= a.this.h.size()) {
                a.this.f3376e.C(0);
                a.this.f3375d.E2(0, 200);
            } else {
                a.this.f3376e.C(i3);
                if (a.this.f3375d.b2() + 1 < a.this.h.size()) {
                    a.this.f3375d.E2(i3, 200);
                }
            }
        }
    }

    private void k(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.call_log_list);
        this.f3374c = recyclerView;
        recyclerView.setOnTouchListener(new ViewOnTouchListenerC0084a());
        this.f = (LinearLayout) view.findViewById(R.id.nodata_text);
    }

    private void l() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.f3375d = linearLayoutManager;
        linearLayoutManager.F2(1);
        this.f3374c.setLayoutManager(this.f3375d);
        com.byd.bluetoothcall.view.b.a aVar = new com.byd.bluetoothcall.view.b.a(getActivity(), this.h, getActivity().isInMultiWindowMode());
        this.f3376e = aVar;
        this.f3374c.setAdapter(aVar);
    }

    public static a m(int i) {
        a aVar = new a();
        Bundle bundle = new Bundle();
        bundle.putInt("section_number", i);
        aVar.setArguments(bundle);
        return aVar;
    }

    public void a() {
        c(com.byd.bluetoothcall.c.a.F().z());
    }

    public void b(CallLogInfo callLogInfo) {
    }

    public void c(List<CallLogInfo> list) {
        this.h.clear();
        if (list != null) {
            BydLogHelper.logD("DANDAN", "SyncCallLog_data = " + list.size());
            this.h.addAll(list);
        }
        BydLogHelper.logD("DANDAN", "callLogInfos = " + this.h.size());
        com.byd.bluetoothcall.view.b.a aVar = this.f3376e;
        if (aVar != null) {
            aVar.D(this.h);
        }
        if (this.f == null) {
            return;
        }
        if (this.h.size() > 0) {
            this.f.setVisibility(8);
        } else {
            this.f.setVisibility(0);
        }
    }

    public void d(boolean z) {
        if (z) {
            c(com.byd.bluetoothcall.c.a.F().z());
            return;
        }
        this.h.clear();
        com.byd.bluetoothcall.view.b.a aVar = this.f3376e;
        if (aVar != null) {
            aVar.D(this.h);
        }
    }

    public void h() {
        com.byd.bluetoothcall.view.b.a aVar = this.f3376e;
        if (aVar == null || aVar.z() == -1) {
            return;
        }
        this.f3376e.C(-1);
    }

    public void i() {
        this.i.sendEmptyMessageDelayed(10002, 100L);
    }

    public void j() {
        this.i.sendEmptyMessageDelayed(10003, 100L);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3373b = (f) new r(this, r.a.b(getActivity().getApplication())).a(f.class);
        this.f3373b.d(getArguments() != null ? getArguments().getInt("section_number") : 1);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = this.g;
        if (view != null) {
            return view;
        }
        if (getActivity().isInMultiWindowMode()) {
            this.g = layoutInflater.inflate(R.layout.multiwindowmode_calllog_fragment, viewGroup, false);
        } else {
            this.g = layoutInflater.inflate(R.layout.calllog_fragment, viewGroup, false);
        }
        k(this.g);
        l();
        return this.g;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        d(c.a.b.b.f0().n0() == 2);
    }
}
