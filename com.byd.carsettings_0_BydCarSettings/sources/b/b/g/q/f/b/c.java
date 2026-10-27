package b.b.g.q.f.b;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.content.res.Resources;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.ActivityChooserModel;
import b.b.g.r.z;
import b.b.m.f.d.d;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.BydAppInfo;
import com.byd.carsettings.model.dilink.ChartData;
import com.byd.carsettings.ui.activity.AppDetailActivity;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.carsettings.ui.dialog.SimpleDialog;
import com.byd.mvp.model.BydData;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import net.sqlcipher.database.SQLiteDatabase;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: AppDetailsPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends b.b.m.e.a<b.b.g.n.j.a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List<String> f1552a = Arrays.asList("com.byd.vrsettings", "com.byd.carsettings", "com.byd.filemanager", "com.byd.vrsettings", "com.telenav.scoutivi.spotify.byd", "com.fce.onlinemedia.deezer", "com.fce.onlinemedia.tidal", "com.fce.onlinemedia.melon", "com.fce.onlinemedia.tunein", "com.fce.onlinemedia.audiothek", "com.fce.onlinemedia.pocketcasts", "com.fce.onlinemedia.iheartradio", "com.fce.onlinemedia.audials", "com.fce.onlinemedia.radioline", "com.fce.onlinemedia.libby", "com.fce.onlinemedia.audible", "com.fce.onlinemedia.musicmate", "com.fce.onlinemedia.bloomberg", "com.android.car.media", "com.byd.music.kr");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1553b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f1554c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f1555d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f1556e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f1557f;
    public TextView g;
    public TextView h;
    public TextView i;
    public ImageView j;
    public TextView k;
    public TextView l;
    public ScrollView m;
    public BydAppInfo n;
    public View o;
    public AppDetailActivity q;
    public CustomViewDialog r;
    public CustomViewDialog s;

    /* JADX INFO: compiled from: AppDetailsPager.java */
    public class a extends b.b.a.j.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f1558a;

        public a(Context context) {
            this.f1558a = context;
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            if (c.this.n == null || !c.this.n.isSystemApp()) {
                c.this.v();
            } else {
                if (!c.this.n.isDisable()) {
                    c.this.u();
                    return;
                }
                c.this.n.setDisable(false);
                this.f1558a.getPackageManager().setApplicationEnabledSetting(c.this.n.getInfo().packageName, 1, 0);
                c.this.k.setText(this.f1558a.getString(R.string.deactivate_system_app_details));
            }
        }
    }

    /* JADX INFO: compiled from: AppDetailsPager.java */
    public class b extends b.b.a.j.d.a {
        public b() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            c.this.t();
        }
    }

    /* JADX INFO: renamed from: b.b.g.q.f.b.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppDetailsPager.java */
    public class C0070c extends b.b.a.j.d.a {
        public C0070c() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            c.this.J();
        }
    }

    /* JADX INFO: compiled from: AppDetailsPager.java */
    public class d extends b.b.a.j.d.a {
        public d() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            c.this.I();
        }
    }

    /* JADX INFO: compiled from: AppDetailsPager.java */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (c.this.mCallback != null) {
                c.this.mCallback.onBackPress();
            }
            c.this.q.finish();
        }
    }

    /* JADX INFO: compiled from: AppDetailsPager.java */
    public class f implements d.a {
        public f() {
        }

        @Override // b.b.m.f.d.d.a
        public void onOKClicked() {
            b.b.m.c.a.a(c.this.TAG, "---dialogToUninstall---onOKClicked---");
            c cVar = c.this;
            cVar.w(cVar.n);
            if (c.this.mCallback != null) {
                c.this.mCallback.onBackPress();
            }
        }
    }

    public c(Context context) {
        super(context);
    }

    public final void A(Message message) {
        this.f1557f.setText(x((ChartData) message.obj, this.mRootView.getContext()));
    }

    public final void B(Message message) {
        String str = (String) message.obj;
        if (TextUtils.isEmpty(str)) {
            this.i.setText(this.mRootView.getContext().getString(R.string.no_memory_use_summary));
        } else {
            this.i.setText(String.format(this.mRootView.getContext().getString(R.string.memory_use_summary), str));
        }
    }

    public void C(Message message) {
        String string;
        List list = (List) message.obj;
        b.b.m.c.a.a(this.TAG, "---onReceivedAppInfoList---list = " + list);
        if (list == null || list.isEmpty()) {
            string = null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            int size = list.size() <= 5 ? list.size() : 5;
            for (int i = 0; i < size; i++) {
                stringBuffer.append(((b.b.g.m.d.c) list.get(i)).b());
                stringBuffer.append("、");
            }
            stringBuffer.deleteCharAt(stringBuffer.toString().length() - 1);
            string = stringBuffer.toString();
        }
        String string2 = this.mRootView.getContext().getString(R.string.permission_not_request_app_details);
        if (string == null || string.length() <= 0) {
            string = string2;
        }
        b.b.m.c.a.a(this.TAG, "permission:" + string);
        this.g.setText(string);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void D() {
        this.mRootView.getContext().getPackageManager();
        String str = this.n.getInfo().packageName;
        if ("com.telenav.scoutivi.spotify.byd".equals(str)) {
            str = "com.spotify.music";
        }
        ((b.b.g.n.j.a) mo7getP()).B(b.b.g.p.h.j(this.mRootView.getContext(), str), 33, 50);
        this.i.setText(this.mRootView.getContext().getString(R.string.calculating_app_manage));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void E(String str) {
        ((b.b.g.n.j.a) mo7getP()).z(str, 33, 52);
        CustomViewDialog customViewDialog = this.r;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
        CustomViewDialog customViewDialog2 = this.s;
        if (customViewDialog2 != null) {
            customViewDialog2.dismiss();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void F() {
        ((b.b.g.n.j.a) mo7getP()).C(this.n.getInfo().packageName, 33, 51);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void G() {
        ((b.b.g.n.j.a) mo7getP()).A(this.n.getInfo().packageName, 33, 49);
    }

    public void H(AppDetailActivity appDetailActivity) {
        this.q = appDetailActivity;
    }

    public final void I() {
        WeakReference<Context> weakReference;
        if (this.n == null || (weakReference = this.ref) == null || weakReference.get() == null) {
            return;
        }
        this.mRootView.getResources();
        b.b.m.c.a.a(this.TAG, "---showPermission---info = " + this.n);
        CustomViewDialog customViewDialogZ = CustomViewDialog.z(8, this.n.getInfo().packageName, "");
        this.s = customViewDialogZ;
        customViewDialogZ.show(this.q.getSupportFragmentManager(), "CustomViewDialog");
    }

    public void J() {
        WeakReference<Context> weakReference;
        if (this.n == null || (weakReference = this.ref) == null || weakReference.get() == null) {
            return;
        }
        b.b.m.c.a.a(this.TAG, "---showStorage---curInfo = " + this.n);
        if (this.n.getInfo() == null) {
            return;
        }
        CustomViewDialog customViewDialogZ = CustomViewDialog.z(3, this.n.getAppName(), this.n.getInfo().packageName);
        this.r = customViewDialogZ;
        customViewDialogZ.show(this.q.getSupportFragmentManager(), "CustomViewDialog");
    }

    public void K(String str) {
        E(str);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_app_details;
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
        this.f1553b.setText(context.getString(R.string.title_app_details_app_manage));
    }

    @Override // b.b.m.e.f
    public boolean initHandler() {
        return true;
    }

    @Override // b.b.m.e.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_544);
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        EventBus.getDefault().register(this);
        ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.iv_back_dialog_app_details);
        this.j = (ImageView) this.mRootView.findViewById(R.id.iv_app_icon_app_details);
        this.f1553b = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_app_details);
        this.f1554c = (TextView) this.mRootView.findViewById(R.id.tv_app_name_app_details);
        this.f1555d = (TextView) this.mRootView.findViewById(R.id.tv_app_version_app_details);
        this.f1556e = (TextView) this.mRootView.findViewById(R.id.tv_storage_usage_app_details);
        this.f1557f = (TextView) this.mRootView.findViewById(R.id.tv_mobile_data_usage_app_details);
        this.g = (TextView) this.mRootView.findViewById(R.id.tv_permission_app_details);
        this.h = (TextView) this.mRootView.findViewById(R.id.tv_def_options_app_details);
        this.i = (TextView) this.mRootView.findViewById(R.id.tv_memory_usage_app_details);
        this.k = (TextView) this.mRootView.findViewById(R.id.btn_uninstall_app_details);
        this.l = (TextView) this.mRootView.findViewById(R.id.btn_kill_app_details);
        ScrollView scrollView = (ScrollView) this.mRootView.findViewById(R.id.app_detail_sv);
        this.m = scrollView;
        b.b.a.h.m.c(scrollView);
        this.k.setOnClickListener(new a(context));
        this.l.setOnClickListener(new b());
        View viewFindViewById = this.mRootView.findViewById(R.id.ll_storage_usage_app_details);
        this.o = this.mRootView.findViewById(R.id.ll_permission_app_details);
        this.mRootView.findViewById(R.id.ll_mobile_data_usage_app_details);
        this.mRootView.findViewById(R.id.ll_def_options_app_details);
        this.mRootView.findViewById(R.id.ll_memory_usage_app_details);
        viewFindViewById.setOnClickListener(new C0070c());
        this.o.setOnClickListener(new d());
        imageView.setOnClickListener(new e());
        if (b.b.a.i.a.d()) {
            this.f1554c.setGravity(21);
        }
    }

    @Override // b.b.m.e.a
    public int initWidth(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_560);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() == 109) {
            b.b.m.c.a.a(c.class.getName(), "APP_FORCE_STOP");
            ((ActivityManager) this.mRootView.getContext().getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY)).killBackgroundProcesses(this.n.getInfo().packageName);
            TextView textView = this.l;
            if (textView != null) {
                textView.setEnabled(false);
                return;
            }
            return;
        }
        if (aVar.h() == 110) {
            b.b.m.c.a.a(c.class.getName(), "CLEAR_APP_CACHE_MSG_ID");
            this.n.setDisable(true);
            Resources resources = this.mRootView.getResources();
            this.mRootView.getContext().getPackageManager().setApplicationEnabledSetting(this.n.getInfo().packageName, 2, 0);
            this.k.setText(resources.getString(R.string.activate_system_app_details));
        }
    }

    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
        switch (message.arg1) {
            case 49:
                C(message);
                break;
            case 50:
                A(message);
                break;
            case 51:
                B(message);
                break;
            case 52:
                z(message);
                break;
        }
    }

    public final void t() {
        WeakReference<Context> weakReference;
        if (this.n == null || (weakReference = this.ref) == null || weakReference.get() == null) {
            return;
        }
        Context context = this.mRootView.getContext();
        Resources resources = this.mRootView.getResources();
        b.b.m.c.a.a(this.TAG, "---doKill---curInfo = " + this.n);
        if (this.n.getInfo().packageName.equals(context.getPackageName())) {
            z.c(context, resources.getString(R.string.hint_to_kill_myself));
        } else {
            SimpleDialog.z(resources.getString(R.string.kill_app_details), resources.getString(R.string.msg_kill_app_details), resources.getString(R.string.kill_sure_app_details), resources.getString(R.string.dialog_btn_cancel), 109).show(this.q.getSupportFragmentManager(), "SimpleDialog");
        }
    }

    public final void u() {
        WeakReference<Context> weakReference;
        if (this.n == null || (weakReference = this.ref) == null || weakReference.get() == null) {
            return;
        }
        this.mRootView.getContext();
        Resources resources = this.mRootView.getResources();
        b.b.m.c.a.a(this.TAG, "---dialogToStopSystemApp---curInfo = " + this.n);
        SimpleDialog.z(resources.getString(R.string.deactivate_app_app_details), resources.getString(R.string.msg_deactivate_app_details), resources.getString(R.string.deactivate_app_app_details), resources.getString(R.string.dialog_btn_cancel), 110).show(this.q.getSupportFragmentManager(), "SimpleDialog");
    }

    @Override // b.b.m.e.f
    public void updateData(BydData bydData) {
        super.updateData(bydData);
        if (bydData == null || !(bydData instanceof BydAppInfo)) {
            return;
        }
        this.n = (BydAppInfo) bydData;
        Resources resources = this.mRootView.getResources();
        String string = resources.getString(R.string.version_app_details);
        String string2 = resources.getString(R.string.storage_usage_app_details);
        resources.getString(R.string.mobile_data_not_use_app_details);
        this.j.setImageDrawable(this.n.getIcon());
        this.f1554c.setText(this.n.getAppName());
        this.f1555d.setText(string + this.n.getVersion());
        this.f1556e.setText(String.format(string2, Formatter.formatFileSize(getContext(), this.n.getCodeBytes() + this.n.getCacheBytes() + this.n.getDataBytes()).toUpperCase(Locale.ROOT)));
        this.h.setText(resources.getString(R.string.def_options_not_set_app_details));
        b.b.m.c.a.a(this.TAG, "---updateData---curInfo = " + this.n);
        if (!this.n.isSystemApp()) {
            this.k.setText(resources.getString(R.string.uninstall_app_details));
        } else if (this.n.isDisable()) {
            this.k.setText(resources.getString(R.string.activate_system_app_details));
        } else {
            this.k.setText(resources.getString(R.string.deactivate_system_app_details));
        }
        String str = this.n.getInfo().packageName;
        this.k.setEnabled(!b.b.g.p.e.f1391a.contains(str));
        if (f1552a.contains(str) || this.n.isSystemApp()) {
            this.l.setEnabled(false);
        }
        G();
        D();
        F();
    }

    public final void v() {
        WeakReference<Context> weakReference;
        if (this.n == null || (weakReference = this.ref) == null || weakReference.get() == null) {
            return;
        }
        Context context = this.mRootView.getContext();
        Resources resources = this.mRootView.getResources();
        if (this.n.getInfo().packageName.equals(context.getPackageName())) {
            z.c(context, resources.getString(R.string.hint_to_uninstall_system_app));
            return;
        }
        b.b.m.c.a.a(this.TAG, "---dialogToUninstall---curInfo = " + this.n);
        b.b.i.d.G(this.ref.get(), this.n, new f());
    }

    public final void w(BydAppInfo bydAppInfo) {
        PendingIntent activity;
        PackageInstaller packageInstaller;
        b.b.m.c.a.a(this.TAG, "---doUninstall---info = " + bydAppInfo);
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        Context context = this.ref.get();
        Intent intent = new Intent();
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        activity = PendingIntent.getActivity(context.getApplicationContext(), 0, intent, 0);
        packageInstaller = context.getPackageManager().getPackageInstaller();
        String str = bydAppInfo.getInfo().packageName;
        str.hashCode();
        switch (str) {
            case "com.fce.onlinemedia.radioline":
                packageInstaller.uninstall("com.radioline.android.radioline.auto", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.pocketcasts":
                packageInstaller.uninstall("au.com.shiftyjelly.pocketcasts", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.audiothek":
                packageInstaller.uninstall("de.ard.audiothek", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.iheartradio":
                packageInstaller.uninstall("com.clearchannel.iheartradio.controller", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.musicmate":
                packageInstaller.uninstall("skplanet.musicmate", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.heute":
                packageInstaller.uninstall("de.heute.mobile", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.libby":
                packageInstaller.uninstall("com.overdrive.mobile.android.automotive.libby", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.tidal":
                packageInstaller.uninstall("com.aspiro.tidal", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.sportschau":
                packageInstaller.uninstall("de.ard.sportschau.automotive", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.bloomberg":
                packageInstaller.uninstall("com.bloomberg.android.plus", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.audials":
                packageInstaller.uninstall("com.audials", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.audible":
                packageInstaller.uninstall("com.audible.application", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.radiofrance":
                packageInstaller.uninstall("com.radiofrance.radio.radiofrance.android", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.deezer":
                packageInstaller.uninstall("deezer.android.app", activity.getIntentSender());
                break;
            case "com.fce.onlinemedia.tunein":
                packageInstaller.uninstall("tunein.player", activity.getIntentSender());
                break;
        }
        packageInstaller.uninstall(str, activity.getIntentSender());
    }

    public final String x(ChartData chartData, Context context) {
        long totalBytes = chartData.detail.getTotalBytes();
        return totalBytes == 0 ? context.getString(R.string.mobile_data_not_use_app_details) : context.getString(R.string.data_summary_format, Formatter.formatFileSize(context, totalBytes), DateUtils.formatDateTime(context, chartData.detail.getStart(), 65552));
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.j.a newP() {
        return new b.b.g.n.j.a();
    }

    public final void z(Message message) {
        updateData((BydAppInfo) message.obj);
    }
}
