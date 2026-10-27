package org.apache.tools.ant.types.selectors.modifiedselector;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class PropertiesfileCache implements Cache {
    private Properties cache;
    private boolean cacheDirty;
    private boolean cacheLoaded;
    private File cachefile;

    public PropertiesfileCache() {
        this.cachefile = null;
        this.cache = new Properties();
        this.cacheLoaded = false;
        this.cacheDirty = true;
    }

    public PropertiesfileCache(File cachefile) {
        this.cachefile = null;
        this.cache = new Properties();
        this.cacheLoaded = false;
        this.cacheDirty = true;
        this.cachefile = cachefile;
    }

    public void setCachefile(File file) {
        this.cachefile = file;
    }

    public File getCachefile() {
        return this.cachefile;
    }

    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Cache
    public boolean isValid() {
        return this.cachefile != null;
    }

    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Cache
    public void load() {
        File file = this.cachefile;
        if (file != null && file.isFile() && this.cachefile.canRead()) {
            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(this.cachefile));
                this.cache.load(bis);
                bis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.cacheLoaded = true;
        this.cacheDirty = false;
    }

    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Cache
    public void save() {
        if (!this.cacheDirty) {
            return;
        }
        if (this.cachefile != null && this.cache.propertyNames().hasMoreElements()) {
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(this.cachefile));
                this.cache.store(bos, (String) null);
                bos.flush();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.cacheDirty = false;
    }

    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Cache
    public void delete() {
        this.cache = new Properties();
        this.cachefile.delete();
        this.cacheLoaded = true;
        this.cacheDirty = false;
    }

    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Cache
    public Object get(Object key) {
        if (!this.cacheLoaded) {
            load();
        }
        try {
            return this.cache.getProperty(String.valueOf(key));
        } catch (ClassCastException e) {
            return null;
        }
    }

    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Cache
    public void put(Object key, Object value) {
        this.cache.put(String.valueOf(key), String.valueOf(value));
        this.cacheDirty = true;
    }

    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Cache
    public Iterator iterator() {
        Vector v = new Vector();
        Enumeration<?> enumerationPropertyNames = this.cache.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            v.add(enumerationPropertyNames.nextElement());
        }
        return v.iterator();
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("<PropertiesfileCache:");
        buf.append("cachefile=");
        buf.append(this.cachefile);
        buf.append(";noOfEntries=");
        buf.append(this.cache.size());
        buf.append(">");
        return buf.toString();
    }
}
