package com.android.bluetooth.map;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.bluetooth.R;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapSettingsAdapter extends BaseExpandableListAdapter {
    private static final boolean D = false;
    private static final String TAG = "BluetoothMapSettingsAdapter";
    private static final boolean V = false;
    public Activity mActivity;
    private int[] mGroupStatus;
    public LayoutInflater mInflater;
    private ArrayList<BluetoothMapAccountItem> mMainGroup;
    ArrayList<Boolean> mPositionArray;
    private LinkedHashMap<BluetoothMapAccountItem, ArrayList<BluetoothMapAccountItem>> mProupList;
    private boolean mCheckAll = true;
    private int mSlotsLeft = 10;

    public BluetoothMapSettingsAdapter(Activity act, ExpandableListView listView, LinkedHashMap<BluetoothMapAccountItem, ArrayList<BluetoothMapAccountItem>> groupsList, int enabledAccountsCounts) {
        this.mActivity = act;
        this.mProupList = groupsList;
        this.mInflater = act.getLayoutInflater();
        this.mGroupStatus = new int[groupsList.size()];
        this.mSlotsLeft -= enabledAccountsCounts;
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() { // from class: com.android.bluetooth.map.BluetoothMapSettingsAdapter.1
            @Override // android.widget.ExpandableListView.OnGroupExpandListener
            public void onGroupExpand(int groupPosition) {
                BluetoothMapAccountItem group = (BluetoothMapAccountItem) BluetoothMapSettingsAdapter.this.mMainGroup.get(groupPosition);
                if (((ArrayList) BluetoothMapSettingsAdapter.this.mProupList.get(group)).size() > 0) {
                    BluetoothMapSettingsAdapter.this.mGroupStatus[groupPosition] = 1;
                }
            }
        });
        this.mMainGroup = new ArrayList<>();
        for (Map.Entry<BluetoothMapAccountItem, ArrayList<BluetoothMapAccountItem>> mapEntry : this.mProupList.entrySet()) {
            this.mMainGroup.add(mapEntry.getKey());
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public BluetoothMapAccountItem getChild(int groupPosition, int childPosition) {
        BluetoothMapAccountItem item = this.mMainGroup.get(groupPosition);
        return this.mProupList.get(item).get(childPosition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<BluetoothMapAccountItem> getChild(BluetoothMapAccountItem group) {
        return this.mProupList.get(group);
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int groupPosition, int childPosition) {
        return 0L;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        if (convertView == null) {
            convertView = this.mInflater.inflate(R.layout.bluetooth_map_settings_account_item, (ViewGroup) null);
            holder = new ChildHolder();
            holder.cb = (CheckBox) convertView.findViewById(R.id.bluetooth_map_settings_item_check);
            holder.title = (TextView) convertView.findViewById(R.id.bluetooth_map_settings_item_text_view);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        final BluetoothMapAccountItem child = getChild(groupPosition, childPosition);
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.bluetooth.map.BluetoothMapSettingsAdapter.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                BluetoothMapAccountItem parentGroup = BluetoothMapSettingsAdapter.this.getGroup(groupPosition);
                boolean oldIsChecked = child.mIsChecked;
                child.mIsChecked = isChecked;
                if (isChecked) {
                    ArrayList<BluetoothMapAccountItem> childList = BluetoothMapSettingsAdapter.this.getChild(parentGroup);
                    int childIndex = childList.indexOf(child);
                    boolean isAllChildClicked = true;
                    if (BluetoothMapSettingsAdapter.this.mSlotsLeft - childList.size() >= 0) {
                        int i = 0;
                        while (true) {
                            if (i >= childList.size()) {
                                break;
                            }
                            if (i != childIndex) {
                                BluetoothMapAccountItem siblings = childList.get(i);
                                if (!siblings.mIsChecked) {
                                    isAllChildClicked = false;
                                    BluetoothMapSettingsDataHolder.sCheckedChilds.put(child.getName(), parentGroup.getName());
                                    break;
                                }
                            }
                            i++;
                        }
                    } else {
                        BluetoothMapSettingsAdapter bluetoothMapSettingsAdapter = BluetoothMapSettingsAdapter.this;
                        bluetoothMapSettingsAdapter.showWarning(bluetoothMapSettingsAdapter.mActivity.getString(R.string.bluetooth_map_settings_no_account_slots_left));
                        isAllChildClicked = false;
                        child.mIsChecked = false;
                    }
                    if (isAllChildClicked) {
                        parentGroup.mIsChecked = true;
                        if (!BluetoothMapSettingsDataHolder.sCheckedChilds.containsKey(child.getName())) {
                            BluetoothMapSettingsDataHolder.sCheckedChilds.put(child.getName(), parentGroup.getName());
                        }
                        BluetoothMapSettingsAdapter.this.mCheckAll = false;
                    }
                } else if (!parentGroup.mIsChecked) {
                    BluetoothMapSettingsAdapter.this.mCheckAll = true;
                    BluetoothMapSettingsDataHolder.sCheckedChilds.remove(child.getName());
                } else {
                    parentGroup.mIsChecked = false;
                    BluetoothMapSettingsAdapter.this.mCheckAll = false;
                    BluetoothMapSettingsDataHolder.sCheckedChilds.remove(child.getName());
                }
                BluetoothMapSettingsAdapter.this.notifyDataSetChanged();
                if (child.mIsChecked != oldIsChecked) {
                    BluetoothMapSettingsAdapter.this.updateAccount(child);
                }
            }
        });
        holder.cb.setChecked(child.mIsChecked);
        holder.title.setText(child.getName());
        return convertView;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int groupPosition) {
        BluetoothMapAccountItem item = this.mMainGroup.get(groupPosition);
        return this.mProupList.get(item).size();
    }

    @Override // android.widget.ExpandableListAdapter
    public BluetoothMapAccountItem getGroup(int groupPosition) {
        return this.mMainGroup.get(groupPosition);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.mMainGroup.size();
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.ExpandableListAdapter
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.ExpandableListAdapter
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int groupPosition) {
        return 0L;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if (convertView == null) {
            convertView = this.mInflater.inflate(R.layout.bluetooth_map_settings_account_group, (ViewGroup) null);
            holder = new GroupHolder();
            holder.cb = (CheckBox) convertView.findViewById(R.id.bluetooth_map_settings_group_checkbox);
            holder.imageView = (ImageView) convertView.findViewById(R.id.bluetooth_map_settings_group_icon);
            holder.title = (TextView) convertView.findViewById(R.id.bluetooth_map_settings_group_text_view);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        final BluetoothMapAccountItem groupItem = getGroup(groupPosition);
        holder.imageView.setImageDrawable(groupItem.getIcon());
        holder.title.setText(groupItem.getName());
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.bluetooth.map.BluetoothMapSettingsAdapter.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (BluetoothMapSettingsAdapter.this.mCheckAll) {
                    ArrayList<BluetoothMapAccountItem> childItem = BluetoothMapSettingsAdapter.this.getChild(groupItem);
                    for (BluetoothMapAccountItem children : childItem) {
                        boolean oldIsChecked = children.mIsChecked;
                        if (BluetoothMapSettingsAdapter.this.mSlotsLeft > 0) {
                            children.mIsChecked = isChecked;
                            if (oldIsChecked != children.mIsChecked) {
                                BluetoothMapSettingsAdapter.this.updateAccount(children);
                            }
                        } else {
                            BluetoothMapSettingsAdapter bluetoothMapSettingsAdapter = BluetoothMapSettingsAdapter.this;
                            bluetoothMapSettingsAdapter.showWarning(bluetoothMapSettingsAdapter.mActivity.getString(R.string.bluetooth_map_settings_no_account_slots_left));
                            isChecked = false;
                        }
                    }
                }
                groupItem.mIsChecked = isChecked;
                BluetoothMapSettingsAdapter.this.notifyDataSetChanged();
                new Handler().postDelayed(new Runnable() { // from class: com.android.bluetooth.map.BluetoothMapSettingsAdapter.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!BluetoothMapSettingsAdapter.this.mCheckAll) {
                            BluetoothMapSettingsAdapter.this.mCheckAll = true;
                        }
                    }
                }, 50L);
            }
        });
        holder.cb.setChecked(groupItem.mIsChecked);
        return convertView;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class GroupHolder {
        public CheckBox cb;
        public ImageView imageView;
        public TextView title;

        private GroupHolder() {
        }
    }

    private class ChildHolder {
        public CheckBox cb;
        public TextView title;

        private ChildHolder() {
        }
    }

    public void updateAccount(BluetoothMapAccountItem bluetoothMapAccountItem) {
        updateSlotCounter(bluetoothMapAccountItem.mIsChecked);
        ContentResolver contentResolver = this.mActivity.getContentResolver();
        Uri uri = Uri.parse(bluetoothMapAccountItem.mBase_uri_no_account + "/" + BluetoothMapContract.TABLE_ACCOUNT);
        ContentValues contentValues = new ContentValues();
        contentValues.put(BluetoothMapContract.AccountColumns.FLAG_EXPOSE, Integer.valueOf(bluetoothMapAccountItem.mIsChecked ? 1 : 0));
        contentValues.put("_id", bluetoothMapAccountItem.getId());
        contentResolver.update(uri, contentValues, null, null);
    }

    private void updateSlotCounter(boolean isChecked) {
        CharSequence text;
        if (isChecked) {
            this.mSlotsLeft--;
        } else {
            this.mSlotsLeft++;
        }
        if (this.mSlotsLeft <= 0) {
            text = this.mActivity.getString(R.string.bluetooth_map_settings_no_account_slots_left);
        } else {
            text = this.mActivity.getString(R.string.bluetooth_map_settings_count) + " " + String.valueOf(this.mSlotsLeft);
        }
        Toast toast = Toast.makeText(this.mActivity, text, 0);
        toast.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWarning(String text) {
        Toast toast = Toast.makeText(this.mActivity, text, 0);
        toast.show();
    }
}
