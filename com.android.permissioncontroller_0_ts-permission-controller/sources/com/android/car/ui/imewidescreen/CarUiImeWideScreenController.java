package com.android.car.ui.imewidescreen;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.ExtractEditText;
import android.inputmethodservice.InputMethodService;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceControlViewHost;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R;
import com.android.car.ui.core.SearchResultsProvider;
import com.android.car.ui.recyclerview.CarUiContentListItem;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import com.android.car.ui.utils.CarUiUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes7.dex */
public class CarUiImeWideScreenController {
    public static final String ADD_DESC_TITLE_TO_CONTENT_AREA = "add_desc_title_to_content_area";
    public static final String ADD_DESC_TO_CONTENT_AREA = "add_desc_to_content_area";
    public static final String ADD_ERROR_DESC_TO_INPUT_AREA = "add_error_desc_to_input_area";
    public static final String CONTENT_AREA_SURFACE_DISPLAY_ID = "content_area_surface_display_id";
    public static final String CONTENT_AREA_SURFACE_HEIGHT = "content_area_surface_height";
    public static final String CONTENT_AREA_SURFACE_HOST_TOKEN = "content_area_surface_host_token";
    public static final String CONTENT_AREA_SURFACE_PACKAGE = "content_area_surface_package";
    public static final String CONTENT_AREA_SURFACE_WIDTH = "content_area_surface_width";
    private static final String NOT_ASTERISK_OR_CAPTURED_ASTERISK = "[^*]+|(\\*)";
    public static final String REQUEST_RENDER_CONTENT_AREA = "request_render_content_area";
    public static final String SEARCH_RESULT_ITEM_ID_LIST = "search_result_item_id_list";
    public static final String SEARCH_RESULT_SUPPLEMENTAL_ICON_ID_LIST = "search_result_supplemental_icon_id_list";
    private static final String TAG = "ImeWideScreenController";
    public static final String WIDE_SCREEN_ACTION = "automotive_wide_screen";
    public static final String WIDE_SCREEN_CLEAR_DATA_ACTION = "automotive_wide_screen_clear_data";
    public static final String WIDE_SCREEN_EXTRACTED_TEXT_ICON_RES_ID = "extracted_text_icon_res_id";
    public static final String WIDE_SCREEN_POST_LOAD_SEARCH_RESULTS_ACTION = "automotive_wide_screen_post_load_search_results";
    public static final String WIDE_SCREEN_SEARCH_RESULTS = "wide_screen_search_results";
    private boolean mAllowAppToHideContentArea;
    private ArrayList<CarUiContentListItem> mAutomotiveSearchItems;
    private View mContentAreaAutomotive;
    private SurfaceView mContentAreaSurfaceView;
    private final Context mContext;
    private View mExtractActionAutomotive;
    private ExtractEditText mExtractEditText;
    private boolean mExtractViewHidden;
    private View mFullscreenArea;
    private boolean mImeRendersAllContent = true;
    private InputConnection mInputConnection;
    private EditorInfo mInputEditorInfo;
    private FrameLayout mInputExtractEditTextContainer;
    private FrameLayout mInputFrame;
    private final InputMethodService mInputMethodService;
    private boolean mIsExtractIconProvidedByApp;
    private RecyclerView mRecyclerView;
    private View mRootView;
    private ImageView mWideScreenClearData;
    private TextView mWideScreenDescription;
    private TextView mWideScreenDescriptionTitle;
    private ImageView mWideScreenErrorImage;
    private TextView mWideScreenErrorMessage;
    private ImageView mWideScreenExtractedTextIcon;

    public CarUiImeWideScreenController(Context context, InputMethodService ims) {
        this.mContext = context;
        this.mInputMethodService = ims;
    }

    public View createWideScreenImeView(View inputView) {
        if (!isWideScreenMode()) {
            return inputView;
        }
        View viewInflate = View.inflate(this.mContext, R.layout.car_ui_ims_wide_screen_input_view, null);
        this.mRootView = viewInflate;
        FrameLayout frameLayout = (FrameLayout) viewInflate.requireViewById(R.id.car_ui_wideScreenInputArea);
        this.mInputFrame = frameLayout;
        frameLayout.addView(inputView, new FrameLayout.LayoutParams(-1, -2));
        this.mAllowAppToHideContentArea = this.mContext.getResources().getBoolean(R.bool.car_ui_ime_wide_screen_allow_app_hide_content_area);
        SurfaceView surfaceView = (SurfaceView) this.mRootView.requireViewById(R.id.car_ui_ime_surface);
        this.mContentAreaSurfaceView = surfaceView;
        surfaceView.setZOrderOnTop(true);
        this.mWideScreenDescriptionTitle = (TextView) this.mRootView.requireViewById(R.id.car_ui_wideScreenDescriptionTitle);
        this.mWideScreenDescription = (TextView) this.mRootView.requireViewById(R.id.car_ui_wideScreenDescription);
        this.mExtractActionAutomotive = this.mRootView.findViewById(R.id.car_ui_inputExtractActionAutomotive);
        this.mContentAreaAutomotive = this.mRootView.requireViewById(R.id.car_ui_contentAreaAutomotive);
        this.mRecyclerView = (RecyclerView) this.mRootView.requireViewById(R.id.car_ui_wideScreenSearchResultList);
        this.mWideScreenErrorMessage = (TextView) this.mRootView.requireViewById(R.id.car_ui_wideScreenErrorMessage);
        this.mWideScreenExtractedTextIcon = (ImageView) this.mRootView.findViewById(R.id.car_ui_wideScreenExtractedTextIcon);
        this.mWideScreenErrorImage = (ImageView) this.mRootView.requireViewById(R.id.car_ui_wideScreenError);
        this.mWideScreenClearData = (ImageView) this.mRootView.requireViewById(R.id.car_ui_wideScreenClearData);
        this.mFullscreenArea = this.mRootView.requireViewById(R.id.car_ui_fullscreenArea);
        this.mInputExtractEditTextContainer = (FrameLayout) this.mRootView.requireViewById(R.id.car_ui_inputExtractEditTextContainer);
        this.mWideScreenClearData.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.imewidescreen.-$$Lambda$CarUiImeWideScreenController$NuYiQhvBBrGVIiINoRQLAsrPPuY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$createWideScreenImeView$0$CarUiImeWideScreenController(view);
            }
        });
        this.mExtractViewHidden = false;
        return this.mRootView;
    }

    public /* synthetic */ void lambda$createWideScreenImeView$0$CarUiImeWideScreenController(View v) {
        this.mInputConnection.performPrivateCommand(WIDE_SCREEN_CLEAR_DATA_ACTION, null);
    }

    public void onComputeInsets(InputMethodService.Insets outInsets) {
        if (!isWideScreenMode()) {
            return;
        }
        Rect tempRect = new Rect();
        int[] tempLocation = new int[2];
        int height = this.mInputMethodService.getWindow().getWindow().getDecorView().getHeight();
        outInsets.visibleTopInsets = height;
        outInsets.contentTopInsets = height;
        if (this.mImeRendersAllContent) {
            outInsets.touchableRegion.setEmpty();
            outInsets.touchableInsets = 0;
        } else {
            this.mInputFrame.getLocationOnScreen(tempLocation);
            tempRect.set(0, 0, tempLocation[0] + this.mInputFrame.getWidth(), tempLocation[1] + this.mInputFrame.getHeight());
            outInsets.touchableRegion.set(tempRect);
            outInsets.touchableInsets = 3;
        }
    }

    public void onAppPrivateCommand(String action, Bundle data) {
        EditorInfo editorInfo;
        if (!isWideScreenMode()) {
            return;
        }
        resetAutomotiveWideScreenViews();
        if (data == null) {
            return;
        }
        if (this.mAllowAppToHideContentArea || ((editorInfo = this.mInputEditorInfo) != null && isPackageAuthorized(editorInfo.packageName))) {
            boolean z = data.getBoolean(REQUEST_RENDER_CONTENT_AREA, true);
            this.mImeRendersAllContent = z;
            if (!z) {
                this.mContentAreaAutomotive.setVisibility(8);
            } else {
                this.mContentAreaAutomotive.setVisibility(0);
            }
        }
        if (data.getParcelable(CONTENT_AREA_SURFACE_PACKAGE) != null && Build.VERSION.SDK_INT >= 30) {
            SurfaceControlViewHost.SurfacePackage surfacePackage = (SurfaceControlViewHost.SurfacePackage) data.getParcelable(CONTENT_AREA_SURFACE_PACKAGE);
            this.mContentAreaSurfaceView.setChildSurfacePackage(surfacePackage);
            this.mContentAreaSurfaceView.setVisibility(0);
            this.mContentAreaAutomotive.setVisibility(8);
        }
        String discTitle = data.getString(ADD_DESC_TITLE_TO_CONTENT_AREA);
        if (!TextUtils.isEmpty(discTitle)) {
            this.mWideScreenDescriptionTitle.setText(discTitle);
            this.mWideScreenDescriptionTitle.setVisibility(0);
            this.mContentAreaAutomotive.setBackground(this.mContext.getDrawable(R.drawable.car_ui_ime_wide_screen_background));
        }
        String disc = data.getString(ADD_DESC_TO_CONTENT_AREA);
        if (!TextUtils.isEmpty(disc)) {
            this.mWideScreenDescription.setText(disc);
            this.mWideScreenDescription.setVisibility(0);
            this.mContentAreaAutomotive.setBackground(this.mContext.getDrawable(R.drawable.car_ui_ime_wide_screen_background));
        }
        String errorMessage = data.getString(ADD_ERROR_DESC_TO_INPUT_AREA);
        if (!TextUtils.isEmpty(errorMessage)) {
            this.mWideScreenErrorMessage.setVisibility(0);
            this.mWideScreenClearData.setVisibility(8);
            this.mWideScreenErrorImage.setVisibility(0);
            setExtractedEditTextBackground(R.drawable.car_ui_ime_wide_screen_input_area_tint_error_color);
            this.mWideScreenErrorMessage.setText(errorMessage);
            this.mContentAreaAutomotive.setBackground(this.mContext.getDrawable(R.drawable.car_ui_ime_wide_screen_background));
        }
        if (TextUtils.isEmpty(errorMessage)) {
            this.mWideScreenErrorMessage.setVisibility(4);
            this.mWideScreenErrorMessage.setText("");
            this.mWideScreenClearData.setVisibility(0);
            this.mWideScreenErrorImage.setVisibility(8);
            setExtractedEditTextBackground(R.drawable.car_ui_ime_wide_screen_input_area_tint_color);
        }
        int extractedTextIcon = data.getInt(WIDE_SCREEN_EXTRACTED_TEXT_ICON_RES_ID);
        if (extractedTextIcon != 0) {
            setWideScreenExtractedIcon(extractedTextIcon);
        }
        if (WIDE_SCREEN_SEARCH_RESULTS.equals(action)) {
            loadSearchItems();
        }
        View view = this.mExtractActionAutomotive;
        if (view != null) {
            view.setVisibility(0);
        }
        if (this.mAutomotiveSearchItems != null) {
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
            this.mRecyclerView.setVerticalScrollBarEnabled(true);
            this.mRecyclerView.setVerticalScrollbarPosition(1);
            this.mRecyclerView.setVisibility(0);
            this.mRecyclerView.setAdapter(new CarUiListItemAdapter(this.mAutomotiveSearchItems));
            this.mContentAreaAutomotive.setBackground(this.mContext.getDrawable(R.drawable.car_ui_ime_wide_screen_background));
            View view2 = this.mExtractActionAutomotive;
            if (view2 != null) {
                view2.setVisibility(8);
            }
        }
    }

    private void loadSearchItems() {
        if (this.mInputEditorInfo == null) {
            Log.w(TAG, "Result can't be loaded, input InputEditorInfo not available ");
            return;
        }
        String url = SearchResultsProvider.CONTENT + getPackageName(this.mInputEditorInfo) + SearchResultsProvider.SEARCH_RESULTS_PROVIDER;
        Uri contentUrl = Uri.parse(url);
        ContentResolver cr = this.mContext.getContentResolver();
        final Cursor c = cr.query(contentUrl, null, null, null, null);
        try {
            this.mAutomotiveSearchItems = new ArrayList<>();
            if (c != null && c.moveToFirst()) {
                do {
                    CarUiContentListItem searchItem = new CarUiContentListItem(CarUiContentListItem.Action.ICON);
                    final String itemId = c.getString(c.getColumnIndex(SearchResultsProvider.ITEM_ID));
                    searchItem.setOnItemClickedListener(new CarUiContentListItem.OnClickListener() { // from class: com.android.car.ui.imewidescreen.-$$Lambda$CarUiImeWideScreenController$Jh9wR8ZB7Fy1tmqvGhRsuEBXwnI
                        @Override // com.android.car.ui.recyclerview.CarUiContentListItem.OnClickListener
                        public final void onClick(CarUiContentListItem carUiContentListItem) {
                            this.f$0.lambda$loadSearchItems$1$CarUiImeWideScreenController(itemId, carUiContentListItem);
                        }
                    });
                    searchItem.setTitle(c.getString(c.getColumnIndex(SearchResultsProvider.TITLE)));
                    searchItem.setBody(c.getString(c.getColumnIndex(SearchResultsProvider.SUBTITLE)));
                    searchItem.setPrimaryIconType(CarUiContentListItem.IconType.CONTENT);
                    byte[] primaryBlob = c.getBlob(c.getColumnIndex(SearchResultsProvider.PRIMARY_IMAGE_BLOB));
                    if (primaryBlob != null) {
                        Bitmap primaryBitmap = (Bitmap) Bitmap.CREATOR.createFromParcel(byteArrayToParcel(primaryBlob));
                        searchItem.setIcon(new BitmapDrawable(this.mContext.getResources(), primaryBitmap));
                    }
                    byte[] secondaryBlob = c.getBlob(c.getColumnIndex(SearchResultsProvider.SECONDARY_IMAGE_BLOB));
                    if (secondaryBlob != null) {
                        Bitmap secondaryBitmap = (Bitmap) Bitmap.CREATOR.createFromParcel(byteArrayToParcel(secondaryBlob));
                        searchItem.setSupplementalIcon(new BitmapDrawable(this.mContext.getResources(), secondaryBitmap), new CarUiContentListItem.OnClickListener() { // from class: com.android.car.ui.imewidescreen.-$$Lambda$CarUiImeWideScreenController$URG3BTrIPsLHXO-m1EOYy7L55sY
                            @Override // com.android.car.ui.recyclerview.CarUiContentListItem.OnClickListener
                            public final void onClick(CarUiContentListItem carUiContentListItem) {
                                this.f$0.lambda$loadSearchItems$2$CarUiImeWideScreenController(c, carUiContentListItem);
                            }
                        });
                    }
                    this.mAutomotiveSearchItems.add(searchItem);
                } while (c.moveToNext());
            }
            if (c != null) {
                c.close();
            }
            this.mInputConnection.performPrivateCommand(WIDE_SCREEN_POST_LOAD_SEARCH_RESULTS_ACTION, null);
        } catch (Throwable th) {
            if (c != null) {
                try {
                    c.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public /* synthetic */ void lambda$loadSearchItems$1$CarUiImeWideScreenController(String itemId, CarUiContentListItem v) {
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_RESULT_ITEM_ID_LIST, itemId);
        this.mInputConnection.performPrivateCommand(WIDE_SCREEN_ACTION, bundle);
    }

    public /* synthetic */ void lambda$loadSearchItems$2$CarUiImeWideScreenController(Cursor c, CarUiContentListItem v) {
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_RESULT_SUPPLEMENTAL_ICON_ID_LIST, c.getString(c.getColumnIndex(SearchResultsProvider.SECONDARY_IMAGE_ID)));
        this.mInputConnection.performPrivateCommand(WIDE_SCREEN_ACTION, bundle);
    }

    private static Parcel byteArrayToParcel(byte[] bytes) {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(bytes, 0, bytes.length);
        parcel.setDataPosition(0);
        return parcel;
    }

    public boolean onEvaluateFullscreenMode(boolean isFullScreen) {
        return isWideScreenMode() || isFullScreen;
    }

    public void onStartInputView(EditorInfo editorInfo, InputConnection inputConnection, CharSequence textForImeAction) {
        if (!isWideScreenMode()) {
            return;
        }
        this.mInputEditorInfo = editorInfo;
        this.mInputConnection = inputConnection;
        View header = this.mRootView.requireViewById(R.id.car_ui_imeWideScreenInputArea);
        boolean hasLabel = false;
        header.setVisibility(0);
        if (!this.mExtractViewHidden) {
            this.mFullscreenArea.setVisibility(0);
        } else {
            this.mFullscreenArea.setVisibility(4);
        }
        ExtractEditText extractEditText = (ExtractEditText) this.mRootView.getRootView().requireViewById(android.R.id.inputExtractEditText);
        this.mExtractEditText = extractEditText;
        extractEditText.setPadding(this.mContext.getResources().getDimensionPixelSize(R.dimen.car_ui_ime_wide_screen_input_edit_text_padding_left), 0, this.mContext.getResources().getDimensionPixelSize(R.dimen.car_ui_ime_wide_screen_input_edit_text_padding_right), 0);
        this.mExtractEditText.setTextSize(this.mContext.getResources().getDimensionPixelSize(R.dimen.car_ui_ime_wide_screen_input_edit_text_size));
        this.mExtractEditText.setGravity(8388627);
        ViewGroup parent = (ViewGroup) this.mExtractEditText.getParent();
        parent.removeViewInLayout(this.mExtractEditText);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
        this.mInputExtractEditTextContainer.addView(this.mExtractEditText, params);
        ImageView close = (ImageView) this.mRootView.findViewById(R.id.car_ui_closeKeyboard);
        if (close != null) {
            close.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.imewidescreen.-$$Lambda$CarUiImeWideScreenController$qMgQ71zkEIzg3mLBz_vqIqhZ9EE
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onStartInputView$3$CarUiImeWideScreenController(view);
                }
            });
        }
        if (!this.mIsExtractIconProvidedByApp) {
            setWideScreenExtractedIcon(0);
        }
        boolean hasAction = (this.mInputEditorInfo.imeOptions & 255) != 1;
        boolean hasInputType = this.mInputEditorInfo.inputType != 0;
        boolean hasNoAccessoryAction = (this.mInputEditorInfo.imeOptions & 536870912) == 0;
        if (this.mInputEditorInfo.actionLabel != null || (hasAction && hasNoAccessoryAction && hasInputType)) {
            hasLabel = true;
        }
        if (hasLabel) {
            intiExtractAction(textForImeAction);
        }
        if (this.mContentAreaSurfaceView.getVisibility() == 8) {
            sendSurfaceInfo();
        }
    }

    public /* synthetic */ void lambda$onStartInputView$3$CarUiImeWideScreenController(View v) {
        this.mInputMethodService.requestHideSelf(0);
    }

    private void sendSurfaceInfo() {
        EditorInfo editorInfo;
        if (!this.mAllowAppToHideContentArea && this.mContentAreaSurfaceView.getDisplay() == null && ((editorInfo = this.mInputEditorInfo) == null || !isPackageAuthorized(editorInfo.packageName))) {
            return;
        }
        int displayId = this.mContentAreaSurfaceView.getDisplay().getDisplayId();
        IBinder hostToken = this.mContentAreaSurfaceView.getHostToken();
        Bundle bundle = new Bundle();
        bundle.putBinder(CONTENT_AREA_SURFACE_HOST_TOKEN, hostToken);
        bundle.putInt(CONTENT_AREA_SURFACE_DISPLAY_ID, displayId);
        bundle.putInt(CONTENT_AREA_SURFACE_HEIGHT, this.mContentAreaSurfaceView.getHeight() + getNavBarHeight());
        bundle.putInt(CONTENT_AREA_SURFACE_WIDTH, this.mContentAreaSurfaceView.getWidth());
        this.mInputConnection.performPrivateCommand(WIDE_SCREEN_ACTION, bundle);
    }

    private boolean isPackageAuthorized(String packageName) {
        String[] packages = this.mContext.getResources().getStringArray(R.array.car_ui_ime_wide_screen_allowed_package_list);
        PackageInfo packageInfo = getPackageInfo(this.mContext, packageName);
        if (packageInfo != null && (packageInfo.applicationInfo.flags & 129) != 0) {
            return true;
        }
        for (String pattern : packages) {
            String regex = createRegexFromGlob(pattern);
            if (packageName.matches(regex)) {
                return true;
            }
        }
        return false;
    }

    private static PackageInfo getPackageInfo(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "package not found: " + packageName);
            return null;
        }
    }

    private static String createRegexFromGlob(String glob) {
        Pattern reg = Pattern.compile(NOT_ASTERISK_OR_CAPTURED_ASTERISK);
        Matcher m = reg.matcher(glob);
        StringBuffer b = new StringBuffer();
        while (m.find()) {
            if (m.group(1) != null) {
                m.appendReplacement(b, ".*");
            } else {
                m.appendReplacement(b, Matcher.quoteReplacement(m.group(0)));
            }
        }
        m.appendTail(b);
        return b.toString();
    }

    private int getNavBarHeight() {
        Resources resources = this.mContext.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", Utils.OS_PKG);
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public void setExtractViewShown(boolean shown) {
        if (!isWideScreenMode() || this.mExtractViewHidden == (!shown)) {
            return;
        }
        boolean z = !shown;
        this.mExtractViewHidden = z;
        if (z) {
            this.mFullscreenArea.setVisibility(4);
        } else {
            this.mFullscreenArea.setVisibility(0);
        }
    }

    private void intiExtractAction(CharSequence textForImeAction) {
        if (this.mExtractActionAutomotive == null) {
            return;
        }
        if (this.mInputEditorInfo.actionLabel != null) {
            ((TextView) this.mExtractActionAutomotive).setText(this.mInputEditorInfo.actionLabel);
        } else {
            ((TextView) this.mExtractActionAutomotive).setText(textForImeAction);
        }
        this.mExtractActionAutomotive.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.imewidescreen.-$$Lambda$CarUiImeWideScreenController$slBLpgKucYvgvKVxoQ3D31f_waE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$intiExtractAction$4$CarUiImeWideScreenController(view);
            }
        });
    }

    public /* synthetic */ void lambda$intiExtractAction$4$CarUiImeWideScreenController(View v) {
        EditorInfo editorInfo = this.mInputEditorInfo;
        InputConnection inputConnection = this.mInputConnection;
        if (editorInfo == null || inputConnection == null) {
            return;
        }
        if (editorInfo.actionId != 0) {
            inputConnection.performEditorAction(editorInfo.actionId);
        } else if ((editorInfo.imeOptions & 255) != 1) {
            inputConnection.performEditorAction(editorInfo.imeOptions & 255);
        }
    }

    private void setExtractedEditTextBackground(int drawableResId) {
        this.mExtractEditText.setBackgroundTintList(this.mContext.getColorStateList(drawableResId));
    }

    void setExtractEditText(ExtractEditText editText) {
        this.mExtractEditText = editText;
    }

    void setEditorInfo(EditorInfo editorInfo) {
        this.mInputEditorInfo = editorInfo;
    }

    String getPackageName(EditorInfo editorInfo) {
        return editorInfo.packageName;
    }

    private void setWideScreenExtractedIcon(int iconResId) {
        ImageView imageView;
        EditorInfo editorInfo = this.mInputEditorInfo;
        if (editorInfo == null || (imageView = this.mWideScreenExtractedTextIcon) == null) {
            return;
        }
        try {
            if (iconResId == 0) {
                imageView.setImageDrawable(this.mContext.getPackageManager().getApplicationIcon(this.mInputEditorInfo.packageName));
            } else {
                this.mIsExtractIconProvidedByApp = true;
                imageView.setImageDrawable(this.mContext.createPackageContext(editorInfo.packageName, 0).getDrawable(iconResId));
            }
            this.mWideScreenExtractedTextIcon.setVisibility(0);
        } catch (PackageManager.NameNotFoundException ex) {
            Log.w(TAG, "setWideScreenExtractedIcon: package name not found ", ex);
            this.mWideScreenExtractedTextIcon.setVisibility(8);
        } catch (Resources.NotFoundException ex2) {
            Log.w(TAG, "setWideScreenExtractedIcon: resource not found with id " + iconResId, ex2);
            this.mWideScreenExtractedTextIcon.setVisibility(8);
        }
    }

    public void onFinishInputView() {
        if (!isWideScreenMode()) {
            return;
        }
        resetAutomotiveWideScreenViews();
    }

    private void resetAutomotiveWideScreenViews() {
        this.mWideScreenDescriptionTitle.setVisibility(8);
        this.mContentAreaSurfaceView.setVisibility(8);
        this.mWideScreenErrorMessage.setVisibility(8);
        this.mRecyclerView.setVisibility(8);
        this.mWideScreenDescription.setVisibility(8);
        this.mFullscreenArea.setVisibility(0);
        ImageView imageView = this.mWideScreenExtractedTextIcon;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        this.mWideScreenClearData.setVisibility(0);
        this.mWideScreenErrorImage.setVisibility(8);
        View view = this.mExtractActionAutomotive;
        if (view != null) {
            view.setVisibility(8);
        }
        this.mContentAreaAutomotive.setVisibility(0);
        this.mContentAreaAutomotive.setBackground(this.mContext.getDrawable(R.drawable.car_ui_ime_wide_screen_no_content_background));
        setExtractedEditTextBackground(R.drawable.car_ui_ime_wide_screen_input_area_tint_color);
        this.mImeRendersAllContent = true;
        this.mIsExtractIconProvidedByApp = false;
        this.mExtractViewHidden = false;
        this.mAutomotiveSearchItems = null;
    }

    public boolean isWideScreenMode() {
        return CarUiUtils.getBooleanSystemProperty(this.mContext.getResources(), R.string.car_ui_ime_wide_screen_system_property_name, false);
    }

    private Drawable loadDrawableFromPackage(int resId) {
        try {
            if (this.mInputEditorInfo != null) {
                return this.mContext.createPackageContext(this.mInputEditorInfo.packageName, 0).getDrawable(resId);
            }
            return null;
        } catch (PackageManager.NameNotFoundException ex) {
            Log.e(TAG, "loadDrawableFromPackage: package name not found: ", ex);
            return null;
        } catch (Resources.NotFoundException ex2) {
            Log.w(TAG, "loadDrawableFromPackage: resource not found with id " + resId, ex2);
            return null;
        }
    }
}
