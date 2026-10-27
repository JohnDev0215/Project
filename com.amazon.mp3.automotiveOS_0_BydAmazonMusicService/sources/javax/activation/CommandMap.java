package javax.activation;

import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes7.dex */
public abstract class CommandMap {
    private static CommandMap defaultCommandMap;
    private static Map<ClassLoader, CommandMap> map = new WeakHashMap();

    public abstract DataContentHandler createDataContentHandler(String str);

    public static synchronized CommandMap getDefaultCommandMap() {
        CommandMap commandMap = defaultCommandMap;
        if (commandMap != null) {
            return commandMap;
        }
        ClassLoader contextClassLoader = SecuritySupport.getContextClassLoader();
        CommandMap mailcapCommandMap = map.get(contextClassLoader);
        if (mailcapCommandMap == null) {
            mailcapCommandMap = new MailcapCommandMap();
            map.put(contextClassLoader, mailcapCommandMap);
        }
        return mailcapCommandMap;
    }

    public DataContentHandler createDataContentHandler(String str, DataSource dataSource) {
        return createDataContentHandler(str);
    }
}
