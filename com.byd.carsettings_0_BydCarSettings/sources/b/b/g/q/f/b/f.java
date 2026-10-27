package b.b.g.q.f.b;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.byd.carsettings.R;
import com.byd.carsettings.sound.model.SpeakerLayoutMode;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.carsettings.ui.dialog.SimpleDialog;
import com.byd.carsettings.ui.widget.SoundFieldView;
import com.byd.carsettings.ui.widget.UserRadioGroup;
import com.byd.mvp.utils.AppConfig;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: BsSoundFieldPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class f extends b.b.m.e.a<b.b.g.n.j.d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewGroup f1585a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewGroup f1586b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f1587c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f1588d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public UserRadioGroup f1589e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Button f1590f;
    public ImageView g;
    public SoundFieldView h;
    public View i;
    public b.b.g.q.h.c.c j;
    public volatile int k;
    public CustomViewDialog l;
    public SpeakerLayoutMode m;
    public volatile boolean n;
    public volatile int o;
    public volatile int q;
    public boolean r;

    /* JADX INFO: compiled from: BsSoundFieldPager.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.b.m.g.c.a(200L)) {
                return;
            }
            f.this.l();
        }
    }

    /* JADX INFO: compiled from: BsSoundFieldPager.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (f.this.mCallback != null) {
                f.this.mCallback.onBackPress();
            }
            f.this.finish();
            if (f.this.l != null) {
                f.this.l.dismiss();
            }
        }
    }

    /* JADX INFO: compiled from: BsSoundFieldPager.java */
    public class c implements RadioGroup.OnCheckedChangeListener {
        public c() {
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            f.this.q(radioGroup, i, true);
        }
    }

    /* JADX INFO: compiled from: BsSoundFieldPager.java */
    public static /* synthetic */ class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1594a;

        static {
            int[] iArr = new int[SpeakerLayoutMode.values().length];
            f1594a = iArr;
            try {
                iArr[SpeakerLayoutMode.SEAT_ROWS_2.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1594a[SpeakerLayoutMode.SEAT_ROWS_2_PICKUP_TRUCK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1594a[SpeakerLayoutMode.SEAT_ROWS_3_SUV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1594a[SpeakerLayoutMode.SEAT_ROWS_3_MPV.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: compiled from: BsSoundFieldPager.java */
    public class e implements b.b.g.q.h.c.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<f> f1595a;

        public e(f fVar) {
            this.f1595a = new WeakReference<>(fVar);
        }

        @Override // b.b.g.q.h.c.c
        public void a(int i, int i2) {
            b.b.m.c.a.a("BsSoundFieldPager", "onTouchStop", "positionX: " + i, "positionY: " + i2);
            this.f1595a.get().r(i, i2);
        }

        @Override // b.b.g.q.h.c.c
        public void b(int i, int i2) {
            this.f1595a.get().r(i, i2);
        }
    }

    public f(Context context, CustomViewDialog customViewDialog) {
        super(context);
        this.k = 0;
        this.n = false;
        this.o = -1;
        this.q = -1;
        this.r = true;
        this.l = customViewDialog;
        this.n = false;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.g.o.i.a.n(this.m);
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
        b.b.m.c.a.a("BsSoundFieldPager", "----initData---");
        this.f1587c.setText(context.getString(R.string.sound_field_bs_sound));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initDataBeforeLoadLayout(Context context) {
        SpeakerLayoutMode speakerLayoutMode;
        ((b.b.g.n.j.d) mo7getP()).l();
        b.b.g.o.h.a aVarH = ((b.b.g.n.j.d) mo7getP()).h();
        b.b.m.c.a.d("BsSoundFieldPager", "initDataBeforeLoadLayout", "config: " + aVarH);
        this.m = SpeakerLayoutMode.SEAT_ROWS_3_SUV;
        if (aVarH != null && (speakerLayoutMode = aVarH.f1344c) != null) {
            this.m = speakerLayoutMode;
        }
        b.b.m.c.a.d("BsSoundFieldPager", "initDataBeforeLoadLayout", "mSpeakerMode: " + this.m);
    }

    @Override // b.b.m.e.f
    public boolean initHandler() {
        return true;
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1585a = (ViewGroup) this.mRootView.findViewById(R.id.ll_title_dialog_common_title);
        this.f1587c = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        this.f1588d = this.mRootView.findViewById(R.id.iv_back_dialog_common_title);
        this.f1590f = (Button) this.mRootView.findViewById(R.id.btn_title_right_dialog_common_title);
        this.f1586b = (ViewGroup) this.mRootView.findViewById(R.id.rl_parent_bs_sound_field);
        int i = d.f1594a[this.m.ordinal()];
        if (i == 1 || i == 2) {
            this.i = this.mRootView.findViewById(R.id.ll_bg_dialog_bs_sound_field);
            this.g = (ImageView) this.mRootView.findViewById(R.id.iv_focus_bs_sound_field);
            this.f1589e = (UserRadioGroup) this.mRootView.findViewById(R.id.rg_bs_sound_field);
            this.h = (SoundFieldView) this.mRootView.findViewById(R.id.sfv_bs_sound_field);
        } else if (i == 3 || i == 4) {
            this.i = this.mRootView.findViewById(R.id.ll_bg_dialog_bs_sound_field_rows_3);
            this.g = (ImageView) this.mRootView.findViewById(R.id.iv_focus_bs_sound_field_rows_3);
            this.f1589e = (UserRadioGroup) this.mRootView.findViewById(R.id.rg_bs_sound_field_rows_3);
            this.h = (SoundFieldView) this.mRootView.findViewById(R.id.sfv_bs_sound_field_rows_3);
        }
        UserRadioGroup userRadioGroup = this.f1589e;
        if (userRadioGroup != null && hasTextOverView(userRadioGroup)) {
            changeWidth(this.f1589e, (int) this.f1589e.getResources().getDimension(R.dimen.dp_194));
        }
        this.f1590f.setOnClickListener(new a());
        this.f1588d.setOnClickListener(new b());
        if (this.j == null) {
            this.j = new e(this);
        }
        this.h.setOnSoundFieldChanhedCallback(this.j);
        this.h.setRangeOfWidthAndHeight(18, 18);
        EventBus.getDefault().register(this);
    }

    public final void l() {
        b.b.m.c.a.a("BsSoundFieldPager", "dialogToResetSoundFocus", "----dialogToResetSoundFocus---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        SimpleDialog.z(activity.getString(R.string.dilink_reset), activity.getString(R.string.msg_dialog_dyna_sound_focus), activity.getString(R.string.dialog_btn_ok), activity.getString(R.string.dialog_btn_cancel), 105).show(this.l.getFragmentManager(), "SimpleDialog");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void loadData() {
        super.loadData();
        this.r = !AppConfig.d();
        b.b.m.c.a.d("BsSoundFieldPager", "loadData", "SpeakerMode: " + this.m + ", leftRudder: " + this.r);
        n(this.m);
        int[] iArrI = ((b.b.g.n.j.d) mo7getP()).i();
        this.h.i(iArrI[0], iArrI[1]);
        int iJ = ((b.b.g.n.j.d) mo7getP()).j();
        b.b.m.c.a.d("BsSoundFieldPager", "loadData", "index: " + iJ);
        if (iJ == 3) {
            ((b.b.g.n.j.d) mo7getP()).o(3);
        }
        s(iJ);
    }

    public final int m(RadioGroup radioGroup, int i) {
        for (int i2 = 0; i2 < radioGroup.getChildCount(); i2++) {
            if (radioGroup.getChildAt(i2).getId() == i) {
                return i2;
            }
        }
        return 0;
    }

    public final void n(SpeakerLayoutMode speakerLayoutMode) {
        b.b.m.c.a.d("BsSoundFieldPager", "loadBySeatType---mode: " + speakerLayoutMode + ", leftRudder: " + this.r);
        int i = d.f1594a[speakerLayoutMode.ordinal()];
        if (i == 1 || i == 2) {
            this.i.setBackgroundResource(b.b.g.o.i.c.g());
        } else if (i == 3 || i == 4) {
            this.i.setBackgroundResource(b.b.g.o.i.c.h());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        this.n = true;
        b.b.m.f.d.d.c(f.class.getName());
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() == 105) {
            b.b.m.c.a.a("BsSoundFieldPager", "position: " + Arrays.toString(((b.b.g.n.j.d) mo7getP()).m()));
            this.o = -1;
            this.q = -1;
            this.h.l();
        }
    }

    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        setViewGroupRtl(this.f1585a, true);
        setViewGroupRtl(this.f1586b, true);
        setViewGroupRtl(this.h, false);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        setViewGroupRtl(this.mRootView, false);
        setViewGroupRtl(this.f1586b, false);
        setViewGroupRtl(this.h, false);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.j.d newP() {
        return new b.b.g.n.j.d();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void q(RadioGroup radioGroup, int i, boolean z) {
        if (this.k == i) {
            return;
        }
        int dimension = (int) this.f1587c.getContext().getResources().getDimension(R.dimen.dp_272);
        this.k = i;
        int iM = m(radioGroup, i);
        if (iM == 3) {
            this.h.setVisibility(0);
            this.g.setVisibility(8);
            this.f1590f.setVisibility(0);
            reloadTextViewWidth(this.f1587c, true, dimension);
        } else {
            this.h.setVisibility(8);
            this.g.setVisibility(0);
            this.f1590f.setVisibility(8);
            reloadTextViewWidth(this.f1587c, false, dimension);
        }
        int iV = b.b.g.o.i.a.v(this.m, iM);
        if (iV != 0) {
            this.g.setImageResource(iV);
        }
        b.b.m.c.a.a("BsSoundFieldPager", "onRadioButtonCheckedChanged", "index: " + iM, "fromUser: " + z);
        if (z) {
            ((b.b.g.n.j.d) mo7getP()).o(iM);
            View childAt = radioGroup.getChildAt(iM);
            if (childAt instanceof TextView) {
                EventBus.getDefault().post(new b.b.a.c.a(230006, ((TextView) childAt).getText().toString().trim()));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void r(int i, int i2) {
        if (this.o == i && this.q == i2) {
            return;
        }
        b.b.m.c.a.a("BsSoundFieldPager", "onSoundFieldPositionOperation", "positionX: " + i, "positionY: " + i2);
        this.o = i;
        this.q = i2;
        ((b.b.g.n.j.d) mo7getP()).n(this.o, this.q);
    }

    public final void s(int i) {
        b.b.m.c.a.a("BsSoundFieldPager", "setRadioGroupIndexChecked", ", index: " + i);
        this.f1589e.setOnCheckedChangeListener(null);
        UserRadioGroup userRadioGroup = this.f1589e;
        if (userRadioGroup != null && i < userRadioGroup.getChildCount()) {
            UserRadioGroup userRadioGroup2 = this.f1589e;
            userRadioGroup2.a(userRadioGroup2.getChildAt(i).getId(), false);
            UserRadioGroup userRadioGroup3 = this.f1589e;
            q(userRadioGroup3, userRadioGroup3.getChildAt(i).getId(), false);
        }
        this.f1589e.setOnCheckedChangeListener(new c());
    }
}
