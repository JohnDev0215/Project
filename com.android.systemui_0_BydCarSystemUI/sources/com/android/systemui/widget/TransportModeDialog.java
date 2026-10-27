package com.android.systemui.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.autodevices.DeviceManager;
import com.android.systemui.base.BaseDialog3;
import com.android.systemui.statusbar.policy.CarController;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TransportModeDialog extends BaseDialog3 implements DialogInterface.OnDismissListener, View.OnClickListener {
    private static final int AUTO_POWER_OFF_DURATION = 8;
    private static final int DELAY_TIME = 1000;
    private static final String TAG = "TransportModeDialog";
    private static final int TRANSPORT_MODE_ON_POWER_DOWN_DURATION = 10;
    private int countDownNum;
    private LinearLayout ll_open_transport_mode;
    CarController mCarController;
    private DialogHandler mHandler;
    private int mPowerOffNode;
    private SeekBar mSeekBar;
    private List<ImageView> mSeekBarScales;
    private final SeekBar.OnSeekBarChangeListener seekBarChangeListener;
    private TextView tvCntProgress;
    private TextView tvOpenTransportConfirmBtn;
    private TextView tvTitle;

    @Override // com.android.systemui.base.BaseDialog3
    protected int getLayoutId() {
        return R.layout.transport_mode_dialog_layout;
    }

    @Override // com.android.systemui.base.BaseDialog3
    protected void initData() {
    }

    @Override // com.android.systemui.base.BaseDialog3
    protected void initSize() {
    }

    public TransportModeDialog(Context context) {
        super(context, R.style.commonDialog);
        this.countDownNum = 10;
        this.seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.android.systemui.widget.TransportModeDialog.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                BydLogHelper.logD("onProgressChanged", "progress : " + progress + " , fromUser : " + fromUser);
                TransportModeDialog.this.tvCntProgress.setText(String.valueOf(progress));
                TransportModeDialog.this.updateSeekBarScales(seekBar.getProgress());
                TransportModeDialog.this.resetAutoPowerOff();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                BydLogHelper.logD("onStartTrackingTouch progress : " + seekBar.getProgress());
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                BydLogHelper.logD("onStopTrackingTouch progress : " + seekBar.getProgress());
            }
        };
        BydLogHelper.logD("new TransportModeDialog");
        this.mCarController = (CarController) Dependency.get(CarController.class);
        this.mPowerOffNode = DeviceManager.getInstance().getPowerOffNode();
        BydLogHelper.logD("new TransportModeDialog mPowerOffNode : " + this.mPowerOffNode);
        this.mHandler = new DialogHandler(this);
        setCanceledOnTouchOutside(true);
        initView();
        initDialogType();
        initListener();
    }

    private void initListener() {
        this.tvOpenTransportConfirmBtn.setOnClickListener(this);
        this.mSeekBar.setOnSeekBarChangeListener(this.seekBarChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetAutoPowerOff() {
        this.countDownNum = 8;
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessage(1);
    }

    private void initDialogType() {
        BydLogHelper.logD("initDialogType : ");
        this.ll_open_transport_mode.setVisibility(0);
        BydLogHelper.logW(TAG, "initDialogType mPowerOffNode : " + this.mPowerOffNode);
        this.tvCntProgress.setText(String.valueOf(this.mPowerOffNode));
        this.mSeekBar.setProgress(this.mPowerOffNode);
        updateSeekBarScales(this.mPowerOffNode);
        resetAutoPowerOff();
    }

    private void initView() {
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.ll_open_transport_mode = (LinearLayout) findViewById(R.id.ll_open_transport_mode);
        ImageView imageView = (ImageView) findViewById(R.id.ivSeekBarScale_1);
        ImageView imageView2 = (ImageView) findViewById(R.id.ivSeekBarScale_2);
        ImageView imageView3 = (ImageView) findViewById(R.id.ivSeekBarScale_3);
        ImageView imageView4 = (ImageView) findViewById(R.id.ivSeekBarScale_4);
        ImageView imageView5 = (ImageView) findViewById(R.id.ivSeekBarScale_5);
        ArrayList arrayList = new ArrayList();
        this.mSeekBarScales = arrayList;
        arrayList.add(imageView);
        this.mSeekBarScales.add(imageView2);
        this.mSeekBarScales.add(imageView3);
        this.mSeekBarScales.add(imageView4);
        this.mSeekBarScales.add(imageView5);
        this.tvOpenTransportConfirmBtn = (TextView) findViewById(R.id.tvOpenTransportConfirmBtn);
        this.mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        this.tvCntProgress = (TextView) findViewById(R.id.tvCntProgress);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        BydLogHelper.logD("onDismiss");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.tvOpenTransportConfirmBtn) {
            int progress = this.mSeekBar.getProgress();
            this.tvCntProgress.setText(String.valueOf(progress));
            BydLogHelper.logW(TAG, "setPowerOffChargedState progress : " + progress);
            this.mCarController.setPowerOffChargedState(progress);
            this.mPowerOffNode = progress;
            DeviceManager.getInstance().setPowerOffNode(this.mPowerOffNode);
            dismiss();
        }
    }

    private static final class DialogHandler extends Handler {
        public static final int START_COUNT_DOWN_MSG = 1;
        private final TransportModeDialog transportModeDialog;

        public DialogHandler(TransportModeDialog dialog) {
            this.transportModeDialog = (TransportModeDialog) new WeakReference(dialog).get();
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BydLogHelper.logD("handleMessage", "msg.what = " + msg.what);
            if (this.transportModeDialog != null && msg.what == 1) {
                this.transportModeDialog.updateCountDownNum();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCountDownNum() {
        if (this.countDownNum > 0) {
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, 1000L);
            this.tvOpenTransportConfirmBtn.setText(this.mContext.getString(R.string.confirm) + " (" + this.countDownNum + "s)");
        } else {
            BydLogHelper.logW(TAG, "updateCountDownNum, count down over");
            if (this.mSeekBar != null) {
                BydLogHelper.logW(TAG, "updateCountDownNum setPowerOffChargedState : " + this.mSeekBar.getProgress());
                this.mCarController.setPowerOffChargedState(this.mSeekBar.getProgress());
            }
            dismiss();
        }
        this.countDownNum--;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSeekBarScales(int progress) {
        int i = (progress < 30 || progress >= 40) ? (progress < 40 || progress >= 50) ? (progress < 50 || progress >= 60) ? (progress < 60 || progress >= 70) ? progress == 70 ? 5 : 0 : 4 : 3 : 2 : 1;
        for (int i2 = 0; i2 < this.mSeekBarScales.size(); i2++) {
            if (i2 < i) {
                this.mSeekBarScales.get(i2).setBackground(this.mContext.getResources().getDrawable(R.color.seek_bar_background_progress_scale_bright_color, null));
            } else {
                this.mSeekBarScales.get(i2).setBackground(this.mContext.getResources().getDrawable(R.color.seek_bar_background_progress_scale_dark_color, null));
            }
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        BydLogHelper.logD("dismiss");
        DialogHandler dialogHandler = this.mHandler;
        if (dialogHandler != null) {
            dialogHandler.removeMessages(1);
            this.mHandler = null;
        }
    }
}
