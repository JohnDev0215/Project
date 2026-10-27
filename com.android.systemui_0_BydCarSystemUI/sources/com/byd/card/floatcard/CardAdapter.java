package com.byd.card.floatcard;

import android.app.UiStyleConfig;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.byd.bluetoothaidl.manage.HfpManage;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.AppOrShortcutItem;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.byd.card.bean.ShortcutInfo;
import com.byd.card.db.CardDatabase;
import com.byd.card.forbidden.ForbiddenManager;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.views.skin.loader.SkinManager;
import com.bydux.api.common.views.window.ToastUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class CardAdapter extends RecyclerView.Adapter<MyViewHolder> implements DragListener {
    private static final int HOST_ID = 2048;
    public static final int ITEM_TYPE_ADD = 1;
    public static final int ITEM_TYPE_APP = 2;
    public static final int ITEM_TYPE_SHORTCUT = 3;
    public static final int ITEM_TYPE_WIDGET = 0;
    private static final String TAG = "CardAdapter";
    public static final int itemTag = 2131296399;
    private boolean isEditStatus;
    private OnItemClickListener listener;
    private AppWidgetHost mAppWidgetHost;
    private AppWidgetManager mAppWidgetManager;
    private final Context mContext;
    private View mEmptyView;
    private boolean mIsDeleteBanned;
    private MyAdapterListener mListener;
    private int mTouchX;
    private int mTouchY;
    private Map<ComponentName, Integer> mWidgetIdMap;
    private volatile CopyOnWriteArrayList<Object> mData = new CopyOnWriteArrayList<>();
    private boolean mShowDelete = false;
    private Object mBluetoothPreviousCard = null;
    private Object mBluetoothCard = null;

    public interface MyAdapterListener {
        void onAddClick(View view, Object obj);

        void onStartDrag(RecyclerView.ViewHolder viewHolder);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Object appOrShortcut, int touchX, int touchY);
    }

    private boolean isImmovableCard(Object info) {
        if (info instanceof AppWidgetProviderInfo) {
            AppWidgetProviderInfo appWidgetProviderInfo = (AppWidgetProviderInfo) info;
            String packageName = appWidgetProviderInfo.provider.getPackageName();
            packageName.hashCode();
            if (packageName.equals("com.neusoft.na.navigation") || packageName.equals("com.huawei.maps.auto.app")) {
                return true;
            }
            return Constant.WidgetName.AUTO_MAP_FAKE_WIDGET.equals(appWidgetProviderInfo.provider.getClassName());
        }
        if (!(info instanceof AppInfo)) {
            return false;
        }
        String packageName2 = ((AppInfo) info).getPackageName();
        packageName2.hashCode();
        return packageName2.equals("com.ts.androidauto.app") || packageName2.equals("com.ts.carplay.app");
    }

    public void moveBluetoothCardFront() {
        this.mBluetoothPreviousCard = null;
        this.mBluetoothCard = null;
        int iMin = Integer.MAX_VALUE;
        int i = 0;
        for (int i2 = 0; i2 < this.mData.size(); i2++) {
            Object obj = this.mData.get(i2);
            if ((obj instanceof AppWidgetProviderInfo) && "com.byd.bluetoothcall".equals(((AppWidgetProviderInfo) obj).provider.getPackageName())) {
                i = i2;
            } else if (!isImmovableCard(obj)) {
                iMin = Math.min(iMin, i2);
            }
        }
        if (iMin < i) {
            this.mBluetoothPreviousCard = this.mData.get(i - 1);
            this.mBluetoothCard = this.mData.get(i);
            this.mData.add(iMin, this.mData.remove(i));
            notifyItemMoved(i, iMin);
        }
        Log.d(TAG, "moveBluetoothCardFront firstMoveableCardPosition = " + iMin + " bluetoothCardPosition = " + i);
    }

    public void moveBluetoothCardBack() {
        Log.d(TAG, "moveBluetoothCardBack");
        if (this.mBluetoothPreviousCard != null && this.mBluetoothCard != null) {
            int iIndexOf = this.mData.indexOf(this.mBluetoothPreviousCard);
            int iIndexOf2 = this.mData.indexOf(this.mBluetoothCard);
            if (iIndexOf >= 0 && iIndexOf2 >= 0) {
                this.mData.add(iIndexOf, this.mData.remove(iIndexOf2));
                notifyItemMoved(iIndexOf2, iIndexOf);
            }
            Log.d(TAG, "moveBluetoothCardBack firstMoveableCardPosition = " + iIndexOf + " bluetoothCardPosition = " + iIndexOf2);
        }
        this.mBluetoothPreviousCard = null;
        this.mBluetoothCard = null;
    }

    public void updateForbidden(boolean isPGear) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.mData) {
            if (obj instanceof AppOrShortcutItem) {
                arrayList.add((AppOrShortcutItem) obj);
            }
        }
        ForbiddenManager.updateForbidden(arrayList, isPGear);
        Log.d(TAG, "updateForbidden isPGear = " + isPGear + " infoList = " + arrayList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CardAdapter(Context context, MyAdapterListener listener) {
        this.mContext = context;
        this.mListener = listener;
        AppWidgetHost appWidgetHost = new AppWidgetHost(context, 2048);
        this.mAppWidgetHost = appWidgetHost;
        appWidgetHost.startListening();
        this.mAppWidgetManager = AppWidgetManager.getInstance(context);
        this.mWidgetIdMap = SpUtils.getWidgetIdMap(context);
    }

    public void updateData(CopyOnWriteArrayList<Object> data) {
        this.mData.clear();
        this.mData.addAll(data);
        updateForbidden(ForbiddenManager.isPGear());
        Log.d(TAG, "updateData size = " + data.size());
    }

    public CopyOnWriteArrayList<Object> getData() {
        return this.mData;
    }

    public void setShowDelete(boolean showDelete) {
        this.mShowDelete = showDelete;
    }

    public boolean isDeleteShow() {
        return this.mShowDelete;
    }

    public void setEditStatus(boolean editStatus) {
        this.isEditStatus = editStatus;
    }

    public void addCarPlayOrAuto(AppInfo appInfo) {
        for (int i = 0; i < this.mData.size(); i++) {
            Object obj = this.mData.get(i);
            if ((obj instanceof AppInfo) && appInfo.packageName.equals(((AppInfo) obj).packageName)) {
                return;
            }
        }
        this.mData.add(0, appInfo);
        saveItemToSp();
        notifyDataSetChanged();
        Log.d(TAG, "addCarPlayOrAuto " + appInfo.packageName);
    }

    public void addWidget(AppWidgetProviderInfo providerInfo) {
        if (providerInfo == null) {
            return;
        }
        for (int i = 0; i < this.mData.size(); i++) {
            Object obj = this.mData.get(i);
            boolean z = obj instanceof AppWidgetProviderInfo;
            if (z && providerInfo.provider.getPackageName().equals(((AppWidgetProviderInfo) obj).provider.getPackageName())) {
                Log.i(TAG, "addWidget return1! providerInfo = " + providerInfo);
                return;
            } else {
                if (z && providerInfo.provider.getClassName().equals(((AppWidgetProviderInfo) obj).provider.getClassName())) {
                    Log.i(TAG, "addWidget return2! providerInfo = " + providerInfo);
                    return;
                }
            }
        }
        if ("com.byd.mycar".equals(providerInfo.provider.getPackageName()) || Constant.PackageName.MY_CAR_PKG_TS.equals(providerInfo.provider.getPackageName())) {
            this.mData.add(2, providerInfo);
        } else {
            this.mData.add(0, providerInfo);
        }
        saveItemToSp();
        notifyDataSetChanged();
        Log.d(TAG, "addWidget " + providerInfo.provider.getPackageName());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return getWidgetViewHolder(this.mContext, parent);
        }
        if (viewType == 1) {
            return getEditViewHolder(this.mContext, parent);
        }
        if (viewType == 2 || viewType == 3) {
            return getCardAppViewHolder(this.mContext, parent);
        }
        return new MyViewHolder(getEmptyView(parent));
    }

    private MyViewHolder getWidgetViewHolder(Context context, ViewGroup parent) {
        View viewInflate;
        int uiStyle = UiStyleConfig.getInstance(context).getUiStyle();
        View viewInflate2 = LayoutInflater.from(this.mContext).inflate(R.layout.item_card_widget_view, parent, false);
        if (uiStyle == 1) {
            if (AppUtils.isRightRudder()) {
                viewInflate2 = LayoutInflater.from(this.mContext).inflate(R.layout.denza_item_card_widget_view_right, parent, false);
            } else {
                viewInflate2 = LayoutInflater.from(this.mContext).inflate(R.layout.denza_item_card_widget_view, parent, false);
            }
        } else {
            if (uiStyle != 2) {
                if (AppUtils.isRightRudder()) {
                    viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.item_card_widget_view_right, parent, false);
                } else {
                    viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.item_card_widget_view, parent, false);
                }
            }
            return new MyViewHolder(viewInflate);
        }
        viewInflate = viewInflate2;
        return new MyViewHolder(viewInflate);
    }

    private MyViewHolder getCardAppViewHolder(Context context, ViewGroup parent) {
        View viewInflate;
        int uiStyle = UiStyleConfig.getInstance(context).getUiStyle();
        View viewInflate2 = LayoutInflater.from(this.mContext).inflate(R.layout.item_card_app_view, parent, false);
        if (uiStyle == 1) {
            viewInflate2 = LayoutInflater.from(this.mContext).inflate(R.layout.denza_item_card_app_view, parent, false);
        } else {
            if (uiStyle != 2) {
                viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.item_card_app_view, parent, false);
            }
            return new CardAppViewHolder(viewInflate);
        }
        viewInflate = viewInflate2;
        return new CardAppViewHolder(viewInflate);
    }

    private MyViewHolder getEditViewHolder(Context context, ViewGroup parent) {
        View viewInflate;
        int uiStyle = UiStyleConfig.getInstance(context).getUiStyle();
        View viewInflate2 = LayoutInflater.from(this.mContext).inflate(R.layout.item_card_add_view, parent, false);
        if (uiStyle == 1) {
            viewInflate2 = LayoutInflater.from(this.mContext).inflate(R.layout.denza_item_card_add_view, parent, false);
        } else {
            if (uiStyle != 2) {
                viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.item_card_add_view, parent, false);
            }
            return new CardAddViewHolder(viewInflate);
        }
        viewInflate = viewInflate2;
        return new CardAddViewHolder(viewInflate);
    }

    private View getEmptyView(ViewGroup parent) {
        View view = this.mEmptyView;
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.mEmptyView);
            }
        } else {
            this.mEmptyView = LayoutInflater.from(this.mContext).inflate(R.layout.item_card_widget_view, parent, false);
        }
        return this.mEmptyView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        if (this.mData != null && this.mData.size() != 0) {
            Object obj = this.mData.get(position);
            if (obj instanceof AppWidgetProviderInfo) {
                return 0;
            }
            if (obj instanceof AppInfo) {
                return 2;
            }
            if (obj instanceof ShortcutInfo) {
                return 3;
            }
            if (obj instanceof AddViewBean) {
                return 1;
            }
            Log.w(TAG, "getItemViewType position " + position + ", itemType -1! obj = " + obj);
        }
        return -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (this.mData == null || this.mData.size() == 0) {
            Log.w(TAG, "card adapter onBindViewHolder data is null");
            return;
        }
        Object obj = this.mData.get(position);
        int itemViewType = getItemViewType(position);
        holder.getContent().setTag(R.id.card_item_tag, Integer.valueOf(itemViewType));
        Log.d(TAG, "onBindViewHolder position " + position + ", itemType : " + itemViewType);
        if (itemViewType == 0) {
            AppWidgetHostView appWidgetHostViewBindWidgetView = bindWidgetView((AppWidgetProviderInfo) obj);
            if (appWidgetHostViewBindWidgetView != null) {
                ((ViewGroup) holder.getContent()).addView(appWidgetHostViewBindWidgetView);
                return;
            }
            return;
        }
        if (itemViewType == 1) {
            bindAddView(holder);
        } else if (itemViewType == 2 || itemViewType == 3) {
            bindAppOrShortcutView(holder, (AppOrShortcutItem) obj, position);
        }
    }

    private void bindAddView(final MyViewHolder holder) {
        if (holder instanceof CardAddViewHolder) {
            CardAddViewHolder cardAddViewHolder = (CardAddViewHolder) holder;
            boolean zIsDenza = UiStyleConfig.getInstance(this.mContext).isDenza();
            cardAddViewHolder.name.setTextColor(SkinManager.getInstance().getColor(R.color.card_app_name));
            if (zIsDenza) {
                cardAddViewHolder.card_app_cl.setBackground(SkinManager.getInstance().getMipmapDrawable(R.mipmap.denza_img_card_bg_addapp));
            } else if (AppUtils.isRtl(this.mContext)) {
                cardAddViewHolder.card_app_cl.setBackground(SkinManager.getInstance().getMipmapDrawable(R.mipmap.img_card_bg_addapp_rtl));
            } else {
                cardAddViewHolder.card_app_cl.setBackground(SkinManager.getInstance().getMipmapDrawable(R.mipmap.img_card_bg_addapp));
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.floatcard.CardAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (CardAdapter.this.mListener != null) {
                    CardAdapter.this.mListener.onAddClick(holder.itemView, CardAdapter.this.mData.get(CardAdapter.this.mData.size() - 1));
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.byd.card.floatcard.CardAdapter.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    private void bindAppOrShortcutView(final MyViewHolder holder, final AppOrShortcutItem appOrShortcutItem, final int position) {
        if (holder instanceof CardAppViewHolder) {
            final CardAppViewHolder cardAppViewHolder = (CardAppViewHolder) holder;
            cardAppViewHolder.icon.setBackground(appOrShortcutItem.getItemDrawable());
            cardAppViewHolder.name.setTextColor(SkinManager.getInstance().getColor(R.color.card_app_name));
            if (UiStyleConfig.getInstance(this.mContext).isDenza()) {
                cardAppViewHolder.constraintLayout.setBackground(SkinManager.getInstance().getMipmapDrawable(R.mipmap.denza_img_card_bg_addapp));
            } else if (AppUtils.isRtl(this.mContext)) {
                cardAppViewHolder.constraintLayout.setBackground(SkinManager.getInstance().getMipmapDrawable(R.mipmap.img_card_bg_addapp_rtl));
            } else {
                cardAppViewHolder.constraintLayout.setBackground(SkinManager.getInstance().getMipmapDrawable(R.mipmap.img_card_bg_addapp));
            }
            if (appOrShortcutItem.isForbidden()) {
                cardAppViewHolder.icon.setAlpha(0.4f);
                cardAppViewHolder.name.setAlpha(0.4f);
            } else {
                cardAppViewHolder.icon.setAlpha(1.0f);
                cardAppViewHolder.name.setAlpha(1.0f);
            }
            Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.CardAdapter.3
                @Override // java.lang.Runnable
                public void run() {
                    AppOrShortcutItem appOrShortcutItem2 = appOrShortcutItem;
                    if (appOrShortcutItem2 instanceof AppInfo) {
                        AppInfo appInfo = (AppInfo) appOrShortcutItem2;
                        if (CardDatabase.getInstance().getAppInfoDao().getAppInfoByPackage(appInfo.packageName) == null) {
                            SpUtils.removeCard(CardAdapter.this.mContext, appInfo.packageName, CardItemBean.TYPE_CARD_APP);
                            Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.CardAdapter.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    CardAdapter.this.mData.remove(appOrShortcutItem);
                                    CardAdapter.this.notifyItemRemoved(position);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if (appOrShortcutItem2 instanceof ShortcutInfo) {
                        ShortcutInfo shortcutInfo = (ShortcutInfo) appOrShortcutItem2;
                        if (CardDatabase.getInstance().getShortcutInfoDao().getShortcutByIdentify(shortcutInfo.getIdentify(), shortcutInfo.getOrigin()) == null) {
                            SpUtils.removeCard(CardAdapter.this.mContext, shortcutInfo.getItemIdentify(), CardItemBean.TYPE_CARD_SHORTCUT);
                            Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.CardAdapter.3.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    CardAdapter.this.mData.remove(appOrShortcutItem);
                                    CardAdapter.this.notifyItemRemoved(position);
                                }
                            });
                        }
                    }
                }
            });
            cardAppViewHolder.name.setText(getCardName(this.mContext, appOrShortcutItem));
            if (!"com.ts.carplay.app".equals(appOrShortcutItem.getItemIdentify()) && !"com.ts.androidauto.app".equals(appOrShortcutItem.getItemIdentify()) && this.mShowDelete) {
                cardAppViewHolder.delete.setVisibility(0);
            } else {
                cardAppViewHolder.delete.setVisibility(8);
            }
            cardAppViewHolder.constraintLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.card.floatcard.CardAdapter.4
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View v, MotionEvent event) {
                    CardAdapter.this.mTouchX = (int) event.getRawX();
                    CardAdapter.this.mTouchY = (int) event.getRawY();
                    return false;
                }
            });
            cardAppViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.floatcard.CardAdapter.5
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Log.i(CardAdapter.TAG, "itemView onClick isEditStatus :" + CardAdapter.this.isEditStatus);
                    if (CardAdapter.this.listener == null || CardAdapter.this.isEditStatus) {
                        return;
                    }
                    CardAdapter.this.listener.onItemClick(v, appOrShortcutItem, CardAdapter.this.mTouchX, CardAdapter.this.mTouchY);
                }
            });
            cardAppViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.byd.card.floatcard.CardAdapter.6
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View v) {
                    return "com.ts.carplay.app".equals(appOrShortcutItem.getItemIdentify()) || "com.ts.androidauto.app".equals(appOrShortcutItem.getItemIdentify());
                }
            });
            cardAppViewHolder.itemView.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.card.floatcard.CardAdapter.7
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() != 4) {
                        return false;
                    }
                    cardAppViewHolder.delete.setVisibility(8);
                    return false;
                }
            });
            cardAppViewHolder.delete.setOnClickListener(new AnonymousClass8(appOrShortcutItem, cardAppViewHolder));
        }
    }

    /* JADX INFO: renamed from: com.byd.card.floatcard.CardAdapter$8, reason: invalid class name */
    class AnonymousClass8 implements View.OnClickListener {
        final /* synthetic */ CardAppViewHolder val$appHolder;
        final /* synthetic */ AppOrShortcutItem val$appOrShortcutItem;

        AnonymousClass8(final AppOrShortcutItem val$appOrShortcutItem, final CardAppViewHolder val$appHolder) {
            this.val$appOrShortcutItem = val$appOrShortcutItem;
            this.val$appHolder = val$appHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (CardAdapter.this.mData == null || CardAdapter.this.mData.size() == 0) {
                Log.w(CardAdapter.TAG, "card delete onClick data is 0");
                return;
            }
            if (HfpManage.getInstance().getCallState() >= 1) {
                ToastUtils.show(CardAdapter.this.mContext, CardAdapter.this.mContext.getString(R.string.forbid_add_tip));
                return;
            }
            if (!CardAdapter.this.mIsDeleteBanned) {
                Iterator it = CardAdapter.this.mData.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (next instanceof AppOrShortcutItem) {
                        String itemIdentify = ((AppOrShortcutItem) next).getItemIdentify();
                        if (TextUtils.equals(itemIdentify, this.val$appOrShortcutItem.getItemIdentify())) {
                            CardAdapter.this.mData.remove(next);
                            Log.d(CardAdapter.TAG, "onClick delete " + itemIdentify);
                            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.CANCEL_APP_CARD, itemIdentify));
                            break;
                        }
                    }
                }
                int adapterPosition = this.val$appHolder.getAdapterPosition();
                CardAdapter.this.notifyItemRemoved(adapterPosition);
                if (adapterPosition == CardAdapter.this.mData.size()) {
                    CardAdapter cardAdapter = CardAdapter.this;
                    cardAdapter.notifyItemRangeChanged(cardAdapter.mData.size() - 1, 1);
                } else {
                    CardAdapter cardAdapter2 = CardAdapter.this;
                    cardAdapter2.notifyItemRangeChanged(adapterPosition, cardAdapter2.mData.size() - adapterPosition);
                }
                CardAdapter.this.mShowDelete = true;
                CardAdapter.this.saveItemToSp();
                CardAdapter.this.mIsDeleteBanned = true;
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.byd.card.floatcard.-$$Lambda$CardAdapter$8$Weac7wqb0NGQa2kdNkctUUCS6KM
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onClick$0$CardAdapter$8();
                    }
                }, 800L);
                return;
            }
            Log.d(CardAdapter.TAG, "card delete banned! too quick!");
        }

        public /* synthetic */ void lambda$onClick$0$CardAdapter$8() {
            CardAdapter.this.mIsDeleteBanned = false;
        }
    }

    public String getCardName(Context context, AppOrShortcutItem appOrShortcutItem) {
        if (context == null || appOrShortcutItem == null) {
            return "";
        }
        if (appOrShortcutItem instanceof AppInfo) {
            AppInfo appInfo = (AppInfo) appOrShortcutItem;
            String appName = AppUtils.getAppName(context, appInfo.packageName, appInfo.activity);
            return (TextUtils.isEmpty(appName) || "".equals(appName)) ? AppUtils.getAppNameByPkg(context, appInfo.packageName) : appName;
        }
        return ((ShortcutInfo) appOrShortcutItem).getTitle();
    }

    private int newWidget(ComponentName providerName) {
        int iAllocateAppWidgetId = this.mAppWidgetHost.allocateAppWidgetId();
        boolean zBindAppWidgetIdIfAllowed = this.mAppWidgetManager.bindAppWidgetIdIfAllowed(iAllocateAppWidgetId, providerName);
        Log.w(TAG, "newWidget appWidgetId =" + iAllocateAppWidgetId + ", success = " + zBindAppWidgetIdIfAllowed + ", providerName =" + providerName);
        if (zBindAppWidgetIdIfAllowed) {
            return iAllocateAppWidgetId;
        }
        this.mAppWidgetHost.deleteAppWidgetId(iAllocateAppWidgetId);
        return -1;
    }

    private AppWidgetHostView bindWidgetView(AppWidgetProviderInfo widgetProviderInfo) {
        AppWidgetHostView appWidgetHostViewCreateView;
        Exception e;
        ComponentName componentName = widgetProviderInfo.provider;
        int iIntValue = this.mWidgetIdMap.getOrDefault(componentName, -1).intValue();
        AppWidgetProviderInfo appWidgetInfo = this.mAppWidgetManager.getAppWidgetInfo(iIntValue);
        if (appWidgetInfo == null || !appWidgetInfo.provider.equals(componentName)) {
            Log.w(TAG, "bindWidgetView new!  saved widget id = " + iIntValue + ", saved provider = " + componentName + ", systemProvider = " + appWidgetInfo);
            iIntValue = newWidget(componentName);
            this.mWidgetIdMap.put(componentName, Integer.valueOf(iIntValue));
            SpUtils.saveWidgetIDMap(this.mContext, this.mWidgetIdMap);
            Log.w(TAG, "bindWidgetView new!  new widget id = " + iIntValue);
        }
        Log.i(TAG, "bindWidgetView appWidgetId =" + iIntValue + ", myProvider =" + componentName + ", data = " + this.mData);
        try {
            appWidgetHostViewCreateView = this.mAppWidgetHost.createView(this.mContext, iIntValue, widgetProviderInfo);
        } catch (Exception e2) {
            appWidgetHostViewCreateView = null;
            e = e2;
        }
        if (appWidgetHostViewCreateView == null) {
            return null;
        }
        try {
            appWidgetHostViewCreateView.setPadding(0, 0, 0, 0);
        } catch (Exception e3) {
            e = e3;
            Log.w(TAG, "AppWidgetHost createView Exception e " + e.getMessage() + ", ProviderInfo : " + widgetProviderInfo.provider.getPackageName());
        }
        return appWidgetHostViewCreateView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.mData == null) {
            return 0;
        }
        return this.mData.size();
    }

    @Override // com.byd.card.floatcard.DragListener
    public boolean onMoveItem(int fromPosition, int toPosition) {
        this.mData.add(toPosition, this.mData.remove(fromPosition));
        Log.d(TAG, "onMoveItem fromPosition = " + fromPosition + " toPosition = " + toPosition);
        notifyItemMoved(fromPosition, toPosition);
        saveItemToSp();
        return true;
    }

    public void saveItemToSp() {
        final ArrayList arrayList = new ArrayList();
        for (Object obj : this.mData) {
            if (obj instanceof AppInfo) {
                arrayList.add(new CardItemBean(((AppInfo) obj).packageName, CardItemBean.TYPE_CARD_APP));
            } else if (obj instanceof ShortcutInfo) {
                arrayList.add(new CardItemBean(((ShortcutInfo) obj).getItemIdentify(), CardItemBean.TYPE_CARD_SHORTCUT));
            } else if (obj instanceof AppWidgetProviderInfo) {
                arrayList.add(new CardItemBean(((AppWidgetProviderInfo) obj).provider.getPackageName(), CardItemBean.TYPE_CARD_WIDGET));
            }
        }
        Log.d(TAG, "saveItemToSp size = " + arrayList.size());
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.CardAdapter.9
            @Override // java.lang.Runnable
            public void run() {
                SpUtils.saveCardList(CardAdapter.this.mContext, arrayList);
            }
        });
    }

    public void removeCarplayOrAuto() {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.CardAdapter.10
            @Override // java.lang.Runnable
            public void run() {
                SpUtils.removeCarPlayOrAuto(CardAdapter.this.mContext);
            }
        });
    }

    @Override // com.byd.card.floatcard.DragListener
    public boolean isShowItemTypeView(View view) {
        try {
            return ((Integer) view.getTag(R.id.card_item_tag)).intValue() != 0;
        } catch (Exception unused) {
            return false;
        }
    }
}
