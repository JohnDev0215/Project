package com.byd.systemsettings.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c1.c;
import c4.a;
import com.byd.systemsettings.R;
import d4.d;

/* JADX INFO: loaded from: classes2.dex */
public final class PopupWindowHelper {
    private static PopupWindow ppw;

    public static void dismiss() {
        PopupWindow popupWindow = ppw;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        ppw.dismiss();
        ppw = null;
    }

    public static void showList(Context context, View view, a aVar, d dVar, PopupWindow.OnDismissListener onDismissListener) {
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
        if (aVar != null) {
            recyclerView.setAdapter(aVar);
            aVar.setOnItemClickedListener(dVar);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(applicationContext);
            linearLayoutManager.setOrientation(1);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setItemAnimator(new c());
        }
        ppw = PopupWindowUtil.showAsDropDown(view, viewInflate, (int) applicationContext.getResources().getDimension(R.dimen.dp_330), (int) applicationContext.getResources().getDimension(R.dimen.dp_240), false, onDismissListener);
    }

    public static void showListOnActivity(Activity activity, View view, a aVar, d dVar, PopupWindow.OnDismissListener onDismissListener) {
        PopupWindow popupWindow = ppw;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        View viewInflate = LayoutInflater.from(activity).inflate(R.layout.ppw_list, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.rcv_ppw_list);
        if (aVar != null) {
            recyclerView.setAdapter(aVar);
            aVar.setOnItemClickedListener(dVar);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
            linearLayoutManager.setOrientation(1);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setItemAnimator(new c());
        }
        ppw = PopupWindowUtil.showAsDropDown(activity, view, viewInflate, (int) activity.getResources().getDimension(R.dimen.dp_330), (int) activity.getResources().getDimension(R.dimen.dp_180), false, false, onDismissListener);
    }
}
