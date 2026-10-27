package org.apache.shiro.realm.jndi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.shiro.jndi.JndiLocator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.RealmFactory;
import org.apache.shiro.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class JndiRealmFactory extends JndiLocator implements RealmFactory {
    Collection<String> jndiNames = null;

    public Collection<String> getJndiNames() {
        return this.jndiNames;
    }

    public void setJndiNames(Collection<String> collection) {
        this.jndiNames = collection;
    }

    public void setJndiNames(String str) throws IllegalStateException {
        String strClean = StringUtils.clean(str);
        if (strClean == null) {
            throw new IllegalStateException("One or more comma-delimited jndi names must be specified for the " + getClass().getName() + " to locate Realms.");
        }
        setJndiNames(Arrays.asList(StringUtils.tokenizeToStringArray(strClean, ",")));
    }

    @Override // org.apache.shiro.realm.RealmFactory
    public Collection<Realm> getRealms() throws IllegalStateException {
        Collection<String> jndiNames = getJndiNames();
        if (jndiNames == null || jndiNames.isEmpty()) {
            throw new IllegalStateException("One or more jndi names must be specified for the " + getClass().getName() + " to locate Realms.");
        }
        ArrayList arrayList = new ArrayList(jndiNames.size());
        for (String str : jndiNames) {
            try {
                arrayList.add((Realm) lookup(str, Realm.class));
            } catch (Exception e) {
                throw new IllegalStateException("Unable to look up realm with jndi name '" + str + "'.", e);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }
}
