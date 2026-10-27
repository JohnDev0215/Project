package org.apache.tools.ant.taskdefs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.EnumeratedAttribute;

/* JADX INFO: loaded from: classes.dex */
public class Tstamp extends Task {
    private Vector customFormats = new Vector();
    private String prefix = "";

    public void setPrefix(String prefix) {
        this.prefix = prefix;
        if (!prefix.endsWith(".")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.prefix);
            stringBuffer.append(".");
            this.prefix = stringBuffer.toString();
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        try {
            Date d = new Date();
            Enumeration i = this.customFormats.elements();
            while (i.hasMoreElements()) {
                CustomFormat cts = (CustomFormat) i.nextElement();
                cts.execute(getProject(), d, getLocation());
            }
            SimpleDateFormat dstamp = new SimpleDateFormat("yyyyMMdd");
            setProperty("DSTAMP", dstamp.format(d));
            SimpleDateFormat tstamp = new SimpleDateFormat("HHmm");
            setProperty("TSTAMP", tstamp.format(d));
            SimpleDateFormat today = new SimpleDateFormat("MMMM d yyyy", Locale.US);
            setProperty("TODAY", today.format(d));
        } catch (Exception e) {
            throw new BuildException(e);
        }
    }

    public CustomFormat createFormat() {
        CustomFormat cts = new CustomFormat();
        this.customFormats.addElement(cts);
        return cts;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProperty(String name, String value) {
        Project project = getProject();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.prefix);
        stringBuffer.append(name);
        project.setNewProperty(stringBuffer.toString(), value);
    }

    public class CustomFormat {
        private String country;
        private String language;
        private String pattern;
        private String propertyName;
        private TimeZone timeZone;
        private String variant;
        private int offset = 0;
        private int field = 5;

        public CustomFormat() {
        }

        public void setProperty(String propertyName) {
            this.propertyName = propertyName;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        public void setLocale(String locale) {
            StringTokenizer st = new StringTokenizer(locale, " \t\n\r\f,");
            try {
                this.language = st.nextToken();
                if (st.hasMoreElements()) {
                    this.country = st.nextToken();
                    if (st.hasMoreElements()) {
                        this.variant = st.nextToken();
                        if (st.hasMoreElements()) {
                            throw new BuildException("bad locale format", Tstamp.this.getLocation());
                        }
                        return;
                    }
                    return;
                }
                this.country = "";
            } catch (NoSuchElementException e) {
                throw new BuildException("bad locale format", e, Tstamp.this.getLocation());
            }
        }

        public void setTimezone(String id) {
            this.timeZone = TimeZone.getTimeZone(id);
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public void setUnit(String unit) {
            Tstamp.this.log("DEPRECATED - The setUnit(String) method has been deprecated. Use setUnit(Tstamp.Unit) instead.");
            Unit u = new Unit();
            u.setValue(unit);
            this.field = u.getCalendarField();
        }

        public void setUnit(Unit unit) {
            this.field = unit.getCalendarField();
        }

        public void execute(Project project, Date date, Location location) {
            SimpleDateFormat sdf;
            if (this.propertyName == null) {
                throw new BuildException("property attribute must be provided", location);
            }
            if (this.pattern == null) {
                throw new BuildException("pattern attribute must be provided", location);
            }
            if (this.language == null) {
                sdf = new SimpleDateFormat(this.pattern);
            } else if (this.variant == null) {
                sdf = new SimpleDateFormat(this.pattern, new Locale(this.language, this.country));
            } else {
                sdf = new SimpleDateFormat(this.pattern, new Locale(this.language, this.country, this.variant));
            }
            if (this.offset != 0) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(this.field, this.offset);
                date = calendar.getTime();
            }
            TimeZone timeZone = this.timeZone;
            if (timeZone != null) {
                sdf.setTimeZone(timeZone);
            }
            Tstamp.this.setProperty(this.propertyName, sdf.format(date));
        }
    }

    public static class Unit extends EnumeratedAttribute {
        private Hashtable calendarFields;
        private static final String MILLISECOND = "millisecond";
        private static final String SECOND = "second";
        private static final String MINUTE = "minute";
        private static final String HOUR = "hour";
        private static final String DAY = "day";
        private static final String WEEK = "week";
        private static final String MONTH = "month";
        private static final String YEAR = "year";
        private static final String[] units = {MILLISECOND, SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR};

        public Unit() {
            Hashtable hashtable = new Hashtable();
            this.calendarFields = hashtable;
            hashtable.put(MILLISECOND, new Integer(14));
            this.calendarFields.put(SECOND, new Integer(13));
            this.calendarFields.put(MINUTE, new Integer(12));
            this.calendarFields.put(HOUR, new Integer(11));
            this.calendarFields.put(DAY, new Integer(5));
            this.calendarFields.put(WEEK, new Integer(3));
            this.calendarFields.put(MONTH, new Integer(2));
            this.calendarFields.put(YEAR, new Integer(1));
        }

        public int getCalendarField() {
            String key = getValue().toLowerCase();
            Integer i = (Integer) this.calendarFields.get(key);
            return i.intValue();
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return units;
        }
    }
}
