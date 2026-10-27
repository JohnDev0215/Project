package net.rdrei.android.dirchooser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: classes3.dex */
public class DirectoryChooserFragment extends DialogFragment {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String ARG_INITIAL_DIRECTORY = "INITIAL_DIRECTORY";
    private static final String ARG_NEW_DIRECTORY_NAME = "NEW_DIRECTORY_NAME";
    public static final String KEY_CURRENT_DIRECTORY = "CURRENT_DIRECTORY";
    private static final String TAG = "DirectoryChooserFragment";
    private Button mBtnCancel;
    private Button mBtnConfirm;
    private ImageButton mBtnCreateFolder;
    private ImageButton mBtnNavUp;
    private FileObserver mFileObserver;
    private ArrayList<String> mFilenames;
    private File[] mFilesInDir;
    private String mInitialDirectory;
    private ListView mListDirectories;
    private ArrayAdapter<String> mListDirectoriesAdapter;
    private OnFragmentInteractionListener mListener;
    private String mNewDirectoryName;
    private File mSelectedDir;
    private TextView mTxtvSelectedFolder;

    public interface OnFragmentInteractionListener {
        void onCancelChooser();

        void onSelectDirectory(String str);
    }

    public static DirectoryChooserFragment newInstance(String str, String str2) {
        DirectoryChooserFragment directoryChooserFragment = new DirectoryChooserFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_NEW_DIRECTORY_NAME, str);
        bundle.putString(ARG_INITIAL_DIRECTORY, str2);
        directoryChooserFragment.setArguments(bundle);
        return directoryChooserFragment;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        File file = this.mSelectedDir;
        if (file != null) {
            bundle.putString(KEY_CURRENT_DIRECTORY, file.getAbsolutePath());
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() == null) {
            throw new IllegalArgumentException("You must create DirectoryChooserFragment via newInstance().");
        }
        this.mNewDirectoryName = getArguments().getString(ARG_NEW_DIRECTORY_NAME);
        this.mInitialDirectory = getArguments().getString(ARG_INITIAL_DIRECTORY);
        if (bundle != null) {
            this.mInitialDirectory = bundle.getString(KEY_CURRENT_DIRECTORY);
        }
        if (getShowsDialog()) {
            setStyle(1, 0);
        } else {
            setHasOptionsMenu(true);
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        File externalStorageDirectory;
        View viewInflate = layoutInflater.inflate(R.layout.directory_chooser, viewGroup, false);
        this.mBtnConfirm = (Button) viewInflate.findViewById(R.id.btnConfirm);
        this.mBtnCancel = (Button) viewInflate.findViewById(R.id.btnCancel);
        this.mBtnNavUp = (ImageButton) viewInflate.findViewById(R.id.btnNavUp);
        this.mBtnCreateFolder = (ImageButton) viewInflate.findViewById(R.id.btnCreateFolder);
        this.mTxtvSelectedFolder = (TextView) viewInflate.findViewById(R.id.txtvSelectedFolder);
        this.mListDirectories = (ListView) viewInflate.findViewById(R.id.directoryList);
        this.mBtnConfirm.setOnClickListener(new View.OnClickListener() { // from class: net.rdrei.android.dirchooser.DirectoryChooserFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DirectoryChooserFragment directoryChooserFragment = DirectoryChooserFragment.this;
                if (directoryChooserFragment.isValidFile(directoryChooserFragment.mSelectedDir)) {
                    DirectoryChooserFragment.this.returnSelectedFolder();
                }
            }
        });
        this.mBtnCancel.setOnClickListener(new View.OnClickListener() { // from class: net.rdrei.android.dirchooser.DirectoryChooserFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DirectoryChooserFragment.this.mListener.onCancelChooser();
            }
        });
        this.mListDirectories.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: net.rdrei.android.dirchooser.DirectoryChooserFragment.3
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                DirectoryChooserFragment.this.debug("Selected index: %d", Integer.valueOf(i));
                if (DirectoryChooserFragment.this.mFilesInDir == null || i < 0 || i >= DirectoryChooserFragment.this.mFilesInDir.length) {
                    return;
                }
                DirectoryChooserFragment directoryChooserFragment = DirectoryChooserFragment.this;
                directoryChooserFragment.changeDirectory(directoryChooserFragment.mFilesInDir[i]);
            }
        });
        this.mBtnNavUp.setOnClickListener(new View.OnClickListener() { // from class: net.rdrei.android.dirchooser.DirectoryChooserFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                File parentFile;
                if (DirectoryChooserFragment.this.mSelectedDir == null || (parentFile = DirectoryChooserFragment.this.mSelectedDir.getParentFile()) == null) {
                    return;
                }
                DirectoryChooserFragment.this.changeDirectory(parentFile);
            }
        });
        this.mBtnCreateFolder.setOnClickListener(new View.OnClickListener() { // from class: net.rdrei.android.dirchooser.DirectoryChooserFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DirectoryChooserFragment.this.openNewFolderDialog();
            }
        });
        if (!getShowsDialog()) {
            this.mBtnCreateFolder.setVisibility(8);
        }
        adjustResourceLightness();
        this.mFilenames = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, this.mFilenames);
        this.mListDirectoriesAdapter = arrayAdapter;
        this.mListDirectories.setAdapter((ListAdapter) arrayAdapter);
        if (this.mInitialDirectory != null && isValidFile(new File(this.mInitialDirectory))) {
            externalStorageDirectory = new File(this.mInitialDirectory);
        } else {
            externalStorageDirectory = Environment.getExternalStorageDirectory();
        }
        changeDirectory(externalStorageDirectory);
        return viewInflate;
    }

    private void adjustResourceLightness() {
        int color;
        TypedArray typedArrayObtainStyledAttributes;
        Resources.Theme theme = getActivity().getTheme();
        if (theme == null || (typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(new int[]{android.R.attr.colorBackground})) == null) {
            color = 16777215;
        } else {
            color = typedArrayObtainStyledAttributes.getColor(0, ViewCompat.MEASURED_SIZE_MASK);
            typedArrayObtainStyledAttributes.recycle();
        }
        if (color == 16777215 || (((double) Color.red(color)) * 0.21d) + (((double) Color.green(color)) * 0.72d) + (((double) Color.blue(color)) * 0.07d) >= 128.0d) {
            return;
        }
        this.mBtnNavUp.setImageResource(R.drawable.navigation_up_light);
        this.mBtnCreateFolder.setImageResource(R.drawable.ic_action_create_light);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException unused) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        FileObserver fileObserver = this.mFileObserver;
        if (fileObserver != null) {
            fileObserver.stopWatching();
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        FileObserver fileObserver = this.mFileObserver;
        if (fileObserver != null) {
            fileObserver.startWatching();
        }
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.directory_chooser, menu);
        MenuItem menuItemFindItem = menu.findItem(R.id.new_folder_item);
        if (menuItemFindItem == null) {
            return;
        }
        menuItemFindItem.setVisible(isValidFile(this.mSelectedDir) && this.mNewDirectoryName != null);
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.new_folder_item) {
            openNewFolderDialog();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openNewFolderDialog() {
        new AlertDialog.Builder(getActivity()).setTitle(R.string.create_folder_label).setMessage(String.format(getString(R.string.create_folder_msg), this.mNewDirectoryName)).setNegativeButton(R.string.cancel_label, new DialogInterface.OnClickListener() { // from class: net.rdrei.android.dirchooser.DirectoryChooserFragment.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton(R.string.confirm_label, new DialogInterface.OnClickListener() { // from class: net.rdrei.android.dirchooser.DirectoryChooserFragment.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Toast.makeText(DirectoryChooserFragment.this.getActivity(), DirectoryChooserFragment.this.createFolder(), 0).show();
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void debug(String str, Object... objArr) {
        Log.d(TAG, String.format(str, objArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeDirectory(File file) {
        if (file == null) {
            debug("Could not change folder: dir was null", new Object[0]);
        } else if (!file.isDirectory()) {
            debug("Could not change folder: dir is no directory", new Object[0]);
        } else {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                int i = 0;
                for (File file2 : fileArrListFiles) {
                    if (file2.isDirectory()) {
                        i++;
                    }
                }
                this.mFilesInDir = new File[i];
                this.mFilenames.clear();
                int i2 = 0;
                int i3 = 0;
                while (i2 < i) {
                    if (fileArrListFiles[i3].isDirectory()) {
                        this.mFilesInDir[i2] = fileArrListFiles[i3];
                        this.mFilenames.add(fileArrListFiles[i3].getName());
                        i2++;
                    }
                    i3++;
                }
                Arrays.sort(this.mFilesInDir);
                Collections.sort(this.mFilenames);
                this.mSelectedDir = file;
                this.mTxtvSelectedFolder.setText(file.getAbsolutePath());
                this.mListDirectoriesAdapter.notifyDataSetChanged();
                FileObserver fileObserverCreateFileObserver = createFileObserver(file.getAbsolutePath());
                this.mFileObserver = fileObserverCreateFileObserver;
                fileObserverCreateFileObserver.startWatching();
                debug("Changed directory to %s", file.getAbsolutePath());
            } else {
                debug("Could not change folder: contents of dir were null", new Object[0]);
            }
        }
        refreshButtonState();
    }

    private void refreshButtonState() {
        File file;
        if (getActivity() == null || (file = this.mSelectedDir) == null) {
            return;
        }
        this.mBtnConfirm.setEnabled(isValidFile(file));
        getActivity().invalidateOptionsMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDirectory() {
        File file = this.mSelectedDir;
        if (file != null) {
            changeDirectory(file);
        }
    }

    private FileObserver createFileObserver(String str) {
        return new FileObserver(str, 960) { // from class: net.rdrei.android.dirchooser.DirectoryChooserFragment.8
            @Override // android.os.FileObserver
            public void onEvent(int i, String str2) {
                DirectoryChooserFragment.this.debug("FileObserver received event %d", Integer.valueOf(i));
                Activity activity = DirectoryChooserFragment.this.getActivity();
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() { // from class: net.rdrei.android.dirchooser.DirectoryChooserFragment.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            DirectoryChooserFragment.this.refreshDirectory();
                        }
                    });
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void returnSelectedFolder() {
        File file = this.mSelectedDir;
        if (file != null) {
            debug("Returning %s as result", file.getAbsolutePath());
            this.mListener.onSelectDirectory(this.mSelectedDir.getAbsolutePath());
        } else {
            this.mListener.onCancelChooser();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int createFolder() {
        File file;
        if (this.mNewDirectoryName != null && (file = this.mSelectedDir) != null && file.canWrite()) {
            File file2 = new File(this.mSelectedDir, this.mNewDirectoryName);
            if (!file2.exists()) {
                if (file2.mkdir()) {
                    return R.string.create_folder_success;
                }
                return R.string.create_folder_error;
            }
            return R.string.create_folder_error_already_exists;
        }
        File file3 = this.mSelectedDir;
        if (file3 != null && !file3.canWrite()) {
            return R.string.create_folder_error_no_write_access;
        }
        return R.string.create_folder_error;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidFile(File file) {
        return file != null && file.isDirectory() && file.canRead() && file.canWrite();
    }
}
