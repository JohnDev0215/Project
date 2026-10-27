package com.android.car.ui.toolbar;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.car.ui.R;
import com.android.car.ui.core.SearchResultsProvider;
import com.android.car.ui.imewidescreen.CarUiImeSearchListItem;
import com.android.car.ui.imewidescreen.CarUiImeWideScreenController;
import com.android.car.ui.recyclerview.CarUiContentListItem;
import com.android.car.ui.toolbar.Toolbar;
import com.android.car.ui.utils.CarUiUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes7.dex */
public class SearchView extends ConstraintLayout {
    private final View mCloseIcon;
    private Uri mContentUri;
    private final int mEndPadding;
    private final Handler mHandler;
    private final ImageView mIcon;
    private final Map<String, CarUiImeSearchListItem> mIdToListItem;
    private final InputMethodManager mInputMethodManager;
    private boolean mIsPlainText;
    private Set<Toolbar.OnSearchCompletedListener> mSearchCompletedListeners;
    private Set<Toolbar.OnSearchListener> mSearchListeners;
    private final EditText mSearchText;
    private final int mStartPadding;
    private final int mStartPaddingWithoutIcon;
    private SurfaceControlViewHost mSurfaceControlViewHost;
    private int mSurfaceHeight;
    private int mSurfaceWidth;
    private final TextWatcher mTextWatcher;
    private boolean mWasShown;
    private View mWideScreenImeContentAreaView;
    private List<? extends CarUiImeSearchListItem> mWideScreenSearchItemList;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mIdToListItem = new HashMap();
        this.mSearchListeners = Collections.emptySet();
        this.mSearchCompletedListeners = Collections.emptySet();
        this.mTextWatcher = new TextWatcher() { // from class: com.android.car.ui.toolbar.SearchView.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                SearchView.this.onSearch(editable.toString());
            }
        };
        this.mIsPlainText = false;
        this.mWasShown = false;
        this.mInputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.car_ui_toolbar_search_view, (ViewGroup) this, true);
        this.mSearchText = (EditText) CarUiUtils.requireViewByRefId(this, R.id.car_ui_toolbar_search_bar);
        this.mIcon = (ImageView) CarUiUtils.requireViewByRefId(this, R.id.car_ui_toolbar_search_icon);
        View viewRequireViewByRefId = CarUiUtils.requireViewByRefId(this, R.id.car_ui_toolbar_search_close);
        this.mCloseIcon = viewRequireViewByRefId;
        viewRequireViewByRefId.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$SearchView$X_FGZTNuIRv-v80Y2K7F5FfOzQE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0$SearchView(view);
            }
        });
        this.mCloseIcon.setVisibility(8);
        this.mStartPaddingWithoutIcon = this.mSearchText.getPaddingStart();
        this.mStartPadding = context.getResources().getDimensionPixelSize(R.dimen.car_ui_toolbar_search_search_icon_container_width);
        this.mEndPadding = context.getResources().getDimensionPixelSize(R.dimen.car_ui_toolbar_search_close_icon_container_width);
        this.mSearchText.setSaveEnabled(false);
        this.mSearchText.setPaddingRelative(this.mStartPadding, 0, this.mEndPadding, 0);
        this.mSearchText.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$SearchView$62IJ9o41BAOfbKaNiYxwV4BlZAM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$1$SearchView(view);
            }
        });
        this.mSearchText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$SearchView$PdhJUp_CUIyKm_OMSe2KP-coplc
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                this.f$0.lambda$new$2$SearchView(view, z);
            }
        });
        this.mSearchText.addTextChangedListener(this.mTextWatcher);
        this.mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$SearchView$v5QvEQm_1c9Nac5MG22mHW2cS2M
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return this.f$0.lambda$new$3$SearchView(textView, i, keyEvent);
            }
        });
        EditText editText = this.mSearchText;
        if (editText instanceof CarUiEditText) {
            ((CarUiEditText) editText).registerOnPrivateImeCommandListener(new SearchViewImeCallback());
        }
    }

    public /* synthetic */ void lambda$new$0$SearchView(View view) {
        if (view.isFocused()) {
            this.mSearchText.requestFocus();
            this.mInputMethodManager.showSoftInput(this.mSearchText, 0);
        }
        this.mSearchText.getText().clear();
    }

    public /* synthetic */ void lambda$new$1$SearchView(View view) {
        this.mInputMethodManager.showSoftInput(view, 0);
    }

    public /* synthetic */ void lambda$new$2$SearchView(View view, boolean hasFocus) {
        if (!hasFocus) {
            this.mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public /* synthetic */ boolean lambda$new$3$SearchView(TextView v, int actionId, KeyEvent event) {
        if (actionId == 6 || actionId == 3) {
            notifyQuerySubmit();
            return false;
        }
        if (isEnter(event)) {
            if (event.getAction() == 1) {
                notifyQuerySubmit();
            }
            return true;
        }
        return false;
    }

    void installWindowInsetsListener(View searchContainer) {
        if (Build.VERSION.SDK_INT < 30) {
            return;
        }
        searchContainer.getRootView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$SearchView$aawmi8d94Z_qM5EgIyZaAJHxxMc
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                return this.f$0.lambda$installWindowInsetsListener$5$SearchView(view, windowInsets);
            }
        });
    }

    public /* synthetic */ WindowInsets lambda$installWindowInsetsListener$5$SearchView(View v, WindowInsets insets) {
        if (insets.isVisible(WindowInsets.Type.ime())) {
            displaySearchWideScreen();
            this.mHandler.post(new Runnable() { // from class: com.android.car.ui.toolbar.-$$Lambda$SearchView$ejP5xb3mVt3hnAc1mk9YpSCVzMo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$installWindowInsetsListener$4$SearchView();
                }
            });
        }
        return v.onApplyWindowInsets(insets);
    }

    public /* synthetic */ void lambda$installWindowInsetsListener$4$SearchView() {
        SurfaceControlViewHost surfaceControlViewHost = this.mSurfaceControlViewHost;
        if (surfaceControlViewHost != null && this.mWideScreenImeContentAreaView != null && surfaceControlViewHost.getView() == null) {
            this.mSurfaceControlViewHost.setView(this.mWideScreenImeContentAreaView, this.mSurfaceWidth, this.mSurfaceHeight);
        }
    }

    void setViewToImeWideScreenSurface(View view) {
        SurfaceControlViewHost surfaceControlViewHost;
        if (view == null && (surfaceControlViewHost = this.mSurfaceControlViewHost) != null) {
            surfaceControlViewHost.release();
        }
        if (view != null && view.getParent() != null) {
            throw new IllegalStateException("view should not have a parent");
        }
        this.mWideScreenImeContentAreaView = view;
    }

    private boolean isEnter(KeyEvent event) {
        if (event == null) {
            return false;
        }
        int keyCode = event.getKeyCode();
        boolean result = keyCode == 66 || keyCode == 160 || keyCode == 84;
        return result;
    }

    private void notifyQuerySubmit() {
        this.mSearchText.clearFocus();
        for (Toolbar.OnSearchCompletedListener listener : this.mSearchCompletedListeners) {
            listener.onSearchCompleted();
        }
    }

    @Override // android.view.View
    public void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        boolean isShown = isShown();
        if (isShown && !this.mWasShown) {
            boolean hasQuery = this.mSearchText.getText().length() > 0;
            this.mCloseIcon.setVisibility(hasQuery ? 0 : 8);
            this.mSearchText.requestFocus();
            this.mInputMethodManager.showSoftInput(this.mSearchText, 0);
        }
        this.mWasShown = isShown;
    }

    public void setSearchListeners(Set<Toolbar.OnSearchListener> listeners) {
        this.mSearchListeners = listeners;
    }

    public void setSearchCompletedListeners(Set<Toolbar.OnSearchCompletedListener> listeners) {
        this.mSearchCompletedListeners = listeners;
    }

    public void setSearchItemsForWideScreen(List<? extends CarUiImeSearchListItem> searchItems) {
        this.mWideScreenSearchItemList = searchItems != null ? new ArrayList(searchItems) : null;
        displaySearchWideScreen();
    }

    private void displaySearchWideScreen() {
        String url = SearchResultsProvider.CONTENT + getContext().getPackageName() + SearchResultsProvider.SEARCH_RESULTS_PROVIDER + "/search_results";
        this.mContentUri = Uri.parse(url);
        this.mIdToListItem.clear();
        getContext().getContentResolver().delete(this.mContentUri, null, null);
        if (this.mWideScreenImeContentAreaView != null) {
            return;
        }
        List<? extends CarUiImeSearchListItem> list = this.mWideScreenSearchItemList;
        if (list == null) {
            this.mInputMethodManager.sendAppPrivateCommand(this.mSearchText, CarUiImeWideScreenController.WIDE_SCREEN_ACTION, null);
            return;
        }
        int id = 0;
        for (CarUiImeSearchListItem item : list) {
            ContentValues values = new ContentValues();
            String idString = String.valueOf(id);
            values.put(SearchResultsProvider.ITEM_ID, Integer.valueOf(id));
            values.put(SearchResultsProvider.SECONDARY_IMAGE_ID, Integer.valueOf(id));
            BitmapDrawable icon = (BitmapDrawable) item.getIcon();
            values.put(SearchResultsProvider.PRIMARY_IMAGE_BLOB, icon != null ? bitmapToByteArray(icon.getBitmap()) : null);
            BitmapDrawable supplementalIcon = (BitmapDrawable) item.getSupplementalIcon();
            values.put(SearchResultsProvider.SECONDARY_IMAGE_BLOB, supplementalIcon != null ? bitmapToByteArray(supplementalIcon.getBitmap()) : null);
            values.put(SearchResultsProvider.TITLE, item.getTitle() != null ? item.getTitle().toString() : null);
            values.put(SearchResultsProvider.SUBTITLE, item.getBody() != null ? item.getBody().toString() : null);
            getContext().getContentResolver().insert(this.mContentUri, values);
            this.mIdToListItem.put(idString, item);
            id++;
        }
        this.mInputMethodManager.sendAppPrivateCommand(this.mSearchText, CarUiImeWideScreenController.WIDE_SCREEN_SEARCH_RESULTS, new Bundle());
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        Parcel parcel = Parcel.obtain();
        bitmap.writeToParcel(parcel, 0);
        byte[] bytes = parcel.marshall();
        parcel.recycle();
        return bytes;
    }

    public void setHint(CharSequence hint) {
        this.mSearchText.setHint(hint);
    }

    public void setIcon(Drawable d) {
        if (d == null) {
            this.mIcon.setImageResource(R.drawable.car_ui_icon_search);
        } else {
            this.mIcon.setImageDrawable(d);
        }
    }

    public void setIcon(int resId) {
        if (resId == 0) {
            this.mIcon.setImageResource(R.drawable.car_ui_icon_search);
        } else {
            this.mIcon.setImageResource(resId);
        }
    }

    public void setPlainText(boolean plainText) {
        if (plainText != this.mIsPlainText) {
            if (plainText) {
                this.mSearchText.setPaddingRelative(this.mStartPaddingWithoutIcon, 0, this.mEndPadding, 0);
                this.mSearchText.setImeOptions(6);
                this.mIcon.setVisibility(8);
            } else {
                this.mSearchText.setPaddingRelative(this.mStartPadding, 0, this.mEndPadding, 0);
                this.mSearchText.setImeOptions(3);
                this.mIcon.setVisibility(0);
            }
            this.mIsPlainText = plainText;
            this.mInputMethodManager.restartInput(this.mSearchText);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSearch(String query) {
        this.mCloseIcon.setVisibility(TextUtils.isEmpty(query) ? 8 : 0);
        for (Toolbar.OnSearchListener listener : this.mSearchListeners) {
            listener.onSearch(query);
        }
    }

    public void setSearchQuery(String query) {
        this.mSearchText.setText(query);
        EditText editText = this.mSearchText;
        editText.setSelection(editText.getText().length());
    }

    private class SearchViewImeCallback implements PrivateImeCommandCallback {
        private SearchViewImeCallback() {
        }

        @Override // com.android.car.ui.toolbar.PrivateImeCommandCallback
        public void onItemClicked(String itemId) {
            CarUiContentListItem.OnClickListener listener;
            CarUiImeSearchListItem item = (CarUiImeSearchListItem) SearchView.this.mIdToListItem.get(itemId);
            if (item != null && (listener = item.getOnClickListener()) != null) {
                listener.onClick(item);
            }
        }

        @Override // com.android.car.ui.toolbar.PrivateImeCommandCallback
        public void onSecondaryImageClicked(String secondaryImageId) {
            CarUiContentListItem.OnClickListener listener;
            CarUiImeSearchListItem item = (CarUiImeSearchListItem) SearchView.this.mIdToListItem.get(secondaryImageId);
            if (item != null && (listener = item.getSupplementalIconOnClickListener()) != null) {
                listener.onClick(item);
            }
        }

        @Override // com.android.car.ui.toolbar.PrivateImeCommandCallback
        public void onSurfaceInfo(int displayId, IBinder binder, int height, int width) {
            if (Build.VERSION.SDK_INT < 30 || SearchView.this.mWideScreenImeContentAreaView == null) {
                return;
            }
            DisplayManager dm = (DisplayManager) SearchView.this.getContext().getSystemService("display");
            Display display = dm.getDisplay(displayId);
            if (SearchView.this.mSurfaceControlViewHost != null) {
                SearchView.this.mSurfaceControlViewHost.release();
            }
            SearchView.this.mSurfaceControlViewHost = new SurfaceControlViewHost(SearchView.this.getContext(), display, binder);
            SearchView.this.mSurfaceHeight = height;
            SearchView.this.mSurfaceWidth = width;
            Bundle bundle = new Bundle();
            bundle.putParcelable(CarUiImeWideScreenController.CONTENT_AREA_SURFACE_PACKAGE, SearchView.this.mSurfaceControlViewHost.getSurfacePackage());
            SearchView.this.mInputMethodManager.sendAppPrivateCommand(SearchView.this.mSearchText, CarUiImeWideScreenController.WIDE_SCREEN_ACTION, bundle);
        }

        @Override // com.android.car.ui.toolbar.PrivateImeCommandCallback
        public void onPostLoadSearchResults() {
            if (SearchView.this.mContentUri != null) {
                SearchView.this.getContext().getContentResolver().delete(SearchView.this.mContentUri, null, null);
            }
        }
    }
}
