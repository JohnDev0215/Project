package org.apache.shiro.session.mgt;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.StoppedSessionException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class SimpleSession implements ValidatingSession, Serializable {
    private static final int ATTRIBUTES_BIT_MASK;
    private static final int EXPIRED_BIT_MASK;
    private static final int HOST_BIT_MASK;
    private static final int LAST_ACCESS_TIME_BIT_MASK;
    protected static final long MILLIS_PER_HOUR = 3600000;
    protected static final long MILLIS_PER_MINUTE = 60000;
    protected static final long MILLIS_PER_SECOND = 1000;
    private static final int START_TIMESTAMP_BIT_MASK;
    private static final int STOP_TIMESTAMP_BIT_MASK;
    private static final int TIMEOUT_BIT_MASK;
    static int bitIndexCounter = 0;
    private static final long serialVersionUID = -7125642695178165650L;
    private transient Map<Object, Object> attributes;
    private transient boolean expired;
    private transient String host;
    private transient Serializable id;
    private transient Date lastAccessTime;
    private transient Date startTimestamp;
    private transient Date stopTimestamp;
    private transient long timeout;
    private static final transient Logger log = LoggerFactory.getLogger(SimpleSession.class);
    private static final int ID_BIT_MASK = 1 << 0;

    private static boolean isFieldPresent(short s, int i) {
        return (s & i) != 0;
    }

    static {
        bitIndexCounter = 0;
        int i = 0 + 1;
        bitIndexCounter = i;
        int i2 = i + 1;
        bitIndexCounter = i2;
        START_TIMESTAMP_BIT_MASK = 1 << i;
        int i3 = i2 + 1;
        bitIndexCounter = i3;
        STOP_TIMESTAMP_BIT_MASK = 1 << i2;
        int i4 = i3 + 1;
        bitIndexCounter = i4;
        LAST_ACCESS_TIME_BIT_MASK = 1 << i3;
        int i5 = i4 + 1;
        bitIndexCounter = i5;
        TIMEOUT_BIT_MASK = 1 << i4;
        int i6 = i5 + 1;
        bitIndexCounter = i6;
        EXPIRED_BIT_MASK = 1 << i5;
        int i7 = i6 + 1;
        bitIndexCounter = i7;
        HOST_BIT_MASK = 1 << i6;
        bitIndexCounter = i7 + 1;
        ATTRIBUTES_BIT_MASK = 1 << i7;
    }

    public SimpleSession() {
        this.timeout = AbstractSessionManager.DEFAULT_GLOBAL_SESSION_TIMEOUT;
        Date date = new Date();
        this.startTimestamp = date;
        this.lastAccessTime = date;
    }

    public SimpleSession(String str) {
        this();
        this.host = str;
    }

    @Override // org.apache.shiro.session.Session
    public Serializable getId() {
        return this.id;
    }

    public void setId(Serializable serializable) {
        this.id = serializable;
    }

    @Override // org.apache.shiro.session.Session
    public Date getStartTimestamp() {
        return this.startTimestamp;
    }

    public void setStartTimestamp(Date date) {
        this.startTimestamp = date;
    }

    public Date getStopTimestamp() {
        return this.stopTimestamp;
    }

    public void setStopTimestamp(Date date) {
        this.stopTimestamp = date;
    }

    @Override // org.apache.shiro.session.Session
    public Date getLastAccessTime() {
        return this.lastAccessTime;
    }

    public void setLastAccessTime(Date date) {
        this.lastAccessTime = date;
    }

    public boolean isExpired() {
        return this.expired;
    }

    public void setExpired(boolean z) {
        this.expired = z;
    }

    @Override // org.apache.shiro.session.Session
    public long getTimeout() {
        return this.timeout;
    }

    @Override // org.apache.shiro.session.Session
    public void setTimeout(long j) {
        this.timeout = j;
    }

    @Override // org.apache.shiro.session.Session
    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public Map<Object, Object> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<Object, Object> map) {
        this.attributes = map;
    }

    @Override // org.apache.shiro.session.Session
    public void touch() {
        this.lastAccessTime = new Date();
    }

    @Override // org.apache.shiro.session.Session
    public void stop() {
        if (this.stopTimestamp == null) {
            this.stopTimestamp = new Date();
        }
    }

    protected boolean isStopped() {
        return getStopTimestamp() != null;
    }

    protected void expire() {
        stop();
        this.expired = true;
    }

    @Override // org.apache.shiro.session.mgt.ValidatingSession
    public boolean isValid() {
        return (isStopped() || isExpired()) ? false : true;
    }

    protected boolean isTimedOut() {
        if (isExpired()) {
            return true;
        }
        long timeout = getTimeout();
        if (timeout >= 0) {
            Date lastAccessTime = getLastAccessTime();
            if (lastAccessTime == null) {
                throw new IllegalStateException("session.lastAccessTime for session with id [" + getId() + "] is null.  This value must be set at least once, preferably at least upon instantiation.  Please check the " + getClass().getName() + " implementation and ensure this value will be set (perhaps in the constructor?)");
            }
            return lastAccessTime.before(new Date(System.currentTimeMillis() - timeout));
        }
        Logger logger = log;
        if (!logger.isTraceEnabled()) {
            return false;
        }
        logger.trace("No timeout for session with id [" + getId() + "].  Session is not considered expired.");
        return false;
    }

    @Override // org.apache.shiro.session.mgt.ValidatingSession
    public void validate() throws InvalidSessionException {
        if (isStopped()) {
            throw new StoppedSessionException("Session with id [" + getId() + "] has been explicitly stopped.  No further interaction under this session is allowed.");
        }
        if (isTimedOut()) {
            expire();
            Date lastAccessTime = getLastAccessTime();
            long timeout = getTimeout();
            Serializable id = getId();
            DateFormat dateFormat = DateFormat.getInstance();
            String str = "Session with id [" + id + "] has expired. Last access time: " + dateFormat.format(lastAccessTime) + ".  Current time: " + dateFormat.format(new Date()) + ".  Session timeout is set to " + (timeout / MILLIS_PER_SECOND) + " seconds (" + (timeout / MILLIS_PER_MINUTE) + " minutes)";
            Logger logger = log;
            if (logger.isTraceEnabled()) {
                logger.trace(str);
            }
            throw new ExpiredSessionException(str);
        }
    }

    private Map<Object, Object> getAttributesLazy() {
        Map<Object, Object> attributes = getAttributes();
        if (attributes != null) {
            return attributes;
        }
        HashMap map = new HashMap();
        setAttributes(map);
        return map;
    }

    @Override // org.apache.shiro.session.Session
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return Collections.emptySet();
        }
        return attributes.keySet();
    }

    @Override // org.apache.shiro.session.Session
    public Object getAttribute(Object obj) {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return null;
        }
        return attributes.get(obj);
    }

    @Override // org.apache.shiro.session.Session
    public void setAttribute(Object obj, Object obj2) {
        if (obj2 == null) {
            removeAttribute(obj);
        } else {
            getAttributesLazy().put(obj, obj2);
        }
    }

    @Override // org.apache.shiro.session.Session
    public Object removeAttribute(Object obj) {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return null;
        }
        return attributes.remove(obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SimpleSession)) {
            return false;
        }
        SimpleSession simpleSession = (SimpleSession) obj;
        Serializable id = getId();
        Serializable id2 = simpleSession.getId();
        if (id != null && id2 != null) {
            return id.equals(id2);
        }
        return onEquals(simpleSession);
    }

    protected boolean onEquals(SimpleSession simpleSession) {
        if (getStartTimestamp() == null ? simpleSession.getStartTimestamp() == null : getStartTimestamp().equals(simpleSession.getStartTimestamp())) {
            if (getStopTimestamp() == null ? simpleSession.getStopTimestamp() == null : getStopTimestamp().equals(simpleSession.getStopTimestamp())) {
                if (getLastAccessTime() == null ? simpleSession.getLastAccessTime() == null : getLastAccessTime().equals(simpleSession.getLastAccessTime())) {
                    if (getTimeout() == simpleSession.getTimeout() && isExpired() == simpleSession.isExpired() && (getHost() == null ? simpleSession.getHost() == null : getHost().equals(simpleSession.getHost())) && (getAttributes() == null ? simpleSession.getAttributes() == null : getAttributes().equals(simpleSession.getAttributes()))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        Serializable id = getId();
        if (id != null) {
            return id.hashCode();
        }
        return ((((((((((((getStartTimestamp() != null ? getStartTimestamp().hashCode() : 0) * 31) + (getStopTimestamp() != null ? getStopTimestamp().hashCode() : 0)) * 31) + (getLastAccessTime() != null ? getLastAccessTime().hashCode() : 0)) * 31) + Long.valueOf(Math.max(getTimeout(), 0L)).hashCode()) * 31) + Boolean.valueOf(isExpired()).hashCode()) * 31) + (getHost() != null ? getHost().hashCode() : 0)) * 31) + (getAttributes() != null ? getAttributes().hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName()).append(",id=").append(getId());
        return sb.toString();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeShort(getAlteredFieldsBitMask());
        Serializable serializable = this.id;
        if (serializable != null) {
            objectOutputStream.writeObject(serializable);
        }
        Date date = this.startTimestamp;
        if (date != null) {
            objectOutputStream.writeObject(date);
        }
        Date date2 = this.stopTimestamp;
        if (date2 != null) {
            objectOutputStream.writeObject(date2);
        }
        Date date3 = this.lastAccessTime;
        if (date3 != null) {
            objectOutputStream.writeObject(date3);
        }
        long j = this.timeout;
        if (j != 0) {
            objectOutputStream.writeLong(j);
        }
        boolean z = this.expired;
        if (z) {
            objectOutputStream.writeBoolean(z);
        }
        String str = this.host;
        if (str != null) {
            objectOutputStream.writeUTF(str);
        }
        if (CollectionUtils.isEmpty(this.attributes)) {
            return;
        }
        objectOutputStream.writeObject(this.attributes);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        short s = objectInputStream.readShort();
        if (isFieldPresent(s, ID_BIT_MASK)) {
            this.id = (Serializable) objectInputStream.readObject();
        }
        if (isFieldPresent(s, START_TIMESTAMP_BIT_MASK)) {
            this.startTimestamp = (Date) objectInputStream.readObject();
        }
        if (isFieldPresent(s, STOP_TIMESTAMP_BIT_MASK)) {
            this.stopTimestamp = (Date) objectInputStream.readObject();
        }
        if (isFieldPresent(s, LAST_ACCESS_TIME_BIT_MASK)) {
            this.lastAccessTime = (Date) objectInputStream.readObject();
        }
        if (isFieldPresent(s, TIMEOUT_BIT_MASK)) {
            this.timeout = objectInputStream.readLong();
        }
        if (isFieldPresent(s, EXPIRED_BIT_MASK)) {
            this.expired = objectInputStream.readBoolean();
        }
        if (isFieldPresent(s, HOST_BIT_MASK)) {
            this.host = objectInputStream.readUTF();
        }
        if (isFieldPresent(s, ATTRIBUTES_BIT_MASK)) {
            this.attributes = (Map) objectInputStream.readObject();
        }
    }

    private short getAlteredFieldsBitMask() {
        int i = this.id != null ? 0 | ID_BIT_MASK : 0;
        if (this.startTimestamp != null) {
            i |= START_TIMESTAMP_BIT_MASK;
        }
        if (this.stopTimestamp != null) {
            i |= STOP_TIMESTAMP_BIT_MASK;
        }
        if (this.lastAccessTime != null) {
            i |= LAST_ACCESS_TIME_BIT_MASK;
        }
        if (this.timeout != 0) {
            i |= TIMEOUT_BIT_MASK;
        }
        if (this.expired) {
            i |= EXPIRED_BIT_MASK;
        }
        if (this.host != null) {
            i |= HOST_BIT_MASK;
        }
        if (!CollectionUtils.isEmpty(this.attributes)) {
            i |= ATTRIBUTES_BIT_MASK;
        }
        return (short) i;
    }
}
