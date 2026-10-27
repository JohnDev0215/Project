package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.condition.Os;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.Parameter;

/* JADX INFO: loaded from: classes.dex */
public class DateSelector extends BaseExtendSelector {
    public static final String CHECKDIRS_KEY = "checkdirs";
    public static final String DATETIME_KEY = "datetime";
    public static final String GRANULARITY_KEY = "granularity";
    public static final String MILLIS_KEY = "millis";
    public static final String PATTERN_KEY = "pattern";
    public static final String WHEN_KEY = "when";
    private int granularity;
    private String pattern;
    private long millis = -1;
    private String dateTime = null;
    private boolean includeDirs = false;
    private int cmp = 2;

    public DateSelector() {
        this.granularity = 0;
        if (Os.isFamily("dos")) {
            this.granularity = 2000;
        }
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("{dateselector date: ");
        buf.append(this.dateTime);
        buf.append(" compare: ");
        int i = this.cmp;
        if (i == 0) {
            buf.append("before");
        } else if (i == 1) {
            buf.append("after");
        } else {
            buf.append("equal");
        }
        buf.append(" granularity: ");
        buf.append(this.granularity);
        if (this.pattern != null) {
            buf.append(" pattern: ");
            buf.append(this.pattern);
        }
        buf.append("}");
        return buf.toString();
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public long getMillis() {
        if (this.dateTime != null) {
            validate();
        }
        return this.millis;
    }

    public void setDatetime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setCheckdirs(boolean includeDirs) {
        this.includeDirs = includeDirs;
    }

    public void setGranularity(int granularity) {
        this.granularity = granularity;
    }

    public void setWhen(TimeComparisons cmp) {
        this.cmp = cmp.getIndex();
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.Parameterizable
    public void setParameters(Parameter[] parameters) {
        super.setParameters(parameters);
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                String paramname = parameters[i].getName();
                if (MILLIS_KEY.equalsIgnoreCase(paramname)) {
                    try {
                        setMillis(new Long(parameters[i].getValue()).longValue());
                    } catch (NumberFormatException e) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Invalid millisecond setting ");
                        stringBuffer.append(parameters[i].getValue());
                        setError(stringBuffer.toString());
                    }
                } else if (DATETIME_KEY.equalsIgnoreCase(paramname)) {
                    setDatetime(parameters[i].getValue());
                } else if (CHECKDIRS_KEY.equalsIgnoreCase(paramname)) {
                    setCheckdirs(Project.toBoolean(parameters[i].getValue()));
                } else if (GRANULARITY_KEY.equalsIgnoreCase(paramname)) {
                    try {
                        setGranularity(new Integer(parameters[i].getValue()).intValue());
                    } catch (NumberFormatException e2) {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Invalid granularity setting ");
                        stringBuffer2.append(parameters[i].getValue());
                        setError(stringBuffer2.toString());
                    }
                } else if ("when".equalsIgnoreCase(paramname)) {
                    TimeComparisons cmp = new TimeComparisons();
                    cmp.setValue(parameters[i].getValue());
                    setWhen(cmp);
                } else if ("pattern".equalsIgnoreCase(paramname)) {
                    setPattern(parameters[i].getValue());
                } else {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Invalid parameter ");
                    stringBuffer3.append(paramname);
                    setError(stringBuffer3.toString());
                }
            }
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        if (this.dateTime == null && this.millis < 0) {
            setError("You must provide a datetime or the number of milliseconds.");
            return;
        }
        if (this.millis < 0 && this.dateTime != null) {
            DateFormat df = this.pattern == null ? DateFormat.getDateTimeInstance(3, 3, Locale.US) : new SimpleDateFormat(this.pattern);
            try {
                setMillis(df.parse(this.dateTime).getTime());
                if (this.millis < 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Date of ");
                    stringBuffer.append(this.dateTime);
                    stringBuffer.append(" results in negative milliseconds value");
                    stringBuffer.append(" relative to epoch (January 1, 1970, 00:00:00 GMT).");
                    setError(stringBuffer.toString());
                }
            } catch (ParseException e) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Date of ");
                stringBuffer2.append(this.dateTime);
                stringBuffer2.append(" Cannot be parsed correctly. It should be in");
                String str = this.pattern;
                if (str == null) {
                    str = " MM/DD/YYYY HH:MM AM_PM";
                }
                stringBuffer2.append(str);
                stringBuffer2.append(" format.");
                setError(stringBuffer2.toString());
            }
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File basedir, String filename, File file) {
        validate();
        if (file.isDirectory() && !this.includeDirs) {
            return true;
        }
        int i = this.cmp;
        return i == 0 ? file.lastModified() - ((long) this.granularity) < this.millis : i == 1 ? file.lastModified() + ((long) this.granularity) > this.millis : Math.abs(file.lastModified() - this.millis) <= ((long) this.granularity);
    }

    public static class TimeComparisons extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"before", "after", "equal"};
        }
    }
}
