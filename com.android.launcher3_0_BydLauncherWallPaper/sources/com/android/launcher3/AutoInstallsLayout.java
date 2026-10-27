package com.android.launcher3;

import android.appwidget.AppWidgetHost;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.util.Patterns;
import android.util.Xml;
import com.android.launcher3.LauncherProvider;
import com.android.launcher3.LauncherSettings;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.launcher3.icons.LauncherIcons;
import com.android.launcher3.util.IntArray;
import java.io.IOException;
import java.util.Locale;
import java.util.function.Supplier;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes4.dex */
public class AutoInstallsLayout {
    static final String ACTION_LAUNCHER_CUSTOMIZATION = "android.autoinstalls.config.action.PLAY_AUTO_INSTALL";
    private static final String ATTR_CLASS_NAME = "className";
    private static final String ATTR_CONTAINER = "container";
    private static final String ATTR_ICON = "icon";
    private static final String ATTR_KEY = "key";
    private static final String ATTR_PACKAGE_NAME = "packageName";
    private static final String ATTR_RANK = "rank";
    private static final String ATTR_SCREEN = "screen";
    private static final String ATTR_SPAN_X = "spanX";
    private static final String ATTR_SPAN_Y = "spanY";
    private static final String ATTR_TITLE = "title";
    private static final String ATTR_URL = "url";
    private static final String ATTR_VALUE = "value";
    private static final String ATTR_WORKSPACE = "workspace";
    private static final String ATTR_X = "x";
    private static final String ATTR_Y = "y";
    private static final String FORMATTED_LAYOUT_RES = "default_layout_%dx%d";
    private static final String FORMATTED_LAYOUT_RES_WITH_HOSTEAT = "default_layout_%dx%d_h%s";
    private static final String HOTSEAT_CONTAINER_NAME = LauncherSettings.Favorites.containerToString(-101);
    private static final String LAYOUT_RES = "default_layout";
    private static final boolean LOGD = false;
    private static final String TAG = "AutoInstalls";
    private static final String TAG_APPWIDGET = "appwidget";
    private static final String TAG_APP_ICON = "appicon";
    private static final String TAG_AUTO_INSTALL = "autoinstall";
    private static final String TAG_EXTRA = "extra";
    private static final String TAG_FOLDER = "folder";
    private static final String TAG_INCLUDE = "include";
    private static final String TAG_SHORTCUT = "shortcut";
    public static final String TAG_WORKSPACE = "workspace";
    final AppWidgetHost mAppWidgetHost;
    protected final LayoutParserCallback mCallback;
    private final int mColumnCount;
    final Context mContext;
    protected SQLiteDatabase mDb;
    private final InvariantDeviceProfile mIdp;
    protected final Supplier<XmlPullParser> mInitialLayoutSupplier;
    protected final PackageManager mPackageManager;
    protected final String mRootTag;
    private final int mRowCount;
    protected final Resources mSourceRes;
    private final int[] mTemp;
    final ContentValues mValues;

    public interface LayoutParserCallback {
        int generateNewItemId();

        int insertAndCheck(SQLiteDatabase db, ContentValues values);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public interface TagParser {
        int parseAndAdd(XmlPullParser parser) throws XmlPullParserException, IOException;
    }

    static AutoInstallsLayout get(Context context, AppWidgetHost appWidgetHost, LayoutParserCallback callback) {
        int identifier;
        Pair<String, Resources> pairFindSystemApk = Utilities.findSystemApk(ACTION_LAUNCHER_CUSTOMIZATION, context.getPackageManager());
        if (pairFindSystemApk == null) {
            return null;
        }
        String str = pairFindSystemApk.first;
        Resources resources = pairFindSystemApk.second;
        InvariantDeviceProfile idp = LauncherAppState.getIDP(context);
        String str2 = String.format(Locale.ENGLISH, FORMATTED_LAYOUT_RES_WITH_HOSTEAT, Integer.valueOf(idp.numColumns), Integer.valueOf(idp.numRows), Integer.valueOf(idp.numHotseatIcons));
        int identifier2 = resources.getIdentifier(str2, "xml", str);
        if (identifier2 == 0) {
            Log.d(TAG, "Formatted layout: " + str2 + " not found. Trying layout without hosteat");
            str2 = String.format(Locale.ENGLISH, FORMATTED_LAYOUT_RES, Integer.valueOf(idp.numColumns), Integer.valueOf(idp.numRows));
            identifier2 = resources.getIdentifier(str2, "xml", str);
        }
        if (identifier2 == 0) {
            Log.d(TAG, "Formatted layout: " + str2 + " not found. Trying the default layout");
            identifier = resources.getIdentifier(LAYOUT_RES, "xml", str);
        } else {
            identifier = identifier2;
        }
        if (identifier == 0) {
            Log.e(TAG, "Layout definition not found in package: " + str);
            return null;
        }
        return new AutoInstallsLayout(context, appWidgetHost, callback, resources, identifier, "workspace");
    }

    public AutoInstallsLayout(Context context, AppWidgetHost appWidgetHost, LayoutParserCallback callback, final Resources res, final int layoutId, String rootTag) {
        this(context, appWidgetHost, callback, res, (Supplier<XmlPullParser>) new Supplier() { // from class: com.android.launcher3.-$$Lambda$AutoInstallsLayout$PHcYDBtQHFaz1kcGb0WLl5qhPfU
            @Override // java.util.function.Supplier
            public final Object get() {
                return res.getXml(layoutId);
            }
        }, rootTag);
    }

    public AutoInstallsLayout(Context context, AppWidgetHost appWidgetHost, LayoutParserCallback callback, Resources res, Supplier<XmlPullParser> initialLayoutSupplier, String rootTag) {
        this.mTemp = new int[2];
        this.mContext = context;
        this.mAppWidgetHost = appWidgetHost;
        this.mCallback = callback;
        this.mPackageManager = context.getPackageManager();
        this.mValues = new ContentValues();
        this.mRootTag = rootTag;
        this.mSourceRes = res;
        this.mInitialLayoutSupplier = initialLayoutSupplier;
        InvariantDeviceProfile idp = LauncherAppState.getIDP(context);
        this.mIdp = idp;
        this.mRowCount = idp.numRows;
        this.mColumnCount = idp.numColumns;
    }

    public int loadLayout(SQLiteDatabase db, IntArray screenIds) {
        this.mDb = db;
        try {
            return parseLayout(this.mInitialLayoutSupplier.get(), screenIds);
        } catch (Exception e) {
            Log.e(TAG, "Error parsing layout: ", e);
            return -1;
        }
    }

    protected int parseLayout(XmlPullParser parser, IntArray screenIds) throws XmlPullParserException, IOException {
        beginDocument(parser, this.mRootTag);
        int depth = parser.getDepth();
        ArrayMap<String, TagParser> layoutElementsMap = getLayoutElementsMap();
        int andAddNode = 0;
        while (true) {
            int next = parser.next();
            if ((next == 3 && parser.getDepth() <= depth) || next == 1) {
                break;
            }
            if (next == 2) {
                andAddNode += parseAndAddNode(parser, layoutElementsMap, screenIds);
            }
        }
        return andAddNode;
    }

    protected void parseContainerAndScreen(XmlPullParser parser, int[] out) {
        if (HOTSEAT_CONTAINER_NAME.equals(getAttributeValue(parser, "container"))) {
            out[0] = -101;
            out[1] = Integer.parseInt(getAttributeValue(parser, "rank"));
        } else {
            out[0] = -100;
            out[1] = Integer.parseInt(getAttributeValue(parser, "screen"));
        }
    }

    protected int parseAndAddNode(XmlPullParser parser, ArrayMap<String, TagParser> tagParserMap, IntArray screenIds) throws XmlPullParserException, IOException {
        if (TAG_INCLUDE.equals(parser.getName())) {
            int attributeResourceValue = getAttributeResourceValue(parser, "workspace", 0);
            if (attributeResourceValue != 0) {
                return parseLayout(this.mSourceRes.getXml(attributeResourceValue), screenIds);
            }
            return 0;
        }
        this.mValues.clear();
        parseContainerAndScreen(parser, this.mTemp);
        int[] iArr = this.mTemp;
        int i = iArr[0];
        int i2 = iArr[1];
        this.mValues.put("container", Integer.valueOf(i));
        this.mValues.put("screen", Integer.valueOf(i2));
        this.mValues.put(LauncherSettings.Favorites.CELLX, convertToDistanceFromEnd(getAttributeValue(parser, ATTR_X), this.mColumnCount));
        this.mValues.put(LauncherSettings.Favorites.CELLY, convertToDistanceFromEnd(getAttributeValue(parser, ATTR_Y), this.mRowCount));
        TagParser tagParser = tagParserMap.get(parser.getName());
        if (tagParser == null || tagParser.parseAndAdd(parser) < 0) {
            return 0;
        }
        if (!screenIds.contains(i2) && i == -100) {
            screenIds.add(i2);
        }
        return 1;
    }

    protected int addShortcut(String title, Intent intent, int type) {
        int iGenerateNewItemId = this.mCallback.generateNewItemId();
        this.mValues.put("intent", intent.toUri(0));
        this.mValues.put("title", title);
        this.mValues.put(LauncherSettings.Favorites.ITEM_TYPE, Integer.valueOf(type));
        this.mValues.put("spanX", (Integer) 1);
        this.mValues.put("spanY", (Integer) 1);
        this.mValues.put("_id", Integer.valueOf(iGenerateNewItemId));
        if (this.mCallback.insertAndCheck(this.mDb, this.mValues) < 0) {
            return -1;
        }
        return iGenerateNewItemId;
    }

    protected ArrayMap<String, TagParser> getFolderElementsMap() {
        ArrayMap<String, TagParser> arrayMap = new ArrayMap<>();
        arrayMap.put(TAG_APP_ICON, new AppShortcutParser());
        arrayMap.put(TAG_AUTO_INSTALL, new AutoInstallParser());
        arrayMap.put("shortcut", new ShortcutParser(this.mSourceRes));
        return arrayMap;
    }

    protected ArrayMap<String, TagParser> getLayoutElementsMap() {
        ArrayMap<String, TagParser> arrayMap = new ArrayMap<>();
        arrayMap.put(TAG_APP_ICON, new AppShortcutParser());
        arrayMap.put(TAG_AUTO_INSTALL, new AutoInstallParser());
        arrayMap.put("folder", new FolderParser(this));
        arrayMap.put("appwidget", new PendingWidgetParser());
        arrayMap.put("shortcut", new ShortcutParser(this.mSourceRes));
        return arrayMap;
    }

    protected class AppShortcutParser implements TagParser {
        protected AppShortcutParser() {
        }

        @Override // com.android.launcher3.AutoInstallsLayout.TagParser
        public int parseAndAdd(XmlPullParser parser) {
            ActivityInfo activityInfo;
            ComponentName componentName;
            String attributeValue = AutoInstallsLayout.getAttributeValue(parser, "packageName");
            String attributeValue2 = AutoInstallsLayout.getAttributeValue(parser, "className");
            if (!TextUtils.isEmpty(attributeValue) && !TextUtils.isEmpty(attributeValue2)) {
                try {
                    try {
                        componentName = new ComponentName(attributeValue, attributeValue2);
                        activityInfo = AutoInstallsLayout.this.mPackageManager.getActivityInfo(componentName, 0);
                    } catch (PackageManager.NameNotFoundException unused) {
                        ComponentName componentName2 = new ComponentName(AutoInstallsLayout.this.mPackageManager.currentToCanonicalPackageNames(new String[]{attributeValue})[0], attributeValue2);
                        activityInfo = AutoInstallsLayout.this.mPackageManager.getActivityInfo(componentName2, 0);
                        componentName = componentName2;
                    }
                    Intent flags = new Intent(Intent.ACTION_MAIN, (Uri) null).addCategory(Intent.CATEGORY_LAUNCHER).setComponent(componentName).setFlags(270532608);
                    AutoInstallsLayout autoInstallsLayout = AutoInstallsLayout.this;
                    return autoInstallsLayout.addShortcut(activityInfo.loadLabel(autoInstallsLayout.mPackageManager).toString(), flags, 0);
                } catch (PackageManager.NameNotFoundException unused2) {
                    Log.e(AutoInstallsLayout.TAG, "Favorite not found: " + attributeValue + "/" + attributeValue2);
                    return -1;
                }
            }
            return invalidPackageOrClass(parser);
        }

        protected int invalidPackageOrClass(XmlPullParser parser) {
            Log.w(AutoInstallsLayout.TAG, "Skipping invalid <favorite> with no component");
            return -1;
        }
    }

    protected class AutoInstallParser implements TagParser {
        protected AutoInstallParser() {
        }

        @Override // com.android.launcher3.AutoInstallsLayout.TagParser
        public int parseAndAdd(XmlPullParser parser) {
            String attributeValue = AutoInstallsLayout.getAttributeValue(parser, "packageName");
            String attributeValue2 = AutoInstallsLayout.getAttributeValue(parser, "className");
            if (TextUtils.isEmpty(attributeValue) || TextUtils.isEmpty(attributeValue2)) {
                return -1;
            }
            AutoInstallsLayout.this.mValues.put("restored", (Integer) 2);
            Intent flags = new Intent(Intent.ACTION_MAIN, (Uri) null).addCategory(Intent.CATEGORY_LAUNCHER).setComponent(new ComponentName(attributeValue, attributeValue2)).setFlags(270532608);
            AutoInstallsLayout autoInstallsLayout = AutoInstallsLayout.this;
            return autoInstallsLayout.addShortcut(autoInstallsLayout.mContext.getString(R.string.package_state_unknown), flags, 0);
        }
    }

    protected class ShortcutParser implements TagParser {
        private final Resources mIconRes;

        public ShortcutParser(Resources iconRes) {
            this.mIconRes = iconRes;
        }

        @Override // com.android.launcher3.AutoInstallsLayout.TagParser
        public int parseAndAdd(XmlPullParser parser) {
            Intent intent;
            Drawable drawable;
            int attributeResourceValue = AutoInstallsLayout.getAttributeResourceValue(parser, "title", 0);
            int attributeResourceValue2 = AutoInstallsLayout.getAttributeResourceValue(parser, "icon", 0);
            if (attributeResourceValue == 0 || attributeResourceValue2 == 0 || (intent = parseIntent(parser)) == null || (drawable = this.mIconRes.getDrawable(attributeResourceValue2)) == null) {
                return -1;
            }
            LauncherIcons launcherIconsObtain = LauncherIcons.obtain(AutoInstallsLayout.this.mContext);
            AutoInstallsLayout.this.mValues.put("icon", GraphicsUtils.flattenBitmap(launcherIconsObtain.createBadgedIconBitmap(drawable, Process.myUserHandle(), Build.VERSION.SDK_INT).icon));
            launcherIconsObtain.recycle();
            AutoInstallsLayout.this.mValues.put(LauncherSettings.Favorites.ICON_PACKAGE, this.mIconRes.getResourcePackageName(attributeResourceValue2));
            AutoInstallsLayout.this.mValues.put(LauncherSettings.Favorites.ICON_RESOURCE, this.mIconRes.getResourceName(attributeResourceValue2));
            intent.setFlags(270532608);
            AutoInstallsLayout autoInstallsLayout = AutoInstallsLayout.this;
            return autoInstallsLayout.addShortcut(autoInstallsLayout.mSourceRes.getString(attributeResourceValue), intent, 1);
        }

        protected Intent parseIntent(XmlPullParser parser) {
            String attributeValue = AutoInstallsLayout.getAttributeValue(parser, "url");
            if (TextUtils.isEmpty(attributeValue) || !Patterns.WEB_URL.matcher(attributeValue).matches()) {
                return null;
            }
            return new Intent("android.intent.action.VIEW", (Uri) null).setData(Uri.parse(attributeValue));
        }
    }

    protected class PendingWidgetParser implements TagParser {
        protected PendingWidgetParser() {
        }

        @Override // com.android.launcher3.AutoInstallsLayout.TagParser
        public int parseAndAdd(XmlPullParser parser) throws XmlPullParserException, IOException {
            String attributeValue = AutoInstallsLayout.getAttributeValue(parser, "packageName");
            String attributeValue2 = AutoInstallsLayout.getAttributeValue(parser, "className");
            if (TextUtils.isEmpty(attributeValue) || TextUtils.isEmpty(attributeValue2)) {
                return -1;
            }
            AutoInstallsLayout.this.mValues.put("spanX", AutoInstallsLayout.getAttributeValue(parser, "spanX"));
            AutoInstallsLayout.this.mValues.put("spanY", AutoInstallsLayout.getAttributeValue(parser, "spanY"));
            AutoInstallsLayout.this.mValues.put(LauncherSettings.Favorites.ITEM_TYPE, (Integer) 4);
            Bundle bundle = new Bundle();
            int depth = parser.getDepth();
            while (true) {
                int next = parser.next();
                if (next == 3 && parser.getDepth() <= depth) {
                    return verifyAndInsert(new ComponentName(attributeValue, attributeValue2), bundle);
                }
                if (next == 2) {
                    if (AutoInstallsLayout.TAG_EXTRA.equals(parser.getName())) {
                        String attributeValue3 = AutoInstallsLayout.getAttributeValue(parser, "key");
                        String attributeValue4 = AutoInstallsLayout.getAttributeValue(parser, "value");
                        if (attributeValue3 == null || attributeValue4 == null) {
                            break;
                        }
                        bundle.putString(attributeValue3, attributeValue4);
                    } else {
                        throw new RuntimeException("Widgets can contain only extras");
                    }
                }
            }
            throw new RuntimeException("Widget extras must have a key and value");
        }

        protected int verifyAndInsert(ComponentName cn, Bundle extras) {
            AutoInstallsLayout.this.mValues.put("appWidgetProvider", cn.flattenToString());
            AutoInstallsLayout.this.mValues.put("restored", (Integer) 35);
            AutoInstallsLayout.this.mValues.put("_id", Integer.valueOf(AutoInstallsLayout.this.mCallback.generateNewItemId()));
            if (!extras.isEmpty()) {
                AutoInstallsLayout.this.mValues.put("intent", new Intent().putExtras(extras).toUri(0));
            }
            int iInsertAndCheck = AutoInstallsLayout.this.mCallback.insertAndCheck(AutoInstallsLayout.this.mDb, AutoInstallsLayout.this.mValues);
            if (iInsertAndCheck < 0) {
                return -1;
            }
            return iInsertAndCheck;
        }
    }

    protected class FolderParser implements TagParser {
        private final ArrayMap<String, TagParser> mFolderElements;

        public FolderParser(final AutoInstallsLayout this$0) {
            this(this$0.getFolderElementsMap());
        }

        public FolderParser(ArrayMap<String, TagParser> elements) {
            this.mFolderElements = elements;
        }

        @Override // com.android.launcher3.AutoInstallsLayout.TagParser
        public int parseAndAdd(XmlPullParser parser) throws XmlPullParserException, IOException {
            String string;
            int attributeResourceValue = AutoInstallsLayout.getAttributeResourceValue(parser, "title", 0);
            if (attributeResourceValue != 0) {
                string = AutoInstallsLayout.this.mSourceRes.getString(attributeResourceValue);
            } else {
                string = AutoInstallsLayout.this.mContext.getResources().getString(R.string.folder_name);
            }
            AutoInstallsLayout.this.mValues.put("title", string);
            AutoInstallsLayout.this.mValues.put(LauncherSettings.Favorites.ITEM_TYPE, (Integer) 2);
            AutoInstallsLayout.this.mValues.put("spanX", (Integer) 1);
            AutoInstallsLayout.this.mValues.put("spanY", (Integer) 1);
            AutoInstallsLayout.this.mValues.put("_id", Integer.valueOf(AutoInstallsLayout.this.mCallback.generateNewItemId()));
            int iInsertAndCheck = AutoInstallsLayout.this.mCallback.insertAndCheck(AutoInstallsLayout.this.mDb, AutoInstallsLayout.this.mValues);
            if (iInsertAndCheck < 0) {
                return -1;
            }
            ContentValues contentValues = new ContentValues(AutoInstallsLayout.this.mValues);
            IntArray intArray = new IntArray();
            int depth = parser.getDepth();
            int i = 0;
            while (true) {
                int next = parser.next();
                if (next == 3 && parser.getDepth() <= depth) {
                    if (intArray.size() >= 2) {
                        return iInsertAndCheck;
                    }
                    LauncherProvider.SqlArguments sqlArguments = new LauncherProvider.SqlArguments(LauncherSettings.Favorites.getContentUri(iInsertAndCheck), null, null);
                    AutoInstallsLayout.this.mDb.delete(sqlArguments.table, sqlArguments.where, sqlArguments.args);
                    if (intArray.size() != 1) {
                        return -1;
                    }
                    ContentValues contentValues2 = new ContentValues();
                    AutoInstallsLayout.copyInteger(contentValues, contentValues2, "container");
                    AutoInstallsLayout.copyInteger(contentValues, contentValues2, "screen");
                    AutoInstallsLayout.copyInteger(contentValues, contentValues2, LauncherSettings.Favorites.CELLX);
                    AutoInstallsLayout.copyInteger(contentValues, contentValues2, LauncherSettings.Favorites.CELLY);
                    int i2 = intArray.get(0);
                    AutoInstallsLayout.this.mDb.update("favorites", contentValues2, "_id=" + i2, null);
                    return i2;
                }
                if (next == 2) {
                    AutoInstallsLayout.this.mValues.clear();
                    AutoInstallsLayout.this.mValues.put("container", Integer.valueOf(iInsertAndCheck));
                    AutoInstallsLayout.this.mValues.put("rank", Integer.valueOf(i));
                    TagParser tagParser = this.mFolderElements.get(parser.getName());
                    if (tagParser != null) {
                        int andAdd = tagParser.parseAndAdd(parser);
                        if (andAdd >= 0) {
                            intArray.add(andAdd);
                            i++;
                        }
                    } else {
                        throw new RuntimeException("Invalid folder item " + parser.getName());
                    }
                }
            }
        }
    }

    protected static void beginDocument(XmlPullParser parser, String firstElementName) throws XmlPullParserException, IOException {
        int next;
        do {
            next = parser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        if (!parser.getName().equals(firstElementName)) {
            throw new XmlPullParserException("Unexpected start tag: found " + parser.getName() + ", expected " + firstElementName);
        }
    }

    private static String convertToDistanceFromEnd(String value, int endValue) {
        int i;
        return (TextUtils.isEmpty(value) || (i = Integer.parseInt(value)) >= 0) ? value : Integer.toString(endValue + i);
    }

    protected static String getAttributeValue(XmlPullParser parser, String attribute) {
        String attributeValue = parser.getAttributeValue("http://schemas.android.com/apk/res-auto/com.android.launcher3", attribute);
        return attributeValue == null ? parser.getAttributeValue(null, attribute) : attributeValue;
    }

    protected static int getAttributeResourceValue(XmlPullParser parser, String attribute, int defaultValue) {
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(parser);
        int attributeResourceValue = attributeSetAsAttributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res-auto/com.android.launcher3", attribute, defaultValue);
        return attributeResourceValue == defaultValue ? attributeSetAsAttributeSet.getAttributeResourceValue(null, attribute, defaultValue) : attributeResourceValue;
    }

    static void copyInteger(ContentValues from, ContentValues to, String key) {
        to.put(key, from.getAsInteger(key));
    }
}
