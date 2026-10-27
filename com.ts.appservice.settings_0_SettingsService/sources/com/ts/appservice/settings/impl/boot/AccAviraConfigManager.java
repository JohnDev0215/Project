package com.ts.appservice.settings.impl.boot;

import android.util.Xml;
import com.android.common.speech.LoggingEvents;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.io.FileInputStream;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
class AccAviraConfigManager {
    private static final String CFG_BLACK_LISTS = "black_lists";
    private static final String CFG_ITEM = "item";
    private static final String CFG_WHITE_LISTS = "white_lists";
    private static final String DEFAULT_CFG_FILENAME = "/system/etc/data/proc_management_cfg.xml";
    private static final String DM_DEFAULT_ENCODING = "utf-8";
    private static final String TAG = "AccAviraConfigManager";
    private static volatile AccAviraConfigManager sInstance;
    private String mConfigFileName;
    private ArrayList<String> mConfigWhiteLists = new ArrayList<>();
    private ArrayList<String> mConfigBlackLists = new ArrayList<>();
    private boolean isInitFalg = false;

    private AccAviraConfigManager() {
        this.mConfigFileName = LoggingEvents.EXTRA_CALLING_APP_NAME;
        this.mConfigFileName = DEFAULT_CFG_FILENAME;
        this.mConfigWhiteLists.clear();
        this.mConfigBlackLists.clear();
    }

    public static AccAviraConfigManager getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (AccAviraConfigManager.class) {
            if (sInstance == null) {
                sInstance = new AccAviraConfigManager();
            }
        }
        return sInstance;
    }

    public ArrayList<String> getWhiteLists() {
        if (!this.isInitFalg) {
            loadConfig();
        }
        return this.mConfigWhiteLists;
    }

    public ArrayList<String> getBlackLists() {
        if (!this.isInitFalg) {
            loadConfig();
        }
        return this.mConfigBlackLists;
    }

    public boolean loadConfig() {
        LogUtil.debug(TAG, "loadSceneConfig() filename = " + this.mConfigFileName);
        String str = this.mConfigFileName;
        if (str == null || str.isEmpty()) {
            LogUtil.error(TAG, "loadSceneConfig() failed! filename is null!");
            return false;
        }
        try {
            FileInputStream in = new FileInputStream(this.mConfigFileName);
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(in, DM_DEFAULT_ENCODING);
            boolean isAddWhiteList = false;
            for (int eventType = parser.getEventType(); eventType != 1; eventType = parser.next()) {
                if (eventType == 0) {
                    this.mConfigWhiteLists.clear();
                    this.mConfigBlackLists.clear();
                } else if (eventType == 2) {
                    if (CFG_WHITE_LISTS.equals(parser.getName())) {
                        isAddWhiteList = true;
                    }
                    if (CFG_BLACK_LISTS.equals(parser.getName())) {
                        isAddWhiteList = false;
                    }
                    if (CFG_ITEM.equals(parser.getName())) {
                        addItemToLists(parser.nextText(), isAddWhiteList);
                    }
                } else if (eventType == 3) {
                    LogUtil.debug(TAG, "loadConfig ok! whitelist size=" + this.mConfigWhiteLists.size() + "blacklist size=" + this.mConfigBlackLists.size());
                }
            }
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.isInitFalg = true;
        return true;
    }

    private void addItemToLists(String item, boolean isWhiteList) {
        if (isWhiteList) {
            this.mConfigWhiteLists.add(item);
        } else {
            this.mConfigBlackLists.add(item);
        }
    }
}
