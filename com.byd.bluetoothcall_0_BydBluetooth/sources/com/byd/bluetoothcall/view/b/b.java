package com.byd.bluetoothcall.view.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.info.CallLogInfo;
import com.bydux.api.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: compiled from: Contacts_info_CallLogAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<CallLogInfo> f3340b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3341c;

    /* JADX INFO: compiled from: Contacts_info_CallLogAdapter.java */
    class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f3342a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ImageView f3343b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f3344c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public View f3345d;

        a() {
        }
    }

    public b(List<CallLogInfo> list, Context context) {
        this.f3340b = new ArrayList();
        this.f3341c = null;
        this.f3340b = list;
        this.f3341c = context;
    }

    public String a(long j) {
        if (j == 0) {
            return "";
        }
        String str = "yyyy/MM/dd aa hh:mm";
        String str2 = com.byd.bluetoothcall.f.f.u(this.f3341c) ? "yyyy/MM/dd aa hh:mm" : "yyyy/MM/dd HH:mm";
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j);
        if (calendar.get(1) == calendar2.get(1)) {
            int i = calendar.get(6) - calendar2.get(6);
            if (i == 0) {
                str = com.byd.bluetoothcall.f.f.u(this.f3341c) ? "aa hh:mm" : "HH:mm";
            } else {
                if (i == 1) {
                    return this.f3341c.getString(R.string.bt_yesterday);
                }
                if (!com.byd.bluetoothcall.f.f.u(this.f3341c)) {
                    str = "yyyy/MM/dd HH:mm";
                }
            }
        } else {
            str = str2;
        }
        return new SimpleDateFormat(str).format(calendar2.getTime());
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3340b.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.f3340b.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.f3341c).inflate(R.layout.contacts_info_calllog_item, (ViewGroup) null);
            aVar = new a();
            aVar.f3342a = (TextView) view.findViewById(R.id.call_log_name);
            aVar.f3343b = (ImageView) view.findViewById(R.id.call_log_type);
            aVar.f3344c = (TextView) view.findViewById(R.id.call_log_time);
            aVar.f3345d = view.findViewById(R.id.line);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (com.byd.bluetoothcall.f.f.l(this.f3341c) == 1) {
            aVar.f3342a.setTextColor(this.f3341c.getColor(R.color.ts_ff_text_color));
            aVar.f3343b.setImageResource(R.drawable.denza_callout_icon);
            aVar.f3344c.setTextColor(this.f3341c.getColor(R.color.ts_b3_text_color));
            aVar.f3345d.setBackgroundColor(this.f3341c.getColor(R.color.ts_list_line_1));
        } else {
            aVar.f3342a.setTextColor(this.f3341c.getColor(R.color.ff_text_color));
            aVar.f3343b.setImageResource(R.drawable.callout_icon);
            aVar.f3344c.setTextColor(this.f3341c.getColor(R.color.b3_text_color));
            aVar.f3345d.setBackgroundColor(this.f3341c.getColor(R.color.list_line_1));
        }
        if (this.f3340b.get(i).j() > 1) {
            aVar.f3342a.setText(com.byd.bluetoothcall.f.e.a(this.f3340b.get(i).o()) + "  (" + this.f3340b.get(i).j() + ")");
        } else {
            aVar.f3342a.setText(com.byd.bluetoothcall.f.e.a(this.f3340b.get(i).o()));
        }
        if (this.f3340b.get(i).r().equals("DIALED")) {
            aVar.f3343b.setVisibility(0);
            aVar.f3342a.setTextColor(this.f3341c.getResources().getColor(R.color.ff_text_color));
        } else if (this.f3340b.get(i).r().equals("MISSED")) {
            aVar.f3343b.setVisibility(4);
            aVar.f3342a.setTextColor(this.f3341c.getResources().getColor(R.color.missed_call_red_color));
        } else if (this.f3340b.get(i).r().equals("RECEIVED")) {
            aVar.f3342a.setTextColor(this.f3341c.getResources().getColor(R.color.ff_text_color));
            aVar.f3343b.setVisibility(4);
        }
        aVar.f3344c.setText(a(this.f3340b.get(i).q()));
        return view;
    }
}
