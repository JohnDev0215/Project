package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.share.send_tab_to_self.TargetDeviceInfo;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: DevicePickerBottomSheetContent.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l90 implements ii, AdapterView.OnItemClickListener {
    public final Context e;
    public final ji f;
    public ViewGroup g;
    public ViewGroup h;
    public final k90 i;
    public final Profile j;
    public final String k;
    public final String l;

    public l90(Context context, String str, String str2, ji jiVar, List<TargetDeviceInfo> list, Profile profile) {
        this.e = context;
        this.f = jiVar;
        this.j = profile;
        k90 k90Var = new k90(list);
        this.i = k90Var;
        this.k = str;
        this.l = str2;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R$layout.send_tab_to_self_device_picker_toolbar, (ViewGroup) null);
        this.g = viewGroup;
        ((TextView) viewGroup.findViewById(R$id.device_picker_toolbar)).setText(R$string.send_tab_to_self_sheet_toolbar);
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(context).inflate(R$layout.send_tab_to_self_device_picker_list, (ViewGroup) null);
        this.h = viewGroup2;
        ListView listView = (ListView) viewGroup2.findViewById(R$id.device_picker_list);
        listView.setAdapter((ListAdapter) k90Var);
        listView.setOnItemClickListener(this);
        listView.addFooterView(LayoutInflater.from(context).inflate(R$layout.send_tab_to_self_device_picker_footer, (ViewGroup) null));
    }

    @Override // defpackage.ii
    public final View d() {
        return this.h;
    }

    @Override // defpackage.ii
    public final int e() {
        return 0;
    }

    @Override // defpackage.ii
    public final View g() {
        return this.g;
    }

    @Override // defpackage.ii
    public final int h() {
        return R$string.send_tab_to_self_sheet_half_height;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.send_tab_to_self_sheet_closed;
    }

    @Override // defpackage.ii
    public final int j() {
        return -2;
    }

    @Override // defpackage.ii
    public final int m() {
        return 0;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        N.MgBRzKvC(1);
        TargetDeviceInfo targetDeviceInfo = this.i.e.get(i);
        N.M$fvB7ud(this.j, this.k, this.l, targetDeviceInfo.a);
        Context context = this.e;
        Resources resources = context.getResources();
        sk skVar = bq.a;
        if (N.M09VlOh_("SendTabToSelfV2") || N.M09VlOh_("UpcomingSharingFeatures")) {
            String string = resources.getString(R$string.send_tab_to_self_device_type_generic);
            int i2 = targetDeviceInfo.b;
            if (i2 == 2) {
                string = resources.getString(R$string.send_tab_to_self_device_type_phone);
            } else if (i2 == 1) {
                string = resources.getString(R$string.send_tab_to_self_device_type_computer);
            }
            Toast.b(0, context, resources.getString(R$string.send_tab_to_self_v2_toast, string)).e();
        } else {
            Toast.b(0, context, resources.getString(R$string.send_tab_to_self_toast, targetDeviceInfo.c)).e();
        }
        this.f.c(this, true);
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.send_tab_to_self_content_description;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.send_tab_to_self_sheet_full_height;
    }

    @Override // defpackage.ii
    public final float s() {
        return -1.0f;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return true;
    }

    @Override // defpackage.ii
    public final void destroy() {
    }
}
