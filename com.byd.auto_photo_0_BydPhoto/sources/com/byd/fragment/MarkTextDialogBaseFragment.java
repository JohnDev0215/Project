package com.byd.fragment;

import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.a.d;
import b.a.e.p;
import com.byd.auto_photo.R;
import com.byd.drawable.TextDressDrawable;
import com.byd.util.w;
import com.byd.util.x;
import com.byd.view.ListItemDecoration;

/* JADX INFO: loaded from: classes2.dex */
public class MarkTextDialogBaseFragment extends BaseDialogFragment {
    private static final int[] COLORS = {-1, ViewCompat.MEASURED_STATE_MASK, SupportMenu.CATEGORY_MASK, InputDeviceCompat.SOURCE_ANY, -16711936, -16776961, -8060686};
    private static final int MSG_SHOW_INPUT = 1;
    private ImageView btnTextEditCancel;
    private ImageView btnTextEditSure;
    private p colorAdapter;
    private int colorIndex;
    private TextDressDrawable editDrawable;
    private EditText etTextDressWord;
    private InputMethodManager imm;
    private String lastInput;
    private MHandler mHandler;
    private OnTextInputListener onTextInputListener;
    private RecyclerView recyclerPaintColor;
    protected int maxLen = 200;
    private int lastColorIndex = -1;
    private final Point currentScreenSize = new Point();
    private TextWatcher textWatcher = new TextWatcher() { // from class: com.byd.fragment.MarkTextDialogBaseFragment.3
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            int length = editable.length();
            MarkTextDialogBaseFragment markTextDialogBaseFragment = MarkTextDialogBaseFragment.this;
            if (length >= markTextDialogBaseFragment.maxLen) {
                w.e(markTextDialogBaseFragment.getActivity(), R.string.name_len_reach_max);
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    };
    private View.OnClickListener onTextWindowClick = new View.OnClickListener() { // from class: com.byd.fragment.MarkTextDialogBaseFragment.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MarkTextDialogBaseFragment.this.hideInputMethod();
            switch (view.getId()) {
                case R.id.btn_text_edit_cancel /* 2131230823 */:
                    MarkTextDialogBaseFragment.this.etTextDressWord.setText(" ");
                    MarkTextDialogBaseFragment.this.dismissAllowingStateLoss();
                    break;
                case R.id.btn_text_edit_sure /* 2131230824 */:
                    MarkTextDialogBaseFragment.this.inputFinish();
                    break;
            }
        }
    };

    private static class MHandler extends d<MarkTextDialogBaseFragment> {
        public MHandler(MarkTextDialogBaseFragment markTextDialogBaseFragment) {
            super(markTextDialogBaseFragment);
        }

        @Override // b.a.d
        public void handleMessage(MarkTextDialogBaseFragment markTextDialogBaseFragment, Message message) {
            markTextDialogBaseFragment.showSoftInput();
        }
    }

    public interface OnTextInputListener {
        void onInputFinish(String str, int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void configEditViewMargin() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.etTextDressWord.getLayoutParams();
        marginLayoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.watermark_edittext_top_margin);
        w.c("configEditViewMargin : " + marginLayoutParams.topMargin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideInputMethod() {
        this.mHandler.removeMessages(1);
        EditText editText = this.etTextDressWord;
        if (editText == null) {
            return;
        }
        this.imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inputFinish() {
        TextDressDrawable textDressDrawable;
        String string = this.etTextDressWord.getText().toString();
        if (TextUtils.isEmpty(string)) {
            w.e(getActivity(), R.string.content_empty);
            return;
        }
        if (string.equals(this.editDrawable.getText()) && ((textDressDrawable = this.editDrawable) == null || textDressDrawable.getTextColor() == COLORS[this.colorIndex])) {
            w.c("text not change");
            this.etTextDressWord.setText(" ");
            dismissAllowingStateLoss();
        } else {
            OnTextInputListener onTextInputListener = this.onTextInputListener;
            if (onTextInputListener != null) {
                onTextInputListener.onInputFinish(string.trim(), COLORS[this.colorIndex]);
            }
            this.etTextDressWord.setText(" ");
            dismissAllowingStateLoss();
        }
    }

    private int searchColorIndex(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = COLORS;
            if (i2 >= iArr.length) {
                return 0;
            }
            if (i == iArr[i2]) {
                return i2;
            }
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSoftInput() {
        if (x.l(getActivity())) {
            w.c("MarkText -> soft input already shown");
            return;
        }
        w.c("MarkText toggle soft input");
        this.imm.toggleSoftInput(0, 2);
        EditText editText = this.etTextDressWord;
        if (editText == null || editText.isFocused()) {
            return;
        }
        this.etTextDressWord.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startShowSoftInput() {
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, 300L);
    }

    protected void configButtonShowByTheme() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        this.btnTextEditSure.setImageDrawable(x.s(activity, R.drawable.icon_edit_sure));
        this.btnTextEditCancel.setImageDrawable(x.s(activity, R.drawable.icon_cancel));
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getButtonConfig() {
        return 0;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getDialogHeight() {
        return -1;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getDialogWidth() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.add_text_window_width);
        if (dimensionPixelSize < 0) {
            return -1;
        }
        return dimensionPixelSize;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getLayout() {
        return R.layout.text_mark_edit;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected String getTitle() {
        return null;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void initUiData() {
        int textColor;
        super.initUiData();
        TextDressDrawable textDressDrawable = this.editDrawable;
        if (textDressDrawable != null) {
            String text = textDressDrawable.getText();
            String str = this.lastInput;
            if (str != null) {
                this.lastInput = null;
                text = str;
            }
            this.etTextDressWord.setText(text);
            this.etTextDressWord.setSelection(0, text.length());
            int i = this.lastColorIndex;
            if (i != -1) {
                this.colorIndex = i;
                textColor = COLORS[i];
            } else {
                textColor = this.editDrawable.getTextColor();
                this.colorIndex = searchColorIndex(textColor);
            }
            this.colorAdapter.d(this.colorIndex);
            this.etTextDressWord.setTextColor(textColor);
            this.etTextDressWord.setAlpha(this.editDrawable.getAlpha() / 255.0f);
        } else {
            int integer = this.lastColorIndex;
            if (integer == -1) {
                integer = getResources().getInteger(R.integer.water_mark_def_color_index);
            }
            this.colorIndex = integer;
            String str2 = this.lastInput;
            if (str2 != null) {
                this.etTextDressWord.setText(str2);
                this.lastInput = null;
            } else {
                this.etTextDressWord.setText("");
            }
            this.etTextDressWord.setSelection(0);
            this.etTextDressWord.setTextColor(COLORS[this.colorIndex]);
            this.colorAdapter.d(this.colorIndex);
        }
        this.lastColorIndex = -1;
        configEditViewMargin();
        startShowSoftInput();
        w.c("MarkText initUiData");
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        w.c("MarkTextDialog onConfigurationChanged");
        Point point = this.currentScreenSize;
        boolean z = (point.x == configuration.screenWidthDp && point.y == configuration.screenHeightDp) ? false : true;
        final boolean zL = x.l(getActivity());
        if (z) {
            hideInputMethod();
        }
        super.onConfigurationChanged(configuration);
        if (z) {
            this.currentScreenSize.set(configuration.screenWidthDp, configuration.screenHeightDp);
            this.mHandler.postDelayed(new Runnable() { // from class: com.byd.fragment.MarkTextDialogBaseFragment.5
                @Override // java.lang.Runnable
                public void run() {
                    MarkTextDialogBaseFragment.this.getDialog().getWindow().setLayout(MarkTextDialogBaseFragment.this.getDialogWidth(), MarkTextDialogBaseFragment.this.getDialogHeight());
                    MarkTextDialogBaseFragment.this.configEditViewMargin();
                    MarkTextDialogBaseFragment.this.etTextDressWord.requestLayout();
                    if (zL) {
                        MarkTextDialogBaseFragment.this.startShowSoftInput();
                    }
                }
            }, 250L);
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.userModel = false;
        this.mHandler = new MHandler(this);
        this.imm = (InputMethodManager) getActivity().getSystemService("input_method");
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.etTextDressWord.removeTextChangedListener(this.textWatcher);
        w.c("==MarkText onDestroyView==");
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        w.c("MarkTextDialog onStart");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.etTextDressWord = (EditText) view.findViewById(R.id.et_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.btn_text_edit_sure);
        this.btnTextEditSure = imageView;
        imageView.setOnClickListener(this.onTextWindowClick);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.btn_text_edit_cancel);
        this.btnTextEditCancel = imageView2;
        imageView2.setOnClickListener(this.onTextWindowClick);
        this.recyclerPaintColor = (RecyclerView) view.findViewById(R.id.recycler_paint_color);
        FragmentActivity activity = getActivity();
        this.recyclerPaintColor.setLayoutManager(new LinearLayoutManager(activity, 0, false));
        this.recyclerPaintColor.addItemDecoration(new ListItemDecoration(getResources().getDimensionPixelSize(R.dimen.dp_20), true));
        if (this.colorAdapter == null) {
            p pVar = new p(activity);
            this.colorAdapter = pVar;
            pVar.e(COLORS);
            this.colorAdapter.h(new p.a() { // from class: com.byd.fragment.MarkTextDialogBaseFragment.1
                @Override // b.a.e.p.a
                public void onStyleItemClick(int i) {
                    MarkTextDialogBaseFragment.this.colorAdapter.d(i);
                    MarkTextDialogBaseFragment.this.etTextDressWord.setTextColor(MarkTextDialogBaseFragment.COLORS[i]);
                    MarkTextDialogBaseFragment.this.colorIndex = i;
                }
            });
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.add_text_color_item_size);
        this.colorAdapter.g(dimensionPixelSize).f(dimensionPixelSize);
        this.recyclerPaintColor.setAdapter(this.colorAdapter);
        this.etTextDressWord.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.fragment.MarkTextDialogBaseFragment.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                MarkTextDialogBaseFragment.this.hideInputMethod();
                MarkTextDialogBaseFragment.this.inputFinish();
                return true;
            }
        });
        this.etTextDressWord.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.maxLen)});
        Configuration configuration = getResources().getConfiguration();
        this.currentScreenSize.set(configuration.screenWidthDp, configuration.screenHeightDp);
        this.etTextDressWord.addTextChangedListener(this.textWatcher);
        configButtonShowByTheme();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void reLayout() {
        w.c("MarkTextDialog relayout");
        this.lastInput = this.etTextDressWord.getText().toString();
        this.lastColorIndex = this.colorIndex;
        this.etTextDressWord.removeTextChangedListener(this.textWatcher);
        super.reLayout();
    }

    public void setEditDrawable(TextDressDrawable textDressDrawable) {
        this.editDrawable = textDressDrawable;
    }

    public void setOnTextInputListener(OnTextInputListener onTextInputListener) {
        this.onTextInputListener = onTextInputListener;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected boolean titleVisible() {
        return false;
    }
}
