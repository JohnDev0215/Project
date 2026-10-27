package com.byd.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import b.a.k.a;
import b.a.k.h;
import b.a.k.p;
import com.byd.auto_photo.R;
import com.byd.bean.FileBean;
import com.byd.taskmanager.b.c;
import com.byd.util.e;
import com.byd.util.j;
import com.byd.util.m;
import com.byd.util.n;
import com.byd.util.w;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseFileDetailDialogFragment extends BaseDialogFragment {
    private a addressTask;
    protected int dialogHeight;
    private h externalManager;
    protected FileBean fileBean;
    private p loadMediaResolutionTask;
    private View mainContainerView;
    protected int minHeight;
    private String sdcardRoot;
    protected TextView tvAddr;
    protected TextView tvLen;
    protected TextView tvName;
    protected TextView tvPath;
    protected TextView tvSize;
    protected TextView tvTime;
    private int lastMeasureHeight = 0;
    private c<String> addressCallback = new c<String>() { // from class: com.byd.fragment.BaseFileDetailDialogFragment.2
        @Override // com.byd.taskmanager.b.c, com.byd.taskmanager.b.b
        public void onCanceled(String str) {
            w.c("load address onCanceled");
        }

        @Override // com.byd.taskmanager.b.b
        public void onComplete(String str, String str2) {
            TextView textView = BaseFileDetailDialogFragment.this.tvAddr;
            if (str2 != null) {
                textView.setText(str2);
            } else {
                textView.setText(R.string.unknown);
            }
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    };
    private c<Point> sizeCallback = new c<Point>() { // from class: com.byd.fragment.BaseFileDetailDialogFragment.3
        @Override // com.byd.taskmanager.b.b
        public void onComplete(String str, Point point) {
            if (point != null) {
                BaseFileDetailDialogFragment.this.tvSize.setText(point.x + "×" + point.y);
            }
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    };

    private String convertPath(String str) {
        String str2;
        Resources resources;
        int i;
        String parent;
        File parentFile = new File(str).getParentFile();
        if (parentFile != null && (parent = parentFile.getParent()) != null && parent.endsWith("Camera/Burstshoot")) {
            str = parentFile.getPath() + "/cover.tmp";
        }
        if (str.startsWith(this.sdcardRoot)) {
            str2 = this.sdcardRoot;
            resources = getResources();
            i = R.string.root_path;
        } else {
            str2 = "/storage/FFFF-FFFF";
            if (str.startsWith("/storage/FFFF-FFFF")) {
                resources = getResources();
                i = R.string.name_ivi;
            } else {
                str2 = "/storage/FFFF-FFFE";
                if (str.startsWith("/storage/FFFF-FFFE")) {
                    resources = getResources();
                    i = R.string.name_left_rse;
                } else {
                    str2 = "/storage/FFFF-FFFD";
                    if (str.startsWith("/storage/FFFF-FFFD")) {
                        resources = getResources();
                        i = R.string.name_right_rse;
                    } else {
                        str2 = "/storage/FFFF-FFFC";
                        if (!str.startsWith("/storage/FFFF-FFFC")) {
                            return j.e(getActivity()).q() > 0 ? j.e(getActivity()).o(str) : str;
                        }
                        resources = getResources();
                        i = R.string.name_fse;
                    }
                }
            }
        }
        return str.replace(str2, resources.getString(i));
    }

    private void showCreateTime() {
        this.tvTime.setText(new SimpleDateFormat(getResources().getString(R.string.time_format), Locale.getDefault()).format(new Date(this.fileBean.getLastModifiedTime())));
        w.c("file time by file last modified");
    }

    protected void configSizeByView() {
        this.lastMeasureHeight = 0;
        this.mainContainerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.byd.fragment.BaseFileDetailDialogFragment.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                FragmentActivity activity = BaseFileDetailDialogFragment.this.getActivity();
                if (activity == null) {
                    BaseFileDetailDialogFragment.this.mainContainerView.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
                int measuredHeight = BaseFileDetailDialogFragment.this.mainContainerView.getMeasuredHeight();
                w.c("FileDetail measure : " + measuredHeight);
                if (BaseFileDetailDialogFragment.this.lastMeasureHeight != measuredHeight) {
                    BaseFileDetailDialogFragment.this.lastMeasureHeight = measuredHeight;
                    return false;
                }
                BaseFileDetailDialogFragment.this.mainContainerView.getViewTreeObserver().removeOnPreDrawListener(this);
                BaseFileDetailDialogFragment.this.configSizeOnPreDraw(activity, measuredHeight);
                return true;
            }
        });
    }

    protected void configSizeOnPreDraw(Activity activity, int i) {
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getRealSize(point);
        int iMin = Math.min(point.x, point.y) - offsetOfFullSW();
        if (i > iMin) {
            this.dialogHeight = iMin;
        } else {
            this.dialogHeight = i;
            int i2 = this.minHeight;
            if (i < i2) {
                this.dialogHeight = i2;
            }
        }
        w.c("file detail view height : " + i + " " + iMin + " " + this.minHeight);
        setWindowSize(activity.getResources().getDimensionPixelSize(R.dimen.dialog_min_width), this.dialogHeight);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getButtonConfig() {
        return 1;
    }

    protected String getDetailTitle(String str) {
        Resources resources;
        int i;
        if (m.b(str)) {
            resources = getActivity().getResources();
            i = R.string.detail_image;
        } else if (m.d(str)) {
            resources = getActivity().getResources();
            i = R.string.detail_video;
        } else {
            resources = getActivity().getResources();
            i = R.string.detail_file;
        }
        return resources.getString(i);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getLayout() {
        return R.layout.fragment_file_detail;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected String getSureButtonText() {
        return getResources().getString(R.string.get_it);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected String getTitle() {
        FileBean fileBean = this.fileBean;
        return fileBean == null ? super.getTitle() : getDetailTitle(m.a(fileBean.getName()));
    }

    protected int offsetOfFullSW() {
        return 200;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.dialogHeight = 0;
        super.onCreate(bundle);
        this.sdcardRoot = Environment.getExternalStorageDirectory().getPath();
        this.externalManager = h.e(getActivity());
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        if (this.addressTask != null) {
            com.byd.taskmanager.a.e().a(this.addressTask);
        }
        if (this.loadMediaResolutionTask != null) {
            com.byd.taskmanager.a.e().a(this.loadMediaResolutionTask);
        }
        super.onDestroy();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.addressCallback.unregistUiObject();
        this.sizeCallback.unregistUiObject();
        super.onDestroyView();
        this.dialogHeight = 0;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        int dimensionPixelSize;
        int i;
        super.onStart();
        this.minHeight = getDialogHeight();
        if (this.dialogHeight > 1) {
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dialog_min_width);
            i = this.dialogHeight;
        } else {
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dialog_min_width);
            i = -2;
        }
        setWindowSize(dimensionPixelSize, i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        p pVar;
        this.mainContainerView = view;
        this.addressCallback.registUiObject(this);
        this.sizeCallback.registUiObject(this);
        this.tvName = (TextView) view.findViewById(R.id.tv_name);
        this.tvTime = (TextView) view.findViewById(R.id.tv_time);
        this.tvAddr = (TextView) view.findViewById(R.id.tv_addr);
        this.tvSize = (TextView) view.findViewById(R.id.tv_file_size);
        this.tvLen = (TextView) view.findViewById(R.id.tv_file_len);
        this.tvPath = (TextView) view.findViewById(R.id.tv_path);
        FileBean fileBean = this.fileBean;
        if (fileBean == null) {
            dismissAllowingStateLoss();
            w.c("FileDetailDialog dismiss by no data");
            return;
        }
        this.tvName.setText(fileBean.getName());
        showCreateTime();
        this.tvPath.setText(convertPath(this.fileBean.getPath()));
        File file = new File(this.fileBean.getPath());
        this.tvLen.setText(n.i(file.length()));
        if (e.G(getActivity()) || !getResources().getBoolean(R.bool.support_location)) {
            view.findViewById(R.id.item_detail_addr).setVisibility(8);
        } else {
            view.findViewById(R.id.item_detail_addr).setVisibility(0);
            String country = getResources().getConfiguration().locale.getCountry();
            if (this.fileBean.getAddress() == null || !(this.fileBean.getAddressLocale() == null || this.fileBean.getAddressLocale().equals(country))) {
                this.addressTask = new a(getActivity(), this.fileBean, this.addressCallback, country);
                com.byd.taskmanager.a.e().d(this.addressTask);
            } else {
                this.tvAddr.setText(this.fileBean.getAddress());
            }
        }
        String strA = m.a(file.getName());
        if (m.b(strA)) {
            pVar = new p(this.fileBean.getPath(), 1, this.sizeCallback);
        } else {
            if (!m.d(strA)) {
                this.tvSize.setText("-");
                configSizeByView();
            }
            pVar = new p(this.fileBean.getPath(), 2, this.sizeCallback);
        }
        this.loadMediaResolutionTask = pVar;
        com.byd.taskmanager.a.e().d(this.loadMediaResolutionTask);
        configSizeByView();
    }

    public void setFileBean(FileBean fileBean) {
        this.fileBean = fileBean;
    }

    protected void setWindowSize(int i, int i2) {
        Window window;
        if (getDialog() == null || (window = getDialog().getWindow()) == null) {
            return;
        }
        window.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (attributes.width == i && attributes.height == i2) {
            return;
        }
        w.c("detail dialog : " + i + "x" + i2);
        window.setLayout(i, i2);
    }
}
