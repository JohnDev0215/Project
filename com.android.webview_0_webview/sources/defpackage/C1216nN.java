package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import java.util.Set;

/* JADX INFO: renamed from: nN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1216nN extends ArrayAdapter {
    public final Context s;
    public final Set t;
    public final boolean u;
    public final int v;

    public C1216nN(Context context, List list, Set set) {
        super(context, AbstractC0425Xm.g0);
        this.s = context;
        addAll(list);
        this.t = null;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= getCount()) {
                z = true;
                break;
            }
            InterfaceC1326pN interfaceC1326pN = (InterfaceC1326pN) getItem(i);
            if (interfaceC1326pN.isEnabled() && !interfaceC1326pN.d()) {
                break;
            } else {
                i++;
            }
        }
        this.u = z;
        this.v = context.getResources().getDimensionPixelSize(AbstractC0299Qm.C2);
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return this.u;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this.s.getSystemService("layout_inflater")).inflate(AbstractC0425Xm.g0, (ViewGroup) null);
            view.setBackground(new C1271oN(null));
        }
        C1271oN c1271oN = (C1271oN) view.getBackground();
        int dimensionPixelSize = this.s.getResources().getDimensionPixelSize(AbstractC0299Qm.B2);
        if (i == 0) {
            c1271oN.a.setColor(0);
        } else {
            int dimensionPixelSize2 = this.s.getResources().getDimensionPixelSize(AbstractC0299Qm.A2);
            dimensionPixelSize += dimensionPixelSize2;
            Rect rect = c1271oN.b;
            rect.set(0, 0, rect.right, dimensionPixelSize2);
            Set set = this.t;
            c1271oN.a.setColor((set == null || !set.contains(Integer.valueOf(i))) ? this.s.getResources().getColor(AbstractC0281Pm.R2) : this.s.getResources().getColor(AbstractC0281Pm.Q2));
        }
        InterfaceC1326pN interfaceC1326pN = (InterfaceC1326pN) getItem(i);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(AbstractC0371Um.Z0);
        if (interfaceC1326pN.e()) {
            dimensionPixelSize = -2;
        }
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, dimensionPixelSize, 1.0f));
        TextView textView = (TextView) view.findViewById(AbstractC0371Um.Y0);
        textView.setText(interfaceC1326pN.a());
        textView.setSingleLine(!interfaceC1326pN.e());
        if (interfaceC1326pN.e()) {
            int iO = AbstractC1251o3.o(textView);
            int paddingEnd = textView.getPaddingEnd();
            int i2 = this.v;
            textView.setPaddingRelative(iO, i2, paddingEnd, i2);
        }
        textView.setEnabled(interfaceC1326pN.isEnabled());
        if (interfaceC1326pN.d() || interfaceC1326pN.g()) {
            textView.setTypeface(null, 1);
        } else {
            textView.setTypeface(null, 0);
        }
        textView.setTextColor(this.s.getResources().getColor(interfaceC1326pN.b()));
        textView.setTextSize(0, this.s.getResources().getDimension(AbstractC0299Qm.g5));
        TextView textView2 = (TextView) view.findViewById(AbstractC0371Um.c1);
        String strF = interfaceC1326pN.f();
        if (TextUtils.isEmpty(strF)) {
            textView2.setVisibility(8);
        } else {
            textView2.setText(strF);
            textView2.setTextSize(0, this.s.getResources().getDimension(AbstractC0299Qm.k5));
            textView2.setVisibility(0);
        }
        ImageView imageView = (ImageView) view.findViewById(AbstractC0371Um.u4);
        ImageView imageView2 = (ImageView) view.findViewById(AbstractC0371Um.j1);
        if (interfaceC1326pN.c()) {
            imageView2.setVisibility(8);
        } else {
            imageView.setVisibility(8);
        }
        if (!interfaceC1326pN.c()) {
            imageView = imageView2;
        }
        if (interfaceC1326pN.h() == 0) {
            imageView.setVisibility(8);
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
            marginLayoutParams.width = -2;
            marginLayoutParams.height = -2;
            int dimensionPixelSize3 = this.s.getResources().getDimensionPixelSize(AbstractC0299Qm.z2);
            marginLayoutParams.setMarginStart(dimensionPixelSize3);
            marginLayoutParams.setMarginEnd(dimensionPixelSize3);
            imageView.setLayoutParams(marginLayoutParams);
            imageView.setImageDrawable(AbstractC1631v.b(this.s, interfaceC1326pN.h()));
            imageView.setVisibility(0);
        }
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        if (i < 0 || i >= getCount()) {
            return false;
        }
        InterfaceC1326pN interfaceC1326pN = (InterfaceC1326pN) getItem(i);
        return interfaceC1326pN.isEnabled() && !interfaceC1326pN.d();
    }
}
