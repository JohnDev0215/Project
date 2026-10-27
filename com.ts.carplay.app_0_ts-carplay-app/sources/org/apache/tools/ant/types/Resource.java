package org.apache.tools.ant.types;

/* JADX INFO: loaded from: classes.dex */
public class Resource implements Cloneable, Comparable {
    public static final long UNKNOWN_SIZE = -1;
    private boolean directory;
    private boolean exists;
    private long lastmodified;
    private String name;
    private long size;

    public Resource() {
        this.name = null;
        this.exists = true;
        this.lastmodified = 0L;
        this.directory = false;
        this.size = -1L;
    }

    public Resource(String name) {
        this(name, false, 0L, false);
    }

    public Resource(String name, boolean exists, long lastmodified) {
        this(name, exists, lastmodified, false);
    }

    public Resource(String name, boolean exists, long lastmodified, boolean directory) {
        this(name, exists, lastmodified, directory, -1L);
    }

    public Resource(String name, boolean exists, long lastmodified, boolean directory, long size) {
        this.name = null;
        this.exists = true;
        this.lastmodified = 0L;
        this.directory = false;
        this.size = -1L;
        this.name = name;
        setName(name);
        setExists(exists);
        setLastModified(lastmodified);
        setDirectory(directory);
        setSize(size);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExists() {
        return this.exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public long getLastModified() {
        if (!this.exists) {
            return 0L;
        }
        long j = this.lastmodified;
        if (j < 0) {
            return 0L;
        }
        return j;
    }

    public void setLastModified(long lastmodified) {
        this.lastmodified = lastmodified;
    }

    public boolean isDirectory() {
        return this.directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public void setSize(long size) {
        this.size = size > -1 ? size : -1L;
    }

    public long getSize() {
        if (this.exists) {
            return this.size;
        }
        return 0L;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("CloneNotSupportedException for a Clonable Resource caught?");
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Object other) {
        if (!(other instanceof Resource)) {
            throw new IllegalArgumentException("Can only be compared with Resources");
        }
        Resource r = (Resource) other;
        return getName().compareTo(r.getName());
    }
}
