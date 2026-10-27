package b.b.g.q.f.d;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.o.c;
import b.b.g.q.f.d.i;
import b.b.g.r.x;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.SoundWaveAdapter;
import com.byd.carsettings.sound.model.SoundWave;
import com.byd.carsettings.sound.model.SpeakerLayoutMode;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.carsettings.ui.dialog.SimpleDialog;
import com.byd.carsettings.ui.widget.AreaableSeekbar;
import com.byd.carsettings.ui.widget.LinearItem;
import com.byd.carsettings.ui.widget.NoScrollGridLayoutManager;
import com.byd.mvp.ui.widget.RecyclerSpace;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: DynaudioPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class f extends i implements b.b.g.q.h.c.b, c.f {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ViewGroup f1758f;
    public LinearItem g;
    public LinearItem h;
    public TextView i;
    public TextView j;
    public AreaableSeekbar k;
    public BydSwitch l;
    public RecyclerView m;
    public String[] n;
    public String[] o;
    public ImageView q;

    /* JADX INFO: compiled from: DynaudioPager.java */
    public class a extends b.b.a.j.d.a {
        public a() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            f.this.u();
        }
    }

    /* JADX INFO: compiled from: DynaudioPager.java */
    public class b extends b.b.a.j.d.a {
        public b() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            f.this.t();
        }
    }

    /* JADX INFO: compiled from: DynaudioPager.java */
    public class c extends b.b.a.j.d.a {
        public c() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            f.this.s();
        }
    }

    /* JADX INFO: compiled from: DynaudioPager.java */
    public class d implements b.b.m.f.c.a.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f1762a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ SoundWaveAdapter f1763b;

        public d(List list, SoundWaveAdapter soundWaveAdapter) {
            this.f1762a = list;
            this.f1763b = soundWaveAdapter;
        }

        @Override // b.b.m.f.c.a.e
        public void onItemClicked(int i) {
            for (int i2 = 0; i2 < this.f1762a.size(); i2++) {
                if (i2 == i) {
                    ((SoundWave) this.f1762a.get(i)).setChecked(true);
                } else {
                    ((SoundWave) this.f1762a.get(i2)).setChecked(false);
                }
            }
            this.f1763b.notifyDataSetChanged();
            b.b.m.f.c.a.e eVar = f.this.f1784e;
            if (eVar != null) {
                eVar.onItemClicked(i);
            }
        }
    }

    /* JADX INFO: compiled from: DynaudioPager.java */
    public class e implements CompoundButton.OnCheckedChangeListener {
        public e() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            f.this.m.setVisibility(z ? 0 : 8);
            i.c cVar = f.this.f1783d;
            if (cVar != null) {
                cVar.a(0, z);
            }
        }
    }

    /* JADX INFO: renamed from: b.b.g.q.f.d.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DynaudioPager.java */
    public class RunnableC0074f implements Runnable {
        public RunnableC0074f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.h.setClickable(true);
        }
    }

    /* JADX INFO: compiled from: DynaudioPager.java */
    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.g.setClickable(true);
        }
    }

    /* JADX INFO: compiled from: DynaudioPager.java */
    public class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.a.c.a f1768a;

        public h(b.b.a.c.a aVar) {
            this.f1768a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            switch (this.f1768a.h()) {
                case 230006:
                    String strG = this.f1768a.g();
                    f.this.g.setRightText(strG != null ? strG : "");
                    break;
                case 230007:
                    String strG2 = this.f1768a.g();
                    f.this.h.setRightText(strG2 != null ? strG2 : "");
                    break;
            }
        }
    }

    public f(Context context, Fragment fragment) {
        super(context);
    }

    @Override // b.b.g.o.c.f
    public void a() {
        loadData();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.d.i
    public void b(int i) {
        int iT = ((b.b.g.n.p.e) mo7getP()).t();
        this.j.setText(iT + "");
        this.k.setProgress(iT);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.subview_sound_dynaudio;
    }

    @Override // b.b.g.q.f.d.i, b.b.m.e.f
    public void initData(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SoundWave(context.getResources().getString(R.string.power_simulate_sound_of_wave), true));
        arrayList.add(new SoundWave(context.getResources().getString(R.string.science_fiction_simulate_sound_of_wave), false));
        arrayList.add(new SoundWave(context.getResources().getString(R.string.sound_3_simulate_sound_of_wave), false));
        arrayList.add(new SoundWave(context.getResources().getString(R.string.sound_4_simulate_sound_of_wave), false));
        int i = context.getResources().getConfiguration().orientation == 2 ? 3 : 4;
        int dimension = (int) context.getResources().getDimension(R.dimen.dp_48);
        int size = (arrayList.size() / i) + (arrayList.size() % i > 0 ? 1 : 0);
        b.b.m.c.a.a(this.TAG, "----initData---rows = " + size);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.m.getLayoutParams();
        layoutParams.height = dimension * size;
        b.b.m.c.a.a(this.TAG, "----initData---lp.height = " + layoutParams.height);
        this.m.setLayoutParams(layoutParams);
        this.m.requestLayout();
        NoScrollGridLayoutManager noScrollGridLayoutManager = new NoScrollGridLayoutManager(context, i);
        noScrollGridLayoutManager.setOrientation(1);
        this.m.setLayoutManager(noScrollGridLayoutManager);
        if (this.m.getItemDecorationCount() <= 0) {
            this.m.addItemDecoration(new RecyclerSpace(0, 0, 0, (int) context.getResources().getDimension(R.dimen.dp_16)));
        }
        SoundWaveAdapter soundWaveAdapter = new SoundWaveAdapter(arrayList);
        this.m.setAdapter(soundWaveAdapter);
        soundWaveAdapter.notifyDataSetChanged();
        soundWaveAdapter.setOnItemClickedListener(new d(arrayList, soundWaveAdapter));
        this.l.setOnCheckedChangeListener(new e());
        x.d(this.i);
    }

    @Override // b.b.m.e.f
    public boolean initHandler() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.d.i, b.b.m.e.f
    public void initViews(Context context) {
        ((b.b.g.n.p.e) mo7getP()).w();
        this.f1758f = (ViewGroup) this.mRootView.findViewById(R.id.ll_sound_regulate_sound_dynaudio);
        this.g = (LinearItem) this.mRootView.findViewById(R.id.item_sound_field_focusing_sound_dynaudio);
        this.h = (LinearItem) this.mRootView.findViewById(R.id.item_sound_charact_sound_dynaudio);
        ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.iv_sound_regulate_detail_sound_dynaudio);
        this.q = imageView;
        imageView.setOnClickListener(new a());
        this.j = (TextView) this.mRootView.findViewById(R.id.tv_progress_sound_dynaudio);
        this.i = (TextView) this.mRootView.findViewById(R.id.tv_title_sound_regulate_sound_dynaudio);
        this.k = (AreaableSeekbar) this.mRootView.findViewById(R.id.sbar_sound_regulate_sound_dynaudio);
        View viewFindViewById = this.mRootView.findViewById(R.id.item_simulate_sound_of_wave_sound_dynaudio);
        this.l = (BydSwitch) this.mRootView.findViewById(R.id.switch_simulate_sound_of_wave_sound_dynaudio);
        this.m = (RecyclerView) this.mRootView.findViewById(R.id.rcv_sound_simulate_sound_of_wave_sound_dynaudio);
        this.g.setOnClickListener(new b());
        this.h.setOnClickListener(new c());
        this.k.setOnSeekBarChangeListener(this);
        viewFindViewById.setVisibility(8);
        c.d.a().X(this);
        EventBus.getDefault().register(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void loadData() {
        SpeakerLayoutMode speakerLayoutMode;
        super.loadData();
        if (this.mRootView == null) {
            return;
        }
        b.b.g.o.h.a aVarJ = ((b.b.g.n.p.e) mo7getP()).j();
        b.b.m.c.a.d(this.TAG, "loadData", "config: " + aVarJ);
        SpeakerLayoutMode speakerLayoutMode2 = SpeakerLayoutMode.SEAT_ROWS_3_SUV;
        if (aVarJ != null && (speakerLayoutMode = aVarJ.f1344c) != null) {
            speakerLayoutMode2 = speakerLayoutMode;
        }
        this.n = this.mRootView.getResources().getStringArray(R.array.dyna_focus_array);
        if (speakerLayoutMode2 == SpeakerLayoutMode.SEAT_ROWS_3_MPV) {
            this.n = this.mRootView.getResources().getStringArray(R.array.dyna_focus_mpv_array);
        }
        b.b.m.c.a.d(this.TAG, "loadData", "soundField: " + Arrays.toString(this.n));
        int iS = ((b.b.g.n.p.e) mo7getP()).s();
        b.b.m.c.a.d(this.TAG, "loadData", "Focus defIndex: " + iS);
        this.g.setRightText(this.n[iS]);
        this.o = this.mRootView.getResources().getStringArray(R.array.dyna_eq_array);
        int iR = ((b.b.g.n.p.e) mo7getP()).r();
        b.b.m.c.a.d(this.TAG, "loadData", "Charact defIndex: " + iR);
        this.h.setRightText(this.o[iR]);
        int iT = ((b.b.g.n.p.e) mo7getP()).t();
        if (iT < 0 || iT > this.k.getMax()) {
            return;
        }
        this.k.setProgress(iT);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        this.k.setOnSeekBarChangeListener(null);
        this.q.setOnClickListener(null);
        this.g.setOnClickListener(null);
        this.h.setOnClickListener(null);
        this.l.setOnCheckedChangeListener(null);
        k(null);
        setOnItemClickedListener(null);
        b.b.m.f.d.d.c(f.class.getName());
        c.d.a().X(null);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        b.b.a.f.h.a().g(new h(aVar), 500L);
    }

    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
        if (message.arg1 != 849) {
            return;
        }
        v(message.arg2, ((Integer) message.obj).intValue());
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        if (z) {
            setViewGroupRtl(this.g, false);
            setViewGroupRtl(this.h, false);
            setViewGroupRtl(this.f1758f, false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        b.b.m.c.a.a(this.TAG, "----onProgressChanged---");
        this.j.setText(i + "");
        if (z) {
            ((b.b.g.n.p.e) mo7getP()).z(i);
        }
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        if (z) {
            setViewGroupRtl(this.g, true);
            setViewGroupRtl(this.h, true);
            setViewGroupRtl(this.f1758f, true);
        }
    }

    public final void s() {
        b.b.m.c.a.a(this.TAG, "----onDynaSoundCharactOperate---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        this.g.setClickable(false);
        CustomViewDialog.y(6).show(((FragmentActivity) this.ref.get()).getSupportFragmentManager(), "CustomViewDialog");
        b.b.a.f.h.a().g(new g(), 200L);
    }

    public final void t() {
        b.b.m.c.a.a(this.TAG, "----onDynaSoundFieldFocusingOperate---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        this.h.setClickable(false);
        CustomViewDialog.y(5).show(((FragmentActivity) this.ref.get()).getSupportFragmentManager(), "CustomViewDialog");
        b.b.a.f.h.a().g(new RunnableC0074f(), 200L);
    }

    public final void u() {
        b.b.m.c.a.a(this.TAG, "----onDynaSoundRegulateDetailOperate---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        FragmentActivity fragmentActivity = (FragmentActivity) this.ref.get();
        SimpleDialog.z(fragmentActivity.getString(R.string.title_dialog_sound_regulate_dynaudio_sound), fragmentActivity.getString(R.string.msg_dialog_sound_regulate_dynaudio_sound), fragmentActivity.getString(R.string.dialog_btn_ok), "", 0).show(fragmentActivity.getSupportFragmentManager(), "SimpleDialog");
    }

    public final void v(int i, int i2) {
        b.b.m.c.a.a(this.TAG, "--onGearSpeedChanged--manufacturer: " + i + ", gear: " + i2);
        if (2 != i) {
            return;
        }
        this.j.setText(i2 + "");
        this.k.setProgress(i2);
    }
}
