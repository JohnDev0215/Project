package org.apache.shiro.realm.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.sql.DataSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.config.Ini;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class JdbcRealm extends AuthorizingRealm {
    protected static final String DEFAULT_AUTHENTICATION_QUERY = "select password from users where username = ?";
    protected static final String DEFAULT_PERMISSIONS_QUERY = "select permission from roles_permissions where role_name = ?";
    protected static final String DEFAULT_SALTED_AUTHENTICATION_QUERY = "select password, password_salt from users where username = ?";
    protected static final String DEFAULT_USER_ROLES_QUERY = "select role_name from user_roles where username = ?";
    private static final Logger log = LoggerFactory.getLogger(JdbcRealm.class);
    protected DataSource dataSource;
    protected String authenticationQuery = DEFAULT_AUTHENTICATION_QUERY;
    protected String userRolesQuery = DEFAULT_USER_ROLES_QUERY;
    protected String permissionsQuery = DEFAULT_PERMISSIONS_QUERY;
    protected boolean permissionsLookupEnabled = false;
    protected SaltStyle saltStyle = SaltStyle.NO_SALT;
    protected boolean saltIsBase64Encoded = true;

    public enum SaltStyle {
        NO_SALT,
        CRYPT,
        COLUMN,
        EXTERNAL
    }

    protected String getSaltForUser(String str) {
        return str;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setAuthenticationQuery(String str) {
        this.authenticationQuery = str;
    }

    public void setUserRolesQuery(String str) {
        this.userRolesQuery = str;
    }

    public void setPermissionsQuery(String str) {
        this.permissionsQuery = str;
    }

    public void setPermissionsLookupEnabled(boolean z) {
        this.permissionsLookupEnabled = z;
    }

    public void setSaltStyle(SaltStyle saltStyle) {
        this.saltStyle = saltStyle;
        if (saltStyle == SaltStyle.COLUMN && this.authenticationQuery.equals(DEFAULT_AUTHENTICATION_QUERY)) {
            this.authenticationQuery = DEFAULT_SALTED_AUTHENTICATION_QUERY;
        }
    }

    public void setSaltIsBase64Encoded(boolean z) {
        this.saltIsBase64Encoded = z;
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x00cf: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:46:0x00cf */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0051 A[Catch: SQLException -> 0x009e, all -> 0x00ce, TryCatch #1 {SQLException -> 0x009e, blocks: (B:6:0x0011, B:22:0x0051, B:24:0x0060, B:26:0x0066, B:28:0x006a, B:29:0x0076, B:32:0x0081, B:33:0x009d, B:15:0x002a, B:16:0x0035, B:17:0x003e, B:18:0x0045, B:19:0x0046), top: B:51:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0081 A[Catch: SQLException -> 0x009e, all -> 0x00ce, TRY_ENTER, TryCatch #1 {SQLException -> 0x009e, blocks: (B:6:0x0011, B:22:0x0051, B:24:0x0060, B:26:0x0066, B:28:0x006a, B:29:0x0076, B:32:0x0081, B:33:0x009d, B:15:0x002a, B:16:0x0035, B:17:0x003e, B:18:0x0045, B:19:0x0046), top: B:51:0x0011 }] */
    @Override // org.apache.shiro.realm.AuthenticatingRealm
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected org.apache.shiro.authc.AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.shiro.realm.jdbc.JdbcRealm.doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken):org.apache.shiro.authc.AuthenticationInfo");
    }

    /* JADX INFO: renamed from: org.apache.shiro.realm.jdbc.JdbcRealm$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$shiro$realm$jdbc$JdbcRealm$SaltStyle;

        static {
            int[] iArr = new int[SaltStyle.values().length];
            $SwitchMap$org$apache$shiro$realm$jdbc$JdbcRealm$SaltStyle = iArr;
            try {
                iArr[SaltStyle.NO_SALT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$shiro$realm$jdbc$JdbcRealm$SaltStyle[SaltStyle.CRYPT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$shiro$realm$jdbc$JdbcRealm$SaltStyle[SaltStyle.COLUMN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$shiro$realm$jdbc$JdbcRealm$SaltStyle[SaltStyle.EXTERNAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private String[] getPasswordForUser(Connection connection, String str) throws Throwable {
        String[] strArr;
        boolean z;
        PreparedStatement preparedStatementPrepareStatement;
        int i = AnonymousClass1.$SwitchMap$org$apache$shiro$realm$jdbc$JdbcRealm$SaltStyle[this.saltStyle.ordinal()];
        if (i == 1 || i == 2 || i == 4) {
            strArr = new String[1];
            z = false;
        } else {
            strArr = new String[2];
            z = true;
        }
        ResultSet resultSetExecuteQuery = null;
        try {
            preparedStatementPrepareStatement = connection.prepareStatement(this.authenticationQuery);
        } catch (Throwable th) {
            th = th;
            preparedStatementPrepareStatement = null;
        }
        try {
            preparedStatementPrepareStatement.setString(1, str);
            resultSetExecuteQuery = preparedStatementPrepareStatement.executeQuery();
            boolean z2 = false;
            while (resultSetExecuteQuery.next()) {
                if (z2) {
                    throw new AuthenticationException("More than one user row found for user [" + str + "]. Usernames must be unique.");
                }
                strArr[0] = resultSetExecuteQuery.getString(1);
                if (z) {
                    strArr[1] = resultSetExecuteQuery.getString(2);
                }
                z2 = true;
            }
            JdbcUtils.closeResultSet(resultSetExecuteQuery);
            JdbcUtils.closeStatement(preparedStatementPrepareStatement);
            return strArr;
        } catch (Throwable th2) {
            th = th2;
            JdbcUtils.closeResultSet(resultSetExecuteQuery);
            JdbcUtils.closeStatement(preparedStatementPrepareStatement);
            throw th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x005a: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:22:0x005a */
    @Override // org.apache.shiro.realm.AuthorizingRealm
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) throws Throwable {
        SQLException e;
        Connection connection;
        Connection connection2;
        if (principalCollection == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        String str = (String) getAvailablePrincipal(principalCollection);
        Connection connection3 = null;
        try {
            try {
                connection2 = this.dataSource.getConnection();
            } catch (SQLException e2) {
                e = e2;
            } catch (Throwable th) {
                th = th;
                JdbcUtils.closeConnection(connection3);
                throw th;
            }
            try {
                Set<String> roleNamesForUser = getRoleNamesForUser(connection2, str);
                Set<String> permissions = this.permissionsLookupEnabled ? getPermissions(connection2, str, roleNamesForUser) : null;
                JdbcUtils.closeConnection(connection2);
                SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roleNamesForUser);
                simpleAuthorizationInfo.setStringPermissions(permissions);
                return simpleAuthorizationInfo;
            } catch (SQLException e3) {
                e = e3;
                String str2 = "There was a SQL error while authorizing user [" + str + Ini.SECTION_SUFFIX;
                Logger logger = log;
                if (logger.isErrorEnabled()) {
                    logger.error(str2, e);
                }
                throw new AuthorizationException(str2, e);
            }
        } catch (Throwable th2) {
            th = th2;
            connection3 = connection;
            JdbcUtils.closeConnection(connection3);
            throw th;
        }
    }

    protected Set<String> getRoleNamesForUser(Connection connection, String str) throws Throwable {
        PreparedStatement preparedStatementPrepareStatement;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ResultSet resultSetExecuteQuery = null;
        try {
            preparedStatementPrepareStatement = connection.prepareStatement(this.userRolesQuery);
        } catch (Throwable th) {
            th = th;
            preparedStatementPrepareStatement = null;
        }
        try {
            preparedStatementPrepareStatement.setString(1, str);
            resultSetExecuteQuery = preparedStatementPrepareStatement.executeQuery();
            while (resultSetExecuteQuery.next()) {
                String string = resultSetExecuteQuery.getString(1);
                if (string != null) {
                    linkedHashSet.add(string);
                } else {
                    Logger logger = log;
                    if (logger.isWarnEnabled()) {
                        logger.warn("Null role name found while retrieving role names for user [" + str + Ini.SECTION_SUFFIX);
                    }
                }
            }
            JdbcUtils.closeResultSet(resultSetExecuteQuery);
            JdbcUtils.closeStatement(preparedStatementPrepareStatement);
            return linkedHashSet;
        } catch (Throwable th2) {
            th = th2;
            JdbcUtils.closeResultSet(resultSetExecuteQuery);
            JdbcUtils.closeStatement(preparedStatementPrepareStatement);
            throw th;
        }
    }

    protected Set<String> getPermissions(Connection connection, String str, Collection<String> collection) throws Throwable {
        PreparedStatement preparedStatementPrepareStatement;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatementPrepareStatement = connection.prepareStatement(this.permissionsQuery);
        } catch (Throwable th) {
            th = th;
        }
        try {
            Iterator<String> it = collection.iterator();
            while (it.hasNext()) {
                preparedStatementPrepareStatement.setString(1, it.next());
                try {
                    ResultSet resultSetExecuteQuery = preparedStatementPrepareStatement.executeQuery();
                    while (resultSetExecuteQuery.next()) {
                        try {
                            linkedHashSet.add(resultSetExecuteQuery.getString(1));
                        } catch (Throwable th2) {
                            th = th2;
                            resultSet = resultSetExecuteQuery;
                            JdbcUtils.closeResultSet(resultSet);
                            throw th;
                        }
                    }
                    JdbcUtils.closeResultSet(resultSetExecuteQuery);
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            JdbcUtils.closeStatement(preparedStatementPrepareStatement);
            return linkedHashSet;
        } catch (Throwable th4) {
            th = th4;
            preparedStatement = preparedStatementPrepareStatement;
            JdbcUtils.closeStatement(preparedStatement);
            throw th;
        }
    }
}
