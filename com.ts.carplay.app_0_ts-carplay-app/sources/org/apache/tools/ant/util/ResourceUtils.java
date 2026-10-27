package org.apache.tools.ant.util;

import java.io.File;
import java.util.Date;
import java.util.Vector;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceFactory;
import org.apache.tools.ant.types.selectors.SelectorUtils;

/* JADX INFO: loaded from: classes.dex */
public class ResourceUtils {
    public static Resource[] selectOutOfDateSources(ProjectComponent logTo, Resource[] source, FileNameMapper mapper, ResourceFactory targets) {
        return selectOutOfDateSources(logTo, source, mapper, targets, FileUtils.newFileUtils().getFileTimestampGranularity());
    }

    public static Resource[] selectOutOfDateSources(ProjectComponent logTo, Resource[] source, FileNameMapper mapper, ResourceFactory targets, long granularity) {
        long now;
        long now2;
        long now3 = new Date().getTime() + granularity;
        Vector vresult = new Vector();
        int counter = 0;
        while (counter < source.length) {
            if (source[counter].getLastModified() > now3) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Warning: ");
                stringBuffer.append(source[counter].getName());
                stringBuffer.append(" modified in the future.");
                logTo.log(stringBuffer.toString(), 1);
            }
            char c = '/';
            String[] targetnames = mapper.mapFileName(source[counter].getName().replace('/', File.separatorChar));
            if (targetnames == null) {
                now = now3;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(source[counter].getName());
                stringBuffer2.append(" skipped - don't know how to handle it");
                logTo.log(stringBuffer2.toString(), 3);
            } else {
                boolean added = false;
                StringBuffer targetList = new StringBuffer();
                int ctarget = 0;
                while (true) {
                    if (added) {
                        now = now3;
                        break;
                    }
                    if (ctarget >= targetnames.length) {
                        now = now3;
                        break;
                    }
                    Resource atarget = targets.getResource(targetnames[ctarget].replace(File.separatorChar, c));
                    if (!atarget.isExists()) {
                        now2 = now3;
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append(source[counter].getName());
                        stringBuffer3.append(" added as ");
                        stringBuffer3.append(atarget.getName());
                        stringBuffer3.append(" doesn't exist.");
                        logTo.log(stringBuffer3.toString(), 3);
                        vresult.addElement(source[counter]);
                        added = true;
                    } else {
                        now2 = now3;
                        if (!atarget.isDirectory() && SelectorUtils.isOutOfDate(source[counter], atarget, (int) granularity)) {
                            StringBuffer stringBuffer4 = new StringBuffer();
                            stringBuffer4.append(source[counter].getName());
                            stringBuffer4.append(" added as ");
                            stringBuffer4.append(atarget.getName());
                            stringBuffer4.append(" is outdated.");
                            logTo.log(stringBuffer4.toString(), 3);
                            vresult.addElement(source[counter]);
                            added = true;
                        } else {
                            if (targetList.length() > 0) {
                                targetList.append(", ");
                            }
                            targetList.append(atarget.getName());
                        }
                    }
                    ctarget++;
                    now3 = now2;
                    c = '/';
                }
                if (!added) {
                    StringBuffer stringBuffer5 = new StringBuffer();
                    stringBuffer5.append(source[counter].getName());
                    stringBuffer5.append(" omitted as ");
                    stringBuffer5.append(targetList.toString());
                    stringBuffer5.append(targetnames.length == 1 ? " is" : " are ");
                    stringBuffer5.append(" up to date.");
                    logTo.log(stringBuffer5.toString(), 3);
                }
            }
            counter++;
            now3 = now;
        }
        Resource[] result = new Resource[vresult.size()];
        vresult.copyInto(result);
        return result;
    }
}
