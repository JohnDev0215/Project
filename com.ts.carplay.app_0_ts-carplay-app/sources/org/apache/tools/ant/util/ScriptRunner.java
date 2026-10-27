package org.apache.tools.ant.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.tools.ant.BuildException;

/* JADX INFO: loaded from: classes.dex */
public class ScriptRunner {
    private String language;
    private String script = "";
    private Map beans = new HashMap();

    static {
        BSFManager.registerScriptingEngine("groovy", "org.codehaus.groovy.bsf.GroovyEngine", new String[]{"groovy", "gy"});
    }

    public void addBeans(Map dictionary) {
        for (String key : dictionary.keySet()) {
            try {
                Object val = dictionary.get(key);
                addBean(key, val);
            } catch (BuildException e) {
            }
        }
    }

    public void addBean(String key, Object bean) {
        boolean z = false;
        if (key.length() > 0 && Character.isJavaIdentifierStart(key.charAt(0))) {
            z = true;
        }
        boolean isValid = z;
        for (int i = 1; isValid && i < key.length(); i++) {
            isValid = Character.isJavaIdentifierPart(key.charAt(i));
        }
        if (isValid) {
            this.beans.put(key, bean);
        }
    }

    public void executeScript(String execName) throws BuildException {
        if (this.language == null) {
            throw new BuildException("script language must be specified");
        }
        try {
            BSFManager manager = new BSFManager();
            for (String key : this.beans.keySet()) {
                Object value = this.beans.get(key);
                if (value != null) {
                    manager.declareBean(key, value, value.getClass());
                } else {
                    manager.undeclareBean(key);
                }
            }
            manager.exec(this.language, execName, 0, 0, this.script);
        } catch (BSFException e) {
            Throwable t = e;
            Throwable te = e.getTargetException();
            if (te != null) {
                if (te instanceof BuildException) {
                    throw ((BuildException) te);
                }
                t = te;
            }
            throw new BuildException(t);
        }
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setSrc(File file) {
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("file ");
            stringBuffer.append(file.getPath());
            stringBuffer.append(" not found.");
            throw new BuildException(stringBuffer.toString());
        }
        int count = (int) file.length();
        byte[] data = new byte[count];
        try {
            FileInputStream inStream = new FileInputStream(file);
            inStream.read(data);
            inStream.close();
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(this.script);
            stringBuffer2.append(new String(data));
            this.script = stringBuffer2.toString();
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }

    public void addText(String text) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.script);
        stringBuffer.append(text);
        this.script = stringBuffer.toString();
    }
}
