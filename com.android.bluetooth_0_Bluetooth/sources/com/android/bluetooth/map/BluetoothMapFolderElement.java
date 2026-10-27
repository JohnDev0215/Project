package com.android.bluetooth.map;

import android.util.Xml;
import androidx.core.app.NotificationCompat;
import com.android.bluetooth.Utils;
import com.android.internal.util.FastXmlSerializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapFolderElement implements Comparable<BluetoothMapFolderElement> {
    private static final boolean D = false;
    private static final String TAG = "BluetoothMapFolderElement";
    private static final boolean V = false;
    private String mName;
    private BluetoothMapFolderElement mParent;
    private long mFolderId = -1;
    private boolean mHasSmsMmsContent = false;
    private boolean mHasImContent = false;
    private boolean mHasEmailContent = false;
    private boolean mIgnore = false;
    private HashMap<String, BluetoothMapFolderElement> mSubFolders = new HashMap<>();

    public BluetoothMapFolderElement(String name, BluetoothMapFolderElement parrent) {
        this.mParent = null;
        this.mName = name;
        this.mParent = parrent;
    }

    public void setIngore(boolean ignore) {
        this.mIgnore = ignore;
    }

    public boolean shouldIgnore() {
        return this.mIgnore;
    }

    public String getName() {
        return this.mName;
    }

    public boolean hasSmsMmsContent() {
        return this.mHasSmsMmsContent;
    }

    public long getFolderId() {
        return this.mFolderId;
    }

    public boolean hasEmailContent() {
        return this.mHasEmailContent;
    }

    public void setFolderId(long folderId) {
        this.mFolderId = folderId;
    }

    public void setHasSmsMmsContent(boolean hasSmsMmsContent) {
        this.mHasSmsMmsContent = hasSmsMmsContent;
    }

    public void setHasEmailContent(boolean hasEmailContent) {
        this.mHasEmailContent = hasEmailContent;
    }

    public void setHasImContent(boolean hasImContent) {
        this.mHasImContent = hasImContent;
    }

    public boolean hasImContent() {
        return this.mHasImContent;
    }

    public BluetoothMapFolderElement getParent() {
        return this.mParent;
    }

    public String getFullPath() {
        StringBuilder sb = new StringBuilder(this.mName);
        for (BluetoothMapFolderElement current = this.mParent; current != null; current = current.getParent()) {
            if (current.getParent() != null) {
                sb.insert(0, current.mName + "/");
            }
        }
        return sb.toString();
    }

    public BluetoothMapFolderElement getFolderByName(String name) {
        BluetoothMapFolderElement folderElement = getRoot().getSubFolder("telecom").getSubFolder(NotificationCompat.CATEGORY_MESSAGE).getSubFolder(name);
        if (folderElement != null && folderElement.getFolderId() == -1) {
            return null;
        }
        return folderElement;
    }

    public BluetoothMapFolderElement getFolderById(long id) {
        return getFolderById(id, this);
    }

    public static BluetoothMapFolderElement getFolderById(long id, BluetoothMapFolderElement folderStructure) {
        if (folderStructure == null) {
            return null;
        }
        return findFolderById(id, folderStructure.getRoot());
    }

    private static BluetoothMapFolderElement findFolderById(long id, BluetoothMapFolderElement folder) {
        if (folder.getFolderId() == id) {
            return folder;
        }
        for (BluetoothMapFolderElement subFolder : (BluetoothMapFolderElement[]) folder.mSubFolders.values().toArray(new BluetoothMapFolderElement[folder.mSubFolders.size()])) {
            BluetoothMapFolderElement ret = findFolderById(id, subFolder);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }

    public BluetoothMapFolderElement getRoot() {
        BluetoothMapFolderElement rootFolder = this;
        while (rootFolder.getParent() != null) {
            rootFolder = rootFolder.getParent();
        }
        return rootFolder;
    }

    public BluetoothMapFolderElement addFolder(String name) {
        String name2 = name.toLowerCase(Locale.US);
        BluetoothMapFolderElement newFolder = this.mSubFolders.get(name2);
        if (newFolder == null) {
            BluetoothMapFolderElement newFolder2 = new BluetoothMapFolderElement(name2, this);
            this.mSubFolders.put(name2, newFolder2);
            return newFolder2;
        }
        return newFolder;
    }

    public BluetoothMapFolderElement addSmsMmsFolder(String name) {
        BluetoothMapFolderElement newFolder = addFolder(name);
        newFolder.setHasSmsMmsContent(true);
        return newFolder;
    }

    public BluetoothMapFolderElement addImFolder(String name, long idFolder) {
        BluetoothMapFolderElement newFolder = addFolder(name);
        newFolder.setHasImContent(true);
        newFolder.setFolderId(idFolder);
        return newFolder;
    }

    public BluetoothMapFolderElement addEmailFolder(String name, long emailFolderId) {
        BluetoothMapFolderElement newFolder = addFolder(name);
        newFolder.setFolderId(emailFolderId);
        newFolder.setHasEmailContent(true);
        return newFolder;
    }

    public int getSubFolderCount() {
        return this.mSubFolders.size();
    }

    public BluetoothMapFolderElement getSubFolder(String folderName) {
        return this.mSubFolders.get(folderName.toLowerCase());
    }

    public byte[] encode(int offset, int count) throws UnsupportedEncodingException {
        StringWriter sw = new StringWriter();
        FastXmlSerializer fastXmlSerializer = new FastXmlSerializer(0);
        BluetoothMapFolderElement[] folders = (BluetoothMapFolderElement[]) this.mSubFolders.values().toArray(new BluetoothMapFolderElement[this.mSubFolders.size()]);
        if (offset > this.mSubFolders.size()) {
            throw new IllegalArgumentException("FolderListingEncode: offset > subFolders.size()");
        }
        int stopIndex = offset + count;
        if (stopIndex > this.mSubFolders.size()) {
            stopIndex = this.mSubFolders.size();
        }
        try {
            fastXmlSerializer.setOutput(sw);
            fastXmlSerializer.startDocument("UTF-8", true);
            fastXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            fastXmlSerializer.startTag(null, "folder-listing");
            fastXmlSerializer.attribute(null, "version", "1.0");
            for (int i = offset; i < stopIndex; i++) {
                fastXmlSerializer.startTag(null, "folder");
                fastXmlSerializer.attribute(null, "name", folders[i].getName());
                fastXmlSerializer.endTag(null, "folder");
            }
            fastXmlSerializer.endTag(null, "folder-listing");
            fastXmlSerializer.endDocument();
            return sw.toString().getBytes("UTF-8");
        } catch (IOException e) {
            throw new IllegalArgumentException("error encoding folderElement");
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException("error encoding folderElement");
        } catch (IllegalStateException e3) {
            throw new IllegalArgumentException("error encoding folderElement");
        }
    }

    public void appendSubfolders(InputStream xmlDocument) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(xmlDocument, "UTF-8");
            while (true) {
                int type = parser.next();
                if (type == 3 || type == 1) {
                    break;
                }
                if (parser.getEventType() == 2) {
                    String name = parser.getName();
                    if (!name.equalsIgnoreCase("folder-listing")) {
                        Utils.skipCurrentTag(parser);
                    }
                    readFolders(parser);
                }
            }
        } finally {
            xmlDocument.close();
        }
    }

    public void readFolders(XmlPullParser parser) throws XmlPullParserException, IOException {
        while (true) {
            int type = parser.next();
            if (type != 3 && type != 1) {
                if (parser.getEventType() == 2) {
                    String name = parser.getName();
                    if (!name.trim().equalsIgnoreCase("folder")) {
                        Utils.skipCurrentTag(parser);
                    } else {
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            if (parser.getAttributeName(i).trim().equalsIgnoreCase("name")) {
                                BluetoothMapFolderElement element = addFolder(parser.getAttributeValue(i).trim());
                                element.setHasEmailContent(this.mHasEmailContent);
                                element.setHasImContent(this.mHasImContent);
                                element.setHasSmsMmsContent(this.mHasSmsMmsContent);
                            }
                        }
                        parser.nextTag();
                    }
                }
            } else {
                return;
            }
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(BluetoothMapFolderElement another) {
        if (another == null) {
            return 1;
        }
        int ret = this.mName.compareToIgnoreCase(another.mName);
        if (ret == 0 && (ret = this.mSubFolders.size() - another.mSubFolders.size()) == 0) {
            for (BluetoothMapFolderElement subfolder : this.mSubFolders.values()) {
                BluetoothMapFolderElement subfolderAnother = another.mSubFolders.get(subfolder.getName());
                if (subfolderAnother == null) {
                    return 1;
                }
                ret = subfolder.compareTo(subfolderAnother);
                if (ret != 0) {
                    return ret;
                }
            }
        }
        return ret;
    }
}
