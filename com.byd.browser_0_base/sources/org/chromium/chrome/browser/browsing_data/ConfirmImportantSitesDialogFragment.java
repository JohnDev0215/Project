package org.chromium.chrome.browser.browsing_data;

import android.R;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import defpackage.n3;
import defpackage.r90;
import defpackage.ss0;
import defpackage.tp0;
import defpackage.uc2;
import defpackage.xz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.chromium.base.CollectionUtil;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class ConfirmImportantSitesDialogFragment extends r90 {
    public String[] p0;
    public String[] r0;
    public c t0;
    public LargeIconBridge u0;
    public ListView v0;
    public final HashMap q0 = new HashMap();
    public final HashMap s0 = new HashMap();

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Intent intent = new Intent();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ConfirmImportantSitesDialogFragment confirmImportantSitesDialogFragment = ConfirmImportantSitesDialogFragment.this;
            for (Map.Entry entry : confirmImportantSitesDialogFragment.s0.entrySet()) {
                Integer num = (Integer) confirmImportantSitesDialogFragment.q0.get(entry.getKey());
                if (((Boolean) entry.getValue()).booleanValue()) {
                    arrayList3.add((String) entry.getKey());
                    arrayList4.add(num);
                } else {
                    arrayList.add((String) entry.getKey());
                    arrayList2.add(num);
                }
            }
            intent.putExtra("DeselectedDomains", (String[]) arrayList.toArray(new String[0]));
            intent.putExtra("DeselectedDomainReasons", CollectionUtil.c(arrayList2));
            intent.putExtra("IgnoredDomains", (String[]) arrayList3.toArray(new String[0]));
            intent.putExtra("IgnoredDomainReasons", CollectionUtil.c(arrayList4));
            Fragment fragmentQ = confirmImportantSitesDialogFragment.Q(true);
            tp0.b bVar = tp0.a;
            ss0 ss0Var = new ss0(confirmImportantSitesDialogFragment);
            tp0.b bVarA = tp0.a(confirmImportantSitesDialogFragment);
            if (bVarA.a.contains(tp0.a.DETECT_TARGET_FRAGMENT_USAGE) && tp0.d(bVarA, confirmImportantSitesDialogFragment.getClass(), ss0.class)) {
                tp0.b(bVarA, ss0Var);
            }
            fragmentQ.Z(confirmImportantSitesDialogFragment.m, -1, intent);
        }
    }

    public class b implements View.OnClickListener {
        public final /* synthetic */ Dialog e;

        public b(Dialog dialog) {
            this.e = dialog;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.e.dismiss();
        }
    }

    public class c extends ArrayAdapter<String> implements AdapterView.OnItemClickListener {
        public final String[] e;
        public final int f;
        public final uc2 g;

        public c(String[] strArr, String[] strArr2, Resources resources) {
            super(ConfirmImportantSitesDialogFragment.this.D(), R$layout.confirm_important_sites_list_row, strArr);
            this.e = strArr;
            ConfirmImportantSitesDialogFragment.this.r0 = strArr2;
            this.f = resources.getDimensionPixelSize(R$dimen.default_favicon_size);
            this.g = org.chromium.chrome.browser.ui.favicon.a.d(getContext());
        }

        public final void a(d dVar, Boolean bool) {
            CheckBox checkBox = dVar.a;
            if (checkBox != null) {
                checkBox.setButtonTintMode(PorterDuff.Mode.DST);
                if (bool.booleanValue()) {
                    dVar.a.setButtonDrawable(AppCompatResources.a(getContext(), R$drawable.check_box_select));
                } else {
                    dVar.a.setButtonDrawable(AppCompatResources.a(getContext(), R$drawable.check_box_no_select));
                }
            }
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            ConfirmImportantSitesDialogFragment confirmImportantSitesDialogFragment = ConfirmImportantSitesDialogFragment.this;
            if (view == null) {
                view = LayoutInflater.from(confirmImportantSitesDialogFragment.D()).inflate(R$layout.confirm_important_sites_list_row, viewGroup, false);
                d dVar = new d();
                dVar.a = (CheckBox) view.findViewById(R$id.icon_row_checkbox);
                dVar.b = (ImageView) view.findViewById(R$id.icon_row_image);
                dVar.c = (TextView) view.findViewById(R$id.tv_url);
                view.setTag(dVar);
            }
            d dVar2 = (d) view.getTag();
            String str = this.e[i];
            Boolean bool = (Boolean) confirmImportantSitesDialogFragment.s0.get(str);
            dVar2.a.setChecked(bool.booleanValue());
            a(dVar2, bool);
            if (TextUtils.isEmpty(str)) {
                dVar2.c.setText(confirmImportantSitesDialogFragment.r0[i]);
            } else {
                dVar2.c.setText(str);
            }
            String str2 = confirmImportantSitesDialogFragment.r0[i];
            org.chromium.chrome.browser.browsing_data.a aVar = new org.chromium.chrome.browser.browsing_data.a(this, dVar2, str2);
            dVar2.d = aVar;
            LargeIconBridge largeIconBridge = confirmImportantSitesDialogFragment.u0;
            largeIconBridge.getClass();
            largeIconBridge.b(new GURL(str2), this.f, aVar);
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public final boolean hasStableIds() {
            return true;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            String str = this.e[i];
            d dVar = (d) view.getTag();
            ConfirmImportantSitesDialogFragment confirmImportantSitesDialogFragment = ConfirmImportantSitesDialogFragment.this;
            boolean z = !((Boolean) confirmImportantSitesDialogFragment.s0.get(str)).booleanValue();
            confirmImportantSitesDialogFragment.s0.put(str, Boolean.valueOf(z));
            dVar.a.setChecked(z);
            a(dVar, Boolean.valueOf(z));
        }
    }

    public static class d {
        public CheckBox a;
        public ImageView b;
        public TextView c;
        public org.chromium.chrome.browser.browsing_data.a d;
    }

    @Override // androidx.fragment.app.Fragment
    public final void C0(Bundle bundle) {
        super.C0(bundle);
        this.p0 = bundle.getStringArray("ImportantDomains");
        this.r0 = bundle.getStringArray("FaviconURLs");
        int[] intArray = bundle.getIntArray("ImportantDomainReasons");
        int i = 0;
        while (true) {
            String[] strArr = this.p0;
            if (i >= strArr.length) {
                return;
            }
            this.q0.put(strArr[i], Integer.valueOf(intArray[i]));
            this.s0.put(this.p0[i], Boolean.TRUE);
            i++;
        }
    }

    @Override // defpackage.r90
    public final Dialog J0(Bundle bundle) {
        boolean z;
        if (bundle != null) {
            this.p0 = new String[0];
            this.r0 = new String[0];
            I0(false, false);
        }
        this.u0 = new LargeIconBridge(Profile.d());
        int iMin = Math.min((((ActivityManager) xz.a.getSystemService("activity")).getMemoryClass() / 16) * 25 * 1024, 102400);
        LargeIconBridge largeIconBridge = this.u0;
        largeIconBridge.getClass();
        largeIconBridge.c = new org.chromium.components.favicon.a(iMin);
        this.t0 = new c(this.p0, this.r0, N());
        HashSet hashSetA = WebappRegistry.b.a.a();
        String[] strArr = this.p0;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (hashSetA.contains(strArr[i])) {
                z = true;
                break;
            }
            i++;
        }
        int i2 = z ? R$string.important_sites_title_with_app : R$string.important_sites_title;
        int i3 = z ? R$string.clear_browsing_data_important_dialog_text_with_app : R$string.clear_browsing_data_important_dialog_text;
        Dialog dialog = new Dialog(K(), R$style.Dialog_Style);
        View viewInflate = ((LayoutInflater) K().getSystemService("layout_inflater")).inflate(R$layout.clear_browsing_important_dialog_listview, (ViewGroup) null);
        ListView listView = (ListView) viewInflate.findViewById(R$id.select_dialog_listview);
        this.v0 = listView;
        listView.setAdapter((ListAdapter) this.t0);
        this.v0.setOnItemClickListener(this.t0);
        ((TextView) viewInflate.findViewById(R$id.title)).setText(i2);
        ((TextView) viewInflate.findViewById(R.id.message)).setText(i3);
        Button button = (Button) viewInflate.findViewById(R$id.btn_sure);
        Button button2 = (Button) viewInflate.findViewById(R$id.btn_cancel);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.ll_sure_cancel);
        if (n3.Q0()) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        dialog.setContentView(viewInflate);
        Window window = dialog.getWindow();
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = K().getResources().getDimensionPixelSize(R$dimen.alert_dialog_max_width);
        attributes.height = -2;
        window.setAttributes(attributes);
        button.setOnClickListener(new a());
        button2.setOnClickListener(new b(dialog));
        return dialog;
    }

    @Override // defpackage.r90, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        LargeIconBridge largeIconBridge = this.u0;
        if (largeIconBridge != null) {
            largeIconBridge.a();
        }
    }
}
