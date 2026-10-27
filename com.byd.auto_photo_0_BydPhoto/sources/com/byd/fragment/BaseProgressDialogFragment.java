package com.byd.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.byd.auto_photo.R;
import com.byd.auto_photo.a;
import com.byd.util.w;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseProgressDialogFragment extends DialogFragment {
    private ViewGroup container;
    private String currentLauge;
    private String msg;
    private int msgRes;
    private onCancelListener onCancelListener;
    private OnDismissListener onDismissListener;
    private Bundle saveInstance;
    protected boolean showing;
    private ThemeReceiver themeReceiver;
    private TextView tvMsg;
    private boolean uiStop;
    protected int uiVisibility;
    protected boolean userModel = true;
    protected boolean configStyle = true;

    public interface OnDismissListener {
        void onDismiss(BaseProgressDialogFragment baseProgressDialogFragment);
    }

    private class ThemeReceiver extends BroadcastReceiver {
        private ThemeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("theme_changed".equals(intent.getAction())) {
                BaseProgressDialogFragment.this.reLayout();
            }
        }
    }

    public interface onCancelListener {
        void onCanceled();
    }

    public BaseProgressDialogFragment() {
    }

    public BaseProgressDialogFragment(int i) {
        this.msgRes = i;
    }

    public BaseProgressDialogFragment(String str) {
        this.msg = str;
    }

    private void checkDismissAfterStop() {
        if (!this.uiStop || getDialog() == null) {
            return;
        }
        getDialog().dismiss();
        setShowsDialog(false);
        try {
            Class<? super Object> superclass = getClass().getSuperclass();
            while (!DialogFragment.class.getCanonicalName().equals(superclass.getCanonicalName())) {
                superclass = superclass.getSuperclass();
                if (Object.class.getCanonicalName().equals(superclass.getCanonicalName()) || Fragment.class.getCanonicalName().equals(superclass.getCanonicalName())) {
                    return;
                }
            }
            Field declaredField = getClass().getSuperclass().getDeclaredField("mDialog");
            declaredField.setAccessible(true);
            declaredField.set(this, null);
            w.c("dismiss after stop , force destroy dialog");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void configWindowAttrs(Window window) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        int dialogWidth = getDialogWidth();
        int dialogHeight = getDialogHeight();
        if (attributes.width == dialogWidth && attributes.height == dialogHeight) {
            return;
        }
        window.setLayout(dialogWidth, dialogHeight);
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        this.showing = false;
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkDismissAfterStop();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismissAllowingStateLoss() {
        this.showing = false;
        try {
            super.dismissAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkDismissAfterStop();
        w.c("progressDialog dismiss " + getTag() + " " + Thread.currentThread().getName());
    }

    protected int getDialogHeight() {
        return getResources().getDimensionPixelSize(R.dimen.progress_dialog_height);
    }

    protected int getDialogWidth() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.progress_dialog_width);
        if (dimensionPixelSize < 0) {
            return -2;
        }
        return dimensionPixelSize;
    }

    protected int getLayout() {
        return R.layout.fragment_progress_dialog;
    }

    public boolean isShowing() {
        return this.showing;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        w.c("ProgressDialog onCancel");
        onCancelListener oncancellistener = this.onCancelListener;
        if (oncancellistener != null) {
            oncancellistener.onCanceled();
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean zIsEmpty = TextUtils.isEmpty(this.msg);
        String languageTag = configuration.locale.toLanguageTag();
        if (zIsEmpty) {
            this.currentLauge = languageTag;
        } else {
            if (languageTag.equals(this.currentLauge)) {
                return;
            }
            reLayout();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.configStyle) {
            setStyle(1, 0);
        }
        if (a.f1178a.intValue() >= 35) {
            this.themeReceiver = new ThemeReceiver();
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.themeReceiver, new IntentFilter("theme_changed"));
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialogOnCreateDialogCustom = onCreateDialogCustom(bundle);
        if (this.uiVisibility != 0) {
            setSystemUiVisibility(dialogOnCreateDialogCustom);
        }
        return dialogOnCreateDialogCustom;
    }

    protected Dialog onCreateDialogCustom(Bundle bundle) {
        return super.onCreateDialog(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        restoreConfig(viewGroup, bundle);
        return this.userModel ? layoutInflater.inflate(getLayout(), viewGroup, false) : super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.showing = false;
        if (this.themeReceiver != null) {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.themeReceiver);
            this.themeReceiver = null;
        }
        if (this.onDismissListener != null) {
            this.onDismissListener = null;
        }
        if (this.onCancelListener != null) {
            this.onCancelListener = null;
        }
        super.onDestroy();
        this.saveInstance = null;
        this.container = null;
        this.msg = null;
        this.currentLauge = null;
        this.tvMsg = null;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.tvMsg != null) {
            this.tvMsg = null;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        OnDismissListener onDismissListener = this.onDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(this);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        this.uiStop = false;
        super.onStart();
        if (getDialog() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.0f;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.progress_dialog_y_offset);
        if (dimensionPixelSize != 0) {
            attributes.y = dimensionPixelSize;
        }
        window.setAttributes(attributes);
        configWindowAttrs(window);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.uiStop = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        TextView textView = (TextView) view.findViewById(R.id.tv_progress_tips);
        this.tvMsg = textView;
        int i = this.msgRes;
        if (i != 0) {
            textView.setText(i);
        } else {
            textView.setText(this.msg);
        }
        if (this.showing) {
            return;
        }
        dismissAllowingStateLoss();
    }

    protected void reLayout() {
        if (this.showing) {
            w.c(getClass().getName() + " reLayout");
            if (getDialog() == null || getActivity() == null) {
                return;
            }
            View viewOnCreateView = onCreateView(getActivity().getLayoutInflater(), this.container, this.saveInstance);
            getDialog().setContentView(viewOnCreateView);
            onViewCreated(viewOnCreateView, this.saveInstance);
        }
    }

    protected void restoreConfig(ViewGroup viewGroup, Bundle bundle) {
        if (this.container == null) {
            this.container = viewGroup;
            this.saveInstance = bundle;
        }
        this.currentLauge = getResources().getConfiguration().locale.toLanguageTag();
    }

    public void setMsg(String str) {
        this.msg = str;
        this.msgRes = 0;
    }

    public void setMsgRes(int i) {
        this.msgRes = i;
        this.msg = null;
    }

    public void setOnCancelListener(onCancelListener oncancellistener) {
        this.onCancelListener = oncancellistener;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    protected void setSystemUiVisibility(Dialog dialog) {
        dialog.getWindow().getDecorView().setSystemUiVisibility(this.uiVisibility);
    }

    public void setUiVisibility(int i) {
        this.uiVisibility = i;
    }

    @Override // androidx.fragment.app.DialogFragment
    public int show(FragmentTransaction fragmentTransaction, String str) {
        try {
            return super.show(fragmentTransaction, str);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        if (this.showing) {
            return;
        }
        this.showing = true;
        try {
            super.show(fragmentManager, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        w.c("progressDialog show : " + str + " " + Thread.currentThread().getName());
    }

    public void updateMsg(String str) {
        TextView textView;
        this.msg = str;
        this.msgRes = 0;
        if (!this.showing || (textView = this.tvMsg) == null) {
            return;
        }
        textView.setText(str);
    }
}
