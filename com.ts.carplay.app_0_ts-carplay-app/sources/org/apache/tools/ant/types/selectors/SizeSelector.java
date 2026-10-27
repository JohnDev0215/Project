package org.apache.tools.ant.types.selectors;

import java.io.File;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.Parameter;

/* JADX INFO: loaded from: classes.dex */
public class SizeSelector extends BaseExtendSelector {
    public static final String SIZE_KEY = "value";
    public static final String UNITS_KEY = "units";
    public static final String WHEN_KEY = "when";
    private long size = -1;
    private long multiplier = 1;
    private long sizelimit = -1;
    private int cmp = 2;

    public String toString() {
        StringBuffer buf = new StringBuffer("{sizeselector value: ");
        buf.append(this.sizelimit);
        buf.append("compare: ");
        int i = this.cmp;
        if (i == 0) {
            buf.append("less");
        } else if (i == 1) {
            buf.append("more");
        } else {
            buf.append("equal");
        }
        buf.append("}");
        return buf.toString();
    }

    public void setValue(long size) {
        this.size = size;
        long j = this.multiplier;
        if (j != 0 && size > -1) {
            this.sizelimit = j * size;
        }
    }

    public void setUnits(ByteUnits units) {
        int i = units.getIndex();
        this.multiplier = 0L;
        if (i > -1 && i < 4) {
            this.multiplier = 1000L;
        } else if (i > 3 && i < 9) {
            this.multiplier = 1024L;
        } else if (i > 8 && i < 13) {
            this.multiplier = 1000000L;
        } else if (i > 12 && i < 18) {
            this.multiplier = 1048576L;
        } else if (i > 17 && i < 22) {
            this.multiplier = 1000000000L;
        } else if (i > 21 && i < 27) {
            this.multiplier = 1073741824L;
        } else if (i > 26 && i < 31) {
            this.multiplier = 1000000000000L;
        } else if (i > 30 && i < 36) {
            this.multiplier = 1099511627776L;
        }
        long j = this.multiplier;
        if (j > 0) {
            long j2 = this.size;
            if (j2 > -1) {
                this.sizelimit = j2 * j;
            }
        }
    }

    public void setWhen(SizeComparisons scmp) {
        this.cmp = scmp.getIndex();
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.Parameterizable
    public void setParameters(Parameter[] parameters) {
        super.setParameters(parameters);
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                String paramname = parameters[i].getName();
                if (SIZE_KEY.equalsIgnoreCase(paramname)) {
                    try {
                        setValue(new Long(parameters[i].getValue()).longValue());
                    } catch (NumberFormatException e) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Invalid size setting ");
                        stringBuffer.append(parameters[i].getValue());
                        setError(stringBuffer.toString());
                    }
                } else if (UNITS_KEY.equalsIgnoreCase(paramname)) {
                    ByteUnits units = new ByteUnits();
                    units.setValue(parameters[i].getValue());
                    setUnits(units);
                } else if ("when".equalsIgnoreCase(paramname)) {
                    SizeComparisons scmp = new SizeComparisons();
                    scmp.setValue(parameters[i].getValue());
                    setWhen(scmp);
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Invalid parameter ");
                    stringBuffer2.append(paramname);
                    setError(stringBuffer2.toString());
                }
            }
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        if (this.size < 0) {
            setError("The value attribute is required, and must be positive");
        } else if (this.multiplier < 1) {
            setError("Invalid Units supplied, must be K,Ki,M,Mi,G,Gi,T,or Ti");
        } else if (this.sizelimit < 0) {
            setError("Internal error: Code is not setting sizelimit correctly");
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File basedir, String filename, File file) {
        validate();
        if (file.isDirectory()) {
            return true;
        }
        int i = this.cmp;
        return i == 0 ? file.length() < this.sizelimit : i == 1 ? file.length() > this.sizelimit : file.length() == this.sizelimit;
    }

    public static class ByteUnits extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"K", "k", "kilo", "KILO", "Ki", "KI", "ki", "kibi", "KIBI", "M", "m", "mega", "MEGA", "Mi", "MI", "mi", "mebi", "MEBI", "G", "g", "giga", "GIGA", "Gi", "GI", "gi", "gibi", "GIBI", "T", "t", "tera", "TERA", "Ti", "TI", "ti", "tebi", "TEBI"};
        }
    }

    public static class SizeComparisons extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"less", "more", "equal"};
        }
    }
}
