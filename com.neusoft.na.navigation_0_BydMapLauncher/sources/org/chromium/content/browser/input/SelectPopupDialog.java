package org.chromium.content.browser.input;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.List;
import org.chromium.content.R;
import org.chromium.content.browser.ContentViewCore;

/* JADX INFO: loaded from: classes3.dex */
public class SelectPopupDialog implements SelectPopup {
    private static final int[] SELECT_DIALOG_ATTRS = {R.attr.select_dialog_multichoice, R.attr.select_dialog_singlechoice};
    private final ContentViewCore mContentViewCore;
    private final AlertDialog mListBoxPopup;
    private boolean mSelectionNotified;

    public SelectPopupDialog(ContentViewCore contentViewCore, Context context, List<SelectPopupItem> list, boolean z, int[] iArr) {
        this.mContentViewCore = contentViewCore;
        final ListView listView = new ListView(context);
        AlertDialog.Builder cancelable = new AlertDialog.Builder(context).setView(listView).setCancelable(true);
        setInverseBackgroundForced(cancelable);
        if (z) {
            cancelable.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: org.chromium.content.browser.input.SelectPopupDialog.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    SelectPopupDialog.this.notifySelection(SelectPopupDialog.getSelectedIndices(listView));
                }
            });
            cancelable.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: org.chromium.content.browser.input.SelectPopupDialog.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    SelectPopupDialog.this.notifySelection(null);
                }
            });
        }
        AlertDialog alertDialogCreate = cancelable.create();
        this.mListBoxPopup = alertDialogCreate;
        listView.setAdapter((ListAdapter) new SelectPopupAdapter(alertDialogCreate.getContext(), getSelectDialogLayout(z), list));
        listView.setFocusableInTouchMode(true);
        if (z) {
            listView.setChoiceMode(2);
            for (int i : iArr) {
                listView.setItemChecked(i, true);
            }
        } else {
            listView.setChoiceMode(1);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: org.chromium.content.browser.input.SelectPopupDialog.3
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                    SelectPopupDialog.this.notifySelection(SelectPopupDialog.getSelectedIndices(listView));
                    SelectPopupDialog.this.mListBoxPopup.dismiss();
                }
            });
            if (iArr.length > 0) {
                listView.setSelection(iArr[0]);
                listView.setItemChecked(iArr[0], true);
            }
        }
        this.mListBoxPopup.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: org.chromium.content.browser.input.SelectPopupDialog.4
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                SelectPopupDialog.this.notifySelection(null);
            }
        });
    }

    private static void setInverseBackgroundForced(AlertDialog.Builder builder) {
        builder.setInverseBackgroundForced(true);
    }

    private int getSelectDialogLayout(boolean z) {
        TypedArray typedArrayObtainStyledAttributes = this.mListBoxPopup.getContext().obtainStyledAttributes(R.style.SelectPopupDialog, SELECT_DIALOG_ATTRS);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(!z ? 1 : 0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int[] getSelectedIndices(ListView listView) {
        SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
        int i = 0;
        for (int i2 = 0; i2 < checkedItemPositions.size(); i2++) {
            if (checkedItemPositions.valueAt(i2)) {
                i++;
            }
        }
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < checkedItemPositions.size(); i4++) {
            if (checkedItemPositions.valueAt(i4)) {
                iArr[i3] = checkedItemPositions.keyAt(i4);
                i3++;
            }
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySelection(int[] iArr) {
        if (this.mSelectionNotified) {
            return;
        }
        this.mContentViewCore.selectPopupMenuItems(iArr);
        this.mSelectionNotified = true;
    }

    @Override // org.chromium.content.browser.input.SelectPopup
    public void show() {
        try {
            this.mListBoxPopup.show();
        } catch (WindowManager.BadTokenException unused) {
            notifySelection(null);
        }
    }

    @Override // org.chromium.content.browser.input.SelectPopup
    public void hide(boolean z) {
        if (z) {
            this.mListBoxPopup.cancel();
            notifySelection(null);
        } else {
            this.mSelectionNotified = true;
            this.mListBoxPopup.cancel();
        }
    }
}
