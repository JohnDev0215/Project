package com.neusoft.na.common;

import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BasicDialog extends DialogFragment {
    private static final String TAG = "XjPermissionDialog";
    protected IDialogClickListener mListener;
    protected boolean mOnlyCall;
    protected List<String> permissionList;

    public interface IDialogClickListener {
        void onExitClick(boolean z);

        void onSettingClick();
    }

    protected abstract View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    protected abstract void onDialogViewCreated(View view, Bundle bundle);

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getDialog().setCanceledOnTouchOutside(false);
        return getDialogView(layoutInflater, viewGroup, bundle);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        onDialogViewCreated(view, bundle);
    }

    public void setPermissionList(List<String> list, boolean z) {
        this.permissionList = list;
        this.mOnlyCall = z;
    }

    public void setOnClickListener(IDialogClickListener iDialogClickListener) {
        this.mListener = iDialogClickListener;
    }

    public static BasicDialog createDialog(String str) {
        Object objNewInstance;
        Log.i(TAG, "createDialog");
        try {
            objNewInstance = Class.forName(str).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (objNewInstance instanceof BasicDialog) {
            return (BasicDialog) objNewInstance;
        }
        Log.e(TAG, "permission dialog must be extends BasicDialog");
        Log.e(TAG, "createDialog null");
        return null;
    }
}
