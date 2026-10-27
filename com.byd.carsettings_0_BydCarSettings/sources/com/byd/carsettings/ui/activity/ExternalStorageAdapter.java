package com.byd.carsettings.ui.activity;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.r.i;
import com.byd.carsettings.R;
import com.byd.carsettings.system.model.UFlashInfo;
import com.byd.carsettings.ui.widget.BydInvalidButton;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ExternalStorageAdapter extends RecyclerView.Adapter<ExternalStorageViewHolder> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f2906a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List<UFlashInfo> f2907b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f2908c;

    public static class ExternalStorageViewHolder extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f2909a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2910b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public BydInvalidButton f2911c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public View f2912d;

        public ExternalStorageViewHolder(View view) {
            super(view);
            this.f2912d = view;
            this.f2909a = (TextView) view.findViewById(R.id.item_es_name);
            this.f2910b = (TextView) view.findViewById(R.id.item_es_me_info);
            this.f2911c = (BydInvalidButton) view.findViewById(R.id.item_es_eject);
        }
    }

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ UFlashInfo f2913a;

        public a(UFlashInfo uFlashInfo) {
            this.f2913a = uFlashInfo;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ExternalStorageAdapter.this.f2908c != null) {
                ExternalStorageAdapter.this.f2908c.a(this.f2913a);
            }
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ UFlashInfo f2915a;

        public b(UFlashInfo uFlashInfo) {
            this.f2915a = uFlashInfo;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ExternalStorageAdapter.this.f2908c != null) {
                ExternalStorageAdapter.this.f2908c.b(this.f2915a);
            }
        }
    }

    public interface c {
        void a(UFlashInfo uFlashInfo);

        void b(UFlashInfo uFlashInfo);
    }

    public ExternalStorageAdapter(Context context, List<UFlashInfo> list) {
        this.f2906a = context;
        this.f2907b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull ExternalStorageViewHolder externalStorageViewHolder, int i) {
        UFlashInfo uFlashInfo = this.f2907b.get(i);
        externalStorageViewHolder.f2911c.setOnClickListener(new a(uFlashInfo));
        externalStorageViewHolder.f2912d.setOnClickListener(new b(uFlashInfo));
        i(externalStorageViewHolder.f2909a, externalStorageViewHolder.f2910b, externalStorageViewHolder.f2911c, uFlashInfo);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public ExternalStorageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ExternalStorageViewHolder(LayoutInflater.from(this.f2906a).inflate(R.layout.item_external_storage, viewGroup, false));
    }

    public void e(UFlashInfo uFlashInfo) {
        this.f2907b.remove(uFlashInfo);
        notifyDataSetChanged();
    }

    public void f(c cVar) {
        this.f2908c = cVar;
    }

    public void g(List<UFlashInfo> list) {
        this.f2907b.clear();
        this.f2907b.addAll(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2907b.size();
    }

    public final void i(TextView textView, TextView textView2, BydInvalidButton bydInvalidButton, UFlashInfo uFlashInfo) {
        String str;
        if (uFlashInfo == null) {
            return;
        }
        textView.setText(uFlashInfo.getName());
        String string = this.f2906a.getString(R.string.external_storage_usage_app_manage);
        int i = uFlashInfo.diskState;
        String string2 = "";
        if (i == 0) {
            String strC = i.c(uFlashInfo.getSize());
            string2 = TextUtils.isEmpty(uFlashInfo.getPath()) ? this.f2906a.getString(R.string.external_storage_usage_unknow_app_manage) : i.c(uFlashInfo.sizeUsed);
            str = strC;
        } else {
            str = "";
        }
        if (i == 1) {
            textView2.setText(this.f2906a.getString(R.string.ejecting_app_manage));
            bydInvalidButton.setInvalid(true);
            bydInvalidButton.setEnabled(false);
        } else if (i == 2) {
            textView2.setText(this.f2906a.getString(R.string.ejected_usage_app_manage));
            bydInvalidButton.setInvalid(true);
            bydInvalidButton.setEnabled(false);
        } else if (i == 3) {
            textView2.setText(this.f2906a.getString(R.string.dilink_usb_mount_ing));
            bydInvalidButton.setInvalid(true);
            bydInvalidButton.setEnabled(false);
        } else {
            textView2.setText(String.format(string, string2, str));
            bydInvalidButton.setInvalid(false);
            bydInvalidButton.setEnabled(true);
        }
    }
}
