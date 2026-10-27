package com.byd.fragment;

import android.car.VehicleAreaDoor;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.a.d;
import b.a.e.d;
import b.a.e.v;
import com.byd.auto_photo.QrCodeShareActivity;
import com.byd.auto_photo.R;
import com.byd.bean.FileBean;
import com.byd.util.k;
import com.byd.util.n;
import com.byd.util.w;
import com.byd.view.GridItemDecoration;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseShareDialogFragment extends BaseDialogFragment {
    private v adapter;
    private boolean alreadyLoaded;
    protected int columns;
    protected View containerView;
    protected int itemSpacingHorizontal;
    protected int itemSpacingVertical;
    private Thread loadActivitiesThread;
    private MHandler mHandler;
    protected RecyclerView mRecycler;
    private ProgressDialogFragment progressDialog;
    private List<FileBean> shareFileBeans;
    private Intent shareIntent;
    protected boolean supportShareQrCode;
    private TextView tvNoActivityTips;

    private static class MHandler extends d<BaseShareDialogFragment> {
        public MHandler(BaseShareDialogFragment baseShareDialogFragment) {
            super(baseShareDialogFragment);
        }

        @Override // b.a.d
        public void handleMessage(BaseShareDialogFragment baseShareDialogFragment, Message message) {
            baseShareDialogFragment.loadFinish(message);
        }
    }

    private void calcContentHeight(List<ResolveInfo> list) {
        if (list == null || list.size() == 0) {
            this.tvNoActivityTips.setVisibility(0);
            return;
        }
        if (((int) Math.ceil(list.size() / this.columns)) >= 2) {
            ViewGroup.LayoutParams layoutParams = this.contentView.getLayoutParams();
            int iB = (this.adapter.b() + this.itemSpacingVertical) * 2;
            if (layoutParams == null) {
                layoutParams = new LinearLayout.LayoutParams(-1, iB);
                this.contentView.setLayoutParams(layoutParams);
            } else {
                layoutParams.height = iB;
            }
            w.c("====calcContentHeight===" + layoutParams.height);
            this.contentView.setLayoutParams(layoutParams);
        }
    }

    private void configItemSize() {
        this.mRecycler.setLayoutManager(new GridLayoutManager(getActivity(), this.columns));
        int dialogWidth = getDialogWidth();
        int contentHorizontalPadding = getContentHorizontalPadding();
        if (contentHorizontalPadding > 0) {
            configContentPadding(contentHorizontalPadding);
            dialogWidth -= contentHorizontalPadding * 2;
        }
        this.adapter.g(this.columns, this.itemSpacingHorizontal, dialogWidth, getImageDefSize());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getFileBeanUri(FileBean fileBean) {
        return Build.VERSION.SDK_INT >= 24 ? n.k(getActivity(), new File(fileBean.getPath())) : Uri.fromFile(new File(fileBean.getPath()));
    }

    private void showTargets() {
        if (this.shareFileBeans == null) {
            return;
        }
        Thread thread = this.loadActivitiesThread;
        if (thread != null && thread.isAlive()) {
            this.loadActivitiesThread.interrupt();
            this.loadActivitiesThread = null;
        }
        if (this.progressDialog == null) {
            ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment(R.string.loading_please_wait);
            this.progressDialog = progressDialogFragment;
            progressDialogFragment.setUiVisibility(this.uiVisibility);
        }
        if (!this.progressDialog.isShowing()) {
            this.progressDialog.show(getChildFragmentManager(), "share_progress");
        }
        Thread thread2 = new Thread() { // from class: com.byd.fragment.BaseShareDialogFragment.2
            /* JADX WARN: Code restructure failed: missing block: B:26:0x00f6, code lost:
            
                if (((com.byd.bean.FileBean) r14.this$0.shareFileBeans.get(0)).getType() == 2) goto L27;
             */
            @Override // java.lang.Thread, java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instruction units count: 375
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.byd.fragment.BaseShareDialogFragment.AnonymousClass2.run():void");
            }
        };
        this.loadActivitiesThread = thread2;
        thread2.start();
    }

    protected void configContentPadding(int i) {
        ViewGroup.LayoutParams layoutParams = this.containerView.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            this.containerView.setPadding(i, 0, i, 0);
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.leftMargin = i;
        marginLayoutParams.rightMargin = i;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void configWindowAttrs(Window window) {
        window.setLayout(getDialogWidth(), -2);
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        w.c("ShareDialog dismiss");
        super.dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismissAllowingStateLoss() {
        w.c("ShareDialog dismissAllowingStateLoss");
        super.dismissAllowingStateLoss();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getButtonConfig() {
        return 2;
    }

    protected int getContentHorizontalPadding() {
        return getResources().getDimensionPixelSize(R.dimen.share_dialog_content_padding);
    }

    protected int getImageDefSize() {
        return getResources().getDimensionPixelSize(R.dimen.share_item_icon_def_size);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getLayout() {
        return R.layout.fragment_share_dialog;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected String getTitle() {
        return getString(R.string.share_title);
    }

    protected void loadFinish(Message message) {
        ProgressDialogFragment progressDialogFragment = this.progressDialog;
        if (progressDialogFragment != null) {
            progressDialogFragment.dismissAllowingStateLoss();
        }
        this.alreadyLoaded = true;
        List<ResolveInfo> list = (List) message.obj;
        if (this.adapter != null) {
            calcContentHeight(list);
            this.adapter.h(list);
        }
        this.progressDialog = null;
    }

    protected void onConfig() {
        this.columns = getResources().getInteger(R.integer.share_target_columns);
        this.itemSpacingHorizontal = getResources().getDimensionPixelSize(R.dimen.share_item_horizontal_spacing);
        this.itemSpacingVertical = getResources().getDimensionPixelSize(R.dimen.share_item_vertical_spacing);
        this.supportShareQrCode = getResources().getInteger(R.integer.support_share_qr_code) == 1;
    }

    protected void onConfigShareIntent(Intent intent) {
        intent.addFlags(VehicleAreaDoor.DOOR_HOOD);
        intent.addFlags(32768);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        this.mHandler = new MHandler(this);
        super.onCreate(bundle);
        onConfig();
        this.alreadyLoaded = false;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        v vVar = this.adapter;
        if (vVar != null) {
            vVar.h(null);
            this.adapter.f();
            this.adapter = null;
        }
        MHandler mHandler = this.mHandler;
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            this.mHandler.release();
            this.mHandler = null;
        }
        super.onDestroy();
        this.alreadyLoaded = false;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        w.c("ShareDialog onStart");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        this.containerView = view.findViewById(R.id.share_apps_container);
        this.mRecycler = (RecyclerView) view.findViewById(R.id.m_recycler_targets);
        this.tvNoActivityTips = (TextView) view.findViewById(R.id.no_activity_tips);
        List<FileBean> list = this.shareFileBeans;
        if (list == null || list.size() == 0) {
            dismissAllowingStateLoss();
            w.c("ShareDialog dismiss by no data");
            return;
        }
        this.mRecycler.addItemDecoration(new GridItemDecoration(this.itemSpacingHorizontal, this.itemSpacingVertical));
        if (this.adapter == null) {
            v vVar = new v(getActivity(), this.supportShareQrCode);
            this.adapter = vVar;
            vVar.i(new d.a() { // from class: com.byd.fragment.BaseShareDialogFragment.1
                public void onContentExpand(int i, int i2) {
                    if (i >= 2) {
                        ViewGroup.LayoutParams layoutParams = BaseShareDialogFragment.this.contentView.getLayoutParams();
                        BaseShareDialogFragment baseShareDialogFragment = BaseShareDialogFragment.this;
                        layoutParams.height = (i2 + baseShareDialogFragment.itemSpacingVertical) * 2;
                        baseShareDialogFragment.contentView.setLayoutParams(layoutParams);
                    }
                }

                @Override // b.a.e.d.a
                public void onShareItemClick(ResolveInfo resolveInfo, int i) {
                    BaseShareDialogFragment baseShareDialogFragment = BaseShareDialogFragment.this;
                    if (baseShareDialogFragment.supportShareQrCode && i == 0) {
                        Intent intent = new Intent(baseShareDialogFragment.getActivity(), (Class<?>) QrCodeShareActivity.class);
                        intent.putExtra("extras_ui_visibility", BaseShareDialogFragment.this.uiVisibility);
                        intent.putParcelableArrayListExtra("extras_share_beans", new ArrayList<>(BaseShareDialogFragment.this.shareFileBeans));
                        BaseShareDialogFragment.this.getActivity().startActivity(intent);
                        k.c(BaseShareDialogFragment.this.getActivity()).h("08", "01");
                    } else {
                        try {
                            ActivityInfo activityInfo = resolveInfo.activityInfo;
                            String str = activityInfo.applicationInfo.packageName;
                            String str2 = activityInfo.name;
                            baseShareDialogFragment.shareIntent.setClassName(str, str2);
                            BaseShareDialogFragment baseShareDialogFragment2 = BaseShareDialogFragment.this;
                            baseShareDialogFragment2.onConfigShareIntent(baseShareDialogFragment2.shareIntent);
                            BaseShareDialogFragment baseShareDialogFragment3 = BaseShareDialogFragment.this;
                            baseShareDialogFragment3.startActivity(baseShareDialogFragment3.shareIntent);
                            w.c("app:" + str + " activity:" + str2);
                            k.c(BaseShareDialogFragment.this.getActivity()).h("08", str);
                        } catch (Exception e) {
                            e.printStackTrace();
                            w.e(BaseShareDialogFragment.this.getActivity(), R.string.share_fail);
                        }
                    }
                    BaseShareDialogFragment.this.dismiss();
                    BaseShareDialogFragment.this.shareFileBeans = null;
                }
            });
        }
        configItemSize();
        this.mRecycler.setAdapter(this.adapter);
        if (this.shareFileBeans != null) {
            if (this.alreadyLoaded) {
                calcContentHeight(this.adapter.c());
            } else {
                showTargets();
            }
        }
    }

    public void setShareFileBean(FileBean fileBean) {
        List<FileBean> list = this.shareFileBeans;
        if (list == null) {
            this.shareFileBeans = new ArrayList(1);
        } else {
            list.clear();
        }
        this.shareFileBeans.add(fileBean);
    }

    public void setShareFileBeans(List<FileBean> list) {
        this.shareFileBeans = list;
    }
}
