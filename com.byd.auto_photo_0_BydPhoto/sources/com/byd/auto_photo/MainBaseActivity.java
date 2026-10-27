package com.byd.auto_photo;

import android.car.Car;
import android.car.VehicleAreaDoor;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.annotation.IdRes;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import b.a.h.l;
import com.byd.fragment.AlbumBaseFragment;
import com.byd.fragment.AlbumFragment;
import com.byd.fragment.AlertDialogFragment;
import com.byd.fragment.MediaDataBaseFragment;
import com.byd.fragment.MediaFileGridBaseFragment;
import com.byd.fragment.MediaFileGridFragment;
import com.byd.provider.PhotoProvider;
import com.byd.theme.ThemeActivity;
import com.byd.util.j;
import com.byd.util.k;
import com.byd.util.m;
import com.byd.util.n;
import com.byd.util.p;
import com.byd.util.r;
import com.byd.util.u;
import com.byd.util.w;
import com.byd.util.x;
import com.byd.view.TabGroup;
import com.byd.view.TabItem;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public abstract class MainBaseActivity extends ThemeActivity implements TabGroup.OnCheckedChangeListener {
    private String A;
    private String B;
    private u C;
    private b.a.k.c D;
    protected k E;
    private int F;
    private Uri H;
    protected h I;
    private i J;
    private boolean K;
    private AlertDialogFragment L;
    protected boolean M;
    private f N;
    protected MediaFileGridBaseFragment O;
    private e P;
    protected volatile MediaDataBaseFragment[] q;
    protected ViewGroup s;
    protected ViewGroup t;
    protected ViewGroup u;
    protected TabGroup v;
    private g w;
    protected TabItem x;
    protected TabItem y;
    private String z;
    protected int r = -1;
    protected boolean G = true;

    class a extends Thread {
        a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            com.byd.share.a.s(MainBaseActivity.this).m();
        }
    }

    class b implements AlertDialogFragment.OnAlertDialogButtonClick {
        b() {
        }

        @Override // com.byd.fragment.AlertDialogFragment.OnAlertDialogButtonClick
        public boolean onClick(int i) {
            if (i != 0) {
                if (i == 1) {
                    w.c("no permission , kill process");
                    Process.killProcess(Process.myPid());
                }
            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(MainBaseActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    w.c("PhotoMain to request permission");
                    return MainBaseActivity.this.Z0();
                }
                MainBaseActivity mainBaseActivity = MainBaseActivity.this;
                mainBaseActivity.requestPermissions(mainBaseActivity.W(), 100);
            }
            return true;
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MainBaseActivity.this.Q0();
        }
    }

    class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f1115a;

        d(View view) {
            this.f1115a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1115a.setEnabled(true);
        }
    }

    private class e extends BroadcastReceiver {
        private e() {
        }

        /* synthetic */ e(MainBaseActivity mainBaseActivity, a aVar) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction())) {
                w.c("Photo MainActivity receive ACTION_CLOSE_SYSTEM_DIALOGS : " + intent.getStringExtra("reason"));
            }
        }
    }

    private static class f extends b.a.d<MainBaseActivity> {
        public f(MainBaseActivity mainBaseActivity) {
            super(mainBaseActivity);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(MainBaseActivity mainBaseActivity, Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 2 && !com.byd.util.e.C(Environment.getExternalStorageDirectory().getPath(), 5)) {
                    w.f(mainBaseActivity, R.string.space_is_insufficient, 1);
                    return;
                }
                return;
            }
            Object[] objArr = (Object[]) message.obj;
            w.c("PhotoMainActivity do media data change : " + objArr[0] + " " + objArr[1]);
            mainBaseActivity.L0((Uri) objArr[0], (String) objArr[1]);
        }
    }

    private class g extends BroadcastReceiver {
        private g() {
        }

        /* synthetic */ g(MainBaseActivity mainBaseActivity, a aVar) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.byd.ACTION_ALBUM_CLOSE".equals(action)) {
                MainBaseActivity.this.finish();
                return;
            }
            if ("com.byd.ACTION_BURSTSHOOT_CHANGE".equals(action)) {
                MainBaseActivity mainBaseActivity = MainBaseActivity.this;
                if (mainBaseActivity.r != 1 || mainBaseActivity.q[0] == null) {
                    return;
                }
                MainBaseActivity.this.q[0].onDataChange(null, null);
            }
        }
    }

    private class h extends ContentObserver {
        public h(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            long id;
            Cursor cursorQuery;
            super.onChange(z, uri);
            if (uri == null) {
                return;
            }
            String strQ = n.q(MainBaseActivity.this, uri);
            w.c("Photo MainActivity onChange path : " + strQ + " " + uri);
            if (strQ != null) {
                int iDelete = MainBaseActivity.this.getContentResolver().delete(MainBaseActivity.this.H, "path=?", new String[]{strQ});
                if (iDelete > 0) {
                    w.c("photo opt data change : " + iDelete);
                    MainBaseActivity mainBaseActivity = MainBaseActivity.this;
                    if (mainBaseActivity.r == 1 && strQ.startsWith(mainBaseActivity.A)) {
                        MainBaseActivity.this.q[0].setDataChange(true);
                        return;
                    }
                    return;
                }
            } else {
                try {
                    String query = uri.getQuery();
                    if (query != null && !"".equals(query)) {
                        return;
                    } else {
                        id = ContentUris.parseId(uri);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    id = -1;
                }
                if (id != -1 && (cursorQuery = MainBaseActivity.this.getContentResolver().query(MainBaseActivity.this.H, null, "extras=?", new String[]{String.valueOf(id)}, null)) != null) {
                    if (cursorQuery.getCount() > 0) {
                        w.c("Photo MainActivity onChange -->is Deleting file : return");
                        cursorQuery.close();
                        return;
                    }
                    cursorQuery.close();
                }
                if (b.a.k.g.f(MainBaseActivity.this).g()) {
                    w.c("Photo MainActivity onChange -->isDelFromMediaStore : return");
                    return;
                } else if ("content://media/external".equals(uri.toString()) && Build.VERSION.SDK_INT > 28) {
                    w.c("deleting , return");
                    return;
                }
            }
            if (j.e(MainBaseActivity.this).l() && (strQ == null || j.e(MainBaseActivity.this).d(strQ) != 0)) {
                w.c("external card scanning , return");
            } else {
                MainBaseActivity.this.N.removeMessages(1);
                MainBaseActivity.this.N.sendMessageDelayed(MainBaseActivity.this.N.obtainMessage(1, new Object[]{uri, strQ}), 750L);
            }
        }
    }

    private class i extends ContentObserver {
        public i(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            int i;
            String string;
            String string2;
            if (uri == null) {
                return;
            }
            w.c("PhotoContentObserver onChange : " + uri);
            MainBaseActivity.this.N.removeMessages(1);
            Cursor cursorQuery = MainBaseActivity.this.getContentResolver().query(uri, new String[]{"path", "opt", "extras"}, null, null, null);
            if (cursorQuery != null) {
                if (cursorQuery.moveToFirst()) {
                    string = cursorQuery.getString(0);
                    i = cursorQuery.getInt(1);
                    string2 = cursorQuery.getString(2);
                    w.c("onChange file path:" + string + " opt:" + i + " extras:" + string2);
                } else {
                    i = -1;
                    string = null;
                    string2 = null;
                }
                cursorQuery.close();
            } else {
                i = -1;
                string = null;
                string2 = null;
            }
            if (string == null) {
                return;
            }
            if (i == 0) {
                MainBaseActivity.this.N.sendMessageDelayed(MainBaseActivity.this.N.obtainMessage(1, new Object[]{Uri.fromFile(new File(string)), string}), 750L);
                return;
            }
            if (i == 1) {
                if (string.startsWith(MainBaseActivity.this.z) || string.startsWith(MainBaseActivity.this.A)) {
                    if (string2 == null) {
                        MainBaseActivity.this.getContentResolver().delete(uri, null, null);
                    }
                    MainBaseActivity mainBaseActivity = MainBaseActivity.this;
                    if (mainBaseActivity.r != 1 || mainBaseActivity.q[0].isDataChange()) {
                        return;
                    }
                    MainBaseActivity.this.q[0].setDataChange(true);
                }
            }
        }
    }

    private void E0(int i2, String str, int i3) {
        if (this.O == null) {
            MediaFileGridFragment mediaFileGridFragment = new MediaFileGridFragment();
            this.O = mediaFileGridFragment;
            mediaFileGridFragment.setSearchSource(i2);
            this.O.setShowLevel(1);
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("search_word", str);
            bundle.putInt("search_type", i3);
            this.O.setArguments(bundle);
            w.c("search by voice");
            j0(this.O, true);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.O.setSearchSource(i2);
            this.O.startSearchByVoice(str, i3);
        }
    }

    private void F0() {
        w.c("init by intent:" + this.r);
        Intent intent = getIntent();
        if ((intent.getFlags() & 1048576) != 0) {
            w.c("Photo MainActivity , from recent");
            W0(0);
            return;
        }
        if (r0(intent.getExtras()) > 0) {
            w.c("has voice code , return");
            return;
        }
        if (intent.hasExtra("index")) {
            U0(intent.getExtras());
            return;
        }
        if (this.r <= -1 || !intent.hasExtra("fullscreen_path")) {
            if (this.r == -1) {
                W0(0);
                return;
            }
            return;
        }
        String stringExtra = intent.getStringExtra("fullscreen_path");
        MediaFileGridBaseFragment mediaFileGridBaseFragment = this.O;
        if (mediaFileGridBaseFragment == null) {
            if (this.r != 0) {
                return;
            } else {
                mediaFileGridBaseFragment = (MediaFileGridBaseFragment) this.q[this.r];
            }
        }
        mediaFileGridBaseFragment.setFocusBean(stringExtra);
    }

    private void G0() {
        this.z = com.byd.util.g.g(this);
        this.A = com.byd.util.g.k(this);
        l.m(this);
        F0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L0(Uri uri, String str) {
        if (this.q == null) {
            return;
        }
        int i2 = this.r;
        if (i2 == 0) {
            if (this.q[0] == null) {
                return;
            }
            if (this.q[0] instanceof AlbumBaseFragment) {
                this.q[0].onDataChange(uri, str);
                return;
            }
            if (str == null || str.startsWith(this.z) || str.startsWith(this.A)) {
                this.q[0].onDataChange(uri, str);
            }
            if (this.q[1] != null) {
                ((AlbumBaseFragment) this.q[1]).onDataChange(uri, str);
                return;
            } else {
                if (this.D.H()) {
                    this.D.q(this, str);
                    return;
                }
                return;
            }
        }
        if (i2 == 1 && this.q[1] != null) {
            this.q[1].onDataChange(uri, str);
            w.c("onMediaStoreDataChange path : " + str);
            if (this.q[0] != null && (str == null || str.startsWith(this.z) || str.startsWith(this.A))) {
                this.q[0].setDataChange(true);
            }
            MediaFileGridBaseFragment mediaFileGridBaseFragmentB0 = B0();
            if (mediaFileGridBaseFragmentB0 == null) {
                return;
            }
            if (str == null) {
                if (mediaFileGridBaseFragmentB0.isPause()) {
                    mediaFileGridBaseFragmentB0.setDataChange(true);
                    return;
                }
                if ((mediaFileGridBaseFragmentB0.getPath() != null && mediaFileGridBaseFragmentB0.getPath().equals(this.A)) || mediaFileGridBaseFragmentB0.getType() == 1 || ((uri.toString().contains("/video") || "content://media/".equals(uri.toString())) && mediaFileGridBaseFragmentB0.getType() == 2)) {
                    mediaFileGridBaseFragmentB0.onDataChange(uri, null);
                    return;
                }
                return;
            }
            String strA = m.a(str);
            if (!TextUtils.isEmpty(strA)) {
                if (m.d(strA) && mediaFileGridBaseFragmentB0.getType() == 1) {
                    return;
                }
                if (m.b(strA) && mediaFileGridBaseFragmentB0.getType() == 2) {
                    return;
                }
            }
            String parent = new File(str).getParent();
            if ((mediaFileGridBaseFragmentB0.getPath() != null && (mediaFileGridBaseFragmentB0.getPath().equals(parent) || "/collect".equals(mediaFileGridBaseFragmentB0.getPath()))) || mediaFileGridBaseFragmentB0.getType() == 1 || mediaFileGridBaseFragmentB0.getType() == 2) {
                mediaFileGridBaseFragmentB0.onDataChange(uri, str);
            }
        }
    }

    private void P0() {
        if (this.q == null) {
            w.c("Photo MainActivity no fragment attach!!!");
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        MediaFileGridBaseFragment mediaFileGridBaseFragment = this.O;
        if (mediaFileGridBaseFragment != null) {
            mediaFileGridBaseFragment.setReleaseOnDestroy(false);
            fragmentTransactionBeginTransaction.remove(this.O);
            this.t.removeAllViews();
            this.t.setVisibility(8);
            this.u.removeAllViews();
            this.u.setVisibility(8);
        }
        for (int i2 = 0; i2 < this.q.length; i2++) {
            if (this.q[i2] != null) {
                this.q[i2].setReleaseOnDestroy(false);
                fragmentTransactionBeginTransaction.remove(this.q[i2]);
                w.c("remove fragment : " + this.q[i2].getTag());
            }
        }
        fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
        ViewGroup viewGroup = this.s;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
    }

    private void R0() {
        int i2;
        try {
            int iD0 = D0();
            ArrayList<Fragment> arrayList = new ArrayList(iD0);
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            for (int i3 = 0; i3 < iD0; i3++) {
                Fragment fragmentFindFragmentByTag = supportFragmentManager.findFragmentByTag("photo_tab_" + i3);
                if (fragmentFindFragmentByTag != null) {
                    arrayList.add(fragmentFindFragmentByTag);
                } else if (this.q != null && this.q.length > i3 && this.q[i3] != null) {
                    fragmentFindFragmentByTag = this.q[i3];
                    arrayList.add(fragmentFindFragmentByTag);
                }
            }
            Fragment fragmentFindFragmentById = supportFragmentManager.findFragmentById(R.id.content_second_level);
            if (fragmentFindFragmentById != null) {
                arrayList.add(fragmentFindFragmentById);
            }
            Fragment fragmentFindFragmentById2 = supportFragmentManager.findFragmentById(R.id.content_second_full_level);
            if (fragmentFindFragmentById2 != null) {
                arrayList.add(fragmentFindFragmentById2);
            }
            if (arrayList.size() > 0) {
                FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
                i2 = 0;
                for (Fragment fragment : arrayList) {
                    try {
                        fragmentTransactionBeginTransaction.remove(fragment);
                        i2++;
                        w.c("resetContainer -> reset container remove : " + fragment);
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                    }
                }
                fragmentTransactionBeginTransaction.commitNow();
                arrayList.clear();
            } else {
                i2 = 0;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 0;
        }
        ViewGroup viewGroup = this.s;
        if (viewGroup != null && viewGroup.getChildCount() > 0) {
            this.s.removeAllViews();
        }
        this.q = null;
        this.r = -1;
        TabGroup tabGroup = this.v;
        if (tabGroup == null || i2 <= 0 || tabGroup.getCheckedId() == R.id.tab_image) {
            return;
        }
        w.c("resetContainer -> reset tab index !!!");
        this.v.setOnCheckedChangeListener(null);
        q0(0);
        this.v.setOnCheckedChangeListener(this);
    }

    private boolean U0(Bundle bundle) {
        int i2 = bundle.getInt("index", 0);
        int i3 = i2 != -1 ? i2 : 0;
        w.c("photo show index : " + i3);
        Q0();
        return W0(i3);
    }

    private boolean m0() {
        if (this.r == -1) {
            return false;
        }
        MediaDataBaseFragment mediaDataBaseFragment = this.q[this.r];
        if (mediaDataBaseFragment instanceof MediaFileGridBaseFragment) {
            if (((MediaFileGridBaseFragment) mediaDataBaseFragment).cancelSelectMode()) {
                return true;
            }
        } else if ((mediaDataBaseFragment instanceof AlbumBaseFragment) && ((AlbumBaseFragment) mediaDataBaseFragment).cancelSelectMode()) {
            return true;
        }
        return false;
    }

    private void n0(int i2, FragmentManager fragmentManager, FragmentTransaction fragmentTransaction) {
        StringBuilder sb;
        String str;
        int iD0 = D0();
        for (int i3 = 0; i3 < iD0; i3++) {
            if (i3 != i2) {
                Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag("photo_tab_" + i3);
                if (fragmentFindFragmentByTag != null) {
                    if (this.q[i3] == null) {
                        fragmentTransaction.remove(fragmentFindFragmentByTag);
                        sb = new StringBuilder();
                        str = "remove no in record fragment : ";
                    } else {
                        fragmentTransaction.detach(fragmentFindFragmentByTag);
                        this.q[i3] = (MediaDataBaseFragment) fragmentFindFragmentByTag;
                        sb = new StringBuilder();
                        str = "detach no in record fragment : ";
                    }
                    sb.append(str);
                    sb.append(fragmentFindFragmentByTag);
                    w.c(sb.toString());
                }
            }
        }
    }

    private void o0() {
        this.N.removeMessages(2);
        this.N.sendEmptyMessageDelayed(2, 500L);
    }

    private int r0(Bundle bundle) {
        String string;
        int i2;
        if (bundle == null || (string = bundle.getString("pkgName")) == null) {
            return 0;
        }
        if (!com.byd.util.e.I(string)) {
            w.c("Photo function no support for :" + string);
            return 2;
        }
        if (bundle.containsKey("index")) {
            x.u(this, "open_album", U0(bundle) ? 1 : 0);
            return 1;
        }
        if (!bundle.containsKey("search_word")) {
            return 0;
        }
        String string2 = bundle.getString("search_word");
        int i3 = bundle.getInt("search_source", 1);
        int i4 = bundle.getInt("search_type", 0);
        w.c("photo search : " + string2 + " source:" + i3 + " type:" + i4);
        if (this.q != null && (i2 = this.r) >= 0 && i2 < this.q.length && this.q[this.r] != null) {
            this.q[this.r].dismissAllDialog();
        }
        W0(i3);
        Y0(i3, string2, i4);
        return 1;
    }

    private void v0(View view, int i2) {
        view.setEnabled(false);
        this.N.postDelayed(new d(view), i2);
    }

    public u A0() {
        return this.C;
    }

    public MediaFileGridBaseFragment B0() {
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(R.id.content_second_level);
        if (fragmentFindFragmentById != null && (fragmentFindFragmentById instanceof MediaFileGridBaseFragment)) {
            return (MediaFileGridBaseFragment) fragmentFindFragmentById;
        }
        Fragment fragmentFindFragmentById2 = getSupportFragmentManager().findFragmentById(R.id.content_second_full_level);
        if (fragmentFindFragmentById2 == null || !(fragmentFindFragmentById2 instanceof MediaFileGridBaseFragment)) {
            return null;
        }
        return (MediaFileGridBaseFragment) fragmentFindFragmentById2;
    }

    public MediaFileGridBaseFragment C0() {
        return this.O;
    }

    protected int D0() {
        return 2;
    }

    protected void H0() {
        if (this.G) {
            this.x = (TabItem) findViewById(R.id.tab_image);
            this.y = (TabItem) findViewById(R.id.tab_album);
        }
    }

    protected boolean I0() {
        for (String str : x.e((PhotoBaseApp) getApplication())) {
            if (checkSelfPermission(str) != 0) {
                w.c(str + " -> not grant");
                return false;
            }
        }
        return true;
    }

    public boolean J0() {
        return (getSupportFragmentManager().findFragmentById(R.id.content_second_level) == null && getSupportFragmentManager().findFragmentById(R.id.content_second_full_level) == null) ? false : true;
    }

    public boolean K0(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        int height = view.getHeight() + i3;
        int width = view.getWidth() + i2;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        return x <= i2 || x >= width || y <= i3 || y >= height;
    }

    protected void M0(int i2) {
    }

    @Override // com.byd.theme.ThemeActivity
    protected void N() {
        w.c("PhotoBaseMainActivity iniUI");
        setContentView(z0());
        H0();
        this.t = (ViewGroup) findViewById(R.id.content_second_level);
        this.u = (ViewGroup) findViewById(R.id.content_second_full_level);
        this.s = (ViewGroup) findViewById(R.id.content);
        if (this.G) {
            TabGroup tabGroup = (TabGroup) findViewById(R.id.tab_group);
            this.v = tabGroup;
            tabGroup.setOnCheckedChangeListener(this);
        }
        if (this.O != null) {
            w.c("PhotoMainActivity resume second level page");
            Bundle arguments = this.O.getArguments();
            if (arguments == null) {
                arguments = new Bundle();
            }
            arguments.putInt("soft_state", this.F);
            this.O.setArguments(arguments);
            j0(this.O, this.K);
        }
        w.c("Photo MainActivity initUi");
        this.F = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0031 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void N0(android.view.View r3, int r4) {
        /*
            r2 = this;
            r0 = 750(0x2ee, float:1.051E-42)
            r2.v0(r3, r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = "onCheckedChanged : "
            r3.append(r0)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.byd.util.w.c(r3)
            r3 = 2131231077(0x7f080165, float:1.8078225E38)
            if (r4 == r3) goto L26
            r3 = 2131231079(0x7f080167, float:1.8078229E38)
            if (r4 == r3) goto L24
            goto L2d
        L24:
            r3 = 0
            goto L27
        L26:
            r3 = 1
        L27:
            r2.V0(r3)
            r2.M0(r3)
        L2d:
            com.byd.fragment.MediaFileGridBaseFragment r3 = r2.O
            if (r3 != 0) goto L32
            return
        L32:
            com.byd.auto_photo.MainBaseActivity$f r3 = r2.N
            if (r3 != 0) goto L3a
            r2.Q0()
            return
        L3a:
            com.byd.auto_photo.MainBaseActivity$c r4 = new com.byd.auto_photo.MainBaseActivity$c
            r4.<init>()
            r0 = 200(0xc8, double:9.9E-322)
            r3.postDelayed(r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_photo.MainBaseActivity.N0(android.view.View, int):void");
    }

    public void O0(int i2) {
        this.F = i2;
        w.c("PhotoMainActivity recordSoftInputState : " + i2);
    }

    public void Q0() {
        if (this.O != null) {
            s0();
            this.O.cancelSelectMode();
            this.O.onFragmentExit();
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.remove(this.O);
            fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
            this.O = null;
            w.c("MainBaseActivity removeSecondPageFragment -> " + this.r);
            if (this.r == 0 && (this.q[0] instanceof MediaFileGridBaseFragment)) {
                ((MediaFileGridBaseFragment) this.q[this.r]).onSecondLevelRemove();
            }
        }
        ViewGroup viewGroup = this.t;
        if (viewGroup != null && viewGroup.getVisibility() == 0) {
            this.t.removeAllViews();
            this.t.setVisibility(8);
        }
        ViewGroup viewGroup2 = this.u;
        if (viewGroup2 != null && viewGroup2.getVisibility() == 0) {
            this.u.removeAllViews();
            this.u.setVisibility(8);
            TabGroup tabGroup = this.v;
            if (tabGroup != null) {
                tabGroup.setVisibility(0);
            }
        }
        ViewGroup viewGroup3 = this.s;
        if (viewGroup3 != null) {
            viewGroup3.setVisibility(0);
        }
    }

    @Override // com.byd.theme.ThemeActivity
    protected void R() {
        w.c("==Photo Main onDeniedPermission");
        if (I0()) {
            S();
            return;
        }
        w.c("Photo App --> permission not grant");
        setTheme(K((getResources().getConfiguration().uiMode & 48) == 32));
        X0();
    }

    @Override // com.byd.theme.ThemeActivity
    protected void S() {
        w.c("==Photo Main onGrantedPermission==");
        b.a.k.h.e(this).i();
        super.S();
        w0();
        o0();
        k kVarC = k.c(this);
        this.E = kVarC;
        kVarC.j();
        this.E.d();
        this.s = (ViewGroup) findViewById(R.id.content);
        R0();
        this.r = -1;
        if (this.q == null) {
            this.q = new MediaDataBaseFragment[D0()];
        }
        G0();
        this.D = b.a.k.c.D(this);
        new a().start();
        com.byd.util.e.d(this);
    }

    public void S0(String str) {
        this.B = str;
    }

    public void T0(u uVar) {
        this.C = uVar;
    }

    @Override // com.byd.theme.ThemeActivity
    protected void U(Configuration configuration) {
        if (this.q != null && I0()) {
            w.c("PhotoMainActivity onNormalConfigurationChanged : " + this.F);
            x0();
        }
    }

    protected synchronized void V0(int i2) {
        String str;
        w.c("Photo show fragment : " + i2 + " current:" + this.r);
        if (this.q == null) {
            return;
        }
        if (com.byd.util.e.E()) {
            moveTaskToBack(true);
            return;
        }
        synchronized (this) {
            try {
                if (i2 != this.r) {
                    FragmentManager supportFragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
                    if (this.r != -1) {
                        m0();
                        fragmentTransactionBeginTransaction.detach(this.q[this.r]);
                        w.c("0->detach : " + this.q[this.r].getTag());
                    } else {
                        n0(i2, supportFragmentManager, fragmentTransactionBeginTransaction);
                    }
                    String str2 = "photo_tab_" + i2;
                    Fragment fragmentFindFragmentByTag = supportFragmentManager.findFragmentByTag(str2);
                    if (this.q[i2] == null) {
                        if (fragmentFindFragmentByTag != null) {
                            w.c("target fragment exist:" + fragmentFindFragmentByTag);
                            fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag);
                            w.c("2->remove : " + str2);
                        }
                        if (this.q[i2] == null) {
                            this.q[i2] = u0(i2);
                            fragmentTransactionBeginTransaction.add(R.id.content, this.q[i2], str2);
                            str = "3->add fragment:" + str2;
                        }
                        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
                        this.r = i2;
                        w.c("Photo show , children : " + this.s.getChildCount());
                    } else if (fragmentFindFragmentByTag != null && fragmentFindFragmentByTag.isDetached() && this.q[i2] == fragmentFindFragmentByTag) {
                        fragmentTransactionBeginTransaction.attach(fragmentFindFragmentByTag);
                        str = "1->attach : " + str2;
                    } else {
                        fragmentTransactionBeginTransaction.add(R.id.content, this.q[i2], str2);
                        str = "1->add fragment : " + str2;
                    }
                    w.c(str);
                    fragmentTransactionBeginTransaction.commitAllowingStateLoss();
                    this.r = i2;
                    w.c("Photo show , children : " + this.s.getChildCount());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                w.c("PhotoMainActivity show fragment exception : " + e2.getMessage());
            }
        }
    }

    @Override // com.byd.theme.ThemeActivity
    protected String[] W() {
        boolean zG = com.byd.util.e.G(this);
        LinkedList linkedList = new LinkedList(Arrays.asList(com.byd.util.e.q(((PhotoBaseApp) getApplication()).c())));
        if (Build.VERSION.SDK_INT >= 29 && !zG && getResources().getBoolean(R.bool.support_location)) {
            linkedList.add("android.permission.ACCESS_MEDIA_LOCATION");
        }
        if (getResources().getInteger(R.integer.support_event_upload) == 1 && !zG) {
            linkedList.add(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        }
        if (zG) {
            linkedList.add(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
        }
        boolean z = getResources().getInteger(R.integer.support_share_more_screen) == 1;
        int iQ = com.byd.share.a.q(this);
        if (z || (getResources().getInteger(R.integer.support_share_qr_code) == 1 && iQ != 0 && iQ != 1)) {
            linkedList.add(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        }
        String[] strArr = new String[linkedList.size()];
        linkedList.toArray(strArr);
        return strArr;
    }

    protected boolean W0(int i2) {
        String str;
        if (this.r == i2) {
            str = "showFragmentAndCheckTab index same";
        } else if (this.q == null) {
            str = "showFragmentAndCheckTab fs is null";
        } else {
            if (i2 <= this.q.length - 1) {
                if (this.G) {
                    v0(this.v, 1200);
                    V0(i2);
                    this.v.setOnCheckedChangeListener(null);
                    q0(i2);
                    this.v.setOnCheckedChangeListener(this);
                } else {
                    V0(i2);
                }
                return true;
            }
            str = "showFragmentAndCheckTab show index too large";
        }
        w.c(str);
        return false;
    }

    protected void X0() {
        if (this.L == null) {
            AlertDialogFragment alertDialogFragmentNewInstance = AlertDialogFragment.newInstance(R.string.no_permission_title, R.string.no_permission_content, R.string.no_permission_request, 0);
            this.L = alertDialogFragmentNewInstance;
            alertDialogFragmentNewInstance.setOnAlertDialogButtonClick(new b());
        }
        this.L.setCancelable(false);
        t0(this.L);
        this.L.show(getSupportFragmentManager(), "no_permission");
    }

    public void Y0(int i2, String str, int i3) {
        MediaFileGridBaseFragment mediaFileGridBaseFragment = this.O;
        if (mediaFileGridBaseFragment != null && mediaFileGridBaseFragment.getSearchSource() == 10 && (this.t.getVisibility() == 0 || this.u.getVisibility() == 0)) {
            Q0();
        }
        Integer num = com.byd.auto_photo.a.f1178a;
        if ((num.intValue() != 2 && this.u.getVisibility() != 0) || (num.intValue() == 2 && this.t.getVisibility() != 0)) {
            E0(i2, str, i3);
        } else {
            this.O.setSearchSource(i2);
            this.O.startSearchByVoice(str, i3);
        }
    }

    protected boolean Z0() {
        try {
            this.M = true;
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.addFlags(VehicleAreaDoor.DOOR_HOOD);
            intent.setData(Uri.fromParts(Car.PACKAGE_SERVICE, getApplication().getPackageName(), null));
            startActivity(intent);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            this.M = false;
            w.e(this, R.string.permission_denied_and_finish);
            return false;
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        InputMethodManager inputMethodManager;
        if (motionEvent.getAction() != 0) {
            if (getWindow().superDispatchTouchEvent(motionEvent)) {
                return true;
            }
            return super.dispatchTouchEvent(motionEvent);
        }
        View currentFocus = getCurrentFocus();
        if (K0(currentFocus, motionEvent) && (inputMethodManager = (InputMethodManager) getSystemService("input_method")) != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void j0(MediaFileGridBaseFragment mediaFileGridBaseFragment, boolean z) {
        k0(mediaFileGridBaseFragment, z, false);
    }

    public void k0(MediaFileGridBaseFragment mediaFileGridBaseFragment, boolean z, boolean z2) {
        this.K = z;
        this.s.setVisibility(4);
        this.O = mediaFileGridBaseFragment;
        if (com.byd.util.e.E()) {
            moveTaskToBack(true);
            return;
        }
        w.c("addSecondLevelPage : " + mediaFileGridBaseFragment.toString());
        try {
            if ((com.byd.auto_photo.a.f1178a.intValue() == 2 || !z) && !z2) {
                if (this.t.getVisibility() != 0) {
                    this.t.removeAllViews();
                    this.t.setVisibility(0);
                    FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionBeginTransaction.add(R.id.content_second_level, mediaFileGridBaseFragment);
                    fragmentTransactionBeginTransaction.commitAllowingStateLoss();
                    return;
                }
                return;
            }
            TabGroup tabGroup = this.v;
            if (tabGroup != null) {
                tabGroup.setVisibility(4);
            }
            if (this.u.getVisibility() != 0) {
                this.u.removeAllViews();
                this.u.setVisibility(0);
                FragmentTransaction fragmentTransactionBeginTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransactionBeginTransaction2.add(R.id.content_second_full_level, mediaFileGridBaseFragment);
                fragmentTransactionBeginTransaction2.commitAllowingStateLoss();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            this.O = null;
            w.c("addSecondLevelPage exception : " + e2.getMessage());
        }
    }

    public void l0() {
        if (this.q[0] != null) {
            this.q[0].setDataChange(true);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        ViewGroup viewGroup;
        w.c("PhotoMainActivity onBackPress");
        ViewGroup viewGroup2 = this.t;
        if ((viewGroup2 == null || viewGroup2.getVisibility() != 0) && ((viewGroup = this.u) == null || viewGroup.getVisibility() != 0)) {
            if (m0()) {
                return;
            }
            finish();
        } else {
            if (this.O.cancelSelectMode()) {
                return;
            }
            Q0();
        }
    }

    public void onCheckedChanged(TabGroup tabGroup, @IdRes int i2) {
        N0(tabGroup, i2);
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        w.c("Photo MainActivity onCreate " + hashCode() + " " + Process.myPid());
        this.N = new f(this);
        b.a.f.a.e().g(this);
        super.onCreate(bundle);
        a aVar = null;
        this.w = new g(this, aVar);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.byd.ACTION_ALBUM_CLOSE");
        intentFilter.addAction("com.byd.ACTION_BURSTSHOOT_CHANGE");
        LocalBroadcastManager.getInstance(this).registerReceiver(this.w, intentFilter);
        e eVar = new e(this, aVar);
        this.P = eVar;
        registerReceiver(eVar, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        this.H = Uri.parse("content://" + getApplicationInfo().packageName + "/opt");
        this.J = new i(null);
        getContentResolver().registerContentObserver(this.H, true, this.J);
        this.I = new h(null);
        getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, this.I);
        getContentResolver().registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, this.I);
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        Q0();
        b.a.f.a.e().i(this);
        getContentResolver().unregisterContentObserver(this.J);
        getContentResolver().unregisterContentObserver(this.I);
        this.I = null;
        if (this.q != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            for (int i2 = 0; i2 < this.q.length; i2++) {
                if (this.q[i2] != null) {
                    this.q[i2].setReleaseOnDestroy(true);
                    fragmentTransactionBeginTransaction.remove(this.q[i2]);
                    this.q[i2] = null;
                }
            }
            fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
            this.q = null;
            this.r = -1;
        }
        k kVar = this.E;
        if (kVar != null) {
            kVar.k();
        }
        super.onDestroy();
        TabGroup tabGroup = this.v;
        if (tabGroup != null) {
            tabGroup.setOnCheckedChangeListener(null);
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.w);
        this.w = null;
        unregisterReceiver(this.P);
        this.P = null;
        w.c("Photo MainActivity onDestroy " + hashCode());
        if (this.g.d()) {
            this.N.removeCallbacksAndMessages(null);
            w.c("app exit , release source->" + com.byd.util.e.g(this));
            com.byd.taskmanager.a.e().g();
            b.a.k.c.D(this).L();
            com.byd.util.f.k(this).s();
            l.m(this).r();
            r.h().j();
            com.byd.util.e.K();
            getContentResolver().delete(this.H, null, null);
            if (p.f()) {
                p.d(this).i();
            }
            com.byd.util.e.e();
            if (com.byd.share.c.y()) {
                com.byd.share.c.w(this).C();
            }
            this.N.release();
            k kVar2 = this.E;
            if (kVar2 != null) {
                kVar2.e();
                this.E = null;
            }
            this.N = null;
        }
        this.J = null;
    }

    @b.a.f.b(threadMode = 1)
    public void onEventMsg(Bundle bundle) {
        w.c("PhotoActivity onEventMsg :" + bundle.toString());
        r0(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        w.c("Photo MainActivity onNewIntent " + hashCode());
        if (I0()) {
            if (this.s == null) {
                N();
                return;
            } else {
                F0();
                return;
            }
        }
        AlertDialogFragment alertDialogFragment = this.L;
        if (alertDialogFragment != null && alertDialogFragment.isShowing()) {
            w.c("no permission dialog is showing onNewIntent");
            return;
        }
        String[] strArrW = W();
        if (strArrW != null) {
            requestPermissions(strArrW, 100);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.N.removeMessages(2);
        w.c("Photo Activity onPause");
    }

    @Override // com.byd.theme.ThemeActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        w.c("Photo Activity onResume : " + this.M);
        p0();
        if (I0()) {
            o0();
        }
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        PhotoProvider.f(this, true);
        super.onStart();
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        PhotoProvider.f(this, false);
    }

    protected void p0() {
        if (this.M) {
            this.M = false;
            if (I0()) {
                S();
            } else {
                X0();
            }
        }
    }

    protected void q0(int i2) {
        TabGroup tabGroup;
        int i3;
        if (i2 == 0) {
            tabGroup = this.v;
            i3 = R.id.tab_image;
        } else {
            if (i2 != 1) {
                return;
            }
            tabGroup = this.v;
            i3 = R.id.tab_album;
        }
        tabGroup.check(i3);
    }

    public void s0() {
        w.c("==clearSearchHistory==");
        if (this.B != null) {
            this.B = null;
        }
        u uVar = this.C;
        if (uVar != null) {
            uVar.a();
            this.C = null;
        }
    }

    protected void t0(AlertDialogFragment alertDialogFragment) {
        alertDialogFragment.setContentGravity(16);
    }

    protected synchronized MediaDataBaseFragment u0(int i2) {
        if (this.q[i2] != null) {
            w.c("createFragment : " + i2 + " , but exist");
            return this.q[i2];
        }
        w.c("createFragment : " + i2);
        if (i2 == 0) {
            return new MediaFileGridFragment();
        }
        if (i2 != 1) {
            return null;
        }
        return new AlbumFragment();
    }

    protected void w0() {
        AlertDialogFragment alertDialogFragment = this.L;
        if (alertDialogFragment != null) {
            if (alertDialogFragment.isShowing()) {
                this.L.dismissAllowingStateLoss();
            }
            this.L = null;
        }
    }

    protected void x0() {
        P0();
        N();
        int i2 = this.r;
        this.r = -1;
        W0(i2);
    }

    public String y0() {
        return this.B;
    }

    protected int z0() {
        return R.layout.activity_main_photo;
    }
}
