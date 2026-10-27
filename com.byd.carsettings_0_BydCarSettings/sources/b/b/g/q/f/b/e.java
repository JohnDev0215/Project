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
import org.libpag.PAGFile;
import org.libpag.PAGImageView;

/* JADX INFO: compiled from: BsEqualizerPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends b.b.m.e.a<b.b.g.n.j.c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewGroup f1574a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1575b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f1576c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f1577d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ViewGroup f1578e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public PAGImageView f1579f;
    public PAGImageView g;
    public PAGImageView h;
    public UserRadioGroup i;
    public View j;
    public Button k;
    public EqualizerView l;
    public b.b.g.q.h.c.a m;
    public volatile int n;
    public CustomViewDialog o;

    /* JADX INFO: compiled from: BsEqualizerPager.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.b.m.g.c.a(200L)) {
                return;
            }
            e.this.p();
        }
    }

    /* JADX INFO: compiled from: BsEqualizerPager.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (e.this.mCallback != null) {
                e.this.mCallback.onBackPress();
            }
            e.this.finish();
            if (e.this.o != null) {
                e.this.o.dismiss();
            }
        }
    }

    /* JADX INFO: compiled from: BsEqualizerPager.java */
    public class c implements RadioGroup.OnCheckedChangeListener {
        public c() {
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            e.this.n(radioGroup, i);
        }
    }

    /* JADX INFO: compiled from: BsEqualizerPager.java */
    public class d implements b.b.g.q.h.c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<e> f1583a;

        public d(e eVar) {
            this.f1583a = new WeakReference<>(eVar);
        }

        @Override // b.b.g.q.h.c.a
        public void a(b.b.g.o.h.c cVar) {
            WeakReference<e> weakReference = this.f1583a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1583a.get().m(cVar);
        }
    }

    public e(Context context, CustomViewDialog customViewDialog) {
        super(context);
        this.n = 0;
        this.o = customViewDialog;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return AppConfig.d() ? R.layout.dialog_bs_sound_charact_r : R.layout.dialog_bs_sound_charact;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initData(Context context) {
        b.b.m.c.a.a(this.TAG, "---initData---context: " + context);
        this.f1575b.setText(context.getString(R.string.sound_charact_bs_sound));
        this.f1577d.setBackgroundResource(b.b.g.o.i.a.m());
        this.f1579f.setComposition(PAGFile.Load(getContext().getAssets(), "pag/classic_912x1088_bmp.pag"));
        this.f1579f.setRepeatCount(-1);
        this.g.setComposition(PAGFile.Load(getContext().getAssets(), "pag/rhythm_912x1088_bmp.pag"));
        this.g.setRepeatCount(-1);
        this.h.setComposition(PAGFile.Load(getContext().getAssets(), "pag/soft_912x1088_bmp.pag"));
        this.h.setRepeatCount(-1);
        int i = ((b.b.g.n.j.c) mo7getP()).i();
        if (i == 3) {
            this.f1578e.setVisibility(8);
        } else {
            this.f1578e.setVisibility(0);
        }
        s(i);
        this.l.h(((b.b.g.n.j.c) mo7getP()).h());
    }

    @Override // b.b.m.e.f
    public boolean initHandler() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initViews(Context context) {
        ((b.b.g.n.j.c) mo7getP()).j();
        this.f1574a = (ViewGroup) this.mRootView.findViewById(R.id.ll_title_dialog_common_title);
        this.f1575b = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        this.f1576c = this.mRootView.findViewById(R.id.iv_back_dialog_common_title);
        this.k = (Button) this.mRootView.findViewById(R.id.btn_title_right_dialog_common_title);
        this.f1577d = this.mRootView.findViewById(R.id.ll_bg_dialog_bs_sound_charact);
        this.f1578e = (ViewGroup) this.mRootView.findViewById(R.id.pag_parent_bs_sound_character);
        this.f1579f = (PAGImageView) this.mRootView.findViewById(R.id.pag_classics_bs_sound_character);
        this.g = (PAGImageView) this.mRootView.findViewById(R.id.pag_rhythm_bs_sound_character);
        this.h = (PAGImageView) this.mRootView.findViewById(R.id.pag_soft_bs_sound_character);
        this.i = (UserRadioGroup) this.mRootView.findViewById(R.id.rg_bs_sound_charact);
        this.j = this.mRootView.findViewById(R.id.rl_range_view_bs_sound_character_seekbar);
        this.l = (EqualizerView) this.mRootView.findViewById(R.id.ev_dialog_bs_sound_charact);
        UserRadioGroup userRadioGroup = this.i;
        if (userRadioGroup != null && hasTextOverView(userRadioGroup)) {
            changeWidth(this.i, (int) this.i.getResources().getDimension(R.dimen.dp_178));
        }
        ViewGroup.LayoutParams layoutParams = this.j.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            int dimension = ((int) this.i.getResources().getDimension(R.dimen.dp_24)) * (-1);
            marginLayoutParams.setMarginEnd(dimension);
            String string = d.g.a().v(context).toString();
            b.b.m.c.a.a(this.TAG, "---initViews---marginEnd: " + dimension + ", lngTag: " + string);
            if (string.startsWith("ar")) {
                marginLayoutParams.width = (int) this.i.getResources().getDimension(R.dimen.dp_72);
            }
            this.j.setLayoutParams(marginLayoutParams);
        }
        this.k.setOnClickListener(new a());
        this.f1576c.setOnClickListener(new b());
        if (this.m == null) {
            this.m = new d(this);
        }
        this.l.setOnFreqBandChangedCallback(this.m);
        EventBus.getDefault().register(this);
    }

    public final void l(int i, View view, View view2, View view3, View view4) {
        if (view == null || view2 == null || view3 == null) {
            return;
        }
        b.b.m.c.a.a(this.TAG, "changeBgSoundCharact", "checkedId: " + i + ", mBrand: " + this.mBrand);
        boolean z = i == R.id.rbtn_custom_bs_sound_charact;
        t(i);
        int dimension = (int) this.f1575b.getContext().getResources().getDimension(R.dimen.dp_272);
        if (z) {
            view2.setVisibility(0);
            view3.setVisibility(0);
            view4.setVisibility(0);
            this.k.setVisibility(0);
            this.f1578e.setVisibility(8);
            reloadTextViewWidth(this.f1575b, true, dimension);
            return;
        }
        view2.setVisibility(8);
        view3.setVisibility(8);
        view4.setVisibility(8);
        this.k.setVisibility(8);
        this.f1578e.setVisibility(0);
        reloadTextViewWidth(this.f1575b, false, dimension);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void m(b.b.g.o.h.c cVar) {
        b.b.m.c.a.a(this.TAG, "---changeFreqBand---freqBand = " + cVar);
        ((b.b.g.n.j.c) mo7getP()).m(cVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void n(RadioGroup radioGroup, int i) {
        if (this.n == i) {
            return;
        }
        this.n = i;
        int i2 = 0;
        while (true) {
            if (i2 >= this.i.getChildCount()) {
                i2 = -1;
                break;
            } else if (this.i.getChildAt(i2).getId() == i) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 == -1) {
            return;
        }
        b.b.m.c.a.a(this.TAG, "---checkRadioButton---index: " + i2);
        ((b.b.g.n.j.c) mo7getP()).n(i2);
        l(i, this.f1577d, this.k, this.l, this.j);
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
        this.m = null;
        b.b.m.f.d.d.c(e.class.getName());
        EventBus.getDefault().unregister(this);
        PAGImageView pAGImageView = this.f1579f;
        if (pAGImageView != null) {
            pAGImageView.clearAnimation();
        }
        PAGImageView pAGImageView2 = this.g;
        if (pAGImageView2 != null) {
            pAGImageView2.clearAnimation();
        }
        PAGImageView pAGImageView3 = this.h;
        if (pAGImageView3 != null) {
            pAGImageView3.clearAnimation();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() == 106) {
            r(((b.b.g.n.j.c) mo7getP()).l());
        }
    }

    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
        b.b.m.c.a.a(this.TAG, "---onHandlerUpdateUI---msg: " + message);
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        setViewGroupRtl(this.f1574a, true);
        setViewGroupRtl(this.f1577d, false);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        setViewGroupRtl(this.mRootView, false);
        setViewGroupRtl(this.f1577d, false);
    }

    public final void p() {
        b.b.m.c.a.a(this.TAG, "----dialogToResetSoundCharact---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        SimpleDialog.z(activity.getString(R.string.dilink_reset), activity.getString(R.string.msg_dialog_dyna_sound_charact), activity.getString(R.string.dialog_btn_ok), activity.getString(R.string.dialog_btn_cancel), 106).show(this.o.getFragmentManager(), "SimpleDialog");
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.j.c newP() {
        return new b.b.g.n.j.c();
    }

    public void r(Map<Integer, Integer> map) {
        this.l.h(map);
    }

    public final void s(int i) {
        b.b.m.c.a.a(this.TAG, "---setRadioGroupIndexChecked---index: " + i);
        UserRadioGroup userRadioGroup = this.i;
        if (userRadioGroup == null || i < 0 || i >= userRadioGroup.getChildCount()) {
            return;
        }
        int id = this.i.getChildAt(i).getId();
        l(id, this.f1577d, this.k, this.l, this.j);
        this.i.setOnCheckedChangeListener(null);
        this.i.check(id);
        this.i.setOnCheckedChangeListener(new c());
    }

    public final void t(int i) {
        PAGImageView pAGImageView = i == R.id.rbtn_classics_bs_sound_charact ? this.f1579f : i == R.id.rbtn_rhythm_bs_sound_charact ? this.g : i == R.id.rbtn_soft_bs_sound_charact ? this.h : null;
        int childCount = this.f1578e.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.f1578e.getChildAt(i2);
            if (childAt instanceof PAGImageView) {
                PAGImageView pAGImageView2 = (PAGImageView) childAt;
                if (childAt.equals(pAGImageView)) {
                    pAGImageView2.play();
                    pAGImageView2.setVisibility(0);
                } else {
                    pAGImageView2.pause();
                    pAGImageView2.setVisibility(8);
                }
            }
        }
    }

    @Override // b.b.m.e.f
    public void updateData(BydData bydData) {
        super.updateData(bydData);
        if (bydData == null || !(bydData instanceof BydAppInfo)) {
        }
    }
}
