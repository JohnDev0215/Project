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

/* JADX INFO: renamed from: nw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1243nw extends ArrayAdapter {
    public final Context s;
    public final Set t;
    public final boolean u;
    public final int v;
    public final boolean w;

    public C1243nw(Context context, List list, Set set, boolean z) {
        super(context, z ? AbstractC0425Xm.H : AbstractC0425Xm.G);
        this.s = context;
        addAll(list);
        this.t = set;
        boolean z2 = false;
        int i = 0;
        while (true) {
            if (i >= getCount()) {
                z2 = true;
                break;
            }
            InterfaceC1326pN interfaceC1326pN = (InterfaceC1326pN) getItem(i);
            if (interfaceC1326pN.isEnabled() && !interfaceC1326pN.d()) {
                break;
            } else {
                i++;
            }
        }
        this.u = z2;
        this.v = context.getResources().getDimensionPixelSize(AbstractC0299Qm.B0);
        this.w = z;
    }

    public final ImageView a(ImageView imageView, InterfaceC1326pN interfaceC1326pN) {
        if (interfaceC1326pN.h() == 0) {
            imageView.setVisibility(8);
            return null;
        }
        imageView.setImageDrawable(AbstractC1631v.b(this.s, interfaceC1326pN.h()));
        imageView.setVisibility(0);
        return imageView;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return this.u;
    }

    public final TextView b(InterfaceC1326pN interfaceC1326pN, View view) {
        TextView textView = (TextView) view.findViewById(AbstractC0371Um.Y0);
        textView.setEnabled(interfaceC1326pN.isEnabled());
        textView.setText(interfaceC1326pN.a());
        return textView;
    }

    public final TextView c(InterfaceC1326pN interfaceC1326pN, View view) {
        TextView textView = (TextView) view.findViewById(AbstractC0371Um.c1);
        String strF = interfaceC1326pN.f();
        if (TextUtils.isEmpty(strF)) {
            textView.setVisibility(8);
            return null;
        }
        textView.setText(strF);
        textView.setVisibility(0);
        return textView;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.s).inflate(this.w ? AbstractC0425Xm.H : AbstractC0425Xm.G, (ViewGroup) null);
            view.setBackground(new C1271oN(null));
        }
        InterfaceC1326pN interfaceC1326pN = (InterfaceC1326pN) getItem(i);
        if (this.w) {
            TextView textViewB = b(interfaceC1326pN, view);
            c(interfaceC1326pN, view);
            ImageView imageViewA = a((ImageView) view.findViewById(AbstractC0371Um.j1), interfaceC1326pN);
            if (imageViewA != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageViewA.getLayoutParams();
                marginLayoutParams.width = -2;
                marginLayoutParams.height = -2;
                imageViewA.setLayoutParams(marginLayoutParams);
            }
            if (interfaceC1326pN.e()) {
                textViewB.setSingleLine(false);
                LinearLayout linearLayout = (LinearLayout) view.findViewById(AbstractC0371Um.Z0);
                int dimensionPixelSize = this.s.getResources().getDimensionPixelSize(AbstractC0299Qm.J0);
                linearLayout.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize);
            }
            return view;
        }
        int dimensionPixelSize2 = this.s.getResources().getDimensionPixelSize(AbstractC0299Qm.A0);
        C1271oN c1271oN = (C1271oN) view.getBackground();
        if (i == 0) {
            c1271oN.a.setColor(0);
        } else {
            int dimensionPixelSize3 = this.s.getResources().getDimensionPixelSize(AbstractC0299Qm.z0);
            dimensionPixelSize2 += dimensionPixelSize3;
            Rect rect = c1271oN.b;
            rect.set(0, 0, rect.right, dimensionPixelSize3);
            Set set = this.t;
            c1271oN.a.setColor((set == null || !set.contains(Integer.valueOf(i))) ? this.s.getResources().getColor(AbstractC0281Pm.R2) : this.s.getResources().getColor(AbstractC0281Pm.Q2));
        }
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(AbstractC0371Um.Z0);
        if (interfaceC1326pN.e()) {
            dimensionPixelSize2 = -2;
        }
        linearLayout2.setOrientation(1);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(0, dimensionPixelSize2, 1.0f));
        TextView textViewB2 = b(interfaceC1326pN, view);
        textViewB2.setSingleLine(!interfaceC1326pN.e());
        if (interfaceC1326pN.e()) {
            int iO = AbstractC1251o3.o(textViewB2);
            int paddingEnd = textViewB2.getPaddingEnd();
            int i2 = this.v;
            textViewB2.setPaddingRelative(iO, i2, paddingEnd, i2);
        }
        if (interfaceC1326pN.d() || interfaceC1326pN.g()) {
            textViewB2.setTypeface(null, 1);
        } else {
            textViewB2.setTypeface(null, 0);
        }
        textViewB2.setTextColor(this.s.getResources().getColor(interfaceC1326pN.b()));
        textViewB2.setTextSize(0, this.s.getResources().getDimension(AbstractC0299Qm.g5));
        TextView textViewC = c(interfaceC1326pN, view);
        if (textViewC != null) {
            textViewC.setTextSize(0, this.s.getResources().getDimension(AbstractC0299Qm.k5));
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
        ImageView imageViewA2 = a(imageView, interfaceC1326pN);
        if (imageViewA2 != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) imageViewA2.getLayoutParams();
            marginLayoutParams2.width = -2;
            marginLayoutParams2.height = -2;
            int dimensionPixelSize4 = this.s.getResources().getDimensionPixelSize(AbstractC0299Qm.z2);
            marginLayoutParams2.setMarginStart(dimensionPixelSize4);
            marginLayoutParams2.setMarginEnd(dimensionPixelSize4);
            imageViewA2.setLayoutParams(marginLayoutParams2);
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
