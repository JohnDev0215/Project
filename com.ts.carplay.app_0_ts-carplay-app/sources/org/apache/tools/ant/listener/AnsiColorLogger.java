package org.apache.tools.ant.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;
import org.apache.tools.ant.DefaultLogger;

/* JADX INFO: loaded from: classes.dex */
public final class AnsiColorLogger extends DefaultLogger {
    private static final int ATTR_DIM = 2;
    private static final String END_COLOR = "\u001b[m";
    private static final int FG_BLUE = 34;
    private static final int FG_CYAN = 36;
    private static final int FG_GREEN = 32;
    private static final int FG_MAGENTA = 35;
    private static final int FG_RED = 31;
    private static final String PREFIX = "\u001b[";
    private static final char SEPARATOR = ';';
    private static final String SUFFIX = "m";
    private String errColor = "\u001b[2;31m";
    private String warnColor = "\u001b[2;35m";
    private String infoColor = "\u001b[2;36m";
    private String verboseColor = "\u001b[2;32m";
    private String debugColor = "\u001b[2;34m";
    private boolean colorsSet = false;

    private final void setColors() {
        String userColorFile = System.getProperty("ant.logger.defaults");
        InputStream in = null;
        try {
            Properties prop = new Properties();
            in = userColorFile != null ? new FileInputStream(userColorFile) : getClass().getResourceAsStream("/org/apache/tools/ant/listener/defaults.properties");
            if (in != null) {
                prop.load(in);
            }
            String err = prop.getProperty("AnsiColorLogger.ERROR_COLOR");
            String warn = prop.getProperty("AnsiColorLogger.WARNING_COLOR");
            String info = prop.getProperty("AnsiColorLogger.INFO_COLOR");
            String verbose = prop.getProperty("AnsiColorLogger.VERBOSE_COLOR");
            String debug = prop.getProperty("AnsiColorLogger.DEBUG_COLOR");
            if (err != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(PREFIX);
                stringBuffer.append(err);
                stringBuffer.append(SUFFIX);
                this.errColor = stringBuffer.toString();
            }
            if (warn != null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(PREFIX);
                stringBuffer2.append(warn);
                stringBuffer2.append(SUFFIX);
                this.warnColor = stringBuffer2.toString();
            }
            if (info != null) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append(PREFIX);
                stringBuffer3.append(info);
                stringBuffer3.append(SUFFIX);
                this.infoColor = stringBuffer3.toString();
            }
            if (verbose != null) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append(PREFIX);
                stringBuffer4.append(verbose);
                stringBuffer4.append(SUFFIX);
                this.verboseColor = stringBuffer4.toString();
            }
            if (debug != null) {
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append(PREFIX);
                stringBuffer5.append(debug);
                stringBuffer5.append(SUFFIX);
                this.debugColor = stringBuffer5.toString();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        } catch (IOException e2) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e3) {
                }
            }
        } catch (Throwable th) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
    }

    @Override // org.apache.tools.ant.DefaultLogger
    protected final void printMessage(String message, PrintStream stream, int priority) {
        if (message != null && stream != null) {
            if (!this.colorsSet) {
                setColors();
                this.colorsSet = true;
            }
            StringBuffer msg = new StringBuffer(message);
            if (priority == 0) {
                msg.insert(0, this.errColor);
                msg.append(END_COLOR);
            } else if (priority == 1) {
                msg.insert(0, this.warnColor);
                msg.append(END_COLOR);
            } else if (priority == 2) {
                msg.insert(0, this.infoColor);
                msg.append(END_COLOR);
            } else if (priority == 3) {
                msg.insert(0, this.verboseColor);
                msg.append(END_COLOR);
            } else if (priority == 4) {
                msg.insert(0, this.debugColor);
                msg.append(END_COLOR);
            }
            String strmessage = msg.toString();
            stream.println(strmessage);
        }
    }
}
