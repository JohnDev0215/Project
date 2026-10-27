package com.android.launcher3;

import android.appwidget.AppWidgetHostView;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Process;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.Display;
import android.view.WindowManager;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.config.FeatureFlags;
import com.android.launcher3.graphics.IconShape;
import com.android.launcher3.util.ConfigMonitor;
import com.android.launcher3.util.IntArray;
import com.android.launcher3.util.MainThreadInitializedObject;
import com.android.launcher3.util.PackageManagerHelper;
import com.android.launcher3.util.Themes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes4.dex */
public class InvariantDeviceProfile {
    public static final int CHANGE_FLAG_GRID = 1;
    public static final int CHANGE_FLAG_ICON_PARAMS = 2;
    private static final float ICON_SIZE_DEFINED_IN_APP_DP = 48.0f;
    public static final String KEY_ICON_PATH_REF = "pref_icon_shape_path";
    private static final String KEY_IDP_GRID_NAME = "idp_grid_name";
    private static final float KNEARESTNEIGHBOR = 3.0f;
    public static final String TAG = "IDP";
    private static final float WEIGHT_EFFICIENT = 100000.0f;
    private static final float WEIGHT_POWER = 5.0f;
    public int defaultLayoutId;
    public Point defaultWallpaperSize;
    public Rect defaultWidgetPadding;
    int demoModeLayoutId;
    public int fillResIconDpi;
    public int iconBitmapSize;
    public String iconShapePath;
    public float iconSize;
    public float iconTextSize;
    public float landscapeIconSize;
    public DeviceProfile landscapeProfile;
    private final ArrayList<OnIDPChangeListener> mChangeListeners = new ArrayList<>();
    private ConfigMonitor mConfigMonitor;
    private SparseArray<TypedValue> mExtraAttrs;
    private OverlayMonitor mOverlayMonitor;
    public int numColumns;
    public int numFolderColumns;
    public int numFolderRows;
    public int numHotseatIcons;
    public int numRows;
    public DeviceProfile portraitProfile;
    public static final MainThreadInitializedObject<InvariantDeviceProfile> INSTANCE = new MainThreadInitializedObject<>(new MainThreadInitializedObject.ObjectProvider() { // from class: com.android.launcher3.-$$Lambda$InvariantDeviceProfile$DNcXzmawjoq65q3wgQi9M48DryY
        @Override // com.android.launcher3.util.MainThreadInitializedObject.ObjectProvider
        public final Object get(Context context) {
            return InvariantDeviceProfile.lambda$DNcXzmawjoq65q3wgQi9M48DryY(context);
        }
    });
    private static final int CONFIG_ICON_MASK_RES_ID = Resources.getSystem().getIdentifier("config_icon_mask", TypedValues.Custom.S_STRING, "android");

    public interface OnIDPChangeListener {
        void onIdpChanged(int changeFlags, InvariantDeviceProfile profile);
    }

    public static /* synthetic */ InvariantDeviceProfile lambda$DNcXzmawjoq65q3wgQi9M48DryY(Context context) {
        return new InvariantDeviceProfile(context);
    }

    private static float wallpaperTravelToScreenWidthRatio(int width, int height) {
        return ((width / height) * 0.30769226f) + 1.0076923f;
    }

    public InvariantDeviceProfile() {
    }

    private InvariantDeviceProfile(InvariantDeviceProfile p) {
        this.numRows = p.numRows;
        this.numColumns = p.numColumns;
        this.numFolderRows = p.numFolderRows;
        this.numFolderColumns = p.numFolderColumns;
        this.iconSize = p.iconSize;
        this.iconShapePath = p.iconShapePath;
        this.landscapeIconSize = p.landscapeIconSize;
        this.iconTextSize = p.iconTextSize;
        this.numHotseatIcons = p.numHotseatIcons;
        this.defaultLayoutId = p.defaultLayoutId;
        this.demoModeLayoutId = p.demoModeLayoutId;
        this.mExtraAttrs = p.mExtraAttrs;
        this.mOverlayMonitor = p.mOverlayMonitor;
    }

    private InvariantDeviceProfile(Context context) {
        initGrid(context, Utilities.getPrefs(context).getString(KEY_IDP_GRID_NAME, null));
        this.mConfigMonitor = new ConfigMonitor(context, FeatureFlags.APPLY_CONFIG_AT_RUNTIME.get() ? new $$Lambda$InvariantDeviceProfile$fPD006L49O1k5Z9EPrGeFQ9YZ9U(this) : new Consumer() { // from class: com.android.launcher3.-$$Lambda$InvariantDeviceProfile$Ke56I167dx6hRg1yoip81-pyx6o
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.killProcess((Context) obj);
            }
        });
        this.mOverlayMonitor = new OverlayMonitor(context);
    }

    public InvariantDeviceProfile(Context context, String gridName) {
        String strInitGrid = initGrid(context, gridName);
        if (strInitGrid == null || !strInitGrid.equals(gridName)) {
            throw new IllegalArgumentException("Unknown grid name");
        }
    }

    private static String getIconShapePath(Context context) {
        int i = CONFIG_ICON_MASK_RES_ID;
        if (i == 0) {
            Log.e(TAG, "Icon mask res identifier failed to retrieve.");
            return "";
        }
        return context.getResources().getString(i);
    }

    private String initGrid(Context context, String gridName) {
        Display defaultDisplay = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        Point point = new Point();
        Point point2 = new Point();
        defaultDisplay.getCurrentSizeRange(point, point2);
        ArrayList<DisplayOption> predefinedDeviceProfiles = getPredefinedDeviceProfiles(context, gridName);
        final float fDpiFromPx = Utilities.dpiFromPx(Math.min(point.x, point.y), displayMetrics);
        final float fDpiFromPx2 = Utilities.dpiFromPx(Math.min(point2.x, point2.y), displayMetrics);
        Log.d(TAG, "minWidthDps = " + fDpiFromPx + " ,minHeightDps = " + fDpiFromPx2);
        Collections.sort(predefinedDeviceProfiles, new Comparator() { // from class: com.android.launcher3.-$$Lambda$InvariantDeviceProfile$TNM5fXLIPY2VJusuCo12K6TlpG0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                float f = fDpiFromPx;
                float f2 = fDpiFromPx2;
                InvariantDeviceProfile.DisplayOption displayOption = (InvariantDeviceProfile.DisplayOption) obj;
                InvariantDeviceProfile.DisplayOption displayOption2 = (InvariantDeviceProfile.DisplayOption) obj2;
                return Float.compare(InvariantDeviceProfile.dist(f, f2, displayOption.minWidthDps, displayOption.minHeightDps), InvariantDeviceProfile.dist(f, f2, displayOption2.minWidthDps, displayOption2.minHeightDps));
            }
        });
        DisplayOption displayOptionInvDistWeightedInterpolate = invDistWeightedInterpolate(fDpiFromPx, fDpiFromPx2, predefinedDeviceProfiles);
        GridOption gridOption = predefinedDeviceProfiles.get(0).grid;
        this.numRows = gridOption.numRows;
        this.numColumns = gridOption.numColumns;
        this.numHotseatIcons = gridOption.numHotseatIcons;
        this.defaultLayoutId = gridOption.defaultLayoutId;
        this.demoModeLayoutId = gridOption.demoModeLayoutId;
        this.numFolderRows = gridOption.numFolderRows;
        this.numFolderColumns = gridOption.numFolderColumns;
        this.mExtraAttrs = gridOption.extraAttrs;
        if (!gridOption.name.equals(gridName)) {
            Utilities.getPrefs(context).edit().putString(KEY_IDP_GRID_NAME, gridOption.name).apply();
        }
        this.iconSize = displayOptionInvDistWeightedInterpolate.iconSize;
        this.iconShapePath = getIconShapePath(context);
        this.landscapeIconSize = displayOptionInvDistWeightedInterpolate.landscapeIconSize;
        this.iconBitmapSize = ResourceUtils.pxFromDp(this.iconSize, displayMetrics);
        this.iconTextSize = displayOptionInvDistWeightedInterpolate.iconTextSize;
        this.fillResIconDpi = getLauncherIconDensity(this.iconBitmapSize);
        applyPartnerDeviceProfileOverrides(context, displayMetrics);
        Point point3 = new Point();
        defaultDisplay.getRealSize(point3);
        int iMin = Math.min(point3.x, point3.y);
        int iMax = Math.max(point3.x, point3.y);
        this.landscapeProfile = new DeviceProfile(context, this, point, point2, iMax, iMin, true, false);
        this.portraitProfile = new DeviceProfile(context, this, point, point2, iMin, iMax, false, false);
        if (context.getResources().getConfiguration().smallestScreenWidthDp >= 720) {
            this.defaultWallpaperSize = new Point((int) (iMax * wallpaperTravelToScreenWidthRatio(iMax, iMin)), iMax);
        } else {
            this.defaultWallpaperSize = new Point(Math.max(iMin * 2, iMax), iMax);
        }
        this.defaultWidgetPadding = AppWidgetHostView.getDefaultPaddingForWidget(context, new ComponentName(context.getPackageName(), getClass().getName()), null);
        return gridOption.name;
    }

    public TypedValue getAttrValue(int attr) {
        SparseArray<TypedValue> sparseArray = this.mExtraAttrs;
        if (sparseArray == null) {
            return null;
        }
        return sparseArray.get(attr);
    }

    public void addOnChangeListener(OnIDPChangeListener listener) {
        this.mChangeListeners.add(listener);
    }

    public void removeOnChangeListener(OnIDPChangeListener listener) {
        this.mChangeListeners.remove(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void killProcess(Context context) {
        Log.e("ConfigMonitor", "restarting launcher");
        Process.killProcess(Process.myPid());
    }

    public void verifyConfigChangedInBackground(final Context context) {
        String string = Utilities.getDevicePrefs(context).getString(KEY_ICON_PATH_REF, "");
        if (string.isEmpty()) {
            Utilities.getDevicePrefs(context).edit().putString(KEY_ICON_PATH_REF, getIconShapePath(context)).apply();
        } else {
            if (string.equals(getIconShapePath(context))) {
                return;
            }
            Utilities.getDevicePrefs(context).edit().putString(KEY_ICON_PATH_REF, getIconShapePath(context)).apply();
            apply(context, 2);
        }
    }

    public void setCurrentGrid(Context context, String gridName) {
        final Context applicationContext = context.getApplicationContext();
        Utilities.getPrefs(applicationContext).edit().putString(KEY_IDP_GRID_NAME, gridName).apply();
        new MainThreadExecutor().execute(new Runnable() { // from class: com.android.launcher3.-$$Lambda$InvariantDeviceProfile$MLRkpMLy5cIviiOlvMMAYhX_CM8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setCurrentGrid$1$InvariantDeviceProfile(applicationContext);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onConfigChanged, reason: merged with bridge method [inline-methods] */
    public void lambda$setCurrentGrid$1$InvariantDeviceProfile(Context context) {
        InvariantDeviceProfile invariantDeviceProfile = new InvariantDeviceProfile(this);
        initGrid(context, null);
        int i = (this.numRows == invariantDeviceProfile.numRows && this.numColumns == invariantDeviceProfile.numColumns && this.numFolderColumns == invariantDeviceProfile.numFolderColumns && this.numFolderRows == invariantDeviceProfile.numFolderRows && this.numHotseatIcons == invariantDeviceProfile.numHotseatIcons) ? 0 : 1;
        if (this.iconSize != invariantDeviceProfile.iconSize || this.iconBitmapSize != invariantDeviceProfile.iconBitmapSize || !this.iconShapePath.equals(invariantDeviceProfile.iconShapePath)) {
            i |= 2;
        }
        if (!this.iconShapePath.equals(invariantDeviceProfile.iconShapePath)) {
            IconShape.init(context);
        }
        apply(context, i);
    }

    private void apply(Context context, int changeFlags) {
        this.mConfigMonitor.unregister();
        this.mConfigMonitor = new ConfigMonitor(context, new $$Lambda$InvariantDeviceProfile$fPD006L49O1k5Z9EPrGeFQ9YZ9U(this));
        Iterator<OnIDPChangeListener> it = this.mChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onIdpChanged(changeFlags, this);
        }
    }

    static ArrayList<DisplayOption> getPredefinedDeviceProfiles(Context context, String gridName) {
        ArrayList<DisplayOption> arrayList = new ArrayList();
        try {
            XmlResourceParser xml = context.getResources().getXml(R.xml.device_profiles);
            try {
                int depth = xml.getDepth();
                while (true) {
                    int next = xml.next();
                    if ((next == 3 && xml.getDepth() <= depth) || next == 1) {
                        break;
                    }
                    if (next == 2 && GridOption.TAG_NAME.equals(xml.getName())) {
                        GridOption gridOption = new GridOption(context, Xml.asAttributeSet(xml));
                        int depth2 = xml.getDepth();
                        while (true) {
                            int next2 = xml.next();
                            if ((next2 != 3 || xml.getDepth() > depth2) && next2 != 1) {
                                if (next2 == 2 && "display-option".equals(xml.getName())) {
                                    arrayList.add(new DisplayOption(gridOption, context, Xml.asAttributeSet(xml)));
                                }
                            }
                        }
                    }
                }
                if (xml != null) {
                    xml.close();
                }
                ArrayList<DisplayOption> arrayList2 = new ArrayList<>();
                if (!TextUtils.isEmpty(gridName)) {
                    for (DisplayOption displayOption : arrayList) {
                        if (gridName.equals(displayOption.grid.name)) {
                            arrayList2.add(displayOption);
                        }
                    }
                }
                if (arrayList2.isEmpty()) {
                    for (DisplayOption displayOption2 : arrayList) {
                        if (displayOption2.canBeDefault) {
                            arrayList2.add(displayOption2);
                        }
                    }
                }
                if (arrayList2.isEmpty()) {
                    throw new RuntimeException("No display option with canBeDefault=true");
                }
                return arrayList2;
            } catch (Throwable th) {
                if (xml != null) {
                    try {
                        xml.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException | XmlPullParserException e) {
            throw new RuntimeException(e);
        }
    }

    private int getLauncherIconDensity(int requiredSize) {
        int[] iArr = {120, 160, 213, 240, 320, 480, 640};
        int i = 640;
        for (int i2 = 6; i2 >= 0; i2--) {
            if ((iArr[i2] * ICON_SIZE_DEFINED_IN_APP_DP) / 160.0f >= requiredSize) {
                i = iArr[i2];
            }
        }
        return i;
    }

    private void applyPartnerDeviceProfileOverrides(Context context, DisplayMetrics dm) {
        Partner partner = Partner.get(context.getPackageManager());
        if (partner != null) {
            partner.applyInvariantDeviceProfileOverrides(this, dm);
        }
    }

    private static float dist(float x0, float y0, float x1, float y1) {
        return (float) Math.hypot(x1 - x0, y1 - y0);
    }

    static DisplayOption invDistWeightedInterpolate(float width, float height, ArrayList<DisplayOption> points) {
        DisplayOption displayOption = points.get(0);
        float f = 0.0f;
        if (dist(width, height, displayOption.minWidthDps, displayOption.minHeightDps) == 0.0f) {
            return displayOption;
        }
        DisplayOption displayOption2 = new DisplayOption();
        for (int i = 0; i < points.size() && i < KNEARESTNEIGHBOR; i++) {
            DisplayOption displayOption3 = points.get(i);
            float fWeight = weight(width, height, displayOption3.minWidthDps, displayOption3.minHeightDps, WEIGHT_POWER);
            f += fWeight;
            displayOption2.add(new DisplayOption().add(displayOption3).multiply(fWeight));
        }
        return displayOption2.multiply(1.0f / f);
    }

    public DeviceProfile getDeviceProfile(Context context) {
        return context.getResources().getConfiguration().orientation == 2 ? this.landscapeProfile : this.portraitProfile;
    }

    private static float weight(float x0, float y0, float x1, float y1, float pow) {
        float fDist = dist(x0, y0, x1, y1);
        if (Float.compare(fDist, 0.0f) == 0) {
            return Float.POSITIVE_INFINITY;
        }
        return (float) (100000.0d / Math.pow(fDist, pow));
    }

    public static final class GridOption {
        public static final String TAG_NAME = "grid-option";
        private final int defaultLayoutId;
        private final int demoModeLayoutId;
        private final SparseArray<TypedValue> extraAttrs;
        public final String name;
        public final int numColumns;
        private final int numFolderColumns;
        private final int numFolderRows;
        private final int numHotseatIcons;
        public final int numRows;

        public GridOption(Context context, AttributeSet attrs) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.GridDisplayOption);
            this.name = typedArrayObtainStyledAttributes.getString(2);
            int i = typedArrayObtainStyledAttributes.getInt(7, 0);
            this.numRows = i;
            int i2 = typedArrayObtainStyledAttributes.getInt(3, 0);
            this.numColumns = i2;
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
            this.defaultLayoutId = resourceId;
            this.demoModeLayoutId = typedArrayObtainStyledAttributes.getResourceId(1, resourceId);
            this.numHotseatIcons = typedArrayObtainStyledAttributes.getInt(6, i2);
            this.numFolderRows = typedArrayObtainStyledAttributes.getInt(5, i);
            this.numFolderColumns = typedArrayObtainStyledAttributes.getInt(4, i2);
            typedArrayObtainStyledAttributes.recycle();
            this.extraAttrs = Themes.createValueMap(context, attrs, IntArray.wrap(R.styleable.GridDisplayOption));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class DisplayOption {
        private final boolean canBeDefault;
        private final GridOption grid;
        private float iconSize;
        private float iconTextSize;
        private float landscapeIconSize;
        private final float minHeightDps;
        private final float minWidthDps;
        private final String name;

        DisplayOption(GridOption grid, Context context, AttributeSet attrs) {
            this.grid = grid;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.ProfileDisplayOption);
            this.name = typedArrayObtainStyledAttributes.getString(6);
            this.minWidthDps = typedArrayObtainStyledAttributes.getFloat(5, 0.0f);
            this.minHeightDps = typedArrayObtainStyledAttributes.getFloat(4, 0.0f);
            this.canBeDefault = typedArrayObtainStyledAttributes.getBoolean(0, false);
            float f = typedArrayObtainStyledAttributes.getFloat(1, 0.0f);
            this.iconSize = f;
            this.landscapeIconSize = typedArrayObtainStyledAttributes.getFloat(3, f);
            this.iconTextSize = typedArrayObtainStyledAttributes.getFloat(2, 0.0f);
            typedArrayObtainStyledAttributes.recycle();
        }

        DisplayOption() {
            this.grid = null;
            this.name = null;
            this.minWidthDps = 0.0f;
            this.minHeightDps = 0.0f;
            this.canBeDefault = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DisplayOption multiply(float w) {
            this.iconSize *= w;
            this.landscapeIconSize *= w;
            this.iconTextSize *= w;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DisplayOption add(DisplayOption p) {
            this.iconSize += p.iconSize;
            this.landscapeIconSize += p.landscapeIconSize;
            this.iconTextSize += p.iconTextSize;
            return this;
        }
    }

    private class OverlayMonitor extends BroadcastReceiver {
        private final String ACTION_OVERLAY_CHANGED = Intent.ACTION_OVERLAY_CHANGED;

        OverlayMonitor(Context context) {
            context.registerReceiver(this, PackageManagerHelper.getPackageFilter("android", Intent.ACTION_OVERLAY_CHANGED));
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            InvariantDeviceProfile.this.lambda$setCurrentGrid$1$InvariantDeviceProfile(context);
        }
    }
}
