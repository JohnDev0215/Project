package com.byd.card.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.UiStyleConfig;
import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.byd.bluetoothaidl.manage.HfpManage;
import com.byd.card.bean.AppOrShortcutItem;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.views.window.ToastUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class AddAppPopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "AddAppPopAdapter";
    private CheckChangeListener changeListener;
    private Context context;
    private boolean isCancel;
    private List<AppOrShortcutItem> mList = new ArrayList();
    private List<AppOrShortcutItem> mCheckedList = new ArrayList();

    public interface CheckChangeListener {
        void onLimitHint(List<AppOrShortcutItem> checkedList);
    }

    public void setCheckChangeListener(CheckChangeListener listener) {
        this.changeListener = listener;
    }

    public AddAppPopAdapter(Context context) {
        this.context = context;
    }

    public void updateAdapter(List<AppOrShortcutItem> list) {
        this.mList.clear();
        this.mCheckedList.clear();
        for (AppOrShortcutItem appOrShortcutItem : list) {
            if (appOrShortcutItem != null) {
                String itemIdentify = appOrShortcutItem.getItemIdentify();
                if (!"com.byd.auto_camera".equals(itemIdentify) || AppUtils.isShowCamera()) {
                    if (!"com.byd.avm".equals(itemIdentify) || AppUtils.isShowAVM()) {
                        if (!"com.byd.auto_photo".equals(itemIdentify) || !Constant.HONG_KONG_CODE.equals(AppUtils.getCountryCode())) {
                            if (appOrShortcutItem.isItemChecked()) {
                                this.mCheckedList.add(appOrShortcutItem);
                            }
                            this.mList.add(appOrShortcutItem);
                        }
                    }
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int uiStyle = UiStyleConfig.getInstance(this.context).getUiStyle();
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_add_app_card, parent, false);
        if (uiStyle == 0) {
            viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_add_app_card, parent, false);
        } else if (uiStyle == 1) {
            viewInflate = LayoutInflater.from(this.context).inflate(R.layout.denza_item_add_app_card, parent, false);
        }
        return new ViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final AppOrShortcutItem appOrShortcutItem = this.mList.get(position);
        String itemName = appOrShortcutItem.getItemName();
        if (itemName.contains("-")) {
            setAppName(viewHolder, itemName);
        } else {
            viewHolder.appName.setText(itemName);
        }
        viewHolder.appIcon.setBackground(appOrShortcutItem.getItemDrawable());
        if (appOrShortcutItem.isItemChecked()) {
            viewHolder.addApp.setVisibility(8);
            viewHolder.mItem.setAlpha(0.6f);
        } else {
            viewHolder.addApp.setVisibility(0);
            if (this.isCancel) {
                cancelEnlargeItemAnimator(viewHolder.itemView);
                this.isCancel = false;
            } else {
                viewHolder.mItem.setAlpha(1.0f);
            }
        }
        viewHolder.addApp.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.adapter.AddAppPopAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HfpManage.getInstance().getCallState() >= 1) {
                    ToastUtils.show(AddAppPopAdapter.this.context.getApplicationContext(), AddAppPopAdapter.this.context.getString(R.string.forbid_add_tip));
                    return;
                }
                if (AddAppPopAdapter.this.changeListener == null || AddAppPopAdapter.this.mCheckedList.size() < 8) {
                    AddAppPopAdapter.this.mCheckedList.add(appOrShortcutItem);
                    appOrShortcutItem.setItemChecked(true);
                    viewHolder.addApp.setVisibility(8);
                    viewHolder.mItem.setAlpha(0.6f);
                    EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.SELECT_APP_CARD, appOrShortcutItem));
                    return;
                }
                AddAppPopAdapter.this.changeListener.onLimitHint(AddAppPopAdapter.this.mCheckedList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelScaleItemAnimator(final View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight() / 2);
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 1.2f, 1.0f).setDuration(100L), ObjectAnimator.ofFloat(view, "scaleY", 1.2f, 1.0f).setDuration(100L));
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.byd.card.adapter.AddAppPopAdapter.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setAlpha(1.0f);
            }
        });
    }

    private void cancelEnlargeItemAnimator(final View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight() / 2);
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.2f).setDuration(100L), ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.2f).setDuration(100L));
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.byd.card.adapter.AddAppPopAdapter.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                AddAppPopAdapter.this.cancelScaleItemAnimator(view);
            }
        });
    }

    private void setAppName(ViewHolder viewHolder, String name) {
        TextPaint paint = viewHolder.appName.getPaint();
        paint.setTextSize(viewHolder.appName.getTextSize());
        if (((int) paint.measureText(name)) > this.context.getResources().getDimensionPixelOffset(R.dimen.dp_128)) {
            int iLastIndexOf = name.lastIndexOf("-");
            viewHolder.appName.setText(String.format("%s\n%s", name.substring(0, iLastIndexOf) + "-", name.substring(iLastIndexOf + 1)));
            return;
        }
        viewHolder.appName.setText(name);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<AppOrShortcutItem> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public List<AppOrShortcutItem> getCheckedList() {
        return this.mCheckedList;
    }

    public void cancelSelectApp(final String packageName) {
        List<AppOrShortcutItem> list;
        Log.d(TAG, "cancelSelectApp packageName = " + packageName);
        if (TextUtils.isEmpty(packageName) || (list = this.mCheckedList) == null || this.mList == null) {
            return;
        }
        list.removeIf(new Predicate() { // from class: com.byd.card.adapter.-$$Lambda$AddAppPopAdapter$vjSXJEKLF6a69vxAYP6upZZ5FrY
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return packageName.equals(((AppOrShortcutItem) obj).getItemIdentify());
            }
        });
        for (int i = 0; i < this.mList.size(); i++) {
            AppOrShortcutItem appOrShortcutItem = this.mList.get(i);
            if (packageName.equals(appOrShortcutItem.getItemIdentify())) {
                appOrShortcutItem.setItemChecked(false);
                this.isCancel = true;
                notifyItemChanged(i);
                return;
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView addApp;
        private ImageView appIcon;
        private TextView appName;
        private ConstraintLayout mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mItem = (ConstraintLayout) itemView.findViewById(R.id.item_check);
            this.appIcon = (ImageView) itemView.findViewById(R.id.app_icon);
            this.addApp = (ImageView) itemView.findViewById(R.id.add_app);
            this.appName = (TextView) itemView.findViewById(R.id.app_name);
        }
    }
}
