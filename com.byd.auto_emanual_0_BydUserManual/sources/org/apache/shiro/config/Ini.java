package org.apache.shiro.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class Ini implements Map<String, Section> {
    public static final String COMMENT_POUND = "#";
    public static final String COMMENT_SEMICOLON = ";";
    public static final String DEFAULT_CHARSET_NAME = "UTF-8";
    public static final String DEFAULT_SECTION_NAME = "";
    protected static final char ESCAPE_TOKEN = '\\';
    public static final String SECTION_PREFIX = "[";
    public static final String SECTION_SUFFIX = "]";
    private static final transient Logger log = LoggerFactory.getLogger(Ini.class);
    private final Map<String, Section> sections;

    public Ini() {
        this.sections = new LinkedHashMap();
    }

    public Ini(Ini ini) {
        this();
        Objects.requireNonNull(ini, "Defaults cannot be null.");
        for (Section section : ini.getSections()) {
            this.sections.put(section.getName(), new Section(section));
        }
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        Collection<Section> collectionValues = this.sections.values();
        if (collectionValues.isEmpty()) {
            return true;
        }
        Iterator<Section> it = collectionValues.iterator();
        while (it.hasNext()) {
            if (!it.next().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public Set<String> getSectionNames() {
        return Collections.unmodifiableSet(this.sections.keySet());
    }

    public Collection<Section> getSections() {
        return Collections.unmodifiableCollection(this.sections.values());
    }

    public Section getSection(String str) {
        return this.sections.get(cleanName(str));
    }

    public Section addSection(String str) {
        String strCleanName = cleanName(str);
        Section section = getSection(strCleanName);
        if (section != null) {
            return section;
        }
        Section section2 = new Section(strCleanName);
        this.sections.put(strCleanName, section2);
        return section2;
    }

    public Section removeSection(String str) {
        return this.sections.remove(cleanName(str));
    }

    private static String cleanName(String str) {
        String strClean = StringUtils.clean(str);
        if (strClean != null) {
            return strClean;
        }
        log.trace("Specified name was null or empty.  Defaulting to the default section (name = \"\")");
        return "";
    }

    public void setSectionProperty(String str, String str2, String str3) {
        String strCleanName = cleanName(str);
        Section section = getSection(strCleanName);
        if (section == null) {
            section = addSection(strCleanName);
        }
        section.put(str2, str3);
    }

    public String getSectionProperty(String str, String str2) {
        Section section = getSection(str);
        if (section != null) {
            return section.get((Object) str2);
        }
        return null;
    }

    public String getSectionProperty(String str, String str2, String str3) {
        String sectionProperty = getSectionProperty(str, str2);
        return sectionProperty != null ? sectionProperty : str3;
    }

    public static Ini fromResourcePath(String str) throws ConfigurationException {
        if (!StringUtils.hasLength(str)) {
            throw new IllegalArgumentException("Resource Path argument cannot be null or empty.");
        }
        Ini ini = new Ini();
        ini.loadFromPath(str);
        return ini;
    }

    public void loadFromPath(String str) throws ConfigurationException {
        try {
            load(ResourceUtils.getInputStreamForPath(str));
        } catch (IOException e) {
            throw new ConfigurationException(e);
        }
    }

    public void load(String str) throws ConfigurationException {
        load(new Scanner(str));
    }

    public void load(InputStream inputStream) throws ConfigurationException {
        Objects.requireNonNull(inputStream, "InputStream argument cannot be null.");
        try {
            load(new InputStreamReader(inputStream, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new ConfigurationException(e);
        }
    }

    public void load(Reader reader) {
        Scanner scanner = new Scanner(reader);
        try {
            load(scanner);
            try {
                scanner.close();
            } catch (Exception e) {
                log.debug("Unable to cleanly close the InputStream scanner.  Non-critical - ignoring.", e);
            }
        } catch (Throwable th) {
            try {
                scanner.close();
            } catch (Exception e2) {
                log.debug("Unable to cleanly close the InputStream scanner.  Non-critical - ignoring.", e2);
            }
            throw th;
        }
    }

    public void merge(Map<String, Section> map) {
        if (map != null) {
            for (Map.Entry<String, Section> entry : map.entrySet()) {
                Section section = getSection(entry.getKey());
                if (section == null) {
                    section = addSection(entry.getKey());
                }
                section.putAll(entry.getValue());
            }
        }
    }

    private void addSection(String str, StringBuilder sb) {
        if (sb.length() > 0) {
            String string = sb.toString();
            if (StringUtils.clean(string) != null) {
                Section section = new Section(str, string);
                if (section.isEmpty()) {
                    return;
                }
                this.sections.put(str, section);
            }
        }
    }

    public void load(Scanner scanner) {
        StringBuilder sb = new StringBuilder();
        String str = "";
        while (scanner.hasNextLine()) {
            String strNextLine = scanner.nextLine();
            String strClean = StringUtils.clean(strNextLine);
            if (strClean != null && !strClean.startsWith(COMMENT_POUND) && !strClean.startsWith(COMMENT_SEMICOLON)) {
                String sectionName = getSectionName(strClean);
                if (sectionName != null) {
                    addSection(str, sb);
                    sb = new StringBuilder();
                    Logger logger = log;
                    if (logger.isDebugEnabled()) {
                        logger.debug("Parsing [" + sectionName + SECTION_SUFFIX);
                    }
                    str = sectionName;
                } else {
                    sb.append(strNextLine).append("\n");
                }
            }
        }
        addSection(str, sb);
    }

    protected static boolean isSectionHeader(String str) {
        String strClean = StringUtils.clean(str);
        return strClean != null && strClean.startsWith(SECTION_PREFIX) && strClean.endsWith(SECTION_SUFFIX);
    }

    protected static String getSectionName(String str) {
        String strClean = StringUtils.clean(str);
        if (isSectionHeader(strClean)) {
            return cleanName(strClean.substring(1, strClean.length() - 1));
        }
        return null;
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (obj instanceof Ini) {
            return this.sections.equals(((Ini) obj).sections);
        }
        return false;
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.sections.hashCode();
    }

    public String toString() {
        Map<String, Section> map = this.sections;
        if (map == null || map.isEmpty()) {
            return "<empty INI>";
        }
        StringBuilder sb = new StringBuilder("sections=");
        int i = 0;
        for (Section section : this.sections.values()) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(section.toString());
            i++;
        }
        return sb.toString();
    }

    @Override // java.util.Map
    public int size() {
        return this.sections.size();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.sections.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.sections.containsValue(obj);
    }

    @Override // java.util.Map
    public Section get(Object obj) {
        return this.sections.get(obj);
    }

    @Override // java.util.Map
    public Section put(String str, Section section) {
        return this.sections.put(str, section);
    }

    @Override // java.util.Map
    public Section remove(Object obj) {
        return this.sections.remove(obj);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Section> map) {
        this.sections.putAll(map);
    }

    @Override // java.util.Map
    public void clear() {
        this.sections.clear();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return Collections.unmodifiableSet(this.sections.keySet());
    }

    @Override // java.util.Map
    public Collection<Section> values() {
        return Collections.unmodifiableCollection(this.sections.values());
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Section>> entrySet() {
        return Collections.unmodifiableSet(this.sections.entrySet());
    }

    public static class Section implements Map<String, String> {
        private final String name;
        private final Map<String, String> props;

        private Section(String str) {
            Objects.requireNonNull(str, "name");
            this.name = str;
            this.props = new LinkedHashMap();
        }

        private Section(String str, String str2) {
            Map<String, String> linkedHashMap;
            Objects.requireNonNull(str, "name");
            this.name = str;
            if (StringUtils.hasText(str2)) {
                linkedHashMap = toMapProps(str2);
            } else {
                linkedHashMap = new LinkedHashMap<>();
            }
            if (linkedHashMap != null) {
                this.props = linkedHashMap;
            } else {
                this.props = new LinkedHashMap();
            }
        }

        private Section(Section section) {
            this(section.getName());
            putAll(section.props);
        }

        protected static boolean isContinued(String str) {
            if (!StringUtils.hasText(str)) {
                return false;
            }
            int i = 0;
            for (int length = str.length() - 1; length > 0 && str.charAt(length) == '\\'; length--) {
                i++;
            }
            return i % 2 != 0;
        }

        private static boolean isKeyValueSeparatorChar(char c) {
            return Character.isWhitespace(c) || c == ':' || c == '=';
        }

        private static boolean isCharEscaped(CharSequence charSequence, int i) {
            return i > 0 && charSequence.charAt(i) == '\\';
        }

        protected static String[] splitKeyValue(String str) {
            String strClean = StringUtils.clean(str);
            if (strClean == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            boolean z = true;
            for (int i = 0; i < strClean.length(); i++) {
                char cCharAt = strClean.charAt(i);
                if (z) {
                    if (isKeyValueSeparatorChar(cCharAt) && !isCharEscaped(strClean, i) && !isCharEscaped(strClean, i - 1)) {
                        z = false;
                    } else if (!isCharEscaped(strClean, i)) {
                        sb.append(cCharAt);
                    }
                } else if (sb2.length() != 0 || !isKeyValueSeparatorChar(cCharAt) || isCharEscaped(strClean, i)) {
                    sb2.append(cCharAt);
                }
            }
            String strClean2 = StringUtils.clean(sb.toString());
            String strClean3 = StringUtils.clean(sb2.toString());
            if (strClean2 != null && strClean3 != null) {
                Ini.log.trace("Discovered key/value pair: {} = {}", strClean2, strClean3);
                return new String[]{strClean2, strClean3};
            }
            throw new IllegalArgumentException("Line argument must contain a key and a value.  Only one string token was found.");
        }

        private static Map<String, String> toMapProps(String str) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            StringBuilder sb = new StringBuilder();
            Scanner scanner = new Scanner(str);
            while (scanner.hasNextLine()) {
                String strClean = StringUtils.clean(scanner.nextLine());
                if (isContinued(strClean)) {
                    sb.append(strClean.substring(0, strClean.length() - 1));
                } else {
                    sb.append(strClean);
                    String string = sb.toString();
                    sb = new StringBuilder();
                    String[] strArrSplitKeyValue = splitKeyValue(string);
                    linkedHashMap.put(strArrSplitKeyValue[0], strArrSplitKeyValue[1]);
                }
            }
            return linkedHashMap;
        }

        public String getName() {
            return this.name;
        }

        @Override // java.util.Map
        public void clear() {
            this.props.clear();
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            return this.props.containsKey(obj);
        }

        @Override // java.util.Map
        public boolean containsValue(Object obj) {
            return this.props.containsValue(obj);
        }

        @Override // java.util.Map
        public Set<Map.Entry<String, String>> entrySet() {
            return this.props.entrySet();
        }

        @Override // java.util.Map
        public String get(Object obj) {
            return this.props.get(obj);
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return this.props.isEmpty();
        }

        @Override // java.util.Map
        public Set<String> keySet() {
            return this.props.keySet();
        }

        @Override // java.util.Map
        public String put(String str, String str2) {
            return this.props.put(str, str2);
        }

        @Override // java.util.Map
        public void putAll(Map<? extends String, ? extends String> map) {
            this.props.putAll(map);
        }

        @Override // java.util.Map
        public String remove(Object obj) {
            return this.props.remove(obj);
        }

        @Override // java.util.Map
        public int size() {
            return this.props.size();
        }

        @Override // java.util.Map
        public Collection<String> values() {
            return this.props.values();
        }

        public String toString() {
            String name = getName();
            return "".equals(name) ? "<default>" : name;
        }

        @Override // java.util.Map
        public boolean equals(Object obj) {
            if (!(obj instanceof Section)) {
                return false;
            }
            Section section = (Section) obj;
            return getName().equals(section.getName()) && this.props.equals(section.props);
        }

        @Override // java.util.Map
        public int hashCode() {
            return (this.name.hashCode() * 31) + this.props.hashCode();
        }
    }
}
