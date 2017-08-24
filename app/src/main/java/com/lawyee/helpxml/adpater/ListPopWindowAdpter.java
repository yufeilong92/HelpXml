package com.lawyee.helpxml.adpater;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lawyee.helpxml.R;

import java.util.ArrayList;


/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: LawHelperAndroid
 * @Package com.laywee.lawyeehelper2.adpater
 * @Description: $显示适配器
 * @author: YFL
 * @date: 2017/3/23 9:47
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */


public class ListPopWindowAdpter extends BaseAdapter {

    private ArrayList<String> mData;
    private Context mContext;
    private final LayoutInflater mInflater;
    private boolean mIsSelect = false;
    int mPosition;
    String mItemName;
   boolean mIsShow=false;

    public ListPopWindowAdpter(ArrayList<String> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    /**
     * @param mIsSelect 是否选中
     * @param position  选中坐标
     */
    public void isSeclectCase(boolean mIsSelect, int position) {
        this.mIsSelect = mIsSelect;
        this.mPosition = position;
        notifyDataSetChanged();
    }

    /**
     *
     * @param item 显示要打勾的item
     */
    public void showSelectItem(String item) {
        this.mItemName = item;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData == null ? null : mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View converview, ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.item_lvpopwindow, null);
        ViewHolder holder = new ViewHolder(view);
        holder.mTvCaseItem.setText(mData.get(i));
        if (mIsSelect == true && mPosition == i) {//首次加载
            holder.mIvSelectItem.setVisibility(View.VISIBLE);
             mIsShow=true;
        } else {
            holder.mIvSelectItem.setVisibility(View.GONE);
        }
        if (mIsShow!=true&&!TextUtils.isEmpty(mItemName)&& mItemName.equals(mData.get(i))) {//点击加载
            holder.mIvSelectItem.setVisibility(View.VISIBLE);
        }
        return view;
    }


    public class ViewHolder {
        public View rootView;
        public TextView mTvCaseItem;
        public ImageView mIvSelectItem;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mTvCaseItem = (TextView) rootView.findViewById(R.id.tv_case_item);
            this.mIvSelectItem = (ImageView) rootView.findViewById(R.id.iv_select_item);
        }

    }
}
