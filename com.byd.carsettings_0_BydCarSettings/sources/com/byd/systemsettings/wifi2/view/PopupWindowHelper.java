package com.byd.systemsettings.wifi2.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.q.d.p;
import b.b.m.f.c.a.e;
import com.byd.carsettings.R;
import com.byd.mvp.ui.adapter.BydAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class PopupWindowHelper {
    private static PopupWindow ppw;

    public static void dismiss() {
        PopupWindow popupWindow = ppw;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        ppw.dismiss();
    }

    public static void showList(Context context, View view, BydAdapter bydAdapter, e eVar, PopupWindow.OnDismissListener onDismissListener) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        PopupWindow popupWindow = ppw;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        View viewInflate = LayoutInflater.from(applicationContext).inflate(R.layout.ppw_list, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.rcv_ppw_list);
        if (bydAdapter != null) {
            recyclerView.setAdapter(bydAdapter);
            bydAdapter.setOnItemClickedListener(eVar);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(applicationContext);
            linearLayoutManager.setOrientation(1);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
        ppw = p.c(view, viewInflate, (int) applicationContext.getResources().getDimension(R.dimen.dp_330), (int) applicationContext.getResources().getDimension(R.dimen.dp_240), false, onDismissListener);
    }

    public static void showListOnActivity(Activity activity, View view, BydAdapter bydAdapter, e eVar, PopupWindow.OnDismissListener onDismissListener) {
        PopupWindow popupWindow = ppw;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        View viewInflate = LayoutInflater.from(activity).inflate(R.layout.ppw_list, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.rcv_ppw_list);
        if (bydAdapter != null) {
            recyclerView.setAdapter(bydAdapter);
            bydAdapter.setOnItemClickedListener(eVar);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
            linearLayoutManager.setOrientation(1);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
        ppw = p.b(activity, view, viewInflate, (int) activity.getResources().getDimension(R.dimen.dp_330), (int) activity.getResources().getDimension(R.dimen.dp_180), false, false, onDismissListener);
    }
}
