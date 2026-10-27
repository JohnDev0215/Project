package com.amazon.mp3.auto.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.amazon.mp3.automotiveOS.R;
import com.google.firebase.perf.util.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingItemAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 %2\u00020\u0001:\u0002%&B\u001d\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0004\b#\u0010$J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0002H\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0002H\u0016J$\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0017R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"¨\u0006'"}, d2 = {"Lcom/amazon/mp3/auto/settings/SettingItemAdapter;", "Landroid/widget/BaseAdapter;", "", "getCount", "index", "", "getItem", "p0", "", "getItemId", "position", "", Constants.ENABLE_DISABLE, "Landroid/view/View;", "view", "Landroid/view/ViewGroup;", "viewGroup", "getView", "Landroid/content/Context;", "context", "Landroid/content/Context;", "", "Lcom/amazon/mp3/auto/settings/SettingItem;", "settingItems", "Ljava/util/List;", "Lcom/amazon/mp3/auto/settings/SettingItemAdapter$OnItemCheckedChangeListener;", "onItemCheckedChangedListener", "Lcom/amazon/mp3/auto/settings/SettingItemAdapter$OnItemCheckedChangeListener;", "getOnItemCheckedChangedListener", "()Lcom/amazon/mp3/auto/settings/SettingItemAdapter$OnItemCheckedChangeListener;", "setOnItemCheckedChangedListener", "(Lcom/amazon/mp3/auto/settings/SettingItemAdapter$OnItemCheckedChangeListener;)V", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/LayoutInflater;", "<init>", "(Landroid/content/Context;Ljava/util/List;)V", "Companion", "OnItemCheckedChangeListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SettingItemAdapter extends BaseAdapter {
    private final Context context;
    private final LayoutInflater inflater;
    private OnItemCheckedChangeListener onItemCheckedChangedListener;
    private final List<SettingItem> settingItems;

    /* JADX INFO: compiled from: SettingItemAdapter.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/auto/settings/SettingItemAdapter$OnItemCheckedChangeListener;", "", "onChangeClick", "", "id", "", "isChecked", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnItemCheckedChangeListener {
        void onChangeClick(int id, boolean isChecked);
    }

    @Override // android.widget.Adapter
    public long getItemId(int p0) {
        return p0;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int position) {
        return false;
    }

    public SettingItemAdapter(Context context, List<SettingItem> settingItems) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(settingItems, "settingItems");
        this.context = context;
        this.settingItems = settingItems;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        Intrinsics.checkNotNullExpressionValue(layoutInflaterFrom, "from(context)");
        this.inflater = layoutInflaterFrom;
    }

    public final void setOnItemCheckedChangedListener(OnItemCheckedChangeListener onItemCheckedChangeListener) {
        this.onItemCheckedChangedListener = onItemCheckedChangeListener;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.settingItems.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int index) {
        return this.settingItems.get(index);
    }

    @Override // android.widget.Adapter
    @SuppressLint({"InflateParams"})
    public View getView(final int index, View view, ViewGroup viewGroup) {
        Companion.ViewHolder viewHolder;
        View newView = view == null ? this.inflater.inflate(R.layout.setting_item, (ViewGroup) null) : view;
        if (view == null) {
            viewHolder = new Companion.ViewHolder();
            viewHolder.setItemIcon((ImageView) newView.findViewById(R.id.item_icon));
            viewHolder.setItemText((TextView) newView.findViewById(R.id.item_content));
            viewHolder.setItemStatus((ToggleButton) newView.findViewById(R.id.item_set_toggle));
            newView.setTag(viewHolder);
        } else {
            Object tag = view.getTag();
            if (tag == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.auto.settings.SettingItemAdapter.Companion.ViewHolder");
            }
            viewHolder = (Companion.ViewHolder) tag;
        }
        SettingItem settingItem = (SettingItem) getItem(index);
        TextView itemText = viewHolder.getItemText();
        if (itemText != null) {
            itemText.setText(settingItem.getText());
        }
        ImageView itemIcon = viewHolder.getItemIcon();
        if (itemIcon != null) {
            itemIcon.setImageResource(settingItem.getIconId());
        }
        ToggleButton itemStatus = viewHolder.getItemStatus();
        if (itemStatus != null) {
            itemStatus.setEnabled(settingItem.getIsEnabled());
        }
        if (settingItem.getIsEnabled()) {
            ToggleButton itemStatus2 = viewHolder.getItemStatus();
            if (itemStatus2 != null) {
                itemStatus2.setChecked(settingItem.getIsChecked());
            }
            TextView itemText2 = viewHolder.getItemText();
            if (itemText2 != null) {
                itemText2.setTextColor(this.context.getResources().getColor(R.color.pure_white));
            }
        } else {
            ToggleButton itemStatus3 = viewHolder.getItemStatus();
            if (itemStatus3 != null) {
                itemStatus3.setChecked(false);
            }
            TextView itemText3 = viewHolder.getItemText();
            if (itemText3 != null) {
                itemText3.setTextColor(this.context.getResources().getColor(R.color.dark_gray));
            }
        }
        ToggleButton itemStatus4 = viewHolder.getItemStatus();
        if (itemStatus4 != null) {
            itemStatus4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.amazon.mp3.auto.settings.SettingItemAdapter$$ExternalSyntheticLambda0
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    SettingItemAdapter.m365getView$lambda1(this.f$0, index, compoundButton, z);
                }
            });
        }
        Intrinsics.checkNotNullExpressionValue(newView, "newView");
        return newView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getView$lambda-1, reason: not valid java name */
    public static final void m365getView$lambda1(SettingItemAdapter this$0, int i, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (compoundButton.isPressed()) {
            this$0.settingItems.get(i).setChecked(z);
            OnItemCheckedChangeListener onItemCheckedChangeListener = this$0.onItemCheckedChangedListener;
            if (onItemCheckedChangeListener == null) {
                return;
            }
            onItemCheckedChangeListener.onChangeClick(i, z);
        }
    }
}
