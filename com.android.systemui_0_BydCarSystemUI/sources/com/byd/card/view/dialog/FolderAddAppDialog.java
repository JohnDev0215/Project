package com.byd.card.view.dialog;

import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.byd.card.adapter.FolderAddAdapter;
import com.byd.card.bean.Constant;
import com.byd.card.db.CardDatabase;
import com.byd.card.drag.ApplicationInfo;
import com.byd.card.floatcard.Executors;
import com.byd.card.utils.AppUtils;
import com.byd.card.view.activity.MoreAppActivity;
import com.byd.card.widget.ViewUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class FolderAddAppDialog extends BaseDialog {
    private static final String ALL_APP = "all_app";
    private static final String TAG = "FolderAddAppDialog";
    private static final int UPDATE = 1000;
    private List<ApplicationInfo> allAppList;
    private List<ApplicationInfo> allList;
    private OnCheckedChangedListener listener;
    private MoreAppActivity mActivity;
    private FolderAddAdapter mAdapter;
    private TextView mCancel;
    private List<ApplicationInfo> mCheckList;
    private TextView mConfirm;
    private Paint mPaint;
    private RecyclerView mRecycler;
    private TextView mTitle;
    private String mTitleText;
    private Handler mainHandler;

    public interface OnCheckedChangedListener {
        void onCheckedChanged(List<ApplicationInfo> checkList, List<ApplicationInfo> list);
    }

    public FolderAddAppDialog(MoreAppActivity activity, int layoutId, ApplicationInfo applicationInfo) {
        super(activity, layoutId, applicationInfo);
        this.mCheckList = new ArrayList();
        this.allList = new ArrayList();
        this.mActivity = null;
        this.mainHandler = new Handler(Looper.getMainLooper()) { // from class: com.byd.card.view.dialog.FolderAddAppDialog.3
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what != 1000) {
                    return;
                }
                FolderAddAppDialog.this.allAppList = msg.getData().getParcelableArrayList(FolderAddAppDialog.ALL_APP);
                if (FolderAddAppDialog.this.allAppList != null && !FolderAddAppDialog.this.allAppList.isEmpty()) {
                    FolderAddAppDialog.this.mTitle.setText(FolderAddAppDialog.this.mTitleText + "(" + FolderAddAppDialog.this.mCheckList.size() + "/" + FolderAddAppDialog.this.allAppList.size() + ")");
                }
                FolderAddAppDialog.this.mAdapter.updateAdapter(FolderAddAppDialog.this.allAppList);
                FolderAddAppDialog.this.mAdapter.notifyDataSetChanged();
            }
        };
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = (int) activity.getResources().getDimension(R.dimen.dp_616);
        attributes.height = (int) activity.getResources().getDimension(R.dimen.dp_528);
        window.clearFlags(2);
        window.setAttributes(attributes);
        this.mActivity = activity;
        this.mPaint = new Paint();
    }

    @Override // com.byd.card.view.dialog.BaseDialog
    public void finishInitView(Dialog dialog) {
        this.mTitle = (TextView) dialog.findViewById(R.id.add_app_title);
        this.mRecycler = (RecyclerView) dialog.findViewById(R.id.add_content);
        this.mConfirm = (TextView) dialog.findViewById(R.id.add_confirm);
        this.mCancel = (TextView) dialog.findViewById(R.id.add_cancel);
        this.mTitleText = this.activity.getResources().getString(R.string.add_to_folder);
        this.mRecycler.addItemDecoration(new SpacesItemDecoration());
        ViewUtils.setRecyclerViewOverScroll(this.mRecycler);
        FolderAddAdapter folderAddAdapter = new FolderAddAdapter(this.activity);
        this.mAdapter = folderAddAdapter;
        this.mRecycler.setAdapter(folderAddAdapter);
        this.mRecycler.setVerticalScrollbarPosition(2);
        initListener();
    }

    @Override // com.byd.card.view.dialog.BaseDialog
    public void onViewClick(View view) {
        int id = view.getId();
        if (id == R.id.add_cancel) {
            dismissDialog();
            return;
        }
        if (id != R.id.add_confirm) {
            return;
        }
        List<ApplicationInfo> checkedList = this.mAdapter.getCheckedList();
        List<ApplicationInfo> unCheckedList = this.mAdapter.getUnCheckedList();
        OnCheckedChangedListener onCheckedChangedListener = this.listener;
        if (onCheckedChangedListener != null) {
            onCheckedChangedListener.onCheckedChanged(checkedList, unCheckedList);
        }
        dismissDialog();
    }

    @Override // com.byd.card.view.dialog.BaseDialog, android.app.Dialog, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            this.mActivity.cancelTimer();
        } else if (ev.getAction() == 1) {
            this.mActivity.startAD();
        }
        return super.dispatchTouchEvent(ev);
    }

    public List<ApplicationInfo> getCancelList() {
        final List<ApplicationInfo> checkedList = this.mAdapter.getCheckedList();
        if (checkedList == null || checkedList.isEmpty()) {
            return this.mCheckList;
        }
        List<ApplicationInfo> list = this.mCheckList;
        if (list != null && list.size() <= checkedList.size()) {
            return (List) this.mCheckList.stream().filter(new Predicate() { // from class: com.byd.card.view.dialog.-$$Lambda$FolderAddAppDialog$KLyHth_LU-3Ca1th5xZIdgLH9os
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return FolderAddAppDialog.lambda$getCancelList$0(checkedList, (ApplicationInfo) obj);
                }
            }).collect(Collectors.toList());
        }
        this.mCheckList.removeAll(checkedList);
        return this.mCheckList;
    }

    static /* synthetic */ boolean lambda$getCancelList$0(List list, ApplicationInfo applicationInfo) {
        return !list.contains(applicationInfo);
    }

    public void setAppInfoList(List<ApplicationInfo> checkList) {
        this.mCheckList.clear();
        this.mCheckList.addAll(checkList);
        for (ApplicationInfo applicationInfo : checkList) {
            if (applicationInfo != null) {
                applicationInfo.checked = true;
                this.allList.add(applicationInfo);
            }
        }
        initData();
    }

    private void initData() {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.view.dialog.FolderAddAppDialog.1
            @Override // java.lang.Runnable
            public void run() {
                FolderAddAppDialog.this.allAppList = CardDatabase.getInstance().getWorkSpaceItemDao().getExceptFolderAllApp();
                Iterator it = FolderAddAppDialog.this.allAppList.iterator();
                while (it.hasNext()) {
                    ApplicationInfo applicationInfo = (ApplicationInfo) it.next();
                    Iterator it2 = FolderAddAppDialog.this.allList.iterator();
                    while (it2.hasNext()) {
                        if (((ApplicationInfo) it2.next()).activity.equals(applicationInfo.activity)) {
                            it.remove();
                        }
                    }
                }
                for (ApplicationInfo applicationInfo2 : FolderAddAppDialog.this.allAppList) {
                    if (!"com.byd.auto_camera".equals(applicationInfo2.mPackageName) || AppUtils.isShowCamera()) {
                        if (!"com.byd.avm".equals(applicationInfo2.mPackageName) || AppUtils.isShowAVM()) {
                            if (!"com.byd.auto_photo".equals(applicationInfo2.mPackageName) || !Constant.HONG_KONG_CODE.equals(AppUtils.getCountryCode())) {
                                FolderAddAppDialog.this.allList.add(FolderAddAppDialog.this.turnAppBean(applicationInfo2));
                            }
                        }
                    }
                }
                Message messageObtain = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(FolderAddAppDialog.ALL_APP, (ArrayList) FolderAddAppDialog.this.allList);
                messageObtain.what = 1000;
                messageObtain.setData(bundle);
                FolderAddAppDialog.this.mainHandler.sendMessage(messageObtain);
            }
        });
    }

    private void initListener() {
        setOnClickListener(this.mConfirm);
        setOnClickListener(this.mCancel);
        this.mAdapter.setOnCheckedChangeListener(new FolderAddAdapter.OnCheckedChangeListener() { // from class: com.byd.card.view.dialog.FolderAddAppDialog.2
            @Override // com.byd.card.adapter.FolderAddAdapter.OnCheckedChangeListener
            public void onCheckedChanged(List<ApplicationInfo> list) {
                FolderAddAppDialog.this.mTitle.setText(FolderAddAppDialog.this.mTitleText + "(" + list.size() + "/" + FolderAddAppDialog.this.allAppList.size() + ")");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ApplicationInfo turnAppBean(ApplicationInfo app) {
        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.folderId = app.folderId;
        applicationInfo.setIndex(app.getIndex());
        applicationInfo.setTitle(app.getTitle());
        applicationInfo.setIcon(BitmapFactory.decodeByteArray(app.getIconBytes(), 0, app.getIconBytes().length));
        applicationInfo.setPackageName(app.getPackageName());
        applicationInfo.setActivity(app.getActivity());
        applicationInfo.setVersionCode(app.getVersionCode());
        applicationInfo.setVersionName(app.getVersionName());
        applicationInfo.setType(app.type);
        return applicationInfo;
    }

    public void dismissDialog() {
        this.mainHandler.removeCallbacksAndMessages(null);
        cancel();
    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private static final int SPAN_COUNT = 4;

        SpacesItemDecoration() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            if (AppUtils.isLandSpace(view.getContext())) {
                int i = childAdapterPosition % 4;
                int dimension = (int) view.getContext().getResources().getDimension(R.dimen.dp_12);
                outRect.left = dimension - ((i * dimension) / 4);
                outRect.right = ((i + 1) * dimension) / 4;
                if (childAdapterPosition < 4) {
                    outRect.top = (int) view.getContext().getResources().getDimension(R.dimen.dp_40);
                }
                outRect.bottom = (int) view.getContext().getResources().getDimension(R.dimen.dp_16);
            } else {
                int i2 = childAdapterPosition % 4;
                if (childAdapterPosition < 4) {
                    outRect.top = (int) view.getContext().getResources().getDimension(R.dimen.dp_40);
                }
                int dimension2 = (int) view.getContext().getResources().getDimension(R.dimen.dp_16);
                outRect.left = dimension2 - ((i2 * dimension2) / 4);
                outRect.right = ((i2 + 1) * dimension2) / 4;
                outRect.bottom = (int) view.getContext().getResources().getDimension(R.dimen.dp_16);
            }
            if (childAdapterPosition < 4) {
                outRect.top = (int) view.getContext().getResources().getDimension(R.dimen.dp_20);
            } else {
                outRect.top = (int) view.getContext().getResources().getDimension(R.dimen.dp_16);
            }
        }
    }

    public void setOnCheckedChangedListener(OnCheckedChangedListener listener) {
        this.listener = listener;
    }
}
