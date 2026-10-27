package com.byd.carsettings;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import b.b.a.h.l;
import b.b.g.n.q.m;
import b.b.g.r.e;
import b.b.g.r.z;
import b.b.o.b.a.a;
import com.byd.base.view.BydSwitch;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class RearViewMirrorActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public BydSwitch f2593a;

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.activity_rear_view_mirror;
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.activity_rear_view_mirror;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.activity_rear_right_view_mirror;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        m.p0().e3(2);
        super.onBackPressed();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        BydLogHelper.logD(getClass().getSimpleName(), " onCheckedChanged  is Check : " + z);
        m.p0().d3(z ? 1 : 2);
        e.i(300001);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        m.p0().e3(2);
        e.h(300001);
        finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(b.b.o.b.b.a.a(this));
        l.F(this, (ImageView) findViewById(R.id.dialog_rearview_inner_mirror_close));
        BydSwitch bydSwitch = (BydSwitch) findViewById(R.id.auto_light_sw);
        this.f2593a = bydSwitch;
        l.e(null, bydSwitch, m.p0().U0() == 2, this);
        m.p0().e3(1);
        EventBus.getDefault().register(this);
        BydLogHelper.logD(getClass().getSimpleName(), " onCreate " + this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        m.p0().e3(2);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() == 300001) {
            BydLogHelper.logD("REAR_VIEW_INNER_AUTO_LIGHT_STATE_CHANGED :" + aVar.c());
            int iC = aVar.c();
            if (e.e(aVar) && (iC = m.p0().U0()) == 0) {
                iC = 1;
            }
            if (iC == 1 || iC == 2) {
                l.f(this.f2593a, iC == 2);
                return;
            }
            return;
        }
        if (aVar.h() == 300002) {
            BydLogHelper.logD("REAR_VIEW_INNER_SCREEN_STATE_CHANGED :" + aVar.c());
            int iV0 = m.p0().V0();
            if (iV0 == 2 || iV0 == 0 || iV0 == 3) {
                m.p0().e3(2);
                finish();
                return;
            }
            return;
        }
        if (aVar.h() == 300004) {
            BydLogHelper.logD("REAR_VIEW_INNER_SCREEN_SW_CONTROL_CHANGED :" + aVar.c());
            if (m.p0().x1() == 2) {
                m.p0().e3(2);
                z.b(getApplicationContext(), R.string.vehiclesettings_rear_view_mirror_instrument_turning_try_again);
                finish();
            }
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        m.p0().e3(z ? 1 : 2);
    }
}
