package com.byd.auto_emanual.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.util.DeviceUtil;
import com.byd.auto_emanual.util.LoadingUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.NetUtil;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class OtherStateDialog extends DialogFragment {
    public static final String STAGE_LOADING = "loading";
    public static final String STAGE_NET_ERROR = "net_error";
    private static final String TAG = "OtherStateDialog";
    private Context context;
    private RelativeLayout stateBackground;
    private ImageView stateImage;
    private TextView stateText;
    private int stateTextId = R.string.state_loading;
    private int stateImageId = R.drawable.loading_54;

    public static OtherStateDialog getDialogInstance(int stateTextId) {
        OtherStateDialog otherStateDialog = new OtherStateDialog();
        otherStateDialog.stateTextId = stateTextId;
        return otherStateDialog;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(3, 0);
        setCancelable(false);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context.getApplicationContext();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.other_state_fragment, container, false);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (getDialog() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        this.stateImage = (ImageView) window.findViewById(R.id.state_image);
        this.stateText = (TextView) window.findViewById(R.id.state_text);
        this.stateBackground = (RelativeLayout) window.findViewById(R.id.state_background);
        if (NetUtil.isNetAvailable(this.context) && this.stateImageId == R.drawable.loading_54) {
            setLoading();
        } else {
            setNetError();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateByConfig();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateByConfig();
    }

    private void updateByConfig() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.stateBackground.getLayoutParams();
        if (this.context.getResources().getConfiguration().orientation == 1) {
            layoutParams.setMarginStart(this.context.getResources().getDimensionPixelOffset(R.dimen.state_margin_start_port));
            layoutParams.setMarginEnd(this.context.getResources().getDimensionPixelOffset(R.dimen.state_margin_start_port));
        } else if (DeviceUtil.isRightDriver()) {
            layoutParams.setMarginEnd(this.context.getResources().getDimensionPixelOffset(R.dimen.state_margin_start));
        } else {
            layoutParams.setMarginStart(this.context.getResources().getDimensionPixelOffset(R.dimen.state_margin_start));
        }
        this.stateText.setText(getString(this.stateTextId));
        this.stateText.setTextColor(ContextCompat.getColor(this.context, R.color.state_text));
        if (this.stateTextId == R.string.state_net_error) {
            setNetError();
        } else {
            setLoading();
        }
    }

    private void setLoading() {
        this.stateText.setText(this.context.getString(this.stateTextId));
        this.stateImage.setImageDrawable(ContextCompat.getDrawable(this.context, this.stateImageId));
        this.stateImage.setBackgroundColor(0);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.stateImage.getLayoutParams();
        layoutParams.width = this.context.getResources().getDimensionPixelOffset(R.dimen.state_loading_size);
        layoutParams.height = this.context.getResources().getDimensionPixelOffset(R.dimen.state_loading_size);
        this.stateImage.setLayoutParams(layoutParams);
        this.stateBackground.setBackground(ContextCompat.getDrawable(this.context, R.drawable.other_state_background));
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.stateBackground.getLayoutParams();
        layoutParams2.width = this.context.getResources().getDimensionPixelOffset(R.dimen.state_width);
        this.stateBackground.setLayoutParams(layoutParams2);
        showLoading();
    }

    private void showLoading() {
        LoadingUtil.startLoading(this.context, this.stateImage);
    }

    private void hideLoading() {
        LoadingUtil.stopLoading(this.stateImage);
        RelativeLayout relativeLayout = this.stateBackground;
        if (relativeLayout == null) {
            return;
        }
        relativeLayout.setBackgroundColor(0);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.stateBackground.getLayoutParams();
        layoutParams.width = -1;
        this.stateBackground.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.stateImage.getLayoutParams();
        layoutParams2.width = this.context.getResources().getDimensionPixelOffset(R.dimen.state_image_size_width);
        layoutParams2.height = this.context.getResources().getDimensionPixelOffset(R.dimen.state_image_size_height);
        this.stateImage.setLayoutParams(layoutParams2);
    }

    private void setNetError() {
        hideLoading();
        this.stateTextId = R.string.state_net_error;
        this.stateText.setText(this.context.getString(R.string.state_net_error));
        this.stateImage.setImageDrawable(new ColorDrawable(0));
        this.stateImage.setBackground(ContextCompat.getDrawable(this.context, R.drawable.state_net_error));
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager manager, String tag) {
        MLog.d(TAG, "show tag = " + tag);
        showAllowingStateLoss(manager, tag);
    }

    public void showAllowingStateLoss(FragmentManager manager, String tag) {
        try {
            Field declaredField = DialogFragment.class.getDeclaredField("mDismissed");
            declaredField.setAccessible(true);
            declaredField.set(this, false);
        } catch (Exception e) {
            MLog.e(TAG, "showAllowingStateLoss mDismissed ", e);
        }
        try {
            Field declaredField2 = DialogFragment.class.getDeclaredField("mShownByMe");
            declaredField2.setAccessible(true);
            declaredField2.set(this, true);
        } catch (Exception e2) {
            MLog.e(TAG, "showAllowingStateLoss mShownByMe ", e2);
        }
        FragmentTransaction fragmentTransactionBeginTransaction = manager.beginTransaction();
        fragmentTransactionBeginTransaction.add(this, tag);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        hideLoading();
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction();
        }
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        super.dismissAllowingStateLoss();
        MLog.d(TAG, "dismiss");
    }
}
