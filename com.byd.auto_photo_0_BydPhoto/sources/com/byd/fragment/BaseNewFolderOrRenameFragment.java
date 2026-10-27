package com.byd.fragment;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
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
import androidx.fragment.app.FragmentActivity;
import b.a.d;
import b.a.h.l;
import b.a.k.g;
import com.byd.auto_photo.R;
import com.byd.auto_photo.a;
import com.byd.bean.AlbumItem;
import com.byd.util.e;
import com.byd.util.f;
import com.byd.util.w;
import com.byd.util.x;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseNewFolderOrRenameFragment extends BaseDialogFragment {
    private static final int DEF_MAX_LEN = 32;
    private static final int MSG_PROGRESS_STRING_UPDATE = 3;
    private static final int MSG_PROGRESS_UPDATE = 2;
    private static final int MSG_RENAME_OK = 1;
    private static final int MSG_SHOW_SOFT_INPUT = 4;
    private FragmentActivity activity;
    private String albumPath;
    private boolean closeByUser;
    private int dialogHeight;
    private AlbumItem editAlbum;
    private EditText etName;
    private String folderName;
    private InputMethodManager imm;
    private boolean inputShowOnPause;
    private boolean isStart;
    private String lastInputStr;
    private MHandler mHandler;
    private int maxLen;
    private String nameLenTipStr;
    private OnCreateListener onCreateListener;
    private ProgressDialogFragment progressDialog;
    private ScannerListener scannerListener;
    private File source;
    private List<String> sourceChildrenPaths;
    private File target;
    protected TextView tvConflict;
    protected TextView tvMaxLenTips;
    protected View viewClearInput;
    protected int softInputMode = 16;
    private TextWatcher textWatcher = new TextWatcher() { // from class: com.byd.fragment.BaseNewFolderOrRenameFragment.3
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            w.c("afterTextChanged : " + ((Object) editable));
            if (!BaseNewFolderOrRenameFragment.this.etName.isFocused()) {
                BaseNewFolderOrRenameFragment.this.etName.requestFocus();
            }
            if (BaseNewFolderOrRenameFragment.this.tvMaxLenTips.getVisibility() != 0) {
                BaseNewFolderOrRenameFragment.this.tvConflict.setVisibility(8);
                BaseNewFolderOrRenameFragment.this.tvMaxLenTips.setVisibility(0);
            }
            int length = editable.length();
            BaseNewFolderOrRenameFragment.this.lastInputStr = editable.toString();
            if (length > 0) {
                View view = BaseNewFolderOrRenameFragment.this.viewClearInput;
                if (view != null && view.getVisibility() != 0) {
                    BaseNewFolderOrRenameFragment.this.viewClearInput.setVisibility(0);
                }
                BaseNewFolderOrRenameFragment.this.checkTextForBtn(editable.toString(), length, true);
                return;
            }
            View view2 = BaseNewFolderOrRenameFragment.this.viewClearInput;
            if (view2 != null && view2.getVisibility() == 0) {
                BaseNewFolderOrRenameFragment.this.viewClearInput.setVisibility(8);
            }
            BaseNewFolderOrRenameFragment.this.setSureButtonEnable(false);
            BaseNewFolderOrRenameFragment.this.onOptInputTips(false);
            if (a.f1178a.intValue() >= 50) {
                BaseNewFolderOrRenameFragment.this.tvMaxLenTips.setVisibility(8);
                return;
            }
            BaseNewFolderOrRenameFragment baseNewFolderOrRenameFragment = BaseNewFolderOrRenameFragment.this;
            baseNewFolderOrRenameFragment.tvMaxLenTips.setTextColor(baseNewFolderOrRenameFragment.getActivity().getResources().getColor(R.color.text_hint_color_night));
            BaseNewFolderOrRenameFragment baseNewFolderOrRenameFragment2 = BaseNewFolderOrRenameFragment.this;
            baseNewFolderOrRenameFragment2.tvMaxLenTips.setText(baseNewFolderOrRenameFragment2.nameLenTipStr);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    };
    private int colorIndex = 0;

    private static class MHandler extends d<BaseNewFolderOrRenameFragment> {
        public MHandler(BaseNewFolderOrRenameFragment baseNewFolderOrRenameFragment) {
            super(baseNewFolderOrRenameFragment);
        }

        @Override // b.a.d
        public void handleMessage(BaseNewFolderOrRenameFragment baseNewFolderOrRenameFragment, Message message) {
            int i = message.what;
            if (i == 1) {
                baseNewFolderOrRenameFragment.doRenameOk();
                return;
            }
            if (i == 2) {
                baseNewFolderOrRenameFragment.updateProgress(message);
            } else if (i == 3) {
                baseNewFolderOrRenameFragment.startUpdateProgress(message);
            } else {
                if (i != 4) {
                    return;
                }
                baseNewFolderOrRenameFragment.showSoftInput();
            }
        }
    }

    public interface OnCreateListener {
        boolean isAlbumExist(String str);

        void onCreateOk(File file);

        void onRenameOk(File file, File file2);

        void onRenameOrCreateCancel();
    }

    private class ScannerListener implements MediaScannerConnection.OnScanCompletedListener {
        private int count;
        private int size;
        private List<String> syncPaths;

        private ScannerListener(List<String> list) {
            setSyncPaths(list);
        }

        @Override // android.media.MediaScannerConnection.OnScanCompletedListener
        public void onScanCompleted(String str, Uri uri) {
            this.count++;
            this.syncPaths.remove(str);
            BaseNewFolderOrRenameFragment.this.mHandler.obtainMessage(3, BaseNewFolderOrRenameFragment.this.activity.getResources().getString(R.string.data_sync) + "\n" + this.count + "/" + this.size).sendToTarget();
            if (this.syncPaths.isEmpty() || this.count == this.size) {
                w.c("---rename folder and sync complete---");
                BaseNewFolderOrRenameFragment.this.mHandler.sendEmptyMessage(1);
            }
        }

        public void setSyncPaths(List<String> list) {
            this.syncPaths = list;
            this.size = list.size();
            this.count = 0;
        }
    }

    private class UpdateThread extends Thread {
        private File[] fs;
        private String path;

        private UpdateThread(File[] fileArr, String str) {
            this.fs = fileArr;
            this.path = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int length = this.fs.length;
            LinkedList linkedList = new LinkedList();
            if (BaseNewFolderOrRenameFragment.this.sourceChildrenPaths == null) {
                BaseNewFolderOrRenameFragment.this.sourceChildrenPaths = new LinkedList();
            } else {
                BaseNewFolderOrRenameFragment.this.sourceChildrenPaths.clear();
            }
            for (int i = 0; i < length; i++) {
                File file = new File(this.path + File.separator + this.fs[i].getName());
                linkedList.add(this.fs[i].getPath());
                linkedList.add(file.getPath());
                l.m(BaseNewFolderOrRenameFragment.this.activity).k(this.fs[i].getPath());
                BaseNewFolderOrRenameFragment.this.mHandler.obtainMessage(2, i, length).sendToTarget();
                BaseNewFolderOrRenameFragment.this.sourceChildrenPaths.add(this.fs[i].getPath());
                if (f.k(BaseNewFolderOrRenameFragment.this.activity).o(this.fs[i].getPath())) {
                    f.k(BaseNewFolderOrRenameFragment.this.activity).x(this.fs[i].getPath(), file.getPath());
                }
                e.a(BaseNewFolderOrRenameFragment.this.activity, file.getPath(), 2);
            }
            if (linkedList.isEmpty()) {
                BaseNewFolderOrRenameFragment.this.mHandler.sendEmptyMessage(1);
            } else {
                BaseNewFolderOrRenameFragment.this.syncMedias(linkedList);
            }
        }
    }

    private void checkTipsViewShow() {
        if (this.tvMaxLenTips.getVisibility() != 0) {
            this.tvMaxLenTips.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRenameOk() {
        ProgressDialogFragment progressDialogFragment = this.progressDialog;
        if (progressDialogFragment != null) {
            progressDialogFragment.dismissAllowingStateLoss();
            this.progressDialog = null;
        }
        OnCreateListener onCreateListener = this.onCreateListener;
        if (onCreateListener != null) {
            onCreateListener.onRenameOk(this.source, this.target);
        }
        List<String> list = this.sourceChildrenPaths;
        if (list != null && list.size() > 0) {
            g.f(this.activity).d(this.sourceChildrenPaths);
        }
        this.folderName = null;
        this.editAlbum = null;
        this.closeByUser = true;
        hideSoftInput();
        super.onSureClick();
    }

    private int getConflictColor() {
        return SupportMenu.CATEGORY_MASK;
    }

    private String getFolderConflictName(File file) {
        String name = file.getName();
        String parent = file.getParent();
        if (isConflictFolderName(name)) {
            name = name.substring(0, name.lastIndexOf("_"));
        }
        String path = file.getPath();
        int i = 1;
        while (file.exists()) {
            path = parent + File.separatorChar + name + "_" + i;
            file = new File(path);
            i++;
        }
        return path;
    }

    private boolean isConflictFolderName(String str) {
        return Pattern.compile(".*(_[0-9]+)$").matcher(str).matches();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSoftInput() {
        if (x.l(this.activity) || !this.isStart) {
            w.c("soft input already shown or page not start : " + this.isStart);
            return;
        }
        boolean zShowSoftInput = this.imm.showSoftInput(this.etName, 0);
        w.c("NewFolderOrReName show soft input : " + zShowSoftInput + " " + this.isStart);
        if (!this.etName.isFocused()) {
            this.etName.requestFocus();
        }
        if (zShowSoftInput || !this.isStart) {
            return;
        }
        this.mHandler.sendEmptyMessageDelayed(4, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startUpdateProgress(Message message) {
        ProgressDialogFragment progressDialogFragment = this.progressDialog;
        if (progressDialogFragment == null || !progressDialogFragment.isShowing()) {
            return;
        }
        this.progressDialog.updateMsg((String) message.obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncMedias(List<String> list) {
        this.mHandler.obtainMessage(3, this.activity.getResources().getString(R.string.data_sync)).sendToTarget();
        String[] strArr = new String[list.size()];
        list.toArray(strArr);
        ScannerListener scannerListener = this.scannerListener;
        if (scannerListener == null) {
            this.scannerListener = new ScannerListener(list);
        } else {
            scannerListener.setSyncPaths(list);
        }
        try {
            MediaScannerConnection.scanFile(this.activity.getApplicationContext(), strArr, null, this.scannerListener);
        } catch (Exception e) {
            e.printStackTrace();
            w.c("rename folder scanFile exception : " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgress(Message message) {
        int i;
        ProgressDialogFragment progressDialogFragment = this.progressDialog;
        if (progressDialogFragment == null || !progressDialogFragment.isShowing() || (i = message.arg2) == 0) {
            return;
        }
        int i2 = (message.arg1 * 100) / i;
        this.progressDialog.updateMsg(i2 + "%");
    }

    protected void checkTextForBtn(String str, int i, boolean z) {
        boolean zO = x.o(str);
        int i2 = SupportMenu.CATEGORY_MASK;
        if (zO) {
            setSureButtonEnable(false);
            checkTipsViewShow();
            this.tvMaxLenTips.setTextColor(SupportMenu.CATEGORY_MASK);
            this.tvMaxLenTips.setText(String.format(Locale.US, getResources().getString(R.string.special_char_tips), "#%?:*|/\\<>"));
            onOptInputTips(true);
            return;
        }
        if (i == this.maxLen) {
            checkTipsViewShow();
            TextView textView = this.tvMaxLenTips;
            if (a.f1178a.intValue() < 50) {
                i2 = -15616;
            }
            textView.setTextColor(i2);
            this.tvMaxLenTips.setText(R.string.name_len_reach_max);
            onOptInputTips(true);
        } else {
            if (a.f1178a.intValue() < 50) {
                checkTipsViewShow();
                this.tvMaxLenTips.setTextColor(this.activity.getResources().getColor(R.color.text_hint_color_night));
                this.tvMaxLenTips.setText(this.nameLenTipStr);
            } else {
                this.tvMaxLenTips.setVisibility(8);
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
        Resources resources;
        int i;
        if (this.editAlbum != null) {
            resources = this.activity.getResources();
            i = R.string.rename_album;
        } else {
            resources = this.activity.getResources();
            i = R.string.new_album;
        }
        return resources.getString(i);
    }

    protected void hideSoftInput() {
        String str;
        w.c("===hideSoftInput===");
        MHandler mHandler = this.mHandler;
        if (mHandler != null) {
            mHandler.removeMessages(4);
        }
        EditText editText = this.etName;
        if (editText == null) {
            str = "etName is null";
        } else {
            str = "hide soft result : " + this.imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
        w.c(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    @Override // com.byd.fragment.BaseDialogSuperFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void initUiData() {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.fragment.BaseNewFolderOrRenameFragment.initUiData():void");
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void onCancelClick() {
        super.onCancelClick();
        hideSoftInput();
        this.closeByUser = true;
        this.folderName = null;
        this.editAlbum = null;
        OnCreateListener onCreateListener = this.onCreateListener;
        if (onCreateListener != null) {
            onCreateListener.onRenameOrCreateCancel();
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        this.mHandler = new MHandler(this);
        super.onCreate(bundle);
        this.imm = (InputMethodManager) getActivity().getSystemService("input_method");
        FragmentActivity fragmentActivity = this.activity;
        if (fragmentActivity == null) {
            dismissAllowingStateLoss();
            w.c("NewFolderDialog dismiss by no activity");
        } else {
            this.albumPath = com.byd.util.g.j(fragmentActivity);
            this.dialogHeight = getResources().getDimensionPixelSize(R.dimen.new_or_rename_dialog_height);
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        MHandler mHandler = this.mHandler;
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            this.mHandler.release();
            this.mHandler = null;
        }
        ProgressDialogFragment progressDialogFragment = this.progressDialog;
        if (progressDialogFragment != null) {
            if (progressDialogFragment.isShowing()) {
                this.progressDialog.dismissAllowingStateLoss();
            }
            this.progressDialog = null;
        }
        super.onDestroy();
        this.inputShowOnPause = false;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.lastInputStr = null;
        super.onDestroyView();
        this.mHandler.removeMessages(4);
        if (a.f1178a.intValue() != 2) {
            this.etName.removeTextChangedListener(this.textWatcher);
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void onDialogDismiss(DialogInterface dialogInterface) {
        super.onDialogDismiss(dialogInterface);
        w.c("==onDismiss==");
        this.colorIndex = 0;
        if (this.closeByUser) {
            return;
        }
        this.folderName = null;
        this.editAlbum = null;
        OnCreateListener onCreateListener = this.onCreateListener;
        if (onCreateListener != null) {
            onCreateListener.onRenameOrCreateCancel();
        }
    }

    protected void onEditTextFocusChange(View view, boolean z) {
        w.c("NewFolderOrRename edit text onFocusChange : " + z);
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
        this.mHandler.removeMessages(4);
        this.inputShowOnPause = false;
        if (this.closeByUser) {
            return;
        }
        boolean zL = x.l(this.activity);
        this.inputShowOnPause = zL;
        if (zL) {
            w.c("NewFolderOrReName onPause to hide soft input");
            hideSoftInput();
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment
    public void onResume() {
        this.isStart = true;
        super.onResume();
        ProgressDialogFragment progressDialogFragment = this.progressDialog;
        if ((progressDialogFragment == null || !progressDialogFragment.isShowing()) && this.inputShowOnPause && !this.mHandler.hasMessages(4)) {
            w.c("NewFolderOrReName onResume to show soft input");
            showSoftDelay();
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setLayout(getResources().getDimensionPixelSize(R.dimen.dialog_min_width), this.dialogHeight);
        window.setSoftInputMode(this.softInputMode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.fragment.BaseDialogSuperFragment
    public void onSureClick() {
        FragmentActivity fragmentActivity;
        int i;
        w.c("newFolderOrRenameDialog onSureClick");
        String strTrim = this.etName.getText().toString().trim();
        if (TextUtils.isEmpty(strTrim)) {
            w.e(this.activity, R.string.name_empty);
            return;
        }
        if (x.o(strTrim)) {
            w.e(this.activity, R.string.name_illegal_by_special);
            return;
        }
        if (strTrim.startsWith(".")) {
            w.e(this.activity, R.string.name_can_not_start_with_dot);
            return;
        }
        this.closeByUser = true;
        String parent = this.editAlbum != null ? new File(this.editAlbum.getUri().getPath()).getParent() : this.albumPath;
        if (parent == null) {
            w.e(this.activity, R.string.disk_inexistence);
            hideSoftInput();
            super.onSureClick();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(parent);
        String str = File.separator;
        sb.append(str);
        sb.append(strTrim);
        String string = sb.toString();
        this.target = new File(string);
        OnCreateListener onCreateListener = this.onCreateListener;
        if ((onCreateListener != null && onCreateListener.isAlbumExist(strTrim) && this.editAlbum == null) || this.target.exists()) {
            this.tvConflict.setTextColor(getConflictColor());
            this.tvMaxLenTips.setVisibility(8);
            this.tvConflict.setVisibility(0);
            this.closeByUser = false;
            setSureButtonEnable(false);
            onOptInputTips(true);
            return;
        }
        setCancelable(false);
        if (this.editAlbum == null) {
            w.c("start new folder");
            boolean zMkdirs = this.target.mkdirs();
            w.c("finish mkdirs : " + zMkdirs);
            if (!zMkdirs) {
                fragmentActivity = this.activity;
                i = R.string.new_album_fail;
                w.e(fragmentActivity, i);
                this.closeByUser = false;
                setCancelable(true);
                return;
            }
            f.k(this.activity).b(string);
            OnCreateListener onCreateListener2 = this.onCreateListener;
            if (onCreateListener2 != null) {
                onCreateListener2.onCreateOk(this.target);
            }
            hideSoftInput();
            super.onSureClick();
            return;
        }
        File file = new File(parent + str + this.folderName);
        this.source = file;
        File[] fileArrListFiles = file.listFiles();
        w.c("rename album :" + this.source.getPath() + " -> " + this.target.getPath());
        if (!this.source.renameTo(this.target)) {
            fragmentActivity = this.activity;
            i = R.string.rename_fail;
            w.e(fragmentActivity, i);
            this.closeByUser = false;
            setCancelable(true);
            return;
        }
        try {
            MediaScannerConnection.scanFile(this.activity, new String[]{this.source.getPath(), this.target.getPath()}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.byd.fragment.BaseNewFolderOrRenameFragment.4
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String str2, Uri uri) {
                    w.c("rename folder onScanCompleted : " + str2);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.k(this.activity).w(this.source.getPath(), string);
        this.editAlbum.setName(strTrim);
        this.editAlbum.setUri(Uri.parse(string));
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            OnCreateListener onCreateListener3 = this.onCreateListener;
            if (onCreateListener3 != null) {
                onCreateListener3.onRenameOk(this.source, this.target);
            }
            hideSoftInput();
            this.folderName = null;
            this.editAlbum = null;
            super.onSureClick();
            return;
        }
        this.editAlbum.setCoverPath(string + File.separator + fileArrListFiles[0].getName());
        if (this.progressDialog == null) {
            ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
            this.progressDialog = progressDialogFragment;
            progressDialogFragment.setCancelable(false);
        }
        this.progressDialog.setMsgRes(R.string.content_updating);
        this.progressDialog.show(this.activity.getSupportFragmentManager(), "rename_content");
        new UpdateThread(fileArrListFiles, string).start();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        w.c("NewFolder Or Rename onViewCreated");
        String str = this.lastInputStr;
        setSureButtonEnable(str != null ? str.length() > 0 : this.editAlbum == null);
        EditText editText = (EditText) view.findViewById(R.id.et_name);
        this.etName = editText;
        editText.setHint(R.string.album_name_hint);
        x.w(getActivity(), 16, this.etName);
        setClearViewShowPos();
        this.etName.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.fragment.BaseNewFolderOrRenameFragment.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view2, boolean z) {
                BaseNewFolderOrRenameFragment.this.onEditTextFocusChange(view2, z);
            }
        });
        this.tvConflict = (TextView) view.findViewById(R.id.tv_conflict_tips);
        this.tvMaxLenTips = (TextView) view.findViewById(R.id.tv_name_len_tips);
        if (a.f1178a.intValue() != 2) {
            View viewFindViewById = view.findViewById(R.id.iv_clear_input);
            this.viewClearInput = viewFindViewById;
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.byd.fragment.BaseNewFolderOrRenameFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    BaseNewFolderOrRenameFragment.this.etName.setText("");
                    BaseNewFolderOrRenameFragment.this.etName.setSelection(0);
                    if (BaseNewFolderOrRenameFragment.this.tvMaxLenTips.getVisibility() != 0) {
                        BaseNewFolderOrRenameFragment.this.tvConflict.setVisibility(8);
                        if (a.f1178a.intValue() < 50) {
                            BaseNewFolderOrRenameFragment.this.tvMaxLenTips.setVisibility(0);
                        }
                    }
                }
            });
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void reLayout() {
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

    public void setEditAlbum(FragmentActivity fragmentActivity, AlbumItem albumItem, String str) {
        this.activity = fragmentActivity;
        this.editAlbum = albumItem;
        this.folderName = str;
    }

    public void setOnCreateListener(OnCreateListener onCreateListener) {
        this.onCreateListener = onCreateListener;
    }

    public void setSoftInputMode(int i) {
        this.softInputMode = i;
    }

    protected void setSureButtonEnable(boolean z) {
        TextView textView = this.btnSure;
        if (textView == null) {
            return;
        }
        textView.setEnabled(z);
    }

    protected void showSoftDelay() {
        this.mHandler.removeMessages(4);
        this.mHandler.sendEmptyMessageDelayed(4, 200L);
    }
}
