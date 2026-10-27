package com.byd.profile.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.profile.Constants;
import com.byd.profile.R;
import com.byd.profile.utils.SharedPreferencesUtil;
import com.byd.profile.view.DefaultItem;
import com.byd.profile.view.GridItemDecoration;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class NapEditDialog extends BaseFullscreenDialog implements View.OnClickListener {
    private boolean isShowMedia;
    private Button mBtnOk;
    private CheckBox mMediaCheck;
    private SeekBar sbVolume;
    private TextView tvMedia;
    private TextView tvVolume;
    private int volume;

    @Override // com.byd.profile.dialog.BaseFullscreenDialog
    protected void onViewAttach() {
    }

    @Override // com.byd.profile.dialog.BaseFullscreenDialog
    protected void onViewDetach() {
    }

    @Override // com.byd.profile.dialog.FloatingWindow
    protected void setWidthAndHeight(int[] size) {
    }

    public NapEditDialog(Context context) {
        super(context);
    }

    @Override // com.byd.profile.dialog.BaseFullscreenDialog
    protected void initContent(View view) {
        view.findViewById(R.id.btn_reset).setOnClickListener(this);
        view.findViewById(R.id.btn_ok).setOnClickListener(this);
        view.findViewById(R.id.btn_cancel).setOnClickListener(this);
        this.sbVolume = (SeekBar) view.findViewById(R.id.sb_volume);
        this.tvVolume = (TextView) view.findViewById(R.id.tv_volume);
        this.tvMedia = (TextView) view.findViewById(R.id.tv_media);
        this.mBtnOk = (Button) view.findViewById(R.id.btn_ok);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_media);
        this.mMediaCheck = checkBox;
        checkBox.setChecked(this.isShowMedia);
        this.mMediaCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.byd.profile.dialog.NapEditDialog.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NapEditDialog.this.setMediaEnable(isChecked);
                NapEditDialog.this.mBtnOk.setEnabled(true);
            }
        });
        setMediaEnable(this.isShowMedia);
        this.tvVolume.setText(String.valueOf(this.volume));
        this.sbVolume.setProgress(this.volume);
        this.sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.byd.profile.dialog.NapEditDialog.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                NapEditDialog.this.mBtnOk.setEnabled(true);
                NapEditDialog.this.volume = progress;
                NapEditDialog.this.tvVolume.setText(String.valueOf(NapEditDialog.this.volume));
            }
        });
        this.mBtnOk.setEnabled(false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        recyclerView.addItemDecoration(new GridItemDecoration.Builder(this.mContext).horSize(this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_16)).verSize(this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_24)).showHeadDivider(false).showLastDivider(false).isExistHead(false).build());
        ArrayList arrayList = new ArrayList();
        initExecuteItem(arrayList);
        recyclerView.setAdapter(new ExecuteAdapter(arrayList));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.btn_cancel) {
            dismiss();
            return;
        }
        if (v.getId() == R.id.btn_ok) {
            execute();
            dismiss();
        } else if (v.getId() == R.id.btn_reset) {
            reset();
        }
    }

    @Override // com.byd.profile.dialog.BaseFullscreenDialog
    protected void initData() {
        this.isShowMedia = SharedPreferencesUtil.getBoolean(this.mContext, Constants.SP_KEY_MEDIA_REST, true);
        this.volume = SharedPreferencesUtil.getInt(this.mContext, Constants.SP_KEY_MEDIA_VOLUME_REST, 5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMediaEnable(boolean isEnable) {
        this.tvMedia.setEnabled(isEnable);
        this.tvVolume.setEnabled(isEnable);
        this.sbVolume.setEnabled(isEnable);
    }

    protected void execute() {
        SharedPreferencesUtil.putInt(this.mContext, Constants.SP_KEY_MEDIA_VOLUME_REST, this.volume);
        SharedPreferencesUtil.putBoolean(this.mContext, Constants.SP_KEY_MEDIA_REST, this.mMediaCheck.isChecked());
    }

    protected void reset() {
        SharedPreferencesUtil.putInt(this.mContext, Constants.SP_KEY_MEDIA_VOLUME_REST, 5);
        this.volume = 5;
        this.mMediaCheck.setChecked(this.isShowMedia);
        this.tvVolume.setText(String.valueOf(this.volume));
        this.sbVolume.setProgress(this.volume);
    }

    @Override // com.byd.profile.dialog.BaseFullscreenDialog
    protected int initLayoutRes() {
        return R.layout.dialog_nap_edit;
    }

    @Override // com.byd.profile.dialog.BaseFullscreenDialog
    protected int initTitle() {
        return R.string.dialog_title_rest;
    }

    protected void initExecuteItem(List<DefaultItem> list) {
        list.add(new DefaultItem(R.drawable.icon_control_screen_off_48, R.string.screen_off));
        list.add(new DefaultItem(R.drawable.icon_control_turn_off_daytime_runnings_48, R.string.daytime_light_off));
        list.add(new DefaultItem(R.drawable.icon_control_atmosphere_48, R.string.ambient_light_off));
        list.add(new DefaultItem(R.drawable.icon_control_auto_seat_adjustment_48, R.string.seat_automatically));
        list.add(new DefaultItem(R.drawable.icon_control_four_door_locks_48, R.string.all_doors_locked_automatically));
        list.add(new DefaultItem(R.drawable.icon_control_air_48, R.string.start_air));
    }

    private static class ExecuteAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<DefaultItem> mList;

        public ExecuteAdapter(List<DefaultItem> list) {
            this.mList = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_execute, (ViewGroup) null));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder holder, int position) {
            DefaultItem defaultItem = this.mList.get(position);
            holder.ivIcon.setImageResource(defaultItem.getIconRes());
            holder.tvName.setText(defaultItem.getTextRes());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mList.size();
        }

        private static class ViewHolder extends RecyclerView.ViewHolder {
            private final ImageView ivIcon;
            private final TextView tvName;

            public ViewHolder(View itemView) {
                super(itemView);
                this.ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
                this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
            }
        }
    }
}
