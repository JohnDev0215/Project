package com.android.launcher3;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.launcher3.AutoInstallsLayout;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultLayoutParser extends AutoInstallsLayout {
    private static final String ACTION_APPWIDGET_DEFAULT_WORKSPACE_CONFIGURE = "com.android.launcher.action.APPWIDGET_DEFAULT_WORKSPACE_CONFIGURE";
    private static final String ATTR_CONTAINER = "container";
    private static final String ATTR_FOLDER_ITEMS = "folderItems";
    private static final String ATTR_SCREEN = "screen";
    protected static final String ATTR_URI = "uri";
    private static final String TAG = "DefaultLayoutParser";
    private static final String TAG_APPWIDGET = "appwidget";
    protected static final String TAG_FAVORITE = "favorite";
    private static final String TAG_FAVORITES = "favorites";
    private static final String TAG_FOLDER = "folder";
    private static final String TAG_PARTNER_FOLDER = "partner-folder";
    protected static final String TAG_RESOLVE = "resolve";
    protected static final String TAG_SHORTCUT = "shortcut";

    public DefaultLayoutParser(Context context, AppWidgetHost appWidgetHost, AutoInstallsLayout.LayoutParserCallback callback, Resources sourceRes, int layoutId) {
        super(context, appWidgetHost, callback, sourceRes, layoutId, "favorites");
    }

    @Override // com.android.launcher3.AutoInstallsLayout
    protected ArrayMap<String, AutoInstallsLayout.TagParser> getFolderElementsMap() {
        return getFolderElementsMap(this.mSourceRes);
    }

    ArrayMap<String, AutoInstallsLayout.TagParser> getFolderElementsMap(Resources res) {
        ArrayMap<String, AutoInstallsLayout.TagParser> arrayMap = new ArrayMap<>();
        arrayMap.put(TAG_FAVORITE, new AppShortcutWithUriParser());
        arrayMap.put("shortcut", new UriShortcutParser(res));
        return arrayMap;
    }

    @Override // com.android.launcher3.AutoInstallsLayout
    protected ArrayMap<String, AutoInstallsLayout.TagParser> getLayoutElementsMap() {
        ArrayMap<String, AutoInstallsLayout.TagParser> arrayMap = new ArrayMap<>();
        arrayMap.put(TAG_FAVORITE, new AppShortcutWithUriParser());
        arrayMap.put("appwidget", new AppWidgetParser());
        arrayMap.put("shortcut", new UriShortcutParser(this.mSourceRes));
        arrayMap.put(TAG_RESOLVE, new ResolveParser());
        arrayMap.put("folder", new MyFolderParser());
        arrayMap.put(TAG_PARTNER_FOLDER, new PartnerFolderParser());
        return arrayMap;
    }

    @Override // com.android.launcher3.AutoInstallsLayout
    protected void parseContainerAndScreen(XmlPullParser parser, int[] out) {
        out[0] = -100;
        String attributeValue = getAttributeValue(parser, "container");
        if (attributeValue != null) {
            out[0] = Integer.parseInt(attributeValue);
        }
        out[1] = Integer.parseInt(getAttributeValue(parser, "screen"));
    }

    public class AppShortcutWithUriParser extends AutoInstallsLayout.AppShortcutParser {
        public AppShortcutWithUriParser() {
            super();
        }

        @Override // com.android.launcher3.AutoInstallsLayout.AppShortcutParser, com.android.launcher3.AutoInstallsLayout.TagParser
        public /* bridge */ /* synthetic */ int parseAndAdd(XmlPullParser parser) {
            return super.parseAndAdd(parser);
        }

        @Override // com.android.launcher3.AutoInstallsLayout.AppShortcutParser
        protected int invalidPackageOrClass(XmlPullParser parser) {
            String attributeValue = AutoInstallsLayout.getAttributeValue(parser, "uri");
            if (TextUtils.isEmpty(attributeValue)) {
                Log.e(DefaultLayoutParser.TAG, "Skipping invalid <favorite> with no component or uri");
                return -1;
            }
            try {
                Intent uri = Intent.parseUri(attributeValue, 0);
                ResolveInfo resolveInfoResolveActivity = DefaultLayoutParser.this.mPackageManager.resolveActivity(uri, 65536);
                List<ResolveInfo> listQueryIntentActivities = DefaultLayoutParser.this.mPackageManager.queryIntentActivities(uri, 65536);
                if (wouldLaunchResolverActivity(resolveInfoResolveActivity, listQueryIntentActivities) && (resolveInfoResolveActivity = getSingleSystemActivity(listQueryIntentActivities)) == null) {
                    Log.w(DefaultLayoutParser.TAG, "No preference or single system activity found for " + uri.toString());
                    return -1;
                }
                ActivityInfo activityInfo = resolveInfoResolveActivity.activityInfo;
                Intent launchIntentForPackage = DefaultLayoutParser.this.mPackageManager.getLaunchIntentForPackage(activityInfo.packageName);
                if (launchIntentForPackage == null) {
                    return -1;
                }
                launchIntentForPackage.setFlags(270532608);
                DefaultLayoutParser defaultLayoutParser = DefaultLayoutParser.this;
                return defaultLayoutParser.addShortcut(activityInfo.loadLabel(defaultLayoutParser.mPackageManager).toString(), launchIntentForPackage, 0);
            } catch (URISyntaxException e) {
                Log.e(DefaultLayoutParser.TAG, "Unable to add meta-favorite: " + attributeValue, e);
                return -1;
            }
        }

        private ResolveInfo getSingleSystemActivity(List<ResolveInfo> appList) {
            int size = appList.size();
            ResolveInfo resolveInfo = null;
            for (int i = 0; i < size; i++) {
                try {
                    if ((DefaultLayoutParser.this.mPackageManager.getApplicationInfo(appList.get(i).activityInfo.packageName, 0).flags & 1) != 0) {
                        if (resolveInfo != null) {
                            return null;
                        }
                        resolveInfo = appList.get(i);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.w(DefaultLayoutParser.TAG, "Unable to get info about resolve results", e);
                    return null;
                }
            }
            return resolveInfo;
        }

        private boolean wouldLaunchResolverActivity(ResolveInfo resolved, List<ResolveInfo> appList) {
            for (int i = 0; i < appList.size(); i++) {
                ResolveInfo resolveInfo = appList.get(i);
                if (resolveInfo.activityInfo.name.equals(resolved.activityInfo.name) && resolveInfo.activityInfo.packageName.equals(resolved.activityInfo.packageName)) {
                    return false;
                }
            }
            return true;
        }
    }

    public class UriShortcutParser extends AutoInstallsLayout.ShortcutParser {
        @Override // com.android.launcher3.AutoInstallsLayout.ShortcutParser, com.android.launcher3.AutoInstallsLayout.TagParser
        public /* bridge */ /* synthetic */ int parseAndAdd(XmlPullParser parser) {
            return super.parseAndAdd(parser);
        }

        public UriShortcutParser(Resources iconRes) {
            super(iconRes);
        }

        @Override // com.android.launcher3.AutoInstallsLayout.ShortcutParser
        protected Intent parseIntent(XmlPullParser parser) {
            String attributeValue;
            try {
                attributeValue = AutoInstallsLayout.getAttributeValue(parser, "uri");
            } catch (URISyntaxException unused) {
                attributeValue = null;
            }
            try {
                return Intent.parseUri(attributeValue, 0);
            } catch (URISyntaxException unused2) {
                Log.w(DefaultLayoutParser.TAG, "Shortcut has malformed uri: " + attributeValue);
                return null;
            }
        }
    }

    public class ResolveParser implements AutoInstallsLayout.TagParser {
        private final AppShortcutWithUriParser mChildParser;

        public ResolveParser() {
            this.mChildParser = DefaultLayoutParser.this.new AppShortcutWithUriParser();
        }

        @Override // com.android.launcher3.AutoInstallsLayout.TagParser
        public int parseAndAdd(XmlPullParser parser) throws XmlPullParserException, IOException {
            int depth = parser.getDepth();
            int andAdd = -1;
            while (true) {
                int next = parser.next();
                if (next == 3 && parser.getDepth() <= depth) {
                    return andAdd;
                }
                if (next == 2 && andAdd <= -1) {
                    String name = parser.getName();
                    if (DefaultLayoutParser.TAG_FAVORITE.equals(name)) {
                        andAdd = this.mChildParser.parseAndAdd(parser);
                    } else {
                        Log.e(DefaultLayoutParser.TAG, "Fallback groups can contain only favorites, found " + name);
                    }
                }
            }
        }
    }

    class PartnerFolderParser implements AutoInstallsLayout.TagParser {
        PartnerFolderParser() {
        }

        @Override // com.android.launcher3.AutoInstallsLayout.TagParser
        public int parseAndAdd(XmlPullParser parser) throws XmlPullParserException, IOException {
            Resources resources;
            int identifier;
            Partner partner = Partner.get(DefaultLayoutParser.this.mPackageManager);
            if (partner == null || (identifier = (resources = partner.getResources()).getIdentifier(Partner.RES_FOLDER, "xml", partner.getPackageName())) == 0) {
                return -1;
            }
            XmlResourceParser xml = resources.getXml(identifier);
            AutoInstallsLayout.beginDocument(xml, "folder");
            DefaultLayoutParser defaultLayoutParser = DefaultLayoutParser.this;
            return new AutoInstallsLayout.FolderParser(defaultLayoutParser.getFolderElementsMap(resources)).parseAndAdd(xml);
        }
    }

    class MyFolderParser extends AutoInstallsLayout.FolderParser {
        MyFolderParser() {
            super(DefaultLayoutParser.this);
        }

        @Override // com.android.launcher3.AutoInstallsLayout.FolderParser, com.android.launcher3.AutoInstallsLayout.TagParser
        public int parseAndAdd(XmlPullParser parser) throws XmlPullParserException, IOException {
            int attributeResourceValue = AutoInstallsLayout.getAttributeResourceValue(parser, DefaultLayoutParser.ATTR_FOLDER_ITEMS, 0);
            if (attributeResourceValue != 0) {
                parser = DefaultLayoutParser.this.mSourceRes.getXml(attributeResourceValue);
                AutoInstallsLayout.beginDocument(parser, "folder");
            }
            return super.parseAndAdd(parser);
        }
    }

    protected class AppWidgetParser extends AutoInstallsLayout.PendingWidgetParser {
        protected AppWidgetParser() {
            super();
        }

        @Override // com.android.launcher3.AutoInstallsLayout.PendingWidgetParser
        protected int verifyAndInsert(ComponentName cn, Bundle extras) {
            int iAllocateAppWidgetId;
            int iInsertAndCheck = -1;
            try {
                DefaultLayoutParser.this.mPackageManager.getReceiverInfo(cn, 0);
            } catch (Exception unused) {
                ComponentName componentName = new ComponentName(DefaultLayoutParser.this.mPackageManager.currentToCanonicalPackageNames(new String[]{cn.getPackageName()})[0], cn.getClassName());
                try {
                    DefaultLayoutParser.this.mPackageManager.getReceiverInfo(componentName, 0);
                    cn = componentName;
                } catch (Exception unused2) {
                    Log.d(DefaultLayoutParser.TAG, "Can't find widget provider: " + componentName.getClassName());
                    return -1;
                }
            }
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(DefaultLayoutParser.this.mContext);
            try {
                iAllocateAppWidgetId = DefaultLayoutParser.this.mAppWidgetHost.allocateAppWidgetId();
            } catch (RuntimeException e) {
                Log.e(DefaultLayoutParser.TAG, "Problem allocating appWidgetId", e);
            }
            if (!appWidgetManager.bindAppWidgetIdIfAllowed(iAllocateAppWidgetId, cn)) {
                Log.e(DefaultLayoutParser.TAG, "Unable to bind app widget id " + cn);
                DefaultLayoutParser.this.mAppWidgetHost.deleteAppWidgetId(iAllocateAppWidgetId);
                return -1;
            }
            DefaultLayoutParser.this.mValues.put("appWidgetId", Integer.valueOf(iAllocateAppWidgetId));
            DefaultLayoutParser.this.mValues.put("appWidgetProvider", cn.flattenToString());
            DefaultLayoutParser.this.mValues.put("_id", Integer.valueOf(DefaultLayoutParser.this.mCallback.generateNewItemId()));
            iInsertAndCheck = DefaultLayoutParser.this.mCallback.insertAndCheck(DefaultLayoutParser.this.mDb, DefaultLayoutParser.this.mValues);
            if (iInsertAndCheck < 0) {
                DefaultLayoutParser.this.mAppWidgetHost.deleteAppWidgetId(iAllocateAppWidgetId);
                return iInsertAndCheck;
            }
            if (!extras.isEmpty()) {
                Intent intent = new Intent(DefaultLayoutParser.ACTION_APPWIDGET_DEFAULT_WORKSPACE_CONFIGURE);
                intent.setComponent(cn);
                intent.putExtras(extras);
                intent.putExtra("appWidgetId", iAllocateAppWidgetId);
                DefaultLayoutParser.this.mContext.sendBroadcast(intent);
            }
            return iInsertAndCheck;
        }
    }
}
