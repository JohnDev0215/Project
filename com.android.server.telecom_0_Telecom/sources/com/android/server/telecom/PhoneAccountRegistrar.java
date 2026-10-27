package com.android.server.telecom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.UserInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.telecom.Log;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.AtomicFile;
import android.util.Base64;
import android.util.Xml;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.FastXmlSerializer;
import com.android.internal.util.IndentingPrintWriter;
import com.android.internal.util.XmlUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public class PhoneAccountRegistrar {

    @VisibleForTesting
    public static final int EXPECTED_STATE_VERSION = 9;

    @VisibleForTesting
    public static final XmlSerialization<DefaultPhoneAccountHandle> sDefaultPhoneAcountHandleXml;

    @VisibleForTesting
    public static final XmlSerialization<PhoneAccountHandle> sPhoneAccountHandleXml;

    @VisibleForTesting
    public static final XmlSerialization<PhoneAccount> sPhoneAccountXml;

    @VisibleForTesting
    public static final XmlSerialization<State> sStateXml;
    private final AppLabelProxy mAppLabelProxy;
    private final AtomicFile mAtomicFile;
    private final Context mContext;
    private UserHandle mCurrentUserHandle;
    private final DefaultDialerCache mDefaultDialerCache;
    private final List<Listener> mListeners;
    private State mState;
    private final SubscriptionManager mSubscriptionManager;
    private String mTestPhoneAccountPackageNameFilter;
    private final UserManager mUserManager;
    private final PhoneAccountRegistrarWriteLock mWriteLock;

    public static abstract class Listener {
        public void onAccountsChanged(PhoneAccountRegistrar phoneAccountRegistrar) {
        }

        public void onDefaultOutgoingChanged(PhoneAccountRegistrar phoneAccountRegistrar) {
        }

        public void onPhoneAccountChanged(PhoneAccountRegistrar phoneAccountRegistrar, PhoneAccount phoneAccount) {
        }

        public void onPhoneAccountRegistered(PhoneAccountRegistrar phoneAccountRegistrar, PhoneAccountHandle phoneAccountHandle) {
        }

        public void onPhoneAccountUnRegistered(PhoneAccountRegistrar phoneAccountRegistrar, PhoneAccountHandle phoneAccountHandle) {
        }
    }

    private interface PhoneAccountRegistrarWriteLock {
    }

    @VisibleForTesting
    public static class State {
        public int versionNumber;
        public final Map<UserHandle, DefaultPhoneAccountHandle> defaultOutgoingAccountHandles = new ConcurrentHashMap();
        public final List<PhoneAccount> accounts = new CopyOnWriteArrayList();
    }

    static {
        new PhoneAccountHandle(new ComponentName("null", "null"), "NO_ACCOUNT_SELECTED");
        sStateXml = new XmlSerialization<State>() { // from class: com.android.server.telecom.PhoneAccountRegistrar.2
            @Override // com.android.server.telecom.PhoneAccountRegistrar.XmlSerialization
            public void writeToXml(State state, XmlSerializer xmlSerializer, Context context) throws IOException {
                if (state != null) {
                    xmlSerializer.startTag(null, "phone_account_registrar_state");
                    xmlSerializer.attribute(null, "version", Objects.toString(9));
                    xmlSerializer.startTag(null, "default_outgoing");
                    Iterator<DefaultPhoneAccountHandle> it = state.defaultOutgoingAccountHandles.values().iterator();
                    while (it.hasNext()) {
                        PhoneAccountRegistrar.sDefaultPhoneAcountHandleXml.writeToXml(it.next(), xmlSerializer, context);
                    }
                    xmlSerializer.endTag(null, "default_outgoing");
                    xmlSerializer.startTag(null, "accounts");
                    Iterator<PhoneAccount> it2 = state.accounts.iterator();
                    while (it2.hasNext()) {
                        PhoneAccountRegistrar.sPhoneAccountXml.writeToXml(it2.next(), xmlSerializer, context);
                    }
                    xmlSerializer.endTag(null, "accounts");
                    xmlSerializer.endTag(null, "phone_account_registrar_state");
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.server.telecom.PhoneAccountRegistrar.XmlSerialization
            public State readFromXml(XmlPullParser xmlPullParser, int i, Context context) throws XmlPullParserException, IOException {
                List<PhoneAccount> list;
                if (!xmlPullParser.getName().equals("phone_account_registrar_state")) {
                    return null;
                }
                State state = new State();
                String attributeValue = xmlPullParser.getAttributeValue(null, "version");
                state.versionNumber = TextUtils.isEmpty(attributeValue) ? 1 : Integer.parseInt(attributeValue);
                int depth = xmlPullParser.getDepth();
                while (XmlUtils.nextElementWithin(xmlPullParser, depth)) {
                    if (xmlPullParser.getName().equals("default_outgoing")) {
                        if (state.versionNumber < 9) {
                            xmlPullParser.nextTag();
                            PhoneAccountHandle fromXml = PhoneAccountRegistrar.sPhoneAccountHandleXml.readFromXml(xmlPullParser, state.versionNumber, context);
                            UserInfo primaryUser = UserManager.get(context).getPrimaryUser();
                            if (primaryUser != null) {
                                UserHandle userHandle = primaryUser.getUserHandle();
                                state.defaultOutgoingAccountHandles.put(userHandle, new DefaultPhoneAccountHandle(userHandle, fromXml, ""));
                            }
                        } else {
                            int depth2 = xmlPullParser.getDepth();
                            while (XmlUtils.nextElementWithin(xmlPullParser, depth2)) {
                                DefaultPhoneAccountHandle fromXml2 = PhoneAccountRegistrar.sDefaultPhoneAcountHandleXml.readFromXml(xmlPullParser, state.versionNumber, context);
                                if (fromXml2 != null && state.accounts != null) {
                                    state.defaultOutgoingAccountHandles.put(fromXml2.userHandle, fromXml2);
                                }
                            }
                        }
                    } else if (xmlPullParser.getName().equals("accounts")) {
                        int depth3 = xmlPullParser.getDepth();
                        while (XmlUtils.nextElementWithin(xmlPullParser, depth3)) {
                            PhoneAccount fromXml3 = PhoneAccountRegistrar.sPhoneAccountXml.readFromXml(xmlPullParser, state.versionNumber, context);
                            if (fromXml3 != null && (list = state.accounts) != null) {
                                list.add(fromXml3);
                            }
                        }
                    }
                }
                return state;
            }
        };
        sDefaultPhoneAcountHandleXml = new XmlSerialization<DefaultPhoneAccountHandle>() { // from class: com.android.server.telecom.PhoneAccountRegistrar.3
            @Override // com.android.server.telecom.PhoneAccountRegistrar.XmlSerialization
            public void writeToXml(DefaultPhoneAccountHandle defaultPhoneAccountHandle, XmlSerializer xmlSerializer, Context context) throws IOException {
                if (defaultPhoneAccountHandle != null) {
                    long serialNumberForUser = UserManager.get(context).getSerialNumberForUser(defaultPhoneAccountHandle.userHandle);
                    if (serialNumberForUser != -1) {
                        xmlSerializer.startTag(null, "default_outgoing_phone_account_handle");
                        writeLong("user_serial_number", serialNumberForUser, xmlSerializer);
                        writeNonNullString("group_id", defaultPhoneAccountHandle.groupId, xmlSerializer);
                        xmlSerializer.startTag(null, "account_handle");
                        PhoneAccountRegistrar.sPhoneAccountHandleXml.writeToXml(defaultPhoneAccountHandle.phoneAccountHandle, xmlSerializer, context);
                        xmlSerializer.endTag(null, "account_handle");
                        xmlSerializer.endTag(null, "default_outgoing_phone_account_handle");
                    }
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.server.telecom.PhoneAccountRegistrar.XmlSerialization
            public DefaultPhoneAccountHandle readFromXml(XmlPullParser xmlPullParser, int i, Context context) throws XmlPullParserException, IOException {
                UserHandle userForSerialNumber;
                if (xmlPullParser.getName().equals("default_outgoing_phone_account_handle")) {
                    int depth = xmlPullParser.getDepth();
                    String text = "";
                    String text2 = null;
                    PhoneAccountHandle fromXml = null;
                    while (XmlUtils.nextElementWithin(xmlPullParser, depth)) {
                        if (xmlPullParser.getName().equals("account_handle")) {
                            xmlPullParser.nextTag();
                            fromXml = PhoneAccountRegistrar.sPhoneAccountHandleXml.readFromXml(xmlPullParser, i, context);
                        } else if (xmlPullParser.getName().equals("user_serial_number")) {
                            xmlPullParser.next();
                            text2 = xmlPullParser.getText();
                        } else if (xmlPullParser.getName().equals("group_id") && xmlPullParser.next() == 4) {
                            text = xmlPullParser.getText();
                        }
                    }
                    if (text2 != null) {
                        try {
                            userForSerialNumber = UserManager.get(context).getUserForSerialNumber(Long.parseLong(text2));
                        } catch (NumberFormatException e) {
                            Log.e(this, e, "Could not parse UserHandle " + text2, new Object[0]);
                            userForSerialNumber = null;
                        }
                        if (fromXml != null && userForSerialNumber != null && text != null) {
                            return new DefaultPhoneAccountHandle(userForSerialNumber, fromXml, text);
                        }
                    } else {
                        userForSerialNumber = null;
                        if (fromXml != null) {
                            return new DefaultPhoneAccountHandle(userForSerialNumber, fromXml, text);
                        }
                    }
                }
                return null;
            }
        };
        sPhoneAccountXml = new XmlSerialization<PhoneAccount>() { // from class: com.android.server.telecom.PhoneAccountRegistrar.4
            @Override // com.android.server.telecom.PhoneAccountRegistrar.XmlSerialization
            public void writeToXml(PhoneAccount phoneAccount, XmlSerializer xmlSerializer, Context context) throws IOException {
                if (phoneAccount != null) {
                    xmlSerializer.startTag(null, "phone_account");
                    if (phoneAccount.getAccountHandle() != null) {
                        xmlSerializer.startTag(null, "account_handle");
                        PhoneAccountRegistrar.sPhoneAccountHandleXml.writeToXml(phoneAccount.getAccountHandle(), xmlSerializer, context);
                        xmlSerializer.endTag(null, "account_handle");
                    }
                    writeTextIfNonNull("handle", phoneAccount.getAddress(), xmlSerializer);
                    writeTextIfNonNull("subscription_number", phoneAccount.getSubscriptionAddress(), xmlSerializer);
                    writeTextIfNonNull("capabilities", Integer.toString(phoneAccount.getCapabilities()), xmlSerializer);
                    writeIconIfNonNull("icon", phoneAccount.getIcon(), xmlSerializer);
                    writeTextIfNonNull("highlight_color", Integer.toString(phoneAccount.getHighlightColor()), xmlSerializer);
                    writeTextIfNonNull("label", phoneAccount.getLabel(), xmlSerializer);
                    writeTextIfNonNull("short_description", phoneAccount.getShortDescription(), xmlSerializer);
                    writeStringList("supported_uri_schemes", phoneAccount.getSupportedUriSchemes(), xmlSerializer);
                    writeBundle("extras", phoneAccount.getExtras(), xmlSerializer);
                    writeTextIfNonNull("enabled", phoneAccount.isEnabled() ? "true" : "false", xmlSerializer);
                    writeTextIfNonNull("supported_audio_routes", Integer.toString(phoneAccount.getSupportedAudioRoutes()), xmlSerializer);
                    xmlSerializer.endTag(null, "phone_account");
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.server.telecom.PhoneAccountRegistrar.XmlSerialization
            public PhoneAccount readFromXml(XmlPullParser xmlPullParser, int i, Context context) throws XmlPullParserException, IOException {
                Icon icon;
                Bundle bundle;
                List<String> arrayList;
                if (!xmlPullParser.getName().equals("phone_account")) {
                    return null;
                }
                int depth = xmlPullParser.getDepth();
                int i2 = -1;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                boolean zEqualsIgnoreCase = false;
                String text = null;
                Icon icon2 = null;
                Bundle bundle2 = null;
                PhoneAccountHandle phoneAccountHandle = null;
                Bitmap bitmap = null;
                Uri uri = null;
                Uri uri2 = null;
                String text2 = null;
                String text3 = null;
                List<String> stringList = null;
                while (XmlUtils.nextElementWithin(xmlPullParser, depth)) {
                    int i6 = depth;
                    String str = text2;
                    if (xmlPullParser.getName().equals("account_handle")) {
                        xmlPullParser.nextTag();
                        phoneAccountHandle = PhoneAccountRegistrar.sPhoneAccountHandleXml.readFromXml(xmlPullParser, i, context);
                    } else if (xmlPullParser.getName().equals("handle")) {
                        xmlPullParser.next();
                        uri = Uri.parse(xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("subscription_number")) {
                        xmlPullParser.next();
                        String text4 = xmlPullParser.getText();
                        uri2 = text4 == null ? null : Uri.parse(text4);
                    } else if (xmlPullParser.getName().equals("capabilities")) {
                        xmlPullParser.next();
                        i3 = Integer.parseInt(xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("icon_res_id")) {
                        xmlPullParser.next();
                        i2 = Integer.parseInt(xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("icon_package_name")) {
                        xmlPullParser.next();
                        text2 = xmlPullParser.getText();
                        depth = i6;
                    } else if (xmlPullParser.getName().equals("icon_bitmap")) {
                        xmlPullParser.next();
                        bitmap = readBitmap(xmlPullParser);
                    } else if (xmlPullParser.getName().equals("icon_tint")) {
                        xmlPullParser.next();
                        Integer.parseInt(xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("highlight_color")) {
                        xmlPullParser.next();
                        i4 = Integer.parseInt(xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("label")) {
                        xmlPullParser.next();
                        text3 = xmlPullParser.getText();
                    } else if (xmlPullParser.getName().equals("short_description")) {
                        xmlPullParser.next();
                        text = xmlPullParser.getText();
                    } else if (xmlPullParser.getName().equals("supported_uri_schemes")) {
                        stringList = readStringList(xmlPullParser);
                    } else if (xmlPullParser.getName().equals("icon")) {
                        xmlPullParser.next();
                        icon2 = readIcon(xmlPullParser);
                    } else if (xmlPullParser.getName().equals("enabled")) {
                        xmlPullParser.next();
                        zEqualsIgnoreCase = "true".equalsIgnoreCase(xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("extras")) {
                        bundle2 = readBundle(xmlPullParser);
                    } else if (xmlPullParser.getName().equals("supported_audio_routes")) {
                        xmlPullParser.next();
                        i5 = Integer.parseInt(xmlPullParser.getText());
                    }
                    depth = i6;
                    text2 = str;
                }
                String str2 = text2;
                ComponentName componentName = new ComponentName("com.android.phone", "com.android.services.telephony.TelephonyConnectionService");
                int i7 = i2;
                ComponentName componentName2 = new ComponentName("com.android.phone", "com.android.services.telephony.sip.SipConnectionService");
                if (i < 2) {
                    arrayList = new ArrayList<>();
                    icon = icon2;
                    bundle = bundle2;
                    if (phoneAccountHandle.getComponentName().equals(componentName2)) {
                        boolean zUseSipForPstnCalls = useSipForPstnCalls(context);
                        arrayList.add("sip");
                        if (zUseSipForPstnCalls) {
                            arrayList.add("tel");
                        }
                    } else {
                        arrayList.add("tel");
                        arrayList.add("voicemail");
                    }
                } else {
                    icon = icon2;
                    bundle = bundle2;
                    arrayList = stringList;
                }
                String packageName = (i >= 5 || bitmap != null) ? str2 : phoneAccountHandle.getComponentName().getPackageName();
                if (i < 6 && phoneAccountHandle.getComponentName().equals(componentName2)) {
                    zEqualsIgnoreCase = true;
                }
                boolean z = (i >= 7 || !phoneAccountHandle.getComponentName().equals(componentName)) ? zEqualsIgnoreCase : true;
                if (i < 8 && phoneAccountHandle.getComponentName().equals(componentName2)) {
                    Uri uri3 = Uri.parse(phoneAccountHandle.getId());
                    if (uri3.getScheme() != null && uri3.getScheme().equals("sip")) {
                        phoneAccountHandle = new PhoneAccountHandle(phoneAccountHandle.getComponentName(), uri3.getSchemeSpecificPart(), phoneAccountHandle.getUserHandle());
                    }
                }
                PhoneAccount.Builder isEnabled = PhoneAccount.builder(phoneAccountHandle, text3).setAddress(uri).setSubscriptionAddress(uri2).setCapabilities(i3).setSupportedAudioRoutes(i < 9 ? 15 : i5).setShortDescription(text).setSupportedUriSchemes(arrayList).setHighlightColor(i4).setExtras(bundle).setIsEnabled(z);
                if (icon != null) {
                    isEnabled.setIcon(icon);
                } else if (bitmap != null) {
                    isEnabled.setIcon(Icon.createWithBitmap(bitmap));
                } else if (!TextUtils.isEmpty(packageName)) {
                    isEnabled.setIcon(Icon.createWithResource(packageName, i7));
                }
                return isEnabled.build();
            }

            private boolean useSipForPstnCalls(Context context) {
                String string = Settings.System.getString(context.getContentResolver(), "sip_call_options");
                if (string == null) {
                    string = "SIP_ADDRESS_ONLY";
                }
                return string.equals("SIP_ALWAYS");
            }
        };
        sPhoneAccountHandleXml = new XmlSerialization<PhoneAccountHandle>() { // from class: com.android.server.telecom.PhoneAccountRegistrar.5
            @Override // com.android.server.telecom.PhoneAccountRegistrar.XmlSerialization
            public void writeToXml(PhoneAccountHandle phoneAccountHandle, XmlSerializer xmlSerializer, Context context) throws IOException {
                if (phoneAccountHandle != null) {
                    xmlSerializer.startTag(null, "phone_account_handle");
                    if (phoneAccountHandle.getComponentName() != null) {
                        writeTextIfNonNull("component_name", phoneAccountHandle.getComponentName().flattenToString(), xmlSerializer);
                    }
                    writeTextIfNonNull("id", phoneAccountHandle.getId(), xmlSerializer);
                    if (phoneAccountHandle.getUserHandle() != null && context != null) {
                        writeLong("user_serial_number", UserManager.get(context).getSerialNumberForUser(phoneAccountHandle.getUserHandle()), xmlSerializer);
                    }
                    xmlSerializer.endTag(null, "phone_account_handle");
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.server.telecom.PhoneAccountRegistrar.XmlSerialization
            public PhoneAccountHandle readFromXml(XmlPullParser xmlPullParser, int i, Context context) throws XmlPullParserException, IOException {
                UserHandle userForSerialNumber = null;
                if (xmlPullParser.getName().equals("phone_account_handle")) {
                    int depth = xmlPullParser.getDepth();
                    UserManager userManager = UserManager.get(context);
                    String text = null;
                    String text2 = null;
                    String text3 = null;
                    while (XmlUtils.nextElementWithin(xmlPullParser, depth)) {
                        if (xmlPullParser.getName().equals("component_name")) {
                            xmlPullParser.next();
                            text = xmlPullParser.getText();
                        } else if (xmlPullParser.getName().equals("id")) {
                            xmlPullParser.next();
                            text3 = xmlPullParser.getText();
                        } else if (xmlPullParser.getName().equals("user_serial_number")) {
                            xmlPullParser.next();
                            text2 = xmlPullParser.getText();
                        }
                    }
                    if (text != null) {
                        if (text2 != null) {
                            try {
                                userForSerialNumber = userManager.getUserForSerialNumber(Long.parseLong(text2));
                            } catch (NumberFormatException e) {
                                Log.e(this, e, "Could not parse UserHandle " + text2, new Object[0]);
                            }
                        }
                        return new PhoneAccountHandle(ComponentName.unflattenFromString(text), text3, userForSerialNumber);
                    }
                }
                return null;
            }
        };
    }

    @VisibleForTesting
    public PhoneAccountRegistrar(Context context, DefaultDialerCache defaultDialerCache, AppLabelProxy appLabelProxy) {
        this(context, "phone-account-registrar-state.xml", defaultDialerCache, appLabelProxy);
    }

    @VisibleForTesting
    public PhoneAccountRegistrar(Context context, String str, DefaultDialerCache defaultDialerCache, AppLabelProxy appLabelProxy) {
        this.mListeners = new CopyOnWriteArrayList();
        this.mWriteLock = new PhoneAccountRegistrarWriteLock(this) { // from class: com.android.server.telecom.PhoneAccountRegistrar.1
        };
        this.mAtomicFile = new AtomicFile(new File(context.getFilesDir(), str));
        this.mState = new State();
        this.mContext = context;
        this.mUserManager = UserManager.get(context);
        this.mDefaultDialerCache = defaultDialerCache;
        this.mSubscriptionManager = SubscriptionManager.from(this.mContext);
        this.mAppLabelProxy = appLabelProxy;
        this.mCurrentUserHandle = Process.myUserHandle();
        read();
    }

    public int getSubscriptionIdForPhoneAccount(PhoneAccountHandle phoneAccountHandle) {
        PhoneAccount phoneAccountUnchecked = getPhoneAccountUnchecked(phoneAccountHandle);
        if (phoneAccountUnchecked == null || !phoneAccountUnchecked.hasCapabilities(4)) {
            return -1;
        }
        return ((TelephonyManager) this.mContext.getSystemService("phone")).getSubscriptionId(phoneAccountHandle);
    }

    public PhoneAccountHandle getOutgoingPhoneAccountForScheme(String str, UserHandle userHandle) {
        PhoneAccountHandle userSelectedOutgoingPhoneAccount = getUserSelectedOutgoingPhoneAccount(userHandle);
        if (userSelectedOutgoingPhoneAccount != null && getPhoneAccountUnchecked(userSelectedOutgoingPhoneAccount).supportsUriScheme(str)) {
            return userSelectedOutgoingPhoneAccount;
        }
        List<PhoneAccountHandle> callCapablePhoneAccounts = getCallCapablePhoneAccounts(str, false, userHandle);
        if (callCapablePhoneAccounts.size() != 1) {
            return null;
        }
        return callCapablePhoneAccounts.get(0);
    }

    public PhoneAccountHandle getOutgoingPhoneAccountForSchemeOfCurrentUser(String str) {
        return getOutgoingPhoneAccountForScheme(str, this.mCurrentUserHandle);
    }

    @VisibleForTesting
    public PhoneAccountHandle getUserSelectedOutgoingPhoneAccount(UserHandle userHandle) {
        DefaultPhoneAccountHandle defaultPhoneAccountHandle;
        if (userHandle == null || (defaultPhoneAccountHandle = this.mState.defaultOutgoingAccountHandles.get(userHandle)) == null || getPhoneAccount(defaultPhoneAccountHandle.phoneAccountHandle, userHandle) == null) {
            return null;
        }
        return defaultPhoneAccountHandle.phoneAccountHandle;
    }

    private DefaultPhoneAccountHandle getUserSelectedDefaultPhoneAccount(UserHandle userHandle) {
        DefaultPhoneAccountHandle defaultPhoneAccountHandle;
        if (userHandle == null || (defaultPhoneAccountHandle = this.mState.defaultOutgoingAccountHandles.get(userHandle)) == null) {
            return null;
        }
        return defaultPhoneAccountHandle;
    }

    private PhoneAccount getPhoneAccountByGroupId(final String str, final ComponentName componentName, UserHandle userHandle, final PhoneAccountHandle phoneAccountHandle) {
        if (str == null || str.isEmpty() || userHandle == null) {
            return null;
        }
        List list = (List) getAllPhoneAccounts(userHandle).stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$PhoneAccountRegistrar$7FlMwaCBCWyiNK5CNbtzp7-HGn8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PhoneAccountRegistrar.lambda$getPhoneAccountByGroupId$0(str, phoneAccountHandle, componentName, (PhoneAccount) obj);
            }
        }).collect(Collectors.toList());
        if (list.size() > 1) {
            Log.w(this, "Found multiple PhoneAccounts registered to the same Group Id!", new Object[0]);
        }
        if (list.isEmpty()) {
            return null;
        }
        return (PhoneAccount) list.get(0);
    }

    static /* synthetic */ boolean lambda$getPhoneAccountByGroupId$0(String str, PhoneAccountHandle phoneAccountHandle, ComponentName componentName, PhoneAccount phoneAccount) {
        return str.equals(phoneAccount.getGroupId()) && !phoneAccount.getAccountHandle().equals(phoneAccountHandle) && Objects.equals(phoneAccount.getAccountHandle().getComponentName(), componentName);
    }

    public void setUserSelectedOutgoingPhoneAccount(PhoneAccountHandle phoneAccountHandle, UserHandle userHandle) {
        boolean zHasCapabilities;
        if (userHandle == null) {
            return;
        }
        DefaultPhoneAccountHandle defaultPhoneAccountHandle = this.mState.defaultOutgoingAccountHandles.get(userHandle);
        PhoneAccountHandle phoneAccountHandle2 = defaultPhoneAccountHandle == null ? null : defaultPhoneAccountHandle.phoneAccountHandle;
        if (phoneAccountHandle == null) {
            this.mState.defaultOutgoingAccountHandles.remove(userHandle);
            zHasCapabilities = false;
        } else {
            PhoneAccount phoneAccount = getPhoneAccount(phoneAccountHandle, userHandle);
            if (phoneAccount == null) {
                Log.w(this, "Trying to set nonexistent default outgoing %s", new Object[]{phoneAccountHandle});
                return;
            } else if (!phoneAccount.hasCapabilities(2)) {
                Log.w(this, "Trying to set non-call-provider default outgoing %s", new Object[]{phoneAccountHandle});
                return;
            } else {
                zHasCapabilities = phoneAccount.hasCapabilities(4);
                Log.i(this, "setUserSelectedOutgoingPhoneAccount: %s", new Object[]{phoneAccountHandle});
                this.mState.defaultOutgoingAccountHandles.put(userHandle, new DefaultPhoneAccountHandle(userHandle, phoneAccountHandle, phoneAccount.getGroupId()));
            }
        }
        if (!Objects.equals(phoneAccountHandle2, phoneAccountHandle)) {
            int subscriptionIdForPhoneAccount = phoneAccountHandle == null ? -1 : getSubscriptionIdForPhoneAccount(phoneAccountHandle);
            if (zHasCapabilities || phoneAccountHandle == null) {
                if (subscriptionIdForPhoneAccount != SubscriptionManager.getDefaultVoiceSubscriptionId()) {
                    Log.i(this, "setUserSelectedOutgoingPhoneAccount: update voice sub; account=%s, subId=%d", new Object[]{phoneAccountHandle, Integer.valueOf(subscriptionIdForPhoneAccount)});
                    this.mSubscriptionManager.setDefaultVoiceSubscriptionId(subscriptionIdForPhoneAccount);
                }
            } else {
                Log.i(this, "setUserSelectedOutgoingPhoneAccount: %s is not a sub", new Object[]{phoneAccountHandle});
            }
        } else {
            Log.i(this, "setUserSelectedOutgoingPhoneAccount: no change to voice sub", new Object[0]);
        }
        write();
        fireDefaultOutgoingChanged();
    }

    boolean isUserSelectedSmsPhoneAccount(PhoneAccountHandle phoneAccountHandle) {
        return getSubscriptionIdForPhoneAccount(phoneAccountHandle) == SubscriptionManager.getDefaultSmsSubscriptionId();
    }

    public ComponentName getSystemSimCallManagerComponent() {
        return getSystemSimCallManagerComponent(SubscriptionManager.getDefaultSubscriptionId());
    }

    public ComponentName getSystemSimCallManagerComponent(int i) {
        PersistableBundle configForSubId = ((CarrierConfigManager) this.mContext.getSystemService("carrier_config")).getConfigForSubId(i);
        String string = configForSubId != null ? configForSubId.getString("default_sim_call_manager_string") : null;
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return ComponentName.unflattenFromString(string);
    }

    public PhoneAccountHandle getSimCallManagerOfCurrentUser() {
        return getSimCallManager(this.mCurrentUserHandle);
    }

    public PhoneAccountHandle getSimCallManager(UserHandle userHandle) {
        return getSimCallManager(SubscriptionManager.getDefaultSubscriptionId(), userHandle);
    }

    public PhoneAccountHandle getSimCallManager(int i, UserHandle userHandle) {
        PhoneAccountHandle phoneAccountHandle;
        String defaultDialerApplication = this.mDefaultDialerCache.getDefaultDialerApplication(userHandle.getIdentifier());
        ComponentName systemSimCallManagerComponent = getSystemSimCallManagerComponent(i);
        PhoneAccountHandle phoneAccountHandle2 = null;
        if (TextUtils.isEmpty(defaultDialerApplication) && systemSimCallManagerComponent == null) {
            phoneAccountHandle = null;
        } else {
            phoneAccountHandle = null;
            for (PhoneAccountHandle phoneAccountHandle3 : getPhoneAccountHandles(1, null, null, true, userHandle)) {
                ComponentName componentName = phoneAccountHandle3.getComponentName();
                if (phoneAccountHandle == null && Objects.equals(componentName, systemSimCallManagerComponent) && !resolveComponent(phoneAccountHandle3).isEmpty()) {
                    phoneAccountHandle = phoneAccountHandle3;
                } else if (phoneAccountHandle2 == null && Objects.equals(componentName.getPackageName(), defaultDialerApplication) && !resolveComponent(phoneAccountHandle3).isEmpty()) {
                    phoneAccountHandle2 = phoneAccountHandle3;
                }
            }
        }
        if (phoneAccountHandle2 == null) {
            phoneAccountHandle2 = phoneAccountHandle;
        }
        Log.i(this, "getSimCallManager: SimCallManager for subId %d queried, returning: %s", new Object[]{Integer.valueOf(i), phoneAccountHandle2});
        return phoneAccountHandle2;
    }

    public void setTestPhoneAccountPackageNameFilter(String str) {
        this.mTestPhoneAccountPackageNameFilter = str;
        Log.i(this, "filter set for PhoneAccounts, packageName=" + str, new Object[0]);
    }

    public List<PhoneAccount> filterRestrictedPhoneAccounts(List<PhoneAccount> list) {
        if (TextUtils.isEmpty(this.mTestPhoneAccountPackageNameFilter)) {
            return new ArrayList(list);
        }
        return (List) list.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$PhoneAccountRegistrar$tFb3YptwTOebs_x1xjjQeLa42t0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$filterRestrictedPhoneAccounts$1$PhoneAccountRegistrar((PhoneAccount) obj);
            }
        }).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$filterRestrictedPhoneAccounts$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$filterRestrictedPhoneAccounts$1$PhoneAccountRegistrar(PhoneAccount phoneAccount) {
        return this.mTestPhoneAccountPackageNameFilter.equals(phoneAccount.getAccountHandle().getComponentName().getPackageName());
    }

    public PhoneAccountHandle getSimCallManagerFromCall(Call call) {
        if (call == null) {
            return null;
        }
        UserHandle initiatingUser = call.getInitiatingUser();
        if (initiatingUser == null) {
            initiatingUser = call.getTargetPhoneAccount().getUserHandle();
        }
        PhoneAccountHandle targetPhoneAccount = call.getTargetPhoneAccount();
        Log.d(this, "getSimCallManagerFromCall: callId=%s, targetPhac=%s", new Object[]{call.getId(), targetPhoneAccount});
        return getSimCallManagerFromHandle(targetPhoneAccount, initiatingUser);
    }

    public PhoneAccountHandle getSimCallManagerFromHandle(PhoneAccountHandle phoneAccountHandle, UserHandle userHandle) {
        PhoneAccount phoneAccountUnchecked = getPhoneAccountUnchecked(phoneAccountHandle);
        if (phoneAccountUnchecked != null && phoneAccountUnchecked.hasCapabilities(1)) {
            return phoneAccountHandle;
        }
        int subscriptionIdForPhoneAccount = getSubscriptionIdForPhoneAccount(phoneAccountHandle);
        if (SubscriptionManager.isValidSubscriptionId(subscriptionIdForPhoneAccount) && subscriptionIdForPhoneAccount != Integer.MAX_VALUE) {
            PhoneAccountHandle simCallManager = getSimCallManager(subscriptionIdForPhoneAccount, userHandle);
            Log.d(this, "getSimCallManagerFromHandle: targetPhac=%s, subId=%d, scm=%s", new Object[]{phoneAccountHandle, Integer.valueOf(subscriptionIdForPhoneAccount), simCallManager});
            return simCallManager;
        }
        PhoneAccountHandle simCallManager2 = getSimCallManager(userHandle);
        Log.d(this, "getSimCallManagerFromHandle: targetPhac=%s, subId(d)=%d, scm=%s", new Object[]{phoneAccountHandle, Integer.valueOf(subscriptionIdForPhoneAccount), simCallManager2});
        return simCallManager2;
    }

    public void setCurrentUserHandle(UserHandle userHandle) {
        if (userHandle == null) {
            Log.d(this, "setCurrentUserHandle, userHandle = null", new Object[0]);
            userHandle = Process.myUserHandle();
        }
        Log.d(this, "setCurrentUserHandle, %s", new Object[]{userHandle});
        this.mCurrentUserHandle = userHandle;
    }

    public boolean enablePhoneAccount(PhoneAccountHandle phoneAccountHandle, boolean z) {
        PhoneAccount phoneAccountUnchecked = getPhoneAccountUnchecked(phoneAccountHandle);
        Object[] objArr = new Object[2];
        objArr[0] = phoneAccountHandle;
        objArr[1] = z ? "enabled" : "disabled";
        Log.i(this, "Phone account %s %s.", objArr);
        if (phoneAccountUnchecked == null) {
            Log.w(this, "Could not find account to enable: " + phoneAccountHandle, new Object[0]);
            return false;
        }
        if (phoneAccountUnchecked.hasCapabilities(4)) {
            Log.w(this, "Could not change enable state of SIM account: " + phoneAccountHandle, new Object[0]);
            return false;
        }
        if (phoneAccountUnchecked.isEnabled() != z) {
            phoneAccountUnchecked.setIsEnabled(z);
            if (!z) {
                removeDefaultPhoneAccountHandle(phoneAccountHandle);
            }
            write();
            fireAccountsChanged();
        }
        return true;
    }

    private void removeDefaultPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle) {
        Iterator<Map.Entry<UserHandle, DefaultPhoneAccountHandle>> it = this.mState.defaultOutgoingAccountHandles.entrySet().iterator();
        while (it.hasNext()) {
            if (phoneAccountHandle.equals(it.next().getValue().phoneAccountHandle)) {
                it.remove();
            }
        }
    }

    private boolean isVisibleForUser(PhoneAccount phoneAccount, UserHandle userHandle, boolean z) {
        if (phoneAccount == null) {
            return false;
        }
        if (userHandle == null) {
            Log.w(this, "userHandle is null in isVisibleForUser", new Object[0]);
            return false;
        }
        if (phoneAccount.hasCapabilities(32)) {
            return true;
        }
        UserHandle userHandle2 = phoneAccount.getAccountHandle().getUserHandle();
        if (userHandle2 == null) {
            return false;
        }
        if (this.mCurrentUserHandle == null) {
            Log.d(this, "Current user is null; assuming true", new Object[0]);
            return true;
        }
        if (z) {
            return UserManager.get(this.mContext).isSameProfileGroup(userHandle.getIdentifier(), userHandle2.getIdentifier());
        }
        return userHandle2.equals(userHandle);
    }

    private List<ResolveInfo> resolveComponent(PhoneAccountHandle phoneAccountHandle) {
        return resolveComponent(phoneAccountHandle.getComponentName(), phoneAccountHandle.getUserHandle());
    }

    private List<ResolveInfo> resolveComponent(ComponentName componentName, UserHandle userHandle) {
        PackageManager packageManager = this.mContext.getPackageManager();
        Intent intent = new Intent("android.telecom.ConnectionService");
        intent.setComponent(componentName);
        try {
            if (userHandle != null) {
                return packageManager.queryIntentServicesAsUser(intent, 0, userHandle.getIdentifier());
            }
            return packageManager.queryIntentServices(intent, 0);
        } catch (SecurityException e) {
            Log.e(this, e, "%s is not visible for the calling user", new Object[]{componentName});
            return Collections.EMPTY_LIST;
        }
    }

    public List<PhoneAccountHandle> getAllPhoneAccountHandles(UserHandle userHandle) {
        return getPhoneAccountHandles(0, null, null, false, userHandle);
    }

    public List<PhoneAccount> getAllPhoneAccounts(UserHandle userHandle) {
        return getPhoneAccounts(0, null, null, false, userHandle);
    }

    public List<PhoneAccount> getAllPhoneAccountsOfCurrentUser() {
        return getAllPhoneAccounts(this.mCurrentUserHandle);
    }

    public List<PhoneAccountHandle> getCallCapablePhoneAccounts(String str, boolean z, UserHandle userHandle) {
        return getCallCapablePhoneAccounts(str, z, userHandle, 0, 128);
    }

    public List<PhoneAccountHandle> getCallCapablePhoneAccounts(String str, boolean z, UserHandle userHandle, int i, int i2) {
        return getPhoneAccountHandles(i | 2, i2, str, null, z, userHandle);
    }

    public List<PhoneAccountHandle> getSelfManagedPhoneAccounts(UserHandle userHandle) {
        return getPhoneAccountHandles(2048, 128, null, null, false, userHandle);
    }

    public List<PhoneAccountHandle> getSimPhoneAccounts(UserHandle userHandle) {
        return getPhoneAccountHandles(6, null, null, false, userHandle);
    }

    public List<PhoneAccountHandle> getSimPhoneAccountsOfCurrentUser() {
        return getSimPhoneAccounts(this.mCurrentUserHandle);
    }

    public List<PhoneAccountHandle> getPhoneAccountsForPackage(String str, UserHandle userHandle) {
        return getPhoneAccountHandles(0, null, str, false, userHandle);
    }

    public void registerPhoneAccount(PhoneAccount phoneAccount) {
        if (!phoneAccountRequiresBindPermission(phoneAccount.getAccountHandle())) {
            Log.w(this, "Phone account %s does not have BIND_TELECOM_CONNECTION_SERVICE permission.", new Object[]{phoneAccount.getAccountHandle()});
            throw new SecurityException("PhoneAccount connection service requires BIND_TELECOM_CONNECTION_SERVICE permission.");
        }
        addOrReplacePhoneAccount(phoneAccount);
    }

    private void addOrReplacePhoneAccount(PhoneAccount phoneAccount) {
        boolean z;
        boolean zIsEnabled;
        Log.d(this, "addOrReplacePhoneAccount(%s -> %s)", new Object[]{phoneAccount.getAccountHandle(), phoneAccount});
        PhoneAccount phoneAccountUnchecked = getPhoneAccountUnchecked(phoneAccount.getAccountHandle());
        if (phoneAccountUnchecked != null) {
            this.mState.accounts.remove(phoneAccountUnchecked);
            zIsEnabled = phoneAccountUnchecked.isEnabled();
            Log.i(this, "Modify account: %s", new Object[]{getAccountDiffString(phoneAccount, phoneAccountUnchecked)});
            z = false;
        } else {
            Log.i(this, "New phone account registered: " + phoneAccount, new Object[0]);
            z = true;
            zIsEnabled = false;
        }
        if (phoneAccount.hasCapabilities(2048)) {
            phoneAccount = phoneAccount.toBuilder().setLabel(this.mAppLabelProxy.getAppLabel(phoneAccount.getAccountHandle().getComponentName().getPackageName())).setCapabilities(phoneAccount.getCapabilities() & (-8)).build();
        }
        this.mState.accounts.add(phoneAccount);
        maybeReplaceOldAccount(phoneAccount);
        phoneAccount.setIsEnabled(zIsEnabled || phoneAccount.hasCapabilities(4) || phoneAccount.hasCapabilities(2048));
        write();
        fireAccountsChanged();
        if (z) {
            fireAccountRegistered(phoneAccount.getAccountHandle());
        } else {
            fireAccountChanged(phoneAccount);
        }
    }

    public void unregisterPhoneAccount(PhoneAccountHandle phoneAccountHandle) {
        PhoneAccount phoneAccountUnchecked = getPhoneAccountUnchecked(phoneAccountHandle);
        if (phoneAccountUnchecked == null || !this.mState.accounts.remove(phoneAccountUnchecked)) {
            return;
        }
        write();
        fireAccountsChanged();
        fireAccountUnRegistered(phoneAccountHandle);
    }

    public void clearAccounts(String str, UserHandle userHandle) {
        boolean z = false;
        for (PhoneAccount phoneAccount : this.mState.accounts) {
            PhoneAccountHandle accountHandle = phoneAccount.getAccountHandle();
            if (Objects.equals(str, accountHandle.getComponentName().getPackageName()) && Objects.equals(userHandle, accountHandle.getUserHandle())) {
                Log.i(this, "Removing phone account " + ((Object) phoneAccount.getLabel()), new Object[0]);
                this.mState.accounts.remove(phoneAccount);
                z = true;
            }
        }
        if (z) {
            write();
            fireAccountsChanged();
        }
    }

    public boolean isVoiceMailNumber(PhoneAccountHandle phoneAccountHandle, String str) {
        return PhoneNumberUtils.isVoiceMailNumber(this.mContext, getSubscriptionIdForPhoneAccount(phoneAccountHandle), str);
    }

    public void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    private void fireAccountRegistered(PhoneAccountHandle phoneAccountHandle) {
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onPhoneAccountRegistered(this, phoneAccountHandle);
        }
    }

    private void fireAccountChanged(PhoneAccount phoneAccount) {
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onPhoneAccountChanged(this, phoneAccount);
        }
    }

    private void fireAccountUnRegistered(PhoneAccountHandle phoneAccountHandle) {
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onPhoneAccountUnRegistered(this, phoneAccountHandle);
        }
    }

    private void fireAccountsChanged() {
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onAccountsChanged(this);
        }
    }

    private void fireDefaultOutgoingChanged() {
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onDefaultOutgoingChanged(this);
        }
    }

    private String getAccountDiffString(PhoneAccount phoneAccount, PhoneAccount phoneAccount2) {
        if (phoneAccount == null || phoneAccount2 == null) {
            return "Diff: " + phoneAccount + ", " + phoneAccount2;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(phoneAccount.getAccountHandle());
        appendDiff(stringBuffer, "addr", Log.piiHandle(phoneAccount.getAddress()), Log.piiHandle(phoneAccount2.getAddress()));
        appendDiff(stringBuffer, "cap", phoneAccount.capabilitiesToString(), phoneAccount2.capabilitiesToString());
        appendDiff(stringBuffer, "hl", Integer.valueOf(phoneAccount.getHighlightColor()), Integer.valueOf(phoneAccount2.getHighlightColor()));
        appendDiff(stringBuffer, "lbl", phoneAccount.getLabel(), phoneAccount2.getLabel());
        appendDiff(stringBuffer, "desc", phoneAccount.getShortDescription(), phoneAccount2.getShortDescription());
        appendDiff(stringBuffer, "subAddr", Log.piiHandle(phoneAccount.getSubscriptionAddress()), Log.piiHandle(phoneAccount2.getSubscriptionAddress()));
        appendDiff(stringBuffer, "uris", phoneAccount.getSupportedUriSchemes(), phoneAccount2.getSupportedUriSchemes());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    private void appendDiff(StringBuffer stringBuffer, String str, Object obj, Object obj2) {
        if (Objects.equals(obj, obj2)) {
            return;
        }
        stringBuffer.append("(");
        stringBuffer.append(str);
        stringBuffer.append(": ");
        stringBuffer.append(obj);
        stringBuffer.append(" -> ");
        stringBuffer.append(obj2);
        stringBuffer.append(")");
    }

    private void maybeReplaceOldAccount(PhoneAccount phoneAccount) {
        UserHandle userHandle = phoneAccount.getAccountHandle().getUserHandle();
        DefaultPhoneAccountHandle userSelectedDefaultPhoneAccount = getUserSelectedDefaultPhoneAccount(userHandle);
        if (userSelectedDefaultPhoneAccount == null || userSelectedDefaultPhoneAccount.groupId.isEmpty()) {
            Log.v(this, "maybeReplaceOldAccount: Not replacing PhoneAccount, no group Id or default.", new Object[0]);
            return;
        }
        if (!userSelectedDefaultPhoneAccount.groupId.equals(phoneAccount.getGroupId())) {
            Log.v(this, "maybeReplaceOldAccount: group Ids are not equal.", new Object[0]);
            return;
        }
        if (Objects.equals(phoneAccount.getAccountHandle().getComponentName(), userSelectedDefaultPhoneAccount.phoneAccountHandle.getComponentName())) {
            setUserSelectedOutgoingPhoneAccount(phoneAccount.getAccountHandle(), userHandle);
        } else {
            Log.v(this, "maybeReplaceOldAccount: group Ids are equal, but ComponentName is not the same as the default. Not replacing default PhoneAccount.", new Object[0]);
        }
        PhoneAccount phoneAccountByGroupId = getPhoneAccountByGroupId(phoneAccount.getGroupId(), phoneAccount.getAccountHandle().getComponentName(), userHandle, phoneAccount.getAccountHandle());
        if (phoneAccountByGroupId != null) {
            Log.v(this, "maybeReplaceOldAccount: Unregistering old PhoneAccount: " + phoneAccountByGroupId.getAccountHandle(), new Object[0]);
            unregisterPhoneAccount(phoneAccountByGroupId.getAccountHandle());
        }
    }

    public boolean phoneAccountRequiresBindPermission(PhoneAccountHandle phoneAccountHandle) {
        List<ResolveInfo> listResolveComponent = resolveComponent(phoneAccountHandle);
        if (listResolveComponent.isEmpty()) {
            Log.w(this, "phoneAccount %s not found", new Object[]{phoneAccountHandle.getComponentName()});
            return false;
        }
        Iterator<ResolveInfo> it = listResolveComponent.iterator();
        while (it.hasNext()) {
            ServiceInfo serviceInfo = it.next().serviceInfo;
            if (serviceInfo == null) {
                return false;
            }
            if (!"android.permission.BIND_CONNECTION_SERVICE".equals(serviceInfo.permission) && !"android.permission.BIND_TELECOM_CONNECTION_SERVICE".equals(serviceInfo.permission)) {
                return false;
            }
        }
        return true;
    }

    public PhoneAccount getPhoneAccountUnchecked(PhoneAccountHandle phoneAccountHandle) {
        for (PhoneAccount phoneAccount : this.mState.accounts) {
            if (Objects.equals(phoneAccountHandle, phoneAccount.getAccountHandle())) {
                return phoneAccount;
            }
        }
        return null;
    }

    public PhoneAccount getPhoneAccount(PhoneAccountHandle phoneAccountHandle, UserHandle userHandle) {
        return getPhoneAccount(phoneAccountHandle, userHandle, false);
    }

    public PhoneAccount getPhoneAccount(PhoneAccountHandle phoneAccountHandle, UserHandle userHandle, boolean z) {
        PhoneAccount phoneAccountUnchecked = getPhoneAccountUnchecked(phoneAccountHandle);
        if (phoneAccountUnchecked == null || !isVisibleForUser(phoneAccountUnchecked, userHandle, z)) {
            return null;
        }
        return phoneAccountUnchecked;
    }

    public PhoneAccount getPhoneAccountOfCurrentUser(PhoneAccountHandle phoneAccountHandle) {
        return getPhoneAccount(phoneAccountHandle, this.mCurrentUserHandle);
    }

    private List<PhoneAccountHandle> getPhoneAccountHandles(int i, String str, String str2, boolean z, UserHandle userHandle) {
        return getPhoneAccountHandles(i, 0, str, str2, z, userHandle);
    }

    private List<PhoneAccountHandle> getPhoneAccountHandles(int i, int i2, String str, String str2, boolean z, UserHandle userHandle) {
        ArrayList arrayList = new ArrayList();
        Iterator<PhoneAccount> it = getPhoneAccounts(i, i2, str, str2, z, userHandle).iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getAccountHandle());
        }
        return arrayList;
    }

    private List<PhoneAccount> getPhoneAccounts(int i, String str, String str2, boolean z, UserHandle userHandle) {
        return getPhoneAccounts(i, 0, str, str2, z, userHandle);
    }

    private List<PhoneAccount> getPhoneAccounts(int i, int i2, String str, String str2, boolean z, UserHandle userHandle) {
        ArrayList arrayList = new ArrayList(this.mState.accounts.size());
        for (PhoneAccount phoneAccount : this.mState.accounts) {
            if (phoneAccount.isEnabled() || z) {
                if ((phoneAccount.getCapabilities() & i2) == 0 && (i == 0 || phoneAccount.hasCapabilities(i))) {
                    if (str == null || phoneAccount.supportsUriScheme(str)) {
                        PhoneAccountHandle accountHandle = phoneAccount.getAccountHandle();
                        if (!resolveComponent(accountHandle).isEmpty() && (str2 == null || str2.equals(accountHandle.getComponentName().getPackageName()))) {
                            if (isVisibleForUser(phoneAccount, userHandle, false)) {
                                arrayList.add(phoneAccount);
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static class DefaultPhoneAccountHandle {
        public final String groupId;
        public final PhoneAccountHandle phoneAccountHandle;
        public final UserHandle userHandle;

        public DefaultPhoneAccountHandle(UserHandle userHandle, PhoneAccountHandle phoneAccountHandle, String str) {
            this.userHandle = userHandle;
            this.phoneAccountHandle = phoneAccountHandle;
            this.groupId = str;
        }
    }

    public void dump(IndentingPrintWriter indentingPrintWriter) {
        if (this.mState != null) {
            indentingPrintWriter.println("xmlVersion: " + this.mState.versionNumber);
            DefaultPhoneAccountHandle defaultPhoneAccountHandle = this.mState.defaultOutgoingAccountHandles.get(Process.myUserHandle());
            StringBuilder sb = new StringBuilder();
            sb.append("defaultOutgoing: ");
            sb.append(defaultPhoneAccountHandle == null ? "none" : defaultPhoneAccountHandle.phoneAccountHandle);
            indentingPrintWriter.println(sb.toString());
            indentingPrintWriter.println("simCallManager: " + getSimCallManager(this.mCurrentUserHandle));
            indentingPrintWriter.println("phoneAccounts:");
            indentingPrintWriter.increaseIndent();
            Iterator<PhoneAccount> it = this.mState.accounts.iterator();
            while (it.hasNext()) {
                indentingPrintWriter.println(it.next());
            }
            indentingPrintWriter.decreaseIndent();
            indentingPrintWriter.increaseIndent();
            indentingPrintWriter.println("test emergency PhoneAccount filter: " + this.mTestPhoneAccountPackageNameFilter);
            indentingPrintWriter.decreaseIndent();
        }
    }

    private void sortPhoneAccounts() {
        if (this.mState.accounts.size() > 1) {
            $$Lambda$PhoneAccountRegistrar$kYSfj5mSmpJifyqYbztJomCZwvI __lambda_phoneaccountregistrar_kysfj5msmpjifyqybztjomczwvi = new Comparator() { // from class: com.android.server.telecom.-$$Lambda$PhoneAccountRegistrar$kYSfj5mSmpJifyqYbztJomCZwvI
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return PhoneAccountRegistrar.lambda$sortPhoneAccounts$2((PhoneAccount) obj, (PhoneAccount) obj2);
                }
            };
            final Comparator comparatorNullsLast = Comparator.nullsLast(new Comparator() { // from class: com.android.server.telecom.-$$Lambda$TEfSBt3hRUlBSSARfPEHsJesTtE
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ((String) obj).compareTo((String) obj2);
                }
            });
            this.mState.accounts.sort(__lambda_phoneaccountregistrar_kysfj5msmpjifyqybztjomczwvi.thenComparing(new Comparator() { // from class: com.android.server.telecom.-$$Lambda$PhoneAccountRegistrar$CrKVRnGRIeSyd0DR2J0CIN6g_og
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    PhoneAccount phoneAccount = (PhoneAccount) obj;
                    PhoneAccount phoneAccount2 = (PhoneAccount) obj2;
                    return comparatorNullsLast.compare(phoneAccount.getExtras() == null ? null : phoneAccount.getExtras().getString("android.telecom.extra.SORT_ORDER", null), phoneAccount2.getExtras() != null ? phoneAccount2.getExtras().getString("android.telecom.extra.SORT_ORDER", null) : null);
                }
            }.thenComparing(new Comparator() { // from class: com.android.server.telecom.-$$Lambda$PhoneAccountRegistrar$GS99IYpK3m5TaDvXIYL022U6seM
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    PhoneAccount phoneAccount = (PhoneAccount) obj;
                    PhoneAccount phoneAccount2 = (PhoneAccount) obj2;
                    return comparatorNullsLast.compare(phoneAccount.getLabel() == null ? null : phoneAccount.getLabel().toString(), phoneAccount2.getLabel() != null ? phoneAccount2.getLabel().toString() : null);
                }
            })));
        }
    }

    static /* synthetic */ int lambda$sortPhoneAccounts$2(PhoneAccount phoneAccount, PhoneAccount phoneAccount2) {
        if (!phoneAccount.hasCapabilities(4) || phoneAccount2.hasCapabilities(4)) {
            return (phoneAccount.hasCapabilities(4) || !phoneAccount2.hasCapabilities(4)) ? 0 : 1;
        }
        return -1;
    }

    private class AsyncXmlWriter extends AsyncTask<ByteArrayOutputStream, Void, Void> {
        private AsyncXmlWriter() {
        }

        @Override // android.os.AsyncTask
        public Void doInBackground(ByteArrayOutputStream... byteArrayOutputStreamArr) throws Throwable {
            FileOutputStream fileOutputStream;
            ByteArrayOutputStream byteArrayOutputStream = byteArrayOutputStreamArr[0];
            try {
            } catch (IOException e) {
                e = e;
                fileOutputStream = null;
            }
            try {
                synchronized (PhoneAccountRegistrar.this.mWriteLock) {
                    try {
                        FileOutputStream fileOutputStreamStartWrite = PhoneAccountRegistrar.this.mAtomicFile.startWrite();
                        byteArrayOutputStream.writeTo(fileOutputStreamStartWrite);
                        PhoneAccountRegistrar.this.mAtomicFile.finishWrite(fileOutputStreamStartWrite);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = null;
                        try {
                            throw th;
                        } catch (IOException e2) {
                            e = e2;
                            Log.e(this, e, "Writing state to XML file", new Object[0]);
                            PhoneAccountRegistrar.this.mAtomicFile.failWrite(fileOutputStream);
                            return null;
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private void write() {
        try {
            sortPhoneAccounts();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            FastXmlSerializer fastXmlSerializer = new FastXmlSerializer();
            fastXmlSerializer.setOutput(byteArrayOutputStream, "utf-8");
            writeToXml(this.mState, fastXmlSerializer, this.mContext);
            fastXmlSerializer.flush();
            new AsyncXmlWriter().execute(byteArrayOutputStream);
        } catch (IOException e) {
            Log.e(this, e, "Writing state to XML buffer", new Object[0]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList, java.util.Collection, java.util.List] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.util.Iterator] */
    private void read() {
        boolean z;
        ?? arrayList = "Closing InputStream";
        try {
            ?? OpenRead = this.mAtomicFile.openRead();
            try {
                try {
                    XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                    xmlPullParserNewPullParser.setInput(new BufferedInputStream(OpenRead), null);
                    xmlPullParserNewPullParser.nextTag();
                    State fromXml = readFromXml(xmlPullParserNewPullParser, this.mContext);
                    this.mState = fromXml;
                    z = fromXml.versionNumber < 9;
                    try {
                        OpenRead.close();
                    } catch (IOException e) {
                        Log.e(this, e, "Closing InputStream", new Object[0]);
                    }
                } catch (IOException | XmlPullParserException e2) {
                    Log.e(this, e2, "Reading state from XML file", new Object[0]);
                    this.mState = new State();
                    try {
                        OpenRead.close();
                    } catch (IOException e3) {
                        Log.e(this, e3, "Closing InputStream", new Object[0]);
                    }
                    z = false;
                }
                arrayList = new ArrayList();
                OpenRead = this.mState.accounts.iterator();
                while (OpenRead.hasNext()) {
                    PhoneAccount phoneAccount = (PhoneAccount) OpenRead.next();
                    UserHandle userHandle = phoneAccount.getAccountHandle().getUserHandle();
                    if (userHandle == null) {
                        Log.w(this, "Missing UserHandle for %s", new Object[]{phoneAccount});
                        arrayList.add(phoneAccount);
                    } else if (this.mUserManager.getSerialNumberForUser(userHandle) == -1) {
                        Log.w(this, "User does not exist for %s", new Object[]{phoneAccount});
                        arrayList.add(phoneAccount);
                    }
                }
                this.mState.accounts.removeAll((Collection<?>) arrayList);
                if (z || !arrayList.isEmpty()) {
                    write();
                }
            } catch (Throwable th) {
                try {
                    OpenRead.close();
                } catch (IOException e4) {
                    Log.e(this, e4, (String) arrayList, new Object[0]);
                }
                throw th;
            }
        } catch (FileNotFoundException unused) {
        }
    }

    private static void writeToXml(State state, XmlSerializer xmlSerializer, Context context) throws IOException {
        sStateXml.writeToXml(state, xmlSerializer, context);
    }

    private static State readFromXml(XmlPullParser xmlPullParser, Context context) throws XmlPullParserException, IOException {
        State fromXml = sStateXml.readFromXml(xmlPullParser, 0, context);
        return fromXml != null ? fromXml : new State();
    }

    @VisibleForTesting
    public static abstract class XmlSerialization<T> {
        public abstract T readFromXml(XmlPullParser xmlPullParser, int i, Context context) throws XmlPullParserException, IOException;

        public abstract void writeToXml(T t, XmlSerializer xmlSerializer, Context context) throws IOException;

        protected void writeTextIfNonNull(String str, Object obj, XmlSerializer xmlSerializer) throws IOException {
            if (obj != null) {
                xmlSerializer.startTag(null, str);
                xmlSerializer.text(Objects.toString(obj));
                xmlSerializer.endTag(null, str);
            }
        }

        protected void writeStringList(String str, List<String> list, XmlSerializer xmlSerializer) throws IOException {
            xmlSerializer.startTag(null, str);
            if (list != null) {
                xmlSerializer.attribute(null, "length", Objects.toString(Integer.valueOf(list.size())));
                for (String str2 : list) {
                    xmlSerializer.startTag(null, "value");
                    if (str2 != null) {
                        xmlSerializer.text(str2);
                    }
                    xmlSerializer.endTag(null, "value");
                }
            } else {
                xmlSerializer.attribute(null, "length", "0");
            }
            xmlSerializer.endTag(null, str);
        }

        protected void writeBundle(String str, Bundle bundle, XmlSerializer xmlSerializer) throws IOException {
            String str2;
            xmlSerializer.startTag(null, str);
            if (bundle != null) {
                for (String str3 : bundle.keySet()) {
                    Object obj = bundle.get(str3);
                    if (obj != null) {
                        if (obj instanceof String) {
                            str2 = "string";
                        } else if (obj instanceof Integer) {
                            str2 = "integer";
                        } else if (obj instanceof Boolean) {
                            str2 = "boolean";
                        } else {
                            Log.w(this, "PhoneAccounts support only string, integer and boolean extras TY.", new Object[0]);
                        }
                        xmlSerializer.startTag(null, "value");
                        xmlSerializer.attribute(null, "key", str3);
                        xmlSerializer.attribute(null, "type", str2);
                        xmlSerializer.text(Objects.toString(obj));
                        xmlSerializer.endTag(null, "value");
                    }
                }
            }
            xmlSerializer.endTag(null, str);
        }

        protected void writeIconIfNonNull(String str, Icon icon, XmlSerializer xmlSerializer) throws IOException {
            if (icon != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                icon.writeToStream(byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String strEncodeToString = Base64.encodeToString(byteArray, 0, byteArray.length, 0);
                xmlSerializer.startTag(null, str);
                xmlSerializer.text(strEncodeToString);
                xmlSerializer.endTag(null, str);
            }
        }

        protected void writeLong(String str, long j, XmlSerializer xmlSerializer) throws IOException {
            xmlSerializer.startTag(null, str);
            xmlSerializer.text(Long.valueOf(j).toString());
            xmlSerializer.endTag(null, str);
        }

        protected void writeNonNullString(String str, String str2, XmlSerializer xmlSerializer) throws IOException {
            xmlSerializer.startTag(null, str);
            if (str2 == null) {
                str2 = "";
            }
            xmlSerializer.text(str2);
            xmlSerializer.endTag(null, str);
        }

        protected List<String> readStringList(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            int i = Integer.parseInt(xmlPullParser.getAttributeValue(null, "length"));
            ArrayList arrayList = new ArrayList(i);
            if (i == 0) {
                return arrayList;
            }
            int depth = xmlPullParser.getDepth();
            while (XmlUtils.nextElementWithin(xmlPullParser, depth)) {
                if (xmlPullParser.getName().equals("value")) {
                    xmlPullParser.next();
                    arrayList.add(xmlPullParser.getText());
                }
            }
            return arrayList;
        }

        protected Bundle readBundle(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            int depth = xmlPullParser.getDepth();
            Bundle bundle = null;
            while (XmlUtils.nextElementWithin(xmlPullParser, depth)) {
                if (xmlPullParser.getName().equals("value")) {
                    String attributeValue = xmlPullParser.getAttributeValue(null, "type");
                    String attributeValue2 = xmlPullParser.getAttributeValue(null, "key");
                    xmlPullParser.next();
                    String text = xmlPullParser.getText();
                    if (bundle == null) {
                        bundle = new Bundle();
                    }
                    if (text != null) {
                        if ("string".equals(attributeValue)) {
                            bundle.putString(attributeValue2, text);
                        } else if ("integer".equals(attributeValue)) {
                            try {
                                bundle.putInt(attributeValue2, Integer.parseInt(text));
                            } catch (NumberFormatException unused) {
                                Log.w(this, "Invalid integer PhoneAccount extra.", new Object[0]);
                            }
                        } else if ("boolean".equals(attributeValue)) {
                            bundle.putBoolean(attributeValue2, Boolean.parseBoolean(text));
                        } else {
                            Log.w(this, "Invalid type " + attributeValue + " for PhoneAccount bundle.", new Object[0]);
                        }
                    }
                }
            }
            return bundle;
        }

        protected Bitmap readBitmap(XmlPullParser xmlPullParser) {
            byte[] bArrDecode = Base64.decode(xmlPullParser.getText(), 0);
            return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
        }

        protected Icon readIcon(XmlPullParser xmlPullParser) throws IOException {
            return Icon.createFromStream(new ByteArrayInputStream(Base64.decode(xmlPullParser.getText(), 0)));
        }
    }
}
