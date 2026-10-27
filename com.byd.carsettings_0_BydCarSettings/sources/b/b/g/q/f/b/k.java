package b.b.g.q.f.b;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import b.b.g.p.d;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.BydAppInfo;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.carsettings.ui.dialog.SimpleDialog;
import com.byd.carsettings.ui.widget.EqualizerView;
import com.byd.carsettings.ui.widget.UserRadioGroup;
import com.byd.mvp.model.BydData;
import com.byd.mvp.utils.AppConfig;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: DynaEqualizerPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class k extends b.b.m.e.a<b.b.g.n.j.g> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewGroup f1633a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1634b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f1635c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f1636d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public UserRadioGroup f1637e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f1638f;
    public Button g;
    public EqualizerView h;
    public b.b.g.q.h.c.a i;
    public volatile int j;
    public CustomViewDialog k;

    /* JADX INFO: compiled from: DynaEqualizerPager.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.b.m.g.c.a(200L)) {
                return;
            }
            k.this.p();
        }
    }

    /* JADX INFO: compiled from: DynaEqualizerPager.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (k.this.mCallback != null) {
                k.this.mCallback.onBackPress();
            }
            k.this.finish();
            if (k.this.k != null) {
                k.this.k.dismiss();
            }
        }
    }

    /* JADX INFO: compiled from: DynaEqualizerPager.java */
    public class c implements RadioGroup.OnCheckedChangeListener {
        public c() {
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            k.this.n(radioGroup, i);
        }
    }

    /* JADX INFO: compiled from: DynaEqualizerPager.java */
    public class d implements b.b.g.q.h.c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<k> f1642a;

        public d(k kVar) {
            this.f1642a = new WeakReference<>(kVar);
        }

        @Override // b.b.g.q.h.c.a
        public void a(b.b.g.o.h.c cVar) {
            WeakReference<k> weakReference = this.f1642a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1642a.get().m(cVar);
        }
    }

    public k(Context context, CustomViewDialog customViewDialog) {
        super(context);
        this.j = 0;
        this.k = customViewDialog;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return AppConfig.d() ? R.layout.dialog_sound_charact_r : R.layout.dialog_sound_charact;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initData(Context context) {
        b.b.m.c.a.a(this.TAG, "---initData---context: " + context);
        this.f1634b.setText(context.getString(R.string.sound_charact_dynaudio_sound));
        s(((b.b.g.n.j.g) mo7getP()).i());
        this.h.h(((b.b.g.n.j.g) mo7getP()).h());
    }

    @Override // b.b.m.e.f
    public boolean initHandler() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initViews(Context context) {
        ((b.b.g.n.j.g) mo7getP()).j();
        this.f1633a = (ViewGroup) this.mRootView.findViewById(R.id.ll_title_dialog_common_title);
        this.f1634b = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        this.f1635c = this.mRootView.findViewById(R.id.iv_back_dialog_common_title);
        this.g = (Button) this.mRootView.findViewById(R.id.btn_title_right_dialog_common_title);
        this.f1636d = this.mRootView.findViewById(R.id.ll_bg_dialog_sound_charact);
        this.f1637e = (UserRadioGroup) this.mRootView.findViewById(R.id.rg_dyna_sound_charact);
        this.f1638f = this.mRootView.findViewById(R.id.rl_range_view_sound_character_seekbar);
        this.h = (EqualizerView) this.mRootView.findViewById(R.id.ev_dialog_dyna_sound_charact);
        UserRadioGroup userRadioGroup = this.f1637e;
        if (userRadioGroup != null && hasTextOverView(userRadioGroup)) {
            changeWidth(this.f1637e, (int) this.f1637e.getResources().getDimension(R.dimen.dp_178));
        }
        ViewGroup.LayoutParams layoutParams = this.f1638f.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            int dimension = ((int) this.f1637e.getResources().getDimension(R.dimen.dp_20)) * (-1);
            marginLayoutParams.setMarginEnd(dimension);
            String string = d.g.a().v(context).toString();
            b.b.m.c.a.a(this.TAG, "---initViews---marginEnd: " + dimension + ", lngTag: " + string);
            if (string.startsWith("ar")) {
                marginLayoutParams.width = (int) this.f1637e.getResources().getDimension(R.dimen.dp_72);
            }
            this.f1638f.setLayoutParams(marginLayoutParams);
        }
        this.g.setOnClickListener(new a());
        this.f1635c.setOnClickListener(new b());
        if (this.i == null) {
            this.i = new d(this);
        }
        this.h.setOnFreqBandChangedCallback(this.i);
        EventBus.getDefault().register(this);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void l(int i, View view, View view2, View view3, View view4) {
        boolean z;
        if (view == null || view2 == null || view3 == null) {
            return;
        }
        b.b.m.c.a.a(this.TAG, "---changeBgSoundCharact---checkedId: " + i + ", mBrand: " + this.mBrand);
        switch (i) {
            case R.id.rbtn_custom_dyna_sound_charact /* 2131297798 */:
                view.setBackgroundResource(b.b.g.o.e.a());
                z = true;
                break;
            case R.id.rbtn_dynamic_dyna_sound_charact /* 2131297807 */:
                view.setBackgroundResource(b.b.g.o.e.b());
                z = false;
                break;
            case R.id.rbtn_original_dyna_sound_charact /* 2131297841 */:
                view.setBackgroundResource(b.b.g.o.e.n());
                z = false;
                break;
            case R.id.rbtn_soft_dyna_sound_charact /* 2131297854 */:
                view.setBackgroundResource(b.b.g.o.e.q());
                z = false;
                break;
            case R.id.rbtn_speech_dyna_sound_charact /* 2131297855 */:
                view.setBackgroundResource(b.b.g.o.e.r());
                z = false;
                break;
            default:
                z = false;
                break;
        }
        int dimension = (int) this.f1634b.getContext().getResources().getDimension(R.dimen.dp_272);
        if (z) {
            view2.setVisibility(0);
            view3.setVisibility(0);
            view4.setVisibility(0);
            this.g.setVisibility(0);
            reloadTextViewWidth(this.f1634b, true, dimension);
            return;
        }
        view2.setVisibility(8);
        view3.setVisibility(8);
        view4.setVisibility(8);
        this.g.setVisibility(8);
        reloadTextViewWidth(this.f1634b, false, dimension);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void m(b.b.g.o.h.c cVar) {
        b.b.m.c.a.a(this.TAG, "---changeFreqBand---freqBand = " + cVar);
        ((b.b.g.n.j.g) mo7getP()).m(cVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void n(RadioGroup radioGroup, int i) {
        if (this.j == i) {
            return;
        }
        this.j = i;
        int i2 = 0;
        while (true) {
            if (i2 >= this.f1637e.getChildCount()) {
                i2 = -1;
                break;
            } else if (this.f1637e.getChildAt(i2).getId() == i) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 == -1) {
            return;
        }
        b.b.m.c.a.a(this.TAG, "---checkRadioButton---index: " + i2);
        ((b.b.g.n.j.g) mo7getP()).n(i2);
        l(i, this.f1636d, this.g, this.h, this.f1638f);
        View childAt = radioGroup.getChildAt(i2);
        if (childAt instanceof TextView) {
            EventBus.getDefault().post(new b.b.a.c.a(230007, ((TextView) childAt).getText().toString().trim()));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        this.i = null;
        b.b.m.f.d.d.c(k.class.getName());
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() == 106) {
            r(((b.b.g.n.j.g) mo7getP()).l());
        }
    }

    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
        b.b.m.c.a.a(this.TAG, "---onHandlerUpdateUI---msg: " + message);
        int i = message.arg1;
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        setViewGroupRtl(this.f1633a, true);
        setViewGroupRtl(this.f1636d, false);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        setViewGroupRtl(this.mRootView, false);
        setViewGroupRtl(this.f1636d, false);
    }

    public final void p() {
        b.b.m.c.a.a(this.TAG, "----dialogToResetDynaSoundCharact---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        SimpleDialog.z(activity.getString(R.string.dilink_reset), activity.getString(R.string.msg_dialog_dyna_sound_charact), activity.getString(R.string.dialog_btn_ok), activity.getString(R.string.dialog_btn_cancel), 106).show(this.k.getFragmentManager(), "SimpleDialog");
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.j.g newP() {
        return new b.b.g.n.j.g();
    }

    public void r(Map<Integer, Integer> map) {
        this.h.h(map);
    }

    public final void s(int i) {
        b.b.m.c.a.a(this.TAG, "---setRadioGroupIndexChecked---index: " + i);
        UserRadioGroup userRadioGroup = this.f1637e;
        if (userRadioGroup == null || i < 0 || i >= userRadioGroup.getChildCount()) {
            return;
        }
        int id = this.f1637e.getChildAt(i).getId();
        l(id, this.f1636d, this.g, this.h, this.f1638f);
        this.f1637e.setOnCheckedChangeListener(null);
        this.f1637e.check(id);
        this.f1637e.setOnCheckedChangeListener(new c());
    }

    @Override // b.b.m.e.f
    public void updateData(BydData bydData) {
        super.updateData(bydData);
        if (bydData == null || !(bydData instanceof BydAppInfo)) {
        }
    }
}
