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

/* JADX INFO: compiled from: MissedCallsFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends Fragment {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private f f3404b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private RecyclerView f3405c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private LinearLayoutManager f3406d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.byd.bluetoothcall.view.b.e f3407e;
    private LinearLayout f;
    private View g;
    private List<CallLogInfo> h = new ArrayList();
    private Handler i = new b();

    /* JADX INFO: compiled from: MissedCallsFragment.java */
    class a implements View.OnTouchListener {
        a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (e.this.f3407e == null) {
                return false;
            }
            e.this.f3407e.C(-1);
            return false;
        }
    }

    /* JADX INFO: compiled from: MissedCallsFragment.java */
    class b extends Handler {
        b() {
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            int i = message.what;
            if (i == 10002) {
                if (e.this.h == null || e.this.h.size() <= 0 || e.this.f3406d == null || e.this.f3407e == null) {
                    return;
                }
                int iZ = e.this.f3407e.z();
                int iV1 = e.this.f3406d.V1();
                if (iZ == -1) {
                    e.this.f3407e.C(iV1);
                    return;
                }
                if (iZ <= 0) {
                    e.this.f3407e.C(e.this.h.size() - 1);
                    e.this.f3406d.E2(e.this.h.size() - 1, 200);
                    return;
                }
                int i2 = iZ - 1;
                e.this.f3407e.C(i2);
                if (iV1 - 1 >= 0) {
                    e.this.f3406d.E2(i2, 200);
                    return;
                }
                return;
            }
            if (i != 10003 || e.this.h == null || e.this.h.size() <= 0 || e.this.f3406d == null || e.this.f3407e == null) {
                return;
            }
            int iZ2 = e.this.f3407e.z();
            int iV12 = e.this.f3406d.V1();
            Log.d("DANDAN", "select_postion == " + iZ2 + " == " + iV12);
            if (iZ2 == -1) {
                e.this.f3407e.C(iV12);
                return;
            }
            int i3 = iZ2 + 1;
            if (i3 >= e.this.h.size()) {
                e.this.f3407e.C(0);
                e.this.f3406d.E2(0, 200);
            } else {
                e.this.f3407e.C(i3);
                if (e.this.f3406d.b2() + 1 < e.this.h.size()) {
                    e.this.f3406d.E2(i3, 200);
                }
            }
        }
    }

    private void k(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.missed_calls_list);
        this.f3405c = recyclerView;
        recyclerView.setOnTouchListener(new a());
        this.f = (LinearLayout) view.findViewById(R.id.nodata_text);
    }

    private void l() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.f3406d = linearLayoutManager;
        linearLayoutManager.F2(1);
        this.f3405c.setLayoutManager(this.f3406d);
        com.byd.bluetoothcall.view.b.e eVar = new com.byd.bluetoothcall.view.b.e(getActivity(), this.h, getActivity().isInMultiWindowMode());
        this.f3407e = eVar;
        this.f3405c.setAdapter(eVar);
    }

    public static e m(int i) {
        e eVar = new e();
        Bundle bundle = new Bundle();
        bundle.putInt("section_number", i);
        eVar.setArguments(bundle);
        return eVar;
    }

    public void a() {
        c(com.byd.bluetoothcall.c.a.F().H());
    }

    public void b(CallLogInfo callLogInfo) {
        if (callLogInfo == null || !callLogInfo.r().equals("MISSED")) {
            return;
        }
        c(com.byd.bluetoothcall.c.a.F().H());
    }

    public void c(List<CallLogInfo> list) {
        this.h.clear();
        if (list != null) {
            BydLogHelper.logD("DANDAN", "miss -- SyncCallLog_data = " + list.size());
            this.h.addAll(list);
        }
        BydLogHelper.logD("DANDAN", "miss_calllog = " + this.h.size());
        com.byd.bluetoothcall.view.b.e eVar = this.f3407e;
        if (eVar != null) {
            eVar.D(this.h);
        }
        if (this.h.size() > 0) {
            LinearLayout linearLayout = this.f;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
                return;
            }
            return;
        }
        LinearLayout linearLayout2 = this.f;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(0);
        }
    }

    public void d(boolean z) {
        if (z) {
            c(com.byd.bluetoothcall.c.a.F().H());
            return;
        }
        this.h.clear();
        com.byd.bluetoothcall.view.b.e eVar = this.f3407e;
        if (eVar != null) {
            eVar.D(this.h);
        }
    }

    public void h() {
        com.byd.bluetoothcall.view.b.e eVar = this.f3407e;
        if (eVar == null || eVar.z() == -1) {
            return;
        }
        this.f3407e.C(-1);
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
        this.f3404b = (f) new r(this, r.a.b(getActivity().getApplication())).a(f.class);
        this.f3404b.d(getArguments() != null ? getArguments().getInt("section_number") : 1);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = this.g;
        if (view != null) {
            return view;
        }
        if (getActivity().isInMultiWindowMode()) {
            this.g = layoutInflater.inflate(R.layout.multiwindowmode_missed_calls_fragment, viewGroup, false);
        } else {
            this.g = layoutInflater.inflate(R.layout.missed_calls_fragment, viewGroup, false);
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
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        d(c.a.b.b.f0().n0() == 2);
    }
}
