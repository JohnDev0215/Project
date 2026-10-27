package com.android.permissioncontroller.role.model;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.res.XmlResourceParser;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import com.android.permissioncontroller.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes8.dex */
public class RoleParser {
    private static final String ATTRIBUTE_BEHAVIOR = "behavior";
    private static final String ATTRIBUTE_DEFAULT_HOLDERS = "defaultHolders";
    private static final String ATTRIBUTE_DESCRIPTION = "description";
    private static final String ATTRIBUTE_EXCLUSIVE = "exclusive";
    private static final String ATTRIBUTE_FALL_BACK_TO_DEFAULT_HOLDER = "fallBackToDefaultHolder";
    private static final String ATTRIBUTE_LABEL = "label";
    private static final String ATTRIBUTE_MAX_TARGET_SDK_VERSION = "maxTargetSdkVersion";
    private static final String ATTRIBUTE_MIME_TYPE = "mimeType";
    private static final String ATTRIBUTE_MODE = "mode";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_OPTIONAL = "optional";
    private static final String ATTRIBUTE_PERMISSION = "permission";
    private static final String ATTRIBUTE_REQUESTABLE = "requestable";
    private static final String ATTRIBUTE_REQUEST_DESCRIPTION = "requestDescription";
    private static final String ATTRIBUTE_REQUEST_TITLE = "requestTitle";
    private static final String ATTRIBUTE_SCHEME = "scheme";
    private static final String ATTRIBUTE_SEARCH_KEYWORDS = "searchKeywords";
    private static final String ATTRIBUTE_SHORT_LABEL = "shortLabel";
    private static final String ATTRIBUTE_SHOW_NONE = "showNone";
    private static final String ATTRIBUTE_SYSTEM_ONLY = "systemOnly";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_VISIBLE = "visible";
    private static final String LOG_TAG = RoleParser.class.getSimpleName();
    private static final String MODE_NAME_ALLOWED = "allowed";
    private static final String MODE_NAME_DEFAULT = "default";
    private static final String MODE_NAME_ERRORED = "errored";
    private static final String MODE_NAME_FOREGROUND = "foreground";
    private static final String MODE_NAME_IGNORED = "ignored";
    private static final String TAG_ACTION = "action";
    private static final String TAG_ACTIVITY = "activity";
    private static final String TAG_APP_OP = "app-op";
    private static final String TAG_APP_OPS = "app-ops";
    private static final String TAG_APP_OP_PERMISSION = "app-op-permission";
    private static final String TAG_APP_OP_PERMISSIONS = "app-op-permissions";
    private static final String TAG_CATEGORY = "category";
    private static final String TAG_DATA = "data";
    private static final String TAG_INTENT_FILTER = "intent-filter";
    private static final String TAG_PERMISSION = "permission";
    private static final String TAG_PERMISSIONS = "permissions";
    private static final String TAG_PERMISSION_SET = "permission-set";
    private static final String TAG_PREFERRED_ACTIVITIES = "preferred-activities";
    private static final String TAG_PREFERRED_ACTIVITY = "preferred-activity";
    private static final String TAG_PROVIDER = "provider";
    private static final String TAG_RECEIVER = "receiver";
    private static final String TAG_REQUIRED_COMPONENTS = "required-components";
    private static final String TAG_ROLE = "role";
    private static final String TAG_ROLES = "roles";
    private static final String TAG_SERVICE = "service";
    private static final ArrayMap<String, Integer> sModeNameToMode;
    private final Context mContext;
    private final boolean mValidationEnabled;

    static {
        ArrayMap<String, Integer> arrayMap = new ArrayMap<>();
        sModeNameToMode = arrayMap;
        arrayMap.put(MODE_NAME_ALLOWED, 0);
        sModeNameToMode.put(MODE_NAME_IGNORED, 1);
        sModeNameToMode.put(MODE_NAME_ERRORED, 2);
        sModeNameToMode.put(MODE_NAME_DEFAULT, 3);
        sModeNameToMode.put(MODE_NAME_FOREGROUND, 4);
    }

    public RoleParser(Context context) {
        this(context, false);
    }

    RoleParser(Context context, boolean validationEnabled) {
        this.mContext = context;
        this.mValidationEnabled = validationEnabled;
    }

    public ArrayMap<String, Role> parse() {
        try {
            XmlResourceParser parser = this.mContext.getResources().getXml(R.xml.roles);
            try {
                Pair<ArrayMap<String, PermissionSet>, ArrayMap<String, Role>> xml = parseXml(parser);
                if (xml == null) {
                    ArrayMap<String, Role> arrayMap = new ArrayMap<>();
                    if (parser != null) {
                        parser.close();
                    }
                    return arrayMap;
                }
                ArrayMap<String, PermissionSet> permissionSets = (ArrayMap) xml.first;
                ArrayMap<String, Role> roles = (ArrayMap) xml.second;
                validateResult(permissionSets, roles);
                if (parser != null) {
                    parser.close();
                }
                return roles;
            } catch (Throwable th) {
                if (parser != null) {
                    try {
                        parser.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException | XmlPullParserException e) {
            throwOrLogMessage("Unable to parse roles.xml", e);
            return new ArrayMap<>();
        }
    }

    private Pair<ArrayMap<String, PermissionSet>, ArrayMap<String, Role>> parseXml(XmlResourceParser parser) throws XmlPullParserException, IOException {
        int depth;
        Pair<ArrayMap<String, PermissionSet>, ArrayMap<String, Role>> xml = null;
        int innerDepth = parser.getDepth() + 1;
        while (true) {
            int type = parser.next();
            if (type == 1 || ((depth = parser.getDepth()) < innerDepth && type == 3)) {
                break;
            }
            if (depth <= innerDepth && type == 2) {
                if (parser.getName().equals(TAG_ROLES)) {
                    if (xml != null) {
                        throwOrLogMessage("Duplicate <roles>");
                        skipCurrentTag(parser);
                    } else {
                        xml = parseRoles(parser);
                    }
                } else {
                    throwOrLogForUnknownTag(parser);
                    skipCurrentTag(parser);
                }
            }
        }
        if (xml == null) {
            throwOrLogMessage("Missing <roles>");
        }
        return xml;
    }

    private Pair<ArrayMap<String, PermissionSet>, ArrayMap<String, Role>> parseRoles(XmlResourceParser parser) throws XmlPullParserException, IOException {
        int depth;
        ArrayMap<String, PermissionSet> permissionSets = new ArrayMap<>();
        ArrayMap<String, Role> roles = new ArrayMap<>();
        int innerDepth = parser.getDepth() + 1;
        while (true) {
            int type = parser.next();
            if (type == 1 || ((depth = parser.getDepth()) < innerDepth && type == 3)) {
                break;
            }
            if (depth <= innerDepth && type == 2) {
                String name = parser.getName();
                byte b = -1;
                int iHashCode = name.hashCode();
                if (iHashCode != -1439271068) {
                    if (iHashCode == 3506294 && name.equals(TAG_ROLE)) {
                        b = 1;
                    }
                } else if (name.equals(TAG_PERMISSION_SET)) {
                    b = 0;
                }
                if (b == 0) {
                    PermissionSet permissionSet = parsePermissionSet(parser);
                    if (permissionSet != null) {
                        validateNoDuplicateElement(permissionSet.getName(), permissionSets.keySet(), "permission set");
                        permissionSets.put(permissionSet.getName(), permissionSet);
                    }
                } else if (b == 1) {
                    Role role = parseRole(parser, permissionSets);
                    if (role != null) {
                        validateNoDuplicateElement(role.getName(), roles.keySet(), TAG_ROLE);
                        roles.put(role.getName(), role);
                    }
                } else {
                    throwOrLogForUnknownTag(parser);
                    skipCurrentTag(parser);
                }
            }
        }
        return new Pair<>(permissionSets, roles);
    }

    private PermissionSet parsePermissionSet(XmlResourceParser parser) throws XmlPullParserException, IOException {
        int depth;
        String name = requireAttributeValue(parser, ATTRIBUTE_NAME, TAG_PERMISSION_SET);
        if (name == null) {
            skipCurrentTag(parser);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int innerDepth = parser.getDepth() + 1;
        while (true) {
            int type = parser.next();
            if (type == 1 || ((depth = parser.getDepth()) < innerDepth && type == 3)) {
                break;
            }
            if (depth <= innerDepth && type == 2) {
                if (parser.getName().equals("permission")) {
                    String permission = requireAttributeValue(parser, ATTRIBUTE_NAME, "permission");
                    if (permission != null) {
                        validateNoDuplicateElement(permission, arrayList, "permission");
                        arrayList.add(permission);
                    }
                } else {
                    throwOrLogForUnknownTag(parser);
                    skipCurrentTag(parser);
                }
            }
        }
        return new PermissionSet(name, arrayList);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private Role parseRole(XmlResourceParser parser, ArrayMap<String, PermissionSet> permissionSets) throws XmlPullParserException, IOException {
        RoleBehavior behavior;
        Integer descriptionResource;
        Integer labelResource;
        Integer shortLabelResource;
        Integer requestTitleResource;
        Integer requestDescriptionResource;
        int type;
        List<RequiredComponent> requiredComponents;
        List<String> permissions;
        List<String> appOpPermissions;
        List<AppOp> appOps;
        List<PreferredActivity> preferredActivities;
        byte b;
        String name = requireAttributeValue(parser, ATTRIBUTE_NAME, TAG_ROLE);
        if (name != null) {
            String behaviorClassSimpleName = getAttributeValue(parser, ATTRIBUTE_BEHAVIOR);
            if (behaviorClassSimpleName != null) {
                String behaviorClassName = RoleParser.class.getPackage().getName() + '.' + behaviorClassSimpleName;
                try {
                    behavior = (RoleBehavior) Class.forName(behaviorClassName).newInstance();
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    throwOrLogMessage("Unable to instantiate behavior: " + behaviorClassName, e);
                    skipCurrentTag(parser);
                    return null;
                }
            } else {
                behavior = null;
            }
            String defaultHoldersResourceName = getAttributeValue(parser, ATTRIBUTE_DEFAULT_HOLDERS);
            int i = 1;
            boolean visible = getAttributeBooleanValue(parser, ATTRIBUTE_VISIBLE, true);
            if (visible) {
                Integer descriptionResource2 = requireAttributeResourceValue(parser, ATTRIBUTE_DESCRIPTION, 0, TAG_ROLE);
                if (descriptionResource2 != null) {
                    Integer labelResource2 = requireAttributeResourceValue(parser, "label", 0, TAG_ROLE);
                    if (labelResource2 != null) {
                        Integer shortLabelResource2 = requireAttributeResourceValue(parser, ATTRIBUTE_SHORT_LABEL, 0, TAG_ROLE);
                        if (shortLabelResource2 != null) {
                            descriptionResource = descriptionResource2;
                            labelResource = labelResource2;
                            shortLabelResource = shortLabelResource2;
                        } else {
                            skipCurrentTag(parser);
                            return null;
                        }
                    } else {
                        skipCurrentTag(parser);
                        return null;
                    }
                } else {
                    skipCurrentTag(parser);
                    return null;
                }
            } else {
                descriptionResource = 0;
                labelResource = 0;
                shortLabelResource = 0;
            }
            Boolean exclusive = requireAttributeBooleanValue(parser, ATTRIBUTE_EXCLUSIVE, true, TAG_ROLE);
            if (exclusive != null) {
                boolean fallBackToDefaultHolder = getAttributeBooleanValue(parser, ATTRIBUTE_FALL_BACK_TO_DEFAULT_HOLDER, false);
                boolean requestable = getAttributeBooleanValue(parser, ATTRIBUTE_REQUESTABLE, visible);
                if (requestable) {
                    Integer requestDescriptionResource2 = requireAttributeResourceValue(parser, ATTRIBUTE_REQUEST_DESCRIPTION, 0, TAG_ROLE);
                    if (requestDescriptionResource2 != null) {
                        requestTitleResource = requireAttributeResourceValue(parser, ATTRIBUTE_REQUEST_TITLE, 0, TAG_ROLE);
                        if (requestTitleResource != null) {
                            requestDescriptionResource = requestDescriptionResource2;
                        } else {
                            skipCurrentTag(parser);
                            return null;
                        }
                    } else {
                        skipCurrentTag(parser);
                        return null;
                    }
                } else {
                    requestTitleResource = 0;
                    requestDescriptionResource = 0;
                }
                int searchKeywordsResource = getAttributeResourceValue(parser, ATTRIBUTE_SEARCH_KEYWORDS, 0);
                boolean showNone = getAttributeBooleanValue(parser, ATTRIBUTE_SHOW_NONE, false);
                if (!showNone || exclusive.booleanValue()) {
                    boolean systemOnly = getAttributeBooleanValue(parser, ATTRIBUTE_SYSTEM_ONLY, false);
                    List<RequiredComponent> requiredComponents2 = null;
                    List<String> permissions2 = null;
                    List<String> appOpPermissions2 = null;
                    List<AppOp> appOps2 = null;
                    List<PreferredActivity> preferredActivities2 = null;
                    int innerDepth = parser.getDepth() + 1;
                    while (true) {
                        int type2 = parser.next();
                        if (type2 != i) {
                            int depth = parser.getDepth();
                            if (depth < innerDepth) {
                                type = type2;
                                if (type == 3) {
                                }
                            } else {
                                type = type2;
                            }
                            if (depth <= innerDepth && type == 2) {
                                String name2 = parser.getName();
                                switch (name2.hashCode()) {
                                    case -795106042:
                                        b = !name2.equals(TAG_APP_OPS) ? (byte) -1 : (byte) 3;
                                        break;
                                    case -162238279:
                                        b = !name2.equals(TAG_PREFERRED_ACTIVITIES) ? (byte) -1 : (byte) 4;
                                        break;
                                    case 658823108:
                                        b = !name2.equals(TAG_APP_OP_PERMISSIONS) ? (byte) -1 : (byte) 2;
                                        break;
                                    case 1133704324:
                                        b = !name2.equals(TAG_PERMISSIONS) ? (byte) -1 : (byte) 1;
                                        break;
                                    case 1920178692:
                                        b = !name2.equals(TAG_REQUIRED_COMPONENTS) ? (byte) -1 : (byte) 0;
                                        break;
                                    default:
                                        b = -1;
                                        break;
                                }
                                if (b != 0) {
                                    if (b != 1) {
                                        if (b != 2) {
                                            if (b != 3) {
                                                if (b == 4) {
                                                    if (preferredActivities2 != null) {
                                                        throwOrLogMessage("Duplicate <preferred-activities> in role: " + name);
                                                        skipCurrentTag(parser);
                                                    } else {
                                                        List<PreferredActivity> preferredActivities3 = parsePreferredActivities(parser);
                                                        preferredActivities2 = preferredActivities3;
                                                    }
                                                } else {
                                                    throwOrLogForUnknownTag(parser);
                                                    skipCurrentTag(parser);
                                                }
                                                i = 1;
                                            } else if (appOps2 != null) {
                                                throwOrLogMessage("Duplicate <app-ops> in role: " + name);
                                                skipCurrentTag(parser);
                                            } else {
                                                List<AppOp> appOps3 = parseAppOps(parser);
                                                appOps2 = appOps3;
                                                i = 1;
                                            }
                                        } else if (appOpPermissions2 != null) {
                                            throwOrLogMessage("Duplicate <app-op-permissions> in role: " + name);
                                            skipCurrentTag(parser);
                                        } else {
                                            List<String> appOpPermissions3 = parseAppOpPermissions(parser);
                                            appOpPermissions2 = appOpPermissions3;
                                            i = 1;
                                        }
                                    } else if (permissions2 != null) {
                                        throwOrLogMessage("Duplicate <permissions> in role: " + name);
                                        skipCurrentTag(parser);
                                    } else {
                                        List<String> permissions3 = parsePermissions(parser, permissionSets);
                                        permissions2 = permissions3;
                                        i = 1;
                                    }
                                } else if (requiredComponents2 != null) {
                                    throwOrLogMessage("Duplicate <required-components> in role: " + name);
                                    skipCurrentTag(parser);
                                } else {
                                    List<RequiredComponent> requiredComponents3 = parseRequiredComponents(parser);
                                    requiredComponents2 = requiredComponents3;
                                    i = 1;
                                }
                            }
                            i = 1;
                        } else {
                            type = type2;
                        }
                    }
                    if (requiredComponents2 != null) {
                        requiredComponents = requiredComponents2;
                    } else {
                        List<RequiredComponent> requiredComponents4 = Collections.emptyList();
                        requiredComponents = requiredComponents4;
                    }
                    if (permissions2 != null) {
                        permissions = permissions2;
                    } else {
                        List<String> permissions4 = Collections.emptyList();
                        permissions = permissions4;
                    }
                    if (appOpPermissions2 != null) {
                        appOpPermissions = appOpPermissions2;
                    } else {
                        List<String> appOpPermissions4 = Collections.emptyList();
                        appOpPermissions = appOpPermissions4;
                    }
                    if (appOps2 != null) {
                        appOps = appOps2;
                    } else {
                        List<AppOp> appOps4 = Collections.emptyList();
                        appOps = appOps4;
                    }
                    if (preferredActivities2 != null) {
                        preferredActivities = preferredActivities2;
                    } else {
                        List<PreferredActivity> preferredActivities4 = Collections.emptyList();
                        preferredActivities = preferredActivities4;
                    }
                    return new Role(name, behavior, defaultHoldersResourceName, descriptionResource.intValue(), exclusive.booleanValue(), fallBackToDefaultHolder, labelResource.intValue(), requestDescriptionResource.intValue(), requestTitleResource.intValue(), requestable, searchKeywordsResource, shortLabelResource.intValue(), showNone, systemOnly, visible, requiredComponents, permissions, appOpPermissions, appOps, preferredActivities);
                }
                throwOrLogMessage("showNone=\"true\" is invalid for a non-exclusive role: " + name);
                skipCurrentTag(parser);
                return null;
            }
            skipCurrentTag(parser);
            return null;
        }
        skipCurrentTag(parser);
        return null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private List<RequiredComponent> parseRequiredComponents(XmlResourceParser parser) throws XmlPullParserException, IOException {
        int depth;
        ArrayList arrayList = new ArrayList();
        int innerDepth = parser.getDepth() + 1;
        while (true) {
            int type = parser.next();
            if (type != 1 && ((depth = parser.getDepth()) >= innerDepth || type != 3)) {
                if (depth <= innerDepth && type == 2) {
                    String name = parser.getName();
                    byte b = -1;
                    switch (name.hashCode()) {
                        case -1655966961:
                            if (name.equals(TAG_ACTIVITY)) {
                                b = 0;
                            }
                            break;
                        case -987494927:
                            if (name.equals(TAG_PROVIDER)) {
                                b = 1;
                            }
                            break;
                        case -808719889:
                            if (name.equals(TAG_RECEIVER)) {
                                b = 2;
                            }
                            break;
                        case 1984153269:
                            if (name.equals("service")) {
                                b = 3;
                            }
                            break;
                    }
                    if (b == 0 || b == 1 || b == 2 || b == 3) {
                        RequiredComponent requiredComponent = parseRequiredComponent(parser, name);
                        if (requiredComponent != null) {
                            validateNoDuplicateElement(requiredComponent, arrayList, "require component");
                            arrayList.add(requiredComponent);
                        }
                    } else {
                        throwOrLogForUnknownTag(parser);
                        skipCurrentTag(parser);
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private RequiredComponent parseRequiredComponent(XmlResourceParser parser, String name) throws XmlPullParserException, IOException {
        byte b;
        int depth;
        String permission = getAttributeValue(parser, "permission");
        IntentFilterData intentFilterData = null;
        int innerDepth = parser.getDepth() + 1;
        while (true) {
            int type = parser.next();
            if (type == 1 || ((depth = parser.getDepth()) < innerDepth && type == 3)) {
                break;
            }
            if (depth <= innerDepth && type == 2) {
                String name2 = parser.getName();
                if (((name2.hashCode() == -1029793847 && name2.equals(TAG_INTENT_FILTER)) ? (byte) 0 : (byte) -1) == 0) {
                    if (intentFilterData != null) {
                        throwOrLogMessage("Duplicate <intent-filter> in <" + name + ">");
                        skipCurrentTag(parser);
                    } else {
                        intentFilterData = parseIntentFilterData(parser);
                    }
                } else {
                    throwOrLogForUnknownTag(parser);
                    skipCurrentTag(parser);
                }
            }
        }
        if (intentFilterData == null) {
            throwOrLogMessage("Missing <intent-filter> in <" + name + ">");
            return null;
        }
        switch (name.hashCode()) {
            case -1655966961:
                if (!name.equals(TAG_ACTIVITY)) {
                    b = -1;
                }
                break;
            case -987494927:
                b = !name.equals(TAG_PROVIDER) ? (byte) -1 : (byte) 1;
                break;
            case -808719889:
                b = !name.equals(TAG_RECEIVER) ? (byte) -1 : (byte) 2;
                break;
            case 1984153269:
                b = !name.equals("service") ? (byte) -1 : (byte) 3;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0) {
            return new RequiredActivity(intentFilterData, permission);
        }
        if (b == 1) {
            return new RequiredContentProvider(intentFilterData, permission);
        }
        if (b == 2) {
            return new RequiredBroadcastReceiver(intentFilterData, permission);
        }
        if (b == 3) {
            return new RequiredService(intentFilterData, permission);
        }
        throwOrLogMessage("Unknown tag <" + name + ">");
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0065 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.android.permissioncontroller.role.model.IntentFilterData parseIntentFilterData(android.content.res.XmlResourceParser r17) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = 0
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = 0
            r5 = 0
            r6 = 0
            int r7 = r17.getDepth()
            r8 = 1
            int r7 = r7 + r8
        L13:
            int r9 = r17.next()
            r10 = r9
            if (r9 == r8) goto Lb4
            int r9 = r17.getDepth()
            r11 = r9
            if (r9 >= r7) goto L24
            r9 = 3
            if (r10 == r9) goto Lb4
        L24:
            if (r11 > r7) goto L13
            r9 = 2
            if (r10 == r9) goto L2a
            goto L13
        L2a:
            java.lang.String r12 = r17.getName()
            int r14 = r12.hashCode()
            r15 = -1422950858(0xffffffffab2f7e36, float:-6.234764E-13)
            java.lang.String r13 = "action"
            java.lang.String r9 = "category"
            if (r14 == r15) goto L58
            r15 = 3076010(0x2eefaa, float:4.310408E-39)
            if (r14 == r15) goto L4e
            r15 = 50511102(0x302bcfe, float:3.842052E-37)
            if (r14 == r15) goto L46
        L45:
            goto L60
        L46:
            boolean r12 = r12.equals(r9)
            if (r12 == 0) goto L45
            r12 = r8
            goto L61
        L4e:
            java.lang.String r14 = "data"
            boolean r12 = r12.equals(r14)
            if (r12 == 0) goto L45
            r12 = 2
            goto L61
        L58:
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L45
            r12 = 0
            goto L61
        L60:
            r12 = -1
        L61:
            java.lang.String r14 = "name"
            if (r12 == 0) goto La1
            if (r12 == r8) goto L8f
            r13 = 2
            if (r12 == r13) goto L71
            r16.throwOrLogForUnknownTag(r17)
            r16.skipCurrentTag(r17)
            goto Lb2
        L71:
            if (r4 != 0) goto L86
            r4 = 1
            java.lang.String r9 = "scheme"
            java.lang.String r5 = r0.getAttributeValue(r1, r9)
            java.lang.String r9 = "mimeType"
            java.lang.String r6 = r0.getAttributeValue(r1, r9)
            if (r6 == 0) goto Lb2
            r0.validateIntentFilterDataType(r6)
            goto Lb2
        L86:
            java.lang.String r9 = "Duplicate <data> in <intent-filter>"
            r0.throwOrLogMessage(r9)
            r16.skipCurrentTag(r17)
            goto L13
        L8f:
            java.lang.String r12 = r0.requireAttributeValue(r1, r14, r9)
            if (r12 != 0) goto L97
            goto L13
        L97:
            r0.validateIntentFilterCategory(r12)
            r0.validateNoDuplicateElement(r12, r3, r9)
            r3.add(r12)
            goto Lb2
        La1:
            if (r2 == 0) goto Lad
            java.lang.String r9 = "Duplicate <action> in <intent-filter>"
            r0.throwOrLogMessage(r9)
            r16.skipCurrentTag(r17)
            goto L13
        Lad:
            java.lang.String r2 = r0.requireAttributeValue(r1, r14, r13)
        Lb2:
            goto L13
        Lb4:
            if (r2 != 0) goto Lbd
            java.lang.String r8 = "Missing <action> in <intent-filter>"
            r0.throwOrLogMessage(r8)
            r8 = 0
            return r8
        Lbd:
            com.android.permissioncontroller.role.model.IntentFilterData r8 = new com.android.permissioncontroller.role.model.IntentFilterData
            r8.<init>(r2, r3, r5, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.role.model.RoleParser.parseIntentFilterData(android.content.res.XmlResourceParser):com.android.permissioncontroller.role.model.IntentFilterData");
    }

    private void validateIntentFilterCategory(String category) {
        if (Objects.equals(category, "android.intent.category.DEFAULT")) {
            throwOrLogMessage("<category> should not include android.intent.category.DEFAULT");
        }
    }

    private void validateIntentFilterDataType(String type) {
        int slashIndex = type.indexOf(47);
        if (slashIndex <= 0 || type.length() < slashIndex + 2) {
            throwOrLogMessage("Invalid attribute \"mimeType\" value on <data>: " + type);
        }
    }

    private List<String> parsePermissions(XmlResourceParser parser, ArrayMap<String, PermissionSet> permissionSets) throws XmlPullParserException, IOException {
        int depth;
        ArrayList arrayList = new ArrayList();
        int innerDepth = parser.getDepth() + 1;
        while (true) {
            int type = parser.next();
            if (type == 1 || ((depth = parser.getDepth()) < innerDepth && type == 3)) {
                break;
            }
            if (depth <= innerDepth && type == 2) {
                String name = parser.getName();
                byte b = -1;
                int iHashCode = name.hashCode();
                if (iHashCode != -1439271068) {
                    if (iHashCode == -517618225 && name.equals("permission")) {
                        b = 1;
                    }
                } else if (name.equals(TAG_PERMISSION_SET)) {
                    b = 0;
                }
                if (b == 0) {
                    String permissionSetName = requireAttributeValue(parser, ATTRIBUTE_NAME, TAG_PERMISSION_SET);
                    if (permissionSetName != null) {
                        if (!permissionSets.containsKey(permissionSetName)) {
                            throwOrLogMessage("Unknown permission set:" + permissionSetName);
                        } else {
                            PermissionSet permissionSet = permissionSets.get(permissionSetName);
                            arrayList.addAll(permissionSet.getPermissions());
                        }
                    }
                } else if (b == 1) {
                    String permission = requireAttributeValue(parser, ATTRIBUTE_NAME, "permission");
                    if (permission != null) {
                        validateNoDuplicateElement(permission, arrayList, "permission");
                        arrayList.add(permission);
                    }
                } else {
                    throwOrLogForUnknownTag(parser);
                    skipCurrentTag(parser);
                }
            }
        }
        return arrayList;
    }

    private List<String> parseAppOpPermissions(XmlResourceParser parser) throws XmlPullParserException, IOException {
        int depth;
        ArrayList arrayList = new ArrayList();
        int innerDepth = parser.getDepth() + 1;
        while (true) {
            int type = parser.next();
            if (type == 1 || ((depth = parser.getDepth()) < innerDepth && type == 3)) {
                break;
            }
            if (depth <= innerDepth && type == 2) {
                if (parser.getName().equals(TAG_APP_OP_PERMISSION)) {
                    String appOpPermission = requireAttributeValue(parser, ATTRIBUTE_NAME, TAG_APP_OP_PERMISSION);
                    if (appOpPermission != null) {
                        validateNoDuplicateElement(appOpPermission, arrayList, "app op permission");
                        arrayList.add(appOpPermission);
                        break;
                    }
                } else {
                    throwOrLogForUnknownTag(parser);
                    skipCurrentTag(parser);
                }
            }
        }
        return arrayList;
    }

    private List<AppOp> parseAppOps(XmlResourceParser parser) throws XmlPullParserException, IOException {
        int depth;
        ArrayList arrayList = new ArrayList();
        List<AppOp> appOps = new ArrayList<>();
        int innerDepth = parser.getDepth() + 1;
        while (true) {
            int type = parser.next();
            if (type == 1 || ((depth = parser.getDepth()) < innerDepth && type == 3)) {
                break;
            }
            if (depth <= innerDepth && type == 2) {
                if (parser.getName().equals(TAG_APP_OP)) {
                    String name = requireAttributeValue(parser, ATTRIBUTE_NAME, TAG_APP_OP);
                    if (name != null) {
                        validateNoDuplicateElement(name, arrayList, "app op");
                        arrayList.add(name);
                        Integer maxTargetSdkVersion = Integer.valueOf(getAttributeIntValue(parser, ATTRIBUTE_MAX_TARGET_SDK_VERSION, Integer.MIN_VALUE));
                        if (maxTargetSdkVersion.intValue() == Integer.MIN_VALUE) {
                            maxTargetSdkVersion = null;
                        }
                        if (maxTargetSdkVersion != null && maxTargetSdkVersion.intValue() < 1) {
                            throwOrLogMessage("Invalid value for \"maxTargetSdkVersion\": " + maxTargetSdkVersion);
                        }
                        String modeName = requireAttributeValue(parser, ATTRIBUTE_MODE, TAG_APP_OP);
                        if (modeName != null) {
                            int modeIndex = sModeNameToMode.indexOfKey(modeName);
                            if (modeIndex < 0) {
                                throwOrLogMessage("Unknown value for \"mode\" on <app-op>: " + modeName);
                            } else {
                                int mode = sModeNameToMode.valueAt(modeIndex).intValue();
                                AppOp appOp = new AppOp(name, maxTargetSdkVersion, mode);
                                appOps.add(appOp);
                            }
                        }
                    }
                } else {
                    throwOrLogForUnknownTag(parser);
                    skipCurrentTag(parser);
                }
            }
        }
        return appOps;
    }

    private List<PreferredActivity> parsePreferredActivities(XmlResourceParser parser) throws XmlPullParserException, IOException {
        int depth;
        ArrayList arrayList = new ArrayList();
        int innerDepth = parser.getDepth() + 1;
        while (true) {
            int type = parser.next();
            if (type == 1 || ((depth = parser.getDepth()) < innerDepth && type == 3)) {
                break;
            }
            if (depth <= innerDepth && type == 2) {
                if (parser.getName().equals(TAG_PREFERRED_ACTIVITY)) {
                    PreferredActivity preferredActivity = parsePreferredActivity(parser);
                    if (preferredActivity != null) {
                        validateNoDuplicateElement(preferredActivity, arrayList, "preferred activity");
                        arrayList.add(preferredActivity);
                    }
                } else {
                    throwOrLogForUnknownTag(parser);
                    skipCurrentTag(parser);
                }
            }
        }
        return arrayList;
    }

    private PreferredActivity parsePreferredActivity(XmlResourceParser parser) throws XmlPullParserException, IOException {
        int depth;
        RequiredActivity activity = null;
        ArrayList arrayList = new ArrayList();
        int innerDepth = parser.getDepth() + 1;
        while (true) {
            int type = parser.next();
            if (type == 1 || ((depth = parser.getDepth()) < innerDepth && type == 3)) {
                break;
            }
            if (depth <= innerDepth && type == 2) {
                String name = parser.getName();
                byte b = -1;
                int iHashCode = name.hashCode();
                if (iHashCode != -1655966961) {
                    if (iHashCode == -1029793847 && name.equals(TAG_INTENT_FILTER)) {
                        b = 1;
                    }
                } else if (name.equals(TAG_ACTIVITY)) {
                    b = 0;
                }
                if (b != 0) {
                    if (b == 1) {
                        IntentFilterData intentFilterData = parseIntentFilterData(parser);
                        if (intentFilterData != null) {
                            validateNoDuplicateElement(intentFilterData, arrayList, "intent filter");
                            if (intentFilterData.getDataType() != null) {
                                throwOrLogMessage("mimeType in <data> is not supported when setting a preferred activity");
                            }
                            arrayList.add(intentFilterData);
                        }
                    } else {
                        throwOrLogForUnknownTag(parser);
                        skipCurrentTag(parser);
                    }
                } else if (activity != null) {
                    throwOrLogMessage("Duplicate <activity> in <preferred-activity>");
                    skipCurrentTag(parser);
                } else {
                    activity = (RequiredActivity) parseRequiredComponent(parser, TAG_ACTIVITY);
                }
            }
        }
        if (activity == null) {
            throwOrLogMessage("Missing <activity> in <preferred-activity>");
            return null;
        }
        if (arrayList.isEmpty()) {
            throwOrLogMessage("Missing <intent-filter> in <preferred-activity>");
            return null;
        }
        return new PreferredActivity(activity, arrayList);
    }

    private void skipCurrentTag(XmlResourceParser parser) throws XmlPullParserException, IOException {
        int innerDepth = parser.getDepth() + 1;
        while (true) {
            int type = parser.next();
            if (type != 1) {
                if (parser.getDepth() < innerDepth && type == 3) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private String getAttributeValue(XmlResourceParser parser, String name) {
        return parser.getAttributeValue(null, name);
    }

    private String requireAttributeValue(XmlResourceParser parser, String name, String tagName) {
        String value = getAttributeValue(parser, name);
        if (value == null) {
            throwOrLogMessage("Missing attribute \"" + name + "\" on <" + tagName + ">");
        }
        return value;
    }

    private boolean getAttributeBooleanValue(XmlResourceParser parser, String name, boolean defaultValue) {
        return parser.getAttributeBooleanValue(null, name, defaultValue);
    }

    private Boolean requireAttributeBooleanValue(XmlResourceParser parser, String name, boolean defaultValue, String tagName) {
        String value = requireAttributeValue(parser, name, tagName);
        if (value == null) {
            return null;
        }
        return Boolean.valueOf(getAttributeBooleanValue(parser, name, defaultValue));
    }

    private int getAttributeIntValue(XmlResourceParser parser, String name, int defaultValue) {
        return parser.getAttributeIntValue(null, name, defaultValue);
    }

    private int getAttributeResourceValue(XmlResourceParser parser, String name, int defaultValue) {
        return parser.getAttributeResourceValue(null, name, defaultValue);
    }

    private Integer requireAttributeResourceValue(XmlResourceParser parser, String name, int defaultValue, String tagName) {
        String value = requireAttributeValue(parser, name, tagName);
        if (value == null) {
            return null;
        }
        return Integer.valueOf(getAttributeResourceValue(parser, name, defaultValue));
    }

    private <T> void validateNoDuplicateElement(T element, Collection<T> collection, String name) {
        if (collection.contains(element)) {
            throwOrLogMessage("Duplicate " + name + ": " + element);
        }
    }

    private void throwOrLogMessage(String message) {
        if (this.mValidationEnabled) {
            throw new IllegalArgumentException(message);
        }
        Log.wtf(LOG_TAG, message);
    }

    private void throwOrLogMessage(String message, Throwable cause) {
        if (this.mValidationEnabled) {
            throw new IllegalArgumentException(message, cause);
        }
        Log.wtf(LOG_TAG, message, cause);
    }

    private void throwOrLogForUnknownTag(XmlResourceParser parser) {
        throwOrLogMessage("Unknown tag: " + parser.getName());
    }

    private void validateResult(ArrayMap<String, PermissionSet> permissionSets, ArrayMap<String, Role> roles) {
        RoleParser roleParser = this;
        if (!roleParser.mValidationEnabled) {
            return;
        }
        int permissionSetsSize = permissionSets.size();
        for (int permissionSetsIndex = 0; permissionSetsIndex < permissionSetsSize; permissionSetsIndex++) {
            PermissionSet permissionSet = permissionSets.valueAt(permissionSetsIndex);
            List<String> permissions = permissionSet.getPermissions();
            int permissionsSize = permissions.size();
            for (int permissionsIndex = 0; permissionsIndex < permissionsSize; permissionsIndex++) {
                roleParser.validatePermission(permissions.get(permissionsIndex));
            }
        }
        int rolesSize = roles.size();
        int rolesIndex = 0;
        while (rolesIndex < rolesSize) {
            Role role = roles.valueAt(rolesIndex);
            List<RequiredComponent> requiredComponents = role.getRequiredComponents();
            int requiredComponentsSize = requiredComponents.size();
            for (int requiredComponentsIndex = 0; requiredComponentsIndex < requiredComponentsSize; requiredComponentsIndex++) {
                RequiredComponent requiredComponent = requiredComponents.get(requiredComponentsIndex);
                String permission = requiredComponent.getPermission();
                if (permission != null) {
                    roleParser.validatePermission(permission);
                }
            }
            List<String> permissions2 = role.getPermissions();
            int permissionsSize2 = permissions2.size();
            for (int i = 0; i < permissionsSize2; i++) {
                roleParser.validatePermission(permissions2.get(i));
            }
            List<AppOp> appOps = role.getAppOps();
            int appOpsSize = appOps.size();
            for (int i2 = 0; i2 < appOpsSize; i2++) {
                AppOp appOp = appOps.get(i2);
                roleParser.validateAppOp(appOp);
            }
            List<String> appOpPermissions = role.getAppOpPermissions();
            int appOpPermissionsSize = appOpPermissions.size();
            int i3 = 0;
            while (i3 < appOpPermissionsSize) {
                int permissionSetsSize2 = permissionSetsSize;
                String appOpPermission = appOpPermissions.get(i3);
                roleParser.validateAppOpPermission(appOpPermission);
                i3++;
                permissionSetsSize = permissionSetsSize2;
            }
            int permissionSetsSize3 = permissionSetsSize;
            List<PreferredActivity> preferredActivities = role.getPreferredActivities();
            int preferredActivitiesSize = preferredActivities.size();
            int preferredActivitiesIndex = 0;
            while (preferredActivitiesIndex < preferredActivitiesSize) {
                List<PreferredActivity> preferredActivities2 = preferredActivities;
                PreferredActivity preferredActivity = preferredActivities.get(preferredActivitiesIndex);
                int rolesSize2 = rolesSize;
                if (role.getRequiredComponents().contains(preferredActivity.getActivity())) {
                    preferredActivitiesIndex++;
                    rolesSize = rolesSize2;
                    preferredActivities = preferredActivities2;
                } else {
                    throw new IllegalArgumentException("<activity> of <preferred-activity> not required in <required-components>, role: " + role.getName() + ", preferred activity: " + preferredActivity);
                }
            }
            rolesIndex++;
            roleParser = this;
            permissionSetsSize = permissionSetsSize3;
        }
    }

    private void validatePermission(String permission) {
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            packageManager.getPermissionInfo(permission, 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalArgumentException("Unknown permission: " + permission, e);
        }
    }

    private void validateAppOpPermission(String appOpPermission) {
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            PermissionInfo permissionInfo = packageManager.getPermissionInfo(appOpPermission, 0);
            if ((permissionInfo.getProtectionFlags() & 64) != 64) {
                throw new IllegalArgumentException("Permission is not an app op permission: " + appOpPermission);
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalArgumentException("Unknown app op permission: " + appOpPermission, e);
        }
    }

    private void validateAppOp(AppOp appOp) {
        String permission = AppOpsManager.opToPermission(appOp.getName());
        if (permission != null) {
            PackageManager packageManager = this.mContext.getPackageManager();
            try {
                PermissionInfo permissionInfo = packageManager.getPermissionInfo(permission, 0);
                if (permissionInfo.getProtection() == 1) {
                    throw new IllegalArgumentException("App op has an associated runtime permission: " + appOp.getName());
                }
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
