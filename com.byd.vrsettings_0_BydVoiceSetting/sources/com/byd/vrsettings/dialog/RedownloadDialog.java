package com.byd.vrsettings.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import com.byd.vrdownload.model.DownloadTask;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.databinding.DialogCommon2Binding;
import com.byd.vrsettings.service.AidlServiceManager;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.LanguageUtil;
import com.byd.vrsettings.utils.LogUtils;
import com.byd.vrsettings.utils.NetUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RedownloadDialog extends Dialog {
    public RedownloadDialog(Context context) {
        this(context, R.style.custom_dialog8155);
    }

    public RedownloadDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {
        DialogCommon2Binding binding;
        View contentView;
        Context context;
        RedownloadDialog dialog;
        ConstraintSet set;
        List<DownloadTask> taskList;
        StringBuffer sb = new StringBuffer();
        List<String> failLangeList = new ArrayList();

        public Builder(Context context) {
            this.taskList = null;
            this.context = context;
            DialogCommon2Binding dialogCommon2BindingInflate = DialogCommon2Binding.inflate((LayoutInflater) context.getSystemService("layout_inflater"));
            this.binding = dialogCommon2BindingInflate;
            this.contentView = dialogCommon2BindingInflate.getRoot();
            this.taskList = new ArrayList();
            ConstraintSet constraintSet = new ConstraintSet();
            this.set = constraintSet;
            constraintSet.clone(this.binding.layoutDialogRoot);
            if (Common.isRightDriver() || Common.isRtl(context)) {
                this.set.clear(R.id.positive, 6);
                this.set.clear(R.id.positive, 7);
                this.set.connect(R.id.positive, 7, 0, 7, (int) TypedValue.applyDimension(1, 104.0f, context.getResources().getDisplayMetrics()));
                this.set.connect(R.id.positive, 6, R.id.negative, 7, (int) TypedValue.applyDimension(1, 48.0f, context.getResources().getDisplayMetrics()));
                this.set.clear(R.id.negative, 6);
                this.set.clear(R.id.negative, 7);
                this.set.connect(R.id.negative, 7, R.id.positive, 6);
                this.set.connect(R.id.negative, 6, 0, 6, (int) TypedValue.applyDimension(1, 104.0f, context.getResources().getDisplayMetrics()));
            } else {
                this.set.clear(R.id.negative, 6);
                this.set.clear(R.id.negative, 7);
                this.set.connect(R.id.negative, 7, 0, 7, (int) TypedValue.applyDimension(1, 104.0f, context.getResources().getDisplayMetrics()));
                this.set.connect(R.id.negative, 6, R.id.positive, 7, (int) TypedValue.applyDimension(1, 48.0f, context.getResources().getDisplayMetrics()));
                this.set.clear(R.id.positive, 6);
                this.set.clear(R.id.positive, 7);
                this.set.connect(R.id.positive, 7, R.id.negative, 6);
                this.set.connect(R.id.positive, 6, 0, 6, (int) TypedValue.applyDimension(1, 104.0f, context.getResources().getDisplayMetrics()));
            }
            this.set.applyTo(this.binding.layoutDialogRoot);
        }

        public RedownloadDialog create() {
            ViewGroup viewGroup;
            View view = this.contentView;
            if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
                viewGroup.removeView(this.contentView);
            }
            if (this.dialog == null) {
                this.dialog = new RedownloadDialog(this.context);
            }
            this.dialog.setContentView(this.contentView, new ViewGroup.LayoutParams(-2, -2));
            this.dialog.setCanceledOnTouchOutside(true);
            this.dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.dialog.RedownloadDialog.Builder.1
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    if (Builder.this.taskList != null) {
                        Builder.this.taskList.clear();
                    }
                    Builder.this.sb = new StringBuffer();
                }
            });
            this.binding.negative.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.RedownloadDialog$Builder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.m40xdcdae3bc(view2);
                }
            });
            this.binding.dialogBg.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.RedownloadDialog$Builder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.m41xab37e1b(view2);
                }
            });
            return this.dialog;
        }

        /* JADX INFO: renamed from: lambda$create$0$com-byd-vrsettings-dialog-RedownloadDialog$Builder, reason: not valid java name */
        /* synthetic */ void m40xdcdae3bc(View view) {
            this.dialog.dismiss();
        }

        /* JADX INFO: renamed from: lambda$create$1$com-byd-vrsettings-dialog-RedownloadDialog$Builder, reason: not valid java name */
        /* synthetic */ void m41xab37e1b(View view) {
            this.dialog.dismiss();
        }

        public Builder setTitle(String str) {
            this.binding.textiewTitle.setVisibility(0);
            this.binding.textiewTitle.setText(str);
            return this;
        }

        public Builder setContent(String str) {
            this.binding.textviewContent.setText(str);
            return this;
        }

        public Builder setLight(boolean z) {
            if (z) {
                this.binding.textiewTitle.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title));
                this.binding.textviewContent.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title));
                this.binding.layoutDialogRoot.setBackgroundResource(R.drawable.bg_setting_dialog);
                this.binding.negative.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title));
                this.binding.dialogBg.setBackgroundColor(ContextCompat.getColor(this.context, R.color.dialog_backgroud_color_day));
            } else {
                this.binding.textiewTitle.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title_night));
                this.binding.textviewContent.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title_night));
                this.binding.layoutDialogRoot.setBackgroundResource(R.drawable.bg_setting_dialog_night);
                this.binding.negative.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title_night));
                this.binding.dialogBg.setBackgroundColor(ContextCompat.getColor(this.context, R.color.dialog_backgroud_color_night));
            }
            return this;
        }

        public Builder setPositiveClickLitener(String str, final View.OnClickListener onClickListener) {
            this.binding.positive.setText(str);
            this.binding.positive.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.RedownloadDialog$Builder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m44xafc96970(onClickListener, view);
                }
            });
            return this;
        }

        /* JADX INFO: renamed from: lambda$setPositiveClickLitener$2$com-byd-vrsettings-dialog-RedownloadDialog$Builder, reason: not valid java name */
        /* synthetic */ void m44xafc96970(View.OnClickListener onClickListener, View view) {
            ViewGroup viewGroup = (ViewGroup) this.contentView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.contentView);
            }
            this.dialog.dismiss();
            redownload();
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }

        public Builder setNegativeClickLitener(String str, final View.OnClickListener onClickListener) {
            this.binding.negative.setText(str);
            this.binding.negative.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.RedownloadDialog$Builder$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m42xcb8fb913(onClickListener, view);
                }
            });
            return this;
        }

        /* JADX INFO: renamed from: lambda$setNegativeClickLitener$3$com-byd-vrsettings-dialog-RedownloadDialog$Builder, reason: not valid java name */
        /* synthetic */ void m42xcb8fb913(View.OnClickListener onClickListener, View view) {
            ViewGroup viewGroup = (ViewGroup) this.contentView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.contentView);
            }
            this.dialog.dismiss();
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }

        public Builder setNegativeClickLitener(final View.OnClickListener onClickListener) {
            this.binding.negative.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.RedownloadDialog$Builder$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m43xf9685372(onClickListener, view);
                }
            });
            return this;
        }

        /* JADX INFO: renamed from: lambda$setNegativeClickLitener$4$com-byd-vrsettings-dialog-RedownloadDialog$Builder, reason: not valid java name */
        /* synthetic */ void m43xf9685372(View.OnClickListener onClickListener, View view) {
            this.dialog.dismiss();
            onClickListener.onClick(view);
        }

        public Builder addData(DownloadTask downloadTask) {
            List<DownloadTask> list = this.taskList;
            if (list == null || list.contains(downloadTask)) {
                LogUtils.e("wens", "taskList:" + this.taskList.toString());
                return this;
            }
            this.taskList.add(downloadTask);
            this.failLangeList.clear();
            for (int i = 0; i < this.taskList.size(); i++) {
                String strConvertAll = LanguageUtil.convertAll(this.contentView.getContext(), this.taskList.get(i).getLanguageUpdate().getLanguage());
                if (!this.failLangeList.contains(strConvertAll)) {
                    this.failLangeList.add(strConvertAll);
                }
            }
            if (this.failLangeList.size() > 1) {
                setContent(String.format(BydVoiceSettingApplication.getContext().getString(R.string.text_dialog_download_fail_multi_tip), this.failLangeList.get(0)));
            } else if (this.failLangeList.size() == 1) {
                setContent(String.format(BydVoiceSettingApplication.getContext().getString(R.string.text_dialog_download_fail_tip), this.failLangeList.get(0)));
            }
            return this;
        }

        public Builder addData(String str) {
            if (!this.sb.toString().contains(str)) {
                this.sb.append(LanguageUtil.convertAll(this.contentView.getContext(), str));
                this.sb.append("、");
            }
            setContent(this.sb.toString());
            return this;
        }

        private void redownload() {
            List<DownloadTask> list = this.taskList;
            if (list != null) {
                for (DownloadTask downloadTask : list) {
                    if (NetUtils.isNetAvailable(this.dialog.getContext())) {
                        AidlServiceManager.getInstance().downLoadPkg(downloadTask.getLanguageUpdate().getLanguage());
                    } else {
                        OtaDataManager.showToast(this.dialog.getContext().getResources().getString(R.string.text_4g_not_connect));
                    }
                }
            }
        }
    }
}
