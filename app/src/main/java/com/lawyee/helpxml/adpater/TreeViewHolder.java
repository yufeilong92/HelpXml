package com.lawyee.helpxml.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lawyee.helpxml.R;
import com.unnamed.b.atv.model.TreeNode;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: MyHolder
 * @Package com.laywee.lawyeehelper2.view
 * @Description: TreeView的Holder
 * @author: czq
 * @date: 2017/3/17 09:50
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: ${year} www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class TreeViewHolder extends TreeNode.BaseNodeViewHolder<TreeViewHolder.IconTreeItem> {
    public TreeViewHolder(Context context) {
        super(context);
    }
    @Override
    public View createNodeView(TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_profile_node, null, false);
        TextView tvValue = (TextView) view.findViewById(R.id.tv_node_value);
        tvValue.setText(value.text);
        return view;
    }
    public static class IconTreeItem {
        public String text;

        public IconTreeItem(String text) {
            this.text = text;
        }
    }
}
