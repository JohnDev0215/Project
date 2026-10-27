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

/* JADX INFO: compiled from: DynaSoundFieldPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class l extends b.b.m.e.a<b.b.g.n.j.h> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewGroup f1644a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewGroup f1645b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f1646c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f1647d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public UserRadioGroup f1648e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Button f1649f;
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

    /* JADX INFO: compiled from: DynaSoundFieldPager.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.b.m.g.c.a(200L)) {
                return;
            }
            l.this.m();
        }
    }

    /* JADX INFO: compiled from: DynaSoundFieldPager.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (l.this.mCallback != null) {
                l.this.mCallback.onBackPress();
            }
            l.this.finish();
            if (l.this.l != null) {
                l.this.l.dismiss();
            }
        }
    }

    /* JADX INFO: compiled from: DynaSoundFieldPager.java */
    public class c implements RadioGroup.OnCheckedChangeListener {
        public c() {
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            l.this.r(radioGroup, i, true);
        }
    }

    /* JADX INFO: compiled from: DynaSoundFieldPager.java */
    public static /* synthetic */ class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1653a;

        static {
            int[] iArr = new int[SpeakerLayoutMode.values().length];
            f1653a = iArr;
            try {
                iArr[SpeakerLayoutMode.SEAT_ROWS_2.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1653a[SpeakerLayoutMode.SEAT_ROWS_2_PICKUP_TRUCK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1653a[SpeakerLayoutMode.SEAT_ROWS_3_SUV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1653a[SpeakerLayoutMode.SEAT_ROWS_3_MPV.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: compiled from: DynaSoundFieldPager.java */
    public class e implements b.b.g.q.h.c.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<l> f1654a;

        public e(l lVar) {
            this.f1654a = new WeakReference<>(lVar);
        }

        @Override // b.b.g.q.h.c.c
        public void a(int i, int i2) {
            b.b.m.c.a.a(l.this.TAG, "onTouchStop", ", positionX: " + i + ", positionY: " + i2);
            this.f1654a.get().s(i, i2);
        }

        @Override // b.b.g.q.h.c.c
        public void b(int i, int i2) {
            this.f1654a.get().s(i, i2);
        }
    }

    public l(Context context, CustomViewDialog customViewDialog) {
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
        return b.b.g.o.e.m(this.m);
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
        b.b.m.c.a.a(this.TAG, "----initData---");
        this.f1646c.setText(context.getString(R.string.sound_field_focusing_dynaudio_sound));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initDataBeforeLoadLayout(Context context) {
        SpeakerLayoutMode speakerLayoutMode;
        ((b.b.g.n.j.h) mo7getP()).l();
        b.b.g.o.h.a aVarH = ((b.b.g.n.j.h) mo7getP()).h();
        b.b.m.c.a.d(this.TAG, "----initDataBeforeLoadLayout---config: " + aVarH);
        this.m = SpeakerLayoutMode.SEAT_ROWS_3_SUV;
        if (aVarH != null && (speakerLayoutMode = aVarH.f1344c) != null) {
            this.m = speakerLayoutMode;
        }
        b.b.m.c.a.d(this.TAG, "----initDataBeforeLoadLayout---mSpeakerMode: " + this.m);
    }

    @Override // b.b.m.e.f
    public boolean initHandler() {
        return true;
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1644a = (ViewGroup) this.mRootView.findViewById(R.id.ll_title_dialog_common_title);
        this.f1646c = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        this.f1647d = this.mRootView.findViewById(R.id.iv_back_dialog_common_title);
        this.f1649f = (Button) this.mRootView.findViewById(R.id.btn_title_right_dialog_common_title);
        this.f1645b = (ViewGroup) this.mRootView.findViewById(R.id.rl_parent_dyna_sound_field);
        int i = d.f1653a[this.m.ordinal()];
        if (i == 1 || i == 2) {
            this.i = this.mRootView.findViewById(R.id.ll_bg_dialog_dyna_sound_field);
            this.g = (ImageView) this.mRootView.findViewById(R.id.iv_focus_dyna_sound_field);
            this.f1648e = (UserRadioGroup) this.mRootView.findViewById(R.id.rg_dyna_sound_field);
            this.h = (SoundFieldView) this.mRootView.findViewById(R.id.sfv_dyna_sound_field);
        } else if (i == 3 || i == 4) {
            this.i = this.mRootView.findViewById(R.id.ll_bg_dialog_dyna_sound_field_rows_3);
            this.g = (ImageView) this.mRootView.findViewById(R.id.iv_focus_dyna_sound_field_rows_3);
            this.f1648e = (UserRadioGroup) this.mRootView.findViewById(R.id.rg_dyna_sound_field_rows_3);
            this.h = (SoundFieldView) this.mRootView.findViewById(R.id.sfv_dyna_sound_field_rows_3);
        }
        UserRadioGroup userRadioGroup = this.f1648e;
        if (userRadioGroup != null && hasTextOverView(userRadioGroup)) {
            changeWidth(this.f1648e, (int) this.f1648e.getResources().getDimension(R.dimen.dp_194));
        }
        this.f1649f.setOnClickListener(new a());
        this.f1647d.setOnClickListener(new b());
        if (this.j == null) {
            this.j = new e(this);
        }
        this.h.setOnSoundFieldChanhedCallback(this.j);
        this.h.setRangeOfWidthAndHeight(18, 18);
        EventBus.getDefault().register(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void loadData() {
        super.loadData();
        this.r = !AppConfig.d();
        b.b.m.c.a.d(this.TAG, "----loadData---mSpeakerMode: " + this.m + ", leftRudder: " + this.r);
        p(this.m);
        int[] iArrI = ((b.b.g.n.j.h) mo7getP()).i();
        this.h.i(iArrI[0], iArrI[1]);
        int iJ = ((b.b.g.n.j.h) mo7getP()).j();
        b.b.m.c.a.d(this.TAG, "----loadData---index: " + iJ);
        if (iJ == 5) {
            ((b.b.g.n.j.h) mo7getP()).o(5);
        }
        t(iJ);
    }

    public final void m() {
        b.b.m.c.a.a(this.TAG, "dialogToResetDynaSoundFocus", "----dialogToResetDynaSoundFocus---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        SimpleDialog.z(activity.getString(R.string.dilink_reset), activity.getString(R.string.msg_dialog_dyna_sound_focus), activity.getString(R.string.dialog_btn_ok), activity.getString(R.string.dialog_btn_cancel), 105).show(this.l.getFragmentManager(), "SimpleDialog");
    }

    public final int n(RadioGroup radioGroup, int i) {
        for (int i2 = 0; i2 < radioGroup.getChildCount(); i2++) {
            if (radioGroup.getChildAt(i2).getId() == i) {
                return i2;
            }
        }
        return 0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        this.n = true;
        b.b.m.f.d.d.c(l.class.getName());
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() == 105) {
            int[] iArrM = ((b.b.g.n.j.h) mo7getP()).m();
            b.b.m.c.a.a(this.TAG, "position: " + Arrays.toString(iArrM));
            this.o = -1;
            this.q = -1;
            this.h.l();
        }
    }

    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
        int i = message.arg1;
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        setViewGroupRtl(this.f1644a, true);
        setViewGroupRtl(this.f1645b, true);
        setViewGroupRtl(this.h, false);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        setViewGroupRtl(this.mRootView, false);
        setViewGroupRtl(this.f1645b, false);
        setViewGroupRtl(this.h, false);
    }

    public final void p(SpeakerLayoutMode speakerLayoutMode) {
        b.b.m.c.a.d(this.TAG, "----loadBySeatType---mode: " + speakerLayoutMode + ", leftRudder: " + this.r);
        int i = d.f1653a[speakerLayoutMode.ordinal()];
        if (i == 1 || i == 2) {
            this.i.setBackgroundResource(b.b.g.o.e.o());
        } else if (i == 3 || i == 4) {
            this.i.setBackgroundResource(b.b.g.o.e.p());
        }
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.j.h newP() {
        return new b.b.g.n.j.h();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void r(RadioGroup radioGroup, int i, boolean z) {
        if (this.k == i) {
            return;
        }
        int dimension = (int) this.f1646c.getContext().getResources().getDimension(R.dimen.dp_272);
        this.k = i;
        int iN = n(radioGroup, i);
        if (iN == 5) {
            this.h.setVisibility(0);
            this.g.setVisibility(8);
            this.f1649f.setVisibility(0);
            reloadTextViewWidth(this.f1646c, true, dimension);
        } else {
            this.h.setVisibility(8);
            this.g.setVisibility(0);
            this.f1649f.setVisibility(8);
            reloadTextViewWidth(this.f1646c, false, dimension);
        }
        int iD = b.b.g.o.e.d(this.m, i);
        if (iD != 0) {
            this.g.setImageResource(iD);
        }
        b.b.m.c.a.a(this.TAG, "onRadioButtonCheckedChanged", ", index: " + iN + ", fromUser: " + z);
        if (z) {
            ((b.b.g.n.j.h) mo7getP()).o(iN);
            View childAt = radioGroup.getChildAt(iN);
            if (childAt instanceof TextView) {
                EventBus.getDefault().post(new b.b.a.c.a(230006, ((TextView) childAt).getText().toString().trim()));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void s(int i, int i2) {
        if (this.o == i && this.q == i2) {
            return;
        }
        b.b.m.c.a.a(this.TAG, "onSoundFieldPositionOperation", ", positionX: " + i + ", positionY: " + i2);
        this.o = i;
        this.q = i2;
        ((b.b.g.n.j.h) mo7getP()).n(this.o, this.q);
    }

    public final void t(int i) {
        b.b.m.c.a.a(this.TAG, "setRadioGroupIndexChecked", ", index: " + i);
        this.f1648e.setOnCheckedChangeListener(null);
        UserRadioGroup userRadioGroup = this.f1648e;
        if (userRadioGroup != null && i < userRadioGroup.getChildCount()) {
            UserRadioGroup userRadioGroup2 = this.f1648e;
            userRadioGroup2.a(userRadioGroup2.getChildAt(i).getId(), false);
            UserRadioGroup userRadioGroup3 = this.f1648e;
            r(userRadioGroup3, userRadioGroup3.getChildAt(i).getId(), false);
        }
        this.f1648e.setOnCheckedChangeListener(new c());
    }
}
