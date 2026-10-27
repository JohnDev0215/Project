package c.a.d;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.byd.widget.WheelView;
import com.byd.widget.segmentedbutton.SegmentedButtonGroup;
import com.bydux.api.R;
import com.bydux.api.common.views.window.ToastUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: DialogUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static List<Object> f1596a = new ArrayList(4);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static List<String> f1597b = new ArrayList(4);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static HashMap<String, Object> f1598c = new HashMap<>();

    /* JADX INFO: compiled from: DialogUtils.java */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ WheelView f1599b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ WheelView f1600c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ SegmentedButtonGroup f1601d;
        public final /* synthetic */ CheckBox[] e;
        public final /* synthetic */ View.OnClickListener f;

        public a(WheelView wheelView, WheelView wheelView2, SegmentedButtonGroup segmentedButtonGroup, CheckBox[] checkBoxArr, View.OnClickListener onClickListener) {
            this.f1599b = wheelView;
            this.f1600c = wheelView2;
            this.f1601d = segmentedButtonGroup;
            this.e = checkBoxArr;
            this.f = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            view.setTag((this.f1599b.getSelectedText() + "|" + this.f1600c.getSelectedText()) + "&" + f.l(this.f1601d, this.e));
            this.f.onClick(view);
        }
    }

    /* JADX INFO: compiled from: DialogUtils.java */
    public class b implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CheckBox[] f1602a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f1603b;

        public b(CheckBox[] checkBoxArr, String str) {
            this.f1602a = checkBoxArr;
            this.f1603b = str;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            while (i < this.f1602a.length) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.f1602a[i].isChecked() ? "1" : "0");
                sb.append(this.f1602a.length + (-1) == i ? "" : "|");
                stringBuffer.append(sb.toString());
                i++;
            }
            f.f1598c.put(this.f1603b + "_strCycle", stringBuffer.toString());
        }
    }

    /* JADX INFO: compiled from: DialogUtils.java */
    public class c implements SegmentedButtonGroup.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ LinearLayout f1604a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ TextView f1605b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Context f1606c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f1607d;
        public final /* synthetic */ CheckBox[] e;

        public c(LinearLayout linearLayout, TextView textView, Context context, String str, CheckBox[] checkBoxArr) {
            this.f1604a = linearLayout;
            this.f1605b = textView;
            this.f1606c = context;
            this.f1607d = str;
            this.e = checkBoxArr;
        }

        @Override // com.byd.widget.segmentedbutton.SegmentedButtonGroup.f
        public void a(View view, View view2, int i) {
            int i2 = 0;
            if (i == 0) {
                this.f1604a.setVisibility(8);
                this.f1605b.setVisibility(0);
                this.f1605b.setText(this.f1606c.getResources().getString(R.string.day_select_tips_oneshot));
                f.f1598c.put(this.f1607d + "_strCycle", "0|0|0|0|0|0|0");
                return;
            }
            if (i == 1) {
                this.f1604a.setVisibility(8);
                this.f1605b.setVisibility(0);
                this.f1605b.setText(this.f1606c.getResources().getString(R.string.day_select_tips_everyday));
                f.f1598c.put(this.f1607d + "_strCycle", "1|1|1|1|1|1|1");
                return;
            }
            this.f1604a.setVisibility(0);
            this.f1605b.setVisibility(8);
            StringBuffer stringBuffer = new StringBuffer();
            while (i2 < this.e.length) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.e[i2].isChecked() ? "1" : "0");
                sb.append(this.e.length - 1 == i2 ? "" : "|");
                stringBuffer.append(sb.toString());
                i2++;
            }
            f.f1598c.put(this.f1607d + "_strCycle", stringBuffer.toString());
        }
    }

    /* JADX INFO: compiled from: DialogUtils.java */
    public class d implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ WheelView f1608a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ WheelView f1609b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ TextView f1610c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f1611d;

        public d(WheelView wheelView, WheelView wheelView2, TextView textView, String str) {
            this.f1608a = wheelView;
            this.f1609b = wheelView2;
            this.f1610c = textView;
            this.f1611d = str;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f1608a.setAlpha(z ? 0.65f : 1.0f);
            this.f1609b.setAlpha(z ? 0.65f : 1.0f);
            this.f1610c.setAlpha(z ? 0.65f : 1.0f);
            f.f1598c.put(this.f1611d + "_cbFull", Boolean.valueOf(z));
        }
    }

    /* JADX INFO: compiled from: DialogUtils.java */
    public class e implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ CheckBox f1612b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ WheelView f1613c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ WheelView f1614d;
        public final /* synthetic */ WheelView e;
        public final /* synthetic */ WheelView f;
        public final /* synthetic */ Context g;
        public final /* synthetic */ SegmentedButtonGroup h;
        public final /* synthetic */ CheckBox[] i;
        public final /* synthetic */ View.OnClickListener j;

        public e(CheckBox checkBox, WheelView wheelView, WheelView wheelView2, WheelView wheelView3, WheelView wheelView4, Context context, SegmentedButtonGroup segmentedButtonGroup, CheckBox[] checkBoxArr, View.OnClickListener onClickListener) {
            this.f1612b = checkBox;
            this.f1613c = wheelView;
            this.f1614d = wheelView2;
            this.e = wheelView3;
            this.f = wheelView4;
            this.g = context;
            this.h = segmentedButtonGroup;
            this.i = checkBoxArr;
            this.j = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!this.f1612b.isChecked() && this.f1613c.getSelectedText().equals(this.f1614d.getSelectedText()) && this.e.getSelectedText().equals(this.f.getSelectedText())) {
                ToastUtils.show(this.g, R.string.plan_time_reset);
                return;
            }
            String str = this.f1613c.getSelectedText() + "|" + this.e.getSelectedText() + "|" + this.f1614d.getSelectedText() + "|" + this.f.getSelectedText();
            String str2 = this.f1612b.isChecked() ? "1" : "0";
            view.setTag(str + "&" + f.l(this.h, this.i) + "&" + str2);
            this.j.onClick(view);
        }
    }

    /* JADX INFO: renamed from: c.a.d.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DialogUtils.java */
    public class C0046f implements WheelView.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CheckBox f1615a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ WheelView f1616b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ WheelView f1617c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ WheelView f1618d;
        public final /* synthetic */ WheelView e;
        public final /* synthetic */ TextView f;
        public final /* synthetic */ String g;

        public C0046f(CheckBox checkBox, WheelView wheelView, WheelView wheelView2, WheelView wheelView3, WheelView wheelView4, TextView textView, String str) {
            this.f1615a = checkBox;
            this.f1616b = wheelView;
            this.f1617c = wheelView2;
            this.f1618d = wheelView3;
            this.e = wheelView4;
            this.f = textView;
            this.g = str;
        }

        @Override // com.byd.widget.WheelView.d
        public void a(View view, int i, String str) {
            try {
                if (this.f1615a != null && (view.getId() == this.f1616b.getId() || view.getId() == this.f1617c.getId())) {
                    this.f1615a.setChecked(false);
                }
                f.e(this.f1618d, this.e, this.f1616b, this.f1617c, this.f, this.g);
            } catch (Exception e) {
                Log.e("smart_charge", e.getMessage());
            }
        }
    }

    /* JADX INFO: compiled from: DialogUtils.java */
    public class g implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ WheelView f1619a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ WheelView f1620b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ WheelView f1621c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ WheelView f1622d;
        public final /* synthetic */ View e;
        public final /* synthetic */ String f;

        public g(WheelView wheelView, WheelView wheelView2, WheelView wheelView3, WheelView wheelView4, View view, String str) {
            this.f1619a = wheelView;
            this.f1620b = wheelView2;
            this.f1621c = wheelView3;
            this.f1622d = wheelView4;
            this.e = view;
            this.f = str;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f1619a.setEnabled(!z);
            this.f1620b.setEnabled(!z);
            this.f1621c.setEnabled(!z);
            this.f1622d.setEnabled(!z);
            this.e.setAlpha(z ? 0.65f : 1.0f);
            f.f1598c.put(this.f + "_cbFull", Boolean.valueOf(z));
        }
    }

    /* JADX INFO: compiled from: DialogUtils.java */
    public class h implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ WheelView f1623b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ WheelView f1624c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ WheelView f1625d;
        public final /* synthetic */ WheelView e;
        public final /* synthetic */ CheckBox f;
        public final /* synthetic */ View.OnClickListener g;

        public h(WheelView wheelView, WheelView wheelView2, WheelView wheelView3, WheelView wheelView4, CheckBox checkBox, View.OnClickListener onClickListener) {
            this.f1623b = wheelView;
            this.f1624c = wheelView2;
            this.f1625d = wheelView3;
            this.e = wheelView4;
            this.f = checkBox;
            this.g = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            view.setTag((this.f1623b.getSelectedText() + "|" + this.f1624c.getSelectedText() + "|" + this.f1625d.getSelectedText() + "|" + this.e.getSelectedText()) + "&" + ((this.f1623b.getSelectedText().equals(this.f1625d.getSelectedText()) && this.f1624c.getSelectedText().equals(this.e.getSelectedText())) ? "1" : this.f.isChecked() ? "1" : "0"));
            this.g.onClick(view);
        }
    }

    public static boolean c(Object obj) {
        if (!(obj instanceof c.a.e.a)) {
            return false;
        }
        ((c.a.e.a) obj).e();
        return true;
    }

    public static void d() {
        int i = 0;
        while (i < f1596a.size()) {
            Object obj = f1596a.get(i);
            if (c(obj)) {
                f1596a.remove(obj);
                i--;
            }
            i++;
        }
    }

    public static void e(WheelView wheelView, WheelView wheelView2, WheelView wheelView3, WheelView wheelView4, TextView textView, String str) {
        int i;
        int i2;
        int i3;
        int i4;
        if (TextUtils.isEmpty(wheelView.getSelectedText())) {
            i = 0;
        } else {
            i = Integer.parseInt(wheelView.getSelectedText());
            f1598c.put(str + "_defaultH", Integer.valueOf(i));
        }
        if (TextUtils.isEmpty(wheelView2.getSelectedText())) {
            i2 = 0;
        } else {
            i2 = Integer.parseInt(wheelView2.getSelectedText());
            f1598c.put(str + "_defaultMin", Integer.valueOf(i2));
        }
        if (wheelView3 == null || TextUtils.isEmpty(wheelView3.getSelectedText())) {
            i3 = 0;
        } else {
            i3 = Integer.parseInt(wheelView3.getSelectedText());
            f1598c.put(str + "_defaultHE", Integer.valueOf(i3));
        }
        if (wheelView4 == null || TextUtils.isEmpty(wheelView4.getSelectedText())) {
            i4 = 0;
        } else {
            i4 = Integer.parseInt(wheelView4.getSelectedText());
            f1598c.put(str + "_defaultMinE", Integer.valueOf(i4));
        }
        if (textView != null) {
            if ((i * 60) + i2 > (i3 * 60) + i4) {
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
        }
    }

    public static c.a.e.a f(Context context, String str, String str2, String str3, int i, int i2, String str4, String str5, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, boolean z, boolean z2, View view) {
        c.a.e.a aVar = new c.a.e.a(context);
        aVar.s(str);
        aVar.u(str2);
        aVar.r(str3, i, i2);
        aVar.q(str4);
        aVar.o(str5);
        aVar.n(onClickListener2);
        aVar.p(onClickListener);
        aVar.j(z);
        aVar.k(z2);
        aVar.v(view);
        f1596a.add(aVar);
        r();
        return aVar;
    }

    public static c.a.e.a g(Context context, String str, String str2, String str3, String str4, String str5, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        return f(context, str, str2, str3, 17, context.getResources().getDimensionPixelSize(R.dimen.sp_20), str4, str5, onClickListener, onClickListener2, true, true, null);
    }

    public static c.a.e.a h(Context context, String str, String str2, String str3, String str4, String str5, String str6, boolean z, String str7, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        f1598c.put(str + "_defaultH", Integer.valueOf(Integer.parseInt(str3)));
        f1598c.put(str + "_defaultMin", Integer.valueOf(Integer.parseInt(str4)));
        f1598c.put(str + "_defaultHE", Integer.valueOf(Integer.parseInt(str5)));
        f1598c.put(str + "_defaultMinE", Integer.valueOf(Integer.parseInt(str6)));
        f1598c.put(str + "_strCycle", str7);
        f1598c.put(str + "_cbFull", Boolean.valueOf(z));
        View viewInflate = LayoutInflater.from(context).inflate(c.a.d.c.a(R.layout.dialog_time_charge_select), (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        viewInflate.setLayoutParams(layoutParams);
        WheelView wheelView = (WheelView) viewInflate.findViewById(R.id.wheelView_h);
        WheelView wheelView2 = (WheelView) viewInflate.findViewById(R.id.wheelView_h_e);
        WheelView wheelView3 = (WheelView) viewInflate.findViewById(R.id.wheelView_m);
        WheelView wheelView4 = (WheelView) viewInflate.findViewById(R.id.wheelView_m_e);
        CheckBox checkBox = (CheckBox) viewInflate.findViewById(R.id.cb_full);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_next_day);
        p(wheelView, wheelView3, wheelView2, wheelView4, checkBox, textView, str);
        q(wheelView, wheelView3, str3, str4, 5);
        q(wheelView2, wheelView4, str5, str6, 5);
        o(wheelView, wheelView3, wheelView2, wheelView4, textView);
        CheckBox[] checkBoxArr = new CheckBox[7];
        SegmentedButtonGroup segmentedButtonGroup = (SegmentedButtonGroup) viewInflate.findViewById(R.id.rg_main);
        n(context, viewInflate, checkBoxArr, segmentedButtonGroup, str7, str);
        checkBox.setOnCheckedChangeListener(new d(wheelView2, wheelView4, textView, str));
        checkBox.setChecked(z);
        return k(context, str, str2, context.getResources().getString(R.string.sure), context.getResources().getString(R.string.cancel), new e(checkBox, wheelView, wheelView2, wheelView3, wheelView4, context, segmentedButtonGroup, checkBoxArr, onClickListener), onClickListener2, false, viewInflate);
    }

    public static c.a.e.a i(Context context, String str, String str2, String str3, String str4, String str5, String str6, boolean z, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        f1598c.put(str + "_defaultH", Integer.valueOf(Integer.parseInt(str3)));
        f1598c.put(str + "_defaultMin", Integer.valueOf(Integer.parseInt(str4)));
        f1598c.put(str + "_defaultHE", Integer.valueOf(Integer.parseInt(str5)));
        f1598c.put(str + "_defaultMinE", Integer.valueOf(Integer.parseInt(str6)));
        f1598c.put(str + "_cbFull", Boolean.valueOf(z));
        View viewInflate = LayoutInflater.from(context).inflate(c.a.d.c.a(R.layout.dialog_time_price_select), (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        viewInflate.setLayoutParams(layoutParams);
        WheelView wheelView = (WheelView) viewInflate.findViewById(R.id.wheelView_h);
        WheelView wheelView2 = (WheelView) viewInflate.findViewById(R.id.wheelView_h_e);
        WheelView wheelView3 = (WheelView) viewInflate.findViewById(R.id.wheelView_m);
        WheelView wheelView4 = (WheelView) viewInflate.findViewById(R.id.wheelView_m_e);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_next_day);
        p(wheelView, wheelView3, wheelView2, wheelView4, null, textView, str);
        q(wheelView, wheelView3, str3, str4, 30);
        q(wheelView2, wheelView4, str5, str6, 30);
        o(wheelView, wheelView3, wheelView2, wheelView4, textView);
        CheckBox checkBox = (CheckBox) viewInflate.findViewById(R.id.ch_no_price_discount);
        checkBox.setOnCheckedChangeListener(new g(wheelView, wheelView2, wheelView3, wheelView4, viewInflate.findViewById(R.id.ll_wheelview), str));
        checkBox.setChecked(z);
        return k(context, str, str2, context.getResources().getString(R.string.sure), context.getResources().getString(R.string.cancel), new h(wheelView, wheelView3, wheelView2, wheelView4, checkBox, onClickListener), onClickListener2, true, viewInflate);
    }

    public static c.a.e.a j(Context context, String str, String str2, String str3, String str4, String str5, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        f1598c.put(str + "_defaultH", Integer.valueOf(Integer.parseInt(str3)));
        f1598c.put(str + "_defaultMin", Integer.valueOf(Integer.parseInt(str4)));
        f1598c.put(str + "_strCycle", str5);
        View viewInflate = LayoutInflater.from(context).inflate(c.a.d.c.a(R.layout.dialog_time_select), (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        viewInflate.setLayoutParams(layoutParams);
        WheelView wheelView = (WheelView) viewInflate.findViewById(R.id.wheelView_h);
        WheelView wheelView2 = (WheelView) viewInflate.findViewById(R.id.wheelView_m);
        p(wheelView, wheelView2, null, null, null, null, str);
        q(wheelView, wheelView2, str3, str4, 5);
        CheckBox[] checkBoxArr = new CheckBox[7];
        SegmentedButtonGroup segmentedButtonGroup = (SegmentedButtonGroup) viewInflate.findViewById(R.id.rg_main);
        n(context, viewInflate, checkBoxArr, segmentedButtonGroup, str5, str);
        return k(context, str, str2, context.getResources().getString(R.string.sure), context.getResources().getString(R.string.cancel), new a(wheelView, wheelView2, segmentedButtonGroup, checkBoxArr, onClickListener), onClickListener2, true, viewInflate);
    }

    public static c.a.e.a k(Context context, String str, String str2, String str3, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, boolean z, View view) {
        return f(context, str, str2, null, 17, context.getResources().getDimensionPixelSize(R.dimen.sp_20), str3, str4, onClickListener, onClickListener2, z, true, view);
    }

    public static String l(SegmentedButtonGroup segmentedButtonGroup, CheckBox[] checkBoxArr) {
        String str = "1|1|1|1|1|1|1";
        if (segmentedButtonGroup.getPosition() == 0) {
            str = "0|0|0|0|0|0|0";
        } else if (segmentedButtonGroup.getPosition() == 1) {
            i = 1;
        } else {
            String string = "";
            int i = 0;
            while (i < checkBoxArr.length) {
                StringBuilder sb = new StringBuilder();
                sb.append(string);
                sb.append(checkBoxArr[i].isChecked() ? "1" : "0");
                sb.append(checkBoxArr.length - 1 == i ? "" : "|");
                string = sb.toString();
                i++;
            }
            i = "0|0|0|0|0|0|0".equals(string) ? 0 : "1|1|1|1|1|1|1".equals(string) ? 1 : 2;
            str = string;
        }
        return str + "&" + i;
    }

    public static String m(Object obj) {
        return obj instanceof c.a.e.a ? ((c.a.e.a) obj).f() : "";
    }

    public static void n(Context context, View view, CheckBox[] checkBoxArr, SegmentedButtonGroup segmentedButtonGroup, String str, String str2) {
        int i = 0;
        checkBoxArr[0] = (CheckBox) view.findViewById(R.id.cb_7);
        checkBoxArr[1] = (CheckBox) view.findViewById(R.id.cb_1);
        checkBoxArr[2] = (CheckBox) view.findViewById(R.id.cb_2);
        checkBoxArr[3] = (CheckBox) view.findViewById(R.id.cb_3);
        checkBoxArr[4] = (CheckBox) view.findViewById(R.id.cb_4);
        checkBoxArr[5] = (CheckBox) view.findViewById(R.id.cb_5);
        checkBoxArr[6] = (CheckBox) view.findViewById(R.id.cb_6);
        for (CheckBox checkBox : checkBoxArr) {
            checkBox.setOnCheckedChangeListener(new b(checkBoxArr, str2));
        }
        segmentedButtonGroup.setOnPositionChangedListener(new c((LinearLayout) view.findViewById(R.id.ll_cb_1), (TextView) view.findViewById(R.id.tv_date_select_tips), context, str2, checkBoxArr));
        if ("0|0|0|0|0|0|0".equals(str)) {
            segmentedButtonGroup.q(0, true);
            for (CheckBox checkBox2 : checkBoxArr) {
                checkBox2.setChecked(false);
            }
            return;
        }
        if ("1|1|1|1|1|1|1".equals(str)) {
            segmentedButtonGroup.q(1, true);
            while (i < checkBoxArr.length) {
                checkBoxArr[i].setChecked(true);
                i++;
            }
            return;
        }
        String[] strArrSplit = str.split("\\|");
        while (i < strArrSplit.length) {
            checkBoxArr[i].setChecked("1".equals(strArrSplit[i]));
            i++;
        }
        segmentedButtonGroup.q(2, true);
    }

    public static void o(WheelView wheelView, WheelView wheelView2, WheelView wheelView3, WheelView wheelView4, TextView textView) {
        int i = Integer.parseInt(wheelView.getSelectedText());
        int i2 = Integer.parseInt(wheelView3.getSelectedText());
        if ((i * 60) + Integer.parseInt(wheelView2.getSelectedText()) > (i2 * 60) + Integer.parseInt(wheelView4.getSelectedText())) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
    }

    public static void p(WheelView wheelView, WheelView wheelView2, WheelView wheelView3, WheelView wheelView4, CheckBox checkBox, TextView textView, String str) {
        C0046f c0046f = new C0046f(checkBox, wheelView3, wheelView4, wheelView, wheelView2, textView, str);
        if (wheelView != null) {
            wheelView.setOnSelectListener(c0046f);
        }
        if (wheelView3 != null) {
            wheelView3.setOnSelectListener(c0046f);
        }
        if (wheelView2 != null) {
            wheelView2.setOnSelectListener(c0046f);
        }
        if (wheelView4 != null) {
            wheelView4.setOnSelectListener(c0046f);
        }
    }

    public static void q(WheelView wheelView, WheelView wheelView2, String str, String str2, int i) {
        StringBuilder sb;
        StringBuilder sb2;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int size = 0;
        for (int i3 = 0; i3 < 24; i3++) {
            if (i3 > 9) {
                sb2 = new StringBuilder();
                sb2.append(i3);
                sb2.append("");
            } else {
                sb2 = new StringBuilder();
                sb2.append("0");
                sb2.append(i3);
            }
            arrayList.add(sb2.toString());
            if (str.equals(i3 + "")) {
                size = arrayList.size() - 1;
            }
        }
        wheelView.setData(arrayList);
        wheelView.setDefault(size);
        ArrayList arrayList2 = new ArrayList();
        int size2 = 0;
        while (i2 < 60) {
            if (i2 > 9) {
                sb = new StringBuilder();
                sb.append(i2);
                sb.append("");
            } else {
                sb = new StringBuilder();
                sb.append("0");
                sb.append(i2);
            }
            arrayList2.add(sb.toString());
            if (str2.equals(i2 + "")) {
                size2 = arrayList2.size() - 1;
            }
            i2 += i;
        }
        wheelView2.setData(arrayList2);
        wheelView2.setDefault(size2);
    }

    public static void r() {
        if (f1596a.size() > 4) {
            int i = 0;
            while (i < f1596a.size() - 4) {
                Object obj = f1596a.get(i);
                if (c(obj)) {
                    f1596a.remove(obj);
                    i--;
                }
                i++;
            }
        }
    }

    public static void s() {
        f1597b.clear();
        Iterator<Object> it = f1596a.iterator();
        while (it.hasNext()) {
            String strM = m(it.next());
            if (!TextUtils.isEmpty(strM)) {
                f1597b.add(strM);
            }
        }
    }
}
