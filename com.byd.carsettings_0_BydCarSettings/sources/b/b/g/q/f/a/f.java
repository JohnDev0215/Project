package b.b.g.q.f.a;

import android.app.Activity;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.widget.SeekBar;
import android.widget.TextView;
import com.byd.carsettings.R;
import com.byd.carsettings.model.CommonItemId;
import com.byd.carsettings.model.CommonSettingItem;
import com.byd.mvp.model.BydData;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SingleSeekbarCard.java */
/* JADX INFO: loaded from: classes2.dex */
public class f extends b.b.g.q.f.a.c<b.b.g.n.i.e> {
    public TextView h;
    public TextView i;
    public SeekBar j;
    public CommonSettingItem k;
    public b.b.g.n.i.e l;
    public Context m;
    public int n;
    public final ContentObserver o;

    /* JADX INFO: compiled from: SingleSeekbarCard.java */
    public class a extends ContentObserver {
        public a(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            try {
                int i = Settings.System.getInt(f.this.m.getContentResolver(), BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS);
                b.b.m.c.a.a(f.this.f2100a, "ContentObserver onChange:" + i);
                if (f.this.k.getOriginalId() == CommonItemId.REGULATE_MEDIA_BRIGHTNESS) {
                    f.this.j.setProgress(i);
                    f.this.i.setText(((int) (((double) i) / 2.55d)) + "%");
                }
            } catch (Settings.SettingNotFoundException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: SingleSeekbarCard.java */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1532a;

        static {
            int[] iArr = new int[CommonItemId.values().length];
            f1532a = iArr;
            try {
                iArr[CommonItemId.REGULATE_INSTRUMENT_BRIGHTNESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1532a[CommonItemId.REGULATE_MEDIA_BRIGHTNESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: SingleSeekbarCard.java */
    public class c implements SeekBar.OnSeekBarChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<f> f1533a;

        public c(f fVar) {
            this.f1533a = new WeakReference<>(fVar);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            WeakReference<f> weakReference = this.f1533a;
            if (weakReference == null || weakReference.get() == null || !z) {
                return;
            }
            this.f1533a.get().J(i);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            WeakReference<f> weakReference = this.f1533a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1533a.get().K();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            WeakReference<f> weakReference = this.f1533a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1533a.get().L();
        }
    }

    public f(Activity activity) {
        super(activity);
        this.n = 0;
        this.o = new a(new Handler());
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.h
    /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.i.e newP() {
        b.b.g.n.i.e eVar = new b.b.g.n.i.e();
        this.l = eVar;
        return eVar;
    }

    public final void J(int i) {
        N(i);
        M(i);
    }

    public final void K() {
        this.k.getOriginalId();
        CommonItemId commonItemId = CommonItemId.REGULATE_INSTRUMENT_BRIGHTNESS;
    }

    public final void L() {
        this.j.getProgress();
    }

    public final void M(int i) {
        int i2 = b.f1532a[this.k.getOriginalId().ordinal()];
        if (i2 == 1) {
            this.l.g(i);
        } else {
            if (i2 != 2) {
                return;
            }
            this.l.f(i);
        }
    }

    public final void N(int i) {
        int i2 = b.f1532a[this.k.getOriginalId().ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                return;
            }
            this.i.setText(((int) (((double) i) / 2.55d)) + "%");
            return;
        }
        int i3 = (int) ((i / 11.0f) * 100.0f);
        if (i3 < 0) {
            i3 = 0;
        }
        this.i.setText(i3 + "%");
        b.b.m.c.a.a(this.f2100a, "updateProgressText:" + i3);
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.e
    public void e(Context context) {
        this.j.setOnSeekBarChangeListener(new c(this));
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.h
    public int getLayoutId() {
        return R.layout.card_single_seekbar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.a.c, b.b.m.e.e
    public void k(Context context) {
        super.k(context);
        this.m = context;
        ((b.b.g.n.i.e) mo7getP()).e();
        this.h = (TextView) this.f2102c.findViewById(R.id.tv_title_card_single_seekbar);
        this.i = (TextView) this.f2102c.findViewById(R.id.tv_progress_card_single_seekbar);
        this.j = (SeekBar) this.f2102c.findViewById(R.id.sbar_card_single_seekbar);
        if (this.m.getContentResolver() != null) {
            this.m.getContentResolver().registerContentObserver(Settings.System.getUriFor(BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS), false, this.o);
        }
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.e
    public void l() {
        super.l();
        if (this.m.getContentResolver() != null) {
            this.m.getContentResolver().unregisterContentObserver(this.o);
        }
    }

    @Override // b.b.m.e.e
    public void n(BydData bydData, int i) {
        if (bydData == null || !(bydData instanceof CommonSettingItem)) {
            return;
        }
        CommonSettingItem commonSettingItem = (CommonSettingItem) bydData;
        this.k = commonSettingItem;
        this.h.setText(commonSettingItem.getTitle());
        this.j.setMax(this.k.getMax());
        if (Build.VERSION.SDK_INT >= 26) {
            this.j.setMin(this.k.getMin());
        }
        int iD = 0;
        int i2 = b.f1532a[this.k.getOriginalId().ordinal()];
        if (i2 == 1) {
            iD = this.l.d();
            this.n = iD;
        } else if (i2 == 2) {
            iD = this.l.c();
        }
        this.j.setProgress(iD);
        N(iD);
    }

    @Override // b.b.g.q.f.a.c
    public void r(int i, b.b.a.c.a aVar) {
        super.r(i, aVar);
        if (this.k.getOriginalId() == CommonItemId.REGULATE_INSTRUMENT_BRIGHTNESS) {
            if (b.b.g.r.e.e(aVar)) {
                this.j.setProgress(this.n);
                N(this.n);
                return;
            }
            if (i > 11) {
                i -= 11;
            }
            if (i < 1 || i > 11) {
                return;
            }
            this.j.setProgress(i);
            N(i);
            this.n = i;
        }
    }
}
