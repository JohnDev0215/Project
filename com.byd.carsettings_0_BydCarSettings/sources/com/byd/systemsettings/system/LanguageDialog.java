package com.byd.systemsettings.system;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.m;
import b.b.g.p.d;
import b.b.g.p.m.a;
import b.b.m.f.c.a.e;
import com.byd.base.view.dialog.BaseDialogFragment;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.LanguageListAdapter;
import com.byd.carsettings.system.model.BydLanguage;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LanguageDialog extends BaseDialogFragment implements View.OnClickListener {
    public static final String BUNDLE_DEF_ALL_LIST = "bundle_def_all_list";
    public static final String BUNDLE_DEF_INDEX = "bundle_def_index";
    public static final String TAG = "LanguageDialog";
    public Button btnCancel;
    public Button btnOk;
    public int checkedIndex = 0;
    private int curLangIndex = 0;
    private boolean getAll;
    public View ivBack;
    private List<BydLanguage> languageList;
    public LinearLayout llDialog;
    public ViewGroup llMasks;
    private LanguageListAdapter mAdapter;
    private OnDismissListener mDismissListener;
    private ViewGroup opertionBottom;
    public RecyclerView rcv;
    public TextView tvTitle;

    public interface OnDismissListener {
        void onDismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void chechItem(int i) {
        this.checkedIndex = i;
        int i2 = 0;
        while (i2 < this.languageList.size()) {
            this.languageList.get(i2).setChecked(i2 == i);
            i2++;
        }
        LanguageListAdapter languageListAdapter = this.mAdapter;
        if (languageListAdapter != null) {
            languageListAdapter.notifyDataSetChanged();
        }
    }

    private void initData(Bundle bundle) {
        int i = bundle.getInt("bundle_def_index");
        boolean z = bundle.getBoolean(BUNDLE_DEF_ALL_LIST);
        this.getAll = z;
        if (z) {
            this.languageList = a.b();
        } else {
            this.languageList = a.e();
        }
        if (i >= 0 && i < this.languageList.size()) {
            chechItem(i);
            this.curLangIndex = i;
        }
        b.b.m.c.a.d(TAG, "---initData---defIndex: " + i);
        setRecyclerViewHeight();
    }

    public static LanguageDialog newLanguageDialog(int i) {
        LanguageDialog languageDialog = new LanguageDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("bundle_def_index", i);
        languageDialog.setArguments(bundle);
        return languageDialog;
    }

    private void setRecyclerViewHeight() {
        b.b.m.c.a.a(TAG, "setRecyclerViewHeight", ", rcv = " + this.rcv);
        Context context = getContext();
        if (context == null || this.rcv == null || this.languageList.isEmpty()) {
            return;
        }
        int size = this.languageList.size();
        b.b.m.c.a.a(TAG, "setRecyclerViewHeight", ", languageSize = " + size);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.rcv.getLayoutParams();
        layoutParams.width = -1;
        int dimension = (int) context.getResources().getDimension(R.dimen.dp_60);
        layoutParams.height = size >= 6 ? dimension * 6 : dimension * size;
        this.rcv.setLayoutParams(layoutParams);
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment
    public int getBaseDialogCommonAnim() {
        return R.style.base_byd_dialog_common_anim_null;
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment
    public int getLayoutResource() {
        return R.layout.dialog_language_selector;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnDismissListener onDismissListener = this.mDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
        dismiss();
        if (view.getId() == R.id.btn_ok_dialog_bottom) {
            Settings.Global.putString(BydApp.getContext().getContentResolver(), "persist.sys.language.switch", this.getAll ? "true" : "false");
            d.g.a().D0(this.languageList.get(this.checkedIndex));
        }
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment, com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        b.b.m.c.a.a(TAG, "----onCreate-------");
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        initData(arguments);
        if (bundle != null) {
            int i = bundle.getInt("bundle_def_index");
            this.checkedIndex = i;
            chechItem(i);
        }
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mDismissListener = null;
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment
    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        OnDismissListener onDismissListener;
        b.b.m.c.a.a(TAG, "---onKeyEvent---keyCode= " + i + ", Action: " + keyEvent.getAction());
        boolean zOnKeyEvent = super.onKeyEvent(i, keyEvent);
        if (i == 4 && (onDismissListener = this.mDismissListener) != null) {
            onDismissListener.onDismiss();
        }
        return zOnKeyEvent;
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment
    public void onLtrRudderR(boolean z) {
        setViewGroupRtl(this.opertionBottom, true);
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment
    public void onRtlRudderL(boolean z) {
        setViewGroupRtl(this.opertionBottom, false);
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("bundle_def_index", this.checkedIndex);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        b.b.m.c.a.a(TAG, "----onViewCreated-------checkedIndex: " + this.checkedIndex);
        this.opertionBottom = (ViewGroup) view.findViewById(R.id.ll_opertion_dialog_bottom);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.ll_dialog_masks);
        this.llMasks = viewGroup;
        viewGroup.setOnClickListener(this);
        this.llDialog = (LinearLayout) view.findViewById(R.id.ll_bg_dialo_sound_focus);
        TextView textView = (TextView) view.findViewById(R.id.tv_title_dialog_common_titles);
        this.tvTitle = textView;
        textView.setText(getString(R.string.language_system));
        View viewFindViewById = view.findViewById(R.id.iv_back_dialog_common_title);
        this.ivBack = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        this.ivBack.setVisibility(8);
        Button button = (Button) view.findViewById(R.id.btn_ok_dialog_bottom);
        this.btnOk = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.btn_cancel_dialog_bottom);
        this.btnCancel = button2;
        button2.setOnClickListener(this);
        this.rcv = (RecyclerView) view.findViewById(R.id.rcv_dialog_language_selector);
        this.rcv.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        m.b(this.rcv);
        this.rcv.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.byd.systemsettings.system.LanguageDialog.1
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(@NonNull Rect rect, @NonNull View view2, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
                super.getItemOffsets(rect, view2, recyclerView, state);
                rect.top = (int) LanguageDialog.this.getResources().getDimension(R.dimen.dp_12);
                rect.bottom = (int) LanguageDialog.this.getResources().getDimension(R.dimen.dp_12);
            }
        });
        LanguageListAdapter languageListAdapter = new LanguageListAdapter(this.languageList);
        this.mAdapter = languageListAdapter;
        this.rcv.setAdapter(languageListAdapter);
        this.mAdapter.setOnItemClickedListener(new e() { // from class: com.byd.systemsettings.system.LanguageDialog.2
            @Override // b.b.m.f.c.a.e
            public void onItemClicked(int i) {
                b.b.m.c.a.a(LanguageDialog.TAG, "---onItemClicked---position: " + i);
                LanguageDialog.this.chechItem(i);
            }
        });
        int i = this.curLangIndex;
        if (i > 2) {
            this.rcv.scrollToPosition(i - 2);
        }
        setAnim(this.llMasks, this.llDialog);
        setRecyclerViewHeight();
        if (this.windowTitle == null) {
            setWindowTitle(getContext(), getClass().getName());
        }
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.mDismissListener = onDismissListener;
    }

    public static LanguageDialog newLanguageDialog(int i, boolean z) {
        LanguageDialog languageDialog = new LanguageDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("bundle_def_index", i);
        bundle.putBoolean(BUNDLE_DEF_ALL_LIST, z);
        languageDialog.setArguments(bundle);
        return languageDialog;
    }
}
