package org.apache.tools.ant.input;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.tools.ant.BuildException;

/* JADX INFO: loaded from: classes.dex */
public class PropertyFileInputHandler implements InputHandler {
    public static final String FILE_NAME_KEY = "ant.input.properties";
    private Properties props = null;

    @Override // org.apache.tools.ant.input.InputHandler
    public void handleInput(InputRequest request) throws BuildException {
        readProps();
        Object o = this.props.get(request.getPrompt());
        if (o == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to find input for '");
            stringBuffer.append(request.getPrompt());
            stringBuffer.append("'");
            throw new BuildException(stringBuffer.toString());
        }
        request.setInput(o.toString());
        if (!request.isInputValid()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Found invalid input ");
            stringBuffer2.append(o);
            stringBuffer2.append(" for '");
            stringBuffer2.append(request.getPrompt());
            stringBuffer2.append("'");
            throw new BuildException(stringBuffer2.toString());
        }
    }

    private synchronized void readProps() throws BuildException {
        if (this.props == null) {
            String propsFile = System.getProperty(FILE_NAME_KEY);
            if (propsFile == null) {
                throw new BuildException("System property ant.input.properties for PropertyFileInputHandler not set");
            }
            Properties properties = new Properties();
            this.props = properties;
            try {
                properties.load(new FileInputStream(propsFile));
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Couldn't load ");
                stringBuffer.append(propsFile);
                throw new BuildException(stringBuffer.toString(), e);
            }
        }
    }
}
