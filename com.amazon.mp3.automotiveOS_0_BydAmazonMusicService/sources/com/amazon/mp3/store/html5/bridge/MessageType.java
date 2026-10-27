package com.amazon.mp3.store.html5.bridge;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public enum MessageType {
    Unknown(""),
    GetProfile("getProfile"),
    GetConnectivityType("getConnectivityType"),
    SyncCloudLibrary("syncCloudLibrary"),
    SignIn("signIn"),
    PermissionRequest("permissionRequest"),
    ClearView("clearView"),
    Invalidate("invalidate"),
    EnableLogging("enableLogging"),
    ShowLibrary("showLibrary"),
    Initialized("initialized"),
    TopLevelControllerStarted("topLevelControllerStarted"),
    Metric("metric"),
    Cached("cached"),
    CacheRefreshed("cacheRefreshed"),
    ShowDetailPage("showDetailPage"),
    PurchaseCompleted("purchaseCompleted"),
    StoreOffline("storeOffline"),
    SearchStateChanged("searchStateChanged"),
    SetSoftKeyboardVisibility("setSoftKeyboardVisibility"),
    LogRequestFailure("logRequestFailure"),
    PlayVideo("playVideo"),
    BridgeOpened("bridgeOpened"),
    ShowGiftCodeEntry("showGiftCodeEntry"),
    AudioFocusReleased("audioFocusReleased"),
    ShortLivedTokenExpired("shortLivedTokenExpired"),
    UpdateViewTitles("updateViewTitles"),
    ShowPrimeBrowse("showPrimeBrowse"),
    GetBrowseList("getBrowseList"),
    GetLocalizedStrings("getLocalizedStrings"),
    RequestAudioFocus("requestAudioFocus"),
    RefineMenuVisibility("refineMenuVisibility"),
    RefreshRobinStatus("refreshRobinStatus"),
    ShowInLibrary("showInLibrary"),
    Navigate("navigate"),
    StartSearch("startSearch"),
    HostVisibilityChanged("hostVisibilityChanged"),
    SetCredentials("setCredentials"),
    EndSearch("endSearch"),
    SetReferringApplication("setReferringApplication"),
    DrillOut("drillOut"),
    ToastNotification("toastNotification");

    private static final Map<String, MessageType> STRINGS_TO_ENUM;
    private final String mName;

    static {
        MessageType[] messageTypeArrValues = values();
        HashMap map = new HashMap((int) ((((double) messageTypeArrValues.length) / 0.75d) + 0.5d));
        for (MessageType messageType : messageTypeArrValues) {
            map.put(messageType.getName(), messageType);
        }
        STRINGS_TO_ENUM = Collections.unmodifiableMap(map);
    }

    MessageType(String name) {
        this.mName = name;
    }

    public String getName() {
        return this.mName;
    }

    public boolean is(String name) {
        return this.mName.equals(name);
    }

    public static MessageType fromString(final String value) {
        if (value == null) {
            return Unknown;
        }
        MessageType messageType = STRINGS_TO_ENUM.get(value);
        return messageType == null ? Unknown : messageType;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mName;
    }
}
