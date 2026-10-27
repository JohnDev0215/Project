package org.apache.tools.ant.taskdefs;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/* JADX INFO: loaded from: classes.dex */
public abstract class JDBCTask extends Task {
    private static Hashtable loaderMap = new Hashtable(3);
    private Path classpath;
    private AntClassLoader loader;
    private boolean caching = true;
    private boolean autocommit = false;
    private String driver = null;
    private String url = null;
    private String userId = null;
    private String password = null;
    private String rdbms = null;
    private String version = null;

    public void setClasspath(Path classpath) {
        this.classpath = classpath;
    }

    public void setCaching(boolean enable) {
        this.caching = enable;
    }

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    public void setClasspathRef(Reference r) {
        createClasspath().setRefid(r);
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAutocommit(boolean autocommit) {
        this.autocommit = autocommit;
    }

    public void setRdbms(String rdbms) {
        this.rdbms = rdbms;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0092, code lost:
    
        if (r3.indexOf(r5.toString()) < 0) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean isValidRdbms(java.sql.Connection r8) {
        /*
            r7 = this;
            java.lang.String r0 = r7.rdbms
            r1 = 1
            if (r0 != 0) goto La
            java.lang.String r0 = r7.version
            if (r0 != 0) goto La
            return r1
        La:
            r0 = 0
            java.sql.DatabaseMetaData r2 = r8.getMetaData()     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r3 = r7.rdbms     // Catch: java.sql.SQLException -> Lb2
            r4 = 3
            if (r3 == 0) goto L51
            java.lang.String r3 = r2.getDatabaseProductName()     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r3 = r3.toLowerCase()     // Catch: java.sql.SQLException -> Lb2
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch: java.sql.SQLException -> Lb2
            r5.<init>()     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r6 = "RDBMS = "
            r5.append(r6)     // Catch: java.sql.SQLException -> Lb2
            r5.append(r3)     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r5 = r5.toString()     // Catch: java.sql.SQLException -> Lb2
            r7.log(r5, r4)     // Catch: java.sql.SQLException -> Lb2
            if (r3 == 0) goto L3a
            java.lang.String r5 = r7.rdbms     // Catch: java.sql.SQLException -> Lb2
            int r5 = r3.indexOf(r5)     // Catch: java.sql.SQLException -> Lb2
            if (r5 >= 0) goto L51
        L3a:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.sql.SQLException -> Lb2
            r1.<init>()     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r5 = "Not the required RDBMS: "
            r1.append(r5)     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r5 = r7.rdbms     // Catch: java.sql.SQLException -> Lb2
            r1.append(r5)     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r1 = r1.toString()     // Catch: java.sql.SQLException -> Lb2
            r7.log(r1, r4)     // Catch: java.sql.SQLException -> Lb2
            return r0
        L51:
            java.lang.String r3 = r7.version     // Catch: java.sql.SQLException -> Lb2
            if (r3 == 0) goto Lb0
            java.lang.String r3 = r2.getDatabaseProductVersion()     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r3 = r3.toLowerCase()     // Catch: java.sql.SQLException -> Lb2
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch: java.sql.SQLException -> Lb2
            r5.<init>()     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r6 = "Version = "
            r5.append(r6)     // Catch: java.sql.SQLException -> Lb2
            r5.append(r3)     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r5 = r5.toString()     // Catch: java.sql.SQLException -> Lb2
            r7.log(r5, r4)     // Catch: java.sql.SQLException -> Lb2
            if (r3 == 0) goto L94
            java.lang.String r5 = r7.version     // Catch: java.sql.SQLException -> Lb2
            boolean r5 = r3.startsWith(r5)     // Catch: java.sql.SQLException -> Lb2
            if (r5 != 0) goto Lb0
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch: java.sql.SQLException -> Lb2
            r5.<init>()     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r6 = " "
            r5.append(r6)     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r6 = r7.version     // Catch: java.sql.SQLException -> Lb2
            r5.append(r6)     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r5 = r5.toString()     // Catch: java.sql.SQLException -> Lb2
            int r5 = r3.indexOf(r5)     // Catch: java.sql.SQLException -> Lb2
            if (r5 >= 0) goto Lb0
        L94:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.sql.SQLException -> Lb2
            r1.<init>()     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r5 = "Not the required version: \""
            r1.append(r5)     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r5 = r7.version     // Catch: java.sql.SQLException -> Lb2
            r1.append(r5)     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r5 = "\""
            r1.append(r5)     // Catch: java.sql.SQLException -> Lb2
            java.lang.String r1 = r1.toString()     // Catch: java.sql.SQLException -> Lb2
            r7.log(r1, r4)     // Catch: java.sql.SQLException -> Lb2
            return r0
        Lb0:
            return r1
        Lb2:
            r1 = move-exception
            java.lang.String r2 = "Failed to obtain required RDBMS information"
            r7.log(r2, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.JDBCTask.isValidRdbms(java.sql.Connection):boolean");
    }

    protected static Hashtable getLoaderMap() {
        return loaderMap;
    }

    protected AntClassLoader getLoader() {
        return this.loader;
    }

    protected Connection getConnection() throws BuildException {
        if (this.userId == null) {
            throw new BuildException("User Id attribute must be set!", getLocation());
        }
        if (this.password == null) {
            throw new BuildException("Password attribute must be set!", getLocation());
        }
        if (this.url == null) {
            throw new BuildException("Url attribute must be set!", getLocation());
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("connecting to ");
            stringBuffer.append(getUrl());
            log(stringBuffer.toString(), 3);
            Properties info = new Properties();
            info.put("user", getUserId());
            info.put("password", getPassword());
            Connection conn = getDriver().connect(getUrl(), info);
            if (conn == null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("No suitable Driver for ");
                stringBuffer2.append(this.url);
                throw new SQLException(stringBuffer2.toString());
            }
            conn.setAutoCommit(this.autocommit);
            return conn;
        } catch (SQLException e) {
            throw new BuildException(e, getLocation());
        }
    }

    private Driver getDriver() throws ClassNotFoundException, BuildException {
        Object obj;
        Class<?> cls;
        if (this.driver == null) {
            throw new BuildException("Driver attribute must be set!", getLocation());
        }
        try {
            try {
                try {
                    if (this.classpath != null) {
                        try {
                            synchronized (loaderMap) {
                                if (this.caching) {
                                    this.loader = (AntClassLoader) loaderMap.get(this.driver);
                                }
                                if (this.loader == null) {
                                    StringBuffer stringBuffer = new StringBuffer();
                                    stringBuffer.append("Loading ");
                                    stringBuffer.append(this.driver);
                                    stringBuffer.append(" using AntClassLoader with classpath ");
                                    stringBuffer.append(this.classpath);
                                    log(stringBuffer.toString(), 3);
                                    AntClassLoader antClassLoaderCreateClassLoader = getProject().createClassLoader(this.classpath);
                                    this.loader = antClassLoaderCreateClassLoader;
                                    if (this.caching) {
                                        loaderMap.put(this.driver, antClassLoaderCreateClassLoader);
                                    }
                                } else {
                                    StringBuffer stringBuffer2 = new StringBuffer();
                                    stringBuffer2.append("Loading ");
                                    stringBuffer2.append(this.driver);
                                    stringBuffer2.append(" using a cached AntClassLoader.");
                                    log(stringBuffer2.toString(), 3);
                                }
                            }
                            cls = this.loader.loadClass(this.driver);
                        } catch (InstantiationException e) {
                            StringBuffer stringBuffer3 = new StringBuffer();
                            stringBuffer3.append("Instantiation Exception: JDBC driver ");
                            stringBuffer3.append(this.driver);
                            stringBuffer3.append(" could not be loaded");
                            throw new BuildException(stringBuffer3.toString(), getLocation());
                        }
                    } else {
                        try {
                            StringBuffer stringBuffer4 = new StringBuffer();
                            stringBuffer4.append("Loading ");
                            stringBuffer4.append(this.driver);
                            stringBuffer4.append(" using system loader.");
                            log(stringBuffer4.toString(), 3);
                            cls = Class.forName(this.driver);
                        } catch (IllegalAccessException e2) {
                            StringBuffer stringBuffer5 = new StringBuffer();
                            stringBuffer5.append("Illegal Access: JDBC driver ");
                            stringBuffer5.append(this.driver);
                            stringBuffer5.append(" could not be loaded");
                            throw new BuildException(stringBuffer5.toString(), getLocation());
                        } catch (InstantiationException e3) {
                            obj = null;
                            StringBuffer stringBuffer32 = new StringBuffer();
                            stringBuffer32.append("Instantiation Exception: JDBC driver ");
                            stringBuffer32.append(this.driver);
                            stringBuffer32.append(" could not be loaded");
                            throw new BuildException(stringBuffer32.toString(), getLocation());
                        }
                    }
                    Driver driverInstance = (Driver) cls.newInstance();
                    return driverInstance;
                } catch (ClassNotFoundException e4) {
                    StringBuffer stringBuffer6 = new StringBuffer();
                    stringBuffer6.append("Class Not Found: JDBC driver ");
                    stringBuffer6.append(this.driver);
                    stringBuffer6.append(" could not be loaded");
                    throw new BuildException(stringBuffer6.toString(), getLocation());
                }
            } catch (IllegalAccessException e5) {
            }
        } catch (InstantiationException e6) {
            obj = null;
        }
    }

    public void isCaching(boolean value) {
        this.caching = value;
    }

    public Path getClasspath() {
        return this.classpath;
    }

    public boolean isAutocommit() {
        return this.autocommit;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserid(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRdbms() {
        return this.rdbms;
    }

    public String getVersion() {
        return this.version;
    }
}
