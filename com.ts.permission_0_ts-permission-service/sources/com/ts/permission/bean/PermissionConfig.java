package com.ts.permission.bean;

import android.util.Log;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PermissionConfig {
    private static final String TAG = "TSPS_PermissionConfig";
    private List<String> presetPaths;
    private List<String> presetShops;
    private List<Rule> rules;
    private int version;

    public List<String> getPresetShops() {
        return this.presetShops;
    }

    public void setPresetShops(List<String> presetShops) {
        this.presetShops = presetShops;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<String> getPresetPaths() {
        return this.presetPaths;
    }

    public void setPresetPaths(List<String> presetPaths) {
        this.presetPaths = presetPaths;
    }

    public List<Rule> getRules() {
        return this.rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public void checkConfig() {
        List<Rule> list = this.rules;
        if (list == null || list.size() == 0) {
            Log.w(TAG, "no permission rules");
            return;
        }
        Iterator<Rule> iterator = this.rules.iterator();
        while (iterator.hasNext()) {
            Rule rule = iterator.next();
            if (rule == null) {
                iterator.remove();
            } else {
                boolean isValid = rule.isValid();
                if (!isValid || !rule.isEnable()) {
                    iterator.remove();
                }
            }
        }
        Collections.sort(this.rules);
    }
}
