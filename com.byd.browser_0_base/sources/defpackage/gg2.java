package defpackage;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.search_engines.TemplateUrl;
import org.chromium.components.search_engines.TemplateUrlService;

/* JADX INFO: compiled from: SearchEngineAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gg2 extends BaseAdapter implements TemplateUrlService.b, TemplateUrlService.c, View.OnClickListener {
    public final Context e;
    public final Profile f;
    public final LayoutInflater g;
    public ArrayList h = new ArrayList();
    public ArrayList i = new ArrayList();
    public int j = -1;
    public int k = -1;
    public boolean l;
    public Runnable m;
    public SettingsLauncher n;

    /* JADX INFO: compiled from: SearchEngineAdapter.java */
    public class a extends View.AccessibilityDelegate {
        public final /* synthetic */ boolean a;

        public a(boolean z) {
            this.a = z;
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setChecked(this.a);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.setCheckable(true);
            accessibilityNodeInfo.setChecked(this.a);
        }
    }

    public gg2(FragmentActivity fragmentActivity, Profile profile) {
        this.e = fragmentActivity;
        this.f = profile;
        this.g = (LayoutInflater) fragmentActivity.getSystemService("layout_inflater");
    }

    public static boolean c(List<TemplateUrl> list, TemplateUrl templateUrl) {
        for (int i = 0; i < list.size(); i++) {
            TemplateUrl templateUrl2 = list.get(i);
            if (templateUrl2.a() == templateUrl.a() && TextUtils.equals(templateUrl2.b(), templateUrl.b()) && TextUtils.equals(templateUrl2.c(), templateUrl.c())) {
                return true;
            }
        }
        return false;
    }

    @Override // org.chromium.components.search_engines.TemplateUrlService.c
    public final void M() {
        d();
    }

    public final int a() {
        return this.i.size() > 0 ? this.h.size() + 1 : this.h.size();
    }

    public final void d() {
        boolean z;
        Profile profile = this.f;
        TemplateUrlService templateUrlServiceQ = oj2.q(profile);
        if (!templateUrlServiceQ.f()) {
            this.l = true;
            templateUrlServiceQ.h(this);
            Object obj = ThreadUtils.a;
            N.MVKcMDBb(templateUrlServiceQ.c, templateUrlServiceQ);
            return;
        }
        Object obj2 = ThreadUtils.a;
        ArrayList arrayList = new ArrayList();
        N.MfJgqWb9(templateUrlServiceQ.c, templateUrlServiceQ, arrayList);
        TemplateUrl templateUrlC = templateUrlServiceQ.c();
        Collections.sort(arrayList, new fg2(templateUrlC));
        long jCurrentTimeMillis = System.currentTimeMillis() - 172800000;
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            TemplateUrl templateUrl = (TemplateUrl) it.next();
            if ((templateUrl.a() ? (char) 1 : templateUrl.equals(templateUrlC) ? (char) 0 : (char) 2) == 2) {
                if (i >= 3 || N.MzOF1_dp(templateUrl.a) <= jCurrentTimeMillis) {
                    it.remove();
                } else {
                    i++;
                }
            }
        }
        if (arrayList.size() != this.i.size() + this.h.size()) {
            z = true;
            break;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            TemplateUrl templateUrl2 = (TemplateUrl) arrayList.get(i2);
            if (!c(this.h, templateUrl2) && !c(this.i, templateUrl2)) {
                z = true;
                break;
            }
        }
        z = false;
        if (z) {
            this.h = new ArrayList();
            this.i = new ArrayList();
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                TemplateUrl templateUrl3 = (TemplateUrl) arrayList.get(i3);
                if ((templateUrl3.a() ? (char) 1 : templateUrl3.equals(templateUrlC) ? (char) 0 : (char) 2) == 2) {
                    this.i.add(templateUrl3);
                } else {
                    this.h.add(templateUrl3);
                }
            }
            this.j = -1;
            for (int i4 = 0; i4 < this.h.size(); i4++) {
                if (((TemplateUrl) this.h.get(i4)).equals(templateUrlC)) {
                    this.j = i4;
                }
            }
            for (int i5 = 0; i5 < this.i.size(); i5++) {
                if (((TemplateUrl) this.i.get(i5)).equals(templateUrlC)) {
                    this.j = a() + i5;
                }
            }
            int i6 = this.j;
            if (i6 == -1) {
                Object[] objArr = new Object[2];
                objArr[0] = Boolean.valueOf(templateUrlC != null);
                TemplateUrlService templateUrlServiceQ2 = oj2.q(profile);
                objArr[1] = Boolean.valueOf(N.MELaF8Vs(templateUrlServiceQ2.c, templateUrlServiceQ2));
                throw new IllegalStateException(String.format("Default search engine is not found in available search engines: DSE is valid=%b, is managed=%b", objArr));
            }
            this.k = i6;
            notifyDataSetChanged();
        }
    }

    @Override // org.chromium.components.search_engines.TemplateUrlService.b
    public final void g() {
        TemplateUrlService templateUrlServiceQ = oj2.q(this.f);
        templateUrlServiceQ.getClass();
        Object obj = ThreadUtils.a;
        templateUrlServiceQ.a.c(this);
        this.l = false;
        d();
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        ArrayList arrayList = this.h;
        int size = arrayList != null ? 0 + arrayList.size() : 0;
        ArrayList arrayList2 = this.i;
        return (arrayList2 == null || arrayList2.size() == 0) ? size : size + this.i.size() + 1;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        if (i < this.h.size()) {
            return this.h.get(i);
        }
        if (i <= this.h.size()) {
            return null;
        }
        return this.i.get(i - a());
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final int getItemViewType(int i) {
        return (i != this.h.size() || this.i.size() == 0) ? 0 : 1;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        if (view == null) {
            view = this.g.inflate((itemViewType != 1 || this.i.size() == 0) ? R$layout.search_engine : R$layout.search_engine_recent_title, (ViewGroup) null);
        }
        if (itemViewType == 1) {
            return view;
        }
        view.setOnClickListener(this);
        view.setTag(Integer.valueOf(i));
        RadioButton radioButton = (RadioButton) view.findViewById(R$id.radiobutton);
        boolean z = i == this.j;
        radioButton.setChecked(z);
        TextView textView = (TextView) view.findViewById(R$id.name);
        this.e.getResources();
        TemplateUrl templateUrl = (TemplateUrl) getItem(i);
        textView.setText(templateUrl.c());
        TextView textView2 = (TextView) view.findViewById(R$id.url);
        textView2.setText(templateUrl.b());
        if (TextUtils.isEmpty(templateUrl.b())) {
            textView2.setVisibility(8);
        }
        radioButton.setImportantForAccessibility(2);
        textView.setAccessibilityDelegate(new a(z));
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final int getViewTypeCount() {
        return 2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int iIntValue = ((Integer) view.getTag()).intValue();
        this.j = iIntValue;
        String strB = iIntValue < this.h.size() ? ((TemplateUrl) this.h.get(iIntValue)).b() : ((TemplateUrl) this.i.get(iIntValue - a())).b();
        TemplateUrlService templateUrlServiceQ = oj2.q(this.f);
        templateUrlServiceQ.getClass();
        Object obj = ThreadUtils.a;
        N.MxknP4iP(templateUrlServiceQ.c, templateUrlServiceQ, strB);
        if (this.j != this.k) {
            n3.X0("SearchEngine_ManualChange");
            this.m.run();
        }
        notifyDataSetChanged();
    }
}
