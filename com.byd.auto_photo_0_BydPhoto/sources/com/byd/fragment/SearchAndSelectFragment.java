package com.byd.fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.byd.auto_photo.AboutActivity;
import com.byd.auto_photo.MainActivity;
import com.byd.auto_photo.R;
import com.byd.fragment.SearchAndSelectBaseFragment;
import com.byd.util.e;
import com.byd.util.g;
import com.byd.util.h;
import com.byd.util.w;
import com.byd.util.x;

/* JADX INFO: loaded from: classes2.dex */
public class SearchAndSelectFragment extends SearchAndSelectBaseFragment implements MainActivity.c {
    private ImageView ivSearchIcon;
    private View layoutSearchInputContainer;
    private PopupWindow moreWindow;
    private int selectSize;
    private TextView tvSelectNum;
    private TextView viewCancelSelectMode;
    private TextView viewCopy;
    private View viewExitSearchMode;
    private TextView viewMove;

    private void dismissMoreMenu() {
        PopupWindow popupWindow = this.moreWindow;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.moreWindow.dismiss();
    }

    private int getMenuWidth() {
        return getResources().getDimensionPixelSize(R.dimen.dp_138);
    }

    private int getMenuXOffset() {
        return e.v(getActivity()) <= 720 ? -80 : -155;
    }

    private void setToolsDrawables() {
        setTextViewDrawableLeft(this.viewCancelSelectMode, R.drawable.icon_close_1);
        setTextViewDrawableLeft(this.btnShare, R.drawable.icon_share);
        setTextViewDrawableLeft(this.btnReName, R.drawable.icon_rename);
        setTextViewDrawableLeft(this.btnSelectDelete, R.drawable.icon_del);
        setTextViewDrawableLeft(this.btnSelectCancel, R.drawable.icon_more);
        setTextViewDrawableLeft(this.tvTitleBack, R.drawable.icon_back_ignore_direction, true);
        FragmentActivity activity = getActivity();
        this.btnSelectAll.setCompoundDrawablesWithIntrinsicBounds(e.p(activity, R.attr.select_all), (Drawable) null, (Drawable) null, (Drawable) null);
        ((TextView) this.btnAbout).setCompoundDrawablesWithIntrinsicBounds(x.s(activity, R.drawable.icon_detail), (Drawable) null, (Drawable) null, (Drawable) null);
        ((ImageView) this.viewExitSearchMode).setImageDrawable(x.s(activity, R.drawable.icon_back_night));
        this.viewToSearchMode.setCompoundDrawablesWithIntrinsicBounds(x.s(activity, R.drawable.icon_search_drawable), (Drawable) null, (Drawable) null, (Drawable) null);
    }

    private void showMoreMenuWindow() {
        if (this.moreWindow == null) {
            View viewInflate = getActivity().getLayoutInflater().inflate(R.layout.title_photo3_more_menu_layout, (ViewGroup) null);
            this.viewMove = (TextView) viewInflate.findViewById(R.id.photo3_more_menu_move);
            TextView textView = (TextView) viewInflate.findViewById(R.id.photo3_more_menu_copy);
            this.viewCopy = textView;
            textView.setOnClickListener(this);
            this.viewMove.setOnClickListener(this);
            PopupWindow popupWindow = new PopupWindow(viewInflate, getMenuWidth(), getResources().getDimensionPixelSize(R.dimen.dp_91));
            this.moreWindow = popupWindow;
            popupWindow.setFocusable(true);
            this.moreWindow.setOutsideTouchable(true);
            this.moreWindow.setBackgroundDrawable(new ColorDrawable());
            this.moreWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.byd.fragment.SearchAndSelectFragment.1
                @Override // android.widget.PopupWindow.OnDismissListener
                public void onDismiss() {
                    SearchAndSelectFragment searchAndSelectFragment = SearchAndSelectFragment.this;
                    searchAndSelectFragment.setTextViewDrawableLeft(searchAndSelectFragment.btnSelectCancel, R.drawable.icon_more);
                    SearchAndSelectFragment.this.btnSelectCancel.setTextColor(SearchAndSelectFragment.this.getResources().getColorStateList(R.color.color_for_press, SearchAndSelectFragment.this.getActivity().getTheme()));
                }
            });
        }
        int iL = e.l(getActivity(), R.attr.text_color_press);
        this.btnSelectCancel.setCompoundDrawablesWithIntrinsicBounds(h.a(getResources().getDrawable(R.drawable.icon_more), iL), (Drawable) null, (Drawable) null, (Drawable) null);
        this.btnSelectCancel.setTextColor(iL);
        setTextViewDrawableLeft(this.viewMove, R.drawable.icon_move);
        setTextViewDrawableLeft(this.viewCopy, R.drawable.icon_copy);
        this.viewCopy.setText(R.string.copy);
        this.viewMove.setText(R.string.move);
        boolean z = this.selectSize > 0;
        this.viewCopy.setEnabled(z);
        this.viewMove.setEnabled(z);
        this.moreWindow.showAsDropDown(this.btnSelectCancel, getMenuXOffset(), 0);
    }

    private void showSelectedNum(int i) {
        TextView textView = this.tvSelectNum;
        Resources resources = getResources();
        textView.setText(i == 1 ? String.format(resources.getString(R.string.select_single), Integer.valueOf(i)) : String.format(resources.getString(R.string.select_total), Integer.valueOf(i)));
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment, android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        ImageView imageView;
        int i;
        super.afterTextChanged(editable);
        if (editable.length() <= 0) {
            imageView = this.btnCancelSearch;
            i = 8;
        } else {
            if (this.btnCancelSearch.getVisibility() == 0) {
                return;
            }
            imageView = this.btnCancelSearch;
            i = 0;
        }
        imageView.setVisibility(i);
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment
    protected void applyTextSize(int i) {
        super.applyTextSize(i);
        this.viewCancelSelectMode.setTextSize(0, i);
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment
    public void cancelSelectMode() {
        dismissMoreMenu();
        super.cancelSelectMode();
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment
    protected void checkMode() {
        super.checkMode();
        if (this.mode != 1 || this.isSelectMode) {
            return;
        }
        this.layoutSearchInputContainer.setVisibility(4);
        this.viewExitSearchMode.setVisibility(8);
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment
    protected int getMoveBtnVisibility() {
        return 8;
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment
    public void initSearchModeUI(boolean z, int i) {
        this.layoutSearchInputContainer.setVisibility(0);
        this.viewExitSearchMode.setVisibility(0);
        super.initSearchModeUI(z, i);
        this.btnCancelSearch.setVisibility(8);
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        SearchAndSelectBaseFragment.OnHeaderListener onHeaderListener;
        boolean z;
        if (e.y()) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_about /* 2131230791 */:
                startActivity(new Intent(getActivity(), (Class<?>) AboutActivity.class));
                return;
            case R.id.btn_cancel_select_mode /* 2131230796 */:
                cancelSelectMode();
                return;
            case R.id.btn_select_cancel /* 2131230819 */:
                showMoreMenuWindow();
                return;
            case R.id.photo3_more_menu_copy /* 2131231008 */:
                dismissMoreMenu();
                onHeaderListener = this.onHeaderListener;
                if (onHeaderListener == null) {
                    return;
                } else {
                    z = false;
                }
                break;
            case R.id.photo3_more_menu_move /* 2131231009 */:
                dismissMoreMenu();
                onHeaderListener = this.onHeaderListener;
                if (onHeaderListener == null) {
                    return;
                } else {
                    z = true;
                }
                break;
            case R.id.view_exit_search_mode /* 2131231184 */:
                hideSoftInput();
                if (this.etSearchWord.getVisibility() == 0) {
                    hideSearchResult();
                    return;
                }
                return;
            default:
                super.onClick(view);
                return;
        }
        onHeaderListener.onCopy(z);
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment, com.byd.fragment.SubBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.defShareVisibility = 8;
        super.onCreate(bundle);
        ((MainActivity) getActivity()).g1(this);
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment, com.byd.fragment.SubBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onDestroy() {
        ((MainActivity) getActivity()).k1(this);
        super.onDestroy();
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onDestroyView() {
        dismissMoreMenu();
        super.onDestroyView();
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment, android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        FragmentActivity activity;
        int i;
        View view2 = this.layoutSearchInputContainer;
        if (view2 == null) {
            return;
        }
        if (z) {
            activity = getActivity();
            i = R.attr.bg_search_input_focus;
        } else {
            activity = getActivity();
            i = R.attr.bg_search_input;
        }
        view2.setBackgroundResource(e.u(activity, i));
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment, com.byd.fragment.SubBaseFragment
    protected void onLocaleChanged(Configuration configuration) {
        this.etSearchWord.setHint(R.string.search_tips);
        onShowTitle();
        this.viewCancelSelectMode.setText(R.string.cancel);
        if (this.tvSelectNum.getVisibility() == 0) {
            showSelectedNum(this.selectSize);
        }
        PopupWindow popupWindow = this.moreWindow;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.viewCopy.setText(R.string.copy);
        this.viewMove.setText(R.string.move);
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment
    public void onSelectedSizeChange(int i) {
        this.selectSize = i;
        boolean z = i > 0;
        this.btnSelectDelete.setEnabled(z);
        TextView textView = this.viewMove;
        if (textView != null) {
            textView.setEnabled(z);
        }
        TextView textView2 = this.viewCopy;
        if (textView2 != null) {
            textView2.setEnabled(z);
        }
        if (this.tvSelectNum.getVisibility() != 0) {
            this.tvSelectNum.setVisibility(0);
        }
        showSelectedNum(i);
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment
    protected void onShowTitle() {
        if (this.album == null) {
            return;
        }
        this.tvTitleBack.setText(x.d(getActivity(), this.album, g.i(getActivity(), "/")));
    }

    @Override // com.byd.auto_photo.MainActivity.c
    public void onThemeChange() {
        View view;
        FragmentActivity activity;
        int i;
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            w.c("SearchAndSelect on theme change : activity is null!!");
            return;
        }
        ColorStateList colorStateList = activity2.getResources().getColorStateList(R.color.color_for_press, activity2.getTheme());
        this.viewCancelSelectMode.setTextColor(colorStateList);
        this.btnSelectAll.setTextColor(colorStateList);
        this.btnShare.setTextColor(colorStateList);
        this.btnSelectDelete.setTextColor(colorStateList);
        this.btnReName.setTextColor(colorStateList);
        this.btnSelectCancel.setTextColor(colorStateList);
        setToolsDrawables();
        this.viewToSearchMode.setCompoundDrawablesWithIntrinsicBounds(e.p(activity2, R.attr.icon_search), (Drawable) null, (Drawable) null, (Drawable) null);
        this.ivSearchIcon.setImageDrawable(e.p(activity2, R.attr.icon_search));
        this.etSearchWord.setTextColor(e.l(activity2, R.attr.search_input_text_color));
        this.etSearchWord.setHintTextColor(e.l(activity2, R.attr.search_hint_text_color));
        ((TextView) this.btnAbout).setTextColor(colorStateList);
        this.btnCancelSearch.setImageDrawable(e.p(activity2, R.attr.icon_clear_input));
        this.tvTitleBack.setTextColor(colorStateList);
        PopupWindow popupWindow = this.moreWindow;
        if (popupWindow != null) {
            boolean zIsShowing = popupWindow.isShowing();
            if (zIsShowing) {
                this.moreWindow.dismiss();
            }
            this.moreWindow = null;
            if (zIsShowing) {
                showMoreMenuWindow();
            }
        }
        View view2 = this.layoutSearchInputContainer;
        if (view2 != null && view2.getVisibility() == 0) {
            if (this.etSearchWord.hasFocus()) {
                view = this.layoutSearchInputContainer;
                activity = getActivity();
                i = R.attr.bg_search_input_focus;
            } else {
                view = this.layoutSearchInputContainer;
                activity = getActivity();
                i = R.attr.bg_search_input;
            }
            view.setBackgroundResource(e.u(activity, i));
        }
        if (this.isSelectAll) {
            setSelectAll(true);
        }
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.layoutSearchInputContainer = view.findViewById(R.id.search_input_container);
        View viewFindViewById = view.findViewById(R.id.view_exit_search_mode);
        this.viewExitSearchMode = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        TextView textView = (TextView) view.findViewById(R.id.btn_cancel_select_mode);
        this.viewCancelSelectMode = textView;
        textView.setOnClickListener(this);
        this.tvSelectNum = (TextView) view.findViewById(R.id.tv_current_select_num);
        this.ivSearchIcon = (ImageView) view.findViewById(R.id.iv_search_icon);
        this.btnSelectCancel.setText(R.string.more);
        setToolsDrawables();
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment
    public void setSearchSource(int i) {
        TextView textView;
        super.setSearchSource(i);
        if (i == 1) {
            TextView textView2 = this.btnSelectDelete;
            if (textView2 != null) {
                textView2.setText(R.string.del_folder);
            }
            if (this.isInSearchPage || (textView = this.btnReName) == null) {
                return;
            }
            setTextViewDrawableLeft(textView, R.drawable.icon_rename);
        }
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment
    public void setSearchTextNoWatcher(String str) {
        super.setSearchTextNoWatcher(str);
        if (str == null || "".equals(str)) {
            return;
        }
        this.btnCancelSearch.setVisibility(0);
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment
    public void setSelectAll(boolean z) {
        TextView textView;
        FragmentActivity activity;
        int i;
        this.isSelectAll = z;
        if (z) {
            textView = this.btnSelectAll;
            activity = getActivity();
            i = R.attr.checkbox_selected;
        } else {
            textView = this.btnSelectAll;
            activity = getActivity();
            i = R.attr.select_all;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(e.p(activity, i), (Drawable) null, (Drawable) null, (Drawable) null);
    }
}
