package org.apache.shiro.realm.text;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.SimpleRole;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.util.PermissionUtils;
import org.apache.shiro.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class TextConfigurationRealm extends SimpleAccountRealm {
    private volatile String roleDefinitions;
    private volatile String userDefinitions;

    @Override // org.apache.shiro.realm.AuthorizingRealm, org.apache.shiro.realm.AuthenticatingRealm
    protected void onInit() {
        super.onInit();
        processDefinitions();
    }

    public String getUserDefinitions() {
        return this.userDefinitions;
    }

    public void setUserDefinitions(String str) {
        this.userDefinitions = str;
    }

    public String getRoleDefinitions() {
        return this.roleDefinitions;
    }

    public void setRoleDefinitions(String str) {
        this.roleDefinitions = str;
    }

    protected void processDefinitions() {
        try {
            processRoleDefinitions();
            processUserDefinitions();
        } catch (ParseException e) {
            throw new ConfigurationException("Unable to parse user and/or role definitions.", e);
        }
    }

    protected void processRoleDefinitions() throws ParseException {
        String roleDefinitions = getRoleDefinitions();
        if (roleDefinitions == null) {
            return;
        }
        processRoleDefinitions(toMap(toLines(roleDefinitions)));
    }

    protected void processRoleDefinitions(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (String str : map.keySet()) {
            String str2 = map.get(str);
            SimpleRole role = getRole(str);
            if (role == null) {
                role = new SimpleRole(str);
                add(role);
            }
            role.setPermissions(PermissionUtils.resolveDelimitedPermissions(str2, getPermissionResolver()));
        }
    }

    protected void processUserDefinitions() throws ParseException {
        String userDefinitions = getUserDefinitions();
        if (userDefinitions == null) {
            return;
        }
        processUserDefinitions(toMap(toLines(userDefinitions)));
    }

    protected void processUserDefinitions(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (String str : map.keySet()) {
            String[] strArrSplit = StringUtils.split(map.get(str));
            String str2 = strArrSplit[0];
            SimpleAccount user = getUser(str);
            if (user == null) {
                user = new SimpleAccount(str, str2, getName());
                add(user);
            }
            user.setCredentials(str2);
            if (strArrSplit.length > 1) {
                for (int i = 1; i < strArrSplit.length; i++) {
                    String str3 = strArrSplit[i];
                    user.addRole(str3);
                    SimpleRole role = getRole(str3);
                    if (role != null) {
                        user.addObjectPermissions(role.getPermissions());
                    }
                }
            } else {
                user.setRoles(null);
            }
        }
    }

    protected static Set<String> toLines(String str) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Scanner scanner = new Scanner(str);
        while (scanner.hasNextLine()) {
            linkedHashSet.add(scanner.nextLine());
        }
        return linkedHashSet;
    }

    protected static Map<String, String> toMap(Collection<String> collection) throws ParseException {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        HashMap map = new HashMap();
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            String[] strArrSplitKeyValue = StringUtils.splitKeyValue(it.next());
            if (strArrSplitKeyValue != null) {
                map.put(strArrSplitKeyValue[0].trim(), strArrSplitKeyValue[1].trim());
            }
        }
        return map;
    }
}
