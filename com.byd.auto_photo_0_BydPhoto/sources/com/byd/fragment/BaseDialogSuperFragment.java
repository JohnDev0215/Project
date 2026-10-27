package com.byd.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.byd.auto_photo.PhotoApp;
import com.byd.auto_photo.R;
import com.byd.auto_photo.a;
import com.byd.util.w;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseDialogSuperFragment extends DialogFragment {
    protected static final int BUTTON_BOTH = 3;
    protected static final int BUTTON_JUST_CANCEL = 2;
    protected static final int BUTTON_JUST_SURE = 1;
    protected static final int BUTTON_NONE = 0;
    protected static final int BUTTON_NONE_CLOSE_IN_TITLE = 4;
    private boolean alreadySetCancelable;
    protected TextView btnCancel;
    protected TextView btnSure;
    private ViewGroup container;
    protected ViewGroup contentView;
    private String currentLauge;
    private int fullStateSetCount;
    protected View mainLayout;
    private DialogInterface.OnDismissListener onDismissListener;
    private Bundle saveInstance;
    protected boolean showing;
    private ThemeReceiver themeReceiver;
    protected int uiVisibility;
    protected boolean userModel = true;
    protected boolean cancelOutside = false;
    protected boolean configStyle = true;
    protected boolean childInitMainLayout = false;
    private Handler mainHandler = new Handler(Looper.getMainLooper()) { // from class: com.byd.fragment.BaseDialogSuperFragment.2
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            if (message.what == 1) {
                BaseDialogSuperFragment.access$108(BaseDialogSuperFragment.this);
                BaseDialogSuperFragment baseDialogSuperFragment = BaseDialogSuperFragment.this;
                baseDialogSuperFragment.setSystemUiVisibility(baseDialogSuperFragment.getDialog());
                if (BaseDialogSuperFragment.this.fullStateSetCount < 3) {
                    sendEmptyMessageDelayed(1, 200L);
                }
            }
        }
    };
    private View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.byd.fragment.BaseDialogSuperFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            if (id != R.id.btn_cancel) {
                if (id == R.id.btn_sure) {
                    BaseDialogSuperFragment.this.onSureClick();
                    return;
                } else if (id != R.id.iv_dialog_title_close) {
                    return;
                }
            }
            BaseDialogSuperFragment.this.onCancelClick();
        }
    };

    private class ThemeReceiver extends BroadcastReceiver {
        private ThemeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("theme_changed".equals(intent.getAction())) {
                BaseDialogSuperFragment.this.mainHandler.post(new Runnable() { // from class: com.byd.fragment.BaseDialogSuperFragment.ThemeReceiver.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (BaseDialogSuperFragment.this.getDialog() != null) {
                            BaseDialogSuperFragment baseDialogSuperFragment = BaseDialogSuperFragment.this;
                            baseDialogSuperFragment.setAmount(baseDialogSuperFragment.getDialog().getWindow());
                        }
                        BaseDialogSuperFragment.this.reLayout();
                    }
                });
            }
        }
    }

    static /* synthetic */ int access$108(BaseDialogSuperFragment baseDialogSuperFragment) {
        int i = baseDialogSuperFragment.fullStateSetCount;
        baseDialogSuperFragment.fullStateSetCount = i + 1;
        return i;
    }

    private void forceResetBar() {
        this.fullStateSetCount = 0;
        this.mainHandler.removeCallbacksAndMessages(null);
        this.mainHandler.sendEmptyMessageDelayed(1, 300L);
    }

    protected void configDefCancelable() {
        if (this.alreadySetCancelable) {
            return;
        }
        super.setCancelable(this.cancelOutside);
        w.c(getClass().getName() + " cancelOutside:" + this.cancelOutside);
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

    protected void configWindowEffect() {
        if (getDialog() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        setAmount(window);
        configWindowAttrs(window);
    }

    protected float getAmountValue() {
        return (getActivity().getResources().getConfiguration().uiMode & 48) == 32 ? 0.8f : 0.3f;
    }

    protected int getBaseLayout() {
        return R.layout.fragment_base_dialog;
    }

    protected int getButtonConfig() {
        return 3;
    }

    protected String getCancelButtonText() {
        return getResources().getString(R.string.cancel);
    }

    protected Drawable getCloseDrawable() {
        return null;
    }

    protected int getDialogHeight() {
        return getResources().getDimensionPixelSize(R.dimen.dialog_min_height);
    }

    protected int getDialogWidth() {
        return getResources().getDimensionPixelSize(R.dimen.dialog_min_width);
    }

    protected abstract int getLayout();

    protected String getSureButtonText() {
        return getResources().getString(R.string.sure);
    }

    protected String getTitle() {
        return getResources().getString(R.string.tips);
    }

    protected void initUiData() {
    }

    public boolean isShowing() {
        return this.showing;
    }

    protected void onCancelClick() {
        dismissAllowingStateLoss();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.locale.toLanguageTag().equals(this.currentLauge)) {
            return;
        }
        onLocaleChanged();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.cancelOutside = getResources().getBoolean(R.bool.dialog_close_touch_outside);
        if (a.f1178a.intValue() < 35) {
            if (this.configStyle) {
                setStyle(1, 0);
            }
        } else {
            if (this.configStyle) {
                setStyle(1, R.style.CustomDialog);
            }
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
        configDefCancelable();
        return dialogOnCreateDialogCustom;
    }

    protected Dialog onCreateDialogCustom(Bundle bundle) {
        return super.onCreateDialog(bundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0111  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View onCreateView(android.view.LayoutInflater r5, android.view.ViewGroup r6, android.os.Bundle r7) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.fragment.BaseDialogSuperFragment.onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        w.c("dialog onDestroy " + getClass());
        this.showing = false;
        if (this.themeReceiver != null) {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.themeReceiver);
            this.themeReceiver = null;
        }
        super.onDestroy();
        this.alreadySetCancelable = false;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mainLayout = null;
    }

    protected void onDialogDismiss(DialogInterface dialogInterface) {
        DialogInterface.OnDismissListener onDismissListener = this.onDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        onDialogDismiss(dialogInterface);
    }

    protected void onLocaleChanged() {
        this.mainHandler.post(new Runnable() { // from class: com.byd.fragment.BaseDialogSuperFragment.4
            @Override // java.lang.Runnable
            public void run() {
                BaseDialogSuperFragment.this.reLayout();
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        if (this.uiVisibility != 0) {
            this.mainHandler.removeCallbacksAndMessages(null);
        }
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.uiVisibility != 0) {
            forceResetBar();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        configWindowEffect();
    }

    protected void onSureClick() {
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

    protected void setAmount(Window window) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        float amountValue = getAmountValue();
        if (attributes.dimAmount != amountValue) {
            attributes.dimAmount = amountValue;
            window.setAttributes(attributes);
        }
    }

    protected void setBackgroundColor(int i) {
        View view = this.mainLayout;
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }

    protected void setButtonEnable(boolean z) {
        TextView textView = this.btnCancel;
        if (textView == null || this.btnSure == null) {
            return;
        }
        textView.setEnabled(z);
        this.btnSure.setEnabled(z);
    }

    protected void setButtonVisible(int i) {
        if (this.userModel) {
            this.mainLayout.findViewById(R.id.line_above_buttons).setVisibility(i);
            this.mainLayout.findViewById(R.id.button_group).setVisibility(i);
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public void setCancelable(boolean z) {
        this.alreadySetCancelable = true;
        super.setCancelable(z);
        w.c(getClass().getName() + " setCancelable:" + z);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    protected void setSystemUiVisibility(Dialog dialog) {
        if (dialog == null) {
            return;
        }
        dialog.getWindow().getDecorView().setSystemUiVisibility(this.uiVisibility);
    }

    public void setUiVisibility(int i) {
        this.uiVisibility = i;
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        if (!this.showing) {
            try {
                this.showing = true;
                super.show(fragmentManager, str);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        w.c("Fragment : " + getClass().getName() + "-" + str + " already show , return");
    }

    protected boolean titleVisible() {
        return true;
    }

    protected void toastMsg(int i) {
        toastMsg(i, false);
    }

    protected void toastMsg(int i, boolean z) {
        if (getActivity() == null) {
            return;
        }
        ((PhotoApp) getActivity().getApplication()).g(getActivity(), i, z);
    }
}
