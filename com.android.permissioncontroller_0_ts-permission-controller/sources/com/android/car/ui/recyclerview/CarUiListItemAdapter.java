package com.android.car.ui.recyclerview;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R;
import com.android.car.ui.recyclerview.CarUiContentListItem;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.utils.CarUiUtils;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public class CarUiListItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements CarUiRecyclerView.ItemCap {
    static final int VIEW_TYPE_LIST_HEADER = 2;
    static final int VIEW_TYPE_LIST_ITEM = 1;
    private final List<? extends CarUiListItem> mItems;
    private int mMaxItems = -1;

    public CarUiListItemAdapter(List<? extends CarUiListItem> items) {
        this.mItems = items;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 1) {
            return new ListItemViewHolder(inflater.inflate(R.layout.car_ui_list_item, parent, false));
        }
        if (viewType == 2) {
            return new HeaderViewHolder(inflater.inflate(R.layout.car_ui_header_list_item, parent, false));
        }
        throw new IllegalStateException("Unknown item type.");
    }

    public List<? extends CarUiListItem> getItems() {
        return this.mItems;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        if (this.mItems.get(position) instanceof CarUiContentListItem) {
            return 1;
        }
        if (this.mItems.get(position) instanceof CarUiHeaderListItem) {
            return 2;
        }
        throw new IllegalStateException("Unknown view type.");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == 1) {
            if (!(holder instanceof ListItemViewHolder)) {
                throw new IllegalStateException("Incorrect view holder type for list item.");
            }
            CarUiListItem item = this.mItems.get(position);
            if (!(item instanceof CarUiContentListItem)) {
                throw new IllegalStateException("Expected item to be bound to viewHolder to be instance of CarUiContentListItem.");
            }
            ((ListItemViewHolder) holder).bind((CarUiContentListItem) item);
            return;
        }
        if (itemViewType == 2) {
            if (!(holder instanceof HeaderViewHolder)) {
                throw new IllegalStateException("Incorrect view holder type for list item.");
            }
            CarUiListItem header = this.mItems.get(position);
            if (!(header instanceof CarUiHeaderListItem)) {
                throw new IllegalStateException("Expected item to be bound to viewHolder to be instance of CarUiHeaderListItem.");
            }
            ((HeaderViewHolder) holder).bind((CarUiHeaderListItem) header);
            return;
        }
        throw new IllegalStateException("Unknown item view type.");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.mMaxItems == -1) {
            return this.mItems.size();
        }
        return Math.min(this.mItems.size(), this.mMaxItems);
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView.ItemCap
    public void setMaxItems(int maxItems) {
        this.mMaxItems = maxItems;
    }

    static class ListItemViewHolder extends RecyclerView.ViewHolder {
        final ViewGroup mActionContainer;
        final View mActionContainerTouchInterceptor;
        final View mActionDivider;
        final ImageView mAvatarIcon;
        final TextView mBody;
        final CheckBox mCheckBox;
        final ImageView mContentIcon;
        final ImageView mIcon;
        final ViewGroup mIconContainer;
        final RadioButton mRadioButton;
        final View mReducedTouchInterceptor;
        final ImageView mSupplementalIcon;
        final Switch mSwitch;
        final TextView mTitle;
        final View mTouchInterceptor;

        ListItemViewHolder(View itemView) {
            super(itemView);
            this.mTitle = (TextView) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_title);
            this.mBody = (TextView) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_body);
            this.mIcon = (ImageView) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_icon);
            this.mContentIcon = (ImageView) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_content_icon);
            this.mAvatarIcon = (ImageView) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_avatar_icon);
            this.mIconContainer = (ViewGroup) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_icon_container);
            this.mActionContainer = (ViewGroup) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_action_container);
            this.mActionDivider = CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_action_divider);
            this.mSwitch = (Switch) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_switch_widget);
            this.mCheckBox = (CheckBox) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_checkbox_widget);
            this.mRadioButton = (RadioButton) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_radio_button_widget);
            this.mSupplementalIcon = (ImageView) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_supplemental_icon);
            this.mReducedTouchInterceptor = CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_reduced_touch_interceptor);
            this.mTouchInterceptor = CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_touch_interceptor);
            this.mActionContainerTouchInterceptor = CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_action_container_touch_interceptor);
        }

        void bind(final CarUiContentListItem item) {
            CharSequence title = item.getTitle();
            CharSequence body = item.getBody();
            Drawable icon = item.getIcon();
            if (!TextUtils.isEmpty(title)) {
                this.mTitle.setText(title);
                this.mTitle.setVisibility(0);
            } else {
                this.mTitle.setVisibility(8);
            }
            if (!TextUtils.isEmpty(body)) {
                this.mBody.setText(body);
                this.mBody.setVisibility(0);
            } else {
                this.mBody.setVisibility(8);
            }
            this.mIcon.setVisibility(8);
            this.mContentIcon.setVisibility(8);
            this.mAvatarIcon.setVisibility(8);
            if (icon != null) {
                this.mIconContainer.setVisibility(0);
                int i = AnonymousClass1.$SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$IconType[item.getPrimaryIconType().ordinal()];
                if (i == 1) {
                    this.mContentIcon.setVisibility(0);
                    this.mContentIcon.setImageDrawable(icon);
                } else if (i == 2) {
                    this.mIcon.setVisibility(0);
                    this.mIcon.setImageDrawable(icon);
                } else if (i == 3) {
                    this.mAvatarIcon.setVisibility(0);
                    this.mAvatarIcon.setImageDrawable(icon);
                    this.mAvatarIcon.setClipToOutline(true);
                }
            } else {
                this.mIconContainer.setVisibility(8);
            }
            this.mActionDivider.setVisibility(item.isActionDividerVisible() ? 0 : 8);
            this.mSwitch.setVisibility(8);
            this.mCheckBox.setVisibility(8);
            this.mRadioButton.setVisibility(8);
            this.mSupplementalIcon.setVisibility(8);
            final CarUiContentListItem.OnClickListener itemOnClickListener = item.getOnClickListener();
            switch (AnonymousClass1.$SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action[item.getAction().ordinal()]) {
                case 1:
                    this.mActionContainer.setVisibility(8);
                    this.mTouchInterceptor.setVisibility(0);
                    this.mTouchInterceptor.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.recyclerview.-$$Lambda$CarUiListItemAdapter$ListItemViewHolder$vYmq87MPMF2iCDptoto9aSwoSRI
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            CarUiListItemAdapter.ListItemViewHolder.lambda$bind$0(itemOnClickListener, item, view);
                        }
                    });
                    this.mReducedTouchInterceptor.setVisibility(8);
                    this.mActionContainerTouchInterceptor.setVisibility(8);
                    break;
                case 2:
                    bindCompoundButton(item, this.mSwitch, itemOnClickListener);
                    break;
                case 3:
                    bindCompoundButton(item, this.mCheckBox, itemOnClickListener);
                    break;
                case 4:
                    bindCompoundButton(item, this.mRadioButton, itemOnClickListener);
                    break;
                case 5:
                    this.mSupplementalIcon.setVisibility(0);
                    this.mSupplementalIcon.setImageDrawable(this.itemView.getContext().getDrawable(R.drawable.car_ui_preference_icon_chevron));
                    this.mActionContainer.setVisibility(0);
                    this.mTouchInterceptor.setVisibility(0);
                    this.mTouchInterceptor.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.recyclerview.-$$Lambda$CarUiListItemAdapter$ListItemViewHolder$_Kyrg36HwlbDv2E3CBtCHtfUhnY
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            CarUiListItemAdapter.ListItemViewHolder.lambda$bind$1(itemOnClickListener, item, view);
                        }
                    });
                    this.mReducedTouchInterceptor.setVisibility(8);
                    this.mActionContainerTouchInterceptor.setVisibility(8);
                    break;
                case 6:
                    this.mSupplementalIcon.setVisibility(0);
                    this.mSupplementalIcon.setImageDrawable(item.getSupplementalIcon());
                    this.mActionContainer.setVisibility(0);
                    if (item.getSupplementalIconOnClickListener() == null) {
                        this.mTouchInterceptor.setVisibility(0);
                        this.mTouchInterceptor.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.recyclerview.-$$Lambda$CarUiListItemAdapter$ListItemViewHolder$j6Kl0VK2D7maS58g7oclJxfONmk
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                CarUiListItemAdapter.ListItemViewHolder.lambda$bind$2(itemOnClickListener, item, view);
                            }
                        });
                        this.mReducedTouchInterceptor.setVisibility(8);
                        this.mActionContainerTouchInterceptor.setVisibility(8);
                    } else {
                        this.mReducedTouchInterceptor.setVisibility(0);
                        this.mReducedTouchInterceptor.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.recyclerview.-$$Lambda$CarUiListItemAdapter$ListItemViewHolder$vKPFwRo_FVQMiwVwkDs4sXpSKRQ
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                CarUiListItemAdapter.ListItemViewHolder.lambda$bind$3(itemOnClickListener, item, view);
                            }
                        });
                        this.mActionContainerTouchInterceptor.setVisibility(0);
                        this.mActionContainerTouchInterceptor.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.recyclerview.-$$Lambda$CarUiListItemAdapter$ListItemViewHolder$H9Uy6PlmUDvvSb-kLC_TMUKevKg
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                CarUiListItemAdapter.ListItemViewHolder.lambda$bind$4(item, view);
                            }
                        });
                        this.mTouchInterceptor.setVisibility(8);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown secondary action type.");
            }
            this.itemView.setActivated(item.isActivated());
            setEnabled(this.itemView, item.isEnabled());
        }

        static /* synthetic */ void lambda$bind$0(CarUiContentListItem.OnClickListener itemOnClickListener, CarUiContentListItem item, View v) {
            if (itemOnClickListener != null) {
                itemOnClickListener.onClick(item);
            }
        }

        static /* synthetic */ void lambda$bind$1(CarUiContentListItem.OnClickListener itemOnClickListener, CarUiContentListItem item, View v) {
            if (itemOnClickListener != null) {
                itemOnClickListener.onClick(item);
            }
        }

        static /* synthetic */ void lambda$bind$2(CarUiContentListItem.OnClickListener itemOnClickListener, CarUiContentListItem item, View v) {
            if (itemOnClickListener != null) {
                itemOnClickListener.onClick(item);
            }
        }

        static /* synthetic */ void lambda$bind$3(CarUiContentListItem.OnClickListener itemOnClickListener, CarUiContentListItem item, View v) {
            if (itemOnClickListener != null) {
                itemOnClickListener.onClick(item);
            }
        }

        static /* synthetic */ void lambda$bind$4(CarUiContentListItem item, View container) {
            if (item.getSupplementalIconOnClickListener() != null) {
                item.getSupplementalIconOnClickListener().onClick(item);
            }
        }

        void setEnabled(View view, boolean enabled) {
            view.setEnabled(enabled);
            if (view instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) view;
                for (int i = 0; i < group.getChildCount(); i++) {
                    setEnabled(group.getChildAt(i), enabled);
                }
            }
        }

        void bindCompoundButton(final CarUiContentListItem item, final CompoundButton compoundButton, final CarUiContentListItem.OnClickListener itemOnClickListener) {
            compoundButton.setVisibility(0);
            compoundButton.setOnCheckedChangeListener(null);
            compoundButton.setChecked(item.isChecked());
            compoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.car.ui.recyclerview.-$$Lambda$CarUiListItemAdapter$ListItemViewHolder$Hf4LbJlbyWgUPek_Tf8nBv3L2ug
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton2, boolean z) {
                    item.setChecked(z);
                }
            });
            this.mTouchInterceptor.setVisibility(0);
            this.mTouchInterceptor.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.recyclerview.-$$Lambda$CarUiListItemAdapter$ListItemViewHolder$-ebGuNtqEeHmdL3tKS0Y8Kofm5M
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CarUiListItemAdapter.ListItemViewHolder.lambda$bindCompoundButton$6(compoundButton, itemOnClickListener, item, view);
                }
            });
            this.mReducedTouchInterceptor.setVisibility(8);
            this.mActionContainerTouchInterceptor.setVisibility(8);
            this.mActionContainer.setVisibility(0);
            this.mActionContainer.setClickable(false);
        }

        static /* synthetic */ void lambda$bindCompoundButton$6(CompoundButton compoundButton, CarUiContentListItem.OnClickListener itemOnClickListener, CarUiContentListItem item, View v) {
            compoundButton.toggle();
            if (itemOnClickListener != null) {
                itemOnClickListener.onClick(item);
            }
        }
    }

    /* JADX INFO: renamed from: com.android.car.ui.recyclerview.CarUiListItemAdapter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action;
        static final /* synthetic */ int[] $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$IconType;

        static {
            int[] iArr = new int[CarUiContentListItem.Action.values().length];
            $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action = iArr;
            try {
                iArr[CarUiContentListItem.Action.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action[CarUiContentListItem.Action.SWITCH.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action[CarUiContentListItem.Action.CHECK_BOX.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action[CarUiContentListItem.Action.RADIO_BUTTON.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action[CarUiContentListItem.Action.CHEVRON.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action[CarUiContentListItem.Action.ICON.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            int[] iArr2 = new int[CarUiContentListItem.IconType.values().length];
            $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$IconType = iArr2;
            try {
                iArr2[CarUiContentListItem.IconType.CONTENT.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$IconType[CarUiContentListItem.IconType.STANDARD.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$IconType[CarUiContentListItem.IconType.AVATAR.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private final TextView mBody;
        private final TextView mTitle;

        HeaderViewHolder(View itemView) {
            super(itemView);
            this.mTitle = (TextView) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_title);
            this.mBody = (TextView) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_item_body);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void bind(CarUiHeaderListItem item) {
            this.mTitle.setText(item.getTitle());
            CharSequence body = item.getBody();
            if (!TextUtils.isEmpty(body)) {
                this.mBody.setText(body);
            } else {
                this.mBody.setVisibility(8);
            }
        }
    }
}
