package org.chromium.ui.autofill;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.chromium.ui.DropdownAdapter;
import org.chromium.ui.DropdownPopupWindow;
import org.chromium.ui.R;
import org.chromium.ui.base.ViewAndroidDelegate;

/* JADX INFO: loaded from: classes3.dex */
public class AutofillPopup extends DropdownPopupWindow implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, PopupWindow.OnDismissListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ITEM_ID_SEPARATOR_ENTRY = -3;
    private final AutofillDelegate mAutofillDelegate;
    private final Context mContext;
    private List<AutofillSuggestion> mSuggestions;

    public AutofillPopup(Context context, ViewAndroidDelegate viewAndroidDelegate, AutofillDelegate autofillDelegate) {
        super(context, viewAndroidDelegate);
        this.mContext = context;
        this.mAutofillDelegate = autofillDelegate;
        setOnItemClickListener(this);
        setOnDismissListener(this);
        disableHideOnOutsideTap();
        setContentDescriptionForAccessibility(context.getString(R.string.autofill_popup_content_description));
    }

    public void filterAndShow(AutofillSuggestion[] autofillSuggestionArr, boolean z) {
        this.mSuggestions = new ArrayList(Arrays.asList(autofillSuggestionArr));
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (int i = 0; i < autofillSuggestionArr.length; i++) {
            if (autofillSuggestionArr[i].getSuggestionId() == -3) {
                hashSet.add(Integer.valueOf(arrayList.size()));
            } else {
                arrayList.add(autofillSuggestionArr[i]);
            }
        }
        setAdapter(new DropdownAdapter(this.mContext, arrayList, hashSet));
        setRtl(z);
        show();
        getListView().setOnItemLongClickListener(this);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.mAutofillDelegate.suggestionSelected(this.mSuggestions.indexOf(((DropdownAdapter) adapterView.getAdapter()).getItem(i)));
    }

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        AutofillSuggestion autofillSuggestion = (AutofillSuggestion) ((DropdownAdapter) adapterView.getAdapter()).getItem(i);
        if (!autofillSuggestion.isDeletable()) {
            return false;
        }
        this.mAutofillDelegate.deleteSuggestion(this.mSuggestions.indexOf(autofillSuggestion));
        return true;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.mAutofillDelegate.dismissed();
    }
}
