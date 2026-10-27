package com.byd.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.auto_photo.R;
import com.byd.auto_photo.a;
import com.byd.util.k;
import com.byd.util.w;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseFileDeleteDialogFragment extends BaseDialogFragment {
    private int deleteBurstNums;
    private int deleteType;
    private boolean folder;
    private boolean isMoreFiles;
    private OnDeleteDialogListener onDeleteDialogListener;
    private boolean relayout;
    private TextView tvDeleteTips;

    public interface OnDeleteDialogListener {
        void onSureDelete();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getLayout() {
        return R.layout.fragment_file_delete_dialog;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected String getSureButtonText() {
        return a.f1178a.intValue() >= 35 ? getResources().getString(R.string.delete) : super.getSureButtonText();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected String getTitle() {
        if (a.f1178a.intValue() != 2) {
            return getString(R.string.delete);
        }
        return null;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void initUiData() {
        TextView textView;
        String string;
        super.initUiData();
        if (this.isMoreFiles || this.folder) {
            this.tvDeleteTips.setText(R.string.del_more_tips);
            return;
        }
        int i = this.deleteType;
        if (i == 2) {
            textView = this.tvDeleteTips;
            string = getResources().getString(R.string.del_video_tips);
        } else if (i != 3) {
            this.tvDeleteTips.setText(getResources().getString(R.string.del_img_tips));
            return;
        } else {
            textView = this.tvDeleteTips;
            string = String.format(getResources().getString(R.string.del_burstshoot_img_tips), Integer.valueOf(this.deleteBurstNums));
        }
        textView.setText(string);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(@NonNull DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        w.c("FileDeleteDialog onCancel");
        onCancelClick();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void onCancelClick() {
        w.c("FileDeleteDialog onCancel click");
        super.onCancelClick();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        k.c(getActivity()).g("06");
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void onDialogDismiss(DialogInterface dialogInterface) {
        w.c("delete file dismiss");
        super.onDialogDismiss(dialogInterface);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void onSureClick() {
        super.onSureClick();
        OnDeleteDialogListener onDeleteDialogListener = this.onDeleteDialogListener;
        if (onDeleteDialogListener != null) {
            onDeleteDialogListener.onSureDelete();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.tvDeleteTips = (TextView) view.findViewById(R.id.tv_delete_tips);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void reLayout() {
        this.relayout = true;
        super.reLayout();
        if (isShowing()) {
            return;
        }
        this.relayout = false;
    }

    public void setFolder(boolean z) {
        this.folder = z;
    }

    public void setInfo(int i, int i2, boolean z) {
        this.deleteType = i;
        this.deleteBurstNums = i2;
        this.isMoreFiles = z;
    }

    public void setOnDeleteDialogListener(OnDeleteDialogListener onDeleteDialogListener) {
        this.onDeleteDialogListener = onDeleteDialogListener;
    }
}
