package com.byd.fragment;

import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.internal.view.SupportMenu;
import b.a.d;
import com.byd.auto_photo.R;
import com.byd.auto_photo.a;
import com.byd.util.e;
import com.byd.util.k;
import com.byd.util.w;
import com.byd.util.x;
import java.io.File;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseRenameFileFragment extends BaseDialogFragment {
    private static final int MAX_LEN = 72;
    private static final int MSG_SHOW_SOFT_INPUT = 1;
    private static final int SHOW_SOFT_INPUT_DELAY = 300;
    private boolean closeByUser;
    private int dialogHeight;
    protected EditText etName;
    private File file;
    private InputMethodManager imm;
    private boolean inputShowOnPause;
    private boolean isStart;
    protected String lastInputStr;
    private MHandler mHandler;
    private String nameLenTipStr;
    private OnRenameListener onRenameListener;
    private int softShowDelay;
    protected TextView tvConflictTips;
    protected TextView tvNameLenTips;
    private int type;
    protected View viewClearInput;
    private int maxLen = 72;
    protected int softInputMode = 16;
    private TextWatcher textWatcher = new TextWatcher() { // from class: com.byd.fragment.BaseRenameFileFragment.1
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (!BaseRenameFileFragment.this.etName.isFocused()) {
                BaseRenameFileFragment.this.etName.requestFocus();
            }
            if (BaseRenameFileFragment.this.tvNameLenTips.getVisibility() != 0) {
                BaseRenameFileFragment.this.tvConflictTips.setVisibility(8);
                BaseRenameFileFragment.this.tvNameLenTips.setVisibility(0);
            }
            int length = editable.length();
            BaseRenameFileFragment.this.lastInputStr = editable.toString();
            if (length > 0) {
                View view = BaseRenameFileFragment.this.viewClearInput;
                if (view != null && view.getVisibility() != 0) {
                    BaseRenameFileFragment.this.viewClearInput.setVisibility(0);
                }
                BaseRenameFileFragment.this.checkTextForBtn(editable.toString(), length, true);
                return;
            }
            View view2 = BaseRenameFileFragment.this.viewClearInput;
            if (view2 != null && view2.getVisibility() == 0) {
                BaseRenameFileFragment.this.viewClearInput.setVisibility(8);
            }
            BaseRenameFileFragment.this.setSureButtonEnable(false);
            BaseRenameFileFragment.this.onOptInputTips(false);
            if (a.f1178a.intValue() >= 50) {
                BaseRenameFileFragment.this.tvNameLenTips.setVisibility(8);
                return;
            }
            BaseRenameFileFragment baseRenameFileFragment = BaseRenameFileFragment.this;
            baseRenameFileFragment.tvNameLenTips.setTextColor(baseRenameFileFragment.getActivity().getResources().getColor(R.color.text_hint_color_night));
            BaseRenameFileFragment baseRenameFileFragment2 = BaseRenameFileFragment.this;
            baseRenameFileFragment2.tvNameLenTips.setText(baseRenameFileFragment2.nameLenTipStr);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    };

    private static class MHandler extends d<BaseRenameFileFragment> {
        public MHandler(BaseRenameFileFragment baseRenameFileFragment) {
            super(baseRenameFileFragment);
        }

        @Override // b.a.d
        public void handleMessage(BaseRenameFileFragment baseRenameFileFragment, Message message) {
            if (message.what != 1) {
                return;
            }
            baseRenameFileFragment.showSoftInput();
        }
    }

    public interface OnRenameListener {
        void onCancel();

        void onRenameOk(File file, File file2);
    }

    private void checkTipsViewShow() {
        if (this.tvNameLenTips.getVisibility() != 0) {
            this.tvNameLenTips.setVisibility(0);
        }
    }

    private int getDelayTime() {
        int i = this.softShowDelay;
        if (i != 0) {
            return i;
        }
        return 300;
    }

    private void setDefText() {
        int length;
        String name = this.file.getName();
        String str = this.lastInputStr;
        if (str != null) {
            this.etName.setText(str);
            length = str.length();
        } else {
            int iLastIndexOf = name.lastIndexOf(".");
            if (iLastIndexOf != -1) {
                name = name.substring(0, iLastIndexOf);
            } else {
                iLastIndexOf = name.length();
            }
            this.etName.setText(name);
            int i = iLastIndexOf;
            str = name;
            length = i;
        }
        this.maxLen = 72;
        if (length > 72) {
            this.maxLen = length;
        }
        Integer num = a.f1178a;
        if (num.intValue() < 50) {
            this.tvNameLenTips.setVisibility(0);
            String str2 = String.format(getResources().getString(R.string.name_len_tips), Integer.valueOf(this.maxLen));
            this.nameLenTipStr = str2;
            this.tvNameLenTips.setText(str2);
        } else {
            this.tvNameLenTips.setVisibility(8);
        }
        this.etName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.maxLen)});
        if (length > 0 && length <= this.etName.getText().length()) {
            if (TextUtils.isEmpty(this.lastInputStr)) {
                this.etName.setSelection(0, length);
            } else {
                this.etName.setSelection(length);
            }
        }
        if (!TextUtils.isEmpty(str)) {
            checkTextForBtn(str, length, false);
        }
        if (num.intValue() != 2) {
            this.etName.addTextChangedListener(this.textWatcher);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSoftInput() {
        if (x.l(getActivity()) || !this.isStart) {
            w.c("RenameFile -> soft input already shown or page no start : " + this.isStart);
            return;
        }
        if (!this.etName.isFocused()) {
            this.etName.requestFocus();
        }
        boolean zShowSoftInput = this.imm.showSoftInput(this.etName, 0);
        w.c("RenameFile showSoftInput : " + zShowSoftInput + " " + this.isStart);
        if (zShowSoftInput || !this.isStart) {
            return;
        }
        this.mHandler.sendEmptyMessageDelayed(1, 200L);
    }

    protected void checkTextForBtn(String str, int i, boolean z) {
        boolean zO = x.o(str);
        int i2 = SupportMenu.CATEGORY_MASK;
        if (zO) {
            setSureButtonEnable(false);
            checkTipsViewShow();
            this.tvNameLenTips.setTextColor(SupportMenu.CATEGORY_MASK);
            this.tvNameLenTips.setText(String.format(Locale.US, getResources().getString(R.string.special_char_tips), "#%?:*|/\\<>"));
            onOptInputTips(true);
            return;
        }
        if (i >= this.maxLen) {
            checkTipsViewShow();
            TextView textView = this.tvNameLenTips;
            if (a.f1178a.intValue() < 50) {
                i2 = -15616;
            }
            textView.setTextColor(i2);
            this.tvNameLenTips.setText(R.string.name_len_reach_max);
            onOptInputTips(true);
        } else {
            if (a.f1178a.intValue() < 50) {
                checkTipsViewShow();
                this.tvNameLenTips.setTextColor(getActivity().getResources().getColor(R.color.text_hint_color_night));
                this.tvNameLenTips.setText(this.nameLenTipStr);
            } else {
                this.tvNameLenTips.setVisibility(8);
            }
            onOptInputTips(false);
        }
        if (z) {
            setSureButtonEnable(true);
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        if (!this.closeByUser) {
            hideSoftInput();
        }
        super.dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismissAllowingStateLoss() {
        if (!this.closeByUser) {
            hideSoftInput();
        }
        super.dismissAllowingStateLoss();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getLayout() {
        return R.layout.fragment_rename_file;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected String getTitle() {
        return getString(R.string.rename);
    }

    protected void hideSoftInput() {
        MHandler mHandler = this.mHandler;
        if (mHandler != null) {
            mHandler.removeMessages(1);
        }
        EditText editText = this.etName;
        if (editText == null || this.mHandler == null) {
            return;
        }
        w.c("RenameFileDialog hide soft input : " + this.imm.hideSoftInputFromWindow(editText.getWindowToken(), 0));
    }

    public void init(String str, int i) {
        EditText editText;
        this.file = new File(str);
        this.type = i;
        if (isVisible() && (editText = this.etName) != null && TextUtils.isEmpty(editText.getText())) {
            setDefText();
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void initUiData() {
        super.initUiData();
        this.tvConflictTips.setVisibility(8);
        if (this.file != null) {
            setDefText();
        }
        w.c("RenameFileFragment init data");
        showSoftInputDelay();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void onCancelClick() {
        super.onCancelClick();
        this.closeByUser = true;
        hideSoftInput();
        OnRenameListener onRenameListener = this.onRenameListener;
        if (onRenameListener != null) {
            onRenameListener.onCancel();
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        this.mHandler = new MHandler(this);
        super.onCreate(bundle);
        this.imm = (InputMethodManager) getActivity().getSystemService("input_method");
        this.dialogHeight = getResources().getDimensionPixelSize(R.dimen.new_or_rename_dialog_height);
        k.c(getActivity()).g("04");
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.inputShowOnPause = false;
        MHandler mHandler = this.mHandler;
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            this.mHandler.release();
            this.mHandler = null;
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.lastInputStr = null;
        super.onDestroyView();
        w.c("Rename onDestroyView");
    }

    protected void onEditTextFocusChange(View view, boolean z) {
        w.c("RenameFile edit text onFocusChange : " + z);
        if (!z) {
            this.etName.requestFocus();
        }
        if (TextUtils.isEmpty(this.lastInputStr)) {
            EditText editText = this.etName;
            editText.setSelection(0, editText.getText().length());
        }
    }

    protected void onOptInputTips(boolean z) {
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment
    public void onPause() {
        this.isStart = false;
        super.onPause();
        this.mHandler.removeMessages(1);
        this.inputShowOnPause = false;
        if (this.closeByUser) {
            return;
        }
        this.inputShowOnPause = x.l(getActivity());
        w.c("Rename onPause inputShowOnPause : " + this.inputShowOnPause);
        if (this.inputShowOnPause) {
            hideSoftInput();
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment
    public void onResume() {
        this.isStart = true;
        super.onResume();
        if (!this.inputShowOnPause || this.mHandler.hasMessages(1)) {
            return;
        }
        w.c("RenameFileFragment onResume -> show");
        this.mHandler.sendEmptyMessageDelayed(1, getDelayTime());
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setLayout(getResources().getDimensionPixelSize(R.dimen.dialog_min_width), this.dialogHeight);
        window.setSoftInputMode(this.softInputMode);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void onSureClick() {
        String strTrim = this.etName.getText().toString().trim();
        w.c("=====onSureClick=====" + strTrim);
        if (TextUtils.isEmpty(strTrim)) {
            w.e(getActivity(), R.string.name_empty);
            return;
        }
        if (x.o(strTrim)) {
            w.e(getActivity(), R.string.name_illegal_by_special);
            return;
        }
        if (strTrim.startsWith(".")) {
            w.e(getActivity(), R.string.name_can_not_start_with_dot);
            return;
        }
        String name = this.file.getName();
        int iLastIndexOf = name.lastIndexOf(".");
        String str = this.file.getParent() + File.separator + strTrim + (iLastIndexOf != -1 ? name.substring(iLastIndexOf) : "");
        File file = new File(str);
        if (file.exists()) {
            this.tvConflictTips.setVisibility(0);
            this.tvNameLenTips.setVisibility(8);
            setSureButtonEnable(false);
            onOptInputTips(true);
            return;
        }
        setCancelable(false);
        boolean zRenameTo = this.file.renameTo(file);
        w.c(this.file.getPath() + " rename to " + file.getPath() + " rename result : " + zRenameTo);
        if (!zRenameTo) {
            w.c("rename error");
            w.e(getActivity(), R.string.rename_fail);
            setCancelable(true);
        } else {
            this.closeByUser = true;
            hideSoftInput();
            if (this.onRenameListener != null) {
                e.a(getActivity(), str, 2);
                this.onRenameListener.onRenameOk(this.file, file);
            }
            super.onSureClick();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (TextUtils.isEmpty(this.lastInputStr)) {
            setSureButtonEnable(false);
        }
        this.closeByUser = false;
        EditText editText = (EditText) view.findViewById(R.id.et_name);
        this.etName = editText;
        editText.setHint(R.string.file_name_hint);
        x.w(getActivity(), 16, this.etName);
        setClearViewShowPos();
        this.tvConflictTips = (TextView) view.findViewById(R.id.tv_conflict_tips);
        this.etName.setSelectAllOnFocus(true);
        this.etName.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.fragment.BaseRenameFileFragment.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view2, boolean z) {
                BaseRenameFileFragment.this.onEditTextFocusChange(view2, z);
            }
        });
        this.tvNameLenTips = (TextView) view.findViewById(R.id.tv_name_len_tips);
        if (this.file == null) {
            dismissAllowingStateLoss();
            w.c("RenameFileDialog dismiss by no file");
        } else if (a.f1178a.intValue() != 2) {
            View viewFindViewById = view.findViewById(R.id.iv_clear_input);
            this.viewClearInput = viewFindViewById;
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.byd.fragment.BaseRenameFileFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    BaseRenameFileFragment.this.etName.setText("");
                    if (BaseRenameFileFragment.this.tvNameLenTips.getVisibility() != 0) {
                        BaseRenameFileFragment.this.tvConflictTips.setVisibility(8);
                        if (a.f1178a.intValue() < 50) {
                            BaseRenameFileFragment.this.tvNameLenTips.setVisibility(0);
                        }
                    }
                }
            });
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void reLayout() {
        hideSoftInput();
        if (a.f1178a.intValue() != 2) {
            this.etName.removeTextChangedListener(this.textWatcher);
        }
        super.reLayout();
    }

    protected void setClearViewShowPos() {
        View view = this.viewClearInput;
        if (view == null) {
            return;
        }
        ((FrameLayout.LayoutParams) view.getLayoutParams()).gravity = getResources().getConfiguration().getLayoutDirection() == 1 ? 3 : 5;
    }

    public void setOnRenameListener(OnRenameListener onRenameListener) {
        this.onRenameListener = onRenameListener;
    }

    public void setSoftInputMode(int i) {
        this.softInputMode = i;
    }

    public void setSoftShowDelay(int i) {
        this.softShowDelay = i;
    }

    protected void setSureButtonEnable(boolean z) {
        TextView textView = this.btnSure;
        if (textView == null) {
            return;
        }
        textView.setEnabled(z);
    }

    protected void showSoftInputDelay() {
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, getDelayTime());
    }
}
