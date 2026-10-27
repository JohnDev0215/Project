package c.a.d.n;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import c.a.d.s.j;
import com.byd.trafficmonitor.view.WaveView;
import com.bydux.api.R;
import com.bydux.api.common.views.window.BydFragment;

/* JADX INFO: compiled from: IcallServiceFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends BydFragment {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f3577b = getClass().getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.d.t.a f3578c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WaveView f3579d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f3580e;

    public static a g() {
        return new a();
    }

    public int b() {
        return R.layout.flow_icall_service_fragment;
    }

    public String c() {
        return this.f3577b;
    }

    public final void d(View view) {
        WaveView waveView = (WaveView) view.findViewById(R.id.wave_view_call);
        this.f3579d = waveView;
        waveView.g(Color.parseColor("#287CEDEC"), Color.parseColor("#3c7CEDEC"));
        this.f3578c = new c.a.d.t.a(this.f3579d);
    }

    public void e(Bundle bundle) {
        d(this.f3580e);
    }

    public void f(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.f3580e.findViewById(R.id.rl_call).setBackgroundResource(R.drawable.flow_icall_bg_1);
    }

    public final void h() {
        String strA = j.a(getContext(), "has_voice", "0");
        String strA2 = j.a(getContext(), "voice_total", "");
        if (strA.equals("1")) {
            this.f3579d.f(getString(R.string.flow_5g_remain), Integer.parseInt(strA2), "分钟");
            this.f3579d.setWaterLevelRatio(0.5f);
            this.f3578c.c();
        }
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3580e = layoutInflater.inflate(b(), viewGroup, false);
        f(layoutInflater, viewGroup);
        e(bundle);
        h();
        return this.f3580e;
    }
}
