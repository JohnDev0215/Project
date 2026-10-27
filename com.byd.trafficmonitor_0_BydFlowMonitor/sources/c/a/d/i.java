package c.a.d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.recyclerview.widget.RecyclerView;
import c.a.d.s.m;
import c.a.d.s.n;
import c.a.d.s.o;
import com.byd.trafficmonitor.MainActivity;
import com.byd.trafficmonitor.model.TrafficInfo;
import com.bydux.api.R;

/* JADX INFO: compiled from: FlowWhereAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends c.a.a.h.b.a<TrafficInfo> {
    public Context j;

    /* JADX INFO: compiled from: FlowWhereAdapter.java */
    public class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ TrafficInfo f3566b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ c.a.d.s.i f3567c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f3568d;

        public a(TrafficInfo trafficInfo, c.a.d.s.i iVar, int i2) {
            this.f3566b = trafficInfo;
            this.f3567c = iVar;
            this.f3568d = i2;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            k kVar;
            if (z) {
                if ("com.neusoft.na.navigation".equals(this.f3566b.o())) {
                    Settings.Global.putInt(i.this.j.getContentResolver(), "persist.mobile.navigation.switch", 0);
                }
                this.f3567c.b(this.f3568d);
                this.f3566b.r(TrafficInfo.f4414b);
            } else {
                if ("com.neusoft.na.navigation".equals(this.f3566b.o())) {
                    Settings.Global.putInt(i.this.j.getContentResolver(), "persist.mobile.navigation.switch", 1);
                }
                this.f3567c.a(this.f3568d);
                this.f3566b.r(TrafficInfo.f4415c);
            }
            if ("com.neusoft.na.ehp".equals(this.f3566b.o()) || (kVar = MainActivity.f4377b) == null) {
                return;
            }
            try {
                kVar.m(this.f3566b.p(), this.f3566b.l());
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public i(Context context) {
        this.j = context;
    }

    @Override // c.a.a.h.b.a
    public void J(RecyclerView.c0 c0Var, int i2, int i3) {
        super.J(c0Var, i2, i3);
        TrafficInfo trafficInfo = B().get(i2);
        if (c0Var instanceof j) {
            j jVar = (j) c0Var;
            Drawable drawableA = m.a(trafficInfo.o(), this.j);
            if (drawableA != null) {
                jVar.O(R.id.iv_app_icon, drawableA);
            } else {
                Drawable drawableN = N(this.j, trafficInfo.o());
                if (drawableN != null) {
                    jVar.O(R.id.iv_app_icon, drawableN);
                }
            }
            jVar.P(R.id.tv_app_name, O(this.j, trafficInfo.o()));
            jVar.P(R.id.tv_flow_up, n.b(trafficInfo.n()));
            jVar.P(R.id.tv_flow_down, n.b(trafficInfo.m()));
            Switch r3 = (Switch) jVar.M(R.id.digital_key_set_switch);
            if (trafficInfo.l() == TrafficInfo.f4414b) {
                r3.setChecked(true);
            } else {
                r3.setChecked(false);
            }
            if (!h.f3565a.contains(trafficInfo.o())) {
                int i4 = SystemProperties.getInt("persist.sys.brower_status", -1);
                if (i4 == 0) {
                    o.a(r3);
                } else if (i4 == 1) {
                    o.b(r3);
                }
            }
            r3.setOnCheckedChangeListener(new a(trafficInfo, c.a.d.s.i.c(this.j.getApplicationContext()), trafficInfo.p()));
        }
    }

    @Override // c.a.a.h.b.a
    public RecyclerView.c0 L(LayoutInflater layoutInflater, ViewGroup viewGroup, int i2) {
        return new j(layoutInflater, viewGroup, this);
    }

    public final Drawable N(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            if (applicationInfo == null) {
                return null;
            }
            return packageManager.getApplicationIcon(applicationInfo);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public final String O(Context context, String str) {
        if ("com.neusoft.na.ehp".equals(str)) {
            return context.getString(R.string.isa_app_name);
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationInfo(str, 128).loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
