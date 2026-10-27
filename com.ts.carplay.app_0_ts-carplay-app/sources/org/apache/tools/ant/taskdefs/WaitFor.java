package org.apache.tools.ant.taskdefs;

import java.util.Hashtable;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.taskdefs.condition.ConditionBase;
import org.apache.tools.ant.types.EnumeratedAttribute;

/* JADX INFO: loaded from: classes.dex */
public class WaitFor extends ConditionBase {
    private String timeoutProperty;
    private long maxWaitMillis = 180000;
    private long maxWaitMultiplier = 1;
    private long checkEveryMillis = 500;
    private long checkEveryMultiplier = 1;

    public void setMaxWait(long time) {
        this.maxWaitMillis = time;
    }

    public void setMaxWaitUnit(Unit unit) {
        this.maxWaitMultiplier = unit.getMultiplier();
    }

    public void setCheckEvery(long time) {
        this.checkEveryMillis = time;
    }

    public void setCheckEveryUnit(Unit unit) {
        this.checkEveryMultiplier = unit.getMultiplier();
    }

    public void setTimeoutProperty(String p) {
        this.timeoutProperty = p;
    }

    public void execute() throws BuildException {
        if (countConditions() > 1) {
            throw new BuildException("You must not nest more than one condition into <waitfor>");
        }
        if (countConditions() < 1) {
            throw new BuildException("You must nest a condition into <waitfor>");
        }
        Condition c = (Condition) getConditions().nextElement();
        long savedMaxWaitMillis = this.maxWaitMillis;
        long savedCheckEveryMillis = this.checkEveryMillis;
        try {
            this.maxWaitMillis *= this.maxWaitMultiplier;
            this.checkEveryMillis *= this.checkEveryMultiplier;
            long start = System.currentTimeMillis();
            long end = this.maxWaitMillis + start;
            while (System.currentTimeMillis() < end) {
                if (c.eval()) {
                    return;
                } else {
                    try {
                        Thread.sleep(this.checkEveryMillis);
                    } catch (InterruptedException e) {
                    }
                }
            }
            if (this.timeoutProperty != null) {
                getProject().setNewProperty(this.timeoutProperty, "true");
            }
        } finally {
            this.maxWaitMillis = savedMaxWaitMillis;
            this.checkEveryMillis = savedCheckEveryMillis;
        }
    }

    public static class Unit extends EnumeratedAttribute {
        private Hashtable timeTable;
        private static final String MILLISECOND = "millisecond";
        private static final String SECOND = "second";
        private static final String MINUTE = "minute";
        private static final String HOUR = "hour";
        private static final String DAY = "day";
        private static final String WEEK = "week";
        private static final String[] units = {MILLISECOND, SECOND, MINUTE, HOUR, DAY, WEEK};

        public Unit() {
            Hashtable hashtable = new Hashtable();
            this.timeTable = hashtable;
            hashtable.put(MILLISECOND, new Long(1L));
            this.timeTable.put(SECOND, new Long(1000L));
            this.timeTable.put(MINUTE, new Long(60000L));
            this.timeTable.put(HOUR, new Long(3600000L));
            this.timeTable.put(DAY, new Long(86400000L));
            this.timeTable.put(WEEK, new Long(604800000L));
        }

        public long getMultiplier() {
            String key = getValue().toLowerCase();
            Long l = (Long) this.timeTable.get(key);
            return l.longValue();
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return units;
        }
    }
}
