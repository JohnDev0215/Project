package com.byd.bluetoothcall.view.c;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.r;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.bluetoothcall.view.BlueToothApplication;
import com.byd.bluetoothcall.view.azlist.AZSideBarView;
import com.byd.info.ContactsInfo;
import com.byd.type_enum.Sync$SyncState;
import com.byd.type_enum.Sync$SyncType;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ContactsFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends Fragment implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected f f3379b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected RecyclerView f3380c;
    protected View f;
    protected AZSideBarView g;
    protected ImageView h;
    protected Animation i;
    protected TextView j;
    protected RelativeLayout l;
    protected RelativeLayout m;
    protected TextView n;
    protected ProgressBar o;
    protected LinearLayout p;
    protected LinearLayout q;
    protected RelativeLayout r;
    private TextView s;
    private i v;
    private LinearLayoutManager w;
    private RecyclerView.n x;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected List<com.byd.bluetoothcall.b.b> f3381d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected List<ContactsInfo> f3382e = new ArrayList();
    protected boolean k = false;
    private boolean t = false;
    private boolean u = false;
    private Handler y = new d(Looper.myLooper());

    /* JADX INFO: compiled from: ContactsFragment.java */
    class a implements View.OnTouchListener {
        a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (b.this.v == null) {
                return false;
            }
            b.this.v.C(-1);
            return false;
        }
    }

    /* JADX INFO: renamed from: com.byd.bluetoothcall.view.c.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ContactsFragment.java */
    class C0085b extends RecyclerView.t {
        C0085b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            super.a(recyclerView, i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i, int i2) {
            super.b(recyclerView, i, i2);
            int iV1 = b.this.w != null ? b.this.w.V1() : 0;
            b bVar = b.this;
            bVar.g.setLight(bVar.v.y(iV1));
        }
    }

    /* JADX INFO: compiled from: ContactsFragment.java */
    class c implements AZSideBarView.c {
        c() {
        }

        @Override // com.byd.bluetoothcall.view.azlist.AZSideBarView.c
        public void a(String str) {
            int iX = b.this.v.x(str.charAt(0));
            if (iX != -1) {
                b.this.w.E2(iX, 0);
                b.this.g.setCurListLetter(str);
            }
        }

        @Override // com.byd.bluetoothcall.view.azlist.AZSideBarView.c
        public void b() {
            int iV1 = b.this.w != null ? b.this.w.V1() : 0;
            b bVar = b.this;
            bVar.g.setLight(bVar.v.y(iV1));
        }
    }

    /* JADX INFO: compiled from: ContactsFragment.java */
    class d extends Handler {
        d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            switch (message.what) {
                case 10001:
                    b.this.k();
                    break;
                case 10002:
                    List<com.byd.bluetoothcall.b.b> list = b.this.f3381d;
                    if (list != null && list.size() > 0 && b.this.w != null && b.this.v != null) {
                        int iZ = b.this.v.z();
                        int iV1 = b.this.w.V1();
                        if (iZ == -1) {
                            b.this.v.C(iV1);
                        } else if (iZ <= 0) {
                            b.this.v.C(b.this.f3381d.size() - 1);
                            b.this.w.E2(b.this.f3381d.size() - 1, 200);
                        } else {
                            int i = iZ - 1;
                            b.this.v.C(i);
                            if (iV1 - 1 >= 0) {
                                b.this.w.E2(i, 200);
                            }
                        }
                        break;
                    }
                    break;
                case 10003:
                    List<com.byd.bluetoothcall.b.b> list2 = b.this.f3381d;
                    if (list2 != null && list2.size() > 0 && b.this.w != null && b.this.v != null) {
                        int iZ2 = b.this.v.z();
                        int iV12 = b.this.w.V1();
                        Log.d("DANDAN", "select_postion == " + iZ2 + " == " + iV12);
                        if (iZ2 != -1) {
                            int i2 = iZ2 + 1;
                            if (i2 >= b.this.f3381d.size()) {
                                b.this.v.C(0);
                                b.this.w.E2(0, 200);
                            } else {
                                b.this.v.C(i2);
                                if (b.this.w.b2() + 1 < b.this.f3381d.size()) {
                                    b.this.w.E2(i2, 200);
                                }
                            }
                        } else {
                            b.this.v.C(iV12);
                        }
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: ContactsFragment.java */
    static /* synthetic */ class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3387a;

        static {
            int[] iArr = new int[Sync$SyncState.values().length];
            f3387a = iArr;
            try {
                iArr[Sync$SyncState.SYNC_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3387a[Sync$SyncState.SYNC_SUCCEED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3387a[Sync$SyncState.SYNC_FAILD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3387a[Sync$SyncState.SYNC_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void A(int i, int i2) {
        if (i == 0 || this.o == null || !isAdded()) {
            return;
        }
        if (i2 > 0 && this.j != null) {
            this.j.setText(String.format(getResources().getString(R.string.bt_contacts_number), Integer.valueOf(i2)));
        }
        ProgressBar progressBar = this.o;
        if (progressBar != null) {
            progressBar.setProgress((i2 * 100) / i);
        }
        TextView textView = this.n;
        if (textView != null) {
            textView.setText(((i2 * 100) / i) + "%");
        }
        RelativeLayout relativeLayout = this.m;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
    }

    private void b() {
        if (this.f3381d.size() > 0) {
            LinearLayout linearLayout = this.p;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
            LinearLayout linearLayout2 = this.q;
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(8);
            }
            RelativeLayout relativeLayout = this.r;
            if (relativeLayout != null) {
                relativeLayout.setVisibility(0);
                return;
            }
            return;
        }
        LinearLayout linearLayout3 = this.p;
        if (linearLayout3 != null) {
            linearLayout3.setVisibility(0);
        }
        LinearLayout linearLayout4 = this.q;
        if (linearLayout4 != null) {
            linearLayout4.setVisibility(8);
        }
        RelativeLayout relativeLayout2 = this.r;
        if (relativeLayout2 != null) {
            relativeLayout2.setVisibility(8);
        }
    }

    private void c() {
        w();
        c.a.b.c.t().g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.y.removeMessages(10001);
        ImageView imageView = this.h;
        if (imageView != null) {
            imageView.clearAnimation();
        }
    }

    private void l() {
        this.f3381d.clear();
        i iVar = this.v;
        if (iVar != null) {
            iVar.D(this.f3381d);
        }
        LinearLayout linearLayout = this.p;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        LinearLayout linearLayout2 = this.q;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(8);
        }
        RelativeLayout relativeLayout = this.l;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(4);
        }
        RelativeLayout relativeLayout2 = this.r;
        if (relativeLayout2 != null) {
            relativeLayout2.setVisibility(8);
        }
    }

    private void p(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.contacts_reflesh_icon);
        this.h = imageView;
        imageView.setOnClickListener(this);
        view.findViewById(R.id.contacts_reflesh_textview).setOnClickListener(this);
        this.f3380c = (RecyclerView) view.findViewById(R.id.recyclerview);
        this.g = (AZSideBarView) view.findViewById(R.id.letter_list);
        this.s = (TextView) view.findViewById(R.id.capital_letter);
        this.g.setOnLetterChangeListener(new c());
        TextView textView = (TextView) view.findViewById(R.id.contacts_search_button);
        this.j = textView;
        textView.setOnClickListener(this);
        view.findViewById(R.id.contacts_search_image).setOnClickListener(this);
        view.findViewById(R.id.sync_contacts_btn).setOnClickListener(this);
        this.l = (RelativeLayout) view.findViewById(R.id.show_contacts_number_layout);
        this.m = (RelativeLayout) view.findViewById(R.id.sync_seekbar_layout);
        this.n = (TextView) view.findViewById(R.id.sync_seekbar_text);
        this.o = (ProgressBar) view.findViewById(R.id.sync_progressBar);
        this.p = (LinearLayout) view.findViewById(R.id.nodata_text);
        this.q = (LinearLayout) view.findViewById(R.id.sync_text);
        this.r = (RelativeLayout) view.findViewById(R.id.show_contacts_layout);
    }

    private void q() {
        RelativeLayout relativeLayout = this.l;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
    }

    private void r() {
        RelativeLayout relativeLayout = this.m;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        ProgressBar progressBar = this.o;
        if (progressBar != null) {
            progressBar.setProgress(0);
        }
        TextView textView = this.n;
        if (textView != null) {
            textView.setText("0%");
        }
    }

    @SuppressLint({"StringFormatInvalid"})
    private void s() {
        this.i = AnimationUtils.loadAnimation(BlueToothApplication.a().getApplicationContext(), R.anim.rotate_anim_play);
        this.j.setText(String.format(getResources().getString(R.string.bt_contacts_number), Integer.valueOf(this.f3381d.size())));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.w = linearLayoutManager;
        linearLayoutManager.F2(1);
        this.f3380c.setLayoutManager(this.w);
        i iVar = new i(getActivity(), this.f3381d);
        this.v = iVar;
        this.f3380c.setAdapter(iVar);
        if (com.byd.bluetoothcall.f.f.r()) {
            this.x = new h(getActivity(), this.f3381d);
        } else {
            this.x = new k(getActivity(), this.f3381d);
        }
        this.f3380c.h(this.x);
        if (com.byd.bluetoothcall.f.f.r()) {
            g gVar = new g(getActivity(), 1);
            if (com.byd.bluetoothcall.f.f.l(getActivity()) == 1) {
                gVar.l(getActivity().getColor(R.color.ts_list_line_1), 1);
            } else {
                gVar.l(getActivity().getColor(R.color.list_line_1), 1);
            }
            this.f3380c.h(gVar);
        } else {
            com.byd.bluetoothcall.view.c.d dVar = new com.byd.bluetoothcall.view.c.d(getActivity(), 1);
            if (com.byd.bluetoothcall.f.f.l(getActivity()) == 1) {
                dVar.l(getActivity().getColor(R.color.ts_list_line_1), 1);
            } else {
                dVar.l(getActivity().getColor(R.color.list_line_1), 1);
            }
            this.f3380c.h(dVar);
        }
        this.f3380c.setOnTouchListener(new a());
        this.f3380c.l(new C0085b());
    }

    public static b t(int i) {
        b bVar = new b();
        Bundle bundle = new Bundle();
        bundle.putInt("section_number", i);
        bVar.setArguments(bundle);
        return bVar;
    }

    private void v() {
        RelativeLayout relativeLayout = this.l;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
    }

    private void w() {
        ImageView imageView = this.h;
        if (imageView == null) {
            return;
        }
        imageView.clearAnimation();
        this.h.startAnimation(this.i);
        this.y.sendEmptyMessageDelayed(10001, 5000L);
    }

    private void x() {
        if (this.f3381d.size() > 0) {
            LinearLayout linearLayout = this.p;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
            LinearLayout linearLayout2 = this.q;
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(8);
            }
            RelativeLayout relativeLayout = this.r;
            if (relativeLayout != null) {
                relativeLayout.setVisibility(0);
                return;
            }
            return;
        }
        if (com.byd.bluetoothcall.c.a.F().P() < 1) {
            if (com.byd.bluetoothcall.c.a.F().L() == Sync$SyncState.SYNC_FAILD || com.byd.bluetoothcall.c.a.F().L() == Sync$SyncState.SYNC_END) {
                z();
                return;
            }
            return;
        }
        LinearLayout linearLayout3 = this.p;
        if (linearLayout3 != null) {
            linearLayout3.setVisibility(0);
        }
        LinearLayout linearLayout4 = this.q;
        if (linearLayout4 != null) {
            linearLayout4.setVisibility(8);
        }
        RelativeLayout relativeLayout2 = this.r;
        if (relativeLayout2 != null) {
            relativeLayout2.setVisibility(8);
        }
        r();
        v();
    }

    private void z() {
        LinearLayout linearLayout = this.q;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        LinearLayout linearLayout2 = this.p;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(8);
        }
        RelativeLayout relativeLayout = this.r;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        RelativeLayout relativeLayout2 = this.l;
        if (relativeLayout2 != null) {
            relativeLayout2.setVisibility(4);
        }
    }

    public void a() {
        y(com.byd.bluetoothcall.c.a.F().D());
    }

    public void d(int i) {
    }

    public void e(ContactsInfo contactsInfo, int i, int i2) {
        q();
        A(i, i2);
    }

    public void f(Sync$SyncType sync$SyncType, Sync$SyncState sync$SyncState) {
        BydLogHelper.logD("DANDAN", "syncType ==== " + sync$SyncType + " syncState  == " + sync$SyncState);
        if (sync$SyncType == Sync$SyncType.PHONE_BOOK) {
            int i = e.f3387a[sync$SyncState.ordinal()];
            if (i == 1) {
                if (this.j != null) {
                    this.j.setText(String.format(getResources().getString(R.string.bt_contacts_number), 0));
                }
                q();
                A(com.byd.bluetoothcall.c.a.F().P(), 0);
                b();
                return;
            }
            if (i == 3) {
                if (com.byd.bluetoothcall.c.a.F().a0() && isAdded()) {
                    com.byd.bluetoothcall.c.a.F().J0(0);
                    com.byd.bluetoothcall.c.a.F().A0(false);
                }
                r();
                z();
                return;
            }
            if (i != 4) {
                return;
            }
            k();
            this.f3382e.clear();
            r();
            v();
            x();
        }
    }

    public void g(boolean z) {
        if (!z) {
            com.byd.bluetoothcall.c.a.F().A0(false);
            l();
            com.byd.bluetoothcall.c.a.F().J0(8);
        } else {
            if (com.byd.bluetoothcall.c.a.F().L() != Sync$SyncState.SYNC_START && com.byd.bluetoothcall.c.a.F().L() != Sync$SyncState.SYNC_ING) {
                y(com.byd.bluetoothcall.c.a.F().D());
                return;
            }
            q();
            A(com.byd.bluetoothcall.c.a.F().P(), com.byd.bluetoothcall.c.a.F().O());
            b();
        }
    }

    public void m() {
        i iVar = this.v;
        if (iVar != null) {
            iVar.C(-1);
        }
    }

    public void n() {
        this.y.sendEmptyMessageDelayed(10002, 100L);
    }

    public void o() {
        this.y.sendEmptyMessageDelayed(10003, 100L);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        m();
        int id = view.getId();
        if (id == R.id.sync_contacts_btn) {
            if (com.byd.bluetoothcall.f.f.n(500)) {
                return;
            }
            com.byd.bluetoothcall.c.a.F().A0(true);
            c.a.b.c.t().g();
            return;
        }
        switch (id) {
            case R.id.contacts_reflesh_icon /* 2131296450 */:
            case R.id.contacts_reflesh_textview /* 2131296451 */:
                if (!com.byd.bluetoothcall.f.f.n(1000)) {
                    c();
                    break;
                }
                break;
            case R.id.contacts_search_button /* 2131296452 */:
            case R.id.contacts_search_image /* 2131296453 */:
                this.k = true;
                com.byd.bluetoothcall.c.a.F().z0(this.k);
                break;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3379b = (f) new r(this, r.a.b(getActivity().getApplication())).a(f.class);
        this.f3379b.d(getArguments() != null ? getArguments().getInt("section_number") : 1);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = this.f;
        if (view != null) {
            return view;
        }
        if (!getActivity().isInMultiWindowMode()) {
            this.f = layoutInflater.inflate(R.layout.contacts_fragment, viewGroup, false);
        } else if (com.byd.bluetoothcall.c.a.F().T() == 2) {
            this.f = layoutInflater.inflate(R.layout.multiwindowmode_land_contacts_fragment, viewGroup, false);
        } else {
            this.f = layoutInflater.inflate(R.layout.multiwindowmode_contacts_fragment, viewGroup, false);
        }
        p(this.f);
        s();
        return this.f;
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
    public void onPause() {
        com.byd.bluetoothcall.c.a.F().J0(8);
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        g(c.a.b.b.f0().n0() == 2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void setInitialSavedState(Fragment.SavedState savedState) {
        super.setInitialSavedState(savedState);
    }

    public void u(View view, boolean z) {
    }

    public void y(List<com.byd.bluetoothcall.b.b> list) {
        if (list == null) {
            return;
        }
        this.f3381d.clear();
        this.f3381d.addAll(list);
        if (this.j != null) {
            this.j.setText(String.format(getResources().getString(R.string.bt_contacts_number), Integer.valueOf(this.f3381d.size())));
        }
        for (int i = 0; i < this.f3381d.size(); i++) {
            this.f3381d.get(i).p(i % 5);
        }
        i iVar = this.v;
        if (iVar != null) {
            iVar.D(this.f3381d);
        }
        if (this.f3381d.size() > 0) {
            LinearLayoutManager linearLayoutManager = this.w;
            int iV1 = linearLayoutManager != null ? linearLayoutManager.V1() : 0;
            AZSideBarView aZSideBarView = this.g;
            if (aZSideBarView != null) {
                aZSideBarView.setLight(this.v.y(iV1));
            }
        }
        LinearLayoutManager linearLayoutManager2 = this.w;
        if (linearLayoutManager2 != null) {
            linearLayoutManager2.x1(0);
        }
        r();
        v();
        x();
    }
}
