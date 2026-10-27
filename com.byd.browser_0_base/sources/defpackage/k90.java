package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.share.send_tab_to_self.TargetDeviceInfo;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: compiled from: DevicePickerBottomSheetAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k90 extends BaseAdapter {
    public final List<TargetDeviceInfo> e;

    public k90(List<TargetDeviceInfo> list) {
        this.e = list;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.e.size();
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        return this.e.get(i);
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view != null) {
            return view;
        }
        Context context = viewGroup.getContext();
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.send_tab_to_self_device_picker_item, viewGroup, false);
        TargetDeviceInfo targetDeviceInfo = this.e.get(i);
        ChromeImageView chromeImageView = (ChromeImageView) viewInflate.findViewById(R$id.device_icon);
        int i2 = targetDeviceInfo.b;
        chromeImageView.setImageDrawable(i2 != 1 ? i2 != 2 ? AppCompatResources.a(context, R$drawable.devices_black_24dp) : AppCompatResources.a(context, R$drawable.smartphone_black_24dp) : AppCompatResources.a(context, R$drawable.computer_black_24dp));
        chromeImageView.setVisibility(0);
        ((TextView) viewInflate.findViewById(R$id.device_name)).setText(targetDeviceInfo.c);
        TextView textView = (TextView) viewInflate.findViewById(R$id.last_active);
        long days = TimeUnit.MILLISECONDS.toDays(Calendar.getInstance().getTimeInMillis() - targetDeviceInfo.d);
        Resources resources = context.getResources();
        textView.setText(days < 1 ? resources.getString(R$string.send_tab_to_self_device_last_active_today) : days == 1 ? resources.getString(R$string.send_tab_to_self_device_last_active_one_day_ago) : resources.getString(R$string.send_tab_to_self_device_last_active_more_than_one_day, Long.valueOf(days)));
        return viewInflate;
    }
}
