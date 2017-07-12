package com.sjsm.checkboxinlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectCodeAdapter extends BaseAdapter {

	private List<Map<String, String>> mProjectData;
	private Context mContext;
	private OnCBClickListener OnCBClickListener;//定义对象
	private static HashMap<Integer, Boolean> isSelected;// 用来控制CheckBox的选中状况
	
	public interface OnCBClickListener {
	    void onCBClick(String name);
	}
	
	public void setOnCBClickListener(OnCBClickListener listener) {
	    this.OnCBClickListener = listener;
	}

	public ProjectCodeAdapter(List<Map<String, String>> projectData, Context context) {
		mProjectData = projectData;
		mContext = context;
		isSelected = new HashMap<Integer, Boolean>();
		// 初始化数据
		initDate();
	}

	// 初始化isSelected的数据
	private void initDate() {
		for (int i = 0; i < mProjectData.size(); i++) {
			getIsSelected().put(i, true);
		}
	}

	@Override
	public int getCount() {
		return mProjectData.size();
	}

	@Override
	public Object getItem(int position) {
		return mProjectData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int index = position;
		final ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.ProjectCode = (TextView) convertView.findViewById(R.id.ProjectCode);
			viewHolder.cbProjectCode = (CheckBox) convertView.findViewById(R.id.cbProjectCode);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.ProjectCode.setText(mProjectData.get(position).get("ProCode"));
		// 根据isSelected来设置checkbox的选中状况
		
		viewHolder.cbProjectCode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				// TODO Auto-generated method stub
				isSelected.put(index, isChecked);
				 notifyDataSetChanged();
			}
		});
		viewHolder.cbProjectCode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(OnCBClickListener != null){
					OnCBClickListener.onCBClick(mProjectData.get(index).get("ProCode"));
				}
			}
		});
		viewHolder.cbProjectCode.setChecked(getIsSelected().get(index));
		return convertView;
	}

	public class ViewHolder {
		public TextView ProjectCode;
		public CheckBox cbProjectCode;
	}

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		ProjectCodeAdapter.isSelected = isSelected;
	}
}
