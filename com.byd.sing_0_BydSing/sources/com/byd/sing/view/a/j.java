package com.byd.sing.view.a;

import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.sing.R;
import com.byd.sing.SingApplication;
import com.byd.sing.m;
import com.byd.sing.view.SettingItem;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

/* JADX INFO: compiled from: SettingAudioFrame.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends com.byd.sing.view.a.c {
    private SettingItem q;
    private SettingItem r;
    private SettingItem s;
    private BYDAutoAudioDevice t;
    private final AbsBYDAutoAudioListener u = new e();

    /* JADX INFO: compiled from: SettingAudioFrame.java */
    class a extends SettingItem.c {
        a() {
        }

        @Override // com.byd.sing.view.SettingItem.c, android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            super.onProgressChanged(seekBar, i, z);
            com.byd.sing.a.a("onProgressChanged progress = " + i);
            if (z) {
                j.this.t.unregisterListener(j.this.u);
                com.byd.sing.j.f().R(i);
                j.this.t.registerListener(j.this.u);
            }
        }
    }

    /* JADX INFO: compiled from: SettingAudioFrame.java */
    class b extends SettingItem.c {
        b() {
        }

        @Override // com.byd.sing.view.SettingItem.c, android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            super.onProgressChanged(seekBar, i, z);
            com.byd.sing.j.f().P(i);
        }
    }

    /* JADX INFO: compiled from: SettingAudioFrame.java */
    class c extends SettingItem.c {
        c() {
        }

        @Override // com.byd.sing.view.SettingItem.c, android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            super.onProgressChanged(seekBar, i, z);
            com.byd.sing.j.f().Q(i);
        }
    }

    /* JADX INFO: compiled from: SettingAudioFrame.java */
    class d implements f.b {
        d() {
        }

        @Override // com.byd.sing.view.a.j.f.b
        public void a(String str, int i) {
            com.byd.sing.a.a("onItemClick position = " + i);
            j.this.N(i);
            com.byd.sing.j.f().O(i);
        }
    }

    /* JADX INFO: compiled from: SettingAudioFrame.java */
    class e extends AbsBYDAutoAudioListener {
        e() {
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onVolumeChanged(int i, int i2) {
            super.onVolumeChanged(i, i2);
            com.byd.sing.a.a("onVolumeChanged");
            j.this.r.setValue(i);
        }
    }

    /* JADX INFO: compiled from: SettingAudioFrame.java */
    private static class f extends RecyclerView.g<c> {
        private b e;
        private final int[] f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f3323d = -1;
        protected ColorFilter g = new LightingColorFilter(-1, 1);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String[] f3322c = SingApplication.a().getResources().getStringArray(R.array.model_name_array);

        /* JADX INFO: compiled from: SettingAudioFrame.java */
        class a implements View.OnClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ c f3324b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f3325c;

            a(c cVar, String str) {
                this.f3324b = cVar;
                this.f3325c = str;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int iJ = this.f3324b.j();
                if (f.this.f3323d != iJ) {
                    f.this.f3323d = iJ;
                    f.this.h();
                }
                if (f.this.e != null) {
                    f.this.e.a(this.f3325c, f.this.f3323d);
                }
            }
        }

        /* JADX INFO: compiled from: SettingAudioFrame.java */
        public interface b {
            void a(String str, int i);
        }

        /* JADX INFO: compiled from: SettingAudioFrame.java */
        private static class c extends RecyclerView.c0 {
            private final ImageView t;
            private final TextView u;

            public c(View view) {
                super(view);
                this.t = (ImageView) view.findViewById(R.id.iv_icon);
                this.u = (TextView) view.findViewById(R.id.tv_name);
            }
        }

        public f() {
            TypedArray typedArrayObtainTypedArray = SingApplication.a().getResources().obtainTypedArray(R.array.model_icon_normal);
            int length = typedArrayObtainTypedArray.length();
            this.f = new int[length];
            for (int i = 0; i < length; i++) {
                this.f[i] = typedArrayObtainTypedArray.getResourceId(i, 0);
            }
            typedArrayObtainTypedArray.recycle();
        }

        private void y(ImageView imageView, int i) {
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(i);
            imageView.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public c m(ViewGroup viewGroup, int i) {
            return com.byd.sing.j.f().q() ? new c(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_model, viewGroup, false)) : new c(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_model_denza, viewGroup, false));
        }

        public void B(b bVar) {
            this.e = bVar;
        }

        public void C(int i) {
            if (i < 0 || i >= c()) {
                return;
            }
            int i2 = this.f3323d;
            this.f3323d = i;
            if (i2 != i) {
                if (i2 != -1) {
                    i(i2);
                }
                i(this.f3323d);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.f3322c.length;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
        public void k(c cVar, int i) {
            String str = this.f3322c[i];
            cVar.u.setText(str);
            cVar.t.setImageResource(this.f[i]);
            if (i == this.f3323d) {
                cVar.f1679b.setBackgroundResource(R.drawable.bg_setting_frame);
                y(cVar.t, 2);
            } else {
                cVar.f1679b.setBackground(null);
                y(cVar.t, 1);
            }
            cVar.f1679b.setOnClickListener(new a(cVar, str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N(int i) {
        com.byd.sing.a.n("reverbSupport mode:" + i);
        if (i == 3) {
            this.s.setNotSupport(true);
        } else {
            this.s.setNotSupport(false);
        }
    }

    @Override // com.byd.sing.view.a.c
    public void F() {
        super.F();
        h.U();
    }

    @Override // com.byd.sing.view.a.c
    public boolean H() {
        return true;
    }

    @Override // com.byd.sing.view.a.c
    public void I() {
        super.I();
        com.byd.sing.a.a("resetDelay");
    }

    @Override // com.byd.sing.view.a.c, com.byd.sing.view.a.f, com.byd.sing.view.a.g
    public void a() {
        org.greenrobot.eventbus.c.c().q(this);
        BYDAutoAudioDevice bYDAutoAudioDevice = this.t;
        if (bYDAutoAudioDevice != null) {
            bYDAutoAudioDevice.unregisterListener(this.u);
        }
        super.a();
    }

    @Override // com.byd.sing.view.a.g
    protected void b(View view) {
        com.byd.sing.a.a("initContent");
        if (!com.byd.sing.j.f().r()) {
            a();
            h.U();
            return;
        }
        org.greenrobot.eventbus.c.c().o(this);
        this.t = BYDAutoAudioDevice.getInstance(SingApplication.a());
        this.q = (SettingItem) view.findViewById(R.id.si_volume_mic);
        this.r = (SettingItem) view.findViewById(R.id.si_volume_system);
        this.s = (SettingItem) view.findViewById(R.id.si_reverberation);
        this.r.setMax(39);
        this.r.setValue(com.byd.sing.j.f().m());
        this.r.setOnVolumeChangeListener(new a());
        this.t.registerListener(this.u);
        int iK = com.byd.sing.j.f().k();
        this.q.setMax(10);
        this.q.setValue(iK);
        this.q.setOnVolumeChangeListener(new b());
        int iL = com.byd.sing.j.f().l();
        this.s.setMax(10);
        this.s.setValue(com.byd.sing.j.f().l());
        this.s.setOnVolumeChangeListener(new c());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), 0, false));
        f fVar = new f();
        recyclerView.setAdapter(fVar);
        fVar.B(new d());
        int iJ = com.byd.sing.j.f().j();
        N(iJ);
        fVar.C(iJ);
        if (com.byd.sing.j.f().t()) {
            com.byd.sing.g.e(iK);
            com.byd.sing.g.f(iL);
            com.byd.sing.g.c(iJ);
        } else {
            if (!com.byd.sing.j.f().s()) {
                com.byd.sing.a.n("no mic connected");
                return;
            }
            m.e(iK);
            m.f(iL);
            m.c(iJ);
        }
    }

    @Override // com.byd.sing.view.a.g
    protected int c() {
        return com.byd.sing.j.f().q() ? com.byd.sing.j.f().w() ? R.layout.layout_setting_audio_fullscreen_right_rudder : R.layout.layout_setting_audio_fullscreen : com.byd.sing.j.f().w() ? R.layout.layout_setting_audio_fullscreen_denza_right_rudder : R.layout.layout_setting_audio_fullscreen_denza;
    }

    @Override // com.byd.sing.view.a.g
    protected int d() {
        return com.byd.sing.k.e() ? R.drawable.icon_ktv_suoxiao : R.drawable.icon_public_close_48;
    }

    @Override // com.byd.sing.view.a.g
    protected int e() {
        return R.drawable.icon_navigation_setting_48;
    }

    @Override // com.byd.sing.view.a.g
    protected int g() {
        return R.string.title_common;
    }

    @Override // com.byd.sing.view.a.g
    protected void i(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_482;
    }

    @Override // com.byd.sing.view.a.f, com.byd.sing.view.a.g
    public void m() {
        org.greenrobot.eventbus.c.c().q(this);
        super.m();
    }

    @Override // com.byd.sing.view.a.g
    protected boolean n() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_left) {
            w();
            return;
        }
        if (id == R.id.iv_right) {
            com.byd.sing.j.f().S(new k());
            a();
        } else if (id == R.id.root_frame) {
            F();
        } else if (id == R.id.root_view) {
            G();
        }
    }

    @l(threadMode = ThreadMode.MAIN)
    public void onVolumeChangeEvent(com.byd.sing.service.a aVar) {
        com.byd.sing.a.a("onVolumeChangeEvent");
        this.q.setValue(com.byd.sing.j.f().k());
    }

    @Override // com.byd.sing.view.a.f
    protected void q() {
    }

    @Override // com.byd.sing.view.a.f
    protected void t(int i, int i2) {
    }

    @Override // com.byd.sing.view.a.f
    protected void v(MotionEvent motionEvent) {
    }

    @Override // com.byd.sing.view.a.f
    protected void w() {
        if (com.byd.sing.k.e()) {
            com.byd.sing.j.f().S(new h());
        }
        a();
    }

    @Override // com.byd.sing.view.a.f
    protected boolean y() {
        return true;
    }
}
