package com.byd.bluetoothcall.view.activity;

import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.byd.bluetoothcall.c.a;
import com.byd.bluetoothcall.view.BlueToothApplication;
import com.byd.bluetoothcall.view.ui.CustomViewPager;
import com.byd.bluetoothcall.view.ui.SoftKeyBoardListener;
import com.byd.bluetoothcall.widget.WidgetProvide;
import com.byd.info.CallLogInfo;
import com.byd.info.ContactsInfo;
import com.byd.type_enum.Sync$SyncState;
import com.byd.type_enum.Sync$SyncType;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.search.BydMatchHelper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, a.g, c.a.a.c, c.a.a.e, c.a.a.d {

    /* JADX INFO: renamed from: b */
    private static String[] f3292b = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private TextView A;
    private TextView B;
    private View C;
    private FrameLayout D;
    protected ListView E;
    protected ImageView F;
    protected EditText G;
    protected com.byd.bluetoothcall.view.b.f I;
    protected TextView J;
    protected TextView K;
    protected String L;
    protected com.byd.bluetoothcall.b.b M;
    protected io.reactivex.disposables.b P;
    protected io.reactivex.disposables.b Q;
    private PhoneNumberFormattingTextWatcher R;
    private com.byd.bluetoothcall.view.d.a X;
    private View Y;
    private TextView Z;
    private ImageView a0;
    private com.byd.bluetoothcall.d.a<com.byd.bluetoothcall.d.c> b0;
    private com.byd.bluetoothcall.d.a<com.byd.bluetoothcall.d.c> c0;

    /* JADX INFO: renamed from: d */
    private com.byd.bluetoothcall.view.c.b f3294d;

    /* JADX INFO: renamed from: e */
    private com.byd.bluetoothcall.view.c.a f3295e;
    private com.byd.bluetoothcall.view.c.e f;
    private com.byd.bluetoothcall.view.c.c g;
    private com.byd.bluetoothcall.view.c.j h;
    private CustomViewPager i;
    private com.byd.bluetoothcall.view.b.d j;
    private EditText k;
    private ImageView l;
    private Editable m;
    private int n;
    private View o;
    private View p;
    private View q;
    private View r;
    private LinearLayout s;
    private View t;
    private TextView u;
    private ImageView v;
    private TextView w;
    private ImageView x;
    private TextView y;

    /* JADX INFO: renamed from: c */
    private List<Fragment> f3293c = new ArrayList();
    private List<com.byd.bluetoothcall.b.b> z = new ArrayList();
    protected List<com.byd.bluetoothcall.b.b> H = new ArrayList();
    private int N = 1;
    private boolean O = false;
    private int S = -1;
    private boolean T = false;
    private int U = 0;
    private String V = "97481c2717ea2b68f542b63b5546899a3d2cbca68796fe3f555bdc82ab9f9c52";
    private String W = "122f15ace7d0b8b24c82ed6ab2b5de430f8054d5836ba9437af6cc59e62d5e36";
    private final int d0 = 10001;
    private final int e0 = 10002;
    private final int f0 = 10003;
    private final int g0 = 10004;
    private final int h0 = 10005;
    private final int i0 = 10006;
    private final int j0 = 10007;
    private final int k0 = 10008;
    private final int l0 = 10009;
    private final int m0 = 10010;
    private Handler n0 = new a(Looper.getMainLooper());

    class a extends Handler {

        /* JADX INFO: renamed from: com.byd.bluetoothcall.view.activity.MainActivity$a$a */
        class C0081a implements d.a.i<com.byd.bluetoothcall.d.c> {
            C0081a() {
            }

            @Override // d.a.i
            /* JADX INFO: renamed from: a */
            public void onNext(com.byd.bluetoothcall.d.c cVar) {
                MainActivity.this.L = cVar.b().toString();
                onComplete();
            }

            @Override // d.a.i
            public void onComplete() {
                if (MainActivity.this.k != null) {
                    com.byd.bluetoothcall.c.a.F().E0(false);
                    if (MainActivity.this.k.getText().length() > 0) {
                        if (!MainActivity.this.O && MainActivity.this.l != null) {
                            MainActivity.this.l.setVisibility(0);
                        }
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.M(mainActivity.L);
                        if (c.a.b.b.f0().n0() == 2) {
                            MainActivity.this.i.setVisibility(MainActivity.this.O ? 8 : 0);
                            MainActivity.this.i.N(MainActivity.this.f3293c.size() - 1, false);
                            return;
                        }
                        return;
                    }
                    com.byd.bluetoothcall.c.a.F().G0("");
                    com.byd.bluetoothcall.c.a.F().H0("");
                    if (MainActivity.this.l != null) {
                        MainActivity.this.l.setVisibility(4);
                    }
                    if (MainActivity.this.k != null) {
                        MainActivity.this.k.setCursorVisible(false);
                    }
                    if (MainActivity.this.i.getCurrentItem() == 4) {
                        if (MainActivity.this.S == -1) {
                            MainActivity.this.S = 0;
                        }
                        MainActivity.this.i.N(MainActivity.this.S, false);
                        MainActivity mainActivity2 = MainActivity.this;
                        mainActivity2.O(mainActivity2.S);
                    }
                    if (MainActivity.this.A != null) {
                        MainActivity.this.A.setText("");
                    }
                    com.byd.bluetoothcall.c.a.F().b();
                }
            }

            @Override // d.a.i
            public void onError(Throwable th) {
            }

            @Override // d.a.i
            public void onSubscribe(io.reactivex.disposables.b bVar) {
                MainActivity.this.Q = bVar;
            }
        }

        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            switch (message.what) {
                case 10001:
                    if (MainActivity.this.i != null && MainActivity.this.i.getCurrentItem() == 3) {
                        if (MainActivity.this.S == -1) {
                            MainActivity.this.S = 0;
                        }
                        MainActivity.this.i.N(MainActivity.this.S, false);
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.O(mainActivity.S);
                        break;
                    }
                    break;
                case 10002:
                    if (MainActivity.this.g != null) {
                        MainActivity.this.g.o((String) message.obj);
                    }
                    com.byd.bluetoothcall.c.a.F().G0((String) message.obj);
                    break;
                case 10003:
                    if (MainActivity.this.k != null) {
                        MainActivity mainActivity2 = MainActivity.this;
                        mainActivity2.b0 = com.byd.bluetoothcall.d.b.a(mainActivity2.k);
                        MainActivity.this.b0.d(200L, TimeUnit.MILLISECONDS).m(d.a.l.c.a.a()).a(new C0081a());
                        break;
                    }
                    break;
                case 10004:
                    if (MainActivity.this.f3294d != null) {
                        MainActivity.this.f3294d.e((ContactsInfo) message.obj, message.arg1, message.arg2);
                    }
                    break;
                case 10005:
                    if (!MainActivity.this.O) {
                        MainActivity.this.i.N(0, false);
                        MainActivity.this.O(0);
                        MainActivity.this.c(true);
                    }
                    EditText editText = MainActivity.this.G;
                    if (editText != null) {
                        editText.setText((String) message.obj);
                        EditText editText2 = MainActivity.this.G;
                        editText2.setSelection(editText2.getText().length());
                    }
                    break;
                case 10006:
                    String string = message.getData().getString("vr_name");
                    String string2 = message.getData().getString("vr_number");
                    if (string2 != null) {
                        com.byd.bluetoothcall.b.b bVar = new com.byd.bluetoothcall.b.b();
                        if (TextUtils.isEmpty(string)) {
                            bVar.s(string2);
                        } else {
                            bVar.s(string);
                        }
                        if (TextUtils.isEmpty(bVar.i())) {
                            bVar.s(MainActivity.this.getString(R.string.unknown_number));
                        }
                        bVar.q(bVar.i());
                        com.byd.bluetoothcall.b.c cVar = new com.byd.bluetoothcall.b.c();
                        cVar.c(string2);
                        cVar.b(com.byd.bluetoothcall.f.d.b(BlueToothApplication.a().getApplicationContext(), cVar.a()));
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(cVar);
                        bVar.t(arrayList);
                        bVar.o(com.byd.bluetoothcall.f.f.f(bVar.i(), 1));
                        bVar.p(0);
                        com.byd.bluetoothcall.view.d.a.f().d();
                        com.byd.bluetoothcall.view.d.a.f().h(bVar, null, null);
                    }
                    break;
                case 10007:
                    EditText editText3 = MainActivity.this.G;
                    if (editText3 != null) {
                        editText3.setEnabled(true);
                        if ((com.byd.bluetoothcall.c.a.F().d0() && c.a.b.b.f0().B0()) || (MainActivity.this.X != null && MainActivity.this.X.i())) {
                            MainActivity.this.n0.sendEmptyMessageDelayed(10009, 10L);
                        } else {
                            MainActivity.this.G.requestFocus();
                            com.byd.bluetoothcall.c.a.F().K0(BlueToothApplication.a().getApplicationContext());
                        }
                    }
                    break;
                case 10008:
                    if (!com.byd.bluetoothcall.c.a.F().c0()) {
                        EditText editText4 = MainActivity.this.G;
                        if (editText4 != null) {
                            editText4.clearFocus();
                            MainActivity.this.G.setEnabled(true);
                            MainActivity.this.n0.sendEmptyMessageDelayed(10009, 10L);
                        }
                    } else {
                        MainActivity.this.n0.sendEmptyMessageDelayed(10007, 800L);
                    }
                    break;
                case 10009:
                    com.byd.bluetoothcall.c.a.F().V(BlueToothApplication.a().getApplicationContext(), MainActivity.this.G);
                    break;
                case 10010:
                    MainActivity.this.j();
                    break;
            }
        }
    }

    class b implements View.OnTouchListener {
        b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0) {
                return false;
            }
            MainActivity.this.k.setCursorVisible(true);
            return false;
        }
    }

    class c implements View.OnLongClickListener {
        c() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            MainActivity.this.a();
            return false;
        }
    }

    class d implements View.OnLongClickListener {
        d() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            com.byd.bluetoothcall.f.f.c(BlueToothApplication.a().getApplicationContext(), 12);
            MainActivity.this.L("+");
            return true;
        }
    }

    class e implements Runnable {

        /* JADX INFO: renamed from: b */
        final /* synthetic */ Sync$SyncType f3301b;

        /* JADX INFO: renamed from: c */
        final /* synthetic */ Sync$SyncState f3302c;

        e(Sync$SyncType sync$SyncType, Sync$SyncState sync$SyncState) {
            this.f3301b = sync$SyncType;
            this.f3302c = sync$SyncState;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MainActivity.this.f3294d != null) {
                MainActivity.this.f3294d.f(this.f3301b, this.f3302c);
            }
            if ((MainActivity.this.isInMultiWindowMode() || MainActivity.this.getResources().getConfiguration().orientation != 2) && this.f3301b == Sync$SyncType.PHONE_BOOK) {
                int i = g.f3308a[this.f3302c.ordinal()];
                if (i == 1) {
                    MainActivity.this.s.setVisibility(8);
                    return;
                }
                if (i != 2) {
                    return;
                }
                if (c.a.b.b.f0().n0() != 2) {
                    MainActivity.this.s.setVisibility(8);
                } else {
                    if (MainActivity.this.O) {
                        return;
                    }
                    MainActivity.this.s.setVisibility(0);
                }
            }
        }
    }

    class f implements Runnable {

        /* JADX INFO: renamed from: b */
        final /* synthetic */ ContactsInfo f3304b;

        /* JADX INFO: renamed from: c */
        final /* synthetic */ int f3305c;

        /* JADX INFO: renamed from: d */
        final /* synthetic */ int f3306d;

        f(ContactsInfo contactsInfo, int i, int i2) {
            this.f3304b = contactsInfo;
            this.f3305c = i;
            this.f3306d = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MainActivity.this.f3294d != null) {
                MainActivity.this.f3294d.e(this.f3304b, this.f3305c, this.f3306d);
            }
        }
    }

    static /* synthetic */ class g {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f3308a;

        static {
            int[] iArr = new int[Sync$SyncState.values().length];
            f3308a = iArr;
            try {
                iArr[Sync$SyncState.SYNC_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3308a[Sync$SyncState.SYNC_END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    class h implements ViewPager.j {
        h() {
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void a(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void c(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void d(int i) {
            if (MainActivity.this.isInMultiWindowMode()) {
                if (i <= 3) {
                    MainActivity.this.S = i;
                }
            } else if (i <= 2) {
                MainActivity.this.S = i;
            }
            MainActivity.this.O(i);
        }
    }

    class i implements AdapterView.OnItemClickListener {
        i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            MainActivity mainActivity = MainActivity.this;
            mainActivity.M = mainActivity.H.get(i);
            String string = MainActivity.this.getString(R.string.bt_call_log_recode);
            String string2 = MainActivity.this.getString(R.string.bt_call_log_no_recode);
            if (MainActivity.this.X != null) {
                MainActivity.this.X.d();
                MainActivity.this.X.h(MainActivity.this.M, string, string2);
            }
        }
    }

    class j implements d.a.i<com.byd.bluetoothcall.d.c> {
        j() {
        }

        @Override // d.a.i
        /* JADX INFO: renamed from: a */
        public void onNext(com.byd.bluetoothcall.d.c cVar) {
            String string = cVar.b().toString();
            if (TextUtils.isEmpty(string)) {
                MainActivity.this.H.clear();
                ImageView imageView = MainActivity.this.F;
                if (imageView != null) {
                    imageView.setVisibility(4);
                }
            } else {
                ImageView imageView2 = MainActivity.this.F;
                if (imageView2 != null) {
                    imageView2.setVisibility(0);
                }
                MainActivity.this.K(string);
            }
            onComplete();
        }

        @Override // d.a.i
        public void onComplete() {
            if (MainActivity.this.H.size() == 0) {
                TextView textView = MainActivity.this.K;
                if (textView != null) {
                    textView.setVisibility(0);
                }
            } else {
                TextView textView2 = MainActivity.this.K;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                }
            }
            MainActivity.this.J.setText(String.format(MainActivity.this.getResources().getString(R.string.bt_find_contacts_numbers), Integer.valueOf(MainActivity.this.H.size())));
            MainActivity.this.I.notifyDataSetChanged();
        }

        @Override // d.a.i
        public void onError(Throwable th) {
        }

        @Override // d.a.i
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            MainActivity.this.P = bVar;
        }
    }

    class k implements SoftKeyBoardListener.b {
        k() {
        }

        @Override // com.byd.bluetoothcall.view.ui.SoftKeyBoardListener.b
        public void a() {
            EditText editText = MainActivity.this.G;
            if (editText != null) {
                editText.clearFocus();
            }
            try {
                Intent intent = new Intent("com.byd.card.open.FloatCard");
                intent.putExtra("package", "com.byd.bluetoothcall");
                MainActivity.this.sendBroadcast(intent);
            } catch (Exception unused) {
            }
        }

        @Override // com.byd.bluetoothcall.view.ui.SoftKeyBoardListener.b
        public void b(int i) {
            try {
                Intent intent = new Intent("com.byd.card.close.FloatCard");
                intent.putExtra("package", "com.byd.bluetoothcall");
                MainActivity.this.sendBroadcast(intent);
                if (MainActivity.this.D.getVisibility() == 8) {
                    com.byd.bluetoothcall.c.a.F().V(BlueToothApplication.a().getApplicationContext(), MainActivity.this.D);
                }
            } catch (Exception unused) {
            }
        }
    }

    class l implements TextView.OnEditorActionListener {
        l() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            Log.d("QREWW", "onEditorAction == " + i);
            if (i != 6 && i != 5) {
                return false;
            }
            EditText editText = MainActivity.this.G;
            if (editText != null) {
                editText.clearFocus();
            }
            com.byd.bluetoothcall.c.a.F().V(BlueToothApplication.a().getApplicationContext(), textView);
            return false;
        }
    }

    class m implements View.OnFocusChangeListener {
        m() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            Log.d("WEFEDWFEWF", "search_book == hasFocus = " + z);
        }
    }

    class n implements View.OnFocusChangeListener {
        n() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            Log.d("WEFEDWFEWF", "keyboard_iput == hasFocus = " + z);
            if (z || MainActivity.this.k == null) {
                return;
            }
            MainActivity.this.k.setSelection(MainActivity.this.k.getText().length());
        }
    }

    class o implements ViewTreeObserver.OnGlobalLayoutListener {
        o() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (MainActivity.this.O) {
                return;
            }
            com.byd.bluetoothcall.c.a.F().V(BlueToothApplication.a().getApplicationContext(), MainActivity.this.k);
        }
    }

    private class p implements ActionMode.Callback {
        private p() {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        /* synthetic */ p(MainActivity mainActivity, a aVar) {
            this();
        }
    }

    private void I() {
        this.X = com.byd.bluetoothcall.view.d.a.f();
        c.a.b.b.f0().F0(this);
        c.a.b.c.t().A(this);
        c.a.b.a.K().V(this);
        if (com.byd.bluetoothcall.c.a.F().T() == 0) {
            this.N = getResources().getConfiguration().orientation;
        } else {
            this.N = com.byd.bluetoothcall.c.a.F().T();
        }
        if (isInMultiWindowMode()) {
            if (com.byd.bluetoothcall.f.f.r() && this.N == 1) {
                setContentView(R.layout.multiwindowmode_ar_activity_main);
            } else {
                setContentView(R.layout.multiwindowmode_land_activity_main);
            }
        } else if (com.byd.bluetoothcall.f.f.k() == 1 && this.N == 2) {
            setContentView(R.layout.right_driver_activity_main);
        } else {
            setContentView(R.layout.activity_main);
        }
        com.byd.bluetoothcall.c.a.F().i0(this);
        this.f3293c.clear();
        com.byd.bluetoothcall.view.c.b bVarT = com.byd.bluetoothcall.view.c.b.t(1);
        this.f3294d = bVarT;
        this.f3293c.add(bVarT);
        com.byd.bluetoothcall.view.c.a aVarM = com.byd.bluetoothcall.view.c.a.m(2);
        this.f3295e = aVarM;
        this.f3293c.add(aVarM);
        com.byd.bluetoothcall.view.c.e eVarM = com.byd.bluetoothcall.view.c.e.m(3);
        this.f = eVarM;
        this.f3293c.add(eVarM);
        com.byd.bluetoothcall.view.c.c cVarN = com.byd.bluetoothcall.view.c.c.n(4);
        this.g = cVarN;
        this.f3293c.add(cVarN);
        com.byd.bluetoothcall.view.c.j jVarE = com.byd.bluetoothcall.view.c.j.e(5);
        this.h = jVarE;
        this.f3293c.add(jVarE);
        String strI = com.byd.bluetoothcall.f.f.i(BYDAutoSysPropertiesDevice.COUNTRY_CODE, "");
        if (TextUtils.isEmpty(strI) || strI.equals("unknown")) {
            this.R = new PhoneNumberFormattingTextWatcher(Locale.getDefault().getCountry());
        } else {
            this.R = new PhoneNumberFormattingTextWatcher(PhoneNumberUtil.k().s(Integer.parseInt(strI)));
        }
        J(isInMultiWindowMode());
        k();
    }

    private void J(boolean z) {
        this.i = (CustomViewPager) findViewById(R.id.view_pager);
        this.C = findViewById(R.id.include_keyboard_layout);
        View viewFindViewById = findViewById(R.id.tab_contacts_layout);
        this.o = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        View viewFindViewById2 = findViewById(R.id.tab_calllog_layout);
        this.p = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this);
        View viewFindViewById3 = findViewById(R.id.tab_missedcalls_layout);
        this.q = viewFindViewById3;
        viewFindViewById3.setOnClickListener(this);
        this.B = (TextView) findViewById(R.id.unread_missed_calls);
        this.A = (TextView) findViewById(R.id.address_of_number);
        this.D = (FrameLayout) findViewById(R.id.contacts_search_layout);
        findViewById(R.id.search_layout_back).setOnClickListener(this);
        this.E = (ListView) findViewById(R.id.search_contacts_list);
        this.G = (EditText) findViewById(R.id.search_book);
        Log.d("WEFEDWFEWF", "search_book.hasFocus() = " + this.G.hasFocus());
        this.G.setLongClickable(false);
        this.G.setOnEditorActionListener(new l());
        this.G.setCustomSelectionActionModeCallback(new p(this, null));
        this.G.setCustomInsertionActionModeCallback(new p(this, null));
        this.G.setOnFocusChangeListener(new m());
        this.J = (TextView) findViewById(R.id.search_contacts_number);
        this.K = (TextView) findViewById(R.id.search_no_data);
        View viewFindViewById4 = findViewById(R.id.contacts_permission_layout);
        this.Y = viewFindViewById4;
        viewFindViewById4.findViewById(R.id.dialog_confirm_btn).setOnClickListener(this);
        this.Y.findViewById(R.id.dialog_cancle_btn).setOnClickListener(this);
        if (z) {
            if (this.n0.hasMessages(10003)) {
                this.n0.removeMessages(10003);
            }
            View viewFindViewById5 = findViewById(R.id.tab_dial_keyboard_layout);
            this.r = viewFindViewById5;
            viewFindViewById5.setOnClickListener(this);
        } else {
            EditText editText = (EditText) findViewById(R.id.keyboard_iput);
            this.k = editText;
            editText.setCustomSelectionActionModeCallback(new p(this, null));
            this.k.setCustomInsertionActionModeCallback(new p(this, null));
            this.k.setOnFocusChangeListener(new n());
            this.k.setLongClickable(false);
            this.k.setCursorVisible(false);
            this.k.clearFocus();
            PhoneNumberFormattingTextWatcher phoneNumberFormattingTextWatcher = this.R;
            if (phoneNumberFormattingTextWatcher != null) {
                this.k.addTextChangedListener(phoneNumberFormattingTextWatcher);
            }
            this.k.setText(com.byd.bluetoothcall.c.a.F().S());
            EditText editText2 = this.k;
            editText2.setSelection(editText2.getText().length());
            this.k.setShowSoftInputOnFocus(false);
            this.k.getViewTreeObserver().addOnGlobalLayoutListener(new o());
            this.k.setOnTouchListener(new b());
            if (this.n0.hasMessages(10003)) {
                this.n0.removeMessages(10003);
            }
            this.n0.sendEmptyMessageDelayed(10003, 500L);
            ImageView imageView = (ImageView) findViewById(R.id.keyboard_delete);
            this.l = imageView;
            imageView.setOnClickListener(this);
            this.l.setOnLongClickListener(new c());
            findViewById(R.id.keyboard_number_1).setOnClickListener(this);
            findViewById(R.id.keyboard_number_2).setOnClickListener(this);
            findViewById(R.id.keyboard_number_3).setOnClickListener(this);
            findViewById(R.id.keyboard_number_4).setOnClickListener(this);
            findViewById(R.id.keyboard_number_5).setOnClickListener(this);
            findViewById(R.id.keyboard_number_6).setOnClickListener(this);
            findViewById(R.id.keyboard_number_7).setOnClickListener(this);
            findViewById(R.id.keyboard_number_8).setOnClickListener(this);
            findViewById(R.id.keyboard_number_9).setOnClickListener(this);
            TextView textView = (TextView) findViewById(R.id.keyboard_number_0);
            this.Z = textView;
            textView.setOnClickListener(this);
            this.Z.setOnLongClickListener(new d());
            findViewById(R.id.keyboard_number_x).setOnClickListener(this);
            findViewById(R.id.keyboard_number_j).setOnClickListener(this);
            ImageView imageView2 = (ImageView) findViewById(R.id.bt_dial_number_btn);
            this.a0 = imageView2;
            imageView2.setOnClickListener(this);
        }
        ImageView imageView3 = (ImageView) findViewById(R.id.search_clear_btn);
        this.F = imageView3;
        imageView3.setOnClickListener(this);
        this.x = (ImageView) findViewById(R.id.bt_battery_icon);
        this.y = (TextView) findViewById(R.id.bt_nametext);
        View viewFindViewById6 = findViewById(R.id.not_connect_layout);
        this.t = viewFindViewById6;
        this.w = (TextView) viewFindViewById6.findViewById(R.id.not_connect_text);
        this.v = (ImageView) this.t.findViewById(R.id.not_connect_image);
        TextView textView2 = (TextView) this.t.findViewById(R.id.go_to_connect_btn);
        this.u = textView2;
        textView2.setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.bt_name_text_layout);
        this.s = linearLayout;
        linearLayout.setOnClickListener(this);
    }

    public void K(String str) {
        this.H.clear();
        this.z.clear();
        if (this.f3294d != null) {
            this.z.addAll(com.byd.bluetoothcall.c.a.F().D());
        }
        for (int i2 = 0; i2 < this.z.size(); i2++) {
            com.byd.bluetoothcall.b.b bVar = new com.byd.bluetoothcall.b.b();
            String strMatchPy = BydMatchHelper.matchPy(str, this.z.get(i2).l());
            if (strMatchPy != null) {
                bVar.s(this.z.get(i2).i());
                bVar.q(this.z.get(i2).i());
                bVar.u(this.z.get(i2).k());
                bVar.o(this.z.get(i2).e());
                bVar.p(this.z.get(i2).g());
                bVar.v(this.z.get(i2).l());
                bVar.w(this.z.get(i2).m());
                bVar.t(this.z.get(i2).j());
                bVar.r(strMatchPy);
            } else {
                String strMatch = BydMatchHelper.match(str, this.z.get(i2).i());
                if (strMatch != null) {
                    bVar.s(this.z.get(i2).i());
                    bVar.q(this.z.get(i2).i());
                    bVar.u(this.z.get(i2).k());
                    bVar.o(this.z.get(i2).e());
                    bVar.p(this.z.get(i2).g());
                    bVar.v(this.z.get(i2).l());
                    bVar.w(this.z.get(i2).m());
                    bVar.t(this.z.get(i2).j());
                    bVar.r(strMatch);
                } else {
                    List<com.byd.bluetoothcall.b.c> listJ = this.z.get(i2).j();
                    int i3 = 0;
                    while (true) {
                        if (i3 >= listJ.size()) {
                            break;
                        }
                        String strMatch2 = BydMatchHelper.match(str, listJ.get(i3).a());
                        if (strMatch2 != null) {
                            bVar.s(this.z.get(i2).i());
                            bVar.u(this.z.get(i2).k());
                            bVar.o(this.z.get(i2).e());
                            bVar.p(this.z.get(i2).g());
                            bVar.v(this.z.get(i2).l());
                            bVar.w(this.z.get(i2).m());
                            bVar.t(this.z.get(i2).j());
                            bVar.r(strMatch2);
                            break;
                        }
                        i3++;
                    }
                }
            }
            if (!TextUtils.isEmpty(bVar.h()) && !this.H.contains(bVar)) {
                this.H.add(bVar);
            }
        }
    }

    public void L(String str) {
        if (c.a.b.b.f0().n0() == 2 || this.T) {
            this.n = this.k.getSelectionStart();
            Editable text = this.k.getText();
            this.m = text;
            if (text == null || str == null) {
                return;
            }
            text.insert(this.n, str);
            if (this.O || this.l.getVisibility() == 0) {
                return;
            }
            this.l.setVisibility(0);
        }
    }

    private void N(int i2) {
        if (this.i != null && c.a.b.b.f0().n0() == 2) {
            EditText editText = this.k;
            if (editText != null && editText.getText().length() > 0) {
                M(this.k.getText().toString());
            }
            this.i.setVisibility(i2);
        }
        EditText editText2 = this.k;
        if (editText2 != null) {
            editText2.setVisibility(i2);
        }
        TextView textView = this.A;
        if (textView != null) {
            textView.setVisibility(i2);
        }
        View view = this.C;
        if (view != null) {
            view.setVisibility(i2);
        }
        ImageView imageView = this.a0;
        if (imageView != null) {
            imageView.setVisibility(i2);
        }
        if (this.s != null) {
            if (isInMultiWindowMode() || getResources().getConfiguration().orientation != 2) {
                if (c.a.b.b.f0().n0() != 2) {
                    this.s.setVisibility(8);
                    return;
                }
                if (this.S > 0 || !(com.byd.bluetoothcall.c.a.F().L() == Sync$SyncState.SYNC_START || com.byd.bluetoothcall.c.a.F().L() == Sync$SyncState.SYNC_ING)) {
                    this.s.setVisibility(i2);
                    return;
                }
                LinearLayout linearLayout = this.s;
                if (linearLayout != null) {
                    linearLayout.setVisibility(8);
                }
            }
        }
    }

    public void O(int i2) {
        View view;
        com.byd.bluetoothcall.c.a.F().q0(i2);
        j();
        if (i2 == 2) {
            com.byd.bluetoothcall.c.a.F().u0(0);
            com.byd.bluetoothcall.c.a.F().l();
            WidgetProvide.j();
        }
        if (i2 == -1) {
            this.o.setEnabled(false);
            this.p.setEnabled(false);
            this.q.setEnabled(false);
            View view2 = this.r;
            if (view2 != null) {
                view2.setEnabled(false);
            }
        } else {
            this.o.setEnabled(true);
            this.p.setEnabled(true);
            this.q.setEnabled(true);
            View view3 = this.r;
            if (view3 != null) {
                view3.setEnabled(true);
            }
        }
        this.o.setSelected(false);
        this.p.setSelected(false);
        this.q.setSelected(false);
        View view4 = this.r;
        if (view4 != null) {
            view4.setSelected(false);
        }
        if (c.a.b.b.f0().e0() == null) {
            return;
        }
        if (i2 == 0) {
            this.o.setSelected(true);
            return;
        }
        if (i2 == 1) {
            this.p.setSelected(true);
            return;
        }
        if (i2 == 2) {
            this.q.setSelected(true);
        } else if (i2 == 3 && (view = this.r) != null) {
            view.setSelected(true);
        }
    }

    public void j() {
        com.byd.bluetoothcall.view.c.e eVar;
        if (com.byd.bluetoothcall.c.a.F().R() == 0) {
            com.byd.bluetoothcall.view.c.b bVar = this.f3294d;
            if (bVar != null) {
                bVar.m();
                return;
            }
            return;
        }
        if (com.byd.bluetoothcall.c.a.F().R() == 1) {
            com.byd.bluetoothcall.view.c.a aVar = this.f3295e;
            if (aVar != null) {
                aVar.h();
                return;
            }
            return;
        }
        if (com.byd.bluetoothcall.c.a.F().R() != 2 || (eVar = this.f) == null) {
            return;
        }
        eVar.h();
    }

    private void k() {
        List<Fragment> listF = getSupportFragmentManager().f();
        androidx.fragment.app.k kVarA = getSupportFragmentManager().a();
        for (int i2 = 0; i2 < listF.size(); i2++) {
            kVarA.k(listF.get(i2));
        }
        kVarA.f();
        this.j = new com.byd.bluetoothcall.view.b.d(getSupportFragmentManager(), this.f3293c);
        this.i.setPagingEnabled(false);
        this.i.setOffscreenPageLimit(5);
        this.i.setAdapter(this.j);
        this.j.l();
        this.i.N(0, false);
        O(0);
        this.i.c(new h());
        com.byd.bluetoothcall.view.b.f fVar = new com.byd.bluetoothcall.view.b.f(this.H, this);
        this.I = fVar;
        this.E.setAdapter((ListAdapter) fVar);
        this.E.setOnItemClickListener(new i());
        com.byd.bluetoothcall.d.a<com.byd.bluetoothcall.d.c> aVarA = com.byd.bluetoothcall.d.b.a(this.G);
        this.c0 = aVarA;
        aVarA.d(300L, TimeUnit.MILLISECONDS).m(d.a.l.c.a.a()).a(new j());
        SoftKeyBoardListener.f(this, new k());
        i(com.byd.bluetoothcall.c.a.F().I());
    }

    private void l(Intent intent) {
        EditText editText;
        try {
            int intExtra = intent.getIntExtra("page", -1);
            Log.d("WEFEDWFEWF", "Jump_Page ========mPage =====" + intExtra);
            String stringExtra = intent.getStringExtra("dial_number");
            BydLogHelper.logD("DANDAN", "number == " + com.byd.bluetoothcall.f.f.d(stringExtra));
            BydLogHelper.logD("DANDAN", "Jump_Page == " + intExtra);
            if (intExtra == 6) {
                String stringExtra2 = intent.getStringExtra("vr_search_name");
                String stringExtra3 = intent.getStringExtra("vr_show_contacts_info_name");
                String stringExtra4 = intent.getStringExtra("vr_show_contacts_info_number");
                if (!TextUtils.isEmpty(stringExtra2)) {
                    this.n0.removeMessages(10005);
                    Message messageObtainMessage = this.n0.obtainMessage();
                    messageObtainMessage.what = 10005;
                    messageObtainMessage.obj = stringExtra2;
                    this.n0.sendMessageDelayed(messageObtainMessage, 500L);
                    return;
                }
                if (TextUtils.isEmpty(stringExtra4)) {
                    return;
                }
                this.n0.removeMessages(10006);
                Message messageObtainMessage2 = this.n0.obtainMessage();
                messageObtainMessage2.what = 10006;
                Bundle bundle = new Bundle();
                bundle.putString("vr_name", stringExtra3);
                bundle.putString("vr_number", stringExtra4);
                messageObtainMessage2.setData(bundle);
                this.n0.sendMessageDelayed(messageObtainMessage2, 500L);
                return;
            }
            if (intExtra == -1) {
                intExtra = this.i.getCurrentItem();
                this.i.N(intExtra, false);
                O(intExtra);
            }
            if (intExtra != 3) {
                if (intExtra == 0) {
                    this.i.N(intExtra, false);
                    O(intExtra);
                    return;
                } else {
                    if (intExtra == 1 || intExtra == 2) {
                        com.byd.bluetoothcall.c.a.F().z0(false);
                        this.i.N(intExtra, false);
                        O(intExtra);
                        return;
                    }
                    return;
                }
            }
            com.byd.bluetoothcall.c.a.F().z0(false);
            if (!isInMultiWindowMode()) {
                if (TextUtils.isEmpty(stringExtra) || (editText = this.k) == null) {
                    return;
                }
                editText.setText(stringExtra);
                EditText editText2 = this.k;
                editText2.setSelection(editText2.getText().length());
                return;
            }
            if (TextUtils.isEmpty(stringExtra) || this.g == null) {
                return;
            }
            this.i.N(3, false);
            O(3);
            setIntent(null);
            if (this.n0.hasMessages(10002)) {
                this.n0.removeMessages(10002);
            }
            Message messageObtainMessage3 = this.n0.obtainMessage();
            messageObtainMessage3.what = 10002;
            messageObtainMessage3.obj = stringExtra;
            this.n0.sendMessageDelayed(messageObtainMessage3, 500L);
        } catch (Exception unused) {
        }
    }

    private void m(String str, String str2) {
        try {
            a();
            if (com.byd.bluetoothcall.f.f.o(1000)) {
                return;
            }
            ComponentName componentName = new ComponentName(str, str2);
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setComponent(componentName);
            startActivity(intent);
        } catch (Exception unused) {
        }
    }

    private void n(boolean z) {
        LinearLayout linearLayout;
        if (!z) {
            if (c.a.b.a.K().M() == 1) {
                this.w.setText(getString(R.string.bt_phone_audio_disabled));
                this.u.setText(getString(R.string.go_to_open));
                this.v.setImageResource(R.drawable.bt_phone_audio_not_open);
            } else {
                this.w.setText(getString(R.string.bt_not_connect));
                this.u.setText(getString(R.string.bt_to_conncet));
                this.v.setImageResource(R.drawable.bt_empty_icon);
            }
            O(-1);
            this.t.setVisibility(0);
            this.i.setVisibility(4);
            LinearLayout linearLayout2 = this.s;
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(4);
            }
            a();
            com.byd.bluetoothcall.c.a.F().z0(false);
            EditText editText = this.G;
            if (editText != null) {
                editText.setText("");
            }
            com.byd.bluetoothcall.c.a.F().V(BlueToothApplication.a().getApplicationContext(), this.s);
            return;
        }
        BluetoothDevice bluetoothDeviceE0 = c.a.b.b.f0().e0();
        this.t.setVisibility(8);
        this.i.setVisibility(this.O ? 8 : 0);
        LinearLayout linearLayout3 = this.s;
        if (linearLayout3 != null && !this.O) {
            linearLayout3.setVisibility(0);
        }
        this.y.setText(bluetoothDeviceE0.getName());
        update_BatLevel(-1, c.a.b.b.f0().e0());
        if (isInMultiWindowMode() && this.i.getCurrentItem() == 4) {
            this.i.setCurrentItem(3);
        }
        O(this.i.getCurrentItem());
        if (isInMultiWindowMode() || getResources().getConfiguration().orientation != 2) {
            if ((com.byd.bluetoothcall.c.a.F().L() == Sync$SyncState.SYNC_START || com.byd.bluetoothcall.c.a.F().L() == Sync$SyncState.SYNC_ING) && (linearLayout = this.s) != null) {
                linearLayout.setVisibility(8);
            }
        }
    }

    public void M(String str) {
        j();
        com.byd.bluetoothcall.c.a.F().H0(str.replace(" ", ""));
        com.byd.bluetoothcall.c.a.F().G0(str.replace(" ", ""));
        com.byd.bluetoothcall.view.c.c cVar = this.g;
        if (cVar != null) {
            cVar.o(str.replace(" ", ""));
        }
        if (str.length() >= 3 && str.length() <= 18) {
            if (c.a.b.b.f0().n0() == 2) {
                this.A.setText(com.byd.bluetoothcall.f.d.b(BlueToothApplication.a().getApplicationContext(), str.replace(" ", "")));
            }
            if (str.length() == 15) {
                if (com.byd.bluetoothcall.f.f.j(str).equals(this.V)) {
                    m("com.byd.secretcode", "com.byd.secretcode.activity.PasswordActivity");
                    this.A.setText("");
                    this.U = 0;
                    this.T = false;
                } else if (com.byd.bluetoothcall.f.f.j(str).equals(this.W)) {
                    m("com.byd.secretcode", "com.byd.secretcode.activity.OBDActivity");
                    this.A.setText("");
                    this.U = 0;
                    this.T = false;
                }
            }
        } else {
            this.A.setText("");
        }
        if (c.a.b.b.f0().n0() == 2) {
            com.byd.bluetoothcall.c.a.F().k0(str.replace("(", "").replace(")", "").replace(" ", "").replace("-", ""));
        }
    }

    @Override // c.a.a.e
    public void SyncBookTotal(int i2) {
        com.byd.bluetoothcall.view.c.b bVar = this.f3294d;
        if (bVar != null) {
            bVar.d(i2);
        }
    }

    @Override // c.a.a.e
    public void SyncCallLogOne_data(CallLogInfo callLogInfo) {
        com.byd.bluetoothcall.view.c.a aVar = this.f3295e;
        if (aVar != null) {
            aVar.b(callLogInfo);
        }
        com.byd.bluetoothcall.view.c.e eVar = this.f;
        if (eVar != null) {
            eVar.b(callLogInfo);
        }
        EditText editText = this.k;
        if (editText != null && editText.getText().length() > 0) {
            M(this.k.getText().toString());
        }
        com.byd.bluetoothcall.view.d.a aVar2 = this.X;
        if (aVar2 == null || !aVar2.i()) {
            return;
        }
        com.byd.bluetoothcall.b.b bVarG = this.X.g();
        if (callLogInfo == null || bVarG == null) {
            return;
        }
        BydLogHelper.logD("DANDAN", "initData ==== " + bVarG);
        this.X.h(bVarG, null, null);
    }

    @Override // c.a.a.e
    public void SyncCallLog_data(List<CallLogInfo> list) {
    }

    @Override // c.a.a.e
    public void SyncPhoeBook_data(List<ContactsInfo> list) {
    }

    @Override // c.a.a.e
    public void SyncProgress(ContactsInfo contactsInfo, int i2, int i3) {
        runOnUiThread(new f(contactsInfo, i2, i3));
    }

    @Override // c.a.a.e
    public void SyncState(Sync$SyncType sync$SyncType, Sync$SyncState sync$SyncState) {
        this.n0.post(new e(sync$SyncType, sync$SyncState));
    }

    @Override // com.byd.bluetoothcall.c.a.g
    public void a() {
        EditText editText = this.k;
        if (editText != null) {
            editText.setText("");
        }
        com.byd.bluetoothcall.c.a.F().H0("");
        com.byd.bluetoothcall.c.a.F().G0("");
        com.byd.bluetoothcall.view.c.c cVar = this.g;
        if (cVar != null) {
            cVar.o("");
        }
    }

    @Override // com.byd.bluetoothcall.c.a.g
    public void b() {
        com.byd.bluetoothcall.view.d.a aVar = this.X;
        if (aVar != null && aVar.i()) {
            this.X.a();
        }
        EditText editText = this.G;
        if (editText != null) {
            editText.clearFocus();
            this.G.setEnabled(true);
            this.n0.sendEmptyMessageDelayed(10009, 10L);
        }
    }

    @Override // com.byd.bluetoothcall.c.a.g
    public void c(boolean z) {
        Log.d("WEFEDWFEWF", "SearchContacts == isSearching = " + z);
        this.O = z;
        if (z) {
            N(8);
            this.D.setVisibility(0);
            this.G.setEnabled(false);
            ImageView imageView = this.l;
            if (imageView != null) {
                imageView.setVisibility(4);
            }
            if (!isInMultiWindowMode()) {
                this.n0.sendEmptyMessageDelayed(10007, 800L);
                return;
            } else {
                this.n0.removeMessages(10008);
                this.n0.sendEmptyMessageDelayed(10008, 200L);
                return;
            }
        }
        this.n0.removeMessages(10007);
        EditText editText = this.G;
        if (editText != null) {
            editText.setText("");
            com.byd.bluetoothcall.c.a.F().V(BlueToothApplication.a().getApplicationContext(), this.G);
        }
        this.D.setVisibility(8);
        N(0);
        EditText editText2 = this.k;
        if (editText2 != null) {
            if (this.l == null || editText2.getText().length() <= 0) {
                this.l.setVisibility(4);
            } else {
                this.l.setVisibility(0);
            }
        }
    }

    @Override // c.a.a.c
    public void connectStateChange(BluetoothDevice bluetoothDevice, int i2) {
        n(i2 == 2);
        if (i2 == 2) {
            com.byd.bluetoothcall.view.c.b bVar = this.f3294d;
            if (bVar != null) {
                bVar.g(true);
            }
            com.byd.bluetoothcall.view.c.a aVar = this.f3295e;
            if (aVar != null) {
                aVar.d(true);
            }
            com.byd.bluetoothcall.view.c.e eVar = this.f;
            if (eVar != null) {
                eVar.d(true);
                return;
            }
            return;
        }
        com.byd.bluetoothcall.view.c.b bVar2 = this.f3294d;
        if (bVar2 != null) {
            bVar2.g(false);
        }
        com.byd.bluetoothcall.view.c.a aVar2 = this.f3295e;
        if (aVar2 != null) {
            aVar2.d(false);
        }
        com.byd.bluetoothcall.view.c.e eVar2 = this.f;
        if (eVar2 != null) {
            eVar2.d(false);
        }
        com.byd.bluetoothcall.view.d.a aVar3 = this.X;
        if (aVar3 == null || !aVar3.i()) {
            return;
        }
        this.X.a();
    }

    @Override // com.byd.bluetoothcall.c.a.g
    public void d() {
        com.byd.bluetoothcall.view.c.a aVar = this.f3295e;
        if (aVar != null) {
            aVar.a();
        }
        com.byd.bluetoothcall.view.c.e eVar = this.f;
        if (eVar != null) {
            eVar.a();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (com.byd.bluetoothcall.f.b.a(currentFocus, motionEvent)) {
                com.byd.bluetoothcall.c.a.F().V(BlueToothApplication.a().getApplicationContext(), currentFocus);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.byd.bluetoothcall.c.a.g
    public void e() {
        this.n0.sendEmptyMessage(10010);
    }

    @Override // com.byd.bluetoothcall.c.a.g
    public void f(int i2) {
        View view = this.Y;
        if (view != null) {
            view.setVisibility(i2);
        }
    }

    @Override // com.byd.bluetoothcall.c.a.g
    public void g(int i2, int i3) {
        com.byd.bluetoothcall.view.c.e eVar;
        com.byd.bluetoothcall.view.c.e eVar2;
        if (i3 == 1) {
            if (i2 == 8) {
                if (com.byd.bluetoothcall.c.a.F().R() == 0) {
                    com.byd.bluetoothcall.view.c.b bVar = this.f3294d;
                    if (bVar != null) {
                        bVar.n();
                        return;
                    }
                    return;
                }
                if (com.byd.bluetoothcall.c.a.F().R() == 1) {
                    com.byd.bluetoothcall.view.c.a aVar = this.f3295e;
                    if (aVar != null) {
                        aVar.i();
                        return;
                    }
                    return;
                }
                if (com.byd.bluetoothcall.c.a.F().R() != 2 || (eVar = this.f) == null) {
                    return;
                }
                eVar.i();
                return;
            }
            if (i2 != 10) {
                return;
            }
            if (com.byd.bluetoothcall.c.a.F().R() == 0) {
                com.byd.bluetoothcall.view.c.b bVar2 = this.f3294d;
                if (bVar2 != null) {
                    bVar2.o();
                    return;
                }
                return;
            }
            if (com.byd.bluetoothcall.c.a.F().R() == 1) {
                com.byd.bluetoothcall.view.c.a aVar2 = this.f3295e;
                if (aVar2 != null) {
                    aVar2.j();
                    return;
                }
                return;
            }
            if (com.byd.bluetoothcall.c.a.F().R() != 2 || (eVar2 = this.f) == null) {
                return;
            }
            eVar2.j();
        }
    }

    @Override // com.byd.bluetoothcall.c.a.g
    public void h() {
        com.byd.bluetoothcall.view.c.b bVar = this.f3294d;
        if (bVar != null) {
            bVar.a();
        }
    }

    @Override // com.byd.bluetoothcall.c.a.g
    public void i(int i2) {
        TextView textView = this.B;
        if (textView != null) {
            if (i2 <= 0) {
                textView.setVisibility(8);
                return;
            }
            if (i2 > 99) {
                textView.setText("99+");
            } else {
                textView.setText(i2 + "");
            }
            this.B.setVisibility(0);
        }
    }

    @Override // c.a.a.c
    public void initFinished() {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        com.byd.bluetoothcall.view.d.a aVar = this.X;
        if (aVar != null && aVar.i()) {
            this.X.a();
            return;
        }
        if (this.O) {
            com.byd.bluetoothcall.c.a.F().z0(false);
            return;
        }
        com.byd.bluetoothcall.c.a.F().G0("");
        com.byd.bluetoothcall.c.a.F().H0("");
        EditText editText = this.k;
        if (editText != null) {
            editText.setText("");
        }
        com.byd.bluetoothcall.c.a.F().z0(false);
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        j();
        switch (view.getId()) {
            case R.id.bt_dial_number_btn /* 2131296369 */:
                if (c.a.b.b.f0().n0() != 2) {
                    int i2 = this.U;
                    if (i2 < 10) {
                        this.U = i2 + 1;
                    } else {
                        this.T = true;
                        this.U = 0;
                    }
                    break;
                } else if (!com.byd.bluetoothcall.f.f.o(1000)) {
                    com.byd.bluetoothcall.f.f.c(this, 11);
                    if (this.k.getText().length() != 0) {
                        c.a.b.b.f0().c(this.k.getText().toString());
                        a();
                        break;
                    } else {
                        String strO = c.a.b.c.t().x().o();
                        if (!TextUtils.isEmpty(strO) && !strO.equals("unknown")) {
                            this.n = this.k.getSelectionStart();
                            Editable text = this.k.getText();
                            this.m = text;
                            text.insert(this.n, strO);
                            break;
                        }
                    }
                }
                break;
            case R.id.bt_name_text_layout /* 2131296372 */:
                com.byd.bluetoothcall.c.a.F().f();
                break;
            case R.id.dialog_cancle_btn /* 2131296485 */:
                f(8);
                break;
            case R.id.dialog_confirm_btn /* 2131296486 */:
                c.a.b.c.t().g();
                f(8);
                break;
            case R.id.go_to_connect_btn /* 2131296536 */:
                com.byd.bluetoothcall.c.a.F().f();
                break;
            case R.id.keyboard_delete /* 2131296579 */:
                this.n = this.k.getSelectionStart();
                int selectionEnd = this.k.getSelectionEnd();
                int i3 = this.n;
                if (i3 > 0 && i3 == selectionEnd) {
                    Editable text2 = this.k.getText();
                    this.m = text2;
                    int i4 = this.n;
                    text2.delete(i4 - 1, i4);
                } else if (i3 == 0 && selectionEnd > 0) {
                    Editable text3 = this.k.getText();
                    this.m = text3;
                    text3.delete(this.n, selectionEnd);
                }
                this.n = this.k.getSelectionStart();
                break;
            case R.id.keyboard_number_0 /* 2131296585 */:
                com.byd.bluetoothcall.f.f.c(this, 12);
                L("0");
                break;
            case R.id.keyboard_number_1 /* 2131296587 */:
                com.byd.bluetoothcall.f.f.c(this, 14);
                L("1");
                break;
            case R.id.keyboard_number_2 /* 2131296589 */:
                com.byd.bluetoothcall.f.f.c(this, 12);
                L("2");
                break;
            case R.id.keyboard_number_3 /* 2131296591 */:
                com.byd.bluetoothcall.f.f.c(this, 13);
                L("3");
                break;
            case R.id.keyboard_number_4 /* 2131296593 */:
                com.byd.bluetoothcall.f.f.c(this, 14);
                L("4");
                break;
            case R.id.keyboard_number_5 /* 2131296595 */:
                com.byd.bluetoothcall.f.f.c(this, 12);
                L("5");
                break;
            case R.id.keyboard_number_6 /* 2131296597 */:
                com.byd.bluetoothcall.f.f.c(this, 13);
                L("6");
                break;
            case R.id.keyboard_number_7 /* 2131296599 */:
                com.byd.bluetoothcall.f.f.c(this, 14);
                L("7");
                break;
            case R.id.keyboard_number_8 /* 2131296601 */:
                com.byd.bluetoothcall.f.f.c(this, 12);
                L("8");
                break;
            case R.id.keyboard_number_9 /* 2131296603 */:
                com.byd.bluetoothcall.f.f.c(this, 13);
                L("9");
                break;
            case R.id.keyboard_number_j /* 2131296605 */:
                com.byd.bluetoothcall.f.f.c(this, 13);
                L("#");
                break;
            case R.id.keyboard_number_x /* 2131296607 */:
                com.byd.bluetoothcall.f.f.c(this, 14);
                L("*");
                break;
            case R.id.search_clear_btn /* 2131296770 */:
                EditText editText = this.G;
                if (editText != null) {
                    editText.setText("");
                }
                break;
            case R.id.search_layout_back /* 2131296776 */:
                this.D.setVisibility(8);
                EditText editText2 = this.G;
                if (editText2 != null) {
                    editText2.setText("");
                }
                com.byd.bluetoothcall.c.a.F().z0(false);
                break;
            case R.id.tab_calllog_layout /* 2131296841 */:
                CustomViewPager customViewPager = this.i;
                if (customViewPager != null) {
                    customViewPager.N(1, false);
                }
                com.byd.bluetoothcall.c.a.F().z0(false);
                break;
            case R.id.tab_contacts_layout /* 2131296842 */:
                CustomViewPager customViewPager2 = this.i;
                if (customViewPager2 != null) {
                    customViewPager2.N(0, false);
                }
                c(com.byd.bluetoothcall.c.a.F().Z());
                break;
            case R.id.tab_dial_keyboard_layout /* 2131296843 */:
                CustomViewPager customViewPager3 = this.i;
                if (customViewPager3 != null) {
                    customViewPager3.N(3, false);
                }
                com.byd.bluetoothcall.c.a.F().z0(false);
                break;
            case R.id.tab_missedcalls_layout /* 2131296844 */:
                CustomViewPager customViewPager4 = this.i;
                if (customViewPager4 != null) {
                    customViewPager4.N(2, false);
                }
                com.byd.bluetoothcall.c.a.F().z0(false);
                break;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (com.byd.bluetoothcall.f.f.l(this) == 1) {
            setTheme(R.style.ThemeTS);
        } else {
            setTheme(R.style.ThemeTang);
        }
        I();
        if (this.n0.hasMessages(10001)) {
            this.n0.removeMessages(10001);
        }
        this.n0.sendEmptyMessageDelayed(10001, 10L);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        PhoneNumberFormattingTextWatcher phoneNumberFormattingTextWatcher;
        super.onDestroy();
        this.X = null;
        this.U = 0;
        this.T = false;
        c.a.b.b.f0().O0(this);
        c.a.b.c.t().B(this);
        c.a.b.a.K().W(this);
        com.byd.bluetoothcall.c.a.F().L0();
        this.n0.removeCallbacksAndMessages(null);
        EditText editText = this.G;
        if (editText != null) {
            editText.setCustomSelectionActionModeCallback(null);
            this.G.setCustomInsertionActionModeCallback(null);
        }
        io.reactivex.disposables.b bVar = this.P;
        if (bVar != null && !bVar.isDisposed()) {
            this.P.dispose();
        }
        io.reactivex.disposables.b bVar2 = this.Q;
        if (bVar2 != null && !bVar2.isDisposed()) {
            this.Q.dispose();
        }
        com.byd.bluetoothcall.d.a<com.byd.bluetoothcall.d.c> aVar = this.b0;
        if (aVar != null) {
            aVar.q(d.a.l.c.a.a());
        }
        com.byd.bluetoothcall.d.a<com.byd.bluetoothcall.d.c> aVar2 = this.c0;
        if (aVar2 != null) {
            aVar2.q(d.a.l.c.a.a());
        }
        EditText editText2 = this.k;
        if (editText2 == null || (phoneNumberFormattingTextWatcher = this.R) == null) {
            return;
        }
        editText2.removeTextChangedListener(phoneNumberFormattingTextWatcher);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        BydLogHelper.logD("DANDAN", "isInMultiWindowMode == " + z);
        if (this.n0.hasMessages(10001)) {
            this.n0.removeMessages(10001);
        }
        this.n0.sendEmptyMessageDelayed(10001, 10L);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("WEFEDWFEWF", "onNewIntent =============" + intent);
        if (intent != null) {
            l(intent);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.G != null) {
            com.byd.bluetoothcall.c.a.F().V(BlueToothApplication.a().getApplicationContext(), this.G);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        com.byd.bluetoothcall.c.a.F().m0(true);
        c.a.b.b.f0().L0(true);
        n(c.a.b.b.f0().n0() == 2);
        Log.d("WEFEDWFEWF", "onResume == isSearching = " + com.byd.bluetoothcall.c.a.F().Z());
        c(com.byd.bluetoothcall.c.a.F().Z());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (intent != null) {
            l(getIntent());
            intent.removeExtra("page");
            intent.removeExtra("dial_number");
            intent.removeExtra("vr_search_name");
            intent.removeExtra("vr_show_contacts_info_name");
            intent.removeExtra("vr_show_contacts_info_number");
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        com.byd.bluetoothcall.c.a.F().m0(false);
        c.a.b.b.f0().L0(false);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        com.byd.bluetoothcall.view.d.a aVar;
        com.byd.bluetoothcall.view.c.b bVar = this.f3294d;
        if (bVar != null) {
            bVar.u(this.i, z);
        }
        if (!z && (aVar = this.X) != null && aVar.i()) {
            this.X.a();
        }
        if (isInMultiWindowMode() && this.i.getCurrentItem() == 4) {
            this.i.setCurrentItem(0);
        }
        super.onWindowFocusChanged(z);
        if (z) {
            return;
        }
        this.n0.removeMessages(10007);
        this.n0.removeMessages(10008);
        EditText editText = this.G;
        if (editText != null) {
            editText.setEnabled(true);
            if (com.byd.bluetoothcall.c.a.F().d0()) {
                return;
            }
            this.n0.sendEmptyMessageDelayed(10009, 10L);
        }
    }

    @Override // c.a.a.d
    public void updateA2dpConnectState(BluetoothDevice bluetoothDevice, int i2) {
        n(c.a.b.b.f0().n0() == 2);
    }

    @Override // c.a.a.d
    public void updateA2dpConnectStateShowToast(BluetoothDevice bluetoothDevice, int i2) {
    }

    @Override // c.a.a.d
    public void updateBattery(int i2) {
    }

    @Override // c.a.a.d
    public void updateCurrentTime(long j2) {
    }

    @Override // c.a.a.d
    public void updatePlayState(int i2) {
    }

    @Override // c.a.a.d
    public void updateTitle(String str, String str2, String str3, String str4) {
    }

    @Override // c.a.a.d
    public void updateTotalTime(long j2) {
    }

    @Override // c.a.a.c
    public void update_BatLevel(int i2, BluetoothDevice bluetoothDevice) {
        int iV;
        if (c.a.b.b.f0().e0() == null || bluetoothDevice == null || !bluetoothDevice.getAddress().equals(c.a.b.b.f0().e0().getAddress())) {
            return;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= c.a.b.b.f0().q0().size()) {
                iV = -1;
                break;
            } else {
                if (bluetoothDevice.getAddress().equals(c.a.b.b.f0().q0().get(i3).f().getAddress())) {
                    iV = c.a.b.b.f0().q0().get(i3).a();
                    break;
                }
                i3++;
            }
        }
        if (iV == -1) {
            iV = c.a.b.b.f0().V();
        }
        ImageView imageView = this.x;
        if (imageView != null) {
            imageView.setVisibility(0);
            this.x.setImageLevel(iV - 1);
        }
    }
}
